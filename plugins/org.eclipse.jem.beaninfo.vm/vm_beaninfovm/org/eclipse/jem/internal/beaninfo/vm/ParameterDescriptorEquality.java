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
 * ParameterDescriptor equality tester
 */
public class ParameterDescriptorEquality extends FeatureDescriptorEquality {
	
	static void INIT() {
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
