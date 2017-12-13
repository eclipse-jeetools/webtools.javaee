/*******************************************************************************
 * Copyright (c) 2011 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ludovic Champenois ludo@java.net
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.AddEjbTimerDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

@SuppressWarnings("restriction")
/* 
 * Java EE 6 EJB Timer wizard
 */
public class AddEjbTimerWizard extends NewEnterpriseBeanWizard {

	public AddEjbTimerWizard() {
		this(null);
	}

	public AddEjbTimerWizard(IDataModel model) {
		super(model);
		setWindowTitle(EJBUIMessages.timerWizardTitle);
	}

	@Override
	protected void doAddPages() {
		AddEjbTimerWizardPage page1 = new AddEjbTimerWizardPage(getDataModel(),
				"page1", EJBUIMessages.timerWizardDescription, //$NON-NLS-1$
				EJBUIMessages.timerWizardTitle, J2EEProjectUtilities.EJB);
		addPage(page1);
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new AddEjbTimerDataModelProvider();
	}
}
