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
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEImportPage;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WebComponentImportWebLibsPage extends J2EEImportPage {
	private Button deselectAllButton;
	private Button selectAllButton;
	protected WARFile warFile;
	public CheckboxTableViewer availableJARsViewer;
	public boolean utilJarSelectionChanged = false;

	/**
	 * @param model
	 * @param pageName
	 */
	public WebComponentImportWebLibsPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.WEB_IMPORT_WEB_LIB_PG_TITLE);
		setDescription(WEBUIMessages.WEB_IMPORT_WEB_LIB_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_IMPORT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IWebUIContextIds.WAR_IMPORT_PAGE2);
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createAvailableJarsList(composite);
		createButtonsGroup(composite);
		return composite;
	}

	/*
	 * Updates the enable state of the all buttons
	 */
	protected void updateButtonEnablements() {
		utilJarSelectionChanged = true;
	}

	protected void createAvailableJarsList(Composite listGroup) {
		availableJARsViewer = CheckboxTableViewer.newCheckList(listGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gData.widthHint = 200;
		gData.heightHint = 80;
		availableJARsViewer.getControl().setLayoutData(gData);
		AvailableWebLibProvider libsProvider = new AvailableWebLibProvider();
		availableJARsViewer.setContentProvider(libsProvider);
		availableJARsViewer.setLabelProvider(libsProvider);
		availableJARsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				availableJARCheckStateChanged(event);
			}
		});
		availableJARsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtonEnablements();
			}
		});
		TableLayout tableLayout = new TableLayout();
		availableJARsViewer.getTable().setLayout(tableLayout);
		availableJARsViewer.getTable().setHeaderVisible(false);
		availableJARsViewer.getTable().setLinesVisible(false);

		model.addListener(new IDataModelListener() {
			public void propertyChanged(DataModelEvent event) {
				if (event.getPropertyName().equals(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED)) {
					availableJARsViewer.setCheckedElements(((List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED)).toArray());
				}
			}
		});
	}

	private void handleDeselectAllButtonPressed() {
		ArrayList emptySelection = new ArrayList(2);
		model.setProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED, emptySelection);
	}

	private void handleSelectAllButtonPressed() {
		ArrayList allSelection = new ArrayList(2);
		Object selection = null;
		for (int i = 0; (null != (selection = availableJARsViewer.getElementAt(i))); i++) {
			allSelection.add(selection);
		}
		model.setProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED, allSelection);
	}

	protected void createButtonsGroup(org.eclipse.swt.widgets.Composite parent) {
		Composite buttonGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		buttonGroup.setLayout(layout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(WEBUIMessages.WAR_IMPORT_SELECT_ALL_LIBS_BUTTON);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 1;
		gd.heightHint = 22;
		gd.widthHint = 120;
		selectAllButton.setLayoutData(gd);
		selectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleSelectAllButtonPressed();
			}
		});

		deselectAllButton = new Button(buttonGroup, SWT.PUSH);
		deselectAllButton.setText(WEBUIMessages.WAR_IMPORT_DESELECT_ALL_LIBS_BUTTON);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		gd.heightHint = 22;
		gd.widthHint = 120;
		deselectAllButton.setLayoutData(gd);
		deselectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleDeselectAllButtonPressed();
			}
		});
	}

	private void refreshEARFileIfNecessary() {
		if (isEARFileChanged()) {
			warFile = (WARFile) model.getProperty(IJ2EEComponentImportDataModelProperties.FILE);
			refresh();
		}
	}

	protected void setJARsCompositeEnabled(boolean enabled) {
		availableJARsViewer.getTable().setEnabled(enabled);
		availableJARsViewer.setAllChecked(false);
		availableJARsViewer.setAllGrayed(!enabled);
		selectAllButton.setEnabled(enabled);
		deselectAllButton.setEnabled(enabled);
	}

	private void refresh() {
		availableJARsViewer.setInput(warFile);
	}

	public boolean isEARFileChanged() {
		return warFile != model.getProperty(IJ2EEComponentImportDataModelProperties.FILE);
	}

	protected void enter() {
		super.enter();
		refreshEARFileIfNecessary();
	}

	public void availableJARCheckStateChanged(CheckStateChangedEvent event) {
		model.setProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED, getJARsForProjects());
		validatePage();
	}

	public List getJARsForProjects() {
		refreshEARFileIfNecessary();
		List result = new ArrayList();
		result.addAll(Arrays.asList(availableJARsViewer.getCheckedElements()));
		return result;
	}

	protected boolean isNullOrEmpty(String aString) {
		return aString == null || aString.length() == 0;
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{};
	}

	protected void restoreWidgetValues() {
		// This page doesn't implement...
	}

	public void storeDefaultSettings() {
		// This page doesn't implement...
	}



}