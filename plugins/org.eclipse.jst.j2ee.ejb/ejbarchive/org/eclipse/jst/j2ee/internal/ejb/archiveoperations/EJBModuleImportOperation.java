/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 5, 2003
 *  
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.ExtendedImportFactory;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

public class EJBModuleImportOperation extends J2EEArtifactImportOperation {

	public EJBModuleImportOperation(EJBModuleImportDataModel dataModel) {
		super(dataModel);
	}

	protected void createModuleProject(J2EEArtifactCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EJBModuleCreationOperation op = new EJBModuleCreationOperation((EJBModuleCreationDataModel) model);
		op.run(monitor);
	}

	protected SaveStrategy createSaveStrategy(IProject project) {
		EJBProjectSaveStrategyImpl saveStrat = new EJBProjectSaveStrategyImpl(project);
		return saveStrat;
	}

	protected void importModuleFile(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		if (null == operationDataModel.getProperty(EJBModuleImportDataModel.EXTENDED_IMPORT_FACTORY)) {
			super.importModuleFile(monitor);
			EJBModuleImportDataModel dm = (EJBModuleImportDataModel) getOperationDataModel();
			IProject project = dm.getProject();
			dm.getJ2EEModuleCreationDataModel().getJ2EEVersion();
			if (!J2EEPlugin.hasDevelopmentRole() && project != null && dm.getJ2EEModuleCreationDataModel().getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID) {
				EJBUnknownPrimKeyHelper helper = new EJBUnknownPrimKeyHelper(dm);
				helper.setUpUnknowPrimaryKey();
			}
		} else {
			ExtendedImportFactory factory = (ExtendedImportFactory) operationDataModel.getProperty(EJBModuleImportDataModel.EXTENDED_IMPORT_FACTORY);
			factory.importModuleFile((J2EEModuleImportDataModel) operationDataModel, monitor);
		}
	}
}