/*******************************************************************************
 * Copyright (c) 2007-2013 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 * Fred Bricon (Red Hat, Inc.) - 359385 : added CLASSPATH_ARCHIVENAME_ATTRIBUTE 
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpathdep;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;

/**
 * Holds constants related to classpath component dependencies.
 */
public interface IClasspathDependencyConstants {

	/**
	 * Enumeration that is used to select component dependency attribute type. 
	 */
	public static enum DependencyAttributeType {CLASSPATH_COMPONENT_DEPENDENCY, CLASSPATH_COMPONENT_NONDEPENDENCY, DEPENDENCY_OR_NONDEPENDENCY} 
	
	/**
	 * Name of the custom Java classpath entry attribute that is used to flag
	 * entries which should be exposed as module dependencies via the virtual component API. 
	 * The value of the attribute represents the runtime path to which resolved classpath entry components
	 * will be mapped in the deployed/exported module. By default, tagged classpath entries of dynamic web 
	 * projects will be mapped to the WEB-INF/lib folder of the web project (or WEB-INF/classes if the entry points to a class folder). 
	 * Tagged classpath entries that reference archives for utility, ejb and connector projects will be mapped to either the root of the EAR (if the project is
	 * referenced by an EAR) or to the /WEB-INF/lib directory of the WAR (if the project is a 
	 * web library reference of a web project); the runtime path of "../" is used to represent the mapping
	 * into the referencing module in both of these cases. Tagged classpath entries that reference class folders for utility, ejb and connector projects
	 * will map into the root of the utility, ejb or connector archive.
	 * Only container, library and variable entries are currently supported; for containers, only the resolved
	 * entries that are library entries are supported. All resolved entries are mapped unless they have the 
	 * "org.eclipse.jst.component.nondependency" attribute (or are project which are not currently supported and are therefore always skipped). 
	 */
	public static final String CLASSPATH_COMPONENT_DEPENDENCY = "org.eclipse.jst.component.dependency"; //$NON-NLS-1$

	/**
	 * Name of the custom Java classpath entry attribute that is used to flag
	 * the resolved entries of classpath containers that should not be exposed
	 * via the virtual component API.
	 */
	public static final String CLASSPATH_COMPONENT_NON_DEPENDENCY = "org.eclipse.jst.component.nondependency"; //$NON-NLS-1$
	
	/**
	 * Runtime path used to indicate that the resolved contributions from a classpath entry should be
	 * mapped into the exported/deployed structure of the parent module at the same location that the target module
	 * is mapped (will be either the EAR root or /WEB-INF/lib).
	 */
	public static final String RUNTIME_MAPPING_INTO_CONTAINER = "../"; //$NON-NLS-1$
	
	/**
	 * IPath value of RUNTIME_MAPPING_INTO_CONTAINER.
	 */
	public static final IPath RUNTIME_MAPPING_INTO_CONTAINER_PATH = new Path(RUNTIME_MAPPING_INTO_CONTAINER);

	/**
	 * Runtime path used to indicate that the resolved contributions from a classpath entry should be
	 * mapped into the root of the exported/deployed module.
	 */
	public static final String RUNTIME_MAPPING_INTO_COMPONENT ="/"; //$NON-NLS-1$
	
	/**
	 * IPath value of RUNTIME_MAPPING_INTO_COMPONENT.
	 */
	public static final IPath RUNTIME_MAPPING_INTO_COMPONENT_PATH = new Path(RUNTIME_MAPPING_INTO_COMPONENT);
	
	/**
	 * Runtime path used to map into /WEB-INF/classes
	 */
	public static final IPath WEB_INF_CLASSES_PATH = new Path(J2EEConstants.WEB_INF_CLASSES).makeAbsolute();
	
	/**
	 * Runtime path used to map into /WEB-INF/lib
	 */
	public static final IPath WEB_INF_LIB_PATH = new Path(J2EEConstants.WEB_INF_LIB).makeAbsolute();
	
	
	/**
	 * Name of the custom Java classpath entry attribute that is used to override the exported/deployed 
	 * archive name of a given binary.
	 */
	public static final String CLASSPATH_ARCHIVENAME_ATTRIBUTE = "org.eclipse.jst.component.archivename"; //$NON-NLS-1$
	
	}
