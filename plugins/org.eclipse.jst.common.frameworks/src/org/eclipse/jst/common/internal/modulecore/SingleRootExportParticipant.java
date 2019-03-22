/*******************************************************************************
 * Copyright (c) 2009, 2015 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *     Roberto Sanchez Herrera - [371907] Do not add duplicate resources
 *     Roberto Sanchez Herrera - [463140] Add references as resources using relative path
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.SingleRootUtil.SingleRootCallback;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.ChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.FlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Single root optimization. 
 * @author rob
 */
public class SingleRootExportParticipant extends AbstractFlattenParticipant {
	private SingleRootParticipantCallback callbackHandler;
	private IVirtualComponent rootComponent;
	private FlatComponentTaskModel dataModel;
	private IFlattenParticipant[] delegates;
	private List<IChildModuleReference> children;
	private	IVirtualReference[] referencesToAddAsResources = null;
	
	public interface SingleRootParticipantCallback extends SingleRootCallback {
		public IFlattenParticipant[] getDelegateParticipants();
	}
	
	public SingleRootExportParticipant() {
		super();
		callbackHandler = null;
	}
	public SingleRootExportParticipant(SingleRootParticipantCallback handler) {
		this();
		callbackHandler = handler;
	}
	
	@Override
	public void initialize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.rootComponent = component;
		this.dataModel = dataModel;
	}


	private void initializeDelegates() {
		if (callbackHandler != null) {
			delegates = callbackHandler.getDelegateParticipants();
		}
		else {
			delegates = new IFlattenParticipant[] {};
		}
	}
	
	@Override
	public boolean canOptimize(IVirtualComponent component,
			FlatComponentTaskModel dataModel) {
		return new SingleRootUtil(component, callbackHandler).isSingleRoot();
	}

	@Override
	public void optimize(IVirtualComponent component, FlatComponentTaskModel dataModel, 
			List<IFlatResource> resources, List<IChildModuleReference> childModules) {
		try {
			resources.clear(); // We want complete control
			childModules.clear();
			children = Collections.synchronizedList(childModules);
			initializeDelegates();
			
			IContainer container = new SingleRootUtil(component, callbackHandler).getSingleRoot();
			IFlatResource[] mr = getMembers(resources, container, new Path("")); //$NON-NLS-1$
			int size = mr.length;
			for (int j = 0; j < size; j++) {
				resources.add(mr[j]);
			}
			addChildModules(component);
			// addChildModules might have added elements to referencesToAddAsResources while looking for 
			// child modules. Let's add these references
			addReferencesAsResources(resources, referencesToAddAsResources);
						
			// run finalizers
			for (int i = 0; i < delegates.length; i++) {
				delegates[i].finalize(component, dataModel, resources);
			}
		} catch( CoreException ce ) {
			// TODO 
		}
	}

	/*
	 * This function adds resources based on the list of references. 
	 */
	private void addReferencesAsResources(List<IFlatResource> resources,
			IVirtualReference[] references) {
		
		for (IVirtualReference reference:references){
			File f = reference.getReferencedComponent().getAdapter(File.class);
			IPath relativeReferenceRuntimePath = reference.getRuntimePath().makeRelative();
			FlatFile file = new FlatFile(f, reference.getArchiveName(), relativeReferenceRuntimePath);
			FlatResource existingRes = VirtualComponentFlattenUtility.getExistingModuleResource(resources, file.getModuleRelativePath().append(file.getName()));
			// If the resource already exist in the list of resources, do not add it
			if ( existingRes == null){
				// The resource is not in the list if resources, so add it.
				IFlatFolder folder = (IFlatFolder) VirtualComponentFlattenUtility
						.getExistingModuleResource(resources, relativeReferenceRuntimePath);
				if( folder == null ) {
					folder = VirtualComponentFlattenUtility.ensureParentExists(resources, relativeReferenceRuntimePath, null);
				}
				if( folder == null )
					resources.add(file);
				else
					VirtualComponentFlattenUtility.addMembersToModuleFolder(folder, new IFlatFile[]{file});	
			}
		}
	}
	
	protected IFlatResource[] getMembers(List<IFlatResource> members, 
			IContainer cont, IPath path) throws CoreException {
		IResource[] res = cont.members();
		int size2 = res.length;
		List list = new ArrayList(size2);
		for (int j = 0; j < size2; j++) {
			if (res[j] instanceof IContainer) {
				IContainer cc = (IContainer) res[j];
				// Retrieve already existing module folder if applicable
				IFlatFolder mf = (FlatFolder) VirtualComponentFlattenUtility.getExistingModuleResource(members,path.append(new Path(cc.getName()).makeRelative()));
				if (mf == null) {
					mf = new FlatFolder(cc, cc.getName(), path);
					IFlatFolder parent = (FlatFolder) VirtualComponentFlattenUtility.getExistingModuleResource(members, path);
					if (path.isEmpty() || path.equals(new Path("/"))) //$NON-NLS-1$
						members.add(mf);
					else {
						if (parent == null)
							parent = VirtualComponentFlattenUtility.ensureParentExists(members, path, cc);
						VirtualComponentFlattenUtility.addMembersToModuleFolder(parent, new IFlatResource[] {mf});
					}
				}
				IFlatResource[] mr = getMembers(members, cc, path.append(cc.getName()));
				VirtualComponentFlattenUtility.addMembersToModuleFolder(mf, mr);
			} else {
				IFile f = (IFile) res[j];
				IFlatFile mf = VirtualComponentFlattenUtility.createModuleFile(f, path);
				if (shouldAddComponentFile(rootComponent, mf))
					list.add(mf);
			}
		}
		FlatResource[] mr = new FlatResource[list.size()];
		list.toArray(mr);
		return mr;
	}
	
	protected void addChildModules(IVirtualComponent vc) throws CoreException {
		ArrayList<IVirtualReference> refAsResource = new ArrayList<IVirtualReference>();


		Map<String, Object> options = new HashMap<String, Object>();
		options.put(IVirtualComponent.REQUESTED_REFERENCE_TYPE, IVirtualComponent.FLATTENABLE_REFERENCES);
		IVirtualReference[] allReferences = vc.getReferences(options);
    	for (int i = 0; i < allReferences.length; i++) {
    		IVirtualReference reference = allReferences[i];
			if (reference.getDependencyType() == DependencyType.USES ) {
				if (shouldIgnoreReference(vc, reference))
					continue;
				
				if (isChildModule(vc, reference)) {
					ChildModuleReference cm = new ChildModuleReference(reference, new Path("")); //$NON-NLS-1$
					List<IChildModuleReference> duplicates = new ArrayList();
					synchronized(children){
						for (IChildModuleReference tmp : children) {
							if (tmp.getRelativeURI().equals(cm.getRelativeURI()))
								duplicates.add(tmp);
						}
						children.removeAll(duplicates);
						children.add(cm);
					}
				} else {
					// It's not a child module, but it is a reference that needs to be added in anyway
					refAsResource.add(reference);
				}
			}
    	}
    	referencesToAddAsResources = refAsResource.toArray(new IVirtualReference[refAsResource.size()]);
	}
	
	protected boolean isChildModule(IVirtualComponent component, IVirtualReference referencedComponent) {
		for (int i = 0; i < delegates.length; i++) {
			if (delegates[i].isChildModule(component, referencedComponent, dataModel))
				return true;
		}
		return false;
	}

	protected boolean shouldIgnoreReference(IVirtualComponent component, IVirtualReference referencedComponent) {
		for (int i = 0; i < delegates.length; i++ ) {
			if (delegates[i].shouldIgnoreReference(component, referencedComponent, dataModel))
				return true;
		}
		return false;
	}
	

	public boolean shouldAddComponentFile(IVirtualComponent component, IFlatFile file) {
		for (int i = 0; i < delegates.length; i++) {
			if (delegates[i].isChildModule(component, dataModel, file)) {
				ChildModuleReference child = new ChildModuleReference(component.getProject(), file);
				children.add(child); 
				return false;
			} else if (!delegates[i].shouldAddExportableFile(component, component, dataModel, file))
				return false;
		}
		return true;
	}
	

}
