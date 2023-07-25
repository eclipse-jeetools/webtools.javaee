/*******************************************************************************
 * Copyright (c) 2008, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Stefan Dimov, stefan.dimov@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui;

import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class DoubleCheckboxTableViewer extends CheckboxTableViewer {
	
	protected int mSecondCheckboxColumnIndex;
	protected ICheckStateListener lstnr = null;
	
    public DoubleCheckboxTableViewer(Table table, int secondCheckBoxColumnIndex) {
        super(table);
        if (secondCheckBoxColumnIndex < 1)
			throw new IllegalArgumentException(
					"The index of the second column with check box must be bigger than zero"); //$NON-NLS-1$
        		
        mSecondCheckboxColumnIndex = secondCheckBoxColumnIndex;
    }

    @Override
	public void addCheckStateListener(ICheckStateListener listener) {
    	super.addCheckStateListener(listener);
    	lstnr = listener;
    }
	
    @Override
	public void removeCheckStateListener(ICheckStateListener listener) {
    	super.removeCheckStateListener(listener);
    	lstnr = null;
    	getTable().getItems();
    	for (int i = 0; i < getTable().getItems().length; i++) {
    		DoubleCheckboxTableItem it = (DoubleCheckboxTableItem)getTable().getItem(i);
    		it.setTableListener(null);
    	}
    }
	
	@Override
	protected ViewerRow internalCreateNewRowPart(int style, int rowIndex) {
		DoubleCheckboxTableItem item;
		if (rowIndex >= 0) {
			item = new DoubleCheckboxTableItem(getTable(), style, rowIndex, mSecondCheckboxColumnIndex, this);
		} else {
			item = new DoubleCheckboxTableItem(getTable(), style, mSecondCheckboxColumnIndex, this);
		}	
		item.setTableListener(lstnr);		
		TableEditor editor = new TableEditor(getTable());
 	    editor.minimumWidth = item.getSecondCheckBox().getSize ().x;
		editor.horizontalAlignment = SWT.CENTER;		
		editor.setEditor(item.getSecondCheckBox(), item, mSecondCheckboxColumnIndex);
		return getViewerRowFromItem(item);
	}
	
    public Object[] getSecondCheckedItems() {
        TableItem[] children = getTable().getItems();
        ArrayList v = new ArrayList(children.length);
        for (int i = 0; i < children.length; i++) {
        	DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)children[i];
            if (item.getSecondChecked()) {
				v.add(item);
			}
        }
        return v.toArray();
    }
    
    public void setAllSecondChecked(boolean state) {
        TableItem[] children = getTable().getItems();
        for (int i = 0; i < children.length; i++) {
            DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)children[i];
            item.setSecondChecked(state);
        }
    }    
    
    public Object[] getSingleCheckedElements() {
        TableItem[] children = getTable().getItems();
        ArrayList v = new ArrayList(children.length);
        for (int i = 0; i < children.length; i++) {
            DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)children[i];
            if (item.getChecked() && (!item.getSecondChecked())) {
				v.add(item.getData());
			}
        }
        return v.toArray();
    }
    
    public Object[] getDoubleCheckedElements() {
        TableItem[] children = getTable().getItems();
        ArrayList v = new ArrayList(children.length);
        for (int i = 0; i < children.length; i++) {
            DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)children[i];
            if (item.getChecked() && item.getSecondChecked()) {
				v.add(item.getData());
			}
        }
        return v.toArray();
    }    
    
    public Object[] getUncheckedItems() {
        TableItem[] children = getTable().getItems();
        ArrayList v = new ArrayList(children.length);
        for (int i = 0; i < children.length; i++) {
            TableItem item = children[i];
            if (!item.getChecked()) {
				v.add(item);
			}
        }
        return v.toArray();
    }

    
    public Object[] getSecondUncheckedElements() {
        TableItem[] children = getTable().getItems();
        ArrayList v = new ArrayList(children.length);
        for (int i = 0; i < children.length; i++) {
        	DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)children[i];
            if (!item.getSecondChecked()) {
				v.add(item.getData());
			}
        }
        return v.toArray();
    }    
   
    public void setSecondCheckedItems(Object[] inputItems) {
        assertElementsNotNull(inputItems);
        Hashtable set = new Hashtable();
        for (int i = 0; i < inputItems.length; ++i) {
            set.put(inputItems[i], inputItems[i]);
        }
        TableItem[] items = getTable().getItems();
        for (int i = 0; i < items.length; ++i) {
        	DoubleCheckboxTableItem item = (DoubleCheckboxTableItem)items[i];
            boolean check = set.containsKey(item);
            if (item.getSecondChecked() != check) {
                item.setSecondChecked(check);
            }

        }
    }

	@Override
	protected void doRemove(int start, int end) {
		// ensure that the second check box is disposed
		for (int i = start; i <= end; i++) { 
			disposeSecondCheckboxOfItem(i);
		}
		
		super.doRemove(start, end);
	}

	@Override
	protected void doRemove(int[] indices) {
		// ensure that the second check box is disposed
		for (int i : indices) {
			disposeSecondCheckboxOfItem(i);
		}
		
		super.doRemove(indices);
	}
	
	private void disposeSecondCheckboxOfItem(int index) {
		TableItem item = getTable().getItem(index);
		if (item instanceof DoubleCheckboxTableItem) {
			((DoubleCheckboxTableItem) item).disposeSecondCheckbox();
		}
	}	
	    
}
