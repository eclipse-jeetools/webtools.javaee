package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class ComponentUtilities {

	public static List getSourceContainers(IVirtualComponent wc, IProject project) {
		List sourceFolders = new ArrayList();
		IFolder folder = project.getFolder(new Path("B/ejbModule"));
		sourceFolders.add(folder);

		// try {
		// IVirtualComponent component = ComponentCore.createComponent(project,wc.getName());
		// IVirtualResource[] resources = component.members();
		// for(int i = 0; i < resources.length; i++) {
		// IVirtualResource resource = (IVirtualResource)resources[i];
		// if(resource.getType() == IVirtualResource.FOLDER)
		// sourceFolders.add(resource.getUnderlyingResource());
		// }
		// } catch (CoreException ce) {
		// Logger.getLogger().log(ce);
		// }
		return sourceFolders;
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
