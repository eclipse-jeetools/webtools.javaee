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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.Property;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.impl.PropertyImpl;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;

public abstract class J2EEComponentCreationOperation extends J2EECreationOperation {
	/**
	 * name of the template emitter to be used to generate the deployment descriptor from the tags
	 */
	protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$
	/**
	 * id of the builder used to kick off generation of web metadata based on parsing of annotations
	 */
	protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$
	
	public J2EEComponentCreationOperation(J2EEComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public J2EEComponentCreationOperation() {
		super();
	}

	protected void execute( String componentType, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEComponentCreationDataModel dataModel = (J2EEComponentCreationDataModel) operationDataModel;
		
	    createProjectStructure();
		createComponent( componentType, monitor);
		
		if (dataModel.getBooleanProperty(J2EEComponentCreationDataModel.CREATE_DEFAULT_FILES)) {
			createDeploymentDescriptor(monitor);
		}
		
		addSrcFolderToProject();
		if (((J2EEComponentCreationDataModel) operationDataModel).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			addAnnotationsBuilder();	
		
		linkToEARIfNecessary(dataModel, monitor);
	}

	protected abstract void createProjectStructure() throws CoreException;
	

	
	protected abstract void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException;

	public static void linkToEARIfNecessary(J2EEComponentCreationDataModel moduleModel, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
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

	
		protected void createComponent( String moduletype, IProgressMonitor monitor ) throws CoreException, InvocationTargetException, InterruptedException {
	    	ModuleCore moduleCore = null;
			try {
				IProject containingProject = getProject();
				moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
				moduleCore.prepareProjectModulesIfNecessary(); 
				ProjectComponents projectModules = moduleCore.getModuleModelRoot();
				
				addContent(projectModules, moduletype);
				moduleCore.saveIfNecessary(null); 
			} finally {
				if(moduleCore != null)
					moduleCore.dispose();
			}     
		}
		public IProject getProject() {
			String projName = operationDataModel.getStringProperty(J2EEComponentCreationDataModel.PROJECT_NAME );
			return ProjectUtilities.getProject( projName );
		}
		
		protected abstract String getVersion();
		protected void addProperties(WorkbenchComponent module){
			return;
		}
		
		private void addContent(ProjectComponents projectModules, String moduletype) {

		    WorkbenchComponent module = addWorkbenchModule(projectModules, getModuleDeployName(), 
		    			moduletype, createModuleURI()); //$NON-NLS-1$
	
		    module.getComponentType().setVersion(getVersion());
		    addProperties(module);

		    addResources( module );
		}		
		
		protected abstract void addResources( WorkbenchComponent component );
		
		public WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, 
					String moduletype, URI aHandle) {
			WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchComponent();
			module.setHandle(aHandle);  
			module.setName(aDeployedName);  
			ComponentType type = ModuleCoreFactory.eINSTANCE.createComponentType();
			type.setModuleTypeId(moduletype);
			module.setComponentType(type);
			theModules.getComponents().add(module);
			return module;
		}
		
		
		public String getModuleName() {
			return (String)operationDataModel.getProperty(J2EEComponentCreationDataModel.MODULE_NAME);
		}
		
		public String getModuleDeployName() {
			return (String)operationDataModel.getProperty(J2EEComponentCreationDataModel.MODULE_DEPLOY_NAME);
		}
		

		private URI createModuleURI() {
			return URI.createURI("module:/resource/"+getProject().getName()+IPath.SEPARATOR+ getModuleDeployName()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		

		public IFile getModuleRelativeFile(String aModuleRelativePath, IProject project) {
			return getProject().getFile(new Path(IPath.SEPARATOR + aModuleRelativePath));
		}

		public void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
			ComponentResource resource = ModuleCoreFactory.eINSTANCE.createComponentResource();		
			resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
			resource.setRuntimePath(URI.createURI(aDeployPath));
			aModule.getResources().add(resource);
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
//				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ioe);
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
			IProject project = ((J2EEComponentCreationDataModel)operationDataModel).getTargetProject(); 
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
    /**
     * Create a folder for given absolute path
     * 
     * @exception com.ibm.itp.core.api.resources.CoreException
     */
    protected IFolder createFolder(IPath absolutePath) throws CoreException {
        if (absolutePath == null || absolutePath.isEmpty())
            return null;
        IFolder folder = getWorkspace().getRoot().getFolder(absolutePath);
        // check if the parent is there
        IContainer parent = folder.getParent();
        if (parent != null && !parent.exists() && (parent instanceof IFolder))
            createFolder(parent.getFullPath());
        if (!folder.exists())
            folder.create(true, true, new NullProgressMonitor());
        return folder;
    }
	
	/**
	 * @return
	 */
	public String getJavaSourceSourceFolder() {
		return getOperationDataModel().getStringProperty(J2EEComponentCreationDataModel.JAVASOURCE_FOLDER);
	}

	/**
	 * @return
	 */
	public String getDeploymentDescriptorFolder() {
		return getOperationDataModel().getStringProperty(J2EEComponentCreationDataModel.DD_FOLDER);
	}
	
	public String getJavaSourceSourcePath() {
		return getOperationDataModel().getStringProperty(J2EEComponentCreationDataModel.JAVASOURCE_FOLDER);
	}
	
    
	private IClasspathEntry[] getClasspathEntries() {
		IClasspathEntry[] sourceEntries = null;
		sourceEntries = getSourceClasspathEntries();
		return sourceEntries;
	}	
	
	private IClasspathEntry[] getSourceClasspathEntries() {
		String sourceFolder = getJavaSourceSourcePath();
		ArrayList list = new ArrayList();
		list.add(JavaCore.newSourceEntry(getProject().getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}
	
	private void addSrcFolderToProject() {
		IJavaProject javaProject = JavaCore.create( this.getProject());
		try {

			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = getClasspathEntries();
			
			int oldSize = oldEntries.length;
			int newSize = newEntries.length;
			
			IClasspathEntry[] classpathEnties = new IClasspathEntry[oldSize + newSize];
			int k = 0;
			for (int i = 0; i < oldEntries.length; i++) {
				classpathEnties[i] = oldEntries[i];
				k++;
			}
			for( int j=0; j< newEntries.length; j++){
				classpathEnties[k] = newEntries[j];
				k++;
			}
			javaProject.setRawClasspath(classpathEnties, null);
		}
		catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}	
}