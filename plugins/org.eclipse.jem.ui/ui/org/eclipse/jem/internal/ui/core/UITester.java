/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: UITester.java,v $
 *  $Revision: 1.1 $  $Date: 2006/08/09 15:38:16 $ 
 */
package org.eclipse.jem.internal.ui.core;

import org.eclipse.ui.PlatformUI;
 

/**
 * Tests if UI is active, and if it is, it will return true.
 * @since 1.2.1
 */
public class UITester implements org.eclipse.jem.util.UITester {

	/* (non-Javadoc)
	 * @see org.eclipse.jem.util.UITester#isCurrentContextUI()
	 */
	public boolean isCurrentContextUI() {
		return PlatformUI.isWorkbenchRunning();
	}

}
