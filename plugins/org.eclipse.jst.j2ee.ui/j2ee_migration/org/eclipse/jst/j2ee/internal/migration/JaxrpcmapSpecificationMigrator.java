/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.migration.actions.J2EEMigrationUIResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.SpecificationMigrator;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webservice.WebServiceConstants;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResource;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.jst.j2ee.webservices.WebServiceEditModel;
import org.eclipse.jst.j2ee.webservices.WebServicesManager;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 *  
 */
public class JaxrpcmapSpecificationMigrator extends SpecificationMigrator {
	private String DEFAULT_NAMESPACE_PREFIX = "pfx"; //$NON-NLS-1$
	private String JAXRPC_SUCCESSFUL = J2EEMigrationUIResourceHandler.getString("JaxrpcmapSpecificationMigrator_UI_1"); //$NON-NLS-1$
	private String NO_MODULE_ROOT_FOUND = J2EEMigrationUIResourceHandler.getString("JaxrpcmapSpecificationMigrator_UI_2"); //$NON-NLS-1$
	private J2EEMigrationStatus JAXRPCMAP_OK_STATUS = new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, JAXRPC_SUCCESSFUL);

	public JaxrpcmapSpecificationMigrator(String aVersion, boolean complex) {
		super(aVersion, complex);
	}

	public JaxrpcmapSpecificationMigrator(XMLResource anXmlResource, String aVersion, boolean complex) {
		super(anXmlResource, aVersion, complex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.J2EESpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.ejb.EJBResource)
	 */
	protected J2EEMigrationStatus migrateTo14(EJBResource ejbResource) {
		//These should be in J2EEInit!!
		//JaxrpcmapPackageImpl.init();
		//JaxrpcmapResourceFactory.registerDtds();
		//
		ArrayList mappingFileList = new ArrayList();
		IProject project = ProjectUtilities.getProject(ejbResource);
		ResourceSet resSet = ejbResource.getResourceSet();
		EJBJar ejbJar = ejbResource.getEJBJar();

		Iterator ejbs = ejbJar.getEnterpriseBeans().iterator();
		while (ejbs.hasNext()) {
			EnterpriseBean ejb = (EnterpriseBean) ejbs.next();
			Iterator serviceRefs = ejb.getServiceRefs().iterator();
			while (serviceRefs.hasNext()) {
				ServiceRef sref = (ServiceRef) serviceRefs.next();
				String mappingFile = sref.getJaxrpcMappingFile();
				if (mappingFile != null && mappingFile.length() > 0) {
					mappingFileList.add(mappingFile);
				}
			}
		}

		appendServiceSideMappingFiles(ejbResource, mappingFileList);
		String[] mappingFiles = convertToStringArray(mappingFileList.toArray());
		return migrateJaxrpcmapTo14(resSet, project, mappingFiles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.SpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.client.ApplicationClientResource)
	 */
	protected J2EEMigrationStatus migrateTo14(ApplicationClientResource resource) {
		//These should be in J2EEInit!!
		//JaxrpcmapPackageImpl.init();
		//JaxrpcmapResourceFactory.registerDtds();
		//
		ArrayList mappingFileList = new ArrayList();
		IProject project = ProjectUtilities.getProject(resource);
		ResourceSet resSet = resource.getResourceSet();
		ApplicationClient appClient = resource.getApplicationClient();

		Iterator serviceRefs = appClient.getServiceRefs().iterator();
		while (serviceRefs.hasNext()) {
			ServiceRef sref = (ServiceRef) serviceRefs.next();
			String mappingFile = sref.getJaxrpcMappingFile();
			if (mappingFile != null && mappingFile.length() > 0) {
				mappingFileList.add(mappingFile);
			}
		}

		String[] mappingFiles = convertToStringArray(mappingFileList.toArray());
		return migrateJaxrpcmapTo14(resSet, project, mappingFiles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.SpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.webapplication.WebAppResource)
	 */
	protected J2EEMigrationStatus migrateTo14(WebAppResource resource) {
		//These should be in J2EEInit!!
		//JaxrpcmapPackageImpl.init();
		//JaxrpcmapResourceFactory.registerDtds();
		//
		ArrayList mappingFileList = new ArrayList();
		IProject project = ProjectUtilities.getProject(resource);
		ResourceSet resSet = resource.getResourceSet();
		WebApp webApp = resource.getWebApp();

		Iterator serviceRefs = webApp.getServiceRefs().iterator();
		while (serviceRefs.hasNext()) {
			ServiceRef sref = (ServiceRef) serviceRefs.next();
			String mappingFile = sref.getJaxrpcMappingFile();
			if (mappingFile != null && mappingFile.length() > 0) {
				mappingFileList.add(mappingFile);
			}
		}

		appendServiceSideMappingFiles(resource, mappingFileList);
		String[] mappingFiles = convertToStringArray(mappingFileList.toArray());

		return migrateJaxrpcmapTo14(resSet, project, mappingFiles);
	}

	private void appendServiceSideMappingFiles(XMLResource resource, ArrayList mappingFileList) {
		IProject project = ProjectUtilities.getProject(resource);
		if (project == null)
			return;

		WebServiceEditModel webServicesEditModel = WebServicesManager.getInstance().getWSEditModel(project);
		if (webServicesEditModel == null)
			return;

		WsddResource wsddResource = webServicesEditModel.getWebServicesXmlResource();
		if (wsddResource == null)
			return;

		WebServices ws = wsddResource.getWebServices();
		if (ws == null)
			return;

		Iterator wsdescs = ws.getWebServiceDescriptions().iterator();
		while (wsdescs.hasNext()) {
			WebServiceDescription wsdesc = (WebServiceDescription) wsdescs.next();
			String mappingFile = wsdesc.getJaxrpcMappingFile();
			if (mappingFile != null && mappingFile.length() > 0) {
				mappingFileList.add(mappingFile);
			}
		}
	}

	private J2EEMigrationStatus migrateJaxrpcmapTo14(ResourceSet resSet, IProject project, String[] moduleRootRelativePathNames) {
		//Get the J2EEResourceFactoryRegistry on the resource set.
		J2EEResourceFactoryRegistry registry = (J2EEResourceFactoryRegistry) resSet.getResourceFactoryRegistry();

		//Get the module root folder
		J2EENature nature = J2EENature.getRegisteredRuntime(project);
		IResource moduleRoot = nature.getModuleServerRoot();
		IFolder moduleRootFolder = null;
		if (moduleRoot instanceof IFolder) {
			moduleRootFolder = (IFolder) moduleRoot;
		} else {
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_POSSIBLE, NO_MODULE_ROOT_FOUND);
		}

		//Migrate all the mapping files in the array
		for (int i = 0; i < moduleRootRelativePathNames.length; i++) {
			String moduleRootRelativePathName = moduleRootRelativePathNames[i];

			//Calculate the name of the mapping file being migrated.
			String mappingFileName = null;
			int lastSlashIdx = moduleRootRelativePathName.lastIndexOf('/');
			if (lastSlashIdx == -1) {
				mappingFileName = moduleRootRelativePathName;
			} else {
				mappingFileName = moduleRootRelativePathName.substring(lastSlashIdx + 1);
			}
			//Register the mapping file.
			registry.registerLastFileSegment(mappingFileName, new JaxrpcmapResourceFactory(RendererFactory.getDefaultRendererFactory()));

			//Get the IFile for the mapping file
			IFile mappingFile = moduleRootFolder.getFile(moduleRootRelativePathName);
			if (mappingFile != null && mappingFile.exists()) {
				//Get the URI
				URI uri = URI.createPlatformResourceURI(mappingFile.getFullPath().toString());
				try {
					//Load the resource
					JaxrpcmapResource jaxrpcmapRes = (JaxrpcmapResource) resSet.getResource(uri, true);

					//Migrate the resource
					jaxrpcmapRes.setModuleVersionID(WebServiceConstants.WEBSERVICE_1_1_ID);
					EObject rootObject = jaxrpcmapRes.getRootObject();
					jaxrpcmapRes.getContents().remove(rootObject);
					jaxrpcmapRes.getContents().add(rootObject);
					JavaWSDLMapping jwm = jaxrpcmapRes.getJavaWSDLMapping();
					JavaWSDLMappingMigrator jwmMigrator = new JavaWSDLMappingMigrator();
					jwmMigrator.migrateTo14(jwm);

					//Save the resource
					Map options = new HashMap();
					jaxrpcmapRes.save(options);

				} catch (Exception e) {
					//Should log something here.
				}
			}
		}
		return JAXRPCMAP_OK_STATUS;
	}


	private String[] convertToStringArray(Object[] a) {
		if (a == null)
			return new String[0];

		int length = a.length;
		String[] sa = new String[length];
		for (int i = 0; i < length; i++) {
			Object obj = a[i];
			if (obj instanceof String) {
				sa[i] = (String) obj;
			}
		}
		return sa;
	}

	private class JavaWSDLMappingMigrator {
		public void migrateTo14(JavaWSDLMapping javaWsdlMapping) {
			Iterator jxtms = javaWsdlMapping.getJavaXMLTypeMappings().iterator();
			Iterator ems = javaWsdlMapping.getExceptionMappings().iterator();
			Iterator ims = javaWsdlMapping.getInterfaceMappings().iterator();
			while (jxtms.hasNext()) {
				JavaXMLTypeMapping jxtm = (JavaXMLTypeMapping) jxtms.next();
				//migrate the root-type-qname
				RootTypeQname rtqn = jxtm.getRootTypeQname();
				if (qnameIsAnonymous(rtqn)) {
					jxtm.setRootTypeQname(null);
					StringBuffer atqnsb = new StringBuffer();
					atqnsb.append(rtqn.getNamespaceURI());
					atqnsb.append(":"); //$NON-NLS-1$
					atqnsb.append(rtqn.getLocalPart());
					String atqn = atqnsb.toString();
					jxtm.setAnonymousTypeQname(atqn);
				} else {
					migrateQNameTo14(rtqn);
				}

			}

			while (ems.hasNext()) {
				ExceptionMapping em = (ExceptionMapping) ems.next();
				WSDLMessage wm = em.getWsdlMessage();
				migrateQNameTo14(wm);
			}

			while (ims.hasNext()) {
				InterfaceMapping im = (InterfaceMapping) ims.next();
				if (im instanceof ServiceInterfaceMapping) {
					ServiceInterfaceMapping sim = (ServiceInterfaceMapping) im;
					WSDLServiceName wsn = sim.getWsdlServiceName();
					migrateQNameTo14(wsn);
				} else if (im instanceof ServiceEndpointInterfaceMapping) {
					ServiceEndpointInterfaceMapping seim = (ServiceEndpointInterfaceMapping) im;

					//wsdl-port-type
					WSDLPortType wpt = seim.getWsdlPortType();
					migrateQNameTo14(wpt);

					//wsdl-binding
					WSDLBinding wb = seim.getWsdlBinding();
					migrateQNameTo14(wb);

					Iterator semms = seim.getServiceEndpointMethodMappings().iterator();
					while (semms.hasNext()) {
						ServiceEndpointMethodMapping semm = (ServiceEndpointMethodMapping) semms.next();
						Iterator mppms = semm.getMethodParamPartsMappings().iterator();
						while (mppms.hasNext()) {
							MethodParamPartsMapping mppm = (MethodParamPartsMapping) mppms.next();
							WSDLMessageMapping wmm = mppm.getWsdlMessageMapping();

							//wsdl-message
							WSDLMessage wm = wmm.getWsdlMessage();
							migrateQNameTo14(wm);
						}
						WSDLReturnValueMapping wrvm = semm.getWsdlReturnValueMapping();
						if (wrvm != null) {
							//wsdl-message
							WSDLMessage wm = wrvm.getWsdlMessage();
							migrateQNameTo14(wm);
						}
					}
				}
			}
		}

		private void migrateQNameTo14(QName qname) {
			String namespaceURI = qname.getNamespaceURI();
			String localPart = qname.getLocalPart();
			qname.setValues(DEFAULT_NAMESPACE_PREFIX, namespaceURI, localPart);
		}

		private boolean qnameIsAnonymous(QName qname) {
			String localPart = qname.getLocalPart();
			if (localPart.indexOf('>') >= 0 || localPart.indexOf('[') >= 0) {
				return true;
			}
			return false;
		}
	}
}