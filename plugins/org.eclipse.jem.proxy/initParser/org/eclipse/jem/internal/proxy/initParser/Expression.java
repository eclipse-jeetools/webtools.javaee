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
 *  $RCSfile: Expression.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import java.util.*;
/**
 * Insert the type's description here.
 * Creation date: (11/01/00 8:46:44 PM)
 * @author: Administrator
 */
public abstract class Expression implements IParserConstants {
	protected Expression currentExpression;
	protected List fExpressionStack;
	protected ClassLoader fClassLoader;
	public int parenthesisLevel = 0;
/** Compare the two char arrays
 */
public static boolean compare(char[] from , char[] to){

	return Arrays.equals(from, to);
}
/**
 * Expression constructor comment.
 * Note: If any exceptions that are due to evaluation, and not due to some
 * invalid state, then the exception should be wrappered in an EvaluationException,
 * and the EvaluationException thrown instead. This way we can distinquish between
 * valid exceptions that result evaluations (such as null pointer exceptions) from
 * parser errors due to string is too complicated.
 */
public abstract Object evaluate() throws Exception;

public abstract boolean isComplete();
/**
 * Return a class type that the expression syntactically would produce.
 * For example, if a field is of type XYZ, return XYZ, even though
 * the value of the field may be SubXYZ where SubXYZ is a subclass of XYZ.
 */
public abstract Class getTypeClass() throws Exception;
/**
 * In evaluation phase, get the type class from the expression.
 * If typeclass not found, then a ClassCastException is thrown.
 * @param exp To getTypeClass from
 * @return type class.
 * @throws Exception ClassNotFoundException if type not found.
 */
public Class getEvaluationTypeClass(Expression exp) throws Exception {
	Class cls = exp.getTypeClass();
	if (cls == null)
		throw new EvaluationException(new ClassNotFoundException(exp.getTypeClassName()));
	return cls;
}

/**
 * @return Type name that should be returned from this expression.
 */
protected abstract String getTypeClassName();

public boolean isNumber(char[] token){

	if ( token.length == 0) return false;
	// See whether the token is a number
	int parsePosition = 0;
	// Take into account it may start with a - sign, e.g. -1
	if ( token[0] == '-' ) parsePosition = 1;
	for (int i = parsePosition; i < token.length ; i++){
		// If the next token is a digit return true
		// Other things like decimals or primitive types, e.g. 2.2 or 2.2f are 
		// handled by NumberLiteral
		return Character.isDigit(token[i]);
	}
	return true;
}
public boolean isPop(){
	return false;
}
protected void popExpressionStack(){

	if ( fExpressionStack != null && fExpressionStack.size() > 0 ) {
		currentExpression = (Expression) fExpressionStack.remove(fExpressionStack.size()-1);
	}	
}
protected void pushExpressionStack(Expression anExpression){

	if ( fExpressionStack == null ) fExpressionStack = new ArrayList(2);
	// The current expression should go onto the stack
	fExpressionStack.add(currentExpression);
	// The current expression is replaced with the argument
	currentExpression = anExpression;
	
}	

void setClassLoader(ClassLoader aClassLoader){
}

/**
 * Push an expression onto us
 * Return the new expression onto which subsequent token should be pushed
 */
public abstract Expression push(char[] token , char tokenDelimiter);

/**
 * Answer if this expression results in a primitive.
 */
public abstract boolean isPrimitive() throws Exception;

public String toString(int depth, boolean aBreak){
	StringBuffer buffer = new StringBuffer();
	if ( aBreak ) {
		buffer.append(System.getProperty("System.lineSeparator")); //$NON-NLS-1$
	}
	for(int i=0;i<depth;i++){
		buffer.append(" "); //$NON-NLS-1$
	}
	buffer.append(toString());
	return buffer.toString();
}
}
