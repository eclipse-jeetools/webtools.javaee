package org.eclipse.jst.j2ee.componentcore.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class EARVirtualRootFolder extends VirtualFolder {

	public EARVirtualRootFolder(IProject aComponentProject, IPath aRuntimePath) {
		super(aComponentProject, aRuntimePath);
	}

	/**
	 * For now, just rip out files with .jar, .rar, or .war file extensions, because these are
	 * the only files automatically added dyamically
	 */
	public IVirtualResource[] members(int memberFlags) throws CoreException {
		IVirtualResource[] members = super.members(memberFlags);
		List virtualResources = new ArrayList();
		boolean shouldAdd = true;
		for (int i = 0; i < members.length; i++) {
			shouldAdd = true;
			if (IVirtualResource.FILE == members[i].getType()) {
				String archiveName = members[i].getName();
				String lowerCase = archiveName.toLowerCase();
				if (lowerCase.endsWith(".jar") || lowerCase.endsWith(".rar") || lowerCase.endsWith(".war")) {
					shouldAdd = false;
				}
			}
			if (shouldAdd) {
				virtualResources.add(members[i]);
			}
		}
		return (IVirtualResource[]) virtualResources
				.toArray(new IVirtualResource[virtualResources.size()]);
	}
}
