package org.eclipse.jst.validation.sample.filesystem;
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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * This class is the file system specific implementation of an incremental IReporter.
 * It caches validation messages, so that messages can both be added and deleted.
 */
public class IncrementalReporter implements IReporter {
	
	public IncrementalReporter() {
		super();
	}
	
	/**
	 * @see IReporter#addMessage(IValidator, IMessage)
	 */
	public void addMessage(IValidator validator, IMessage message) {
		if (validator == null) {
			return;
		}

		if (message == null) {
			return;
		}

		ValidatorMessages vm = MessageManager.getManager().getMessages(validator);
		if (vm != null) {
			vm.addValidationMessage(message);
		}
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
		return null;
	}

	/**
	 * Return a Map of String fully-qualified validator class names,
	 * with a value of Collection, and the Collection contains the
	 * MessageMetaData reported by the validator, of the given severity.
	 */
	public Map getMessages(int messageType) {
		return MessageManager.getManager().getMessages(messageType);
	}

	/**
	 * @see IReporter#isCancelled()
	 */
	public boolean isCancelled() {
		// For simplicity, never allow cancellation.
		// In a production system, this method might query another object to find out its cancellation status.
		return false;
	}

	/**
	 * @see IReporter#removeAllMessages(IValidator)
	 */
	public void removeAllMessages(IValidator validator) {
		if (validator == null) {
			return;
		}
		MessageManager.getManager().removeAllMessages(validator);
	}

	/**
	 * @see IReporter#removeAllMessages(IValidator, Object)
	 */
	public void removeAllMessages(IValidator validator, Object object) {
		if (validator == null) {
			return;
		}
		

		MessageManager.getManager().removeAllMessages(validator, object);
	}

	/**
	 * @see IReporter#removeMessageSubset(IValidator, Object, String)
	 */
	public void removeMessageSubset(IValidator validator, Object obj, String groupName) {
		// implement later
	}

	/**
	 * Display a formatted list of all of the problems found while
	 * validating the file.
	 */
	public void report() {
		System.out.println();
		System.out.println("Error messages"); //$NON-NLS-1$
		Map messages = getMessages(IMessage.HIGH_SEVERITY);
		report(messages);

		System.out.println();
		System.out.println("Warning messages"); //$NON-NLS-1$
		messages = getMessages(IMessage.NORMAL_SEVERITY);
		report(messages);

		System.out.println();
		System.out.println("Information messages"); //$NON-NLS-1$
		messages = getMessages(IMessage.LOW_SEVERITY);
		report(messages);
	}
	
	/**
	 * @see IReporter#report(Map)
	 */
	protected static void report(Map messages) {
		if(messages.size() == 0) {
			return;
		}
		
		Iterator iterator = messages.keySet().iterator();
		while(iterator.hasNext()) {
			String validatorClassName = (String)iterator.next();
			IValidator validator = FilesystemManager.getManager().getValidator(validatorClassName);
			IFilesystemHelper helper = FilesystemManager.getManager().getHelper(validator);
			List mssgList = (List)messages.get(validator);
			if(mssgList == null) {
				continue;
			}
			
			Collections.sort(mssgList, MessageManager.getManager().getMessageComparator(helper));
			
			for(int i=0; i<mssgList.size(); i++) {
				IMessage mssg = (IMessage)mssgList.get(i);
				System.out.println(MessageManager.formatMessage(validator, mssg));
			}
		}
	}
}
