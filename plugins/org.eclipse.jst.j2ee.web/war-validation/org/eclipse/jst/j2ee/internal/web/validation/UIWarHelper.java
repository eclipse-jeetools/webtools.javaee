/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.validation;

import java.util.Hashtable;


/**
 * Insert the type's description here. Creation date: (10/2/2001 7:06:43 PM)
 * 
 * @author: Administrator
 */
public class UIWarHelper extends WarHelper {

	Hashtable aWarFileMap = new Hashtable();

	/**
	 * UIWarHelper constructor comment.
	 */
	public UIWarHelper() {
		super();
	}
	
	public void disposeWarFileMap() {
		if (warFileMap != null) {
			warFileMap.clear();
			warFileMap = null;
		}
	}
}
