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
package org.eclipse.jst.j2ee.application.operations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;

public abstract class FlexibileJ2EEModuleCreationOperation extends FlexibileJ2EECreationOperation {
	/**
	 * name of the template emitter to be used to generate the deployment descriptor from the tags
	 */
	protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$
	/**
	 * id of the builder used to kick off generation of web metadata based on parsing of annotations
	 */
	protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$
	
	public FlexibileJ2EEModuleCreationOperation(FlexibleJ2EEModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	public FlexibileJ2EEModuleCreationOperation() {
		super();
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		FlexibleJ2EEModuleCreationDataModel dataModel = (FlexibleJ2EEModuleCreationDataModel) operationDataModel;
		createModule(monitor);
		if (dataModel.getBooleanProperty(J2EEArtifactCreationDataModel.CREATE_DEFAULT_FILES)) {
			createDeploymentDescriptor(monitor);
			//J2EENature nature = (J2EENature) dataModel.getProjectDataModel().getProject().getNature(dataModel.getJ2EENatureID());
			//createManifest(nature, monitor);
		}
		linkToEARIfNecessary(dataModel, monitor);
	}

	protected abstract void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException;

	public static void linkToEARIfNecessary(FlexibleJ2EEModuleCreationDataModel moduleModel, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//		if (moduleModel.getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR)) {
//			EnterpriseApplicationCreationDataModel earModel = moduleModel.getApplicationCreationDataModel();
//			if (!earModel.getTargetProject().exists()) {
//				EnterpriseApplicationCreationOperation earOp = new EnterpriseApplicationCreationOperation(earModel);
//				earOp.doRun(monitor);
//			}
//			AddArchiveProjectToEARDataModel addModuleModel = moduleModel.getAddModuleToApplicationDataModel();
//			AddArchiveProjectToEAROperation addModuleOp = new AddArchiveProjectToEAROperation(addModuleModel);
//			addModuleOp.doRun(monitor);
//		}
	}

	protected void createModule(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//		JavaProjectCreationDataModel projectModel = ((J2EEModuleCreationDataModel) operationDataModel).getJavaProjectCreationDataModel();
//		JavaProjectCreationOperation javaProjectOperation = new JavaProjectCreationOperation(projectModel);
//		javaProjectOperation.doRun(monitor);
//		updateClasspath(projectModel);
		//J2EEModuleCreationDataModel dataModel = (J2EEModuleCreationDataModel) operationDataModel;
		//J2EENature nature = (J2EENature) dataModel.getProjectDataModel().getProject().getNature(dataModel.getJ2EENatureID());
		//setVersion(nature, monitor);
		//addServerTarget(monitor);
	}

	/**
	 * @param projectModel
	 * @throws JavaModelException
	 */
//	private void updateClasspath(JavaProjectCreationDataModel projectModel) throws JavaModelException {
//		ClassPathSelection classpath = ((J2EEModuleCreationDataModel) getOperationDataModel()).getClassPathSelection();
//		if (classpath == null || classpath.getClasspathElements().size() == 0)
//			return;
//		IClasspathEntry[] newEntries = classpath.getClasspathEntriesForSelected();
//		IProject project = projectModel.getProject();
//		if (project == null)
//			return;
//		IJavaProject javaProject = JavaCore.create(project);
//		IClasspathEntry[] existingEntries = javaProject.getRawClasspath();
//
//		List allEntries = new ArrayList();
//		if (existingEntries != null && existingEntries.length > 0)
//			allEntries.addAll(Arrays.asList(existingEntries));
//		if (newEntries != null && newEntries.length > 0)
//			allEntries.addAll(Arrays.asList(newEntries));
//		IClasspathEntry[] finalEntriesArray = new IClasspathEntry[allEntries.size()];
//		allEntries.toArray(finalEntriesArray);
//		javaProject.setRawClasspath(finalEntriesArray, new NullProgressMonitor());
//	}

	/**
	 * @param monitor
	 */
//	protected void createManifest(J2EENature nature, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//		if (nature.getEMFRoot() != null) {
//			try {
//				IFile file = nature.getEMFRoot().getFile(new Path(J2EEConstants.MANIFEST_URI));
//				ManifestFileCreationAction.createManifestFile(file, nature.getProject());
//			} catch (IOException ioe) {
//				com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ioe);
//				return;
//			}
//			UpdateManifestOperation op = new UpdateManifestOperation(((J2EEModuleCreationDataModel) operationDataModel).getUpdateManifestDataModel());
//			op.doRun(monitor);
//		}
//	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#dispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void dispose(IProgressMonitor pm) {
		try {
			getOperationDataModel().dispose();
			super.dispose(pm);
		} catch (RuntimeException re) {
			//Ignore
		}
	}
	
	/**
	 * This method is intended for internal use only.  This method will add the annotations builder
	 * for Xdoclet to the targetted project.  This needs to be removed from the operation and set
	 * up to be more extensible throughout the workbench.
	 * @see EJBModuleCreationOperation#execute(IProgressMonitor)
	 * 
	 * @deprecated
	 */
	protected final void addAnnotationsBuilder() {
		try {
			// Find the xdoclet builder from the extension registry
			IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(TEMPLATE_EMITTER);
			String builderID = configurationElements[0].getNamespace() + "."+ configurationElements[0].getAttribute(BUILDER_ID); //$NON-NLS-1$
			IProject project = operationDataModel.getTargetProject(); 
			IProjectDescription description = project.getDescription();
			ICommand[] commands = description.getBuildSpec();
			boolean found = false;
			// Check if the builder is already set on the project
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(builderID)) {
					found = true;
					break;
				}
			}
			// If the builder is not on the project, add it
			if (!found) {
				ICommand command = description.newCommand();
				command.setBuilderName(builderID);
				ICommand[] newCommands = new ICommand[commands.length + 1];
				System.arraycopy(commands, 0, newCommands, 0, commands.length);
				newCommands[commands.length] = command;
				IProjectDescription desc = project.getDescription();
				desc.setBuildSpec(newCommands);
				project.setDescription(desc, null);
			}
		} catch (Exception e) {
			//Ignore
		}
	}

}