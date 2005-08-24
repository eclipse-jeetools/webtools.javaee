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
 *  $RCSfile: PropertyDescriptorEquality.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:31:29 $ 
 */

import java.beans.*;
/**
 * PropertyDescriptor equality tester
 */
public abstract class PropertyDescriptorEquality extends FeatureDescriptorEquality {	
	
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
