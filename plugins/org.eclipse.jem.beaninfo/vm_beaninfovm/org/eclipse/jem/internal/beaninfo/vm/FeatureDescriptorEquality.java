package org.eclipse.jem.internal.beaninfo.vm;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: FeatureDescriptorEquality.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:45:49 $ 
 */

import java.beans.*;
import java.util.*;
import java.lang.reflect.*;
/**
 * This object is used to test for semantic equality (equals())
 * between feature objects. It is needed because Feature's don't
 * provide a semantic equality, only an identity equality. Need
 * semantic equality so that keys in map can be found equal
 * semantically.
 */

public class FeatureDescriptorEquality {
	protected FeatureDescriptor fFeature;
	
	private HashMap fValues;	// The key/values for this feature. We grab them out
								// so that we don't have to keep re-getting them for equality
								// compares. This is done the first time needed in the equals
								// statement.
								
	private int fHashCode = 0;	// Hashcode of this equality object. The hashcode for the feature is expensive
					// so we will calculate it once (the assumption is that
					// features once created aren't changed, which for our
					// purposes here in beaninfo land is good).
		
	protected static HashMap MAP_EQUALITY = new HashMap(10);	// Map from descriptor to equality type.
	static {
		try {
			MAP_EQUALITY.put(FeatureDescriptor.class, (FeatureDescriptorEquality.class).getConstructor(new Class[] {FeatureDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
		// Need to set up the others.
		BeanDescriptorEquality.INIT();
		EventSetDescriptorEquality.INIT();		
		IndexedPropertyDescriptorEquality.INIT();
		MethodDescriptorEquality.INIT();		
		ParameterDescriptorEquality.INIT();
		PropertyDescriptorEquality.INIT();
	}
			
	/**
	 * Create the appropriate descriptor equality for this object.
	 */
	public static FeatureDescriptorEquality createEquality(FeatureDescriptor descr) {
		try {
			return (FeatureDescriptorEquality) ((Constructor) MAP_EQUALITY.get(descr.getClass())).newInstance(new Object[] {descr});
		} catch (IllegalAccessException e) {
		} catch (InstantiationException e) {
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public FeatureDescriptorEquality() {
	}
	
	/**
	 * NOTE: Every subclass needs to implement an override for the methods:
	 *       calculateHashCode
	 *       equals
	 *       clearFeature - if it has any cache values
	 */
					
	public FeatureDescriptorEquality(FeatureDescriptor feature) {
		setFeature(feature);
	}
	
	public final void setFeature(FeatureDescriptor feature) {
		clearFeature();
		fFeature = feature;
	}
	
	/**
	 * A new feature is being set into this object,
	 * clear any cache members so that they can be reconstructed.
	 *
	 * NOTE: Subclasses - remember to call super.clearFeature();
	 */
	protected void clearFeature() {
		fValues = null;
		fHashCode = 0;
	}
	
	public final int hashCode() {
		if (fHashCode == 0)
			fHashCode = calculateHashCode();
		return fHashCode;
	}

	protected final HashMap values() {
		if (fValues == null) {
			fValues = new HashMap(5);
			
			Enumeration keys = fFeature.attributeNames();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				fValues.put(key, fFeature.getValue(key));
			}
		}
		return fValues;
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
		int hashcode = 0;
		if (fFeature.getName() != null)
			hashcode += fFeature.getName().hashCode();
			
		if (fFeature.getDisplayName() != fFeature.getName())
			hashcode += fFeature.getDisplayName().hashCode();
		if (fFeature.getShortDescription() != fFeature.getDisplayName())
			hashcode += fFeature.getShortDescription().hashCode();			
			
		hashcode += (fFeature.isExpert() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		hashcode += (fFeature.isHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();		
		hashcode += (fFeature.isPreferred() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		
		hashcode += values().hashCode();
		return hashcode;
	}
	
	/**
	 * equals: See if this is equal semantically.
	 *
	 * NOTE: Every subclass needs to implement this and
	 *       the first few lines should be:
	 *         if (identityTest())
	 *           return true;
	 *         if (!super.equals(obj))
	 *           return false;
	 */
	 
	 public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
		if (!(obj instanceof FeatureDescriptorEquality))
			return false;
			
		FeatureDescriptorEquality ofe = (FeatureDescriptorEquality) obj;	 		
	 	FeatureDescriptor of = ofe.fFeature;
	 		
	 	if (fFeature.getClass() != of.getClass())
	 		return false;
	 		
	 	if (!fFeature.getName().equals(of.getName()))
	 		return false;
	 	if (!fFeature.getDisplayName().equals(of.getDisplayName()))
	 		return false;
	 	if (!fFeature.getShortDescription().equals(of.getShortDescription()))
	 		return false;	 
	 	if (fFeature.isExpert() != of.isExpert() ||
		 		fFeature.isHidden() != of.isHidden() ||
		 		fFeature.isPreferred() != of.isPreferred())
	 		return false;
	 	
		if (!values().equals(ofe.values()))
			return false;
		return true;
	 }
	 
	 /*
	  * Identity test: Tests for quick identity of
	  * descriptors. If this returns true, then 
	  * no other tests required.
	  */
	 protected boolean identityTest(Object obj) {
	 	if (!(obj instanceof FeatureDescriptorEquality))
	 		return false;
	 	if (this == obj)
	 		return true;

	 	if (((FeatureDescriptorEquality) obj).fFeature == fFeature)
	 		return true;
	 		
	 	return false;	 	
	 }	
}