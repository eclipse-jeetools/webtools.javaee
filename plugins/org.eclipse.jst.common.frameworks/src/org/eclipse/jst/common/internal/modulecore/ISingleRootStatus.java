/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

public interface ISingleRootStatus extends IStatus {
	
	// Information [0-32] 
	public static final int SINGLE_ROOT_CONTAINER_FOUND = 1;
	public static final int SINGLE_ROOT_FORCED = 2;
	//DO NOT ADD ANY OTHER CONSTANTS TO THE INFORMATION SECTION
	
	// Warnings [33-127]
	public static final int SOURCE_PATH_NOT_FOUND = 33;
	
	// Errors [128-512]
	public static final int NO_COMPONENT_FOUND = 129;
	public static final int EXPLICITLY_DISABLED = 130;
	public static final int CONSUMABLE_REFERENCES_FOUND = 131;
	public static final int LINKED_RESOURCES_FOUND = 132;
	public static final int NO_RESOURCE_MAPS_FOUND = 133;
	public static final int JAVA_OUTPUT_NOT_A_CONTENT_ROOT = 134;
	public static final int JAVA_OUTPUT_GREATER_THAN_1 = 135;
	public static final int RUNTIME_PATH_NOT_ROOT = 136;
	public static final int SOURCE_NOT_JAVA_CONTAINER = 137;
	public static final int BINARY_COMPONENT_FOUND = 138;

	// callback-contributed errors: 512 and up
	
	/**
	 * Returns the path with which the status is associated.
	 *
	 * @return the path with which the status is associated.
	 */
	public IPath getPath();
	
	/** 
	 * Will return the IContainer that is used as the "single-root".
	 * If a "single-root" structure is not found this method will return null. 
	 * 
	 * @return the "single-root" container
	 */ 
	public IContainer getSingleRoot();
}
