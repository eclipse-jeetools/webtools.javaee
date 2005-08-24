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
package org.eclipse.jem.beaninfo.vm;

/*
 *  $RCSfile: BaseBeanInfo.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:31:28 $ 
 */

import java.awt.Image;
import java.beans.*;
import java.lang.reflect.*;

/**
 * A BaseBeanInfo that provides common support for BeanInfos within the JEM environment.
 * 
 * @since 1.1.0
 */
public abstract class BaseBeanInfo extends SimpleBeanInfo {

	// Constants to use to create all descriptors etc.
	protected static java.util.ResourceBundle RESBUNDLE = java.util.ResourceBundle.getBundle("org.eclipse.jem.beaninfo.vm.beaninfo"); //$NON-NLS-1$

	/**
	 * Bound indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String BOUND = "bound";//$NON-NLS-1$

	/**
	 * Constrained indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String CONSTRAINED = "constrained";//$NON-NLS-1$

	/**
	 * Property editor class indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String PROPERTYEDITORCLASS = "propertyEditorClass";//$NON-NLS-1$

	/**
	 * Read Method indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String READMETHOD = "readMethod";//$NON-NLS-1$

	/**
	 * Write method indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String WRITEMETHOD = "writeMethod";//$NON-NLS-1$

	/**
	 * Displayname indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String DISPLAYNAME = "displayName";//$NON-NLS-1$
	/**
	 * Indicator used to describe a factory instantiation pattern.  Not API as will change to become more extensible 
	 * via .override mechanism in future
	 * 
	 * @since 1.1
	 */	
	public static final String FACTORY_CREATION = "FACTORY_CREATION";//$NON-NLS-1$	

	/**
	 * Expert indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String EXPERT = "expert";//$NON-NLS-1$

	/**
	 * Hidden indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String HIDDEN = "hidden";//$NON-NLS-1$

	/**
	 * Preferred indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String PREFERRED = "preferred";//$NON-NLS-1$

	/**
	 * Short description indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String SHORTDESCRIPTION = "shortDescription";//$NON-NLS-1$

	/**
	 * Customizer class indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String CUSTOMIZERCLASS = "customizerClass";//$NON-NLS-1$

	/**
	 * Category indicator for apply property arguments. Category is a pre-defined attribute name too. That is where the category is stored in a
	 * descriptor.
	 * 
	 * @since 1.1.0
	 */
	public static final String CATEGORY = "category"; //$NON-NLS-1$

	/**
	 * In Default eventset indicator for apply property arguments.
	 * 
	 * @since 1.1.0
	 */
	public static final String INDEFAULTEVENTSET = "inDefaultEventSet";//$NON-NLS-1$
	
	/**
	 * This is a Feature Attribute Key. When this key exists, the value is a java.lang.reflect.Field. It means this property
	 * is a field and not a getter/setter. The getter/setter will be ignored and the property type will be the type of the field.
	 * <p>
	 * At this time, do not use field on an indexed property. This is currently an indefined situation.
	 * 
	 * @since 1.1.0
	 */
	public static final String FIELDPROPERTY = "field"; //$NON-NLS-1$

	/**
	 * Enumeration values indicator for apply property arguments. Enumeration values is a pre-defined attribute name too. That is where the
	 * enumeration values are stored.
	 * 
	 * @since 1.1.0
	 */
	public static final String ENUMERATIONVALUES = "enumerationValues";//$NON-NLS-1$

	/**
	 * Obscure indicator for apply property arguments. Obsure is a pre-defined attribute name too. That is where the obscure setting is stored.
	 * <p>
	 * Obsure means most users don't need it. In the future such features won't even be cached so as to reduce the in-memory costs. Currently this
	 * flag is ignored.
	 * 
	 * @since 1.1.0
	 */
	public static final String OBSCURE = "ivjObscure";//$NON-NLS-1$

	/**
	 * Design time indicator for apply property arguments. Design time is a pre-defined attribute name too. That is where the design time setting is
	 * stored.
	 * <p>
	 * Design time means:
	 * <ul>
	 * <li>Not set: Will be a property that can be connected to, and shows on property sheet (if not hidden).
	 * <li><code>true</code>: Special property (it will show on property sheet if not hidden), but it can't be connected to. Usually this is a
	 * property that is fluffed up for the IDE purposes but doesn't have a get/set method. This means it is a property for design time and not for
	 * runtime.
	 * <li><code>false</code>: This property will not show up on property sheet but it can be connected to.
	 * </ul>
	 * 
	 * @since 1.1.0
	 */
	public static final String DESIGNTIMEPROPERTY = "ivjDesignTimeProperty"; //$NON-NLS-1$

	/**
	 * EventAdapterClass indicator for apply property arguments. Event adapter class is a pre-defined attribute name too. That is where the event
	 * adapter is stored.
	 * <p>
	 * Adapter class for eventSetDescriptors that provide default no-op implementation of the interface methods. For example
	 * <code>java.awt.event.WindowListener</code> has an adapter of <code>java.awt.event.WindowAdapter</code>. What is stored is actually the
	 * class name, not the class itself.
	 * 
	 * @since 1.1.0
	 */
	public static final String EVENTADAPTERCLASS = "eventAdapterClass"; //$NON-NLS-1$

	// The keys for icon file names, NOT THE java.awt.icon key.
	public static final String ICONCOLOR16X16URL = "ICON_COLOR_16x16_URL"; //$NON-NLS-1$	// Not used.

	public static final String ICONCOLOR32X32URL = "ICON_COLOR_32x32_URL"; //$NON-NLS-1$     	// Not used

	public static final String ICONMONO16X16URL = "ICON_MONO_16x16_URL"; //$NON-NLS-1$        	// Not used

	public static final String ICONMONO32X32URL = "ICON_MONO_32x32_URL"; //$NON-NLS-1$			// Not used

	public static final boolean JVM_1_3 = System.getProperty("java.version", "").startsWith("1.3"); //$NON-NLS-1$ //$NON-NLS-2$  //$NON-NLS-3$
	
	/**
	 * Empty args list for those descriptors that don't have arguments.
	 * @since 1.1.0
	 */
	public static final Object[] EMPTY_ARGS = new Object[0];

	/**
	 * Capitalize the string. This uppercases only the first character. So if you have property name of "abc" it will become "Abc".
	 * 
	 * @param s
	 * @return string with first letter capitalized.
	 * 
	 * @since 1.1.0
	 */
	public static String capitalize(String s) {
		if (s.length() == 0) { return s; }
		char chars[] = s.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	/**
	 * Create a BeanDescriptor object given an array of keyword/value arguments. Use the keywords defined in this class, e.g. BOUND, EXPERT, etc.
	 * 
	 * @param cls
	 *            bean for which the bean descriptor is being created.
	 * @param args
	 *            arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 * @return new bean descriptor
	 * 
	 * @since 1.1.0
	 */
	public static BeanDescriptor createBeanDescriptor(Class cls, Object[] args) {
		Class customizerClass = null;

		/* Find the specified customizerClass */
		for (int i = 0; i < args.length; i += 2) {
			if (CUSTOMIZERCLASS.equals(args[i])) {
				customizerClass = (Class) args[i + 1];
				break;
			}
		}

		BeanDescriptor bd = new BeanDescriptor(cls, customizerClass);

		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];
			setFeatureDescriptorValue(bd, key, value);
		}

		return bd;
	}

	/**
	 * Create a beans EventSetDescriptor given the following:
	 * 
	 * @param cls
	 *            The bean class
	 * @param name
	 *            Name of event set
	 * @param args
	 *            arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 * @param lmds
	 *            array of MethodDescriptors defining the listener methods
	 * @param listenerType
	 *            type of listener
	 * @param addListenerName
	 *            add listener method name
	 * @param removeListenerNameremove
	 *            listener method name
	 * @return new event set descriptor
	 * @since 1.1.0
	 */
	public static EventSetDescriptor createEventSetDescriptor(Class cls, String name, Object[] args, MethodDescriptor[] lmds, Class listenerType,
			String addListenerName, String removeListenerName) {
		EventSetDescriptor esd = null;
		Class[] paramTypes = { listenerType};
		try {
			java.lang.reflect.Method addMethod = null;
			java.lang.reflect.Method removeMethod = null;
			try {
				/* get addListenerMethod with parameter types. */
				addMethod = cls.getMethod(addListenerName, paramTypes);
			} catch (Exception ie) {
				throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
						new Object[] { addListenerName}));
			}
			;
			try {
				/* get removeListenerMethod with parameter types. */
				removeMethod = cls.getMethod(removeListenerName, paramTypes);
			} catch (Exception ie) {
				throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
						new Object[] { removeListenerName}));
			}
			;

			esd = new EventSetDescriptor(name, listenerType, lmds, addMethod, removeMethod);
		} catch (Exception ie) {
			throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_the_E1_EXC_"), //$NON-NLS-1$
					new Object[] { name}));
		}
		;
		// set the event set descriptor properties
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];
			if (INDEFAULTEVENTSET.equals(key)) {
				esd.setInDefaultEventSet(((Boolean) value).booleanValue());
			} else
				setFeatureDescriptorValue(esd, key, value);
		}

		return esd;
	}

	/**
	 * Create a bean's MethodDescriptor.
	 * 
	 * @param cls
	 *            class of the method.
	 * @param name
	 *            name of the method.
	 * @param args
	 *            arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 * @param params
	 *            parameter descriptors or <code>null</code> if no parameter descriptors.
	 * @param paramTypes
	 *            parameter types
	 * @return new method descriptor
	 * 
	 * @since 1.1.0
	 */
	public static MethodDescriptor createMethodDescriptor(Class cls, String name, Object[] args, ParameterDescriptor[] params, Class[] paramTypes) {
		MethodDescriptor md = null;
		try {
			java.lang.reflect.Method aMethod = null;
			try {
				/* getMethod with parameter types. */
				aMethod = cls.getMethod(name, paramTypes);
			} catch (Exception ie) {
				throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_get_the_meth1_EXC_"), //$NON-NLS-1$
						new Object[] { name}));
			}
			;
			if (params != null && params.length > 0)
				md = new MethodDescriptor(aMethod, params);
			else
				md = new MethodDescriptor(aMethod);
		} catch (Exception ie) {
			throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_Method_EXC_"), //$NON-NLS-1$
					new Object[] { name}));
		}
		;
		// set the method properties
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];
			setFeatureDescriptorValue(md, key, value);
		}
		return md;
	}

	private static PropertyDescriptor createOtherPropertyDescriptor(String name, Class cls) throws IntrospectionException {
		Method readMethod = null;
		Method writeMethod = null;
		String base = capitalize(name);
		Class[] parameters = new Class[0];

		// First we try boolean accessor pattern
		try {
			readMethod = cls.getMethod("is" + base, parameters);//$NON-NLS-1$
		} catch (Exception ex1) {
		}
		if (readMethod == null) {
			try {
				// Else we try the get accessor pattern.
				readMethod = cls.getMethod("get" + base, parameters);//$NON-NLS-1$
			} catch (Exception ex2) {
				// Find by matching methods of the class
				readMethod = findMethod(cls, "get" + base, 0);//$NON-NLS-1$
			}
		}

		if (readMethod == null) {
			// For write-only properties, find the write method
			writeMethod = findMethod(cls, "set" + base, 1);//$NON-NLS-1$
		} else {
			// In Sun's code, reflection fails if there are two
			// setters with the same name and the first setter located
			// does not have the same return type of the getter.
			// This fixes that.
			parameters = new Class[1];
			parameters[0] = readMethod.getReturnType();
			try {
				writeMethod = cls.getMethod("set" + base, parameters);//$NON-NLS-1$
			} catch (Exception ex3) {
			}
		}
		// create the property descriptor
		if ((readMethod != null) || (writeMethod != null)) {
			return new PropertyDescriptor(name, readMethod, writeMethod);
		} else {
			throw new IntrospectionException(java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_find_the_acc1_EXC_"), //$NON-NLS-1$
					new Object[] { name}));
		}
	}

	/**
	 * Create a beans parameter descriptor.
	 * 
	 * @param name
	 *            name of parameter
	 * @param args
	 *            arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 * @return new parameter descriptor
	 * 
	 * @since 1.1.0
	 */
	public static ParameterDescriptor createParameterDescriptor(String name, Object[] args) {
		ParameterDescriptor pd = null;
		try {
			pd = new ParameterDescriptor();
		} catch (Exception ie) {
			throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_Param1_EXC_"), //$NON-NLS-1$
					new Object[] { name}));
		}
		;
		// set the name
		pd.setName(name);
		// set the method properties
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];
			setFeatureDescriptorValue(pd, key, value);
		}

		return pd;
	}
	
	private static Method GETCLASS; 
	
	static {
		try {
			GETCLASS = Object.class.getMethod("getClass", null); //$NON-NLS-1$
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
	}
	/**
	 * Create a property descriptor describing a field property.
	 * <p>
	 * Note: This is non-standard. The VE knows how to handle this, but any one else using BeanInfo will see this as a property with
	 * no getter or setter.
	 * @param name
	 * @param field
	 * @param args
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static PropertyDescriptor createFieldPropertyDescriptor(String name, Field field, Object[] args) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(name, null, null);
			pd.setValue(FIELDPROPERTY, field);	// Set the field property so we know it is a field.
			applyFieldArguments(pd, args);
			// Need to set in a phony read method because Introspector will throw it away otherwise. We just use Object.getClass for this.
			// We will ignore the property type for fields. If used outside of VE then it will look like a class property.
			pd.setReadMethod(GETCLASS);
			return pd;
		} catch (IntrospectionException e) {
			throwError(e, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_the_P1_EXC_"), //$NON-NLS-1$
					new Object[] { name}));
			return null;
		}
	}

	/**
	 * Create a bean's property descriptor.
	 * 
	 * @param cls
	 *            class of who owns the property (usually the bean). It is used to look up get/set methods for the property.
	 * @param name
	 *            name of the property. It will use get{Name} and set{Name} to find get/set methods.
	 * @param args
	 *            arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 * @return new property descriptor
	 * 
	 * @since 1.1.0
	 */
	public static PropertyDescriptor createPropertyDescriptor(Class cls, String name, Object[] args) {
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
				throwError(ie, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_the_P1_EXC_"), //$NON-NLS-1$
						new Object[] { name}));
			}
		}

		applyPropertyArguments(pd, args, cls);

		return pd;
	}

	/**
	 * Create a new PropertyDescriptor based upon the PD sent in. It will clone the sent in one, and apply the args to override any specific setting.
	 * Class cls is used for finding read/write methods, if any.
	 * 
	 * This is used when wanting to override only a few specific settings from a property descriptor from the super class.
	 * 
	 * @param fromPDS
	 *            The PropertyDescriptor array to find the entry to clone. It will be changed in place in the array.
	 * @param name
	 *            The name of the property to find and clone and override.
	 * @param cls
	 *            The class to use to find read/write methods in args. If no read/write methods specified, then this may be null.
	 * @param args
	 *            The arguments to override from fromPD. arg pairs, [0] keyword, [1] value, [2] keyword, [3] value, etc.
	 */
	public void replacePropertyDescriptor(PropertyDescriptor[] pds, String name, Class cls, Object[] args) {
		PropertyDescriptor pd = null;
		int iPD = findPropertyDescriptor(pds, name);
		if (iPD == -1)
			return;
		PropertyDescriptor fromPD = pds[iPD];
		try {

			pd = pds[iPD] = new PropertyDescriptor(fromPD.getName(), null, null);
		} catch (IntrospectionException e) {
			throwError(e, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_the_P1_EXC_"), //$NON-NLS-1$
					new Object[] { fromPD.getName()}));
		}

		// Now copy over the contents of fromPD.
		clonePropertySettings(fromPD, pd);

		// Now apply the overrides
		applyPropertyArguments(pd, args, cls);
		return;
	}

	private void clonePropertySettings(PropertyDescriptor fromPD, PropertyDescriptor pd) {
		try {
			pd.setReadMethod(fromPD.getReadMethod());
			pd.setWriteMethod(fromPD.getWriteMethod());
			pd.setPropertyEditorClass(fromPD.getPropertyEditorClass());
			pd.setBound(fromPD.isBound());
			pd.setConstrained(fromPD.isConstrained());
			cloneFeatureSettings(fromPD, pd);
		} catch (IntrospectionException e) {
			throwError(e, java.text.MessageFormat.format(RESBUNDLE.getString("Cannot_create_the_P1_EXC_"), //$NON-NLS-1$
					new Object[] { fromPD.getName()}));
		}
	}

	private void cloneFeatureSettings(FeatureDescriptor fromFD, FeatureDescriptor fd) {
		fd.setExpert(fromFD.isExpert());
		fd.setHidden(fromFD.isHidden());
		fd.setPreferred(fromFD.isPreferred());
		fd.setShortDescription(fromFD.getShortDescription());
		fd.setDisplayName(fromFD.getDisplayName());

		java.util.Enumeration keys = fromFD.attributeNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			Object value = fromFD.getValue(key);
			fd.setValue(key, value);
		}
	}

	/*
	 * The common property arguments between field and standard properties.
	 */
	private static boolean applyCommonPropertyArguments(PropertyDescriptor pd, String key, Object value) {
		if (BOUND.equals(key)) {
			pd.setBound(((Boolean) value).booleanValue());
		} else if (CONSTRAINED.equals(key)) {
			pd.setConstrained(((Boolean) value).booleanValue());
		} else if (PROPERTYEDITORCLASS.equals(key)) {
			pd.setPropertyEditorClass((Class) value);
		} else if (FIELDPROPERTY.equals(key))
			return true;	// This should not be applied except through createFieldProperty.
		else
			return false;
		return true;
	
	}
	
	private static void applyPropertyArguments(PropertyDescriptor pd, Object[] args, Class cls) {
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];

			if (!applyCommonPropertyArguments(pd, key, value)) {
				if (READMETHOD.equals(key)) {
					String methodName = (String) value;
					Method method;
					try {
						method = cls.getMethod(methodName, new Class[0]);
						pd.setReadMethod(method);
					} catch (Exception e) {
						throwError(e, java.text.MessageFormat.format(RESBUNDLE.getString("{0}_no_read_method_EXC_"), //$NON-NLS-1$
								new Object[] { cls, methodName}));
					}
				} else if (WRITEMETHOD.equals(key)) {
					String methodName = (String) value;
					try {
						if (methodName == null) {
							pd.setWriteMethod(null);
						} else {
							Method method;
							Class type = pd.getPropertyType();
							method = cls.getMethod(methodName, new Class[] { type});
							pd.setWriteMethod(method);
						}
					} catch (Exception e) {
						throwError(e, java.text.MessageFormat.format(RESBUNDLE.getString("{0}_no_write_method_EXC_"), //$NON-NLS-1$
								new Object[] { cls, methodName}));
					}
				} else {
					// arbitrary value
					setFeatureDescriptorValue(pd, key, value);
				}
			}
		}
	}

	private static void applyFieldArguments(PropertyDescriptor pd, Object[] args) {
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];
			Object value = args[i + 1];

			if (!applyCommonPropertyArguments(pd, key, value)) {
				if (READMETHOD.equals(key)) {
					// ignored for field.
				} else if (WRITEMETHOD.equals(key)) {
					// ignored for field.
				} else {
					// arbitrary value
					setFeatureDescriptorValue(pd, key, value);
				}
			}
		}
	}

	/**
	 * Find the method by comparing (name & parameter size) against the methods in the class. This is an expensive call and should be used only if
	 * getMethod with specific parameter types can't find method.
	 * 
	 * @return java.lang.reflect.Method
	 * @param aClass
	 *            java.lang.Class
	 * @param methodName
	 *            java.lang.String
	 * @param parameterCount
	 *            int
	 */
	public static java.lang.reflect.Method findMethod(java.lang.Class aClass, java.lang.String methodName, int parameterCount) {
		try {
			/*
			 * Since this method attempts to find a method by getting all methods from the class, this method should only be called if getMethod
			 * cannot find the method.
			 */
			java.lang.reflect.Method methods[] = aClass.getMethods();
			for (int index = 0; index < methods.length; index++) {
				java.lang.reflect.Method method = methods[index];
				if ((method.getParameterTypes().length == parameterCount) && (method.getName().equals(methodName))) { return method; }
				;
			}
			;
		} catch (java.lang.Throwable exception) {
			return null;
		}
		;
		return null;
	}

	/**
	 * Find a property descriptor of a given name in the list.
	 * 
	 * @param pds
	 *            The array of property descriptors to search, may be null.
	 * @param name
	 *            The name to search for.
	 * @return The found property descriptor index, or -1 if not found.
	 */
	public static int findPropertyDescriptor(PropertyDescriptor[] pds, String name) {
		for (int i = 0; i < pds.length; i++) {
			if (name.equals(pds[i].getName()))
				return i;
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.BeanInfo#getDefaultEventIndex()
	 */
	public int getDefaultEventIndex() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.BeanInfo#getDefaultPropertyIndex()
	 */
	public int getDefaultPropertyIndex() {
		return -1;
	}
	
	
	/* (non-Javadoc)
	 * @see java.beans.SimpleBeanInfo#getBeanDescriptor()
	 */
	public BeanDescriptor getBeanDescriptor() {
		// Default is to create an empty one.
		return createBeanDescriptor(getBeanClass(), EMPTY_ARGS);
	}

	/**
	 * Implementation for BeanInfo. This implementation will return the BeanInfo of the superclass.
	 * 
	 * @see BeanInfo#getAdditionalBeanInfo()
	 * @since 1.1.0
	 */
	public BeanInfo[] getAdditionalBeanInfo() {
		try {
			BeanInfo[] result = new BeanInfo[] { Introspector.getBeanInfo(getBeanClass().getSuperclass())};
			PropertyDescriptor[] oPDs = result[0].getPropertyDescriptors();
			PropertyDescriptor[] nPDs = overridePropertyDescriptors(oPDs);
			if (oPDs != nPDs)
				result[0] = new OverridePDBeanInfo(result[0], nPDs);
			return result;
		} catch (IntrospectionException e) {
			return new BeanInfo[0];
		}
	}

	private static class OverridePDBeanInfo implements BeanInfo {

		private BeanInfo originalBeanInfo;

		private PropertyDescriptor[] overridePDs;

		public OverridePDBeanInfo(BeanInfo bi, PropertyDescriptor[] pds) {
			originalBeanInfo = bi;
			overridePDs = pds;
		}

		public BeanInfo[] getAdditionalBeanInfo() {
			return originalBeanInfo.getAdditionalBeanInfo();
		}

		public BeanDescriptor getBeanDescriptor() {
			return originalBeanInfo.getBeanDescriptor();
		}

		public int getDefaultEventIndex() {
			return originalBeanInfo.getDefaultEventIndex();
		}

		public int getDefaultPropertyIndex() {
			return originalBeanInfo.getDefaultPropertyIndex();
		}

		public EventSetDescriptor[] getEventSetDescriptors() {
			return originalBeanInfo.getEventSetDescriptors();
		}

		public Image getIcon(int iconKind) {
			return originalBeanInfo.getIcon(iconKind);
		}

		public MethodDescriptor[] getMethodDescriptors() {
			return originalBeanInfo.getMethodDescriptors();
		}

		public PropertyDescriptor[] getPropertyDescriptors() {
			return overridePDs;
		}
	}

	/**
	 * Allow overrides to parent beaninfo. Subclasses should override this method if they wish to override and change any inherited properties. This
	 * allows removal of inherited properties or changes of specific properties (such as change from hidden to not hidden).
	 * 
	 * Note: If there any changes, this must return a DIFFERENT array. If it returns the same array, then the changes will not be accepted. If just
	 * overriding, should use pds.clone() to get the new array and then change the specific entries.
	 * 
	 * @param pds
	 * @return The new changed array or the same array if no changes.
	 * @since 1.1.0
	 */
	protected PropertyDescriptor[] overridePropertyDescriptors(PropertyDescriptor[] pds) {
		return pds;
	}

	/**
	 * Get the bean class this beaninfo is for. Used by subclasses to quickly get the bean class without having to code it over and over.
	 * 
	 * @return bean class for this beaninfo.
	 * 
	 * @since 1.1.0
	 */
	public abstract Class getBeanClass();

	/**
	 * Called whenever the bean information class throws an exception. By default it prints a message and then a stacktrace to sys err.
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 * @since 1.1.0
	 */
	public void handleException(Throwable exception) {
		System.err.println(RESBUNDLE.getString("UNCAUGHT_EXC_")); //$NON-NLS-1$
		exception.printStackTrace();
	}

	private static void setFeatureDescriptorValue(FeatureDescriptor fd, String key, Object value) {
		if (DISPLAYNAME.equals(key)) {
			fd.setDisplayName((String) value);
		} else if (EXPERT.equals(key)) {
			fd.setExpert(((Boolean) value).booleanValue());
		} else if (HIDDEN.equals(key)) {
			fd.setHidden(((Boolean) value).booleanValue());
		} else if (PREFERRED.equals(key)) {
			fd.setPreferred(((Boolean) value).booleanValue());
			if (JVM_1_3) {
				// Bug in 1.3 doesn't preserve the preferred flag, so we will put it into the attributes too.
				fd.setValue(PREFERRED, value);
			}
		} else if (SHORTDESCRIPTION.equals(key)) {
			fd.setShortDescription((String) value);
		}
		// Otherwise assume an arbitrary-named value
		// Assume that the FeatureDescriptor private hashTable\
		// contains only arbitrary-named attributes
		else {
			fd.setValue(key, value);
		}
	}

	/**
	 * Fatal errors are handled by calling this method. By default it throws an Error exception.
	 * 
	 * @param e
	 *            exception exception message placed into the new Error thrown.
	 * @param s
	 *            message added to exception message. <code>null</code> if nothing to add.
	 * 
	 * @throws Error
	 *             turns exception into an Error.
	 * @since 1.1.0
	 */
	protected static void throwError(Exception e, String s) {
		throw new Error(e.toString() + " " + s);//$NON-NLS-1$
	}
}
