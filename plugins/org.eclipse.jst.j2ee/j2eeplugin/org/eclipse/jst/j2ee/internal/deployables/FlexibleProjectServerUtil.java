/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.server.core.IJ2EEModule;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;

public class FlexibleProjectServerUtil {
	
	public static IJ2EEModule getModuleDelegate(WorkbenchComponent component) {
		IModule module = getModule(component);
		return getModuleDelegate(module);
	}
	
	public static IModule getModule(IProject project) {
		return ServerUtil.getModule(project);
	}

	public static IJ2EEModule getModuleDelegate(IModule module) {
		return (IJ2EEModule) module.loadAdapter(IJ2EEModule.class, null);
	}

	public static IModule getModule(WorkbenchComponent component) {
		return getModule(StructureEdit.getContainingProject(component));
	}
}
