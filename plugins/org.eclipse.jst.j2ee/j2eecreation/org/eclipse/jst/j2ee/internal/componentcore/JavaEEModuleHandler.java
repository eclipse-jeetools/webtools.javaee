/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import java.util.List;

import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.IModuleHandler;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JavaEEModuleHandler implements IModuleHandler {

	public String getArchiveName(IVirtualComponent comp) {
		return JavaEEProjectUtilities.getComponentURI(comp);
	}

	public List<IVirtualComponent> getFilteredListForAdd(IVirtualComponent sourceComponent, IVirtualComponent[] availableComponents) {
		// TODO Auto-generated method stub
		return null;
	}

}
