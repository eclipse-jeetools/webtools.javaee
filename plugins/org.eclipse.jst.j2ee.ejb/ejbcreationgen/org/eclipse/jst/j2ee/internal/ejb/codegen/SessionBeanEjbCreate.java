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
package org.eclipse.jst.j2ee.internal.ejb.codegen;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;




/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class SessionBeanEjbCreate extends SessionBeanEjbMethod {
	/**
	 * SessionBeanEjbCreate constructor comment.
	 */
	public SessionBeanEjbCreate() {
		super();
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME, IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "ejbCreate";//$NON-NLS-1$
	}
}