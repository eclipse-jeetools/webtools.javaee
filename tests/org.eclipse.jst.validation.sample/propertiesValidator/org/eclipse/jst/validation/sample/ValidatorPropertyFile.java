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

import java.io.LineNumberReader;

import org.eclipse.jst.validation.sample.parser.APropertyFile;
import org.eclipse.jst.validation.sample.parser.MessageMetaData;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.IReporter;
import org.eclipse.wst.validation.core.IValidator;
import org.eclispe.wst.validation.internal.core.Message;

/**
 * This class represents an APropertyFile that is instantiated by a validator.
 */
public class ValidatorPropertyFile extends APropertyFile {
	private IReporter _reporter = null;
	private IValidator _validator = null;
	private String _fileName = null; // if this is created from a reader instead of a file name, we only have the bundle name
	
	public ValidatorPropertyFile(LineNumberReader reader, String fileName, IReporter reporter, IValidator validator) {
		super();
		setFileName(fileName);
		_reporter = reporter;
		_validator = validator;
		parseFile(reader);
	}
	
	public void setFileName(String bundleName) {
		_fileName = bundleName;
	}
	
	/**
	 * Return the name of the .properties file (or bundle).
	 */
	public String getFileName() {
		return _fileName;
	}
	
	/*
	 * @see APropertyFile#report(String)
	 */
	public void report(String str) {
		// Never print titles like "DUPLICATE MESSAGE IDS". Print only the IMessage.
	}
	
	/*
	 * @see APropertyFile#report(MessageMetaData)
	 */
	public void report(MessageMetaData mmd) {
		IMessage message = new Message();
		message.setBundleName(mmd.getBundleName());
		message.setSeverity(getSeverity(mmd.getSeverity()));
		message.setId(mmd.getId());
		message.setParams(mmd.getParams());
		message.setTargetObject(mmd.getTargetObject());
		message.setLineNo(mmd.getLineNumber());
		message.setLength(mmd.getLength());
		message.setOffset(mmd.getOffset());
		message.setLength(mmd.getLength());
		_reporter.addMessage(getValidator(), message);
	}
	
	/**
	 * Given the severity of the MessageMetaData, return the corresponding
	 * severity of the validation framework.
	 */
	private static int getSeverity(int mmdSeverity) {
		switch(mmdSeverity) {
			case(MessageMetaData.ERROR): {
				return IMessage.HIGH_SEVERITY;
			}
			
			case(MessageMetaData.INFO): {
				return IMessage.LOW_SEVERITY;
			}
			
			default: {
				return IMessage.NORMAL_SEVERITY;
			}
		}
	}
	
	/**
	 * Return the IValidator instance that is checking this file.
	 */
	public IValidator getValidator() {
		return _validator;
	}

	/**
	 * Return the name of this file that identifies this file uniquely.
	 */
	public String getQualifiedFileName() {
		return getFileName();
	}
}