package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;

public class FacetedProjectAdapterFactory implements IAdapterFactory {


    private static final Class[] ADAPTER_TYPES = { IActionFilter.class };
    
    public Object getAdapter( final Object adaptable, 
                              final Class adapterType )
    {
        if( adapterType == IActionFilter.class )
        {
            return new FacetedProjectActionFilter();
        }
        else
        {
            return null;
        }
    }

    public Class[] getAdapterList()
    {
        return ADAPTER_TYPES;
    }
    
    
}
