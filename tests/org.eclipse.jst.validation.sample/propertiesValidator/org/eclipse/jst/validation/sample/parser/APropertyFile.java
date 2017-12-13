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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class represents a .properties file; extensions of this class implement
 * environment-specific messages, such as how to report the known problems. This
 * class, and every class in the org.eclipse.jst.validation.sample.parser package, is independent
 * of the framework environment.
 */
public abstract class APropertyFile {
	private static final String EQUALS = "="; //$NON-NLS-1$

	private List _propertyLines = null; // The List of PropertyLine instances.
	private List _parseWarnings = null; // The List of MessageMetaData warnings found when parsing the .properties file.
	private boolean _debug = false;

	/**
	 * Return a name that identifies this file uniquely.
	 */
	public abstract String getQualifiedFileName();
	
	/**
	 * Tell the user that there is a problem - in WebSphere Studio, this
	 * shows up as a row in the Task View; in the sample filesystem framework,
	 * this appears as a message to System.out.
	 */
	public abstract void report(String str);
	
	/**
	 * Tell the user that there is a problem - in WebSphere Studio, this
	 * shows up as a row in the Task View; in the sample filesystem framework,
	 * this appears as a message to System.out.
	 */
	public abstract void report(MessageMetaData mmd);
	
	protected APropertyFile() {
		_propertyLines = new ArrayList();
		_parseWarnings = new ArrayList();
	}
	
	/**
	 * Add the List of MessageMetaData to the list of warning messages.
	 */
	public void addParseWarnings(List list) {
		_parseWarnings.addAll(list);
	}

	/**
	 * Add a single warning message to the list of parse warnings.
	 */
	public void addParseWarning(MessageMetaData mmd) {
		_parseWarnings.add(mmd);
	}
		
	/**
	 * Return the List of warning messages that report problems found
	 * while parsing the .properties file.
	 */
	public List getParseWarnings() {
		return _parseWarnings;
	}
	
	
	public String toString() {
		return getQualifiedFileName();
	}

	/**
	 * Should debug information be printed when parsing?
	 */	
	public boolean debug() {
		return _debug;
	}
	
	protected void setDebug(boolean d) {
		_debug = d;
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(!(o instanceof PropertyFile)) {
			return false;
		}
		
		PropertyFile file = (PropertyFile)o;
		return getQualifiedFileName().equals(file.getQualifiedFileName());
	}
	
	public int hashCode() {
		return getQualifiedFileName().hashCode();
	}

	/**
	 * Return the number of messages in the file, including duplicates & blanks.
	 */
	public int getNumProperties() {
		return _propertyLines.size();
	}

	/**
	 * Excluding message ids and error ids, output the number of words in the
	 * .properties file.
	 */
	public int getNumWords() {
		// NOT a fast method....how to optimize?
		Iterator iterator = _propertyLines.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			PropertyLine line = (PropertyLine) iterator.next();
			count += line.getNumWords();
		}
		return count;
	}
	
	/**
	 * Return a count of the number of unique message prefixes in the file.
	 * (A message prefix starts the message text, e.g., ABCD0000E.)
	 */
	public int getNumUniquePrefixes() {
		Set uniqueIds = new HashSet();
		Iterator iterator = getPropertyLines().iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(line.hasMessagePrefix()) {
				uniqueIds.add(line.getMessagePrefix());
			}
		}
		return uniqueIds.size();
	}
	
	/**
	 * Return a count of the number of message prefixes used in the file.
	 * Usually this is the same as the number of unique prefixes, but if 
	 * a .properties file has variations of a message (slightly different 
	 * wording for a different context), then the number of prefixes will 
	 * be larger than the number of unique prefixess.
	 */
	public int getNumPrefixes() {
		int count = 0;
		Iterator iterator = getPropertyLines().iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(line.hasMessagePrefix()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Return a count of the number of messages that do not use prefixes in the file.
	 */
	public int getNumWithoutPrefixes() {
		int count = 0;
		Iterator iterator = getPropertyLines().iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(!line.hasMessagePrefix()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Return a List of the PropertyLine instances contained in this file.
	 */
	public List getPropertyLines() {
		return _propertyLines;
	}
	
	/**
	 * Return the PropertyLine instance that is identified by the 
	 * given messageId.
	 */
	public PropertyLine getPropertyLine(String messageId) {
		Collections.sort(_propertyLines, PropertyLineComparator.getMessageIdComparator());
		int index = Collections.binarySearch(_propertyLines, messageId, PropertyLineComparator.getMessageIdComparator());
		if(index < 0) {
			return null;
		}
		
		PropertyLine result = (PropertyLine)_propertyLines.get(index);
		return result;
	}

	/**
	 * Parse the file to create a collection of uniquely identified 
	 * keys with their associated values. If there are any duplicate 
	 * keys, the last value is the one that is stored. Store the 
	 * value of the duplicate keys, and the line number on which 
	 * each instance of the key (and its value) are located.
	 */
	protected void parseFile(File propertyFile) {
		FileReader input = null;
		LineNumberReader lineInput = null;
		try {
			input = new FileReader(propertyFile);
		}
		catch (FileNotFoundException e) {
			// We can ignore this exception because we have already checked in the constructor that the
			// file exists.
		}

		// Because we want to read in a line at a time from the file, convert the FileReader to a LineReader
		lineInput = new LineNumberReader(input);
		
		parseFile(lineInput);
		
		try {
			lineInput.close();
		}
		catch (IOException e) {
		}
		input = null;
		lineInput = null;
	}
		
	/**
	 * Given a LineNumberReader on a .properties file, read the
	 * file and note any problems that may need to be reported to
	 * the user.
	 */
	protected void parseFile(LineNumberReader lineInput) {
		_propertyLines.clear();
		_parseWarnings.clear();

		boolean isNewKey = true;
		String key = null;
		int lineNumber = 0;
		String value = null;
		int offset = 0; // each .properties line needs to know its offset so that columns in the line can be calculated.
		int messageOffset = 0; // the offset, relative to the start of the line, where the message prefix starts
		String line = null;
		while (true) {
			try {
				line = lineInput.readLine(); // calculate the offset in the finally block
				if (line == null)
					break;
				line = line.trim();

				// ignore blank lines
				if (line.equals("")) //$NON-NLS-1$
					continue;

				// strip off lines that begin with '#'
				if (line.startsWith("#")) //$NON-NLS-1$
					continue;

				// strip off lines that begin with '/'
				if (line.startsWith("/")) //$NON-NLS-1$
					continue;

				if (isNewKey) {
					// We know that the line is of the form
					//
					// KEYNAME = VALUE
					//
					if (line.indexOf(EQUALS) != -1) {
						// Make sure the line is not of the form " = Message" (i.e., missing the message id).
						if(line.indexOf(EQUALS) == 0) {
							throw new java.util.NoSuchElementException();
						}
						
						// The user may or may not put spaces between the key
						// name, the equals sign, and the value. This gives us four cases:
						//    1. keyname=value
						//    2. keyname= value
						//    3. keyname =value
						//    4. keyname = value
						// The way to deal with all four cases is to find the index of the
						// '=' sign, and the value is the remainder of the line after the
						// sign.
						//
						key = line.substring(0, line.indexOf(EQUALS));
						key = key.trim();

						// add 1 to the EQUALS index because '=' is one character long
						value = line.substring(line.indexOf(EQUALS) + 1);
						value = value.trim();

						messageOffset = line.indexOf(value);

						lineNumber = lineInput.getLineNumber();
					}
					else {
						// error in line syntax
						throw new java.util.NoSuchElementException();
					}
				}
				else {
					// need to read in the multiple lines to get the multi-line value
					value += line;
				}

				if (value.endsWith("\\")) { //$NON-NLS-1$
					// multi-line value
					// read in every line from the file until you reach an end-of-line
					isNewKey = false;
				}
				else {
					// end of multi-line value, or end of single-line value
					isNewKey = true;
					PropertyLine pline = new PropertyLine(this, offset, messageOffset, key, value, lineNumber);
					_propertyLines.add(pline);
				}
			}
			catch (IOException e) {
				// When there is an IOException, we have reached the end of the file.
				break;
			}
			catch (NullPointerException e) {
				// If a line is not of the form KEYNAME = VALUE, there might be a null exception.
				// Just continue onto the next line in the file
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.ERROR, IValidationConstants.ABCD0080, null, this, lineInput.getLineNumber());
				report(mmd);
				break;
			}
			catch (java.util.NoSuchElementException e) {
				// If a line is not of the form KEYNAME = VALUE, there might be a NoSuchElement exception.
				// Just continue onto the next line in the file
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.ERROR, IValidationConstants.ABCD0080, null, this, lineInput.getLineNumber());
				report(mmd);
			}
			finally {
				if(line != null) {
					offset += line.length() + 2; // hack. 
					// Can't get offset from BufferedReader, and since the reader strips 
					// the '\n' and '\r' from the line before the line is returned, the 
					// true length of the line can't be ascertained. This hack will not 
					// work on Unix because unix's EOL char is different than Windows.
					//
					// When there's time, need to find an alternative to LineNumberReader.
				}
			}
		}
	}
	
	/**
	 * Print a list of all message prefixes, e.g., ABCD0000E, that are used
	 * in the .properties file.
	 */
	public void printAllMessagePrefixes() {
		report("Line Number\tMessage prefix\tMessage id"); //$NON-NLS-1$
		Collections.sort(_propertyLines, PropertyLineComparator.getMessagePrefixComparator());
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if((line.getMessagePrefix() != null) && (!line.getMessagePrefix().equals(""))) { //$NON-NLS-1$
				StringBuffer buffer = new StringBuffer(String.valueOf(line.getLineNumber()));
				buffer.append("\t"); //$NON-NLS-1$
				buffer.append(line.getMessagePrefix());
				buffer.append("\t"); //$NON-NLS-1$
				buffer.append(line.getMessageId());
				report(buffer.toString());
			}
		}
	}

	/**
	 * Print a list of all of the message prefixes, e.g., ABCD1111E, 
	 * that are not used in the file. A message prefix is considered
	 * &quot;missing&quot; if a message prefix that precedes it, 
	 * e.g. ABCD0000E, and a message prefix that follows it, 
	 * e.g. ABCD2222E, are contained in the file, but the prefix is not.
	 * This list is useful when adding a new message to the file;
	 * the prefixes printed by this method are unused and one can be
	 * selected for the new method.
	 */
	public void printAllMissingMessagePrefixes() {
		int lastNumber = -1;
		
		Collections.sort(_propertyLines, PropertyLineComparator.getMessagePrefixComparator());
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			String prefix = line.getMessagePrefix();
			int prefixNumber = 0;
			if(prefix == null) {
				continue;
			}
			
			if(prefix.equals("")) { //$NON-NLS-1$
				continue;
			}
			
			String prefixLetters = prefix.substring(0, 4); // "ABCD" of "ABCD0000W"
			try {
				// Re: magic numbers "4" and "8" below.
				// Since the message prefix is of the form ABCD0000E,
				// strip off the first four characters up to (and excluding) the 
				// eighth character, "E".
				prefixNumber = Integer.parseInt(prefix.substring(4, 8));
			}
			catch(NumberFormatException e) {
				// just continue
				continue;
			}
			if(lastNumber != -1) {
				if(prefixNumber != (lastNumber + 1)) {
					// Start at lastNumber + 1 because lastNumber exists.
					// Exclude prefixNumber because prefixNumber exists.
					for(int i=lastNumber+1; i<prefixNumber; i++) {
						report(prefixLetters + i);
					}
				}
			}
			lastNumber = prefixNumber;
		}
	}

	/**
	 * Print all of the messages in the .properties file that use
	 * a message prefix, e.g. ABCD0000E.
	 */
	public void printAllMessagesWithAMessagePrefix() {
		report("MESSAGES WITH PREFIXES"); //$NON-NLS-1$
		Collections.sort(_propertyLines, PropertyLineComparator.getLineNoComparator());
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(line.hasMessagePrefix()) {
				report(line.toString());
			}
		}
	}

	/**
	 * Print all of the messages in the .properties file whose message
	 * text does not begin with a message prefix, e.g., ABCD0000E.
	 */
	public void printAllMessagesWithoutAMessagePrefix() {
		report("BLANK MESSAGE PREFIX"); //$NON-NLS-1$
		Collections.sort(_propertyLines, PropertyLineComparator.getLineNoComparator());
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(!line.hasMessagePrefix()) {
				report(line.toString());
			}
		}
	}
	
	/**
	 * Print a list of all messages whose text is the empty string (&quot;&quot;).
	 */
	public void printAllMessagesWhichAreBlank() {
		report("BLANK MESSAGES"); //$NON-NLS-1$
		Collections.sort(_propertyLines, PropertyLineComparator.getLineNoComparator());
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			if(line.getMessage().equals("")) { //$NON-NLS-1$
				StringBuffer buffer = new StringBuffer("line number "); //$NON-NLS-1$
				buffer.append(line.getLineNumber());
				buffer.append(", message id "); //$NON-NLS-1$
				buffer.append(line.getMessageId());
				report(buffer.toString());
			}
		}
	}
	
	/**
	 * Print a list of the lines that have syntax errors, for example,
	 * missing the equals sign between the message id and the message 
	 * text.
	 */
	public void printSyntaxWarnings() {
		List parseWarnings = getParseWarnings();
		Iterator iterator = parseWarnings.iterator();
		while (iterator.hasNext()) {
			report((MessageMetaData)iterator.next());
		}
	}

	/**
	 * Print a list of all of the messages in the .properties file that
	 * use the same message id. If more than one message uses the same
	 * id, when the ResourceBundle is asked for that message id, it will
	 * return the last message (i.e., the largest line number) with that
	 * id in the file.
	 */
	public void printDuplicateMessageId() {
		printDuplicateMessageId(true);
	}
	
	/**
	 * Print a list of all of the messages in the .properties file that
	 * use the same message id. If more than one message uses the same
	 * id, when the ResourceBundle is asked for that message id, it will
	 * return the last message (i.e., the largest line number) with that
	 * id in the file.
	 * 
	 * The boolean parameter is used to determine whether or not 
	 * the &quot;DUPLICATE MESSAGE IDS&quot; title is emitted before 
	 * the list of duplicates.
	 */
	public void printDuplicateMessageId(boolean printTitle) {
		if(printTitle) {
			report("DUPLICATE MESSAGE IDS"); //$NON-NLS-1$
		}
		Comparator c = PropertyLineComparator.getMessageIdComparator();
		String messageId = IValidationConstants.ABCD0060;
		Collections.sort(_propertyLines, c);
		Iterator iterator = _propertyLines.iterator();
		PropertyLine lastLine = null;
		PropertyLine line = null;
		boolean needToPrintLast = false;
		while(iterator.hasNext()) {
			lastLine = line;
			line = (PropertyLine)iterator.next();
			
			if(c.compare(lastLine, line) == 0) {
				needToPrintLast = true;
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{lastLine.getMessageId()}, lastLine, lastLine.getLineNumber());
				report(mmd);
			}
			else if(needToPrintLast) {
				// The last duplicate line needs to be printed, because lastLine is always printed but line needs to be printed too.
				needToPrintLast = false;
				MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{lastLine.getMessageId()}, lastLine, lastLine.getLineNumber());
				report(mmd);
			}
		}

		if(needToPrintLast) {
			// The last duplicate line needs to be printed, because lastLine is always printed but line needs to be printed too.
			MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{line.getMessageId()}, line, line.getLineNumber());
			report(mmd);
		}
	}
	
	/**
	 * Print a list of all of the messages in the .properties file that
	 * do not use a unique message prefix (e.g., ABCD0000E), with the 
	 * exception of all messages which are intended to be duplicates.
	 * Generally, users expect one message prefix per problem, so 
	 * reused message prefixes can be confusing. Any message which 
	 * deliberately reuses a message prefix must use a message id 
	 * that starts with that prefix.
	 */
	public void printDuplicateMessagePrefix() {
		printDuplicateMessagePrefix(true);
	}
	
	/**
	 * Print a list of all of the messages in the .properties file that
	 * do not use a unique message prefix (e.g., ABCD0000E), with the 
	 * exception of all messages which are intended to be duplicates.
	 * Generally, users expect one message prefix per problem, so 
	 * reused message prefixes can be confusing. Any message which 
	 * deliberately reuses a message prefix must use a message id 
	 * that starts with that prefix.
	 * 
	 * The boolean parameter is used to determine whether or not 
	 * the &quot;DUPLICATE MESSAGE PREFIXESS&quot; title is emitted 
	 * before the list of duplicates.
	 */	
	public void printDuplicateMessagePrefix(boolean printTitle) {
		if(printTitle) {
			report("DUPLICATE MESSAGE PREFIXES"); //$NON-NLS-1$
		}
		Comparator c = PropertyLineComparator.getMessagePrefixComparator();
		Collections.sort(_propertyLines, c);
		String messageId = IValidationConstants.ABCD0070;
		Iterator iterator = _propertyLines.iterator();
		PropertyLine lastLine = null;
		PropertyLine line = null;
		boolean needToPrintLast = false;
		while(iterator.hasNext()) {
			lastLine = line;
			line = (PropertyLine)iterator.next();
			
			if(lastLine != null) {
				if(!lastLine.getMessageId().startsWith(lastLine.getShortMessagePrefix())) {
					if(c.compare(lastLine, line) == 0) {
						needToPrintLast = true;
						MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{lastLine.getMessagePrefix()}, lastLine, lastLine.getLineNumber());
						report(mmd);
					}
					else if(needToPrintLast) {
						// The last duplicate line needs to be printed, because lastLine is always printed but line needs to be printed too.
						needToPrintLast = false;
						MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{lastLine.getMessagePrefix()}, lastLine, lastLine.getLineNumber());
						report(mmd);
					}
				}
			}
		}

		if(lastLine != null) {
			if(!lastLine.getMessageId().startsWith(lastLine.getShortMessagePrefix())) {
				if(needToPrintLast) {
					// The last duplicate line needs to be printed, because lastLine is always printed but line needs to be printed too.
					MessageMetaData mmd = new MessageMetaData(IValidationConstants.BUNDLENAME, MessageMetaData.WARNING, messageId, new String[]{line.getMessagePrefix()}, line, line.getLineNumber());
					report(mmd);
				}
			}
		}
	}

	/**
	 * Print a list of message prefixes whose first characters match
	 * the prefix parameter. This method is useful when you want to see
	 * if a range of prefixes is in use or not.
	 */
	public void printMessagePrefixStartingWith(final String prefix) {
		report("Find all error prefixes starting with " + prefix); //$NON-NLS-1$
		Comparator c = PropertyLineComparator.getMessagePrefixComparator();
		Collections.sort(_propertyLines, c);
		Iterator iterator = _propertyLines.iterator();
		PropertyLine line = null;
		while(iterator.hasNext()) {
			line = (PropertyLine)iterator.next();
			if (line.getMessagePrefix().startsWith(prefix)) {
				StringBuffer buffer = new StringBuffer(String.valueOf(line.getLineNumber()));
				buffer.append("\t"); //$NON-NLS-1$
				buffer.append(line.getMessagePrefix());
				buffer.append("\t"); //$NON-NLS-1$
				buffer.append(line.getMessageId());
				report(buffer.toString());
			}
		}
	}
	
	/**
	 * Print the last prefix (i.e., the one with the largest number, 
	 * e.g. ABCD2222 is larger than ABCD1111 or ABCD0000) in the file 
	 * whose first characters match the prefix parameter. 
	 */
	public void printLastMessagePrefixStartingWith(final String prefix) {
		report("Find last error prefix starting with " + prefix); //$NON-NLS-1$
		Comparator c = PropertyLineComparator.getMessagePrefixComparator();
		Collections.sort(_propertyLines, c);
		PropertyLine[] lines = new PropertyLine[_propertyLines.size()];
		_propertyLines.toArray(lines);
		boolean found = false;
		for(int i=lines.length; i>-1; i--) {
			PropertyLine line = lines[i];
			if (line.getMessagePrefix().startsWith(prefix)) {
				report(line.toString());
				found = true;
				break;
			}
		}
		if(!found) {
			report("No error ids were found that started with " + prefix); //$NON-NLS-1$
		}
	}

	/**
	 * Given an estimate of the number of characters in a substitution parameter,
	 * print a list of the number of characters in a given message, if each 
	 * parameter is replaced with &quot;length&quot; characters.
	 */
	public void printExpectedStringLength(final int length) {
		report("EXPECTED LENGTH OF MESSAGE WITH PARAMETERS " + length + " LONG"); //$NON-NLS-1$ //$NON-NLS-2$
		Comparator c = PropertyLineComparator.getStringLengthComparator(length);
		Collections.sort(_propertyLines, c);
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();

			StringBuffer buffer = new StringBuffer("Length: "); //$NON-NLS-1$
			buffer.append(line.getExpectedLength(length));
			buffer.append("\t"); //$NON-NLS-1$
			buffer.append(line.toStringWithExpectedLength(length));
			report(buffer.toString());
		}
	}

	/**
	 * Print the number of characters in each message.
	 */
	public void printStringLength() {
		// "0" means no substitution
		printExpectedStringLength(0);
	}

	/**
	 * Print the contents of the .properties file, sorted by message id.
	 */
	public void printContents() {
		report("CONTENTS OF .properties FILE, SORTED BY MESSAGE ID"); //$NON-NLS-1$
		Comparator c = PropertyLineComparator.getMessageIdComparator();
		Collections.sort(_propertyLines, c);
		Iterator iterator = _propertyLines.iterator();
		while(iterator.hasNext()) {
			PropertyLine line = (PropertyLine)iterator.next();
			report(line.toString());
		}
	}

}