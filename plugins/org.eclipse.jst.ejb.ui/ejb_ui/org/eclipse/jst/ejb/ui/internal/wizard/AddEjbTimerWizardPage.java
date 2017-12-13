/*******************************************************************************
 * Copyright (c) 2011, 2014 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ludovic Champenois ludo@java.net
 * IBM - Async and Non-persistent support
 *******************************************************************************/


package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.AddEjbTimerDataModelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

@SuppressWarnings("restriction")
/* 
 * Java EE 6 EJB Timer wizard page
 */
public class AddEjbTimerWizardPage extends NewEnterpriseBeanClassWizardPage {

	private Text scheduleText;
	private Button nonPersistentCheckbox;

	public AddEjbTimerWizardPage(IDataModel mode, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(mode, pageName, pageDesc, pageTitle, moduleType);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);

		addSeperator(composite, 3);
		
		new Label(composite, SWT.LEFT).setText(EJBUIMessages.timerScheduleLabel);
		scheduleText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP /*| SWT.V_SCROLL*/);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.verticalSpan = 2;
		initializeDialogUnits(scheduleText);
		layoutData.heightHint = convertHeightInCharsToPixels(2);
		scheduleText.setLayoutData(layoutData);
		synchHelper.synchText(scheduleText,
				AddEjbTimerDataModelProvider.SCHEDULE, null);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		nonPersistentCheckbox = new Button(composite, SWT.CHECK);
		nonPersistentCheckbox.setLayoutData(gd);
		nonPersistentCheckbox.setText(IEjbWizardConstants.NON_PERSISTENT);
		synchHelper.synchCheckbox(nonPersistentCheckbox, 
				AddEjbTimerDataModelProvider.NON_PERSISTENT, null);

		return composite;
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		String[] base = super.getValidationPropertyNames();
		String[] result = new String[base.length + 2];
		System.arraycopy(base, 0, result, 0, base.length);
		result[base.length] = AddEjbTimerDataModelProvider.SCHEDULE;
		result[base.length+1] = AddEjbTimerDataModelProvider.NON_PERSISTENT;
		
		return result;
	}


}
