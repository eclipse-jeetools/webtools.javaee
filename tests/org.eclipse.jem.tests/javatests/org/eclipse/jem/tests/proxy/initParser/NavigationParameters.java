package org.eclipse.jem.tests.proxy.initParser;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: NavigationParameters.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:25:46 $ 
 */

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

/**
 * Test class for the initStrimg parser - This is there after the Spanish folks found some errors with initStrings
 * that showed up deficiencies where we had methods with multiple arguments and some of the expressions inside the
 * arguments were not being closed correctly.  This class is designed to have lots of methods ( constructors and statics
 * and instance methods ) with multiple argument types to test these scenarios, and nice toString() methods so that we can
 * get good hit results on the test succeeding by a perfect toString() match
 */
public class NavigationParameters implements java.io.Serializable {
	protected int x = Integer.MIN_VALUE;
	protected int y = Integer.MIN_VALUE;
	protected boolean boolA, boolB, boolC;
	protected Color colorA, colorB;
	protected String stringA, stringB, stringC;
	protected double d1 = Double.MIN_VALUE;
	protected double d2 = Double.MIN_VALUE;
	protected float f1 = Float.MIN_VALUE;
	protected float f2 = Float.MIN_VALUE;	
	protected float f3 = Float.MIN_VALUE;	
	protected int[][] fIntArray;	
	protected double[][] fDoubleDoubleArray;
	protected double[] fDoubleArray;	
	protected Map fElements;	

// The original constructor we broke on - left there for posterity
public NavigationParameters(String newContextUsed, int newNavigation, String newOperationName, int newChainContext, String newViewName, String newViewSource, String newOutputMapFormat, String newInputMapFormat, String a, String b, int x, int y, int w, int h, boolean useP, boolean useS) {
}
public NavigationParameters(){
}
public NavigationParameters(int x){
	this.x = x;
	y = 100;
}
public NavigationParameters setElemAt(Object aKey, int aValue){
	if ( fElements == null ) {
		fElements = new HashMap(1);
	};
	fElements.put(aKey,new Integer(aValue));
	return this;
}
public NavigationParameters(String string1, String string2, String string3, boolean bool1, boolean bool2, boolean bool3, int x){
	stringA = string1;
	stringB = string2;
	stringC = string3;
	boolA = bool1;
	boolB = bool2;
	boolC = bool3;	
	this.x = x;
}
public NavigationParameters(int[][] intArray){
	fIntArray = intArray;
}
public NavigationParameters(double[] doubleArray){
	fDoubleArray = doubleArray;
}
// Constructors with different combinations of arguments
public NavigationParameters(boolean a, boolean b){
	boolA = a;
	boolB = b;
}
public NavigationParameters(int x, int y){
	this.x = x;
	this.y = y;
}
public NavigationParameters(float f1, float f2, float f3){
	this.f1 = f1;
	this.f2 = f2;
	this.f3 = f3;	
}
public NavigationParameters(double[][] aDoubleDoubleArray){
	fDoubleDoubleArray = aDoubleDoubleArray;
}
public NavigationParameters(double d1, double d2){
	this.d1 = d1;
	this.d2 = d2;
}
public NavigationParameters(Color colorA, Color colorB){
	this.colorA = colorA;
	this.colorB = colorB;
}
public NavigationParameters(String stringA, String stringB){
	this.stringA = stringA;
	this.stringB = stringB;
}
// Static method calls with different combinations of arguments
public static NavigationParameters get(boolean a, boolean b){
	return new NavigationParameters(a,b);
}
public static NavigationParameters get(int x, int y){
	return new NavigationParameters(x,y);
}
public static NavigationParameters get(float f1, float f2, float f3){
	return new NavigationParameters(f1,f2,f3);
}
public static NavigationParameters get(double d1, double d2){
	return new NavigationParameters(d1,d2);
}
public static NavigationParameters get(Color colorA, Color colorB){
	return new NavigationParameters(colorA,colorB);
}
public static NavigationParameters get(String stringA, String stringB){
	return new NavigationParameters(stringA,stringB);
}
public static Object getReversed(String arg){
	// Return the argument reversed
	StringBuffer buffer = new StringBuffer(arg.length());
	for (int i = arg.length()-1; i >= 0; i--) {
		buffer.append(arg.charAt(i));
	}
	return buffer.toString();
}
public static Color getColor(String colorName){
	// Return the colorName to test cast statements
	try {
		Field field = Color.class.getField(colorName);
		return (Color) field.get(Color.class);
	} catch ( NoSuchFieldException exc ) {
		try {
			// It is possible that the field is on SystemColor and not color
			Field field = SystemColor.class.getField(colorName);
			return (Color) field.get(Color.class);		
		} catch ( Exception e ) {
		}
	} catch ( Exception exc ) {
	}
	return null;
}
// Instance method calls with different combinations of arguments
public NavigationParameters set(boolean a, boolean b){
	boolA = a;
	boolB = b;
	return this;	
}
public NavigationParameters set(int x, int y){
	this.x = x;
	this.y = y;
	return this;	
}
public NavigationParameters set(float f1, float f2, float f3){
	this.f1 = f1;
	this.f2 = f2;
	this.f3 = f3;
	return this;	
}
public NavigationParameters set(double d1, double d2){
	this.d1 = d1;
	this.d2 = d2;
	return this;	
}
public NavigationParameters set(Color colorA, Color colorB){
	this.colorA = colorA;
	this.colorB = colorB;
	return this;
}
public NavigationParameters set(String stringA, String stringB){
	this.stringA = stringA;
	this.stringB = stringB;
	return this;	
}
// To string method to help the tests be OK? with matching toString() results
public String toString(){
	StringWriter writer = new StringWriter();
	writer.write("NavigationParameters(");
	if ( x != Integer.MIN_VALUE ) writer.write(new Integer(x).toString());
	if ( y != Integer.MIN_VALUE ) writer.write(new Integer(y).toString());	
	writer.write("boolA=" + boolA);
	writer.write("boolA=" + boolB);	
	writer.write("boolA=" + boolA);
	writer.write("boolC=" + boolC);		
	if ( colorA != null ) writer.write("colorA=" + colorA);
	if ( colorB != null ) writer.write("colorB=" + colorB);	
	if ( stringA != null ) writer.write("stringA=" + stringA);
	if ( stringB != null ) writer.write("stringB=" + stringB);		
	if ( stringC != null ) writer.write("stringB=" + stringC);
	if ( d1 != Double.MIN_VALUE ) writer.write("d1=" + d1);
	if ( d2 != Double.MIN_VALUE ) writer.write("d1=" + d2);
	if ( f1 != Float.MIN_VALUE ) writer.write("f1=" + f1);
	if ( f2 != Float.MIN_VALUE ) writer.write("f2=" + f2);
	if ( f2 != Float.MIN_VALUE ) writer.write("f3=" + f3);	
	if ( fIntArray != null ) {
		writer.write("int array=");
		for (int i = 0; i < fIntArray.length; i++) {
			writer.write("[");
			// The elements in the array are themselves arrays - it is two dimensional
			int[] elements = fIntArray[i];
			for (int j = 0; j < elements.length; j++) {
				writer.write(new Integer(elements[j]).toString());	
			}
			writer.write("]");
		}
	}
	if ( fDoubleDoubleArray != null ) {
		writer.write("doubledouble array=");
		for (int i = 0; i < fDoubleDoubleArray.length; i++) {
			writer.write("[");
			// The elements in the array are themselves arrays - it is two dimensional
			double[] elements = fDoubleDoubleArray[i];
			for (int j = 0; j < elements.length; j++) {
				writer.write(new Double(elements[j]).toString());	
			}
			writer.write("]");
		}
	}
	
	if ( fDoubleArray != null ) {	
		writer.write("double array=");		
		for (int i = 0; i < fDoubleArray.length; i++) {
			writer.write("(");			
			writer.write(new Double(fDoubleArray[i]).toString());
			writer.write(")");			
		}
	}
	writer.write(")");
	return writer.toString();
}

}
