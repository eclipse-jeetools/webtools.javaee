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
 *  $RCSfile: StringLiteral.java,v $
 *  $Revision: 1.3 $  $Date: 2005/06/23 01:48:07 $ 
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
 */
public Expression push(char[] token , char delimiter){
	int appendTokenStart = 0;
	int appendTokenLength = token.length;
	if (isEscaped) {
		isEscaped = false;
		if (token.length != 0) {
			char c = token[0];
			switch (c) {
				case 'b':
					valueBuffer.append('\b');
					appendTokenStart = 1;
					appendTokenLength--;
					break;
				case 't':
					valueBuffer.append('\t');
					appendTokenStart = 1;
					appendTokenLength--;
					break;
				case 'n':
					valueBuffer.append('\n');
					appendTokenStart = 1;
					appendTokenLength--;
					break;
				case 'r':
					valueBuffer.append('\r');
					appendTokenStart = 1;
					appendTokenLength--;
					break;
				case 'u':
					// Unicode, next four gather for text;
					if (appendTokenLength>=5) {
						valueBuffer.append((char) Integer.parseInt(new String(token, 1, 4), 16));
						appendTokenStart=5;
						appendTokenLength-=5;
					}
					break;
			}
			// If we didn't append anything, then not a true escape, so put the escape on.
			if (appendTokenStart==0)
				valueBuffer.append(DelimiterEscape);
		} else {
			if (delimiter == DelimiterQuote || delimiter == DelimiterEscape || delimiter == DelimiterSingleQuote)
				valueBuffer.append(delimiter);	// It was a true escape.
			else {
				valueBuffer.append(DelimiterEscape);	// If wasn't a true escape
				valueBuffer.append(delimiter);
			}
			return this;
		}
	}
	
	if (appendTokenLength > 0)
		valueBuffer.append(token, appendTokenStart, appendTokenLength);
	
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