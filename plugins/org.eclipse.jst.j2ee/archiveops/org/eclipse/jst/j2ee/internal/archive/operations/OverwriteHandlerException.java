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
package org.eclipse.jst.j2ee.internal.archive.operations;



/**
 * Insert the type's description here. Creation date: (8/16/2001 6:07:35 PM)
 * 
 * @author: Administrator
 */
public class OverwriteHandlerException extends RuntimeException {
    /**
     * Warning clean-up 12/05/2005
     */   
	private static final long serialVersionUID = 1651836694391641930L;

	/**
	 * OverwriteHandlerException constructor comment.
	 */
	public OverwriteHandlerException() {
		super();
	}

	/**
	 * OverwriteHandlerException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public OverwriteHandlerException(String s) {
		super(s);
	}
}