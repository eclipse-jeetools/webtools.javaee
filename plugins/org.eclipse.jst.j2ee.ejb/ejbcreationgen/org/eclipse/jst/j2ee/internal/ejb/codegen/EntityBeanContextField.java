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
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:37:42 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityBeanContextField extends EnterpriseBeanContextField {
	/**
	 * EntityBeanContextField constructor comment.
	 */
	public EntityBeanContextField() {
		super();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "myEntityCtx";//$NON-NLS-1$
	}

	/**
	 * getType method comment.
	 */
	protected String getType() throws GenerationException {
		return IEJBGenConstants.ENTITY_CONTEXT_NAME;
	}

	protected boolean shouldDelete() {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		return super.shouldDelete() || helper.getSupertype() != null;
	}
}