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
package org.eclispe.jst.j2ee.internal.ejb.association.codegen;



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (5/7/2001 9:22:57 AM)
 * 
 * @author: Administrator
 */
public abstract class CMPEntityBeanRoleAccessorWithParam extends CMPEntityRoleMethodGenerator implements org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants {
	/**
	 * CMPEntityBeanRoleAccessorWithParam constructor comment.
	 */
	public CMPEntityBeanRoleAccessorWithParam() {
		super();
	}

	/**
	 * Return the Entity ResourceSet from the role's type bean name.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		bodyBuf.indent();
		bodyBuf.formatWithMargin(getBodyPattern(), getBodyPatternReplacements());//$NON-NLS-1$
		bodyBuf.unindent();
	}

	/**
	 * Return the pattern string for the body.
	 */
	protected abstract String getBodyPattern();

	/**
	 * Return the replacements to the body pattern.
	 */
	protected String[] getBodyPatternReplacements() throws GenerationException {
		return new String[]{getRoleLinkGetterName(), getParameterName()};
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		return new String[]{REMOTE_EXCEPTION_NAME};
	}

	/**
	 * Return the role's type remote interface name.
	 */
	protected String getOldParameterTypeName() {
		return RoleHelper.getRoleTypeRemoteInterfaceName(getRoleHelper().getOldRole());
	}

	/**
	 * Return the role's type remote interface name.
	 */
	protected String[] getOldParameterTypeNames() throws GenerationException {
		String paramName = getOldParameterTypeName();
		if (paramName != null)
			return new String[]{paramName};
		return null;
	}

	/**
	 * An attribute setter has one parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName(getParameterName());
		parms[0].setType(getParameterTypeName());
		return parms;
	}

	protected String getParameterName() {
		return EJBGenHelpers.asParameterName(getRoleHelper().getRole().getName());
	}

	/**
	 * Return the role's type remote interface name.
	 */
	protected String getParameterTypeName() {
		return RoleHelper.getRoleTypeRemoteInterfaceName(getRoleHelper().getRole());
	}

	protected String getRoleLinkGetterName() {
		return RoleHelper.getLinkGetterName(getRoleHelper().getRole());
	}
}