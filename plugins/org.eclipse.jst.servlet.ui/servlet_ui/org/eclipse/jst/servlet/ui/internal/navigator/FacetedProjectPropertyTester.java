/*******************************************************************************
 * Copyright (c) 2010 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.navigator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

public class FacetedProjectPropertyTester extends PropertyTester

{
    public boolean test( final Object receiver, 
                         final String property, 
                         final Object[] args, 
                         final Object value )
    {
        try
        {
            if( ! ( receiver instanceof ICompressedNode ) )
            {
                return false;
            }
            
            IProject pj = null;
            if (receiver instanceof CompressedJavaProject) {
            	pj = ((CompressedJavaProject) receiver).getProject();
            } else if (receiver instanceof CompressedJavaLibraries) {
            	pj = ((CompressedJavaLibraries) receiver).getCompressedProject().getProject();
            }
            
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
            //
        }
            
        return false;
    }
}
