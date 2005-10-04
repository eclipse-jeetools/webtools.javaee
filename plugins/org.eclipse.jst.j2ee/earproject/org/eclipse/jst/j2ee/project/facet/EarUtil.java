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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarUtil 
{
    public static void addModuleReference( final IProject earproj,
                                           final IProject moduleproj,
                                           final Module module )
    
        throws CoreException
        
    {
        addProjectReference( earproj, moduleproj );
        addComponentReference( earproj, moduleproj );
        
        if( module != null )
        {
            addDeploymentDescriptorEntry( earproj, module );
        }
    }
    
    private static void addProjectReference( final IProject project,
                                             final IProject referenced )
    
        throws CoreException
        
    {
        final IProject[] oldrefs = project.getReferencedProjects();
        final IProject[] refs = new IProject[ oldrefs.length + 1 ];
        
        System.arraycopy( oldrefs, 0, refs, 0, oldrefs.length );
        refs[ oldrefs.length ] = referenced;
        
        final IProjectDescription desc = project.getDescription();
        desc.setReferencedProjects( refs );
        
        project.setDescription( desc, null );        
    }
    
    private static void addComponentReference( final IProject project,
                                               final IProject referenced )
    {
        final IVirtualComponent comp1
            = ComponentCore.createComponent( project, project.getName() );
        
        final IVirtualComponent comp2
            = ComponentCore.createComponent( referenced, referenced.getName() );
        
        final IVirtualReference[] oldrefs = comp1.getReferences();
        
        final IVirtualReference[] refs 
            = new IVirtualReference[ oldrefs.length + 1 ];
        
        System.arraycopy( oldrefs, 0, refs, 0, oldrefs.length );
        
        refs[ oldrefs.length ] 
            = ComponentCore.createReference( comp1, comp2 );
        
        comp1.setReferences( refs );
    }
    
    private static void addDeploymentDescriptorEntry( final IProject earproj,
                                                      final Module module )
    {
        
        final EARArtifactEdit eardd
            = EARArtifactEdit.getEARArtifactEditForWrite( earproj );
                
        try
        {
            final Application app = eardd.getApplication();
            app.getModules().add( module );
            eardd.saveIfNecessary( null );
        }
        finally
        {
            eardd.dispose();
        }
    }

}
