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
 *  $RCSfile: REMStandardBeanTypeProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.CommandErrorException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * We are a beanTypeProxyFactory for creating REMBeanTypeProxy objects
 * Primitive types as well as common types like String are cached for speed
 * of lookup.
 * We are the standard one that the VCE uses.
 * Creation date: (12/3/99 2:32:48 PM)
 * @author: Joe Winchester
 */
public final class REMStandardBeanTypeProxyFactory implements IStandardBeanTypeProxyFactory {
	
	protected final REMProxyFactoryRegistry fFactoryRegistry;
	protected REMStandardBeanProxyFactory fBeanFactory;
	
	
	// Hashtable to cache proxies for classes so they are found on second and subsequent lookups
	protected Map fBeanProxies = new HashMap(1000);
	
	// A Cache of bean type proxies that should not ever be released.	These are the standard ones
	// that we create here. They are never released because they wouldn't be correctly re-created
	// if they were. Also they are standard ones with standard ID's that don't change so they
	// don't need to be re-created later.
	protected Set fPermanentProxies = new HashSet(30);
	
	// Cache of requested but not found bean types. If not maintaining the list, this variable will be null.
	// The values are strings (classnames in JNI format).
	protected Set fNotFoundTypes = null; 

	// Cached copy of a few typical bean type proxies.
	REMBeanTypeProxy objectClass;
	REMClassBeanTypeProxy classClass;	
	REMVoidBeanTypeProxy voidType;
	REMBooleanTypeBeanTypeProxy booleanType;
	REMBooleanClassBeanTypeProxy booleanClass;
	REMIntegerTypeBeanTypeProxy intType;
	REMIntegerClassBeanTypeProxy integerClass;
	REMByteTypeBeanTypeProxy byteType;
	REMByteClassBeanTypeProxy byteClass;		
	REMShortClassBeanTypeProxy shortClass;	
	REMShortTypeBeanTypeProxy shortType;
	REMLongClassBeanTypeProxy longClass;	
	REMLongTypeBeanTypeProxy longType;	
	REMDoubleClassBeanTypeProxy doubleClass;	
	REMDoubleTypeBeanTypeProxy doubleType;	
	REMFloatClassBeanTypeProxy floatClass;	
	REMFloatTypeBeanTypeProxy floatType;	
	REMBigDecimalBeanTypeProxy bigDecimalClass;		
	REMBigIntegerBeanTypeProxy bigIntegerClass;		
	REMCharacterTypeBeanTypeProxy charType;
	REMCharacterClassBeanTypeProxy characterClass;	
	REMStringBeanTypeProxy stringClass;

/**
 * IDEBeanTypeProxyFactory constructor comment.
 */
REMStandardBeanTypeProxyFactory(REMProxyFactoryRegistry aRegistry) {
	fFactoryRegistry = aRegistry;
	aRegistry.registerBeanTypeProxyFactory(this);

	// Now initialize the cache.
	objectClass = new REMBeanTypeProxy(fFactoryRegistry, new Integer(Commands.OBJECT_CLASS), Object.class.getName(), null);
	classClass = new REMClassBeanTypeProxy(fFactoryRegistry, objectClass);		
	voidType = new REMVoidBeanTypeProxy(fFactoryRegistry);
	booleanType = new REMBooleanTypeBeanTypeProxy(fFactoryRegistry);
	booleanClass = new REMBooleanClassBeanTypeProxy(fFactoryRegistry, objectClass);
	IBeanTypeProxy numberClass = objectClass.newBeanTypeForClass(new Integer(Commands.NUMBER_CLASS), Number.class.getName(), true);
	intType = new REMIntegerTypeBeanTypeProxy(fFactoryRegistry);
	integerClass = new REMIntegerClassBeanTypeProxy(fFactoryRegistry, numberClass);
	byteType = new REMByteTypeBeanTypeProxy(fFactoryRegistry);
	byteClass = new REMByteClassBeanTypeProxy(fFactoryRegistry, numberClass);	
	shortType = new REMShortTypeBeanTypeProxy(fFactoryRegistry);
	shortClass = new REMShortClassBeanTypeProxy(fFactoryRegistry, numberClass);
	longType = new REMLongTypeBeanTypeProxy(fFactoryRegistry);
	longClass = new REMLongClassBeanTypeProxy(fFactoryRegistry, numberClass);
	doubleType = new REMDoubleTypeBeanTypeProxy(fFactoryRegistry);
	doubleClass = new REMDoubleClassBeanTypeProxy(fFactoryRegistry, numberClass);
	floatType = new REMFloatTypeBeanTypeProxy(fFactoryRegistry);
	floatClass = new REMFloatClassBeanTypeProxy(fFactoryRegistry, numberClass);					
	bigDecimalClass = new REMBigDecimalBeanTypeProxy(fFactoryRegistry, numberClass);	
	bigIntegerClass = new REMBigIntegerBeanTypeProxy(fFactoryRegistry, numberClass);		
	charType = new REMCharacterTypeBeanTypeProxy(fFactoryRegistry);	
	characterClass = new REMCharacterClassBeanTypeProxy(fFactoryRegistry, objectClass);
	stringClass = new REMStringBeanTypeProxy(fFactoryRegistry, objectClass);
	IBeanTypeProxy throwableClass = new REMThrowableBeanTypeProxy(fFactoryRegistry, new Integer(Commands.THROWABLE_CLASS), Throwable.class.getName(), objectClass);
	IBeanTypeProxy threadClass = objectClass.newBeanTypeForClass(new Integer(Commands.THREAD_CLASS), Thread.class.getName(), false);

	// Initialize the hashtable with the primitives, their lang equivalents, and also common classes like String

	// Primitives
	fBeanProxies.put(voidType.getTypeName(), voidType);
	fBeanProxies.put(intType.getTypeName(), intType);
	fBeanProxies.put(booleanType.getTypeName(), booleanType);
	fBeanProxies.put(charType.getTypeName(), charType);
	fBeanProxies.put(byteType.getTypeName(), byteType);
	fBeanProxies.put(shortType.getTypeName(), shortType);
	fBeanProxies.put(longType.getTypeName(), longType);
	fBeanProxies.put(floatType.getTypeName(), floatType);
	fBeanProxies.put(doubleType.getTypeName(), doubleType);

	// java.lang primitive peers
	// Note that special classes are used for some of these which allow the IDE to get the
	// lang objects from the objects that are holding proxies
	fBeanProxies.put(integerClass.getTypeName(), integerClass);
	fBeanProxies.put(booleanClass.getTypeName(), booleanClass);
	fBeanProxies.put(characterClass.getTypeName(), characterClass);
	fBeanProxies.put(byteClass.getTypeName(), byteClass);
	fBeanProxies.put(shortClass.getTypeName(), shortClass);
	fBeanProxies.put(longClass.getTypeName(), longClass);
	fBeanProxies.put(floatClass.getTypeName(), floatClass);
	fBeanProxies.put(doubleClass.getTypeName(), doubleClass);
	fBeanProxies.put(bigDecimalClass.getTypeName(), bigDecimalClass);
	fBeanProxies.put(bigIntegerClass.getTypeName(), bigIntegerClass);
	fBeanProxies.put(stringClass.getTypeName(), stringClass);
	fBeanProxies.put(throwableClass.getTypeName(), throwableClass);
	fBeanProxies.put(objectClass.getTypeName(), objectClass);
	fBeanProxies.put(classClass.getTypeName(), classClass);
	fBeanProxies.put(numberClass.getTypeName(), numberClass);	
	fBeanProxies.put(threadClass.getTypeName(), threadClass);
	
	// Mark these as permanent.
	fPermanentProxies.addAll(fBeanProxies.values());
}

/**
 * Initialize AFTER BeanProxyFactory has been created. This is REQUIRED!
 * NOTE: It is package protected so that only REMStandardBeanProxyFactory can call it when ready.
 */
synchronized void initialize(REMStandardBeanProxyFactory proxyFactory) {
	fBeanFactory = proxyFactory;
	fBeanFactory.registerProxies(fBeanProxies.values());
}
	
	
/*
 * This is called when we know we don't have the class registered, so
 * we need to create the proxy. We have a connection passed in and will reuse it as necessary
 * 
 * It is important that this be called only from within a transaction.
 */
private synchronized IREMBeanTypeProxy createBeanTypeProxy(String typeName, IREMConnection connection) throws CommandException {
	
	// We don't have the beantype proxy, so create it.	
	IREMBeanTypeProxy beanTypeProxy = null;
	Commands.GetClassReturn ret = null;
	try {
		ret = getClassReturn(connection, typeName);
	} catch (ThrowableProxy ep) {
		// Print out the trace and return proxy with init error.
		ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", ep)); //$NON-NLS-1$
		IREMBeanTypeProxy proxy = new REMInitErrorBeanTypeProxy(fFactoryRegistry, MessageFormat.format(ProxyRemoteMessages.getString("ExceptionErrorMsg_EXC_"), new Object[] {ep.getTypeProxy().getTypeName(), ep.getProxyLocalizedMessage()}), typeName); //$NON-NLS-1$
		registerBeanTypeProxy(proxy, false);
		return proxy;
	}
	if (ret == null) {
		if (fNotFoundTypes != null)
			fNotFoundTypes.add(typeName);
		return null;	// The class doesn't even exist on the server.
	}

	if (typeName.charAt(0) != '[') {
		// It is not an array.
		IREMBeanTypeProxy superTypeProxy = null;
		if (!ret.isInterface && ret.superClassname.length() != 0) {
			// Get the beantype proxy of the superclass.
			superTypeProxy = getBeanTypeProxy(ret.superClassname, connection);
		}
			
		// First check with the factory for the package of the class.
		// Inner classes have to use the dollar notation since if they didn't we couldn't tell where
		// the package ended and the class started. This check is here in case the
		// extension factory can handle this class but needs the id from the server to
		// create it.
		int packageIndex = typeName.lastIndexOf('.');
		if (packageIndex != -1) {
			String packageName = typeName.substring(0, packageIndex);
			IREMBeanTypeProxyFactory packageFactory = (IREMBeanTypeProxyFactory) fFactoryRegistry.getBeanTypeProxyFactoryExtension(packageName);
			if (packageFactory != null) {
				beanTypeProxy = packageFactory.getExtensionBeanTypeProxy(typeName, new Integer(ret.classID), superTypeProxy);
				if (beanTypeProxy != null) {
					registerBeanTypeProxy(beanTypeProxy, false);
					return beanTypeProxy;
				}
			}
		}							
		
		if (ret.isInterface) {
			// Interface never have a super type, so we will create a specific type.
			beanTypeProxy = new REMInterfaceBeanTypeProxy(fFactoryRegistry, new Integer(ret.classID), typeName);
		} else {		
			// Ask the supertype
			// to create a beantype proxy of the same beantype proxy class.
			// This is so that any subclasses will get the same beantype proxy class
			// for it if it is special.			
			if (superTypeProxy != null)
				beanTypeProxy = superTypeProxy.newBeanTypeForClass(new Integer(ret.classID), typeName, ret.isAbstract);
		}
	} else
		beanTypeProxy = new REMArrayBeanTypeProxy(fFactoryRegistry, new Integer(ret.classID), typeName, objectClass);
	 	
	// Cache the instance so we can re-use it again
	if (beanTypeProxy != null) 		
		registerBeanTypeProxy(beanTypeProxy, false);
	return beanTypeProxy;
}
/**
 * Using the helper class to find a class by name, then create the proxy.
 */
public IBeanTypeProxy getBeanTypeProxy(String typeName) {
	try {
		return getBeanTypeProxy(getJNIFormatName(typeName), (IREMConnection) null);
	} catch (CommandException e) {
		// Try once more (we won't have received recoverable exceptions here, they were already caught and handled)
		try {
			return getBeanTypeProxy(typeName, (IREMConnection) null);
		} catch (CommandException eAgain) {
			// Failed again.
			ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", eAgain)); //$NON-NLS-1$
		}
	}
	return null;
}
	
/**
 * One that internally allows that we already have a connection to work with.
 * If the connection is null, then one will be created.
 * 
 * It is important that if the connection is not null, then we are in a transaction.
 */
private synchronized IREMBeanTypeProxy getBeanTypeProxy(String typeName, IREMConnection inConnect) throws CommandException {	

	// See whether we already have the proxy for the argument name
	IREMBeanTypeProxy beanTypeProxy = (IREMBeanTypeProxy) fBeanProxies.get(typeName);
	if (beanTypeProxy != null) {
		return beanTypeProxy;
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
			IREMBeanTypeProxyFactory packageFactory = (IREMBeanTypeProxyFactory) fFactoryRegistry.getBeanTypeProxyFactoryExtension(packageName);
			if (packageFactory != null) {
				beanTypeProxy = (IREMBeanTypeProxy) packageFactory.getExtensionBeanTypeProxy(typeName);
				if (beanTypeProxy != null) {
					registerBeanTypeProxy(beanTypeProxy, false);
					return beanTypeProxy;
				}
			}
		}
	}
	
	IREMConnection connect = inConnect != null ? inConnect : fFactoryRegistry.getFreeConnection();
	if (connect != null) {
		if (inConnect == null)
			fBeanFactory.startTransaction();	// Start a transation.
		try {
			return createBeanTypeProxy(typeName, connect);
		} catch (CommandException e) {
			if (inConnect == null) {
				// Need to close the connection, not return it.
				fFactoryRegistry.closeConnection(connect);
				connect = null;	// So that it won't be returned.
			}
			throw e;	// Pass it on up
		} finally {
			if (inConnect == null)
				fBeanFactory.stopTransaction();
			if (inConnect == null && connect != null)
				fFactoryRegistry.returnConnection(connect);
		}
	}
		
	return null;
}

/*
 * It is important this be called only from within a transaction.
 */
private Commands.GetClassReturn getClassReturn(IREMConnection connection, String className) throws CommandException, ThrowableProxy {
	try {
		return connection.getClass(className);
	} catch (CommandErrorException e) {
		fBeanFactory.processErrorReturn(e);	// Let proxy factory handle the error return
	}
	return null;
}

/**
 * Return an Array type proxy for the given class name of
 * the specified dimensions. This is a helper method. The
 * same result can be gotton from getBeanTypeProxy.
 * e.g.
 *      getBeanTypeProxy("java.lang.Object", 3)
 *    is the same as:
 *      getBeanTypeProxy("[[[Ljava.lang.Object;")
 *
 *    They both result in a type of:
 *      Object [][][]
 *
 *    or
 *      getBeanTypeProxy("[Ljava.langObject;", 3)
 *    becomes
 *      Object [][][][]
 */
public IBeanTypeProxy getBeanTypeProxy(String componentClassName, int dimensions) {
	String jniComponentClassName = getJNIFormatName(componentClassName);
	String compType = jniComponentClassName;
	if (jniComponentClassName.charAt(0) != '[') {
		// We're not already an array, so create correct template.
		compType = (String) Commands.MAP_TYPENAME_TO_SHORTSIG.get(jniComponentClassName);
		if (compType == null) {
			// It is a class, and not a type.
			compType = "L"+jniComponentClassName+";"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	// Now create it with the appropriate number of '[' in front.
	StringBuffer buffer = new StringBuffer(dimensions+compType.length());
	for (int i=0; i<dimensions; i++)
		buffer.append('[');
	buffer.append(compType);
	return getBeanTypeProxy(buffer.toString());
}

/*
 * convert formal type name for an array (i.e. java.lang.Object[]
 * to the jni format (i.e. [Ljava.lang.Object;)
 * This is used when a name is passed in from the IDE side.
 * The VM side uses the jni format, and all of proxy uses the jni format.
 */
protected String getJNIFormatName(String classname) {
	if (classname.length() == 0 || !classname.endsWith("]")) //$NON-NLS-1$
		return classname;	// Not an array,or invalid
	
	StringBuffer jni = new StringBuffer(classname.length());
	int firstOpenBracket = classname.indexOf('[');
	int ob = firstOpenBracket;
	while (ob > -1) {
		int cb = classname.indexOf(']', ob);
		if (cb == -1)
			break;
		jni.append('[');
		ob = classname.indexOf('[', cb);
	}
	
	IBeanTypeProxy finalType = getBeanTypeProxy(classname.substring(0, firstOpenBracket).trim());
	if (finalType != null)
		if (!finalType.isPrimitive()) {
			jni.append('L');
			jni.append(finalType.getTypeName());
			jni.append(';');
		} else {
			jni.append(Commands.MAP_TYPENAME_TO_SHORTSIG.get(finalType.getTypeName()));
		}
	
	return jni.toString();
}

	

/**
 * Get the bean type proxy from a class id. This means that a new class id
 * was sent back from the server that we don't have yet. We need to go ask
 * the server for information on this type so that we can create it.
 *
 * NOTE: This is package protected so that only the standard bean proxy factory
 * can call it. 
 * 
 * It is important that this has been called within a transaction.
 */
IREMBeanTypeProxy createBeanTypeProxy(Integer classID) {
	IREMConnection connect = fFactoryRegistry.getFreeConnection();	
	if (connect != null) {
		try {
			return createBeanTypeProxy(classID, connect);
		} catch (CommandException e) {
			if (e.isRecoverable()) {
				// It is recoverable, print message, keep connection live and return it.
				ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e)); //$NON-NLS-1$
			} else {
				// Try again, close connection, get a new one.
				fFactoryRegistry.closeConnection(connect);
				connect = null;
				connect = fFactoryRegistry.getFreeConnection();
				if (connect != null)
					try {
						return createBeanTypeProxy(classID, connect);
					} catch (CommandException eAgain) {
						// Failed again. Close connection, don't return it.
						ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", eAgain)); //$NON-NLS-1$
						fFactoryRegistry.closeConnection(connect);
						connect = null;
					}
			}
		} finally {
			if (connect != null)
				fFactoryRegistry.returnConnection(connect);
		}
	}
	return null;
}

/*
 * One that internally allows that we already have a connection to work with.
 * If the connection is null, then one will be created.
 * 
 * It is important this be called only from within a transaction.
 */
private synchronized IREMBeanTypeProxy createBeanTypeProxy(Integer classID, IREMConnection connect) throws CommandException {	
	// We don't have the beantype proxy, so create it.
	IREMBeanTypeProxy beanTypeProxy = null;

	Commands.GetClassIDReturn ret = null;
	try {
		ret = getClassIDReturn(connect, classID);
	} catch (ThrowableProxy ep) {
		// Just print out the trace and return proxy not found.
		ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", ep)); //$NON-NLS-1$
		return null;
	}
	
	// If the signature is that of a class.
	if (ret.className.charAt(0) != '[') {
		// It is not an array.
		IREMBeanTypeProxy superTypeProxy = null;
		if (!ret.isInterface && ret.superClassname.length() != 0) {
			// Get the beantype proxy of the superclass.
			superTypeProxy = getBeanTypeProxy(ret.superClassname, connect);
		}
		
		// First check with the factory for the package of the class.
		// Inner classes have to use the dollar notation since if they didn't we couldn't tell where
		// the package ended and the class started.
		int packageIndex = ret.className.lastIndexOf('.');
		if (packageIndex != -1) {
			String packageName = ret.className.substring(0, packageIndex);
			IREMBeanTypeProxyFactory packageFactory = (IREMBeanTypeProxyFactory) fFactoryRegistry.getBeanTypeProxyFactoryExtension(packageName);
			if (packageFactory != null) {
				beanTypeProxy = packageFactory.getExtensionBeanTypeProxy(ret.className, classID, superTypeProxy);
				if (beanTypeProxy != null) {
					registerBeanTypeProxy(beanTypeProxy, false);
					return beanTypeProxy;
				}
			}
		}	
			
		if (ret.isInterface) {
			// Interface never have a super type, so we will let the object class do it for us.
			beanTypeProxy = new REMInterfaceBeanTypeProxy(fFactoryRegistry, classID, ret.className);	
		} else {		
			// Ask the beantype proxy of the superclass 
			// to create a beantype proxy of the same beantype proxy class.
			// This is so that any subclasses will get the same beantype proxy class
			// for it if it is special.			
			if (superTypeProxy != null)
				beanTypeProxy = superTypeProxy.newBeanTypeForClass(classID, ret.className, ret.isAbstract);
		}
 
		// Cache the instance so we can re-use it again
		if (beanTypeProxy != null)		
			registerBeanTypeProxy(beanTypeProxy, false);
		return beanTypeProxy;
	} else {		
		// It is an array.
		beanTypeProxy = new REMArrayBeanTypeProxy(fFactoryRegistry, classID, ret.className, objectClass );
		registerBeanTypeProxy(beanTypeProxy, false);
		return beanTypeProxy;
	}	
}

/*
 * It is important that this be called only from within a transaction.
 */
private Commands.GetClassIDReturn getClassIDReturn(IREMConnection connection, Integer classID) throws CommandException, ThrowableProxy {
	try {
		return connection.getClassFromID(classID.intValue());
	} catch (CommandErrorException e) {
		fBeanFactory.processErrorReturn(e);	// Let proxy factory handle the error return
	}
	return null;
}



/**
 * registerBeanTypeProxy.
 * Register this bean type proxy on behalf of the
 * custom factory. This is so that during initializations,
 * the custom factory can cache specific bean type proxies
 * ahead of time. 
 *
 * The permanent flag indicates whether this bean type should never be
 * released (i.e. not even explicitly released).
 */
public synchronized void registerBeanTypeProxy(IBeanTypeProxy aBeanTypeProxy, boolean permanent) {
	fBeanProxies.put(aBeanTypeProxy.getTypeName(), aBeanTypeProxy);
	fBeanFactory.registerProxy((IREMBeanProxy) aBeanTypeProxy);
	if (permanent)
		fPermanentProxies.add(aBeanTypeProxy);
}

/**
 * A beantype proxy is asked to be released. We can only
 * release ones that were not in the permanent set that we 
 * initialized with. Those in the permanent set can't be changed
 * so we can't release them. 
 *
 * Answer whether it can be released from the server
 * too.
 *
 * NOTE: Package protected since only REMStandardBeanProxyFactory should call it.
 */
boolean releaseProxy(IBeanTypeProxy proxy) {
/** Currently we won't allow any bean type proxies to be released. We don't have a good
 *  strategy for handling that there may be hard refs from subtypes. One thought is that
 *  beanproxies table should store SoftReferences so that only when space is needed, 
 *  that any beantype that doesn't have a subtype (since subtypes hold a strong ref) or,
 *  is in the permanent table (since that is hardref) could be GC'd. Then what would happen
 *  is on releaseProxy we don't actually release, we change it to a WeakRef so that it would
 *  definitely be released on a GC. These are complicated arch. and we're not sure if it
 *  should be allowed or not. So for now, we don't allow them to be released.
	if (!fPermanentProxies.contains(proxy)) {
		// We can release it. It is not one of the permanent ones.
		synchronized(this) {
			fBeanProxies.remove(proxy.getTypeName());
			return true;
		}
	}
*/	
	return false;
}

/**
 * Terminate this factory. Since all of the proxies are registered in the
 * proxy factory, there is no need to release them here. There is no
 * need to clear out any fields since this factory will not be held onto
 * by anything and so it will be GC'd.
 */
public void terminateFactory() {
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isBeanTypeRegistered(String)
 */
public synchronized boolean isBeanTypeRegistered(String className) {
	return fBeanProxies.containsKey(getJNIFormatName(className));
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#registeredTypes()
 */
public Set registeredTypes() {
	return fBeanProxies.keySet();
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isBeanTypeNotFound(String)
 */
public synchronized boolean isBeanTypeNotFound(String className) {
	return fNotFoundTypes != null && fNotFoundTypes.contains(getJNIFormatName(className));
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#isMaintainNotFoundTypes()
 */
public synchronized boolean isMaintainNotFoundTypes() {
	return fNotFoundTypes != null;
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory#setMaintainNotFoundTypes(boolean)
 */
public synchronized void setMaintainNotFoundTypes(boolean maintain) {
	if (maintain) {
		if (fNotFoundTypes == null)
			fNotFoundTypes = new HashSet();
	} else
		fNotFoundTypes = null;
}

}


