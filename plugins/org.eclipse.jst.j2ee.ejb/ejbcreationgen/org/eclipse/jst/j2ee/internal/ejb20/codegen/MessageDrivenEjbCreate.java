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
import org.eclipse.jst.j2ee.internal.ejb.codegen.SessionBeanEjbCreate;


public class MessageDrivenEjbCreate extends SessionBeanEjbCreate {

	/**
	 * Constructor for MessageDrivenEjbCreate.
	 */
	public MessageDrivenEjbCreate() {
		super();
	}

	/*
	 * @see JavaMethodGenerator#getExceptions()
	 */
	protected String[] getExceptions() throws GenerationException {
		return null;
	}

}