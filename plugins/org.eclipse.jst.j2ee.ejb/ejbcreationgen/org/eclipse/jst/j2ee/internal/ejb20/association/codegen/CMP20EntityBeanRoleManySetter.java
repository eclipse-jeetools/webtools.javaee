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
package org.eclipse.jst.j2ee.internal.ejb20.association.codegen;



/**
 * @author DABERG
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public class CMP20EntityBeanRoleManySetter extends CMP20EntityBeanRoleSetter {

	/**
	 * Constructor for CMP20EntityBeanRoleManySetter.
	 */
	public CMP20EntityBeanRoleManySetter() {
		super();
	}

	protected String getParameterTypeName() {
		return getManyParamTypeName(getRoleHelper().getRole());
	}

}