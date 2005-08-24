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
package org.eclipse.jem.internal.beaninfo.vm;
/*
 *  $RCSfile: BeanDescriptorEquality.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:31:29 $ 
 */

import java.beans.*;
/**
 * Equality tester for BeanDescriptors
 */
public class BeanDescriptorEquality extends FeatureDescriptorEquality {
	static void INIT() {
		try {
			MAP_EQUALITY.put(BeanDescriptor.class, (BeanDescriptorEquality.class).getConstructor(new Class[] {BeanDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}
	
	/**
	 * Constructor for BeanDescriptorEquality.
	 */
	public BeanDescriptorEquality() {
		super();
	}


	public BeanDescriptorEquality(BeanDescriptor descr) {
		super(descr);
	}
	
	/**
	 * Calculate the hashcode for the current feature, add this
	 * to the hashcode received from super.calculateHashCode
	 * and return the new value.
	 *
	 * NOTE: for subclasses, it is MANDITORY that the first line be:
	 *         int hashcode = super.calculateHashCode();
	 *       and the last line be:
	 *         return hashcode*31 + (your calculated hashcode for just this subclass);
	 */
	protected int calculateHashCode() {
		int hashcode = super.calculateHashCode();
		BeanDescriptor bd = (BeanDescriptor) fFeature;
		int hc = bd.getBeanClass().hashCode();
		if (bd.getCustomizerClass() != null)
			hc += bd.getCustomizerClass().hashCode();
			
		return hashcode*31 + hc;
	}
	 
	public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
		if (!super.equals(obj))
			return false;
 	
		BeanDescriptor ob = (BeanDescriptor) ((FeatureDescriptorEquality) obj).fFeature;
		BeanDescriptor fb = (BeanDescriptor) fFeature;
 		
		if (ob.getBeanClass() != fb.getBeanClass())
			return false;
		if (ob.getCustomizerClass() != fb.getCustomizerClass())
			return false;
 			
		return true;
	}
}
