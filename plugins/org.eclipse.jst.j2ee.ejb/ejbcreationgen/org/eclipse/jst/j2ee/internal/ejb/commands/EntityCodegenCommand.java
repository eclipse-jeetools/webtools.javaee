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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTopLevelGenerationHelper;


/**
 * Insert the type's description here. Creation date: (9/6/2000 1:40:55 PM)
 * 
 * @author: Administrator
 */
public class EntityCodegenCommand extends EnterpriseBeanCodegenCommand {
	/**
	 * Insert the method's description here. Creation date: (9/6/2000 8:55:37 AM)
	 * 
	 * @param aProjectName
	 *            java.lang.String
	 * @param aPackageRootName
	 *            java.lang.String
	 */
	public EntityCodegenCommand(Entity anEntity) {
		super(anEntity);
	}

	/**
	 * createCodegenHelper method comment.
	 */
	protected JavaTopLevelGenerationHelper createCodegenHelper() {
		EntityHelper aHelper = new EntityHelper(getEjb());
		EnterpriseBeanCommand root = getEnterpriseBeanCommand();
		if (root != null && root.isCreateCommand())
			aHelper.setIsCreate(true);
		return aHelper;
	}

	/**
	 * getGeneratorName method comment.
	 */
	public java.lang.String getGeneratorName() {
		return org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants.ENTITY_GENERATOR_NAME;
	}
}