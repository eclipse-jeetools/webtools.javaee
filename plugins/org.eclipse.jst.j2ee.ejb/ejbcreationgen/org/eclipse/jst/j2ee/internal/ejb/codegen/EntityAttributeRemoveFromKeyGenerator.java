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
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeGenerator;


/**
 * Insert the type's description here. Creation date: (10/13/00 2:03:34 PM)
 * 
 * @author: Steve Wasleski
 */
public class EntityAttributeRemoveFromKeyGenerator extends EntityAttributeGenerator {
	/**
	 * EntityAttributeRemoveFromKeyGenerator constructor comment.
	 */
	public EntityAttributeRemoveFromKeyGenerator() {
		super();
	}

	/**
	 * This implementation expects a org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helper.AttributeHelper as its source
	 * element. It creates the subgenerators for the Java Model work.
	 */
	public void initialize(Object attributeHelper) throws GenerationException {
		AttributeHelper attrHelper = (AttributeHelper) attributeHelper;
		attrHelper.setDelete();
		super.initialize(attrHelper);
		attrHelper.setUpdate();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void run() throws GenerationException {
		JavaTypeGenerator typeGen = getDeclaringTypeGenerator();
		if (typeGen.isClass()) {
			EnterpriseBeanClass classGen = (EnterpriseBeanClass) typeGen;
			if (classGen.isKeyGenerator())
				super.run();
		}
	}
}