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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.GenerationHelper;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanClientInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityAttributeGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeGenerator;


/**
 * Insert the type's description here. Creation date: (10/10/00 3:20:16 PM)
 * 
 * @author: Steve Wasleski
 */
public class Entity20AttributeGenerator extends EntityAttributeGenerator {
	/**
	 * Returns the logical name of the attribute getter generator.
	 */
	protected String getGetterGeneratorName() {
		return IEJB20GenConstants.ENTITY20_ATTRIBUTE_GETTER;
	}

	/**
	 * Returns the logical name of the attribute setter generator.
	 */
	protected String getSetterGeneratorName() {
		return IEJB20GenConstants.ENTITY20_ATTRIBUTE_SETTER;
	}

	/**
	 * This implementation expects a org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helper.AttributeHelper as its source
	 * element. It creates the subgenerators for the Java Model work.
	 */
	public void initialize(Object attributeHelper) throws GenerationException {
		super.initialize(attributeHelper);
		//setup local promote
		JavaTypeGenerator typeGen = getDeclaringTypeGenerator();
		if (typeGen.isClass())
			return;
		AttributeHelper helper = (AttributeHelper) attributeHelper;
		if (helper.isCreate() && helper.isLocal() && !((EnterpriseBeanClientInterface) typeGen).isRemote()) {
			IBaseGenerator methodGen = getGenerator(getGetterGeneratorName());
			methodGen.initialize(attributeHelper);
			methodGen = getGenerator(getSetterGeneratorName());
			methodGen.initialize(attributeHelper);
		}
	}

	protected boolean shouldAddFieldGenerator() {
		return isConcreteBeanAttribute();
	}

	/**
	 * Return whether we are generating the concrete bean implementation for an attribute
	 */
	public boolean isConcreteBeanAttribute() {
		GenerationHelper parentHelper = ((AttributeHelper) getSourceElement()).getParent();
		if (parentHelper.isTopLevelHelper())
			return ((EntityHelper) parentHelper).getConcreteBeanHelper() != null;
		return false;
	}
}