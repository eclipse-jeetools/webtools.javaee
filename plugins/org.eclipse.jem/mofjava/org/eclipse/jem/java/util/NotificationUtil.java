/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: NotificationUtil.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/04 23:11:33 $ 
 */
package org.eclipse.jem.java.util;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor;
import org.eclipse.jem.internal.java.adapters.ReflectionAdaptor;
 

/**
 * This is a utility class of static methods for handling special JEM notifications.
 * 
 * @since 1.1.0
 */
public class NotificationUtil {
	/*
	 * Not meant to be constructed.
	 */
	private NotificationUtil() {
		
	}
	
	/**
	 * Answer whether this notification is a reflection notification. If it is, then it means that the JavaClass has
	 * just had reflection performed on it.
	 * @param msg
	 * @return <code>true</code> if this is a reflection event.
	 * 
	 * @since 1.1.0
	 */
	public static boolean isReflectionEvent(Notification msg) {
		return msg.getEventType() == ReflectionAdaptor.EVENT && msg.getFeature() == ReflectionAdaptor.REFLECTION_EVENT;
	}
	
	/**
	 * Answer whether this notification is a reflection flush event of a JavaClass with existing code. If it is, then it means that the 
	 * JavaClass has just had the reflection results flushed, AND this was a class that had either source or binary code on it..
	 * @param msg
	 * @return <code>true</code> if this is a flush event.
	 * 
	 * @since 1.1.0
	 */
	public static boolean isFlushEvent(Notification msg) {
		return msg.getEventType() == ReflectionAdaptor.EVENT && msg.getFeature() == JavaReflectionAdaptor.FLUSH_REFLECTION_EVENT;
	}

	/**
	 * Answer whether this notification is a reflection flush event of a JavaClass that is new and doesn't have existing code.
	 * If it is, then it means that the JavaClass has just had the reflection results flushed, AND this was a class that was new and
	 * had no code behind it.
	 * @param msg
	 * @return <code>true</code> if this is a flush event of new class.
	 * 
	 * @since 1.1.0
	 */
	public static boolean isFlushNewEvent(Notification msg) {
		return msg.getEventType() == ReflectionAdaptor.EVENT && msg.getFeature() == JavaReflectionAdaptor.FLUSH_NEW_REFLECTION_EVENT;
	}

}
