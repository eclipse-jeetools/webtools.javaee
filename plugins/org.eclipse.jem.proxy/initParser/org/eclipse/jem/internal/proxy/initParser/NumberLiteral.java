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
 *  $RCSfile: NumberLiteral.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


public class NumberLiteral extends Expression {
	protected String numberString = ""; //$NON-NLS-1$
	protected boolean isComplete = false;
	protected Class numberClass = Integer.TYPE;
	protected int exponentFlag = NO_EXPONENT;
	
	protected static final int
		NO_EXPONENT = 0,
		HAS_EXPONENT = 1,
		HAS_EXPONENT_MINUS = 2;
/**
 * evaluate method comment.
 */
public NumberLiteral(){
}
/**
 * evaluate method comment.
 */
public Object evaluate() throws Exception {
	try {
		// Return a number of the correct instance
		if (numberClass == Float.TYPE){
			return new Float(Float.parseFloat(numberString));
		} else if (numberClass == Long.TYPE ) {
			return Long.decode(numberString);
		} else if (numberClass == Double.TYPE ){
			return new Double(Double.parseDouble(numberString));
		} else
			return Integer.decode(numberString);
	} catch (Exception e) {
		throw new EvaluationException(e);
	}
	
}
public boolean isComplete(){
	return isComplete;
}

/**
 * Return the type of number that we are.
 */
public Class getTypeClass(){
	return numberClass;
}

protected String getTypeClassName() {
	return numberClass != null ? numberClass.getName() : ""; //$NON-NLS-1$
}
/**
 * If the delimiter is an operation such as a +,-,/,* then we can consume it
 * If the delimiter is period and we don't already have one then it is the decimal portion of us
 */
public Expression push(char[] token , char delimiter){
		
	// Close brackets complete use		
	if(delimiter == DelimiterCloseParen || delimiter == DelimiterComma || delimiter == DelimiterCloseElipse){
		isComplete = true;
	}
	
	if (delimiter == OperMinus && numberString.length() == 0)
		numberString+=OperMinus;	// No digits yet, but we have a minus sign, so add to accumulating number.
		
	numberString += processNumberToken(token);	
	
	// If we have part of a number and are asked to do some arithmetic throw an 
	// error that this has not been coded yet, except minus could be part of an exponent.
	if(numberString.length() > 0 && token.length > 1) {
		switch(delimiter){
			case OperMinus:
				if (exponentFlag == HAS_EXPONENT) {
					exponentFlag = HAS_EXPONENT_MINUS;
					numberString += OperMinus;
					break;
				}
			case OperPlus:			
			case OperMultiply:
			case OperDivide:
			{
				throw new RuntimeException("Can't do arithmetic yet"); //$NON-NLS-1$
			}
		}
	}
	
	if (delimiter == DelimiterPeriod){
		numberClass = Double.TYPE;	// A decimal makes it double.
		numberString += DelimiterPeriod;
	}	

	if(delimiter == BitOR || delimiter == BitAND){
			Expression result = new PrimitiveOperation(this,delimiter);
			isComplete = true;
			return result;
	}

	return this;
	
}
protected String processNumberToken(char[] token){
	
	for (int i=0; i<token.length; i++)
		if (token[i] == 'E' || token[i] == 'e') {
			numberClass = Double.TYPE;	// Definitely a floating because of this.
			exponentFlag = HAS_EXPONENT;
			break;
		}
			
	// The token could be a number, e.g. 10 or 5 or could be qualified with a primitive
	// suffix, e.g. 10l or 5f
	char[] numberToken = token;
	if ( token.length > 0 ) {
		switch(token[token.length - 1]){
			case 'F': 			
			case 'f':
				numberClass = Float.TYPE;
				numberToken = new char[token.length - 1];
				System.arraycopy(token,0,numberToken,0,numberToken.length);
				isComplete = true;
				break;
			case 'L':			
			case 'l':
				numberClass = Long.TYPE;
				numberToken = new char[token.length - 1];
				System.arraycopy(token,0,numberToken,0,numberToken.length);				
				isComplete = true;
				break;
			case 'D':			
			case 'd':
				numberClass = Double.TYPE;
				numberToken = new char[token.length - 1];
				System.arraycopy(token,0,numberToken,0,numberToken.length);				
				isComplete = true;
				break;
		}
	}
			
	return new String(numberToken);
}
public String toString(){

	java.io.StringWriter writer = new java.io.StringWriter();
	writer.write("Number \""); //$NON-NLS-1$
	writer.write(numberString);
	writer.write("\""); //$NON-NLS-1$
	return writer.toString();

}

/**
 * Number literals are primitive
 */
public boolean isPrimitive() {
	return true;
}
}
