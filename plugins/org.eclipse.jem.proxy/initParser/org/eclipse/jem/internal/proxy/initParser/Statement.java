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
 *  $RCSfile: Statement.java,v $
 *  $Revision: 1.3 $  $Date: 2005/06/21 19:21:07 $ 
 */



public class Statement extends Expression {
	public boolean isStringObject = false;
	public boolean isCast = false;
	protected boolean messageOrFieldStarted;
	
/**
 * Statement constructor comment.
 */
public Statement(ClassLoader aClassLoader) {
	super();
	fClassLoader = aClassLoader;
}

public boolean isComplete(){
	return currentExpression != null && currentExpression.isComplete();
}

/** Defer this to the expression
 */
public Object evaluate() throws Exception {
	if ( currentExpression != null ) {
		return currentExpression.evaluate();
	} else {
		throw new RuntimeException(ProxyInitParserMessages.Statement_UnexpectedExecution_EXC_);	// This is not an evaluation error, it shouldn't of occured, so it is a parser error. //$NON-NLS-1$
	}
}
/**
 */
public Class getTypeClass() throws Exception {
	return currentExpression.getTypeClass();
	//throw new RuntimeException(ProxyBaseVMNLS.RESBUNDLE.getString(ProxyBaseVMNLS.STATEMENT_UNEXPECTED_EVALUATION));
}

protected String getTypeClassName() {
	return currentExpression.getTypeClassName();
}
/** There are several types of token that can begin a statement.
 *  new , ' '						-	Create a constructor
 *	1-10, true, false, etc...		-	Create a literal
 *  Foo,  '.'						-	Create a Type
 */
public Expression push(char[] token , char delimiter){
	
	// If messageOrFieldStart is true then our expression is complete and either a message or field is being created
	// See which it is and create the correct expression
	if (messageOrFieldStarted){
		messageOrFieldStarted = false;
		if ( delimiter == DelimiterOpenParen){
			Message message = new Message(currentExpression,token,fClassLoader);
			currentExpression = message;
			return message;
		} else { 
			Field field = new Field(currentExpression,token,fClassLoader);
			currentExpression = field;
			return field;
		}
	}	
	
	// Look for "new " for a constructor"
	if ( delimiter == DelimiterSpace && compare( token , ConstantNew ) ) {
		// If we thought we were a possible cast and we now aren't then we must be a block expression
		if ( isCast ) {
			currentExpression = new Block(new Constructor(fClassLoader));
		} else {
			currentExpression = new Constructor(fClassLoader);
		}
		isCast = false;	// Can't be a cast now		
		return currentExpression;
	}
	
	// Look for "null" 
	if ( compare( token , ConstantNull ) ) {
		currentExpression = new NullLiteral();
		isCast = false;	// Can't be a cast now		
		// Null cannot process anything so we return the statement
		return this;
	}

	// Look for a number
	if ( isNumber( token ) || delimiter == OperMinus ) {
		currentExpression = new NumberLiteral();
		currentExpression = currentExpression.push(token,delimiter);
		isCast = false;	// Can't be a cast now		
		return currentExpression;
	}

	if ( compare( token , ConstantTrue ) ) {
		currentExpression  = new BooleanLiteral(true);
		// true cannot process anything so we return the statement
		isCast = false;	// Can't be a cast now		
		return currentExpression;
//		return this;
	}

	if ( compare( token , ConstantFalse ) ) {
		// false cannot process anything so we return the statement
		currentExpression = new BooleanLiteral(false);
		isCast = false;	// Can't be a cast now		
		return this;
	}
	
	if ( delimiter == DelimiterQuote ) {
		currentExpression = new StringLiteral();
		isCast = false;	// Can't be a cast now		
		return currentExpression;		
	}
	
	if ( delimiter == DelimiterSingleQuote ) {
		currentExpression = new CharLiteral();
		isCast = false;	// Can't be a cast now		
		return currentExpression;		
	}	
	
	if (delimiter == DelimiterPeriod && token.length == 0 && currentExpression != null){
		if (currentExpression instanceof StringLiteral){
			//String s = "\"" + (String)(currentExpression.evaluate()) + "\"" ;
			//return new Constructor(s);
			isStringObject = true;
			return this;
		}
	}
	
	if (isStringObject && (token.length > 0) && (delimiter == DelimiterOpenParen)){
		return new Message(currentExpression, token,fClassLoader);
	}
	
	//handle type casting
	if ((token.length == 0) && (delimiter == DelimiterOpenParen)){
		// If we are a already a cast then we must be a block.
		if ( isCast ) {
			currentExpression = new Block(fClassLoader);
		} else {
			isCast = true;	// Possibly a cast
		}
		return this;
	}	
	if (isCast && (delimiter == DelimiterCloseParen || token.length != 0)){
		Static aStatic = new Static(fClassLoader);
		Cast aCast = new Cast(aStatic,fClassLoader);
		currentExpression = aCast.push(token,delimiter);
		return currentExpression;
	}
	
	if ( token.length != 0 ) {
		currentExpression = new Static(fClassLoader).push(token,delimiter);
		return currentExpression;
	}
	
	// If we get a { then we are part of an array argument
	if (delimiter == DelimiterOpenElipse){
		currentExpression = new ArrayArguments();
		return currentExpression;
	}
	
	// If our expression is closed and we receive a . we are either a message or a field.  We won't know this until
	// the next token we receive, which will be either be delimited by a ( in which case it is a message, else it is a field
	if (currentExpression != null && currentExpression.isComplete() && delimiter == DelimiterPeriod){
		messageOrFieldStarted = true;
	}
		
	return this;
}

public String toString(){
	StringBuffer buffer = new StringBuffer();
	buffer.append("Statement{"); //$NON-NLS-1$
	if ( currentExpression != null ) {
		buffer.append(currentExpression.toString());
	}
	buffer.append('}');
	return buffer.toString();
}
/**
 * Is this statement a primitive.
 */
public boolean isPrimitive() throws Exception {
	if ( currentExpression != null ) {
		return currentExpression.isPrimitive();
	} else {
		throw new RuntimeException(ProxyInitParserMessages.Statement_UnexpectedExecution_EXC_); 
	}
}
}
