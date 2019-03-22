/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Add classpath folder references. This is necessary because 
 * these classpath folder references are not exactly stored as 
 * IVirtualReference objects persisted in the component.xml, but rather 
 * pulled in from another model. 
 * 
 * @author rob.stryker@jboss.com
 *
 */
public class AddClasspathFoldersParticipant extends AbstractFlattenParticipant {
	private List<IFlatResource> list;
	
	@Override
	public boolean shouldIgnoreReference(IVirtualComponent rootComponent,
			IVirtualReference referenced, FlatComponentTaskModel dataModel) {
		if( (rootComponent instanceof IClasspathDependencyProvider) && 
			(referenced.getReferencedComponent() instanceof IClasspathDependencyComponent) &&
			((IClasspathDependencyComponent)referenced.getReferencedComponent()).isClassFolder())
			return true;
		return false;
	}

	
	@Override
	public void finalize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.list = resources;
		if( !(component instanceof IClasspathDependencyProvider ))
			return;
		// Add all Java output folders that have publish/export attributes
		try {
			addClassFolderDependencies((IClasspathDependencyProvider)component);
		} catch( CoreException ce ) {
			// Ignore
		}
	}

	private void addClassFolderDependencies(final IClasspathDependencyProvider component) throws CoreException {
		if (!component.isBinary()) {
			final IVirtualReference[] cpRefs = component.getJavaClasspathReferences();
			for (int i = 0; i < cpRefs.length; i++) {
				final IVirtualReference cpRef = cpRefs[i];
				final IPath runtimePath = cpRef.getRuntimePath();
				final IVirtualComponent comp = cpRef.getReferencedComponent();
				if (comp instanceof IClasspathDependencyComponent) {
					final IClasspathDependencyComponent cpComp = (IClasspathDependencyComponent) comp;
					if (cpComp.isClassFolder() && cpComp.getClassFolder() != null) {
						IPath targetPath = null;
						targetPath = runtimePath.makeRelative();
						final IContainer container = cpComp.getClassFolder();
						new VirtualComponentFlattenUtility(list, null).addContainer(container, targetPath);
					}
				}
			}
		}
	}
}
