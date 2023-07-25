/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore.util;

public interface UpdateManifestDataModelProperties {
	/**
	 * Project name with manifest to update, type String required.
	 */
	public static final String PROJECT_NAME = "UpdateManifestDataModel.PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * java.util.List of Strings
	 */
	public static final String JAR_LIST = "UpdateManifestDataModel.CLASSPATH_LIST"; //$NON-NLS-1$

	/**
	 * String. This is build from the JAR_LIST property. Never set this property.
	 */
	public static final String JAR_LIST_TEXT_UI = "UpdateManifestDataModel.CLASSPATH_LIST_TEXT_UI"; //$NON-NLS-1$

	/**
	 * Boolean, true merges, false replaces, default is true
	 */
	public static final String MERGE = "UpdateManifestDataModel.MERGE"; //$NON-NLS-1$

	/**
	 * String, no default.
	 */
	public static final String MAIN_CLASS = "UpdateManifestDataModel.MAIN_CLASS"; //$NON-NLS-1$
	
		
	/**
	 * String, no default.
	 */
	public static final String MANIFEST_FILE = "UpdateManifestDataModel.MANIFEST_FILE"; //$NON-NLS-1$	
	

}
