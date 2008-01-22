/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;

public class FilterMappingItem implements IFilterMappingItem {
    
    private int mappingType;
    private int dispatchers;
    private String mapping;
    
    public FilterMappingItem(int type, String mapping) {
        mappingType = type;
        this.mapping = mapping;
    }

    public FilterMappingItem(int type, String mapping, int dispatchers) {
        this(type, mapping);
        this.dispatchers = dispatchers;
    }

    public int getMappingType() {
        return mappingType;
    }
    
    public boolean isUrlPatternType() {
    	return mappingType == IFilterMappingItem.URL_PATTERN;
    }
    
    public boolean isServletNameType() {
    	return mappingType == IFilterMappingItem.SERVLET_NAME;
    }

    public int getDispatchers() {
        return dispatchers;
    }

    public String getDispatchersAsString() {
        String result = "";
        if ((dispatchers & REQUEST) > 0) {
            result += WebAppEditResourceHandler.getString("DISPATCHER_R") + " "; 
        }
        if ((dispatchers & FORWARD) > 0) {
            result += WebAppEditResourceHandler.getString("DISPATCHER_F") + " "; 
        }
        if ((dispatchers & INCLUDE) > 0) {
            result += WebAppEditResourceHandler.getString("DISPATCHER_I") + " "; 
        }
        if ((dispatchers & ERROR) > 0) {
            result += WebAppEditResourceHandler.getString("DISPATCHER_E") + " "; 
        }
        return result.trim();
    }

    public String getName() {
        return mapping;
    }

}
