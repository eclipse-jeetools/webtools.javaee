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
/*
 * Created on Aug 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateInputProvider;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class WebSettingsStateInputProvider implements ResourceStateInputProvider {
	protected IProject project = null;

	/**
	 * Constructor for WebSettingsStateInputProvider.
	 */
	public WebSettingsStateInputProvider(IProject theProject) {
		super();
		project = theProject;
	}

	/**
	 * @see ResourceStateInputProvider#isDirty()
	 */
	public boolean isDirty() {
		return false;
	}

	/**
	 * @see ResourceStateInputProvider#getResources()
	 */
	public List getResources() {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @see com.ibm.etools.emf.workbench.ResourceStateInputProvider#getNonResourceFiles()
	 */
	public List getNonResourceFiles() {
		IFile aFile = project.getFile(IWebNatureConstants.WEBSETTINGS_FILE_NAME);
		return Collections.singletonList(aFile);
	}

	/**
	 * @see ResourceStateInputProvider#getNonResourceInconsistentFiles()
	 */
	public List getNonResourceInconsistentFiles() {
		return null;
	}

	/**
	 * @see ResourceStateInputProvider#cacheNonResourceValidateState(List)
	 */
	public void cacheNonResourceValidateState(List arg0) {
	}
}