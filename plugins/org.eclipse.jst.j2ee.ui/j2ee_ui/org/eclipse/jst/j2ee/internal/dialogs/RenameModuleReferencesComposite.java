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

public class RenameModuleReferencesComposite extends Composite implements J2EERenameUIConstants, Listener {

	protected Button renameModulesCheckbox;
	protected Button renameModuleDependenciesCheckbox;
	protected boolean isEARRename;

	/**
	 * Constructor for RenameModuleReferencesComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RenameModuleReferencesComposite(Composite parent, int style, boolean isEARRename) {
		super(parent, style);
		this.isEARRename = isEARRename;
		addChildren();
	}

	protected void addChildren() {
		GridLayout lay = new GridLayout();
		lay.numColumns = 1;
		setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		setLayoutData(data);

		addRenameModulesCheckbox();
		addRenameModuleDependenciesCheckbox();
	}

	protected void addRenameModuleDependenciesCheckbox() {
		renameModuleDependenciesCheckbox = new Button(this, SWT.CHECK);
		renameModuleDependenciesCheckbox.setSelection(true);
		renameModuleDependenciesCheckbox.setText(RENAME_MODULE_DEPENDENCIES);

	}

	protected void addRenameModulesCheckbox() {
		renameModulesCheckbox = new Button(this, SWT.CHECK);
		renameModulesCheckbox.setSelection(true);
		String label = isEARRename ? RENAME_MODULES_OTHER : RENAME_MODULES;
		renameModulesCheckbox.setText(label);
		renameModulesCheckbox.addListener(SWT.Selection, this);
	}

	public boolean shouldRenameModuleDependencies() {
		return renameModuleDependenciesCheckbox.getSelection();
	}

	public boolean shouldRenameModules() {
		return renameModulesCheckbox.getSelection();
	}

	public void setButtonsEnabled(boolean enabled) {
		renameModuleDependenciesCheckbox.setSelection(enabled);
		renameModulesCheckbox.setSelection(enabled);
		renameModuleDependenciesCheckbox.setEnabled(enabled);
		renameModulesCheckbox.setEnabled(enabled);
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == renameModulesCheckbox && renameModulesCheckbox.getSelection() && !renameModuleDependenciesCheckbox.getSelection())
			renameModuleDependenciesCheckbox.setSelection(true);
	}

}