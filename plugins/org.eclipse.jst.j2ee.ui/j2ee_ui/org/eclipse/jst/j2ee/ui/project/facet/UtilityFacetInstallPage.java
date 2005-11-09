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

import org.eclipse.jst.j2ee.project.facet.IUtilityFacetInstallDataModelProperties;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class UtilityFacetInstallPage 

    extends AbstractFacetWizardPage
    
{
    private IDataModel config;
    private EarSelectionPanelOld earPanel;
    
    public UtilityFacetInstallPage() 
    {
        super( "utility.facet.install.page" );
        
        setTitle( Resources.pageTitle );
        setDescription( Resources.pageDescription );
    }
    
    public void createControl( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 1, false ) );
        
        this.earPanel = new EarSelectionPanelOld( composite, SWT.NONE );
        this.earPanel.setLayoutData( gdhfill() );
        
        this.earPanel.addListener( new Listener()
        {
            public void handleEvent( final Event event ) 
            {
                config.setStringProperty(IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME,earPanel.getEarProjectName() );
                validate();
            }
        } );
        
        setControl( composite );
    }
    
    public void setConfig( final Object config ) 
    {
        this.config = (IDataModel) config;
    }

    private void validate()
    {
        boolean valid = true;
        
        if( this.earPanel.getAddToEar() && 
            this.earPanel.getEarProjectName() == null )
        {
            valid = false;
        }
        
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
        
        static
        {
            initializeMessages( UtilityFacetInstallPage.class.getName(), 
                                Resources.class );
        }
    }
    
}

