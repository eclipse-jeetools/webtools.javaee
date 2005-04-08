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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * This class manages messages for one validator. Each validator 
 * can store three types of messages: error (SeverityEnum.HIGH_SEVERITY), 
 * warning (SeverityEnum.NORMAL_SEVERITY), and info (SeverityEnum.LOW_SEVERITY).
 * Each message is associated with an Object (for incremental validation).
 * If a message does not apply to a particular Object, the default Object
 * is used.
 *
 * Each Object is associated with either 0, 1, or >1 messages. 
 */
public class ValidatorMessages {
	private Map _messages = null;
	private IValidator _validator = null;
	protected static final Object DEFAULT_OBJECT = new Object();

	public ValidatorMessages(IValidator validator) {
		_messages = new HashMap();
		_validator = validator;
	}
	
	/**
	 * Return the validator that owns these messages.
	 */
	public IValidator getValidator() {
		return _validator;
	}
	
	/**
	 * Add an IMessage to the default group.
	 */
	public void addValidationMessage(IMessage message) {
		addValidationMessage(message, null);
	}
	
	/**
	 * Add an IMessage to the named group.
	 */
	public void addValidationMessage(IMessage message, String groupName) {
		if (message == null) {
			return;
		}

		if (message.getTargetObject() == null) {
			// If object is null, the error message does not apply to a particular object.
			//
			// This value might be used if, for example, the validator experiences an internal, unrecoverable error.
			// You need to let the user know that validation terminated abnormally, but a list
			// of the validation which proceeded normally can help to narrow down what part of the
			// code caused a problem.
			//
			// Or it could be something generic, e.g. it applies to a .jar file, instead of a file in the .jar.
			message.setTargetObject(getDefaultObject());
		}
		
		if(groupName != null) {
			message.setGroupName(groupName);
		}

		List list = (List) _messages.get(message.getTargetObject());
		if (list == null) {
			list = new ArrayList();
		}
		list.add(message);
		_messages.put(message.getTargetObject(), list);
	}
	
	/**
	 * If the IMessage does not have a target object, the object
	 * returned by this method is used as the key in the Map.
	 */
	public static Object getDefaultObject() {
		return DEFAULT_OBJECT;
	}
	
	/**
	 * Return all messages whose severity matches the messageTypes severity.
	 */
	public List getMessages(int messageTypes) {
		List messages = new ArrayList();

		Iterator iterator = _messages.values().iterator();
		while (iterator.hasNext()) {
			List list = (List) iterator.next();

			Object[] listContents = list.toArray();
			for (int i = 0; i < listContents.length; i++) {
				IMessage message = (IMessage) listContents[i];

				if ((messageTypes & message.getSeverity()) != 0) {
					messages.add(message);
				}
			}
		}
		return messages;
	}
	
	/**
	 * Remove all messages that apply to the given object. If object is
	 * null, the messages owned by the default object are removed.
	 */
	public void removeAllMessages(Object object) {
		if (object == null) {
			object = getDefaultObject();
		}

		_messages.remove(object);
	}
	
	/**
	 * Remove all messages that apply to the given object which are in
	 * the named group. If Object is null, the default object is used.
	 */
	public void removeMessageSubset(Object object, String groupName) {
		if (groupName == null) {
			removeAllMessages(object);
			return;
		}

		if (object == null) {
			object = getDefaultObject();
		}

		List list = (List) _messages.get(object);
		if (list == null) {
			return;
		}

		if (list.size() == 0) {
			return;
		}

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			IMessage msg = (IMessage) iterator.next();
			String msgGroupName = msg.getGroupName();
			if (groupName.equals(msgGroupName)) {
				list.remove(msg);
			}
		}
	}
}
