/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;

import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


public class CMP20EntityBeanFlatKeyRoleEjbCreateMB extends EntityBean20EjbCreateMB {
	protected JavaParameterDescriptor[] getEntityKeyAttributeFieldsAsParms(Entity entity) throws GenerationException {
		if (entity == null)
			return null;
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		return EJBGenHelpers.getEntityKeyFieldsAsFlatParms(entity, topHelper, getSourceContext().getNavigator());
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.ejb20.codegen.EntityBean20EjbCreateMB#writeKeyRoles(IGenerationBuffer)
	 */
	protected void writeKeyRoles(IGenerationBuffer buffer) throws GenerationException {
		//do nothing
	}

}