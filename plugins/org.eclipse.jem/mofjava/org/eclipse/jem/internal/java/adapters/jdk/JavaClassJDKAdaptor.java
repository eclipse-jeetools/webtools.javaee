/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaClassJDKAdaptor.java,v $
 *  $Revision: 1.14 $  $Date: 2005/09/14 23:30:35 $ 
 */

package org.eclipse.jem.internal.java.adapters.jdk;

import java.util.List;
import java.util.logging.Level;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;


import org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor;
import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
import org.eclipse.jem.java.*;
import org.eclipse.jem.java.internal.impl.JavaClassImpl;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * Reflect the class using standard java.reflect methods.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public class JavaClassJDKAdaptor extends JDKAdaptor implements IJavaClassAdaptor {

	protected Class sourceType = null;

	public JavaClassJDKAdaptor(Notifier target, JavaJDKAdapterFactory anAdapterFactory) {
		super(target, anAdapterFactory);
	}

	/**
	 * addFields - reflect our fields
	 */
	protected void addFields() {
		XMIResource resource = (XMIResource) getJavaClassTarget().eResource();
		List targetFields = getJavaClassTarget().getFieldsGen();
		targetFields.clear();
		java.lang.reflect.Field[] fields = {};
		try {
			fields = getSourceType().getDeclaredFields();
		} catch (NoClassDefFoundError error) {
			System.out.println(ResourceHandler.getString(
					"Could_Not_Reflect_Fields_ERROR_", new Object[] { getJavaClassTarget().getQualifiedName(), error.getMessage()})); //$NON-NLS-1$
		}
		for (int i = 0; i < fields.length; i++) {
			targetFields.add(createJavaField(fields[i], resource));
		}
	}

	/**
	 * addMethods - reflect our methods
	 */
	protected void addMethods() {
		// We need to first do methods and then do constructors because the JDK treats them as two
		// different objects, which the Java Model treats them as both Method's.
		XMIResource resource = (XMIResource) getJavaClassTarget().eResource();
		List targetMethods = getJavaClassTarget().getMethodsGen();
		targetMethods.clear();
		java.lang.reflect.Method[] methods = {};
		try {
			methods = getSourceType().getDeclaredMethods();
		} catch (NoClassDefFoundError error) {
			Logger logger = Logger.getLogger();
			if (logger.isLoggingLevel(Level.WARNING)) {
				logger
						.log(
								ResourceHandler
										.getString(
												"Could_Not_Reflect_Methods_ERROR_", new Object[] { getJavaClassTarget().getQualifiedName(), error.toString()}), Level.WARNING); //$NON-NLS-1$
			}
		}
		for (int i = 0; i < methods.length; i++) {
			targetMethods.add(createJavaMethod(methods[i], resource));
		}

		// Now do the constructors
		java.lang.reflect.Constructor[] ctors = {};
		try {
			ctors = getSourceType().getDeclaredConstructors();
		} catch (NoClassDefFoundError error) {
			Logger logger = Logger.getLogger();
			if (logger.isLoggingLevel(Level.WARNING)) {
				logger
						.log(
								ResourceHandler
										.getString(
												"Could_Not_Reflect_Constructors_ERROR_", new Object[] { getJavaClassTarget().getQualifiedName(), error.getMessage()}), Level.WARNING); //$NON-NLS-1$
			}
		}
		for (int i = 0; i < ctors.length; i++) {
			targetMethods.add(createJavaMethod(ctors[i], resource));
		}

	}

	/**
	 * Clear the reflected fields list.
	 */
	protected boolean flushFields() {
		getJavaClassTarget().getFieldsGen().clear();
		return true;
	}

	/**
	 * Clear the implements list.
	 */
	protected boolean flushImplements() {
		getJavaClassTarget().getImplementsInterfacesGen().clear();
		return true;
	}

	/**
	 * Clear the reflected methods list.
	 */
	protected boolean flushMethods() {
		getJavaClassTarget().getMethodsGen().clear();
		return true;
	}

	protected boolean flushInnerClasses() {
		getJavaClassTarget().getDeclaredClassesGen().clear();
		return true;
	}

	protected boolean flushModifiers() {
		JavaClass javaClassTarget = (JavaClass) getTarget();
		javaClassTarget.setAbstract(false);
		javaClassTarget.setFinal(false);
		javaClassTarget.setPublic(false);
		javaClassTarget.setKind(TypeKind.UNDEFINED_LITERAL);
		return true;
	}

	/**
	 * @see org.eclipse.jem.java.adapters.JavaReflectionAdaptor#flushReflectedValues(boolean)
	 */
	protected boolean flushReflectedValues(boolean clearCachedModelObject) {
		boolean result = flushModifiers();
		result &= flushSuper();
		result &= flushImplements();
		result &= flushMethods();
		result &= flushFields();
		result &= flushInnerClasses();
		return result;
	}

	/**
	 * @see org.eclipse.jem.java.adapters.JavaReflectionAdaptor#postFlushReflectedValuesIfNecessary()
	 */
	protected void postFlushReflectedValuesIfNecessary(boolean isExisting) {
		getJavaClassTarget().setReflected(false);
		super.postFlushReflectedValuesIfNecessary(isExisting);
	}

	/**
	 * Set the supertype to be null.
	 */
	protected boolean flushSuper() {
		List targetSupers = getJavaClassTarget().primGetESuperTypes();
		targetSupers.clear();
		return true;
	}

	/**
	 * Return the target typed to a JavaClass.
	 */
	protected JavaClassImpl getJavaClassTarget() {
		return (JavaClassImpl) getTarget();
	}

	public Object getReflectionSource() {
		return getSourceType();
	}
    /* (non-Javadoc)
     * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#hasReflectionSource()
     */
    public boolean hasCachedReflectionSource() {
        return sourceType != null;
    }

	/**
	 * getSourceType - return the java.lang.Class which describes our existing Java class
	 */
	protected Class getSourceType() {
		if (sourceType == null) {
			sourceType = getType((JavaClass) getTarget());
		}
		return sourceType;
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
	 * Return true if the sourceType is null or if it is a binary type. Reflection from the JDK is always from binary.
	 */
	public boolean isSourceTypeFromBinary() {
		return true;
	}

	/**
	 * reflectValues - template method, subclasses override to pump values into target. on entry: name, containing package (and qualified name), and
	 * document must be set. Return true if successful JavaClass adaptor: - set modifiers - set name - set reference to super - create methods -
	 * create fields - add imports
	 */
	public boolean reflectValues() {
		super.reflectValues();
		try {
			if (getSourceType() != null) {
				setModifiers();
				setNaming();
				try {
					setSuper();
				} catch (InheritanceCycleException e) {
					Logger.getLogger().log(e);
				}
				setImplements();
				addMethods();
				addFields();
				reflectInnerClasses();
				getAdapterFactory().registerReflection(getSourceType().getName(), this);
				//	addImports();
				return true;
			}
			return false;
		} finally {
			//Don't cache the class beyond the operation of reflect values;
			//this enables dynamic swapping of the alternate class loader
			//for java reflection, as well as avoids potential memory leakage
			sourceType = null;
		}
	}

	/**
	 *  
	 */
	protected void reflectInnerClasses() {
		Class[] innerClasses = getSourceType().getClasses();
		if (innerClasses.length != 0) {
			List declaredClasses = getJavaClassTarget().getDeclaredClassesGen();
			JavaClass inner;
			ResourceSet set = getTargetResource().getResourceSet();
			for (int i = 0; i < innerClasses.length; i++) {
				inner = (JavaClass) JavaRefFactory.eINSTANCE.reflectType(innerClasses[i].getName(), set);
				declaredClasses.add(inner);
			}
		}

	}

	/**
	 * setImplements - set our implemented/super interfaces here For an interface, these are superclasses. For a class, these are implemented
	 * interfaces.
	 */
	protected void setImplements() {
		Class[] interfaces = getSourceType().getInterfaces();
		// needs work, the names above will be simple names if we are relfecting from a source file
		JavaClassImpl javaClassTarget = (JavaClassImpl) getTarget();
		JavaClass ref;
		List intList = javaClassTarget.getImplementsInterfacesGen();
		intList.clear();
		for (int i = 0; i < interfaces.length; i++) {
			ref = createJavaClassRef(interfaces[i].getName());
			intList.add(ref);
		}
	}

	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		JavaClass javaClassTarget = (JavaClass) getTarget();
		javaClassTarget.setAbstract(java.lang.reflect.Modifier.isAbstract(getSourceType().getModifiers()));
		javaClassTarget.setFinal(java.lang.reflect.Modifier.isFinal(getSourceType().getModifiers()));
		javaClassTarget.setPublic(java.lang.reflect.Modifier.isPublic(getSourceType().getModifiers()));
		// Set type to class or interface, not yet handling EXCEPTION
		if (getSourceType().isInterface())
			javaClassTarget.setKind(TypeKind.INTERFACE_LITERAL);
		else
			javaClassTarget.setKind(TypeKind.CLASS_LITERAL);
	}

	/**
	 * setNaming - set the naming values here - qualified name (package name + name) must be set first, that is the path to the real Java class - ID -
	 * simple name, identity within a package document - NO UUID!!!
	 */
	protected void setNaming() {
		//	JavaClass javaClassTarget = (JavaClass) getTarget();
		//	javaClassTarget.refSetUUID((String) null);
		//	((XMIResource)javaClassTarget.eResource()).setID(javaClassTarget,getSimpleName(getSourceType().getName()));
	}

	/**
	 * setSuper - set our supertype here, implemented interface are handled separately
	 */
	protected void setSuper() throws InheritanceCycleException {
		Class superClass = null;
		superClass = getSourceType().getSuperclass();
		if (superClass != null) {
			JavaClass javaClassTarget = (JavaClass) getTarget();
			javaClassTarget.setSupertype(createJavaClassRef(superClass.getName()));
		}
	}

	/**
	 * Return true if the sourceType can be found.
	 */
	public boolean sourceTypeExists() {
		return getSourceType() != null;
	}

	public boolean reflectFieldsIfNecessary() {
		return reflectValuesIfNecessary();
	}

	public boolean reflectMethodsIfNecessary() {
		return reflectValuesIfNecessary();
	}
}

