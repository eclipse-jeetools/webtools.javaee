/******************************************************************************
 * Copyright (c) 2008 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.common.project.facet.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class DataModelToJavaFacetInstallConfigAdapter

    implements IAdapterFactory
    
{
    private static final Class[] ADAPTER_TYPES = { JavaFacetInstallConfig.class };
    
    public Object getAdapter( final Object adaptable, 
                              final Class adapterType )
    {
        if( adapterType == JavaFacetInstallConfig.class )
        {
            final IDataModel dm = (IDataModel) adaptable;
            
            return dm.getProperty( IJavaFacetInstallDataModelProperties.JAVA_FACET_INSTALL_CONFIG );
        }

        return null;
    }

    public Class[] getAdapterList()
    {
        return ADAPTER_TYPES;
    }

}
