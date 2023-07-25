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

public interface IFilterMappingItem {
	
    public static int URL_PATTERN = 0;
    public static int SERVLET_NAME = 1;
    
    public static int REQUEST = 1 << 1;
    public static int FORWARD = 1 << 2;
    public static int INCLUDE = 1 << 3;
    public static int ERROR = 1 << 4;
 
    public String getName();
    public int getMappingType();
    public boolean isUrlPatternType();
    public boolean isServletNameType();
    public int getDispatchers();
    public String getDispatchersAsString();
    
}
