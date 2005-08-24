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
 *  $RCSfile: Message.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:07 $ 
 */


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
import org.eclipse.jem.internal.proxy.common.MethodHelper;

public class Message extends Expression {
	protected boolean trailingPeriod = false;
	protected Method fMethod;
	public Expression receiver;
	public String message;
	public boolean argsClosed = false;
	public boolean insideArgsOpenedParen = false;
	public boolean insideArgsClosedParen = false;
	protected boolean isComplete = false;
	public ArrayList arguments = new ArrayList(2);
	
public Message(Expression aReceiver , char[] token , ClassLoader aClassLoader){
	receiver = aReceiver;
	message = new String(token);
	fClassLoader = aClassLoader;
}
public boolean isComplete(){
	return isComplete;
}

/**
 * Send the message to the receiver
 */
public Object evaluate() throws Exception {
	cacheMethod();
	
	// Get the array of arguments
	Object[] args = new Object[arguments.size()];
	Iterator itr = arguments.iterator();
	for (int i = 0; i < arguments.size() ; i++){
		Expression anExpression = (Expression)itr.next();
		args[i] = anExpression.evaluate();
	}
	
	Object receiverValue = receiver.evaluate();	// So if evaluation exception thrown, we don't wrapper again.
	
	try {
		return fMethod.invoke(receiverValue, args);
	} catch (Exception e) {
		throw new EvaluationException(e);
	}
}

/**
 * Cache the message
 */
protected void cacheMethod() throws Exception {
	if (fMethod == null) {
		Class[] argTypes = new Class[arguments.size()];
		Iterator itr = arguments.iterator();
		for (int i=0; i<argTypes.length; i++)
			argTypes[i] = getEvaluationTypeClass((Expression) itr.next());
			
		try {
			fMethod = MethodHelper.findCompatibleMethod(getEvaluationTypeClass(receiver), message, argTypes);
		} catch (NoSuchMethodException e) {
			throw new EvaluationException(e);
		} catch (AmbiguousMethodException e) {
			throw new EvaluationException(e);
		}
	}
}


/**
 * getTypeClass method comment.
 */
public Class getTypeClass() throws Exception {
	cacheMethod();
	return fMethod.getReturnType();
}

protected String getTypeClassName() {
	return "";	// If we got this far, we don't know what it is. //$NON-NLS-1$
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
	
	// If we have been closed and we receive a , or a ) we are complete - this is a structural token intended for someone else,
	// probably a message argument lower down the stack
	if (argsClosed && (tokenDelimiter == DelimiterComma || tokenDelimiter == DelimiterCloseParen)){
		isComplete = true;
		return this;
	}
	

	// If the args are opened and a token was supplied then it must be an argument
	//if ( argsOpened && ( tokenDelimiter == DelimiterCloseParen || tokenDelimiter == DelimiterComma || 
	//tokenDelimiter == DelimiterSpace || tokenDelimiter == DelimiterQuote || tokenDelimiter == DelimiterPeriod)) {
	if (!argsClosed){	
		if ( arguments.size() > 0 ){
			Expression openArgument = (Expression)arguments.get(arguments.size()-1);
			if ( !openArgument.isComplete() ) {
				openArgument.push(token,tokenDelimiter);
				// If the argument is complete and we received a ) then the message is complete
				if ( openArgument.isComplete() && tokenDelimiter == DelimiterCloseParen){
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
		if ( newArgument == null && (token.length > 0 || tokenDelimiter == DelimiterQuote || tokenDelimiter == DelimiterSingleQuote || tokenDelimiter == DelimiterOpenParen )) {
			newArgument = new Statement(fClassLoader).push(token,tokenDelimiter);
			newArgument = new MessageArgument(newArgument);
			arguments.add(newArgument);
		}
		// If the token after the argument is a ) then the message is being closed
		if ( !insideArgsOpenedParen  && tokenDelimiter == DelimiterCloseParen ) {
			argsClosed = true;
			return this;
		}
		if ( insideArgsOpenedParen  && tokenDelimiter == DelimiterCloseParen ) {
			insideArgsClosedParen  = true;
			return this;
		}

		// If the token after the argument is a , or a ' ' then the argument is being closed
		if ( tokenDelimiter == DelimiterComma || tokenDelimiter == DelimiterSpace ) {
			return this;
		}
		
		// Otherwise the new argument is stil processing.  Return it
//		return newArgument;
	}
	
	// If we don't have a message yet, then consume this one
	if ( message.length() == 0 ) {
		message = new String(token);
		return this;
	}	

	return this;	
}
public String toString(){

	java.io.StringWriter writer = new java.io.StringWriter();
	writer.write("Msg Name=\""); //$NON-NLS-1$
	if ( message != null ) {
		writer.write(message);
	} else {
		writer.write("UNNAMED"); //$NON-NLS-1$
	}
	writer.write("\" Rcv=("); //$NON-NLS-1$
	if ( receiver != null ){
		writer.write(receiver.toString());
	} else {
		writer.write("NONE"); //$NON-NLS-1$
	}
	writer.write(')');
	if ( arguments != null ) {
		writer.write("Args(");		 //$NON-NLS-1$
		Iterator iter = arguments.iterator();
		int i=1;
		while(iter.hasNext()){
			writer.write(new Integer(i).toString());
			writer.write('(');
			writer.write(iter.next().toString());
			writer.write("),");			 //$NON-NLS-1$
		}
		writer.write(')');				
	}
	
	return writer.toString();

}

/**
 * Is the message result a primitive.
 */
public boolean isPrimitive() throws Exception {
	cacheMethod();
	return fMethod.getReturnType().isPrimitive();
}
}
