/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import org.eclipse.core.resources.IFile;

public class WebServicesClientEditModelOwner extends AbstractEditModelOwner {
	public WebServicesClientEditModelOwner(IFile inputFile) {
		fInputFile = inputFile;
		fProject = fInputFile.getProject();

	}

	public EditModel createEditModel() {
		fEditModel = new WebServicesClientEditModel(this);
		return fEditModel;
	}

	public void dispose() {
		fEditModel = null;
		EditModelFactory.getEditModelFactory().disposeWebServicesClientEditModel(fProject);
	}

}