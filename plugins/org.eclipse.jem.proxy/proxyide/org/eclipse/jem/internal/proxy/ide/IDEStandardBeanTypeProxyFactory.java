/*****************************************************************************************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ****************************************************************************************************************************************************/
package org.eclipse.jem.internal.proxy.ide;

/*
 * $RCSfile: IDEStandardBeanTypeProxyFactory.java,v $ $Revision: 1.9 $ $Date: 2005/05/11 19:01:12 $
 */

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.common.MapTypes;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;

public class IDEStandardBeanTypeProxyFactory implements IStandardBeanTypeProxyFactory {

	protected final IDEProxyFactoryRegistry fFactoryRegistry;

	// Hashtable to cache proxies for classes so they are found on second and subsequent lookups
	protected Map beanProxies;

	public static Map MAP_SHORTSIG_TO_TYPE = new HashMap(8);

	public static Map MAP_TYPENAME_TO_SHORTSIG = new HashMap(8);

	static {
		MAP_SHORTSIG_TO_TYPE.put("B", Byte.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("C", Character.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("D", Double.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("F", Float.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("I", Integer.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("J", Long.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("S", Short.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("Z", Boolean.TYPE); //$NON-NLS-1$

		MAP_TYPENAME_TO_SHORTSIG.put("byte", "B"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("char", "C"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("double", "D"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("float", "F"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("int", "I"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("long", "J"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("short", "S"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("boolean", "Z"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	// Cached copy of a few typical bean type proxies.
	IDEBeanTypeProxy objectClass;

	IDEBooleanTypeBeanTypeProxy booleanType;

	IDEBooleanClassBeanTypeProxy booleanClass;

	IDEIntegerTypeBeanTypeProxy intType;

	IDEIntegerClassBeanTypeProxy integerClass;

	IDEFloatTypeBeanTypeProxy floatType;

	IDEFloatClassBeanTypeProxy floatClass;

	IDELongTypeBeanTypeProxy longType;

	IDELongClassBeanTypeProxy longClass;

	IDEShortTypeBeanTypeProxy shortType;

	IDEShortClassBeanTypeProxy shortClass;

	IDEByteTypeBeanTypeProxy byteType;

	IDEByteClassBeanTypeProxy byteClass;

	IDECharTypeBeanTypeProxy charType;

	IDECharacterClassBeanTypeProxy charClass;

	IDEDoubleTypeBeanTypeProxy doubleType;

	IDEDoubleClassBeanTypeProxy doubleClass;

	IDEStringBeanTypeProxy stringClass;

	IDEClassBeanTypeProxy classClass;

	IDEBeanTypeProxy voidType;

	public IDEStandardBeanTypeProxyFactory(IDEProxyFactoryRegistry aRegistry) {
		fFactoryRegistry = aRegistry;
		aRegistry.registerBeanTypeProxyFactory(this);

		// Now initialize the cache.
		objectClass = new IDEBeanTypeProxy(fFactoryRegistry, Object.class);
		booleanType = new IDEBooleanTypeBeanTypeProxy(fFactoryRegistry, Boolean.TYPE);
		booleanClass = new IDEBooleanClassBeanTypeProxy(fFactoryRegistry, Boolean.class);
		intType = new IDEIntegerTypeBeanTypeProxy(fFactoryRegistry, Integer.TYPE);
		integerClass = new IDEIntegerClassBeanTypeProxy(fFactoryRegistry, Integer.class);
		floatType = new IDEFloatTypeBeanTypeProxy(fFactoryRegistry, Float.TYPE);
		floatClass = new IDEFloatClassBeanTypeProxy(fFactoryRegistry, Float.class);
		longType = new IDELongTypeBeanTypeProxy(fFactoryRegistry, Long.TYPE);
		longClass = new IDELongClassBeanTypeProxy(fFactoryRegistry, Long.class);
		shortType = new IDEShortTypeBeanTypeProxy(fFactoryRegistry, Short.TYPE);
		shortClass = new IDEShortClassBeanTypeProxy(fFactoryRegistry, Short.class);
		byteType = new IDEByteTypeBeanTypeProxy(fFactoryRegistry, Byte.TYPE);
		byteClass = new IDEByteClassBeanTypeProxy(fFactoryRegistry, Byte.class);
		charType = new IDECharTypeBeanTypeProxy(fFactoryRegistry, Character.TYPE);
		charClass = new IDECharacterClassBeanTypeProxy(fFactoryRegistry, Character.class);
		doubleType = new IDEDoubleTypeBeanTypeProxy(fFactoryRegistry, Double.TYPE);
		doubleClass = new IDEDoubleClassBeanTypeProxy(fFactoryRegistry, Double.class);
		stringClass = new IDEStringBeanTypeProxy(fFactoryRegistry, String.class);
		classClass = new IDEClassBeanTypeProxy(fFactoryRegistry, java.lang.Class.class);
		voidType = new IDEBeanTypeProxy(fFactoryRegistry, Void.TYPE);

		// Initialize the hashtable with the primitives, their lang equivalents, and also common classes like String
		beanProxies = new HashMap(20);

		// Primitives
		beanProxies.put(intType.getTypeName(), intType);
		beanProxies.put(booleanType.getTypeName(), booleanType);
		beanProxies.put(charType.getTypeName(), charType);
		beanProxies.put(byteType.getTypeName(), byteType);
		beanProxies.put(shortType.getTypeName(), shortType);
		beanProxies.put(longType.getTypeName(), longType);
		beanProxies.put(floatType.getTypeName(), floatType);
		beanProxies.put(doubleType.getTypeName(), doubleType);

		// java.lang primitive peers
		// Note that special classes are used for some of these which allow the IDE to get the
		// lang objects from the objects that are holding proxies
		beanProxies.put(integerClass.getTypeName(), integerClass);
		beanProxies.put(booleanClass.getTypeName(), booleanClass);
		beanProxies.put(charClass.getTypeName(), charClass);
		beanProxies.put(byteClass.getTypeName(), byteClass);
		beanProxies.put(shortClass.getTypeName(), shortClass);
		beanProxies.put(longClass.getTypeName(), longClass);
		beanProxies.put(floatClass.getTypeName(), floatClass);
		beanProxies.put(doubleClass.getTypeName(), doubleClass);
		beanProxies.put(BigDecimal.class.getName(), new IDEBigDecimalBeanTypeProxy(fFactoryRegistry, BigDecimal.class));//$NON-NLS-1$
		beanProxies.put(BigInteger.class.getName(), new IDEBigIntegerBeanTypeProxy(fFactoryRegistry, BigInteger.class));//$NON-NLS-1$		
		beanProxies.put(stringClass.getTypeName(), stringClass);

		beanProxies.put(classClass.getTypeName(), classClass);
		beanProxies.put(voidType.getTypeName(), voidType);
	}

	/**
	 * We are an IDE proxy and know that the type is in the same VM as the IDE. the IDEBeanTypeProxy object NOTE This is package protected because the
	 * only person who can call it are priveledged classes that are also creating things in an IDEProxy environment. If anyone needs to make this
	 * method public they are doing the wrong thing as they should use the public method getBeanTypeProxy(String) that is on the interface. The only
	 * other object that can guarantee that they have the class for the argument are those that are part of the idevm package
	 */
	IBeanTypeProxy getBeanTypeProxy(Class anIDEClass) {
		return getBeanTypeProxy(anIDEClass.getName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#getBeanTypeProxy(java.lang.String)
	 */
	public synchronized IBeanTypeProxy getBeanTypeProxy(String typeName) {
		typeName = MapTypes.getJNIFormatName(typeName);

		// See whether we already have the proxy for the argument name
		IProxyBeanType beanTypeProxy = (IProxyBeanType) beanProxies.get(typeName);
		// See if there and resolved, if so, return it. If not resolved, that means we need it NOW
		// so we must go for it. When finally resolved the original ExpressionProxy will be deregistered and
		// the resolved beantypeproxy will be in its place.
		if (beanTypeProxy != null && beanTypeProxy.isBeanProxy()) {
			return (IBeanTypeProxy) beanTypeProxy;
		}

		// If not an array, then see if the package extension mechanism can find it.
		// Do this here so that if it is found in the package extension we won't necessarily create an
		// extra connection when not needed.
		if (typeName.charAt(0) != '[') {
			// It is not an array
			// First check with the factory for the package of the class.
			// Inner classes have to use the dollar notation since if they didn't we couldn't tell where
			// the package ended and the class started.
			int packageIndex = typeName.lastIndexOf('.');
			if (packageIndex != -1) {
				String packageName = typeName.substring(0, packageIndex);
				IDEExtensionBeanTypeProxyFactory packageFactory = (IDEExtensionBeanTypeProxyFactory) fFactoryRegistry
						.getBeanTypeProxyFactoryExtension(packageName);
				if (packageFactory != null) {
					beanTypeProxy = packageFactory.getExtensionBeanTypeProxy(typeName);
					if (beanTypeProxy != null) {
						registerBeanTypeProxy((IBeanTypeProxy) beanTypeProxy, false);
						return (IBeanTypeProxy) beanTypeProxy;
					}
				}
			}
			// There was not a registered factory that dealt with the class. Load it using the factory
			// registry which has the plugin's class loader
			try {
				Class ideClass = fFactoryRegistry.loadClass(typeName);
				IDEBeanTypeProxy superTypeProxy = null;
				if (ideClass.getSuperclass() != null) {
					// Get the beantype proxy of the superclass.
					superTypeProxy = (IDEBeanTypeProxy) getBeanTypeProxy(ideClass.getSuperclass());
				}

				// Ask the supertype
				// to create a beantype proxy of the same beantype proxy class.
				// This is so that any subclasses will get the same beantype proxy class
				// for it if it is special.
				if (superTypeProxy != null)
					beanTypeProxy = superTypeProxy.newBeanTypeForClass(ideClass);

				if (beanTypeProxy == null)
					beanTypeProxy = new IDEBeanTypeProxy(fFactoryRegistry, ideClass);
			} catch (ClassNotFoundException e) {
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.INFO, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e));
				String msg = MessageFormat.format("{0}({1})", new Object[] { e.getClass(), e.getMessage()}); //$NON-NLS-1$
				beanTypeProxy = new IDEInitErrorBeanTypeProxy(fFactoryRegistry, typeName, msg);
			} catch (ExceptionInInitializerError e) {
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e));
				String msg = MessageFormat.format("{0}({1})", new Object[] { e.getClass(), e.getMessage()}); //$NON-NLS-1$
				beanTypeProxy = new IDEInitErrorBeanTypeProxy(fFactoryRegistry, typeName, msg);
			} catch (LinkageError e) {
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e));
				String msg = MessageFormat.format("{0}({1})", new Object[] { e.getClass(), e.getMessage()}); //$NON-NLS-1$
				beanTypeProxy = new IDEInitErrorBeanTypeProxy(fFactoryRegistry, typeName, msg);
			}

			// Cache the instance so we can re-use it again
			beanProxies.put(typeName, beanTypeProxy);
			return (IBeanTypeProxy) beanTypeProxy;
		} else {
			// need to create a array of this many dimensions so that we can get the appropriate class for it.
			int dims = typeName.lastIndexOf('[') + 1;
			Class finalComponentType = null;
			if (typeName.charAt(dims) == 'L') {
				// It is a class.
				// Strip off up to the 'L', and the trailing ';'. That is the class name.
				IDEBeanTypeProxy finalType = (IDEBeanTypeProxy) getBeanTypeProxy(typeName.substring(dims + 1, typeName.length() - 1));
				if (finalType != null)
					finalComponentType = finalType.fClass;
			} else {
				// It is a type. Need to map it.
				finalComponentType = (Class) IDEStandardBeanTypeProxyFactory.MAP_SHORTSIG_TO_TYPE.get(typeName.substring(dims, dims + 1));
			}

			if (finalComponentType != null) {
				Object dummyArray = Array.newInstance(finalComponentType, new int[dims]);
				beanTypeProxy = new IDEArrayBeanTypeProxy(fFactoryRegistry, typeName, dummyArray.getClass());
				beanProxies.put(typeName, beanTypeProxy);
			}
			return (IBeanTypeProxy) beanTypeProxy;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#getBeanTypeProxy(org.eclipse.jem.internal.proxy.core.IExpression,
	 *      java.lang.String)
	 */
	public synchronized IProxyBeanType getBeanTypeProxy(IExpression expression, String typeName) {
		typeName = MapTypes.getJNIFormatName(typeName);

		// See whether we already have the proxy for the argument name
		IProxyBeanType beanTypeProxy = (IProxyBeanType) beanProxies.get(typeName);
		if (beanTypeProxy != null) { return beanTypeProxy; }
		
		// Now see if an expression proxy cached.
		beanTypeProxy = ((IDEExpression) expression).getBeanType(typeName);
		if (beanTypeProxy != null)
			return beanTypeProxy;		


		// If not an array, then see if the package extension mechanism can find it.
		// Do this here so that if it is found in the package extension we won't necessarily create an
		// extra connection when not needed.
		if (typeName.charAt(0) != '[') {
			// It is not an array
			// First check with the factory for the package of the class.
			// Inner classes have to use the dollar notation since if they didn't we couldn't tell where
			// the package ended and the class started.
			int packageIndex = typeName.lastIndexOf('.');
			if (packageIndex != -1) {
				String packageName = typeName.substring(0, packageIndex);
				IDEExtensionBeanTypeProxyFactory packageFactory = (IDEExtensionBeanTypeProxyFactory) fFactoryRegistry
						.getBeanTypeProxyFactoryExtension(packageName);
				if (packageFactory != null) {
					beanTypeProxy = packageFactory.getExtensionBeanTypeProxy(typeName, expression);
					if (beanTypeProxy != null) {
						registerBeanTypeProxy(beanTypeProxy, false);
						return beanTypeProxy;
					}
				}
			}
		}

		// There was not a registered factory that dealt with the class. So create the expression proxy.
		beanTypeProxy = ((Expression) expression).createBeanTypeExpressionProxy(typeName);
		registerBeanTypeProxy(beanTypeProxy, false);
		return beanTypeProxy;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#getBeanTypeProxy(java.lang.String, int)
	 */
	public IBeanTypeProxy getBeanTypeProxy(String componentClassName, int dimensions) {
		return getBeanTypeProxy(getArrayClassname(componentClassName, dimensions));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#getBeanTypeProxy(org.eclipse.jem.internal.proxy.core.IExpression,
	 *      java.lang.String, int)
	 */
	public IProxyBeanType getBeanTypeProxy(IExpression expression, String componentClassName, int dimensions) {
		return getBeanTypeProxy(expression, getArrayClassname(componentClassName, dimensions));
	}

	/**
	 * @param componentClassName
	 * @param dimensions
	 * @return
	 * 
	 * @since 1.1.0
	 */
	protected String getArrayClassname(String componentClassName, int dimensions) {
		String jniComponentTypeName = MapTypes.getJNIFormatName(componentClassName);
		String compType = jniComponentTypeName;
		if (jniComponentTypeName.charAt(0) != '[') {
			// We're not already an array, so create correct template.
			compType = (String) MAP_TYPENAME_TO_SHORTSIG.get(componentClassName);
			if (compType == null) {
				// It is a class, and not a type.
				compType = "L" + jniComponentTypeName + ";"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		// Now create it with the appropriate number of '[' in front.
		StringBuffer buffer = new StringBuffer(dimensions + compType.length());
		for (int i = 0; i < dimensions; i++)
			buffer.append('[');
		buffer.append(compType);
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxyFactory#terminateFactory(boolean)
	 */
	public void terminateFactory(boolean wait) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#registerBeanTypeProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy,
	 *      boolean)
	 */
	public synchronized void registerBeanTypeProxy(IBeanTypeProxy aBeanTypeProxy, boolean permanent) {
		beanProxies.put(aBeanTypeProxy.getTypeName(), aBeanTypeProxy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#registerBeanTypeProxy(org.eclipse.jem.internal.proxy.core.IProxyBeanType,
	 *      boolean)
	 */
	public void registerBeanTypeProxy(IProxyBeanType aProxyBeanType, boolean permanent) {
		if (aProxyBeanType.isBeanProxy())
			registerBeanTypeProxy((IBeanTypeProxy) aProxyBeanType, permanent); // A regular kind, do regular registration.
		else {
			ExpressionProxy beanExpressionProxy = ((ExpressionProxy) aProxyBeanType);
			final String typeName = aProxyBeanType.getTypeName();
			((IDEExpression) beanExpressionProxy.getExpression()).addBeanType(typeName, aProxyBeanType);
			beanExpressionProxy.addProxyListener(new ExpressionProxy.ProxyAdapter() {

				public void proxyResolved(ProxyEvent event) {
					String typeName = ((IProxyBeanType) event.getSource()).getTypeName();
					synchronized (IDEStandardBeanTypeProxyFactory.this) {
						if (!beanProxies.containsKey(typeName)) {
							// It hasn't been resolved through some other means. So this is good. Actually this should never
							// occur because upon resolution we've already registered the bean type proxy through the
							// normal mechanisms. But to be safe, we'll do it here.
							beanProxies.put(typeName, event.getProxy());
						}
					}
				}
				
				public void proxyNotResolved(ExpressionProxy.ProxyEvent event) {
					((IDEExpression) ((ExpressionProxy) event.getSource()).getExpression()).removeBeanType(typeName);
				}
			});
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isBeanTypeRegistered(String)
	 */
	public synchronized boolean isBeanTypeRegistered(String className) {
		return beanProxies.containsKey(MapTypes.getJNIFormatName(className));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#registeredTypes()
	 */
	public Set registeredTypes() {
		return beanProxies.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isBeanTypeNotFound(String)
	 */
	public boolean isBeanTypeNotFound(String className) {
		// Do nothing. No need for it in IDE system because there will always be a proxy, even when not found.
		// In that case an IDEInitErrorBeanTypeProxy will be created.
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isMaintainNotFoundTypes()
	 */
	public boolean isMaintainNotFoundTypes() {
		// Do nothing. No need for it in IDE system because there will always be a proxy, even when not found.
		// In that case an IDEInitErrorBeanTypeProxy will be created.
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#setMaintainNotFoundTypes(boolean)
	 */
	public void setMaintainNotFoundTypes(boolean maintain) {
		// Do nothing. No need for it in IDE system because there will always be a proxy, even when not found.
		// In that case an IDEInitErrorBeanTypeProxy will be created.
	}
}