/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards.wizards";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String label_session_bean;
	public static String label_message_driven_bean;
	public static String label_container_managed_entity_bean;
	public static String label_change_your_provider_preference;
	public static String msg_err_annotation_provider_not_valid;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}