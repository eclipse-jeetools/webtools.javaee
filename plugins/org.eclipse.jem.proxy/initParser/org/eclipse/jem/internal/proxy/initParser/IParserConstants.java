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
 *  $RCSfile: IParserConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Constants for the parser.
 * Creation date: (11/05/00 9:37:45 PM)
 * @author: Administrator
 */
public interface IParserConstants {
	// Define the delimiters
	public static final char DelimiterSpace = ' ';
	public static final char DelimiterPeriod = '.';
	public static final char DelimiterOpenParen = '(';
	public static final char DelimiterCloseParen = ')';
	public static final char DelimiterComma = ',';
	public static final char DelimiterQuote = '"';
	public static final char DelimiterEscape = '\\';
	public static final char DelimiterSingleQuote = '\'';
	public static final char DelimiterOpenElipse = '{';
	public static final char DelimiterCloseElipse = '}';	
	public static final char DelimiterOpenSquareBrace = '[';
	public static final char DelimiterCloseSquareBrace = ']';
	// Define the math functions
	public static final char OperPlus = '+';
	public static final char OperMinus = '-';
	public static final char OperMultiply = '*';
	public static final char OperDivide = '/';
	// Define the primitive functions
	public static final char BitOR = '|';
	public static final char BitAND = '&';
	
	// Define constants
	public static final char[] ConstantNew = new char[] { 'n' , 'e' , 'w' };
	public static final char[] ConstantTrue = new char[] { 't' , 'r' , 'u' , 'e' };
	public static final char[] ConstantFalse = new char[] { 'f' , 'a' , 'l' , 's' , 'e' };
	public static final char[] ConstantNull = new char[] { 'n' , 'u' , 'l' , 'l' };
}
