/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.validation;

/**
 */
public abstract class ASessionHomeVRule extends AHomeVRule {
	/**
	 * If the type of the method isn't listed in the KNOWN_METHODS, what type of method is it
	 * considered to be?
	 */
	public long getDefaultMethodType() {
		// If it's not a create method, then it shouldn't be there.
		return EXCLUDED_METHOD;
	}
}
