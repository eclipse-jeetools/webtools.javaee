package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IFieldProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/12 21:44:26 $ 
 */


/**
 * A Proxy to a field that allows the field value to be retrieved and set
 * This allows target VM proxying of the field and is analagous to the java.lang.Reflect.Field
 * in the same way IMethodProxy is analagous to java.lang.Reflect.Method
 * Creation date: (1/17/00 12:17:52 PM)
 * @author: Joe Winchester
 */
public interface IFieldProxy extends IAccessibleObjectProxy {
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
