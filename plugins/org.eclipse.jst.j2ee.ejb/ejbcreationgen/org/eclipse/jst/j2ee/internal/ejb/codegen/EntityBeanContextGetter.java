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
public class EntityBeanContextGetter extends EntityBeanEjbMethod {
	/**
	 * EntityBeanContextGetter constructor comment.
	 */
	public EntityBeanContextGetter() {
		super();
	}

	/**
	 * Return the session context.
	 */
	protected String getBody() throws GenerationException {
		return "return myEntityCtx;\n";//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		return null;
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "getEntityContext";//$NON-NLS-1$
	}

	/**
	 * getReturnType method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return IEJBGenConstants.ENTITY_CONTEXT_NAME;
	}
}