/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib;

import org.eclipse.core.resources.IProject;

public interface ITaglibRegistryManager {

	ITaglibRegistry getTaglibRegistry(IProject project);

	/**
	 * use this if you want to access the registry only if it exists
	 * 
	 * @param project
	 * @return
	 */
	boolean isTaglibRegistryExists(IProject project);


	void dispose();
}