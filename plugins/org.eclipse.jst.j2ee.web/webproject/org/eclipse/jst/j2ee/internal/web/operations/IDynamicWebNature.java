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
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.resources.IContainer;
import org.eclipse.wst.web.internal.operation.IStaticWebNature;


/**
 * Insert the type's description here. Creation date: (10/16/2001 1:40:37 PM)
 * 
 * @author: Administrator
 */
public interface IDynamicWebNature extends IStaticWebNature {

	/**
	 * Insert the method's description here. Creation date: (10/31/2001 2:26:39 PM)
	 * 
	 * @return org.eclipse.core.resources.IContainer
	 */
	IContainer getCSSFolder();


	/**
	 * Insert the method's description here. Creation date: (10/23/2001 2:44:09 PM)
	 * 
	 * @return com.ibm.iwt.webproject.RelationData
	 */
	RelationData getRelationData();

}