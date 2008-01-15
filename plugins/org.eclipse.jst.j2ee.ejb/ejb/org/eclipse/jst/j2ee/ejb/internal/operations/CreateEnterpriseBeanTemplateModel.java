/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateEnterpriseBeanTemplateModel implements INewJavaClassDataModelProperties, INewMessageDrivenBeanClassDataModelProperties {
	
	protected static final String ATT_MAPPED_NAME = "mappedName"; //$NON-NLS-1$
	protected static final String ATT_NAME = "name"; //$NON-NLS-1$
	
	protected static final String QUOTATION_STRING = "\"";
	
	protected IDataModel dataModel;
	
	public CreateEnterpriseBeanTemplateModel(IDataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = new ImportsCollection(this);
		
		String className = getClassName();
		String superclassName = getQualifiedSuperclassName();

		if (superclassName != null && superclassName.length() > 0 &&
				!equalSimpleNames(className, superclassName)) 
			collection.add(superclassName);
		
		List<String> interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			for (String iface : interfaces) {
				if (!equalSimpleNames(getClassName(), iface)) 
					collection.add(iface);
			}
		}
		
		Collection<Method> methods = getUnimplementedMethods();
		for (Method method : methods) {
			collection.addAll(method.getParameterImports());
			collection.addAll(method.getReturnTypeImports());
		}
		
		return collection;
	}

	public String getClassName() {
		return getProperty(CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(JAVA_PACKAGE).trim();
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		String qualified = getQualifiedSuperclassName();
		if (equalSimpleNames(getClassName(), qualified)) {
			return qualified;
		} else {
			return Signature.getSimpleName(qualified);
		}
	}
	
	public String getQualifiedSuperclassName() {
		return getProperty(SUPERCLASS).trim();
	}
	
	public List<String> getInterfaces() {
		List<String> qualifiedInterfaces = getQualifiedInterfaces();
		List<String> interfaces = new ArrayList<String>(qualifiedInterfaces.size());
		
		for (String qualified : qualifiedInterfaces) {
			if (equalSimpleNames(getClassName(), qualified)) {
				interfaces.add(qualified);
			} else {
				interfaces.add(Signature.getSimpleName(qualified));
			}
		}
		
		return interfaces;
	}

	public List<String> getQualifiedInterfaces() {
		List<String> interfaces = (List<String>) dataModel.getProperty(INTERFACES);
		if (interfaces == null) {
			return new ArrayList<String>();
		} else
			return interfaces;
	}
	
	public boolean isPublic() {
		return dataModel.getBooleanProperty(MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return dataModel.getBooleanProperty(MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return dataModel.getBooleanProperty(MODIFIER_ABSTRACT);
	}
	
	public Collection<Method> getUnimplementedMethods() {
        Collection<Method> unimplementedMethods = new HashSet<Method>();
        
        if (shouldImplementAbstractMethods()) {
        	IJavaProject javaProject = getJavaProject();
    	    List<String> interfaces = getQualifiedInterfaces();
	        for (String iface : interfaces) {
        		try {
		        	IType type = javaProject.findType(iface);
		        	if (type != null)
	        			getUnimplementedMethod0(type, unimplementedMethods);
        		} catch (JavaModelException e) {
        	        Logger.getLogger().log(e);
    	        }
	        }
        }
        
        return unimplementedMethods;
    }
	
	private void getUnimplementedMethod0(IType type, Collection<Method> unimplementedMethods) throws JavaModelException {
		IJavaProject javaProject = getJavaProject();
		if (type.isBinary()) {
		    IMethod[] methods = type.getMethods();
		    for (IMethod method : methods) {
		    	unimplementedMethods.add(new BinaryMethod(method));
		    }
		    
		    // process super interfaces
		    String[] superInterfaces = type.getSuperInterfaceNames();
			for (String superInterface : superInterfaces) {
				IType superInterfaceType = javaProject.findType(superInterface);
				if (superInterfaceType != null) 
					getUnimplementedMethod0(superInterfaceType, unimplementedMethods);
			}
		} else {
			ICompilationUnit compilationUnit = type.getCompilationUnit();
		    TypeDeclaration declarationFromType = getTypeDeclarationFromType(type.getFullyQualifiedName(), compilationUnit);
		    if (declarationFromType != null) {
		        MethodDeclaration[] methods = declarationFromType.getMethods();
		        for (MethodDeclaration method : methods) {
		        	unimplementedMethods.add(new SourceMethod(method));
		        }
		    }
		    
		    // process super interfaces
		    List<Type> superInterfaces = declarationFromType.superInterfaceTypes();
		    for (Type superInterface : superInterfaces) {
		    	ITypeBinding binding = superInterface.resolveBinding();
		    	IType superInterfaceType = javaProject.findType(binding.getQualifiedName());
				if (superInterfaceType != null) 
					getUnimplementedMethod0(superInterfaceType, unimplementedMethods);
		    }
		}
	}

	protected String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
	}

	protected boolean equalSimpleNames(String name1, String name2) {
		String simpleName1 = Signature.getSimpleName(name1);
		String simpleName2 = Signature.getSimpleName(name2);
		return simpleName1.equals(simpleName2);
	}
    
    protected IJavaProject getJavaProject() {
    	IProject p = (IProject) dataModel.getProperty(INewJavaClassDataModelProperties.PROJECT);
        return JavaCore.create(p);
    }
    
    private TypeDeclaration getTypeDeclarationFromType(String typeName, ICompilationUnit unit) {
        CompilationUnit cu = (CompilationUnit) parse(unit);
        Iterator iterator = cu.types().iterator();
        while (iterator.hasNext()) {
        	Object obj = iterator.next();
        	if (obj instanceof TypeDeclaration) {
	            TypeDeclaration declaration = (TypeDeclaration) obj;
	            ITypeBinding tb = declaration.resolveBinding();
	            if (tb != null) {
	                String declarationName = tb.getQualifiedName();
	                if (typeName.equals(declarationName)) {
	                    return declaration;
	                }
	            }
        	}
        }

        return null;
    }
    
    private ASTNode parse(ICompilationUnit unit) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(unit);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        return parser.createAST(null);
    }

    public boolean shouldImplementAbstractMethods(){
		return dataModel.getBooleanProperty(ABSTRACT_METHODS);
	}
    
    public boolean isContainerType() {
		int transactionType = dataModel.getIntProperty(TRANSACTION_TYPE);
		return transactionType == NewSessionBeanClassDataModelProvider.TRANSACTION_TYPE_CONTAINER_INDEX;
	}
}
