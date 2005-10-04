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

package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetInstallDelegate 

    implements IDelegate
    
{
    public void execute( final IProject project, 
                         final IProjectFacetVersion fv,
                         final Object cfg,
                         final IProgressMonitor monitor )
    
        throws CoreException
        
    {
        if( monitor != null )
        {
            monitor.beginTask( "", 1 );
        }
        
        try
        {
            final EarFacetInstallConfig config 
                = cfg != null 
                  ? (EarFacetInstallConfig) cfg 
                  : new EarFacetInstallConfig();

            // Add WTP natures.
          
            WtpUtils.addNatures( project );
          
            // Create the directory structure.
            
            final IWorkspace ws = ResourcesPlugin.getWorkspace();
            final IPath pjpath = project.getFullPath();
            
            final IPath earcontent = pjpath.append( config.getContentDir() );
            mkdirs( ws.getRoot().getFolder( earcontent ) );

            final IPath metainf = earcontent.append( "META-INF" );
            mkdirs( ws.getRoot().getFolder( metainf ) );
            
            // Setup the flexible project structure. 
            
            final IVirtualComponent c
                = ComponentCore.createComponent( project, project.getName() );

            c.create( 0, null );

            final ComponentType ctype 
                = ComponentcoreFactory.eINSTANCE.createComponentType();
            
            ctype.setComponentTypeId( "jst.ear" );
            ctype.setVersion( fv.getVersionString() );
            
            final StructureEdit edit 
                = StructureEdit.getStructureEditForWrite( project );
            
            try
            {
                StructureEdit.setComponentType( c, ctype );
                edit.saveIfNecessary( null );
            }
            finally
            {
                edit.dispose();
            }
            
            final IVirtualFolder approot = c.getRootFolder();
            
            approot.createLink( new Path( "/" + config.getContentDir() ), 
                                0, null );
            
            // Create the deployment descriptor (application.xml).
            
            
            final EARArtifactEdit eardd
                = EARArtifactEdit.getEARArtifactEditForWrite( project );
            
            try
            {
                eardd.createModelRoot( 14 );
                eardd.saveIfNecessary( null );
            }
            finally
            {
                eardd.dispose();
            }
            
            if( monitor != null )
            {
                monitor.worked( 1 );
            }
        }
        finally
        {
            if( monitor != null )
            {
                monitor.done();
            }
        }
    }
    
    private static void mkdirs( final IFolder folder )
    
        throws CoreException
        
    {
        if( ! folder.exists() )
        {
            if( folder.getParent() instanceof IFolder )
            {
                mkdirs( (IFolder) folder.getParent() );
            }
            
            folder.create( true, true, null );
        }
    }

}
