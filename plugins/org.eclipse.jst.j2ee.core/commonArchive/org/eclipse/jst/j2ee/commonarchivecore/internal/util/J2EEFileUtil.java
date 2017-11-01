/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.EmptyResourceException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.xml.GeneralXmlDocumentReader;
import org.eclipse.jst.j2ee.internal.xml.XmlDocumentReader;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.ibm.icu.util.StringTokenizer;


/**
 * This is a utility class to hold helper methods common to multiple classes in the archive support
 * packages
 */
public class J2EEFileUtil {
	public static final String DOT_CLASS = ".class"; //$NON-NLS-1$

	public static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	public static final String DOT_SQLJ = ".sqlj"; //$NON-NLS-1$
	
	public static final String DOT_JSP = ".jsp"; //$NON-NLS-1$

	protected static boolean isRuntime = false;

	/**
	 * Flag to indicate whether the temp directory should be used for extracting nested jars for
	 * read; defaults to true
	 */
	protected static boolean shouldUseTempDirectoryForRead = true;

	/**
	 * Settable name for the temp directory; must resolve to a valid file on the file system; if not
	 * specified, defaults to the system temp directory
	 */
	protected static String tempDirectoryName;

	protected static java.io.File tempDirectory;
	
	/**
	 * Flag to indicate whether empty directories should be included during import; defaults to true.
	 * 
	 * @see ZipFileLoadStrategyImpl#isIncludeEmptyDirectories()
	 */
	public static boolean INCLUDE_EMPTY_DIRECTORIES = true;
	

	protected static ModuleFile getModuleFileUsingAltDD(EARFile ear, Resource res) {
		if (res == null)
			return null;

		String uri = res.getURI().toString();
		Module m = ear.getDeploymentDescriptor().getModuleHavingAltDD(uri);
		if (m == null)
			return null;
		ModuleRef ref = ear.getModuleRef(m);
		if (ref == null)
			return null;

		return ref.getModuleFile();
	}

	public static String getModuleFileTypeName(int moduleType) {
		switch (moduleType) {
			case XMLResource.APPLICATION_TYPE :
				return CommonArchiveResourceHandler.A_Application_file; 
			case XMLResource.APP_CLIENT_TYPE :
				return CommonArchiveResourceHandler.An_Application_Client_JAR_file; 
			case XMLResource.EJB_TYPE :
				return CommonArchiveResourceHandler.An_EJB_JAR_file; 
			case XMLResource.WEB_APP_TYPE :
				return CommonArchiveResourceHandler.A_WAR_file; 
			case XMLResource.RAR_TYPE :
				return CommonArchiveResourceHandler.A_RAR_file; 
		}
		return null;
	}

	/**
	 * Concatenates the two strings and ensures the correct separator is used in the path
	 */
	public static String getOSUri(String uri) {
		char sep = File.separatorChar;
		if (sep != '/')
			return uri.replace('/', sep);
		return uri.replace('\\', sep);
	}


	/**
	 * Returns the first element in the extent of the resource
	 * 
	 * @throws EmptyResourceException -
	 *             thrown if the extent is empty
	 */
	public static EObject getRoot(Resource aResource) throws EmptyResourceException {
		EList extent = aResource.getContents();
		if (extent.size() < 1) {
			throw new EmptyResourceException(aResource.getURI().toString());
		}
		return (EObject) extent.get(0);
	}

	public static java.io.File getTempDirectory() {
		return tempDirectory;
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 1:16:48 PM)
	 * 
	 * @return java.lang.String
	 */
	public static java.lang.String getTempDirectoryName() {
		return tempDirectoryName;
	}

	public static String[] getTokens(String aString) {
		return getTokens(aString, null);
	}

	public static String[] getTokens(String aString, String delimiter) {

		StringTokenizer tok = (delimiter == null) ? new StringTokenizer(aString) : new StringTokenizer(aString, delimiter);
		int size = tok.countTokens();
		String[] tokens = new String[size];
		for (int i = 0; i < size && tok.hasMoreTokens(); i++) {
			tokens[i] = tok.nextToken();
		}
		return tokens;
	}

	/**
	 * getValueIgnoreKeyCase method comment.
	 */
	public static java.lang.String getValueIgnoreKeyCase(java.lang.String key, java.util.jar.Attributes attr) {
		Iterator keysAndValues = attr.entrySet().iterator();
		while (keysAndValues.hasNext()) {
			Map.Entry entry = (Map.Entry) keysAndValues.next();
			String entryKey = entry.getKey().toString();
			if (entryKey.equalsIgnoreCase(key))
				return entry.getValue() == null ? null : entry.getValue().toString();
		}
		return null;
	}


	public static boolean isJ2EE13FastCheck(ModuleFile moduleFile) {
		if (moduleFile == null)
			return false;
		else if (moduleFile.isRARFile())
			return true;
		InputStream in = null;
		String uri = moduleFile.getDeploymentDescriptorUri();
		try {
			in = moduleFile.getInputStream(uri);

			DocumentType docType = XmlDocumentReader.readDocumentType(in, uri);
			if (docType == null)
				return false;
			String[] ids = getJ2EE13PublicAndSystemIdFor(moduleFile);
			if (ids == null)
				return false;
			return ids[0].equals(docType.getPublicId()) && ids[1].equals(docType.getSystemId());
		} catch (IOException ex) {
			return false;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				//Ignore
			}
		}

	}

	protected static String[] getJ2EE13PublicAndSystemIdFor(ModuleFile moduleFile) {
		if (moduleFile.isEARFile())
			return new String[]{J2EEConstants.APPLICATION_PUBLICID_1_3, J2EEConstants.APPLICATION_SYSTEMID_1_3};
		else if (moduleFile.isEJBJarFile())
			return new String[]{J2EEConstants.EJBJAR_PUBLICID_2_0, J2EEConstants.EJBJAR_SYSTEMID_2_0};
		else if (moduleFile.isApplicationClientFile())
			return new String[]{J2EEConstants.APP_CLIENT_PUBLICID_1_3, J2EEConstants.APP_CLIENT_SYSTEMID_1_3};
		else if (moduleFile.isWARFile())
			return new String[]{J2EEConstants.WEBAPP_PUBLICID_2_3, J2EEConstants.WEBAPP_SYSTEMID_2_3};
		else if (moduleFile.isRARFile())
			return new String[]{J2EEConstants.CONNECTOR_PUBLICID_1_0, J2EEConstants.CONNECTOR_SYSTEMID_1_0};
		else
			return null;
	}

	
    public static int getFastSpecVersion(String fileURI) {
        InputStream in = null;
        try {
        	in = new FileInputStream(fileURI);

           DocumentType docType = null;

           InputSource source = new InputSource(in);
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
                 version = lookupVersion(fileURI, publicID, systemID);
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
                          schemaName = schemaNode.getNodeValue();
                       }
                       Node versionNode = map.getNamedItem("version");//$NON-NLS-1$
                       if (null != versionNode) {
                          versionString = versionNode.getNodeValue();
                       }
                    }
                 }
              }
              if (null != schemaName) {
                 version = lookupVersion(fileURI, schemaName);
              }
              if (version == -1) {
                 version = parseVersionString(fileURI, versionString);
              }
           }

           return version;
        } catch (IOException ex) {
           return -1;
        } finally {
           try {
              if (in != null)
                 in.close();
           } catch (IOException ex) {
              //Ignore
           }
        }
     }

    private static int parseVersionString(String fileURI, String versionAttr) {
        int version = -1;
        if (isEARFile(fileURI)) {
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
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_6_0_TEXT)) {
              version = J2EEVersionConstants.JEE_6_0_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_7_0_TEXT)) {
              version = J2EEVersionConstants.JEE_7_0_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_8_0_TEXT)) {
        	   	  version = J2EEVersionConstants.JEE_8_0_ID;
           }
        } else if (isEJBJarFile(fileURI)) {
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
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) { 
              version = J2EEVersionConstants.EJB_3_1_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_3_2_TEXT)) { 
              version = J2EEVersionConstants.EJB_3_2_ID;
           }
        } else if (isApplicationClientFile(fileURI)) {
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
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_6_0_TEXT)) {
              version = J2EEVersionConstants.JEE_6_0_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_7_0_TEXT)) {
              version = J2EEVersionConstants.JEE_7_0_ID;
           }  else if (versionAttr.equals(J2EEVersionConstants.VERSION_8_0_TEXT)) {
        	  version = J2EEVersionConstants.JEE_8_0_ID;
           }
        } else if (isWARFile(fileURI)) {
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
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) {
              version = J2EEVersionConstants.WEB_3_0_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) {
              version = J2EEVersionConstants.WEB_3_1_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_4_0_TEXT)) {
        	  version = J2EEVersionConstants.WEB_4_0_ID;
           }
        } else if (isRARFile(fileURI)) {
           if (null == versionAttr) {
              version = J2EEVersionConstants.JCA_1_5_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_0_TEXT)) {
              version = J2EEVersionConstants.JCA_1_0_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_5_TEXT)) {
              version = J2EEVersionConstants.JCA_1_5_ID;
           } else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_6_TEXT)) {
              version = J2EEVersionConstants.JCA_1_6_ID;
           } else {
              version = J2EEVersionConstants.JCA_1_5_ID;
           }
        }
        return version;

     }

	private static int lookupVersion(String fileURI, String schemaName) {
        int version = -1;
        if (isEARFile(fileURI)) {
           if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_8)) {
        	  version = J2EEVersionConstants.JEE_8_0_ID;
           } else if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_7)) {
              version = J2EEVersionConstants.JEE_7_0_ID;
           } else if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_6)) {
              version = J2EEVersionConstants.JEE_6_0_ID;
           } else if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_5)) {
              version = J2EEVersionConstants.JEE_5_0_ID;
           } else if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_1_4)) {
              version = J2EEVersionConstants.J2EE_1_4_ID;
           }
        } else if (isEJBJarFile(fileURI)) {
           if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_3_2_NS)) != -1) {
              version = J2EEVersionConstants.EJB_3_2_ID;   
           } else if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_3_1_NS)) != -1) {
              version = J2EEVersionConstants.EJB_3_1_ID;   
           } else if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_3_0_NS)) != -1) {
              version = J2EEVersionConstants.EJB_3_0_ID;
           } else if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_2_1_NS)) != -1) {
              version = J2EEVersionConstants.EJB_2_1_ID;
           }
        } else if (isApplicationClientFile(fileURI)) {
           if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_8)) {
        	  version = J2EEVersionConstants.JEE_8_0_ID;
           } else if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_7)) {
              version = J2EEVersionConstants.JEE_7_0_ID;
           } else if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_6)) {
              version = J2EEVersionConstants.JEE_6_0_ID;
           } else if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_5)) {
              version = J2EEVersionConstants.JEE_5_0_ID;
           } else if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_1_4)) {
              version = J2EEVersionConstants.J2EE_1_4_ID;
           }
        } else if (isWARFile(fileURI)) {
           if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_4_0)) {
        	  version = J2EEVersionConstants.WEB_4_0_ID;
           } else if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_3_1)) {
              version = J2EEVersionConstants.WEB_3_1_ID;
           } else if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_3_0)) {
              version = J2EEVersionConstants.WEB_3_0_ID;
           } else if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_2_5)) {
              version = J2EEVersionConstants.WEB_2_5_ID;
           } else if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_2_4)) {
              version = J2EEVersionConstants.WEB_2_4_ID;
           }
        } else if (isRARFile(fileURI)) {
           if (schemaName.equals(J2EEConstants.CONNECTOR_SCHEMA_1_6)) {
              version = J2EEVersionConstants.JCA_1_6_ID;
           } else if (schemaName.equals(J2EEConstants.CONNECTOR_SCHEMA_1_5)) {
              version = J2EEVersionConstants.JCA_1_5_ID;
           }
        }
        return version;
     }

	private static int lookupVersion(String fileURI, String publicID, String systemID) {
		int version = -1;
		if (isEARFile(fileURI)) {
			if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_3) && (systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_3)||systemID.equals(J2EEConstants.APPLICATION_ALT_SYSTEMID_1_3))) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_2) && (systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_2)||systemID.equals(J2EEConstants.APPLICATION_ALT_SYSTEMID_1_2))) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (isEJBJarFile(fileURI)) {
			if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_2_0) && (systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_2_0)||systemID.equals(J2EEConstants.EJBJAR_ALT_SYSTEMID_2_0))) {
				version = J2EEVersionConstants.EJB_2_0_ID;
			} else if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_1_1) && (systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_1_1)||systemID.equals(J2EEConstants.EJBJAR_ALT_SYSTEMID_1_1))) {
				version = J2EEVersionConstants.EJB_1_1_ID;
			} else {
				version = J2EEVersionConstants.EJB_2_1_ID;
			}
		} else if (isApplicationClientFile(fileURI)) {
			if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_3) && (systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_3)||systemID.equals(J2EEConstants.APP_CLIENT_ALT_SYSTEMID_1_3))) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_2) && (systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_2)||systemID.equals(J2EEConstants.APP_CLIENT_ALT_SYSTEMID_1_2))) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (isWARFile(fileURI)) {
			if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_3) && (systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_3)||systemID.equals(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_3))) {
				version = J2EEVersionConstants.WEB_2_3_ID;
			} else if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_2) && (systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_2)||systemID.equals(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_2))) {
				version = J2EEVersionConstants.WEB_2_2_ID;
			} else {
				version = J2EEVersionConstants.WEB_2_4_ID;
			}
		} else if (isRARFile(fileURI)) {
			if (publicID.equals(J2EEConstants.CONNECTOR_PUBLICID_1_0) && (systemID.equals(J2EEConstants.CONNECTOR_SYSTEMID_1_0)||systemID.equals(J2EEConstants.CONNECTOR_ALT_SYSTEMID_1_0))) {
				version = J2EEVersionConstants.JCA_1_0_ID;
			} else {
				version = J2EEVersionConstants.JCA_1_5_ID;
			}
		}
		return version;
	}

	private static boolean isRARFile(String fileURI) {
		
		return (fileURI.indexOf(J2EEConstants.RAR_SHORT_NAME) != -1);
	}

	private static boolean isWARFile(String fileURI) {
		
		return (fileURI.indexOf(J2EEConstants.WEBAPP_DD_SHORT_NAME) != -1);
	}

	private static boolean isApplicationClientFile(String fileURI) {
		return (fileURI.indexOf(J2EEConstants.APP_CLIENT_DD_SHORT_NAME) != -1);
	}

	private static boolean isEJBJarFile(String fileURI) {
		return (fileURI.indexOf(J2EEConstants.EJBJAR_DD_SHORT_NAME) != -1);
	}

	private static boolean isEARFile(String fileURI) {
		return (fileURI.indexOf(J2EEConstants.APPLICATION_DD_SHORT_NAME) != -1);
	}

}
