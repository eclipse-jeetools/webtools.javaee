/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CONSTRUCTOR;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.MODIFIER_ABSTRACT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.MODIFIER_FINAL;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.MODIFIER_PUBLIC;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;

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
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateJavaEEArtifactTemplateModel { 
	
	public static final String ATT_NAME = "name"; //$NON-NLS-1$
	public static final String ATT_DESCRIPTION = "description"; //$NON-NLS-1$
	
	/**
	 * Constant representing no compatibility flag.
	 */
	public static final int FLAG_NONE = 0x00000000;
	
	/**
	 * Constant representing the <i>Qualified Superclass Name</i> compatibility
	 * flag.
	 * 
	 * <p>
	 * When this flag is set then the {@link #getSuperclassName()} method always
	 * returns the qualified name of the superclass.
	 * </p>
	 * 
	 * @see #getSuperclassName()
	 */
	public static final int FLAG_QUALIFIED_SUPERCLASS_NAME = 0x00000001;
	
	/**
	 * Constant representing a combination of all possible compatibility flags.
	 */
	public static final int FLAG_ALL = 0xffffffff;
	
	/**
	 * Compatibility flags. 
	 * 
	 * @see #addFlags(int)
	 * @see #removeFlags(int)
	 * @see #FLAG_NONE
	 * @see #FLAG_QUALIFIED_SUPERCLASS_NAME
	 * @see #FLAG_ALL
	 */
	protected int flags; 
	
	protected IDataModel dataModel;
	
	public CreateJavaEEArtifactTemplateModel(IDataModel dataModel) {
		this.dataModel = dataModel;
		this.flags = FLAG_QUALIFIED_SUPERCLASS_NAME;
	}
	
	/**
	 * Adds compatibility flags represented by the given bitmask.
	 * 
	 * @param bitmask -
	 *            represents the flags to add.
	 *            
	 * @see #addFlags(int)
	 * @see #removeFlags(int)
	 * @see #FLAG_NONE
	 * @see #FLAG_QUALIFIED_SUPERCLASS_NAME
	 * @see #FLAG_ALL
	 */
	public void addFlags(int bitmask) {
		flags = flags | bitmask;
	}
	
	/**
	 * Removes compatibility flags represented by the given bitmask.
	 * 
	 * @param bitmask -
	 *            represents the flags to remove.
	 *            
	 * @see #addFlags(int)
	 * @see #removeFlags(int)
	 * @see #FLAG_NONE
	 * @see #FLAG_QUALIFIED_SUPERCLASS_NAME
	 * @see #FLAG_ALL
	 */
	public void removeFlags(int bitmask) {
		flags = flags & ~bitmask;
	}
	
	/**
	 * Check if compatibility flags, represented by the given bitmask, are set.
	 * 
	 * @param bitmask -
	 *            represents the flags to check.
	 * 
	 * @return <code>true</code> - if all of the given flags are set,
	 *         <code>false</code> - if any of the given flags is not set.  
	 *            
	 * @see #addFlags(int)
	 * @see #removeFlags(int)
	 * @see #FLAG_NONE
	 * @see #FLAG_QUALIFIED_SUPERCLASS_NAME
	 * @see #FLAG_ALL
	 */
	public boolean areFlagsSet(int bitmask) {
		return (flags & bitmask) != 0;
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = new ImportsCollection(this);
		
		String className = getClassName();
		String superclassName = getQualifiedSuperclassName();

		if (superclassName != null && superclassName.length() > 0 &&
				!areFlagsSet(FLAG_QUALIFIED_SUPERCLASS_NAME) && 
				!equalSimpleNames(className, superclassName)) {
			collection.add(superclassName);
		}
		
		List<String> interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			for (String iface : interfaces) {
				if (!equalSimpleNames(getClassName(), iface)) { 
					collection.add(iface);
				}
			}
		}
		
		List<Constructor> constructors = getConstructors();
		for (Constructor constructor : constructors) {
			List<String> types = constructor.getNonPrimitiveParameterTypes();
			for (String type : types) {
				collection.add(type);
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
		if (areFlagsSet(FLAG_QUALIFIED_SUPERCLASS_NAME) || equalSimpleNames(getClassName(), qualified)) {
			return qualified;
		}
		return Signature.getSimpleName(qualified);
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
		return (interfaces == null) ? new ArrayList<String>() : interfaces;
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
	
	public boolean shouldGenSuperclassConstructors() {
		return dataModel.getBooleanProperty(CONSTRUCTOR);
	}
    
    public boolean shouldImplementAbstractMethods(){
		return dataModel.getBooleanProperty(ABSTRACT_METHODS);
	}

    public boolean hasEmptySuperclassConstructor() {
    	List<Constructor> constructors = getConstructors();
    	for (Constructor constructor : constructors) {
    		if (constructor.isParameterless())
    			return true;
    	}
        
    	return false;
	}
	
    public List<Constructor> getConstructors() {
        List<Constructor> constrs = new ArrayList<Constructor>();
        
        String superclass = dataModel.getStringProperty(SUPERCLASS);
        if (superclass != null && superclass.length() > 0) {
            IProject p = (IProject) dataModel.getProperty(PROJECT);
            IJavaProject javaProject = JavaCore.create(p);
            if (javaProject != null) {
                try {
                    IType type = javaProject.findType(superclass);
                    if (type != null) {
	                    if (type.isBinary()) {
	                        IMethod[] methods = type.getMethods();
	                        for (IMethod method : methods) {
	                            if (method.isConstructor()) 
	                                constrs.add(new BinaryConstructor(method));
	                        }
	                    } else {
	                    	ICompilationUnit compilationUnit = type.getCompilationUnit();
	                        TypeDeclaration declarationFromType = getTypeDeclarationFromType(superclass, compilationUnit);
	                        if (declarationFromType != null) {
	                            MethodDeclaration[] methods = declarationFromType.getMethods();
	                            for (MethodDeclaration method : methods) {
	                                if (method.isConstructor()) 
	                                    constrs.add(new SourceConstructor(method));
	                            }
	                        }
	                    }
                    }
                } catch (JavaModelException e) {
                	J2EEPlugin.logError(e);
                }
            }
        }
        
        return constrs;
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
        			J2EEPlugin.logError(e);
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
    	IProject p = (IProject) dataModel.getProperty(PROJECT);
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
    
}
