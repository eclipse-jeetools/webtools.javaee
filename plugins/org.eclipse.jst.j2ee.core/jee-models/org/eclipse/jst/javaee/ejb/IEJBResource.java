/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import org.eclipse.emf.ecore.EObject;

public interface IEJBResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the jar
	 */
	public abstract EJBJar getEjbJar();

}