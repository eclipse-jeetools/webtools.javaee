/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.annotations.ui;

import org.eclipse.osgi.util.NLS;

public final class IWRDResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.common.internal.annotations.ui.taghandlerui";//$NON-NLS-1$

	private IWRDResources() {
		// Do not instantiate
	}

	public static String J2EEAnnotationsCompletionProcessor_3;
	public static String J2EEAnnotationsCompletionProcessor_4;

	static {
		NLS.initializeMessages(BUNDLE_NAME, IWRDResources.class);
	}
}