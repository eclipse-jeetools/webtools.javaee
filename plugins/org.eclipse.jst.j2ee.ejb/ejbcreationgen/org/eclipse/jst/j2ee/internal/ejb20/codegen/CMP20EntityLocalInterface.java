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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.CMPEntityRemoteInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;


/**
 * Insert the type's description here. Creation date: (5/7/2001 11:30:09 AM)
 * 
 * @author: Administrator
 */
public class CMP20EntityLocalInterface extends CMPEntityRemoteInterface {
	/**
	 * CMPEntityRemoteInterface constructor comment.
	 */
	public CMP20EntityLocalInterface() {
		super();
	}

	/*
	 * @see EnterpriseBeanClientInterface#isRemote()
	 */
	public boolean isRemote() {
		return false;
	}

	/**
	 * Returns the logical name for an attribute generator.
	 */
	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null)
			name = IEJB20GenConstants.ENTITY20_ATTRIBUTE;
		return name;
	}

	/**
	 * @see CMPEntityRemoteInterface#getRoleGenerator()
	 */
	protected IBaseGenerator getRoleGenerator() throws GeneratorNotFoundException {
		return getGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE);
	}

}