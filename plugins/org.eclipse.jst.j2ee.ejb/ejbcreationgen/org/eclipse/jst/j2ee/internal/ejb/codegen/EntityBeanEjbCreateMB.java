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
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (5/4/2001 11:42:44 AM)
 * 
 * @author: Administrator
 */
public class EntityBeanEjbCreateMB extends org.eclipse.jst.j2ee.internal.codegen.DependentGenerator {
	static final String BODY_PATTERN = "this.%0 = %0;\n";//$NON-NLS-1$

	protected JavaParameterDescriptor[] getEntityKeyAttributeFieldsAsParms() throws GenerationException {
		JavaParameterDescriptor[] descs, superDescs, result;
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		descs = getEntityKeyAttributeFieldsAsParms(helper.getEntity());
		superDescs = getEntityKeyAttributeFieldsAsParms((Entity) helper.getSupertype());
		if (superDescs == null)
			return descs;
		int l1, l2, rl;
		l1 = superDescs.length;
		l2 = descs.length;
		rl = l2 - l1;
		result = new JavaParameterDescriptor[rl];
		for (int i = 0; i < rl; i++) {
			result[i] = descs[l2 + 1 + i];
		}
		return result;
	}

	protected JavaParameterDescriptor[] getRequiredAttributeFieldsAsParms() throws GenerationException {
		return getEntityKeyAttributeFieldsAsParms();
	}

	protected JavaParameterDescriptor[] getEntityKeyAttributeFieldsAsParms(Entity entity) throws GenerationException {
		if (entity == null)
			return null;
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		return EJBGenHelpers.getEntityKeyAttributeFieldsAsParms(entity, topHelper, getSourceContext().getNavigator());
	}

	public void run(IGenerationBuffer buffer) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = getRequiredAttributeFieldsAsParms();
		if ((parmDescs != null) && (parmDescs.length > 0)) {
			String[] parmNames = new String[getBodyPatternIndexes()];
			for (int i = 0; i < parmDescs.length; i++) {
				writeMethodBody(buffer, parmNames, parmDescs[i].getName());
			}
		}
	}

	protected int getBodyPatternIndexes() {
		return 1;
	}

	protected void writeMethodBody(IGenerationBuffer buffer, String[] patternArgs, String parmName) {
		patternArgs[0] = parmName;
		buffer.formatWithMargin(BODY_PATTERN, patternArgs);
	}
}