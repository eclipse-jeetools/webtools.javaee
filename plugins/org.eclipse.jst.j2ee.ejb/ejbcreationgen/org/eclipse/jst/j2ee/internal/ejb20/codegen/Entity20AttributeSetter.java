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
import org.eclipse.jst.j2ee.internal.codegen.GenerationBuffer;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanClass;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanClientInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityAttributeSetter;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeGenerator;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class Entity20AttributeSetter extends EntityAttributeSetter {
	protected final static String BODY = "instanceExtension.markDirty(%0);\n" + //$NON-NLS-1$
				"%1 = %2;\n"; //$NON-NLS-1$

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

	/**
	 * getBody method comment.
	 */
	protected String getBody() throws GenerationException {
		GenerationBuffer gb = new GenerationBuffer();
		gb.format(BODY, new String[]{String.valueOf(getCMPIndex()), primGetName(), getParmName()});
		return gb.toString();
	}

	/**
	 * Return the index of the attribute for which we are generating a setter. Used to mark fields
	 * dirty.
	 */
	protected int getCMPIndex() {
		return ((AttributeHelper) getSourceElement()).getAttributeIndex();
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
		//Do not delete if adding to the key.
		return attrHelper.isDelete();
	}
}