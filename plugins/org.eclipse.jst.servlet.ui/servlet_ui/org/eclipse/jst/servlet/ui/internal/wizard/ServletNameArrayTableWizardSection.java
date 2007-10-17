/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ServletNameArrayTableWizardSection extends Composite {

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
	    protected String result;
	    private Table fServletList;
	    protected Text fText;
	    protected ILabelProvider fElementRenderer;
	    private int[] fElementMap;
	    private String[] fElements;
	    private boolean fIgnoreCase = true;
	    
	    private class TypeRenderer extends LabelProvider {
	        private final Image CLASS_ICON = JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_CLASS); 

	        public String getText(Object element) {
	            return (String) element;
	        }

	        public Image getImage(Object element) {
	            return CLASS_ICON;
	        }

	    }
	    
	    public AddStringArrayDialog(Shell shell, String windowTitle, String[] labelsForTextField) {
	        super(shell);
	        fElementRenderer = new TypeRenderer();
	    }
	    
	    /**
	     * CMPFieldDialog constructor comment.
	     */
	    public Control createDialogArea(Composite parent) {
	        Composite composite = (Composite) super.createDialogArea(parent);
	        getShell().setText("Select Servlet Name");
	        
	        GridLayout layout = new GridLayout();
	        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
	        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
	        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
	        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
	        composite.setLayout(layout);
	        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	        composite.setFont(parent.getFont());

	        Label messageLabel = new Label(composite, SWT.NONE);
	        GridData gd = new GridData();
	        messageLabel.setLayoutData(gd);
	        messageLabel.setText("Choose a servlet name:"); //$NON-NLS-1$

	        fText = createText(composite);
	        
	        messageLabel = new Label(composite, SWT.NONE);
	        gd = new GridData();
	        messageLabel.setLayoutData(gd);
	        messageLabel.setText("Servlet Names"); //$NON-NLS-1$

	        fServletList = createServletsList(composite);
	        
	        // set focus
	        //texts[0].setFocus();
	        Dialog.applyDialogFont(parent);
	        return composite;
	    }

	    protected Control createContents(Composite parent) {
	        Composite composite = (Composite) super.createContents(parent);
	        updateOKButton();
	        return composite;
	    }
	    
	    /**
	     * update the list to reflect a new match string.
	     * @param matchString java.lang.String
	     */
	    protected final void rematch(String matchString) {
	        int k = 0;
	        String text = fText.getText();
	        StringMatcher matcher = new StringMatcher(text + "*", fIgnoreCase, false); //$NON-NLS-1$
	        String lastString = null;
	        int length = fElements.length;
	        for (int i = 0; i < length; i++) {
	            while (i < length && fElements[i].equals(lastString))
	                i++;
	            if (i < length) {
	                lastString = fElements[i];
	                if (matcher.match(fElements[i])) {
	                    fElementMap[k] = i;
	                    k++;
	                }
	            }
	        }
	        fElementMap[k] = -1;

	        updateServletsListWidget(fElementMap, k);
	    }
	    
	    private void updateServletsListWidget(int[] indices, int size) {
	        fServletList.setRedraw(false);
	        int itemCount = fServletList.getItemCount();
	        if (size < itemCount)
	            fServletList.remove(0, itemCount - size - 1);
	        TableItem[] items = fServletList.getItems();
	        for (int i = 0; i < size; i++) {
	            TableItem ti = null;
	            if (i < itemCount)
	                ti = items[i];
	            else
	                ti = new TableItem(fServletList, i);
	            ti.setText(fElements[indices[i]]);
	            Image img = fElementRenderer.getImage(fElements[indices[i]]);
	            if (img != null)
	                ti.setImage(img);
	        }
	        if (fServletList.getItemCount() > 0)
	            fServletList.setSelection(0);
	        fServletList.setRedraw(true);
	        handleServletSelectionChanged();
	    }
	    
	    protected void okPressed() {
	        TableItem[] selection = fServletList.getSelection();
            result = fText.getText();
            if (selection.length > 0) {
                result = selection[0].getText(); 
            }
            callback.getServlets().remove(result);
	        super.okPressed();
	    }

	    public String getResult() {
	        return result;
	    }

	    private void updateOKButton() {
	        TableItem[] selection = fServletList.getSelection();
	        String result = fText.getText();
	        if (selection.length > 0) {
	            result = selection[0].getText(); 
	        }
	        getButton(IDialogConstants.OK_ID).setEnabled(callback.validate(result));
	    }
	    
	    /**
	     * Creates the list widget and sets layout data.
	     * @return org.eclipse.swt.widgets.List
	     */
	    private Table createServletsList(Composite parent) {
	        Table list = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
	        list.addListener(SWT.Selection, new Listener() {
	            public void handleEvent(Event evt) {
	                handleServletSelectionChanged();
	            }
	        });
	        list.addListener(SWT.MouseDoubleClick, new Listener() {
	            public void handleEvent(Event evt) {
	                handleServletDoubleClick();
	            }
	        });
	        list.addDisposeListener(new DisposeListener() {
	            public void widgetDisposed(DisposeEvent e) {
	                fElementRenderer.dispose();
	            }
	        });
	        GridData spec = new GridData();
	        spec.widthHint = convertWidthInCharsToPixels(50);
	        spec.heightHint = convertHeightInCharsToPixels(15);
	        spec.grabExcessVerticalSpace = true;
	        spec.grabExcessHorizontalSpace = true;
	        spec.horizontalAlignment = GridData.FILL;
	        spec.verticalAlignment = GridData.FILL;
	        list.setLayoutData(spec);
	        return list;
	    }
	    
	    /**
	     * Creates the text widget and sets layout data.
	     * @return org.eclipse.swt.widgets.Text
	     */
	    private Text createText(Composite parent) {
	        Text text = new Text(parent, SWT.BORDER);
	        GridData spec = new GridData();
	        spec.grabExcessVerticalSpace = false;
	        spec.grabExcessHorizontalSpace = true;
	        spec.horizontalAlignment = GridData.FILL;
	        spec.verticalAlignment = GridData.BEGINNING;
	        text.setLayoutData(spec);
	        Listener l = new Listener() {
	            public void handleEvent(Event evt) {
	                rematch(fText.getText());
	            }
	        };
	        text.addListener(SWT.Modify, l);
	        return text;
	    }
	    
	    /**
	     * @return the ID of the button that is 'pressed' on doubleClick in the lists.
	     * By default it is the OK button.
	     * Override to change this setting.
	     */
	    protected int getDefaultButtonID() {
	        return IDialogConstants.OK_ID;
	    }
	    
	    protected final void handleServletDoubleClick() {
	        int selection = fServletList.getSelectionIndex();
	        if (selection > -1) {
	            buttonPressed(getDefaultButtonID());
	        }
	    }
	    
	    protected final void handleServletSelectionChanged() {
	        int selection = fServletList.getSelectionIndex();
	        if (selection >= 0) {
	            int i = fElementMap[selection];
	            int k = i;
	            int length = fElements.length;
	            while (k < length && fElements[k].equals(fElements[i])) {
	                k++;
	            }
	        }
	        updateOKButton();
	    }

        @Override
        public void create() {
            super.create();
            fText.setFocus();
            rematch(""); //$NON-NLS-1$
            updateOKButton();
        }

        @Override
        public int open() {
            setElements(callback.getServletNames());
            //setInitialSelection(""); //$NON-NLS-1$
            return super.open();
        }
	    
        public void setElements(String[] elements) {
            fElements = elements;
            fElementMap = new int[fElements.length + 1];
        }
	}
	

	private TableViewer viewer;
	private Button addButton;
	private Button removeButton;
	private String title;
	private String[] labelsForText;
	private IDataModel model;
	private String propertyName;
	private Image labelProviderImage;
	private ServletNamesArrayTableWizardSectionCallback callback;

    public ServletNameArrayTableWizardSection(Composite parent, String title, String addButtonLabel, String removeButtonLabel, 
            String[] labelsForText, Image labelProviderImage, IDataModel model, String propertyName) {
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
                handleAddButtonSelected();
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
                handleRemoveButtonSelected();
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
        
        callback = new ServletNamesArrayTableWizardSectionCallback(null);
	}

	private void handleAddButtonSelected() {
		AddStringArrayDialog dialog = new AddStringArrayDialog(getShell(), title, labelsForText);
		dialog.open();
		String result = dialog.getResult();
		addServletName(result != null ? new String[] { result } : null);
	}

	private void handleRemoveButtonSelected() {
		ISelection selection = viewer.getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		List<String[]> selectedObj = ((IStructuredSelection) selection).toList();
		removeStringArrays(selectedObj);
		List<String> servlets = callback.getServlets();
		for (String[] servlet : selectedObj) {
		    servlets.add(servlet[0]);
        }
		
	}
	
	public void addServletName(String[] servletName) {
		if (servletName == null)
			return;
		List valueList = (List) viewer.getInput();
		if (valueList == null)
			valueList = new ArrayList();
		valueList.add(servletName);
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

	public Button getRemoveButton() {
		return removeButton;
	}
	
	/**
	 * Set callback for customizing the preprocessing of the user input. 
	 * 
	 * @param callback an implementation of the callback interface. 
	 */
	public void setCallback(ServletNamesArrayTableWizardSectionCallback callback) {
		this.callback = callback;
	}
}
