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
 *  $RCSfile: MethodDescriptorEquality.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:45:49 $ 
 */

import java.beans.*;
import java.util.*;
/**
 * Equality tester for MethodDescriptors
 */
public class MethodDescriptorEquality extends FeatureDescriptorEquality {
	
	static void INIT() {
		try {
			MAP_EQUALITY.put(MethodDescriptor.class, (MethodDescriptorEquality.class).getConstructor(new Class[] {MethodDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}	
	
	private ArrayList fParameterDescriptors;	// Array of ParameterDescriptorEquality's.
	
	public MethodDescriptorEquality() {
	}
	
	public MethodDescriptorEquality(MethodDescriptor descr) {
		super(descr);
	}
	
	/**
	 * A new feature is being set into this object,
	 * clear any cache members so that they can be reconstructed.
	 *
	 * NOTE: Subclasses - remember to call super.clearFeature();
	 */
	protected void clearFeature() {
		super.clearFeature();
		fParameterDescriptors = null;
	}	
	
	protected ArrayList parameterDescriptors() {
		if (fParameterDescriptors == null) {
			ParameterDescriptor[] pds = ((MethodDescriptor) fFeature).getParameterDescriptors();
			if (pds != null) {			
				fParameterDescriptors = new ArrayList(pds.length);
				for (int i=0; i<pds.length; i++)
					fParameterDescriptors.add(new ParameterDescriptorEquality(pds[i]));
			}
		}
		return fParameterDescriptors;
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
		MethodDescriptor bd = (MethodDescriptor) fFeature;
		int hc = bd.getMethod().hashCode();
		if (parameterDescriptors() != null)
			hc += parameterDescriptors().hashCode(); 

		return hashcode*31 + hc;
	}

	public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
			
		if (!super.equals(obj))
			return false;

		MethodDescriptorEquality oem = (MethodDescriptorEquality) obj;
		MethodDescriptor om = (MethodDescriptor) oem.fFeature;
		MethodDescriptor fm = (MethodDescriptor) fFeature;
 		
		if (fm.getMethod() != om.getMethod())
			return false;
				
		if (fParameterDescriptors != null || oem.fParameterDescriptors != null) {
			// We are in a Map lookup situation, so one side has listener method equalities, so we will compare that way.
			if (parameterDescriptors() == null)
				if (((MethodDescriptorEquality) obj).parameterDescriptors() != null)
					return false;
				else ;
			else
				if (!parameterDescriptors().equals(((MethodDescriptorEquality) obj).parameterDescriptors()))
					return false;
		} else {
			// We are in the building the list phases, don't waste space building entire list.
			ParameterDescriptor[] ours = fm.getParameterDescriptors();
			ParameterDescriptor[] theirs = om.getParameterDescriptors();
			if (ours == theirs)
				return true;
			else if (ours == null)
				if (theirs != null)
					return false;
				else
					;
			else if (theirs == null)
				return false;
			else if (ours.length != theirs.length)
				return false;
			else if (ours.length > 0) {
				ParameterDescriptorEquality workingOurs = new ParameterDescriptorEquality();
				ParameterDescriptorEquality workingThiers = new ParameterDescriptorEquality();
				for (int i = 0; i < ours.length; i++) {
					workingOurs.setFeature(ours[i]);
					workingThiers.setFeature(theirs[i]);
					if (!workingOurs.equals(workingThiers))
						return false;
				}
			}			
		}
				
 			
		return true;
	}
}