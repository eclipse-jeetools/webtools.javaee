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


import org.eclipse.jdt.internal.compiler.env.IConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanClass;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanClientInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityAttributeGetter;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeGenerator;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class Entity20AttributeGetter extends EntityAttributeGetter {
	/**
	 * Caluclates the name of the method.
	 */
	protected String calculateName(String attrName, String attrType) throws GenerationException {
		StringBuffer nameBuffer = new StringBuffer();
		String prefix = IEJBGenConstants.GETTER_PREFIX;
		/*
		 * Drop the "is" convention for EJB 2.0 if ((attrType != null) &&
		 * (attrType.equals("boolean")))//$NON-NLS-1$ prefix =
		 * IEJBGenConstants.BOOLEAN_GETTER_PREFIX;
		 */
		nameBuffer.append(prefix);
		nameBuffer.append(Character.toUpperCase(attrName.charAt(0)));
		for (int i = 1; i < attrName.length(); i++)
			nameBuffer.append(attrName.charAt(i));
		return nameBuffer.toString();
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
		EnterpriseBeanClass ejbClass = (EnterpriseBeanClass) getDeclaringTypeGenerator();
		if (ejbClass.getClassRefHelper() != null && ejbClass.getClassRefHelper().isConcreteBeanHelper())
			return IConstants.AccPublic;
		return IConstants.AccPublic | IConstants.AccAbstract;
	}

	protected String[] getExceptions() throws GenerationException {
		String[] names = null;
		JavaTypeGenerator typeGen = getDeclaringTypeGenerator();
		if (typeGen.isInterface()) {
			if (((EnterpriseBeanClientInterface) typeGen).isRemote())
				names = new String[]{IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		}
		return names;
	}

	protected boolean shouldDeleteOldMember(AttributeHelper attrHelper) {
		//do not delete if adding to the key
		return attrHelper.isDelete();
	}
}