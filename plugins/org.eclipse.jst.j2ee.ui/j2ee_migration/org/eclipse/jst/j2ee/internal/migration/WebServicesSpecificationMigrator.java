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
/*
 * Created on May 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.actions.J2EEMigrationUIResourceHandler;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.SpecificationMigrator;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.jst.j2ee.webservices.WebServicesManager;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServicesSpecificationMigrator extends SpecificationMigrator {
	protected IProject project;
	private static final J2EEMigrationStatus WEBSERVICES_OK_STATUS = new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("WebServicesSpecificationMigrator_UI_0")); //$NON-NLS-1$
	private static final J2EEMigrationStatus NO_WEBSERVICES_STATUS = new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("WebServicesSpecificationMigrator_UI_1")); //$NON-NLS-1$

	/**
	 * @param aVersion
	 * @param complex
	 */
	public WebServicesSpecificationMigrator(String aVersion, boolean complex) {
		super(aVersion, complex);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param anXmlResource
	 * @param aVersion
	 * @param complex
	 */
	public WebServicesSpecificationMigrator(IProject proj, String aVersion, boolean complex) {
		super(null, aVersion, complex);
		project = proj;
	}

	/**
	 *  
	 */
	protected J2EEMigrationStatus migrateWebServiceResourceTo14IfExists() {
		WebServicesManager manager = WebServicesManager.getInstance();
		WsddResource resource = manager.getWebServicesResource(project);
		if (resource != null) {
			resource.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
			EObject rootObject = resource.getRootObject();
			resource.getContents().remove(rootObject);
			resource.getContents().add(rootObject);
			try {
				migrateStatelessSessionBeans(resource);
				migrateDescriptions(resource);
				migrateQNames(resource);
				resource.saveIfNecessary();
				return WEBSERVICES_OK_STATUS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return NO_WEBSERVICES_STATUS;
	}

	/**
	 *  
	 */
	private void migrateStatelessSessionBeans(WsddResource resource) {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
		if (nature != null) {
			WebServices ws = resource.getWebServices();
			List wsds = ws.getWebServiceDescriptions();
			for (int i = 0; i < wsds.size(); i++) {
				WebServiceDescription wsd = (WebServiceDescription) wsds.get(i);
				List pcs = wsd.getPortComponents();
				for (int j = 0; j < pcs.size(); j++) {
					PortComponent pc = (PortComponent) pcs.get(j);
					ServiceImplBean sBean = pc.getServiceImplBean();
					if (sBean != null) {
						String ejbName = sBean.getEEJBLink().getEjbLink();
						String seiName = pc.getServiceEndpointInterface();
						if (ejbName != null && seiName != null) {
							EJBJar jar = nature.getEJBJar();
							EnterpriseBean bean = jar.getEnterpriseBeanNamed(ejbName);
							if (bean != null)
								((Session) bean).setServiceEndpointName(seiName);
						}
					}
				}
			}
		}
	}

	/**
	 * @param resource
	 */
	private void migrateDescriptions(WsddResource resource) {
		WebServices ws = resource.getWebServices();
		List wsds = ws.getWebServiceDescriptions();
		for (int i = 0; i < wsds.size(); i++) {
			WebServiceDescription wsd = (WebServiceDescription) wsds.get(i);
			List portComponents = wsd.getPortComponents();
			for (int j = 0; j < portComponents.size(); j++) {
				PortComponent pc = (PortComponent) portComponents.get(j);
				List handlers = pc.getHandlers();
				for (int k = 0; k < handlers.size(); k++) {
					Handler handler = (Handler) handlers.get(k);
					List initParams = handler.getInitParams();
					for (int l = 0; l < initParams.size(); l++) {
						InitParam initParam = (InitParam) initParams.get(l);
						String description = initParam.getDescription();
						DescriptionType descType = WscommonFactory.eINSTANCE.createDescriptionType();
						descType.setValue(description);
						initParam.getDescriptionTypes().add(descType);
					}
				}
			}
		}
	}

	private void migrateQNames(WsddResource resource) {
		WebServices ws = resource.getWebServices();
		List wsds = ws.getWebServiceDescriptions();
		for (int i = 0; i < wsds.size(); i++) {
			WebServiceDescription desc = (WebServiceDescription) wsds.get(i);
			List pcs = desc.getPortComponents();
			for (int j = 0; j < pcs.size(); j++) {
				PortComponent pc = (PortComponent) pcs.get(j);
				WSDLPort wsdlPort = pc.getWsdlPort();
				String prefix = "pfx"; //$NON-NLS-1$
				String namespaceURI = wsdlPort.getNamespaceURI();
				String localPart = wsdlPort.getLocalPart();
				wsdlPort.setValues(prefix, namespaceURI, localPart);

				List handlers = pc.getHandlers();
				for (int k = 0; k < handlers.size(); k++) {
					Handler handler = (Handler) handlers.get(k);
					List soapHeaders = handler.getSoapHeaders();
					for (int l = 0; l < soapHeaders.size(); l++) {
						SOAPHeader sp = (SOAPHeader) soapHeaders.get(l);
						namespaceURI = sp.getNamespaceURI();
						localPart = sp.getLocalPart();
						sp.setValues(prefix, namespaceURI, localPart);
					}
				}
			}

		}
	}
}