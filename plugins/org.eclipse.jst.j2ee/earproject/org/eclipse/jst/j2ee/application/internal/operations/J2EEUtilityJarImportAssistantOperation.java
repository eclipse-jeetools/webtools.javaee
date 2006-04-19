/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class J2EEUtilityJarImportAssistantOperation extends AbstractOperation {

	private File utilityJar;

	private boolean overwriteIfNecessary = false;
	private String associatedEARProjectName;

	public J2EEUtilityJarImportAssistantOperation(String label, File utilityJar) {
		super(label);
		this.utilityJar = utilityJar;
	}

	public void setAssociatedEARProjectName(String associatedEARProjectName) {
		this.associatedEARProjectName = associatedEARProjectName;
	}


	public void setOverwriteIfNecessary(boolean overwriteProject) {
		this.overwriteIfNecessary = overwriteProject;
	}

	protected final void createLinkedPathVariable(String linkedPathVariable, File archiveFile) throws CoreException {
		IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
		IPath linkedPath = new Path(archiveFile.getAbsolutePath());
		manager.setValue(linkedPathVariable, linkedPath);

	}

	protected final String getUtilityJarProjectName(File utilityJar) {
		String name = null;
		if (utilityJar != null) {
			int len = utilityJar.getName().lastIndexOf('.');
			name = utilityJar.getName().substring(0, len);
		}
		return name;
	} 

	protected final IStatus linkArchiveToEAR(IProject earProject, String uriMapping, IProject utilityProject, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, ExecutionException {
		IDataModel addArchiveProjectToEARDataModel = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());

		IVirtualComponent earcomponent = ComponentCore.createComponent(earProject);
		IVirtualComponent utilcomponent = ComponentCore.createComponent(utilityProject);

		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earcomponent);
		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Collections.singletonList(utilcomponent));
		Map uriMap = new HashMap();
		uriMap.put(utilcomponent, uriMapping);
		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
		return addArchiveProjectToEARDataModel.getDefaultOperation().execute(monitor, null);
	}

	protected final IStatus createVirtualArchiveComponent(IProject targetProject, String uriMapping, IFile utilityJarIFile, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, ExecutionException {
		IDataModel addArchiveProjectToEARDataModel = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());

		IVirtualComponent earcomponent = ComponentCore.createComponent(targetProject);

		IVirtualComponent utilcomponent = ComponentCore.createArchiveComponent(targetProject, VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR + utilityJarIFile.getProjectRelativePath().toString());

		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earcomponent);
		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Collections.singletonList(utilcomponent));
		Map uriMap = new HashMap();
		uriMap.put(utilcomponent, uriMapping);
		addArchiveProjectToEARDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
		return  addArchiveProjectToEARDataModel.getDefaultOperation().execute(monitor, null);
	}
	

	protected final IStatus removeRootMapping(IVirtualComponent sourceComponent, String uriMapping, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, ExecutionException {

		try {
			sourceComponent.getRootFolder().removeLink(new Path(uriMapping), 0, monitor);
		} catch (CoreException e) { 
			return J2EEPlugin.createErrorStatus(0, e.getMessage(), e);
		}
		return Status.OK_STATUS;
	}	

	protected File getUtilityJar() {
		return utilityJar;
	}

	protected final IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}


	protected final String getAssociatedEARProjectName() {
		return associatedEARProjectName;
	}

	protected final boolean isOverwriteIfNecessary() {
		return overwriteIfNecessary;
	}
 
	protected final void addLibraryToClasspath(IProject associatedEARProject, IFile copiedJarFile, IProgressMonitor monitor) throws CoreException, JavaModelException {
		if (associatedEARProject.hasNature(JavaCore.NATURE_ID)) {

			IClasspathEntry entry = JavaCore.newLibraryEntry(copiedJarFile.getFullPath().makeAbsolute(), null, // source
																												// attachment
						null, // source attachment root
						new IAccessRule[0], // accessRules
						new IClasspathAttribute[0], // extraAttributes
						false); // isExported

			IJavaProject earJavaProject = JavaCore.create(associatedEARProject);
			IClasspathEntry[] rawClasspath = earJavaProject.getRawClasspath();
			IClasspathEntry[] newClasspath = new IClasspathEntry[rawClasspath.length + 1];
			System.arraycopy(rawClasspath, 0, newClasspath, 0, rawClasspath.length);
			newClasspath[rawClasspath.length] = entry;
			earJavaProject.setRawClasspath(newClasspath, monitor);

		}
	}
	
    protected final IStatus createLinkedArchive(IProject project, String linkedFileName, File archiveFile, String linkedPathVariable, IProgressMonitor monitor) throws CoreException {
        IFile linkedJarFile = null; 
        IPath pathToArchive = getLinkedPath(archiveFile, linkedPathVariable);

        linkedJarFile = project.getFile(linkedFileName);
        if (linkedJarFile.exists()) {
            if (isOverwriteIfNecessary())
                linkedJarFile.delete(true, true, new SubProgressMonitor(monitor, 1));
            else
                return J2EEPlugin.createErrorStatus(0, NLS.bind(EARCreationResourceHandler.CreateProjectWithLinkedJarOperation_File_already_exists_0_, linkedJarFile.getFullPath()), null);
        }
        linkedJarFile.createLink(pathToArchive, IResource.ALLOW_MISSING_LOCAL, new SubProgressMonitor(monitor, 1));

        addLibraryToClasspath(project, linkedJarFile, monitor);
        return Status.OK_STATUS;
    } 

    protected final IPath getLinkedPath(File archiveFile, String linkedPathVariable) throws CoreException {
        

        if (linkedPathVariable == null || linkedPathVariable.length() == 0)
            return new Path(archiveFile.getAbsolutePath());
        else {
            createLinkedPathVariable(linkedPathVariable, archiveFile.getParentFile());
            return new Path(linkedPathVariable).append(archiveFile.getName());
        }
    } 

}
