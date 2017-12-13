package org.eclipse.jst.validation.sample.parser;
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

import java.io.File;

/**
 * This PropertyFile is instantiated by code that isn't an IValidator.
 */
public class PropertyFile extends APropertyFile {
	private String _enclosingZipFileName = null;
	private String _propertyFileName = null;
	private String _fileName = null;
	
	public PropertyFile(String fileName) throws java.io.IOException {
		this(fileName, false);
	}

	public PropertyFile(String fileName, boolean trace) throws java.io.IOException {
		this(new File(fileName), trace);
	}
	
	public PropertyFile(File propertyFile) throws java.io.IOException {
		this(propertyFile, false);
	}
	
	public PropertyFile(File propertyFile, boolean trace) throws java.io.IOException {
		this(null, propertyFile, trace);
	}
	
	public PropertyFile(String enclosingZipFileName, File propertyFile) throws java.io.IOException {
		this(enclosingZipFileName, propertyFile, false);
	}

	public PropertyFile(String enclosingZipFileName, File propertyFile, boolean debug) throws java.io.IOException {
		if (!propertyFile.exists())
			throw new java.io.IOException("Cannot find file " + propertyFile.getName()); //$NON-NLS-1$
		if (!propertyFile.isFile())
			throw new java.io.IOException(propertyFile.getName() + " is not a file"); //$NON-NLS-1$
		if (!propertyFile.canRead())
			throw new java.io.IOException("Cannot read " + propertyFile.getName()); //$NON-NLS-1$
		if (!propertyFile.getName().endsWith("properties")) //$NON-NLS-1$
			throw new java.io.IOException("Can parse only .properties files; ignoring " + propertyFile.getName()); //$NON-NLS-1$

		setDebug(debug);
			
		_enclosingZipFileName = enclosingZipFileName;
		
		_fileName = propertyFile.getName();
		
		report("File name: " + _fileName); //$NON-NLS-1$
		parseFile(propertyFile);
		report(""); //$NON-NLS-1$

		propertyFile = null;
	}
	
	/*
	 * @see APropertyFile#report(String)
	 */
	public void report(String str) {
		System.out.println(str);
	}
	
	/*
	 * @see APropertyFile#report(MessageMetaData)
	 */
	public void report(MessageMetaData message) {
		StringBuffer buffer = new StringBuffer(message.getText(getClass().getClassLoader()));
		buffer.append(ResourceHandler.getExternalizedMessage(IValidationConstants.BUNDLENAME, IValidationConstants.LINE_LOC, new String[]{String.valueOf(message.getLineNumber()), getQualifiedFileName()}));
		report(buffer.toString());
	}
	
	public String getQualifiedFileName() {
		if (_propertyFileName == null) {
			if (_enclosingZipFileName == null) {
				_propertyFileName = _fileName;
			}
			else {
				_propertyFileName = _enclosingZipFileName + "::" + _fileName; //$NON-NLS-1$
			}
		}
		return _propertyFileName;
	}

	public String getFileName() {
		return _fileName;
	}
}