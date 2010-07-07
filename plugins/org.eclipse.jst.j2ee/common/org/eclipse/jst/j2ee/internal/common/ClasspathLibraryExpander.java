package org.eclipse.jst.j2ee.internal.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class ClasspathLibraryExpander extends FlatVirtualComponent {

	private IVirtualReference consumedRef;

	public ClasspathLibraryExpander(IVirtualComponent component) {
		super(component);
	}
	
	public ClasspathLibraryExpander(IVirtualComponent component, IVirtualReference reference) {
		super(component);
		consumedRef = reference;
	}
	
	@Override
	protected boolean canOptimize() {
		return true;
	}

	@Override
	protected void optimize(List<IFlatResource> resources, List<IChildModuleReference> children) {
		if (getComponent() != null) {
			VirtualComponentFlattenUtility util = new VirtualComponentFlattenUtility(resources, this);
			try {
				//if a consumable reference is specified we should consume the reference
				if (consumedRef != null) {
					consumeComponent(util, new Path(""), consumedRef); //$NON-NLS-1$
				}
				//else consume all of the component's consumable reference
				else {
					addConsumedReferences(util, getComponent(), new Path("")); 	//$NON-NLS-1$
				}
			} catch (CoreException e) {
				org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
			}
		}
	}
	
	public List <IFlatFile> fetchFlatFiles() throws CoreException {
		IFlatResource [] resources = fetchResources();
		List<IFlatFile> flatFiles = new ArrayList<IFlatFile>();
		return fetchFlatFiles(resources, flatFiles);
	}
	
	private List <IFlatFile> fetchFlatFiles(IFlatResource[] resources, List<IFlatFile> flatFiles) {
		for (int i = 0; i < resources.length; i++) {
			IFlatResource resource = resources[i];
			if (resource instanceof IFlatFile) {
				flatFiles.add((IFlatFile)resource);
			} else if (resource instanceof IFlatFolder) {
				fetchFlatFiles(((IFlatFolder)resource).members(), flatFiles);
			}
		}
		return flatFiles;
	}

	public IFlatResource fetchResource(IPath runtimePath) throws CoreException {
		IFlatResource [] resources = fetchResources();
		return fetchResource(runtimePath.makeRelative(), resources);
	}

	private IFlatResource fetchResource(IPath runtimePath, IFlatResource[] resources) {
		for(IFlatResource resource : resources){
			IPath fullResourcePath = resource.getModuleRelativePath().append(resource.getName());
			if(fullResourcePath.equals(runtimePath)){
				return resource;
			} 
			else if(fullResourcePath.isPrefixOf(runtimePath)){
				if(resource instanceof IFlatFolder){
					IFlatFolder folder = (IFlatFolder)resource;
					return fetchResource(runtimePath, folder.members());
				}
			}
		}
		return null;
	}
}