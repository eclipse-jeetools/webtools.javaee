/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * This dataModel is used for to import Web Modules (from WAR files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class WebModuleImportDataModel extends J2EEModuleImportDataModel {
	//do not modify this property constant
	public static final String HANDLED_ARCHIVES = "WARImportDataModel.HANDLED_ARCHIVES"; //$NON-NLS-1$

	/**
	 * Imports the specified WAR file into the specified Web Module project.
	 * 
	 * @param warFileName
	 *            The path to the WAR file.
	 * @param webProjectName
	 *            The name of the Web project where the Web Module should be imported.
	 * @param addToEar
	 *            If this is <code>true</code> then Web project specified by
	 *            <code>webProjectName</code> will be added to the Enterprise Application project
	 *            specified by <code>earProjectName</code>.
	 * @param earProjectName
	 *            The name of the Enterprise Application project to add the specified Web project
	 *            to. This field is only relevant if <code>addToEar</code> is set to
	 *            <code>true</code>. If this is set to <code>null</code> then a default name
	 *            computed from the <code>webProjectName</code> will be used.
	 * @since WTP 1.0
	 */
	public static void importArchive(String warFileName, String webProjectName, boolean addToEar, String earProjectName) {
		WebModuleImportDataModel dataModel = new WebModuleImportDataModel();
		dataModel.setProperty(FILE_NAME, warFileName);
		dataModel.setProperty(PROJECT_NAME, webProjectName);
		dataModel.setBooleanProperty(ADD_TO_EAR, addToEar);
		if (earProjectName != null) {
			dataModel.setProperty(EAR_PROJECT, earProjectName);
		}
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(HANDLED_ARCHIVES);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(BINARY)) {
			return Boolean.FALSE;
		}
		if (propertyName.equals(HANDLED_ARCHIVES)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected J2EEArtifactCreationDataModelOld createJ2EEProjectCreationDataModel() {
		WebModuleCreationDataModel dm = new WebModuleCreationDataModel();
		dm.setBooleanProperty(J2EEArtifactCreationDataModelOld.ADD_SERVER_TARGET, false);
		dm.setBooleanProperty(WebModuleCreationDataModel.MIGRATE_WEB_SETTINGS, false);
		return dm;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean returnVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(PRESERVE_PROJECT_METADATA) || ((propertyName.equals(FILE) || propertyName.equals(FILE_NAME)) && getBooleanProperty(PRESERVE_PROJECT_METADATA))) {
			String webContentName = null;
			if (getBooleanProperty(PRESERVE_PROJECT_METADATA)) {
				WARFileImpl warFile = (WARFileImpl) getArchiveFile();
				if (null != warFile) {
					if (warFile.containsFile(".j2ee")) { //$NON-NLS-1$
						//To do:  Needs work here, no web content exists now
//						try {
//							//webContentName = WebSettings.getWebContentDirectory(warFile.getInputStream(".j2ee")); //$NON-NLS-1$
//							ArtifactEdit artifact = null;
//							WebArtifactEdit webEdit = null;
//							try{
//								artifact = ModuleCore.getFirstArtifactEditForRead( project );
//								webEdit = ( WebArtifactEdit )artifact;
//					       		if(webEdit != null) {
//               		
//					       		}			
//							}catch (Exception e) {
//								e.printStackTrace();
//							}finally{
//								if( webEdit != null )
//									webEdit.dispose();
//							}	
//							
//						} catch (FileNotFoundException e) {
//							//Do nothing
//						} catch (IOException e) {
//							//Do nothing
//						}
					}
				}
			}
			setProperty(WebModuleCreationDataModel.WEB_CONTENT, webContentName);
		}
		return returnVal;
	}


	protected int getType() {
		return XMLResource.WEB_APP_TYPE;
	}

	protected IStatus validateModuleType() {
		if (getArchiveFile() instanceof WARFile)
			return OK_STATUS;

		//TODO: STRING MOVE
		return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString("Temp String for none WARFile")); //$NON-NLS-1$);
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		setArchiveFile(CommonarchiveFactory.eINSTANCE.openWARFile(getArchiveOptions(), uri));
		if (getArchiveFile() == null)
			return false;
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new WebModuleImportOperation(this);
	}

	public void extractHandled(List newList, boolean addModels) {
		List handledList = new ArrayList();
		WARFile warFile = (WARFile) getArchiveFile();
		List libArchives = warFile.getLibArchives();
		J2EEArtifactImportDataModel model = null;
		for (int i = newList.size() - 1; i > -1; i--) {
			model = (J2EEArtifactImportDataModel) newList.get(i);
			if (libArchives.contains(model.getArchiveFile())) {
				if (addModels) {
					handledList.add(model);
				}
				newList.remove(model);
			}
		}
		if (addModels) {
			setProperty(HANDLED_ARCHIVES, handledList);
		}
	}

	public boolean handlesArchive(Archive archive) {
		List list = (List) getProperty(HANDLED_ARCHIVES);
		J2EEArtifactImportDataModel model = null;
		for (int i = 0; i < list.size(); i++) {
			model = (J2EEArtifactImportDataModel) list.get(i);
			if (model.getArchiveFile() == archive) {
				return true;
			}
		}
		return false;
	}

}