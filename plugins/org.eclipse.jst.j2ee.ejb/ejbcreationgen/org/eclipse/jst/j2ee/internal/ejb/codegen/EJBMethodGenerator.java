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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBMethodGenerator extends JavaMethodGenerator {
	public String VOID = "void"; //$NON-NLS-1$

	/**
	 *  
	 */
	public EJBMethodGenerator() {
		super();
	}

	/**
	 * Initializes the client view generators.
	 * 
	 * @param Object
	 *            currentMethod - The current method to generate.
	 * @throws GenerationException
	 */
	public void initialize(Object currentMethod) throws GenerationException {
		super.initialize(currentMethod);
	}

	/**
	 * Gets an array of exceptions for the method.
	 * 
	 * @return String[]
	 * @throws GenerationException
	 */
	protected String[] getExceptions() throws GenerationException {
		ArrayList exceptionList = new ArrayList();
		if ((this.getMethod().getJavaExceptions() != null) && (this.getMethod().getJavaExceptions().size() > 0)) {
			Iterator exceptions = this.getMethod().getJavaExceptions().iterator();
			while (exceptions.hasNext()) {
				JavaClass ex = (JavaClass) exceptions.next();
				String exName = ex.getQualifiedName();
				exceptionList.add(exName);
			}// while
		}
		return (String[]) exceptionList.toArray(new String[exceptionList.size()]);
	}

	/**
	 * Gets all the parameters for the method.
	 * 
	 * @return JavaParameterDescriptor[]
	 * @throws GenerationException
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		if (this.getMethod().listParametersWithoutReturn() != null || this.getMethod().listParametersWithoutReturn().length > 0) {
			JavaParameter[] parmArray = this.getMethod().listParametersWithoutReturn();
			JavaParameterDescriptor[] params = new JavaParameterDescriptor[parmArray.length];
			for (int i = 0; i < parmArray.length; i++) {
				JavaParameter parm = parmArray[i];
				JavaParameterDescriptor paramDescriptor = new JavaParameterDescriptor();
				paramDescriptor.setName(parm.getName());
				paramDescriptor.setType(parm.getJavaType().getJavaName());
				params[i] = paramDescriptor;
			}// while
			return params;
		}// if
		return new JavaParameterDescriptor[0];
	}

	/**
	 * Gets the return type of the method.
	 * 
	 * @return String
	 * @throws GenerationException
	 */
	protected String getReturnType() throws GenerationException {
		JavaHelpers currentType = getMethod().getReturnType();
		return currentType.getQualifiedName();
	}

	/**
	 * Get the name of the method.
	 * 
	 * @throws GenerationException
	 */
	protected String getName() throws GenerationException {
		return this.getMethod().getName();
	}

	/**
	 * Gets the method.
	 * 
	 * @return Method
	 */
	public Method getMethod() {
		return (Method) this.getSourceElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodGenerator#getBody()
	 */
	protected String getBody() throws GenerationException {
		JavaHelpers currentType = getMethod().getReturnType();
		if (currentType instanceof JavaClass) {
			return "return null;"; //$NON-NLS-1$
		} else if (currentType.isPrimitive() && !currentType.getName().equals(VOID)) {
			if (currentType.getWrapper().getQualifiedName().equals("java.lang.Boolean")) { //$NON-NLS-1$
				return "return false;"; //$NON-NLS-1$
			}
			return "return 0;"; //$NON-NLS-1$
		}
		return super.getBody();
	} /*
	   * (non-Javadoc)
	   * 
	   * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodGenerator#getComment()
	   */

	protected String getComment() throws GenerationException {
		//TODO need to change the getComment to codegen the right comment - VKB
		return super.getComment();
	}
}