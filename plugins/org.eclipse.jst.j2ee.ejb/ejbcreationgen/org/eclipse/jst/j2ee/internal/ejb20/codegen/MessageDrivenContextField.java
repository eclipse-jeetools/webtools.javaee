/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.SessionBeanContextField;


public class MessageDrivenContextField extends SessionBeanContextField {

	/**
	 * Constructor for MessageDrivenContextField.
	 */
	public MessageDrivenContextField() {
		super();
	}

	/*
	 * @see BaseGenerator#getName()
	 */
	protected String getName() throws GenerationException {
		return "fMessageDrivenCtx"; //$NON-NLS-1$
	}

	/*
	 * @see JavaFieldGenerator#getType()
	 */
	protected String getType() throws GenerationException {
		return IEJB20GenConstants.MESSAGE_DRIVEN_CONTEXT_TYPE_NAME;
	}

}