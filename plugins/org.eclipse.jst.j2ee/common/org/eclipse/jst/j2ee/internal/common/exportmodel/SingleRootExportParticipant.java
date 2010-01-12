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
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.project.SingleRootUtil;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.FlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Single root optimization. 
 * @author rob
 */
public class SingleRootExportParticipant extends AbstractFlattenParticipant {
	private IVirtualComponent component;
	private FlatComponentTaskModel dataModel;
	private ReplaceManifestExportParticipant manifestReplacementDelegate;
	
	@Override
	public void initialize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.component = component;
		this.dataModel = dataModel;
	}


	@Override
	public boolean canOptimize(IVirtualComponent component,
			FlatComponentTaskModel dataModel) {
		return new SingleRootUtil(component).isSingleRoot() && !hasClasspathDependencies(component);
	}
	
	protected boolean hasClasspathDependencies(IVirtualComponent component) {
		try {
			final Map entriesToAttrib = ClasspathDependencyUtil.getRawComponentClasspathDependencies(
					JavaCoreLite.create(component.getProject()), 
					DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY);
			return entriesToAttrib != null && entriesToAttrib.size() > 0;
		} catch( CoreException ce ) {}
		return false;
	}

	@Override
	public void optimize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		manifestReplacementDelegate = new ReplaceManifestExportParticipant();
		
		try {
			resources.clear(); // We want complete control
			IContainer container = new SingleRootUtil(component).getSingleRoot();
			IFlatResource[] mr = getMembers(resources, container, new Path("")); //$NON-NLS-1$
			int size = mr.length;
			for (int j = 0; j < size; j++) {
				resources.add(mr[j]);
			}
			manifestReplacementDelegate.forceUpdate(component, dataModel, resources);
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
				FlatFile mf = VirtualComponentFlattenUtility.createModuleFile(f, path);
				if (shouldAddComponentFile(mf)) {
					list.add(mf);
				}
			}
		}
		FlatResource[] mr = new FlatResource[list.size()];
		list.toArray(mr);
		return mr;
	}
	
	// checking solely for manifest file
	protected boolean shouldAddComponentFile(FlatFile file) {
		return manifestReplacementDelegate.shouldAddExportableFile(component, component, dataModel, file);
	}
	
}
