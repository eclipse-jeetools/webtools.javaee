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
 *  $RCSfile: TestCompatibleMethodsData.java,v $
 *  $Revision: 1.1 $  $Date: 2006/03/19 18:27:12 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;
 

/**
 * Used to test compatible methods searching.
 * @since 1.2.0
 */
public class TestCompatibleMethodsData {

	protected void t() {
		qqq(new Integer(1), new Object());
	}
	
	protected int qqq(Object o, Object o1) {
		return 0;
	}
	
	private int qqq(Integer i, Object o) {
		return -1;
	}
}
