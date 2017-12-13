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
/*
 *  $RCSfile: TestAccess.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:32 $ 
 */
package testPackage;
 

/**
 * Used for testing fields.
 * @since 1.1.0
 */
public class TestAccess {

	public static void testVoid() {
		
	}
	
	public int field1;
	protected int field2;
	
	public static int STATIC_FIELD = 3;

	TestAccess(RuntimeException i) {
		
	}
	
	TestAccess(Throwable n) {
		
	}
	
	public TestAccess() {
		
	}
	
	public TestAccess(Exception o) {
		
	}
	
	public void ddd(Integer o, Number n) {
		
	}
	
	public void ddd(Number o, Integer n) {
		
	}
	
	public void ddd(Number n, Number n1) {
		
	}

	
	public void xyz() {
	}
	
	public void xyz(Number n) {
	}
	
	public void xyz(Object n) {
	}
	
	protected void qxr() {
		
	}
}
