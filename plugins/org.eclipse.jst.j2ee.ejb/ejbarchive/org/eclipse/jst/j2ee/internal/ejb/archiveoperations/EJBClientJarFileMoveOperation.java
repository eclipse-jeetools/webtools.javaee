/*
 * Created on May 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;



/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBClientJarFileMoveOperation extends AbstractDataModelOperation
  implements IEJBClientJarFileMoveDataModelProperties{
	protected IProject clientProject;
	protected IProject ejbProject;
	protected IWorkspace workspace;
	protected IProgressMonitor monitor;
	protected IProgressMonitor moveResourceMonitor;
	protected Map javaFilesToMove = new HashMap();
	



	public EJBClientJarFileMoveOperation(IDataModel model){
		super(model); 
		initialize();
	}
	
	public IStatus execute(IProgressMonitor aMonitor, IAdaptable info) throws ExecutionException {
        monitor = aMonitor;
        return moveRequiredFiles();
	}
	

	protected void initialize() {
		ejbProject = getEJBProject(model.getStringProperty(EJB_PROJECT_NAME));
		if(ejbProject == null) return;
        workspace = ejbProject.getWorkspace();
		clientProject = workspace.getRoot().getProject( model.getStringProperty( EJB_CLIENTVIEW_PROJECT_NAME));
		javaFilesToMove = (Map)model.getProperty( FILES_TO_MOVE_MAP );
	}
	
	private IStatus moveRequiredFiles() {
		
		IStatus status = OK_STATUS;
		try{
	        moveResourceMonitor = createSubProgressMonitor(1);
	        Iterator iter = javaFilesToMove.entrySet().iterator();
	        IPackageFragmentRoot packageFragmentRoot;
	        Set types;
	        while (iter.hasNext()) {
	            Map.Entry entry = (Entry) iter.next();
	            packageFragmentRoot = (IPackageFragmentRoot) entry.getKey();
	            types = (Set) entry.getValue();
	            try {
	                if (packageFragmentRoot.getKind() == IPackageFragmentRoot.K_SOURCE)
	                    moveFiles(packageFragmentRoot, types);
	                else
	                    moveClassFiles(packageFragmentRoot, types);
	            } catch (JavaModelException e) {
	                Logger.getLogger().logError(e);
	            }
	        }
	        IJavaProject javaProject = JemProjectUtilities.getJavaProject(clientProject);
	        //Refresh problems
	        javaProject.close();
	        javaProject.open(null);
		}catch(CoreException e){
			Logger.getLogger().logError( e );
		}
		return status;
	}

	
	private void moveFiles(IPackageFragmentRoot packageFragmentRoot, Set types) throws CoreException {
		IFile file = null;
		int leadSegmentsToTruncate = -1;
		IFolder sourceSourceFolder = getSourceFolder(packageFragmentRoot);
		if(sourceSourceFolder == null) return;
		IFolder targetSourceFolder = getOrCreateTargetSourceFolder(sourceSourceFolder);
		if(targetSourceFolder == null) return;
		IPath sourceFolderPath = targetSourceFolder.getFullPath();
		try {
			leadSegmentsToTruncate = packageFragmentRoot.getUnderlyingResource().getFullPath().segmentCount();
		} catch (JavaModelException ex) {
			Logger.getLogger().logError(ex);
			return;
		}
		for (Iterator iter = types.iterator(); iter.hasNext(); ) {
			file = (IFile) iter.next();
			IPath relPath = file.getFullPath().removeFirstSegments(leadSegmentsToTruncate);
			IPath newPath = sourceFolderPath.append(relPath);
			mkdirs(newPath, workspace.getRoot());
			IFile existing = workspace.getRoot().getFile(newPath);
			if (!existing.exists())
				file.move(newPath, true, moveResourceMonitor);
		}
	}
	
	/**
	 * @param sourceSourceFolder
	 * @return
	 */
	private IFolder getOrCreateTargetSourceFolder(IFolder sourceSourceFolder) throws CoreException {
		IFolder targetFolder = clientProject.getFolder(sourceSourceFolder.getProjectRelativePath());
		if(targetFolder == null || !targetFolder.exists()) {
			mkFolders(targetFolder.getFullPath(), workspace.getRoot());
			JemProjectUtilities.appendJavaClassPath(clientProject, JavaCore.newSourceEntry(targetFolder.getFullPath()));
		}
		return targetFolder;
	}
	
	protected void mkFolders(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 1)
			return;
		IFolder folder = root.getFolder(newPath);
		IPath parentPath = newPath.removeLastSegments(1);
		if (!folder.exists()) {
			mkFolders(parentPath, root);
			folder.create(true, true, null);
		}
	}
	/**
	 * @param packageFragmentRoot
	 * @return
	 */
	private IFolder getSourceFolder(IPackageFragmentRoot packageFragmentRoot) {
		IResource res = null;
		try {
			res = packageFragmentRoot.getUnderlyingResource();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		if(res != null && res instanceof IFolder)
			return (IFolder)res;
		return null;
	}
	
	
	private void moveClassFiles(IPackageFragmentRoot packageFragmentRoot, Set types) throws CoreException {
		IFolder folder = clientProject.getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);
		if (!folder.exists())
			folder.create(true, true, null);
		try {
			JemProjectUtilities.appendJavaClassPath(clientProject, JavaCore.newLibraryEntry(folder.getFullPath(), null, null, true));
		} catch (CoreException ex) {
			Logger.getLogger().logError(ex);
		}
		moveFiles(packageFragmentRoot, types);
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

	protected IProject getEJBProject(String aProjectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}
	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}
	
}
