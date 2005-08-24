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
 *  $RCSfile: Block.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */

/**
 */
public class Block extends Expression {
	
	protected boolean fIsClosed = false;
	protected boolean fIsComplete = false;
	protected boolean fMessageOrField = false;
	
	public Block(Expression anExpression){
		currentExpression = anExpression;
	}

	public Block(ClassLoader fClassLoader){
		currentExpression = new Statement(fClassLoader);
	}

	public Object evaluate() throws Exception {
		return currentExpression.evaluate();
	}

	public boolean isComplete() {
		return fIsComplete;
	}

	public Class getTypeClass() throws Exception {
		return currentExpression.getTypeClass();
	}


	public Expression push(char[] token, char tokenDelimiter) {
			
		// If we are closed (i.e. completed with a )) and we get a ) or , then we are all finished up
		// We should not forward this token to the expression, as it isComplete
		if ((fIsClosed || currentExpression.isComplete()) && (tokenDelimiter == DelimiterCloseParen || tokenDelimiter == DelimiterComma)){
			fIsComplete = true;
			return this;
		}
		
		// If we are a message of field and we get a ( we are message or a field otherwise
		if (fMessageOrField){
			fMessageOrField = false;
			// The problem now is that we are a block of an expression and we need to create a message
			// where we are the receiver.  However we are on the stack and going to be popped when the message finishes
			// so we must alter ourself to be a block whose current expression is message whose argument
			// is a copy of us
			if ( tokenDelimiter == DelimiterOpenParen){
				Message message = new Message(currentExpression,token,fClassLoader);
				currentExpression = message;
				return message;
			} else {
				Field field = new Field(currentExpression,token,fClassLoader);
				currentExpression = field;
				return field;
			}
		}
		
		// If we are closed and we get a . then we must begin a message or field expression
		if (currentExpression.isComplete() && tokenDelimiter == DelimiterPeriod ){
			fMessageOrField = true;
			return this;
		}
		
		Expression result = currentExpression.push(token,tokenDelimiter);
		// If the result if a push then push the stack
		if ( result != currentExpression ) {
			pushExpressionStack(result);	
		}
		// If our current expression is complete and we are receiving a close parent then we are closed
		if (currentExpression.isComplete() && tokenDelimiter == DelimiterCloseParen){
			fIsClosed = true;

		}
		// If we are here the current expression is still processing tokens so we should return it
		return currentExpression;
	}

	public boolean isPrimitive() throws Exception {
		return false;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Block("); //$NON-NLS-1$
		if ( currentExpression != null ) {
			buffer.append(currentExpression.toString());
		}
		buffer.append(')');
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.initParser.Expression#getTypeClassName()
	 */
	protected String getTypeClassName() {
		return currentExpression.getTypeClassName();
	}

}
