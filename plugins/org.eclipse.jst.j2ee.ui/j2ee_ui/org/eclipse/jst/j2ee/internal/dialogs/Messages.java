/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.dialogs;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.internal.dialogs.messages"; //$NON-NLS-1$
	public static String RuntimeSelectionDialog_Configure_Target_Runtime_;
	public static String J2EEDeployAction_Deploy_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
