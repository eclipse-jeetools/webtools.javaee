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
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.earcreation.AddUtilityJARMapCommand;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.RemoveUtilityJARMapCommand;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

/**
 * @author schacher
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBClientJARRemovalOperation extends AbstractEJBClientJAROperation {

	protected IPath clientProjectPath, ejbProjectPath;

	protected List sourceContainers, libraryContainers;

	protected IContainer ejbSourceContainer;

	protected IPath ejbSourcePath;

	protected IPath outputPath;

	protected boolean shouldDelete;

	protected boolean yesToAll = false;

	public EJBClientJARRemovalOperation(IDataModel dataModel, IOperationHandler opHandler) {
		super(dataModel, opHandler);
		shouldDelete = true;
        //TODO allow user to set if old project should be deleted.....a new data model provider may be needed.
        //dataModel.getBooleanProperty(IEJBClientComponentCreationDataModelProperties.DELETE_WHEN_FINISHED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.clientjarcreation.AbstractEJBClientJAROperation#initialize()
	 */
	protected void initialize() {
//		super.initialize();
//		clientProject = ejbNature.getDefinedEJBClientJARProject();
//		clientProjectPath = clientProject.getFullPath();
//		ejbProjectPath = ejbProject.getFullPath();
//		sourceContainers = JemProjectUtilities.getSourceContainers(clientProject);
//		libraryContainers = JemProjectUtilities.getLibraryContainers(clientProject);
//		IJavaProject proj = JavaCore.create(clientProject);
//		try {
//			outputPath = proj.getOutputLocation().removeFirstSegments(1);
//		} catch (JavaModelException e) {
//			//Ignore
//		}
//
//		ejbSourceContainer = ejbNature.getSourceFolder();
//		ejbSourcePath = ejbSourceContainer.getFullPath();
//		initializeEARNatures();
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

	protected void initializeEARNatures() {
//		Set natures = new HashSet();
//		natures.addAll(Arrays.asList(ejbNature.getReferencingEARProjects()));
//		natures.addAll(Arrays.asList(J2EEProjectUtilities.getReferencingEARProjects(clientProject)));
//		earNatures = (EARNatureRuntime[]) natures.toArray(new EARNatureRuntime[natures.size()]);
	}

	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		monitor = aMonitor;
		initialize();
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
		copyOutgoingClasspathEntries(clientProject, ejbProject, false);
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
//		((EJBEditModel) editModel).getEJBJar().setEjbClientJar(null);
	}

	/*
	 * In cross EAR references, it is possible the EJB module is not included in the referencing
	 * EAR. If so, then we must add the EJB module as a utility JAR to the referencing EAR.
	 */
	private void ensureEJBJARInAllEARs() {
		for (int i = 0; i < earNatures.length; i++) {
			ensureEJBJARInEAR(earNatures[i]);
		}
	}

	private void ensureEJBJARInEAR(EARNatureRuntime runtime) {
		String ejbURI = runtime.getJARUri(ejbProject);
		if (ejbURI != null)
			return;
		ejbURI = J2EEProjectUtilities.getUtilityJARUriInFirstEAR(ejbProject);
		EAREditModel model = null;
		try {
			model = runtime.getEarEditModelForWrite(this);
			AddUtilityJARMapCommand cmd = new AddUtilityJARMapCommand(model, ejbURI, ejbProject);
			model.getCommandStack().execute(cmd);
			IProgressMonitor subMonitor = createSubProgressMonitor(1);
			model.saveIfNecessary(subMonitor, this);
		} finally {
			if (model != null)
				model.releaseAccess(this);
		}
	}

	/*
	 * copy all JAR dependencies not already contained by the ejb module, from the client JAR to the
	 * ejb module, and remove the module dependency from the EJB module
	 */
	private void updateEJBModuleJARDependencies() throws InvocationTargetException, InterruptedException {
		JARDependencyDataModel dataModel = new JARDependencyDataModel();
		dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbProject.getName());
		ArrayList list = new ArrayList();
		list.add(clientProject.getName() + ".jar"); //$NON-NLS-1$
		dataModel.setProperty(JARDependencyDataModel.JAR_LIST, list);
		dataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[0].getProject().getName());
		dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_REMOVE);
		dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientProject.getName());
		JARDependencyOperation op = new JARDependencyOperation(dataModel);
		op.run(createSubProgressMonitor(1));
		ArchiveManifest clientMf = J2EEProjectUtilities.readManifest(clientProject);
		if (clientMf == null)
			return;
		String[] mfEntries = clientMf.getClassPathTokenized();
		if (mfEntries.length == 0)
			return;
		createSubProgressMonitor(earNatures.length);
		JARDependencyDataModel dataModel2 = null;
		for (int i = 0; i < earNatures.length; i++) {
			normalize(mfEntries, earNatures[i], false);
			dataModel2 = new JARDependencyDataModel();
			dataModel2.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbProject.getName());
			dataModel.setProperty(JARDependencyDataModel.JAR_LIST, list);
			dataModel2.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[i].getProject().getName());
			dataModel2.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
			JARDependencyOperation op2 = new JARDependencyOperation(dataModel2);
			op2.run(createSubProgressMonitor(1));
		}
	}

	/*
	 * For each module or JAR in each EAR that references the client JAR, replace the dependency
	 * from the client JAR to the EJB module
	 */
	private void moveIncomingJARDependencies() throws InvocationTargetException, InterruptedException {
		InvertClientJARDependencyCompoundOperation op = new InvertClientJARDependencyCompoundOperation(earNatures, clientProject, ejbProject);
		op.run(createSubProgressMonitor(1));
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
				return ProjectUtilities.DOT_CLASSPATH.equals(segment) || ProjectUtilities.DOT_PROJECT.equals(segment) || ".runtime".equals(segment); //$NON-NLS-1$ 
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
			String message = format(ClientJARCreationConstants.SHOULD_OVERWRITE, new String[]{filePath.toString(), newPath.toString()});
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

	/*
	 * Iterate all the EARs, and remove the client JARs. If the EAR does not reference the EJB
	 * module, then add the EJB JAR as a utility JAR
	 */
	private void removeClientProjectFromEARs() {
		for (int i = 0; i < earNatures.length; i++) {
			removeClientProjectFromEAR(earNatures[i]);
		}
	}

	private void removeClientProjectFromEAR(EARNatureRuntime runtime) {
		EAREditModel model = null;
		String clientURI = runtime.getJARUri(clientProject);
		if (clientURI == null)
			return;
		try {
			model = runtime.getEarEditModelForWrite(this);
			RemoveUtilityJARMapCommand cmd = new RemoveUtilityJARMapCommand(model, clientURI, clientProject);
			model.getCommandStack().execute(cmd);
			IProgressMonitor subMonitor = createSubProgressMonitor(1);
			model.saveIfNecessary(subMonitor, this);
		} finally {
			if (model != null)
				model.releaseAccess(this);
		}
	}

	/*
	 * remove the client project from the workspace
	 */
	private void deleteClientProject() throws CoreException {
		if (shouldDelete)
			clientProject.delete(true, true, createSubProgressMonitor(1));
	}
}