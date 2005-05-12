/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation.base;
/*
 *  $RCSfile: FeatureValueProvider.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/12 22:17:05 $ 
 */

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Implementers of this interface (an EMF EObject subclass) provide a visiter
 * capability to the set features on the EObject. This way only the set features
 * are presented to the visitor. This can save a lot of time.
 * 
 * @since 1.1.0
 */
public interface FeatureValueProvider {
	
	/**
	 * A helper class for FeatureValueProvider.
	 * 
	 * @see FeatureValueProviderHelper#visitSetFeatures(EObject, Visitor)
	 * 
	 * @since 1.1.0
	 */
	public static class FeatureValueProviderHelper {
		
		/**
		 * A helper to handle where the object may or may not be
		 * implement FeatureValueProvider. This way it can be a
		 * common access to do it.
		 * 
		 * @param eobject
		 * @param visitor
		 * @return
		 * 
		 * @since 1.1.0
		 */
		public static Object visitSetFeatures(EObject eobject, Visitor visitor) {
			FeatureValueProvider fvp;
			try {
				fvp = (FeatureValueProvider) eobject;
			} catch (ClassCastException e) {
				// Not a FeatureValueProvider, so do normal.
				Iterator features = eobject.eClass().getEAllStructuralFeatures().iterator();
				while(features.hasNext()){
					EStructuralFeature sf = (EStructuralFeature)features.next();
					if(eobject.eIsSet(sf)){
						Object result = visitor.isSet(sf, eobject.eGet(sf));
						if (result != null)
							return result;
					}
				}
				return null;
			}
			// We run it outside of the class cast block so in caise the visit itself
			// throws a class cast, that one can go on out from here.
			return fvp.visitSetFeatures(visitor);
		}
		
		private FeatureValueProviderHelper() {
		}
	}
	
	/**
	 * The interface for the visiter callback.
	 * 
	 * @since 1.1.0
	 */
	public interface Visitor{
		/**
		 * Called for each set feature on the FeatureValueProvider.
		 * 
		 * @param feature
		 * @param value
		 * @return <code>null</code> to continue to next setting, or a value to stop visiting and return that value be the real exception.
		 * @since 1.1.0
		 */
		Object isSet(EStructuralFeature feature, Object value);
	}	

	/**
	 * Visit the set features.
	 * @param aVisitor
	 * @param <code>null</code> if all settings visited, or the value returned from the visit (isSet) that returned a non-nullSe.
	 * @since 1.1.0
	 */
	public Object visitSetFeatures(Visitor aVisitor);
}
