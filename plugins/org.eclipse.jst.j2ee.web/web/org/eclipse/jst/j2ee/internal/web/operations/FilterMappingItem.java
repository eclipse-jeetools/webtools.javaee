package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;

public class FilterMappingItem implements IFilterMappingItem {
    
    private int mappingType;
    private int dispatchers;
    private Object mapping;
    
    public FilterMappingItem(int type, Object mapping) {
        mappingType = type;
        this.mapping = mapping;
    }

    public FilterMappingItem(int type, Object mapping, int dispatchers) {
        this(type, mapping);
        this.dispatchers = dispatchers;
    }

    public int getMappingType() {
        return mappingType;
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

    public Object getMapping() {
        return mapping;
    }

    public String getName() {
        if (mappingType == URL_PATTERN) {
            return (String) mapping;
        }
        return ((IType) mapping).getElementName();
    }

}
