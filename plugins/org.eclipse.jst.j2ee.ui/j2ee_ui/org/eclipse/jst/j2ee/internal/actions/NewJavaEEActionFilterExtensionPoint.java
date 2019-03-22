/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.actions;

import static org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin.PLUGIN_ID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Contains the logic for processing the <code>org.eclipse.jst.j2ee.ui.newjavaeeactionfilter</code>
 * extension point. 
 * 
 */

public final class NewJavaEEActionFilterExtensionPoint
{
    public static final String EXTENSION_POINT_ID = "newJavaEEActionFilter"; //$NON-NLS-1$
    public static final String New_JavaEE_Wizard_Id = "newJavaEEWizardId"; //$NON-NLS-1$
    public static final String Filter_Wizard= "filterWizard"; //$NON-NLS-1$
    
    private static Set<String> extensions = null;
    
    public static boolean hasFilter( final String newjavaeewizardId )
    {
        readExtensions();
        
        return extensions.contains(newjavaeewizardId);
    }
                                                                             
    private static synchronized void readExtensions()
    {
        if( extensions != null )
        {
            return;
        }
        
        extensions = new HashSet<String>();
        
        for( IConfigurationElement element 
             : getTopLevelElements( findExtensions( PLUGIN_ID, EXTENSION_POINT_ID ) ) )
        {
            if( element.getName().equals( New_JavaEE_Wizard_Id ) )
            {
               readExtension( element );
            }
        }
    }

    private static void readExtension( final IConfigurationElement config )
        
    {
    	String newActionId;
        

    	newActionId = findRequiredAttribute( config, Filter_Wizard );
        
        
        extensions.add( newActionId );
    }
    
    private static Collection<IConfigurationElement> getTopLevelElements( final Collection<IExtension> extensions )
    {
        final List<IConfigurationElement> elements = new ArrayList<IConfigurationElement>();
        
        for( IExtension extension : extensions )
        {
            for( IConfigurationElement element : extension.getConfigurationElements() )
            {
                elements.add( element );
            }
        }
        
        return elements;
    }
    
	private static Collection<IExtension> findExtensions(final String pluginId, final String extensionPointId) {
		final IExtensionRegistry registry = Platform.getExtensionRegistry();
		final IExtensionPoint point = registry.getExtensionPoint(pluginId, extensionPointId);

		if (point == null) {
			throw new RuntimeException();
		}

		final List<IExtension> extensions = new ArrayList<IExtension>();

		for (IExtension extension : point.getExtensions()) {
			extensions.add(extension);
		}

		return extensions;
	}

	private static String findRequiredAttribute(final IConfigurationElement el, final String attribute)

	{
		final String val = el.getAttribute(attribute);

		return val;
	}
    
}
