package org.eclipse.jem.internal.java.adapters.jdk;
/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaJDKAdapterFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:37:02 $ 
 */
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory;
import org.eclipse.jem.internal.java.adapters.ReflectionAdaptor;
/**
 * Insert the type's description here.
 * Creation date: (6/13/2000 1:20:31 PM)
 * @author: Administrator
 */
public class JavaJDKAdapterFactory extends JavaReflectionAdapterFactory {
	protected ClassLoader contextClassLoader;
	/**
	 * JavaJDOMAdapterFactory constructor comment.
	 */
	public JavaJDKAdapterFactory() {
		super();
		//adapterKey = TYPE_NAME;
	}
	/**
	 * JavaJDOMAdapterFactory constructor comment.
	 */
	public JavaJDKAdapterFactory(ClassLoader aClassLoader) {
		this();
		setContextClassLoader(aClassLoader);
	}
	protected ReflectionAdaptor createJavaClassAdaptor(Notifier target) {
		return new JavaClassJDKAdaptor(target, this);
	}
	protected ReflectionAdaptor createJavaFieldAdaptor(Notifier target) {
		return new JavaFieldJDKAdaptor(target, this);
	}
	protected ReflectionAdaptor createJavaMethodAdaptor(Notifier target) {
		return new JavaMethodJDKAdaptor(target, this);
	}
	// Flush ALL adapters, worst case
	public void flushAll() {
		isBusyIteratingReflected = true;
		try {
			Iterator i = reflected.values().iterator();
			while (i.hasNext())
				 ((JavaClassJDKAdaptor) i.next()).flushReflectedValuesIfNecessary();
		} finally {
			finishedIteratingReflected();
		}
	}
	// Flush the adapter for a source object
	public void flushReflection(String source) {
		JavaClassJDKAdaptor a = (JavaClassJDKAdaptor) reflected.get(source);
		if (a != null)
			a.flushReflectedValuesIfNecessary();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/07/00 3:38:12 PM)
	 * @return java.lang.ClassLoader
	 */
	public java.lang.ClassLoader getContextClassLoader() {
		return contextClassLoader;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/07/00 3:38:12 PM)
	 * @param newContextClassLoader java.lang.ClassLoader
	 */
	public void setContextClassLoader(java.lang.ClassLoader newContextClassLoader) {
		contextClassLoader = newContextClassLoader;
	}
}
