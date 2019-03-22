/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Collect the classpath LIB references from a dependency provider
 * and add to the members list.
 * 
 */
public class AddClasspathLibRefsProviderParticipant extends AbstractFlattenParticipant {
	private List<IFlatResource> list;
	
	
	@Override
	public void finalize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.list = resources;
		
		if (!(component instanceof IClasspathDependencyProvider))
			return;
		
		addReferencedComponentClasspathDependencies((IClasspathDependencyProvider)component);
	}

	private void addReferencedComponentClasspathDependencies(final IClasspathDependencyProvider component) {
		final IVirtualReference[] cpRefs = component.getJavaClasspathReferences();
		for (int j = 0; j < cpRefs.length; j++) {
			final IVirtualReference cpRef = cpRefs[j];
			IPath cpRefRuntimePath = cpRef.getRuntimePath();

			if (cpRef.getReferencedComponent() instanceof IClasspathDependencyComponent) {
				// want to avoid adding dups
				IClasspathDependencyComponent cpComp = (IClasspathDependencyComponent) cpRef.getReferencedComponent();
				// don't want to process class folder refs here
				if (cpComp.isClassFolder())
					continue;

				if (cpRefRuntimePath.toString().startsWith(IClasspathDependencyReceiver.RUNTIME_MAPPING_INTO_CONTAINER))
					continue;

				new VirtualComponentFlattenUtility(list, null).addFile(cpComp, cpRefRuntimePath, cpComp);
			}
		}
	}
	
}
