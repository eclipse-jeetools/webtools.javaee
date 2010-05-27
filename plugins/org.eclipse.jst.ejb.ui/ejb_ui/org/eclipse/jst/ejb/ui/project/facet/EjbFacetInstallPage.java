/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *				 David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.project.facet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;



public class EjbFacetInstallPage extends J2EEModuleFacetInstallPage 
	implements IEjbFacetInstallDataModelProperties {

    private static final String MODULE_NAME_UI = J2EEUIMessages.getResourceString(J2EEUIMessages.NAME_LABEL);
    
	protected Button addClient;	
    protected Text clientNameText = null;  
	private Label clientJarURILabel;
	private Text clientJarURI;
    
	public EjbFacetInstallPage() {
		super("ejb.facet.install.page"); //$NON-NLS-1$
		setTitle(EJBUIMessages.pageTitle);
		setDescription(EJBUIMessages.pageDescription);
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CREATE_CLIENT, CLIENT_NAME, CLIENT_SOURCE_FOLDER, CLIENT_URI, CONFIG_FOLDER};
	}

		
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Composite ejbClientComposite = createEjbClientComposite(composite);
		createEJBClientGroup(ejbClientComposite);
		createProjectNameGroup(ejbClientComposite);
		createClientJarURISection(ejbClientComposite);
		
		new Label(composite, SWT.NONE); // pad
		
        createGenerateDescriptorControl(composite, J2EEConstants.EJBJAR_DD_SHORT_NAME);
        registerFacetVersionChangeListener();
        
	    Dialog.applyDialogFont(parent);
	    
		return composite;
	}
	
    @Override
	protected void handleFacetVersionChangedEvent()
    {
        String fv = model.getStringProperty(FACET_VERSION_STR);
        boolean isEJB30OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv) >= J2EEVersionConstants.EJB_3_0_ID;
        this.addDD.setVisible(isEJB30OrGreater);
    }
    
    private Composite createEjbClientComposite(Composite parent) {
    	Group group = new Group(parent, SWT.NONE);
    	group.setLayout(new GridLayout(2, false));
    	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    	group.setText(EJBUIMessages.EJB_CLIENT_JAR_GROUP);
    	return group;
    }

	private void createEJBClientGroup(Composite parent) {
		addClient = new Button(parent, SWT.CHECK);
		addClient.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.CREATE_EJB_CLIENT_JAR));
		synchHelper.synchCheckbox(addClient, CREATE_CLIENT, null);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		addClient.setLayoutData(gd);
		synchHelper.synchCheckbox(addClient, CREATE_CLIENT, null);
	}
	
    private void createProjectNameGroup(Composite parent) {
        // set up project name label
        Label projectNameLabel = new Label(parent, SWT.NONE);
        projectNameLabel.setText(MODULE_NAME_UI);
        GridData data = new GridData();
        projectNameLabel.setLayoutData(data);
        // set up project name entry field
        clientNameText = new Text(parent, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        //data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        clientNameText.setLayoutData(data);
        synchHelper.synchText(clientNameText, CLIENT_NAME, new Control[]{projectNameLabel});
    }
    
    private void createClientJarURISection(Composite parent) {
        // set up project name label
    	clientJarURILabel = new Label(parent, SWT.NONE);
    	clientJarURILabel.setText(EJBUIMessages.Client_JAR_URI);
        GridData data = new GridData();
        clientJarURILabel.setLayoutData(data);

        clientJarURI = new Text(parent, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        //data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        clientJarURI.setLayoutData(data);
		synchHelper.synchText(clientJarURI, CLIENT_URI, new Control[]{clientJarURILabel});
    }    
}
