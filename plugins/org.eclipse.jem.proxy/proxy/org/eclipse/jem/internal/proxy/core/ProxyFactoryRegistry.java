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
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: ProxyFactoryRegistry.java,v $
 *  $Revision: 1.11 $  $Date: 2005/06/15 20:19:11 $ 
 */


import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.jem.util.TimerTests;

/**
 * Registry of proxy factories on a per-VM basis.
 * There can be multiple VM's, this would be required by the
 * desktop because more than one project could be open, and each
 * would require their own VM because the classpaths would be different.
 * This class may be subclassed to provide more factories for a particular
 * VM. 
 * Creation date: (3/13/00 10:48:10 AM)
 * @author: Richard Lee Kulp
 */
public abstract class ProxyFactoryRegistry {
	
	protected boolean fIsValid = true;	// The registry is valid until terminated.
	protected ListenerList registryListeners = new ListenerList();
	
	/**
	 * IRegistryListener
	 * Listen for the registry being terminated.
	 */
	public interface IRegistryListener {
		/**
		 * Method registryTerminated.
		 * 
		 * The registry has been terminated. When this is called the registry
		 * is no longer valid. It should not be assumed any calls can be made
		 * to the registry.
		 * 
		 * @param registry
		 */
		public void registryTerminated(ProxyFactoryRegistry registry);
	}
		

	// Factory registration IS NOT a dynamic registration. Once registered for a VM or an instance
	// they should not be changed. This is because references are cached in other objects.
	// The VM associated with this Registry shouldn't exited and restarted because
	// the cached object will then have invalid references to no longer existing objects.
	// If the VM needs to be restarted, then all proxies need to be tossed.
	
	//
	// Individual ProxyFactoryRegistry, applies to a particular VM.
	//

	// ***************************
	//
	// IBeanProxyFactory Registry
	//
	
	// The standard bean  proxy factory, the one the desktop will use for packages that have not
	// been extended and registered by customization developers.
	protected IStandardBeanProxyFactory fCurrentStandardBeanProxyFactory;

	// The directory of registered extension factories, typically registered by package.
	final protected Hashtable fRegisteredExtensionBeanProxyFactories = new Hashtable();

	// ***************************
	//
	// IBeanTypeProxyFactory Registry
	//
	
	// The standard bean type proxy factory, the one the desktop will use for packages that have not
	// been extended and registered by customization developers.
	protected IStandardBeanTypeProxyFactory fCurrentStandardBeanTypeProxyFactory;

	// The directory of registered extension factories, typically registered by package.
	final protected Hashtable fRegisteredExtensionBeanTypeProxyFactories = new Hashtable();
	

	// ***************************
	//
	// Other factories
	//
		
	protected IMethodProxyFactory fMethodProxyFactory = null;	// Method Proxy Factory

	// ***************************
	//
	// Constants Registry. Different extensions can store
	// objects by key as a registry of constants.
	// For example, java.awt extension will store an object
	// which has some pre-fetched method proxies stored in it so
	// that they don't need to be continually recreated.
	//
	// This feature should be used with care, so that
	// only proxies that really need to be cached should
	// be cached.
	//
	// Don't store proxies to live beans in here because
	// those will be created and destroyed over and over,
	// and the instance in this cache will get stale.
	// Should only store things like often used method
	// proxies that once created are rarely changed.
	//
	// Since the proxies can be on any VM, you should have
	// an interface defining what's available in your
	// constants entry, and access it through that.

	protected final Hashtable fRegisteredConstants = new Hashtable();		

	/**
	 * ProxyFactoryRegistry constructor comment.
	 */
	public ProxyFactoryRegistry() {
		super();
	}
	
	/**
	 * Method addRegistryListener.
	 * @param listener
	 */
	public void addRegistryListener(IRegistryListener listener) {
		registryListeners.add(listener);
	}
	
	/**
	 * Method removeRegistryListener.
	 * @param listener
	 */
	public void removeRegistryListener(IRegistryListener listener) {
		registryListeners.remove(listener);
	}
	
	protected void fireRegistryTerminated() {
		if (!registryListeners.isEmpty()) {
			Object[] list = registryListeners.getListeners();
			for (int i = 0; i < list.length; i++) {
				((IRegistryListener) list[i]).registryTerminated(this);
			}
		}
	}
	
	/**
	 Return the current factory to be used for creating IBeanProxy instances
	 */
	public IStandardBeanProxyFactory getBeanProxyFactory() {
		if (fCurrentStandardBeanProxyFactory == null) {
			throw new RuntimeException(ProxyMessages.getString(ProxyMessages.PROXYFACTORY_NOBEANPROXY));//$NON-NLS-1$
		}
		return fCurrentStandardBeanProxyFactory;
	}
	/**
	 Return the current registered bean proxy factory to be used for creating IBeanProxy instances
	 */
	public IBeanProxyFactory getBeanProxyFactoryExtension(String packageName) {
		return (IBeanProxyFactory)fRegisteredExtensionBeanProxyFactories.get(packageName);
	}
	
	/**
	 Return the current factory to be used for creating IBeanTypeProxy instances
	 */
	public IStandardBeanTypeProxyFactory getBeanTypeProxyFactory() {
		if (fCurrentStandardBeanTypeProxyFactory == null) {
			throw new RuntimeException(ProxyMessages.getString(ProxyMessages.PROXYFACTORY_NOBEANTYPEPROXY));//$NON-NLS-1$
		}
		return fCurrentStandardBeanTypeProxyFactory;
	}
	/**
	 Return the current registered bean type proxy factory to be used for creating IBeanTypeProxy instances
	 */
	public IBeanTypeProxyFactory getBeanTypeProxyFactoryExtension(String packageName) {
		return (IBeanTypeProxyFactory)fRegisteredExtensionBeanTypeProxyFactories.get(packageName);
	}
	/**
	 * Return the requested constants entry
	 */
	public Object getConstants(Object key) {
		return fRegisteredConstants.get(key);
	}
	/**
	 * Return the MethodProxyFactory for this VM.
	 * Creation date: (3/13/00 10:54:59 AM)
	 * @return org.eclipse.jem.internal.proxy.core.IMethodProxyFactory
	 */
	public IMethodProxyFactory getMethodProxyFactory() {
		return fMethodProxyFactory;
	}
	
	
	/**
	 * Set the requested constants entry for this VM.
	 */
	public void registerConstants(Object key, Object constant) {
		fRegisteredConstants.put(key, constant);
	}
    
    /**
     * Remove the constants for the given key.
     * @param key
     * 
     * @since 1.1.0
     */
    public void deregisterConstants(Object key) {
        fRegisteredConstants.remove(key);
    }
	/**
	 * Release the proxy, no longer needed.
	 * This is a helper method to easily access the release from the bean proxy factory.
	 */
	public void releaseProxy(IBeanProxy proxy) {
		// To simply things if release is called when the factory is down (invalid), then just
		// go on because the proxy is already released.
		if (fCurrentStandardBeanProxyFactory != null)
			fCurrentStandardBeanProxyFactory.releaseProxy(proxy);
	} 

	/**
	 * Is this a valid registry, i.e. is it not terminated.
	 */
	public boolean isValid() {
		return fIsValid;
	}
	
	/**
	 * Terminate the registry without waiting.
	 * <p>
	 * Terminate the registry, but don't necessarily wait for it.
	 *
	 * @see #terminateRegistry(boolean)
	 * @since 1.0.0
	 */
	public final void terminateRegistry() {
		terminateRegistry(false);
	}
		
	/**
	 * Terminate the registry.
	 * <p>
	 * This will go through each factory and terminate it, and
	 * let the subclass terminate. It will then remove all of the factories so that
	 * if there are any proxies still hanging around they won't hold onto everything,
	 * just the this registry will be held onto.
	 *<p>
	 * Note during termination, the factories should not reference any other factory.
	 * It can assume that the factories will take care of themselves and they should
	 * only release their resources.
	 * <p>
	 * The constants registry will not be terminated because they aren't factories.
	 * However, they will be cleared (no longer referenced) from here so that they
	 * can be GC'd.
	 * 
	 * @param wait <code>true</code> to wait for complete termination, <code>false</code> to kick off termination but return ASAP.
	 * @since 1.0.2
	 */
	public final void terminateRegistry(boolean wait) {
		if (!fIsValid)
			return;	// Already or are already terminating. Don't do it again and don't notify again.
		fIsValid = false;
		if (fCurrentStandardBeanTypeProxyFactory != null) {
//			TimerTests.basicTest.startStep("Terminate Bean Type Factory");
			fCurrentStandardBeanTypeProxyFactory.terminateFactory(wait);
//			TimerTests.basicTest.stopStep("Terminate Bean Type Factory");
			fCurrentStandardBeanTypeProxyFactory = null;
		}
		if (fCurrentStandardBeanProxyFactory != null) {
			fCurrentStandardBeanProxyFactory.terminateFactory(wait);
			fCurrentStandardBeanProxyFactory = null;
		}
		if (fMethodProxyFactory != null) {
			fMethodProxyFactory.terminateFactory(wait);
			fMethodProxyFactory = null;
		}
		
		Iterator itr = fRegisteredExtensionBeanTypeProxyFactories.values().iterator();
		while (itr.hasNext()) {
			((IBeanProxyFactory) itr.next()).terminateFactory(wait);
		}
		fRegisteredExtensionBeanTypeProxyFactories.clear();
		
		itr = fRegisteredExtensionBeanProxyFactories.values().iterator();
		while (itr.hasNext()) {
			((IBeanProxyFactory) itr.next()).terminateFactory(wait);
		}
		fRegisteredExtensionBeanProxyFactories.clear();
		
		fRegisteredConstants.clear();
		
		TimerTests.basicTest.startStep("Registry Terminated"); //$NON-NLS-1$
		registryTerminated(wait);
		TimerTests.basicTest.stopStep("Registry Terminated"); //$NON-NLS-1$
		
//		TimerTests.basicTest.startStep("Registry Terminated Notification");
		fireRegistryTerminated();	// Let everyone know that we are gone. This is fired even if wait is false because at this point in time the registry is invalid.
//		TimerTests.basicTest.stopStep("Registry Terminated Notification");
	}
	

	/**
	 * Terminate the Registry. 
	 * <p>
	 * It is up to each registry to determine what this means.
	 * <p>
	 * The wait parm is a suggestion if it is <code>false</code>, but it must be honoured if <code>true</code>
	 * 
	 * @param wait wait for registry to terminate flag.
	 * 
	 * @since 1.0.2
	 */
	protected abstract void registryTerminated(boolean wait);
	
	/**
	 * Get the callback registry.
	 */
	public abstract ICallbackRegistry getCallbackRegistry();
	
}
