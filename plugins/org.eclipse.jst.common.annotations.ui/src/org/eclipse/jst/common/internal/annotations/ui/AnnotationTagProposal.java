/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 4, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.common.internal.annotations.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension2;
import org.eclipse.jst.common.internal.annotations.registry.AttributeValueProposalHelper;
import org.eclipse.swt.graphics.Image;


/**
 * @author kelleyp
 * 
 * A completion proposal especially for Annotation tag completions. This problem this class was
 * created to solve was the problem of attaching help text to a proposal. The mechanism inside of
 * JavaCompletionProposal was useless to us, since it was tied to the idea that the proposal would
 * be for an actual java element, that has javadoc attached to it, etc... So here we subclass
 * JavaCompletionProposal and override <code>getAdditionalProposalInfo</code> for a more suitable
 * way of associating help text with a proposal.
 */
public class AnnotationTagProposal extends AbstractAnnotationTagProposal implements IJavaCompletionProposal, ICompletionProposalExtension, ICompletionProposalExtension2 {
	private static final char QUOTE = '"';
	private boolean ensureBeginQuote = false;
	private boolean ensureEndQuote = false;
	private String localString;
	//String used to validate the prefix.
	private String fValidationString;

	/**
	 * Localized help text.
	 */
	private String locText;

	/**
	 * 
	 * @param replacementString
	 * @param replacementOffset
	 * @param replacementLength
	 * @param image
	 * @param displayString
	 * @param relevance
	 */
	public AnnotationTagProposal(String replacementString, int replacementOffset, int replacementLength, Image image, String displayString, int relevance) {
		Assert.isNotNull(replacementString);
		Assert.isTrue(replacementOffset >= 0);
		Assert.isTrue(replacementLength >= 0);

		setReplacementString(replacementString);
		setReplacementOffset(replacementOffset);
		setReplacementLength(replacementLength);
		setImage(image);
		setDisplayString(displayString == null ? replacementString : displayString);
		setRelevance(relevance);
		setCursorPosition(replacementString.length());
		setSortString(displayString == null ? replacementString : displayString);
		this.localString = replacementString;
	}

	/**
	 * @see org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal#JavaCompletionProposal(java.lang.String, int, int,
	 *      org.eclipse.swt.graphics.Image, java.lang.String, int,
	 *      org.eclipse.jface.text.ITextViewer)
	 * @param replacementString
	 * @param replacementOffset
	 * @param replacementLength
	 * @param image
	 * @param displayString
	 * @param relevance
	 */

	public AnnotationTagProposal(AttributeValueProposalHelper proposalHelper) {
		this(proposalHelper.getReplacementString(), proposalHelper.getValueOffset(), proposalHelper.getReplacementLength(), null, proposalHelper.getValueDisplayString(), 90);
		if (proposalHelper instanceof UIAttributeValueProposalHelper)
			setImage(((UIAttributeValueProposalHelper) proposalHelper).getImage());
		setEnsureBeginQuote(proposalHelper.ensureBeginQuote());
		setEnsureEndQuote(proposalHelper.ensureEndQuote());
	}

	public AnnotationTagProposal(UIAttributeValueProposalHelper proposalHelper) {
		this(proposalHelper.getReplacementString(), proposalHelper.getValueOffset(), proposalHelper.getReplacementLength(), proposalHelper.getImage(), proposalHelper.getValueDisplayString(), 90);
		setEnsureBeginQuote(proposalHelper.ensureBeginQuote());
		setEnsureEndQuote(proposalHelper.ensureEndQuote());
	}

	/**
	 * Our override that uses <code>textHolder</code> to provide the help text.
	 */
	@Override
	public String getAdditionalProposalInfo() {
		return locText;
	}

	/**
	 * Sets the holder of the help text that can be displayed with this proposal.
	 * 
	 * @param hld
	 *            an LocalizedTextContainer
	 */
	public void setHelpText(String s) {
		locText = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal#apply(org.eclipse.jface.text.IDocument,
	 *      char, int)
	 */
	@Override
	public void apply(IDocument document, char trigger, int offset) {
		ensureQuotedIfNecessary(document, offset);
		super.apply(document, trigger, offset);
	}

	/**
	 * @param document
	 * @param offset
	 */
	private void ensureQuotedIfNecessary(IDocument document, int offset) {
		if (ensureBeginQuote || ensureEndQuote) {
			try {
				char begin = document.getChar(getReplacementOffset() - 1);
				char end = document.getChar(offset);
				if (ensureBeginQuote && ensureEndQuote && begin != QUOTE && end != QUOTE) {
					StringBuffer b = new StringBuffer();
					b.append(QUOTE).append(localString).append(QUOTE);
					localString = b.toString();
				} else if (ensureBeginQuote && begin != QUOTE)
					localString = QUOTE + localString;
				else if (ensureEndQuote && end != QUOTE)
					localString = localString + QUOTE;
				setReplacementString(localString);
				setCursorPosition(localString.length());
			} catch (BadLocationException e) {
				// Do nothing
			}
		}
	}

	public void setEnsureQuoted(boolean ensureQuoted) {
		setEnsureBeginQuote(ensureQuoted);
		setEnsureEndQuote(ensureQuoted);
	}

	//public void setPartialValueString(String partialValueString) {
	//	this.partialValueString = partialValueString;
	//}
	public void setEnsureBeginQuote(boolean ensureBeginQuote) {
		this.ensureBeginQuote = ensureBeginQuote;
	}

	public void setEnsureEndQuote(boolean ensureEndQuote) {
		this.ensureEndQuote = ensureEndQuote;
	}
	
	@Override
	protected boolean isValidPrefix(String prefix) {
		if (getReplacementString().startsWith(getDisplayString())) {
			return super.isValidPrefix(prefix);
		}
		return super.isPrefix(trim(prefix), getValidationString());
	}
	
	private String getValidationString() {
		if (fValidationString == null) {
			fValidationString = trim(getReplacementString());
		}
		return fValidationString;
	}

	
	/*
	 * It is possible that the replacement string is complex and larger than the prefix.
	 * In this case we want to trim the prefix to the last whitespace character.  This
	 * will provide us with a prefix to use when matching the display string.
	 */
	private String trim(String aString) {
		if (aString == null || aString.length() == 0)
			return aString;
		StringBuffer buffer = new StringBuffer();
		char[] chars = aString.toCharArray();
		for (int i = chars.length - 1; i > -1; i--) {
			if (Character.isWhitespace(chars[i])) {
				break;
			}
			buffer.append(chars[i]);
		}
		buffer = buffer.reverse();
		return buffer.toString();
	}
}