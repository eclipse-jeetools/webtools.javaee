/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.jst.j2ee.application.operations.ExtendedImportFactory;
import org.eclipse.jst.j2ee.application.operations.ExtendedImportRegistry;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBModuleImportOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * This dataModel is used for to import EJB Modules (from EJB Jar files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EJBModuleImportDataModel extends J2EEModuleImportDataModel {

	/**
	 * Imports the specified EJB Jar file into the specified EJB Module project.
	 * 
	 * @param ejbJarFileName
	 *            The path to the EJB Jar file.
	 * @param ejbProjectName
	 *            The name of the EJB project where the EJB Module should be imported.
	 * @param addToEar
	 *            If this is <code>true</code> then EJB project specified by
	 *            <code>ejbProjectName</code> will be added to the Enterprise Application project
	 *            specified by <code>earProjectName</code>.
	 * @param earProjectName
	 *            The name of the Enterprise Application project to add the specified EJB project
	 *            to. This field is only relevant if <code>addToEar</code> is set to
	 *            <code>true</code>. If this is set to <code>null</code> then a default name
	 *            computed from the <code>ejbProjectName</code> will be used.
	 * @since WTP 1.0
	 */
	public static void importArchive(String ejbJarFileName, String ejbProjectName, boolean addToEar, String earProjectName) {
		EJBModuleImportDataModel dataModel = new EJBModuleImportDataModel();
		dataModel.setProperty(FILE_NAME, ejbJarFileName);
		dataModel.setProperty(PROJECT_NAME, ejbProjectName);
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


	protected J2EEArtifactCreationDataModel createJ2EEProjectCreationDataModel() {
		EJBModuleCreationDataModel m = new EJBModuleCreationDataModel();
		m.setProperty(EJBModuleCreationDataModel.CREATE_CLIENT, Boolean.FALSE);
		return m;
	}

	protected int getType() {
		return XMLResource.EJB_TYPE;
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		OpenFailureException cachedException = null;
		try {
			setArchiveFile(CommonarchiveFactory.eINSTANCE.openEJBJarFile(getArchiveOptions(), uri));
		} catch (OpenFailureException e) {
			cachedException = e;
		}
		if (getArchiveFile() == null) {
			List extendedFactories = ExtendedImportRegistry.getInstance().getFactories(ExtendedImportRegistry.EJB_TYPE);
			for (int i = 0; null == getArchiveFile() && i < extendedFactories.size(); i++) {
				ExtendedImportFactory factory = (ExtendedImportFactory) extendedFactories.get(i);
				setArchiveFile(factory.openArchive(getArchiveOptions(), uri));
				setProperty(J2EEModuleImportDataModel.EXTENDED_IMPORT_FACTORY, factory);
			}
		}
		if (getArchiveFile() == null) {
			if (cachedException != null) {
				throw cachedException;
			}
			return false;
		}
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new EJBModuleImportOperation(this);
	}
}