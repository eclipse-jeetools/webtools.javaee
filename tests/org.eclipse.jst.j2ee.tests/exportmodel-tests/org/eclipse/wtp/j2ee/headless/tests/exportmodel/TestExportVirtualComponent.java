package org.eclipse.wtp.j2ee.headless.tests.exportmodel;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFile;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class TestExportVirtualComponent extends VirtualComponent {
	private IFile file;
	public TestExportVirtualComponent(IFile file) {
		super(file.getProject(), new Path(""));
		this.file = file;
	}

	public boolean exists() { 
		return true;
	}

	public IPath getFullPath() {
		return file.getFullPath();
	}
	public IVirtualFolder getRootFolder() {
		return new TestExportVirtualFolder(file.getProject());
	}
	
	public class TestExportVirtualFolder extends VirtualFolder {
		public TestExportVirtualFolder(IProject aComponentProject) {
			super(aComponentProject, new Path("/"));
		}
		
		public IVirtualResource[] members(int memberFlags) throws CoreException {
			return new IVirtualResource[] { 
					new VirtualFile(file.getProject(), new Path("inside"), file)
			};
		}

	}

}
