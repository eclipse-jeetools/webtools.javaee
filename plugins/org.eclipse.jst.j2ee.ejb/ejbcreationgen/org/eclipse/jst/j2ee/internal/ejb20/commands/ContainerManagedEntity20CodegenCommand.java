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
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.codegen.BaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.TopLevelGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.commands.EntityCodegenCommand;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * Insert the type's description here. Creation date: (9/6/2000 1:41:14 PM)
 * 
 * @author: Administrator
 */
public class ContainerManagedEntity20CodegenCommand extends EntityCodegenCommand {
	/**
	 * Insert the method's description here. Creation date: (9/6/2000 8:55:37 AM)
	 * 
	 * @param aProjectName
	 *            java.lang.String
	 * @param aPackageRootName
	 *            java.lang.String
	 */
	public ContainerManagedEntity20CodegenCommand(ContainerManagedEntity aCMP) {
		super(aCMP);
	}

	/**
	 * Override to pick up the EJB 2.0 dictionary. Creation date: (8/19/2000 4:04:11 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.java.codegen.api.IJavaBaseGenerator
	 * @param generatorName
	 *            java.lang.String
	 */
	public IBaseGenerator getGenerator(String generatorName, TopLevelGenerationHelper aHelper) {
		try {
			// Search 2.0 dictionary first so we can override existing generators
			return BaseGenerator.getGenerator(IEJB20GenConstants.DICTIONARY_NAME, generatorName, this.getClass(), aHelper);
		} catch (GeneratorNotFoundException e) {
			try {
				return BaseGenerator.getGenerator(IEJBGenConstants.DICTIONARY_NAME, generatorName, this.getClass(), aHelper);
			} catch (GeneratorNotFoundException e2) {
				e.printStackTrace(System.out);
				throw new RuntimeException(e2.getMessage());
			}
		}
	}

	protected String getGeneratorDictionaryName() {
		return IEJB20GenConstants.DICTIONARY_NAME;
	}

	/**
	 * getGeneratorName method comment.
	 */
	public java.lang.String getGeneratorName() {
		return IEJB20GenConstants.CMP20_ENTITY_GENERATOR_NAME;
	}
}