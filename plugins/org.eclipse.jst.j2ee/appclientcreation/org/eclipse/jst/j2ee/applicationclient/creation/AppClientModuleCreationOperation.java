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
 * Created on Nov 5, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.applicationclient.creation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationOperationOld;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

public class AppClientModuleCreationOperation extends J2EEModuleCreationOperationOld {

	public AppClientModuleCreationOperation(AppClientModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EditModelOperation op = new EditModelOperation((J2EEModuleCreationDataModelOld) operationDataModel) {
			protected void execute(IProgressMonitor localMonitor) throws CoreException, InvocationTargetException, InterruptedException {
				AppClientEditModel model = (AppClientEditModel) editModel;

				IFolder metainf = model.getAppClientNature().getEMFRoot().getFolder(new Path(ArchiveConstants.META_INF));
				if (!metainf.exists()) {
					metainf.create(true, true, null);
				}

				model.makeDeploymentDescriptorWithRoot();
				AppClientModuleCreationDataModel dataModel = (AppClientModuleCreationDataModel) operationDataModel;
				if (dataModel.getBooleanProperty(AppClientModuleCreationDataModel.CREATE_DEFAULT_MAIN_CLASS)) {
					NewJavaClassDataModel mainClassDataModel = new NewJavaClassDataModel();
					mainClassDataModel.setProperty(NewJavaClassDataModel.PROJECT_NAME, dataModel.getProjectDataModel().getProject().getName());
					mainClassDataModel.setProperty(NewJavaClassDataModel.CLASS_NAME, "Main"); //$NON-NLS-1$
					mainClassDataModel.setBooleanProperty(NewJavaClassDataModel.MAIN_METHOD, true);
					mainClassDataModel.getDefaultOperation().run(localMonitor);
					dataModel.getUpdateManifestDataModel().setProperty(UpdateManifestDataModel.MAIN_CLASS, mainClassDataModel.getProperty(NewJavaClassDataModel.CLASS_NAME));
				}
			}
		};
		op.doRun(monitor);
	}
}