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


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


/**
 * Creates the generator for remote methods.
 */
public class EJBRemoteClientMethodGenerator extends EJBMethodGenerator {

	/**
	 * Default constructor.
	 */
	public EJBRemoteClientMethodGenerator() {
		super();
	}// EJBRemoteClientMethodGenerator

	/**
	 * Gets an array of exceptions for the method.
	 * 
	 * @return String[]
	 * @throws GenerationException
	 */
	protected String[] getExceptions() throws GenerationException {
		ArrayList exceptionList = new ArrayList();
		boolean needToAddRemoteException = true;

		if ((this.getMethod().getJavaExceptions() != null) && (this.getMethod().getJavaExceptions().size() > 0)) {

			Iterator exceptions = this.getMethod().getJavaExceptions().iterator();

			while (exceptions.hasNext()) {
				JavaClass ex = (JavaClass) exceptions.next();
				String exName = ex.getQualifiedName();
				if (needToAddRemoteException && exName.equals(IEJBGenConstants.REMOTE_EXCEPTION_NAME)) {
					needToAddRemoteException = false;
				}
				exceptionList.add(exName);
			}// while

		}
		//ejbCreate methods do not throw remote exceptions in the local interface.
		if (needToAddRemoteException) {
			exceptionList.add(IEJBGenConstants.REMOTE_EXCEPTION_NAME);
		}// if

		return (String[]) exceptionList.toArray(new String[exceptionList.size()]);
	}// getExceptions

	/**
	 * Gets the return type of the method.
	 * 
	 * @return String
	 * @throws GenerationException
	 */
	protected String getReturnType() throws GenerationException {
		JavaHelpers currentType = getMethod().getReturnType();
		if (currentType == getCopiedClientInterface())
			return getClientInterface().getQualifiedName();
		return currentType.getQualifiedName();
	}// getReturnType


	protected EnterpriseBean getEnterpriseBean() {
		return ((EnterpriseBeanHelper) getTopLevelHelper()).getEjb();
	}

	protected JavaClass getClientInterface() {
		EnterpriseBean ejb = getEnterpriseBean();
		if (ejb != null)
			return ejb.getRemoteInterface();
		return null;
	}

	protected JavaClass getCopiedClientInterface() {
		EnterpriseBean ejb = getEnterpriseBean();
		if (ejb != null)
			return ejb.getLocalInterface();
		return null;
	}

}// EJBRemoteClientMethodGenerator
