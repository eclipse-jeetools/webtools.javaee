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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaFieldGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:37:42 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityAttributeField extends JavaFieldGenerator {
	/**
	 * EntityAttributeField constructor comment.
	 */
	public EntityAttributeField() {
		super();
	}

	/**
	 * Override to make default public.
	 */
	protected int deriveFlags() throws GenerationException {
		return org.eclipse.jdt.internal.compiler.env.IConstants.AccPublic;
	}

	protected AttributeHelper getAttributeHelper() {
		return (AttributeHelper) getSourceElement();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Implementation field for persistent attribute: " + getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * Subclasses must override to get the field initializer from the source model. The default is
	 * no initializer.
	 */
	protected String getInitializer() throws GenerationException {
		return getAttributeHelper().getInitializer();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return getAttributeHelper().getName();
	}

	/**
	 * getType method comment.
	 */
	protected String getType() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return getAttributeHelper().getGenerationTypeName();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		AttributeHelper attrHelper = (AttributeHelper) getSourceElement();
		if (!attrHelper.isCreate()) {
			JavaMemberHistoryDescriptor historyDesc = new JavaMemberHistoryDescriptor();
			if (attrHelper.getOldName() != null)
				historyDesc.setName(attrHelper.getOldName());
			else
				historyDesc.setName(getName());
			historyDesc.setDeleteOnly(attrHelper.isDelete());
			setHistoryDescriptor(historyDesc);
		}
	}
}