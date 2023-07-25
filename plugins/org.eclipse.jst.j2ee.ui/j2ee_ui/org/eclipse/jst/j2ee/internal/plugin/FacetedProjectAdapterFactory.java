/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;

public class FacetedProjectAdapterFactory implements IAdapterFactory {


    private static final Class[] ADAPTER_TYPES = { IActionFilter.class };
    
    @Override
	public Object getAdapter( final Object adaptable, 
                              final Class adapterType )
    {
        if( adapterType == IActionFilter.class )
        {
            return new FacetedProjectActionFilter();
        }
        return null;
    }

    @Override
	public Class[] getAdapterList()
    {
        return ADAPTER_TYPES;
    }
    
    
}
