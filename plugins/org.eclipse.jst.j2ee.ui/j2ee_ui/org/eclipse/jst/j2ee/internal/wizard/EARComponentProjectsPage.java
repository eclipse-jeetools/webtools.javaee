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
 * Created on Dec 8, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EARComponentProjectsPage extends J2EEImportPage {

	private CheckboxTableViewer earFileListViewer;

	public static final String INCLUDE_COLUMN = J2EEUIMessages.getResourceString("EARImportProjectsPage_UI_0"); //$NON-NLS-1$
	public static final String FILE_COLUMN = J2EEUIMessages.getResourceString("EARImportProjectsPage_UI_1"); //$NON-NLS-1$
	public static final String PROJECT_COLUMN = J2EEUIMessages.getResourceString("EARImportProjectsPage_UI_2"); //$NON-NLS-1$

	/**
	 * @param model
	 * @param pageName
	 */
	public EARComponentProjectsPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_PROJECT_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_PROJECT_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setInfopopID(IJ2EEUIContextIds.IMPORT_EAR_WIZARD_P3);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createListGroup(composite);
		createButtonsGroup(composite);

		return composite;
	}

	protected void setColumnEditors() {
		Table t = earFileListViewer.getTable();
		CellEditor[] columnEditors = new CellEditor[t.getColumnCount()];
		columnEditors[1] = new TextCellEditor(t);

		earFileListViewer.setCellEditors(columnEditors);
	}

	protected void createButtonsGroup(Composite parent) {
		Composite buttonGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		buttonGroup.setLayout(layout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button selectNotInWorkspace = new Button(buttonGroup, SWT.PUSH);
		selectNotInWorkspace.setText(J2EEUIMessages.getResourceString("EARImportProjectsPage_UI_3")); //$NON-NLS-1$
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 1;
		gd.heightHint = 22;
		gd.widthHint = 120;
		selectNotInWorkspace.setLayoutData(gd);
		selectNotInWorkspace.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST);
				List selectedList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
				List newList = new ArrayList();
				newList.addAll(selectedList);
				IDataModel importDM = null;
				for (int i = 0; i < list.size(); i++) {
					importDM = (IDataModel) list.get(i);
					if (!newList.contains(importDM) && !((IVirtualComponent) importDM.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT)).exists()) {
						newList.add(importDM);
					}
				}
				model.setProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST, newList);
			}
		});

		Button selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_SELECT_ALL_UTIL_BUTTON));
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 1;
		gd.heightHint = 22;
		gd.widthHint = 120;
		selectAllButton.setLayoutData(gd);
		selectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST);
				List newList = new ArrayList();
				newList.addAll(list);
				model.setProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST, newList);
			}
		});

		Button deselectAllButton = new Button(buttonGroup, SWT.PUSH);
		deselectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_DESELECT_ALL_UTIL_BUTTON));
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 1;
		gd.heightHint = 22;
		gd.widthHint = 120;
		deselectAllButton.setLayoutData(gd);
		deselectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List newList = new ArrayList();
				model.setProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST, newList);
			}
		});
	}

	public void propertyChanged(DataModelEvent event) {
		if (event.getPropertyName().equals(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST)) {
			updateGUICheckSelection();
		}
		super.propertyChanged(event);
	}

	public void setFileListViewerInput() {
		TableObjects files = new TableObjects();
		Iterator iterator = ((List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST)).iterator();
		while (iterator.hasNext()) {
			files.tableObjectsList.add(iterator.next());
		}
		earFileListViewer.setInput(files);
		updateGUICheckSelection();
	}

	private void updateGUICheckSelection() {
		List selectedList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
		List projectList = (List) model.getProperty(IEARComponentImportDataModelProperties.ALL_PROJECT_MODELS_LIST);
		Object currentElement = null;
		for (int i = 0; i < projectList.size(); i++) {
			currentElement = projectList.get(i);
			earFileListViewer.setChecked(currentElement, selectedList.contains(currentElement));
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.wizard.J2EEWizardPage#enter()
	 */
	protected void enter() {
		super.enter();
		setFileListViewerInput();
		validatePage();
	}

	/**
	 * Creates the import source specification widgets. <b>Subclasses </b> must override this hook
	 * method.
	 * 
	 * @param parent
	 *            a <code>Composite</code> that is to be used as the parent of this group's
	 *            collection of visual components
	 * @see org.eclipse.swt.widgets.Composite
	 */
	protected void createListGroup(org.eclipse.swt.widgets.Composite parent) {
		Composite listGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		listGroup.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		listGroup.setLayoutData(gd);

		earFileListViewer = CheckboxTableViewer.newCheckList(listGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		EARImportListContentProvider provider = new EARImportListContentProvider();
		earFileListViewer.setContentProvider(provider);
		earFileListViewer.setLabelProvider(provider);
		earFileListViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				IDataModel matchingModel = null;// getEARImportDataModel().getMatchingEJBJarOrClient(aModel);
				if (null != matchingModel) {
					earFileListViewer.setChecked(matchingModel, event.getChecked());
				}
				List result = new ArrayList();
				result.addAll(Arrays.asList(earFileListViewer.getCheckedElements()));
				model.setProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST, result);

			}
		});

		Table earFileListTable = (Table) earFileListViewer.getControl();
		earFileListTable.setHeaderVisible(true);
		earFileListTable.setLinesVisible(true);
		// set up table layout
		TableLayout tableLayout = new org.eclipse.jface.viewers.TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(100, true));
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		earFileListTable.setLayout(tableLayout);

		gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gd.widthHint = 400;
		earFileListTable.setLayoutData(gd);


		TableColumn fileNameColumns = new TableColumn(earFileListTable, SWT.NONE);
		fileNameColumns.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_Modules_in_EAR)); //$NON-NLS-1$ = "Modules in EAR"
		fileNameColumns.setResizable(true);

		TableColumn importNameColumn = new TableColumn(earFileListTable, SWT.NONE);
		importNameColumn.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_New_Project_Name)); //$NON-NLS-1$ = "New Project Name"
		importNameColumn.setResizable(true);

		String[] columnProperties = new String[2];
		columnProperties[0] = FILE_COLUMN;
		columnProperties[1] = PROJECT_COLUMN;
		earFileListViewer.setColumnProperties(columnProperties);

		setColumnEditors();
		earFileListViewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				return PROJECT_COLUMN.equals(property);
			}

			public Object getValue(Object element, String property) {
				TableItem[] items = earFileListViewer.getTable().getSelection();
				TableItem item = items[0];
				return item.getText(1);
			}

			public void modify(Object element, String property, Object value) {
				TableItem elementHolder = (TableItem) element;
				if (property.equals(PROJECT_COLUMN)) {
					elementHolder.setText(1, (String) value);
					((IDataModel) elementHolder.getData()).setProperty(IJ2EEComponentImportDataModelProperties.COMPONENT_NAME, value);
				}
			}
		});
	}


	protected void restoreWidgetValues() {
		// This page doesn't implement...
	}

	public void storeDefaultSettings() {
		// This page doesn't implement...
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST};
	}


}