package org.eclipse.jem.tests.proxy.initParser;
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
 *  $RCSfile: BorderTest.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BorderTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for BorderTest.
	 * @param name
	 */
	public BorderTest(String name) {
		super(name);
	}
	
	public void testEtchedRaised() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED)" ,
			 javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));	
	}
	
	public void testBevelRaised() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)",
			javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}

	public void testEmpty() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createEmptyBorder()",
			javax.swing.BorderFactory.createEmptyBorder());
	}

	public void testMatte() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.black)",
			false,
			javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.black));
	}

	public void testSoftBevel() throws Throwable {
		testHelper.testInitString(
			"new javax.swing.border.SoftBevelBorder(0)",
			false,
			new javax.swing.border.SoftBevelBorder(0));
	}

	public void testEmptySize() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)",
			false,
			javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	public void testLine() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createLineBorder(java.awt.Color.black, 2)",
			false,
			javax.swing.BorderFactory.createLineBorder(java.awt.Color.black, 2));
	}

	public void testSoftBevelRaised() throws Throwable {
		testHelper.testInitString(
			"new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)",
			false,
			new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}

	public void testTitledBasic() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createTitledBorder(null,\"frog\",2,0)",
			false,
			javax.swing.BorderFactory.createTitledBorder(null, "frog", 2, 0));
	}

	public void testCompoundBasic() throws Throwable {
		testHelper.testInitString(
			"new javax.swing.border.CompoundBorder(javax.swing.BorderFactory.createEmptyBorder(),javax.swing.BorderFactory.createEmptyBorder())",
			false,
			new javax.swing.border.CompoundBorder(javax.swing.BorderFactory.createEmptyBorder(),javax.swing.BorderFactory.createEmptyBorder()));
	}

	public void testCompoundMedium() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED))",
			false,
			javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
	}

    // TODO: This test fails due to defect xxxx 
    //       Reenable it once the defect is fixed
    
//	public void testCompoundAdvanced() {
//		testHelper.testInitString(
//			"javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED))",
//			javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED)));
//	}
	
	
	public void testTitledAdvanced() throws Throwable {
		testHelper.testInitString(
			"javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.activeCaption,30), \"Hello\", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(\"Dialog\", java.awt.Font.BOLD, 18), java.awt.Color.lightGray)",
			false,
			javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.activeCaption,30), "Hello", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 18), java.awt.Color.lightGray));
	}
}
