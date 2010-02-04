/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.SingleRootUtil.SingleRootCallback;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.FlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Single root optimization. 
 * @author rob
 */
public class SingleRootExportParticipant extends AbstractFlattenParticipant {
	private SingleRootParticipantCallback callbackHandler;
	private IVirtualComponent rootComponent;
	private FlatComponentTaskModel dataModel;
	
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


	@Override
	public boolean canOptimize(IVirtualComponent component,
			FlatComponentTaskModel dataModel) {
		return new SingleRootUtil(component, callbackHandler).isSingleRoot();
	}

	@Override
	public void optimize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		try {
			resources.clear(); // We want complete control
			IContainer container = new SingleRootUtil(component).getSingleRoot();
			IFlatResource[] mr = getMembers(resources, container, new Path("")); //$NON-NLS-1$
			int size = mr.length;
			for (int j = 0; j < size; j++) {
				resources.add(mr[j]);
			}
			
			// run finalizers
			if( callbackHandler != null ) {
				IFlattenParticipant[] delegates = callbackHandler.getDelegateParticipants();
				for(int i = 0; i < delegates.length; i++ ) {
					delegates[i].finalize(component, dataModel, resources);
				}
			}
		} catch( CoreException ce ) {
			// TODO 
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
				if( shouldAddExportableFile(rootComponent, rootComponent, dataModel, mf))
					list.add(mf);
			}
		}
		FlatResource[] mr = new FlatResource[list.size()];
		list.toArray(mr);
		return mr;
	}
	
	@Override
	public boolean shouldAddExportableFile(IVirtualComponent rootComponent,
			IVirtualComponent currentComponent, FlatComponentTaskModel dataModel,
			IFlatFile file) {
		if( callbackHandler != null ) {
			IFlattenParticipant[] delegates = callbackHandler.getDelegateParticipants();
			for(int i = 0; i < delegates.length; i++ ) {
				if( !delegates[i].shouldAddExportableFile(rootComponent, currentComponent, dataModel, file))
					return false;
			}
		}
		return true;
	}

}
