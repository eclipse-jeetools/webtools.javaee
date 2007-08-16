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
package org.eclipse.jst.j2ee.internal.webservice.helper;

public class WebServiceEvent {

	public static final int REFRESH = 1;
	public static final int REMOVE = 0;
	
	private int eventType = 0;
	
	public WebServiceEvent(int anEventType) {
		super();
		eventType = anEventType;
	}
	
	public int getEventType() {
		return eventType;
	}

}
