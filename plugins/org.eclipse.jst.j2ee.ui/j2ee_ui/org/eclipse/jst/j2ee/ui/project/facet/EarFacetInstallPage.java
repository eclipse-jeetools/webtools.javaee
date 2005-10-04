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

package org.eclipse.jst.j2ee.ui.project.facet;

import org.eclipse.jst.j2ee.project.facet.EarFacetInstallConfig;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetInstallPage 

    extends AbstractFacetWizardPage
    
{
    private EarFacetInstallConfig config;
    private Label contentDirLabel;
    private Text contentDir;
    
    public EarFacetInstallPage() 
    {
        super( "ear.facet.install.page" );
        
        setTitle( Resources.pageTitle );
        setDescription( Resources.pageDescription );
    }
    
    public void createControl( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 1, false ) );
        
        this.contentDirLabel = new Label( composite, SWT.NONE );
        this.contentDirLabel.setText( Resources.contentDirLabel );
        this.contentDirLabel.setLayoutData( gdhfill() );
        
        this.contentDir = new Text( composite, SWT.BORDER );
        this.contentDir.setText( this.config.getContentDir() );
        this.contentDir.setLayoutData( gdhfill() );
        
        final ModifyListener modifyListener = new ModifyListener()
        {
            public void modifyText( final ModifyEvent event ) 
            {
                validate();
            }
        };
        
        this.contentDir.addModifyListener( modifyListener );
        
        setControl( composite );
    }
    
    public void setConfig( final Object config ) 
    {
        this.config = (EarFacetInstallConfig) config;
    }

    public void transferStateToConfig() 
    {
        this.config.setContentDir( this.contentDir.getText() );
    }
    
    private void validate()
    {
        boolean valid = true;

        final Display display = this.contentDirLabel.getDisplay();
        
        final String contentDirLabelText;
        final Color contentDirLabelColor;
        
        if( this.contentDir.getText().trim().length() == 0 )
        {
            contentDirLabelText = Resources.contentDirLabelInvalid;
            contentDirLabelColor = display.getSystemColor( SWT.COLOR_RED );
            
            valid = false;
        }
        else
        {
            contentDirLabelText = Resources.contentDirLabel;
            contentDirLabelColor = null;
        }
        
        this.contentDirLabel.setText( contentDirLabelText );
        this.contentDirLabel.setForeground( contentDirLabelColor );
        
        this.setPageComplete( valid );
    }
    
    private static GridData gdhfill()
    {
        return new GridData( GridData.FILL_HORIZONTAL );
    }

    private static final class Resources
    
        extends NLS
        
    {
        public static String pageTitle;
        public static String pageDescription;
        public static String contentDirLabel;
        public static String contentDirLabelInvalid;
        
        static
        {
            initializeMessages( EarFacetInstallPage.class.getName(), 
                                Resources.class );
        }
    }
    
}
