package org.eclipse.jem.internal.proxy.initParser;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: CharLiteral.java,v $
 *  $Revision: 1.4 $  $Date: 2005/06/21 20:22:51 $ 
 */

import java.text.MessageFormat;



public class CharLiteral extends Expression {
	protected boolean isComplete;
	public char value;
	public StringBuffer valueBuffer = new StringBuffer();
	protected boolean isEscaped = false;

/**
 * constructor
 */
public CharLiteral(){
}
	
/**
 * evaluate method comment.
 */
public Object evaluate() throws Exception {
	if (valueBuffer.length() == 1)
		return new Character(valueBuffer.charAt(0));
	else
		throw new EvaluationException(
			new IllegalArgumentException(MessageFormat.format(ProxyInitParserMessages.getString("CharTooComplicated_EXC_"), //$NON-NLS-1$
				new Object[] {valueBuffer.toString()})));
}

public boolean isComplete(){
	return isComplete;
}
/**
 * We must evaluate ourself and return the type of the result
 */
 
public Class getTypeClass() {
	return Character.TYPE;
}

protected String getTypeClassName() {
	return Character.TYPE.getName();
}
/**
 *This string might be broken into a few tokens
 *so we need a StringBuffer 
 */
public Expression push(char[] token , char delimiter){
	
	if (isEscaped) {
		isEscaped = false;
		if (token.length != 0) {
			// Had an escape followed by stuff, so not a true esc for our current definition
			valueBuffer.append(DelimiterEscape);
		} else {
			if (delimiter == DelimiterSingleQuote || delimiter == DelimiterEscape)
				valueBuffer.append(delimiter);	// It was a true escape.
			else {
				valueBuffer.append(DelimiterEscape);	// If wasn't a true escape
				valueBuffer.append(delimiter);
			}
			return this;
		}
	}
	
	valueBuffer.append(token);
	
	if (delimiter == DelimiterSingleQuote){		
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
 * chars are primitives.
 */
public boolean isPrimitive() {
	return true;
}
public String toString(){
	StringBuffer buffer = new StringBuffer();
	buffer.append("Char('"); //$NON-NLS-1$
	if ( valueBuffer != null ) {
		buffer.append(valueBuffer.toString());
	}
	buffer.append(')');
	return buffer.toString();
}

}