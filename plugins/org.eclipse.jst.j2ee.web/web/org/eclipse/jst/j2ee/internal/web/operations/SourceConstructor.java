/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

public class SourceConstructor implements Constructor {
	
	private MethodDeclaration method;
	
	public SourceConstructor(MethodDeclaration method) {
		this.method = method;
	}
	
	/**
	 * @see Constructor#isParameterless()
	 */
	public boolean isParameterless() {
		return method.parameters().size() == 0;
	}

	/**
	 * @see Constructor#isPublic()
	 */
	public boolean isPublic() {
		int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers);
	}

	/**
	 * @see Constructor#isProtected()
	 */
	public boolean isProtected() {
		int modifiers = method.getModifiers();
        return Modifier.isProtected(modifiers);
	}

	/**
	 * @see Constructor#getParamsForCall()
	 */
	public String getParamsForCall() {
		return this.getParams(false, true);
	}

	/**
	 * @see Constructor#getParamsForDeclaration()
	 */
	public String getParamsForDeclaration() {
		return this.getParams(true, true);
	}

	/**
	 * @see Constructor#getParamsForJavadoc()
	 */
	public String getParamsForJavadoc() {
		return this.getParams(true, false);
	}

	/**
	 * @see Constructor#getNonPrimitiveParameterTypes()
	 */
	@SuppressWarnings("unchecked")
	public List<String> getNonPrimitiveParameterTypes() {
		List<String> result = new ArrayList<String>();
		
		List<SingleVariableDeclaration> parameters = method.parameters();
		for (SingleVariableDeclaration parameter : parameters) {
			Type type =  parameter.getType();
			if (!type.isPrimitiveType()) {
				ITypeBinding binding = type.resolveBinding();
				if (binding != null)
					result.add(binding.getQualifiedName());
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private String getParams(boolean types, boolean names) {
		StringBuilder result = new StringBuilder();
		
		Iterator<SingleVariableDeclaration> iterator = method.parameters().iterator();
        while (iterator.hasNext()) {
        	SingleVariableDeclaration parameter = iterator.next();
        	
        	if (types) 
        		result.append(parameter.getType());
        	
        	if (types && names) 
        		result.append(" "); //$NON-NLS-1$
        	
        	if (names) 
        		result.append(parameter.getName());
        	
            if (iterator.hasNext())
                result.append(", "); //$NON-NLS-1$
        }
        
        return result.toString();
	}

}
