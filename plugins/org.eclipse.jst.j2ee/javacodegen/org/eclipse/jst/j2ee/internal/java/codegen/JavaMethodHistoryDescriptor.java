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

/**
 * Used to describe the existing method that is to be removed and perhaps act as the source for
 * merging.
 */
public class JavaMethodHistoryDescriptor extends JavaMemberHistoryDescriptor {
	private String[] fParameterTypes = null;

	/**
	 * JavaMethodDescriptor default constructor.
	 */
	public JavaMethodHistoryDescriptor() {
		super();
	}

	/**
	 * Returns the old method's parameter types.
	 * 
	 * @return The parameter types as Java readable type names (not signatures)
	 */
	public String[] getParameterTypes() {
		return fParameterTypes;
	}

	/**
	 * Sets the old method's parameter types.
	 * 
	 * @param newParameterTypes
	 *            The parameter types as Java readable type names (not signatures)
	 */
	public void setParameterTypes(String[] newParameterTypes) {
		fParameterTypes = newParameterTypes;
	}

	/**
	 * Sets the old method's parameter types. The toString() method is called on each element of the
	 * List, unless the element is a JavaParameterDescriptor. In that case, just the type name is
	 * extracted from the parameter descriptor.
	 */
	public void setParameterTypes(List newParameterTypes) {
		fParameterTypes = new String[newParameterTypes.size()];
		Iterator newParameterTypesIter = newParameterTypes.iterator();
		int i = 0;
		Object element = null;
		while (newParameterTypesIter.hasNext()) {
			element = newParameterTypesIter.next();
			if (element instanceof JavaParameterDescriptor)
				fParameterTypes[i] = ((JavaParameterDescriptor) element).getType();
			else
				fParameterTypes[i] = element.toString();
			i++;
		}
	}
}