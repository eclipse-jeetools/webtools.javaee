package org.eclipse.jem.internal.java.adapters;
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
 *  $RCSfile: JavaReflectionAdapterFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:16:21 $ 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.jem.java.JavaRefPackage;
public abstract class JavaReflectionAdapterFactory extends AdapterFactoryImpl {
	// Maintain a mapping of the source objects to the adaptors which have
	// relfected from them.  This allows a flush operation to force those
	// adapters to re-reflect.
	protected HashMap reflected = new HashMap();
	protected static final String TYPE_NAME = ReadAdaptor.TYPE_KEY;
	protected boolean isBusyIteratingReflected = false;
	protected Map registerQueue;
	protected List unregisterQueue;
/**
 * JavaReflectionAdapterFactory constructor comment.
 */
public JavaReflectionAdapterFactory() {
	super();
}
public Adapter createAdapter(Notifier target) {
	ReflectionAdaptor adapter = null;
	EObject object = (EObject) target;
	JavaRefPackage pack = getJavaRefPackage();
	if (object.eClass() == pack.getJavaClass())
		adapter = createJavaClassAdaptor(target);
	else
		if (object.eClass() == pack.getArrayType())
			adapter = createArrayTypeAdaptor(target);
		else
			if (object.eClass() == pack.getMethod())
				adapter = createJavaMethodAdaptor(target);
			else
				if (object.eClass() == pack.getField())
					adapter = createJavaFieldAdaptor(target);
	return adapter;
}
protected ReflectionAdaptor createArrayTypeAdaptor(Notifier target) {
	// temporary implementation, placeholder to later optimize
	// Array reflection.  For now, do class reflection.
	return createJavaClassAdaptor(target);
}
protected abstract ReflectionAdaptor createJavaClassAdaptor(Notifier target) ;
protected abstract ReflectionAdaptor createJavaFieldAdaptor(Notifier target) ;
protected abstract ReflectionAdaptor createJavaMethodAdaptor(Notifier target) ;
// Flush the adapter for a source object
public Notification disAssociateSource(String source) {
	return disAssociateSource(source, true);
}

public Notification disAssociateSource(String source, boolean doNotify) {
	JavaReflectionAdaptor a = (JavaReflectionAdaptor) reflected.get(source);
	Notification notification = null;
	if (a != null) {
		if (doNotify)
			a.releaseSourceType();
		else
			notification = a.releaseSourceTypeNoNotification();
	}
	return notification;
}
public void flushAll() {}
// Flush the adapter for a source object
public void flushReflection(String source) {}
protected JavaRefPackage getJavaRefPackage() {
	return (JavaRefPackage) EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);
}
public void registerReflection(String source, ReflectionAdaptor adapter) {
	if (isBusyIteratingReflected) {
		if (registerQueue == null)
			registerQueue = new HashMap();
		registerQueue.put(source, adapter);
	} else
		reflected.put(source, adapter);
}
public void unregisterReflection(String source) {
	if (isBusyIteratingReflected) {
		if (unregisterQueue == null)
			unregisterQueue = new ArrayList();
		unregisterQueue.add(source);
	} else
		reflected.remove(source);
}
public void disAssociateSourcePlusInner(String source) {
	disAssociateSourcePlusInner(source, true);
}

public Notification disAssociateSourcePlusInner(String source, boolean doNotify) {
	isBusyIteratingReflected = true;
	Notification notification = null;
	try {
		String innerName = source + '$';
		Iterator it = reflected.entrySet().iterator();
		Map.Entry entry;
		String key;
		JavaReflectionAdaptor adaptor;
		while (it.hasNext()) {
			entry = (Map.Entry) it.next();
			key = (String) entry.getKey();
			if (key.equals(source) || key.startsWith(innerName)) {
				adaptor = (JavaReflectionAdaptor) reflected.get(key);
				if (adaptor != null) {
					if (doNotify)
						adaptor.releaseSourceType();
					else
						notification = adaptor.releaseSourceTypeNoNotification();
				}
			}
		}
	} finally {
		finishedIteratingReflected();
	}
	return notification;
}

protected void finishedIteratingReflected() {
	isBusyIteratingReflected = false;
	if (unregisterQueue != null && !unregisterQueue.isEmpty()) {
		for (int i = 0; i < unregisterQueue.size(); i++) {
			reflected.remove(unregisterQueue.get(i));
		}
		unregisterQueue.clear();
	}
	if (registerQueue != null && !registerQueue.isEmpty()) {
		Iterator it = registerQueue.entrySet().iterator();
		Map.Entry entry;
		while (it.hasNext()) {
			entry = (Map.Entry) it.next();
			reflected.put(entry.getKey(), entry.getValue());
		}
		registerQueue.clear();
	}
}
}








