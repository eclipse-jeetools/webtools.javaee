/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationPropertiesNew;
import org.eclipse.wst.common.frameworks.internal.ui.NewProjectGroup;
import org.eclipse.wst.server.ui.ServerUIUtil;


public abstract class J2EEComponentFacetCreationWizardPage extends DataModelWizardPage implements IFacetProjectCreationDataModelProperties {

	private static final boolean isWindows = SWT.getPlatform().toLowerCase().startsWith("win"); //$NON-NLS-1$
	protected static final String MODULE_VERSION = J2EEUIMessages.MODULE_VERSION_LABEL;
	protected EarSelectionPanel earPanel;
	protected Combo serverTargetCombo;

	protected NewProjectGroup projectNameGroup;

	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	// private static final String NEW_LABEL_UI =
	// J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$
	private static final String MODULE_NAME_UI = J2EEUIMessages.getResourceString(J2EEUIMessages.NAME_LABEL); //$NON-NLS-1$
	private String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL); //$NON-NLS-1$
	private static final String defDirDialogLabel = "Directory"; //$NON-NLS-1$

	public J2EEComponentFacetCreationWizardPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(top, getInfopopID());
		top.setLayout(new GridLayout());
		top.setLayoutData(new GridData(GridData.FILL_BOTH));
		createModuleGroup(top);
		Composite composite = new Composite(top, SWT.NONE);
		composite.setLayoutData(gdhfill());
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createServerTargetComposite(composite);
		createEarComposit(composite);
		// createServerEarAndStandaloneGroup(composite);


		Composite detail = new Composite(top, SWT.NONE);
		detail.setLayout(new GridLayout());
		detail.setData(new GridData(GridData.FILL_BOTH));
		return top;
	}

	private void createEarComposit(Composite composite) {
		FacetDataModelMap map = (FacetDataModelMap)model.getProperty(FACET_DM_MAP);
		IDataModel facetModel = (IDataModel)map.get(getModuleFacetID());
		
		earPanel = new EarSelectionPanel( facetModel, composite, SWT.NONE );
		GridData data = gdhfill();
		data.horizontalSpan = 3;
        earPanel.setLayoutData( data );
	}
	
	protected abstract String getModuleFacetID();

	private static GridData gdhfill()
    {
        return new GridData( GridData.FILL_HORIZONTAL );
    }

	protected void createModuleGroup(Composite parent) {
		IDataModel nestedProjectDM = model.getNestedModel(NESTED_PROJECT_DM);
		nestedProjectDM.addListener(this);
		projectNameGroup = new NewProjectGroup(parent, nestedProjectDM);
	}

	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_RUNTIME_LBL));
		serverTargetCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetCombo.setLayoutData(gdhfill());
		Button newServerTargetButton = new Button(parent, SWT.NONE);
		newServerTargetButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newServerTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!J2EEComponentFacetCreationWizardPage.launchNewRuntimeWizard(getShell(), model)) {
					setErrorMessage(J2EECreationResourceHandler.getString("ServerTargetDataModel_UI_9")); //$NON-NLS-1$
				}
			}
		});
		Control[] deps = new Control[]{label, newServerTargetButton};
		synchHelper.synchCombo(serverTargetCombo, FACET_RUNTIME, deps);
		if (serverTargetCombo.getSelectionIndex() == -1 && serverTargetCombo.getVisibleItemCount() != 0)
			serverTargetCombo.select(0);
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{IProjectCreationPropertiesNew.PROJECT_NAME, IProjectCreationPropertiesNew.PROJECT_LOCATION, FACET_RUNTIME};
	}

	protected String getVersionLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.MODULE_VERSION_LABEL);
	}

	protected String getSourceFolderLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.SOURCEFOLDER);
	}

	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
		if (projectNameGroup != null)
			projectNameGroup.dispose();
	}

	protected void createVersionComposite(Composite parent, String labelText, String versionProp) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(labelText);
		Combo versionCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		GridData gridData = gdhfill();
		gridData.widthHint = 305;
		versionCombo.setLayoutData(gridData);
		Control[] deps = new Control[]{label};
		synchHelper.synchCombo(versionCombo, versionProp, deps);
		if (versionCombo.getSelectionIndex() == -1) {
			String[] items = versionCombo.getItems();
			if (items != null && items.length > 0)
				versionCombo.select(items.length - 1);
		}
		new Label(parent, SWT.NONE); // pad
	}



	public static boolean launchNewRuntimeWizard(Shell shell, IDataModel model) {
		DataModelPropertyDescriptor[] preAdditionDescriptors = model.getValidPropertyDescriptors(FACET_RUNTIME);
		boolean isOK = ServerUIUtil.showNewRuntimeWizard(shell, "", ""); //$NON-NLS-1$  //$NON-NLS-2$
		if (isOK && model != null) {

			DataModelPropertyDescriptor[] postAdditionDescriptors = model.getValidPropertyDescriptors(FACET_RUNTIME);
			Object[] preAddition = new Object[preAdditionDescriptors.length];
			for (int i = 0; i < preAddition.length; i++) {
				preAddition[i] = preAdditionDescriptors[i].getPropertyValue();
			}
			Object[] postAddition = new Object[postAdditionDescriptors.length];
			for (int i = 0; i < postAddition.length; i++) {
				postAddition[i] = postAdditionDescriptors[i].getPropertyValue();
			}
			Object newAddition = ProjectUtilities.getNewObject(preAddition, postAddition);

			model.notifyPropertyChange(FACET_RUNTIME, IDataModel.VALID_VALUES_CHG);
			if (newAddition != null)
				model.setProperty(FACET_RUNTIME, newAddition);
			else
				return false;
		}
		return isOK;
	}

}