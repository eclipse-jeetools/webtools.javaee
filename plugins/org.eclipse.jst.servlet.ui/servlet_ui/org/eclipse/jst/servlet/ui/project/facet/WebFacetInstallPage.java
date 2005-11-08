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

package org.eclipse.jst.servlet.ui.project.facet;

import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebFacetInstallPage 

    extends AbstractFacetWizardPage
    
{
    private IDataModel config;
    private EarSelectionPanel earPanel;
    private Label contextRootLabel;
    private Text contextRoot;
    private Label contentDirLabel;
    private Text contentDir;
    private Button createWebInfSrc;
    
    public WebFacetInstallPage() 
    {
        super( "web.facet.install.page" );
        
        setTitle( Resources.pageTitle );
        setDescription( Resources.pageDescription );
    }
    
    public void createControl( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 1, false ) );
        
        this.earPanel = new EarSelectionPanel( composite, SWT.NONE );
        this.earPanel.setLayoutData( gdhfill() );
        
        this.earPanel.addListener( new Listener()
        {
            public void handleEvent( final Event event ) 
            {
                config.setStringProperty(IWebFacetInstallDataModelProperties.EAR_PROJECT_NAME,earPanel.getEarProjectName() );
                validate();
            }
        } );
        
        this.contextRootLabel = new Label( composite, SWT.NONE );
        this.contextRootLabel.setText( Resources.contextRootLabel );
        this.contextRootLabel.setLayoutData( gdhfill() );
        
        this.contextRoot = new Text( composite, SWT.BORDER );
        this.contextRoot.setText( "" );
        this.contextRoot.setLayoutData( gdhfill() );
        this.contextRoot.setData( "label", this.contextRootLabel );

        this.contextRoot.addModifyListener( new ModifyListener()
        {
            public void modifyText( final ModifyEvent event ) 
            {
                if( ! config.getStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT).equals( contextRoot.getText() ) )
                {
                    config.setStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT, contextRoot.getText() );
                }
                
                validate();
            }
        } );
        
        this.contentDirLabel = new Label( composite, SWT.NONE );
        this.contentDirLabel.setText( Resources.contentDirLabel );
        this.contentDirLabel.setLayoutData( gdhfill() );
        
        this.contentDir = new Text( composite, SWT.BORDER );
        this.contentDir.setText( config.getStringProperty(IWebFacetInstallDataModelProperties.CONTENT_DIR));
        this.contentDir.setLayoutData( gdhfill() );
        this.contentDir.setData( "label", this.contentDirLabel );

        this.contentDir.addModifyListener( new ModifyListener()
        {
            public void modifyText( final ModifyEvent event ) 
            {
                config.setStringProperty(IWebFacetInstallDataModelProperties.CONTENT_DIR, contentDir.getText() );
                validate();
            }
        } );
        
        this.createWebInfSrc = new Button( composite, SWT.CHECK );
        this.createWebInfSrc.setText( Resources.createWebinfSrcLabel );
        
        this.createWebInfSrc.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( final SelectionEvent e )
            {
                config.setBooleanProperty(IWebFacetInstallDataModelProperties.CREATE_WEB_INF_SRC,createWebInfSrc.getSelection() );
            }
        } );
        
        setControl( composite );
    }
    
    public void setConfig( final Object config ) 
    {
        this.config = (IDataModel) config;
    }

    public void transferStateToConfig() 
    {
    }
    
    public void setVisible( final boolean visible )
    {
        if( visible )
        {
            this.contextRoot.setText( this.config.getStringProperty( IWebFacetInstallDataModelProperties.CONTEXT_ROOT ) );
        }
        
        super.setVisible( visible );
    }
    
    private void validate()
    {
        boolean valid = true;
        
        if( ! validateNotEmpty( this.contextRoot, Resources.contextRootLabel, Resources.contentDirLabelInvalid ) ||
            ! validateNotEmpty( this.contentDir, Resources.contentDirLabel, Resources.contentDirLabelInvalid ) )
        {
            valid = false;
        }
        
        if( this.earPanel.getAddToEar() && 
            this.earPanel.getEarProjectName() == null )
        {
            valid = false;
        }
        
        this.setPageComplete( valid );
    }
    
    private static boolean validateNotEmpty( final Text textbox,
                                             final String validLabel,
                                             final String invalidLabel )
    {
        final Label label = (Label) textbox.getData( "label" );
        
        final boolean valid;
        final String text;
        final Color color;
        
        if( textbox.getText().trim().length() == 0 )
        {
            text = invalidLabel;
            color = label.getDisplay().getSystemColor( SWT.COLOR_RED );
            
            valid = false;
        }
        else
        {
            text = validLabel;
            color = null;
            
            valid = true;
        }
        
        label.setText( text );
        label.setForeground( color );
        
        return valid;
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
        public static String contextRootLabel;
        public static String contextRootLabelInvalid;
        public static String contentDirLabel;
        public static String contentDirLabelInvalid;
        public static String createWebinfSrcLabel;
        
        static
        {
            initializeMessages( WebFacetInstallPage.class.getName(), 
                                Resources.class );
        }
    }
    
}

