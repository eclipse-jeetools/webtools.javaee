package org.eclipse.wst.validation.sample.filesystem;
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

import java.util.List;

import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.IReporter;
import org.eclipse.wst.validation.core.IValidator;

/**
 * This reporter is used when full validation is needed. It doesn't cache
 * anything, so it's faster than the IncrementalReporter.
 */
public class FullReporter implements IReporter {
	public FullReporter() {
		super();
	}

	/**
	 * @see IReporter#addMessage(IValidator, IMessage)
	 */
	public void addMessage(IValidator validator, IMessage message) {
		if (message == null) {
			return;
		}

		System.out.println(MessageManager.formatMessage(validator, message));
	}
	
	/**
	 * @see IReporter#displaySubtask(IValidator, IMessage)
	 */
	public void displaySubtask(IValidator validator, IMessage message) {
		// Flush the message to the user immediately.
		System.err.println(message.getText());
	}
	
	/**
	 * @see IReporter#getMessages()
	 */
	public List getMessages() {
		// this reporter does not support message access 
		return null;
	}
	
	/**
	 * @see IReporter#isCancelled()
	 */
	public boolean isCancelled() {
		// to make things easy, do not allow the user to cancel validation.
		return false;
	}
	
	/**
	 * @see IReporter#removeAllMessages(IValidator)
	 */
	public void removeAllMessages(IValidator validator) {
		// since no messages are stored, there's nothing to remove
	}
	
	/**
	 * @see IReporter#removeAllMessages(IValidator, Object)
	 */
	public void removeAllMessages(IValidator validator, Object object) {
		// since no messages are stored, there's nothing to remove
	}
	
	/**
	 * @see IReporter#removeMessageSubset(IValidator, Object, String)
	 */
	public void removeMessageSubset(IValidator validator, Object obj, String groupName) {
		// Since this reporter doesn't store messages, there is no removal of any messages
	}
}
