package org.eclipse.jst.j2ee.internal.provider;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
            if( ! ( receiver instanceof J2EEItemProvider ) )
            {
                return false;
            }
            
            IResource resource = ((J2EEItemProvider) receiver).getAssociatedFile();
            final IProject pj = resource.getProject();
            
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
