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
package org.eclipse.jst.common.internal.annotations.ui;

import org.eclipse.jst.common.internal.annotations.registry.AttributeValueProposalHelper;
import org.eclipse.swt.graphics.Image;


/**
 * @author DABERG
 *  
 */
public class UIAttributeValueProposalHelper extends AttributeValueProposalHelper {
	private Image image;

	/**
	 * @param replacementString
	 * @param valueOffset
	 * @param replacementLength
	 * @param valueDisplayString
	 */
	public UIAttributeValueProposalHelper(String replacementString, int valueOffset, int replacementLength, String valueDisplayString) {
		super(replacementString, valueOffset, replacementLength, valueDisplayString);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}