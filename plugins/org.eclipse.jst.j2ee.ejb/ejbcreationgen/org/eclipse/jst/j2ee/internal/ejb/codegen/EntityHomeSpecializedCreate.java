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
 * @author John Lanuti
 */
public class EntityHomeSpecializedCreate extends EntityHomeCreate {

	/**
	 * The parms are each key attribute and a bean for a role in the key.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] keyDesc = super.getParameterDescriptors();

		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();

		// Add all the non key required attributes to the specialized create() method
		JavaParameterDescriptor[] nonKeyDesc = EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(entity, topHelper, getSourceContext().getNavigator());
		JavaParameterDescriptor[] result = new JavaParameterDescriptor[keyDesc.length + nonKeyDesc.length];
		System.arraycopy(keyDesc, 0, result, 0, keyDesc.length);
		System.arraycopy(nonKeyDesc, 0, result, keyDesc.length, nonKeyDesc.length);
		return result;
	}
}