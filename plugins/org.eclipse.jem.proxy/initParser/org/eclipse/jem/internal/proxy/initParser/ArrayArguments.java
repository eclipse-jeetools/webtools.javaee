/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.initParser;
/*
 *  $RCSfile: ArrayArguments.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */

import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.*;
/**
 * This represents the arguments in a multi dimensional array with construction parameters, e.g.
 * new int[][] { { 2 ,3 } , { 4 ,5 }}
 * the arguments of the constructor will be instance of ArrayArguments 
 */
public class ArrayArguments extends Expression {
	
	Static fType;
	List arguments = new ArrayList(2);
	boolean argsClosed;
	boolean insideArgsOpenedParen;
	boolean insideArgsClosedParen;
	
public Expression push(char[] token, char tokenDelimiter) {
	
	// If the args are opened and a token was supplied then it must be an argument
	//if ( argsOpened && ( tokenDelimiter == DelimiterCloseParen || tokenDelimiter == DelimiterComma || 
	//tokenDelimiter == DelimiterSpace || tokenDelimiter == DelimiterQuote || tokenDelimiter == DelimiterPeriod)) {
	if (!argsClosed){	
		if ( arguments.size() > 0 ){
			Expression openArgument = (Expression)arguments.get(arguments.size()-1);
			if ( !openArgument.isComplete() ) {
				openArgument.push(token,tokenDelimiter);
				// If the argument is complete and we received a ) then the message is complete
				if ( openArgument.isComplete() && tokenDelimiter == DelimiterCloseElipse){
					argsClosed = true;
				}
				return this;
			}
		}	
		
		// We must have a new argument - process accordingly	
		Expression newArgument = null;
		if (!insideArgsOpenedParen && tokenDelimiter == DelimiterOpenParen){
			insideArgsOpenedParen = true;
			newArgument = new Statement(fClassLoader).push(token,tokenDelimiter);
			newArgument = new MessageArgument(newArgument);
			arguments.add(newArgument);

		}
		if ( token.length > 0 || tokenDelimiter == DelimiterQuote || tokenDelimiter == DelimiterSingleQuote || tokenDelimiter == OperMinus ) {
			newArgument = new Statement(fClassLoader).push(token,tokenDelimiter);
			newArgument = new MessageArgument(newArgument);
			arguments.add(newArgument);
		}
		// If the token after the argument is a ) then the message is being closed
		if ( !insideArgsOpenedParen  && tokenDelimiter == DelimiterCloseElipse ) {
			argsClosed = true;
			return this;
		}
		if ( insideArgsOpenedParen  && tokenDelimiter == DelimiterCloseElipse ) {
			insideArgsClosedParen  = true;
			argsClosed = true;
			return this;
		}
	}
	return this;
}
/**
 * In the case of the expression new int[][] { {2,3} , {4,5} } then we are the 2,3 or the 4,5
 * to evaluate we must return an array of ints based on our size.  The type we return is determined by the initial 
 * constructor who will have given this to us as our fType
 */	
public Object evaluate() throws Exception {

	Object result = Array.newInstance(fType.getPrimitiveTypeClass(),arguments.size());
	for (int i = 0; i < arguments.size(); i++) {
		Object argumentValue = ((Expression)arguments.get(i)).evaluate();
		Array.set(result,i,argumentValue);
	}
	return result;
}

public Class getTypeClass() throws Exception {
	return null;
}

public boolean isComplete() {
	return argsClosed;
}

public boolean isPrimitive() throws Exception {
	return false;
}
public void setType(Static type) {
	fType = type;
}
public void contributeArgumentNumber(List argumentNumberList){
	
	// Add the number of arguments we have to the list
	argumentNumberList.add(new Integer(arguments.size()));
	// If our arguments are themselves array arguments ( which will occur in an array of 3 or more dimensions )
	// then we should cascade this message to our argument so that the total array depth is known
	if ( arguments.size() > 0 ) {
		Object firstArgument = arguments.get(0);
		if ( firstArgument instanceof ArrayArguments ) {
//			((ArrayArguments)firstArgument)contributeArgumentNumber(argumentNumberList);
		}
	}
	
}
public String toString(){
	StringWriter writer = new StringWriter();
	writer.write("Args("); //$NON-NLS-1$
	writer.write(new Integer(arguments.size()).toString());
	writer.write(")-"); //$NON-NLS-1$
	writer.write(arguments.toString());
	return writer.toString();
}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.initParser.Expression#getTypeClassName()
	 */
	protected String getTypeClassName() {
		return "";	// Not applicable //$NON-NLS-1$
	}

}
