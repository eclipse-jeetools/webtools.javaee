/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.datamodel.properties;


/**
 * <p>
 * IJavaComponentCreationDataModelProperties provides properties to the 
 * JavaComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IJavaComponentCreationDataModelProperties specifically, but not limited to all J2EE component related
 * creation.
 * @see org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * 
 * @plannedfor 1.0
 */
public interface IUtilityJavaComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties {
	/**
	 * Optional, type String
     * String indicating the name of the the root Java Source Folder in the component being created.
     * The DataModelProvider will default the Java Source Folder to the Component folder.
     * 
	 */
	public static final String EAR_PROJECT_NAME = "IUtilityJavaComponentCreationDataModelProperties.EAR_PROJECT_NAME";

}
