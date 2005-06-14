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

package org.eclipse.jst.common.jdt.internal.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public abstract class FlexibleProjectContainerInitializer 

    extends ClasspathContainerInitializer

{
    private static final ClasspathDecorationsManager decorations
        = FlexibleProjectContainer.getDecorationsManager();
    
    public boolean canUpdateClasspathContainer( final IPath containerPath, 
                                                final IJavaProject project) 
    {
        return true; 
    }

    public void requestClasspathContainerUpdate( final IPath containerPath, 
                                                 final IJavaProject project, 
                                                 final IClasspathContainer sg ) 
    
        throws CoreException
        
    {
        final String cid = containerPath.toString();
        final IClasspathEntry[] entries = sg.getClasspathEntries();
        
        decorations.clearAllDecorations( cid );
        
        for( int i = 0; i < entries.length; i++ )
        {
            final IClasspathEntry entry = entries[ i ];
            
            final IPath srcpath = entry.getSourceAttachmentPath();
            final IPath srcrootpath = entry.getSourceAttachmentRootPath();
            final IClasspathAttribute[] attrs = entry.getExtraAttributes();
            
            if( srcpath != null || attrs.length > 0 )
            {
                final String eid = entry.getPath().toString();
                final ClasspathDecorations dec = new ClasspathDecorations();
                
                dec.setSourceAttachmentPath( srcpath );
                dec.setSourceAttachmentRootPath( srcrootpath );
                dec.setExtraAttributes( attrs );
                
                decorations.setDecorations( cid, eid, dec );
            }
        }
        
        decorations.save();
        
        final IClasspathContainer container
            = JavaCore.getClasspathContainer( containerPath, project );
        
        ( (FlexibleProjectContainer) container ).refresh();
    }

}
