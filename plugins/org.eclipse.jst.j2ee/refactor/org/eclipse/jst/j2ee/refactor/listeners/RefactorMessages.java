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

package org.eclipse.jst.j2ee.refactor.listeners;

import org.eclipse.osgi.util.NLS;

public class RefactorMessages extends NLS {

	public static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.refactor.listeners.messages"; //$NON-NLS-1$
	public static String J2EEElementChangedListener_J2EE_Component_Mapping_Update_;
	public static String ProjectRefactoringListener_J2EE_Project_Rename_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, RefactorMessages.class);
	}

}
