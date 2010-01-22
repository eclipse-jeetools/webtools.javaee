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
package org.eclipse.jst.j2ee.project;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;

public interface ISingleRootStatus extends IStatus {
	
	// Information Only [0-32]
	
	// Warnings [33-65]
	public static final int SOURCE_PATH_NOT_FOUND = 33;
	
	// Errors [66-98]
	public static final int NO_COMPONENT_FOUND = 67;

	public static final int JAVA_OUTPUT_GREATER_THAN_1 = 68;

	public static final int EAR_PROJECT_FOUND = 69;

	public static final int LINKED_RESOURCES_FOUND = 70;

	public static final int JAVA_OUTPUT_NOT_A_CONTENT_ROOT = 71;

	public static final int JAVA_OUTPUT_NOT_WEBINF_CLASSES = 72;

	public static final int ATLEAST_1_RESOURCE_MAP_MISSING = 73;

	public static final int NO_RESOURCE_MAPS_FOUND = 74;

	public static final int ONE_CONTENT_ROOT_REQUIRED = 75;

	public static final int ATLEAST_1_JAVA_SOURCE_REQUIRED = 76;

	public static final int RUNTIME_PATH_NOT_ROOT = 77;

	public static final int SOURCE_NOT_JAVA_CONTAINER = 78;

	public static final int RUNTIME_PATH_NOT_ROOT_OR_WEBINF_CLASSES = 79;

	public static final int ONLY_1_CONTENT_ROOT_ALLOWED = 80;
	
	public ComponentResource getComponentResource();


}
