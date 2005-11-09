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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.project.facet.ui.IFacetWizardPage;
import org.eclipse.wst.common.project.facet.ui.IWizardContext;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebFacetInstallPage extends DataModelWizardPage implements IWebFacetInstallDataModelProperties, IFacetWizardPage
    
{
    private EarSelectionPanel earPanel;
    private Label contextRootLabel;
    private Text contextRoot;
    private Label contentDirLabel;
    private Text contentDir;
    private Button createWebInfSrc;
    
    public WebFacetInstallPage() 
    {
    	//TODO figure out a better way to do this without compromising the IDataModelWizard framework.
    	super(DataModelFactory.createDataModel(new AbstractDataModelProvider(){}), "web.facet.install.page"); //$NON-NLS-1$
        setTitle( Resources.pageTitle );
        setDescription( Resources.pageDescription );
    }
    
    protected Composite createTopLevelComposite( final Composite parent ) 
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout( 1, false ) );

        this.earPanel = new EarSelectionPanel( model, composite, SWT.NONE );
        this.earPanel.setLayoutData( gdhfill() );
        
        this.contextRootLabel = new Label( composite, SWT.NONE );
        this.contextRootLabel.setText( Resources.contextRootLabel );
        this.contextRootLabel.setLayoutData( gdhfill() );
        
        this.contextRoot = new Text( composite, SWT.BORDER );
        this.contextRoot.setLayoutData( gdhfill() );
        this.contextRoot.setData( "label", this.contextRootLabel ); //$NON-NLS-1$
        synchHelper.synchText(contextRoot, CONTEXT_ROOT, new Control [] { contextRootLabel });
        
        this.contentDirLabel = new Label( composite, SWT.NONE );
        this.contentDirLabel.setText( Resources.contentDirLabel );
        this.contentDirLabel.setLayoutData( gdhfill() );
        
        this.contentDir = new Text( composite, SWT.BORDER );
        this.contentDir.setLayoutData( gdhfill() );
        this.contentDir.setData( "label", this.contentDirLabel ); //$NON-NLS-1$
        synchHelper.synchText(contentDir, CONTENT_DIR, null);
        
        this.createWebInfSrc = new Button( composite, SWT.CHECK );
        this.createWebInfSrc.setText( Resources.createWebinfSrcLabel );
        synchHelper.synchCheckbox(createWebInfSrc, CREATE_WEB_INF_SRC, null);
        return composite;
    }
    
    protected String[] getValidationPropertyNames() {
    	return new String [] { EAR_PROJECT_NAME, CONTEXT_ROOT, CONTENT_DIR, CREATE_WEB_INF_SRC };
    }
    
    public void setConfig( final Object config ) 
    {
    	model.removeListener(this);
    	synchHelper.dispose();
    	
        model = (IDataModel) config;
        model.addListener(this);
		synchHelper = initializeSynchHelper(model);
    }
    
    private void validate()
    {
        boolean valid = true;
        
        if( ! validateNotEmpty( this.contextRoot, Resources.contextRootLabel, Resources.contentDirLabelInvalid ) ||
            ! validateNotEmpty( this.contentDir, Resources.contentDirLabel, Resources.contentDirLabelInvalid ) )
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

	public void setWizardContext(IWizardContext context) {
		// Intentionally empty
	}

    public void transferStateToConfig() {
		// Intentionally empty
    }
    
    public void dispose() {
    	if(null != earPanel){
    		earPanel.dispose();
    	}
    	super.dispose();
    }

	
}