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
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * @version 1.0
 * @author: John Lanuti
 */
public class EntityBeanSpecializedEjbCreateMB extends EntityBeanEjbCreateMB {

	protected JavaParameterDescriptor[] getRequiredAttributeFieldsAsParms() throws GenerationException {

		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		return EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator());
	}



}