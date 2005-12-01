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

import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;

public interface INewJavaClassDataModelProperties extends IArtifactEditOperationDataModelProperties {

	/**
	 * Required, String property used to set the unqualified java class name for the new java class.
	 */
	public static final String CLASS_NAME = "NewJavaClassDataModel.CLASS_NAME"; //$NON-NLS-1$
	
	/**
	 * Optional, String property used to set the source folder location for the new java class.  The first
	 * source folder found in the project will be used if one is not specified.
	 */
	public static final String SOURCE_FOLDER = "NewJavaClassDataModel.SOURCE_FOLDER"; //$NON-NLS-1$
	
	/**
	 * Optional, String property used to to set the java package for the new java class.  The default
	 * package is used if one is not specified.
	 */
	public static final String JAVA_PACKAGE = "NewJavaClassDataModel.JAVA_PACKAGE"; //$NON-NLS-1$
	
	/**
	 * Optional, String property used to set the qualified java class name of the superclass of the
	 * new java class.
	 */
	public static final String SUPERCLASS = "NewJavaClassDataModel.SUPERCLASS"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set the visibility of the new java class. This is true
	 * by default.
	 */
	public static final String MODIFIER_PUBLIC = "NewJavaClassDataModel.MODIFIER_PUBLIC"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set whether the new java class is abstract.  This is false
	 * by default.
	 */
	public static final String MODIFIER_ABSTRACT = "NewJavaClassDataModel.MODIFIER_ABSTRACT"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set whether the new java class is declared final.  This is false
	 * by default.
	 */
	public static final String MODIFIER_FINAL = "NewJavaClassDataModel.MODIFIER_FINAL"; //$NON-NLS-1$
	
	/**
	 * Optional, List property of all the qualified names of interfaces the new java class should implement.
	 */
	public static final String INTERFACES = "NewJavaClassDataModel.INTERFACES"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set whether the new java class should generate a main method.  This
	 * is false by default.
	 */
	public static final String MAIN_METHOD = "NewJavaClassDataModel.MAIN_METHOD"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set whether or not the constructor from the superclass should be
	 * generated in the new java class.  The default value is true.
	 */
	public static final String CONSTRUCTOR = "NewJavaClassDataModel.CONSTRUCTOR"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to set whether the new java class should add method stubs for unimplemented
	 * methods defined in the interfaces of the interface list.  This is true by default.
	 */
	public static final String ABSTRACT_METHODS = "NewJavaClassDataModel.ABSTRACT_METHODS"; //$NON-NLS-1$
	
	/**
	 * THIS PROPERTY SHOULD NOT BE SET, it's value is meant to be determined as a helper method
	 */
	public static final String JAVA_PACKAGE_FRAGMENT_ROOT = "NewJavaClassDataModel.JAVA_PACKAGE_FRAGMENT_ROOT"; //$NON-NLS-1$
	
	/**
	 * THIS PROPERTY SHOULD NOT BE SET, it's value is meant to be determined as a helper method
	 */
	public static final String JAVA_SOURCE_FOLDER = "NewJavaClassDataModel.JAVA_SOURCE_FOLDER"; //$NON-NLS-1$
	
	/**
	 * THIS PROPERTY SHOULD NOT BE SET, it's value is meant to be determined as a helper method
	 */
	public static final String PROJECT = "NewJavaClassDataModel.PROJECT"; //$NON-NLS-1$
	
	/**
	 * THIS PROPERTY SHOULD NOT BE SET, it's value is meant to be determined as a helper method
	 */
	public static final String QUALIFIED_CLASS_NAME = "NewJavaClassDataModel.QUALIFIED_CLASS_NAME"; //$NON-NLS-1$
}
