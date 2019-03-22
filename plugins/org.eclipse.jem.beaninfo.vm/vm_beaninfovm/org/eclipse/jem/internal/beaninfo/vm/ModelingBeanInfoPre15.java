/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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

import java.beans.BeanInfo;

/**
 * This is the modeling BeanInfo for Pre-JDK 1.4.
 */
public class ModelingBeanInfoPre15 extends ModelingBeanInfo {

	public ModelingBeanInfoPre15(BeanInfo beanInfo, int doFlags) {
		super(beanInfo, doFlags);
	}

	public ModelingBeanInfoPre15(BeanInfo beanInfo, BeanInfo superBeanInfo, int doFlags) {
		super(beanInfo, superBeanInfo, doFlags);
	}
}
