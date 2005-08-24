/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: JFCTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:58:54 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class JFCTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for JFCTest.
	 * @param name
	 */
	public JFCTest(String name) {
		super(name);
	}

    public void testDimension() throws Throwable {
		testHelper.testInitString("new java.awt.Dimension(1,1)", new java.awt.Dimension(1, 1));	
    }
	public void testDimensionNeg() throws Throwable {
		testHelper.testInitString("new java.awt.Dimension(-1,-1)", new java.awt.Dimension(-1, -1));	
	}
	public void testColorConstant() throws Throwable {
		testHelper.testInitString("java.awt.Color.cyan", java.awt.Color.cyan);	
	}
	public void testColorString() throws Throwable {
		testHelper.testInitString("java.awt.Color.cyan.toString()", java.awt.Color.cyan.toString());    	
	}
	public void testColorRGB() throws Throwable {
		testHelper.testInitString("new java.awt.Color(10,20,30)", new java.awt.Color(10, 20, 30));	
	}
	public void testInteger() throws Throwable {
		testHelper.testInitString("new Integer(50)", new Integer(50));    	
	}
	public void testIntegerNegative() throws Throwable {
		testHelper.testInitString("new java.lang.Integer(-50)", new Integer(-50));    	
	}	
	public void testShort() throws Throwable {
		testHelper.testInitString("new Short((short)50)", new Short((short)50));    	
	}
	public void testShortNegative() throws Throwable {
		testHelper.testInitString("new Short((short)-50)", new Short((short)-50));    	
	}	
	public void testLong() throws Throwable {
		testHelper.testInitString("new Long(50L)", new Long(50L));    	
	}
	public void testLongNegative() throws Throwable {
		testHelper.testInitString("new java.lang.Long(-50L)", new Long(-50L));    	
	}	
	public void testByte() throws Throwable {
		testHelper.testInitString("new Byte((byte)10)", new Byte((byte)10));    	
	}
	public void testByteNegative() throws Throwable {
		testHelper.testInitString("new Byte((byte)-10)", new Byte((byte)-10));    	
	}	
	public void testFloat() throws Throwable {
//		TODO Need to fix this
//			It just doesn't work w/o java.lang on front. There is a problem with Contructor going closed too early because of the'.' in 3.5,
//			but if you fix that, then (new String("Frog").concat("xyz")).concat(... gets all messed up because it
//			tries to process String().concat() together, and how that should work doesn't work the way it does. 		
		testHelper.testInitString("new java.lang.Float(10.3F)", new Float(10.3F));    	
	}
	public void testDouble() throws Throwable {
//		TODO same problem as testFloat. Need java.lang for now.		
		testHelper.testInitString("new java.lang.Double(10.3D)", new Double(10.3D));    	
	}	
	public void testIntegerString() throws NumberFormatException, Throwable {
		testHelper.testInitString("new Integer(\"50\")", new Integer("50"));    	
	}
	public void testFlowLayout() throws Throwable {
		testHelper.testInitString("new java.awt.FlowLayout()", new java.awt.FlowLayout());	
	}
	public void testImageIcon() throws Throwable {
		testHelper.testInitString("new javax.swing.ImageIcon( \"C:/WINNT/Zapotec.bmp\")",new javax.swing.ImageIcon( "C:/WINNT/Zapotec.bmp" ));  	
	}
	public void testRectangle() throws Throwable {
		testHelper.testInitString(
				"new java.awt.Rectangle( 10 , 20 , 30 , 40 )",
				new java.awt.Rectangle(10, 20, 30, 40));
    	
	}
	public void testLocale() throws Throwable {
		testHelper.testInitString("new java.util.Locale(\"fr\",\"FR\")", new java.util.Locale("fr", "FR"));	
	}
	public void testLocaleVariant() throws Throwable {
		testHelper.testInitString("new java.util.Locale(\"fr\",\"FR\",\"\")", new java.util.Locale("fr", "FR", ""));	
	}
	public void testLocaleException() throws Throwable {
          //Locale constructor cannot take null as argument like this: new Java.util.Locale("fr", "FR", null)
		  //so this is expected to fail
		  testHelper.testInitString("new java.util.Locale(\"fr\",\"FR\",null)", null, true, true);	
	}
	public void testDialog() throws Throwable {
		testHelper.testInitString("new java.awt.Dialog(new java.awt.Frame())", false, new java.awt.Dialog(new java.awt.Frame()));	
	}
	public void testLength() throws Throwable {
		testHelper.testInitString("new Integer( 50 ).toString().length()", new Integer(new Integer( 50 ).toString().length()));	
	}
	public void testSize() throws Throwable {
		testHelper.testInitString("new java.util.ArrayList().size()", new Integer(new java.util.ArrayList().size()));		
	}
	public void testCursor() throws Throwable {
		testHelper.testInitString("new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)",new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));	
	}
	public void testPanel() throws Throwable {
		testHelper.testInitString("new java.awt.Panel(null)", false, new java.awt.Panel(null));	
	}
	
	public void testComplexFont() throws Throwable {
		testHelper.testInitString(
			"new java.awt.Font(\"Dialog\", java.awt.Font.BOLD | java.awt.Font.ITALIC, 12)",
			new java.awt.Font("Dialog", java.awt.Font.BOLD | java.awt.Font.ITALIC, 12));		
	}
	
	public void testKeyStroke() throws Throwable {
		testHelper.testInitString(
			"javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.ALT_MASK | java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK, true)",
			javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.ALT_MASK | java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK, true));
	}
}
