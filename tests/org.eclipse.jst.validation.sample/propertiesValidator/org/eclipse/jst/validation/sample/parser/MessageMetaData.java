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

import java.util.*;


/**
 * This class holds the metadata for an IMessage that must be 
 * reported to the user (i.e., a validation error, warning,
 * or information message). 
 */
public class MessageMetaData {
	public static final int INT_UNSET = -1;
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	
	private String _bundleName = null;
	private int _severity = INT_UNSET;
	private String _messageId = null;
	private String[] _parms = null;
	private Object _targetObject = null;
	private int _lineNumber = INT_UNSET;
	private int _offset = INT_UNSET;
	private int _length = INT_UNSET;
	private String _text = null;
	
	/**
	 * This constructor should be used if the message text has already
	 * been translated.
	 */
	public MessageMetaData(String messageText) {
		_text = messageText;
	}
	
	/**
	 * This constructor should be used if the message text is not translated
	 * until just before the text should be shown to the user.
	 * 
	 * @param bundleName - The name of the resource bundle in which the message is contained
	 * @param severity - One of the com.ibm.etools.validation.core.SeverityEnum constants; this parameter represents the severity of the IMessage.
	 * @param messageId - The message id that uniquely identifies the message to be retrieved from the bundle.
	 * @param parms - The parameters to be substituted into the java.text.MessageText's patterns.
	 * @param targetObject - The target object of the IMessage (see com.ibm.etools.validation.core.IMessage).
	 * @param lineNumber - The line number where the problem can be found.
	 */
	public MessageMetaData(String bundleName, int severity, String messageId, String[] parms, Object targetObject, int lineNumber) {
		this(bundleName, severity, messageId, parms, targetObject, lineNumber, INT_UNSET, INT_UNSET);
	}
	
	/**
	 * This constructor should be used if the message text is not translated
	 * until just before the text should be shown to the user.
	 * 
	 * @param bundleName - The name of the resource bundle in which the message is contained
	 * @param severity - One of the com.ibm.etools.validation.core.SeverityEnum constants; this parameter represents the severity of the IMessage.
	 * @param messageId - The message id that uniquely identifies the message to be retrieved from the bundle.
	 * @param parms - The parameters to be substituted into the java.text.MessageText's patterns.
	 * @param targetObject - The target object of the IMessage (see com.ibm.etools.validation.core.IMessage).
	 * @param lineNumber - The line number where the problem can be found.
	 * @param offset - In number of characters, how many characters from the start of the .properties file to the location of the problem.
	 * @param length - In number of characters, how many characters from the offset to the end of the problem.
	 */
	public MessageMetaData(String bundleName, int severity, String messageId, String[] parms, Object targetObject, int lineNumber, int offset, int length) {
		_bundleName = bundleName;
		_severity = severity;
		_messageId = messageId;
		_parms = parms;
		_targetObject = targetObject;
		_lineNumber = lineNumber;
		_offset = offset;
		_length = length;
	}
	
	public String getBundleName() {
		return _bundleName;
	}
	
	public int getSeverity() {
		return _severity;
	}
	
	public String getId() {
		return _messageId;
	}
	
	public String[] getParams() {
		return _parms;
	}
	
	public Object getTargetObject() {
		return _targetObject;
	}
	
	public int getLineNumber() {
		return _lineNumber;
	}
	
	public int getOffset() {
		return _offset;
	}

	public int getLength() {
		return _length;
	}
	
	/**
	 * Use the given ClassLoader to load this message's text
	 * from the resource bundle.
	 */
	public String getText(ClassLoader classLoader) {
		if(_text == null) {
			return getText(Locale.getDefault(), classLoader);
		}
		return _text;
	}

	/**
	 * Use the given ClassLoader to load this message's text,
	 * for the Locale, from the resource bundle.
	 */	
	public String getText(Locale locale, ClassLoader classLoader) {
		if(_text == null) {
			return getText(getBundleName(), locale, classLoader, getId(), getParams());
		}
		
		return _text;
	}
	
	/**
	 * Return the translated message.
	 * 
	 * @param bundleName The name of the resource bundle to search for the message.
	 * @param locale The Locale for which the message should be loaded.
	 * @param classLoader The ClassLoader which can locate the resource bundle.
	 * @param messageId The unique id, in the resource bundle, that identifies the message.
	 * @param parms The parameters to substitute into the pattern of the java.text.MessageFormat message.
	 */
	public static java.lang.String getText(String bundleName, Locale locale, ClassLoader classLoader, String messageId, String[] parms) {
		String message = ""; //$NON-NLS-1$

		if (locale == null) {
			return message;
		}

		ResourceBundle bundle = getBundle(bundleName, locale, classLoader);
		if (bundle == null) {
			return message;
		}

		try {
			message = bundle.getString(messageId);

			if (parms != null) {
				message = java.text.MessageFormat.format(message, parms);
			}
		}
		catch (MissingResourceException exc) {
			System.err.println(exc.getMessage());
			System.err.println(messageId);
		}
		catch (NullPointerException exc) {
			System.err.println(exc.getMessage());
			System.err.println(messageId);
		}

		return message;
	}

	/**
	 * Return the resource bundle which contains the messages, as identified by
	 * @link #getBundleName()
	 */
	public static ResourceBundle getBundle(String bundleName, Locale locale, ClassLoader classLoader) {
		ResourceBundle bundle = null;
		try {
			if (classLoader == null) {
				bundle = ResourceBundle.getBundle(bundleName, locale);
			}
			else {
				bundle = ResourceBundle.getBundle(bundleName, locale, classLoader);
			}
		}
		catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return bundle;
	}
	
}

