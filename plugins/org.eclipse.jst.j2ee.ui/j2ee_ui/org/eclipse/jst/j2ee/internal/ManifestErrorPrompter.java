/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 17, 2005
 */
package org.eclipse.jst.j2ee.internal;

import org.eclipse.jst.j2ee.internal.plugin.ErrorDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author jialin
 */
public class ManifestErrorPrompter implements ICommonManifestUIConstants {

	/**
	 * Constructor for ManifestErrorPrompter.
	 */
	private ManifestErrorPrompter() {
		super();
	}
	
	public static boolean showManifestException(Shell shell, String baseMessage, boolean includeCancel, Throwable caught) {
		StringBuffer msg = new StringBuffer(baseMessage);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_1);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_2);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_3);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_4);
		return ErrorDialog.openError(shell,
		   ERROR_READING_MANIFEST_DIALOG_TITLE,
		   msg.toString(),
		   caught,
		   0, includeCancel);
	}

}
