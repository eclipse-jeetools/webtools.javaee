/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;

import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;



/**
 * Implementors of this interface answer one of the prescribed return codes when asked whether to
 * overwrite a certain path string (which could represent a resource path, a file system path, etc)
 */
public interface IOverwriteQuery {
	public static final String CANCEL = EJBCreationResourceHandler.CANCEL_UI_; 
	public static final String NO = EJBCreationResourceHandler.NO_UI_; 
	public static final String YES = EJBCreationResourceHandler.YES_UI_; 
	public static final String ALL = EJBCreationResourceHandler.ALL_UI_; 

	/**
	 * Answer one of the interface's prescribed responses indicating whether the entity represented
	 * by the passed String should be overwritten
	 * 
	 * @return String
	 * @param pathString
	 *            java.lang.String
	 */
	String queryOverwrite(String pathString);
}
