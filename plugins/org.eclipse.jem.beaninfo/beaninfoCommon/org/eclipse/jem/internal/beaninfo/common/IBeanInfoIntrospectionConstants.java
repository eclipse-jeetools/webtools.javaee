/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanInfoIntrospectionConstants.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:44:52 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * These are constants needed for transferring BeanInfo results from the BeanInfo VM.
 * @since 1.1.0
 */
public interface IBeanInfoIntrospectionConstants {
	
	/**
	 * Introspection bit flag indicating do the BeanDecorator. Sent to ModelingBeanInfo.introspect method.
	 * @since 1.1.0
	 */
	public static final int DO_BEAN_DECOR = 0x1;
	
	/**
	 * Introspection bit flag indicating do the Properties. Sent to ModelingBeanInfo.introspect method.
	 * @since 1.1.0
	 */
	public static final int DO_PROPERTIES = 0x2;

	/**
	 * Introspection bit flag indicating do the Methods. Sent to ModelingBeanInfo.introspect method.
	 * @since 1.1.0
	 */
	public static final int DO_METHODS = 0x4;

	/**
	 * Introspection bit flag indicating do the Events. Sent to ModelingBeanInfo.introspect method.
	 * @since 1.1.0
	 */
	public static final int DO_EVENTS = 0x8;

	/**
	 * BeanDecorator was sent command id. 
	 * <p>
	 * This will be sent to callBack(int id, InputStream is). The InputStream will be Objects (use ObjectInputStream). 
	 * The stream will be broken into sections.
	 * Each section will be headed by the command id of that section (e.g. BEAN_DECORATOR_SENT or PROPERTY_DECORATORS_SENT).
	 * Following the command id will be the type of input specific data. 
	 * <p>
	 * The data following the command id will be a BeanRecord from the ObjectInputStream.
	 * 
	 * @see BeanRecord
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @since 1.1.0
	 */
	public static final int BEAN_DECORATOR_SENT = 1;

	/**
	 * PropertyDecorators send command id.
	 * <p>
	 * This will be sent to callBack(int id, InputStream is). The InputStream will be Objects (use ObjectInputStream). 
	 * The stream will be broken into sections.
	 * Each section will be headed by the command id of that section (e.g. BEAN_DECORATOR_SENT or PROPERTY_DECORATORS_SENT).
	 * Following the command id will be the type of input specific data. 
	 * <p>
	 * The first object will be  an int and will be the number of properties and each object after that
	 * will be a PropertyRecord/IndexedPropertyRecord. 
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @see PropertyRecord
	 * @see IndexedPropertyRecord
	 */
	public static final int PROPERTY_DECORATORS_SENT = 2;

	/**
	 * MethodDecorators send command id.
	 * <p>
	 * This will be sent to callBack(int id, InputStream is). The InputStream will be Objects (use ObjectInputStream). 
	 * The stream will be broken into sections.
	 * Each section will be headed by the command id of that section (e.g. BEAN_DECORATOR_SENT or PROPERTY_DECORATORS_SENT).
	 * Following the command id will be the type of input specific data. 
	 * <p>
	 * The InputStream will be Objects (use ObjectInputStream).
	 * The first object will be  an int and will be the number of methods and each object after that
	 * will be a MethodRecord. 
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @see MethodRecord
	 */
	public static final int METHOD_DECORATORS_SENT = 3;
	
	/**
	 * EventSetDecorators send command id.
	 * <p>
	 * This will be sent to callBack(int id, InputStream is). The InputStream will be Objects (use ObjectInputStream). 
	 * The stream will be broken into sections.
	 * Each section will be headed by the command id of that section (e.g. BEAN_DECORATOR_SENT or PROPERTY_DECORATORS_SENT).
	 * Following the command id will be the type of input specific data. 
	 * <p>
	 * The first object will be  an int and will be the number of events and each object after that
	 * will be a EventSetRecord. 
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @see MethodRecord
	 */
	public static final int EVENT_DECORATORS_SENT = 4;

	/**
	 * Done send command id.
	 * <p>
	 * This will be sent to callBack(int id, InputStream is). The InputStream will be Objects (use ObjectInputStream). 
	 * The stream will be broken into sections.
	 * Each section will be headed by the command id of that section (e.g. BEAN_DECORATOR_SENT or PROPERTY_DECORATORS_SENT).
	 * Following the command id will be the type of input specific data. 
	 * <p>
	 * This command id means there is no more data and it should return.
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @see MethodRecord
	 */
	public static final int DONE = 5;
	
}
