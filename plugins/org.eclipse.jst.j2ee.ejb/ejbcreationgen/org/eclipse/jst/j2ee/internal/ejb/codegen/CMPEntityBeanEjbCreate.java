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



import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (5/3/2001 4:54:24 PM)
 * 
 * @author: Administrator
 */
public class CMPEntityBeanEjbCreate extends EntityBeanEjbCreate {
	static final String METHOD_COMMENT = "ejbCreate method for a CMP entity bean.\n";//$NON-NLS-1$
	private Boolean addSuperCall;

	/**
	 * CMPEntityBeanEjbCreate constructor comment.
	 */
	public CMPEntityBeanEjbCreate() {
		super();
	}

	protected void appendSuperCall(IGenerationBuffer buffer) throws GenerationException {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		ContainerManagedEntity superCMP = (ContainerManagedEntity) helper.getSupertype();
		JavaParameterDescriptor[] descriptors = getParameterDescriptors(superCMP);
		buffer.appendWithMargin("super.");//$NON-NLS-1$
		buffer.append(getName());
		buffer.append("(");//$NON-NLS-1$
		int length = descriptors.length;
		int stop = length - 1;
		for (int i = 0; i < length; i++) {
			buffer.append(descriptors[i].getName());
			if (i != stop)
				buffer.append(", ");//$NON-NLS-1$
		}
		buffer.append(");\n");//$NON-NLS-1$
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return METHOD_COMMENT;
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		createDependentGenerator(IEJBGenConstants.CMP_ENTITY_BEAN_EJBCREATE_MB);
	}

	/**
	 * Insert our body line prior to the dependents running.
	 */
	public void runDependents(IGenerationBuffer buffer) throws GenerationException {
		insertBody(buffer);
		super.runDependents(buffer);
	}

	protected void insertBody(IGenerationBuffer buffer) throws GenerationException {
		if (shouldAddSuperCall())
			appendSuperCall(buffer);
	}

	protected boolean shouldAddSuperCall() throws GenerationException {
		if (addSuperCall == null) {
			EntityHelper helper = (EntityHelper) getTopLevelHelper();
			addSuperCall = new Boolean(EntityHelper.hasLocalRequiredRolesWithSupertype(helper.getEntity()));
		}
		return addSuperCall.booleanValue();
	}

	protected boolean shouldDelete() {
		boolean shouldDelete = super.shouldDelete();
		try {
			if (shouldDelete)
				shouldDelete = !shouldAddSuperCall();
		} catch (GenerationException e) {
		}
		return shouldDelete;
	}
}