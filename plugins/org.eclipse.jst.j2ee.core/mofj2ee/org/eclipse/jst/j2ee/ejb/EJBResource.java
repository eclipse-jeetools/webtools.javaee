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
/*
 * Created on Mar 27, 2003
 *
 */
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.jst.j2ee.internal.common.XMLResource;

/**
 * @author schacher
 */
public interface EJBResource extends XMLResource {
	EJBJar getEJBJar();
	/**
	 * Return true if this Resource supports the EJB 1.1 spec.
	 */
	boolean isEJB1_1();
	/**
	 * Return true if this Resource supports the EJB 2.0 spec.
	 */
	boolean isEJB2_0();
	/**
	 * Sets the batchMode.
	 * @param batchMode The batchMode to set
	 */
	public void setBatchMode(boolean isBatch);
	/**
	 * Gets the batchmode - from the renderer
	 */
	public boolean isBatchMode();

}
