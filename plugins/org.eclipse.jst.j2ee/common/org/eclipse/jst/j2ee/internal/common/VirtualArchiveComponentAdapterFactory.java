/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IPath.class && adaptableObject instanceof VirtualArchiveComponent ) {
			VirtualArchiveComponent component = (VirtualArchiveComponent) adaptableObject;
			IPath path = J2EEProjectUtilities.getResolvedPathForArchiveComponent(component.getName());
			return path;
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[]{ IPath.class };
	}	

}
