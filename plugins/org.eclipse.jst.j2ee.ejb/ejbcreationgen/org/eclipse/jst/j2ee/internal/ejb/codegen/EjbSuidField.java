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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaFieldGenerator;






/**
 * Insert the type's description here. Creation date: (10/11/00 11:37:42 AM)
 * 
 * @author: Steve Wasleski
 */
public class EjbSuidField extends JavaFieldGenerator {
	/**
	 * EjbSuidField constructor comment.
	 */
	public EjbSuidField() {
		super();
	}

	/**
	 * Override to make default public.
	 */
	protected int deriveFlags() throws GenerationException {
		return org.eclipse.jdt.internal.compiler.env.IConstants.AccFinal | org.eclipse.jdt.internal.compiler.env.IConstants.AccStatic;
	}

	/**
	 * We use a fixed suid.
	 */
	protected String getInitializer() throws GenerationException {
		return "3206093459760846163L";//$NON-NLS-1$
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "serialVersionUID";//$NON-NLS-1$
	}

	/**
	 * getType method comment.
	 */
	protected String getType() throws GenerationException {
		return "long";//$NON-NLS-1$
	}
}