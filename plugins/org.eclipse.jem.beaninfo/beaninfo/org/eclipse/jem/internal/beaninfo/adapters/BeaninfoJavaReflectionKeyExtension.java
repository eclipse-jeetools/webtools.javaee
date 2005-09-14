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
package org.eclipse.jem.internal.beaninfo.adapters;
/*
 *  $RCSfile: BeaninfoJavaReflectionKeyExtension.java,v $
 *  $Revision: 1.7 $  $Date: 2005/09/14 23:30:38 $ 
 */

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMIResource;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.adapters.IJavaReflectionKey;
import org.eclipse.jem.java.adapters.IJavaReflectionKeyExtension;

/**
 * Java Reflection Key extension to retrieve keys for beaninfo creations.
 * 
 * Handles attributes and behaviors.
 * 
 * @version 	1.0
 * @author R. L. Kulp  
 */
public class BeaninfoJavaReflectionKeyExtension implements IJavaReflectionKeyExtension {
	
	// The format of the keys are:
	// behaviors:  "classname/behavior/behaviorname"
	// structural features: "classname/featurename"
	public static final String 
		BEHAVIOR = "/operation/",	// Piece in key that indicates this is a behavior. //$NON-NLS-1$
		EVENT = "/event/",		// Piece in key that indicates this is an event. //$NON-NLS-1$
		FEATURE = "/";			// Piece in key that indicates this is an attribute. //$NON-NLS-1$
	
	/**
	 * Get the object for this key.
	 */
	public Object getObject(String key, IJavaReflectionKey reflectionKey) {
		if (key != null) {
			int ibehave = key.indexOf(BEHAVIOR);
			if (ibehave > -1) {
				// We have a possible behavior.
				String classname = key.substring(0, ibehave);
				int ibehaveName = ibehave+BEHAVIOR.length();
				if (ibehaveName < key.length()) {
					JavaClass javaclass = getJavaClass(reflectionKey, classname);
					if (javaclass != null) {
						javaclass.getEOperations();	// Get the list introspected and populated if not already.
						return reflectionKey.primGet(key);	// It should now be there, 
					}
				}
			} else {
				int ievent = key.indexOf(EVENT);
				if (ievent > -1) {
					// We have a possible event.
					String classname = key.substring(0, ievent);
					int ieventName = ievent+EVENT.length();
					if (ieventName < key.length()) {
						JavaClass javaclass = getJavaClass(reflectionKey, classname);
						if (javaclass != null) {
							javaclass.getEvents();	// Get the list introspected and populated if not already.
							return reflectionKey.primGet(key);	// It should now be there, 
						}
					}
				} else {
					int ifeature = key.indexOf(FEATURE);
					if (ifeature > -1) {
						// We have a possible feature.
						String classname = key.substring(0, ifeature);
						int ifeatureName = ifeature+FEATURE.length();
						if (ifeatureName < key.length()) {
							String feature = key.substring(ifeatureName);
							JavaClass javaclass = getJavaClass(reflectionKey, classname);
							if (javaclass != null) {
								// This is just a hack until we can get ID situation straightened out.
								// Need to cause introspection of the attributes and references.
								javaclass.getEStructuralFeatures();
								Object result = reflectionKey.primGet(key);	// See if it now got added as an id.
								if (result == null) {
									// Well, it could of been added by the diff merge, which as of now can't handle ids.
									// So we will find it within the attributes/references.
									result = findFeature(feature, javaclass.getEReferences());
									if (result == null)
										result = findFeature(feature, javaclass.getEAttributes());									
									if (result instanceof EStructuralFeature) {
										// Need to add it as an id so next time won't come through here.
										((XMIResource) javaclass.eResource()).setID((EObject) result, key);	// So next time it will find it directly.
									}
								}
								return result;
							}
						}
					}
				}
			}
		}
						
		return null;
	}

	private EStructuralFeature findFeature(String featureName, List featureList) {
		Iterator itr = featureList.iterator();		
		while (itr.hasNext()) {
			EStructuralFeature feature = (EStructuralFeature) itr.next();
			if (featureName.equals(feature.getName())) {
				return feature;
			}
		}
		return null;
	}

	protected JavaClass getJavaClass(IJavaReflectionKey reflectionKey, String classname) {
		Object eclass = reflectionKey.primGet(classname);
		if (eclass == null) 
			eclass = reflectionKey.getJavaType(classname);	// Let it create it.
		if (eclass instanceof JavaClass)
			return (JavaClass) eclass;
		else
			return null;
	}

}
