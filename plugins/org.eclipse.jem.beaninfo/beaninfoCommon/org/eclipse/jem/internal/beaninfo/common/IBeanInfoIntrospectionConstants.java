/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanInfoIntrospectionConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/04 23:11:53 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * These are constants needed for transferring BeanInfo results from the BeanInfo VM.
 * @since 1.1.0
 */
public interface IBeanInfoIntrospectionConstants {
	/**
	 * BeanDecorator was sent command id. 
	 * <p>
	 * This will be sent to callBack(int id, Object parm) as the id. 
	 * The parm will be a BeanRecord.
	 * We are using this callback instead of a stream because the
	 * size is not that big and is only one object that is serialized.
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, Object)
	 * @see BeanRecord
	 * @since 1.1.0
	 */
	public static final int BEAN_DECORATOR_SENT = 1;

	/**
	 * PropertyDecorators send command id.
	 * <p>
	 * This will be sent to the callBack(int id, InputStream is) as the id.
	 * The InputStream will be Objects (use ObjectInputStream).
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
	 * This will be sent to the callBack(int id, InputStream is) as the id.
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
	 * This will be sent to the callBack(int id, InputStream is) as the id.
	 * The InputStream will be Objects (use ObjectInputStream).
	 * The first object will be  an int and will be the number of events and each object after that
	 * will be a EventSetRecord. 
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, InputStream)
	 * @see MethodRecord
	 */
	public static final int EVENT_DECORATORS_SENT = 4;
	
}
