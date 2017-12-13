/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests;

/**
 * This is used to make the JEM tests run in UI mode instead of HEADLESS mode,
 * even though a UI is not available. The tests will normally need that mode.
 */
public class UITester implements org.eclipse.jem.util.UITester {

	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.UITester#isCurrentContextUI()
	 */
	public boolean isCurrentContextUI() {
		return true;
	}

}
