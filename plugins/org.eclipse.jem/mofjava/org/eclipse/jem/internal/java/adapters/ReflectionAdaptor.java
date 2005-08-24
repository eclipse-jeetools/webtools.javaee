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
package org.eclipse.jem.internal.java.adapters;
/*
 *  $RCSfile: ReflectionAdaptor.java,v $
 *  $Revision: 1.12 $  $Date: 2005/08/24 20:20:25 $ 
 */
import java.util.logging.Level;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;


import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
import org.eclipse.jem.util.logger.proxy.Logger;
/**
 * ReflectionAdaptor - a read adaptor base implementation which does a bulk
 * 	load of relflected values on the first request.
 * 	Subclasses can optimize to defer some properties.
 * 	Properties may also be deferred by setting their values with proxy references,
 * 	for example, for supertype and other referenced types.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public abstract class ReflectionAdaptor extends org.eclipse.emf.common.notify.impl.AdapterImpl implements ReadAdaptor {
	public static final char C_CLASS_MEMBER_DELIMITER = '.';
	public static final char C_METHOD_PARM_DELIMITER = '(';
	public static final char C_METHODID_PARMID_DELIMITER = '-';
	public static final char C_PARM_PARM_DELIMITER = ',';
	public static final char PATH_DELIMITER = '/';
	// SW id contains & in xml file casues exception throw during load
        // public static final String S_CONSTRUCTOR_TOKEN = "&V";//$NON-NLS-1$
	public static final String S_CONSTRUCTOR_TOKEN = "_V";//$NON-NLS-1$ // SW
	// cache a static empty String[] for no parm methods
	protected static String[] emptyStringArray = new String[0];
	
	
	/**
	 * Notification event type for special Reflection notifications. This will be the event type for the special ones, like REFLECTION_EVENT.
	 * The listener should listen for this event type, and then check the feature for the type of special event (like REFLECTION_EVENT).
	 * @since 1.1.0
	 */
	public static final int EVENT = -3456;	// Using a funny number to try to eliminate confliction with any other specials that may of occur from other code on the JavaClass.
	
	/**
	 * Special notification event type. This is sent against a JavaClass (as the target) whenever reflection occurs. It will be
	 * sent under the notification event type of REFLECTION_EVENT.
	 * @since 1.1.0
	 */
	public static final EAttribute REFLECTION_EVENT = EcoreFactory.eINSTANCE.createEAttribute();

	/*
	 * Fill in the name. Not really needed but it would be nice.
	 */
	static {REFLECTION_EVENT.setName("reflectValues");} //$NON-NLS-1$
	
	protected boolean hasReflected = false;
	protected boolean isReflecting = false;
public ReflectionAdaptor() {
	super();
}
public ReflectionAdaptor(Notifier target) {
	super();
	setTarget(target);
}
/**
 * Helper method to ensure full initialization of the target.  Required
 * for serialization.
 */
public static void forceDeferredReadFor(EObject target) {
	ReflectionAdaptor adaptor = retrieveAdaptorFrom(target);
	if (adaptor != null) {
		adaptor.reflectValuesIfNecessary();
	}
}
protected Resource getTargetResource() {
	if (getTarget() != null)
		return ((org.eclipse.emf.ecore.EObject) getTarget()).eResource();
	return null;
}
/**
 * Helper method to fetch the adaptor from the object, and if it exists, get the adapted
 * value for the attribute.  Overloaded for many-sided attributes where the return value would
 * otherwise be an enumeration; in this case will return an Array instead. 
 */
public static Object getValue(EObject object, EReference attribute) {	
//FB	ReflectionAdaptor adaptor = retrieveAdaptorFrom(object);
//FB	if (adaptor != null)
//FB		return adaptor.getValueIn(object, attribute);
//FB	return ((IReadAdaptable) object).primRefValue(attribute);
	return object.eGet(attribute); //FB
	
}
/*Helper method to fetch the adaptor from the object, and if it exists, get the adapted
 *value for the attribute.
 */
public static Object getValue(EObject object, EObject attribute) {	
//FB	ReflectionAdaptor adaptor = retrieveAdaptorFrom(object);
//FB	if (adaptor != null)
//FB		return adaptor.getValueIn(object, attribute);
//FB	return ((IReadAdaptable) object).primRefValue(attribute);
	return object.eGet((EStructuralFeature)attribute); //FB
}
/**
 * getValueIn method comment.
 */
public Object getValueIn(EObject object, EObject attribute) {
//FB	reflectValuesIfNecessary();
//FB	return ((IReadAdaptable) object).primRefValue(attribute);
	return object.eGet((EStructuralFeature)attribute); //FB
}
/**
 * isAdaptorForType method comment.
 */
public boolean isAdapterForType(Object type) {
	return (type == ReadAdaptor.TYPE_KEY);
}
/**
 * reflectValues - template method, subclasses override to pump values into target
 */
public abstract boolean reflectValues();
/**
 * Return a boolean indicating whether reflection had occurred.
 */
public synchronized boolean reflectValuesIfNecessary() {
	if (!hasReflected && !isReflecting) {
		try {
			isReflecting = true;
			if (!((EObject)getTarget()).eIsProxy())
				hasReflected = reflectValues();
			else
				hasReflected = false;	// AS long we are a proxy, we won't reflect.
		} catch (Throwable e) {
			hasReflected = false;
			Logger logger = Logger.getLogger();
			if (logger.isLoggingLevel(Level.WARNING)) {
				logger.log(ResourceHandler.getString("Failed_reflecting_values_ERROR_"), Level.WARNING); //$NON-NLS-1$ = "Failed reflecting values!!!"
				logger.logWarning(e);
			}
		} finally {
			isReflecting = false;
			getTarget().eNotify(new ENotificationImpl((InternalEObject)getTarget(), EVENT, REFLECTION_EVENT, null, null, Notification.NO_INDEX));
		}
	}
	return hasReflected;
}
public static ReflectionAdaptor retrieveAdaptorFrom(EObject object) {
	synchronized (object) {
		return (ReflectionAdaptor)EcoreUtil.getRegisteredAdapter(object, ReadAdaptor.TYPE_KEY);
	}
}
}




