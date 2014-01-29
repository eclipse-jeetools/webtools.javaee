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
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

public class BinaryMethod implements Method {
	
	private IMethod method;
	
	public BinaryMethod(IMethod method) {
		this.method = method;
	}
	
	/**
	 * @see Method#getSignature()
	 */
	public String getSignature() {
		try {
			return method.getSignature();
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see Method#getName()
	 */
	public String getName() {
		return method.getElementName();
	}

	/**
	 * @see Method#getContainingJavaClass()
	 */
	public String getContainingJavaClass() {
		return method.getDeclaringType().getElementName();
	}

	/**
	 * @see Method#getReturnType()
	 */
	public String getReturnType() {
		try {
			return Signature.getSignatureSimpleName(method.getReturnType());
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @see Method#getDefaultReturnValue()
	 */
	public String getDefaultReturnValue() {
		try {
			String signature = method.getReturnType();
			int kind = Signature.getTypeSignatureKind(signature);
			if (kind == Signature.BASE_TYPE_SIGNATURE) {
				if (Signature.SIG_VOID.equals(signature)) {
					return null;
				} else if (Signature.SIG_BOOLEAN.equals(signature)) {
					return "false"; //$NON-NLS-1$
				} else {
					return "0"; //$NON-NLS-1$
				}
			}
			return "null"; //$NON-NLS-1$
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @see Method#getReturnTypeImports()
	 */
	public Collection<String> getReturnTypeImports() {
		try {
			List<String> result = new ArrayList<String>();
			
			String signature = method.getReturnType();
			getTypeImports(signature, result);
			
			return result;
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see Method#getParamsForCall()
	 */
	public String getParamsForCall() {
		return this.getParams(false, true);
	}

	/**
	 * @see Method#getParamsForDeclaration()
	 */
	public String getParamsForDeclaration() {
		return this.getParams(true, true);
	}

	/**
	 * @see Method#getParamsForJavadoc()()
	 */
	public String getParamsForJavadoc() {
		return this.getParams(true, false);
	}
	
	/**
	 * @see Method#getParameterImports()
	 */
	public Collection<String> getParameterImports() {
		List<String> result = new ArrayList<String>();
		
		String[] parameterTypes = method.getParameterTypes();
		for (String parameterType : parameterTypes) {
			getTypeImports(parameterType, result);
		}
		
		return result;
	}
	
	
	
	/**
	 * @see Method#getExceptions()
	 */
	
	public String getExceptions(){
		StringBuffer exceptions = new StringBuffer(); 
		try {
			String[] exceptionTypes = method.getExceptionTypes();
			for (int i=0; i<exceptionTypes.length; i++){
			   exceptions.append(Signature.toString(exceptionTypes[i]));
	           if (i < exceptionTypes.length - 1)
	                exceptions.append(", "); //$NON-NLS-1$
			}
			
		} catch (JavaModelException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}

		return exceptions.toString();
		
	}
	
	
	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		Method obj = (Method) o;
		return this.getName().equals(obj.getName()) && 
			this.getSignature().equals(obj.getSignature());
	}
	
	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getName().hashCode() * getSignature().hashCode();
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
	
	private void getTypeImports(String type, List<String> result) {
		int kind = Signature.getTypeSignatureKind(type);
		
		switch (kind) {
		case Signature.ARRAY_TYPE_SIGNATURE:
			getArrayTypeImports(type, result);
			break;
			
		case Signature.BASE_TYPE_SIGNATURE:
			getBaseTypeImports(type, result);
			break;
			
		case Signature.CAPTURE_TYPE_SIGNATURE:
			getCaptureTypeImports(type, result);
			break;
			
		case Signature.CLASS_TYPE_SIGNATURE:
			getClassTypeImports(type, result);
			break;
			
		case Signature.TYPE_VARIABLE_SIGNATURE:
			getTypeVarialbleImports(type, result);
			break;
		
		case Signature.WILDCARD_TYPE_SIGNATURE:
			getWildcardTypeImports(type, result);
			break;

		default:
			throw new IllegalStateException("invalid type signature kind: " + kind); //$NON-NLS-1$
		
		}
	}

	private void getArrayTypeImports(String type, List<String> result) {
		String elementType = Signature.getElementType(type);
		getTypeImports(elementType, result);
	}

	private void getBaseTypeImports(String type, List<String> result) {
		// do nothing - no imports required for primitive types
	}

	private void getCaptureTypeImports(String type, List<String> result) {
	}

	private void getClassTypeImports(String type, List<String> result) {
		result.add(Signature.toString(type));
	}

	private void getTypeVarialbleImports(String type, List<String> result) {
	}

	private void getWildcardTypeImports(String type, List<String> result) {
	}

}
