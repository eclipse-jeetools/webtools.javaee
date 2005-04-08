package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.internal.operations.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.componentcore.internal.operation.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.ui.DataModelSynchHelper;

public class ServerEarAndStandaloneDataModelGroup {
	
	private Button newEAR;
	private Combo earCombo;
	private Label earLabel;
	private Button addToEAR;
	private IDataModel model;
	private DataModelSynchHelper synchHelper;
	private Composite parentComposite;

	/**
	 *  
	 */
	public ServerEarAndStandaloneDataModelGroup(Composite parent, IDataModel model) {
		this.model = model;
		this.parentComposite = parent;
		synchHelper = new DataModelSynchHelper(model);
		buildComposites(parent);
	}

	/**
	 * 
	 * @param parent
	 */
	public void buildComposites(Composite parent) {
		createEarAndStandaloneComposite(parent);
	}

	/**
	 * @param parent
	 */
	protected void createEarAndStandaloneComposite(Composite parent) {

		if (model.getBooleanProperty(IJ2EEComponentCreationDataModelProperties.UI_SHOW_EAR_SECTION)) {

			Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			separator.setLayoutData(gd);

			new Label(parent, SWT.NONE); //pad

			// Create Add to EAR checkbox
			addToEAR = new Button(parent, SWT.CHECK);
			addToEAR.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.LINK_MODULETO_EAR_PROJECT));
			addToEAR.setSelection(true);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			addToEAR.setLayoutData(gd);
			synchHelper.synchCheckbox(addToEAR, J2EEComponentCreationDataModel.ADD_TO_EAR, null);
			addToEAR.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					handleAddToEarSelection();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					//do nothing
				}
			});
			// Create EAR Group
			earLabel = new Label(parent, SWT.NONE);
			earLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_PROJECT_FOR_MODULE_CREATION));

			earCombo = new Combo(parent, SWT.NONE);
			earCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			newEAR = new Button(parent, SWT.NONE);
			newEAR.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_W));
			newEAR.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			newEAR.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					handleNewEarSelected();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					//do nothing
				}

			});

			Control[] deps = new Control[]{earLabel, newEAR};
			synchHelper.synchCombo(earCombo, J2EEComponentCreationDataModel.EAR_MODULE_NAME, deps);
		}
	}

	/**
	 *  
	 */
	protected void handleAddToEarSelection() {
		boolean selection = addToEAR.getSelection();
		earLabel.setEnabled(selection);
		earCombo.setEnabled(selection);
		newEAR.setEnabled(selection);
	}

	/**
	 *  
	 */
	protected void handleNewEarSelected() {
		IDataModel earModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		earModel.setIntProperty(IJ2EEComponentCreationDataModelProperties.J2EE_VERSION, model.getIntProperty(IJ2EEComponentCreationDataModelProperties.J2EE_VERSION));
		earModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_MODULE_NAME));
		EARComponentCreationWizard earWizard = new EARComponentCreationWizard();
		WizardDialog dialog = new WizardDialog(parentComposite.getShell(), earWizard);
		if (Window.OK == dialog.open()) {
			model.setProperty(IJ2EEComponentCreationDataModelProperties.EAR_MODULE_NAME, earModel.getProperty(ComponentCreationDataModel.COMPONENT_NAME));
		}
	}

	public void dispose() {
		model.removeListener(synchHelper);
		model.dispose();
		synchHelper = null;
		model = null;
	}
}