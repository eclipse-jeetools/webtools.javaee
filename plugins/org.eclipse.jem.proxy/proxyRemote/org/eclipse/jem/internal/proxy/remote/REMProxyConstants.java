package org.eclipse.jem.internal.proxy.remote;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IFieldProxy;
import org.eclipse.jem.internal.proxy.core.IInvokable;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;

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
 *  $RCSfile: REMProxyConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/08 11:41:25 $ 
 */


/**
 * MethodProxyConstants is a cache of IMethodProxies to avoid repeated lookup
 * and thereby avoid the relatively expensive java.lang.reflect calls to repeatedly
 * lookup method by name
 */
public class REMProxyConstants {

	public static Class REGISTRY_KEY = REMProxyConstants.class;
	private Map classCache = new HashMap(80);
	private Map invokablesCache = new HashMap(80);	
	private Map fieldsCache = new HashMap(80);
	
	public static REMProxyConstants getConstants(ProxyFactoryRegistry aRegistry){
		REMProxyConstants constants = (REMProxyConstants) aRegistry.getConstants(REGISTRY_KEY);
		if(constants != null){
			return constants;
		} else {
			constants = new REMProxyConstants(); 
			aRegistry.registerConstants(REMProxyConstants.REGISTRY_KEY,constants);
		}
		return constants;
	}
	
	static int remMethodCount = 0;
	static int uniqueMethodCount = 0;	
	static int remInvokableCount = 0;
	static int uniqueInvokableCount = 0;
	static int invokeInvokeCount = 0;
	static int methodProxyInvokeCount = 0;
	static int remFieldCount = 0;
	static int uniqueFieldCount = 0;
	static int remConstructorCalled = 0;
	static HashMap methodCountMap = new HashMap();
	static HashMap fieldCountMap = new HashMap();	
	static HashMap fieldSetCountMap = new HashMap();	
	
	public static void reset(){
		remMethodCount = uniqueMethodCount = remInvokableCount = uniqueInvokableCount = remConstructorCalled =
			methodProxyInvokeCount = invokeInvokeCount = remFieldCount = uniqueFieldCount = 0;
		methodCountMap = new HashMap();
		fieldCountMap = new HashMap();
		fieldSetCountMap = new HashMap();
	}
	
	public static void println(){
		
		System.out.println("--------------------------------------------------");		
		System.out.println("Method proxies invokes = " + methodProxyInvokeCount);
		System.out.println("Invoke invokes = " + invokeInvokeCount);
		System.out.println("..................................................");		
		System.out.println("Methods retrieved = " + remMethodCount + "(" + uniqueMethodCount + ")");
		System.out.println("Invokes retrieved = " + remInvokableCount + "(" + uniqueInvokableCount + ")");
		System.out.println("Fields retrieved = " + remFieldCount + "(" + uniqueFieldCount + ")");
		System.out.println("Constructor calls = " + remConstructorCalled);
		System.out.println("--------------------------------------------------");		
		System.out.println("-Count of methods invoked-------------------------");
		System.out.println("--------------------------------------------------");		
		
		HashSet set = new HashSet();
		Iterator keys = methodCountMap.keySet().iterator();
		while(keys.hasNext()){
			set.add(keys.next());
		}
	
		// Collate the methods called
		keys = set.iterator();
		while(keys.hasNext()){
			REMMethodProxy methodProxy = (REMMethodProxy)keys.next();
			System.out.println(methodProxy.getClassType().getTypeName() + "," + methodProxy.getName() + "," + methodCountMap.get(methodProxy));
		}
		
		System.out.println("--------------------------------------------------");		
		System.out.println("-Count of fields get called ----------------------");
		System.out.println("--------------------------------------------------");		
		
		set = new HashSet();
		keys = fieldCountMap.keySet().iterator();
		while(keys.hasNext()){
			set.add(keys.next());
		}
		
		// Collate the fields accessed
		keys = set.iterator();
		while(keys.hasNext()){
			REMFieldProxy fieldProxy = (REMFieldProxy)keys.next();
			System.out.println(fieldProxy.toBeanString() + "," + fieldCountMap.get(fieldProxy));
		}		
		
		System.out.println("--------------------------------------------------");		
		System.out.println("-Count of fields set called ----------------------");
		System.out.println("--------------------------------------------------");		
		
		set = new HashSet();
		keys = fieldSetCountMap.keySet().iterator();
		while(keys.hasNext()){
			set.add(keys.next());
		}
		
		// Collate the fields set
		keys = set.iterator();
		while(keys.hasNext()){
			REMFieldProxy fieldProxy = (REMFieldProxy)keys.next();
			System.out.println(fieldProxy.toBeanString() + "," + fieldSetCountMap.get(fieldProxy));
		}				
		
	}
	
/**
 * @param aBeanTypeProxy = BeanTypeProxy for the method
 * @param methodName = methodName to be looked for
 * @param parmTypes = array of qualified type names for the method arguments, null if no methods
 */ 
	public IMethodProxy getMethodProxy(IBeanTypeProxy aBeanTypeProxy, String methodName, String[] parmTypes){

		remMethodCount++;		
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map methods = getMethods(aBeanTypeProxy);
		
		// The syntax of the key is methodName(parmType1,parmType2)
		String key = null; 
		if(parmTypes == null){
			key = methodName;
		} else {
			key = getKey(methodName, parmTypes);
		}
		
		// Lookup the cache'd method proxy
		IMethodProxy result = (IMethodProxy) methods.get(key);
		if(result != null) return result;
		
		uniqueMethodCount++;
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
	Map methods = (Map) classCache.get(aBeanTypeProxy);
	if(methods == null){
		methods = new HashMap(20);
		classCache.put(aBeanTypeProxy,methods);
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
		
		remInvokableCount++;
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map invokables = getInvokables(aBeanTypeProxy);	
		
		String key = null; 
		if(parmTypeNames == null){
			key = invokableName;
		} else {
			key = getKey(invokableName, parmTypeNames);
		}			
		
		IInvokable result = (IInvokable) invokables.get(key);
		if(result != null) return result;
		
		uniqueInvokableCount++;
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
			
			remInvokableCount++;
			// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
			Map invokables = getInvokables(aBeanTypeProxy);	
			
			String key = null; 
			if(parmTypes == null){
				key = invokableName;
			} else {
				key = getKey(invokableName, getParmTypeNames(parmTypes));
			}			
			
			IInvokable result = (IInvokable) invokables.get(key);
			if(result != null) return result;
			
			uniqueInvokableCount++;
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
		
		remMethodCount++;		
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map methods = getMethods(aBeanTypeProxy);	
				
		String key = null; 
		if(parmTypes == null){
			key = methodName;
		} else {
			key = getKey(methodName, getParmTypeNames(parmTypes));
		}	
		
		// Lookup the cache'd method proxy
		IMethodProxy result = (IMethodProxy) methods.get(key);
		if(result != null) return result;
		
		uniqueMethodCount++;
		// Get the method proxy and cache this before returning it
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) aBeanTypeProxy.getProxyFactoryRegistry().getMethodProxyFactory();
		result = proxyFactory.getMethodProxy((IREMBeanTypeProxy)aBeanTypeProxy,methodName,parmTypes);
		methods.put(key,result);		
		return result;				
	}

 /**
  * @return array of Strings for fully qualified type names
  * @param parmTypes Array of bean proxy types
  */
private String[] getParmTypeNames(IBeanTypeProxy[] parmTypes) {
	//	The syntax of the key is methodName(parmType1,parmType2)
	String[] parmTypeNames = new String[parmTypes.length];
	for (int i = 0; i < parmTypes.length; i++) {
		parmTypeNames[i] = parmTypes[i].getFormalTypeName();
	}
	return parmTypeNames;
}
	/**
	 * @param methodName
	 * @param parmTypes
	 * @return
	 */
	private static String getKey(String methodName, String[] parmTypes) {
		String key;
		StringBuffer keyBuffer = new StringBuffer();
		keyBuffer.append(methodName);
		keyBuffer.append('(');
		for (int i = 0; i < parmTypes.length; i++) {
			keyBuffer.append(parmTypes[i]);
			if(i < parmTypes.length - 1) keyBuffer.append(',');
		}
		keyBuffer.append(')');
		key = keyBuffer.toString();
		return key;
	}

	/**
	 * @param proxy
	 */
	static void methodInvoked(REMMethodProxy proxy) {
	
		Integer count = (Integer)methodCountMap.get(proxy);
		if(count == null){
			methodCountMap.put(proxy,new Integer(1));
		} else {
			methodCountMap.put(proxy, new Integer(count.intValue() + 1));
		}
	}

	static void fieldGetInvoked(IBeanProxy proxy) {

		Integer count = (Integer)fieldCountMap.get(proxy);
		if(count == null){
			fieldCountMap.put(proxy,new Integer(1));
		} else {
			fieldCountMap.put(proxy, new Integer(count.intValue() + 1));
		}		
	}

	static void fieldSetInvoked(IBeanProxy proxy, IBeanProxy value) {

		Integer count = (Integer)fieldSetCountMap.get(proxy);
		if(count == null){
			fieldSetCountMap.put(proxy,new Integer(1));
		} else {
			fieldSetCountMap.put(proxy, new Integer(count.intValue() + 1));
		}				
	}

	/**
	 * @param proxy for the BeanType of the field
	 * @param fieldName of the field, e.g. (java.awt.Dimension, width) for the "width" field on Dimension
	 * @return The field proxy that is cache'd for performance
	 */
	public IFieldProxy getFieldProxy(REMAbstractBeanTypeProxy aBeanTypeProxy, String fieldName) {

		remFieldCount++;		
		// The field map is keyed by the BeanTypeProxy and holds a further map of cache'd fields
		Map fields = getFields(aBeanTypeProxy);	
				
		// Lookup the cache'd Field proxy
		IFieldProxy result = (IFieldProxy) fields.get(fieldName);
		if(result != null) return result;
		
		uniqueFieldCount++;
		
		result = (IFieldProxy) REMStandardBeanProxyConstants.getConstants(aBeanTypeProxy.getProxyFactoryRegistry()).getClassGetField().invokeCatchThrowableExceptions(
				aBeanTypeProxy,
				aBeanTypeProxy.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(fieldName));		
		fields.put(fieldName,result);		
		return result;				
	}			
}
