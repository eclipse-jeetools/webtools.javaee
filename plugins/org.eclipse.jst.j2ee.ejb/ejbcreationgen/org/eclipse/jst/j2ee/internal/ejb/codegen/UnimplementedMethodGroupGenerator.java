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
/*
 * Created on Mar 25, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.codegen;

import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class UnimplementedMethodGroupGenerator extends EJBMethodGroupGenerator {
	protected JavaClass unimplementedInterface;

	/**
	 *  
	 */
	public UnimplementedMethodGroupGenerator() {
		super();
	}

	public UnimplementedMethodGroupGenerator(IType unimplementedInterface) {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.codegen.BaseGenerator#getName()
	 */
	protected String getName() throws GenerationException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerator#initialize(java.lang.Object)
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		List methods = unimplementedInterface.getMethods();
		generateMethods(IEJB20GenConstants.EJB_INTERFACE_METHOD_GENERATOR, methods);
	}

	/**
	 * Fires off the correct generator and generates the passed in method.
	 * 
	 * @param String
	 *            generatorName - Name of the generator to fire.
	 */
	public void generateMethods(String generatorName, IMethod[] currentMethodList) throws GenerationException {
		for (int i = 0; i < currentMethodList.length; i++) {
			IMethod unimplementedMethod = currentMethodList[i];
			this.createMemberGenerator(generatorName, unimplementedMethod);
		}
	}

	/**
	 * @return Returns the unimplementedInterface.
	 */
	public JavaClass getUnimplementedInterface() {
		return unimplementedInterface;
	}

	/**
	 * @param unimplementedInterface
	 *            The unimplementedInterface to set.
	 */
	public void setUnimplementedInterface(JavaClass unimplementedInterface) {
		this.unimplementedInterface = unimplementedInterface;
	}
}