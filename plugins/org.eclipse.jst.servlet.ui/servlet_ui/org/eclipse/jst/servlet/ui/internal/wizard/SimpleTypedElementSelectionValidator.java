/*******************************************************************************
 * Copyright (c) 2006, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * Implementation of a <code>ISelectionValidator</code> to validate the type
 * of an element. Empty selections are not accepted.
 */
public class SimpleTypedElementSelectionValidator implements ISelectionStatusValidator {

	private IStatus fgErrorStatus = new Status(IStatus.ERROR, ServletUIPlugin.PLUGIN_ID, 0, "", null); //$NON-NLS-1$
	private IStatus fgOKStatus = new Status(IStatus.OK, ServletUIPlugin.PLUGIN_ID, 0, "", null); //$NON-NLS-1$

	private Class[] fAcceptedTypes;
	private boolean fAllowMultipleSelection;

	/**
	 * @param acceptedTypes
	 *            The types accepted by the validator
	 * @param allowMultipleSelection
	 *            If set to <code>true</code>, the validator allows
	 *            multiple selection.
	 */
	public SimpleTypedElementSelectionValidator(Class[] acceptedTypes, boolean allowMultipleSelection) {
		org.eclipse.core.runtime.Assert.isNotNull(acceptedTypes);
		fAcceptedTypes = acceptedTypes;
		fAllowMultipleSelection = allowMultipleSelection;
	}

	/*
	 * @see org.eclipse.ui.dialogs.ISelectionValidator#isValid(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object[] elements) {
		if (isValid(elements)) {
			return fgOKStatus;
		}
		return fgErrorStatus;
	}

	private boolean isOfAcceptedType(Object o) {
		for (int i = 0; i < fAcceptedTypes.length; i++) {
			if (fAcceptedTypes[i].isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid(Object[] selection) {
		if (selection.length == 0) {
			return false;
		}

		if (!fAllowMultipleSelection && selection.length != 1) {
			return false;
		}

		for (int i = 0; i < selection.length; i++) {
			Object o = selection[i];
			if (!isOfAcceptedType(o))  {
				return false;
			}
		}
		return true;
	}
}
