/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.annotations.core;

import java.util.HashMap;
import java.util.Map;



/**
 * @author mdelder
 *  
 */
public class AnnotatedCommentHandler implements TagParseEventHandler {

	private Map annotations;

	private Token annotationToken;

	/**
	 *  
	 */
	public AnnotatedCommentHandler() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.annotations.core.TagParseEventHandler#annotationTag(org.eclipse.wst.common.internal.annotations.core.Token)
	 */
	@Override
	public void annotationTag(Token tag) {
		this.annotationToken = tag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.annotations.core.TagParseEventHandler#endOfTag(int)
	 */
	@Override
	public void endOfTag(int pos) {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.annotations.core.TagParseEventHandler#attribute(org.eclipse.wst.common.internal.annotations.core.Token,
	 *      int, org.eclipse.wst.common.internal.annotations.core.Token)
	 */
	@Override
	public void attribute(Token name, int equalsPosition, Token value) {
		if (value.getText() == null || value.getText().length() == 0)
			getAnnotations().put(this.annotationToken.getText(), name.getText());
		else
			getAnnotations().put(name.getText(), value.getText());
	}

	/**
	 * @return Returns the annotations.
	 */
	public Map getAnnotations() {
		if (annotations == null)
			annotations = new HashMap();
		return annotations;
	}
}