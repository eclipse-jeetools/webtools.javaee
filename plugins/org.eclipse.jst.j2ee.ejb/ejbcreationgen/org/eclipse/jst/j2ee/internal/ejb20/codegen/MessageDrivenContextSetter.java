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
import org.eclipse.jst.j2ee.internal.ejb.codegen.SessionBeanContextSetter;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


public class MessageDrivenContextSetter extends SessionBeanContextSetter {

	/**
	 * Constructor for MessageDrivenContextSetter.
	 */
	public MessageDrivenContextSetter() {
		super();
	}

	protected String getBody() throws GenerationException {
		return "fMessageDrivenCtx = ctx;\n"; //$NON-NLS-1$
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "setMessageDrivenContext"; //$NON-NLS-1$
	}

	/**
	 * The new context is the parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName("ctx"); //$NON-NLS-1$
		parms[0].setType(IEJB20GenConstants.MESSAGE_DRIVEN_CONTEXT_TYPE_NAME);
		return parms;
	}

	protected String[] getExceptions() throws GenerationException {
		return null;
	}

}