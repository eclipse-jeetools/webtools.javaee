/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters;
/*
 *  $RCSfile: JavaReflectionAdaptor.java,v $
 *  $Revision: 1.10 $  $Date: 2005/02/09 19:04:04 $ 
 */
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;


import org.eclipse.jem.java.*;
import org.eclipse.jem.util.logger.proxy.Logger;
	
/**
 * 
 */
public abstract class JavaReflectionAdaptor extends ReflectionAdaptor {
	private static final String C_METHOD_DEFAULT_CTOR = String.valueOf(C_METHOD_PARM_DELIMITER) + S_CONSTRUCTOR_TOKEN;
	protected static final String LEFT_BRACKET = "[";//$NON-NLS-1$
	
	/**
	 * Special notification event type. This is sent against a JavaClass (as the target) whenever flush of the reflection occurs. It will be
	 * sent under the notification event type of REFLECTION_EVENT.
	 * @since 1.1.0
	 */
	public static final EAttribute FLUSH_REFLECTION_EVENT = EcorePackage.eINSTANCE.getEcoreFactory().createEAttribute();
	
	/**
	 * Special notification event type. This is sent against a JavaClass (as the target) whenever flush of a new class (i.e. 
	 * no source was found) of the reflection occurs. It will be
	 * sent under the notification event type of REFLECTION_EVENT.
	 * @since 1.1.0
	 */
	public static final EAttribute FLUSH_NEW_REFLECTION_EVENT = EcorePackage.eINSTANCE.getEcoreFactory().createEAttribute();

	/*
	 * Fill in the name. Not really needed but it would be nice.
	 */
	static {
		FLUSH_REFLECTION_EVENT.setName("flushReflectedValues");    //$NON-NLS-1$
		FLUSH_NEW_REFLECTION_EVENT.setName("flushNewReflectedValues"); //$NON-NLS-1$
	}
	protected boolean hasFlushed = false;
	protected boolean isFlushing = false;
/**
 * JavaReflectionAdapter constructor comment.
 */
public JavaReflectionAdaptor() {
	super();
}
/**
 * JavaReflectionAdapter constructor comment.
 * @param target org.eclipse.emf.common.notify.Notifier
 */
public JavaReflectionAdaptor(org.eclipse.emf.common.notify.Notifier target) {
	super(target);
}
/**
 * createBlock - instantiate a Block containing the passed source
 */
public Block createBlock(String name, String sourceString) {
	Block newBlock = getJavaFactory().createBlock();
	newBlock.setName(name + "_" + "block");//$NON-NLS-2$//$NON-NLS-1$
	newBlock.setSource(sourceString);
	return newBlock;
}
/**
 * setSuper - set our supertype here, implemented interface are handled separately
 */
public JavaClass createJavaClassRef(String targetName) {
	JavaClass ref = org.eclipse.jem.java.impl.JavaRefFactoryImpl.getActiveFactory().createJavaClass();
   JavaURL javaurl = new JavaURL(targetName);
  ((InternalEObject) ref).eSetProxyURI(URI.createURI(javaurl.getFullString()));
   return ref;
}
/**
 * createJavaParameter - instantiate a Java Parameter based on the passed name and type name (a simple name, NOT A SIGNATURE!!!)
 * The id for a parameter has to be complex in order to be parsable into class, method, and parm.
 * It is created by appending the parm name to the method id, with a known separator.
 * It will look something like "Foo.doSomething(java.lang.Integer-arg0"
 */
public JavaParameter createJavaParameter(Method parentMethod, String parmName, String parmTypeName) {
	JavaParameter newParm = getJavaFactory().createJavaParameter();
	if (parmName!=null)
	   newParm.setName(parmName);
// ((InternalEObject)newParm).eSetID(parentMethod.eID() + C_METHODID_PARMID_DELIMITER + parmName);
	String classRefString = parmTypeName;
	newParm.setEType(createJavaClassRef(classRefString));
	return newParm;
}
/**
 * This method will return a List of dimensions for a typeName.
 * For example "foo[][]" would return a List of Integers
 * 1, 1.  At some point we may want to actually capture the size
 * for Fields but we would need the initializer source to determine that.
 */
public List getArrayDimensions(String typeName) {
	List dimensions = new java.util.ArrayList();
	if (typeName != null) {
		int begin = 0;
		int index = -1;
		while (begin < typeName.length()) {
			index = typeName.indexOf(LEFT_BRACKET, begin);
			if (index > -1) {
				dimensions.add(new Integer(1));
				begin = index + 1;
			} else {
				begin = typeName.length();
			}
		}
	}
	return dimensions;
}
/* Get the Java Factory
 */
protected static JavaRefFactory getJavaFactory() {
	return ((org.eclipse.jem.java.JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jem.java.JavaRefPackage.eNS_URI)).getJavaRefFactory();
}
public abstract Object getReflectionSource();
/**
 * getTypeNamesFromMethodUUID - Pull the parm type names out of a method ID
 * 		It will be in the form: "simpleclass.methodName(com.fronk.Parm1_type,parm2type"
 */
protected static String[] getTypeNamesFromMethodID(String methodID) {
	if (methodID.charAt(methodID.length()-1) == C_METHOD_PARM_DELIMITER || methodID.endsWith(C_METHOD_DEFAULT_CTOR))
		return emptyStringArray;
		
	// Count the parms first.  The number of parms is the number of occurrences of ',' + 1
	int numParms = 1;
	int pos = -1;
	// Skip the '.' after classname
	pos = methodID.indexOf(C_CLASS_MEMBER_DELIMITER, ++pos);
	// Look for the start of the parms
	int parmPos = methodID.indexOf(C_METHOD_PARM_DELIMITER, ++pos);
	pos = parmPos;
	while ((pos = methodID.indexOf(C_PARM_PARM_DELIMITER, ++pos)) != -1)
		numParms++;
	String[] parmTypeNames = new String[numParms];
	// now collect the parm names
	// skip the method name
	pos = parmPos;
	int i = 0, end;
	do {
		end = methodID.indexOf(C_PARM_PARM_DELIMITER, pos + 1);
		// This is the last parameter, we may need to strip a trailing &V for a constructor
		if (end == -1)
			end = methodID.indexOf(S_CONSTRUCTOR_TOKEN, pos + 1);
		// otherwise take the rest of the ID
		if (end == -1)
			end = methodID.length();
		parmTypeNames[i++] = methodID.substring(pos + 1, end);
	} while ((pos = methodID.indexOf(C_PARM_PARM_DELIMITER, ++pos)) != -1);
	return parmTypeNames;
}
public abstract boolean hasCachedReflectionSource();

public boolean hasReflectionSource() {
    return getReflectionSource() != null;
}

/**
 * Subclasses should override.
 *
 */
public void releaseSourceType(){
}

/**
 * Subclasses should override.
 * @return
 */
public Notification releaseSourceTypeNoNotification() {
	return null;
}

public static void releaseSourceType(JavaClass javaClass) {
	if (javaClass == null)
		return;

	JavaReflectionAdaptor existing = (JavaReflectionAdaptor) retrieveAdaptorFrom(javaClass);
	if (existing != null)
		existing.releaseSourceType();
}
/*
 * This method is called by a Field Adaptor to set the type of aField
 * to be aTypeName.  aTypeName may contain array brackets which need
 * to be detected in order to set the array dimensions on aField.
 */
protected void setFieldType(Field aField, String aTypeName) {
	if (aField != null && aTypeName != null) {
		String classRefString = aTypeName;
		aField.setEType(createJavaClassRef(classRefString));
	}
}
public final boolean flushReflectedValuesIfNecessary() {
	return flushReflectedValuesIfNecessary(false);
}
public final boolean flushReflectedValuesIfNecessary(boolean clearCachedModelObject) {
	Notification not = flushReflectedValuesIfNecessaryNoNotification(clearCachedModelObject);
	if (not != null)
		getTarget().eNotify(not);
	return hasFlushed;
}

public synchronized Notification flushReflectedValuesIfNecessaryNoNotification(boolean clearCachedModelObject) {
	if (!hasFlushed && !isFlushing) {
		boolean isExisting = hasCachedReflectionSource();
		try {
			isFlushing = true;
			hasReflected = false;
			hasFlushed = flushReflectedValues(clearCachedModelObject);
		} catch (Throwable e) {
			hasFlushed = false;
			Logger.getLogger().log(e);
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else if (e instanceof Error)
				throw (Error) e;
			else
				throw new RuntimeException(e.getMessage());
		} finally {
			isFlushing = false;
			postFlushReflectedValuesIfNecessary(isExisting);
		}
		return createFlushNotification(isExisting);
	}
	return null;
}

/**
 * @param isExisting
 * @return
 */
protected Notification createFlushNotification(boolean isExisting) {
	EStructuralFeature feature = isExisting ? FLUSH_REFLECTION_EVENT : FLUSH_NEW_REFLECTION_EVENT;
	return new ENotificationImpl((InternalEObject)getTarget(),EVENT, feature, null, null);
}
protected void postFlushReflectedValuesIfNecessary(boolean isExisting) {
}
/**
 * Subclasses should override to perform the actual clearing of the values.
 */
protected boolean flushReflectedValues(boolean clearCachedModelObject) {
	return true;
}
/**
 * Return a boolean indicating whether reflection had occurred.
 */
public boolean reflectValuesIfNecessary() {
	if (isFlushing)
		return false;
	return super.reflectValuesIfNecessary();
}

/**
 * reflectValues - template method, subclasses override to pump values into target
 */
public boolean reflectValues() {
	hasFlushed = false;
	return true;
}
}




