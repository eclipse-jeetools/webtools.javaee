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
package org.eclipse.jst.j2ee.internal.jca.operations;


import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.J2EEEditModel;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.wst.common.emfworkbench.EMFWorkbenchContext;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Creates an edit model for the Connector project.
 */
public class ConnectorEditModel extends J2EEEditModel {
	/**
	 * @param editModelID
	 * @param context
	 * @param readOnly
	 */
	public ConnectorEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}

	/**
	 * @param editModelID
	 * @param context
	 * @param readOnly
	 * @param knownResourceURIs
	 * @param shouldAccessUnkownURIsAsReadOnly
	 */
	public ConnectorEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnkownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnkownResourcesAsReadOnly);
	}

	public ConnectorNatureRuntime getConnectorNature() {
		return ConnectorNatureRuntime.getRuntime(getProject());
	}

	/**
	 * Return the root element of the DD which is the Connector element, from ra.xml.
	 * 
	 * @return Connector
	 */
	public Connector getConnector() {

		try {
			Resource dd = this.getConnectorXmiResource();
			if (dd != null) {
				Object rootObject = J2EEEditModel.getRoot(dd);
				if (rootObject instanceof Connector) {
					return (Connector) rootObject;
				} // if
			} // if
		} catch (CoreException e) {
			Logger.getLogger().logError(IConnectorNatureConstants.ERROR_OCCURED_GETTING_CONN_ERROR_);
		} catch (IOException e2) {
			Logger.getLogger().logError(IConnectorNatureConstants.ERROR_OCCURED_GETTING_CONN_ERROR_);
		} // try

		return null;

	} // getConnector

	/**
	 * Returns the respected resource based on an archive constant.
	 * 
	 * @return Resource
	 * @throws Exception
	 */
	public Resource getConnectorXmiResource() throws CoreException, IOException {
		return getResource(ArchiveConstants.RAR_DD_URI_OBJ);
	} // getConnectorXmiResource

	/**
	 * Creates an connector XMI resource
	 * 
	 * @return Resource
	 */
	public Resource makeConnectorXmiResource() {
		return createResource(ArchiveConstants.RAR_DD_URI_OBJ);
	} // makeConnectorXmiResource

	/**
	 * Creates the deployment descriptor.
	 * 
	 * @return Resource
	 */
	public Resource makeDeploymentDescriptorWithRoot() {
		XMLResource res = (XMLResource) makeConnectorXmiResource();
		Connector connector = JcaPackage.eINSTANCE.getJcaFactory().createConnector();
		res.getContents().add(connector);
		res.setID(connector, ArchiveConstants.CONNECTOR_ID);
		res.setModuleVersionID(getConnectorNature().getModuleVersion());
		connector.setDisplayName(getProject().getName());
		return res;
	} // makeDeploymentDescriptorWithRoot

	public XMLResource getDeploymentDescriptorResource() {
		try {
			return (XMLResource) getConnectorXmiResource();
		} catch (CoreException e) {
			return null;
		} catch (IOException e2) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getConnector();
	}

	public String getDevelopmentAcivityID() {
		return DefaultModuleProjectCreationOperation.ENTERPRISE_JAVA;
	}

}