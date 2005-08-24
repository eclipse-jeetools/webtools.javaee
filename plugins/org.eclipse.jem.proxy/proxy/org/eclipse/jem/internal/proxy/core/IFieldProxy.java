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
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IFieldProxy.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:39:06 $ 
 */


/**
 * A Proxy to a field that allows the field value to be retrieved and set
 * This allows target VM proxying of the field and is analagous to the java.lang.Reflect.Field
 * in the same way IMethodProxy is analagous to java.lang.Reflect.Method
 * Creation date: (1/17/00 12:17:52 PM)
 * @author: Joe Winchester
 */
public interface IFieldProxy extends IAccessibleObjectProxy, IProxyField {
/**
 * Return the type of the field.
 */
IBeanTypeProxy getFieldType();

/**
 * Return the value of us on the subject argument.
 * If the field is a primitive type, the return proxy
 * will be of the primitive type too.  
 * Creation date: (1/17/00 12:28:48 PM)
 */
IBeanProxy get(IBeanProxy aSubject) throws ThrowableProxy;

/**
 * Set the argument as the field value on the subject
 * Creation date: (1/17/00 12:28:48 PM)
 */
void set(IBeanProxy aSubject, IBeanProxy argument) throws ThrowableProxy;

}
