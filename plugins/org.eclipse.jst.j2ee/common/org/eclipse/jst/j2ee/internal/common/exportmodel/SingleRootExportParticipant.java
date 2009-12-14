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
import org.eclipse.wst.common.componentcore.export.AbstractExportParticipant;
import org.eclipse.wst.common.componentcore.export.ExportModelUtil;
import org.eclipse.wst.common.componentcore.export.ExportableFile;
import org.eclipse.wst.common.componentcore.export.ExportableFolder;
import org.eclipse.wst.common.componentcore.export.ExportableResource;
import org.eclipse.wst.common.componentcore.export.IExportableFolder;
import org.eclipse.wst.common.componentcore.export.IExportableResource;
import org.eclipse.wst.common.componentcore.export.ExportModel.ExportTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Single root optimization. 
 * @author rob
 */
public class SingleRootExportParticipant extends AbstractExportParticipant {
	private IVirtualComponent component;
	private ExportTaskModel dataModel;
	private ReplaceManifestExportParticipant manifestReplacementDelegate;
	
	@Override
	public void initialize(IVirtualComponent component,
			ExportTaskModel dataModel, List<IExportableResource> resources) {
		this.component = component;
		this.dataModel = dataModel;
	}


	@Override
	public boolean canOptimize(IVirtualComponent component,
			ExportTaskModel dataModel) {
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
			ExportTaskModel dataModel, List<IExportableResource> resources) {
		manifestReplacementDelegate = new ReplaceManifestExportParticipant();
		
		try {
			resources.clear(); // We want complete control
			IContainer container = new SingleRootUtil(component).getSingleRoot();
			IExportableResource[] mr = getMembers(resources, container, new Path("")); //$NON-NLS-1$
			int size = mr.length;
			for (int j = 0; j < size; j++) {
				resources.add(mr[j]);
			}
			manifestReplacementDelegate.forceUpdate(component, dataModel, resources);
		} catch( CoreException ce ) {
			// TODO 
		}
	}

	protected IExportableResource[] getMembers(List<IExportableResource> members, 
			IContainer cont, IPath path) throws CoreException {
		IResource[] res = cont.members();
		int size2 = res.length;
		List list = new ArrayList(size2);
		for (int j = 0; j < size2; j++) {
			if (res[j] instanceof IContainer) {
				IContainer cc = (IContainer) res[j];
				// Retrieve already existing module folder if applicable
				IExportableFolder mf = (ExportableFolder) ExportModelUtil.getExistingModuleResource(members,path.append(new Path(cc.getName()).makeRelative()));
				if (mf == null) {
					mf = new ExportableFolder(cc, cc.getName(), path);
					IExportableFolder parent = (ExportableFolder) ExportModelUtil.getExistingModuleResource(members, path);
					if (path.isEmpty() || path.equals(new Path("/"))) //$NON-NLS-1$
						members.add(mf);
					else {
						if (parent == null)
							parent = ExportModelUtil.ensureParentExists(members, path, cc);
						ExportModelUtil.addMembersToModuleFolder(parent, new IExportableResource[] {mf});
					}
				}
				IExportableResource[] mr = getMembers(members, cc, path.append(cc.getName()));
				ExportModelUtil.addMembersToModuleFolder(mf, mr);
			} else {
				IFile f = (IFile) res[j];
				ExportableFile mf = ExportModelUtil.createModuleFile(f, path);
				if (shouldAddComponentFile(mf)) {
					list.add(mf);
				}
			}
		}
		ExportableResource[] mr = new ExportableResource[list.size()];
		list.toArray(mr);
		return mr;
	}
	
	// checking solely for manifest file
	protected boolean shouldAddComponentFile(ExportableFile file) {
		return manifestReplacementDelegate.shouldAddExportableFile(component, component, dataModel, file);
	}
	
}
