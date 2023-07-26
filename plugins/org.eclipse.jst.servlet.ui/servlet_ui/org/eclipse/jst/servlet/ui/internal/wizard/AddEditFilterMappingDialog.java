/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ERROR;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.FORWARD;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.INCLUDE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.REQUEST;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.URL_PATTERN_LABEL;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.internal.dialogs.TwoArrayQuickSorter;
import org.eclipse.jst.j2ee.internal.web.operations.FilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.operations.IFilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionStatusDialog;
import org.eclipse.ui.internal.layout.CellLayout;
import org.eclipse.ui.part.PageBook;

/**
 * Insert the type's description here.
 * Creation date: (7/30/2001 11:16:36 AM)
 */
public class AddEditFilterMappingDialog extends SelectionStatusDialog implements SelectionListener {	
	
	private static class TypeRenderer extends LabelProvider {
        private final Image SERVLET_ICON = 
            ImageDescriptor.createFromURL((URL) WebPlugin.getDefault().getImage("servlet")).createImage(); //$NON-NLS-1$

        @Override
		public String getText(Object element) {
            return ((String) element);
        }

        @Override
		public Image getImage(Object element) {
            return SERVLET_ICON;
        }

	}
	
    public final static int SERVLET = 0;
	public final static int URL_PATTERN = 1;
	protected Button fServletButton;
	protected Button fURLPatternButton;
	protected int fSelection = -1;
	protected PageBook fPageBook = null;
	protected Control fURLPatternControl = null;
	protected Control fServletControl = null;
	protected Composite fChild = null;
	// construction parameters
	protected IRunnableContext fRunnableContext;
	protected ILabelProvider fElementRenderer;
	private Object[] fElements;
	private boolean fIgnoreCase = true;
	private String fUpperListLabel;

	// SWT widgets
	private Table fUpperList;
	protected Text fURLText;
	private String[] fServletNames;
	private String[] fRenderedStrings;
	private int dispatchers;
	private Button fRequest;
	private Button fForward;
	private Button fInclude;
	private Button fErorr;
	private IFilterMappingItem selectedItem;

	/**
	 * MultiSelectFilteredFileSelectionDialog constructor comment.
	 * @param parent Shell
	 * @param title String
	 * @param message String
	 * @parent extensions String[]
	 * @param allowMultiple boolean
	 */
	public AddEditFilterMappingDialog(Shell parent, String title, IProject project, 
	        List<IFilterMappingItem> elements, IFilterMappingItem item) {
		super(parent);
		selectedItem = item;
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);

		if (title == null)
			setTitle(WebAppEditResourceHandler.getString("File_Selection_UI_")); //$NON-NLS-1$
		else setTitle(title);
		
		Status currStatus = new Status(Status.OK, ServletUIPlugin.PLUGIN_ID, Status.OK, "", null); //$NON-NLS-1$
		
		updateStatus(currStatus);
		fElementRenderer = new TypeRenderer();
		fRunnableContext = ServletUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		try {
            IModelProvider provider = ModelProviderManager.getModelProvider(project);
            Object mObj = provider.getModelObject();
            ArrayList<String> servletsList = new ArrayList<String>();
            if (mObj instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
                org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) mObj;
                List<Servlet> servlets = webApp.getServlets();
                for (Servlet servlet : servlets) {
                    String servletName = servlet.getServletName();
                    if (!servletName.equals(item != null ? item.getName() : null) && 
                            isAlreadyAdded(servletName, elements)) continue;
                    servletsList.add(servletName);
                }
            } else if (mObj instanceof org.eclipse.jst.javaee.web.WebApp) {
                org.eclipse.jst.javaee.web.WebApp webApp= (org.eclipse.jst.javaee.web.WebApp) mObj;
                List<org.eclipse.jst.javaee.web.Servlet> servlets = webApp.getServlets();
                for (org.eclipse.jst.javaee.web.Servlet servlet : servlets) {
                    String servletName = servlet.getServletName();
                    if (!servletName.equals(item != null ? item.getName() : null) && 
                            isAlreadyAdded(servletName, elements)) continue;
                    servletsList.add(servletName);
                }
            }
    
            fServletNames = servletsList.toArray(new String[servletsList.size()]);
            servletsList = null;
        } catch (Exception e) {
            ServletUIPlugin.log(e);
        }
	}
	
    private boolean isAlreadyAdded(String servlet, List<IFilterMappingItem> elements) {
        for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
            IFilterMappingItem item = (IFilterMappingItem) iterator.next();
            if (item.isServletNameType() && item.getName().equals(servlet)) 
                return true;
        }
        return false;
    }

    /**
	 * @private
	 */
    @Override
	protected void computeResult() {
        if (fSelection == URL_PATTERN) {
            java.util.List result = new ArrayList(1);           
            dispatchers = getDispatchers();
            FilterMappingItem mappingItem = 
                new FilterMappingItem(FilterMappingItem.URL_PATTERN, fURLText.getText().trim(), 
                        dispatchers);
            result.add(mappingItem);
            setResult(result);
        } else {
            String servletName = (String) getWidgetSelection();
            if (servletName == null) {
//              String title = WebAppEditResourceHandler.getString("Select_Class_UI_"); //$NON-NLS-1$ = "Select Class"
//              String message = WebAppEditResourceHandler.getString("Could_not_uniquely_map_the_ERROR_"); //$NON-NLS-1$ = "Could not uniquely map the class name to a class."
//              MessageDialog.openError(getShell(), title, message);
                setResult(null);
            } else {
                dispatchers = getDispatchers();
                java.util.List result = new ArrayList(1);
                FilterMappingItem mappingItem = 
                    new FilterMappingItem(FilterMappingItem.SERVLET_NAME, servletName, dispatchers);
                result.add(mappingItem);
                setResult(result);
            }
        }
    }

    private int getDispatchers() {
        int dispatchers = 0;
        if (fRequest.getSelection()) {
            dispatchers |= IFilterMappingItem.REQUEST;
        }
        if (fForward.getSelection()) {
            dispatchers |= IFilterMappingItem.FORWARD;
        }
        if (fInclude.getSelection()) {
            dispatchers |= IFilterMappingItem.INCLUDE;
        }
        if (fErorr.getSelection()) {
            dispatchers |= IFilterMappingItem.ERROR;
        }
        return dispatchers;
    }
	
	@Override
	public void create() {
		super.create();
        if (selectedItem == null && (fServletNames != null && fServletNames.length > 0)) fSelection = SERVLET; 
		updateOkState();
	}
	
	/**
	 * Creates and returns the contents of this dialog's 
	 * button bar.
	 * <p>
	 * The <code>Dialog</code> implementation of this framework method
	 * lays out a button bar and calls the <code>createButtonsForButtonBar</code>
	 * framework method to populate it. Subclasses may override.
	 * </p>
	 *
	 * @param parent the parent composite to contain the button bar
	 * @return the button bar control
	 */
	@Override
	protected Control createButtonBar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();

		layout.numColumns = 2;

		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite composite2 = new Composite(composite, SWT.NONE);

		// create a layout with spacing and margins appropriate for the font size.
		layout = new GridLayout();
		layout.numColumns = 0; // this is incremented by createButton
		layout.makeColumnsEqualWidth = true;
		layout.marginWidth = convertHorizontalDLUsToPixels(org.eclipse.jface.dialogs.IDialogConstants.HORIZONTAL_MARGIN);
		layout.marginHeight = convertVerticalDLUsToPixels(org.eclipse.jface.dialogs.IDialogConstants.VERTICAL_MARGIN);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(org.eclipse.jface.dialogs.IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing = convertVerticalDLUsToPixels(org.eclipse.jface.dialogs.IDialogConstants.VERTICAL_SPACING);

		composite2.setLayout(layout);

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER);
		composite2.setLayoutData(data);

		composite2.setFont(parent.getFont());

		// Add the buttons to the button bar.
		super.createButtonsForButtonBar(composite2);

		return composite;
	}
	
	/*
	 * @private
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		GridData gd = new GridData();

		fChild = new Composite(parent, SWT.NONE);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(fChild, "com.ibm.etools.webapplicationedit.webx2010"); //$NON-NLS-1$
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginHeight = 0;
		fChild.setLayout(gl);

		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessVerticalSpace = true;
		fChild.setLayoutData(gd);

		// Create the Web Type radio buttons and text fields.
		fServletButton = new Button(fChild, SWT.RADIO);
		fServletButton.setText(WebAppEditResourceHandler.getString("Servlet_UI_")); //$NON-NLS-1$ = Servlet
		gd = new GridData();
		fServletButton.setLayoutData(gd);
		fServletButton.addSelectionListener(this);

		fURLPatternButton = new Button(fChild, SWT.RADIO);
		fURLPatternButton.setText(WebAppEditResourceHandler.getString("URL_pattern_UI_")); //$NON-NLS-1$
		gd = new GridData();
		fURLPatternButton.setLayoutData(gd);
		fURLPatternButton.addSelectionListener(this);

		//Create URL Pattern page 
		fPageBook = new PageBook(fChild, SWT.NONE);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalSpan = 2;
		fPageBook.setLayoutData(gd);
		
        Composite composite = new Composite(fPageBook, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());
        
        Label messageLabel = new Label(composite, SWT.NONE);
        gd = new GridData();
        messageLabel.setLayoutData(gd);
        messageLabel.setText(URL_PATTERN_LABEL);

        fURLText = new Text(composite, SWT.BORDER);
        GridData spec = new GridData();
        spec.grabExcessVerticalSpace = false;
        spec.grabExcessHorizontalSpace = true;
        spec.horizontalAlignment = GridData.FILL;
        spec.verticalAlignment = GridData.BEGINNING;
        fURLText.setLayoutData(spec);
        Listener l = new Listener() {
            @Override
			public void handleEvent(Event evt) {
                updateOkState();
            }
        };
        fURLText.addListener(SWT.Modify, l);
        fURLPatternControl = composite;

        //Create Servlet page
        composite = new Composite(fPageBook, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());

        messageLabel = new Label(composite, SWT.NONE);
        gd = new GridData();
        messageLabel.setLayoutData(gd);
        messageLabel.setText(WebAppEditResourceHandler.getString("Choose_a_servlet__1")); //$NON-NLS-1$

        fUpperList = createUpperList(composite);

        fServletControl = composite;

        //Create Dispatchers control
        Group dispatchers = new Group(fChild, SWT.SHADOW_IN);
        dispatchers.setText(WebAppEditResourceHandler.getString("Select_Dispatchers_UI_")); //$NON-NLS-1$
        dispatchers.setLayout(new CellLayout(2).setMargins(10,10).setSpacing(5,5));
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        gridData.horizontalSpan = 2;
        dispatchers.setLayoutData(gridData);
        fRequest = new Button(dispatchers, SWT.CHECK);
        fRequest.setText(REQUEST);
        fForward = new Button(dispatchers, SWT.CHECK);
        fForward.setText(FORWARD);
        fInclude = new Button(dispatchers, SWT.CHECK);
        fInclude.setText(INCLUDE);
        fErorr = new Button(dispatchers, SWT.CHECK);
        fErorr.setText(ERROR);
        
        if (selectedItem != null) {
            if (selectedItem.isUrlPatternType()) {
                fSelection = URL_PATTERN;
            } else {
                fSelection = SERVLET;
            }
        } else {
            if (fServletNames == null || fServletNames.length == 0) {
                fSelection = URL_PATTERN;
            }
        }
        updateUpperListWidget();

        fServletButton.setEnabled(true);
        if (fSelection == URL_PATTERN) {
            fURLPatternButton.setSelection(true);
            fPageBook.showPage(fURLPatternControl);
            fServletButton.setSelection(false);
            if (fServletNames == null || fServletNames.length == 0) {
                fServletButton.setEnabled(false);
            }
            if (selectedItem != null) {
                fURLText.setText(selectedItem.getName());
                setDispatchers(selectedItem.getDispatchers());              
            }
        } else {
            fServletButton.setSelection(true);
            fPageBook.showPage(fServletControl);
            if (selectedItem != null) {
                fUpperList.setSelection(getServletIndex(fUpperList, selectedItem.getName()));
                setDispatchers(selectedItem.getDispatchers());              
            }
        }
		updateOkState();
		return parent;
	}
	
    private int getServletIndex(Table list, String name) {
        TableItem[] items = list.getItems();
        for (int i = 0; i < items.length; i++) {
            TableItem tableItem = items[i];
            if (tableItem.getText().equals(name)) return i; 
        }
        return -1;
    }

	private void setDispatchers(int dispatchers) {
        if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
            fRequest.setSelection(true);
        }
        if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
            fForward.setSelection(true);
        }
        if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
            fInclude.setSelection(true);
        }
        if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
            fErorr.setSelection(true);
        }
    }
	
	/**
	 * Creates the list widget and sets layout data.
	 * @return org.eclipse.swt.widgets.List
	 */
	private Table createUpperList(Composite parent) {
		if (fUpperListLabel != null)
			 (new Label(parent, SWT.NONE)).setText(fUpperListLabel);

		Table list = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
//		list.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event evt) {
//				handleUpperSelectionChanged();
//			}
//		});
		list.addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event evt) {
				handleUpperDoubleClick();
			}
		});
		list.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				fElementRenderer.dispose();
			}
		});
		GridData spec = new GridData();
		spec.widthHint = convertWidthInCharsToPixels(50);
		spec.heightHint = convertHeightInCharsToPixels(4);
		spec.grabExcessVerticalSpace = true;
		spec.grabExcessHorizontalSpace = true;
		spec.horizontalAlignment = GridData.FILL;
		spec.verticalAlignment = GridData.FILL;
		list.setLayoutData(spec);
		return list;
	}
	
	/**
	 * @return the ID of the button that is 'pressed' on doubleClick in the lists.
	 * By default it is the OK button.
	 * Override to change this setting.
	 */
	protected int getDefaultButtonID() {
		return IDialogConstants.OK_ID;
	}
	
	public int getSelectedItem() {
		return fSelection;
	}
	
	protected Object getWidgetSelection() {
        int index = fUpperList.getSelectionIndex();
        return index >= 0 ? fElements[index] : null;
	}
	
	protected final void handleLowerDoubleClick() {
		if (getWidgetSelection() != null)
			buttonPressed(getDefaultButtonID());
	}
	
	protected final void handleLowerSelectionChanged() {
		updateOkState();
	}
	
	protected final void handleUpperDoubleClick() {
		if (getWidgetSelection() != null)
			buttonPressed(getDefaultButtonID());
	}
	
	@Override
	public int open() {
        if (fServletNames == null || fServletNames.length == 0) {
            fSelection = URL_PATTERN;
        }

        setElements(fServletNames);
        setInitialSelections(new Object[] { "" }); //$NON-NLS-1$
        return super.open();
	}
	
	/**
	 * 
	 * @return java.lang.String[]
	 * @param p org.eclipse.jface.elements.IIndexedProperty
	 */
	private String[] renderStrings(Object[] p) {
		String[] strings = new String[p.length];
		int size = strings.length;
		for (int i = 0; i < size; i++) {
			strings[i] = fElementRenderer.getText(p[i]);
		}
		new TwoArrayQuickSorter(fIgnoreCase).sort(strings, p);
		return strings;
	}

	public void setElements(Object[] elements) {
	    Object [] innerElements = elements;
		if (innerElements == null){
			innerElements = new Object[0];
	    }
		fElements = innerElements;
		fRenderedStrings = renderStrings(fElements);
	}

	public void setSelectedItem(int newSelection) {
		fSelection = newSelection;
	}
	
	private void updateOkState() {
		Button okButton = getOkButton();
		if (okButton != null)
		    if (fSelection == SERVLET) {
		        okButton.setEnabled(getWidgetSelection() != null);
		    } else {
		        String result = fURLText.getText().trim();		        
		        okButton.setEnabled(result.length() > 0);
		    }
	}
	
    private void updateUpperListWidget() {
        fUpperList.setRedraw(false);
        fUpperList.clearAll();
        for (int i = 0; i < fRenderedStrings.length; i++) {
            TableItem ti = new TableItem(fUpperList, SWT.NONE);
            ti.setText(fRenderedStrings[i]);
            // XXX: 1G65LDG: JFUIF:WIN2000 - ILabelProvider used outside a viewer
            Image img = fElementRenderer.getImage(fRenderedStrings[i]);
            ti.setImage(img);
        }
        if (fUpperList.getItemCount() > 0) {
            fUpperList.setSelection(0);
        }
        fUpperList.setRedraw(true);
    }
	
	/**
	 * Sent when default selection occurs in the control.
	 * <p>
	 * For example, on some platforms default selection occurs
	 * in a List when the user double-clicks an item or types
	 * return in a Text.
	 * </p>
	 *
	 * @param e an event containing information about the default selection
	 */
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// Do nothing
	}
	
	/**
	 * Sent when selection occurs in the control.
	 * <p>
	 * For example, on some platforms selection occurs in
	 * a List when the user selects an item or items.
	 * </p>
	 *
	 * @param e an event containing information about the selection
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.widget == fURLPatternButton) {
			fSelection = URL_PATTERN;
			fPageBook.showPage(fURLPatternControl);
			updateOkState();
		} else if (e.widget == fServletButton) {
			fSelection = SERVLET;
			fPageBook.showPage(fServletControl);
			getShell().pack();
			updateOkState();
		} else
			fSelection = -1;
	}
}
