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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.web.taglib.IWebXMLTaglibInfo;


/**
 * @author wsad2
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class WebXMLTaglibInfo extends TaglibInfo implements IWebXMLTaglibInfo {

	public WebXMLTaglibInfo(IProject project, String uri, IPath tldLocation) {
		super(project, uri, tldLocation);
		setWebXMLLocation(getLocation());
	}

	public WebXMLTaglibInfo(IProject project, String uri, IPath jarLocation, IPath tldLocation) {
		super(project, uri, jarLocation, tldLocation);
		setWebXMLLocation(getLocation());
	}



	private boolean isLocationResolved = true;

	private IPath webXMLLocation;


	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.IWebXMLTaglibInfo#isLocationResolved()
	 */
	public boolean isLocationResolved() {
		return this.isLocationResolved;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.IWebXMLTaglibInfo#getWebXMLTaglibLocation()
	 */
	public IPath getWebXMLTaglibLocation() {
		return this.webXMLLocation;
	}


	/**
	 * Sets the isLocationResolved.
	 * 
	 * @param isLocationResolved
	 *            The isLocationResolved to set
	 */
	public void setIsLocationResolved(boolean isResolved) {
		this.isLocationResolved = isResolved;
	}

	/**
	 * Sets the webXMLLocation.
	 * 
	 * @param webXMLLocation
	 *            The webXMLLocation to set
	 */
	public void setWebXMLLocation(IPath webXMLLocation) {
		this.webXMLLocation = webXMLLocation;
	}

}