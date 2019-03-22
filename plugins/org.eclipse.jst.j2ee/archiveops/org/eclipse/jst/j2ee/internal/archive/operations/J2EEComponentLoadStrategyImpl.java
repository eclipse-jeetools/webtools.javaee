/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.List;

import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class J2EEComponentLoadStrategyImpl extends ComponentLoadStrategyImpl{

	public J2EEComponentLoadStrategyImpl(IVirtualComponent component) {
		super(component);
	}
	
	public J2EEComponentLoadStrategyImpl(IVirtualComponent component, boolean includeClasspathComponents) {
		super(component, includeClasspathComponents);
	}
	
	@Override
	public List getFiles() {
		super.getFiles();
		if (includeClasspathComponents) {
			addMappedClassFolders(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_COMPONENT_PATH);
		}
		return filesHolder.getFiles();
	}
}
