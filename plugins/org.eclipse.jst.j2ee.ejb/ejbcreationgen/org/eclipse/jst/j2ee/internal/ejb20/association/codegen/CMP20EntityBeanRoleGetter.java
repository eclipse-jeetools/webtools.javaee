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


import org.eclipse.jdt.internal.compiler.env.IConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclispe.jst.j2ee.internal.ejb.association.codegen.CMPEntityRoleMethodGenerator;


/**
 * Insert the type's description here. Creation date: (5/7/2001 8:56:01 AM)
 * 
 * @author: Administrator
 */
public class CMP20EntityBeanRoleGetter extends CMPEntityRoleMethodGenerator implements IEJBGenConstants {
	/**
	 * CMPEntityBeanRoleGetter constructor comment.
	 */
	public CMP20EntityBeanRoleGetter() {
		super();
	}

	/**
	 * The generator examines the source model and derives the modifer flags for this target
	 * element. The modifier flags are defined in
	 * org.eclipse.jdt.internal.compiler.env.api.IConstants. The default value AccPublic for
	 * members. The field generators change the default to AccPrivate.
	 */
	protected int deriveFlags() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return IConstants.AccPublic;
		return IConstants.AccPublic | IConstants.AccAbstract;
	}

	/**
	 * Subclasses must implement to get the member name from the source model.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return RoleHelper.getGetterName(getRoleHelper().getRole());
	}

	/**
	 * Subclasses must implement to get the member name from the source model.
	 */
	protected String getOldName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return RoleHelper.getGetterName(getRoleHelper().getOldRole());
	}

	/**
	 * Return the remote interface name of the role's bean type.
	 */
	protected String getReturnType() throws GenerationException {
		return RoleHelper.getRoleType(getRoleHelper().getRole()).getLocalInterfaceName();
	}
}