package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: REMPrimitiveBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * Primitive BeanType Proxies
 */
public abstract class REMPrimitiveBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy  {
	
	protected REMPrimitiveBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname) {
		super(aRegistry, anID, aClassname, null);
	}
	
	/**
	 * Return whether this is the primitive Byte.TYPE or Byte.class
	 */
	public boolean isPrimitive() {
		return true;
	}
	
	/**
	 * Return the null ctor proxy for this method with these arguments.
	 * For all of the primitive types, this is an invalid operation.	 
	 */		
	public IConstructorProxy getNullConstructorProxy() {	
		throw new UnsupportedOperationException();
	}
		
	/**
	 * Return a proxy to the constructor for the target VM being the same as the REM
	 * For all of the primitive types, this is an invalid operation.
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames) {	
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return a proxy to the constructor for the target VM being the same as the REM
	 * For all of the primitive types, this is an invalid operation.
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {	
		throw new UnsupportedOperationException();
	}	
	
	/**
	 * Return a proxy to the field for the target VM being the same as the REM
	 * For all of the primitive types, this is an invalid operation.
	 */	
	public IFieldProxy getFieldProxy(String fieldName) {	
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the primitive types, this is an invalid operation.	 
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		throw new UnsupportedOperationException();
	}
		
	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the primitive types, this is an invalid operation.	 
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the primitive types, this is an invalid operation.	 
	 */	
	public IMethodProxy getMethodProxy(String methodName, String argumentQualifiedTypeName) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the primitive types, this is an invalid operation.	 
	 */		
	public IMethodProxy getMethodProxy(String methodName) {	
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Create a new bean proxy with the specified id.
	 *
	 * For all of the primitive types, this is an invalid operation. They are created
	 * explicitly through the appropriate create method.
	 */
	public IREMBeanProxy newBeanProxy(Integer anID) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
	 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
	 * This is because subclasses, unless explicitly overridden, should use the same kind
	 * of beantype proxy. This way if anything special is needed for a specific type, then
	 * that can be done.
	 *
	 * For all of the primitive types, they aren't classes, so you can't create a newBeanType 
	 * for subclasses.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * release - BeanType is about to be released.
	 * primitives don't need to be released, they don't hold onto any ids or resources.
	 */
	public void release() {
	}
	
	/**
	 * Have a string that should represent a number.
	 * See if it ends in a type letter, if so parse as such.
	 * See if there is an 'E' in it, then parse as a double.
	 * If neither condition, then try parse as long, if that 
	 * fails try as double.
	 */
	protected static Number parseString(String v) {
		if (v.length() == 0)
			return null;
		char last = v.charAt(v.length()-1);
		try {
			switch (last) {
				case 'd':
				case 'D':
					return Double.valueOf(v);
				case 'f':
				case 'F':
					return Float.valueOf(v);
				case 'l':
				case 'L':
					return Long.decode(v.substring(0, v.length()-1));
			}
		} catch (NumberFormatException e) {
			return null;
		}
		
		try {
			return Long.decode(v);
		} catch (NumberFormatException e) {
			try {
				return Double.valueOf(v);
			} catch (NumberFormatException e1) {
				return null;
			}
		}
	}
}