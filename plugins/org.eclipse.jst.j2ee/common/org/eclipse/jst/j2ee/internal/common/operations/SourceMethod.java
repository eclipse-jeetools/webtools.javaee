/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.PrimitiveType.Code;

public class SourceMethod implements Method {
	
	private MethodDeclaration method;
	
	public SourceMethod(MethodDeclaration method) {
		this.method = method;
	}
	
	/**
	 * @see Method#getSignature()
	 */
	public String getSignature() {
		List<SingleVariableDeclaration> parameters = method.parameters();
		List<String> parameterTypesList = new ArrayList<String>();
		for (SingleVariableDeclaration parameter : parameters) {
			parameterTypesList.add(parameter.getType().resolveBinding().getQualifiedName());
		}
		
		String[] parameterTypes = parameterTypesList.toArray(new String[] { });
		String returnType = method.getReturnType2().resolveBinding().getQualifiedName();
		
		return Signature.createMethodSignature(parameterTypes, returnType);
	}

	/**
	 * @see Method#getName()
	 */
	public String getName() {
		return method.getName().getIdentifier();
	}

	/**
	 * @see Method#getContainingJavaClass()
	 */
	public String getContainingJavaClass() {
		TypeDeclaration type = (TypeDeclaration) method.getParent();
		return type.getName().getIdentifier();
	}

	/**
	 * @see Method#getReturnType()
	 */
	public String getReturnType() {
		return method.getReturnType2().resolveBinding().getName();
	}
	
	/**
	 * @see Method#getDefaultReturnValue()
	 */
	public String getDefaultReturnValue() {
		Type returnType = method.getReturnType2();
		if (returnType.isPrimitiveType()) {
			Code code = ((PrimitiveType) returnType).getPrimitiveTypeCode();
			if (code == PrimitiveType.VOID) {
				return null;
			} else if (code == PrimitiveType.BOOLEAN) {
				return "false"; //$NON-NLS-1$
			} else {
				return "0"; //$NON-NLS-1$
			}
		}
		return "null"; //$NON-NLS-1$
	}
	
	/**
	 * @see Method#getReturnTypeImports()
	 */
	public Collection<String> getReturnTypeImports() {
		List<String> result = new ArrayList<String>();
		
		Type returnType = method.getReturnType2();
		getTypeImports(returnType, result);
		
		return result;
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
	 * @see Method#getParamsForJavadoc()
	 */
	public String getParamsForJavadoc() {
		return this.getParams(true, false);
	}

	/**
	 * @see Method#getParameterImports()
	 */
	public Collection<String> getParameterImports() {
		List<String> result = new ArrayList<String>();
		
		List<SingleVariableDeclaration> parameters = method.parameters();
		for (SingleVariableDeclaration parameter : parameters) {
			Type type =  parameter.getType();
			getTypeImports(type, result);
		}
		
		return result;
	}
	
	
	/**
	 * @see Method#getExceptions()
	 */
	
	public String getExceptions(){
		StringBuffer exceptions = new StringBuffer();
		List exceptionTypes = method.thrownExceptions();
		
		for (int i=0; i<exceptionTypes.size(); i++){
			exceptions.append(exceptionTypes.get(i));
	        if (i < exceptionTypes.size() - 1)
	        	exceptions.append(", "); //$NON-NLS-1$			
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

	private void getTypeImports(Type type, List<String> result) {
		if (type.isArrayType()) {
			getArrayTypeImports(type, result);
		} else if (type.isParameterizedType()) {
			getParameterizedTypeImports(type, result);
		} else if (type.isPrimitiveType()) {
			getPrimitiveTypeImports(type, result);
		} else if (type.isSimpleType()) {
			getSimpleTypeImports(type, result);
		} else if (type.isQualifiedType()) {
			getQualifiedTypeImports(type, result);
		} else if (type.isWildcardType()) {
			getWildcardTypeImports(type, result);
		}
	}

	private void getArrayTypeImports(Type type, List<String> result) {
		ArrayType arrayType = (ArrayType) type;
		Type componentType = arrayType.getComponentType();
		getTypeImports(componentType, result);
	}

	private void getParameterizedTypeImports(Type type, List<String> result) {
		ParameterizedType parameterizedType = (ParameterizedType) type;
		
		Type mainType = parameterizedType.getType();
		getTypeImports(mainType, result);
		
		List<Type> arguments = parameterizedType.typeArguments();
		for (Type argument : arguments) {
			getTypeImports(argument, result);
		}
	}

	private void getPrimitiveTypeImports(Type type, List<String> result) {
		// do nothing - no imports required for primitive types
	}

	private void getSimpleTypeImports(Type type, List<String> result) {
		ITypeBinding binding = type.resolveBinding();
		if (binding != null){
			if (binding.isParameterizedType() && binding.getBinaryName() != null){
				result.add(binding.getBinaryName());
			}
			else{
				result.add(binding.getQualifiedName());
			}
		}
	}
	
	private void getQualifiedTypeImports(Type type, List<String> result) {
		QualifiedType qualifiedType = (QualifiedType) type;
		result.add(qualifiedType.getQualifier() + "." + qualifiedType.getName()); //$NON-NLS-1$
	}

	private void getWildcardTypeImports(Type type, List<String> result) {
		WildcardType wildcardType = (WildcardType) type;
		Type bound = wildcardType.getBound();
		if (bound != null) {
			getTypeImports(bound, result);
		}
	}

}
