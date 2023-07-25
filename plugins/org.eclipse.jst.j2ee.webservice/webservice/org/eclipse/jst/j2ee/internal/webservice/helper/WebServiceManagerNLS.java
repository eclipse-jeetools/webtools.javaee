/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.helper;

import org.eclipse.osgi.util.NLS;

public class WebServiceManagerNLS extends NLS {

	public static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.internal.webservice.helper.messages"; //$NON-NLS-1$
	public static String WebServicesManager_Loading_Webservice_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, WebServiceManagerNLS.class);
	}

}
