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
package org.eclipse.jst.j2ee.internal.java.codegen;



import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;


/**
 * Information about a Java method that is to be generated is gathered into a method descriptor for
 * ease of handling.
 */
public class JavaMethodDescriptor extends JavaMemberDescriptor {
	private String fReturnType = null;
	private JavaParameterDescriptor[] fParameterDescriptors = null;
	private String[] fExceptions = null;

	/**
	 * JavaMethodDescriptor default constructor.
	 */
	public JavaMethodDescriptor() {
		super();
	}

	/**
	 * Each Java method to be generated can have exceptions on a throws clause. The default is no
	 * throws clause.
	 * 
	 * @return The exception names in Java readable form (not signatures)
	 */
	public String[] getExceptions() {
		return fExceptions;
	}

	/**
	 * Each Java method to be generated can have parameters. The default is no parameters.
	 * 
	 * @return The parameter descriptors
	 */
	public JavaParameterDescriptor[] getParameterDescriptors() {
		return fParameterDescriptors;
	}

	/**
	 * Each Java method to be generated can have a return type. The default is void.
	 * 
	 * @return The return type in Java readable form (not a signature)
	 */
	public String getReturnType() {
		String ret = fReturnType;
		if ((ret == null) || (ret.length() == 0))
			ret = IJavaGenConstants.VOID_RETURN;
		return ret;
	}

	/**
	 * Each Java method to be generated can have exceptions on a throws clause. The default is no
	 * throws clause.
	 * 
	 * @param newExceptions
	 *            The exception names as an array in Java readable form (not signatures)
	 */
	public void setExceptions(String[] newExceptions) {
		fExceptions = newExceptions;
	}

	/**
	 * Each Java method to be generated can have exceptions on a throws clause. The default is no
	 * throws clause.
	 * 
	 * @param newExceptions
	 *            The exception names as a List in Java readable form (not signatures)
	 */
	public void setExceptions(List newExceptions) {
		fExceptions = new String[newExceptions.size()];
		Iterator newExceptionsIter = newExceptions.iterator();
		int i = 0;
		while (newExceptionsIter.hasNext()) {
			fExceptions[i] = newExceptionsIter.next().toString();
			i++;
		}
	}

	/**
	 * Each Java method to be generated can have parameters. The default is no parameters.
	 * 
	 * @param newParameterDescriptors
	 *            The parameter descriptors as an array
	 */
	public void setParameterDescriptors(JavaParameterDescriptor[] newParameterDescriptors) {
		fParameterDescriptors = newParameterDescriptors;
	}

	/**
	 * Each Java method to be generated can have parameters. The default is no parameters.
	 * 
	 * @param newParameterDescriptors
	 *            The parameter descriptors as a List
	 * @throws GenerationException
	 *             each member of the List must be an instance of JavaParameterDescriptor.
	 */
	public void setParameterDescriptors(List newParameterDescriptors) throws GenerationException {
		try {
			fParameterDescriptors = new JavaParameterDescriptor[newParameterDescriptors.size()];
			fParameterDescriptors = (JavaParameterDescriptor[]) newParameterDescriptors.toArray(fParameterDescriptors);
		} catch (Exception exc) {
			fParameterDescriptors = null;
			throw new GenerationException(exc);
		}
	}

	/**
	 * Each Java method to be generated can have a return type. The default is void.
	 * 
	 * @param newReturnType
	 *            The return type in Java readable form (not a signature)
	 */
	public void setReturnType(String newReturnType) {
		fReturnType = newReturnType;
	}
}