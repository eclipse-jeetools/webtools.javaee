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



import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaConstructorGenerator;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityKeyDefaultCtor extends JavaConstructorGenerator {
	/**
	 * EntityKeyDefaultCtor constructor comment.
	 */
	public EntityKeyDefaultCtor() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Creates an empty key for Entity Bean: " + ((Entity) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}
}