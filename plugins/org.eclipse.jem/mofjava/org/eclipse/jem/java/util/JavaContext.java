/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaContext.java,v $
 *  $Revision: 1.1 $  $Date: 2006/01/13 23:46:51 $ 
 */
package org.eclipse.jem.java.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;

/**
 * Static helper class to handle the java context. The java context is a resource set
 * that has the appropriate reflection adapter attached, either for java reflection or
 * for JDT reflection. The default is for java reflection. When running in Eclipse it
 * will be the JDT reflection.
 * <p>
 * <b>Note:</b>This should only be used by customers that are not doing normal Eclipse JDT JEM
 * reflection. Normal JEM should be using the JEM EMF nature access instead.
 * @since 1.2.0
 */
public class JavaContext {

	private static Class ReflectionFactoryClass = JavaJDKAdapterFactory.class;
	
	private JavaContext() {
		
	}
	
	/**
	 * Create the java context. To be used outside of Eclipse.
	 * Within normal Eclipse JEM usage the JEM EMF nature should be used instead.
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public static ResourceSet createJavaContext() {
		org.eclipse.jem.internal.java.init.JavaInit.init();
		ResourceSet set = new ResourceSetImpl();
		set.getAdapterFactories().add(createJavaReflectionAdapterFactory());
		return set;
	}
	
	private static AdapterFactory createJavaReflectionAdapterFactory() {
		AdapterFactory factoryInstance = null;
		try {
			if (getReflectionAdapterFactoryClass() != null)
				factoryInstance = (AdapterFactory) getReflectionAdapterFactoryClass().newInstance();
		} catch (Exception e) {
			// Reflection or instantiation problems.
			// OK, can't do Java Model reflection
		}
		return factoryInstance;
	}

	/**
	 * Return the Class for the Java refection adapter factory.
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public static Class getReflectionAdapterFactoryClass() {
		return ReflectionFactoryClass;
	}

	/**
	 * Set the Class for the Java refection adapter factory. Within Eclipse this should not
	 * be set by customers.
	 * 
	 * @param javaReflectionFactoryClass
	 * 
	 * @since 1.2.0
	 */
	public static void setReflectionAdapterFactoryClass(Class javaReflectionFactoryClass) {
		ReflectionFactoryClass = javaReflectionFactoryClass;
	}
	

}
