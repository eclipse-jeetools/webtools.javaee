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
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class StringArrayTableWizardSection extends Composite {

	protected class StringArrayListContentProvider implements IStructuredContentProvider {
		public boolean isDeleted(Object element) {
			return false;
		}
		public Object[] getElements(Object element) {
			if (element instanceof List) {
				return ((List) element).toArray();
			}
			return new Object[0];
		}
		public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
			//Default nothing
		}
		public void dispose() {
			//Default nothing
		}
	}
	protected class StringArrayListLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return labelProviderImage;
		}
		public String getText(Object element) {
			String[] array = (String[]) element;
			String s = array[0];
			return s;
		}
	}

	protected class AddStringArrayDialog extends Dialog {
		protected String windowTitle;
		protected String[] labelsForTextField;
		protected Text[] texts;
		protected String[] stringArray;
		/**
		 * CMPFieldDialog constructor comment.
		 */
		public AddStringArrayDialog(Shell shell, String windowTitle, String[] labelsForTextField) {
			super(shell);
			this.windowTitle = windowTitle;
			this.labelsForTextField = labelsForTextField;
		}
		/**
		 * CMPFieldDialog constructor comment.
		 */
		public Control createDialogArea(Composite parent) {

			Composite composite = (Composite) super.createDialogArea(parent);
			getShell().setText(windowTitle);

			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			composite.setLayout(layout);
			GridData data = new GridData();
			data.verticalAlignment = GridData.FILL;
			data.horizontalAlignment = GridData.FILL;
			data.widthHint = 300;
			composite.setLayoutData(data);

			int n = labelsForTextField.length;
			texts = new Text[n];
			for (int i = 0; i < n; i++) {
				Label label = new Label(composite, SWT.LEFT);
				label.setText(labelsForTextField[i]);
				label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				texts[i] = new Text(composite, SWT.SINGLE | SWT.BORDER);
				data = new GridData(GridData.FILL_HORIZONTAL);
				data.widthHint = 100;
				texts[i].setLayoutData(data);
			}

			// set focus
			texts[0].setFocus();
			return composite;
		}

		protected void okPressed() {
			int n = labelsForTextField.length;
			stringArray = new String[n];
			for (int i = 0; i < n; i++) {
				stringArray[i] = texts[i].getText();
			}
			super.okPressed();
		}

		public String[] getStringArray() {
			return stringArray;
		}
	}

	private TableViewer viewer;
	private Button addButton;
	private Button removeButton;
	private String title;
	private String[] labelsForText;
	private WTPOperationDataModel model;
	private String propertyName;
	private Image labelProviderImage;

	public StringArrayTableWizardSection(Composite parent, String title, String addButtonLabel, String removeButtonLabel, String[] labelsForText, Image labelProviderImage,
			WTPOperationDataModel model, String propertyName) {
		super(parent, SWT.NONE);
		this.title = title;
		this.labelsForText = labelsForText;
		this.labelProviderImage = labelProviderImage;
		this.model = model;
		this.propertyName = propertyName;

		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 4;
		layout.marginWidth = 0;
		this.setLayout(layout);
		this.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label titleLabel = new Label(this, SWT.LEFT);
		titleLabel.setText(title);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		titleLabel.setLayoutData(data);

		viewer = new TableViewer(this);
		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new StringArrayListContentProvider());
		viewer.setLabelProvider(new StringArrayListLabelProvider());

		Composite buttonCompo = new Composite(this, SWT.NULL);
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonCompo.setLayout(layout);
		buttonCompo.setLayoutData(new GridData(GridData.FILL_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING));

		addButton = new Button(buttonCompo, SWT.PUSH);
		addButton.setText(addButtonLabel);
		addButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				handleAddButtonSelected(event);
			}
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});

		removeButton = new Button(buttonCompo, SWT.PUSH);
		removeButton.setText(removeButtonLabel);
		removeButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		removeButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				handleRemoveButtonSelected(event);
			}
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});
		removeButton.setEnabled(false);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				removeButton.setEnabled(!selection.isEmpty());
			}
		});
	}

	private void handleAddButtonSelected(SelectionEvent event) {
		AddStringArrayDialog dialog = new AddStringArrayDialog(getShell(), title, labelsForText);
		dialog.open();
		String[] stringArray = dialog.getStringArray();
		addStringArray(stringArray);
	}

	private void handleRemoveButtonSelected(SelectionEvent event) {
		ISelection selection = viewer.getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		Object selectedObj = ((IStructuredSelection) selection).getFirstElement();
		removeStringArray(selectedObj);
	}

	public void addStringArray(String[] stringArray) {
		List valueList = new ArrayList();
		if (stringArray == null)
			return;
		valueList = (List) viewer.getInput();
		if (valueList == null)
			valueList = new ArrayList();
		valueList.add(stringArray);
		setInput(valueList);
	}

	public void removeStringArray(Object selectedStringArray) {
		List valueList = (List) viewer.getInput();
		valueList.remove(selectedStringArray);
		setInput(valueList);
	}

	public void setInput(List input) {
		viewer.setInput(input);
		// Create a new list to trigger property change
		List newInput = new ArrayList();
		newInput.addAll(input);
		model.setProperty(propertyName, newInput);
	}

	public TableViewer getTableViewer() {
		return viewer;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}
}