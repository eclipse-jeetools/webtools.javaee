/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ManifestLineValidator extends Object {
	public static final int MAX_LINE_LENGTH = 72;
	protected int lineNumber;
	protected int currentByte;
	protected int byteCount;
	protected InputStream inputStream;
	protected List lineNumbers;
	protected boolean endsWithLineBreak = true;

	/**
	 * Constructor for LineValidator.
	 */
	public ManifestLineValidator(InputStream in) {
		super();
		inputStream = new BufferedInputStream(in);
		lineNumber = 0;
	}

	protected List getLineNumbers() {
		if (lineNumbers == null)
			lineNumbers = new ArrayList();
		return lineNumbers;
	}

	public int[] getLineNumbersExceedingLimit() {
		if (lineNumbers == null)
			return new int[0];

		int[] result = new int[lineNumbers.size()];
		for (int i = 0; i < lineNumbers.size(); i++) {
			Integer list_element = (Integer) lineNumbers.get(i);
			result[i] = list_element.intValue();
		}
		return result;
	}

	public void validate() throws IOException {
		readNext();
		while (currentByte != -1) {
			validateNextLine();
		}
	}

	public int getLineCount() {
		return lineNumber;
	}

	protected void readNext() throws IOException {
		currentByte = inputStream.read();
	}

	protected void validateNextLine() throws IOException {
		lineNumber++;
		byteCount = 0;
		boolean eolReached = false;
		while (currentByte != -1) {
			if (currentByte == '\r') {
				eolReached = true;
				readNext();
				if (currentByte == '\n') {
					readNext();
				}
				break;
			} else if (currentByte == '\n') {
				eolReached = true;
				readNext();
				break;
			} else {
				byteCount++;
				readNext();
			}
		}
		if (currentByte == -1 && !eolReached && byteCount > 0)
			endsWithLineBreak = false;
		if (byteCount > 72)
			getLineNumbers().add(new Integer(lineNumber));
	}

	public boolean endsWithLineBreak() {
		return endsWithLineBreak;
	}

	public boolean hasErrors() {
		return !endsWithLineBreak || (lineNumbers != null && !lineNumbers.isEmpty());
	}
}
