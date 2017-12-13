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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * PropertyLine represents a line in a PropertyFile. It has a message id,
 * a message value (the text of the property; this is the text which is
 * translated), a line number, and, optionally, an unique error id (of the
 * form ABCD1234{E|W|I}).
 */
public class PropertyLine implements Comparable {
	private String _messageId; // the message id (not translated)
	private String _message = null; // the message value with the error id (translated)
	private String _messagePrefix = null; // the prefix (e.g. ABCD1234{E|W|I}
	private String _shortMessage = null; // the message value without the error id (translated)
	private int _lineNumber = -1; // the line number of this message value
	private int _numWords = -1;
	private final String EMPTY = ""; //$NON-NLS-1$
	private APropertyFile _file = null;
	private int _offset = -1; // the offset, in the .properties file, where this line begins
	private int _messageOffset = -1;  // the offset in the line, after the EQUALS, where the message prefix begins
	
	private static final char QUOTE = '\'';
	private static final char OBRACKET = '{';
	private static final char CBRACKET = '}';

	// The Enum for the message severity can be one of these four values. If there is an error id,
	// UNASSIGNED indicates an invalid value. 
	public final static int UNASSIGNED = 0;
	public final static int ERROR = 0x1;
	public final static int WARNING = 0x2;
	public final static int INFO = 0x3;

	private int _severity = UNASSIGNED; // the severity of the message, if this message has an error id. UNASSIGNED means either that the severity was an invalid severity (not info, warn, or error), or that the message has no error id. Check the value of the error id to find out which of the two is the case.
	private int _numParms = 0;
	
	private static final char[] BLANK = "                                                                                                    ".toCharArray(); // this string is 100 characters long. It's used to quickly get a blank string in the getBlank(int) method. Hopefully the user will never want more than 100 char in a given parameter. //$NON-NLS-1$

	public PropertyLine(APropertyFile file, int offset, int messageOffset, String messageId, String message, int lineNumber) {
		// Comment on the use of intern().
		// Because it's likely that we'll be comparing this string a lot,
		// load it into the Java constants space through intern(). Since we won't
		// be modifying this string, it's safe to load it into the constants space.
		//
		_offset = offset; // the offset in the .properties file where this line begins
		_messageOffset = messageOffset; // the offset in the line, after the EQUALS, where the message prefix begins
		_messageId = messageId;
		_message = message; // re: intern(). Ditto.
		_lineNumber = lineNumber;
		_file = file;

		parse(message);
	}

	public int compareTo(Object o) {
		// by default, sort by message id
		if (!(o instanceof PropertyLine)) {
			// then how on earth did this method get called??
			// put it at the end of the list
			return 1;
		}

		return getMessageId().compareTo(((PropertyLine) o).getMessageId());
	}

	public APropertyFile getFile() {
		return _file;
	}

	public String getMessagePrefix() {
		return _messagePrefix;
	}
	
	public boolean hasMessagePrefix() {
		if(_messagePrefix == null) {
			return false;
		}
		
		if(_messagePrefix.equals(EMPTY)) {
			return false;
		}
		
		return true;
	}
	
	public int getLineNumber() {
		return _lineNumber;
	}
	public String getMessage() {
		return _message;
	}
	public String getMessageId() {
		return _messageId;
	}
	public String getShortMessage() {
		return _shortMessage;
	}
	
	/**
	 * This method returns the number of words in the message excluding 
	 * the message error id prefix.
	 */
	public int getNumWords() {
		if(_numWords == -1) {
			_numWords = 0;
			StringTokenizer tokenizer = new StringTokenizer(getShortMessage());
			_numWords = tokenizer.countTokens();
		}
		return _numWords;
	}
	
	/**
	 * This method returns the number of characters in the 
	 * message including the message prefix and parameters. (i.e., {0} would
	 * be counted as three characters.)
	 */
	public int getLength() {
		return getMessage().length();
	}
	
	/**
	 * This method returns the number of characters in the 
	 * message including the message prefix and the expected 
	 * number of characters. (That is, the lengthParm parameter
	 * is used as an approximation of the length of each parameter
	 * in the message.)
	 */
	public int getExpectedLength(int lengthParm) {
		if(hasParameters() && (lengthParm > 0)) {
			return getExpectedMessage(lengthParm).length();
		}
		return getLength();
	}
	
	/**
	 * This method substitutes a blank stub of the given length
	 * every place in the string where there is a parm.
	 */
	public String getExpectedMessage(int lengthParm) {
		if(hasParameters() && (lengthParm > 0)) {
			// message cannot be null or blank or it wouldn't have parameters
			String blank = getBlank(lengthParm);
			
			int numParm = getNumParameters();
			String[] parm = new String[numParm];
			for(int i=0; i<parm.length; i++) {
				parm[i] = blank;
			}
			
			try {
				return java.text.MessageFormat.format(getMessage(), parm);
			}
			catch(IllegalArgumentException exc) {
				if(getFile().debug()) {
					getFile().report(exc.getMessage());
					getFile().report(getFile().getQualifiedFileName() + "::" + getMessageId()); //$NON-NLS-1$
				}
				return getMessage();
			}
		}
		return getMessage();
	}
	
	/**
	 * Return a blank string of the given length.
	 */
	public static String getBlank(int length) {
		char[] blank = new char[length];
		if(length > BLANK.length) {
			for(int i=0; i<length; i++) {
				blank[i] = ' ';
			}
		}
		else {
			System.arraycopy(BLANK, 0, blank, 0, length);
		}
		return new String(blank);
	}

	/**
	 * Return true if the line has java.text.MessageFormat patterns.
	 */
	public boolean hasParameters() {
		return getNumParameters() > 0;
	}
	
	/**
	 * Return the number of java.text.MessageFormat patterns in the property line.
	 */
	public int getNumParameters() {
		return _numParms;
	}
	
	/**
	 * Return the number of characters, from the start of the file, to
	 * the beginning of the message.
	 */
	public int getMessageOffset() {
		return _messageOffset;
	}
	
	/**
	 * The offset, in the .properties file, where this line begins.
	 */
	public int getOffset() {
		return _offset;
	}
	
	/**
	 * The severity of the message, if this message has an error id. 
	 * UNASSIGNED means either that the severity was an invalid severity 
	 * (not info, warn, or error), or that the message has no error id. Check 
	 * the value of the error id to find out which of the two is the case.
	 */
	public int getSeverity() {
		return _severity;
	}

	/**
	* Parse the message value to extract the unique error id, if any exists.
	* This method initializes some of the fields in this class.
	*/
	public void parse(String message) {
		// To see why it's safe to call intern(), read the comment in the
		// constructor.

		// Case 1: ABCD0000E: Blah Blah Blah
		// Case 2: ABCD0000E  Blah Blah Blah
		// Case 3: ABCD0000E : Blah Blah Blah
		// Case 4: ABCD0000E  Blah:Blah Blah
		// Case 5: ABCD0000E :
		// Case 6: Blah Blah Blah (i.e., no prefix)
		// Case 7: ABCD0000E: Blah
		// Case 8: ABCD0000E  Blah
		// Case 9: ABCD0000E : Blah
		// Case 10: ABCD0000E Blah
		// Case 11: ABCD0000E

		if (message == null) {
			_messagePrefix = EMPTY;
			_shortMessage = EMPTY;
			return;
		}

		StringTokenizer tokenizer = new StringTokenizer(message);
		int numTokens = tokenizer.countTokens();
		if (numTokens == 0) {
			_messagePrefix = EMPTY;
			_shortMessage = message;
		}
		else {
			String errorId = tokenizer.nextToken();
			boolean prefixExists = false;

			// Sometimes the ':' isn't there or isn't in the right place, so don't determine the message prefix based on that.
			String afterPrefix = null;
			if(errorId.length() >= 9) {
				afterPrefix = errorId.substring(0, 9); // Must be of the form ABCD1234E, which is 9 char long
				char[] temp = afterPrefix.toCharArray();
				prefixExists = Character.isLetter(temp[0]) && Character.isUpperCase(temp[0]);
				prefixExists = prefixExists && Character.isLetter(temp[1]) && Character.isUpperCase(temp[0]);
				prefixExists = prefixExists && Character.isLetter(temp[2]) && Character.isUpperCase(temp[0]);
				prefixExists = prefixExists && Character.isLetter(temp[3]) && Character.isUpperCase(temp[0]);
				prefixExists = prefixExists && Character.isDigit(temp[4]);
				prefixExists = prefixExists && Character.isDigit(temp[5]);
				prefixExists = prefixExists && Character.isDigit(temp[6]);
				prefixExists = prefixExists && Character.isDigit(temp[7]);
				prefixExists = prefixExists && Character.isLetter(temp[8]) && Character.isUpperCase(temp[0]);

				if (prefixExists) {
					switch (temp[8]) {
						case ('E') :
							{
								_severity = ERROR;
								break;
							}
						case ('W') :
							{
								_severity = WARNING;
								break;
							}
						case ('I') :
							{
								_severity = INFO;
								break;
							}
	
						default :
							{
								_severity = UNASSIGNED;
								break;
							}
					}
					_messagePrefix = afterPrefix;
					
					int colonIndex = errorId.indexOf(':');
					boolean noColon = (colonIndex == -1);
	
					int shortMessageIndex = 10; // assume that only the ABCD0000E prefix will be stripped (i.e., no colon)
					if(noColon) {
						// No colon following message prefix immediately.
						// Is there whitespace before the colon?
						if (numTokens > 1) {
							String nextToken = tokenizer.nextToken();
							colonIndex = nextToken.indexOf(':');
							noColon = ((colonIndex == -1) || (nextToken.trim().indexOf(':') != 0)); // case 4 trim condition will be true
							
							// ":" should follow the message prefix immediately
							// Know there's a prefix, and that's 9 char
							if(noColon) {
								// Case 2, 4, 8 or 10
//								MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0120, new String[]{}, this, getLineNumber());
//								getFile().addParseWarning(mmd);
								shortMessageIndex = 10;
							}
							else {
//								MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0130, new String[]{}, this, getLineNumber());
//								getFile().addParseWarning(mmd);
								
								if(numTokens > 2) {
									// Case 3 or 9
									nextToken = tokenizer.nextToken();
									shortMessageIndex = message.indexOf(nextToken);
								}
								else {
									// Case 5
									shortMessageIndex = colonIndex + 1;
								}								
							}
							_shortMessage = message.substring(shortMessageIndex, message.length());
						}
						else {
							// Case 11
							_shortMessage = EMPTY;
						}
					}
					else {
						// Case 1 or 7
						shortMessageIndex = colonIndex + 1;
						_shortMessage = message.substring(shortMessageIndex, message.length());
					}
				}
	
			}

			if (!prefixExists) {
				// Case 6
				_messagePrefix = EMPTY;
				_shortMessage = message;
			}
		}

		getFile().addParseWarnings(parseMessage());
	}

	/**
	 * Once the field that holds the message text has been set (i.e., parse(String)
	 * has been called), this method parses the message text, finds any syntax or
	 * other problems, and returns a List of MessageMetaData, with one MessageMetaData
	 * for each problem.
	 */
	public List parseMessage() {
		// now parse to see if all apostrophes are in pairs, if there are parameters or not, etc.
		ArrayList intList = new ArrayList();
		int numObrackets = 0;
		int numCbrackets = 0;
		int index = 0;
		char token;
		int singleQuoteIndex = -1;
		int doubleQuoteIndex = -1;
		int missingTerminatingQuoteIndex = -1;
		boolean parmExists = false;
		int numCharBeforeShort = getMessageOffset() + getMessage().indexOf(getShortMessage());
		
		while (index < getShortMessage().length()) {
			token = getShortMessage().charAt(index++);
			if (token == QUOTE) {
				if (index < getShortMessage().length()) {
					token = getShortMessage().charAt(index++);
				}
				else {
					token = ' ';
				}
				
				if ((token == OBRACKET) || (token == CBRACKET)){
					if (index < getShortMessage().length()) {
						token = getShortMessage().charAt(index++);
					}
					else {
						token = ' ';
					}
					
					// Does the { or } character have a terminating single quote?
					if(token != QUOTE) {
						missingTerminatingQuoteIndex = (numCharBeforeShort + index-1);
					}
				}
				else if ((token == QUOTE)) {
					doubleQuoteIndex = (numCharBeforeShort + index-1);
				}
				else  {
					// '{' means that a { will be output,
					// '}' means that a } will be output,
					// '' means that a ' will be output.
					
					// Since the quote is a special character, and the opening bracket is a 
					// special character, keep track of the number of open brackets. If the
					// following character isn't a quote or opening bracket, warn the user 
					// that it takes two apostrophes to see one apostrophe.
					singleQuoteIndex = (numCharBeforeShort + index-1);
				}
				
			}
			else if (token == OBRACKET) {
				numObrackets++;
				parmExists = true;
				StringBuffer numBuffer = null;
				if (index < getShortMessage().length()) {
					numBuffer = new StringBuffer();
					while (index < getShortMessage().length()) {
						char digit = getShortMessage().charAt(index);
						if ((Character.isDigit(digit)) || (digit == '-') || (digit == '+')) {
							numBuffer.append(digit);
							index++;
						}
						else {
							break;
						}
					}
				}
				else {
					// not an error; just not a number. Could be one of the other 
					// ChoiceFormat, or whatever, formats. Do not get the next 
					// token, because we still need to process this one.
					continue;
				}

				try {
					if ((numBuffer != null) && (numBuffer.length() > 0)) {
						Integer parmNum = Integer.valueOf(numBuffer.toString());
						intList.add(parmNum);
					}
				}
				catch (NumberFormatException exc) {
					// not an error; just not a number. Could be one of the other 
					// ChoiceFormat, or whatever, formats. Do not get the next 
					// token, because we still need to process this one.
				}
			}
			else if (token == CBRACKET) {
				numCbrackets++;
			}
		}
		
		_numParms = Math.max(numObrackets, numCbrackets);

		// find parse warnings
		List messages = new ArrayList();
		if (numObrackets != numCbrackets) {
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.ERROR, IValidationConstants.ABCD0000, new String[]{String.valueOf(numObrackets), String.valueOf(numCbrackets)}, this, getLineNumber());
			messages.add(mmd);
		}
		
		if(missingTerminatingQuoteIndex != -1) {
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0110, new String[]{String.valueOf(missingTerminatingQuoteIndex)}, this, getLineNumber(), getOffset() + missingTerminatingQuoteIndex, 1); // magic number 1 because a quote is one character long
			messages.add(mmd);
		}

		// Now sort the list, to see if there's any numbers missing, duplicated, or negative
		Collections.sort(intList, new Comparator() {
			public int compare(Object a, Object b) {
				// negative = b before a
				// zero = a = b
				// positive = a before b
				return ((Integer) a).intValue() - ((Integer) b).intValue();
			}
		});
		Iterator iterator = intList.iterator();
		int parmCount = 0;
		int lastNumber = -1;
		int largestNumber = -1;
		while (iterator.hasNext()) {
			Integer parm = (Integer) iterator.next();
			if (parm.intValue() < 0) {
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.ERROR, IValidationConstants.ABCD0010E, new String[]{String.valueOf(parm.intValue())}, this, getLineNumber());
				messages.add(mmd);
			}
			else if (parm.intValue() == lastNumber) {
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0020, new String[]{String.valueOf(lastNumber)}, this, getLineNumber());
				messages.add(mmd);
			}
			else if (parm.intValue() != parmCount) {
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0030, new String[]{String.valueOf(parmCount)}, this, getLineNumber());
				messages.add(mmd);
			}
			else {
				parmCount++;
			}
			lastNumber = parm.intValue();
			largestNumber = Math.max(largestNumber, parm.intValue());
		}
		if(largestNumber > 0) {
			// Not a choice format.
			//
			// Although the first default (counting the number of 
			// brackets) is okay in most cases, sometimes a parameter 
			// is missing, and so the number of paramters passed into 
			// the MessageFormat needs to be larger. (e.g. {0} {2}, 
			// need String[] to be {"", "", ""}, not {"", ""}). 
			
			// Need to add "1" to it because the numbers start at a 0 index.
			_numParms = largestNumber + 1; 
		}
		
		if(largestNumber > 9) {
			// MessageFormat will not substitute in parameters after the 10 parameter (i.e., number 9).
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, IValidationConstants.ABCD0010W, new String[]{String.valueOf(largestNumber)}, this, getLineNumber());
			messages.add(mmd);
		}

		if ((singleQuoteIndex > -1) && parmExists) {
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0040, new String[]{String.valueOf(singleQuoteIndex)}, this, getLineNumber(), getOffset() + singleQuoteIndex, 1); // magic number 1 because a quote is one character long
			messages.add(mmd);
		}
		else if ((doubleQuoteIndex > -1) && !parmExists) {
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.INFO, IValidationConstants.ABCD0050, new String[]{String.valueOf(doubleQuoteIndex)}, this, getLineNumber(), getOffset() + doubleQuoteIndex, 1); // magic number 1 because a quote is one character long
			messages.add(mmd);
		}

		return messages;
	}

	/**
	 * Format this .properties line for display.
	 */
	public String toString() {
		StringBuffer buffer = null;
		if(getFile() == null) {
			buffer = new StringBuffer();
		}
		else {
			buffer = new StringBuffer(getFile().getQualifiedFileName());
		}
		buffer.append("\tLine number: "); //$NON-NLS-1$
		buffer.append(String.valueOf(getLineNumber()));
		buffer.append("\t"); //$NON-NLS-1$
		buffer.append(getMessageId());
		buffer.append(" = "); //$NON-NLS-1$
		buffer.append(getMessage());
		return buffer.toString();
	}
	
	/**
	 * Format this .properties line for display, and substitute a stub,
	 * of the specified length, into each java.text.MessageFormat pattern.
	 */
	public String toStringWithExpectedLength(int length) {
		StringBuffer buffer = null;
		if(getFile() == null) {
			buffer = new StringBuffer();
		}
		else {
			buffer = new StringBuffer(getFile().getQualifiedFileName());
		}
		buffer.append("\tLine number: "); //$NON-NLS-1$
		buffer.append(String.valueOf(getLineNumber()));
		buffer.append("\t"); //$NON-NLS-1$
		buffer.append(getMessageId());
		buffer.append(" = "); //$NON-NLS-1$
		buffer.append(getExpectedMessage(length));
		return buffer.toString();
	}
	
	/**
	 * Return the message prefix without the severity, e.g.,
	 * ABCD0000 instead of ABCD0000E.
	 */
	public String getShortMessagePrefix() {
		String prefix = getMessagePrefix();
		if(prefix.equals("")) { //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
		
		char prefixEnd = prefix.charAt(prefix.length() - 1);
		if ((prefixEnd == 'E') || (prefixEnd == 'W') || (prefixEnd == 'I')) {
			prefix = prefix.substring(0, prefix.length() - 1);
		}
		return prefix;
	}
	
	/**
	 * A PropertyLine is equal if it is in the same file and on the same line number.
	 */
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(!(o instanceof PropertyLine)) {
			return false;
		}
		
		PropertyLine oLine = (PropertyLine)o;
		if(!(getLineNumber() == oLine.getLineNumber())) {
			return false;
		}
		
		if(!getFile().equals(oLine.getFile())) {
			return false;
		}
		
		return true;
	}
	
	public int hashCode() {
		return (getFile().hashCode() + getLineNumber());
	}
}