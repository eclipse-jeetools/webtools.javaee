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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.common.jdt.internal.javalite.IJavaProjectLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AddMappedOutputFoldersParticipant extends AbstractFlattenParticipant {
	private List<IFlatResource> list;

	@Override
	public void finalize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.list = resources;
		list.getClass();
		if( !isApprovedComponent(component))
			return;

		HashMap<IContainer, IPath> mapped = getMappedJavaOutputContainers(component.getProject());
		Iterator<IContainer> i = mapped.keySet().iterator();
		while(i.hasNext()) {
			IContainer next = i.next();
			try {
				new VirtualComponentFlattenUtility(list, null).addContainer(next, mapped.get(next));
			} catch( CoreException ce) {}
		}
	}
	
	protected boolean isApprovedComponent(IVirtualComponent vc) {
		// TODO
		return true;
	}

	public final static HashMap<IContainer, IPath> getMappedJavaOutputContainers(IProject project) {
		ComponentResourceProxy[] proxies = findAllMappingProxies(project);
		IJavaProjectLite javaProjectLite = JavaCoreLite.create(project);

		HashMap<IContainer, IPath> map = new HashMap<IContainer, IPath>();
		IClasspathEntry[] entries = javaProjectLite.readRawClasspath();
		for (IClasspathEntry entry : entries) {
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IPath cpePath = entry.getPath();
				for( int i = 0; i < proxies.length; i++ ) {
					if( cpePath.equals(new Path(project.getName()).append(proxies[i].source).makeAbsolute())) {
						IContainer outputContainer = JavaLiteUtilities.getJavaOutputContainer(javaProjectLite, entry);
						if (!map.containsKey(outputContainer)) {
							map.put(outputContainer, proxies[i].runtimePath);
						}
					}
					// TODO 
				}
			}
		}
		return map;
	}

	/* 
	 * This code below is also duplicated in common.ui in the generic page
	 * to handle module assembly 
	 */
	
	public static ComponentResourceProxy[] findAllMappingProxies(IProject project) {
		ComponentResource[] allMappings = findAllMappings(project);
		ComponentResourceProxy[] proxies = new ComponentResourceProxy[allMappings.length];
		for( int i = 0; i < allMappings.length; i++ ) {
			proxies[i] = new ComponentResourceProxy(
					allMappings[i].getSourcePath(), 
					allMappings[i].getRuntimePath());
		}
		return proxies;
	}
	
	protected static ComponentResource[] findAllMappings(IProject project) {
		StructureEdit structureEdit = null;
		try {
			structureEdit = StructureEdit.getStructureEditForRead(project);
			WorkbenchComponent component = structureEdit.getComponent();
			Object[] arr = component.getResources().toArray();
			ComponentResource[] result = new ComponentResource[arr.length];
			for( int i = 0; i < arr.length; i++ )
				result[i] = (ComponentResource)arr[i];
			return result;
		} catch(Exception e) {
		} finally {
			if( structureEdit != null )
				structureEdit.dispose();
		}
		return new ComponentResource[]{};
	}
	
	public static class ComponentResourceProxy {
		public IPath source, runtimePath;
		public ComponentResourceProxy(IPath source, IPath runtimePath) {
			this.source = source;
			this.runtimePath = runtimePath;
		}
	}
}