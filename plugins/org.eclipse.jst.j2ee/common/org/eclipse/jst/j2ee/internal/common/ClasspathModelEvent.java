/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.common;


public class ClasspathModelEvent extends Object {
	protected int eventType;
	public static final int CLASS_PATH_CHANGED = 1;
	public static final int EAR_PROJECT_CHANGED = 2;
	public static final int MANIFEST_CHANGED = 3;
	public static final int CLASS_PATH_RESET = 4;
	public static final int MAIN_CLASS_CHANGED = 5;
	public static final int MODEL_SAVED = 6;

	/**
	 * Constructor for ClasspathModelEvent.
	 */
	public ClasspathModelEvent(int eventType) {
		super();
		this.eventType = eventType;
	}

	/**
	 * Gets the eventType.
	 * 
	 * @return Returns a int
	 */
	public int getEventType() {
		return eventType;
	}


}