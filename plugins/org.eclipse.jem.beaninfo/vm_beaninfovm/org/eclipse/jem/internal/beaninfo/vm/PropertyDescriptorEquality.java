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
 *  $RCSfile: PropertyDescriptorEquality.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/31 20:16:34 $ 
 */

import java.beans.*;
/**
 * PropertyDescriptor equality tester
 */
public class PropertyDescriptorEquality extends FeatureDescriptorEquality {
	
	static void INIT() {
		try {
			MAP_EQUALITY.put(PropertyDescriptor.class, (PropertyDescriptorEquality.class).getConstructor(new Class[] {PropertyDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}	
	
	public PropertyDescriptorEquality() {
	}
	
	public PropertyDescriptorEquality(PropertyDescriptor descr) {
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
		PropertyDescriptor pd = (PropertyDescriptor) fFeature;
		int hc = 0;
		if (pd.getPropertyEditorClass() != null)
			hc += pd.getPropertyEditorClass().hashCode();
		if (pd.getPropertyType() != null)
			hc += pd.getPropertyType().hashCode();
		if (pd.getReadMethod() != null)
			hc += pd.getReadMethod().hashCode();
		if (pd.getWriteMethod() != null)
			hc += pd.getWriteMethod().hashCode();
			
		hc += (pd.isBound() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		hc += (pd.isConstrained() ? Boolean.TRUE : Boolean.FALSE).hashCode();		
						
		return hashcode*31 + hc;
	}

	public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
			
		if (!super.equals(obj))
			return false;
 	
		PropertyDescriptor op = (PropertyDescriptor) ((FeatureDescriptorEquality) obj).fFeature;
		PropertyDescriptor fp = (PropertyDescriptor) fFeature;
 		
		if (op.getPropertyEditorClass() != fp.getPropertyEditorClass())
			return false;
		if (op.getReadMethod() != fp.getReadMethod())
			return false;
		if (op.getWriteMethod() != fp.getWriteMethod())
			return false;
		if (op.isBound() != fp.isBound())
			return false;				
		if (op.isConstrained() != fp.isConstrained())
			return false;														
			
		return true;
	}


}