/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;

/**
 * Properties for the UpdateClasspathAttributesOperation.
 */
public interface UpdateClasspathAttributesDataModelProperties extends IClasspathDependencyConstants {

	/**
	 * Project name. Required.
	 */
	public static final String PROJECT_NAME = "UpdateClasspathAttributesDataModel.PROJECT_NAME"; //$NON-NLS-1$
	
	/**
	 * Classpath entries that should have the component dependency attribute. Map from IClasspathEntry to runtime IPath. Will be
	 * ignored if either ENTRIES_TO_ADD_ATTRIBUTE or ENTRIES_TO_REMOVE_ATTRIBUTE are specified.
	 */
	public static final String ENTRIES_WITH_ATTRIBUTE = "UpdateClasspathAttributesDataModel.ENTRIES_WITH_ATTRIBUTE"; //$NON-NLS-1$
	
	/**
	 * Classpath entries to which the component dependency attribute should be added. Map from IClasspathEntry to runtime IPath. If this 
	 * property is specified, ENTRIES_WITH_ATTRIBUTE and ENTRIES_TO_REMOVE_ATTRIBUTE will be ignored
	 */
	public static final String ENTRIES_TO_ADD_ATTRIBUTE = "UpdateClasspathAttributesDataModel.ENTRIES_TO_ADD_ATTRIBUTE"; //$NON-NLS-1$
	
	/**
	 * Classpath entries to which the component dependency attribute should be removed. 
	 * Map from IClasspathEntry to runtime IPath. If this property is specified, ENTRIES_WITH_ATTRIBUTE will be ignored. This attribute will be ignored if ENTRIES_TO_ADD_ATTRIBUTE is specified.
	 */
	public static final String ENTRIES_TO_REMOVE_ATTRIBUTE = "UpdateClasspathAttributesDataModel.ENTRIES_TO_REMOVE_ATTRIBUTE"; //$NON-NLS-1$

	/**
	 * Flag used to determine if the "org.eclipse.jst.component.dependency" or the "org.eclipse.jst.component.nondependency" attribute is being added/removed.
	 * Will be true if the "org.eclipse.jst.component.dependency" attribute is being modified. Defaults to true.
	 */
	public static final String MODIFY_CLASSPATH_COMPONENT_DEPENDENCY = "UpdateClasspathAttributesDataModel.MODIFY_CLASSPATH_COMPONENT_DEPENDENCY"; //$NON-NLS-1$
	
	
}
