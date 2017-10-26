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
package org.eclipse.jst.j2ee.internal.common.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

public class BinaryConstructor implements Constructor {
	
	private IMethod method;

	public BinaryConstructor(IMethod method) {
		this.method = method;
	}

	/**
	 * @see Constructor#isParameterless()
	 */
	@Override
	public boolean isParameterless() {
		return method.getNumberOfParameters() == 0;
	}

	/**
	 * @see Constructor#isPublic()
	 */
	@Override
	public boolean isPublic() {
		int flags;
		try {
			flags = method.getFlags();
		} catch (JavaModelException e) {
			J2EEPlugin.logError(e);
			flags = 0;
		}
        return Flags.isPublic(flags);
	}

	/**
	 * @see Constructor#isProtected()
	 */
	@Override
	public boolean isProtected() {
		int flags;
		try {
			flags = method.getFlags();
		} catch (JavaModelException e) {
			J2EEPlugin.logError(e);
			flags = 0;
		}
        return Flags.isProtected(flags);
	}

	/**
	 * @see Constructor#getParamsForCall()
	 */
	@Override
	public String getParamsForCall() {
		return this.getParams(false, true);
	}

	/**
	 * @see Constructor#getParamsForDeclaration()
	 */
	@Override
	public String getParamsForDeclaration() {
		return this.getParams(true, true);
	}

	/**
	 * @see Constructor#getParamsForJavadoc()()
	 */
	@Override
	public String getParamsForJavadoc() {
		return this.getParams(true, false);
	}
	
	/**
	 * @see Constructor#getNonPrimitiveParameterTypes()
	 */
	@Override
	public List<String> getNonPrimitiveParameterTypes() {
		List<String> result = new ArrayList<String>();
		
		String[] parameterTypes = method.getParameterTypes();
		for (String parameterType : parameterTypes) {
			if (Signature.getTypeSignatureKind(parameterType) != Signature.BASE_TYPE_SIGNATURE) {
				result.add(Signature.toString(parameterType));
			}
		}
		
		return result;
	}

	private String getParams(boolean types, boolean names) {
		StringBuilder result = new StringBuilder();
		
        String[] parameterTypes = method.getParameterTypes();
        String[] parameterNames;
		try {
			parameterNames = method.getParameterNames();
		} catch (JavaModelException e) {
			J2EEPlugin.logError(e);
			
			parameterNames = new String[parameterTypes.length];
			for (int i = 0; i < parameterNames.length; i++) {
				parameterNames[i] = "arg" + i; //$NON-NLS-1$
			}
		}
        
        for (int i = 0; i < parameterTypes.length; i++) {
        	if (types) 
        		result.append(Signature.getSignatureSimpleName(parameterTypes[i]));
        	
        	if (types && names) 
        		result.append(" "); //$NON-NLS-1$
        	
        	if (names) 
        		result.append(parameterNames[i]);
        	
            if (i < parameterNames.length - 1)
                result.append(", "); //$NON-NLS-1$
        }
		
		return result.toString();
	}

}
