package initParserTest46376;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SameNameTestClass.java,v $
 *  $Revision: 1.1 $  $Date: 2003/11/11 16:27:40 $ 
 */
/**
 * This is to test for defect [46376].
 * 
 * SameName test. This is test where you have this:
 * 	InitParserTest46376.SameNameTestClass.java
 * 	InitParserTest46376.java
 * 
 * and
 * 
 * 	new org.eclipse.jem.tests.proxy.initParser.SameNameTestClass.RealClass()
 * 
 * Before [46376] the Static parser would find SameNameTestClass.java instead of the RealClass and would of failed.
 * To compile in Eclipse we need to have one of the classes be in the default package. Eclipse complains if we didn't.
 * But there is nothing to stop this from happening with packages too if they are spread across compile groups.  
 */
public class SameNameTestClass {
	
	public boolean equals(Object other) {
		// To make test easier, we simply looking if both of same type.
		return other != null && other.getClass() == SameNameTestClass.class;
	}

}
