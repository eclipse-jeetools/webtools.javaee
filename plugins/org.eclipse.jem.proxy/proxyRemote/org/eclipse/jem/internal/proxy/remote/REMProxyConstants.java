/*******************************************************************************
 * Copyright (c)  2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMProxyConstants.java,v $
 *  $Revision: 1.6 $  $Date: 2005/05/18 23:11:26 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.util.*;
import java.util.Map.Entry;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;




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
	
	private REMProxyFactoryRegistry registry;
	
	public REMProxyConstants(REMProxyFactoryRegistry registry) {
		this.registry = registry;
	}
	
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
				
		protected abstract boolean compareParms(IProxyBeanType[] parms);
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
		protected boolean compareParms(IProxyBeanType[] parms) {
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
		public IProxyBeanType[] parmTypes;
		
		public MethodKeyProxyParms(String methodName, IProxyBeanType[] parmTypes) {
			super(methodName);
			this.parmTypes = parmTypes;
		}
		
		public Object toMethodKeyStringParms() {
			String[] parms = new String[parmTypes.length];
			for (int i = 0; i < parmTypes.length; i++) {
				parms[i] = parmTypes[i].getTypeName();
			}
			return new MethodKeyStringParms(methodName, parms);
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
		protected boolean compareParms(IProxyBeanType[] parms) {
			if (parms.length != parmTypes.length)
				return false;
			for (int i = 0; i < parms.length; i++) {
				if (!parmTypes[i].getTypeName().equals(parms[i].getTypeName()))
					return false;
			}
			return true;
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
			System.out.println("--------------------------------------------------"); //$NON-NLS-1$
			System.out.println("Method proxies invokes = " + METHODPROXYINVOKECOUNT); //$NON-NLS-1$
			System.out.println("Invoke invokes = " + INVOKEINVOKECOUNT); //$NON-NLS-1$
			System.out.println(".................................................."); //$NON-NLS-1$
			System.out.println("Methods retrieved = " + REMMETHODCOUNT + "(" + UNIQUEMETHODCOUNT + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println("Invokes retrieved = " + REMINVOKABLECOUNT + "(" + UNIQUEINVOKABLECOUNT + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println("Fields retrieved = " + REMFIELDCOUNT + "(" + UNIQUEFIELDCOUNT + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println("Constructor calls = " + REMCONSTRUCTORCALLED); //$NON-NLS-1$
			System.out.println("--------------------------------------------------"); //$NON-NLS-1$
			System.out.println("-Count of methods invoked-------------------------"); //$NON-NLS-1$
			System.out.println("--------------------------------------------------"); //$NON-NLS-1$

			// Collate the methods called
			Iterator entries = METHODCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMMethodProxy methodProxy = (REMMethodProxy) entry.getKey();
				System.out.println(methodProxy.getClassType().getTypeName() + "," + methodProxy.getName() + "," + entry.getValue()); //$NON-NLS-1$ //$NON-NLS-2$
			}

			System.out.println("--------------------------------------------------"); //$NON-NLS-1$
			System.out.println("-Count of fields get called ----------------------"); //$NON-NLS-1$
			System.out.println("--------------------------------------------------"); //$NON-NLS-1$

			// Collate the fields accessed
			entries = FIELDCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMFieldProxy fieldProxy = (REMFieldProxy) entry.getKey();
				System.out.println(fieldProxy.toBeanString() + "," + entry.getValue()); //$NON-NLS-1$
			}

			System.out.println("--------------------------------------------------"); //$NON-NLS-1$
			System.out.println("-Count of fields set called ----------------------"); //$NON-NLS-1$
			System.out.println("--------------------------------------------------"); //$NON-NLS-1$

			// Collate the fields set
			entries = FIELDSETCOUNTMAP.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Entry) entries.next();
				REMFieldProxy fieldProxy = (REMFieldProxy) entry.getKey();
				System.out.println(fieldProxy.toBeanString() + "," + entry.getValue()); //$NON-NLS-1$
			} 
		}
		
	}
	
/**
 * @param aBeanTypeProxy = BeanTypeProxy for the method
 * @param methodName = methodName to be looked for
 * @param parmTypes = array of qualified type names for the method arguments, null if no methods
 */ 
	public IMethodProxy getMethodProxy(IBeanTypeProxy aBeanTypeProxy, String methodName, String[] parmTypes){
		if (!registry.isValid())
			return null;

		REMMETHODCOUNT++;
		Map methods;
		Object key;
		synchronized (this) {
			// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
			methods = getMethods(aBeanTypeProxy);

			// The syntax of the key is methodName(parmType1,parmType2)
			if (parmTypes == null || parmTypes.length == 0) {
				key = methodName;
			} else {
				key = new MethodKeyStringParms(methodName, parmTypes);
			}

			IMethodProxy result = (IMethodProxy) methods.get(key);
			if (result != null)
				return result;
		}
		
		UNIQUEMETHODCOUNT++;
		// Get the method proxy and cache this before returning it
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) registry.getMethodProxyFactory();
		IMethodProxy result = proxyFactory.getMethodProxy((IREMBeanTypeProxy)aBeanTypeProxy,methodName,parmTypes);
		synchronized (this) {
			// Get it again to make sure it hasn't changed due to a race condition. We don't sync for the getMethodProxy because that goes to the remote vm and could deadlock.
			IMethodProxy mValue = (IMethodProxy) methods.get(key);
			if (mValue != null && mValue != result) {
				registry.releaseProxy(result); // Don't need the result now, got it through a race condition.
				return mValue; // We have a real value now.
			}
			methods.put(key, result);
		}		
		return result;				
	}
/**
 * @param aBeanTypeProxy
 * @return Map of cache'd methods
 */
private Map getMethods(IProxyBeanType aBeanTypeProxy) {
	Map methods = (Map) methodsCache.get(aBeanTypeProxy.getTypeName());
	if(methods == null){
		methods = new HashMap(20);
		methodsCache.put(aBeanTypeProxy.getTypeName(),methods);
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
private Map getFields(IProxyBeanType aBeanTypeProxy) {
	Map fields = (Map) fieldsCache.get(aBeanTypeProxy.getTypeName());
	if(fields == null){
		fields = new HashMap(20);
		fieldsCache.put(aBeanTypeProxy.getTypeName(),fields);
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
		if (!registry.isValid())
			return null;
		
		REMMETHODCOUNT++;		
		// The classCache map is keyed by the BeanTypeProxy and holds a further map of cache'd methods
		Map methods;
		Object key;
		synchronized (this) {
			methods = getMethods(aBeanTypeProxy);

			key = null;
			if (parmTypes == null || parmTypes.length == 0) {
				key = methodName;
			} else {
				key = new MethodKeyProxyParms(methodName, parmTypes);
			}

			IMethodProxy result = (IMethodProxy) methods.get(key);
			if (result != null)
				return result;
		}
		
		UNIQUEMETHODCOUNT++;
		// Get the method proxy and cache this before returning it
		// Get the method proxy and cache this before returning it
		REMMethodProxyFactory proxyFactory = (REMMethodProxyFactory) registry.getMethodProxyFactory();
		IMethodProxy result = proxyFactory.getMethodProxy((IREMBeanTypeProxy)aBeanTypeProxy,methodName,parmTypes);
		synchronized (this) {
			// Get it again to make sure it hasn't changed due to a race condition. We don't sync for the getMethodProxy because that goes to the remote vm and could deadlock.
			IMethodProxy mValue = (IMethodProxy) methods.get(key);
			if (mValue != null && mValue != result) {
				registry.releaseProxy(result); // Don't need result now, got it already through a race condition.
				return mValue; // We have a real value now.
			}
			methods.put(key, result);
		}		

		return result;				
	}

	/**
	 * Return the proxy method for the method through the expression. 
	 * @param expression
	 * @param aBeanTypeProxy
	 * @param methodName
	 * @param parmTypes
	 * @return either the IMethodProxy if already resolved or an ExpressionProxy if not yet resolved.
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, IProxyBeanType aBeanTypeProxy, String methodName, IProxyBeanType[] parmTypes){
		if (!registry.isValid())
			return null;
		
		REMMETHODCOUNT++;	
		Map methods;
		Map epMethods;
		Object key;
		boolean isKey;
		synchronized (this) {
			// The classCache map is keyed by the BeanTypeProxy name and holds a further map of cache'd methods
			methods = getMethods(aBeanTypeProxy);

			if (parmTypes == null || parmTypes.length == 0) {
				key = methodName;
				isKey = false;
			} else {
				key = new MethodKeyProxyParms(methodName, parmTypes);
				isKey = true;
			}

			IProxyMethod result = (IProxyMethod) methods.get(key);
			if (result != null)
				return result;
			
			// See if stored in the expression.
			epMethods = ((REMExpression) expression).getMethods(aBeanTypeProxy);
			result = (IProxyMethod) epMethods.get(key);
			if (result != null)
				return result;
		}
		
		UNIQUEMETHODCOUNT++;
		// Get the method expression proxy and cache this before returning it
		IProxyMethod result = ((Expression) expression).createMethodExpressionProxy(aBeanTypeProxy,methodName,parmTypes);
		epMethods.put(key, result);
		final Object epKey = key;
		final Map rMethods = methods; 
		final Map fepMethods = epMethods;
		final boolean isKeyType = isKey;
		((ExpressionProxy) result).addProxyListener(new ExpressionProxy.ProxyAdapter() {
			public void proxyResolved(ProxyEvent event) {
				synchronized (REMProxyConstants.this) {
					if (rMethods.containsKey(epKey))
						return;	// We already have a true method proxy in there. A race condition occurred.
					
					// Now put this resolved guy into the methods.
					// We don't want the key to contain expression proxies in the final map, so if it is a key type
					// we will turn it into a string type key.
					Object key;
					if (isKeyType) {
						key = ((MethodKeyProxyParms) epKey).toMethodKeyStringParms();	// So that we don't put a ket that contains expression proxy parm types into the main map.
					} else
						key = epKey;
					
					rMethods.put(key, event.getProxy());
				}
			}
			
			public void proxyNotResolved(ExpressionProxy.ProxyEvent event) {
				synchronized (REMProxyConstants.this) {
					fepMethods.remove(epKey);
				}
				}
			
		});
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
		if (!registry.isValid())
			return null;
		
		REMFIELDCOUNT++;
		Map fields;
		synchronized (this) {
			// The field map is keyed by the BeanTypeProxy and holds a further map of cache'd fields
			fields = getFields(aBeanTypeProxy);	
				
			// Lookup the cache'd Field proxy
			IFieldProxy result = (IFieldProxy) fields.get(fieldName);
			if (result != null)
				return result;
		}
		
		UNIQUEFIELDCOUNT++;
		IFieldProxy result = (IFieldProxy) REMStandardBeanProxyConstants.getConstants(aBeanTypeProxy.getProxyFactoryRegistry()).getClassGetField().invokeCatchThrowableExceptions(
				aBeanTypeProxy,
				registry.getBeanProxyFactory().createBeanProxyWith(fieldName));	
		synchronized (this) {
			IFieldProxy fValue = (IFieldProxy) fields.get(fieldName);
			if (fValue != null) {
				registry.releaseProxy(result);	// Don't need it now. A race had put another one in.
				return fValue;
			}
			fields.put(fieldName,result);
		}
		return result;				
	}
	
	public IProxyField getFieldProxy(IExpression expression, IProxyBeanType aBeanTypeProxy, final String fieldName){
		if (!registry.isValid())
			return null;
		
		REMFIELDCOUNT++;
		Map fields;
		Map epFields;
		synchronized (this) {
			// The classCache map is keyed by the BeanTypeProxy name and holds a further map of cache'd methods
			fields = getFields(aBeanTypeProxy);	
		
			IProxyField result = (IProxyField) fields.get(fieldName);
			if (result != null)
				return result;
			
			// See if stored in the expression.
			epFields = ((REMExpression) expression).getFields(aBeanTypeProxy);
			result = (IProxyField) epFields.get(fieldName);
			if (result != null)
				return result;			
		}
		
		UNIQUEFIELDCOUNT++;
		// Get the field expression proxy and cache this before returning it
		IProxyField result = ((REMExpression) expression).createFieldExpressionProxy(aBeanTypeProxy, fieldName);
		epFields.put(fieldName, result);
		final Map fpFields = fields;
		final Map fepFields = epFields;
		((ExpressionProxy) result).addProxyListener(new ExpressionProxy.ProxyAdapter() {

			public void proxyResolved(ProxyEvent event) {
				synchronized (REMProxyConstants.this) {
					if (fpFields.containsKey(fieldName))
						return;	// Already set to resolved value by someone else.
					fpFields.put(fieldName, event.getProxy());
				}
			}
			 public void proxyNotResolved(ExpressionProxy.ProxyEvent event) {
				 synchronized (REMProxyConstants.this) {
					 fepFields.remove(fieldName);
				}
			 }
		});
		
		return result;				
	}
}
