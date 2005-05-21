package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class ComponentUtilities {

	public static IPackageFragmentRoot[] getSourceContainers(IVirtualComponent wc) {
		 List sourceFolders = new ArrayList();
		 try {
		 IVirtualResource[] resources = wc.members();
		 if(resources != null || resources.length > 0) {
			 for(int i = 0; i < resources.length; i++) {
				 if(resources[i].getType() == IVirtualResource.FOLDER) {
				 IFolder folder = ((IVirtualFolder)resources[i]).getUnderlyingFolder();
				 IJavaElement element = JavaCore.create(folder);
				 if(element != null && element.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT)
				    sourceFolders.add(element);
				 }
			 }
		   }
		 } catch (CoreException ce) {
		 Logger.getLogger().log(ce);
		 }
		 return (IPackageFragmentRoot[]) sourceFolders.toArray(new IPackageFragmentRoot[sourceFolders.size()]);
	}
	
	
	

	/**
	 * Ensure the container is not read-only.
	 * <p>
	 * For Linux, a Resource cannot be created in a ReadOnly folder. This is only necessary for new files.
	 * 
	 * @param resource
	 *            workspace resource to make read/write
	 * @since 1.0.0
	 */
	public static void ensureContainerNotReadOnly(IResource resource) {
		if (resource != null && !resource.exists()) { //it must be new
			IContainer container = resource.getParent();
			while (container != null && !container.isReadOnly())
				container = container.getParent();
			if (container != null)
				container.setReadOnly(false);
		}
	}

	public static IFolder createFolderInComponent(IVirtualComponent component, String folderName) throws CoreException {
		if (folderName != null) {
			IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(component.getProjectRelativePath().append(folderName));
			if (!folder.exists()) {
				ProjectUtilities.ensureContainerNotReadOnly(folder);
				folder.create(true, true, null);
			}
			return folder;
		}
		return null;
	}

}
