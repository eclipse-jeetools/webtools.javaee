/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.adapters.jdom;
/*
 *  $RCSfile: JavaModelListener.java,v $
 *  $Revision: 1.9 $  $Date: 2006/02/06 23:37:59 $ 
 */


/**
 * Insert the type's description here.
 * Creation date: (10/31/2000 1:13:12 PM)
 * @author: Administrator
 */
public abstract class JavaModelListener extends org.eclipse.jem.workbench.utility.JavaModelListener {

	/**
	 * 
	 * 
	 * @since 1.2.0
	 */
	public JavaModelListener() {
		super();
	}

	/**
	 * @param eventsToListen
	 * 
	 * @since 1.2.0
	 */
	public JavaModelListener(int eventsToListen) {
		super(eventsToListen);
	}
	
}
