/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

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
		@Override
		public Object[] getElements(Object element) {
			if (element instanceof List) {
				return ((List) element).toArray();
			}
			return new Object[0];
		}
		@Override
		public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
			//Default nothing
		}
		@Override
		public void dispose() {
			//Default nothing
		}
	}
	
	protected class StringArrayListLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
		    if (columnIndex == 0) {
		        return labelProviderImage;       
		    }
			return null;
		}
		
		@Override
		public String getColumnText(Object element, int columnIndex) {
			String[] array = (String[]) element;
			return array[columnIndex];
		}

        @Override
        public Image getImage(Object element) {
            return labelProviderImage;
        }

        @Override
        public String getText(Object element) {
			String[] array = (String[]) element;
			if (array.length > 0) {
				return array[0];
			}
			return super.getText(element);
		}
	}

	protected class AddStringArrayDialog extends Dialog implements ModifyListener {
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
		@Override
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
			Dialog.applyDialogFont(parent);
			return composite;
		}
		
		@Override
		protected Control createContents(Composite parent) {
			Composite composite = (Composite) super.createContents(parent);
			
			for (int i = 0; i < texts.length; i++) {
				texts[i].addModifyListener(this);
			}
			
			updateOKButton();
			
			return composite;
		}

		@Override
		protected void okPressed() {
			stringArray = callback.retrieveResultStrings(texts);
			super.okPressed();
		}

		public String[] getStringArray() {
			return stringArray;
		}
		
		@Override
		public void modifyText(ModifyEvent e) {
			updateOKButton();
		}
		
		private void updateOKButton() {
			getButton(IDialogConstants.OK_ID).setEnabled(callback.validate(texts));
		}
	}
	
	protected class EditStringArrayDialog extends AddStringArrayDialog {
		protected String[] valuesForTextField;
		/**
		 * CMPFieldDialog constructor comment.
		 */
		public EditStringArrayDialog(Shell shell, String windowTitle, String[] labelsForTextField, String[] valuesForTextField) {
			super(shell, windowTitle, labelsForTextField);
			this.valuesForTextField = valuesForTextField;
		}
		/**
		 * CMPFieldDialog constructor comment.
		 */
		@Override
		public Control createDialogArea(Composite parent) {

			Composite composite = (Composite) super.createDialogArea(parent);

			int n = valuesForTextField.length;
			for (int i = 0; i < n; i++) {
				texts[i].setText(valuesForTextField[i]);
			}
			
			return composite;
		}
	}
	
	/**
	 * Callback interface used by the Add/Edit-StringArrayDialog classes. 
	 */
	public interface StringArrayDialogCallback {
		
		/**
		 * Validates the text fields. 
		 * <p>Used to decide wheather to enable the OK button of the dialog. 
		 * If the method returns <code>true</code> the OK button is enabled, 
		 * otherwise the OK button is disabled.</p> 
		 * 
		 * @param reference to the text fields in the dialog
		 * 
		 * @return <code>true</code> if the values in the text fields are 
		 *         valid, <code>false</code> otherwise. 
		 */
		public boolean validate(Text[] texts);
		
		/**
		 * Retrieves the strings from the text fields of the dialog. 
		 * <p>Implementers of the callback can use these method to do some 
		 * preprocessing (like trimming) of the data in the text fields before 
		 * using it. The returned values will be the actual data that will be 
		 * put in the table viewer.</p> 
		 *  
		 * @param texts reference to the text fields in the dialog
		 * 
		 * @return the values retreived from the text fields
		 */
		public String[] retrieveResultStrings(Text[] texts);
		
	}
	
	/**
	 * Default adapter with basic implementation of the 
	 * <code>StringArrayDialogCallback</code> interface. 
	 */
	protected class StringArrayDialogCallbackAdapter implements StringArrayDialogCallback {
		
		/**
		 * Returns always <code>true</code>. 
		 */
		@Override
		public boolean validate(Text[] texts) {
			return true;
		}

		/**
		 * Just retreives the unmodified values of the text fields as a 
		 * string array. 
		 */
		@Override
		public String[] retrieveResultStrings(Text[] texts) {
			int n = texts.length;
			String[] result = new String[n];
			for (int i = 0; i < n; i++) {
				result[i] = texts[i].getText();
			}
			return result;
		}
		
	}

	private TableViewer viewer;
	private Button addButton;
	private Button editButton;
	private Button removeButton;
	private String dialogTitle;
	private String[] fieldLabels;
	private IDataModel model;
	private String propertyName;
	private Image labelProviderImage;
	private StringArrayDialogCallback callback;

	public StringArrayTableWizardSection(Composite parent, String title, String addButtonLabel, String removeButtonLabel, 
			String[] labelsForText, Image labelProviderImage, IDataModel model, String propertyName) {
		this(parent, title, addButtonLabel, null, removeButtonLabel, labelsForText, labelProviderImage, model, propertyName);
	}

	public StringArrayTableWizardSection(Composite parent, String title, String addButtonLabel, String editButtonLabel, String removeButtonLabel, 
			String[] labelsForText, Image labelProviderImage, IDataModel model, String propertyName) {
		this(parent, title, title, addButtonLabel, editButtonLabel, removeButtonLabel, labelsForText, labelsForText, labelProviderImage, model, propertyName);
	}

	public StringArrayTableWizardSection(Composite parent, String componentLabel, String dialogTitle, String addButtonLabel, String editButtonLabel, String removeButtonLabel, 
			String[] columnTitles, String[] fieldLabels, Image labelProviderImage, IDataModel model, String propertyName) {
		super(parent, SWT.NONE);
		this.dialogTitle = dialogTitle;
		this.fieldLabels = fieldLabels;
		this.labelProviderImage = labelProviderImage;
		this.model = model;
		this.propertyName = propertyName;

		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 4;
		layout.marginWidth = 0;
		this.setLayout(layout);
		this.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label titleLabel = new Label(this, SWT.LEFT);
		titleLabel.setText(componentLabel);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		titleLabel.setLayoutData(data);

		Table table = new Table(this, SWT.FULL_SELECTION | SWT.BORDER);
        viewer = new TableViewer(table);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new StringArrayListContentProvider());
		viewer.setLabelProvider(new StringArrayListLabelProvider());
		
		final Composite buttonCompo = new Composite(this, SWT.NULL);
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonCompo.setLayout(layout);
		buttonCompo.setLayoutData(new GridData(GridData.FILL_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING));

		addButton = new Button(buttonCompo, SWT.PUSH);
		addButton.setText(addButtonLabel);
		addButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		addButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleAddButtonSelected();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});

		if (editButtonLabel != null) {
			editButton = new Button(buttonCompo, SWT.PUSH);
			editButton.setText(editButtonLabel);
			editButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
			editButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					handleEditButtonSelected();
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent event) {
					//Do nothing
				}
			});
			editButton.setEnabled(false);
		}

		removeButton = new Button(buttonCompo, SWT.PUSH);
		removeButton.setText(removeButtonLabel);
		removeButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		removeButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleRemoveButtonSelected();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});
		removeButton.setEnabled(false);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (editButton != null) {
					boolean enabled = ((IStructuredSelection) selection).size() == 1;
					editButton.setEnabled(enabled);
				}
				removeButton.setEnabled(!selection.isEmpty());
			}
		});
		
		if (editButton != null) {
			viewer.addDoubleClickListener(new IDoubleClickListener() {
				@Override
				public void doubleClick(DoubleClickEvent event) {
					handleEditButtonSelected();
				}
			});
		}
		
		if (columnTitles.length > 1) {
            for (int i = 0; i < columnTitles.length; i++) {
                TableColumn tableColumn = new TableColumn(table, SWT.NONE);
                tableColumn.setText(columnTitles[i]);
            }
            table.setHeaderVisible(true);
            this.addControlListener(new ControlAdapter() {
                @Override
				public void controlResized(ControlEvent e) {
                    Table table = viewer.getTable();
                    TableColumn[] columns = table.getColumns();
                    Point buttonArea = buttonCompo.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                    Rectangle area = table.getParent().getClientArea();
                    Point preferredSize = viewer.getTable().computeSize(SWT.DEFAULT, SWT.DEFAULT);
                    int width = area.width - 2 * table.getBorderWidth() - buttonArea.x - columns.length * 2;
                    if (preferredSize.y > area.height + table.getHeaderHeight()) {
                        // Subtract the scrollbar width from the total column width
                        // if a vertical scrollbar will be required
                        Point vBarSize = table.getVerticalBar().getSize();
                        width -= vBarSize.x;
                    }
                    Point oldSize = table.getSize();
                    int consumeWidth = 0;
                    for (int i = 0; i < columns.length; i++) {
                        if (oldSize.x > area.width) {
                            // table is getting smaller so make the columns 
                            // smaller first and then resize the table to
                            // match the client area width
                            consumeWidth = setColumntWidth(width, columns, consumeWidth, i);
                            table.setSize(area.width - buttonArea.x - columns.length * 2, area.height);
                        } else {
                            // table is getting bigger so make the table 
                            // bigger first and then make the columns wider
                            // to match the client area width
                            table.setSize(area.width - buttonArea.x - columns.length * 2, area.height);
                            consumeWidth = setColumntWidth(width, columns, consumeWidth, i);
                        }
                    }
                }

                private int setColumntWidth(int width, TableColumn[] columns, int consumeWidth, int i) {
                	int localConsumeWidth = consumeWidth; 
                    if (i < columns.length - 1) {
                        columns[i].setWidth(width / columns.length);
                        localConsumeWidth += columns[i].getWidth();
                    } else {
                        columns[i].setWidth(width - localConsumeWidth);
                    }
                    return localConsumeWidth;
                }
            });
        }
        
		callback = new StringArrayDialogCallbackAdapter();
	}

	private void handleAddButtonSelected() {
		AddStringArrayDialog dialog = new AddStringArrayDialog(getShell(), dialogTitle, fieldLabels);
		dialog.open();
		String[] stringArray = dialog.getStringArray();
		addStringArray(stringArray);
	}

	private void handleEditButtonSelected() {
		ISelection s = viewer.getSelection();
		if (!(s instanceof IStructuredSelection))
			return;
		IStructuredSelection selection = (IStructuredSelection) s;
		if (selection.size() != 1)
			return;
		
		Object selectedObj = selection.getFirstElement();
		String[] valuesForText = (String[]) selectedObj;
		
		EditStringArrayDialog dialog = new EditStringArrayDialog(getShell(), dialogTitle, fieldLabels, valuesForText);
		dialog.open();
		String[] stringArray = dialog.getStringArray();
		editStringArray(valuesForText, stringArray);
	}

	private void handleRemoveButtonSelected() {
		ISelection selection = viewer.getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		List selectedObj = ((IStructuredSelection) selection).toList();
		removeStringArrays(selectedObj);
	}
	
	public void addStringArray(String[] stringArray) {
		if (stringArray == null)
			return;
		List valueList = (List) viewer.getInput();
		if (valueList == null)
			valueList = new ArrayList();
		valueList.add(stringArray);
		setInput(valueList);
	}

	public void editStringArray(String[] oldStringArray, String[] newStringArray) {
		if (newStringArray == null)
			return;
		
		List valueList = (List) viewer.getInput();
		if (valueList == null)
			valueList = new ArrayList();
		
		int index = valueList.indexOf(oldStringArray);
		if (index == -1) {
			valueList.add(newStringArray);
		} else {
			valueList.set(index, newStringArray);
		}
		
		setInput(valueList);
	}

	public void removeStringArray(Object selectedStringArray) {
		List valueList = (List) viewer.getInput();
		valueList.remove(selectedStringArray);
		setInput(valueList);
	}
	
	public void removeStringArrays(Collection selectedStringArrays) {
		List valueList = (List) viewer.getInput();
		valueList.removeAll(selectedStringArrays);
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

	public Button getEditButton() {
		return editButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}
	
	/**
	 * Set callback for customizing the preprocessing of the user input. 
	 * 
	 * @param callback an implementation of the callback interface. 
	 */
	public void setCallback(StringArrayDialogCallback callback) {
		this.callback = callback;
	}
}
