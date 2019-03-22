/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.vm;
/*


 */

import java.beans.*;
/**
 * IndexedPropertyDescriptor equality tester
 */
public class IndexedPropertyDescriptorEquality extends PropertyDescriptorEquality {

	static void INIT() {
		try {
			MAP_EQUALITY.put(IndexedPropertyDescriptor.class, (IndexedPropertyDescriptorEquality.class).getConstructor(new Class[] {PropertyDescriptor.class}));
			MAP_EQUALITY.put(PropertyDescriptor.class, (IndexedPropertyDescriptorEquality.class).getConstructor(new Class[] {PropertyDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}	
	
	public IndexedPropertyDescriptorEquality() {
	}
	
	public IndexedPropertyDescriptorEquality(PropertyDescriptor descr) {
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
		if (fFeature instanceof IndexedPropertyDescriptor) {
			IndexedPropertyDescriptor pd = (IndexedPropertyDescriptor) fFeature;
			
			int hc = pd.getIndexedPropertyType().hashCode();
	
			if (pd.getIndexedReadMethod() != null)
				hc += pd.getIndexedReadMethod().hashCode();
			if (pd.getIndexedWriteMethod() != null)
				hc += pd.getIndexedWriteMethod().hashCode();
			return hashcode*31 + hc;
		} else
			return hashcode;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof FeatureDescriptorEquality))
			return false;
		if (identityTest(obj))
			return true;
		if (fFeature.getClass() != ((FeatureDescriptorEquality) obj).fFeature.getClass())
			return false;	// If they aren't both PropertyDesciptors or IndexedPropertyDescriptors, then they don't match.
		if (!super.equals(obj))
			return false;
 	
		if (fFeature instanceof IndexedPropertyDescriptor) {
			IndexedPropertyDescriptor op = (IndexedPropertyDescriptor) ((FeatureDescriptorEquality) obj).fFeature;
			IndexedPropertyDescriptor fp = (IndexedPropertyDescriptor) fFeature;

			if (op.getIndexedPropertyType() != fp.getIndexedPropertyType())
				return false;
			if (op.getIndexedReadMethod() != fp.getIndexedReadMethod())
				return false;
			if (op.getIndexedWriteMethod() != fp.getIndexedWriteMethod())
				return false; 
		}													
			
		return true;
	}


	

}
