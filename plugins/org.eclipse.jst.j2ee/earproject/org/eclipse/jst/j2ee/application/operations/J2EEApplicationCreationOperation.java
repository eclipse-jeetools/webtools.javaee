/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

public class J2EEApplicationCreationOperation extends J2EEArtifactCreationOperation {

	public J2EEApplicationCreationOperation(J2EEApplicationCreationDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEApplicationCreationDataModel dataModel = (J2EEApplicationCreationDataModel) operationDataModel;

		createProject(monitor);
		setVersion((J2EENature) dataModel.getProjectDataModel().getProject().getNature(IEARNatureConstants.NATURE_ID), monitor);
		addServerTarget(monitor);
		if (dataModel.getBooleanProperty(J2EEApplicationCreationDataModel.CREATE_DEFAULT_FILES)) {
			createApplication(dataModel, monitor);
		}
		addModules(dataModel.getAddModulesToEARDataModel(), monitor);
	}

	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		ProjectCreationDataModel projectModel = ((J2EEArtifactCreationDataModel) operationDataModel).getProjectDataModel();
		ProjectCreationOperation projectOperation = new ProjectCreationOperation(projectModel);
		projectOperation.doRun(monitor);
	}

	private void addModules(AddArchiveProjectsToEARDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		if (null != model) {
			AddArchiveProjectsToEAROperation modulesOperation = new AddArchiveProjectsToEAROperation(model);
			modulesOperation.doRun(monitor);
		}
	}

	private void createApplication(J2EEApplicationCreationDataModel dataModel, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EditModelOperation op = new EditModelOperation(dataModel) {
			protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
				EAREditModel earEditModel = (EAREditModel) editModel;
				earEditModel.makeDeploymentDescriptorWithRoot();
			}
		};
		op.doRun(monitor);
	}

	protected void setVersion(J2EENature nature, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEApplicationCreationDataModel dataModel = (J2EEApplicationCreationDataModel) operationDataModel;
		nature.setModuleVersion(dataModel.getIntProperty(J2EEApplicationCreationDataModel.APPLICATION_VERSION));
	}

}