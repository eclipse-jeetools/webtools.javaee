/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common;

import org.eclipse.jem.java.JavaClass;
/**
 * Declares a class in the application must be registered as a web application listener bean.
 * @since 1.0 */
public interface Listener extends CompatibilityDescriptionGroup{

/**
 * Get the JavaClass for the listener.
 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ListenerClass reference
	 * The listener-class element declares a class in the application must be
	 * registered as a web application listener bean.
	 */
	JavaClass getListenerClass();

/**
 * Get the fully qualified class name for the listener.
 */
public String getListenerClassName();
/**
 * Sets the listener class name.
 */
public void setListenerClassName(String listenerClassName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the ListenerClass reference
	 */
	void setListenerClass(JavaClass value);

}














