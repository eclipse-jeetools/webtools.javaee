/*
 * Created on Mar 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.internal.operations.AddArchiveProjectsToEARDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.DefaultJ2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EARComponentCreationSecondPage extends WTPWizardPage {
	private Button selectAllButton;
	private Button deselectAllButton;
	private Button newModuleButton;
	private CheckboxTableViewer moduleProjectsViewer;
	private boolean ignoreCheckedState = false;
	/**
	 * @param model
	 * @param pageName
	 */
	public EARComponentCreationSecondPage(EARComponentCreationDataModel  model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_SECOND_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_SECOND_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.ui.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.ui.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite modulesGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		modulesGroup.setLayout(layout);
//		setInfopopID(IJ2EEUIContextIds.NEW_EAR_ADD_MODULES_PAGE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		modulesGroup.setLayoutData(gridData);
		createModuleProjectOptions(modulesGroup);
		createButtonsGroup(modulesGroup);
		return modulesGroup;
	}

	/**
	 * @param modulesGroup
	 */
	private void createModuleProjectOptions(Composite modulesGroup) {
		moduleProjectsViewer = CheckboxTableViewer.newCheckList(modulesGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gData = new GridData(GridData.FILL_BOTH);
		gData.widthHint = 200;
		gData.heightHint = 80;
		moduleProjectsViewer.getControl().setLayoutData(gData);
		int j2eeVersion = getModel().getIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION);
		AvailableJ2EEComponentsContentProvider provider = new AvailableJ2EEComponentsContentProvider(j2eeVersion);
		moduleProjectsViewer.setContentProvider(provider);
		moduleProjectsViewer.setLabelProvider(new J2EEComponentLabelProvider());
		setCheckedItemsFromModel();
		moduleProjectsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (!ignoreCheckedState) {
					((EARComponentCreationDataModel)getModel()).setProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST, getCheckedElementsAsList());
				}
			}
		});
		TableLayout tableLayout = new TableLayout();
		moduleProjectsViewer.getTable().setLayout(tableLayout);
		moduleProjectsViewer.getTable().setHeaderVisible(false);
		moduleProjectsViewer.getTable().setLinesVisible(false);
		moduleProjectsViewer.setSorter(null);
	}

	/**
	 *  
	 */
	private void setCheckedItemsFromModel() {
//		List projects = (List) getModulesModel().getProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST);
//		moduleProjectsViewer.setCheckedElements(projects.toArray());
	}

	private void refreshModules() {
		moduleProjectsViewer.refresh();
		setCheckedItemsFromModel();
	}

	protected List getCheckedElementsAsList() {
		Object[] elements = moduleProjectsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else
			list = Arrays.asList(elements);
		return list;
	}

	protected void createButtonsGroup(org.eclipse.swt.widgets.Composite parent) {
		Composite buttonGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		buttonGroup.setLayout(layout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_SELECT));
		selectAllButton.addListener(SWT.Selection, this);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		selectAllButton.setLayoutData(gd);
		deselectAllButton = new Button(buttonGroup, SWT.PUSH);
		deselectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_DESELECT));
		deselectAllButton.addListener(SWT.Selection, this);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		deselectAllButton.setLayoutData(gd);
		newModuleButton = new Button(buttonGroup, SWT.PUSH);
		newModuleButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_NEW));
		newModuleButton.addListener(SWT.Selection, this);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		newModuleButton.setLayoutData(gd);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(Event)
	 */
	public void handleEvent(Event evt) {
		if (evt.widget == selectAllButton)
			handleSelectAllButtonPressed();
		else if (evt.widget == deselectAllButton)
			handleDeselectAllButtonPressed();
		else if (evt.widget == newModuleButton)
			handleNewModuleButtonPressed();
		else
			super.handleEvent(evt);
	}

	/**
	 *  
	 */
	private void handleNewModuleButtonPressed() {
		DefaultJ2EEComponentCreationDataModel aModel = createNewModuleModel();
		DefaultJ2EEComponentCreationWizard wizard = new DefaultJ2EEComponentCreationWizard(aModel);
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		if (dialog.open() != IDialogConstants.CANCEL_ID) {
			IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
			moduleProjectsViewer.setInput(input);
		}
	}

	private DefaultJ2EEComponentCreationDataModel createNewModuleModel() {
		DefaultJ2EEComponentCreationDataModel defaultModel = new DefaultJ2EEComponentCreationDataModel();
		// transfer properties, project name
		String projectName = model.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME);
		defaultModel.setProperty(DefaultJ2EEComponentCreationDataModel.PROJECT_NAME, projectName);
		// ear component name
		String earName = model.getStringProperty(EARComponentCreationDataModel.COMPONENT_NAME);
		defaultModel.setProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME, earName);
		// ear j2ee version
		int j2eeVersion = model.getIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION);
		defaultModel.setProperty(DefaultJ2EEComponentCreationDataModel.J2EE_VERSION, new Integer(j2eeVersion));
		return defaultModel;
	}

	/**
	 *  
	 */
	private void handleDeselectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(false);
			((EARComponentCreationDataModel)getModel()).setProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST, null);
		} finally {
			ignoreCheckedState = false;
		}
	}

	/**
	 *  
	 */
	private void handleSelectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(true);
			((EARComponentCreationDataModel)getModel()).setProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST, getCheckedElementsAsList());
		} finally {
			ignoreCheckedState = false;
		}
	}

	private AddArchiveProjectsToEARDataModel getModulesModel() {
//		return ((EnterpriseApplicationCreationDataModel) model).getAddModulesToEARDataModel();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#enter()
	 */
	protected void enter() {
		IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
		moduleProjectsViewer.setInput(input);
		super.enter();
	}

}
