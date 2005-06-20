/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.datamodel.properties;

import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;

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
 * @since 1.0
 */
public interface IJavaComponentCreationDataModelProperties extends IComponentCreationDataModelProperties {
	/**
	 * Optional, type String
     * String indicating the name of the the root Java Source Folder in the component being created.
     * The DataModelProvider will default the Java Source Folder to the Component folder.
     * 
	 */
	public static final String JAVASOURCE_FOLDER = "IJavaComponentCreationDataModelProperties.JAVASOURCE_FOLDER";
    /**
     * Optional, type String
     * String indicating the name of the the root folder containing the Manifest.MF in the component being created.
     * The DataModelProvider will default the Manifest folder to the Component folder/META-INF.
     */
	public static final String MANIFEST_FOLDER = "IJavaComponentCreationDataModelProperties.MANIFEST_FOLDER";
	
    /**
     * Required, type String. This is used to specify the server target.
     * 
     */
    public static final String RUNTIME_TARGET_ID = IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID;	
}
