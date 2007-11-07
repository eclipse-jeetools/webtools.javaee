package org.eclipse.jst.servlet.ui.internal.wizard;

/**
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.internal.dialogs.TwoArrayQuickSorter;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.web.operations.FilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.operations.IFilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.SelectionStatusDialog;
import org.eclipse.ui.internal.layout.CellLayout;
import org.eclipse.ui.part.PageBook;

/**
 * Insert the type's description here.
 * Creation date: (7/30/2001 11:16:36 AM)
 */
public class AddEditFilterMappingDialog extends SelectionStatusDialog implements SelectionListener {	

	private static class PackageRenderer extends LabelProvider {
		private final Image PACKAGE_ICON = JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_PACKAGE); 

		public String getText(Object element) {
			IType type = (IType) element;
			String p = type.getPackageFragment().getElementName();
			if ("".equals(p)) //$NON-NLS-1$
				p = IWebWizardConstants.DEFAULT_PACKAGE;
			return (p + " - " + type.getPackageFragment().getParent().getPath().toString()); //$NON-NLS-1$
		}
		public Image getImage(Object element) {
			return PACKAGE_ICON;
		}
	}

	private static class TypeRenderer extends LabelProvider {
		private final Image CLASS_ICON = JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_CLASS); 

		public String getText(Object element) {
			IType e = ((IType) element);
			return e.getElementName();
		}

		public Image getImage(Object element) {
			return CLASS_ICON;
		}

	}
	private ISelectionStatusValidator fValidator = null;
	
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
	protected ILabelProvider fQualifierRenderer;
	private Object[] fElements;
	private boolean fIgnoreCase = true;
	private String fUpperListLabel;
	private String fLowerListLabel;
	// SWT widgets
	private Table fUpperList;
	private Table fLowerList;
	protected Text fText;
	protected Text fURLText;
	private IType[] fIT;
	private String[] fRenderedStrings;
	private int[] fElementMap;
	private Integer[] fQualifierMap;
	private int dispatchers;
	private Button fRequest;
	private Button fForward;
	private Button fInclude;
	private Button fErorr;
	private IFilterMappingItem selectedItem;

	private ISelectionStatusValidator fLocalValidator = null;
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
		fLocalValidator = new SimpleTypedElementSelectionValidator(new Class[] { IFile.class }, false);
		
		Status currStatus = new Status(Status.OK, ServletUIPlugin.PLUGIN_ID, Status.OK, "", null);
		
		updateStatus(currStatus);
		fElementRenderer = new TypeRenderer();
		fQualifierRenderer = new PackageRenderer();
		fRunnableContext = J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		try {
			IJavaElement jelem = null;
			IProject proj = null;
			jelem = (IJavaElement) project.getAdapter(IJavaElement.class);
			if (jelem == null) {
				IResource resource = (IResource) project.getAdapter(IResource.class);
				if (resource != null) {
					proj = resource.getProject();
					if (proj != null) {
						jelem = org.eclipse.jdt.core.JavaCore.create(proj);
					}
				}
			}
			IJavaProject jp = jelem.getJavaProject();

			IType servletType = jp.findType("javax.servlet.Servlet"); //$NON-NLS-1$
			// next 3 lines fix defect 177686
			if (servletType == null) {
				return;
			}

			ArrayList servletClasses = new ArrayList();
			ITypeHierarchy tH = servletType.newTypeHierarchy(jp, null);
			IType[] types = tH.getAllSubtypes(servletType);
			for (int i = 0; i < types.length; i++) {
				if (types[i].isClass() && !types[i].isBinary() && !servletClasses.contains(types[i])) {
				    if (!types[i].equals(item != null ? item.getMapping() : null) && 
				            isAlreadyAdded(types[i], elements)) continue;
				    servletClasses.add(types[i]);
				}
			}
			fIT = (IType[]) servletClasses.toArray(new IType[servletClasses.size()]);
			servletClasses = null;
		} catch (Exception exc) {
			Logger.getLogger().logError(exc);
		}
	}
	
	private boolean isAlreadyAdded(IType type, List<IFilterMappingItem> elements) {
        for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
            IFilterMappingItem item = (IFilterMappingItem) iterator.next();
            if (item.getMappingType() == IFilterMappingItem.SERVLET_NAME) {
                if (item.getMapping().equals(type)) return true;
            }
        }
        return false;
    }

    /**
	 * @private
	 */
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
			IType type = (IType) getWidgetSelection();
			if (type != null) {
				if (type == null) {
					String title = WebAppEditResourceHandler.getString("Select_Class_UI_"); //$NON-NLS-1$ = "Select Class"
					String message = WebAppEditResourceHandler.getString("Could_not_uniquely_map_the_ERROR_"); //$NON-NLS-1$ = "Could not uniquely map the class name to a class."
					MessageDialog.openError(getShell(), title, message);
					setResult(null);
				} else {
				    dispatchers = getDispatchers();
					java.util.List result = new ArrayList(1);
					FilterMappingItem mappingItem = 
					    new FilterMappingItem(FilterMappingItem.SERVLET_NAME, type, dispatchers);
					result.add(mappingItem);
					setResult(result);
				}
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
	
	public void create() {
		super.create();
		fText.setFocus();
		rematch(""); //$NON-NLS-1$
		if (selectedItem == null && (fIT != null && fIT.length > 0)) fSelection = SERVLET; 
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

		fPageBook = new PageBook(fChild, SWT.NONE);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalSpan = 2;
		fPageBook.setLayoutData(gd);
		
		//fURLPatternControl = super.createDialogArea(fPageBook);
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
        messageLabel.setText(IWebWizardConstants.URL_PATTERN_LABEL); //$NON-NLS-1$

        fURLText = new Text(composite, SWT.BORDER);
        GridData spec = new GridData();
        spec.grabExcessVerticalSpace = false;
        spec.grabExcessHorizontalSpace = true;
        spec.horizontalAlignment = GridData.FILL;
        spec.verticalAlignment = GridData.BEGINNING;
        fURLText.setLayoutData(spec);
        Listener l = new Listener() {
            public void handleEvent(Event evt) {
                updateOkState();
            }
        };
        fURLText.addListener(SWT.Modify, l);
        fURLPatternControl = composite;

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

		fText = createText(composite);

		messageLabel = new Label(composite, SWT.NONE);
		gd = new GridData();
		messageLabel.setLayoutData(gd);
		messageLabel.setText(WebAppEditResourceHandler.getString("Matching_servlets__2")); //$NON-NLS-1$

		fUpperList = createUpperList(composite);

		messageLabel = new Label(composite, SWT.NONE);
		gd = new GridData();
		messageLabel.setLayoutData(gd);
		messageLabel.setText(WebAppEditResourceHandler.getString("Qualifier__3")); //$NON-NLS-1$

		fLowerList = createLowerList(composite);

		fServletControl = composite;

	    //Create Dispatchers control
        Group dispatchers = new Group(fChild, SWT.SHADOW_IN);
        dispatchers.setText("Select Dispatchers");
        dispatchers.setLayout(new CellLayout(2).setMargins(10,10).setSpacing(5,5));
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        gridData.horizontalSpan = 2;
        dispatchers.setLayoutData(gridData);
        fRequest = new Button(dispatchers, SWT.CHECK);
        fRequest.setText(IWebWizardConstants.REQUEST);
        fForward = new Button(dispatchers, SWT.CHECK);
        fForward.setText(IWebWizardConstants.FORWARD);
        fInclude = new Button(dispatchers, SWT.CHECK);
        fInclude.setText(IWebWizardConstants.INCLUDE);
        fErorr = new Button(dispatchers, SWT.CHECK);
        fErorr.setText(IWebWizardConstants.ERROR);
        
        if (selectedItem != null) {
            if (selectedItem.getMappingType() == IFilterMappingItem.URL_PATTERN) {
                fSelection = URL_PATTERN;
            } else {
                fSelection = SERVLET;
            }
        }
        
		if (fSelection == URL_PATTERN) {
		    fURLPatternButton.setSelection(true);
			fPageBook.showPage(fURLPatternControl);
			if (fIT.length == 0) {
			    fServletButton.setSelection(false);
	            fServletButton.setEnabled(false);
			}
            if (selectedItem != null) {
                fURLText.setText(selectedItem.getName());
                setDispatchers(selectedItem.getDispatchers());              
            }
		} else {
		    fServletButton.setEnabled(true);
		    fServletButton.setSelection(true);
			fPageBook.showPage(fServletControl);
	        if (selectedItem != null) {
	            fText.setText(selectedItem.getName());
	            setDispatchers(selectedItem.getDispatchers());	            
	        }
		}
		updateOkState();
		return parent;
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
	private Table createLowerList(Composite parent) {
		if (fLowerListLabel != null)
			 (new Label(parent, SWT.NONE)).setText(fLowerListLabel);

		Table list = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		list.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event evt) {
				handleLowerSelectionChanged();
			}
		});
		list.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event evt) {
				handleLowerDoubleClick();
			}
		});
		list.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				fQualifierRenderer.dispose();
			}
		});
		GridData spec = new GridData();
		spec.widthHint = convertWidthInCharsToPixels(50);
		spec.heightHint = convertHeightInCharsToPixels(2);
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
	 * Creates the list widget and sets layout data.
	 * @return org.eclipse.swt.widgets.List
	 */
	private Table createUpperList(Composite parent) {
		if (fUpperListLabel != null)
			 (new Label(parent, SWT.NONE)).setText(fUpperListLabel);

		Table list = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		list.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event evt) {
				handleUpperSelectionChanged();
			}
		});
		list.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event evt) {
				handleUpperDoubleClick();
			}
		});
		list.addDisposeListener(new DisposeListener() {
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
		int i = fLowerList.getSelectionIndex();
		if (fQualifierMap != null) {
			if (fQualifierMap.length == 1)
				i = 0;
			if (i < 0) {
				return null;
			} 
			Integer index = fQualifierMap[i];
			return fElements[index.intValue()];
		}
		return null;
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
	
	protected final void handleUpperSelectionChanged() {
		int selection = fUpperList.getSelectionIndex();
		if (selection >= 0) {
			int i = fElementMap[selection];
			int k = i;
			int length = fRenderedStrings.length;
			while (k < length && fRenderedStrings[k].equals(fRenderedStrings[i])) {
				k++;
			}
			updateLowerListWidget(i, k);
		} else
			updateLowerListWidget(0, 0);
	}
	
	public int open() {
		if (fIT == null || fIT.length == 0) {
		    fSelection = URL_PATTERN;
		}

		setElements(fIT);
		setInitialSelections(new Object[] { "" }); //$NON-NLS-1$
		return super.open();
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
			while (i < length && fRenderedStrings[i].equals(lastString))
				i++;
			if (i < length) {
				lastString = fRenderedStrings[i];
				if (matcher.match(fRenderedStrings[i])) {
					fElementMap[k] = i;
					k++;
				}
			}
		}
		fElementMap[k] = -1;

		updateUpperListWidget(fElementMap, k);
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
	    if (elements == null) elements = new Object[0]; 
		fElements = elements;
		fElementMap = new int[fElements.length + 1];
		fRenderedStrings = renderStrings(fElements);
	}

	public void setSelectedItem(int newSelection) {
		fSelection = newSelection;
	}

	private void updateLowerListWidget(int from, int to) {
		fLowerList.removeAll();
		fQualifierMap = new Integer[to - from];
		String[] qualifiers = new String[to - from];
		for (int i = from; i < to; i++) {
			// XXX: 1G65LDG: JFUIF:WIN2000 - ILabelProvider used outside a viewer
			qualifiers[i - from] = fQualifierRenderer.getText(fElements[i]);
			fQualifierMap[i - from] = new Integer(i);
		}

		new TwoArrayQuickSorter(fIgnoreCase).sort(qualifiers, fQualifierMap);

		for (int i = 0; i < to - from; i++) {
			TableItem ti = new TableItem(fLowerList, i);
			ti.setText(qualifiers[i]);
			// XXX: 1G65LDG: JFUIF:WIN2000 - ILabelProvider used outside a viewer
			Image img = fQualifierRenderer.getImage(fElements[from + i]);
			if (img != null)
				ti.setImage(img);
		}

		if (fLowerList.getItemCount() > 0)
			fLowerList.setSelection(0);
		updateOkState();
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
	
	private void updateUpperListWidget(int[] indices, int size) {
		fUpperList.setRedraw(false);
		int itemCount = fUpperList.getItemCount();
		if (size < itemCount)
			fUpperList.remove(0, itemCount - size - 1);
		TableItem[] items = fUpperList.getItems();
		for (int i = 0; i < size; i++) {
			TableItem ti = null;
			if (i < itemCount)
				ti = items[i];
			else
				ti = new TableItem(fUpperList, i);
			ti.setText(fRenderedStrings[indices[i]]);
			// XXX: 1G65LDG: JFUIF:WIN2000 - ILabelProvider used outside a viewer
			Image img = fElementRenderer.getImage(fElements[indices[i]]);
			if (img != null)
				ti.setImage(img);
		}
		if (fUpperList.getItemCount() > 0)
			fUpperList.setSelection(0);
		fUpperList.setRedraw(true);
		handleUpperSelectionChanged();
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
	public void widgetSelected(SelectionEvent e) {
		if (e.widget == fURLPatternButton) {
			fSelection = URL_PATTERN;
			fPageBook.showPage(fURLPatternControl);
			updateOkState();
		} else if (e.widget == fServletButton) {
			fSelection = SERVLET;
			fPageBook.showPage(fServletControl);
			getShell().pack();
		} else
			fSelection = -1;
	}
	
	/**
	 * @see ElementTreeSelectionDialog#updateOKStatus()
	 */
	protected void updateOKStatus() {
		Button okButton = getOkButton();
		if (okButton != null)
			okButton.setEnabled(fLocalValidator.validate(getResult()).isOK());
	}

}
