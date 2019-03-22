/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     rfrost@bea.com - initial API and impl
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.componentcore.datamodel.properties.IServerContextRootDataModelProperties;

/**
 * Holds DataModelOperation properties for updating the context-root of modules in associated EAR application.xml files.
 */
public interface IUpdateModuleContextRootProperties extends IServerContextRootDataModelProperties {
	/**
	 * ID for nexted IDataModel
	 */
	public static final String NESTED_MODEL_ID = "UpdateModuleContextRootModel"; //$NON-NLS-1$
	/**
	 * Name of property (in nested model) that holds a List of EAR IProjects.
	 */
	public static final String EARS_TO_UPDATE = "IUpdateModuleContextRootProperties.EARS_TO_UPDATE"; //$NON-NLS-1$
	
}
