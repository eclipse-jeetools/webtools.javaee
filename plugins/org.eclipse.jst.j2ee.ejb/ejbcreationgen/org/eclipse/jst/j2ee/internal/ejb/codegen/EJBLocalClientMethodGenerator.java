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


import java.util.Iterator;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;


/**
 * Createst the generator for local client view.
 */
public class EJBLocalClientMethodGenerator extends EJBRemoteClientMethodGenerator {

	/**
	 * Default constructor
	 */
	public EJBLocalClientMethodGenerator() {
		super();
	}// EJBLocalClientMethodGenerator

	/**
	 * Gets an array of exceptions for the method.
	 * 
	 * @return String[]
	 * @throws GenerationException
	 */
	protected String[] getExceptions() throws GenerationException {

		if ((this.getMethod().getJavaExceptions() != null) && (this.getMethod().getJavaExceptions().size() > 0)) {

			Iterator exceptions = this.getMethod().getJavaExceptions().iterator();
			int exceptionSize = this.getMethod().getJavaExceptions().size();

			String[] exceptionArray = new String[exceptionSize - 1];

			int i = 0;
			while (exceptions.hasNext()) {
				JavaClass ex = (JavaClass) exceptions.next();

				if (!(ex.getQualifiedName().equals(IEJBGenConstants.REMOTE_EXCEPTION_NAME))) {
					exceptionArray[i++] = ex.getQualifiedName();
				}// if

			}// while

			return exceptionArray;
		}// if

		return new String[0];
	}// getExceptions

	protected JavaClass getClientInterface() {
		EnterpriseBean ejb = getEnterpriseBean();
		if (ejb != null)
			return ejb.getLocalInterface();
		return null;
	}

	protected JavaClass getCopiedClientInterface() {
		EnterpriseBean ejb = getEnterpriseBean();
		if (ejb != null)
			return ejb.getRemoteInterface();
		return null;
	}

}// EJBLocalClientMethodGenerator
