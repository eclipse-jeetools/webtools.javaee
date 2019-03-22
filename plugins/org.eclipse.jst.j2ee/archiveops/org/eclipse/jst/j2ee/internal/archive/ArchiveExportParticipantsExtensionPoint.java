/******************************************************************************
 * Copyright (c) 2005, 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.archive;

import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findExtensions;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findOptionalElement;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findRequiredAttribute;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.findRequiredElement;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.getTopLevelElements;
import static org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.instantiate;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.PLUGIN_ID;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.createErrorStatus;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.log;
import static org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.archive.IArchiveExportParticipant;
import org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint.PluginUtil.InvalidExtensionException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponent;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponentType;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponentVersion;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;
import org.osgi.framework.Bundle;

/**
 * Contains the logic for processing the <code>org.eclipse.jst.j2ee.archiveExportParticipants</code>
 * extension point. 
 * 
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class ArchiveExportParticipantsExtensionPoint
{
    public static final String EXTENSION_POINT_ID = "archiveExportParticipants"; //$NON-NLS-1$

    private static final String EL_PARTICIPANT = "participant"; //$NON-NLS-1$
    private static final String EL_RUNTIME_COMPONENT = "runtime-component"; //$NON-NLS-1$
    private static final String EL_FACTORY = "factory"; //$NON-NLS-1$
    private static final String EL_ENABLEMENT = "enablement"; //$NON-NLS-1$
    private static final String ATTR_ID = "id"; //$NON-NLS-1$
    private static final String ATTR_TYPE = "type"; //$NON-NLS-1$
    private static final String ATTR_VERSION = "version"; //$NON-NLS-1$
    private static final String ATTR_CLASS = "class"; //$NON-NLS-1$
    
    private static List<ParticipantInfo> extensions = null;
    
    public static class ParticipantInfo
    {
        private String id;
        private String pluginId = null;
        private String className = null;
        private IRuntimeComponentType runtimeComponentType = null;
        private String runtimeComponentVersionExpr = null;
        private Expression enablementCondition = null;
        
        public String getId()
        {
            return this.id;
        }
        
        public IArchiveExportParticipant loadParticipant()
        {
            try
            {
                return instantiate( this.pluginId, this.className, IArchiveExportParticipant.class );
            }
            catch( CoreException e )
            {
                log( e.getStatus() );
                return null;
            }
        }
    }
    
    public static List<ParticipantInfo> getExtensions( final IProject project,
                                                       final IRuntime runtime )
    {
        readExtensions();
        
        final List<ParticipantInfo> result = new ArrayList<ParticipantInfo>();
        
        for( ParticipantInfo partInfo : extensions )
        {
            boolean match = false;
            
            for( IRuntimeComponent rc : runtime.getRuntimeComponents() )
            {
                final IRuntimeComponentType rct = rc.getRuntimeComponentType();
                
                if( rct == partInfo.runtimeComponentType )
                {
                    final IRuntimeComponentVersion rcv = rc.getRuntimeComponentVersion();
                    
                    try
                    {
                        if( rct.getVersions( partInfo.runtimeComponentVersionExpr ).contains( rcv ) )
                        {
                            match = true;
                            break;
                        }
                    }
                    catch( CoreException e )
                    {
                        logError( -1, e.getMessage(), e );
                    }
                }
            }
            
            if( match && partInfo.enablementCondition != null )
            {
                final EvaluationContext evalContext = new EvaluationContext( null, project );
                evalContext.setAllowPluginActivation( true );
            
                try
                {
                    final EvaluationResult evalResult 
                        = partInfo.enablementCondition.evaluate( evalContext );
                    
                    if( evalResult != EvaluationResult.TRUE )
                    {
                        match = false;
                    }
                }
                catch( CoreException e )
                {
                    logError( -1, e.getMessage(), e );
                }
            }
            
            if( match )
            {
                result.add( partInfo );
            }
        }
        
        return Collections.unmodifiableList( result );
    }
                                                                             
    private static synchronized void readExtensions()
    {
        if( extensions != null )
        {
            return;
        }
        
        extensions = new ArrayList<ParticipantInfo>();
        
        for( IConfigurationElement element 
             : getTopLevelElements( findExtensions( PLUGIN_ID, EXTENSION_POINT_ID ) ) )
        {
            if( element.getName().equals( EL_PARTICIPANT ) )
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
        final ParticipantInfo info = new ParticipantInfo();

        info.pluginId = config.getContributor().getName();
        info.id = info.pluginId + "." + findRequiredAttribute( config, ATTR_ID ); //$NON-NLS-1$
        
        final IConfigurationElement elFactory = findRequiredElement( config, EL_FACTORY );
        info.className = findRequiredAttribute( elFactory, ATTR_CLASS );
        
        final IConfigurationElement elRuntimeComponent 
            = findRequiredElement( config, EL_RUNTIME_COMPONENT );
        
        final String rcTypeString = findRequiredAttribute( elRuntimeComponent, ATTR_TYPE );
        
        if( ! RuntimeManager.isRuntimeComponentTypeDefined( rcTypeString ) )
        {
            // TODO: Log the problem
            throw new InvalidExtensionException();
        }
        
        info.runtimeComponentType = RuntimeManager.getRuntimeComponentType( rcTypeString );
        info.runtimeComponentVersionExpr = elRuntimeComponent.getAttribute( ATTR_VERSION );
        
        final IConfigurationElement elEnablement = findOptionalElement( config, EL_ENABLEMENT );
        
        try
        {
            info.enablementCondition = ExpressionConverter.getDefault().perform( elEnablement );
        }
        catch( CoreException e )
        {
            logError( -1, e.getMessage(), e );
            throw new InvalidExtensionException();
        }
        
        extensions.add( info );
    }
    
    /**
     * Utility methods that are helpful for implementing extension points.
     *
     * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
     */

    public static final class PluginUtil
    {
        public static final class InvalidExtensionException

            extends Exception
        
        {
            private static final long serialVersionUID = 1L;
        }
        
        private PluginUtil() {}
        
        public static Collection<IExtension> findExtensions( final String pluginId,
                                                             final String extensionPointId )
        {
            final IExtensionRegistry registry = Platform.getExtensionRegistry();
            final IExtensionPoint point = registry.getExtensionPoint( pluginId, extensionPointId );
            
            if( point == null )
            {
                throw new RuntimeException();
            }
            
            final List<IExtension> extensions = new ArrayList<IExtension>();
            
            for( IExtension extension : point.getExtensions() )
            {
                extensions.add( extension );
            }
            
            return extensions;
        }
        
        public static Collection<IConfigurationElement> getTopLevelElements( final Collection<IExtension> extensions )
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

        public static void reportMissingAttribute( final IConfigurationElement el,
                                                   final String attribute )
        {
            final String pluginId = el.getContributor().getName();
            final String extPointId = el.getDeclaringExtension().getExtensionPointUniqueIdentifier();
            
            final String msg
                = Resources.bind( Resources.missingAttribute, pluginId, extPointId, el.getName(), 
                                  attribute );

            log( createErrorStatus( -1, msg, null ) );
        }

        public static void reportMissingElement( final IConfigurationElement el,
                                                 final String element )
        {
            final String pluginId = el.getContributor().getName();
            final String extPointId = el.getDeclaringExtension().getExtensionPointUniqueIdentifier();
            
            final String msg
                = Resources.bind( Resources.missingElement, pluginId, extPointId, el.getName(), 
                                  element );

            log( createErrorStatus( -1, msg, null ) );
        }

        public static String findRequiredAttribute( final IConfigurationElement el,
                                                    final String attribute )

            throws InvalidExtensionException

        {
            final String val = el.getAttribute( attribute );

            if( val == null )
            {
                reportMissingAttribute( el, attribute );
                throw new InvalidExtensionException();
            }

            return val;
        }

        public static IConfigurationElement findRequiredElement( final IConfigurationElement el,
                                                                 final String childElement )

            throws InvalidExtensionException

        {
            final IConfigurationElement[] children = el.getChildren( childElement );

            if( children.length == 0 )
            {
                reportMissingElement( el, childElement );
                throw new InvalidExtensionException();
            }

            return children[ 0 ];
        }

        public static IConfigurationElement findOptionalElement( final IConfigurationElement el,
                                                                 final String childElement )
        {
            final IConfigurationElement[] children = el.getChildren( childElement );

            if( children.length == 0 )
            {
                return null;
            }
            return children[ 0 ];
        }
        
        public static String getElementValue( final IConfigurationElement el,
                                              final String defaultValue )
        {
            if( el != null )
            {
                String text = el.getValue();
                
                if( text != null )
                {
                    text = text.trim();
                    
                    if( text.length() > 0 )
                    {
                        return text;
                    }
                }
            }
            
            return defaultValue;
        }
        
        @SuppressWarnings( "unchecked" )
        public static <T> T instantiate( final String pluginId,
                                         final String clname,
                                         final Class<T> interfc )
        
            throws CoreException
            
        {
            final Bundle bundle = Platform.getBundle( pluginId );
            
            final Object obj;
            
            try
            {
                final Class cl = bundle.loadClass( clname );
                obj = cl.newInstance();
            }
            catch( Exception e )
            {
                final String msg = NLS.bind( Resources.failedToCreate, clname );
                throw new CoreException( createErrorStatus( -1, msg, e ) );
            }
            
            if( ! interfc.isAssignableFrom( obj.getClass() ) )
            {
                final String msg
                    = NLS.bind( Resources.doesNotImplement, clname, interfc.getClass().getName() );
                
                throw new CoreException( createErrorStatus( -1, msg, null ) );
            }
            
            return (T) obj;
        }
        
        private static final class Resources

            extends NLS
        
        {
            public static String missingAttribute;
            public static String missingElement;
            public static String failedToCreate;
            public static String doesNotImplement;
        
            static
            {
                initializeMessages( PluginUtil.class.getName(), Resources.class );
            }
            
            public static String bind( final String message,
                                       final String arg1,
                                       final String arg2,
                                       final String arg3,
                                       final String arg4 )
            {
                return bind( message, new Object[] { arg1, arg2, arg3, arg4 } );
            }
        }
        
    }
    
}
