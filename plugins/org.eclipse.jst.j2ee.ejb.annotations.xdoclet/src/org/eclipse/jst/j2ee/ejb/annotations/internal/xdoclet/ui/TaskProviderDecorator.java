/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class TaskProviderDecorator {

	private IExtension[] extensions;

	private XDocletPreferenceStore preferenceStore;
	Table table;
	Button edit;

	public TaskProviderDecorator(IExtension[] extensions, XDocletPreferenceStore preferenceStore) {
		super();
		this.extensions = extensions;
		this.preferenceStore = preferenceStore;
	}

	public void decorate(final Composite composite) {

		createTableViewer(createTable(composite));
		Dialog.applyDialogFont(composite);
	}

	private Table createTable(final Composite parent) {
		int style = SWT.CHECK | SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		GridLayout layout;
		GridData gridData;
		Group libraryPanel = new Group(parent, SWT.NONE);
		libraryPanel.setText("Tasks");
		layout = new GridLayout(3, false);
		libraryPanel.setLayout(layout);
		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 3;
		libraryPanel.setLayoutData(gridData);

		table = new Table(libraryPanel, style);
		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 1;
		table.setLayoutData(gridData);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText(columnNames[0]);
		column.setWidth(60);

		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText(columnNames[1]);
		column.setWidth(200);

		edit = new Button(libraryPanel, SWT.PUSH);
		edit.setText("Edit...");
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING);
		edit.setLayoutData(data);
		edit.setEnabled(false);
		edit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IExtension extension = (IExtension) table.getSelection()[0].getData();
				if (extension != null) {
					if (showWizard(extension, parent.getShell()) != Window.CANCEL) {
					}
				}
			}
		});

		return table;
	}

	/**
	 * Create the TableViewer
	 */
	private TableViewer createTableViewer(Table table) {

		CheckboxTableViewer tableViewer = new CheckboxTableViewer(table);
		tableViewer.setUseHashlookup(true);

		tableViewer.setColumnProperties(columnNames);
		tableViewer.setLabelProvider(new FieldLabelProvider());
		tableViewer.setContentProvider(new FieldContentProvider());
		tableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				IExtension extension = (IExtension) event.getElement();

				IConfigurationElement[] elements = extension.getConfigurationElements();
				if (elements == null)
					return;

				String id = elements[0].getAttribute("id");
				String selection = id + ".defaultSelection";
				TaskProviderDecorator.this.preferenceStore.setProperty(selection, event.getChecked());
			}
		});
		tableViewer.setInput(extensions);
		tableViewer.setCheckedElements(getCheckedTasks());

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				Object obj = getSelection(event.getSelection());
				boolean enabled= false;
				if(obj != null && obj instanceof IExtension)
					enabled = ((IExtension)obj).getConfigurationElements().length >1;
				edit.setEnabled(enabled);
			}
		});

		return tableViewer;
	}

	protected Object getSelection(ISelection sele) {
		IStructuredSelection sel = (IStructuredSelection) sele;
		return sel.getFirstElement();
	}

	private Object[] getCheckedTasks() {
		ArrayList checked = new ArrayList(extensions.length);
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if (elements != null) {
				String id = elements[0].getAttribute("id");
				String selection = id + ".defaultSelection";
				if (preferenceStore.getBooleanPropertyNoGlobal(selection))
					checked.add(extension);
			}
		}

		return checked.toArray();
	}

	protected static String[] columnNames = { "include", "task" };

	protected int getColumnIndex(String columName) {
		if (columName == null)
			return -1;
		for (int i = 0; i < columnNames.length; i++) {
			String col = columnNames[i];
			if (columName.equals(col))
				return i;

		}
		return -1;
	}

	public class FieldContentProvider implements IStructuredContentProvider {
		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public Object[] getElements(Object parent) {
			return (IExtension[]) parent;
		}
	}

	public class FieldLabelProvider extends LabelProvider implements ITableLabelProvider {

		public String getColumnText(Viewer v, Object obj, int index) {
			return getColumnText(obj, index);
		}

		public Image getColumnImage(Viewer v, Object obj, int index) {
			return getColumnImage(obj, index);
		}

		public Image getColumnImage(Object obj, int index) {
			return null; // unchecked
		}

		public String getColumnText(Object obj, int index) {

			IExtension extension = (IExtension) obj;
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if (elements == null)
				return "";

			switch (index) {
			case 0: // Local
				return "";
			case 1: // Local
				return elements[0].getAttribute("name");
			}
			return "";
		}

	}

	protected int showWizard(final IExtension extension, Shell shell) {
		Wizard wizard = new TagPropertiesWizard(extension, preferenceStore);
		wizard.setForcePreviousAndNextButtons(true);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		return dialog.open();
	}
}
