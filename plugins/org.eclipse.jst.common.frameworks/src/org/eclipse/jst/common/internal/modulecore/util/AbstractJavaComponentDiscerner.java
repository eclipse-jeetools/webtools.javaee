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
package org.eclipse.jst.common.internal.modulecore.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class AbstractJavaComponentDiscerner implements IJavaComponentDiscerner {

	public IProject[] findParentProjects(IProject child) {
		return new IProject[]{};
	}

	public IVirtualReference[] findPossibleManifestEntries(
			IProject parentProject, IProject childProject) {
		return new IVirtualReference[]{};
	}

	public IVirtualReference[] findPossibleManifestEntries(
			IProject parentProject, IProject childProject,
			IVirtualReference[] currentEntries) {
		return new IVirtualReference[]{};
	}

	public IVirtualReference[] findCurrentManifestEntries(
			IProject parentProject, IProject childProject) {
		return new IVirtualReference[]{};
	}
}
