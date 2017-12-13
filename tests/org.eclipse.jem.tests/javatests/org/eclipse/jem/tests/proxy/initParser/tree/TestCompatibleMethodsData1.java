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
 *  $RCSfile: TestCompatibleMethodsData1.java,v $
 *  $Revision: 1.1 $  $Date: 2006/03/19 18:27:12 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

 

/**
 * Used for testing most compatible protected methods
 * @since 1.2.0
 */
public class TestCompatibleMethodsData1 extends TestCompatibleMethodsData {

	protected int qqq(Number n, Integer i1) {
		return 1;
	}
	
	protected int qqq(Integer i, Object o1) {
		return 2;
	}

}
