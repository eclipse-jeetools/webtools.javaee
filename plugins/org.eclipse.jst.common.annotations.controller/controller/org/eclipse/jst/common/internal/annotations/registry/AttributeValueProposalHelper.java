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
package org.eclipse.jst.common.internal.annotations.registry;

/**
 * @author DABERG
 *  
 */
public class AttributeValueProposalHelper {
	private String replacementString;
	private int valueOffset = 0;
	private int replacementLength = 0;
	private String valueDisplayString;
	private boolean ensureBeginQuote = true;
	private boolean ensureEndQuote = true;

	public AttributeValueProposalHelper(String replacementString, int valueOffset, int replacementLength, String valueDisplayString) {
		this.replacementString = replacementString;
		this.valueOffset = valueOffset;
		this.replacementLength = replacementLength;
		this.valueDisplayString = valueDisplayString;
	}

	public int getReplacementLength() {
		return replacementLength;
	}

	public void setReplacementLength(int replacementLength) {
		this.replacementLength = replacementLength;
	}

	public String getReplacementString() {
		return replacementString;
	}

	public void setReplacementString(String replacementString) {
		this.replacementString = replacementString;
	}

	public String getValueDisplayString() {
		return valueDisplayString;
	}

	public void setValueDisplayString(String valueDisplayString) {
		this.valueDisplayString = valueDisplayString;
	}

	public int getValueOffset() {
		return valueOffset;
	}

	public void setValueOffset(int valueOffset) {
		this.valueOffset = valueOffset;
	}

	public boolean ensureBeginQuote() {
		return ensureBeginQuote;
	}

	public void setEnsureBeginQuote(boolean ensureBeginQuote) {
		this.ensureBeginQuote = ensureBeginQuote;
	}

	public boolean ensureEndQuote() {
		return ensureEndQuote;
	}

	public void setEnsureEndQuote(boolean ensureEndQuote) {
		this.ensureEndQuote = ensureEndQuote;
	}
}