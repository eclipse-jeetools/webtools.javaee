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
 *  $Revision: 1.4 $  $Date: 2005/05/11 19:01:16 $ 
 */

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
	Object visitSetFeatures(Visitor aVisitor);
}
