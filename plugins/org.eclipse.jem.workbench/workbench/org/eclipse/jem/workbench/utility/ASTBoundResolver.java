/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ASTBoundResolver.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/04 21:36:32 $ 
 */
package org.eclipse.jem.workbench.utility;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.internal.instantiation.PTName;
import org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST.InvalidExpressionException;
 
/**
 * This works on resolved AST nodes. If the nodes had not been resolved, this will return noting.
 * 
 * @since 1.0.0
 */
public class ASTBoundResolver extends ParseTreeCreationFromAST.Resolver {

	/* (non-Javadoc)
	 * @see org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST.IResolver#resolveName(org.eclipse.jdt.core.dom.Name)
	 */
	public PTExpression resolveName(Name name) {
		IBinding binding = name.resolveBinding();
		if (binding == null)
			return null;
		
		switch (binding.getKind()) {
			case IBinding.TYPE:
				String typename = getTypeName((ITypeBinding) binding);
				PTName ptname = InstantiationFactory.eINSTANCE.createPTName();
				ptname.setName(typename);
				return ptname;
			case IBinding.VARIABLE:
				IVariableBinding variableBinding = (IVariableBinding) binding;
				if (variableBinding.isField()) {
					if (Modifier.isStatic(variableBinding.getModifiers())) {
						PTFieldAccess fa = InstantiationFactory.eINSTANCE.createPTFieldAccess();
						// If just a simple name, then it is like this.field, so no receiver. Not sure how to handle this yet.
						if (name.isQualifiedName()) {
							// There are parts before this one. Get them as an expression (either a name, or another field access)
							fa.setReceiver(resolveName(((QualifiedName) name).getQualifier()));
						}
						fa.setField(variableBinding.getName());
						return fa;
					} else {
						throwInvalidExpressionException("Not sure how to handle yet local field access of \""+variableBinding.getName()+"\"");
//						push(new PushFieldVariable(variableId, getTypeSignature(declaringTypeBinding), fCounter));
//						push(new PushThis(getEnclosingLevel(node, declaringTypeBinding)));
//						storeInstruction();
					}
				} else {
					throwInvalidExpressionException("Not sure how to handle yet local variable access of \""+variableBinding.getName()+"\"");
//					push(new PushLocalVariable(variableId));
				}				
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.workbench.utility.IResolver#resolveType(org.eclipse.jdt.core.dom.Type)
	 */
	public String resolveType(Type type) {
		ITypeBinding binding = type.resolveBinding();
		return (binding != null) ? getTypeName(binding) : null; 
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST.Resolver#resolveType(org.eclipse.jdt.core.dom.Name)
	 */
	public String resolveType(Name name) throws InvalidExpressionException {
		ITypeBinding binding = name.resolveTypeBinding();
		return (binding != null) ? getTypeName(binding) : null; 
	}
	
	protected String getTypeName(ITypeBinding typeBinding) {
		StringBuffer name;
		if (typeBinding.isArray()) {
			name= new StringBuffer(getTypeName(typeBinding.getElementType()));
			int dimensions= typeBinding.getDimensions();
			for (int i= 0; i < dimensions; i++) {
				name.append("[]"); //$NON-NLS-1$
			}
			return name.toString();
		}
		name= new StringBuffer(typeBinding.getName());
		IPackageBinding packageBinding= typeBinding.getPackage();
		typeBinding= typeBinding.getDeclaringClass();
		while(typeBinding != null) {
			name.insert(0, '$').insert(0, typeBinding.getName());
			typeBinding= typeBinding.getDeclaringClass();
		}
		if (packageBinding != null && !packageBinding.isUnnamed()) {
			name.insert(0, '.').insert(0, packageBinding.getName());
		}
		return name.toString();
	}
}
