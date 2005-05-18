/*
 * This is for primitive expressions that are optimized out the compiler such as bit or, bit and, etc..
   */
package org.eclipse.jem.internal.proxy.initParser;

import java.text.MessageFormat;

/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PrimitiveOperation.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/18 23:11:27 $ 
 */

public class PrimitiveOperation extends Expression {
	
	protected int operation;				// This can be BitOR or BitAND
	protected Expression receiver;	// The left hand side of the expression
	protected boolean isComplete = false;
	
	public PrimitiveOperation(Expression aReceiver, int anOperation){
		receiver = aReceiver;
		operation = anOperation;
	}

	public Object evaluate() throws Exception {
		Object leftHandSide = receiver.evaluate();
		Object rightHandSide = currentExpression.evaluate();
		if (leftHandSide instanceof Integer && rightHandSide instanceof Integer) {
			if(operation == BitOR) {
				return new Integer(((Integer)leftHandSide).intValue() | ((Integer)rightHandSide).intValue());				
			} else if(operation == BitAND) {
				return new Integer(((Integer)leftHandSide).intValue() & ((Integer)rightHandSide).intValue());				
			}
		}
		throw new RuntimeException(MessageFormat.format(ProxyInitParserMessages.getString("PrimitiveOperation.Evaluate.InvalidOperator_EXC_"), new Object[]{getOperDescription(), leftHandSide, rightHandSide}));		 //$NON-NLS-1$
	}
	protected String getOperDescription(){
		if(operation == BitOR) return "|"; //$NON-NLS-1$
		if (operation == BitAND) return "&";			 //$NON-NLS-1$
		return "???"; //$NON-NLS-1$
	}

	public boolean isComplete() {
		return isComplete;
	}

	public Class getTypeClass() throws Exception {
		return Integer.TYPE;
	}

	protected String getTypeClassName() {
		return Integer.TYPE.getName();
	}

	public Expression push(char[] token, char tokenDelimiter) {
	
		// Create a new statement if the argument is null ( we are always created with a receiver )
		if(currentExpression == null && token.length > 0){
			currentExpression = new Statement(fClassLoader).push(token,tokenDelimiter);
			pushExpressionStack(currentExpression);
			return this;
		} 
		
		// Ignore whitespace
		if(token.length == 0 && tokenDelimiter == ' ') return this;
		
		// If we have an argument we just keep pushing the expression onto this
		if(currentExpression != null){
			Expression result = currentExpression.push(token,tokenDelimiter);
			if(result != currentExpression){
				pushExpressionStack(result);
			}
		}
		
		if(currentExpression != null && currentExpression.isComplete()){
			if(tokenDelimiter == DelimiterComma){
				isComplete = true;
				return this;
			} else if (tokenDelimiter == DelimiterCloseParen){
				if(receiver.parenthesisLevel > 0){
					receiver.parenthesisLevel--;
				} else {
					isComplete = true;			
					return this;		
				}
			}
		}				
	
		return this;
	}

	public boolean isPrimitive() throws Exception {
		return true;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		if (receiver != null) buffer.append(receiver.toString());
		buffer.append(getOperDescription());
		if (currentExpression != null) buffer.append(currentExpression.toString()); 
		return buffer.toString();
	}

}
