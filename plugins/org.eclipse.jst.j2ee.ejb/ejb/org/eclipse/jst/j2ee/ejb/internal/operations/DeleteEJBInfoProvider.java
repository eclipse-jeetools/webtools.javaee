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
package org.eclipse.jst.j2ee.ejb.internal.operations;


/**
 * Insert the type's description here. Creation date: (9/7/2001 4:37:35 PM)
 * 
 * @author: Administrator
 */
public interface DeleteEJBInfoProvider {
	//TODO - These need to be extensions to an operation model
	boolean isAccessBeanDelete();

	boolean isBeanClassesDelete();

	boolean isBeanDelete();

	boolean isBeanDeployDelete();
}