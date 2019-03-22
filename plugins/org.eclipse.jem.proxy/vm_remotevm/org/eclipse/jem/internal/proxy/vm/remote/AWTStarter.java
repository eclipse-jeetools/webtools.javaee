/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.vm.remote;

import java.awt.Toolkit;
 

/**
 * This class is used to do start the AWT eventqueue ahead of time to try to parallel process it.
 * @since 1.1.0
 */
public class AWTStarter {

	public static void startAWT() {
		Thread t = new Thread() {
			public void run() {
				Thread.yield();	// So as not to possibly hold up the guy doing the starting.
				Toolkit.getDefaultToolkit();
			}
		};
		t.start();
	}
}
