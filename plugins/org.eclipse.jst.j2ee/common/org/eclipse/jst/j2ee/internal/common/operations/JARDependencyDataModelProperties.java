/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.operations;

import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;

public interface JARDependencyDataModelProperties {

	/**
	 * String, the ear project name, required
	 */
	public static final String EAR_PROJECT_NAME = "AbstractJARDependencyDataModel.EAR_PROJECT_NAME"; //$NON-NLS-1$

	public static final String REFERENCED_PROJECT_NAME = "AbstractJARDependencyDataModel.REFERENCED_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * nested, required
	 */
	public static final String PROJECT_NAME = UpdateManifestDataModelProperties.PROJECT_NAME;


	/**
	 * Used for client JAR dependency inversion
	 */
	public static final String OPPOSITE_PROJECT_NAME = "AbstractJARDependencyDataModel.OPPOSITE_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * nested
	 */
	public static final String JAR_LIST = UpdateManifestDataModelProperties.JAR_LIST;

	/**
	 * type Integer, default JAR_MANIPULATION_ADD, other possible values are JAR_MANIPULATION_REMOVE
	 * and JAR_MANIPULATION_INVERT
	 */
	public static final String JAR_MANIPULATION_TYPE = "AbstractJARDependencyDataModel.JAR_MANIPULATION_TYPE"; //$NON-NLS-1$
	
	public static final String NESTED_MODEL_UPDATE_MAINFEST = "AbstractJARDependencyDataModel.NESTED_MODEL_UPDATE_MAINFEST"; //$NON-NLS-1$

	public static final int JAR_MANIPULATION_ADD = 0;
	public static final int JAR_MANIPULATION_REMOVE = 1;
	public static final int JAR_MANIPULATION_INVERT = 2;

}
