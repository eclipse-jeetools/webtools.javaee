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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;





public class MessageDrivenOnMessage extends JavaMethodGenerator {

	/**
	 * Constructor for MessageDrivenOnMessage.
	 */
	public MessageDrivenOnMessage() {
		super();
	}

	/*
	 * @see BaseGenerator#getName()
	 */
	protected String getName() throws GenerationException {
		return "onMessage"; //$NON-NLS$ //$NON-NLS-1$
	}

	/*
	 * @see JavaMethodGenerator#getParameterDescriptors()
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] descriptors = new JavaParameterDescriptor[1];
		JavaParameterDescriptor desc = new JavaParameterDescriptor();
		desc.setName("msg"); //$NON-NLS-1$
		desc.setType(IEJB20GenConstants.MESSAGE_INTERFACE_NAME);
		descriptors[0] = desc;
		return descriptors;
	}

}