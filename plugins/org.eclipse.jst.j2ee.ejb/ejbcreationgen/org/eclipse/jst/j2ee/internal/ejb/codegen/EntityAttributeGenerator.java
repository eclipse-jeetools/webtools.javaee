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
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberGroupGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeGenerator;


/**
 * Insert the type's description here. Creation date: (10/10/00 3:20:16 PM)
 * 
 * @author: Steve Wasleski
 */
public class EntityAttributeGenerator extends JavaMemberGroupGenerator {
	/**
	 * EntityAttributeGenerator constructor comment.
	 */
	public EntityAttributeGenerator() {
		super();
	}

	/**
	 * Returns the logical name of the attribute field generator.
	 */
	protected String getFieldGeneratorName() {
		return IEJBGenConstants.ENTITY_ATTRIBUTE_FIELD;
	}

	/**
	 * Returns the logical name of the attribute getter generator.
	 */
	protected String getGetterGeneratorName() {
		return IEJBGenConstants.ENTITY_ATTRIBUTE_GETTER;
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return ((AttributeHelper) getSourceElement()).getName();
	}

	/**
	 * Returns the logical name of the attribute setter generator.
	 */
	protected String getSetterGeneratorName() {
		return IEJBGenConstants.ENTITY_ATTRIBUTE_SETTER;
	}

	/**
	 * This implementation expects a org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helper.AttributeHelper as its source
	 * element. It creates the subgenerators for the Java Model work.
	 */
	public void initialize(Object attributeHelper) throws GenerationException {
		super.initialize(attributeHelper);
		JavaTypeGenerator typeGen = getDeclaringTypeGenerator();
		boolean isClass = typeGen.isClass();
		if (shouldAddFieldGenerator()) {
			IBaseGenerator fieldGen = getGenerator(getFieldGeneratorName());
			fieldGen.initialize(attributeHelper);
		}
		AttributeHelper helper = (AttributeHelper) attributeHelper;
		if (!(isClass && ((EnterpriseBeanClass) typeGen).isKeyGenerator()) && (helper.isCreate() && ((helper.isGenerateAccessors() && isClass) || // In
					// the
					// case
					// of
					// existing
					// bean,
					// we
					// may
					// only
					// promote.
					(helper.isRemote() && !isClass && ((EnterpriseBeanClientInterface) typeGen).isRemote())) || !helper.isCreate())) {
			IBaseGenerator methodGen = getGenerator(getGetterGeneratorName());
			methodGen.initialize(attributeHelper);
			methodGen = getGenerator(getSetterGeneratorName());
			methodGen.initialize(attributeHelper);
		}
	}

	protected boolean shouldAddFieldGenerator() throws GenerationException {
		return getDeclaringTypeGenerator().isClass();
	}
}