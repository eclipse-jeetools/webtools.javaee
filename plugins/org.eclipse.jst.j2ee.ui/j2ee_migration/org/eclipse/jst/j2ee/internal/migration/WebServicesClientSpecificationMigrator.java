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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.J2EEEditModel;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.migration.actions.J2EEMigrationUIResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.SpecificationMigrator;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservices.WebServiceEditModel;
import org.eclipse.jst.j2ee.webservices.WebServicesManager;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author mdelder
 *  
 */
public class WebServicesClientSpecificationMigrator extends SpecificationMigrator {

	private static final J2EEMigrationStatus WEBSERVICES_OK_STATUS = new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("WebServicesClientSpecificationMigrator_UI_0")); //$NON-NLS-1$

	/**
	 * @param aVersion
	 * @param complex
	 */
	public WebServicesClientSpecificationMigrator(String aVersion, boolean complex) {
		super(aVersion, complex);
	}

	/**
	 * @param anXmlResource
	 * @param aVersion
	 * @param complex
	 */
	public WebServicesClientSpecificationMigrator(XMLResource anXmlResource, String aVersion, boolean complex) {
		super(anXmlResource, aVersion, complex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.J2EESpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.ejb.EJBResource)
	 */
	protected J2EEMigrationStatus migrateTo14(EJBResource ejbResource) {

		WebServicesClient client = null;
		try {
			client = getWebservicesClient(ejbResource);
		} catch (WebservicesMigrationException e) {
			return e.status;
		}

		ComponentScopedRefs scopedRefs = null;
		EnterpriseBean enterpriseBean = null;

		EList componentScopedRefs = client.getComponentScopedRefs();
		EJBJar ejbJar = ejbResource.getEJBJar();
		for (int i = 0; i < componentScopedRefs.size(); i++) {
			scopedRefs = (ComponentScopedRefs) componentScopedRefs.get(i);
			enterpriseBean = ejbJar.getEnterpriseBeanNamed(scopedRefs.getComponentName());
			enterpriseBean.getServiceRefs().addAll(scopedRefs.getServiceRefs());
		}

		deleteResource(client);
		if (enterpriseBean != null)
			migrateQNames(enterpriseBean.getServiceRefs());

		return WEBSERVICES_OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.SpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.client.ApplicationClientResource)
	 */
	protected J2EEMigrationStatus migrateTo14(ApplicationClientResource resource) {
		WebServicesClient client = null;
		try {
			client = getWebservicesClient(resource);
		} catch (WebservicesMigrationException e) {
			return e.status;
		}
		ApplicationClient appclient = resource.getApplicationClient();
		appclient.getServiceRefs().addAll(client.getServiceRefs());
		deleteResource(client);
		migrateQNames(appclient.getServiceRefs());

		return WEBSERVICES_OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.migration.SpecificationMigrator#migrateTo14(org.eclipse.jst.j2ee.webapplication.WebAppResource)
	 */
	protected J2EEMigrationStatus migrateTo14(WebAppResource resource) {
		WebServicesClient client = null;
		try {
			client = getWebservicesClient(resource);
		} catch (WebservicesMigrationException e) {
			return e.status;
		}
		WebApp webapp = resource.getWebApp();
		List serviceRefs = webapp.getServiceRefs();
		serviceRefs.addAll(client.getServiceRefs());
		deleteResource(client);
		migrateQNames(serviceRefs);

		return WEBSERVICES_OK_STATUS;
	}

	protected void deleteResource(WebServicesClient client) {

		IProject project = ProjectUtilities.getProject(client);

		J2EEEditModel editModel = null;
		try {
			J2EENature nature = J2EENature.getRegisteredRuntime(project);
			editModel = nature.getJ2EEEditModelForWrite(this);

			Resource webservicesClientResource = null;
			if (editModel != null)
				webservicesClientResource = editModel.get13WebServicesClientResource();
			editModel.deleteResource(webservicesClientResource);

		} finally {
			if (editModel != null)
				editModel.releaseAccess(this);
		}

	}

	protected WebServicesClient getWebservicesClient(XMLResource resource) throws WebservicesMigrationException {

		IProject project = ProjectUtilities.getProject(resource);
		if (project == null)
			throw new WebservicesMigrationException(new J2EEMigrationStatus(J2EEMigrationStatus.ERROR, J2EEMigrationUIResourceHandler.getString("WebServicesClientSpecificationMigrator_UI_1"))); //$NON-NLS-1$

		WebServiceEditModel webServicesEditModel = WebServicesManager.getInstance().getWSEditModel(project);
		if (webServicesEditModel == null)
			throw new WebservicesMigrationException(new J2EEMigrationStatus(J2EEMigrationStatus.ERROR, J2EEMigrationUIResourceHandler.getString("WebServicesClientSpecificationMigrator_UI_2"))); //$NON-NLS-1$

		WebServicesClient client = webServicesEditModel.get13WebServicesClientResource().getWebServicesClient();
		if (client == null)
			throw new WebservicesMigrationException(new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, J2EEMigrationUIResourceHandler.getString("WebServicesClientSpecificationMigrator_UI_3"))); //$NON-NLS-1$

		return client;
	}

	private void migrateQNames(List serviceRefs) {
		for (int i = 0; i < serviceRefs.size(); i++) {
			ServiceRef serviceRef = (ServiceRef) serviceRefs.get(i);
			QName qname = serviceRef.getServiceQname();
			String prefix = "pfx"; //$NON-NLS-1$
			String namespaceURI = qname.getNamespaceURI();
			String localPart = qname.getLocalPart();
			qname.setValues(prefix, namespaceURI, localPart);
		}
	}

}