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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;


/**
 * Insert the type's description here. Creation date: (9/18/2001 12:01:19 PM)
 * 
 * @author: Administrator
 */
public class EntityLocalHomeFindByPrimaryKey extends org.eclipse.jst.j2ee.internal.ejb.codegen.EntityHomeFindByPrimaryKey {
	/**
	 * CMP20EntityHomeFindByPrimaryKey constructor comment.
	 */
	public EntityLocalHomeFindByPrimaryKey() {
		super();
	}

	/**
	 * getReturnType method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return ((Entity) getSourceElement()).getLocalInterfaceName();
	}

	protected String[] getExceptions() throws GenerationException {
		return new String[]{IEJBGenConstants.FINDER_EXCEPTION_NAME};
	}
}