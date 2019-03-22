/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation.base;

import org.eclipse.osgi.util.NLS;

public final class InstantiationBaseMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.instantiation.base.messages";//$NON-NLS-1$

	private InstantiationBaseMessages() {
		// Do not instantiate
	}

	public static String ParseTreeAllocationInstantiationVisitor_CurrentlyThisNotSupported_EXC_;
	public static String ParseTreeAllocationInstantiationVisitor_CannotProcessAnonymousDeclarations_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, InstantiationBaseMessages.class);
	}
}