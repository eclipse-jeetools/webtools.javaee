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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMStandardBeanProxyFactory.java,v $
 *  $Revision: 1.13 $  $Date: 2005/06/21 20:27:52 $ 
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
 * The Remote VM Standard Bean Proxy Factory.
 * Creation date: (12/3/99 12:01:45 PM)
 * @author: Joe Winchester
 */
public final class REMStandardBeanProxyFactory implements IStandardBeanProxyFactory {
	protected final REMProxyFactoryRegistry fRegistry;
	protected final REMStandardBeanTypeProxyFactory fBeanTypeProxyFactory;	// Convenience field.

	
	// We need a map for mapping object id's to the proxy. The entry will actually be
	// a REMStandardBeanProxyFactory.WeakProxyReference. This is so that the
	// proxy will not be held tightly by this map and can be GC'd if not referenced.
	// Periodically, the reference queue will be processed to remove any of the
	// entries that have been GC'd and the server will be told that it can release
	// the ID on its side.
	//
	// This map will be used as synchronize object also for access to it.
	//
	// There will be a low priority job that will occasionally process the GC'd objects
	// and remove them from the queue and the remote vm. It will not remove during lockedTransactions.
	//
	// NOTE: Certain proxies never get added to the map. They are the
	// standard types/class (i.e. null, byte, int, long, short, double, float, byte, string). These
	// always have the value saved in the proxy so that it can be retrieved without going back
	// to the server. 
	private final HashMap fIDToProxiesMap = new HashMap(1000);	// Map ID's to proxies. The proxies have their id's so we don't need a reverse map
	private int transactionLockCount = 0;	// Count of transactions that have locked access. It is changed under sync control of fIDToProxies map.
	
	/**
	 * The Weak reference used for the id to proxies map for the proxy
	 * so that we can clean it up when the proxy has been garbage collected.
	 *
	 * It is important that all changes to the ProxyRef are done through sync on fIDToProxiesMap.
	 */
	private static class ProxyRef extends java.lang.ref.WeakReference {
		private Integer id;	// We need the id because this reference will be on the value side
					// and not the key side of the map, so we need to know the key
					// so that the object can be removed from the map.
					//
					// If the ID is null, then this means this entry has been processed
					// in some way and should not be released or removed from the table.
		private int useCnt = 1;	// Usage/Ref count. Needed so that we don't get rid of too soon. Since we don't
					// create a ProxyRef without a usage, we start it off with one. It will be incremented
					// then on retrievals when needed by users outside of the remote factories themselves.
					// It won't be physically released until either GC got rid of it, or it goes to 0 or less
					// on a release request.
					
		public ProxyRef(Integer anID, Object proxy, java.lang.ref.ReferenceQueue q) {
			super(proxy, q);
			id = anID;
		}
		
		public Integer getID() {
			return id;
		}
		
		public void clear() {
			super.clear();
			id = null;
		}
		
		public synchronized int useCnt() {
			return useCnt;
		}
		
		public synchronized void incrUseCnt() {
			useCnt++;
		}
		
		public synchronized int decrUseCnt() {
			if (--useCnt < 0)
				useCnt = 0;
			return useCnt;
		}
	}
	
	/* Reference queue for cleared Proxies */
	private java.lang.ref.ReferenceQueue queue = new java.lang.ref.ReferenceQueue();	
	
	/**
	 * Process the entries on the id to proxies map that have been garbage collected.
	 * It is package-protected because only REMRegistryController should call it.
	 */
	void processQueue() {
		ProxyRef pr;	
		while (true) {
			if (Thread.interrupted())
				return;	// Maybe going down.	(This actually a kludge because the thread happens to be the master thread from the registry controller).
				
			synchronized (fIDToProxiesMap) {
				if (queue == null || transactionLockCount > 0)
					break;	// Either no queue (we are cleaning up) or in a transaction, stop processing and retry on next time slice.
				if ((pr = (ProxyRef) queue.poll()) != null) {				
					if (pr.getID() != null) {
						// It hasn't been processed by some other means.				
						fIDToProxiesMap.remove(pr.getID());						
						releaseID(pr.getID().intValue());
					}
				} else
					break;	// There are no more waiting, so leave.
			}
		}
	}
	
	
/**
 * REMBeanProxyFactory constructor comment.
 *
 * Note: It assumes the beantype factory has already been registered.
 */
REMStandardBeanProxyFactory(REMProxyFactoryRegistry aRegistry) {
	fRegistry = aRegistry;
	aRegistry.registerBeanProxyFactory(this);
	fBeanTypeProxyFactory = (REMStandardBeanTypeProxyFactory) aRegistry.getBeanTypeProxyFactory();
	fBeanTypeProxyFactory.initialize(this);	// Now we're ready for the beantype factory to be initialized.
	
}


/**
 * Register a collection of Proxies
 */
public void registerProxies(Collection proxies) {
	synchronized(fIDToProxiesMap) {
		Iterator itr = proxies.iterator();
		while (itr.hasNext()) {
			IREMBeanProxy proxy = (IREMBeanProxy) itr.next();
			if (proxy instanceof IBeanTypeProxy || !(proxy.getTypeProxy() instanceof IREMConstantBeanTypeProxy)) {
				ProxyRef oldRef = (ProxyRef) fIDToProxiesMap.put(proxy.getID(), new ProxyRef(proxy.getID(), proxy, queue));
				if (oldRef != null) {
					// We've replaced it with a new one, so we will clear out the ref so that it won't later try to remove itself
					oldRef.clear();
				}
			}
		}		
	}
}

/**
 * Register a single proxy
 */
public void registerProxy(IREMBeanProxy proxy) {
	if (proxy instanceof IBeanTypeProxy || !(proxy.getTypeProxy() instanceof IREMConstantBeanTypeProxy))
		synchronized(fIDToProxiesMap) {
			ProxyRef oldRef = (ProxyRef) fIDToProxiesMap.put(proxy.getID(), new ProxyRef(proxy.getID(), proxy, queue));
			if (oldRef != null) {
				// We've replaced it with a new one, so we will clear out the ref so that it won't later try to remove itself
				oldRef.clear();
			}							
		}
}

/**
 * Release a proxy because no longer needed.
 */
public void releaseProxy(IBeanProxy proxy) {
	if (!proxy.isValid())
		return;
	if (proxy instanceof IBeanTypeProxy && !fBeanTypeProxyFactory.releaseProxy((IBeanTypeProxy) proxy))
		return;	// BeanType and type factory won't allow release of it.
	Integer id = ((IREMBeanProxy) proxy).getID();
	synchronized(fIDToProxiesMap) {			
		// Synced in here so that we will remove it before some one else from a different thread may try
		// to access it again.
		if (id.intValue() != Commands.NOT_AN_ID) {
			ProxyRef ref = (ProxyRef) fIDToProxiesMap.get(id);
			if (ref == null || ref.decrUseCnt() <= 0) {
				// No usage, so do actual release.
				fIDToProxiesMap.remove(id);
				((IREMBeanProxy) proxy).release();
				if (ref != null)
					ref.enqueue();	// Queue it up so that on next release cycle it will go away.
			} 
		} else {
			((IREMBeanProxy) proxy).release();			
		}
	}
}

/**
 * Release a specific ID. This is used in case an ID has been sent
 * but we couldn't proxy it. In that case we only have an ID. It is
 * also used when a proxy has been released explicitly or through GC.
 * In that case it has already been de-registered.
 */
private void releaseID(int anID) {
	try {
		IREMConnection connect = fRegistry.getFreeConnection();
		try {
			connect.releaseID(anID);
		} finally {
			fRegistry.returnConnection(connect);
		}
	} catch (IllegalStateException e) {
		// Couldn't get connection, don't bother with a release.
	}
}

/**
 * For the Remote Factory we will create an REMBeanProxy using the null constructor
 * Package protected so only REMBeanTypeProxies can create instances.
 */
 
IBeanProxy createBeanProxy(IREMBeanTypeProxy aTypeProxy) throws ThrowableProxy {
	return REMStandardBeanProxyConstants.getConstants(fRegistry).getClassNewInstance().invoke(null, aTypeProxy);
}

/**
 * For the Remote Factory we will create a REMBeanProxy using the initializationString.
 * Package protected so only REMBeanTypeProxies can create instances. 
 */
IBeanProxy createBeanProxy(IREMBeanTypeProxy aTypeProxy, String initializationString)
	throws ThrowableProxy, CommandException, ClassCastException, InstantiationException, IllegalStateException {
	IREMConnection connect = fRegistry.getFreeConnection();
	startTransaction();
	// Starting a transaction, we will be getting id's back and need to get data in a separate step, so we need to group it in a transaction.
	try {
		Commands.ValueObject newInstanceData = null;
		try {
			newInstanceData = getNewInstanceData(aTypeProxy, initializationString, connect);
		} catch (CommandErrorException e) {
			switch (e.getErrorCode()) {
				case Commands.CLASS_CAST_EXCEPTION:
					// The result was not of the correct type.
					throw new ClassCastException(
						MessageFormat.format(ProxyRemoteMessages.getString("Classcast_EXC_"), new Object[] {extractFirstLine(initializationString), aTypeProxy.getTypeName()})); //$NON-NLS-1$
				case Commands.CANNOT_EVALUATE_STRING:
					// Want to log the exception that caused it to not evaluate.
					// Don't need to log this side, just log the RemoteVM side of the trace.
					java.io.StringWriter s = new java.io.StringWriter();
					java.io.PrintWriter w = new java.io.PrintWriter(s);
					((ThrowableProxy) e.getErrorObject()).printProxyStackTrace(w);
					ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, s.toString(), null));
					throw new InstantiationException(
						MessageFormat.format(ProxyRemoteMessages.getString("Instantiate_EXC_"), new Object[] {extractFirstLine(initializationString)})); //$NON-NLS-1$
				default:
					throw e; //$NON-NLS-1$
			}
		} catch (CommandException e) {
			if (e.isRecoverable()) {
				ProxyPlugin.getPlugin().getLogger().log( 
					new Status(
						IStatus.WARNING,
						ProxyPlugin.getPlugin().getBundle().getSymbolicName(),
						0,
						"", //$NON-NLS-1$
						e));
			} else {
				// It failed in the command, try again.
				fRegistry.closeConnection(connect);
				connect = null;
				connect = fRegistry.getFreeConnection();
				try {
					newInstanceData = getNewInstanceData(aTypeProxy, initializationString, connect);
				} catch (CommandException eAgain) {
					// It failed again. Close this connection and don't let it be returned.
					fRegistry.closeConnection(connect);
					connect = null; // This is so that it won't be returned.
				}
			}
		} finally {
			if (connect != null)
				fRegistry.returnConnection(connect);
		}

		if (newInstanceData != null)
			return getBeanProxy(newInstanceData); // Turn it into a proxy
	} finally {
		stopTransaction();	// Now we can release the transaction.
	}

return null;
}

private String extractFirstLine(String initString) {
	// Need to extract the first line for the messageFormat not to barf.
	int cr = initString.indexOf('\r');
	int lf = initString.indexOf('\n');	
	if (cr != -1 || lf != -1) {
		if (cr == -1 || (lf != -1 && lf < cr))
			return initString.substring(0, lf);
		else
			return initString.substring(0, cr);
	} else
		return initString;
}
	
	
/**
 * actually create it using a passed in connection. This allows retry capability.
 *
 * This will send the request over to the connection.
 *
 * If we get an OBJECT back, then the beantypeproxy that the classID in the
 * value points to must be of type IREMConstantBeanTypeProxy so that we can
 * send this new object to the correct beantype to create the correct proxy.
 *
 * If we get an OBJECT_ID back, then the beantypeproxy that the classID in
 * the value points to must be able to handle creating a proxy of that type. 
 */
private Commands.ValueObject getNewInstanceData(IREMBeanTypeProxy aTypeProxy, String initializationString, IREMConnection connect) throws ThrowableProxy, CommandException {	
	try {
		Commands.ValueObject newInstanceData = new Commands.ValueObject();
		connect.getNewInstance(aTypeProxy.getID().intValue(), initializationString, newInstanceData);
		return newInstanceData;
	} catch (CommandErrorException e) {
		// TBD - Needs to handle error of not evaluatable and send over to the compilor.
		processErrorReturn(e);	// Process this
	}
	return null;
}

/**
 * actually create it using a passed in connection. This allows retry capability.
 */
private void getObjectInstanceData(IREMConnection connect, int objectID, Commands.ValueObject valueReturn) throws ThrowableProxy, CommandException {	
	try {
		connect.getObjectData(objectID, valueReturn);
	} catch (CommandErrorException e) {
		processErrorReturn(e);	// Process this
	}
}

/**
 * Get a bean proxy from the value object passed in. If not yet created, create one.
 * NOTE: NULL type actually returns a true null. This is so that if people are casting
 * the returned proxy to a specific type (e.g IIntegerBeanProxy), then they won't get
 * a ClassCastException, they will get a null. This is easier for them to handle.
 * 
 * NOTE: This is public ONLY so that extension factories can create correct types of
 * proxies in consistent manner from a value object. 
 * 
 * It is important that this is called
 * from within a transaction only because otherwise the value could have invalid data
 * by the time we try to get the data out of it.
 */
public IBeanProxy getBeanProxy(Commands.ValueObject value) throws ThrowableProxy, CommandException {
	switch (value.type) {
		// Null result.
		case (byte) Commands.VOID_TYPE:
			return null;
		// A constant object was received.
		case Commands.OBJECT:
			IREMConstantBeanTypeProxy constantBeanType = null;
			try {
				constantBeanType = (IREMConstantBeanTypeProxy) getBeanType(value.classID);
				if (constantBeanType == null)
					return null;	// Cannot find the type to create it.
			} catch (ClassCastException e) {
				// It wasn't a constant type, so we can't create it. Return null.
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
				return null;
			}
			
			return constantBeanType.newBeanProxy(value);
		
		// An existing object_id was returned, the object should exist. If it doesn't
		// then submit the info command to try to recreate it correctly.
		case Commands.OBJECT_ID:
			Integer objectID = new Integer(value.objectID);
			IBeanProxy proxy = retrieveProxy(objectID, true);
			if (proxy != null)
				return proxy;

			// Not found, need to try to recreate it.
			IREMConnection connect = fRegistry.getFreeConnection();
			try {
				getObjectInstanceData(connect, value.objectID, value); // Go and get the data
			} catch (CommandErrorException e) {
				if (e.isRecoverable()) {
					ProxyPlugin.getPlugin().getLogger().log(
						new Status(
							IStatus.WARNING,
							ProxyPlugin.getPlugin().getBundle().getSymbolicName(),
							0,
							"", //$NON-NLS-1$
							e));
					return null;
				} else {
					// Try one more time.
					fRegistry.closeConnection(connect);
					connect = null;
					connect = fRegistry.getFreeConnection();
					try {
						getObjectInstanceData(connect, value.objectID, value); // Go and get the data
					} catch (CommandErrorException eAgain) {
						fRegistry.closeConnection(connect);
						connect = null;
						throw eAgain;
					}
				}
			} finally {
				if (connect != null)
					fRegistry.returnConnection(connect);
			}

			return getBeanProxy(value); // Now process it to create the new data.
			
		// An new object id. Need to get the class type and let it create it.
		case Commands.NEW_OBJECT_ID:
			try {
				IREMBeanTypeProxy newBeanType = getBeanType(value.classID);
				IREMBeanProxy newProxy = newBeanType.newBeanProxy(new Integer(value.objectID));
				if (newProxy != null)
					registerProxy(newProxy);
				return newProxy;
			} catch (CommandException e) {
				// The server has created a new object, but we couldn't create/register a proxy for it.
				// We need to release it so that the server can get rid of it. Otherwise it would hang
				// around over there forever.
				releaseID(value.objectID);
				throw e;				
			} catch (RuntimeException e) {
				// The server has created a new object, but we couldn't create/register a proxy for it.
				// We need to release it so that the server can get rid of it. Otherwise it would hang
				// around over there forever.
				releaseID(value.objectID);
				throw e;
			}			
			
		// An exception was thrown, create the ThrowableProxy and then throw it.
		case Commands.THROW:
			IREMBeanProxy newThrowProxy	= null;
			try {
				IREMBeanTypeProxy newThrowType = newThrowType = getBeanType(value.classID);
				newThrowProxy = newThrowType.newBeanProxy(new Integer(value.objectID));
				if (newThrowProxy != null)
					registerProxy(newThrowProxy);
			} catch (CommandException e) {
				// The server has created a new object, but we couldn't create/register a proxy for it.
				// We need to release it so that the server can get rid of it. Otherwise it would hang
				// around over there forever.
				releaseID(value.objectID);
				throw e;				
			} catch (RuntimeException e) {
				// The server has created a new object, but we couldn't create/register a proxy for it.
				// We need to release it so that the server can get rid of it. Otherwise it would hang
				// around over there forever.
				releaseID(value.objectID);
				throw e;
			}				
			// It really should be a throwable, but if not, just return it.
			if (newThrowProxy instanceof ThrowableProxy)
				throw (ThrowableProxy) newThrowProxy;
			else
				return newThrowProxy;		
		
		// It is one of the standard kinds, which are Constant types
		default:
			IREMConstantBeanTypeProxy standardBeanType = null;
			try {
				standardBeanType = (IREMConstantBeanTypeProxy) getBeanType(value.type);
				if (standardBeanType == null)
					return null;	// Cannot find the type to create it.
			} catch (ClassCastException e) {
				// It wasn't a standard type, so we can't create it. Return null.
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
				return null;
			}
			return standardBeanType.newBeanProxy(value);
	}						
}

/**
 * Process the error exception. Get the data from it and turn it into proxy data.
 * If it is a THROW, then it will throw the Throwable instead. 
 *
 * NOTE: It is public ONLY so that extension factories can process errors in
 * an consistent manner. 
 * 
 * It is important that this be called only within a transaction.
 */
public void processErrorReturn(CommandErrorException e) throws CommandException, ThrowableProxy {
	int code = e.getErrorCode();
	Object data = null;
	if (code == Commands.THROWABLE_SENT)
		data = getBeanProxy(e.getValue());	// It is Throw sent, so let the throw from getBeanProxy continue on out. (But as a safety, we still try to get the data.
	else {
		try {
			data = getBeanProxy(e.getValue());
		} catch (ThrowableProxy t) {
			// But we want to keep throwables in this case. They are just data for the command error.
			data = t;
		}
	}
	throw new CommandErrorException(MessageFormat.format(ProxyRemoteMessages.getString("RemoteCmd_EXC_"), new Object[] {new Integer(code)}), code, e.getValue(), data);	// Create a new one and throw it containing the proxied data. //$NON-NLS-1$
}
	 

/**
 * Get a beantype where the id passed in is the classID of the beantype.
 * If not found, then go get it loaded. If it can't be found, then we will
 * release the id because it means we have an id from the server, but we
 * can't create a proxy for it, so don't keep the server holding it.
 * NOTE: This is public ONLY so that extension factories can create correct types
 * in a consistent manner from the id.
 * 
 * It is important that this be called only from within a transaction. 
 */
public IREMBeanTypeProxy getBeanType(int id) throws CommandException {
	IREMBeanTypeProxy beanType = null;
	try {
		Integer classID = new Integer(id);
		beanType = (IREMBeanTypeProxy) retrieveProxy(classID, false);
		if (beanType == null)
			beanType = fBeanTypeProxyFactory.createBeanTypeProxy(classID);	// We don't have it, need to go to the factory so that it can go query what it needs
		if (beanType == null)
			return null;	// Cannot find the type to create it.
	} catch (ClassCastException e) {
		// It wasn't a bean type, so we can't create it. Return null.
		ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
	} finally {
		if (beanType == null)
			releaseID(id);	// Couldn't create a proxy, so release the id.
	}
	return beanType;
}


/**
 * Return a proxy wrapping the primitive integer
 */
public IIntegerBeanProxy createBeanProxyWith(int aPrimitiveInteger) {

	return fBeanTypeProxyFactory.intType.createIntegerBeanProxy(aPrimitiveInteger);
}

/**
 * Return a proxy wrapping the primitive byte
 */
public INumberBeanProxy createBeanProxyWith(byte aPrimitiveByte) {

	return fBeanTypeProxyFactory.byteType.createByteBeanProxy(aPrimitiveByte);
}

/**
 * Return a proxy wrapping the primitive char
 */
public ICharacterBeanProxy createBeanProxyWith(char aPrimitiveChar) {

	return fBeanTypeProxyFactory.charType.createCharacterBeanProxy(aPrimitiveChar);
}

/**
 * Return a proxy wrapping the primitive short
 */
public INumberBeanProxy createBeanProxyWith(short aPrimitiveShort) {

	return fBeanTypeProxyFactory.shortType.createShortBeanProxy(aPrimitiveShort);
}

/**
 * Return a proxy wrapping the primitive long
 */
public INumberBeanProxy createBeanProxyWith(long aPrimitiveLong) {

	return fBeanTypeProxyFactory.longType.createLongBeanProxy(aPrimitiveLong);
}
/**
 * Return a proxy wrapping the primitive double
 */
public INumberBeanProxy createBeanProxyWith(double aPrimitiveDouble) {

	return fBeanTypeProxyFactory.doubleType.createDoubleBeanProxy(aPrimitiveDouble);
}
/**
 * Return a proxy wrapping the primitive float
 */
public INumberBeanProxy createBeanProxyWith(float aPrimitiveFloat) {

	return fBeanTypeProxyFactory.floatType.createFloatBeanProxy(aPrimitiveFloat);
}

/**
 * createBeanProxyWith method comment.
 */
public IBooleanBeanProxy createBeanProxyWith(Boolean aBoolean) {
	return fBeanTypeProxyFactory.booleanClass.createBooleanBeanProxy(aBoolean);
}
/**
 * Return a proxy that wraps the Integer argument
 */
public IIntegerBeanProxy createBeanProxyWith(Integer anInteger) {

	return fBeanTypeProxyFactory.integerClass.createIntegerBeanProxy(anInteger);
}
/**
 * createBeanProxyWith method comment.
 */
public INumberBeanProxy createBeanProxyWith(Number aNumber) {
	REMAbstractNumberBeanTypeProxy type = (REMAbstractNumberBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(aNumber.getClass().getName());
	return type.createNumberBeanProxy(aNumber);
}
/**
 * Return a proxy for the argument
 */
public IStringBeanProxy createBeanProxyWith(String aString) {

	return fBeanTypeProxyFactory.stringClass.createStringBeanProxy(aString);

}
/**
 * Return a proxy for the argument
 */
public ICharacterBeanProxy createBeanProxyWith(Character aCharacter) {

	return fBeanTypeProxyFactory.characterClass.createCharacterBeanProxy(aCharacter);

}
/**
 * createBeanProxyWith method comment.
 */
public IBooleanBeanProxy createBeanProxyWith(boolean aPrimitiveBoolean) {
	return fBeanTypeProxyFactory.booleanType.createBooleanBeanProxy(aPrimitiveBoolean);
}

/**
 * Create an array bean proxy.
 *
 *   - (int, new int[2] {3, 4}) will create:
 *      int [3] [4]
 *
 *   - (int[], new int[1] {1})
 *      int [1]
 *
 *   - (int[], new int[2] {2,3})
 *      int [2] [3]
 * 
 *
 *   - (int[], null) or (int[], new int[0]) or (int, null) or (int, new int[0])
 *      int [0]...
 *     or
 *     (int[][]..., null) or (int[][]..., new int[0])
 *      int[0][]...
 *     This is because an array instance with no specified dimensions is not valid. 
 *
 *   - (int[][], new int[1] {3})
 *      int[3][]
 */
public IArrayBeanProxy createBeanProxyWith(IBeanTypeProxy type, int[] dimensions) throws ThrowableProxy {
	if (type.isArray())
		return ((REMArrayBeanTypeProxy) type).createBeanProxyWith(dimensions);	// Already an array type, just pass it on.
	else {
		// It is not an array type, so we need to get an array of this type and dimensions.
		REMArrayBeanTypeProxy arrayType = (REMArrayBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(type.getTypeName(), dimensions.length);
		return arrayType.createBeanProxyWith(dimensions);
	}
}
/**
 * Create a one-dimensional array. 
 * The result will be the same as calling 
 *   createBeanProxyWith(IBeanTypeProxy type, new int[1] {x})
 * where 'x' is the value passed in as the dimension.
 */
public IArrayBeanProxy createBeanProxyWith(IBeanTypeProxy type, int dimension) throws ThrowableProxy {
	return createBeanProxyWith(type, new int[] {dimension});
}

/**
 * Retrieve the proxy from the mapping table. Handle already GC'd.
 * If this returns null, it is important that the caller tries to recreate
 * it since the id is still valid on the server.
 */
private IBeanProxy retrieveProxy(Integer objectID, boolean incrementUseCnt) {
	synchronized (fIDToProxiesMap) {
		ProxyRef ref = (ProxyRef) fIDToProxiesMap.get(objectID);
		if (ref != null) {
			Object bp = ref.get();			
			if (ref.isEnqueued() || bp == null) {
				// It's waiting to be removed, or has been removed. i.e. GC'd, so just remove it from the map, next processQueue will remove it from the queue.
				fIDToProxiesMap.remove(objectID);
				ref.clear();		// This is so that when the processQueue see's it, 
							// it won't send a release request to the server 
							// since it will be recreated when this method returns.
				return null;
			} else {
				if (incrementUseCnt)
					ref.incrUseCnt();
				return (IBeanProxy) bp;
			}	
		} else
			return null;
	}
}

/**
 * Start Transaction: During the time between start/stop transaction,
 * proxies will not be cleaned up. This prevents the case of a two step
 * transaction where the returned ID from the remote vm is for a proxy
 * that is about to be cleaned up, and then having that proxy disappear
 * when going for the data for it.
 * 
 * Note: It is IMPERITIVE that the start/stop is used in the following way:
 *     factory.startTransaction();
 *     try {
 *         do your stuff...
 *     } finally {
 *         factory.stopTransaction();
 *     }
 * 
 * This way it can never accidently leave it in a locked state.
 */
public	void startTransaction()  {
	synchronized (fIDToProxiesMap) {
		transactionLockCount++;
	}
}

public boolean inTransaction() {
	synchronized (fIDToProxiesMap) {
		return transactionLockCount != 0;
	}
}

/**
 * Stop Transaction: During the time between start/stop transaction,
 * proxies will not be cleaned up. This prevents the case of a two step
 * transaction where the returned ID from the remote vm is for a proxy
 * that is about to be cleaned up, and then having that proxy disappear
 * when going for the data for it.
 * 
 * Note: It is IMPERITIVE that the start/stop is used in the following way:
 *     factory.startTransaction();
 *     try {
 *         do your stuff...
 *     } finally {
 *         factory.stopTransaction();
 *     }
 * 
 * This way it can never accidently leave it in a locked state.
 */
public	void stopTransaction()  {
	synchronized (fIDToProxiesMap) {
		if (--transactionLockCount < 0)
			transactionLockCount = 0;	// Shouldn't occur, but just in case.
	}
}

/**
 * Terminate the factory. If this is being terminated, then the server is being terminated too.
 * So just go through all of the proxies and release them, but don't send any notification to
 * the server since the server is subsequently just going to throw them away when it terminates.
 * <p>
 * This can't run async, so wait is a suggestion here.
 */
public void terminateFactory(boolean wait) {
	synchronized (fIDToProxiesMap) {
		Iterator itr = fIDToProxiesMap.values().iterator();
		while (itr.hasNext()) {
			ProxyRef ref = (ProxyRef) itr.next();
			if (ref != null) {
				Object bp = ref.get();
				// If not cleaned up and not enqueued, release it.
				if (bp != null && !ref.isEnqueued())
					((IREMBeanProxy) bp).release();
			}
		}
		
		fIDToProxiesMap.clear();
		queue = null;	// Don't bother processing the queue, don't need to now.
	}
}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createExpression()
	 */
	public IExpression createExpression() {
		return new REMExpression(this.fRegistry);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#createBeanProxyFrom(java.lang.String)
	 */
	public IBeanProxy createBeanProxyFrom(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
		try {
			return createBeanProxy(fBeanTypeProxyFactory.voidType, initializationString);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$		}
		}
		return null;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IStandardBeanProxyFactory#convertToPrimitiveBeanProxy(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public IBeanProxy convertToPrimitiveBeanProxy(IBeanProxy nonPrimitiveProxy) {
		if (nonPrimitiveProxy == null)
			return null;
		if (!nonPrimitiveProxy.isValid())
			return nonPrimitiveProxy;
		IREMBeanTypeProxy type = (IREMBeanTypeProxy) nonPrimitiveProxy.getTypeProxy();
		// Step into the internals. The ID is a constant int, so we can use a switch stmt.
		switch (type.getID().intValue()) {
			case Commands.BOOLEAN_CLASS:
				return this.createBeanProxyWith(((IBooleanBeanProxy) nonPrimitiveProxy).booleanValue());
			case Commands.BYTE_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).byteValue());
			case Commands.CHARACTER_CLASS:
				return this.createBeanProxyWith(((ICharacterBeanProxy) nonPrimitiveProxy).charValue());
			case Commands.DOUBLE_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).doubleValue());
			case Commands.FLOAT_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).floatValue());
			case Commands.INTEGER_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).intValue());
			case Commands.LONG_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).longValue());
			case Commands.SHORT_CLASS:
				return this.createBeanProxyWith(((INumberBeanProxy) nonPrimitiveProxy).shortValue());
			default:
				return nonPrimitiveProxy;
		}
	}
}


