/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class DeleteModuleReferencesComposite extends Composite implements J2EEDeleteUIConstants, Listener {

	protected Button deleteModulesCheckbox;
	protected Button deleteModuleDependenciesCheckbox;
	protected boolean isEARDelete;

	/**
	 * Constructor for DeleteModuleReferencesComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeleteModuleReferencesComposite(Composite parent, int style, boolean isEARDelete) {
		super(parent, style);
		this.isEARDelete = isEARDelete;
		addChildren();
	}

	protected void addChildren() {
		GridLayout lay = new GridLayout();
		lay.numColumns = 1;
		setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		setLayoutData(data);

		addDeleteModulesCheckbox();
		addDeleteModuleDependenciesCheckbox();
	}

	protected void addDeleteModuleDependenciesCheckbox() {
		deleteModuleDependenciesCheckbox = new Button(this, SWT.CHECK);
		deleteModuleDependenciesCheckbox.setText(DELETE_MODULE_DEPENDENCIES);

	}

	protected void addDeleteModulesCheckbox() {
		deleteModulesCheckbox = new Button(this, SWT.CHECK);
		String label = isEARDelete ? DELETE_MODULES_OTHER : DELETE_MODULES;
		deleteModulesCheckbox.setText(label);
		deleteModulesCheckbox.addListener(SWT.Selection, this);
	}

	public boolean shouldDeleteModuleDependencies() {
		return deleteModuleDependenciesCheckbox.getSelection();
	}

	public boolean shouldDeleteModules() {
		return deleteModulesCheckbox.getSelection();
	}

	public void setButtonsEnabled(boolean enabled) {
		deleteModuleDependenciesCheckbox.setSelection(enabled);
		deleteModulesCheckbox.setSelection(enabled);
		deleteModuleDependenciesCheckbox.setEnabled(enabled);
		deleteModulesCheckbox.setEnabled(enabled);
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == deleteModulesCheckbox && deleteModulesCheckbox.getSelection() && !deleteModuleDependenciesCheckbox.getSelection())
			deleteModuleDependenciesCheckbox.setSelection(true);
	}

}