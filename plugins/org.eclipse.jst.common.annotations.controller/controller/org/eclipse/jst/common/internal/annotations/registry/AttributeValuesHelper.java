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
/*
 * Created on Jul 1, 2004
 */
package org.eclipse.jst.common.internal.annotations.registry;

import org.eclipse.jdt.core.IJavaElement;

/**
 * @author jlanuti
 */
public interface AttributeValuesHelper {
	static final String[] EMPTY_VALUES = new String[0];
	static final AttributeValueProposalHelper[] EMPTY_PROPOSAL_HELPERS = new AttributeValueProposalHelper[0];

	/**
	 * Return a simple String array containing the valid values for the given
	 * {@link TagAttributeSpec}and {@link IJavaElement}.
	 * 
	 * @param tas
	 * @param javaElement
	 * @return
	 */
	public String[] getValidValues(TagAttribSpec tas, IJavaElement javaElement);

	/**
	 * This is a more advanced api for returning valid values for a given {@link TagAttribSpec}.
	 * This api provides you with more flexibility to control the replacement string that is used
	 * for the completion.
	 * 
	 * @param tas
	 * @param partialValue
	 * @param valueOffset
	 * @param javaElement
	 * @return
	 */
	public AttributeValueProposalHelper[] getAttributeValueProposalHelpers(TagAttribSpec tas, String partialValue, int valueOffset, IJavaElement javaElement);

}