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

import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jst.j2ee.internal.SecondCheckBoxStateChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class DoubleCheckboxTableItem extends TableItem {
	
	protected int mSecondCheckboxColumnIndex;
	protected Button secondCheckBox;
	protected SelectionListener selLstnr;
	protected ICheckStateListener tblLstnr = null;
	protected DoubleCheckboxTableViewer tableViewer;
	
	public DoubleCheckboxTableItem (Table parent, int style, int secondCheckboxColumnIndex, DoubleCheckboxTableViewer tableViewer) {
		super(parent, style);
		mSecondCheckboxColumnIndex = secondCheckboxColumnIndex;
		this.tableViewer = tableViewer;
		createCheckBox(parent);
	}
	
	public DoubleCheckboxTableItem(Table parent, int style, int index, int secondCheckboxColumnIndex, DoubleCheckboxTableViewer tableViewer) {
		super(parent, style, index);
		mSecondCheckboxColumnIndex = secondCheckboxColumnIndex;
		this.tableViewer = tableViewer;		
		createCheckBox(parent);		
	}
	
	public void setSecondChecked (boolean checked) {
		secondCheckBox.setSelection(checked);
	}
	
	public boolean getSecondChecked() {
		return secondCheckBox.getSelection();
	}
	
	public void setSecondGrayed(boolean grayed) {
		secondCheckBox.setGrayed(grayed);
	}
	
	public boolean getSecondGrayed() {
		return secondCheckBox.getGrayed();
	}
	
	public void setSecondEnabled(boolean enabled) {
		secondCheckBox.setEnabled(enabled);
	}
	
	public boolean getSecondEnabled() {
		return secondCheckBox.getEnabled();
	}
	
	public boolean isSecondEnabled() {
		return secondCheckBox.isEnabled();
	}
	
	private void createCheckBox(Table parentTable) {
		secondCheckBox = new Button(parentTable, SWT.CHECK | SWT.FLAT);
		secondCheckBox.pack();
		final DoubleCheckboxTableItem th = this; 
		selLstnr = new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SecondCheckBoxStateChangedEvent evt = new SecondCheckBoxStateChangedEvent(tableViewer, 
																			getData(),
																			getChecked());
				evt.setTableItem(th);
				tblLstnr.checkStateChanged(evt);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		};
		secondCheckBox.addSelectionListener(selLstnr);
	}
	
	public Button getSecondCheckBox() {
		return secondCheckBox; 
	}
	
	@Override
	public void dispose() {
		disposeSecondCheckbox();
		super.dispose();
	}
	
	protected void disposeSecondCheckbox() {
		if (secondCheckBox != null) {
			secondCheckBox.removeSelectionListener(selLstnr);
			secondCheckBox.dispose();
			secondCheckBox = null;
		}
		selLstnr = null;
	}
	
	void setTableListener(ICheckStateListener tblLstnr) {
		this.tblLstnr = tblLstnr;
	}
	
	@Override
	protected void checkSubclass () {}

}
