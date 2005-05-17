/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface IJ2EEArtifactImportDataModelProperties extends IDataModelProperties {

	/**
	 * This property is the loaded Module file.
	 */
	public static final String MODULE_FILE = "IJ2EEArtifactImportDataModelProperties.MODULE_FILE";
	
	/**
	 * This property tracks the target IVirtualComponent the archive will be imported into. 
	 */
	public static final String TARGET_VIRTUAL_COMPONENT = "IJ2EEArtifactImportDataModelProperties.TARGET_VIRTUAL_COMPONENT";

	/**
	 * This is an ID for a nested IDataModel for the VirtualComponentCreation.
	 */
	public static final String NESTED_VIRTUAL_COMPONENT_MODEL = "IJ2EEArtifactImportDataModelProperties.NESTED_VIRTUAL_COMPONENT_MODEL";;

}
