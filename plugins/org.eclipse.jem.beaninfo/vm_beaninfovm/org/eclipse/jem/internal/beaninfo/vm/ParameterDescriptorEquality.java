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
 *  $RCSfile: ParameterDescriptorEquality.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */

import java.beans.*;
/**
 * ParameterDescriptor equality tester
 */
public class ParameterDescriptorEquality extends FeatureDescriptorEquality {
	
	static {
		try {
			MAP_EQUALITY.put(ParameterDescriptor.class, (ParameterDescriptorEquality.class).getConstructor(new Class[] {ParameterDescriptor.class}));
		} catch (NoSuchMethodException e) {
		}
	}	
	
	public ParameterDescriptorEquality() {
	}
	
	public ParameterDescriptorEquality(ParameterDescriptor descr) {
		super(descr);
	}	

}