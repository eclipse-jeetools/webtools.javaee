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



import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

/**
 * Insert the type's description here. Creation date: (8/23/2001 3:19:10 PM)
 * 
 * @author: Administrator
 */
public interface ChangeKeyClassInfoProvider {
	EnterpriseBean getEnterpriseBean();

	String getKeyClassName();

	String getKeyClassPackageName();

	boolean isExistingKeyClass();

	boolean shouldUsePrimitiveKeyAttributeType();

	boolean shouldDeleteObsoleteKeyClass();
}