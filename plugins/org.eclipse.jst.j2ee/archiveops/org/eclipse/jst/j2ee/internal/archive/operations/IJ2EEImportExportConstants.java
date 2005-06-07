/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;


import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

public interface IJ2EEImportExportConstants {

	public String CLASSPATH_FILE_URI = ProjectUtilities.DOT_CLASSPATH;
	public String PROJECT_FILE_URI = ProjectUtilities.DOT_PROJECT;
	public String PROJECT_RUNTIME_URI = ".runtime"; //$NON-NLS-1$

}
