/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib;

import org.eclipse.core.runtime.IPath;

/**
 * @author wsad2
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface IWebXMLTaglibInfo extends ITaglibInfo {

	/**
	 * Method isResolved. If the return value is true then the getLocation method actually resolves
	 * to a resource , if the return value is false then the getLocation method doesnt resolve to a
	 * resouce.
	 * 
	 * @return boolean
	 */
	public boolean isLocationResolved();

	/**
	 * Method getWebXMLTaglibLocation. This method returns the actual value specified as the
	 * location of the taglib defined in a deployment descriptor entry
	 * 
	 * @return IPath
	 */
	public IPath getWebXMLTaglibLocation();

}