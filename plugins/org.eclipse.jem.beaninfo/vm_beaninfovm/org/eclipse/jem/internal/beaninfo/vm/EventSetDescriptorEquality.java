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
 *  $RCSfile: EventSetDescriptorEquality.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:31:29 $ 
 */

import java.beans.*;
import java.util.*;
import java.lang.reflect.Method;
/**
 * Equality tester for EventSetDescriptors
 */
public class EventSetDescriptorEquality extends FeatureDescriptorEquality {
	
	static void INIT() {
		try {
			MAP_EQUALITY.put(EventSetDescriptor.class, (EventSetDescriptorEquality.class).getConstructor(new Class[] {EventSetDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}
	
	private ArrayList fListenerMethodDescriptors;	// Array of MethodDescriptorEquality's.
	
	/**
	 * Constructor for EventSetDescriptorEquality.
	 */
	public EventSetDescriptorEquality() {
		super();
	}


	public EventSetDescriptorEquality(EventSetDescriptor descr) {
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
		fListenerMethodDescriptors = null;
	}
		
	protected ArrayList listenerMethodDescriptors() {
		if (fListenerMethodDescriptors == null) {
			MethodDescriptor[] mds = ((EventSetDescriptor) fFeature).getListenerMethodDescriptors();
			fListenerMethodDescriptors = new ArrayList(mds.length);
			for (int i=0; i<mds.length; i++)
				fListenerMethodDescriptors.add(new MethodDescriptorEquality(mds[i]));
		}
		return fListenerMethodDescriptors;
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
		EventSetDescriptor bd = (EventSetDescriptor) fFeature;
		int hc = bd.getAddListenerMethod().hashCode();
		Method[] methods = bd.getListenerMethods();
		int mhc = 0;
		for (int i=0; i<methods.length; i++)
			mhc = mhc*31 + methods[i].hashCode();
		hc += mhc;
		hc += listenerMethodDescriptors().hashCode();
		hc += bd.getListenerType().hashCode();
		hc += bd.getRemoveListenerMethod().hashCode();
		hc += (bd.isInDefaultEventSet() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		hc += (bd.isUnicast() ? Boolean.TRUE : Boolean.FALSE).hashCode();		

		return hashcode*31 + hc;
	}

	 
	public boolean equals(Object obj) {
		if (identityTest(obj))
			return true;
			
		if (!super.equals(obj))
 			return false;
 	
		EventSetDescriptor oe = (EventSetDescriptor) ((FeatureDescriptorEquality) obj).fFeature;
		EventSetDescriptor fe = (EventSetDescriptor) fFeature;
		
		EventSetDescriptorEquality oee = (EventSetDescriptorEquality) obj;
 		
		if (!oe.getAddListenerMethod().equals(fe.getAddListenerMethod()))
			return false;
		if (!java.util.Arrays.equals(oe.getListenerMethods(), fe.getListenerMethods()))
			return false;	
		if (oe.getListenerType() != fe.getListenerType())
			return false;
		if (oe.getRemoveListenerMethod() != fe.getRemoveListenerMethod())
			return false;
		if (oe.isInDefaultEventSet() != fe.isInDefaultEventSet())
			return false;
		if (oe.isUnicast() != oe.isUnicast())
			return false;
		
		if (fListenerMethodDescriptors != null || oee.fListenerMethodDescriptors != null) {
			// We are in a Map lookup situation, so one side has listener method equalities, so we will compare that way.
			if (!oee.listenerMethodDescriptors().equals(listenerMethodDescriptors()))			
				return false;
		} else {
			// We are in the building the list phases, don't waste space building entire list.
			MethodDescriptor[] ours = fe.getListenerMethodDescriptors();
			MethodDescriptor[] theirs = oe.getListenerMethodDescriptors();
			if (ours.length != theirs.length)
				return false;
			if (ours.length > 0) {
				MethodDescriptorEquality workingOurs = new MethodDescriptorEquality();
				MethodDescriptorEquality workingThiers = new MethodDescriptorEquality();
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
