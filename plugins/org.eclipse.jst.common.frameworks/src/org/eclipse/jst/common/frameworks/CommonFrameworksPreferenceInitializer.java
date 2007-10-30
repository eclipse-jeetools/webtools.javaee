/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.frameworks;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class CommonFrameworksPreferenceInitializer extends
		AbstractPreferenceInitializer {

    private static final String DEFAULT_SOURCE_FOLDER_VALUE = "src"; //$NON-NLS-1$
    private static final String DEFAULT_OUTPUT_FOLDER_VALUE = "build/classes"; //$NON-NLS-1$

    public CommonFrameworksPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(CommonFrameworksPlugin.getDefault().getBundle().getSymbolicName());
		
		node.put(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER, getDefaultSourceFolder());
		node.put(CommonFrameworksPlugin.OUTPUT_FOLDER, getDefaultOutputFolder());
	}
	
	private static String getDefaultSourceFolder()
	{
	    String res = getProductProperty( CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER );
	    
	    if( res == null )
	    {
	        res = DEFAULT_SOURCE_FOLDER_VALUE;
	    }
	    
	    return res;
	}
	
	private static String getDefaultOutputFolder()
	{
        String res = getProductProperty( CommonFrameworksPlugin.OUTPUT_FOLDER );
        
        if( res == null )
        {
            res = DEFAULT_OUTPUT_FOLDER_VALUE;
        }
        
        return res;
	}
	
	private static String getProductProperty( final String propName )
	{
        String value = null;
        
        if( Platform.getProduct() != null )
        {
            value = Platform.getProduct().getProperty( propName );
        }
        
        return value;
	}
	
}
