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
 *  $RCSfile: MessageArgument.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:55:20 $ 
 */


import java.io.StringWriter;
import java.util.*;

public class MessageArgument extends Expression {

	protected boolean isComplete = false;
	Expression fExpression;
	ArrayList fExpressionStack = new ArrayList();

public MessageArgument(Expression anExpression){
	fExpression = anExpression;
}
	
public Object evaluate() throws Exception {
	
	return fExpression.evaluate();
	
}	

public Class getTypeClass() throws Exception {
	
	return fExpression.getTypeClass();
	
}

protected String getTypeClassName() {
	return fExpression.getTypeClassName();
}
public boolean isComplete(){
	return isComplete || fExpression.isComplete();
}
	
public Expression push(char[] token , char delimiter){

	// Even if our expression is complete and we receive anything other than a , then the expression must process it as this may be a field or message
	// that is being sent to the expression to make up a complex argument	
	Expression result = fExpression.push(token,delimiter);
	// If the result if a push then push the stack
	if ( result != fExpression ) {
		pushExpressionStack(result);
	} else if (result.isComplete()){
		// If our expression is complete, we are completed with a ) pop the stack so the previous expression can process us
		if (delimiter == DelimiterCloseParen ) {
			isComplete = true;
			popExpressionStack();
		} else if (delimiter == DelimiterComma || delimiter == DelimiterQuote ) {
			// If we receive a comma we pop the stack so the previous expression can process us but we don't flag us as complete
			popExpressionStack();
			// unless our expression is some array argument AND it is complete AND we have a close elipse }
		} else if ( delimiter == DelimiterCloseElipse ) {
			popExpressionStack();
			if ( fExpression instanceof ArrayArguments ) {
				isComplete = true;
			}
		}
	}
	return this;
}	
	
/* Pop the top expression off the stack
 */
protected void popExpressionStack(){

	if ( fExpressionStack.size() > 0 ) {
		Expression lastExpression = (Expression)fExpressionStack.get(fExpressionStack.size() - 1);
		if ( !lastExpression.isComplete() ) {
			fExpression = (Expression) fExpressionStack.remove(fExpressionStack.size() - 1);
		} else { 
			isComplete = true;
		} 
	} else {
		isComplete = true;
	}
}

/* Push the expression onto the stack
 */
protected void pushExpressionStack(Expression anExpression){

	// The current expression should go onto the stack
	fExpressionStack.add(fExpression);
	// The current expression is replaced with the argument
	fExpression = anExpression;
	
}

public boolean isPrimitive(){
	return false;
}
public String toString(){
	StringWriter writer = new StringWriter();
	writer.write("Arg("); //$NON-NLS-1$
	if ( fExpression != null ) {
		writer.write(fExpression.toString());
	}
	writer.write(")"); //$NON-NLS-1$
	return writer.toString();
}
public void contributeArgumentNumber(List argumentNumberList){

	if ( fExpression instanceof ArrayArguments ) {
		((ArrayArguments)fExpression).contributeArgumentNumber(argumentNumberList);
	}	
	
}
}