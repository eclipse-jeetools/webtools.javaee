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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class DeleteModuleComposite extends Composite implements J2EEDeleteUIConstants, Listener {

	//	protected Button deleteProjectsCheckBox;
	protected Button deleteAppProjectsBtn;
	protected Button deleteRefProjectsBtn;
	protected Composite radioComposite;
	protected DeleteModuleReferencesComposite moduleRefsComposite;


	/**
	 * Constructor for DeleteModuleComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeleteModuleComposite(Composite parent, int style) {
		super(parent, style);
		addChildren();

	}

	protected void addChildren() {
		addDeleteProjectsGroup();
		addDeleteModuleRefsComposite();
		//set default values
		deleteAppProjectsBtn.setSelection(true);
		deleteAppProjectsBtnSelected();
	}

	protected void addDeleteProjectsGroup() {
		GridLayout lay = new GridLayout();
		lay.numColumns = 1;
		setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		setLayoutData(data);

		radioComposite = new Composite(this, SWT.NONE);
		lay = new GridLayout();
		lay.numColumns = 2;
		radioComposite.setLayout(lay);
		data = new GridData(GridData.FILL_BOTH);
		radioComposite.setLayoutData(data);
		deleteAppProjectsBtn = new Button(radioComposite, SWT.RADIO);
		deleteAppProjectsBtn.setText(DELETE_PROJECTS_ONLY);
		deleteAppProjectsBtn.addListener(SWT.Selection, this);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		deleteAppProjectsBtn.setLayoutData(data);

		deleteRefProjectsBtn = new Button(radioComposite, SWT.RADIO);
		deleteRefProjectsBtn.setText(DELETE_PROJECT_REFERENCES);
		deleteRefProjectsBtn.addListener(SWT.Selection, this);
		deleteRefProjectsBtn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	}

	protected void addDeleteModuleRefsComposite() {
		moduleRefsComposite = new DeleteModuleReferencesComposite(this, SWT.NONE, false);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		data.horizontalIndent = 10;
		moduleRefsComposite.setLayoutData(data);
	}


	protected void addSeparator() {
		Label sep = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		sep.setLayoutData(data);
	}

	public boolean shouldDeleteProjects() {
		//		return deleteProjectsCheckBox.getSelection();
		// the project should be deleted, since delete was the selected action
		return true;
	}


	public boolean shouldDeleteModuleDependencies() {
		return moduleRefsComposite.shouldDeleteModuleDependencies();
	}

	public boolean shouldDeleteModules() {
		return moduleRefsComposite.shouldDeleteModules();
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == deleteAppProjectsBtn)
			deleteAppProjectsBtnSelected();
		else if (event.widget == deleteRefProjectsBtn)
			deleteRefProjectsBtnSelected();
	}

	protected void deleteAppProjectsBtnSelected() {
		if (deleteAppProjectsBtn.getSelection()) {
			moduleRefsComposite.setButtonsEnabled(false);
		}
	}

	protected void deleteRefProjectsBtnSelected() {
		if (deleteRefProjectsBtn.getSelection()) {
			moduleRefsComposite.setButtonsEnabled(true);
		}
	}
}