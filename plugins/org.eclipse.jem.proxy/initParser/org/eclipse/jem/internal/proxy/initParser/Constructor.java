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
 *  $RCSfile: Constructor.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:07 $ 
 */


import java.util.*;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
import org.eclipse.jem.internal.proxy.common.MethodHelper;
/**
 * Insert the type's description here.
 * Creation date: (11/01/00 8:52:36 PM)
 * @author: Administrator
 */
public class Constructor extends Expression {
	public boolean argsOpened = false;
	public boolean argsClosed = false;
	public boolean trailingPeriod = false;
	public ArrayList arguments = new ArrayList(2);
	public Static type;
	protected boolean fIsComplete;
	public boolean insideArgsOpenedParen = false;
	public boolean insideArgsClosedParen = false;
	public boolean isString = false;
	public java.lang.reflect.Constructor ctor;
/**
 * Constructor constructor comment.
 */
public Constructor(ClassLoader aClassLoader) {
	super();
	fClassLoader = aClassLoader;
}

public boolean isComplete(){
	return fIsComplete;
}

/**
 * Invoke the construtor
 */
public Object evaluate() throws Exception {

	if ( type.isArray() ) {
		// For arrays we get the array that is actually held inside the static
		// To instantiate the array it is possible that the static knows the array size, e.g. new int[2]
		// or else it must be given this from the number of arguments we have, e.g. new int[] { 2 , 3 }
		if ( arguments.size() > 0 ) {
			type.setArrayArguments(arguments);
		}
		Object result = type.evaluate();
		// Deal with array initialization arguments
		return result;
	} else {
		// For non arrays we find the method and invoke it on the type
		cacheCtor();
		// Get the array of arguments
		Object[] args = new Object[arguments.size()];
		Iterator itr = arguments.iterator();
		for (int i = 0; i < arguments.size() ; i++){
			Expression anExpression = (Expression)itr.next();
			args[i] = anExpression.evaluate();
		}
	
		try {
			return ctor.newInstance(args);	
		} catch (Exception e) {
			// If we got this far, then we have a valid parse, so anything is an evaluation exception.
			throw new EvaluationException(e);
		}
	}
}
/** A constructor can only return the class of its type
 */
public Class getTypeClass() throws Exception {
	if ( type.isArray() ) {
		// For arrays we get the array that is actually held inside the static
		// To instantiate the array it is possible that the static knows the array size, e.g. new int[2]
		// or else it must be given this from the number of arguments we have, e.g. new int[] { 2 , 3 }
		if ( arguments.size() > 0 ) {
			type.setArrayArguments(arguments);
		}
	}
	return type.getTypeClass();
}

protected String getTypeClassName() {
	return type.getTypeClassName();
}

private void cacheCtor() throws Exception {
	
	if (ctor == null) {
		Class[] argTypes = new Class[arguments.size()];
		Iterator itr = arguments.iterator();
		for (int i=0; i<argTypes.length; i++)
			argTypes[i] = getEvaluationTypeClass((Expression) itr.next());
			
		try {
			ctor = MethodHelper.findCompatibleConstructor(getEvaluationTypeClass(this), argTypes);
		} catch (NoSuchMethodException e) {
			throw new EvaluationException(e);
		} catch (AmbiguousMethodException e) {
			throw new EvaluationException(e);
		}
	}
}
protected boolean isDelimiterOpened(char token){
	return type.isArray() ? token == DelimiterOpenElipse : token == DelimiterOpenParen;	
}
protected boolean isDelimiterClosed(char token){
	return type.isArray() ? token == DelimiterCloseElipse : token == DelimiterCloseParen;
}
/**
 * push method comment.
 */
public Expression push(char[] token, char tokenDelimiter) {

	// If we are closed and we receive a . with no token then remember this
	if ( argsClosed && !trailingPeriod && tokenDelimiter == DelimiterPeriod && token.length == 0 ) {
		trailingPeriod = true;
		return this;
	}
	// If we have been closed with a . and we receive a . then we are a field
	if ( trailingPeriod && tokenDelimiter == DelimiterPeriod ) {
		return new Field(this,token,fClassLoader);
	}
	// If we have been closed with a . and we receive a ( we are a message
	if ( trailingPeriod && tokenDelimiter == DelimiterOpenParen ) {
		return new Message(this,token,fClassLoader);
	}
	
	// Lazy initialize the type if required
	if ( type == null ) {
		switch ( tokenDelimiter ) {
			case DelimiterPeriod: {
				type = new Static(token,tokenDelimiter,fClassLoader);
				type.setClassLoader(fClassLoader);
				return this;
			}
			case DelimiterOpenParen: {
				type = new Static(token,tokenDelimiter,fClassLoader);
				type.setClassLoader(fClassLoader);
				argsOpened = true;
				return this;
			} 
			case DelimiterOpenSquareBrace: { 
//				throw new CannotProcessArrayTypesException();
				// Array dimenions are created as staements in the arrayDimensions
				// This is for expressions like new String[] or new String[3], where the Statement represents the 3, i.e.
				// the code that declares the size of the array
				type = new Static(token,tokenDelimiter,fClassLoader,true);
				return this;
			}
			// If we get a { and our static is an array then this is the array initialization parameters
			case DelimiterOpenElipse: { 
				if ( type != null && type.isArray() ) {
					argsOpened = true;
				}
			}
			default: {
				return null;
			}
		}
	}
	
	// If we have a static that is an array then it consumes token until it is complete
	if ( type != null && type.isArray() ) {
		// The type consumes token as long as it is still in the array declaration section
		if ( type.isProcessingArrayDimension || tokenDelimiter == DelimiterOpenSquareBrace ) {
			type.push(token,tokenDelimiter);
			return this;
		}
	}
		
	// If the type is incomplete and the token is a . ( for another class ) or a [ for an array then push it onto the type
	if (!type.isArray() && type.getTypeClass() == null && (tokenDelimiter == DelimiterPeriod || tokenDelimiter == DelimiterOpenSquareBrace)) {
		type.push(token , tokenDelimiter);
		return this;
	}
	// If the type is incomplete and the token is a ( then push it onto the type and open the parens
	if (!type.isArray()){
		if (type.getTypeClass() == null && !argsOpened && isDelimiterOpened(tokenDelimiter)) {
			argsOpened = true;
			insideArgsOpenedParen = true;
			type.push(token , DelimiterSpace );
			return this;
		}
	} else { 
		if ( !argsOpened && isDelimiterOpened(tokenDelimiter)) {
			argsOpened = true;
			insideArgsOpenedParen = true;
			return this;			
		}
	}
	
	// If the args are already closed and we get another close expression, then just return ourselves
	// This occurs in the case of nested array arguments within constructors
	if ( argsClosed && (tokenDelimiter == DelimiterCloseParen)){
		fIsComplete = true;
		return this;
	}
	
	// If the arguments are closed and we receive a { then this is an inner class declaration, e.g.
	// new javax.swing.table.DefaultTableModel(){}
	// This can't be processed by us so we need to throw an exception
	if (argsClosed && tokenDelimiter == DelimiterOpenElipse ) {
		throw new CannotProcessInnerClassesException();
	}

	// If the args are opened and a token was supplied then it must be an argument
	if (argsOpened){
		// If we already have an incomplete argument then this may be a constructor, a static reference, etc...
		// and we should forward the request to the argument
		Expression openArgument = null;
		if ( arguments.size() > 0 ){
			openArgument = (Expression)arguments.get(arguments.size()-1);
			if ( !openArgument.isComplete() ) {
				openArgument.push(token,tokenDelimiter);
				// If the argument is complete and we received a ) then the constructor is complete
				// or if we receive a } and are an array we are complete
				if ( openArgument.isComplete() && isDelimiterClosed(tokenDelimiter) ){
					argsClosed = true;
				}
				return this;
			} 
		}
			
		Expression newArgument = null;
		// If we are not already inside the open arg and we get another open paren this is probably a cast
		// or some kind of statement, and OperMinus is probably the start of a number
		// If the args are not already open we must be opened with either a { or a ( depending on whether we are an array or not
		// however if the args are opened already and we receive a ( then this is the start of a new statement
//		if ((!insideArgsOpenedParen && isDelimiterOpened(tokenDelimiter)) || tokenDelimiter == DelimiterOpenParen){
		if (tokenDelimiter == DelimiterOpenElipse || tokenDelimiter == DelimiterOpenParen){
			insideArgsOpenedParen = true;
			newArgument = new Statement(fClassLoader).push(token,tokenDelimiter);
			if ( newArgument instanceof ArrayArguments ) {
				((ArrayArguments)newArgument).setType(type);
			}
			newArgument = new MessageArgument(newArgument);
			arguments.add(newArgument);

		}
		
		// Start of a possible string or number or character
		if ( ( token.length > 0 || tokenDelimiter == DelimiterQuote || tokenDelimiter == DelimiterSingleQuote) || tokenDelimiter == OperMinus ){
			newArgument = new Statement(fClassLoader).push(token,tokenDelimiter);
			newArgument = new MessageArgument(newArgument);
			arguments.add(newArgument);
		}
		// If the token after the argument is a ) then the message is being closed
		if ( !insideArgsOpenedParen || argumentsAreComplete() && isDelimiterClosed(tokenDelimiter) ) {
			argsClosed = true;
			return this;
		}
		if ( insideArgsOpenedParen && isDelimiterClosed(tokenDelimiter) ) {
			insideArgsClosedParen  = true;
			return this;
		}

		// If the token after the argument is a , or a ' ' then the argument is being closed
		if ( tokenDelimiter == DelimiterComma || tokenDelimiter == DelimiterSpace ) {
			// If our arguments are closed then we must be complete.  We need to signal the fact we are closed
			// otherwise we will not be processed correctly if we are part of a stack 
			if ( argsClosed ) {
				fIsComplete = true;
			}			
			return this;
		}
		
		// If we receive a close bracket then we are closed as long as the last argument is closed
		if(argsOpened &&  isDelimiterClosed(tokenDelimiter)){
			// No parms - we are a closed constructor
			if(arguments.size() == 0){
				argsClosed = true;
				return this;
			} 
		}
		
		// If our last argument is closed and receive a ) and we have no new argument then we are closed
		if (tokenDelimiter == DelimiterCloseParen && newArgument == null){
			Expression lastArgument = (Expression)arguments.get(arguments.size()-1);
			if ( lastArgument.isComplete() ) {
				argsClosed = true;
				return this;
			}
		}			
				
		// Otherwise the new argument is stil processing.  Return it
		return newArgument;
	}

	return this;
}
protected boolean argumentsAreComplete(){
	if ( arguments.size() == 0) return true;
	return ((Expression)arguments.get(arguments.size()-1)).isComplete();
}
public String toString(){

	java.io.StringWriter writer = new java.io.StringWriter();
	writer.write("Constructor \""); //$NON-NLS-1$
	if ( type != null ) {
		writer.write(type.typeWriter.toString());
	} else {
		writer.write("UNTYPED"); //$NON-NLS-1$
	}
	for (int i = 0; i < arguments.size() ; i++){
		writer.write(" Arg("); //$NON-NLS-1$
		writer.write("" + (i+1)); //$NON-NLS-1$
		writer.write(") = "); //$NON-NLS-1$
		writer.write(arguments.get(i).toString());
	}
	
	return writer.toString();

}

/**
 * This is never primitive
 */
public boolean isPrimitive() {
	return false;
}
}
