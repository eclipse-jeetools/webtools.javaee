/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.deploy;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class FatalDeployerException extends Exception {
    /**
     * Warning clean-up 12/05/2005
     */   
	private static final long serialVersionUID = -7991505439979201757L;

	/**
	 *  
	 */
	public FatalDeployerException() {
		super();
	}

	/**
	 * @param message
	 */
	public FatalDeployerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FatalDeployerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public FatalDeployerException(Throwable cause) {
		super(cause);
	}
}
