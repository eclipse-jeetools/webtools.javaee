package org.eclipse.jem.internal.beaninfo.vm;
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
 *  $RCSfile: IndexedPropertyDescriptorEquality.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/31 20:16:34 $ 
 */

import java.beans.*;
/**
 * IndexedPropertyDescriptor equality tester
 */
public class IndexedPropertyDescriptorEquality extends PropertyDescriptorEquality {

	static void INIT() {
		try {
			MAP_EQUALITY.put(IndexedPropertyDescriptor.class, (IndexedPropertyDescriptorEquality.class).getConstructor(new Class[] {IndexedPropertyDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}	
	
	public IndexedPropertyDescriptorEquality() {
	}
	
	public IndexedPropertyDescriptorEquality(IndexedPropertyDescriptor descr) {
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
		IndexedPropertyDescriptor pd = (IndexedPropertyDescriptor) fFeature;
		
		int hc = pd.getIndexedPropertyType().hashCode();

		if (pd.getIndexedReadMethod() != null)
			hc += pd.getIndexedReadMethod().hashCode();
		if (pd.getIndexedWriteMethod() != null)
			hc += pd.getIndexedWriteMethod().hashCode();		
						
		return hashcode*31 + hc;
	}

	public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
		if (!super.equals(obj))
			return false;
 	
		IndexedPropertyDescriptor op = (IndexedPropertyDescriptor) ((FeatureDescriptorEquality) obj).fFeature;
		IndexedPropertyDescriptor fp = (IndexedPropertyDescriptor) fFeature;
 		
		if (op.getIndexedPropertyType() != fp.getIndexedPropertyType())
			return false;
		if (op.getIndexedReadMethod() != fp.getIndexedReadMethod())
			return false;
		if (op.getIndexedWriteMethod() != fp.getIndexedWriteMethod())
			return false;													
			
		return true;
	}


	

}