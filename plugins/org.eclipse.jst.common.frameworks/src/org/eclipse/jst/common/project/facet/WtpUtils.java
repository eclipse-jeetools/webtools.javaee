/******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WtpUtils 
{
    private WtpUtils() {}
    
    private static final String WTP_NATURE 
        = "org.eclipse.wst.common.modulecore.ModuleCoreNature"; //$NON-NLS-1$
    
    private static final String JEM_NATURE
        = "org.eclipse.jem.workbench.JavaEMFNature"; //$NON-NLS-1$
    
    private static final String[] NATURES = {WTP_NATURE, JEM_NATURE};
    
    public static void addNatures( final IProject project )
    
        throws CoreException
        
    {
        for (int i = 0; i < NATURES.length; i++) {
			if (!project.hasNature(NATURES[i])) {
				ProjectUtilities.addNatureToProject(project, NATURES[i]);
			}
		}
    }

    public static void addNaturestoEAR( final IProject project )
    
    throws CoreException
    
	{
	    final IProjectDescription desc = project.getDescription();
	    final String[] current = desc.getNatureIds();
	    final String[] replacement = new String[ current.length + 1 ];
	    System.arraycopy( current, 0, replacement, 0, current.length );
	    replacement[ current.length ] = WTP_NATURE;
	    desc.setNatureIds( replacement );
	    project.setDescription( desc, null );
	}  
    
    public static void removeNatures( final IProject project )
    
    throws CoreException
    
	{
        for (int i = 0; i < NATURES.length; i++) {
			if (project.hasNature(NATURES[i])) { // Remove nature if exists
				ProjectUtilities.removeNatureFromProject(project, NATURES[i]);
			}
		}
	}    
}
