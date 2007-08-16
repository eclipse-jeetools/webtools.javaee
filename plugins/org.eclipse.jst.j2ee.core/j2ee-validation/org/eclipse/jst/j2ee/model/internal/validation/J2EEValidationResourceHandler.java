/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

public class J2EEValidationResourceHandler {
	private static java.util.Hashtable handlers = new java.util.Hashtable();
	protected ClassLoader resourceClassLoader;
	protected java.util.Locale locale = null;
	protected String bundleName = null;
	protected java.util.ResourceBundle bundle = null;
/**
 * ResourceHandler constructor.  This constructor uses the default locale.
 *
 */
public J2EEValidationResourceHandler() {
	super();
}
/**
 * ResourceHandler constructor.
 *
 * @param The Locale object containing the locale to be used to locate the resource bundle.
 */
public J2EEValidationResourceHandler(Locale locale) {
	super();
	setLocale(locale);
}
/**
 * Getter for the resource bundle property.  This property is lazily initialized using the
 * locale property; so that property must be set before calling this method.
 *
 * @return The ResourceBundle object containing the messages to be handled by this instance.
 */
public java.util.ResourceBundle getBundle() {
	if (this.bundle == null) {
		try {
			if (getResourceClassLoader() == null)
				this.bundle = java.util.ResourceBundle.getBundle(getBundleName(),getLocale());
			else
				this.bundle = java.util.ResourceBundle.getBundle(getBundleName(),getLocale(), getResourceClassLoader());
		} catch (MissingResourceException e) {
			System.out.println("Exception retrieving bundle: " + getBundleName() +//$NON-NLS-1$
				" using locale: " + getLocale().toString());//$NON-NLS-1$
			e.printStackTrace();
		} // endtry
	} // endif
	return this.bundle;
}

/**
 * Insert the method's description here.
 * Creation date: (1/5/01 2:26:24 PM)
 * @return java.lang.String
 */
public java.lang.String getBundleName() {
	return bundleName;
}
/**
 * Get the message with the specified key.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass) {
	
	return getHandler(fileName, aClass).getMessage(key);
	
}
/**
 * Get the message template with the specified key, and format the message using
 * the specified Object array.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg The Object array containing the substitution values for the message.
 * @return The message string.
 */
public static String getExternalizedMessage(String fileName, String key, Class aClass, Object arg[]) {
	
	return getHandler(fileName, aClass).getMessage(key, arg);
}
/**
 * Get the message template with the specified key, and format the message using
 * the specified Object array.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg The Object array containing the substitution values for the message.
 * @return The message string.
 */
public static String getExternalizedMessage(String fileName, String key, Class aClass, Object arg[], Locale locale) {
	
	return getHandler(fileName, aClass, locale).getMessage(key, arg);
}
/**
 * Get the message template with the specified key, and format the message using
 * the specified String argument.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @return The message string.
 */
public static String getExternalizedMessage(String fileName, String key, Class aClass, String arg1) {
	
	return getHandler(fileName, aClass).getMessage(key, arg1);

}
/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @return The message string.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass, String arg1, String arg2) {
	
	return getHandler(fileName, aClass).getMessage(key, arg1, arg2);

}
/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @return The message string.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass, String arg1, String arg2, String arg3) {
	
	return getHandler(fileName, aClass).getMessage(key, arg1, arg2, arg3);
	
}
/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @return The message string.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass, String arg1, String arg2, String arg3, String arg4) {

	return getHandler(fileName, aClass).getMessage(key, arg1, arg2, arg3, arg4);

}
/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @param arg5 The argument containing the substitution values for the message variable 5.
 * @return The message string.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass,
						 String arg1, 
						 String arg2, 
						 String arg3, 
						 String arg4, 
						 String arg5) {
	
	return getHandler(fileName, aClass).getMessage(key,
						 arg1, 
						 arg2, 
						 arg3, 
						 arg4, 
						 arg5);
	
}
/**
 * Get the message with the specified key.
 *
 * @param fileName The name of the property file that contains the message.
 * @param key The key for the message to be retrieved.
 */

public static String getExternalizedMessage(String fileName, String key, Class aClass, Locale locale) {
	
	return getHandler(fileName, aClass, locale).getMessage(key);
	
}
/**
 * Get the static instance of this class that corresponds to the passed property file.
 *
 * @return The resource handler object which can be used to obtain messages.
 */
public static J2EEValidationResourceHandler getHandler(String fileName, Class aClass) {
	return getHandler(fileName,aClass,Locale.getDefault());
}
/**
 * Get the static instance of this class that corresponds to the passed property file.
 *
 * @return The resource handler object which can be used to obtain messages.
 */
public static J2EEValidationResourceHandler getHandler(String fileName, Class aClass, Locale locale) {
	String handlerKey = getHandlerKey(fileName, locale);
	if (handlers.get(handlerKey) == null) {
		J2EEValidationResourceHandler temp = new J2EEValidationResourceHandler(locale);
		temp.setResourceClassLoader(aClass.getClassLoader());
		temp.setBundleName(fileName);
		handlers.put(handlerKey,temp);
	}
	
	return (J2EEValidationResourceHandler)(handlers.get(handlerKey));
}
  private static String getHandlerKey(String _baseName, Locale locale) {
	StringBuffer localeName = new StringBuffer("_").append(locale.toString());//$NON-NLS-1$
	if (locale.toString().equals(""))//$NON-NLS-1$
	  localeName.setLength(0);
	return _baseName + localeName.toString();
  }      
/**
 * Insert the method's description here.
 * Creation date: (1/26/2001 1:04:50 PM)
 * @return java.lang.ClassLoader
 */
public java.lang.ClassLoader getResourceClassLoader() {
	return resourceClassLoader;
}
/**
 * Insert the method's description here.
 * Creation date: (1/26/2001 1:04:50 PM)
 * @param newResourceClassLoader java.lang.ClassLoader
 */
public void setResourceClassLoader(java.lang.ClassLoader newResourceClassLoader) {
	resourceClassLoader = newResourceClassLoader;
}

/**
 * Setter for the locale property.
 *
 * @param The locale.
 */
public void setLocale(Locale locale) {
	this.locale = locale;
}

/**
 * Insert the method's description here.
 * Creation date: (1/5/01 2:26:24 PM)
 * @param newBundleName java.lang.String
 */
public void setBundleName(java.lang.String newBundleName) {
	bundleName = newBundleName;
}


/**
 * Getter for the locale property.
 *
 * @return The locale.
 */
public Locale getLocale() {
	if (locale == null)
		locale = Locale.getDefault();
	return locale;
}

/**
 * Get the message with the specified key.
 *
 * @param key The key for the message to be retrieved.
 */

public String getMessage(String key) {
	String temp = null;
	try {
		temp = getBundle().getString(key);
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
}

/**
 * Get the message template with the specified key, and format the message using
 * the specified Object array.
 *
 * @param key The key for the message to be retrieved.
 * @param arg The Object array containing the substitution values for the message.
 * @return The message string.
 */
public String getMessage(String key, Object arg[]) {
	String temp = null;
	try {
		temp = MessageFormat.format(getBundle().getString(key), arg);
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
}

/**
 * Get the message template with the specified key, and format the message using
 * the specified String argument.
 *
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @return The message string.
 */
public String getMessage(String key, String arg1) {

	String temp = null;
	try {
		if (arg1 != null) {
			Object msgobj[] = new Object[1];
			msgobj[0] = arg1;
			temp = getMessage(key, msgobj);
		} 
		else {
			temp = getMessage(key);
		}
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
}

/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @return The message string.
 */

public String getMessage(String key, String arg1, String arg2) {
	String temp = null;
	try {
		if ((arg1 != null) && (arg2 != null)) {
			Object msgobj[] = new Object[2];
			msgobj[0] = arg1;
			msgobj[1] = arg2;
			temp = getMessage(key, msgobj);
		} 
		else {
			temp = getMessage(key);
		}
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;

}

/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @return The message string.
 */

public String getMessage(String key, String arg1, String arg2, String arg3) {
	String temp = null;
	try {
		if ((arg1 != null) && (arg2 != null) && (arg3 != null)) {
			Object msgobj[] = new Object[3];
			msgobj[0] = arg1;
			msgobj[1] = arg2;
			msgobj[2] = arg3;
			temp = getMessage(key, msgobj);
		} 
		else {
			temp = getMessage(key);
		}
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
	
}

/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @return The message string.
 */

public String getMessage(String key, String arg1, String arg2, String arg3, String arg4) {
	String temp = null;
	try {
		if ((arg1 != null) && (arg2 != null) && (arg3 != null) && (arg4 != null)) {
			Object msgobj[] = new Object[4];
			msgobj[0] = arg1;
			msgobj[1] = arg2;
			msgobj[2] = arg3;
			msgobj[3] = arg4;
			temp = getMessage(key, msgobj);
		} 
		else {
			temp = getMessage(key);
		}
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
}

/**
 * Get the message template with the specified key, and format the message using
 * the specified String arguments.
 *
 * @param key The key for the message to be retrieved.
 * @param arg1 The argument containing the substitution values for the message variable 1.
 * @param arg2 The argument containing the substitution values for the message variable 2.
 * @param arg3 The argument containing the substitution values for the message variable 3.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @param arg4 The argument containing the substitution values for the message variable 4.
 * @param arg5 The argument containing the substitution values for the message variable 5.
 * @return The message string.
 */

public String getMessage(String key,
						 String arg1, 
						 String arg2, 
						 String arg3, 
						 String arg4, 
						 String arg5) {

	String temp = null;
	try {
		if ((arg1 != null) && (arg2 != null) && (arg3 != null) && 
				(arg4 != null) && (arg5 != null) ) {
			Object msgobj[] = new Object[5];
			msgobj[0] = arg1;
			msgobj[1] = arg2;
			msgobj[2] = arg3;
			msgobj[3] = arg4;
			msgobj[4] = arg5;
			temp = getMessage(key, msgobj);
		} 
		else {
			temp = getMessage(key);
		}
	}
	catch (MissingResourceException ex) {
		System.out.println("Resource Exception: " + ex);//$NON-NLS-1$
		temp = "<<Key: " + key + " not found in resource: " + getBundleName() + ">>";//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
	return temp;
	
}
}
