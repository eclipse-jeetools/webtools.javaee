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
 *  $RCSfile: InitializationStringParser.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */


import java.util.ArrayList;
/**
 * Insert the type's description here.
 * Creation date: (11/01/00 8:53:45 PM)
 * @author: Administrator
 */
public class InitializationStringParser implements IParserConstants {
	protected ClassLoader fClassLoader;
	protected boolean isComplete = false;
	protected String fInitString;
	protected int fSourceLength;
	protected char[] fSource;
	protected int fSourcePosition = -1;
	private char[] fCurrentToken;
	private char fCurrentTokenDelimiter = 0;
	protected ArrayList fExpressionStack = new ArrayList(4);
	protected Expression fExpression;
/**
 * InitializationStringParser constructor comment.
 */
public InitializationStringParser(String initString){

	fInitString = initString;
	fSource = initString.toCharArray();
	fSourceLength = fSource.length;

}
/** Look for a known token
 */
protected void createNextToken(){

	fCurrentTokenDelimiter = 0;
	if ( fSourcePosition >= fSourceLength - 1 ) {
		isComplete = true;
		return;
	}

	fSourcePosition++;
	// If we are not in a token then look for " " or else for a period
	char[] fCurrentTokenSource = new char[fSourceLength];
	int i = 0;
	scanner : for ( ; fSourcePosition < fSourceLength ; fSourcePosition++ , i++ ){
		// Advance the source position so when finding the next token we skip over the '.' or ' '
		// Record the delimiter that was found
		switch ( fSource[fSourcePosition] ) {
			case DelimiterPeriod: {
				fCurrentTokenDelimiter = DelimiterPeriod;
				break scanner;
			}
			case DelimiterOpenParen: {
				fCurrentTokenDelimiter = DelimiterOpenParen;
				break scanner;
			}
			case DelimiterCloseParen: {
				fCurrentTokenDelimiter = DelimiterCloseParen;
				break scanner;
			}
			case DelimiterComma: {
				fCurrentTokenDelimiter = DelimiterComma;
				break scanner;
			}
			case DelimiterSpace: {
				fCurrentTokenDelimiter = DelimiterSpace;
				break scanner;
			}
			case DelimiterQuote: {
				fCurrentTokenDelimiter = DelimiterQuote;
				break scanner;
			}
			case DelimiterSingleQuote: {
				fCurrentTokenDelimiter = DelimiterSingleQuote;
				break scanner;
			}			
			case DelimiterEscape: {
				fCurrentTokenDelimiter = DelimiterEscape;
				break scanner;
			}
			case DelimiterOpenElipse:{
				fCurrentTokenDelimiter = DelimiterOpenElipse;
				break scanner;
			}
			case DelimiterOpenSquareBrace:{
				fCurrentTokenDelimiter = DelimiterOpenSquareBrace;
				break scanner;
			}
			case DelimiterCloseSquareBrace:{
				fCurrentTokenDelimiter = DelimiterCloseSquareBrace;
				break scanner;
			}			
			case DelimiterCloseElipse:{
				fCurrentTokenDelimiter = DelimiterCloseElipse;
				break scanner;
			}				
			// Do the math functions
			case OperPlus: {
				fCurrentTokenDelimiter = OperPlus;
				break scanner;
			}
			case OperMinus: {
				fCurrentTokenDelimiter = OperMinus;
				break scanner;
			}
			case OperMultiply: {
				fCurrentTokenDelimiter = OperMultiply;
				break scanner;
			}
			case OperDivide: {
				fCurrentTokenDelimiter = OperDivide;
				break scanner;
			}
			// Do the primitive BitWise operations
			case BitOR: {
				fCurrentTokenDelimiter = BitOR;
				break scanner;
			}
			case BitAND: {
				fCurrentTokenDelimiter = BitAND;
				break scanner;
			}			
			default: {
				fCurrentTokenSource[i] = fSource[fSourcePosition];
			}
		}
	}

	// Push the token onto the stack for later decoding
	if ( i >= 1 ) {
		fCurrentToken = new char[i];
		System.arraycopy( fCurrentTokenSource , 0 , fCurrentToken , 0 , i );
	} else {
		fCurrentToken = new char[0];
	}

}
/** Answer the result of evaluating the expression
 */
public Object evaluate() throws InitializationStringEvaluationException {

	try {
		return fExpression.evaluate();
	} catch (java.lang.reflect.InvocationTargetException e) {
		throw new EvaluationException(e);
	} catch (EvaluationException e) {
		throw e;	// Don't loose these
	} catch (Throwable e) {
		throw new InitializationStringEvaluationException(e);
	}

}
public static Object evaluate(String initString) throws InitializationStringEvaluationException {

	return evaluate(initString,null);

}
public static Object evaluate(String initString,ClassLoader aClassLoader) throws InitializationStringEvaluationException {

	InitializationStringParser parser = null;;
	try {
		parser = new InitializationStringParser(initString);
		parser.setClassLoader(aClassLoader);
		parser.parse();
	} catch ( Throwable exc ) {
		throw new InitializationStringEvaluationException(exc);
	}		
	return parser.evaluate();
}

/**
 * Return a parser already parsed. Use the default classloader (i.e. classloader that loaded InitializationStringParser).
 * 
 * @param initString
 * @return The parser, already parsed.
 * @throws InitializationStringEvaluationException
 */

public static InitializationStringParser createParser(String initString) throws InitializationStringEvaluationException {
	return createParser(initString, null);
}
 
/**
 * Return a parser already parsed. Use the given classloader.
 * 
 * @param initString
 * @param classLoader
 * @return The parser, already parsed.
 * @throws InitializationStringEvaluationException
 */
public static InitializationStringParser createParser(String initString, ClassLoader classLoader) throws InitializationStringEvaluationException {

	try {
		InitializationStringParser parser = new InitializationStringParser(initString);
		parser.setClassLoader(classLoader);
		parser.parse();
		return parser;
	} catch ( Throwable exc ) {
		throw new InitializationStringEvaluationException(exc);
	}

}

/** Analyze the string
 */
public void parse(){
	
	createFirstExpression();
	int i =1;
	while ( true ){
		createNextToken();
		if ( isComplete ) {
			// If the current expression is incomplete it must be completed 
			// by the end, which is basically the equiavlent of a;
			return;
		} else  {
			i++;
			Expression result = fExpression.push( fCurrentToken , fCurrentTokenDelimiter );
			// If the result if a push then push the stack
			if ( result.isComplete() ) {
				popExpressionStack();
			} else if ( result != fExpression ) {
				pushExpressionStack(result);
			}
		}
	}
}

/**
 * Answer if the string results in a primitive.
 */
public boolean isPrimitive() throws InitializationStringEvaluationException {
	try {
		return fExpression.isPrimitive();
	} catch (Exception e) {
		throw new InitializationStringEvaluationException(e);
	}
}

public Class getExpectedType() throws InitializationStringEvaluationException {
	try {
		return fExpression.getTypeClass();
	} catch (Exception e) {
		throw new InitializationStringEvaluationException(e);
	}
}

/* Pop the top expression off the stack
 */
protected void popExpressionStack(){

	if ( fExpressionStack.size() > 0 ) {
		fExpression = (Expression) fExpressionStack.remove(fExpressionStack.size()-1);
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
protected void createFirstExpression(){
	if ( fExpression == null ) {
		fExpression = new Statement(fClassLoader);
	};
}
public void setClassLoader(ClassLoader aClassLoader){
	fClassLoader = aClassLoader;	
}
public String toString(){
	StringBuffer writer = new StringBuffer();
	writer.append("Parser. token=\""); //$NON-NLS-1$
	if ( fCurrentToken != null ) {
		writer.append(fCurrentToken);
	} 
	writer.append("\", delim=\""); //$NON-NLS-1$
	writer.append(fCurrentTokenDelimiter);
	writer.append("\", initString=\""); //$NON-NLS-1$
	writer.append(fInitString);
	return writer.toString();	
}
}
