/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.contenttype;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.EmptyResourceException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveURIConverterImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.DeleteOnExitUtility;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.xml.GeneralXmlDocumentReader;
import org.eclipse.jst.j2ee.internal.xml.XmlDocumentReader;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


public class J2EESpecFinder {

	public static String getFastSpecVersion(InputStream in) {

		InputSource source = null;

		try {
			DocumentType docType = null;
			source = new InputSource(in);
			GeneralXmlDocumentReader aReader = new XmlDocumentReader(source);
			aReader.setValidate(false);
			Document aDocument = aReader.parseDocument();
			if (aDocument != null) {
	              docType = aDocument.getDoctype();
	           }
	           int version = -1;
	           if (null != docType) {
	              String publicID = docType.getPublicId();
	              String systemID = docType.getSystemId();
	              if (publicID != null && systemID != null) {
	                 version = lookupVersion(docType, publicID, systemID);
	              }
	           }
	           if (version == -1) {
	              String schemaName = null;
	              String versionString = null;
	              if (aDocument != null) {
	                 if (null == docType) {
	                    NamedNodeMap map = aDocument.getDocumentElement().getAttributes();
	                    if (null != map) {
	                       Node schemaNode = map.getNamedItem("xsi:schemaLocation"); //$NON-NLS-1$
	                       if (null != schemaNode) {
	                          schemaName = schemaNode.getNodeValue().trim();
	                       }
	                       Node versionNode = map.getNamedItem("version");//$NON-NLS-1$
	                       if (null != versionNode) {
	                          versionString = versionNode.getNodeValue();
	                       }
	                    }
	                 }
	              }
	              if (null != schemaName) {
	                 version = lookupVersion(schemaName);
	              }
	              if (version == -1) {
	                 version = parseVersionString(schemaName, versionString);
	              }
	           }

	           return J2EEVersionUtil.convertVersionIntToString(version);
			
		} catch (Exception ex) {
			return null;
		} finally {
			try {
				if (in != null)
					in.reset();
			} catch (IOException ex) {
				// Ignore
			}
		}
	}

	private static boolean isApplicationClientFile(String name) {
		return (name.indexOf(J2EEConstants.APP_CLIENT_DOCTYPE) != -1);
	}

	private static boolean isEARFile(String name) {
		return (name.indexOf(J2EEConstants.APPLICATION_DOCTYPE) != -1);
	}

	private static boolean isEJBJarFile(String name) {
		return (name.indexOf(J2EEConstants.EJBJAR_DOCTYPE) != -1);
	}

	private static boolean isRARFile(String name) {
		
		return (name.indexOf(J2EEConstants.CONNECTOR_DOCTYPE) != -1);
	}

	private static boolean isWARFile(String name) {
		
		return (name.indexOf(J2EEConstants.WEBAPP_DOCTYPE) != -1);
	}

	private static int lookupVersion(String schemaName) {
	    int version = -1;
	    if (isEARFile(schemaName)) {
	       if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_5)) {          //EJB3
	          version = J2EEVersionConstants.JEE_5_0_ID;                        //EJB3
	       } else if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_1_4)) {
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       }
	    } else if (isEJBJarFile(schemaName)) {
	       if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_3_0_NS)) != -1) { //EJB3
	          version = J2EEVersionConstants.EJB_3_0_ID;                         //EJB3
	       } else if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_2_1_NS)) != -1) {
	          version = J2EEVersionConstants.EJB_2_1_ID;
	       }
	    } else if (isApplicationClientFile(schemaName)) {
	       if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_5)) {           //EJB3
	          version = J2EEVersionConstants.JEE_5_0_ID;                        //EJB3
	       } else if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_1_4)) {
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       }
	    } else if (isWARFile(schemaName)) {
	       if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_2_5)) {             //EJB3
	          version = J2EEVersionConstants.WEB_2_5_ID;                         //EJB3
	       } else if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_2_4)) {
	          version = J2EEVersionConstants.WEB_2_4_ID;
	       }
	    } else if (isRARFile(schemaName)) {
	       if (schemaName.equals(J2EEConstants.CONNECTOR_SCHEMA_1_5)) {
	          version = J2EEVersionConstants.JCA_1_5_ID;
	       }
	    }
	    return version;
	 }

	private static int lookupVersion(DocumentType aType, String publicID, String systemID) {
		int version = -1;
		if (isEARFile(aType.getName())) {
			if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_3) && (systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_3)||systemID.equals(J2EEConstants.APPLICATION_ALT_SYSTEMID_1_3))) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_2) && (systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_2)||systemID.equals(J2EEConstants.APPLICATION_ALT_SYSTEMID_1_2))) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (isEJBJarFile(aType.getName())) {
			if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_2_0) && (systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_2_0)||systemID.equals(J2EEConstants.EJBJAR_ALT_SYSTEMID_2_0))) {
				version = J2EEVersionConstants.EJB_2_0_ID;
			} else if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_1_1) && (systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_1_1)||systemID.equals(J2EEConstants.EJBJAR_ALT_SYSTEMID_1_1))) {
				version = J2EEVersionConstants.EJB_1_1_ID;
			} else {
				version = J2EEVersionConstants.EJB_2_1_ID;
			}
		} else if (isApplicationClientFile(aType.getName())) {
			if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_3) && (systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_3)||systemID.equals(J2EEConstants.APP_CLIENT_ALT_SYSTEMID_1_3))) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_2) && (systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_2)||systemID.equals(J2EEConstants.APP_CLIENT_ALT_SYSTEMID_1_2))) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (isWARFile(aType.getName())) {
			if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_3) && (systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_3)||systemID.equals(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_3))) {
				version = J2EEVersionConstants.WEB_2_3_ID;
			} else if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_2) && (systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_2)||systemID.equals(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_2))) {
				version = J2EEVersionConstants.WEB_2_2_ID;
			} else {
				version = J2EEVersionConstants.WEB_2_4_ID;
			}
		} else if (isRARFile(aType.getName())) {
			if (publicID.equals(J2EEConstants.CONNECTOR_PUBLICID_1_0) && (systemID.equals(J2EEConstants.CONNECTOR_SYSTEMID_1_0)||systemID.equals(J2EEConstants.CONNECTOR_ALT_SYSTEMID_1_0))) {
				version = J2EEVersionConstants.JCA_1_0_ID;
			} else {
				version = J2EEVersionConstants.JCA_1_5_ID;
			}
		}
		return version;
	}

	private static int parseVersionString(String schemaName, String versionAttr) {
	    int version = -1;
	    if (isEARFile(schemaName)) {
	       if (null == versionAttr) {
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
	          version = J2EEVersionConstants.J2EE_1_2_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
	          version = J2EEVersionConstants.J2EE_1_3_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_4_TEXT)) { 
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_5_0_TEXT)) {   
	          version = J2EEVersionConstants.JEE_5_0_ID;                          
	       }
	    } else if (isEJBJarFile(schemaName)) {
	       if (null == versionAttr) {
	          version = J2EEVersionConstants.EJB_2_1_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_1_TEXT)) {
	          version = J2EEVersionConstants.EJB_1_1_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_0_TEXT)) {
	          version = J2EEVersionConstants.EJB_2_0_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_1_TEXT)) { 
	          version = J2EEVersionConstants.EJB_2_1_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) { 
	          version = J2EEVersionConstants.EJB_3_0_ID;                           
	       }
	    } else if (isApplicationClientFile(schemaName)) {
	       if (null == versionAttr) {
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
	          version = J2EEVersionConstants.J2EE_1_2_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
	          version = J2EEVersionConstants.J2EE_1_3_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_4_TEXT)) {
	          version = J2EEVersionConstants.J2EE_1_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_5_0_TEXT)) {   
	          version = J2EEVersionConstants.JEE_5_0_ID;                          
	       } 
	    } else if (isWARFile(schemaName)) {
	       if (null == versionAttr) {
	          version = J2EEVersionConstants.WEB_2_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_2_TEXT)) {
	          version = J2EEVersionConstants.WEB_2_2_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_3_TEXT)) {
	          version = J2EEVersionConstants.WEB_2_3_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_4_TEXT)) {
	          version = J2EEVersionConstants.WEB_2_4_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_5_TEXT)) { 
	          version = J2EEVersionConstants.WEB_2_5_ID;                           
	       }
	    } else if (isRARFile(schemaName)) {
	       if (null == versionAttr) {
	          version = J2EEVersionConstants.JCA_1_5_ID;
	       } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_0_TEXT)) {
	          version = J2EEVersionConstants.JCA_1_0_ID;
	       } else {
	          version = J2EEVersionConstants.JCA_1_5_ID;
	       }
	    }
	    return version;
	
	 }

}
