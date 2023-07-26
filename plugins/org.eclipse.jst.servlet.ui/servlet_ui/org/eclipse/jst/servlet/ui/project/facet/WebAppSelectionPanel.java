/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.servlet.ui.project.facet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.web.project.facet.IWebFragmentFacetInstallDataModelProperties;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;


public final class WebAppSelectionPanel implements IWebFragmentFacetInstallDataModelProperties
    
{
    private final Button addToWar;
    private final Combo combo;
    private final Button newButton;
    private final Label label;
   
    private final IDataModel model;
    private DataModelSynchHelper synchhelper;
    
    public WebAppSelectionPanel( final IDataModel model, final Composite parent) 
    {
        this.model = model;
        this.synchhelper = new DataModelSynchHelper(model);
        
        final Group group = new Group( parent, SWT.NONE );
        group.setLayoutData( gdhfill() );
        group.setLayout( new GridLayout( 3, false ) );
        group.setText( Resources.warMemberShip );
        
        this.addToWar = new Button( group, SWT.CHECK );
        this.addToWar.setText( Resources.addToWarLabel );
        GridDataFactory.defaultsFor(this.addToWar).span(3, 1).applyTo(this.addToWar);
        synchhelper.synchCheckbox(addToWar, ADD_TO_WAR, null);

        label = new Label(group, SWT.NULL);
        label.setText(Resources.warProjectLabel);
        this.combo = new Combo(group, SWT.NONE);
        this.combo.setLayoutData( gdhfill() );
        
        this.newButton = new Button( group, SWT.PUSH );
        this.newButton.setText( Resources.newButtonLabel );
        GridDataFactory.defaultsFor(this.newButton).applyTo(this.newButton);
        
        this.newButton.addSelectionListener( new SelectionAdapter()
        {
            @Override
			public void widgetSelected( final SelectionEvent event )
            {
                handleAddButton();
            }
        } );
        
        synchhelper.synchCombo(combo, WAR_PROJECT_NAME, new Control[]{label, newButton});
        Dialog.applyDialogFont(parent);
    }

    private void handleAddButton()
    {
        final WebProjectWizard wizard = new WebProjectWizard();
        
        final WizardDialog dialog 
            = new WizardDialog( newButton.getShell(), wizard );
        
/*        IRuntime runtime = (IRuntime)model.getProperty(FACET_RUNTIME);
    	wizard.ssetRuntimeInDataModel(runtime);
*/
    	if( dialog.open() != Window.CANCEL )
        {
            model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
            final String warproj = wizard.getProjectName();
            model.setProperty(WAR_PROJECT_NAME, warproj);
        }
    }
    
    private static GridData gdhfill()
    {
        return new GridData( GridData.FILL_HORIZONTAL );
    }

    public static final GridData gdhspan( final GridData gd,
                                          final int span )
    {
        gd.horizontalSpan = span;
        return gd;
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String addToWarLabel;
        public static String newButtonLabel;
        public static String warProjectLabel;
        public static String warMemberShip;
        
        static
        {
            initializeMessages( WebAppSelectionPanel.class.getName(), 
                                Resources.class );
        }
    }
    
    public void dispose() {
    	if(synchhelper != null){
    		synchhelper.dispose();
    		synchhelper = null;
    	}
    }
    
    public String getComboText(){
    	return combo.getText();
    }    
}
