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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityAttributeSetter extends JavaMethodGenerator {
	/**
	 * EntityAttributeSetter constructor comment.
	 */
	public EntityAttributeSetter() {
		super();
	}

	/**
	 * Caluclates the name of the method.
	 */
	protected String calculateName(String attrName) {
		StringBuffer nameBuffer = new StringBuffer();
		nameBuffer.append(IEJBGenConstants.SETTER_PREFIX);
		nameBuffer.append(Character.toUpperCase(attrName.charAt(0)));
		for (int i = 1; i < attrName.length(); i++)
			nameBuffer.append(attrName.charAt(i));
		return nameBuffer.toString();
	}

	/**
	 * getName method comment.
	 */
	protected String getBody() throws GenerationException {
		return primGetName() + " = " + getParmName() + ";\n";//$NON-NLS-2$//$NON-NLS-1$
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Set accessor for persistent attribute: " + primGetName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
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
		return calculateName(primGetName());
	}

	/**
	 * An attribute setter has one parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName(getParmName());
		parms[0].setType(getParmType());
		return parms;
	}

	/**
	 * Caluclates the name of the method.
	 */
	protected String getParmName() throws GenerationException {
		StringBuffer nameBuffer = new StringBuffer();
		nameBuffer.append(IEJBGenConstants.SETTER_PARM_NAME_PREFIX);
		String attrName = primGetName();
		nameBuffer.append(Character.toUpperCase(attrName.charAt(0)));
		for (int i = 1; i < attrName.length(); i++)
			nameBuffer.append(attrName.charAt(i));
		return nameBuffer.toString();
	}

	/**
	 * getName method comment.
	 */
	protected String getParmType() throws GenerationException {
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
				historyDesc.setName(calculateName(attrHelper.getOldName()));
			else
				historyDesc.setName(getName());

			String[] parmTypes = new String[1];
			parmTypes[0] = (attrHelper.getOldGenerationTypeName() == null) ? getParmType() : (attrHelper.getOldGenerationTypeName());
			if (parmTypes[0] != null)
				historyDesc.setParameterTypes(parmTypes);

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