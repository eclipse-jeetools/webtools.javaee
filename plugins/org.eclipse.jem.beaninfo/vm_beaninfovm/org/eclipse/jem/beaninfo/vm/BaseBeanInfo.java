package org.eclipse.jem.beaninfo.vm;
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
 *  $RCSfile: BaseBeanInfo.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */

import java.beans.*;
import java.lang.reflect.*;
/**
 * BaseBeanInfo is used as a utility for creating
 * beaninfos and provides useful constants.
 * It can also be used as a superclass for simplicity purposes.
 */
public abstract class BaseBeanInfo extends SimpleBeanInfo {
	// Constants to use to create all descriptors etc.
	public static java.util.ResourceBundle RESBUNDLE = java.util.ResourceBundle.getBundle("org.eclipse.jem.beaninfo.vm.basebeaninfonls");  //$NON-NLS-1$
	public static final String BOUND = "bound";//$NON-NLS-1$
	public static final String CONSTRAINED = "constrained";//$NON-NLS-1$
	public static final String PROPERTYEDITORCLASS = "propertyEditorClass";//$NON-NLS-1$
	public static final String READMETHOD = "readMethod";//$NON-NLS-1$
	public static final String WRITEMETHOD = "writeMethod";//$NON-NLS-1$
	public static final String DISPLAYNAME = "displayName";//$NON-NLS-1$
	public static final String EXPERT = "expert";//$NON-NLS-1$
	public static final String HIDDEN = "hidden";//$NON-NLS-1$
	public static final String PREFERRED = "preferred";//$NON-NLS-1$
	public static final String SHORTDESCRIPTION = "shortDescription";//$NON-NLS-1$
	public static final String CUSTOMIZERCLASS = "customizerClass";//$NON-NLS-1$
	
	public static final String CATEGORY = "category"; //$NON-NLS-1$
	
	// Constants to use to create event set descriptors
	public static final String INDEFAULTEVENTSET = "inDefaultEventSet";//$NON-NLS-1$
	// Enumeration support
	public static final String ENUMERATIONVALUES = "enumerationValues";//$NON-NLS-1$
	// some features declared obscure to reduce cache size
	public static final String OBSCURE = "ivjObscure";//$NON-NLS-1$
	// control visibility on the properties sheet at design time
	public static final String DESIGNTIMEPROPERTY = "ivjDesignTimeProperty";			//$NON-NLS-1$
	
	// adapter classes for eventSetDescriptors that provide default no-op implementation of the interface methods
	// e.g. java.awt.event.WindowListener has java.awt.evennt.WindowAdapter
	public static final String EVENTADAPTERCLASS = "eventAdapterClass"; //$NON-NLS-1$
	
	// The keys for icon file names, NOT THE java.awt.icon key.
	public static final String ICONCOLOR16X16URL = "ICON_COLOR_16x16";	//$NON-NLS-1$
	public static final String ICONCOLOR32X32URL = "ICON_COLOR_32x32";	//$NON-NLS-1$
	public static final String ICONMONO16X16URL = "ICON_MONO_16x16";	//$NON-NLS-1$
	public static final String ICONMONO32X32URL = "ICON_MONO_32x32";	//$NON-NLS-1$
	
	public static final boolean JVM_1_3 = System.getProperty("java.version","").startsWith("1.3");	 //$NON-NLS-1$ //$NON-NLS-2$  //$NON-NLS-3$

// Modified methods from java.beans.Introspector
private static String capitalize(String s) 
{
	if (s.length() == 0) {
		return s;
	}
	char chars[] = s.toCharArray();
	chars[0] = Character.toUpperCase(chars[0]);
	return new String(chars);
}
/**
  * Create a BeanDescriptor object given an array of keyword/value 
  * arguments.
  */
public BeanDescriptor createBeanDescriptor(Class cls, Object[] args) 
{
	Class customizerClass = null;
	
	/* Find the specified customizerClass */
	for(int i = 0; i < args.length; i += 2) {
	   if (CUSTOMIZERCLASS.equals((String)args[i])) {
			customizerClass = (Class)args[i + 1];
			break;
	   }
	}

	BeanDescriptor bd = new BeanDescriptor(cls, customizerClass);

	for(int i = 0; i < args.length; i += 2) {
		String key = (String)args[i];
		Object value = args[i + 1];
		setFeatureDescriptorValue(bd, key, value);
	}
	
	return bd;
}
/**
  * Create a beans EventSetDescriptor given the following:
  * @param cls - The bean class
  * @param name - Name of event set
  * @param args - array of attribute keys and values
  * @param lmds - array of MethodDescriptors defining the
  *               listener methods
  * @param listenerType - type of listener
  * @param addListenerName - add listener method name
  * @param removeListenerName- remove listener method name
  * required information
  */
public EventSetDescriptor createEventSetDescriptor(Class cls, String name, Object[] args,
		MethodDescriptor[] lmds, Class listenerType,
		String addListenerName, String removeListenerName) 
{
	EventSetDescriptor esd = null;
	Class[] paramTypes = { listenerType };
	try {
		java.lang.reflect.Method addMethod = null;
		java.lang.reflect.Method removeMethod = null;
		try {
			/* get addListenerMethod with parameter types. */
			addMethod = cls.getMethod(addListenerName, paramTypes);
		} catch (Exception ie) {
			throwError(ie,
				java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
					new Object[] {addListenerName} ));
		};
		try {
			/* get removeListenerMethod with parameter types. */
			removeMethod = cls.getMethod(removeListenerName, paramTypes);
		} catch (Exception ie) {
			throwError(ie,
				java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
					new Object[] {removeListenerName} ));
		};

		esd = new EventSetDescriptor(name, listenerType,
			lmds, addMethod, removeMethod);
	} catch (Exception ie) {
		throwError(ie,
			java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_create_the_E1_EXC_"), //$NON-NLS-1$
					new Object[] {name} ));
	};
	// set the event set descriptor properties
	for(int i = 0; i < args.length; i += 2) {
	   String key = (String)args[i];
	   Object value = args[i+ 1];
	   if (INDEFAULTEVENTSET.equals(key)) {
			esd.setInDefaultEventSet(((Boolean)value).booleanValue());
	   }
	   else
			setFeatureDescriptorValue(esd, key, value);
	}

	return esd;
}
/**
  * Create a beans MethodDescriptor
  */
public MethodDescriptor createMethodDescriptor(Class cls, String name, Object[] args,
		ParameterDescriptor[] params, Class[] paramTypes) 
{
	MethodDescriptor md = null;
	try {
		java.lang.reflect.Method aMethod = null;
		try {
			/* getMethod with parameter types. */
			aMethod = cls.getMethod(name, paramTypes);
		} catch (Exception ie) {
			throwError(ie, 
				java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
					new Object[] {name} ));
		};
	if(paramTypes.length > 0)
			md = new MethodDescriptor(aMethod, params);
		else
			md = new MethodDescriptor(aMethod);
	} catch (Exception ie) {
		throwError(ie, 
			java.text.MessageFormat.format(
				RESBUNDLE.getString("Cannot_create_Method_EXC_"), //$NON-NLS-1$
				new Object[] {name} ));
	};
   // set the method properties
	for(int i = 0; i < args.length; i += 2) {
	   String key = (String)args[i];
	   Object value = args[i + 1];	    
		setFeatureDescriptorValue(md, key, value);
	}
	return md;
}
private PropertyDescriptor createOtherPropertyDescriptor(		
		String name, Class cls) throws IntrospectionException 
{
	Method readMethod = null;
	Method writeMethod = null;
	String base = capitalize(name);
	Class[] parameters = new Class[0];

	// First we try boolean accessor pattern
	try {
		readMethod = cls.getMethod("is" + base, parameters);//$NON-NLS-1$
	} catch (Exception ex1) {}
	if (readMethod == null) {
		try {
			// Else we try the get accessor pattern.
			readMethod = cls.getMethod("get" + base, parameters);//$NON-NLS-1$
		} catch (Exception ex2) {
			// Find by matching methods of the class
			readMethod = findMethod(cls, "get" + base, 0);//$NON-NLS-1$
		}
	}
	
	if(readMethod == null){
		// For write-only properties, find the write method	
		writeMethod = findMethod(cls, "set" + base, 1);//$NON-NLS-1$
	}
	else {
		// In Sun's code, reflection fails if there are two
		// setters with the same name and the first setter located
		// does not have the same return type of the getter.
		// This fixes that.
		parameters = new Class[1]; 
		parameters[0] = readMethod.getReturnType();
		try {
			writeMethod = cls.getMethod("set" + base, parameters);//$NON-NLS-1$
		} catch (Exception ex3) {}
	}
	// create the property descriptor	
	if((readMethod != null) || (writeMethod != null)) {
		return new PropertyDescriptor(name, readMethod, writeMethod);
	}
	else {
		throw new IntrospectionException(
			java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_find_the_acc1_EXC_"), //$NON-NLS-1$
					new Object[] {name} ));
	}
}
	/**
	 * Create a beans ParameterDescriptor given array of keyword/value 
	 * arguments.
	 */
public ParameterDescriptor createParameterDescriptor(String name, Object[] args) 
{
	ParameterDescriptor pd = null;
	try {
			pd = new ParameterDescriptor();
	} catch (Exception ie) {
		throwError(ie,
			java.text.MessageFormat.format(
				RESBUNDLE.getString("Cannot_create_Param1_EXC_"), //$NON-NLS-1$
				new Object[] {name} ));
	};
	// set the name
	pd.setName(name);
   // set the method properties
	for(int i = 0; i < args.length; i += 2) {
	   String key = (String)args[i];
	   Object value = args[i + 1];	    
		setFeatureDescriptorValue(pd, key, value);
	}

	return pd;
}
/**
  * Create a beans PropertyDescriptor given an array of keyword/values 
  */
public PropertyDescriptor createPropertyDescriptor(Class cls, String name, Object[] args) 
{
	PropertyDescriptor pd = null;
	try {		
		// Create assuming that the getter/setter follows reflection patterns
		pd = new PropertyDescriptor(name, cls);
	} catch (IntrospectionException e) {
		// Try creating a property descriptor for read-only, write-only
		// or if Sun's reflection fails
		try {
			pd = createOtherPropertyDescriptor(name, cls);
		} catch (IntrospectionException ie) {
			throwError(ie,
				java.text.MessageFormat.format(
					RESBUNDLE.getString("Cannot_create_the_P1_EXC_"), //$NON-NLS-1$
					new Object[] {name} ));
		}
	}
		
	// set the display name same as the name	
	setFeatureDescriptorValue(pd, DISPLAYNAME, name);
		
	for(int i = 0; i < args.length; i += 2) {
	   String key = (String)args[i];
	   Object value = args[i + 1];
	    
	   if (BOUND.equals(key)) {
			pd.setBound(((Boolean)value).booleanValue());
		} 
		else if (CONSTRAINED.equals(key)) {
			pd.setConstrained(((Boolean)value).booleanValue());
		}
		else if (PROPERTYEDITORCLASS.equals(key)) {
			pd.setPropertyEditorClass((Class)value);
		}
		else if (READMETHOD.equals(key)) {
			String methodName = (String)value;
			Method method;
			try {
					method = cls.getMethod(methodName, new Class[0]);
					pd.setReadMethod(method);
			}
			catch(Exception e) {
				throwError(e, 
					java.text.MessageFormat.format(
						RESBUNDLE.getString("{0}_no_read_method_EXC_"), //$NON-NLS-1$
						new Object[] {cls, methodName} ));
			}
		}
		else if (WRITEMETHOD.equals(key)) {
			String methodName = (String)value;
			try {
				if(methodName == null){
					pd.setWriteMethod(null);
				} else {
					Method method;
						Class type = pd.getPropertyType();
						method = cls.getMethod(methodName, new Class[]{type});
						pd.setWriteMethod(method);
				} 
			} catch(Exception e) {
					throwError(e,
				java.text.MessageFormat.format(
					RESBUNDLE.getString("{0}_no_write_method_EXC_"), //$NON-NLS-1$
					new Object[] {cls, methodName} ));
			}
		}
		else {
		// arbitrary value
			setFeatureDescriptorValue(pd, key, value);
		}
	}

	return pd;
}
/**
 * Find the method by comparing (name & parameter size) against the methods in the class.
 * @return java.lang.reflect.Method
 * @param aClass java.lang.Class
 * @param methodName java.lang.String
 * @param parameterCount int
 */
public static java.lang.reflect.Method findMethod(java.lang.Class aClass, 
	java.lang.String methodName, int parameterCount) {
	try {
		/* Since this method attempts to find a method by getting all methods 
		 * from the class, this method should only be called if getMethod cannot 
		 * find the method. */
		java.lang.reflect.Method methods[] = aClass.getMethods();
		for (int index = 0; index < methods.length; index++){
			java.lang.reflect.Method method = methods[index];
			if ((method.getParameterTypes().length == parameterCount) 
				&& (method.getName().equals(methodName))) {
				return method;
			};
		};
	} catch (java.lang.Throwable exception) {
		return null;
	};
	return null;
}
	/**
	 * The default index is always 0 i.e. show the first event
	 */
	public int getDefaultEventIndex() {
	return -1;
	}
	/**
	 * The default index is always 0 i.e. show the first property
	 */
	public int getDefaultPropertyIndex() {
	return -1;
	}

public BeanInfo[] getAdditionalBeanInfo(){
	try {
		return new BeanInfo[] {
			Introspector.getBeanInfo(getBeanClass().getSuperclass())
		};
	} catch (IntrospectionException e) {
		return new BeanInfo[0];
	}
}
public abstract Class getBeanClass();
/**
 * Called whenever the bean information class throws an exception.
 * @param exception java.lang.Throwable
 */
public void handleException(Throwable exception) {
	System.err.println(RESBUNDLE.getString("UNCAUGHT_EXC_")); //$NON-NLS-1$
	exception.printStackTrace();
}
private void setFeatureDescriptorValue(FeatureDescriptor fd, String key, Object value)
{
	if (DISPLAYNAME.equals(key)) {
		fd.setDisplayName((String)value);
	}
	else if (EXPERT.equals(key)) {
		fd.setExpert(((Boolean)value).booleanValue());
	}
	else if (HIDDEN.equals(key)) {
		fd.setHidden(((Boolean)value).booleanValue());
	}
	else if (PREFERRED.equals(key)) {
		fd.setPreferred(((Boolean) value).booleanValue());
		if (JVM_1_3) {
			// Bug in 1.3 doesn't preserve the preferred flag, so we will put it into the attributes too.
			fd.setValue(PREFERRED, value);
		}
	}
	else if (SHORTDESCRIPTION.equals(key)) {
		fd.setShortDescription((String)value);
	}
	// Otherwise assume an arbitrary-named value
	// Assume that the FeatureDescriptor private hashTable\
	// contains only arbitrary-named attributes
	else {
		fd.setValue(key, value);
	}
}    
/**
  * Fatal errors are handled by calling this method.
  */
protected void throwError(Exception e, String s) 
{
	throw new Error(e.toString() + " " + s);//$NON-NLS-1$
}
}