/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.reference;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesClientDataHelper;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.wsdl.Service;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class ServiceReferenceDataModel extends ReferenceDataModel implements WebServicesClientDataHelper {
	/**
	 * Required, type String
	 */
	public static final String TARGET_WEB_SERVICE = "ServiceReferenceDataModel.TARGET_WEB_SERVICE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String WSDL_FILE = "ServiceReferenceDataModel.WSDL_FILE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String JAX_RPC_MAPPING_FILE = "ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String QNAME_NAMESPACE_URI = "ServiceReferenceDataModel.QNAME_NAMESPACE_URI"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String QNAME_lOCAL_PART = "ServiceReferenceDataModel.QNAME_lOCAL_PART"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String WSDL_URL = "ServiceReferenceDataModel.WSDL_URL"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String QNAME = "ServiceReferenceDataModel.QNAME"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String LINKS = "ServiceReferenceDataModel.LINK"; //$NON-NLS-1$

	private String[] serviceEndpointInterfaceNames = null;
	private String serviceInterfaceName = null;
	private boolean didGenDescriptor = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(TARGET_WEB_SERVICE);
		addValidBaseProperty(WSDL_FILE);
		addValidBaseProperty(JAX_RPC_MAPPING_FILE);
		addValidBaseProperty(QNAME_NAMESPACE_URI);
		addValidBaseProperty(QNAME_lOCAL_PART);
		addValidBaseProperty(WSDL_URL);
		addValidBaseProperty(QNAME);
		addValidBaseProperty(LINKS);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(TARGET_WEB_SERVICE)) {
			notifyDefaultChange(REF_NAME);
			notifyDefaultChange(WSDL_FILE);
			notifyDefaultChange(WSDL_URL);
			notifyDefaultChange(QNAME);
			notifyDefaultChange(QNAME_NAMESPACE_URI);
			notifyDefaultChange(QNAME_lOCAL_PART);
		}
		return doSet;
	}

	protected Object getLocationForServiceFromWSIL(Service wsdl) {
		// Fix to get "external" workspace wsdl file because wsilparser uses generic resource set
		if (wsdl.eResource() == null)
			return wsdl.getEnclosingDefinition().getLocation();
		URI uri = wsdl.eResource().getURI();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath rootPath = root.getLocation();
		IPath wsdlPath = new Path(uri.toFileString());
		if (wsdlPath.matchingFirstSegments(rootPath) == rootPath.segmentCount()) {
			IFile wsdlFile = root.getFile(wsdlPath.removeFirstSegments(rootPath.segmentCount()));
			if (wsdlFile.exists()) {
				return wsdlFile.getProjectRelativePath().removeFirstSegments(1).toString();
			}
		}
		return null;
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(REF_NAME)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null)
				return "service/" + wsdl.getQName().getLocalPart(); //$NON-NLS-1$
		} else if (propertyName.equals(WSDL_FILE)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null) {
				boolean isInternal = WorkbenchResourceHelper.getFile(wsdl) != null && WorkbenchResourceHelper.getFile(wsdl).exists();
				if (isInternal)
					return WorkbenchResourceHelper.getFile(wsdl).getProjectRelativePath().removeFirstSegments(1).toString();
				Object location = getLocationForServiceFromWSIL(wsdl);
				if (location != null)
					return location;
			}
		} else if (propertyName.equals(WSDL_URL)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null) {
				boolean isInternal = WorkbenchResourceHelper.getFile(wsdl) != null && WorkbenchResourceHelper.getFile(wsdl).exists();
				if (isInternal)
					return "file:/" + WorkbenchResourceHelper.getFile(wsdl).getRawLocation().toString(); //$NON-NLS-1$
				return wsdl.getEnclosingDefinition().getLocation();
			}
		} else if (propertyName.equals(QNAME)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null)
				return wsdl.getQName();
		} else if (propertyName.equals(QNAME_NAMESPACE_URI)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null) {
				QName qName = wsdl.getQName();
				if (qName != null)
					return qName.getNamespaceURI();
			}
		} else if (propertyName.equals(QNAME_lOCAL_PART)) {
			Service wsdl = (Service) getProperty(TARGET_WEB_SERVICE);
			if (wsdl != null) {
				QName qName = wsdl.getQName();
				if (qName != null)
					return qName.getLocalPart();
			}
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus stat = super.doValidateProperty(propertyName);
		if (!(getProperty(TARGET_WEB_SERVICE) instanceof Service)) {
			return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ServiceReferenceDataModel_ERROR_8")); //$NON-NLS-1$
		}
		return stat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new ServiceReferenceCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getWSDLUrl()
	 */
	public String getWSDLUrl() {
		return getStringProperty(WSDL_URL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getServiceQName()
	 */
	public String getServiceQName() {
		return getStringProperty(QNAME_NAMESPACE_URI) + ":" + getStringProperty(QNAME_lOCAL_PART); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getServiceInterfaceName()
	 */
	public String getServiceInterfaceName() {
		return serviceInterfaceName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getServiceEndpointInterfaceNames()
	 */
	public String[] getServiceEndpointInterfaceNames() {
		return serviceEndpointInterfaceNames;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#setServiceInterfaceName(java.lang.String)
	 */
	public void setServiceInterfaceName(String name) {
		serviceInterfaceName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#setServiceEndpointInterfaceNames(java.lang.String[])
	 */
	public void setServiceEndpointInterfaceNames(String[] names) {
		serviceEndpointInterfaceNames = names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getProjectName()
	 */
	public String getProjectName() {
		Object owner = getProperty(OWNER);
		String name = ProjectUtilities.getProject(owner).getName();
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#getOutputWSDLFileName()
	 */
	public String getOutputWSDLFileName() {
		Object owner = getProperty(OWNER);
		String outputLocation = null;
		IProject project;
		switch (getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				project = ProjectUtilities.getProject(owner);
				outputLocation = project.getFullPath().toString() + "\\appClientModule\\META-INF\\"; //$NON-NLS-1$
				break;
			case XMLResource.EJB_TYPE :
				project = ProjectUtilities.getProject(owner);
				outputLocation = project.getFullPath().toString() + "\\ejbModule\\META-INF\\"; //$NON-NLS-1$
				break;
			case XMLResource.WEB_APP_TYPE :
				project = ProjectUtilities.getProject(owner);
				outputLocation = project.getFullPath().toString() + "\\Web Content\\WEB-INF\\"; //$NON-NLS-1$
				break;
		}
		if (getProperty(TARGET_WEB_SERVICE) == null)
			return ""; //$NON-NLS-1$
		return outputLocation + ((Service) getProperty(TARGET_WEB_SERVICE)).getQName().getLocalPart() + ".wsdl"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#shouldDeploy()
	 */
	public boolean shouldDeploy() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#shouldGenDescriptors()
	 */
	public boolean shouldGenDescriptors() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservices.WebServicesClientDataHelper#setDidGenDescriptors(boolean)
	 */
	public void setDidGenDescriptors(boolean b) {
		didGenDescriptor = b;
	}

	public boolean didGenDescriptors() {
		return didGenDescriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#isResultProperty(java.lang.String)
	 */
	protected boolean isResultProperty(String propertyName) {
		if (propertyName.equals(JAX_RPC_MAPPING_FILE))
			return true;
		return super.isResultProperty(propertyName);
	}

}