/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import org.eclipse.emf.ecore.EObject;

public interface IWebFragmentResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the web fragment root
	 */
	public abstract WebFragment getWebFragment();

}