/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

public interface INewFilterClassDataModelProperties extends INewWebClassDataModelProperties {
	
	/**
	 * Optional, boolean property used to specify whether to generate the init method.
	 * The default is false.
	 */
	public static final String INIT = "NewFilterClassDataModel.INIT"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the destroy method.
	 * The default is false.
	 */
	public static final String DESTROY = "NewFilterClassDataModel.DESTROY"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doFilter method.
	 * The default is true.
	 */
	public static final String DO_FILTER = "NewFilterClassDataModel.DO_FILTER"; //$NON-NLS-1$
	
	/**
	 * Optional, List property used to cache all the init params defined on the filter.
	 */
	public static final String INIT_PARAM = "NewFilterClassDataModel.INIT_PARAM"; //$NON-NLS-1$

    /**
     * Optional, List propety used to cache all the filter mappings for this filter on the web application.
     */
    public static final String FILTER_MAPPINGS = "NewFilterClassDataModel.FILTER_MAPPINGS"; //$NON-NLS-1$
    
	/**
	 * Optional, boolean property used to enable asynchronous support for this filter
	 */
	public static final String ASYNC_SUPPORT = "NewFilterClassDataModel.ASYNC_SUPPORT"; //$NON-NLS-1$
	
}
