/*******************************************************************************
 * Copyright (c)  2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMProxyConstants.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/09 17:21:45 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.util.*;
import java.util.Map.Entry;

import org.eclipse.jem.internal.proxy.core.*;




/**
 * MethodProxyConstants is a cache of IMethodProxies to avoid repeated lookup
 * and thereby avoid the relatively expensive java.lang.reflect calls to repeatedly
 * lookup method by name
 * 
 * @since 1.1.0
 */
public class REMProxyConstants {

	private Map methodsCache = new HashMap(80);
	private Map invokablesCache = new HashMap(80);	
	private Map fieldsCache = new HashMap(80);
	
	/*
	 * Used as the key to the methodCache and invokablesCache when there are parms.
	 * It allows the parms to be either strings or IBeanTypeProxies without the
	 * overhead of creating complicated strings.
	 * 
	 * It will compare method name and each individual parm name without fluffing
	 * up a string and building it up.
	 * 
	 * For no parm methods, just the name of the method as a string will be the key.
	 * 
	 * @since 1.1.0
	 */
	private abstract static class MethodKey {
		public String methodName;
		public MethodKey(String methodName) {
			this.methodName = methodName;
		}
				
		protected abstract boolean compareParms(IBeanTypeProxy[] parms);
		protected abstract boolean compareParms(String[] parms);
		
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			return methodName.hashCode();
		}
	}
	
	private static class MethodKeyStringParms extends MethodKey {
		public String[] parmNames;
		
		public MethodKeyStringParms(String methodName, String[] parmNames) {
			super(methodName);
			this.parmNames = parmNames;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			try {
				return ((MethodKey) obj).compareParms(parmNames);
			} catch (ClassCastException e) {
				return false;
			}
		}
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#hashCode()
		 */
		public int hashCode() {
			int h = super.hashCode();
			for (int i = 0; i < parmNames.length; i++) {
				h += parmNames[i].hashCode();
			}
			return h;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#compareParms(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
		 */
		protected boolean compareParms(IBeanTypeProxy[] parms) {
			if (parms.length != parmNames.length)
				return false;
			for (int i = 0; i < parms.length; i++) {
				if (!parms[i].getTypeName().equals(parmNames[i]))
					return false;
			}
			return true;
		}
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#compareParms(java.lang.String[])
		 */
		protected boolean compareParms(String[] parms) {
			return Arrays.equals(parms, parmNames);
		}
	}
	
	private static class MethodKeyProxyParms extends MethodKey {
		public IBeanTypeProxy[] parmTypes;
		
		public MethodKeyProxyParms(String methodName, IBeanTypeProxy[] parmTypes) {
			super(methodName);
			this.parmTypes = parmTypes;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			try {
				return ((MethodKey) obj).compareParms(parmTypes);
			} catch (ClassCastException e) {
				return false;
			}
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#hashCode()
		 */
		public int hashCode() {
			int h = super.hashCode();
			for (int i = 0; i < parmTypes.length; i++) {
				h += parmTypes[i].getTypeName().hashCode();
			}
			return h;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#compareParms(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
		 */
		protected boolean compareParms(String[] parms) {
			if (parms.length != parmTypes.length)
				return false;
			for (int i = 0; i < parms.length; i++) {
				if (!parmTypes[i].getTypeName().equals(parms[i]))
					return false;
			}
			return true;
		}
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.remote.REMProxyConstants.MethodKey#compareParms(java.lang.String[])
		 */
		protected boolean compareParms(IBeanTypeProxy[] parms) {
			return Arrays.equals(parms, parmTypes);
		}		
	}
		
	static int REMMETHODCOUNT = 0;
	static int UNIQUEMETHODCOUNT = 0;	
	static int REMINVOKABLECOUNT = 0;
	static int UNIQUEINVOKABLECOUNT = 0;
	static int INVOKEINVOKECOUNT = 0;
	static int METHODPROXYINVOKECOUNT = 0;
	static int REMFIELDCOUNT = 0;
	static int UNIQUEFIELDCOUNT = 0;
	static int REMCONSTRUCTORCALLED = 0;
	static HashMap METHODCOUNTMAP;
	static HashMap FIELDCOUNTMAP;	 
	static HashMap FIELDSETCOUNTMAP;
	static boolean GATHER_COUNTS;
	
	/**
	 * Set if counts should be gathered.
	 * 
	 * @param gatherCounts
	 * 
	 * @since 1.1.0
	 */
	public static void setGatherCounts(boolean gatherCounts) {
		if (gatherCounts != GATHER_COUNTS) {
			reset();
			if (gatherCounts) {
				if (METHODCOUNTMAP == null) {
					METHODCOUNTMAP = new HashMap();
					FIELDCOUNTMAP = new HashMap();
					FIELDSETCOUNTMAP = new HashMap();
				}
			}
			GATHER_COUNTS = gatherCounts;
		}
	}
	
	
	public static void reset(){
		REMMETHODCOUNT = UNIQUEMETHODCOUNT = REMINVOKABLECOUNT = UNIQUEINVOKABLECOUNT = REMCONSTRUCTORCALLED = METHODPROXYINVOKECOUNT = INVOKEINVOKECOUNT = REMFIELDCOUNT = UNIQUEFIELDCOUNT = 0;
		if (GATHER_COUNTS) {
			METHODCOUNTMAP.clear();
			FIELDCOUNTMAP.clear();
			FIELDSETCOUNTMAP.clear();
		}
	}
	
	public static void println(){
		
		if (GATHER_COUNTS) {
			System.out.println("--------------------------------------------------");
			System.out.println("Method proxies invokes = " + METHODPROXYINVOKECOUNT);
			System.out.println("Invoke invokes = " + INVOKEINVOKECOUNT);
			System.out.println("..................................................");
			System.out.println("Methods retrieved = " + REMMETHODCOUNT + "(" + UNIQUEMETHODCOUNT + ")");
			System.out.println("Invokes retrieved = " + REMINVOKABLECOUNT + "(" + UNIQUEINVOKABLECOUNT + ")");
			System.out.println("Fields retrieved = " + REMFIELDCOUNT + "(" + UNIQUEFIELDCOUNT + ")");
			System.out.println("Constructor calls = " + REMCONSTRUCTORCALLED);
			System.out.println("--------------------------------------------------");
			System.out.println("-Count of methods invoked-------------------------");
			System.out.println("--------------------------------------------------");

			// Collate the methods called
			Iterator entries = METHODCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMMethodProxy methodProxy = (REMMethodProxy) entry.getKey();
				System.out.println(methodProxy.getClassType().getTypeName() + "," + methodProxy.getName() + "," + entry.getValue());
			}

			System.out.println("--------------------------------------------------");
			System.out.println("-Count of fields get called ----------------------");
			System.out.println("--------------------------------------------------");

			// Collate the fields accessed
			entries = FIELDCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMFieldProxy fieldProxy = (REMFieldProxy) entry.getKey();
				System.out.println(fieldProxy.toBeanString() + "," + entry.getValue());
			}

			System.out.println("--------------------------------------------------");
			System.out.println("-Count of fields set called ----------------------");
			System.out.println("--------------------------------------------------");

			// Collate the fields set
			entries = FIELDSETCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMFieldProxy fieldProxy = (REMFieldProxy) entry.getKey();
				System.out.println(fieldProxy.toBeanString() + "," + entry.getValue());
			} 
		}
		
	}
	
/**
 * @param aBeanTypeProxy = BeanTypeProxy for the method
 * @param methodName = methodName to be looked for
 * @param parmTypes = array of qualified type names for the method arguments, null if no methods
 */ 
	public IMethodProxy getMethodProxy(IBeanTypeProxy aBeanTypeProxy, String methodName, String[] parmTypes){

		REMMETHODCOUNT++;		
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map methods = getMethods(aBeanTypeProxy);
		
		// The syntax of the key is methodName(parmType1,parmType2)
		Object key = null; 
		if(parmTypes == null || parmTypes.length == 0){
			key = methodName;
		} else {
			key = new MethodKeyStringParms(methodName, parmTypes);
		}
		
		// Lookup the cache'd method proxy
		IMethodProxy result = (IMethodProxy) methods.get(key);
		// Do we have it, and is still valid (in case someone did a release on it).
		if(result != null && result.isValid()) return result;
		
		UNIQUEMETHODCOUNT++;
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) aBeanTypeProxy.getProxyFactoryRegistry().getMethodProxyFactory();
		result = proxyFactory.getMethodProxy((IREMBeanTypeProxy)aBeanTypeProxy,methodName,parmTypes);
		methods.put(key,result);
		return result;
		
	}
/**
 * @param aBeanTypeProxy
 * @return Map of cache'd methods
 */
private Map getMethods(IBeanTypeProxy aBeanTypeProxy) {
	Map methods = (Map) methodsCache.get(aBeanTypeProxy);
	if(methods == null){
		methods = new HashMap(20);
		methodsCache.put(aBeanTypeProxy,methods);
	}
	return methods;
}
/**
 * @param aBeanTypeProxy
 * @return Map of cache'd invokables
 */
private Map getInvokables(IBeanTypeProxy aBeanTypeProxy) {
	Map invokables = (Map) invokablesCache.get(aBeanTypeProxy);
	if(invokables == null){
		invokables = new HashMap(20);
		invokablesCache.put(aBeanTypeProxy,invokables);
	}
	return invokables;
}
/**
 * @param aBeanTypeProxy
 * @return Map of cache'd fields
 */
private Map getFields(IBeanTypeProxy aBeanTypeProxy) {
	Map fields = (Map) fieldsCache.get(aBeanTypeProxy);
	if(fields == null){
		fields = new HashMap(20);
		fieldsCache.put(aBeanTypeProxy,fields);
	}
	return fields;
}
/**
 * @param aBeanTypeProxy = BeanTypeProxy for the method
 * @param methodName = methodName to be looked for
 * @param parmTypes = array of qualified type names for the method arguments, null if no arguments
 */ 
	public IInvokable getInvokable(IBeanTypeProxy aBeanTypeProxy, String invokableName, String[] parmTypeNames){
		
		REMINVOKABLECOUNT++;
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map invokables = getInvokables(aBeanTypeProxy);	
		
		Object key = null; 
		if(parmTypeNames == null || parmTypeNames.length == 0){
			key = invokableName;
		} else {
			key = new MethodKeyStringParms(invokableName, parmTypeNames);
		}			
		
		IInvokable result = (IInvokable) invokables.get(key);
		if(result != null) return result;
		
		UNIQUEINVOKABLECOUNT++;
		// Get the method proxy and cache this before returning it
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) aBeanTypeProxy.getProxyFactoryRegistry().getMethodProxyFactory();
		result = proxyFactory.getInvokable((IREMBeanTypeProxy)aBeanTypeProxy,invokableName,parmTypeNames);
		invokables.put(key,result);		
		return result;				
				
	}
	/**
	 * @param aBeanTypeProxy = BeanTypeProxy for the method
	 * @param methodName = methodName to be looked for
	 * @param parmTypes = array of IBeanTypeProxy types for the method arguments, null if no arguments
	 */ 
		public IInvokable getInvokable(IBeanTypeProxy aBeanTypeProxy, String invokableName, IBeanTypeProxy[] parmTypes){
			
			REMINVOKABLECOUNT++;
			// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
			Map invokables = getInvokables(aBeanTypeProxy);	
			
			Object key = null; 
			if(parmTypes == null || parmTypes.length == 0){
				key = invokableName;
			} else {
				key = new MethodKeyProxyParms(invokableName, parmTypes);
			}			
			
			IInvokable result = (IInvokable) invokables.get(key);
			if(result != null) return result;
			
			UNIQUEINVOKABLECOUNT++;
			// Get the method proxy and cache this before returning it
			// Get the method proxy and cache this before returning it
			REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) aBeanTypeProxy.getProxyFactoryRegistry().getMethodProxyFactory();
			result = proxyFactory.getInvokable((IREMBeanTypeProxy)aBeanTypeProxy,invokableName,parmTypes);
			invokables.put(key,result);		
			return result;				
					
		}	
/**
 * @param aBeanTypeProxy = BeanTypeProxy for the method
 * @param methodName = methodName to be looked for
 * @param parmTypes = array of qualified type names for the method arguments, null if no methods
 */ 
	public IMethodProxy getMethodProxy(IBeanTypeProxy aBeanTypeProxy, String methodName, IBeanTypeProxy[] parmTypes){
		
		REMMETHODCOUNT++;		
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map methods = getMethods(aBeanTypeProxy);	
				
		Object key = null; 
		if(parmTypes == null || parmTypes.length == 0){
			key = methodName;
		} else {
			key = new MethodKeyProxyParms(methodName, parmTypes);
		}	
		
		// Lookup the cache'd method proxy
		IMethodProxy result = (IMethodProxy) methods.get(key);
		if(result != null) return result;
		
		UNIQUEMETHODCOUNT++;
		// Get the method proxy and cache this before returning it
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) aBeanTypeProxy.getProxyFactoryRegistry().getMethodProxyFactory();
		result = proxyFactory.getMethodProxy((IREMBeanTypeProxy)aBeanTypeProxy,methodName,parmTypes);
		methods.put(key,result);		
		return result;				
	}


	/**
	 * @param proxy
	 */
	static void methodInvoked(REMMethodProxy proxy) {
	
		if (GATHER_COUNTS) {
			Integer count = (Integer) METHODCOUNTMAP.get(proxy);
			if (count == null) {
				METHODCOUNTMAP.put(proxy, new Integer(1));
			} else {
				METHODCOUNTMAP.put(proxy, new Integer(count.intValue() + 1));
			}
		}
	}

	static void fieldGetInvoked(IBeanProxy proxy) {

		if (GATHER_COUNTS) {
			Integer count = (Integer) FIELDCOUNTMAP.get(proxy);
			if (count == null) {
				FIELDCOUNTMAP.put(proxy, new Integer(1));
			} else {
				FIELDCOUNTMAP.put(proxy, new Integer(count.intValue() + 1));
			} 
		}		
	}

	static void fieldSetInvoked(IBeanProxy proxy, IBeanProxy value) {

		if (GATHER_COUNTS) {
			Integer count = (Integer) FIELDSETCOUNTMAP.get(proxy);
			if (count == null) {
				FIELDSETCOUNTMAP.put(proxy, new Integer(1));
			} else {
				FIELDSETCOUNTMAP.put(proxy, new Integer(count.intValue() + 1));
			} 
		}				
	}

	/**
	 * @param proxy for the BeanType of the field
	 * @param fieldName of the field, e.g. (java.awt.Dimension, width) for the "width" field on Dimension
	 * @return The field proxy that is cache'd for performance
	 */
	public IFieldProxy getFieldProxy(REMAbstractBeanTypeProxy aBeanTypeProxy, String fieldName) {

		REMFIELDCOUNT++;		
		// The field map is keyed by the BeanTypeProxy and holds a further map of cache'd fields
		Map fields = getFields(aBeanTypeProxy);	
				
		// Lookup the cache'd Field proxy
		IFieldProxy result = (IFieldProxy) fields.get(fieldName);
		if(result != null && result.isValid()) return result;
		
		UNIQUEFIELDCOUNT++;
		
		result = (IFieldProxy) REMStandardBeanProxyConstants.getConstants(aBeanTypeProxy.getProxyFactoryRegistry()).getClassGetField().invokeCatchThrowableExceptions(
				aBeanTypeProxy,
				aBeanTypeProxy.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(fieldName));		
		fields.put(fieldName,result);		
		return result;				
	}			
}
