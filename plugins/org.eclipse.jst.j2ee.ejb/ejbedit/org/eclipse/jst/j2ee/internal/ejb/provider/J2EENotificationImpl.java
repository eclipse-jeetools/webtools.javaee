/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;

import org.eclipse.emf.common.notify.impl.NotificationImpl;

/**
 * @author dfholttp
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class J2EENotificationImpl extends NotificationImpl {
	Object feature = null;
	Object notifier = null;

	/**
	 * Constructor for EJBNotificationImpl.
	 */
	public J2EENotificationImpl(int eventType, Object oldValue, Object newValue) {
		super(eventType, oldValue, newValue);
	}

	/**
	 * Constructor for EJBNotificationImpl.
	 */
	public J2EENotificationImpl(int eventType, Object oldValue, Object newValue, boolean isSetChange) {
		super(eventType, oldValue, newValue, isSetChange);
	}

	/**
	 * Constructor for EJBNotificationImpl.
	 */
	public J2EENotificationImpl(int eventType, Object oldValue, Object newValue, int position) {
		super(eventType, oldValue, newValue, position);
	}

	/**
	 * Constructor for full EJBNOtificationImpl
	 */
	public J2EENotificationImpl(Object notifier, int eventType, Object oldValue, Object newValue, int position, Object feature) {
		super(eventType, oldValue, newValue, position);
		this.notifier = notifier;
		this.feature = feature;
	}

	/**
	 * Constructor for full EJBNOtificationImpl
	 */
	public J2EENotificationImpl(Object notifier, int eventType, Object oldValue, Object newValue, int position) {
		super(eventType, oldValue, newValue, position);
		this.notifier = notifier;
	}

	public Object getNotifier() {
		return notifier;
	}

	public Object getFeature() {
		return feature;
	}

}