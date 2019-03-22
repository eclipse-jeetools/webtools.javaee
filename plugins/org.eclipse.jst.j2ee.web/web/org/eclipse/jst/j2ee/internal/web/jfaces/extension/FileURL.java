/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 13, 2004
 */
package org.eclipse.jst.j2ee.internal.web.jfaces.extension;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;


/**
 * @author vijayb
 */
public interface FileURL {
	
	/**
	 * @return The existingURL if no suitable extender is found, else the 
	 * path corresponding to the passed in resource. 
	 */
	public IPath getFileURL(IResource resource, IPath existingURL);
}