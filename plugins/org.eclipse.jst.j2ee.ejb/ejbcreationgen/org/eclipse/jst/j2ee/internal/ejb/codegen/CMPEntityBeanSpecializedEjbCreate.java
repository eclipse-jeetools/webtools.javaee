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


import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * @version 1.0
 * @author
 */
public class CMPEntityBeanSpecializedEjbCreate extends CMPEntityBeanEjbCreate {

	/**
	 * Constructor for CMPEntityBeanSpecializedEjbCreate.
	 */
	public CMPEntityBeanSpecializedEjbCreate() {
		super();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		createDependentGenerator(IEJBGenConstants.ENTITY_BEAN_SPECIALIZED_EJBCREATE_MB);
	}

	/**
	 * The parms are each key attribute and a bean for a role in the key.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors(Entity entity) throws GenerationException {
		JavaParameterDescriptor[] keyDesc = super.getParameterDescriptors(entity);
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		// Add all the non key required attributes to the specialized ejbCreate() method
		JavaParameterDescriptor[] nonKeyDesc = EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(entity, topHelper, getSourceContext().getNavigator());
		JavaParameterDescriptor[] result = new JavaParameterDescriptor[keyDesc.length + nonKeyDesc.length];
		System.arraycopy(keyDesc, 0, result, 0, keyDesc.length);
		System.arraycopy(nonKeyDesc, 0, result, keyDesc.length, nonKeyDesc.length);
		return result;
	}

	/**
	 * Method initializeHistoryDescriptor.
	 */
	protected void initializeHistoryDescriptor() throws GenerationException {

		// do nothing, we don't attempt to do cleanup on specialized creates
	}

}