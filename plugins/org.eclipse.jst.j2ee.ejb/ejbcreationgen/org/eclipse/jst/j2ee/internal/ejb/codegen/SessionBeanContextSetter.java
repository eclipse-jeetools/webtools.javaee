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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;






/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class SessionBeanContextSetter extends SessionBeanEjbMethod {
	/**
	 * SessionBeanContextSetter constructor comment.
	 */
	public SessionBeanContextSetter() {
		super();
	}

	/**
	 * Return the session context.
	 */
	protected String getBody() throws GenerationException {
		return "mySessionCtx = ctx;\n";//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return new String[]{IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return super.getExceptions();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "setSessionContext";//$NON-NLS-1$
	}

	/**
	 * The new context is the parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName("ctx");//$NON-NLS-1$
		parms[0].setType(IEJBGenConstants.SESSION_CONTEXT_NAME);
		return parms;
	}
}