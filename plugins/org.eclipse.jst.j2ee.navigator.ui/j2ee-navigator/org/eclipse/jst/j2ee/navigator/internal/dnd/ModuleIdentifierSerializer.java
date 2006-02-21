/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.wst.common.frameworks.internal.AdaptabilityUtility;



/**
 * @author mdelder
 *  
 */
public class ModuleIdentifierSerializer {


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.dnd.ISerializer#toByteArray(java.lang.Object)
	 */
	public byte[] toByteArray(Object data) {
		IProject project = (IProject) AdaptabilityUtility.getAdapter(data, IProject.class);
		if (project != null)
			return project.getName().getBytes();
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.dnd.ISerializer#fromByteArray(byte[])
	 */
	public Object fromByteArray(byte[] data) {
		String name = new String(data);
		return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
	}
}
