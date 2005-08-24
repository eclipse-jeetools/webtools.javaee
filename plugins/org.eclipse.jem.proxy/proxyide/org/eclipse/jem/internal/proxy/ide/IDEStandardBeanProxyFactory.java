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
package org.eclipse.jem.internal.proxy.ide;
/*
 * $RCSfile: IDEStandardBeanProxyFactory.java,v $ $Revision: 1.11 $ $Date: 2005/08/24 20:39:06 $
 */

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.IVMServer;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.InitializationStringParser;

public class IDEStandardBeanProxyFactory implements IStandardBeanProxyFactory {


	protected IDEProxyFactoryRegistry fRegistry;
	protected IDEStandardBeanTypeProxyFactory fBeanTypeProxyFactory;
	private IBeanProxy ivmServerProxy;
	
	/*
	 * Return a bean proxy for the argument. To do this create through the correct BeanTypeProxy
	 * Which ensures that special subclasses of IDEBeanProxy are created. This is just a local
	 * helper method.
	 */
	static IIDEBeanProxy createBeanProxy(ProxyFactoryRegistry aRegistry, Object anObject) {

		return ((IDEStandardBeanProxyFactory) aRegistry.getBeanProxyFactory()).createIDEBeanProxyWith(anObject);
	}
	
	IDEStandardBeanProxyFactory(IDEProxyFactoryRegistry aRegistry) {
		fRegistry = aRegistry;
		fRegistry.registerBeanProxyFactory(this);
		fBeanTypeProxyFactory = (IDEStandardBeanTypeProxyFactory) aRegistry.getBeanTypeProxyFactory();
		ivmServerProxy = fRegistry.getBeanProxy(IVMServer.class, fRegistry);
	}
	
	public IStringBeanProxy createBeanProxyWith(String aString) {
		return fBeanTypeProxyFactory.stringClass.createStringBeanProxy(aString);
	}
	
	public INumberBeanProxy createBeanProxyWith(float aFloat) {
		return fBeanTypeProxyFactory.floatType.createFloatBeanProxy(aFloat);
	}
	
	public INumberBeanProxy createBeanProxyWith(short aShort) {
		return fBeanTypeProxyFactory.shortType.createShortBeanProxy(aShort);
	}
	
	public ICharacterBeanProxy createBeanProxyWith(char aChar) {
		return fBeanTypeProxyFactory.charType.createCharBeanProxy(aChar);
	}
	
	public ICharacterBeanProxy createBeanProxyWith(Character aCharacter) {
		return fBeanTypeProxyFactory.charClass.createCharacterBeanProxy(aCharacter);
	}
	
	public IIntegerBeanProxy createBeanProxyWith(int anInt) {
		return fBeanTypeProxyFactory.intType.createIntegerBeanProxy(anInt);
	}
	
	public IIntegerBeanProxy createBeanProxyWith(Integer anInteger) {
		return fBeanTypeProxyFactory.integerClass.createIntegerBeanProxy(anInteger);
	}
	
	public INumberBeanProxy createBeanProxyWith(long aLong) {
		return fBeanTypeProxyFactory.longType.createLongBeanProxy(aLong);
	}
	
	public INumberBeanProxy createBeanProxyWith(Number aNumber) {
		IDENumberBeanTypeProxy numberTypeProxy = (IDENumberBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(aNumber.getClass());
		return numberTypeProxy.createNumberBeanProxy(aNumber);
	}
	
	public IBooleanBeanProxy createBeanProxyWith(boolean aBoolean) {
		return fBeanTypeProxyFactory.booleanType.createBooleanBeanProxy(aBoolean);
	}
	
	public IBooleanBeanProxy createBeanProxyWith(Boolean aBoolean) {
		return fBeanTypeProxyFactory.booleanClass.createBooleanBeanProxy(aBoolean);
	}
	
	public INumberBeanProxy createBeanProxyWith(byte aByte) {
		return fBeanTypeProxyFactory.byteType.createByteBeanProxy(aByte);
	}
	
	public INumberBeanProxy createBeanProxyWith(double aDouble) {
		return fBeanTypeProxyFactory.doubleType.createDoubleBeanProxy(aDouble);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#convertToPrimitiveBeanProxy(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public IBeanProxy convertToPrimitiveBeanProxy(IBeanProxy nonPrimitiveProxy) {
		if (nonPrimitiveProxy == null)
			return null;
		if (!nonPrimitiveProxy.isValid())
			return nonPrimitiveProxy;
		IDEBeanTypeProxy type = (IDEBeanTypeProxy) nonPrimitiveProxy.getTypeProxy();
		if (type.getClass() == Boolean.class)
			return this.createBeanProxyWith(((IBooleanBeanProxy) nonPrimitiveProxy).booleanValue());
		else if (type.getClass() == Byte.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).byteValue());
		else if (type.getClass() == Character.class)
			return this.createBeanProxyWith(((ICharacterBeanProxy) nonPrimitiveProxy).charValue());
		else if (type.getClass() == Double.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).doubleValue());
		else if (type.getClass() == Float.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).floatValue());
		else if (type.getClass() == Integer.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).intValue());
		else if (type.getClass() == Long.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).longValue());
		else if (type.getClass() == Short.class)
			return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).shortValue());
		else
			return nonPrimitiveProxy;
	}

	/**
	 * Create a one-dimensional array. The result will be the same as calling
	 * createBeanProxyWith(IBeanTypeProxy type, new int[1] {x}) where 'x' is the value passed in as
	 * the dimension.
	 */
	public IArrayBeanProxy createBeanProxyWith(IBeanTypeProxy type, int dimension) throws ThrowableProxy {
		return createBeanProxyWith(type, new int[] { dimension });
	}
	
	/**
	 * Create an array bean proxy.
	 *  - (int, new int[2] {3, 4}) will create: int [3] [4]
	 *  - (int[], new int[1] {1}) int [1]
	 *  - (int[], new int[2] {2,3}) int [2] [3]
	 * 
	 *  - (int[], null) or (int[], new int[0]) or (int, null) or (int, new int[0]) int [0]... or
	 * (int[][]..., null) or (int[][]..., new int[0]) int[0][]... This is because an array instance
	 * with no specified dimensions is not valid.
	 *  - (int[][], new int[1] {3}) int[3][]
	 */
	public IArrayBeanProxy createBeanProxyWith(IBeanTypeProxy type, int[] dimensions) throws ThrowableProxy {
		if (type.isArray())
			return ((IDEArrayBeanTypeProxy) type).createBeanProxyWith(dimensions); // Already an
																				   // array type,
																				   // just pass it
																				   // on.
		else {
			// It is not an array type, so we need to get an array of this type and dimensions.
			IDEArrayBeanTypeProxy arrayType =
				(IDEArrayBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(type.getTypeName(), dimensions.length);
			return arrayType.createBeanProxyWith(dimensions);
		}
	}
	
	public void releaseProxy(IBeanProxy aProxy) {
		// Do nothing. This is more for remote proxies
	}
	
	/**
	 * createIDEBeanProxyWith method comment. This is not part of the IStandardBeanProxyFactory
	 * interface. It is available only from IDEBeanProxyFactory so that IDE registered extensions
	 * can wrapper a bean. They will need to get current and explicitly cast to
	 * IDEBeanProxyFactory, so they should only be IDE types themselves.
	 */
	public IIDEBeanProxy createIDEBeanProxyWith(Object anObject) {
		if (anObject == null)
			return null;
		return ((IDEBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(anObject.getClass())).newBeanProxy(anObject);
	}
	
	/**
	 * For the IDE Factory we will create an IDEBeanProxy using the initializationString. If the
	 * format of the string is not one we understand, pass it onto the evaluator. The
	 * initStringParser is passed to allow shortcut evaluation. Package protected so only
	 * IDEBeanTypeProxies can create instances.
	 */
	IBeanProxy createBeanProxy(IDEBeanTypeProxy aTypeProxy, String initializationString) {
		if (initializationString.equals("null")) //$NON-NLS-1$
			return null; // Value is initialized as null.
		// We have to evaluate the result of the initializationString
		try {
			ClassLoader pluginClassLoader = fRegistry.getPluginClassLoader();
			Object newValue = InitializationStringParser.evaluate(initializationString, pluginClassLoader);
			return newValue != null ? aTypeProxy.newBeanProxy(newValue) : null;
		} catch (Exception exc) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc)); //$NON-NLS-1$
			return null;
		}
	}
	
	/**
	 * For the IDE Factory we will create an IDEBeanProxy using the null constructor Package
	 * protected so only IDEBeanTypeProxies can create instances.
	 */
	IBeanProxy createBeanProxy(IDEBeanTypeProxy aTypeProxy) {

		Class beanType = aTypeProxy.fClass;

		// If the beanType is a primitive, the creation will fail because you can't
		// create a primitive without a value.
		try {
			return aTypeProxy.newBeanProxy(!(aTypeProxy.isPrimitive()) ? beanType.newInstance() : null);
		} catch (Exception exc) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc)); //$NON-NLS-1$
			return null;
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxyFactory#terminateFactory(boolean)
	 */
	public void terminateFactory(boolean wait) {
		// Nothing really to do here - more applicable to remote proxy factories
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createExpression()
	 */
	public IExpression createExpression() {
		return new IDEExpression(this.fRegistry);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createBeanProxyFrom(java.lang.String)
	 */
	public IBeanProxy createBeanProxyFrom(String initializationString) throws ThrowableProxy {
		if (initializationString.equals("null")) //$NON-NLS-1$
			return null; // Value is initialized as null.
		// We have to evaluate the result of the initializationString
		try {
			ClassLoader pluginClassLoader = fRegistry.getPluginClassLoader();
			InitializationStringParser parser = InitializationStringParser.createParser(initializationString, pluginClassLoader);
			Object newValue = parser.evaluate();
			if (newValue != null) {
				return fRegistry.getBeanProxy(parser.getExpectedType(), newValue);
			} else 
				return null;
		} catch (Exception exc) {
			ProxyPlugin.getPlugin().getLogger().log(
					new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc)); //$NON-NLS-1$
			return null;
		}
	}

	public IBeanProxy getIVMServerProxy() {
		return ivmServerProxy;
	}

}
