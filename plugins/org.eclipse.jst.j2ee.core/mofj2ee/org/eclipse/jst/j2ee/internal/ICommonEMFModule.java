/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

/**
 * Used to introduce EMF model concepts on both Java EE model implementations - separated from clean Module interface
 *
 */
public interface ICommonEMFModule {

	/**
	 * Sets the string fragment used to identify this object - must be unique within the document
	 */
	public void setId(String frag);

	/**
	 * Gets the id used to identify this object
	 */
	public String getId();

}
