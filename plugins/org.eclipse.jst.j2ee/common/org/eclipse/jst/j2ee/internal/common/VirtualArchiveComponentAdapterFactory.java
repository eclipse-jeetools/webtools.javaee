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
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;

public class VirtualArchiveComponentAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == VirtualArchiveComponent.ADAPTER_TYPE ) {
			VirtualArchiveComponent component = (VirtualArchiveComponent) adaptableObject;
			IPath path = J2EEProjectUtilities.getResolvedPathForArchiveComponent(component.getName());
			return path;
		}
		return null;
	}

	
	public Class[] getAdapterList() {
		return new Class[]{VirtualArchiveComponent.class};
	}	

}
