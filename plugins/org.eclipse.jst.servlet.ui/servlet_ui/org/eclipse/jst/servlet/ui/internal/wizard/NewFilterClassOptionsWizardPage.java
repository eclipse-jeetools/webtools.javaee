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

import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DESTROY;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DO_FILTER;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER;

import java.util.Iterator;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.internal.web.operations.FilterSupertypesValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewFilterClassOptionsWizardPage extends
		NewWebClassOptionsWizardPage implements ISelectionChangedListener {
	
	protected Button initButton;
	protected Button destroyButton;
	protected Button doFilterButton;
	
	public NewFilterClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	/**
	 * Create the composite with all the stubs
	 */
	@Override
	protected void createStubsComposite(Composite parent) {
		super.createStubsComposite(parent);

		Composite comp = new Composite(methodStubs, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.makeColumnsEqualWidth = true;
		comp.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		comp.setLayoutData(data);
		
		initButton = new Button(comp, SWT.CHECK);
		initButton.setText("init"); //$NON-NLS-1$
		synchHelper.synchCheckbox(initButton, INIT, null);

		destroyButton = new Button(comp, SWT.CHECK);
		destroyButton.setText("destroy"); //$NON-NLS-1$
		synchHelper.synchCheckbox(destroyButton, DESTROY, null);

		doFilterButton = new Button(comp, SWT.CHECK);
		doFilterButton.setText("doFilter"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doFilterButton, DO_FILTER, null);
		
		interfaceViewer.addSelectionChangedListener(this);
		
	    Dialog.applyDialogFont(parent);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event.getSelection();
		
		// if the selection is empty, then the remove button is disabled
		if (selection.isEmpty()) {
			removeButton.setEnabled(false);
			return;
		}
		
		// if the selection is non-empty and the filter extends a class which
		// implements javax.servlet.Filter, then the remove button is enabled
		if (FilterSupertypesValidator.isFilterSuperclass(model)) {
			removeButton.setEnabled(true);
			return;
		} 
		
		// if the selection is non-empty and the filter does not extend a class
		// which implements javax.servlet.Filter, then the remove button is
		// disabled only if the Filter interface is in the selection
		Iterator iter = selection.iterator();
		while (iter.hasNext()) {
			if (QUALIFIED_FILTER.equals(iter.next()))
			removeButton.setEnabled(false);
			return;
		}

		// in all other cases the remove button is enabled
		removeButton.setEnabled(true);
	}
}
