package org.eclipse.jst.validation.sample;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * DISCLAIMER OF WARRANTIES.
 * The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard or IBM
 * product and is provided to you solely for the purpose of assisting
 * you in the development of your applications.  The code is provided
 * "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE FUNCTION OR PERFORMANCE OF
 * THIS CODE.  THIS CODE MAY CONTAIN ERRORS.  IBM shall not be liable
 * for any damages arising out of your use of the sample code, even
 * if it has been advised of the possibility of such damages.
 * 
 */

/**
 * This class contains the constants that uniquely identify each
 * model that the Properties Validator's helper must load. All of
 * these models must be supported by the IHelper implementation
 * or the helper cannot be used to support the Properties Validator.
 */
public interface PModelEnum {
	public static final String LINEINPUTREADER = "get a LineInputReader for a .properties file"; //$NON-NLS-1$
	public static final String RELEASE_LINEINPUTREADER = "relase (close) the LineInputReader"; //$NON-NLS-1$
	public static final String ALL_PROPERTIES_FILES = "return an IFileDelta[] of all of the .properties files in the project"; //$NON-NLS-1$
	public static final String MSGLOGGER = "get the MsgLogger needed to log messages. This MsgLogger must never be null."; //$NON-NLS-1$
	public static final String FILTER = "get only the .properties files that are in src dir; filter out the .properties files that are in bin dir (i.e., don't validate the same file twice"; //$NON-NLS-1$

}
