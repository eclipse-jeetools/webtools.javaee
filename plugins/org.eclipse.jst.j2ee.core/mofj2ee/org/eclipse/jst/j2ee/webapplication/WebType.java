/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.jst.j2ee.common.J2EEEObject;
/**
 * @generated
 * @since 1.0
 */
public interface WebType extends J2EEEObject {

	/**
	 * Returns true if the type denotes a JSP
	 * @return boolean value
	 */
	public boolean isJspType();
	
	/**
	 * Returns true if the type denotes a servlet
	 * @return boolean value
	 */
	public boolean isServletType();
}














