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
 * This class compares two .properties file and outputs a list
 * of the differences. This class is used to count the number
 * of words that have changed.
 */
public class CompareProperties {
	public static final String lineSeparator = java.lang.System.getProperty("line.separator"); //$NON-NLS-1$
	private TreeSet _deleted = null;
	private TreeMap _changed = null;
	private TreeSet _added = null;
	private PropertyFile _oldFile = null;
	private PropertyFile _newFile = null;

	/**
	 * This class is used to store PropertyLine instances which have the same
	 * message id, but different values. When the list of differences is output,
	 * all values are output so that the user can compare the lines manually, to
	 * see what's different.
	 */
	class CompareLine implements Comparable {
		private ArrayList _lines;
		private final String _messageId;

		public CompareLine(String messageId) {
			_messageId = messageId;
			_lines = new ArrayList();
		}

		public void add(PropertyLine oldLine, PropertyLine newLine) {
			_lines.add(new PropertyLine[]{oldLine, newLine});
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer();
			Iterator iterator = _lines.iterator();
			while (iterator.hasNext()) {
				buffer.append(lineSeparator);
				buffer.append("\t"); //$NON-NLS-1$
				PropertyLine[] lines = (PropertyLine[])iterator.next();
				
				buffer.append(lines[0]);
				buffer.append("\n"); //$NON-NLS-1$
				buffer.append(lines[1]);
			}
			return buffer.toString();
		}

		/**
		 * Since this is a changed string, return the absolute difference of words between the strings.
		 */		
		public int getNumWords() {
			int numWords = 0;
			Iterator iterator = _lines.iterator();
			while (iterator.hasNext()) {
				PropertyLine[] lines = (PropertyLine[])iterator.next();
				numWords = numWords + compare(lines[0], lines[1]);
			}
			return numWords;
		}
		
		private int compare(PropertyLine oldLine, PropertyLine newLine) {
			// For every word in the old string, see if it exists in the new
			// string. The position of the word doesn't matter - if the word
			// exists in the new, then the word is not counted as a "changed"
			// word. 
			//    1. If the word exists, remove the word from the newLine and
			//       advance to the next old token. (Remove word from newLine
			//       in case the word existed twice in the old string but only
			//       once in the new. The second oldWord should be counted as
			//       a changed word.)
			//    2. If the word doesn't exist, numChanged++ and advance to the
			//       next old token.
			//    3. Once all of the oldWords have been checked, tokenize the
			//       remaining newWord and count the number of words in the string.
			//       These words have been added and each one counts as a 
			//       changed word.
			int numChangedWords = 0;
			StringTokenizer oldTokenizer = new StringTokenizer(oldLine.getMessage());

			// Need to be careful...want the entire word, not oldWord="on" mistaken for newWord="one" or newWord="bond"
			// Easier to create a list of new words to compare against.
			StringTokenizer newTokenizer = new StringTokenizer(newLine.getMessage());
			List newWords = new ArrayList(); // Can't use a set in case the newLine uses a word, e.g. "the", more than once.
			while(newTokenizer.hasMoreTokens()) {
				newWords.add(newTokenizer.nextToken());
			}
			
			while(oldTokenizer.hasMoreTokens()) {
				String oldWord = oldTokenizer.nextToken();
				if(newWords.contains(oldWord)) {
					newWords.remove(oldWord);
				}
				else {
					numChangedWords++;
				}
			}

			// Can count the tokens but not the elments.
			numChangedWords += newWords.size();
			return numChangedWords;
		}

		public String getMessageId() {
			return _messageId;
		}

		public int compareTo(Object o) {
			// by default, sort by message id
			if (!(o instanceof CompareLine)) {
				// then how on earth did this method get called??
				// put it at the end of the list
				return 1;
			}

			return getMessageId().compareTo(((CompareLine) o).getMessageId());
		}
	}

	/**
	 * Compare the two PropertyFile and print out a list of the differences; 
	 * the first parameter is the older .properties file, and the second 
	 * parameter is the newer .properties file.
	 */
	public CompareProperties(PropertyFile oldFile, PropertyFile newFile) {
		_deleted = new TreeSet();
		_changed = new TreeMap();
		_added = new TreeSet();
		
		_oldFile = oldFile;
		_newFile = newFile;

		compare(oldFile, newFile);
	}
	
	/**
	 * In the older PropertyFile, the message text was different; cache the
	 * older version of the PropertyLine and the newer version of the PropertyLine.
	 */
	private void addChanged(PropertyLine oldLine, PropertyLine newLine) {
		CompareLine cl = (CompareLine) _changed.get(oldLine.getMessageId());
		if (cl == null) {
			cl = new CompareLine(oldLine.getMessageId());
		}
		cl.add(oldLine, newLine);
		_changed.put(oldLine.getMessageId(), cl);
	}
	
	/**
	 * Compare the two property files and build the collections of variable names with
	 * their associated values.
	 */
	public void compare(PropertyFile oldFile, PropertyFile newFile) {
		_added.clear();
		_deleted.clear();
		_changed.clear();

		// For each element in file 1, see if it exists in file 2
		//   a) if it doesn't exist, add it to the list of "deleted" strings
		//   b) if it exists, and if the value is different, add it to the list of "changed" strings
		//   c) if it exists, and if the value is the same, add it to the list of "not changed" strings
		//   d) delete the entry, if it exists, from file 2's hashtable so we don't check it twice.
		// For each element in file 2 not checked already, it cannot exist in file 1, so add it to the list of "new" strings
		//
		// Need some way to abort comparison if either of the files contains duplicate
		// message ids.
		//
		List oldKeys = new ArrayList(oldFile.getPropertyLines());
		List newKeys = new ArrayList(newFile.getPropertyLines());
		Collections.sort(oldKeys, PropertyLineComparator.getMessageIdComparator());
		Collections.sort(newKeys, PropertyLineComparator.getMessageIdComparator());
		Iterator oldIterator = oldKeys.iterator();
		Iterator newIterator = newKeys.iterator();
		PropertyLine oldLine = (oldIterator.hasNext()) ? (PropertyLine) oldIterator.next() : null;
		PropertyLine newLine = (newIterator.hasNext()) ? (PropertyLine) newIterator.next() : null;
		while ((oldLine != null) && (newLine != null)) {
			// oldLine message id is either <, =, or > newLine message id.
			// if <, message id has been deleted.
			// if =, see if changed (or just compare message ids.)
			// if >, new line is a new message id.
			// to increment, increment only the < (whether it's oldLine or newLine).
			int compare = oldLine.getMessageId().compareTo(newLine.getMessageId());
			if (compare < 0) {
				// deleted
				_deleted.add(oldLine);
				if (oldIterator.hasNext()) {
					oldLine = (PropertyLine) oldIterator.next();
				}
				else {
					oldLine = null;
				}
			}
			else if (compare == 0) {
				// existed before. Check if changed.
				if (!oldLine.getMessage().equals(newLine.getMessage())) {
					// changed
					addChanged(oldLine, newLine);
				}
				if (oldIterator.hasNext() && newIterator.hasNext()) {
					oldLine = (PropertyLine) oldIterator.next();
					newLine = (PropertyLine) newIterator.next();
				}
				else {
					oldLine = null;
					newLine = null;
				}

			}
			else {
				// added
				_added.add(newLine);
				if (newIterator.hasNext()) {
					newLine = (PropertyLine) newIterator.next();
				}
				else {
					newLine = null;
				}
			}
		}

		if (oldLine != null) {
			_deleted.add(oldLine);
		}

		if (newLine != null) {
			_added.add(newLine);
		}

		while (oldIterator.hasNext()) {
			// all of the rest have been deleted
			_deleted.add(oldIterator.next());
		}

		while (newIterator.hasNext()) {
			// all of the rest have been added
			_added.add(newIterator.next());
		}
	}
	
	/**
	 * Return a Collction of PropertyLine instances that exist in
	 * the newer PropertyFile that aren't in the older PropertyFile.
	 */
	public Set getAdded() {
		return _added;
	}
	
	/**
	 * Return a Collection of CompareLine instances that represent
	 * the two PropertyLine instances; one from the older PropertyFile,
	 * and one from the newer PropertyFile.
	 */
	public Collection getChanged() {
		return _changed.values();
	}
	
	/**
	 * Return a Collection of PropertyLine instances that do not
	 * exist in the newer PropertyFile yet that exist in the older
	 * PropertyFile.
	 */
	public Set getDeleted() {
		return _deleted;
	}
	
	/**
	 * Print out all of the collections of variable strings.
	 */
	public void printResults() {
		// create an output log in the current directory, and in it list the strings in a section each.
		int numNew = printStrings("NEW PROPERTIES", _added); //$NON-NLS-1$
		
		int numWordsDeleted = printStrings("DELETED PROPERTIES", _deleted); //$NON-NLS-1$
		int numWordsChanged =  printStrings("CHANGED PROPERTIES", _changed.values()); //$NON-NLS-1$
		float totalChange = numNew + numWordsDeleted + numWordsChanged;
		float numWords = _newFile.getNumWords();
		float percent = totalChange / numWords * 100;
		
		System.out.println();
		System.out.println("Number of new words: " + numNew); //$NON-NLS-1$
		System.out.println("Number of words in deleted messages: " + numWordsDeleted); //$NON-NLS-1$
		System.out.println("Number of changed words in modified messages: " + numWordsChanged); //$NON-NLS-1$
		System.out.println("Number of words in file " + _newFile.getQualifiedFileName() + ": " + numWords); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println("Total change of words: " + totalChange + ", which is a " + percent + "% change."); //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	public void printResultsForTranslation() {
		// create an output log in the current directory, and in it list the strings in a section each.
		printStringsForTranslation("DELETED PROPERTIES", _deleted); //$NON-NLS-1$
		printStringsForTranslation("NEW PROPERTIES", _added); //$NON-NLS-1$
		printStringsForTranslation("CHANGED PROPERTIES", _changed.values()); //$NON-NLS-1$
	}
	
	/**
	 * Print the number of lines in the .properties file.
	 */
	private int printStrings(String header, Collection lines) {
		System.out.println();
		System.out.println(header);
		int count = 0;
		int numWords = 0;
		Iterator iterator = lines.iterator();
		while (iterator.hasNext()) {
			Object line = iterator.next();
			if(line instanceof PropertyLine) {
				numWords += ((PropertyLine)line).getNumWords();
			}
			else {
				// must be a compare line
				numWords += ((CompareLine)line).getNumWords();
			}
			
			count++;
			System.out.println(line);
		}
		System.out.println("Number of properties: " + count); //$NON-NLS-1$
		System.out.println();
		return numWords;
	}
	
	/**
	 * Print the contents of the sorted collection of lines from the .properties file.
	 */
	private void printStringsForTranslation(String header, Collection lines) {
		System.out.println();
		System.out.println(header);
		int count = 0;
		Iterator iterator = lines.iterator();
		while (iterator.hasNext()) {
			count++;
			Object line = iterator.next();
			if (line instanceof PropertyLine) {
				PropertyLine propline = (PropertyLine) line;
				System.out.println(propline.getMessageId());
			}
			else if (line instanceof CompareLine) {
				CompareLine propline = (CompareLine) line;
				System.out.println(propline.getMessageId());
			}
			else {
				System.out.println("instance of " + line.getClass().getName()); //$NON-NLS-1$
			}
		}
		System.out.println("Total: " + count); //$NON-NLS-1$
		System.out.println();
	}
}