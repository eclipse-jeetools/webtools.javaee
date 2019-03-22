/******************************************************************************
 * Copyright (c) 2008 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.common.project.facet.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetInstallConfigToDataModelAdapter

    implements IAdapterFactory
    
{
    private static final Class[] ADAPTER_TYPES = { IDataModel.class };
    
    public Object getAdapter( final Object adaptable, 
                              final Class adapterType )
    {
        if( adapterType == IDataModel.class )
        {
            final JavaFacetInstallDataModelProvider provider 
                = new JavaFacetInstallDataModelProvider( (JavaFacetInstallConfig) adaptable );
            
            return DataModelFactory.createDataModel( provider );
        }

        return null;
    }

    public Class[] getAdapterList()
    {
        return ADAPTER_TYPES;
    }

}
