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

package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.wst.validation.internal.IValidationSelectionHandler;


/**
 * Java Project validation 
 */
public class JavaProjectValidationHandler implements IValidationSelectionHandler {
	
	private String validationType = null;
	
	/**
	 * Default constructor
	 */
	public JavaProjectValidationHandler() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.IValidationSelectionHandler#getBaseValidationType(java.lang.Object)
	 */
	public IResource getBaseValidationType(Object selection) {
		if (selection instanceof IJavaProject)
			return ((IJavaProject)selection).getProject();
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.IValidationSelectionHandler#getValidationTypeString()
	 */
	public String getValidationTypeString() {
		return validationType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.IValidationSelectionHandler#setValidationTypeString(java.lang.String)
	 */
	public void setValidationTypeString(String validationType) {
		this.validationType = validationType;
	}

}
