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
 *  $RCSfile: StringLiteral.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */



public class StringLiteral extends Expression {
	protected boolean isComplete;
	public String value;
	public StringBuffer valueBuffer = new StringBuffer();
	protected boolean isEscaped = false;

/**
 * constructor
 */
public StringLiteral(){
}
	
/**
 * evaluate method comment.
 */
public Object evaluate() {
	return valueBuffer.toString();
}

public boolean isComplete(){
	return isComplete;
}
/**
 * We must evaluate ourself and return the type of the result
 */
 
public Class getTypeClass() {
	return String.class;
}

protected String getTypeClassName() {
	return String.class.getName();
}

/**
 *This string might be broken into a few tokens
 *so we need a StringBuffer.
 * 
 * For now can only handle \" and \\ as escapes. 
 * Any other escapes will be left untouched. (i.e.
 * "\r" will be a backslash followed by "r", not a
 * return char.
 */
public Expression push(char[] token , char delimiter){
	
	if (isEscaped) {
		isEscaped = false;
		if (token.length != 0) {
			// Had an escape followed by stuff, so not a true esc for our current definition
			valueBuffer.append(DelimiterEscape);
		} else {
			if (delimiter == DelimiterQuote || delimiter == DelimiterEscape)
				valueBuffer.append(delimiter);	// It was a true escape.
			else {
				valueBuffer.append(DelimiterEscape);	// If wasn't a true escape
				valueBuffer.append(delimiter);
			}
			return this;
		}
	}
	
	valueBuffer.append(token);
	
	if (delimiter == DelimiterQuote){		
		isComplete =true;
		return this;
	}
	
	// If the delimiter is an escape character remember it so we can escape
	// the next token, otherwise treat it as a literal
	if (delimiter == DelimiterEscape ){
		isEscaped = true;
	} else {
		valueBuffer.append(delimiter);
	}
	return this;
}

/**
 * Strings are not primitives.
 */
public boolean isPrimitive() {
	return false;
}
public String toString(){
	StringBuffer buffer = new StringBuffer();
	buffer.append("String(\""); //$NON-NLS-1$
	if ( valueBuffer != null ) {
		buffer.append(valueBuffer.toString());
	}
	buffer.append("\""); //$NON-NLS-1$
	return buffer.toString();
}
}