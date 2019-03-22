/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.frameworks;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.eclipse.core.runtime.Platform;
import java.lang.Throwable;
import org.eclipse.core.runtime.CoreException;

public final class CommonFrameworksPlugin 

    extends WTPPlugin

{
    public static final String PLUGIN_ID = "org.eclipse.jst.common.frameworks"; //$NON-NLS-1$
    public static final String DEFAULT_SOURCE_FOLDER = "defaultSource"; //$NON-NLS-1$
    public static final String OUTPUT_FOLDER = "outputFolder"; //$NON-NLS-1$
   
    private static CommonFrameworksPlugin inst;

    /**
     * Get the plugin singleton.
     */
    
    public static CommonFrameworksPlugin getDefault() 
    {
        return inst;
    }
    
    public CommonFrameworksPlugin() {
    	super();
		if (inst == null)
			inst = this;
	}
    @Override
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

	public static IStatus createStatus(int severity, String message, Throwable exception) {
		return new Status(severity, PLUGIN_ID, message, exception);
	}

	public static IStatus createStatus(int severity, String message) {
		return createStatus(severity, message, null);
	}

	public static void logError(Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, exception.getMessage(), exception));
	}

	public static void logError(CoreException exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( exception.getStatus() );
	}
    
}
