/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;

import org.eclipse.jst.j2ee.internal.J2EEConstants;



/**
 * This is a catalog of useful constants for the archive support. Can be used to store relative
 * paths to specific xml and xmi resources.
 */
public interface ArchiveConstants extends J2EEConstants {
	//Standard Jar info
	/** "com" */
	String RAR_CLASSES_URI = "com"; //$NON-NLS-1$

	/**
	 * Relative path in a war file with no leading slash "WEB-INF/lib/"
	 */
	String WEBAPP_LIB_URI = "WEB-INF/lib/"; //$NON-NLS-1$

	/**
	 * Relative path in a war file with no leading slash "WEB-INF/classes/"
	 */
	String WEBAPP_CLASSES_URI = "WEB-INF/classes/"; //$NON-NLS-1$
}
