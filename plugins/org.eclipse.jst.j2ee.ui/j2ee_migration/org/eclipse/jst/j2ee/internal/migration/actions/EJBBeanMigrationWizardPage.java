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
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.EJBClientViewMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.EJBJarMigrationConfig;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.wst.internal.common.ui.WTPWizardPage;


/**
 * EJBBeanMigrationWizardPage
 */
public class EJBBeanMigrationWizardPage extends WTPWizardPage implements ICheckStateListener {
	private static final Integer PAGE_OK = new Integer(0);
	protected CheckboxTreeViewer beanViewer;
	protected ComposedMigrationConfig composedMigrationConfig;
	protected Group compositeLocal;
	protected Button deleteRemoteButton, selectAll, deselectAll, selectAllEntities, selectRequired;
	protected List modules;
	protected int numSelected = 0;
	protected IWizardPage previousPage;
	protected Button reuseDeletedName, specSuffix;
	protected Text selectStatus, suffixName, suffixExample1;
	protected int totalBeans = 0;


	/**
	 * Constructor for EJBBeanMigrationWizard.
	 * 
	 * @param pageName
	 */
	public EJBBeanMigrationWizardPage(String pageName, ComposedMigrationConfig composedConfig) {
		super(composedConfig, pageName);
		composedMigrationConfig = composedConfig;
		setTitle(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_WIZARD_TITLE);
		setDescription(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_WIZARD_DESCRIPTION);
	}

	/**
	 * Method addListeners
	 */
	protected void addListeners() {
		beanViewer.addCheckStateListener(this);
		beanViewer.getTree().addListener(SWT.Selection, this);
		selectAll.addListener(SWT.Selection, this);
		deselectAll.addListener(SWT.Selection, this);
		selectAllEntities.addListener(SWT.Selection, this);
		selectRequired.addListener(SWT.Selection, this);
		deleteRemoteButton.addListener(SWT.Selection, this);
		reuseDeletedName.addListener(SWT.Selection, this);
		specSuffix.addListener(SWT.Selection, this);
		suffixName.addListener(SWT.Modify, this);
	}

	private void beanConfigCheckStateChanged(boolean checked, EJBClientViewMigrationConfig element) {
		element.setIsSelected(checked);
		refreshTreeViewerCheckState(element.getParentConfig());
	}


	protected void changeLocalNamingViewEnablement(boolean selection) {
		reuseDeletedName.setEnabled(selection);
		if (!selection) {
			reuseDeletedName.setSelection(false);
			specSuffix.setSelection(true);
			suffixName.setEnabled(true);
			handleReuseDeleteName(false);
			handleSpecSuffix(true);
			suffixExample1.setEnabled(true);
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		boolean checked = event.getChecked();
		Object element = event.getElement();
		if (element instanceof EJBJarMigrationConfig)
			jarConfigCheckStateChanged(checked, (EJBJarMigrationConfig) element);
		else if (element instanceof EJBClientViewMigrationConfig)
			beanConfigCheckStateChanged(checked, (EJBClientViewMigrationConfig) element);
		updateSelectedStatus();
		validateControls();
	}

	public boolean confirmAllRequiredBeansSelected() {
		Map unselected = getAllUnselectedRequiredBeans();
		if (unselected.isEmpty())
			return true;
		boolean confirmed = RequiredBeansDialog.openConfirm(getShell(), unselected);
		if (confirmed) {
			selectRequired(unselected);
			if (isCurrentPage())
				refreshTreeViewer();
			return true;
		}
		return false;
	}


	protected void createBeanViewer(Composite composite) {
		Label ejbLabel = new Label(composite, SWT.NULL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		ejbLabel.setLayoutData(data);
		ejbLabel.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_BEANS_HEADER);

		data = new GridData(GridData.FILL_BOTH);
		beanViewer = new CheckboxTreeViewer(composite, SWT.CHECK | SWT.BORDER);
		data.heightHint = 120;
		beanViewer.getTree().setLayoutData(data);
		beanViewer.setLabelProvider(new BeanLabelProvider());
		beanViewer.setContentProvider(new BeanContentProvider());
		beanViewer.setAutoExpandLevel(2);
	}

	protected void createButtons(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		selectAll = new Button(composite, SWT.NONE);
		selectAll.setText(IMigrationWizardConstants.SELECT_ALL);
		selectAll.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		deselectAll = new Button(composite, SWT.NONE);
		deselectAll.setText(IMigrationWizardConstants.DESELECT_ALL);
		deselectAll.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		selectAllEntities = new Button(composite, SWT.NONE);
		selectAllEntities.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_SELECT_ALL_ENTITY);
		selectAllEntities.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		selectRequired = new Button(composite, SWT.NONE);
		selectRequired.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_SELECT_REQUIRED);
		selectRequired.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (numSelected == totalBeans)
			selectRequired.setEnabled(false);
	}

	protected void createEARButtons(Composite composite) {
		deleteRemoteButton = new Button(composite, SWT.CHECK);
		deleteRemoteButton.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_DELETE_REMOTE_CLIENT_VIEW);
		createLocalClientNaming(composite);
	}

	protected void createLocalClientNaming(Composite parent) {
		compositeLocal = new Group(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		compositeLocal.setLayout(layout);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 17;
		compositeLocal.setLayoutData(data);
		compositeLocal.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_LOCAL_CLIENT_NAMING_TITLE);

		reuseDeletedName = new Button(compositeLocal, SWT.RADIO | SWT.NULL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		reuseDeletedName.setLayoutData(data);
		reuseDeletedName.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_LOCAL_CLIENT_NAMING_REUSE);
		reuseDeletedName.setEnabled(false);

		specSuffix = new Button(compositeLocal, SWT.RADIO | SWT.NULL);
		specSuffix.setText(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_LOCAL_CLIENT_NAMING_SUFFIX);
		specSuffix.setSelection(true);

		suffixName = new Text(compositeLocal, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		data.widthHint = 300;
		suffixName.setLayoutData(data);

		Label empty = new Label(compositeLocal, SWT.NULL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 2;
		empty.setLayoutData(data);

		suffixExample1 = new Text(compositeLocal, SWT.WRAP | SWT.MULTI | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.horizontalIndent = 17;
		data.heightHint = 50;
		suffixExample1.setLayoutData(data);
	}

	protected void createNumberSelected(Composite composite) {
		selectStatus = new Text(composite, SWT.WRAP | SWT.READ_ONLY);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		selectStatus.setLayoutData(data);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#createTopLevelComposite(Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		//layout.verticalSpacing = 0;
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		WorkbenchHelp.setHelp(composite, J2EEUIContextIds.MIGRATION_WIZARD_CMP);

		createBeanViewer(composite);
		createButtons(composite);
		createNumberSelected(composite);
		setSpacer(composite);
		createEARButtons(composite);

		return composite;
	}

	public void enter() {
		super.enter();
		beanViewer.setInput(getConfigs());
		if (getSuffixFromConfig() == null)
			suffixName.setText(""); //$NON-NLS-1$
		else
			suffixName.setText(getSuffixFromConfig());
		refreshTreeViewer();
	}


	public void exit() {
		super.exit();
	}

	protected String format(String aPattern, String arg1, String arg2) {
		return MessageFormat.format(aPattern, new String[]{arg1, arg2});
	}

	protected String formatNumSelected(String aPattern, int selected, int total) {
		return MessageFormat.format(aPattern, new String[]{"" + selected, "" + total}); //$NON-NLS-1$ //$NON-NLS-2$
	}


	private Map getAllUnselectedRequiredBeans() {
		Map result = null;
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			List unselected = config.getChildrenRequiringSelection();
			if (!unselected.isEmpty()) {
				if (result == null)
					result = new HashMap();
				result.put(config, unselected);
			}
		}
		return result == null ? Collections.EMPTY_MAP : result;
	}

	protected List getConfigs() {
		if (composedMigrationConfig != null)
			return composedMigrationConfig.getEJBJarChildren();
		return Collections.EMPTY_LIST;
	}

	private String getSuffixFromConfig() {
		List configs = getConfigs();
		if (configs.isEmpty())
			return EJBJarMigrationConfig.DEFAULT_LOCAL_SUFFIX;
		return ((EJBJarMigrationConfig) configs.get(0)).getLocalClientViewSuffix();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	private void handleDeleteRemoteClientView(boolean deleteRemote) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((EJBJarMigrationConfig) configs.get(i)).setDeleteRemoteClientView(deleteRemote);
		}
	}

	/**
	 * Method handleEvent
	 */
	public void handleEvent(Event event) {
		if (previousPage == null)
			previousPage = getWizard().getPage("ejbModulePage"); //$NON-NLS-1$
		if (event.widget == selectAll) {
			setAllSelected(true);
			selectRequired.setEnabled(false);
			((EJBModuleMigrationWizardPage) previousPage).setLocalClientSelection(true);
		} else if (event.widget == deselectAll) {
			setAllSelected(false);
			selectRequired.setEnabled(true);
			((EJBModuleMigrationWizardPage) previousPage).setLocalClientSelection(false);
		} else if (event.widget == selectAllEntities) {
			selectAllEntities();
			handleLocalClientUpdate();
			if (numSelected == totalBeans) {
				selectRequired.setEnabled(false);
			} else {
				selectRequired.setEnabled(true);
			}
		} else if (event.widget == selectRequired) {
			selectRequired();
			handleLocalClientUpdate();
		} else if (event.widget == deleteRemoteButton) {
			changeLocalNamingViewEnablement(deleteRemoteButton.getSelection());
			if (reuseDeletedName.getSelection() && !deleteRemoteButton.getSelection()) {
				suffixName.setEnabled(false);
			}
			handleDeleteRemoteClientView(deleteRemoteButton.getSelection());
		} else if (event.widget == reuseDeletedName) {
			suffixName.setEnabled(false);
			suffixExample1.setEnabled(false);
			handleReuseDeleteName(reuseDeletedName.getSelection());
		} else if (event.widget == specSuffix) {
			suffixName.setEnabled(true);
			suffixExample1.setEnabled(true);
			handleSpecSuffix(specSuffix.getSelection());
		} else if (event.widget == suffixName) {
			handleSuffixNameChange();
		} else if (event.widget == beanViewer.getTree()) {
			handleLocalClientUpdate();
		}
		super.handleEvent(event);
	}

	/**
	 *  
	 */
	private void handleLocalClientUpdate() {
		if (numSelected == 0) {
			((EJBModuleMigrationWizardPage) previousPage).setLocalClientSelection(false);
			selectRequired.setEnabled(true);
		} else {
			((EJBModuleMigrationWizardPage) previousPage).setLocalClientSelection(true);
			if (numSelected == totalBeans) {
				selectRequired.setEnabled(false);
			} else {
				selectRequired.setEnabled(true);
			}
		}

		return;
	}

	private void handleReuseDeleteName(boolean reuse) {
		if (!reuse)
			return;
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			config.setReuseRemoteClientViewName(reuse);
		}
	}

	private void handleSpecSuffix(boolean spec) {
		if (!spec)
			return;
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			config.setReuseRemoteClientViewName(!spec);
			config.setLocalClientViewSuffix(suffixName.getText());
		}
	}


	private void handleSuffixNameChange() {
		List configs = getConfigs();
		String text = suffixName.getText().trim();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			if (!text.equals(config.getLocalClientViewSuffix()))
				config.setLocalClientViewSuffix(text);
		}
		suffixExample1.setText(format(IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_LOCAL_CLIENT_NAMING_EXP, text, text));
	}

	protected boolean hasApplicableBeans() {
		return !EJBJarMigrationConfig.filterConfigsWithNoApplicableClientConfigs(getConfigs()).isEmpty();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return super.isPageComplete() || !hasApplicableBeans();
	}

	private void jarConfigCheckStateChanged(boolean checked, EJBJarMigrationConfig element) {
		element.setAllChildrenSelected(checked);
		beanViewer.setGrayed(element, false);
		refreshTreeViewerChidren(element);
	}

	private void refreshTreeViewer() {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			refreshTreeViewer(config);
		}
		updateSelectedStatus();
	}


	private void refreshTreeViewer(EJBJarMigrationConfig config) {
		refreshTreeViewerCheckState(config);
		refreshTreeViewerChidren(config);
		validateControls();
	}

	private void refreshTreeViewerCheckState(EJBJarMigrationConfig config) {
		boolean parentChecked = config.isAnyChildSelected();
		beanViewer.setChecked(config, parentChecked);
		beanViewer.setGrayed(config, parentChecked && !config.isAllSelected());
	}

	private void refreshTreeViewerChidren(EJBJarMigrationConfig config) {
		List configs = config.getChildren();
		for (int i = 0; i < configs.size(); i++) {
			EJBClientViewMigrationConfig child = (EJBClientViewMigrationConfig) configs.get(i);
			beanViewer.setChecked(child, child.isSelected());
		}
	}

	protected void selectAllEntities() {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((EJBJarMigrationConfig) configs.get(i)).selectAllEntities();
		}
		refreshTreeViewer();
	}

	protected void selectRequired() {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((EJBJarMigrationConfig) configs.get(i)).selectRequiredChildren();
		}
		refreshTreeViewer();
	}

	private void selectRequired(Map unselected) {
		Iterator iter = unselected.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) entry.getKey();
			List aList = (List) entry.getValue();
			config.setChildrenSelected(aList, true);
		}
	}

	protected void setAllSelected(boolean state) {
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			((EJBJarMigrationConfig) configs.get(i)).setAllChildrenSelected(state);
		}
		refreshTreeViewer();
	}

	private void setSpacer(Composite composite) {
		Label space = new Label(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		space.setLayoutData(data);
	}

	protected void updateSelectedStatus() {
		numSelected = 0;
		totalBeans = 0;
		List configs = getConfigs();
		for (int i = 0; i < configs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) configs.get(i);
			numSelected += config.getSelectedCount();
			totalBeans += config.getChildCount();
		}

		selectStatus.setText(formatNumSelected(IMigrationWizardConstants.EJB_MIGRATE_SELECTED_BEANS, numSelected, totalBeans));
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEWizardPage#validateControls()
	 */
	protected void validateControls() {
		setOKStatus(PAGE_OK);
		updateSelectedStatus();
		if (numSelected == 0) {
			//this.setErrorStatus(PAGE_OK,
			// IWizardConstants.LOCAL_VIEW_MIGRATE_ERROR_NO_BEAN_SELECTED);
		} else if (specSuffix.getSelection() && suffixName.getText().equals("")) { //$NON-NLS-1$
			this.setErrorStatus(PAGE_OK, IMigrationWizardConstants.LOCAL_VIEW_MIGRATE_ERROR_SUFFIX_EMPTY);
		}
		super.validatePage();
	}

}