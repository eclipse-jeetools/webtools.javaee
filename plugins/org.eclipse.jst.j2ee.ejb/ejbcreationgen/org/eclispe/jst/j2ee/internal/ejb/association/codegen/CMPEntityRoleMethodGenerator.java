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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;






/**
 * Insert the type's description here. Creation date: (5/4/2001 5:29:29 PM)
 * 
 * @author: Administrator
 */
public abstract class CMPEntityRoleMethodGenerator extends EJBRoleMethodGenerator {
	static final String COMMENT_PATTERN = "This method was generated for supporting the relationship role named {0}.\nIt will be deleted/edited when the relationship is deleted/edited.\n";//$NON-NLS-1$

	/**
	 * CMPEntityRoleMethodGenerator constructor comment.
	 */
	public CMPEntityRoleMethodGenerator() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return format(COMMENT_PATTERN, new String[]{getRoleHelper().getRole().getName()});
	}

	/**
	 * Subclasses must implement to get the member name from the source model.
	 */
	protected abstract String getOldName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException;

	/**
	 * Subclasses should override if they have parameters.
	 */
	protected String[] getOldParameterTypeNames() throws GenerationException {
		return null;
	}

	/**
	 * This implementation sets the roleHelper as the source element and checks old field info in
	 * the helper.
	 */
	public void initialize(Object roleHelper) throws GenerationException {
		super.initialize(roleHelper);
		if (!getRoleHelper().isCreate()) {
			JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
			if (getRoleHelper().getOldRole() != null)
				historyDesc.setName(getOldName());
			else
				historyDesc.setName(getName());
			historyDesc.setParameterTypes(getOldParameterTypeNames());
			historyDesc.setDeleteOnly(getRoleHelper().isDelete());
			setHistoryDescriptor(historyDesc);
		}
	}
}