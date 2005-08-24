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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMStandardBeanProxyConstants.java,v $
 *  $Revision: 1.9 $  $Date: 2005/08/24 20:39:06 $ 
 */


import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
/**
 * REM Standard Proxy constants.
 *
 * Use the static accessor method (getConstants()) to get the appropriate 
 * constants instance for the registry. This is done so that until the
 * the constants are needed, they aren't registered.
 *
 * There are some standard utility functions too.
 *
 * This is final because this can't be extended. This specific one
 * will be registered with the factory for this key. Any extensions
 * must be done in their own constants and registry key.
 *
 * Creation date: (4/7/00 4:47:48 PM)
 * @author: Administrator
 */
public final class REMStandardBeanProxyConstants {
	public static final Object REGISTRY_KEY = new Object();
		
	private final ProxyFactoryRegistry fRegistry;
	
	private IMethodProxy fClassNewInstance;
	private IMethodProxy fClassGetField;
	private IMethodProxy fClassGetDeclaredField;
	private IMethodProxy fClassGetDeclaredFields;	
	private IMethodProxy fClassGetFields;	
	private IMethodProxy fClassGetConstructor;
	private IMethodProxy fClassGetConstructors;
	private IMethodProxy fClassGetDeclaredConstructor;
	private IMethodProxy fClassGetDeclaredConstructors;
	private IMethodProxy fClassGetMethods;
	private IMethodProxy fClassGetDeclaredMethods;
	private IMethodProxy fClassGetDeclaredMethod;
	
	private IMethodProxy fMethodHelperFindCompatibleConstructor;
	private IMethodProxy fMethodHelperFindCompatibleMethod;
	
	private IMethodProxy fClassIsAssignableFrom;
	
	private IMethodProxy fObjectToString;
	private IMethodProxy fObjectEquals;
	
	private IMethodProxy fThrowableLocalizedMessage;
	private IMethodProxy fThrowableMessage;
	
	private IMethodProxy fMethodDeclaringClassMessage;
	private IMethodProxy fMethodMethodNameMessage;
	private IMethodProxy fMethodParameterTypesMessage;
	private IMethodProxy fMethodReturnTypeMessage;
	
	private IMethodProxy fAccessibleIsAccessible;
	private IMethodProxy fAccessibleSetAccessible;
	
	private IMethodProxy fFieldGet;
	private IMethodProxy fFieldGetType;
	private IMethodProxy fFieldGetByte;
	private IMethodProxy fFieldGetBoolean;
	private IMethodProxy fFieldGetChar;
	private IMethodProxy fFieldGetDouble;
	private IMethodProxy fFieldGetFloat;
	private IMethodProxy fFieldGetInt;
	private IMethodProxy fFieldGetShort;
	private IMethodProxy fFieldGetLong;	
	private IMethodProxy fFieldSet;
	
	private IMethodProxy fConstructorNewInstance;	
	private IMethodProxy fConstructorParameterTypesMessage;
	
	private IMethodProxy fArrayNewInstanceOneDimension;
	private IMethodProxy fArrayNewInstanceMultiDimensions;
	private IMethodProxy fArrayGetLength;
	private IMethodProxy fArrayGet;
	private IMethodProxy fArrayGetByte;
	private IMethodProxy fArrayGetBoolean;
	private IMethodProxy fArrayGetChar;
	private IMethodProxy fArrayGetDouble;
	private IMethodProxy fArrayGetFloat;
	private IMethodProxy fArrayGetInt;
	private IMethodProxy fArrayGetShort;
	private IMethodProxy fArrayGetLong;	
	private IMethodProxy fArraySet;	
	
	private IMethodProxy fArrayHelperGet;
	private IMethodProxy fArrayHelperGetByte;
	private IMethodProxy fArrayHelperGetBoolean;
	private IMethodProxy fArrayHelperGetChar;
	private IMethodProxy fArrayHelperGetDouble;
	private IMethodProxy fArrayHelperGetFloat;
	private IMethodProxy fArrayHelperGetInt;
	private IMethodProxy fArrayHelperGetShort;
	private IMethodProxy fArrayHelperGetLong;	
	private IMethodProxy fArrayHelperSet;			
	
/**
 * Get the constants instance for the specified registry.
 */
public static REMStandardBeanProxyConstants getConstants(ProxyFactoryRegistry registry) {
	REMStandardBeanProxyConstants constants = (REMStandardBeanProxyConstants) registry.getConstants(REGISTRY_KEY);
	if (constants == null)
		registry.registerConstants(REGISTRY_KEY, constants = new REMStandardBeanProxyConstants(registry));	
	return constants;
}


private REMStandardBeanProxyConstants(ProxyFactoryRegistry registry) {
	super();
	fRegistry = registry;
}

public IMethodProxy getClassNewInstance() {
	// We actually need a special new instance because we want to be able to access outside of security. For our purposes security doesn't matter for construction.
	if (fClassNewInstance == null)
		fClassNewInstance = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ClassHelper", "newInstance", new String[] {"java.lang.Class"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassNewInstance;
}

public IMethodProxy getClassGetField() {
	if (fClassGetField == null)
		fClassGetField = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getField", new String[] {"java.lang.String"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassGetField;
}

public IMethodProxy getClassConstructor() {
	if (fClassGetConstructor == null)
		fClassGetConstructor = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getConstructor", new String[] {"[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassGetConstructor;
}

public IMethodProxy getClassConstructors() {
	if (fClassGetConstructors == null)
		fClassGetConstructors = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getConstructors", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetConstructors;
}

public IMethodProxy getDeclaredClassConstructor() {
	if (fClassGetDeclaredConstructor == null)
		fClassGetDeclaredConstructor = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredConstructor", new String[] {"[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassGetDeclaredConstructor;
}

public IMethodProxy getDeclaredClassConstructors() {
	if (fClassGetDeclaredConstructors == null)
		fClassGetDeclaredConstructors = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredConstructors", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetDeclaredConstructors;
}

public IMethodProxy getClassIsAssignableFrom() {
	if (fClassIsAssignableFrom == null)
		fClassIsAssignableFrom = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "isAssignableFrom", new String[] {"java.lang.Class"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassIsAssignableFrom;
}

public IMethodProxy getObjectToString() {
	if (fObjectToString == null)
		fObjectToString = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Object", "toString", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fObjectToString;
}

public IMethodProxy getObjectEquals() {
	if (fObjectEquals == null)
		fObjectEquals = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Object", "equals", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fObjectEquals;
}
public IMethodProxy getThrowableLocalizedMessage() {
	if (fThrowableLocalizedMessage == null)
		fThrowableLocalizedMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Throwable", "getLocalizedMessage", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fThrowableLocalizedMessage;
}

public IMethodProxy getThrowableMessage() {
	if (fThrowableMessage == null)
		fThrowableMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Throwable", "getMessage", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fThrowableMessage;
}

public IMethodProxy getMethodDeclaringClassMessage() {
	if (fMethodDeclaringClassMessage == null)
		fMethodDeclaringClassMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Method", "getDeclaringClass", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fMethodDeclaringClassMessage;
}

public IMethodProxy getMethodMethodNameMessage() {
	if (fMethodMethodNameMessage == null)
		fMethodMethodNameMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Method", "getName", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fMethodMethodNameMessage;
}	

public IMethodProxy getMethodParameterTypesMessage() {
	if (fMethodParameterTypesMessage == null)
		fMethodParameterTypesMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Method", "getParameterTypes", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fMethodParameterTypesMessage;
}

public IMethodProxy getMethodReturnTypeMessage() {
	if (fMethodReturnTypeMessage == null)
		fMethodReturnTypeMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Method", "getReturnType", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fMethodReturnTypeMessage;
}

public IMethodProxy getAccessibleIsAccessible() {
	if (fAccessibleIsAccessible == null)
		fAccessibleIsAccessible = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.AccessibleObject", "isAccessible", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fAccessibleIsAccessible;
}

public IMethodProxy getAccessibleSetAccessible() {
	if (fAccessibleSetAccessible == null)
		fAccessibleSetAccessible = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.AccessibleObject", "setAccessible", new String[] {"boolean"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fAccessibleSetAccessible;
}

public IMethodProxy getFieldGetType() {
	if (fFieldGetType == null)
		fFieldGetType = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getType", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fFieldGetType;
}

public IMethodProxy getFieldGet() {
	if (fFieldGet == null)
		fFieldGet = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "get", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGet;
}

public IMethodProxy getFieldGetByte() {
	if (fFieldGetByte == null)
		fFieldGetByte = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getByte", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetByte;
}

public IMethodProxy getFieldGetBoolean() {
	if (fFieldGetBoolean == null)
		fFieldGetBoolean = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getBoolean", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetBoolean;
}

public IMethodProxy getFieldGetChar() {
	if (fFieldGetChar == null)
		fFieldGetChar = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getChar", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetChar;
}

public IMethodProxy getFieldGetDouble() {
	if (fFieldGetDouble == null)
		fFieldGetDouble = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getDouble", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetDouble;
}

public IMethodProxy getFieldGetFloat() {
	if (fFieldGetFloat == null)
		fFieldGetFloat = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getFloat", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetFloat;
}

public IMethodProxy getFieldGetInt() {
	if (fFieldGetInt == null)
		fFieldGetInt = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getInt", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetInt;
}

public IMethodProxy getFieldGetLong() {
	if (fFieldGetLong == null)
		fFieldGetLong = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getLong", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetLong;
}

public IMethodProxy getFieldGetShort() {
	if (fFieldGetShort == null)
		fFieldGetShort = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "getShort", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fFieldGetShort;
}

public IMethodProxy getFieldSet() {
	if (fFieldSet == null)
		fFieldSet = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Field", "set", new String[] {"java.lang.Object", "java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fFieldSet;
}

public IMethodProxy getConstructorNewInstance() {
	if (fConstructorNewInstance == null)
		fConstructorNewInstance = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Constructor", "newInstance", new String[] {"[Ljava.lang.Object;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fConstructorNewInstance;
}

public IMethodProxy getConstructorParameterTypesMessage() {
	if (fConstructorParameterTypesMessage == null)
		fConstructorParameterTypesMessage = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Constructor", "getParameterTypes", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fConstructorParameterTypesMessage;
}

public IMethodProxy getArrayNewInstanceOneDimension() {
	if (fArrayNewInstanceOneDimension == null)
		fArrayNewInstanceOneDimension = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "newInstance", new String[] {"java.lang.Class", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayNewInstanceOneDimension;
}

public IMethodProxy getArrayNewInstanceMultiDimension() {
	if (fArrayNewInstanceMultiDimensions == null)
		fArrayNewInstanceMultiDimensions = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "newInstance", new String[] {"java.lang.Class", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayNewInstanceMultiDimensions;
}

public IMethodProxy getArrayGetLength() {
	if (fArrayGetLength == null)
		fArrayGetLength = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getLength", new String[] {"java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fArrayGetLength;
}

public IMethodProxy getArrayGet() {
	if (fArrayGet == null)
		fArrayGet = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "get", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGet;
}

public IMethodProxy getArrayGetByte() {
	if (fArrayGetByte == null)
		fArrayGetByte = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getByte", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetByte;
}

public IMethodProxy getArrayGetBoolean() {
	if (fArrayGetBoolean == null)
		fArrayGetBoolean = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getBoolean", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetBoolean;
}

public IMethodProxy getArrayGetChar() {
	if (fArrayGetChar == null)
		fArrayGetChar = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getChar", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetChar;
}

public IMethodProxy getArrayGetDouble() {
	if (fArrayGetDouble == null)
		fArrayGetDouble = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getDouble", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetDouble;
}

public IMethodProxy getArrayGetFloat() {
	if (fArrayGetFloat == null)
		fArrayGetFloat = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getFloat", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetFloat;
}

public IMethodProxy getArrayGetInt() {
	if (fArrayGetInt == null)
		fArrayGetInt = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getInt", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetInt;
}

public IMethodProxy getArrayGetLong() {
	if (fArrayGetLong == null)
		fArrayGetLong = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getLong", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetLong;
}

public IMethodProxy getArrayGetShort() {
	if (fArrayGetShort == null)
		fArrayGetShort = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "getShort", new String[] {"java.lang.Object", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayGetShort;
}

public IMethodProxy getArraySet() {
	if (fArraySet == null)
		fArraySet = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.reflect.Array", "set", new String[] {"java.lang.Object", "int", "java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	return fArraySet;
}

public IMethodProxy getArrayHelperGet() {
	if (fArrayHelperGet == null)
		fArrayHelperGet = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "get", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGet;
}

public IMethodProxy getArrayHelperGetByte() {
	if (fArrayHelperGetByte == null)
		fArrayHelperGetByte = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getByte", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetByte;
}

public IMethodProxy getArrayHelperGetBoolean() {
	if (fArrayHelperGetBoolean == null)
		fArrayHelperGetBoolean = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getBoolean", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetBoolean;
}

public IMethodProxy getArrayHelperGetChar() {
	if (fArrayHelperGetChar == null)
		fArrayHelperGetChar = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getChar", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetChar;
}

public IMethodProxy getArrayHelperGetDouble() {
	if (fArrayHelperGetDouble == null)
		fArrayHelperGetDouble = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getDouble", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetDouble;
}

public IMethodProxy getArrayHelperGetFloat() {
	if (fArrayHelperGetFloat == null)
		fArrayHelperGetFloat = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getFloat", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetFloat;
}

public IMethodProxy getArrayHelperGetInt() {
	if (fArrayHelperGetInt == null)
		fArrayHelperGetInt = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getInt", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetInt;
}

public IMethodProxy getArrayHelperGetLong() {
	if (fArrayHelperGetLong == null)
		fArrayHelperGetLong = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getLong", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetLong;
}

public IMethodProxy getArrayHelperGetShort() {
	if (fArrayHelperGetShort == null)
		fArrayHelperGetShort = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "getShort", new String[] {"java.lang.Object", "[I"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fArrayHelperGetShort;
}

public IMethodProxy getArrayHelperSet() {
	if (fArrayHelperSet == null)
		fArrayHelperSet = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.vm.remote.ArrayHelper", "set", new String[] {"java.lang.Object", "[I", "java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	return fArrayHelperSet;
}


public IMethodProxy getClassGetDeclaredField() {

	if (fClassGetDeclaredField == null)
			fClassGetDeclaredField = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredField", new String[] {"java.lang.String"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fClassGetDeclaredField;
}

public IMethodProxy getClassGetDeclaredFields() {

	if (fClassGetDeclaredFields == null)
			fClassGetDeclaredFields = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredFields", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetDeclaredFields;
}
public IMethodProxy getClassGetFields() {

	if (fClassGetFields == null)
			fClassGetFields = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getFields", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetFields;
}


/**
 * @return
 * 
 * @since 1.1.0
 */
public IMethodProxy getClassMethods() {
	if (fClassGetMethods == null)
		fClassGetMethods = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getMethods", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetMethods;
}

/**
 * @return
 * 
 * @since 1.1.0
 */
public IMethodProxy getClassDeclaredMethods() {
	if (fClassGetDeclaredMethods == null)
		fClassGetDeclaredMethods = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredMethods", null); //$NON-NLS-1$ //$NON-NLS-2$
	return fClassGetDeclaredMethods;
}

public IMethodProxy getClassDeclaredMethod() {
	if (fClassGetDeclaredMethod == null)
		fClassGetDeclaredMethod = fRegistry.getMethodProxyFactory().getMethodProxy("java.lang.Class", "getDeclaredMethod", new String[] {"java.lang.String", "[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fClassGetDeclaredMethod;
}

public IMethodProxy getFindCompatibleConstructorMethod() {
	if (fMethodHelperFindCompatibleConstructor == null)
		fMethodHelperFindCompatibleConstructor = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.common.MethodHelper", "findCompatibleConstructor", new String[] {"java.lang.Class", "[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	return fMethodHelperFindCompatibleConstructor;
}

public IMethodProxy getFindCompatibleMethodMethod() {
	if (fMethodHelperFindCompatibleMethod == null)
		fMethodHelperFindCompatibleMethod = fRegistry.getMethodProxyFactory().getMethodProxy("org.eclipse.jem.internal.proxy.common.MethodHelper", "findCompatibleMethod", new String[] {"java.lang.Class", "java.lang.String", "[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	return fMethodHelperFindCompatibleMethod;
}
}
