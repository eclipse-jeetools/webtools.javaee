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
package org.eclipse.jst.j2ee.internal.migration.actions;


import org.eclipse.jst.j2ee.internal.actions.AbstractActionWithDelegate;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.IActionDelegate;


/**
 *  
 */
public class MigrateJ2EEAction extends AbstractActionWithDelegate {
	public static final String LABEL = J2EEUIPlugin.getDefault().getDescriptor().getResourceString("%openJ2EEMigrationWizard.action.label_ui_"); //$NON-NLS-1$

	/**
	 * Constructor for MigrateJ2EEAction.
	 */
	public MigrateJ2EEAction() {
		super();
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.actions.AbstractActionWithDelegate#createDelegate()
	 */
	protected IActionDelegate createDelegate() {
		return new MigrateJ2EEActionDelgate();
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.actions.AbstractActionWithDelegate#getLabel()
	 */
	protected String getLabel() {
		return LABEL;
	}

}