package org.eclipse.jem.internal.proxy.initParser;
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
 *  $RCSfile: Static.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Static extends Expression {
	protected static final HashMap sPrimitiveTypes;
	
	static {
		sPrimitiveTypes = new HashMap(10);
		sPrimitiveTypes.put("byte", Byte.TYPE); //$NON-NLS-1$
		sPrimitiveTypes.put("char", Character.TYPE); //$NON-NLS-1$
		sPrimitiveTypes.put("short", Short.TYPE); //$NON-NLS-1$
		sPrimitiveTypes.put("int", Integer.TYPE); //$NON-NLS-1$
		sPrimitiveTypes.put("long", Long.TYPE); //$NON-NLS-1$
		sPrimitiveTypes.put("float", Float.TYPE);		 //$NON-NLS-1$
		sPrimitiveTypes.put("double", Double.TYPE); //$NON-NLS-1$
	}

	protected ArrayList arrayDimensions;
	protected List fArrayArguments;
	protected StringWriter typeWriter = new StringWriter();
	protected boolean completedWithPeriod = false;
	protected char[] completionToken;
	public Class type;
	protected boolean isComplete;

	public boolean isProcessingArrayDimension;
/**
 * Create an unknown expression
 */
public Static(ClassLoader aClassLoader){
	fClassLoader = aClassLoader;
}
public Static(char[] aToken,char delimiter,ClassLoader aClassLoader){
	
	fClassLoader = aClassLoader;
	try { 
		typeWriter.write(aToken);
	} catch ( IOException exc ) {};
	checkForValidType();
	if ( type == null && delimiter == DelimiterPeriod ){
		typeWriter.write(DelimiterPeriod);
	}
		
}
public Static(char[] aToken,char delimiter,ClassLoader aClassLoader,boolean isArray){
	this(aToken,delimiter,aClassLoader);
	isProcessingArrayDimension = true;
	arrayDimensions = new ArrayList(1);
	arrayDimensions.add(new Statement(aClassLoader));
}

public boolean isComplete(){
	return isComplete;
}
public boolean isArray(){
	return arrayDimensions != null;
}
/**
 * See whether or not we have a valid type
 * This uses the class loader given to us if one specified.  The reason for this
 * is that Eclipse uses a special plugin class loader and when we are running
 * in a VM inside eclipse ( e.g. for IDEVM proxy stuff ) we must have
 * reference to the plugin that is using us otherwise we can't load its classes
 */
protected void checkForValidType(){

	type = (Class) sPrimitiveTypes.get(typeWriter.toString());
	if (type == null)
		try {
			if ( fClassLoader == null ) {
				type = Class.forName(typeWriter.toString());
			} else {
				type = fClassLoader.loadClass(typeWriter.toString());
			}
		} catch ( ClassNotFoundException exc ) {
			try {
				type = Class.forName("java.lang." + typeWriter.toString()); //$NON-NLS-1$
				StringWriter writer = new StringWriter();
				writer.write(type.getName());
				typeWriter = writer;
			} catch ( ClassNotFoundException exc1 ) {}
		}
	
}
/**
 * If we have any static methods return the result of evaluating them against the type
 */
public Object evaluate() {

	// If we are an array but haven't created it do so
	if ( isArray() ) {
		if ( array == null ) {
			evaluateArray();
		}
		return array;
	} else {
		if ( type != null ) { 
			return type;
		} else { 
			// If we have no type then we are some kind incomplete expression that cannot be evaluted
			throw new RuntimeException();
		}
	}
}
/**
 * The type of us is either simply the class, or if we are an array then we must create the java.lang.reflect.Array
 * using the reflective APIs
 */
protected Object array;
public Class getTypeClass() {

	if ( isArray() ) {
		if ( array == null ) {
			// Do not evaluate the array and return its class.  Instead just work out the class by our type
			// and number of dimensions
			Object result = Array.newInstance(type,getNumberOfDimensions());
			return result.getClass();
		} else { 
			return array.getClass();
		}
	} else { 
		return type;
	}
}

protected String getTypeClassName() {
	return typeWriter.toString();
}
public Class getPrimitiveTypeClass(){
	return type;
}
protected int[] getNumberOfDimensions(){
	List dimensions = new ArrayList(1);
	dimensions.add(new Integer(fArrayArguments.size()));
	((MessageArgument)fArrayArguments.get(0)).contributeArgumentNumber(dimensions);
	// The total number of arguments is a set of Integer objects in the dimensions list
	// convert this to an int[]
	int[] intDimensions = new int[dimensions.size()];
	for (int i = 0; i < dimensions.size(); i++) {
		intDimensions[i] = ((Integer)dimensions.get(i)).intValue();
	}
	return intDimensions;
}
/**
 * Evaluate the array
 */
protected void evaluateArray(){
	if ( fArrayArguments != null ) { 
		// If the array isn't declared with a size but this is supplied with argument these will be in the fArrayArguments
		// new int[] { 2 ,3 } will have the constructor arguments supplied as two message arguments with 
		if ( array == null ) {
			// The size of the array arguments is our array size, however for a multi arg array we need
			// to find the size of any nested arrays within arguments themselves, e.g. 
			// new int[][] { {2,3} , {3,4} } then we have two array arguments, each of which is a MessageArgument
			// whose statement is an ArrayArguments that has two arguments, etc...
			// To find the number of arguments we create a list that we add our number of arguments to, and then
			// pass this all the way up the arguments chain using contributeArgumentNumber(List) so that each element
			// can add to the list the number of arguments ( if any ) that they have.  
			array = Array.newInstance(type,getNumberOfDimensions());
			// Set the elements in the array to be the result of evaluating the constructor arguments
			for (int i = 0; i < fArrayArguments.size(); i++) {
				Expression expression = (Expression)fArrayArguments.get(i);
				try {	
					Object element = expression.evaluate();
					Array.set(array,i,element);
				} catch ( Exception exc ) {
					// Any evaluation exceptions should be thrown back
					throw new RuntimeException();
				}
			}
		}
	} else if ( arrayDimensions != null ) {
		// To get the class of a reflective array we must actually first create it and then ask it for its class
		// The array Dimensions are present if the array was explicitly declared with a size, e.g. new int[2][3]
		// will have to arrayDimensions that represent the expressions 2 and 3 ( as NumberLiteral instances )
		if ( array == null ) {
			// Evaluate all of the arrayDimensions.  These should be integers
			int[] dimensionSizes = new int[arrayDimensions.size()];
			for (int i = 0; i < dimensionSizes.length; i++) {
				try { 
					Integer dimensionSize = (Integer) ((Expression)arrayDimensions.get(i)).evaluate();
					dimensionSizes[i] = dimensionSize.intValue();
				} catch ( Exception exc ) {
					throw new RuntimeException();
				}
			}
			// For a multi arg array we need to create using the static method on array that takes an int[] that represents
			// the number of dimensions, e.g. for new String[2][3] we do Array.newInstance(String.class,new int[] { 2 , 3 };
			array = Array.newInstance(type,dimensionSizes);
		}
	}
}
/**
 * If the token is a period then it could either be part of the type or else a static method call
 */
public Expression push(char[] token , char delimiter ) {

	// If we don't yet have a valid type then see if we now do
	if (type == null){
		try { 
			typeWriter.write(token);
		} catch ( IOException exc ) {};
		checkForValidType();
		// If we got a valid type and a period then remember it
		if ( delimiter == DelimiterPeriod ) {
			if (type != null) {
				completedWithPeriod = true;
				return this;
			}
		}		
	}
	
	if ( arrayDimensions != null && isProcessingArrayDimension ) {
		Expression lastArrayDimension = (Expression)arrayDimensions.get(arrayDimensions.size()-1);
		lastArrayDimension.push(token,delimiter);
		if ( delimiter == DelimiterCloseSquareBrace && isProcessingArrayDimension ) {
			isProcessingArrayDimension = false;
		}
		return this;
	}
	
	if ( delimiter == DelimiterOpenSquareBrace && !isProcessingArrayDimension ) {
		if ( arrayDimensions == null ) arrayDimensions = new ArrayList(1);
		Statement statement = new Statement(fClassLoader);
		arrayDimensions.add(statement);
		isProcessingArrayDimension = true;
		return this;		
	}
	
	// If we have a type and the delimiter is a ( then it must be a message beginning
	if ( type != null ) {
		if (delimiter == DelimiterOpenParen) {
			isComplete = true;
			return new Message( this , token , fClassLoader );
		} else if (completedWithPeriod){
			isComplete = true;
			Field field = new Field(this,token,fClassLoader);
			// If our token is a ), ' ' or , then the field is completed,
			// otherwise leave it open so it will process the remaining tokens
//			if (delimiter == DelimiterCloseParen || delimiter == DelimiterSpace || delimiter == DelimiterComma) {
			if (delimiter == DelimiterCloseParen || delimiter == DelimiterComma) {
				field.isComplete = true;
			}
			return field;
		}
	}
			
	// We are still looking for a type so append a .
	if ( type == null ) {
		typeWriter.write('.');
	}
	return this;
}
public String toString(){

	StringWriter writer = new StringWriter();
	writer.write("Static "); //$NON-NLS-1$
	if ( type == null ) { 
		writer.write("(Incomplete) {"); //$NON-NLS-1$
	} else {
		writer.write("(Complete) {"); //$NON-NLS-1$
	}
	writer.write(typeWriter.toString());
	writer.write("}"); //$NON-NLS-1$
	if ( arrayDimensions != null ) {
		writer.write(" array dimensions="); //$NON-NLS-1$
		writer.write(new Integer(arrayDimensions.size()).toString());
	}
	if ( fArrayArguments != null ) {	
		writer.write(" array dimensions="); //$NON-NLS-1$
		writer.write(new Integer(fArrayArguments.size()).toString());
	}
	return writer.toString();

}

public boolean isPrimitive() {
	return getTypeClass().isPrimitive();
}
public void setArrayArguments(List arguments) {
	fArrayArguments = arguments;
}
}
