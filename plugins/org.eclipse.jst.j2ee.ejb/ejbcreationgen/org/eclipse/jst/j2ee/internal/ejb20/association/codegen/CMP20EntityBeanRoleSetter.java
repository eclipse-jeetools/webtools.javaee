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
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclispe.jst.j2ee.internal.ejb.association.codegen.CMPEntityBeanRoleAccessorWithParam;


/**
 * Insert the type's description here. Creation date: (5/4/2001 5:20:00 PM)
 * 
 * @author: Administrator
 */
public class CMP20EntityBeanRoleSetter extends CMPEntityBeanRoleAccessorWithParam implements org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants {
	/**
	 * CMPEntityBeanRoleSetter constructor comment.
	 */
	public CMP20EntityBeanRoleSetter() {
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
		return RoleHelper.getSetterName(getRoleHelper().getRole());
	}

	/**
	 * Subclasses must implement to get the member name from the source model.
	 */
	protected String getOldName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return RoleHelper.getSetterName(getRoleHelper().getOldRole());
	}

	protected String getOldParameterTypeName() {
		CommonRelationshipRole role = getRoleHelper().getOldRole();
		if (role != null && role.isNavigable()) {
			if (role.isMany())
				return getManyParamTypeName(role);
			return RoleHelper.getRoleType(role).getLocalInterfaceName();
		}
		return null;
	}

	/**
	 * Method getManyParamTypeName.
	 * 
	 * @param role
	 * @return String
	 */
	protected String getManyParamTypeName(CommonRelationshipRole role) {
		CMRField field = ((EJBRelationshipRole) role).getCmrField();
		return field.getCollectionTypeName();
	}

	protected String getParameterTypeName() {
		return RoleHelper.getRoleType(getRoleHelper().getRole()).getLocalInterfaceName();
	}

	/**
	 * @see CMPEntityBeanRoleAccessorWithParam#getBodyPattern()
	 */
	protected String getBodyPattern() {
		return null;
	}

	protected String[] getExceptions() throws GenerationException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodGenerator#getReturnType()
	 */
	protected String getReturnType() throws GenerationException {
		return null;
	}
}