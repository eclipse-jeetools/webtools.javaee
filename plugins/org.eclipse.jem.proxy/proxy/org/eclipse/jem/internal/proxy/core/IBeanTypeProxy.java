/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanTypeProxy.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/15 22:53:46 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;

/**
 * A proxy for a BeanType (i.e. Java type/class). Creation date: (12/3/99 11:38:06 AM)
 * 
 * @author: Joe Winchester
 */
public interface IBeanTypeProxy extends IBeanProxy {
	
	/**
	 * Find the most compatible constructor (out of the declared constructors). This means it will
	 * find either an exact match or an override that is compatible, e.g. X(Object j) is returned when looking with arg type of "java.lang.String".
	 * @param argumentTypes array of arg types or <code>null</code> if none. (In case of null, this is the default ctor, so just use that).
	 * @return
	 * @throws NoSuchMethodException
	 * @throws AmbiguousMethodException
	 * 
	 * @since 1.1.0
	 */
	public IConstructorProxy getCompatibleConstructor(IBeanTypeProxy[] argumentTypes) throws AmbiguousMethodException, NoSuchMethodException;
	
	/**
	 * Find the most compatible public method, including inheritied. This means it will
	 * find either an exact match or an override that is compatible, e.g. xyz(Object j) is returned when looking with arg type of "java.lang.String".
	 * @param methodName
	 * @param argumentTypes array of arg types or <code>null</code> if none. (In case of null, just use getMethod(String methodName) since only one is compatible then.
	 * @return
	 * @throws AmbiguousMethodException
	 * @throws NoSuchMethodException
	 * 
	 * @since 1.1.0
	 */
	public IMethodProxy getCompatibleMethod(String methodName, IBeanTypeProxy[] argumentTypes)  throws AmbiguousMethodException, NoSuchMethodException;

	/**
	 * Return the constructor proxy on the receiver with the specified arguments Creation date: (12/3/99 2:25:07 PM)
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames);

	/**
	 * Return the constructor proxy on the receiver with the specified types Creation date: (12/3/99 2:25:07 PM)
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes);
	
	/**
	 * Return an array of public constructors for this class. 
	 * 
	 * @return an array of constructor proxies or <code>null</code> if an error.
	 * 
	 * @since 1.1.0
	 */
	public IConstructorProxy[] getConstructors();

	/**
	 * Return the declared constructor proxy on the receiver with the specified arguments Creation date: (12/3/99 2:25:07 PM)
	 */
	public IConstructorProxy getDeclaredConstructorProxy(String[] argumentClassNames);

	/**
	 * Return the declared constructor proxy on the receiver with the specified types Creation date: (12/3/99 2:25:07 PM)
	 */
	public IConstructorProxy getDeclaredConstructorProxy(IBeanTypeProxy[] argumentTypes);

	/**
	 * Return an array of declared constructors for this class. 
	 * 
	 * @return an array of constructor proxies or <code>null</code> if an error.
	 * 
	 * @since 1.1.0
	 */
	public IConstructorProxy[] getDeclaredConstructors();

	/**
	 * Return the array of field proxies.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IFieldProxy[] getFields();

	/**
	 * Return the array of declared field proxies.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IFieldProxy[] getDeclaredFields();

	/**
	 * Return the fieldproxy on the receiver with the specified name Creation date: (12/3/99 2:25:07 PM)
	 */
	public IFieldProxy getFieldProxy(String fieldName);

	/**
	 * Return the declared fieldproxy on the receiver with the specified name
	 */
	public IFieldProxy getDeclaredFieldProxy(String fieldName);

	/**
	 * Return the method proxy on the receiver with the specified name and no arguments.
	 * 
	 * @param methodName
	 * @return 
	 * @since 1.0.0
	 */
	public IMethodProxy getMethodProxy(String methodName);

	/**
	 * Return the method proxy on the receiver with the qualified class names as string arguments
	 * 
	 * @param methodName
	 * @param argumentClassNames
	 * @return 
	 * @since 1.0.0
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames);

	/**
	 * Return the method proxy on the receiver with the specified name and one argument
	 * 
	 * @param methodName
	 * @param argumentClassName
	 * @return 
	 * @since 1.0.0
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentClassName);

	/**
	 * Return the method proxy on the receiver with the beanTypes as arguments
	 * 
	 * @param methodName
	 * @param argumentTypes
	 * @return 
	 * @since 1.0.0
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes);

	/**
	 * Return an array of public methods for this class. 
	 * 
	 * @return an array of method proxies or <code>null</code> if an error.
	 * 
	 * @since 1.1.0
	 */
	public IMethodProxy[] getMethods();

	/**
	 * Return the declared method proxy on the receiver with the specified arguments Creation date: (12/3/99 2:25:07 PM)
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, String[] argumentClassNames);

	/**
	 * Return the declared method proxy on the receiver with the specified types Creation date: (12/3/99 2:25:07 PM)
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes);

	/**
	 * Return an array of declared methods for this class. 
	 * 
	 * @return an array of method proxies or <code>null</code> if an error.
	 * 
	 * @since 1.1.0
	 */
	public IMethodProxy[] getDeclaredMethods();

	/**
	 * Return the invokable on the receiver with the specified name and no arguments.
	 * <p>
	 * The method proxy is not retrieved. Instead the method will be looked up
	 * each time on the vm. Because of this these are suitable only for one-shot invokations. If it is to be invoked often, then a method proxy should
	 * be retrieved instead.
	 * <p>
	 * Though typical for one-shot deal the invokable can be used over and over. There is just overhead because the method is looked up each time. So a reasonable compromise
	 * would be if using it infrequently or is used closely together once or twice it is better to use a Invokable instead of a method proxy.
	 * <p>
	 * Note there is no guarantee that the method is available. This won't be known until the actual invoke is done.
	 * 
	 * @param methodName
	 * @return 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(String methodName);

	/**
	 * Return the method proxy on the receiver with the qualified class names as string arguments
	 * <p>
	 * The method proxy is not retrieved. Instead the method will be looked up
	 * each time on the vm. Because of this these are suitable only for one-shot invokations. If it is to be invoked often, then a method proxy should
	 * be retrieved instead.
	 * <p>
	 * Though typical for one-shot deal the invokable can be used over and over. There is just overhead because the method is looked up each time. So a reasonable compromise
	 * would be if using it infrequently or is used closely together once or twice it is better to use a Invokable instead of a method proxy.
	 * <p>
	 * Note there is no guarantee that the method is available. This won't be known until the actual invoke is done.
	 * 
	 * @param methodName
	 * @param argumentClassNames
	 * @return 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(String methodName, String[] argumentClassNames);

	/**
	 * Return the method proxy on the receiver with the specified name and one argument
	 * <p>
	 * The method proxy is not retrieved. Instead the method will be looked up
	 * each time on the vm. Because of this these are suitable only for one-shot invokations. If it is to be invoked often, then a method proxy should
	 * be retrieved instead.
	 * <p>
	 * Though typical for one-shot deal the invokable can be used over and over. There is just overhead because the method is looked up each time. So a reasonable compromise
	 * would be if using it infrequently or is used closely together once or twice it is better to use a Invokable instead of a method proxy.
	 * <p>
	 * Note there is no guarantee that the method is available. This won't be known until the actual invoke is done.
	 * 
	 * @param methodName
	 * @param argumentClassName
	 * @return 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(String methodName, String argumentClassName);

	/**
	 * Return the method proxy on the receiver with the beanTypes as arguments
	 * <p>
	 * The method proxy is not retrieved. Instead the method will be looked up
	 * each time on the vm. Because of this these are suitable only for one-shot invokations. If it is to be invoked often, then a method proxy should
	 * be retrieved instead.
	 * <p>
	 * Though typical for one-shot deal the invokable can be used over and over. There is just overhead because the method is looked up each time. So a reasonable compromise
	 * would be if using it infrequently or is used closely together once or twice it is better to use a Invokable instead of a method proxy.
	 * <p>
	 * Note there is no guarantee that the method is available. This won't be known until the actual invoke is done.
	 * 
	 * @param methodName
	 * @param argumentTypes
	 * @return 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(String methodName, IBeanTypeProxy[] argumentTypes);
	
	/**
	 * Return the constructor proxy on the receiver with no arguments Creation date: (12/3/99 2:25:07 PM)
	 */
	public IConstructorProxy getNullConstructorProxy();

	/**
	 * Answer the type proxy for the superclass Creation date: (12/3/99 2:25:07 PM)
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy();

	/**
	 * Answer the name of the type we are proxying This is the fully qualified name. For arrays it will return the format: [Lclassname; Creation date:
	 * (12/3/99 2:25:07 PM)
	 */
	public String getTypeName();

	/**
	 * Answer the formal format type name. For normal classes, this just the same as getTypeName(), but for arrays, it is of the format classname[]
	 */
	public String getFormalTypeName();

	/**
	 * Answer a boolean as to whether we are an array type.
	 */
	public boolean isArray();

	/**
	 * Answer a boolean as to whether we are a type or an interface Creation date: (12/3/99 2:25:07 PM)
	 */
	public boolean isInterface();

	/**
	 * Answer a boolean as to whether we are a primitive or not.
	 */
	public boolean isPrimitive();

	/**
	 * Answer a boolean as to whether we are a kind of the argument We can either be it, inherit from it, or implement it
	 */
	public boolean isKindOf(IBeanTypeProxy aBeanProxyType);

	/**
	 * Return a new instance Creation date: (12/3/99 2:25:07 PM)
	 */
	public IBeanProxy newInstance() throws ThrowableProxy;

	/**
	 * Return a new instance of this type using the initialization string to create the proxy. ClassCastException is thrown if the initstring doesn't
	 * result in an object compatible with this type. InstantiationException is thrown when the initialization string cannot be parsed correctly.
	 * Creation date: (12/3/99 2:25:07 PM)
	 */
	public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException;

	/**
	 * Return the exception message that happened when trying to find this bean type. Class not found is not such an exception. In that case the bean
	 * type will be returned as a null instead. An example of such is an initialization error during loading the class, i.e. it was found but some
	 * static failed to initialize.
	 * 
	 * Return null if there is no initialization error.
	 */
	public String getInitializationError();
}
