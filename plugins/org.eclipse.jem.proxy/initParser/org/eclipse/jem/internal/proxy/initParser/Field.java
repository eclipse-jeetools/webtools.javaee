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
 *  $RCSfile: Field.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Insert the type's description here.
 * Creation date: (11/05/00 10:39:28 PM)
 * @author: Administrator
 */
public class Field extends Expression {
	public boolean isComplete;
	protected java.lang.reflect.Field fField;
	public Expression receiver;
	public String fieldName;
	protected Class fResultType;
	
public Field(Expression aReceiver , char[] token , ClassLoader aClassLoader){

	receiver = aReceiver;
	if ( token.length > 0 ){
		fieldName = new String(token);
	}

}
public boolean isComplete(){
	return isComplete;
}

/**
 * Send the message to the receiver
 */
public Object evaluate() throws Exception {
	Object receiverValue = receiver.evaluate();	// So if it fails, we get an evaluation exception going on up.
	try {
		cacheField();		
		return fField.get(receiverValue);
	} catch (Exception e) {
		throw new EvaluationException(e);	// Any exception at this point is an evaluation exception.
	}
}

/**
 * Get the field from reflection.
 */
protected void cacheField() throws Exception {
	if (fField == null) {
		Class aReceiverClass = getEvaluationTypeClass(receiver);
		fField = aReceiverClass.getField(fieldName);
		fResultType = fField.getType();		
	}
}

/**
 * The result type is the type of the field.
 */
public Class getTypeClass() throws Exception {
	if ( fResultType == null ) {
		cacheField();
	}
	return fResultType;
}

protected String getTypeClassName() {
	return "";	// If we got this far, we don't know what it is. //$NON-NLS-1$
}
/**
 * push method comment.
 */
public Expression push(char[] token, char tokenDelimiter) {

	// If the delimiter is a ( then this is the start of a message
	if ( tokenDelimiter == DelimiterOpenParen ) {
		return new Message(this , token , fClassLoader);
	}

	// If the delimiter is a . then this is a field
	if (token.length > 0  && ( tokenDelimiter == DelimiterPeriod || tokenDelimiter == DelimiterSpace
	|| tokenDelimiter == DelimiterComma)) {
		isComplete = true;
		return new Field(this , token , fClassLoader);
		
	}
	
	if(fieldName != null && tokenDelimiter == DelimiterComma){
		isComplete = true;
		return this;
	}
	
	// If we have no delimiter or a close paren then we have just received the field name
	if (!isComplete && fieldName == null) {
		fieldName = new String(token);
		return this;
	}
	
	if(tokenDelimiter == BitOR || tokenDelimiter == BitAND){
		isComplete = true;
		PrimitiveOperation result = new PrimitiveOperation(this,tokenDelimiter);
		return result;
	}
	
	// Ignore spaces as this maybe padding between the field and the next valid token
	if(token.length == 0 && tokenDelimiter == ' ') return this;

	throw new RuntimeException(java.text.MessageFormat.format(ProxyInitParserMessages.getString(ProxyInitParserMessages.UNEXPECTED_TOKEN), new Object[] {new StringBuffer(token.length+1).append(token).append(tokenDelimiter).toString()}));
	
}
public String toString(){

	java.io.StringWriter writer = new java.io.StringWriter();
	writer.write("Field Name=\""); //$NON-NLS-1$
	if ( fieldName != null ) {
		writer.write(fieldName);
	} else {
		writer.write("UNNAMED"); //$NON-NLS-1$
	}
	writer.write("\" Receiver="); //$NON-NLS-1$
	if ( receiver != null ){
		writer.write(receiver.toString());
	} else {
		writer.write("NONE"); //$NON-NLS-1$
	}
	return writer.toString();

}

/**
 * Test the field type to determine if primitive or not.
 */
public boolean isPrimitive() throws Exception {
	cacheField();
	return getTypeClass().isPrimitive();
}
}
