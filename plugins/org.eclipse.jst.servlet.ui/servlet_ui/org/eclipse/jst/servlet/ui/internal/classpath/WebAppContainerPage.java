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

package org.eclipse.jst.servlet.ui.internal.classpath;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jdt.ui.wizards.NewElementWizardPage;
import org.eclipse.jst.j2ee.internal.web.classpath.WebAppContainer;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public class WebAppContainerPage 

    extends NewElementWizardPage 
    implements IClasspathContainerPage, IClasspathContainerPageExtension 
    
{
    private IProject project;
    private IJavaProject jproject;
    private String component;
    private Label componentLabel;
    private Combo componentCombo;
    
    public WebAppContainerPage() 
    {
        super( WEBUIMessages.WEB_CONT_NAME ); //$NON-NLS-1$
        
        setTitle( WEBUIMessages.WEB_CONT_PAGE_TITLE ); //$NON-NLS-1$
        setDescription( WEBUIMessages.WEB_CONT_PAGE_DESCRIPTION ); //$NON-NLS-1$
        
        // TODO: Replace with a custom image.
        setImageDescriptor( JavaPluginImages.DESC_WIZBAN_ADD_LIBRARY ); //$NON-NLS-1$
    }
    
    public void initialize( final IJavaProject aJproject,
                            final IClasspathEntry[] cp )
    {
        this.jproject = aJproject;
        this.project = aJproject.getProject();
    }
    
    public IClasspathEntry getSelection() 
    {
        final int sel = this.componentCombo.getSelectionIndex();
        final String cmp = this.componentCombo.getItem( sel ).trim();
        
        return WebAppContainer.convert( cmp );
    }

    public void setSelection( final IClasspathEntry cpe ) 
    {
        if( cpe == null )
        {
            return;
        }
        
        this.component = WebAppContainer.convert( cpe );
    }
    
    public void createControl( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 1, false ) );
        
        this.componentLabel = new Label( composite, SWT.NONE );
        this.componentLabel.setText( WEBUIMessages.WEB_CONT_PAGE_COMP_LABEL ); //$NON-NLS-1$
        
        this.componentCombo = new Combo( composite, SWT.READ_ONLY );
        
        final List components = components();
        
        for( int i = 0, n = components.size(); i < n; i++  )
        {
            final String cmp = (String) components.get( i );
            this.componentCombo.add( cmp + "        " ); //$NON-NLS-1$
            
            if( this.component != null && this.component.equals( cmp ) )
            {
                this.componentCombo.select( i );
            }
        }
        
        setControl( composite );
    }
    
    public boolean finish() 
    {
        return this.componentCombo.getSelectionIndex() != -1;
    }
    
    private List components()
    {
        final List used = new ArrayList();
        
        try
        {
            final IClasspathEntry[] cp = this.jproject.getRawClasspath();
            
            for( int i = 0; i < cp.length; i++ )
            {
                final IClasspathEntry cpe = cp[ i ];
                
                if( WebAppContainer.check( cpe ) )
                {
                    used.add( WebAppContainer.convert( cpe ) );
                }
            }
        }
        catch( JavaModelException e )
        {
            // TODO: Handle this.
        }
        
        final List res = new ArrayList();
        
        final IVirtualComponent component = ComponentCore.createComponent(project);
        
       
            final IVirtualComponent vc = component;
            final String cmp = vc.getName();
            
            // TODO: Re-enable this check when getComponentTypeId() is implemented.
            //if( vc.getComponentTypeId().equals( "jst.web" ) )
            if( ( this.component != null && this.component.equals( cmp ) ) ||
                ! used.contains( vc.getName() ) )
            {
                res.add( vc.getName() );
            }
        
        
        return res;
    }
}
