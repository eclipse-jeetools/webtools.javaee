/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.jca.archive.operations.RARProjectLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @deprecated
 * Use
 * <p>
 * 	ConnectorArtifactEdit
 * </p>
 */
public class ConnectorNatureRuntime extends J2EEModuleNature {

	private static final String CONNECTOR_PROJECT_10_OVERLAY = "1_0_ovr"; //$NON-NLS-1$
	private static final String CONNECTOR_PROJECT_11_OVERLAY = "1_1_ovr"; //$NON-NLS-1$

	/**
	 * Default constructor.
	 */
	public ConnectorNatureRuntime() {
		super();
	} // ConnectorNatureRuntime

	/**
	 * Create a new connector runtime from the project info
	 * 
	 * @param ConnectorProjectInfo
	 *            cpInfo - connector project info to be added
	 * @return ConnectorNatureRuntime
	 * @throws CoreException
	 */
	//	public static ConnectorNatureRuntime createRuntime(ConnectorProjectInfo cpInfo) throws
	// CoreException {
	//
	//		IProject project = cpInfo.getProject();
	//
	//		if (!hasRuntime(project)) {
	//			addNatureToProject(project, cpInfo.getNatureId());
	//			ConnectorNatureRuntime cnRuntime = getRuntime(project);
	//			cnRuntime.initializeFromInfo(cpInfo);
	//			
	//			
	//			return cnRuntime;
	//		} // if
	//
	//		return getRuntime(project);
	//
	//	} // createRuntime
	/**
	 * Returns the edit for write operation.
	 * 
	 * @return ConnectorEditModel
	 */
	public ConnectorEditModel getConnectorEditModelForWrite(Object accessorKey) {
		return (ConnectorEditModel) getEditModelForWrite(IConnectorNatureConstants.EDIT_MODEL_ID, accessorKey);
	}// ConnectorEditModel

	/**
	 * Returns the edit for read operation.
	 * 
	 * @return ConnectorEditModel
	 */
	public ConnectorEditModel getConnectortEditModelForRead(Object accessorKey) {
		return (ConnectorEditModel) getEditModelForRead(IConnectorNatureConstants.EDIT_MODEL_ID, accessorKey);
	}// getConnectortEditModelForRead

	/**
	 * Return the root object, the connector, from the ra.xml DD.
	 * 
	 * @return Connector
	 */
	public Connector getConnector() {
		return ((ConnectorEditModel) getCacheEditModel()).getConnector();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		// TODO Auto-generated method stub
		int j2eeVersion;
		switch (getModuleVersion()) {
			case J2EEVersionConstants.JCA_1_0_ID :
				j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
				break;
			default :
				j2eeVersion = J2EEVersionConstants.J2EE_1_4_ID;
		}
		return j2eeVersion;
	}

	/**
	 * Return the RAR DD URI
	 * 
	 * @return Resource
	 * @throws Exception
	 */
	public Resource getConnectorXmiResource() {
		return getResource(URI.createURI(J2EEConstants.RAR_DD_URI));
	} // getConnectorXmiResource


	public String getEditModelKey() {
		return IConnectorNatureConstants.EDIT_MODEL_ID;
	}

	protected EditModel createCacheEditModel() {
		return getConnectortEditModelForRead(this);
	}


	/**
	 * Get a WebNatureRuntime that corresponds to the supplied project.
	 * 
	 * @param IProject
	 *            project - Project to get nature for.
	 * @return ConnectorNatureRuntime
	 */
	public static ConnectorNatureRuntime getRuntime(IProject project) {
		return (ConnectorNatureRuntime) getRuntime(project, IConnectorNatureConstants.CONNECTOR_NATURE_ID);
	} // ConnectorNatureRuntime

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @param IProject
	 *            project - project to check if specfic nature is available.
	 * @return boolean
	 */
	public static boolean hasRuntime(IProject project) {
		return hasRuntime(project, IConnectorNatureConstants.NATURE_ID);
	} // hasRuntime


	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 * 
	 * @return Archive
	 * @throws OpenFailureException
	 */
	public Archive asArchive() throws OpenFailureException {
		return this.asRARFile();
	}// asArchive

	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 * 
	 * @param boolean
	 *            shouldExportSource - Project to get nature for.
	 * @return Archive
	 * @throws OpenFailureException
	 */
	public Archive asArchive(boolean shouldExportSource) throws OpenFailureException {
		return this.asRARFile(shouldExportSource);
	}// asArchive



	/**
	 * Returns a RAR file from the current project.
	 * 
	 * @return RARFile
	 * @throws OpenFailureException
	 */
	public RARFile asRARFile() throws OpenFailureException {

		IProject aProject = this.getProject();

		if (aProject == null) {
			return null;
		}// if

		LoadStrategy loader = new RARProjectLoadStrategyImpl(aProject);
		loader.setResourceSet(this.getResourceSet());

		return this.getCommonArchiveFactory().openRARFile(loader, aProject.getName());

	}// asRARFile

	/**
	 * Returns a RAR file from the current project.
	 * 
	 * @param boolean
	 *            shouldExportSource - Sets export soruce flag.
	 * @return RARFile
	 * @throws OpenFailureException
	 */
	public RARFile asRARFile(boolean shouldExportSource) throws OpenFailureException {

		IProject aProject = this.getProject();

		if (aProject == null) {
			return null;
		}// if

		RARProjectLoadStrategyImpl loader = new RARProjectLoadStrategyImpl(aProject);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());

		return getCommonArchiveFactory().openRARFile(loader, aProject.getName());

	}// asRARFile

	/**
	 * Method used for adding a Connector project to an ear project; subclasses must override to
	 * create a new instance of the correct kind of Module
	 * 
	 * @return Module
	 */
	public Module createNewModule() {
		return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createConnectorModule();
	} // createNewModule

	/**
	 * Return the nature's ID.
	 * 
	 * @return String
	 */
	public String getNatureID() {
		return IConnectorNatureConstants.NATURE_ID;
	} // getNatureID

	/**
	 * Return the ID of the plugin that this nature is contained within.
	 * 
	 * @return String
	 */
	protected String getPluginID() {
		return J2EEPlugin.PLUGIN_ID;
	}// getPluginID

	/**
	 * Return the default source path
	 * 
	 * @return String
	 */
	protected String getDefaultSourcePathString() {
		return IConnectorNatureConstants.DEFAULT_SOURCE_PATH;
	} // getDefaultSourcePathString

	/**
	 * Gets the container.
	 * 
	 * @return IContainer
	 */
	public IContainer getModuleServerRoot() {
		return ProjectUtilities.getJavaProjectOutputContainer(project);
	}// getModuleServerRoot

	/**
	 * Get the module root folder.
	 * 
	 * @return IContainer
	 */
	public IContainer getModuleRoot() {
		return getSourceFolder();
	}// getModuleRoot

	/**
	 * Gets the overlay icon name
	 * 
	 * @return String
	 */
	public String getOverlayIconName() {

		switch (getJ2EEVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
			case J2EEVersionConstants.J2EE_1_3_ID :
				return CONNECTOR_PROJECT_10_OVERLAY;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return CONNECTOR_PROJECT_11_OVERLAY;
		}
	}

	/**
	 * Checks if a uri matches 'META-INF/ra.xml' that of the rar file.
	 * 
	 * @param String
	 *            uri - The current uri
	 * @return boolean
	 */
	public boolean matchXmlUri(String uri) {
		if (uri != null && uri.equals(J2EEConstants.RAR_DD_URI)) {
			return true;
		}// if
		return false;
	}// matchXmlUri



	/**
	 * Creates the nature for a Connector project.
	 */
	public int getDeploymentDescriptorType() {
		return XMLResource.RAR_TYPE;
	}

	public boolean isJ2EE1_3() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		return getConnector();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getVersionFromModuleFile()
	 */
	protected int getVersionFromModuleFile() {

		Connector ddRoot = getConnector();
		if (ddRoot != null) {
			return ddRoot.getVersionID();
		}
		return J2EEVersionConstants.JCA_1_5_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForRead(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForRead(Object accessorKey) {
		return getConnectortEditModelForRead(accessorKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForWrite(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForWrite(Object accessorKey) {
		return getConnectorEditModelForWrite(accessorKey);
	}


} // ConnectorNatureRuntime
