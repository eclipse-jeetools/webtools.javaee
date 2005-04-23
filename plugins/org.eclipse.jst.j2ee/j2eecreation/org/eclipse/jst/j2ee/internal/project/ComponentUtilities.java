package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
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

}
