/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class J2EEArtifactExportOperation extends AbstractDataModelOperation {

	protected IProgressMonitor progressMonitor;
	private IVirtualComponent component;
	private IPath destinationPath;
	private ModuleFile moduleFile;
	private boolean exportSource = false;

	public J2EEArtifactExportOperation() {
		super();
	}

	public J2EEArtifactExportOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		setComponent((IVirtualComponent) model.getProperty(IJ2EEComponentExportDataModelProperties.COMPONENT));
		setDestinationPath(new Path(model.getStringProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION)));
		setExportSource(model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES));
		try {
			setProgressMonitor(monitor);
			// defect 240999
			component.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			if (model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD)) {
				runNecessaryBuilders(component, monitor);
			}
			export();
		} catch (Exception e) {
			throw new ExecutionException(EJBArchiveOpsResourceHandler.Error_exporting__UI_ + archiveString(), e);
		}
		return OK_STATUS;
	}

	protected abstract void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException;

	protected abstract String archiveString();

	protected void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	protected IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	private void setComponent(IVirtualComponent newComponent) {
		component = newComponent;
	}

	protected IVirtualComponent getComponent() {
		if (component == null)
			component = (IVirtualComponent) model.getProperty(IJ2EEComponentExportDataModelProperties.COMPONENT);
		return component;
	}

	protected IPath getDestinationPath() {
		return destinationPath;
	}

	protected void setDestinationPath(IPath newDestinationPath) {
		destinationPath = newDestinationPath;
	}

	protected boolean isExportSource() {
		return exportSource;
	}

	protected void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	protected ModuleFile getModuleFile() {
		return moduleFile;
	}

	protected void setModuleFile(ModuleFile newModuleFile) {
		moduleFile = newModuleFile;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	protected void runNecessaryBuilders(IVirtualComponent component, IProgressMonitor monitor) throws CoreException {
		IProject project = component.getProject();
		IProjectDescription description = project.getDescription();
		ICommand javaBuilder = getJavaCommand(description);
		if (javaBuilder != null) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, JavaCore.BUILDER_ID, javaBuilder.getArguments(), monitor);
		}
		ICommand libCopyBuilder = getLibCopyBuilder(description);
		if (null != libCopyBuilder) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, LibCopyBuilder.BUILDER_ID, libCopyBuilder.getArguments(), monitor);
		}
	}

	/**
	 * Find the specific Java command amongst the build spec of a given description
	 */
	protected ICommand getJavaCommand(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(JavaCore.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;
	}

	protected ICommand getLibCopyBuilder(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(LibCopyBuilder.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;

	}

	public ISchedulingRule getSchedulingRule() {
		Set projs = gatherDependentProjects(getComponent(), new HashSet());
		ISchedulingRule combinedRule = null;
		IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
		for (Iterator iter = projs.iterator(); iter.hasNext();) {
			IProject proj = (IProject) iter.next();
			ISchedulingRule rule = ruleFactory.createRule(proj);
			combinedRule = MultiRule.combine(rule, combinedRule);
		}
		combinedRule = MultiRule.combine(ruleFactory.buildRule(), combinedRule);

		return combinedRule;
	}

	private Set gatherDependentProjects(IVirtualComponent comp, Set projs) {
		if (!projs.contains(comp.getProject())) {
			projs.add(comp.getProject());
			IVirtualReference[] refs = comp.getReferences();
			for (int i = 0; i < refs.length; i++) {
				IVirtualReference refComp = refs[i];
				projs.addAll(gatherDependentProjects(refComp.getReferencedComponent(), projs));
			}
		}
		return projs;
	}

}
