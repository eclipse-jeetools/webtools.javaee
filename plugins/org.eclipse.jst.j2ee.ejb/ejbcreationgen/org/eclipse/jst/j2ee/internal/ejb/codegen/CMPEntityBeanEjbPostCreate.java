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
 * Insert the type's description here. Creation date: (6/6/2001 5:52:13 PM)
 * 
 * @author: Administrator
 */
public class CMPEntityBeanEjbPostCreate extends EntityBeanEjbPostCreate {
	private Boolean addSuperCall;

	/**
	 * CMPEntityBeanEjbPostCreate constructor comment.
	 */
	public CMPEntityBeanEjbPostCreate() {
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
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		createDependentGenerator();
	}

	protected void createDependentGenerator() throws GenerationException {
		createDependentGenerator(IEJBGenConstants.CMP_ENTITY_BEAN_EJBPOSTCREATE_MB);
	}

	/**
	 * Insert our body line prior to the dependents running.
	 */
	public void runDependents(IGenerationBuffer buffer) throws GenerationException {
		if (shouldAddSuperCall())
			appendSuperCall(buffer);
		super.runDependents(buffer);
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