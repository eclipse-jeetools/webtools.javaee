/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;


/**
 * @author schacher
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBClientJARRemovalOperation extends AbstractDataModelOperation 
 implements IEjbClientProjectRemovalDataModelProperties{

	protected IProject ejbProject;
	
	protected IProject clientProject;
	
	protected IPath clientProjectPath, ejbProjectPath;

	protected List sourceContainers, libraryContainers;

	protected IContainer ejbSourceContainer;

	protected IPath ejbSourcePath;

	protected IPath outputPath;

	protected boolean shouldDelete;

	protected boolean yesToAll = false;
	
	protected IWorkspace workspace;
	
	protected IProgressMonitor monitor;
	
	protected IProgressMonitor moveResourceMonitor;
	
	protected IOperationHandler operationHandler;

	public EJBClientJARRemovalOperation(IDataModel dataModel) {
		super(dataModel);
		shouldDelete = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.clientjarcreation.AbstractEJBClientJAROperation#initialize()
	 */
	protected void initialize() {
//		super.initialize();
		ejbProject = (IProject) model.getProperty( IEjbClientProjectRemovalDataModelProperties.EJB_PROJECT );
		clientProject = (IProject) model.getProperty( IEjbClientProjectRemovalDataModelProperties.EJB_CLIENT_VIEW_PROJECT );
		operationHandler = (IOperationHandler)model.getProperty( IEjbClientProjectRemovalDataModelProperties.OP_HANDLER );
		
		workspace = ejbProject.getWorkspace();
		clientProjectPath = clientProject.getFullPath();
		ejbProjectPath = ejbProject.getFullPath();
		sourceContainers = JemProjectUtilities.getSourceContainers(clientProject);
		libraryContainers = JemProjectUtilities.getLibraryContainers(clientProject);
		IJavaProject proj = JavaCore.create(clientProject);
		try {
			outputPath = proj.getOutputLocation().removeFirstSegments(1);
		} catch (JavaModelException e) {
			//Ignore
		}

		IContainer container = J2EEProjectUtilities.getSourceFolderOrFirst( ejbProject, null );
		if( container.getType() == IResource.FOLDER ){
			ejbSourceContainer = container;
		}

		ejbSourcePath = ejbSourceContainer.getFullPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.clientjarcreation.AbstractEJBClientJAROperation#addAdditionalFilesForValidateEdit(java.util.List)
	 */
	protected void addAdditionalFilesForValidateEdit(final List roFiles) throws CoreException {
		IResourceVisitor visitor = new IResourceVisitor() {
			public boolean visit(IResource resource) throws CoreException {
				if (resource.isDerived())
					return false;
				switch (resource.getType()) {
					case IResource.PROJECT :
						return true;
					case IResource.FOLDER :
						return true;
					case IResource.FILE :
						if (resource.isReadOnly())
							roFiles.add(resource);
						break;
					default :
						break;
				}
				return false;
			}
		};

		clientProject.accept(visitor);
	}


	
	public IStatus execute(IProgressMonitor aMonitor, IAdaptable info) throws ExecutionException {
		monitor = aMonitor;
		initialize();
		try{
			if (!verifyFilesInSync()) {
				monitor.done();
				throw new OperationCanceledException();
			}
			monitor.beginTask(ClientJARCreationConstants.REMOVING_CLIENT_JAR, 10);
			//It would be nice to only make one pass through the EARs. However, we can't.
			//The reason is that both JARs must be in the EAR when moving JAR dependencies.
			//Then when we are done we can remove the client JARs from the EARs.
			ensureEJBJARInAllEARs();
			updateEJBModuleJARDependencies();
			EJBClientJarCreationHelper.copyOutgoingClasspathEntries(clientProject, ejbProject, false);
			moveIncomingJARDependencies();
			updateDD();
			removeClientProjectFromEARs();
			moveFiles();
			// add all the source containers to the class path
			// add the root to the class path
			addSourceContainersToClassPath();
			//
			if (moveResourceMonitor.isCanceled())
				throw new InterruptedException();
			deleteClientProject();
		}catch(CoreException e){
			Logger.getLogger().logError( e );
		}catch(InvocationTargetException e){
			Logger.getLogger().logError( e );
		}catch(InterruptedException e){
			Logger.getLogger().logError( e );
		}

		return OK_STATUS;
	}


	/**
	 * @throws JavaModelException
	 *  
	 */
	private void addSourceContainersToClassPath() throws JavaModelException {
		for (int x = 0; x < sourceContainers.size(); ++x) {
			if (sourceContainers.get(x) instanceof IResource) {
				IResource resource = (IResource) sourceContainers.get(x);
				if (clientProject == resource)
					return;
				IPath srcPath = ejbProjectPath.append(resource.getProjectRelativePath());
				IFolder existing = workspace.getRoot().getFolder(srcPath);
				if (existing != null && existing.exists())
					JemProjectUtilities.appendJavaClassPath(ejbProject, JavaCore.newSourceEntry(srcPath));
			}
		}
	}

	/*
	 * Remove the client JAR entry from the deployment descriptor
	 */
	private void updateDD() {
		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite(ejbProject);

			if (ejbEdit != null) {
				EJBJarImpl ejbres = (EJBJarImpl) ejbEdit.getDeploymentDescriptorRoot();
				ejbres.setEjbClientJar( null );
				ejbEdit.saveIfNecessary( null );
			}
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}
	}

	/*
	 * In cross EAR references, it is possible the EJB module is not included in the referencing
	 * EAR. If so, then we must add the EJB module as a utility JAR to the referencing EAR.
	 */
	private void ensureEJBJARInAllEARs() {
		//TODO switch to use component API
//		for (int i = 0; i < earNatures.length; i++) {
//			ensureEJBJARInEAR(earNatures[i]);
//		}
	}

	//TODO fix up ensure method to use component API
//	private void ensureEJBJARInEAR(EARNatureRuntime runtime) {
//		String ejbURI = runtime.getJARUri(ejbProject);
//		if (ejbURI != null)
//			return;
//		ejbURI = J2EEProjectUtilities.getUtilityJARUriInFirstEAR(ejbProject);
//		EAREditModel model = null;
//		try {
//			model = runtime.getEarEditModelForWrite(this);
//			AddUtilityJARMapCommand cmd = new AddUtilityJARMapCommand(model, ejbURI, ejbProject);
//			model.getCommandStack().execute(cmd);
//			IProgressMonitor subMonitor = createSubProgressMonitor(1);
//			model.saveIfNecessary(subMonitor, this);
//		} finally {
//			if (model != null)
//				model.releaseAccess(this);
//		}
//	}

	/*
	 * copy all JAR dependencies not already contained by the ejb module,
	 * from the client JAR to the ejb module,
	 * and remove the module dependency from the EJB module
	 */
	private void updateEJBModuleJARDependencies() throws InvocationTargetException, InterruptedException {

		
		ArrayList list = new ArrayList();
		list.add(clientProject.getName() + ".jar"); //$NON-NLS-1$
		
		IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects( ejbProject );
		
		//remove  project dependency of client project from the  EJB project.
		IDataModel dataModel = DataModelFactory.createDataModel( new JARDependencyDataModelProvider() );
		dataModel.setProperty( JARDependencyDataModelProperties.PROJECT_NAME, ejbProject.getName());
		dataModel.setProperty( JARDependencyDataModelProperties.JAR_LIST, list );
		dataModel.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME,
					earProjects[0].getName());
		dataModel.setIntProperty( JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE,
					JARDependencyDataModelProperties.JAR_MANIPULATION_REMOVE );
		dataModel.setProperty( JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME,
					clientProject.getName() );
		
		try {
			dataModel.getDefaultOperation().execute( createSubProgressMonitor(1), null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError( e );
		}

		
		
		ArchiveManifest clientMf = J2EEProjectUtilities.readManifest(clientProject);
		if (clientMf == null)
			return;
		String[] mfEntries = clientMf.getClassPathTokenized();
		if (mfEntries.length == 0)
			return;
		
		createSubProgressMonitor( earProjects.length );
		
		//copy all JAR dependencies not already contained by the ejb module,
		//from the client JAR to the ejb module	

		for (int i = 0; i < earProjects.length; i++) {
			List result = EJBClientJarCreationHelper.normalize(mfEntries, earProjects[i], ejbProject, false);
			
			IDataModel dataModel2 = DataModelFactory.createDataModel( new JARDependencyDataModelProvider() );
			dataModel2.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, ejbProject.getName());
			dataModel2.setProperty(JARDependencyDataModelProperties.JAR_LIST, result);
			dataModel2.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME, earProjects[i].getName());
			dataModel2.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE,
						JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
			
			try{
				dataModel2.getDefaultOperation().execute( createSubProgressMonitor(1), null );
			}catch (ExecutionException e) {
				Logger.getLogger().logError( e );
			}
		}
	}

	/*
	 * For each module or JAR in each EAR that references the client JAR, replace the dependency
	 * from the client JAR to the EJB module
	 */
	private void moveIncomingJARDependencies() throws InvocationTargetException, InterruptedException {
		
		InvertClientJARDependencyCompoundOperation op = 
			new InvertClientJARDependencyCompoundOperation( J2EEProjectUtilities.getReferencingEARProjects( ejbProject ),
						clientProject,
						ejbProject );
		try {
			op.execute(createSubProgressMonitor(1), null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError( e );
		}
	}

	/*
	 * move all the non-derived files from the client JAR project into the EJB project. Use an
	 * overwrite handler to deal with collisions.
	 */
	private void moveFiles() throws CoreException {
		moveResourceMonitor = createSubProgressMonitor(1);
		IResourceVisitor visitor = getRootResourceVisitor();
		clientProject.accept(visitor);
	}

	private IResourceVisitor getRootResourceVisitor() {

		return new IResourceVisitor() {
			private boolean projectIsSource = sourceContainers.contains(clientProject);

			public boolean visit(IResource resource) throws CoreException {
				if (moveResourceMonitor.isCanceled())
					return false;
				switch (resource.getType()) {
					case IResource.FILE :
						if (!resource.isDerived())
							visitFile((IFile) resource);
						return false;
					case IResource.FOLDER :
						return visitFolder((IFolder) resource);
					case IResource.PROJECT :
						return true;
					default :
						return false;
				}
			}

			private void visitFile(IFile file) throws CoreException {
				if (isMetaFile(file))
					return;
				else if (projectIsSource)
					moveFile(file, clientProjectPath, ejbSourcePath);
				else
					moveFile(file, clientProjectPath, ejbProjectPath);

			}

			private boolean isMetaFile(IFile file) {
				String segment = file.getProjectRelativePath().toString();
				return ProjectUtilities.DOT_CLASSPATH.equals(segment) ||
						ProjectUtilities.DOT_PROJECT.equals(segment) ||
						".runtime".equals(segment) ||
						IModuleConstants.COMPONENT_FILE_NAME.equals(segment) ||
						segment.startsWith(".settings"); //$NON-NLS-1$ 
			}

			private boolean visitFolder(IFolder folder) throws CoreException {
				if (isSourceFolder(folder)) {
					visitSourceFolder(folder);
					return false;
				} else if (isOutputFolder(folder)) {
					return false;
				} else if (isClassesFolder(folder)) {
					visitClassesFolder(folder);
					return false;
				} else
					return true;
			}

			private boolean isOutputFolder(IFolder folder) {
				IPath path = folder.getProjectRelativePath();
				while (path.segmentCount() > 0 && null != outputPath) {
					if (path.equals(outputPath)) {
						return true;
					}
					path = path.removeLastSegments(1);
				}
				return false;
			}

			private boolean isSourceFolder(IFolder folder) {
				return sourceContainers.contains(folder);
			}

			private void visitSourceFolder(IFolder folder) throws CoreException {
				folder.accept(getFolderResourceVisitor(folder.getFullPath(), computeEJBSourceFolder(folder)));
			}

			private IPath computeEJBSourceFolder(IFolder clientProjectFolder) {
				IPath clientFolderRelativePath = clientProjectFolder.getProjectRelativePath();
				return ejbProjectPath.append(clientFolderRelativePath);
			}

			private boolean isClassesFolder(IFolder folder) {
				return libraryContainers.contains(folder);
			}

			protected void visitClassesFolder(IFolder folder) throws CoreException {
				IPath classesPath = getEJBClassesPath();
				folder.accept(getFolderResourceVisitor(folder.getFullPath(), classesPath));
			}

			private IPath getEJBClassesPath() throws CoreException {
				IFolder folder = ejbProject.getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);
				if (!folder.exists())
					folder.create(true, true, null);
				JemProjectUtilities.appendJavaClassPath(ejbProject, JavaCore.newLibraryEntry(folder.getFullPath(), null, null, true));
				return folder.getFullPath();
			}
		};
	}

	private IResourceVisitor getFolderResourceVisitor(final IPath sourceRoot, final IPath destinationRoot) {
		return new IResourceVisitor() {
			public boolean visit(IResource resource) throws CoreException {
				if (moveResourceMonitor.isCanceled())
					return false;
				switch (resource.getType()) {
					case IResource.FILE :
						if (!resource.isDerived() && !isManifest(sourceRoot, resource.getFullPath()))
							moveFile((IFile) resource, sourceRoot, destinationRoot);
						return false;
					case IResource.FOLDER :
						return true;
					default :
						return false;
				}
			}

			private boolean isManifest(IPath new_sourceRoot, IPath path) {
				String relativeURI = path.removeFirstSegments(new_sourceRoot.segmentCount()).toString();
				return J2EEConstants.MANIFEST_URI.equals(relativeURI);
			}
		};
	}

	private void moveFile(IFile file, IPath sourceRoot, IPath destinationRoot) throws CoreException {
		IPath filePath = file.getFullPath();
		IPath relativeFilePath = filePath.removeFirstSegments(sourceRoot.segmentCount());
		IPath newPath = destinationRoot.append(relativeFilePath);
		mkdirs(newPath, workspace.getRoot());
		IFile existing = workspace.getRoot().getFile(newPath);
		if (!yesToAll && existing.exists()) {
			String message = MessageFormat.format(ClientJARCreationConstants.SHOULD_OVERWRITE, new String[]{filePath.toString(), newPath.toString()});
			int answer = IOperationHandler.YES_TO_ALL;
			if (operationHandler != null)
				answer = operationHandler.canContinueWithAllCheckAllowCancel(message);
			switch (answer) {
				case IOperationHandler.YES :
					break;
				case IOperationHandler.YES_TO_ALL :
					yesToAll = true;
					break;
				case IOperationHandler.NO :
					return;
				case IOperationHandler.CANCEL :
					moveResourceMonitor.setCanceled(true);
					return;
				default :
					break;
			}
		}
		if (!existing.exists())
			file.move(newPath, true, moveResourceMonitor);
		else
			existing.setContents(file.getContents(), true, true, moveResourceMonitor);
	}


	private void removeClientProjectFromEARs() {

		IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects( clientProject );
		for (int i = 0; i < earProjects.length; i++) {
			removeClientProjectFromEAR( earProjects[i] );
		}
	}


	private void removeClientProjectFromEAR( IProject earProject ) {
		
		IVirtualComponent earComp = ComponentCore.createComponent(earProject);
		IVirtualComponent component = ComponentCore.createComponent(clientProject);

		if (earComp.exists() && component.exists()) {
			IDataModel dm = DataModelFactory.createDataModel(new RemoveReferenceComponentsDataModelProvider());
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);

			List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			modList.add(component);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
			try {
				dm.getDefaultOperation().execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().log(e);
			}
		}
		
	}

	/*
	 * remove the client project from the workspace
	 */
	private void deleteClientProject() throws CoreException {
		if (shouldDelete)
			clientProject.delete(true, true, createSubProgressMonitor(1));
	}

	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}
	
	protected boolean verifyFilesInSync() throws CoreException {
		if (verifyFilesInSync(ejbProject))
			return verifyFilesInSync(clientProject);
		else
			return false;
	}
	
	protected boolean verifyFilesInSync(IProject project) throws CoreException {
		if (!project.exists())
			return true;
		if (!project.isSynchronized(IResource.DEPTH_INFINITE)) {
			String message = MessageFormat.format(ClientJARCreationConstants.FILES_OUT_OF_SYNC, new String[]{project.getName()});
			if (operationHandler == null || operationHandler.canContinue(message)) 
				ejbProject.refreshLocal(IResource.DEPTH_INFINITE, createSubProgressMonitor(1));
			else 
				return false;
		}
		return true;
	}	
	
	protected void mkdirs(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 2)
			return;
		IPath parentPath = newPath.removeLastSegments(1);
		IFolder folder = root.getFolder(parentPath);
		if (!folder.exists()) {
			mkdirs(parentPath, root);
			folder.create(true, true, null);
		}
	}	
}