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
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;


/**
 * Insert the type's description here. Creation date: (5/7/2001 9:09:00 AM)
 * 
 * @author: Administrator
 */
public class CMP20EntityBeanRoleManyGetter extends CMP20EntityBeanRoleGetter {
	/**
	 * CMPEntityBeanRoleManyGetter constructor comment.
	 */
	public CMP20EntityBeanRoleManyGetter() {
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
	 * Return the CMRField type.
	 */
	protected String getReturnType() throws GenerationException {
		CMRField field = ((EJBRelationshipRole) getRoleHelper().getRole()).getCmrField();
		return field.getCollectionTypeName();
	}
}