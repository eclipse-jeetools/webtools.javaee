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
package org.eclipse.jst.j2ee.internal.ejb.codegen;



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityAttributeGetter extends JavaMethodGenerator {
	/**
	 * EntityAttributeGetter constructor comment.
	 */
	public EntityAttributeGetter() {
		super();
	}

	/**
	 * Caluclates the name of the method.
	 */
	protected String calculateName(String attrName, String attrType) throws GenerationException {
		StringBuffer nameBuffer = new StringBuffer();
		String prefix = IEJBGenConstants.GETTER_PREFIX;
		if ((attrType != null) && (attrType.equals("boolean")))//$NON-NLS-1$
			prefix = IEJBGenConstants.BOOLEAN_GETTER_PREFIX;
		nameBuffer.append(prefix);
		nameBuffer.append(Character.toUpperCase(attrName.charAt(0)));
		for (int i = 1; i < attrName.length(); i++)
			nameBuffer.append(attrName.charAt(i));
		return nameBuffer.toString();
	}

	/**
	 * getName method comment.
	 */
	protected String getBody() throws GenerationException {
		return "return " + primGetName() + ";\n";//$NON-NLS-2$//$NON-NLS-1$
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Get accessor for persistent attribute: " + primGetName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		String[] names = null;
		if (getDeclaringTypeGenerator().isInterface())
			names = new String[]{IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return names;
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return calculateName(primGetName(), getReturnType());
	}

	/**
	 * getName method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return ((AttributeHelper) getSourceElement()).getGenerationTypeName();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		AttributeHelper attrHelper = (AttributeHelper) getSourceElement();
		if (!attrHelper.isCreate()) {
			JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
			if (attrHelper.getOldName() != null)
				historyDesc.setName(calculateName(attrHelper.getOldName(), attrHelper.getOldGenerationTypeName()));
			else
				historyDesc.setName(getName());
			historyDesc.setDeleteOnly(shouldDeleteOldMember(attrHelper));
			setHistoryDescriptor(historyDesc);
		}
	}

	protected boolean shouldDeleteOldMember(AttributeHelper attrHelper) {
		return attrHelper.isDelete() || attrHelper.isAddingKey();
	}

	/**
	 * getName method comment.
	 */
	protected String primGetName() throws GenerationException {
		return ((AttributeHelper) getSourceElement()).getName();
	}
}