/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.jst.j2ee.internal.web.operations.FilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.operations.IFilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;

public class FilterMappingsArrayTableWizardSection extends Composite implements IDataModelListener {

	protected class StringArrayListContentProvider implements IStructuredContentProvider {
		public boolean isDeleted(Object element) {
			return false;
		}
		public Object[] getElements(Object element) {
			if (element instanceof List) {
				return ((List<FilterMappingItem>) element).toArray();
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
	protected class StringArrayListLabelProvider extends LabelProvider implements ITableLabelProvider {
        private final Image URL_PATTERN_ICON = 
            ImageDescriptor.createFromURL((URL) WebPlugin.getDefault().getImage("url_mapping")).createImage(); //$NON-NLS-1$
        private final Image SERVLET_ICON = 
            ImageDescriptor.createFromURL((URL) WebPlugin.getDefault().getImage("servlet")).createImage(); //$NON-NLS-1$

        public Image getColumnImage(Object element, int columnIndex) {
		    if (columnIndex == 0) {
		        FilterMappingItem item = (FilterMappingItem) element;
		        if (item.isServletNameType()) {
		            return SERVLET_ICON;
		        } else if (item.isUrlPatternType()) {
		            return URL_PATTERN_ICON;
		        }
		        
		    }
		    return null;
		}
		
		public String getColumnText(Object element, int columnIndex) {
		    if (columnIndex == 1) {
		        return ((FilterMappingItem) element).getName();
		    } else if (columnIndex == 2) {
		        return ((FilterMappingItem) element).getDispatchersAsString();
		    }
		    return null;
        }

	}
	
	final static int ICON_WIDTH = 25;
	final static int DISPATCHERS_WIDTH = 100;
	private final static String ICON_PROP = "ICON_PROP"; //$NON-NLS-1$
	private final static String NAME_PROP = "NAME_PROP"; //$NON-NLS-1$
	private final static String DISPATCHERS_PROP = "DISPATCHERS_PROP"; //$NON-NLS-1$
	
	private TableViewer viewer;
	private Button addButton;
	private Button editButton;
	private Button removeButton;
	private IDataModel model;
	private String propertyName;

    public FilterMappingsArrayTableWizardSection(Composite parent, IDataModel model, String propertyName) {
	    super(parent, SWT.NONE);
	    
        this.model = model;
        this.propertyName = propertyName;
        model.addListener(this);
        
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = 4;
        layout.marginWidth = 0;
        this.setLayout(layout);
        this.setLayoutData(new GridData(GridData.FILL_BOTH));

        Label titleLabel = new Label(this, SWT.LEFT);
        titleLabel.setText(FILTER_MAPPINGS_LABEL);
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
        addButton.setText(ADD_BUTTON_LABEL_D);
        addButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
        addButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                handleAddButtonSelected();
            }
            public void widgetDefaultSelected(SelectionEvent event) {
                //Do nothing
            }
        });

        editButton = new Button(buttonCompo, SWT.PUSH);
        editButton.setText(EDIT_BUTTON_LABEL_T);
        editButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
        editButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                handleEditButtonSelected();
            }
            public void widgetDefaultSelected(SelectionEvent event) {
                //Do nothing
            }
        });
        editButton.setEnabled(false);

        removeButton = new Button(buttonCompo, SWT.PUSH);
        removeButton.setText(REMOVE_BUTTON_LABEL_V);
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
				if (editButton != null) {
					boolean enabled = ((IStructuredSelection) selection).size() == 1;
					editButton.setEnabled(enabled);
				}
                removeButton.setEnabled(!selection.isEmpty());
            }
        });
        
		if (editButton != null) {
			viewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					handleEditButtonSelected();
				}
			});
		}
		
        TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(ICON_WIDTH);
		tableColumn.setResizable(false);
		tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setText(URL_SERVLET_LABEL);
        tableColumn.setResizable(true);
        tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setText(DISPATCHERS_LABEL);
        tableColumn.setWidth(DISPATCHERS_WIDTH);
        tableColumn.setResizable(false);
        viewer.setColumnProperties(new String[] {ICON_PROP, NAME_PROP, DISPATCHERS_PROP});
		table.setHeaderVisible(true);
		this.addControlListener(new ControlAdapter() {
		    @Override
			public void controlResized(ControlEvent e) {
		        Table table = viewer.getTable();
		        TableColumn[] columns = table.getColumns();
		        Point buttonArea = buttonCompo.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		        Rectangle area = table.getParent().getClientArea();
		        Point preferredSize = viewer.getTable().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		        int width = area.width - 2*table.getBorderWidth() - buttonArea.x - columns.length*2;
		        if (preferredSize.y > area.height + table.getHeaderHeight()) {
		            // Subtract the scrollbar width from the total column width
		            // if a vertical scrollbar will be required
		            Point vBarSize = table.getVerticalBar().getSize();
		            width -= vBarSize.x;
		        }
		        Point oldSize = table.getSize();
		        for (int i=0; i < columns.length; i++) {
		            if (oldSize.x > area.width) {
		                // table is getting smaller so make the columns 
		                // smaller first and then resize the table to
		                // match the client area width
		                if (i == 1) columns[i].setWidth(width - ICON_WIDTH - DISPATCHERS_WIDTH);
		                table.setSize(area.width - buttonArea.x - columns.length*2, area.height);
		            } else {
		                // table is getting bigger so make the table 
		                // bigger first and then make the columns wider
		                // to match the client area width
		                table.setSize(area.width - buttonArea.x - columns.length*2, area.height);
		                if (i == 1) columns[i].setWidth(width - ICON_WIDTH - DISPATCHERS_WIDTH);
		            }
		        }
		    }
		});
		
		viewer.setInput(model.getProperty(propertyName));
	}

	private void handleAddButtonSelected() {
	    String title = WebAppEditResourceHandler.getString("Add_Filter_Mapping_Title_UI_"); //$NON-NLS-1$
	    IProject p = (IProject) model.getProperty(PROJECT);
	    List valueList = (List) viewer.getInput();
	    AddEditFilterMappingDialog dialog = 
	        new AddEditFilterMappingDialog(getShell(), title, p, valueList, null);
		dialog.open();
		Object[] result = dialog.getResult();
		
		addFilterMapping((result != null && result.length > 0) ? (IFilterMappingItem) result[0] : null);
	}

     private void handleEditButtonSelected() {
         String title = WebAppEditResourceHandler.getString("Edit_Filter_Mapping_Title_UI_"); //$NON-NLS-1$
		ISelection s = viewer.getSelection();
		if (!(s instanceof IStructuredSelection))
			return;
		IStructuredSelection selection = (IStructuredSelection) s;
		if (selection.size() != 1)
			return;
		List valueList = (List) viewer.getInput();
		Object selectedObj = selection.getFirstElement();
		FilterMappingItem value = (FilterMappingItem) selectedObj;
		IProject p = (IProject) model.getProperty(PROJECT);
		AddEditFilterMappingDialog dialog = 
            new AddEditFilterMappingDialog(getShell(), title, p, valueList, value);
		dialog.open();
		Object[] result = dialog.getResult();
		if (result == null || result.length == 0) return;
		editFilterMapping(value, (IFilterMappingItem) result[0]);
	}
	
	private void handleRemoveButtonSelected() {
		ISelection selection = viewer.getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		List selectedObj = ((IStructuredSelection) selection).toList();
		removeFilterMappings(selectedObj);
	}
	
	private void addFilterMapping(IFilterMappingItem filterMapping) {
		if (filterMapping == null) return;
		List valueList = (List) viewer.getInput();
		if (valueList == null) {
			valueList = new ArrayList();
		}
		valueList.add(filterMapping);
		setInput(valueList);
	}
	
	private void editFilterMapping(IFilterMappingItem oldFilterMapping, IFilterMappingItem newFilterMapping) {
		if (newFilterMapping == null) return;		
		List valueList = (List) viewer.getInput();
		if (valueList == null) {
			valueList = new ArrayList();
		}
		int index = valueList.indexOf(oldFilterMapping);
		if (index == -1) {
			valueList.add(newFilterMapping);
		} else {
			valueList.set(index, newFilterMapping);
		}
		
		setInput(valueList);
	}

	private void removeFilterMappings(Collection selectedFilterMappings) {
		List valueList = (List) viewer.getInput();
		valueList.removeAll(selectedFilterMappings);
		setInput(valueList);
	}

	public void setInput(List input) {
		viewer.setInput(input);
		// Create a new list to trigger property change
		List newInput = new ArrayList();
		newInput.addAll(input);
		model.setProperty(propertyName, newInput);
	}
	
	public List getInput() {
	    List valueList = (List) viewer.getInput();
        if (valueList == null) {
            valueList = new ArrayList();
        }
        return valueList;
	}

	public void propertyChanged(DataModelEvent event) {
		if (this.propertyName.equals(event.getPropertyName())) {
			viewer.setInput(event.getProperty());
		}
	}

	@Override
	public void dispose() {
		model.removeListener(this);
		super.dispose();
	}

}
