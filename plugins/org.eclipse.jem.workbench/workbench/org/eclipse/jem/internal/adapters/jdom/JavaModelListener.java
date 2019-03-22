/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.adapters.jdom;
/*


 */


/**
 * Insert the type's description here.
 * Creation date: (10/31/2000 1:13:12 PM)
 * @author: Administrator
 * @deprecated Use {@link org.eclipse.jem.workbench.utility.JavaModelListener} instead.
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
