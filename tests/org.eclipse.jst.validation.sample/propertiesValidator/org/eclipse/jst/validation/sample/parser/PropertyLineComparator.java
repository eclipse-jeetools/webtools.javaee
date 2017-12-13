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

import java.util.Comparator;

/**
 * This class contains all of the Comparator classes that can be used
 * to sort PropertyLine instances in an APropertyFile. (For example,
 * to find duplicate message ids, the MessageIdComparator would be used
 * to sort the lines according to message id. The sorted list is 
 * traversed to find out if one line has an identical message id 
 * to the line that follows it, and if so, a parse warning is reported.)
 */
public class PropertyLineComparator {
	private static PropertyLineComparator inst = new PropertyLineComparator();
	
	private static final Comparator _messagePrefixComparator = inst.new MessagePrefixComparator();
	private static final Comparator _messageIdComparator = inst.new MessageIdComparator();
	private static Comparator _stringLengthComparator = new StringLengthComparator();
	private static final Comparator _valueComparator = inst.new ValueComparator();
	private static final Comparator _lineNoComparator = inst.new LineNoComparator();

	private PropertyLineComparator() {
	}	

	/**
	 * This class compares PropertyLine instances and sorts them according to 
	 * their message prefix (e.g., ABCD0000E).
	 */
	private class MessagePrefixComparator implements java.util.Comparator {
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
			
			// Can't just look to see if they're the same; need to mark
			// them as the same even if the last letter (E|W|I) is different.
			String aid = ((PropertyLine) a).getMessagePrefix();
			String bid = ((PropertyLine) b).getMessagePrefix();
			if((aid == null) && (bid == null)) {
				return 0;
			}
			else if(aid == null) {
				return -1;
			}
			else if(bid == null) {
				return 1;
			}
				
			aid = aid.toUpperCase().trim();
			bid = bid.toUpperCase().trim();

			if((aid.equals("")) && (bid.equals(""))) { //$NON-NLS-1$  //$NON-NLS-2$
				return 0;
			}
			else if(aid.equals("")) { //$NON-NLS-1$
				return -1;
			}
			else if(bid.equals("")) { //$NON-NLS-1$
				return 1;
			}

			return ((PropertyLine)a).getShortMessagePrefix().toUpperCase().compareTo(((PropertyLine)b).getShortMessagePrefix().toUpperCase());
		}
	}
	
	/**
	 * This comparator sorts PropertyLine by their message id.
	 */
	private class MessageIdComparator implements java.util.Comparator {
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
			
			String aMessageId = null;
			String bMessageId = null;
			if(a instanceof PropertyLine) {
				aMessageId = ((PropertyLine)a).getMessageId();
			}
			else {
				aMessageId = (String)a;
			}
			
			if(b instanceof PropertyLine) {
				bMessageId = ((PropertyLine)b).getMessageId();
			}
			else {
				bMessageId = (String)b;
			}
				
			
			if((aMessageId == null) && (bMessageId == null)) {
				return 0;
			}
			else if(aMessageId == null) {
				return -1;
			}
			else if(bMessageId == null) {
				return 1;
			}
			else {
				return aMessageId.compareTo(bMessageId);
			}
		}
	}
	
	/**
	 * Sort the PropertyLine by the length of characters in the message text;
	 * if you want to substitute characters for the message parameters, 
	 * set the length field.
	 */
	static class StringLengthComparator implements java.util.Comparator {
		public static int length = 0;
		
		public int compare(Object a, Object b) {
			// return a negative number if object a is less than object b
			// return a zero if the objects are equal
			// return a positive number if object a is greater than object b
			if((a == null) && (b == null)) {
				return 0;
			}
			else if(a == null) {
				return -1;
			}
			else if(b == null) {
				return 1;
			}
			
			int aLength = ((PropertyLine)a).getExpectedLength(length);
			int bLength = ((PropertyLine)b).getExpectedLength(length);
				
			return (aLength - bLength);
		}
	}
	
	/**
	 * Alphabetize the message text of the PropertyLine.
	 */
	private class ValueComparator implements java.util.Comparator {
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
			
			String aMessage = ((PropertyLine)a).getMessage();
			String bMessage = ((PropertyLine)b).getMessage();
			if((aMessage == null) && (bMessage == null)) {
				return 0;
			}
			else if(aMessage == null) {
				return -1;
			}
			else if(bMessage == null) {
				return 1;
			}
				
			return aMessage.compareTo(bMessage);
		}
	}
	
	/**
	 * Sort by line number of the PropertyLine, with the smallest line
	 * number first in the list.
	 */
	private class LineNoComparator implements java.util.Comparator {
		public int compare(Object a, Object b) {
			// return a negative number if object a is less than object b
			// return a zero if the objects are equal
			// return a positive number if object a is greater than object b
			if((a == null) && (b == null)) {
				return 0;
			}
			else if(a == null) {
				return -1;
			}
			else if(b == null) {
				return 1;
			}
			
			int aLineNo = ((PropertyLine)a).getLineNumber();
			int bLineNo = ((PropertyLine)b).getLineNumber();
			if((aLineNo < 0) && (bLineNo < 0)) {
				return 0;
			}
			else if(aLineNo < 0) {
				return -1;
			}
			else if(bLineNo < 0) {
				return 1;
			}
			
			return (aLineNo - bLineNo);
		}
	}

	public static Comparator getMessagePrefixComparator() {
		return _messagePrefixComparator;
	}

	public static Comparator getMessageIdComparator() {
		return _messageIdComparator;
	}

	public static Comparator getStringLengthComparator(int length) {
		StringLengthComparator.length = length;
		return _stringLengthComparator;
	}

	public static Comparator getValueComparator() {
		return _valueComparator;
	}
	
	public static Comparator getLineNoComparator() {
		return _lineNoComparator;
	}
}

