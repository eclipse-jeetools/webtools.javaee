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
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleWorkbenchURIConverterImpl;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.WebEditModel;
import org.eclipse.jst.j2ee.internal.web.operations.WebSettingsMigrator;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

import com.ibm.wtp.emf.workbench.ProjectResourceSet;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WebProjectCreationOperation extends J2EEModuleCreationOperation {
	public WebProjectCreationOperation(WebProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	public WebProjectCreationOperation() {
		super();
	}

	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.createProject(monitor);
		J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(operationDataModel.getTargetProject());
		nature.getWebSettings().setWebContentName(operationDataModel.getStringProperty(WebProjectCreationDataModel.WEB_CONTENT));
		nature.getWebSettings().setContextRoot(operationDataModel.getStringProperty(WebProjectCreationDataModel.CONTEXT_ROOT));
		URIConverter uriConverter = ((ProjectResourceSet) nature.getResourceSet()).getURIConverter();
		if (uriConverter instanceof J2EEModuleWorkbenchURIConverterImpl)
			((J2EEModuleWorkbenchURIConverterImpl) uriConverter).recomputeContainersIfNecessary();
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EditModelOperation op = new EditModelOperation((J2EEModuleCreationDataModel) operationDataModel) {
			protected void execute(IProgressMonitor amonitor) throws CoreException, InvocationTargetException, InterruptedException {
				WebEditModel model = (WebEditModel) editModel;
				IFolder metainf = model.getWebNature().getEMFRoot().getFolder(new Path(J2EEConstants.META_INF));
				if (!metainf.exists()) {
					IFolder parent = metainf.getParent().getFolder(null);
					if (!parent.exists()) {
						parent.create(true, true, null);
					}
					metainf.create(true, true, null);
				}
				IFolder webinf = model.getWebNature().getEMFRoot().getFolder(new Path(J2EEConstants.WEB_INF));
				if (!webinf.exists()) {
					webinf.create(true, true, null);
				}
				IFolder lib = webinf.getFolder("lib"); //$NON-NLS-1$
				if (!lib.exists()) {
					lib.create(true, true, null);
				}
				model.makeDeploymentDescriptorWithRoot();
			}
		};
		op.doRun(monitor);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute(monitor);
		J2EEModuleCreationDataModel dataModel = (J2EEModuleCreationDataModel) operationDataModel;
		if (dataModel.getBooleanProperty(WebProjectCreationDataModel.MIGRATE_WEB_SETTINGS)) {
			IProject project = dataModel.getProjectDataModel().getProject();
			J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
			webNature.getWebSettings().write();
			project.getFile(webNature.getWebSettingsPath()).refreshLocal(0, monitor);
			WebSettingsMigrator migrator = new WebSettingsMigrator();
			migrator.migrate(project);
		}
	}
}