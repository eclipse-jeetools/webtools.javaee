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
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;



import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

/**
 * Insert the type's description here. Creation date: (9/1/2000 1:20:17 PM)
 * 
 * @author: Administrator
 */
public interface IEJBCodegenHelper {
	EnterpriseBean getEjb();

	org.eclipse.emf.ecore.EObject getMetaObject();
}