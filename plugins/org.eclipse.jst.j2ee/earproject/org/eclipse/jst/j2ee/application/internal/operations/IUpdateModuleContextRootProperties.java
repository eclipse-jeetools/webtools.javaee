/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     rfrost@bea.com - initial API and impl
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.web.internal.operation.IWebProjectPropertiesUpdateDataModelProperties;

/**
 * Holds DataModelOperation properties for updating the context-root of modules in associated EAR application.xml files.
 */
public interface IUpdateModuleContextRootProperties extends IWebProjectPropertiesUpdateDataModelProperties {
	/**
	 * ID for nexted IDataModel
	 */
	public static final String NESTED_MODEL_ID = "UpdateModuleContextRootModel"; //$NON-NLS-1$
	/**
	 * Name of property (in nested model) that holds a List of EAR IProjects.
	 */
	public static final String EARS_TO_UPDATE = "IUpdateModuleContextRootProperties.EARS_TO_UPDATE"; //$NON-NLS-1$
	
}
