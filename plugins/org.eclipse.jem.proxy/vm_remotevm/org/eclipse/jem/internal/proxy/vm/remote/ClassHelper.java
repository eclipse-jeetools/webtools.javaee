package org.eclipse.jem.internal.proxy.vm.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ClassHelper.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:57:54 $ 
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This class is a helper for Class accessing functions.
 * Its main function is to bypass security. This is because
 * we don't care about security for remote vm. Security is
 * handled by the IDE and compilor over there. We do what
 * we're told.
 * 
 * @author richkulp
 */
public class ClassHelper {

	/**
	 * Constructor for ClassHelper.
	 */
	private ClassHelper() {
		super();
	}
	
	private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];	
	public static Object newInstance(Class targetClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
		// We want to get the default ctor, which is in the declared list. No need
		// to worry about inheritance because ctors are never virtual.
		Constructor ctor = targetClass.getDeclaredConstructor(EMPTY_CLASS_ARRAY);
		ctor.setAccessible(true);
		return ctor.newInstance(EMPTY_OBJECT_ARRAY);
	}

}
