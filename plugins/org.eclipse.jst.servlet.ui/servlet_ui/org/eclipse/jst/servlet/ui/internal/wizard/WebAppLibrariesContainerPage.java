/******************************************************************************
 * Copyright (c) 2006, 2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jdt.ui.wizards.NewElementWizardPage;
import org.eclipse.jst.j2ee.internal.web.classpath.WebAppLibrariesContainer;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public class WebAppLibrariesContainerPage 

    extends NewElementWizardPage 
    implements IClasspathContainerPage, IClasspathContainerPageExtension 
    
{
    private IProject ownerProject;
    private String libsProjectName;
    private Combo projectsCombo;
    
    public WebAppLibrariesContainerPage() 
    {
        super( "WebAppLibrariesContainerPage" ); //$NON-NLS-1$
        
        setTitle( Resources.pageTitle );
        setDescription( Resources.pageDescription );
    }
    
    @Override
	public IClasspathEntry getSelection() 
    {
        IPath path = new Path( WebAppLibrariesContainer.CONTAINER_ID );
        
        final int index = this.projectsCombo.getSelectionIndex();
        if(index != -1){
	        final String selectedProjectName = this.projectsCombo.getItem( index );
	        
	        if( this.ownerProject == null || 
	            ! selectedProjectName.equals( this.ownerProject.getName() ) )
	        {
	            path = path.append( selectedProjectName );
	        }
        }
        
        return JavaCore.newContainerEntry( path );
    }

    @Override
	public void setSelection( final IClasspathEntry cpentry ) 
    {
        final IPath path = cpentry == null ? null : cpentry.getPath();
        
        if( path == null || path.segmentCount() == 1 )
        {
            if( this.ownerProject != null )
            {
                this.libsProjectName = this.ownerProject.getName();
            }
        }
        else
        {
            this.libsProjectName = path.segment( 1 );
        }
    }
    
    @Override
	public void createControl( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 2, false ) );
        
        final Label label = new Label( composite, SWT.NONE );
        label.setText( Resources.projectsLabel );
        
        final String[] webProjects = getWebProjects();
        
        this.projectsCombo = new Combo( composite, SWT.READ_ONLY );
        this.projectsCombo.setItems( webProjects );
        
        final int index;
        
        if( this.ownerProject != null )
        {
            index = indexOf( webProjects, this.libsProjectName );
        }
        else
        {
            if( this.projectsCombo.getItemCount() > 0 )
            {
                index = 0;
            }
            else
            {
                index = -1;
            }
        }
        
        if( index != -1 )
        {
            this.projectsCombo.select( index );
        }
        
        final GridData gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.minimumWidth = 100;
        
        this.projectsCombo.setLayoutData( gd );
        
        setControl( composite );
    }
    
    @Override
	public boolean finish() 
    {
        return true;
    }
    
    @Override
	public void initialize( final IJavaProject project, 
                            final IClasspathEntry[] currentEntries ) 
    {
        this.ownerProject = ( project == null ? null : project.getProject() );
    }
    
    private static String[] getWebProjects()
    {
        try
        {
            final Set projects
                = ProjectFacetsManager.getFacetedProjects( WebFacetUtils.WEB_FACET );
            
            final String[] names = new String[ projects.size() ];
            int i = 0;
            
            for( Iterator itr = projects.iterator(); itr.hasNext(); i++ )
            {
                final IFacetedProject fproj = (IFacetedProject) itr.next();
                names[ i ] = fproj.getProject().getName();
            }
            
            Arrays.sort( names );
            
            return names;
        }
        catch( CoreException e )
        {
            ServletUIPlugin.log( e.getStatus() );
            return new String[ 0 ];
        }
    }
    
    private static int indexOf( final String[] array,
                                final String str )
    {
        for( int i = 0; i < array.length; i++ )
        {
            if( array[ i ].equals( str ) )
            {
                return i;
            }
        }
        
        return -1;
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String pageTitle;
        public static String pageDescription;
        public static String projectsLabel;
        
        static
        {
            initializeMessages( WebAppLibrariesContainerPage.class.getName(), 
                                Resources.class );
        }
    }
    
}
