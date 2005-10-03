/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SearchpathOrderingWorkbookPage.java,v $
 *  $Revision: 1.5 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.*;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

public class SearchpathOrderingWorkbookPage implements IBuildSearchPage{

	private Label label = null;
	private Table table = null;
	private Composite buttonBar = null;
	private Button upButton = null;
	private Button downButton = null;
	private Label spacer1 = null;
	private Button exportAllButton = null;
	private Button unExportAllButton = null;
	private IStatus status;
	private boolean inUpdate;
	private BeaninfoPathsBlock beaninfoPathsBlock;
	private Composite top;
	private CheckboxTableViewer tableViewer=null;
	private SearchPathListLabelProvider labelProvider;

	private List elements;
	private List selected;
	private List checked;

	public SearchpathOrderingWorkbookPage(BeaninfoPathsBlock beaninfoPathsBlock) {
		this.beaninfoPathsBlock = beaninfoPathsBlock;
		this.status = StatusHelper.OK_STATUS;
		this.labelProvider = new SearchPathListLabelProvider();
		this.elements = new ArrayList();
	}

	public Control createControl(Composite parent){
		top = new Composite(parent, SWT.NONE);
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		label = new Label(top, SWT.NONE);
		label.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_label);
		label.setLayoutData(gridData);
		createTable();
		top.setLayout(gridLayout);
		createButtonBar();
		top.setSize(new Point(300, 200));
		updateEnabledStates();
		if(spacer1==null){
			//TODO
		}
		return top;
	}

	/**
	 * This method initializes table	
	 *
	 */
	private void createTable() {
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		table = new Table(top, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CHECK | SWT.FULL_SELECTION);
		table.setLayoutData(gridData1);
		table.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				setSelection(BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection()));
				updateButtons();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		tableViewer = new CheckboxTableViewer(table);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(labelProvider);
		tableViewer.setInput(elements);
		if(getSelection()!=null)
			tableViewer.setSelection(new StructuredSelection(getSelection()));
		if(checked!=null)
			tableViewer.setCheckedElements(checked.toArray());
		tableViewer.addCheckStateListener(new ICheckStateListener(){
			public void checkStateChanged(CheckStateChangedEvent event) {
				pageChanged();
			}
		});
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createButtonBar() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
		rowLayout.fill = true;
		GridData gridData2 = new org.eclipse.swt.layout.GridData();
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		buttonBar = new Composite(top, SWT.NONE);
		buttonBar.setLayoutData(gridData2);
		buttonBar.setLayout(rowLayout);
		upButton = new Button(buttonBar, SWT.NONE);
		upButton.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_up_button);
		upButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Iterator selItr = getSelection().iterator();
				while (selItr.hasNext()) {
					Object element = selItr.next();
					int oldIndex = elements.indexOf(element);
					if(oldIndex>0){
						elements.remove(element);
						elements.add(--oldIndex, element);
					}
				}
				tableViewer.refresh();
				pageChanged();
			}
		});
		downButton = new Button(buttonBar, SWT.NONE);
		downButton.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_down_button);
		downButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Iterator selItr = getSelection().iterator();
				while (selItr.hasNext()) {
					Object element = selItr.next();
					int oldIndex = elements.indexOf(element);
					if(oldIndex<(elements.size()-1)){
						elements.remove(element);
						elements.add(++oldIndex, element);
					}
				}
				tableViewer.refresh();
				pageChanged();
			}
		});
		spacer1 = new Label(buttonBar, SWT.NONE);
		exportAllButton = new Button(buttonBar, SWT.NONE);
		exportAllButton.setText(BeanInfoUIMessages.BeanInfoPathsBlock_ExportAll);
		exportAllButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				setCheckedElements(getElements());
			}
		});
		unExportAllButton = new Button(buttonBar, SWT.NONE);
		unExportAllButton.setText(BeanInfoUIMessages.BeanInfoPathsBlock_UnexportAll);
		unExportAllButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				setCheckedElements(null);
			}
		});
	}

	public List getElements() {
		return new ArrayList(elements);
	}
	
	public void setElements(List elements){
		this.elements.clear();
		this.elements.addAll(elements);
		if(tableViewer!=null){
			tableViewer.refresh();
			updateButtons();
		}
	}

	public void init(IJavaProject javaProject) {
		labelProvider.setJavaProject(javaProject);
	}

	public void setCheckedElements(List exportedEntries) {
		this.checked = exportedEntries;
		if(tableViewer!=null && !table.isDisposed()){
			tableViewer.setCheckedElements(checked!=null ? checked.toArray() : new Object[0]);
			updateButtons();
		}
		pageChanged();
	}

	public boolean isChecked(BPListElement currElement) {
		if(tableViewer!=null){
			return tableViewer.getChecked(currElement);
		}
		return checked!=null && checked.contains(currElement);
	}

	public List getSelection() {
		return selected;
	}

	public void setSelection(List selection) {
		this.selected = selection;
		if(tableViewer!=null && !table.isDisposed()){
			tableViewer.setSelection(new StructuredSelection(selection!=null?selection : new ArrayList()));
			updateButtons();
		}
	}
	
	public void pageChanged(){
		updateSearchPathStatus();
		beaninfoPathsBlock.doStatusLineUpdate();
	}
	
	protected void updateButtons(){
		upButton.setEnabled(canMoveUp() && beaninfoPathsBlock.isBeaninfoEnabled());
		downButton.setEnabled(canMoveDown() && beaninfoPathsBlock.isBeaninfoEnabled());
		exportAllButton.setEnabled(elements!=null && beaninfoPathsBlock.isBeaninfoEnabled());
		unExportAllButton.setEnabled(elements!=null && beaninfoPathsBlock.isBeaninfoEnabled());
	}
	
	private boolean canMoveDown() {
		int[] indc= table.getSelectionIndices();
		int k= elements.size() - 1;
		for (int i= indc.length - 1; i >= 0 ; i--, k--) {
			if (indc[i] != k) {
				return true;
			}
		}
		return false;
	}

	private boolean canMoveUp() {
		int[] indc= table.getSelectionIndices();
		for (int i= 0; i < indc.length; i++) {
			if (indc[i] != i) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the search path.
	 */
	private void updateSearchPathStatus() {
		if (inUpdate)
			return;
		try {
			inUpdate = true;

			status = StatusHelper.OK_STATUS;

			List elements = getElements();

			boolean entryMissing = false;

			// Because of bug in setcheckedWithoutUpdate, which sets to true no matter what the state is, we need
			// to accumulate the checked elements and re-set them again after this so that they will be correct.	
			ArrayList exported = new ArrayList();

			for (Iterator entries = elements.iterator(); entries.hasNext();) {
				BPListElement currElement = (BPListElement) entries.next();

				boolean isChecked = isChecked(currElement);
				if (currElement.canExportBeChanged()) {
					if (isChecked)
						exported.add(currElement);
					currElement.setExported(isChecked);
				} else {
					//				fSearchOrder.setCheckedWithoutUpdate(currElement, currElement.isExported());
					if (currElement.isExported())
						exported.add(currElement);
				}

				entryMissing = entryMissing || currElement.isMissing();
			}

			// Now reset the checked states, due to bug
			setCheckedElements(exported);

			if (entryMissing) {
				status = new Status(IStatus.WARNING, 
						JEMUIPlugin.getPlugin().getBundle().getSymbolicName(),
						IStatus.WARNING,
						BeanInfoUIMessages.BeaninfoPathsBlock_UI__warning_EntryMissing,
						null); 
			}
		} finally {
			inUpdate = false;
		}
	}

	public IStatus getStatus() {
		return status;
	}

	public void setBeaninfoEnabled(boolean enable) {
		if(top!=null && !top.isDisposed())
			updateEnabledStates();
	}

	private void updateEnabledStates() {
		updateButtons();
		table.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		label.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
	}

}
