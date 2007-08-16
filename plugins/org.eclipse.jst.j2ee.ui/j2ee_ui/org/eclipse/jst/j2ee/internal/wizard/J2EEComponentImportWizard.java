/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.IImportWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * <p>
 * Serves as a base class for Wizards which import J2EE module structures into Eclipse projects.
 * </p>
 * <p>
 * Import wizards must define the following methods:
 * <ul>
 * <li>{@link #getImportOperation()}
 * <li>{@link #getModuleValidatorStrings()}
 * </ul>
 * </p>
 * <p>
 * And optionally, they may override the following methods from
 * {@see org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactImportWizard}:
 * <ul>
 * <li>{@link #getFinalPerspectiveID()}
 * <li>{@link #doInit()()}
 * <li>{@link #doDispose()()}
 * </ul>
 */
public abstract class J2EEComponentImportWizard extends J2EEArtifactImportWizard implements IImportWizard, IExecutableExtension {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public J2EEComponentImportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls and interface with the operation.
	 * </p>
	 * 
	 * @param model
	 *            The model parameter is used to pre-populate wizard controls and interface with the
	 *            operation
	 */
	public J2EEComponentImportWizard(IDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("38"));//$NON-NLS-1$ 
	}

	/**
	 * <p>
	 * The Import Wizards can run arbitrary validators once the module has been created. These
	 * validators ensure that the structure created by the Import operation and the contents of that
	 * structure are valid. Any errors will be announced to the Problems view in Eclipse.
	 * </p>
	 * 
	 * @return An array of validator IDs that should be used for this module type
	 */
	protected abstract String[] getModuleValidatorStrings();

}
