/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: ArrayConstructorTest.java,v $
 *  $Revision: 1.2 $  $Date: 2004/08/27 15:33:39 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ArrayConstructorTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for ArrayContructorTest.
	 * @param name
	 */
	public ArrayConstructorTest(String name) {
		super(name);
	}
		
    public void testEmptyString1D() throws Throwable {
		testHelper.testInitString("new String[2]",new String[2]);	
    }
	public void testEmptyString2D() throws Throwable {
		testHelper.testInitString("new String[2][2]",new String[2][2]);	
	}
	public void testEmptyColor1D() throws Throwable {
		testHelper.testInitString("new java.awt.Color[3]",new java.awt.Color[3]);	
	}
	public void testEmptyColor2D() throws Throwable {
		testHelper.testInitString("new java.awt.Color[3][4]",new java.awt.Color[3][4]);	
	}
	public void testEmptyInt() throws Throwable {
		testHelper.testInitString("new int[3]", new int[3]);	
	}
	
	// TODO: enable this test when this works
	/*
	public void testCalculatedSize() {
		testHelper.testInitString("new String[new Integer(3).intValue()]",new String[new Integer(3).intValue()]);    	
	}
	*/
	
	public void testInitializedInts() throws Throwable {
		testHelper.testInitString("new int[] {-2,3}",new int[]{-2,3});	
	}
	public void testInitializedIntsTrailingComma() throws Throwable {
		testHelper.testInitString("new int[] {-2,3,}",new int[]{-2,3,});
	}
	public void testInitializedDoubleParens() throws Throwable {
		testHelper.testInitString("new double[] {(-2), 3.0 }",new double[] {(-2), 3.0 });	
	}
	public void testInitializedStringsTwo() throws Throwable {
		testHelper.testInitString("new String[] {\"1\",\"2\"}",new String[] {"1","2"});	
	}
	public void testInitializedStringsThree() throws Throwable {
		testHelper.testInitString("new String[] {\"Frog\",\"Dog\",\"Cow\"}",new String[] {"Frog","Dog","Cow"});	
	}
	public void testInitializedColors() throws Throwable {
		testHelper.testInitString("new java.awt.Color[] { java.awt.Color.red , new java.awt.Color(0,0,0) }",new java.awt.Color[] { java.awt.Color.red , new java.awt.Color(0,0,0) });	
	}
	public void testInitalizedInts2D1() throws Throwable {
		testHelper.testInitString("new int[][] { { 2 , -3 } , { 4 , 5 } }",new int[][] { { 2 , -3 } , { 4 , 5 } });	
	}
	public void testInitalizedInts2D2() throws Throwable {
		testHelper.testInitString("new int[][] {{1,2,3},{4,5,6}}",new int[][]{{1,2,3},{4,5,6}});	
	}
	public void testInitalizedInts2DParensCommas() throws Throwable {
		// Much to my surprise, extra commands are valid syntax at the end of the arguments even without any following argument
		// the resolve that occurs by the Visual Editor actually inserts these, so we need tests to make sure we can deal with them correctly
		testHelper.testInitString("new int[][] { {2 ,(-3),} , { 4 , 5,}}",new int[][] { { 2 , (-3),} , { 4 , 5,}});	
	}
	public void testInitalizedInts2DParensCommas2() throws Throwable {
		testHelper.testInitString("new int[][] { {2 ,(-3),} , { 4 , 5,},}",new int[][] { { 2 , (-3),} , { 4 , 5,},});	
	}
	public void testInitalizedDoubles2D() throws Throwable {
		testHelper.testInitString("new double[][] { { 2 , 3 } , { 4 , 5 } }",new double[][] { { 2 , 3 } , { 4 , 5 } });	
	}
	public void testInitalizedDoubles2DMany() throws Throwable {
		testHelper.testInitString("new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 }}",new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 }});	
	}
	public void testInt2DParam() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3},{3,4,5}})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3},{3,4,5}}));	
	}
	public void testInt2DParamCommas() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3,},{3,4,5,}})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3,},{3,4,5,}}));	
	}
	public void testInt2DParamCommas2() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3,},{3,4,5,},})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new int[][] {{1,2,3,},{3,4,5,},}));	
	}
	public void testDoubleParam() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{1,2,3})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{1,2,3}));	
	}
	public void testDoubleParam2() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{1.0,2,-3.5})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{1.0,2,-3.5}));	
	}
	public void testDoubleParam3() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{-1,2,-0.5})",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[]{-1,2,-0.5}));	
	}
	public void testDouble2DParam() throws Throwable {
		// This test is one that is similar to the type of constructor used by TableLayout for SWA
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 }})",
						new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 }}));    	
	}
	public void testDouble2DParamCommas() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 , }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 , } , })",
							new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new double[][]{{ 5.0, -2.0, 5.0, -1.0, -1.0, 5.0, -2.0, 5.0 , }, { 5.0, -2.0, 5.0, -2.0, 5.0, -1.0, 5.0, -2.0, 5.0 , } , }));	
	}
	
	
	// TODO: enable this test when the case is handled properly
	/*
	public void testInt3D() {
		// Needs fixing - We don't have dimensions greater than 2 working yet
		testHelper.testInitString("new int[][][] { { { 1 , 2 } , { 3 , 4 } }, { { 5 , 6 } , { 7 , 8 } } }",new int[][][] { { { 1 , 2 } , { 3 , 4 } }, { { 5 , 6 } , { 7 , 8 } } });    	
	}
    */
}
