/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 8, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.internal.ui.WTPDataModelSynchHelper;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class ServerTargetGroup {
	private ServerTargetDataModel model;
	private WTPDataModelSynchHelper synchHelper;
	private Combo targetServerCombo;
	private Button newTargetServerButton;
	public Composite parentUI;

	/**
	 *  
	 */
	public ServerTargetGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param style
	 */
	public ServerTargetGroup(Composite parent, int style, ServerTargetDataModel model) {
		this.model = model;
		this.parentUI = parent;
		synchHelper = new WTPDataModelSynchHelper(model);
		buildComposites(parent);
	}

	/**
	 * Create the controls within this composite
	 */
	public void buildComposites(Composite parent) {
		createServerTargetGroup(parent);
	}

	/**
	 * @param parent
	 */
	private void createServerTargetGroup(Composite parent) {

		Label serverTargetLabel = new Label(parent, SWT.NONE);
		serverTargetLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_SERVER_LBL));
		GridData data = new GridData();
		serverTargetLabel.setLayoutData(data);


		targetServerCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		targetServerCombo.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));

		newTargetServerButton = new Button(parent, SWT.NONE);
		newTargetServerButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newTargetServerButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newTargetServerButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleNewTargetServerButtonPressed();
			}
		});
		newTargetServerButton.setEnabled(true);

		Control[] deps = new Control[]{serverTargetLabel, newTargetServerButton};
		synchHelper.synchCombo(targetServerCombo, ServerTargetDataModel.RUNTIME_TARGET_ID, deps);

	}

	/**
	 *  
	 */
	protected void handleNewTargetServerButtonPressed() {
		J2EEProjectCreationPage.launchNewRuntimeWizard(parentUI.getShell(), model);
	}

	public void dispose() {
		model.removeListener(synchHelper);
		synchHelper.dispose();
		model = null;
	}

	/**
	 * @return Returns the targetServerCombo.
	 */
	public Combo getTargetServerCombo() {
		return targetServerCombo;
	}

	/**
	 * @param targetServerCombo
	 *            The targetServerCombo to set.
	 */
	public void setTargetServerCombo(Combo targetServerCombo) {
		this.targetServerCombo = targetServerCombo;
	}

	/**
	 * @return Returns the newTargetServerButton.
	 */
	public Button getNewTargetServerButton() {
		return newTargetServerButton;
	}

	/**
	 * @param newTargetServerButton
	 *            The newTargetServerButton to set.
	 */
	public void setNewTargetServerButton(Button newTargetServerButton) {
		this.newTargetServerButton = newTargetServerButton;
	}
}