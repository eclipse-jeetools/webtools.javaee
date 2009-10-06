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
package org.eclipse.jem.internal.beaninfo.adapters;

import org.eclipse.osgi.util.NLS;

public final class BeanInfoAdapterMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.adapters.messages";//$NON-NLS-1$

	private BeanInfoAdapterMessages() {
		// Do not instantiate
	}

	public static String INTROSPECT_FAILED_EXC_;
	public static String BeaninfoClassAdapter_ClassNotFound;
	public static String BeaninfoNature_InvalidProject;
	public static String UICreateRegistryJobHandler_StartBeaninfoRegistry;

	static {
		NLS.initializeMessages(BUNDLE_NAME, BeanInfoAdapterMessages.class);
	}
}