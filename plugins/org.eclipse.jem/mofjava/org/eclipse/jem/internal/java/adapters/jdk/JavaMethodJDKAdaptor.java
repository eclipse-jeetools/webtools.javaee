/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters.jdk;
/*
 *  $RCSfile: JavaMethodJDKAdaptor.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/09 19:04:04 $ 
 */

import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.*;
import org.eclipse.jem.internal.java.adapters.IJavaMethodAdapter;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.impl.MethodImpl;
/**
 * Insert the type's description here.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public class JavaMethodJDKAdaptor extends JDKAdaptor implements IJavaMethodAdapter {
	protected java.lang.reflect.AccessibleObject sourceAccessible = null; // Could be method or ctor.
	protected Class parentType = null;
	// cache a static empty Class[] for no parm methods
	protected static Class[] emptyClassArray = new Class[0];
	// cache a static empty String[], too
	protected static String[] emptyStringArray = new String[0];
	public JavaMethodJDKAdaptor(Notifier target, JavaJDKAdapterFactory anAdapterFactory) {
		super(target, anAdapterFactory);
	}
	/**
	 * addExceptions - reflect our exception list
	 */
	protected void addExceptions() {
		Class[] exceptions =
			(getSourceAccessible() instanceof java.lang.reflect.Method)
				? ((java.lang.reflect.Method) getSourceAccessible()).getExceptionTypes()
				: ((java.lang.reflect.Constructor) getSourceAccessible()).getExceptionTypes();
		//	EList exList = (EList) javaMethodTarget.primRefValue(JavaRefPackage.eINSTANCE.getMethod_JavaExceptions());
		List exList = getMethodTarget().getJavaExceptionsGen();
		for (int i = 0; i < exceptions.length; i++) {
			exList.add(createJavaClassRef(exceptions[i].getName()));
		}
	}

	protected MethodImpl getMethodTarget() {
		return (MethodImpl) getTarget();
	}
	/**
	 * addParameters - reflect our parms
	 */
	protected void addParameters() {
		Class[] parmTypes =
			(getSourceAccessible() instanceof java.lang.reflect.Method)
				? ((java.lang.reflect.Method) getSourceAccessible()).getParameterTypes()
				: ((java.lang.reflect.Constructor) getSourceAccessible()).getParameterTypes();
		MethodImpl javaMethodTarget = getMethodTarget();
		// 	List pList = (List) javaMethodTarget.primRefValue(JavaRefPackage.eINSTANCE.getMethod_Parameters());
		List pList = javaMethodTarget.getParametersGen();
		for (int i = 0; i < parmTypes.length; i++) {
			pList.add(createJavaParameter(javaMethodTarget, "arg" + i, getTypeName(parmTypes[i]))); //$NON-NLS-1$
		}
	}
	protected JavaClass getContainingJavaClass() {
		return ((Method) getTarget()).getContainingJavaClass();
	}
	/**
	 * getParentType - return the Class which corresponds to our parent JavaClass
	 * we're going to do this a lot, so cache it.
	 */
	protected Class getParentType() {
		if (parentType == null) {
			Method targetMethod = (Method) getTarget();
			JavaClass parentJavaClass = targetMethod.getContainingJavaClass();
			JavaClassJDKAdaptor pa = (JavaClassJDKAdaptor) EcoreUtil.getAdapter(parentJavaClass.eAdapters(), ReadAdaptor.TYPE_KEY);
			if (pa != null)
				parentType = pa.getSourceType();
		}
		return parentType;
	}
	/**
	 * getParmTypeSignatures - return an array of Classes for our parameter types
	 * 	For reflection purposes, we can only rely on our ID, since our parms may
	 *  not yet be known.
	 */
	protected Class[] getParmTypes() {
		Method javaMethodTarget = (Method) getTarget();
		String id = ((XMIResource) javaMethodTarget.eResource()).getID(javaMethodTarget);
		String[] typeNames = getTypeNamesFromMethodID(id);
		if (typeNames == null)
			return emptyClassArray;
		int n = typeNames.length;
		if (n == 0)
			return emptyClassArray;
		Class[] types = new Class[n];
		for (int i = 0; i < n; ++i) {
			types[i] = getType(typeNames[i]);
		}
		return types;
	}
	public Object getReflectionSource() {
		return getSourceAccessible();
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#hasReflectionSource()
     */
    public boolean hasCachedReflectionSource() { 
        return sourceAccessible != null;
    }
	/**
	 * getsourceMethod - return the java.lang.reflect.Method which describes our implementing method
	 */
	protected java.lang.reflect.AccessibleObject getSourceAccessible() {
		if (sourceAccessible == null) {
			Class parent = this.getParentType();
			if (parent != null) {
				Class[] parmTypes = this.getParmTypes();
				try {
					sourceAccessible = parent.getDeclaredMethod(((Method) getTarget()).getName(), parmTypes);
				} catch (NoSuchMethodException e) {
					// OK, can't reflect it
				}

				if (sourceAccessible == null) {
					// It wasn't a method, try for constructor.
					try {
						sourceAccessible = parent.getDeclaredConstructor(parmTypes);
					} catch (NoSuchMethodException e) {
						// OK, can't reflect it
					}
				}
			}
		}
		return sourceAccessible;
	}
	/**
	 * getValueIn method comment.
	 */
	public Object getValueIn(EObject object, EObject attribute) {
		// At this point, this adapter does not dynamically compute any values,
		// all values are pushed back into the target on the initial call.
		return super.getValueIn(object, attribute);
	}
	/**
	 * reflectValues - template method, subclasses override to pump values into target.
	 * on entry: UUID, name, containing package (and qualified name), and document must be set.
	 * Method adaptor:
	 *	- set modifiers
	 *	- set name
	 * 	- set return type
	 * 	- add parameters
	 * 	- add exceptions
	 */
	public boolean reflectValues() {
		if (getSourceAccessible() != null) {
			((Method) getTarget()).setIsGenerated(false);
			setModifiers();
			setNaming();
			setReturnType();
			addParameters();
			addExceptions();
			return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaMethodAdapter#reflectGeneratedIfNecessary()
	 */
	public boolean reflectGeneratedIfNecessary() {
		return reflectValuesIfNecessary();
	}
	
	public boolean reflectParamNamesIfNecessary() {
		return reflectValuesIfNecessary();
	}
	
	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		Method methodTarget = (Method) getTarget();
		int modifiers =
			(getSourceAccessible() instanceof java.lang.reflect.Method)
				? ((java.lang.reflect.Method) getSourceAccessible()).getModifiers()
				: ((java.lang.reflect.Constructor) getSourceAccessible()).getModifiers();
		methodTarget.setAbstract(java.lang.reflect.Modifier.isAbstract(modifiers));
		methodTarget.setFinal(java.lang.reflect.Modifier.isFinal(modifiers));
		methodTarget.setNative(java.lang.reflect.Modifier.isNative(modifiers));
		methodTarget.setStatic(java.lang.reflect.Modifier.isStatic(modifiers));
		methodTarget.setSynchronized(java.lang.reflect.Modifier.isSynchronized(modifiers));
		methodTarget.setConstructor(getSourceAccessible() instanceof java.lang.reflect.Constructor);
		// Set visibility
		JavaClass javaClass = getContainingJavaClass();
		if ((javaClass.getKind() == TypeKind.INTERFACE_LITERAL) || (java.lang.reflect.Modifier.isPublic(modifiers)))
			methodTarget.setJavaVisibility(JavaVisibilityKind.PUBLIC_LITERAL);
		else if (java.lang.reflect.Modifier.isPrivate(modifiers))
			methodTarget.setJavaVisibility(JavaVisibilityKind.PRIVATE_LITERAL);
		else if (java.lang.reflect.Modifier.isProtected(modifiers))
			methodTarget.setJavaVisibility(JavaVisibilityKind.PROTECTED_LITERAL);
		else
			//Visibility must be package
			methodTarget.setJavaVisibility(JavaVisibilityKind.PACKAGE_LITERAL);
	}
	/**
	 * setNaming - set the naming values here
	 * 	- qualified name must be set first, that is the path to the real Java class
	 *	- ID
	 * 	- name-based UUID
	 */
	protected void setNaming() {
		//
		//	naming is currently a no-op since the name and UUID must be set prior to reflection
		//	...and ID is redundant with UUID.
		//	javaFieldTarget.setID(parent.getQualifiedName() + "_" + javaFieldTarget.getName());
	}
	/**
	 * setType - set our return type here
	 */
	protected void setReturnType() {
		if (getSourceAccessible() instanceof java.lang.reflect.Method) {
			Class type = ((java.lang.reflect.Method) getSourceAccessible()).getReturnType();
			Method javaMethodTarget = (Method) getTarget();
			/*
					JavaParameter newParameter = createJavaParameter(javaMethodTarget, "result", getTypeName(type));//$NON-NLS-1$
					newParameter.setParameterKind(MetaJavaParameterKind.RETURN);
					javaMethodTarget.getParameters().add(newParameter);
			*/
			javaMethodTarget.setEType(createJavaClassRef(getTypeName(type)));
		}
	}
}
