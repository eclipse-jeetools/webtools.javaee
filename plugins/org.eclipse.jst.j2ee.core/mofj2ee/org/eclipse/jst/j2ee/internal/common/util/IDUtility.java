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
package org.eclipse.jst.j2ee.internal.common.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emf.utilities.IDUtil;


/**
 * Utility class for generating and setting unique id's for ref objects contained within a resource.  This is needed
 * sometimes when a refobject is referenced by an object in a separate resource.  This utility will force the refobject 
 * to notify after the id gets set, using the static feature on this class.  The object must be contained in a resource
 * for the utility methods to work.
 */
public class IDUtility {

/**
 * IDUtility constructor comment.
 */
public IDUtility() {
	super();
}
/**
 * Generate a default id and notify.
 */
protected static String primSetDefaultID(EObject object) {
	return IDUtil.getOrAssignID(object);
}
	
/**
 * Generate a default id and notify.
 * Helper for default id generation to support cross document references to non xmi resources
 * (eg, xml deployment descriptors); called from overriden accessors in selected objects;  
 */
public static String setDefaultID(EObject object) {
	if (object == null || object.eResource() == null)
		return null;
	return primSetDefaultID(object);
}
	
/**
 * Generate a default id and notify, for this object and for all referenced objects that are contained
 * within the same resource.  Helper for default id generation to support cross document references to non xmi resources
 * (eg, xml deployment descriptors); useful for post copy/add  
 */
public static String setDefaultID(EObject object, boolean recurseChildren) {
	if (object == null || object.eResource() == null)
		return null;
		
	String result = primSetDefaultID(object);

	if (recurseChildren)
		setDefaultIDForChildren(object);

	return result;
}
	
protected static void setDefaultIDForChildren(EObject object) {

	List objects = object.eContents();
	for (int i = 0; i < objects.size(); i++){
		EObject o = (EObject)objects.get(i);
		primSetDefaultID(o);
	}
}
}

