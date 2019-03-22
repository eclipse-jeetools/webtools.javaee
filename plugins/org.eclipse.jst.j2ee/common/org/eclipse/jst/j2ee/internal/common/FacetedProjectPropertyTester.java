/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.internal.FacetCorePlugin;

public final class FacetedProjectPropertyTester extends PropertyTester

{
@Override
public boolean test( final Object receiver, 
                     final String property, 
                     final Object[] args, 
                     final Object value )
{
    try
    {
        if( ! ( receiver instanceof ICompilationUnit ) )
        {
            return false;
        }
        
        final IProject pj = ( (ICompilationUnit ) receiver ).getJavaProject().getProject();
        
        if( pj == null )
        {
            return false;
        }
        
        final String val = (String) value;
        final int colon = val.indexOf( ':' );
        
        final String fid;
        final String vexpr;
        
        if( colon == -1 || colon == val.length() - 1 )
        {
            fid = val;
            vexpr = null;
        }
        else
        {
            fid = val.substring( 0, colon );
            vexpr = val.substring( colon + 1 );
        }
        
        return FacetedProjectFramework.hasProjectFacet( pj, fid, vexpr );
    }
    catch( CoreException e )
    {
        FacetCorePlugin.log( e.getStatus() );
    }
        
    return false;
}

}

