/*******************************************************************************
 * Copyright (c) 2010, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ejb.validation.internal;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jee.ejb.validation.internal.messages"; //$NON-NLS-1$
	

	private Messages() {
		// Do not instantiate
	}
	
	public static String NO_BEANS_ERROR;
	public static String NO_BEANS_ERROR_LOCATION;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
