package org.eclipse.jst.j2ee.componentcore;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.componentcore.UtilityBinaryComponentHelper;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEModuleVirtualArchiveComponent extends VirtualArchiveComponent {

	private static final IVirtualReference[] NO_REFERENCES = new VirtualReference[0];
	protected String [] manifestClasspath;
	
	public J2EEModuleVirtualArchiveComponent(IProject aComponentProject,String archiveLocation, IPath aRuntimePath) {
		super(aComponentProject, archiveLocation, aRuntimePath);
	}
	
	public IVirtualReference[] getReferences() {
		List dynamicReferences = J2EEModuleVirtualComponent.getManifestReferences(this, null);
		if(null == dynamicReferences){
			return NO_REFERENCES;
		}
		return (IVirtualReference[]) dynamicReferences.toArray(new IVirtualReference[dynamicReferences.size()]);
	}
	
	public String [] getManifestClasspath() {
		if(null == manifestClasspath){
			UtilityBinaryComponentHelper helper = null;
			try{
				helper = new UtilityBinaryComponentHelper(this);
				ArchiveManifest manifest = helper.getArchive().getManifest();
				manifestClasspath = manifest.getClassPathTokenized();
			} catch (Exception e){
			} finally {
				helper.dispose();
			}
			if(manifestClasspath == null){
				manifestClasspath = new String [0];
			}
		}
		return manifestClasspath;
	}
	
}
