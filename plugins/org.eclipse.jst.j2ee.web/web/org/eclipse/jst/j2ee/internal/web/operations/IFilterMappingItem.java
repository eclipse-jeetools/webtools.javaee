package org.eclipse.jst.j2ee.internal.web.operations;

public interface IFilterMappingItem {
    public static int URL_PATTERN = 0;
    public static int SERVLET_NAME = 1;
    
    public static int REQUEST = 1 << 1;
    public static int FORWARD = 1 << 2;
    public static int INCLUDE = 1 << 3;
    public static int ERROR = 1 << 4;
 
    public int getDispatchers();
    public String getDispatchersAsString();
    public int getMappingType();
    public Object getMapping();
    public String getName();
}
