/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;


import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


public class RenameModuleComposite extends Composite implements J2EERenameUIConstants, Listener {

	// protected Button renameProjectsCheckBox;
	protected RenameModuleReferencesComposite moduleRefsComposite;
	protected Text newNameText = null;
	protected Text newContextRootText = null;
	protected Label newContextRootLabel = null;
	protected String oldName = null;
	protected Button OKButton = null;
	protected Label statusMessageLabel = null;

	/**
	 * Constructor for RenameModuleComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RenameModuleComposite(Composite parent, int style) {
		super(parent, style);
		addChildren();
	}

	protected void addChildren() {
		addRenameProjectsGroup();
		addSeparator();
		addRenameModuleRefsComposite();
		//Add in a label for status messages if required
		statusMessageLabel = new Label(this, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		statusMessageLabel.setLayoutData(data);
		statusMessageLabel.setFont(this.getFont());
	}

	protected void addRenameProjectsGroup() {
		GridLayout lay = new GridLayout();
		lay.numColumns = 2;
		setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		setLayoutData(data);

		newNameText = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		newNameText.setLayoutData(data);

		newContextRootLabel = new Label(this, SWT.NONE);
		newContextRootLabel.setText(RENAME_CONTEXT_ROOT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		newContextRootLabel.setLayoutData(data);

		newContextRootText = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		newContextRootText.setLayoutData(data);

		/*
		 * renameProjectsCheckBox = new Button(this, SWT.CHECK);
		 * renameProjectsCheckBox.setText(RENAME_PROJECTS);
		 * renameProjectsCheckBox.setSelection(true);
		 * renameProjectsCheckBox.addListener(SWT.Selection, this); data = new
		 * GridData(GridData.FILL_HORIZONTAL); data.horizontalSpan = 2;
		 * renameProjectsCheckBox.setLayoutData(data);
		 */
	}

	protected void addRenameModuleRefsComposite() {
		moduleRefsComposite = new RenameModuleReferencesComposite(this, SWT.NONE, false);
	}

	protected void addSeparator() {
		Label sep = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		sep.setLayoutData(data);
	}

	public boolean shouldRenameProjects() {
		//  return renameProjectsCheckBox.getSelection();
		// always rename the project
		return true;
	}

	public boolean shouldRenameModuleDependencies() {
		return moduleRefsComposite.shouldRenameModuleDependencies();
	}

	public boolean shouldRenameModules() {
		return moduleRefsComposite.shouldRenameModules();
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == newNameText && OKButton != null) {
			String newName = newNameText.getText();
			if (newName.trim().length() == 0) {
				statusMessageLabel.setText("");//$NON-NLS-1$
				OKButton.setEnabled(false);
				return;
			}
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject newProject = root.getProject(newName);
			if (newProject.exists()) {
				statusMessageLabel.setForeground(JFaceColors.getErrorText(statusMessageLabel.getDisplay()));
				String[] names = {newName};
				statusMessageLabel.setText(MessageFormat.format(J2EEUIMessages.getResourceString("Project_already_exists."), names)); //$NON-NLS-1$
				OKButton.setEnabled(false);
			} else {
				statusMessageLabel.setText("");//$NON-NLS-1$
				OKButton.setEnabled(true);
			}
		}
	}

	public String getNewName()

	{
		return newNameText.getText();
	}

	public void setNewName(String newName) {
		oldName = newName;
		newNameText.setText(newName);
		newNameText.selectAll();
		newNameText.addListener(SWT.Modify, this);
	}

	public String getNewContextRoot() {
		return newContextRootText.getText();
	}

	public void setNewContextRoot(String newContextRoot) {
		if (newContextRoot != null && newContextRoot.length() > 1) {
			newContextRootText.setText(newContextRoot);
		} else {
			newContextRootLabel.setVisible(false);
			newContextRootText.setVisible(false);
		}
	}

	/**
	 * Sets the OKButton.
	 * 
	 * @param OKButton
	 *            The OKButton to set
	 */
	public void setOKButton(Button oKButton) {
		OKButton = oKButton;
		OKButton.setEnabled(false);
	}
}