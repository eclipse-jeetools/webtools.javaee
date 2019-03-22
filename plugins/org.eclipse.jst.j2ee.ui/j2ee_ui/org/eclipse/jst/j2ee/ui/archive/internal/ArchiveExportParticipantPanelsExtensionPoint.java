/******************************************************************************
 * Copyright (c) 2005, 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.archive.internal;

import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findExtensions;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findRequiredAttribute;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.getTopLevelElements;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.instantiate;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin.PLUGIN_ID;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin.log;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.InvalidExtensionException;
import org.eclipse.jst.j2ee.ui.archive.IArchiveExportParticipantPanelFactory;

/**
 * Contains the logic for processing the <code>org.eclipse.jst.j2ee.ui.archiveExportParticipantPanels</code>
 * extension point. 
 * 
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class ArchiveExportParticipantPanelsExtensionPoint
{
    public static final String EXTENSION_POINT_ID = "archiveExportParticipantPanels"; //$NON-NLS-1$

    private static final String EL_PANEL_FACTORY = "panel-factory"; //$NON-NLS-1$
    private static final String ATTR_ARCHIVE_EXPORT_PARTICIPANT_ID = "archiveExportParticipantId"; //$NON-NLS-1$
    private static final String ATTR_CLASS = "class"; //$NON-NLS-1$
    
    private static Map<String,PanelFactoryInfo> extensions = null;
    
    public static class PanelFactoryInfo
    {
        private String archiveExportParticipantId;
        private String pluginId = null;
        private String className = null;
        
        public String getArchiveExportExtensionId()
        {
            return this.archiveExportParticipantId;
        }
        
        public IArchiveExportParticipantPanelFactory loadPanelFactory()
        {
            try
            {
                return instantiate( this.pluginId, this.className, 
                                    IArchiveExportParticipantPanelFactory.class );
            }
            catch( CoreException e )
            {
                log( e.getStatus() );
                return null;
            }
        }
    }
    
    public static PanelFactoryInfo getExtension( final String archiveExportParticipantId )
    {
        readExtensions();
        
        return extensions.get( archiveExportParticipantId );
    }
                                                                             
    private static synchronized void readExtensions()
    {
        if( extensions != null )
        {
            return;
        }
        
        extensions = new HashMap<String,PanelFactoryInfo>();
        
        for( IConfigurationElement element 
             : getTopLevelElements( findExtensions( PLUGIN_ID, EXTENSION_POINT_ID ) ) )
        {
            if( element.getName().equals( EL_PANEL_FACTORY ) )
            {
                try
                {
                    readExtension( element );
                }
                catch( InvalidExtensionException e )
                {
                    // Continue. The problem has been reported to the user via the log.
                }
            }
        }
    }

    private static void readExtension( final IConfigurationElement config )
    
        throws InvalidExtensionException
        
    {
        final PanelFactoryInfo ext = new PanelFactoryInfo();

        ext.archiveExportParticipantId = findRequiredAttribute( config, ATTR_ARCHIVE_EXPORT_PARTICIPANT_ID );
        ext.pluginId = config.getContributor().getName();
        ext.className = findRequiredAttribute( config, ATTR_CLASS );
        
        extensions.put( ext.archiveExportParticipantId, ext );
    }
    
}
