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
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action;

public interface IFacetDataModelPropeties extends IDataModelProperties {

	public static final Object FACET_TYPE_INSTALL = Action.Type.INSTALL;
	public static final Object FACET_TYPE_UNINSTALL = Action.Type.UNINSTALL;
	public static final Object FACET_TYPE_VERSION_CHANGE = Action.Type.VERSION_CHANGE;

	public static final String FACET_PROJECT_NAME = "IWebFacetInstallDataModelProperties.FACET_PROJECT_NAME";

	public static final String FACET_TYPE = "IFacetDataModelPropeties.FACET_TYPE";

	public static final String FACET_ID = "IFacetDataModelPropeties.FACET_ID";

	public static final String FACET_VERSION_STR = "IFacetDataModelPropeties.FACET_VERSION_STR";

	public static final String FACET_VERSION = "IFacetDataModelPropeties.FACET_VERSION";

}
