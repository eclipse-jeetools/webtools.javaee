package org.eclipse.jem.internal.beaninfo.adapters;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: Utilities.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:17:00 $ 
 */
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.jem.internal.beaninfo.*;
import org.eclipse.jem.java.*;
import org.eclipse.jem.java.JavaURL;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.internal.java.adapters.ReflectionAdaptor;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * Beaninfo (introspection) utilities methods
 */

public final class Utilities {
	
	private static final java.lang.reflect.Method sSignatureToString;
	static {
		// If in the workbench, we want to use the Signature.toString(type) method
		// to convert array arguments. However, we may not be in workbench, so we
		// need to use reflection.
		java.lang.reflect.Method ssig = null;
		try {
			Class signature = Class.forName("org.eclipse.jdt.core.Signature"); //$NON-NLS-1$
			ssig = signature.getMethod("toString", new Class[] { String.class }); //$NON-NLS-1$
		} catch (ClassNotFoundException e) {
		} catch (NoSuchMethodException e) {
		}

		sSignatureToString = ssig;
	}

	private Utilities() {
	}

	/**
	 * Utility to return the EClassifier from the given name. (EClassifier because
	 * it could return either a JavaDataType or a JavaClass).
	 */
	public static JavaHelpers getJavaClass(String className, ResourceSet rset) {
		return (JavaHelpers) rset.getEObject(URI.createURI((new JavaURL(className)).getFullString()), true);
	}

	/** 
	 * Utility to return the EClassifier from the given IBeanTypeProxy.
	 */
	public static EClassifier getJavaClass(IBeanTypeProxy type, ResourceSet rset) {
		if (type != null) {
			String typeName = null;
			if (!type.isArray())
				typeName = type.getTypeName();
			else {
				typeName = ((IArrayBeanTypeProxy) type).getFinalComponentType().getTypeName();
				int dim = ((IArrayBeanTypeProxy) type).getDimensions();
				while (dim-- > 0)
					typeName = typeName.concat("[]"); //$NON-NLS-1$
			}
			return getJavaClass(typeName, rset);
		} else
			return null;
	}

	/**
	 * Utility to return the Method from the given IMethodProxy.
	 */
	public static Method getMethod(IMethodProxy method, ResourceSet rset) {
		return method != null ? (Method) rset.getEObject(URI.createURI(getMethodURL(method)), true) : null;
	}

	/**
	 * Answer the URL String for Method from the given IMethodProxy
	 */
	public static String getMethodURL(IMethodProxy method) {
		String className = method.getClassType().getTypeName();
		int classStart = className.lastIndexOf('.');
		StringBuffer url = new StringBuffer(50);
		url.append("java:/"); //$NON-NLS-1$
		if (classStart > -1)
			url.append(className.substring(0, classStart));
		url.append('#');
		IBeanTypeProxy[] parms = method.getParameterTypes();
		String[] parmTypes = new String[parms.length];
		for (int i = 0; i < parmTypes.length; i++) {
			IBeanTypeProxy ptype = parms[i];
			if (!ptype.isArray() || sSignatureToString == null)
				parmTypes[i] = parms[i].getTypeName();
			else {
				// It is an array and we are in the workbench. Need to converty [I to int[]
				try {
					parmTypes[i] = (String) sSignatureToString.invoke(null, new Object[] { ptype.getTypeName()});
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				}
			}
		}
		url.append(computeMethodID(className.substring(classStart + 1), method.getName(), parmTypes));
		return url.toString();
	}

	/**
	 * Turn it into an URI.
	 */
	public static URI getMethodURI(IMethodProxy method) {
		return URI.createURI(getMethodURL(method));
	}

	/**
	 * Utility to compute the Java Modeling METHODID from method declaring class name,
	 * method name, and parameter class names.
	 */
	public static String computeMethodID(String className, String methodName, String[] parameterTypes) {
		StringBuffer out = new StringBuffer(50);
		out.append(className);
		out.append(ReflectionAdaptor.C_CLASS_MEMBER_DELIMITER);
		out.append(methodName);
		out.append(ReflectionAdaptor.C_METHOD_PARM_DELIMITER);
		for (int i = 0; i < parameterTypes.length; i++) {
			out.append(parameterTypes[i]);
			if (i < parameterTypes.length - 1)
				out.append(ReflectionAdaptor.C_PARM_PARM_DELIMITER);
		}
		if (className.equals(methodName))
			out.append(ReflectionAdaptor.S_CONSTRUCTOR_TOKEN); //It's a constructor
		return out.toString();
	}

	/**
	 * Utility to retrieve the BeanDecorator.
	 */
	public static BeanDecorator getBeanDecorator(EModelElement model) {
		return (BeanDecorator) getDecorator(model, BeanDecorator.class);
	}
	/**
	 * Utility to retrieve a decorator of the specified class.
	 */
	public static EAnnotation getDecorator(EModelElement model, Class decoratorClass) {
		Iterator itr = model.getEAnnotations().iterator();
		while (itr.hasNext()) {
			EAnnotation decr = (EAnnotation) itr.next();
			if (decoratorClass.isInstance(decr))
				return decr;
		}
		return null;
	}

	/**
	 * Utility to retrieve the EventSetDecorator.
	 */
	public static EventSetDecorator getEventSetDecorator(EModelElement model) {
		return (EventSetDecorator) getDecorator(model, EventSetDecorator.class);
	}

	/**
	 * Utility to retrieve the MethodDecorator.
	 */
	public static MethodDecorator getMethodDecorator(EModelElement model) {
		return (MethodDecorator) getDecorator(model, MethodDecorator.class);
	}

	/**
	 * Utility to retrieve the PropertyDecorator.
	 */
	public static PropertyDecorator getPropertyDecorator(EModelElement model) {
		return (PropertyDecorator) getDecorator(model, PropertyDecorator.class);
	}

	/**
	 * Utility to return an iterator on the list which will return the property decorators
	 * of only the properties in the EList passed in. If the property does not have a
	 * property decorator, then it is not a beaninfo property.
	 */
	public static Iterator getPropertiesIterator(final EList properties) {
		return new Iterator() {
			private Iterator itr = properties.iterator();
			private boolean hasNext = true;
			private PropertyDecorator next;
			{
				findNext();
			}
			public boolean hasNext() {
				return hasNext;
			}

			public Object next() {
				PropertyDecorator temp = next;
				findNext();
				return temp;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void findNext() {
				while (itr.hasNext()) {
					EModelElement nextOne = (EModelElement) itr.next();
					next = getPropertyDecorator(nextOne);
					if (next != null) {
						return;
					}
				}
				hasNext = false;
			}
		};
	}

	/**
	 * Utility to return an iterator on the list which will return the EventSet decorators
	 * of only the BeanEvents in the EList passed in.
	 */
	public static Iterator getEventSetsIterator(final EList events) {
		return new Iterator() {
			private Iterator itr = events.iterator();
			private boolean hasNext = true;
			private EventSetDecorator next;
			{
				findNext();
			}
			public boolean hasNext() {
				return hasNext;
			}

			public Object next() {
				EventSetDecorator temp = next;
				findNext();
				return temp;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void findNext() {
				while (itr.hasNext()) {
					EModelElement nextOne = (EModelElement) itr.next();
					next = getEventSetDecorator(nextOne);
					if (next != null) {
						return;
					}
				}
				hasNext = false;
			}
		};
	}

	/**
	 * Utility to return an iterator on the list which will return the Method decorators
	 * of only the Methods in the EList passed in.
	 */
	public static Iterator getMethodsIterator(final EList methods) {
		return new Iterator() {
			private Iterator itr = methods.iterator();
			private boolean hasNext = true;
			private MethodDecorator next;
			{
				findNext();
			}
			public boolean hasNext() {
				return hasNext;
			}

			public Object next() {
				MethodDecorator temp = next;
				findNext();
				return temp;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void findNext() {
				while (itr.hasNext()) {
					EModelElement nextOne = (EModelElement) itr.next();
					next = getMethodDecorator(nextOne);
					if (next != null) {
						return;
					}
				}
				hasNext = false;
			}
		};
	}

	/**
	 * Get the search path from the remote vm. 
	 * 
	 * Note: This shouldn't be used when working with a BeaninfoNature. Use the
	 * accessors taking the nature  instead. Otherwise there will be inconsistencies since the search path won't be
	 * saved across invocations of the workspace if it is not updated through the nature).
	 */
	public static IArrayBeanProxy getBeanInfoSearchPath(ProxyFactoryRegistry registry) {
		return (IArrayBeanProxy) BeaninfoProxyConstants
			.getConstants(registry)
			.getGetBeanInfoSearchPathProxy()
			.invokeCatchThrowableExceptions(
			null);
	}

	/**
	 * Set the search path to the array of strings passed in on the remote vm.
	 *
	 * Note: This shouldn't be used when working with a BeaninfoNature. Use the
	 * accessors taking the nature  instead. Otherwise there will be inconsistencies since the search path won't be
	 * saved across invocations of the workspace if it is not updated through the nature).	 
	 */
	public static void setBeanInfoSearchPath(ProxyFactoryRegistry registry, String[] paths) {

		try {
			BeaninfoProxyConstants biConstants = BeaninfoProxyConstants.getConstants(registry);
			JavaStandardBeanProxyConstants jConstants = JavaStandardBeanProxyConstants.getConstants(registry);
			IStandardBeanProxyFactory proxyFactory = registry.getBeanProxyFactory();
			IArrayBeanProxy newPath = proxyFactory.createBeanProxyWith(jConstants.getStringType(), paths != null ? paths.length : 0);
			if (paths != null)
				for (int i = 0; i < paths.length; i++)
					newPath.set(proxyFactory.createBeanProxyWith(paths[i]), i);
			biConstants.getSetBeanInfoSearchPathProxy().invoke(null, newPath);
		} catch (ThrowableProxy e) {
		}
	}

	/**
	 * From the Beaninfo Nature, insert a path to the beaninfo path at the given index, -1 means at the end.
	 * If index is larger than the current path, it will also add at the end.
	 */
	public static void insertBeanInfoSearchPath(BeaninfoNature nature, IBeaninfosDocEntry path, int index) throws CoreException {
		BeaninfosDoc infoPath = nature.getSearchPath();
		IBeaninfosDocEntry[] oldPath = infoPath.getSearchpath();

		IBeaninfosDocEntry[] newPath = new IBeaninfosDocEntry[oldPath.length + 1];

		if (index == -1 || index >= oldPath.length) {
			// At the end or past end
			System.arraycopy(oldPath, 0, newPath, 0, oldPath.length);
			newPath[oldPath.length] = path;
		} else {
			// In the middle
			System.arraycopy(oldPath, 0, newPath, 0, index);
			newPath[index] = path;
			System.arraycopy(oldPath, index, newPath, index + 1, oldPath.length - index);
		}

		infoPath.setSearchpath(newPath);
		nature.setSearchPath(infoPath);
	}

	/**
	 * Insert a path to the beaninfo path at the given index, -1 means at the end.
	 * If index is larger than the current path, it will also add at the end.
	 *
	 * Note: This shouldn't be used when working with a BeaninfoNature. Use the
	 * accessors taking the nature  instead. Otherwise there will be inconsistencies since the search path won't be
	 * saved across invocations of the workspace if it is not updated through the nature).	 
	 */
	public static void insertBeanInfoSearchPath(ProxyFactoryRegistry registry, String path, int index) {
		try {
			BeaninfoProxyConstants biConstants = BeaninfoProxyConstants.getConstants(registry);
			IArrayBeanProxy infoPath = (IArrayBeanProxy) biConstants.getGetBeanInfoSearchPathProxy().invoke(null);
			int pathLength = infoPath.getLength();

			IStandardBeanProxyFactory proxyFactory = registry.getBeanProxyFactory();

			IArrayBeanProxy newPath = proxyFactory.createBeanProxyWith(infoPath.getTypeProxy(), pathLength + 1);
			IBeanProxy stringProxy = proxyFactory.createBeanProxyWith(path);

			JavaStandardBeanProxyConstants constants = JavaStandardBeanProxyConstants.getConstants(registry);
			if (index == -1 || index >= pathLength) {
				// At the end or past end
				constants.arraycopy(infoPath, 0, newPath, 0, infoPath.getLength());
				newPath.set(stringProxy, pathLength);
			} else {
				// In the middle
				constants.arraycopy(infoPath, 0, newPath, 0, index);
				newPath.set(stringProxy, index);
				constants.arraycopy(infoPath, index, newPath, index + 1, pathLength - index);
			}

			biConstants.getSetBeanInfoSearchPathProxy().invoke(null, newPath);
		} catch (ThrowableProxy e) {
		}
	}

	/**
	 * From the Beaninfo Nature, remove the specified path from the beaninfo search path.
	 * Not an error if not found.
	 */
	public static void removeBeanInfoPath(BeaninfoNature nature, IBeaninfosDocEntry path) throws CoreException {
		BeaninfosDoc infoPath = nature.getSearchPath();
		IBeaninfosDocEntry[] oldPath = infoPath.getSearchpath();

		for (int i = 0; i < oldPath.length; i++) {
			if (path.equals(oldPath[i])) {
				// We found it, so remove it.
				IBeaninfosDocEntry[] newPath = new IBeaninfosDocEntry[oldPath.length - 1];
				System.arraycopy(oldPath, 0, newPath, 0, i);
				if (i < oldPath.length - 1)
					System.arraycopy(oldPath, i + 1, newPath, i, oldPath.length - i - 1);
				infoPath.setSearchpath(newPath);
				nature.setSearchPath(infoPath);
				return;
			}
		}
	}

	/**
	 * Remove the specified path from the beaninfo search path.
	 * Not an error if not found.
	 *
	 * Note: This shouldn't be used when working with a BeaninfoNature. Use the
	 * accessors taking the nature  instead. Otherwise there will be inconsistencies since the search path won't be
	 * saved across invocations of the workspace if it is not updated through the nature).	 	 
	 */
	public static void removeBeanInfoPath(ProxyFactoryRegistry registry, String path) {
		try {
			BeaninfoProxyConstants biConstants = BeaninfoProxyConstants.getConstants(registry);
			IArrayBeanProxy infoPath = (IArrayBeanProxy) biConstants.getGetBeanInfoSearchPathProxy().invoke(null);
			int pathLength = infoPath.getLength();

			for (int i = 0; i < pathLength; i++) {
				IStringBeanProxy aPath = (IStringBeanProxy) infoPath.get(i);
				if (path.equals(aPath.stringValue())) {
					// We found it, so remove it.
					IArrayBeanProxy newPath = registry.getBeanProxyFactory().createBeanProxyWith(infoPath.getTypeProxy(), pathLength - 1);
					JavaStandardBeanProxyConstants constants = JavaStandardBeanProxyConstants.getConstants(registry);
					constants.arraycopy(infoPath, 0, newPath, 0, i);
					if (i < pathLength - 1)
						constants.arraycopy(infoPath, i + 1, newPath, i, pathLength - i - 1);
					biConstants.getSetBeanInfoSearchPathProxy().invoke(null, newPath);
					return;
				}
			}
		} catch (ThrowableProxy e) {
		}
	};

}