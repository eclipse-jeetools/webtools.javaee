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
package org.eclipse.jst.j2ee.internal.project;


import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchByteArrayOutputStream;



/**
 * @author vijayb
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ManifestFileCreationAction {

	public static final String MANIFEST_HEADER = "Manifest-Version: 1.0\r\nClass-Path: \r\n\r\n"; //$NON-NLS-1$

	/**
	 * Constructor for ManifestFileCreationAction.
	 */
	public ManifestFileCreationAction() {
		super();
	}

	public static void createManifestFile(IFile file, IProject aJ2EEProject) throws CoreException, IOException {
		try {
			if (!ProjectUtilities.isBinaryProject(aJ2EEProject)) {
				WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(file);
				out.write(MANIFEST_HEADER.getBytes());
				out.close();
			}
		} catch (IOException ioe) {
			throw ioe;
		}
	}
}