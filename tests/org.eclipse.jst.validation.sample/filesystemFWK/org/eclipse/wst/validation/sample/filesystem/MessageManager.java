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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.IValidator;

/**
 * This class manages the validation messages for each validator. Each validator's
 * messages can be retrieved by using the validator's unique name as a key into the
 * table. (i.e., call getMessages with the validator's unique name as the parameter.)
 */
public class MessageManager {
	private static MessageManager _singleton = null;
	private Comparator _comparator = null;

	private Map _validationMessages = null;

	private MessageManager() {
		super();

		_validationMessages = new HashMap(10);
	}

	/**
	 * Return the MessageManager singleton.
	 */
	public static MessageManager getManager() {
		if (_singleton == null) {
			_singleton = new MessageManager();
		}
		return _singleton;
	}
	
	/**
	 * Return a map of all messages of the given severity. The key of the map
	 * is the IValidator, the value of the IValidator is the List of IMessage
	 * instances of the given severity.
	 */
	public Map getMessages(int messageTypes) {
		Map messages = new HashMap();

		Iterator iterator = _validationMessages.values().iterator();
		while(iterator.hasNext()) {
			ValidatorMessages mssg = (ValidatorMessages) iterator.next();

			messages.put(mssg.getValidator().getClass().getName(), mssg.getMessages(messageTypes));
		}
		return messages;
	}
	
	/**
	 * This method returns the ValidationMessage owned by the identified validator,
	 * if one exists. If one doesn't exist, it is created & registered, and then 
	 * returned.
	 */
	public ValidatorMessages getMessages(IValidator validator) {
		if (validator == null) {
			return null;
		}

		ValidatorMessages message = (ValidatorMessages) _validationMessages.get(validator.getClass().getName());
		if (message == null) {
			// if it doesn't exist, create it.
			message = new ValidatorMessages(validator);
			_validationMessages.put(validator.getClass().getName(), message);
		}
		return message;
	}
	
	/**
	 * Remove all messages owned by the identified validator.
	 */
	public void removeAllMessages(IValidator validator) {
		if (validator == null) {
			return;
		}

		_validationMessages.remove(validator.getClass().getName());
	}
	
	/**
	 * Remove all messages, owned by the identified validator, reported
	 * against the given target object.
	 */
	public void removeAllMessages(IValidator validator, Object object) {
		if (validator == null) {
			return;
		}

		ValidatorMessages message = getMessages(validator);
		if (message != null) {
			message.removeAllMessages(object);
		}
	}
	
	/**
	 * To support removal of a subset of validation messages, an IValidator
	 * may assign group names to IMessages. An IMessage subset will be identified
	 * by the name of its group. Default (null) means no group. This method will
	 * remove only the IMessage's that are in the group identified by groupName.
	 */
	public void removeMessageSubset(IValidator validator, Object object, String groupName) {
		if (validator == null) {
			return;
		}

		ValidatorMessages message = getMessages(validator);
		if (message != null) {
			message.removeAllMessages(object);
		}
	}

	/**
	 * Return the IMessage as a String suitable for displaying to the user.
	 */
	public static String formatMessage(IValidator validator, IMessage message) {
		if (message == null) {
			return ""; //$NON-NLS-1$
		}

		int severity = message.getSeverity();
		Object object = message.getTargetObject();
		StringBuffer formattedMessage = new StringBuffer();
		switch (severity) {
			case (IMessage.HIGH_SEVERITY) :
				{
					formattedMessage.append("Error: "); //$NON-NLS-1$
					break;
				}

			case (IMessage.LOW_SEVERITY) :
				{
					formattedMessage.append("Information: "); //$NON-NLS-1$
					break;
				}

			case (IMessage.NORMAL_SEVERITY) :
			default :
				{
					formattedMessage.append("Warning: "); //$NON-NLS-1$
					break;
				}
		}

		formattedMessage.append(message.getText());

		if (object != null) {
			String fileName = FilesystemManager.getManager().getHelper(validator).getFileName(message);
			int lineNumber = message.getLineNumber();

			if((fileName != null) || (lineNumber != IMessage.LINENO_UNSET)) {
				formattedMessage.append("["); //$NON-NLS-1$
				if(fileName != null) {
					formattedMessage.append(fileName);
				}
				if(lineNumber != IMessage.LINENO_UNSET) {
					formattedMessage.append(" line number: "); //$NON-NLS-1$
					formattedMessage.append(String.valueOf(lineNumber));
				}
				formattedMessage.append("] "); //$NON-NLS-1$
			}
		}

		return formattedMessage.toString();
	}

	/**
	 * This Comparator is used to sort messages first by file name, then by line number.
	 */
	public Comparator getMessageComparator(final IFilesystemHelper helper) {
		if(_comparator == null) {
			_comparator = 	new Comparator() {
				protected int compare(String a, String b) {
					if((a == null) && (b == null)) {
						return 0;
					}
					else if(a == null) {
						return -1;
					}
					else if(b == null) {
						return 1;
					}
					
					return a.compareTo(b);
				}
				
				protected int compare(int a, int b) {
					if((a == IMessage.LINENO_UNSET) && (b == IMessage.LINENO_UNSET)) {
						return 0;
					}
					else if(a == IMessage.LINENO_UNSET) {
						return -1;
					}
					else if(b == IMessage.LINENO_UNSET) {
						return 1;
					}
					
					return (a - b);
				}
				
				public int compare(Object a, Object b) {
					if((a == null) && (b == null)) {
						return 0;
					}
					else if(a == null) {
						return -1;
					}
					else if(b == null) {
						return 1;
					}
					
					IMessage aMssg = (IMessage)a;
					IMessage bMssg = (IMessage)b;
					
					// Sort by file name, then line number. Unset line numbers go to the start of the list.
					String aFileName = helper.getFileName(aMssg);
					String bFileName = helper.getFileName(bMssg);
					int result = compare(aFileName, bFileName);
					if(result != 0) {
						return result;
					}
					
					int aLineNumber = aMssg.getLineNumber();
					int bLineNumber = bMssg.getLineNumber();
					return compare(aLineNumber, bLineNumber);
				}
			};
		}
		return _comparator;
	}
}
