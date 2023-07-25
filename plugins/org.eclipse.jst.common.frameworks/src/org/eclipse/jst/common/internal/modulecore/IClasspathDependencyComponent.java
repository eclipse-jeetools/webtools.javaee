/*******************************************************************************
 * Copyright (c) 2009, 2023 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.core.resources.IContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public interface IClasspathDependencyComponent extends IVirtualComponent {
	public boolean isClassFolder();
	public IContainer getClassFolder();
	@Override
	public IVirtualReference[] getReferences();
	public String[] getManifestClasspath();
}
