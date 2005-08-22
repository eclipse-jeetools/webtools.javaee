/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.frameworks;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;

public final class CommonFrameworksPlugin 

    extends WTPPlugin

{
    public static final String PLUGIN_ID = "org.eclipse.jst.common.frameworks"; //$NON-NLS-1$
    
    private static final CommonFrameworksPlugin inst
        = new CommonFrameworksPlugin();

    /**
     * Get the plugin singleton.
     */
    
    public static CommonFrameworksPlugin getDefault() 
    {
        return inst;
    }
    
    public String getPluginID() 
    {
        return PLUGIN_ID;
    }
    
    public static void log( final Exception e )
    {
        final ILog log = CommonFrameworksPlugin.getDefault().getLog();
        final String msg = "Encountered an unexpected exception."; //$NON-NLS-1$
        
        log.log( new Status( IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e ) );
    }
    
}
