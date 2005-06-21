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
package org.eclipse.jem.internal.proxy.initParser;

import org.eclipse.osgi.util.NLS;

public final class ProxyInitParserMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.proxy.initParser.messages";//$NON-NLS-1$

	private ProxyInitParserMessages() {
		// Do not instantiate
	}

	public static String UnexpectedToken_EXC_;
	public static String Statement_UnexpectedExecution_EXC_;
	public static String Statement_UnexpectedEvaluation_EXC_;
	public static String CharTooComplicated_EXC_;
	public static String PrimitiveOperation_Evaluate_InvalidOperator_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProxyInitParserMessages.class);
	}
}