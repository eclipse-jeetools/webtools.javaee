/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;



import java.util.Iterator;
import java.util.List;


/**
 * This implementation of the interface wraps a StringBuffer.
 * <p>
 * Need preferences tie-ins for some of this.
 */
public class GenerationBuffer implements IGenerationBuffer {
	private StringBuffer fBuffer = null;
	private StringBuffer fCurrentMargin = null;
	private String fIndentString = IBaseGenConstants.DEFAULT_INDENT;
	private int fIndentCount = 0;
	private char fFormatParmMarker = IBaseGenConstants.DEFAULT_FORMAT_PARM_MARKER;

	/**
	 * GenerationBuffer default constructor.
	 */
	public GenerationBuffer() {
		super();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void append(String[] strings) {
		if ((strings != null) && (strings.length > 0)) {
			for (int i = 0; i < strings.length; i++) {
				append(strings[i]);
				if (i < (strings.length - 1))
					append(IBaseGenConstants.COMMA_SEPARATOR);
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void append(String aString) {
		if ((aString != null) && (aString.length() > 0))
			getBuffer().append(aString);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void append(List strings) {
		if (strings != null) {
			Iterator stringsIter = strings.iterator();
			while (stringsIter.hasNext()) {
				append(stringsIter.next().toString());
				if (stringsIter.hasNext())
					append(IBaseGenConstants.COMMA_SEPARATOR);
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void appendWithMargin(String aString) {
		appendWithMargin(aString, true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void appendWithMargin(String aString, boolean startMargin) {
		if ((aString != null) && (aString.length() > 0)) {
			StringBuffer buffer = getBuffer();
			buffer.ensureCapacity(buffer.length() + aString.length() + 50);
			if (startMargin)
				margin();

			int sourceLength = aString.length();
			char current;
			boolean inLineBreak = false;
			for (int i = 0; i < sourceLength; i++) {
				current = aString.charAt(i);
				//			if (Character.getType(current) == Character.LINE_SEPARATOR) { This should work,
				// but getType is broken.
				if ((current == '\r') || (current == '\n')) {
					inLineBreak = true;
				} else {
					if (inLineBreak) {
						margin();
						inLineBreak = false;
					}
				}
				buffer.append(current);
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void appendWithMarginAndPrefix(String aString, String prefix) {
		appendWithMarginAndPrefix(aString, prefix, true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void appendWithMarginAndPrefix(String aString, String prefix, boolean startMargin) {
		if ((aString != null) && (aString.length() > 0)) {
			StringBuffer buffer = getBuffer();
			buffer.ensureCapacity(buffer.length() + aString.length() + 50);
			if (startMargin) {
				margin();
				append(prefix);
			}

			int sourceLength = aString.length();
			char current;
			boolean inLineBreak = false;
			for (int i = 0; i < sourceLength; i++) {
				current = aString.charAt(i);
				//			if (Character.getType(current) == Character.LINE_SEPARATOR) { This should work,
				// but getType is broken.
				if ((current == '\r') || (current == '\n')) {
					inLineBreak = true;
				} else {
					if (inLineBreak) {
						margin();
						append(prefix);
						inLineBreak = false;
					}
				}
				buffer.append(current);
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void format(String format, String[] parms) {
		if ((format != null) && (format.length() > 0)) {
			StringBuffer buffer = getBuffer();
			// A fast guess to avoid lots of reallocation.
			buffer.ensureCapacity(buffer.length() + format.length() + (20 * parms.length));
			int formatLen = format.length();
			int formatPos = 0;
			char parmMarker = getFormatParmMarker();
			char formatChar;
			boolean inParm = false;
			StringBuffer parmNumber = new StringBuffer();
			while (formatPos < formatLen) {
				formatChar = format.charAt(formatPos);
				formatPos++;
				// If we are in a parm...
				if (inParm) {
					// If no number yet and the character is not a digit, print the parm marker and
					// print this character if it is not a parm marker.
					// Also we are no longer "inParm".
					if ((parmNumber.length() == 0) && (!Character.isDigit(formatChar))) {
						inParm = false;
						buffer.append(parmMarker);
						if (formatChar != parmMarker)
							buffer.append(formatChar);
					} else {
						// If it is a digit, add it to the number buffer.
						if (Character.isDigit(formatChar)) {
							parmNumber.append(formatChar);
							// If this was the last character, process the parm.
							if (formatPos == formatLen)
								append(parms[Integer.parseInt(parmNumber.toString())]);
						} else {
							// The number is done, get the parm and append it.
							append(parms[Integer.parseInt(parmNumber.toString())]);
							parmNumber.setLength(0);
							inParm = false;
							// Also, we need to consider the current character over again.
							formatPos--;
						}
					}
				} else {
					// If it is a parm marker and not the last character, go into parm. Otherwise,
					// just append it.
					if ((formatChar == parmMarker) && (formatPos < formatLen))
						inParm = true;
					else
						buffer.append(formatChar);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void format(String format, List parms) {
		String[] stringParms = new String[parms.size()];
		Iterator parmsIter = parms.iterator();
		int i = 0;
		while (parmsIter.hasNext()) {
			stringParms[i] = parmsIter.next().toString();
			i++;
		}
		format(format, stringParms);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void formatWithMargin(String format, String[] parms) {
		formatWithMargin(format, parms, true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void formatWithMargin(String format, String[] parms, boolean startMargin) {
		if ((format != null) && (format.length() > 0)) {
			StringBuffer buffer = getBuffer();
			// A fast guess to avoid lots of reallocation.
			buffer.ensureCapacity(buffer.length() + format.length() + (20 * parms.length));
			if (startMargin)
				margin();

			int formatLen = format.length();
			int formatPos = 0;
			char parmMarker = getFormatParmMarker();
			char formatChar;
			boolean inParm = false;
			StringBuffer parmNumber = new StringBuffer();
			boolean inLineBreak = false;
			while (formatPos < formatLen) {
				formatChar = format.charAt(formatPos);
				formatPos++;

				// Check for line breaks to add margins.
				//			if (Character.getType(formatChar) == Character.LINE_SEPARATOR) { This should
				// work, but getType is broken.
				if ((formatChar == '\r') || (formatChar == '\n')) {
					inLineBreak = true;
				} else {
					if (inLineBreak) {
						margin();
						inLineBreak = false;
					}
				}

				// If we are in a parm...
				if (inParm) {
					// If no number yet and the character is not a digit, print the parm marker and
					// print this character if it is not a parm marker.
					// Also we are no longer "inParm".
					if ((parmNumber.length() == 0) && (!Character.isDigit(formatChar))) {
						inParm = false;
						buffer.append(parmMarker);
						if (formatChar != parmMarker)
							buffer.append(formatChar);
					} else {
						// If it is a digit, add it to the number buffer.
						if (Character.isDigit(formatChar)) {
							parmNumber.append(formatChar);
							// If this was the last character, process the parm.
							if (formatPos == formatLen)
								append(parms[Integer.parseInt(parmNumber.toString())]);
						} else {
							// The number is done, get the parm and append it.
							appendWithMargin(parms[Integer.parseInt(parmNumber.toString())], false);
							parmNumber.setLength(0);
							inParm = false;
							// Also, we need to consider the current character over again.
							formatPos--;
						}
					}
				} else {
					// If it is a parm marker and not the last character, go into parm. Otherwise,
					// just append it.
					if ((formatChar == parmMarker) && (formatPos < formatLen))
						inParm = true;
					else
						buffer.append(formatChar);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void formatWithMargin(String format, List parms) {
		formatWithMargin(format, parms, true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void formatWithMargin(String format, List parms, boolean startMargin) {
		String[] stringParms = new String[parms.size()];
		Iterator parmsIter = parms.iterator();
		int i = 0;
		while (parmsIter.hasNext()) {
			stringParms[i] = parmsIter.next().toString();
			i++;
		}
		formatWithMargin(format, stringParms, startMargin);
	}

	/**
	 * Returns the buffer.
	 */
	protected StringBuffer getBuffer() {
		if (fBuffer == null)
			fBuffer = new StringBuffer(200);
		return fBuffer;
	}

	/**
	 * Returns the current margin string based on fIndentCount and fIndentString.
	 */
	protected StringBuffer getCurrentMargin() {
		if (fCurrentMargin == null) {
			fCurrentMargin = new StringBuffer();
			for (int i = 0; i < fIndentCount; i++)
				fCurrentMargin.append(fIndentString);
		}
		return fCurrentMargin;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public char getFormatParmMarker() {
		return fFormatParmMarker;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void indent() {
		getCurrentMargin().append(fIndentString);
		fIndentCount++;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void margin() {
		// The tests are not strictly needed, but using them will reduce String
		// related garbage problems.
		if (fIndentCount > 0) {
			if (fIndentCount == 1)
				append(fIndentString);
			else
				append(getCurrentMargin().toString());
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void setFormatParmMarker(char newFormatParmMarker) {
		fFormatParmMarker = newFormatParmMarker;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void setIndent(int indentCount) {
		int setCount = (indentCount > 0) ? indentCount : 0;
		if (setCount != fIndentCount) {
			fIndentCount = setCount;
			fCurrentMargin = null;
		}
	}

	/**
	 * The buffer contents is the code String.
	 */
	public String toString() {
		return getBuffer().toString();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	public void unindent() {
		if (fIndentCount > 0) {
			fIndentCount--;
			fCurrentMargin = null;
		}
	}
}