package org.eclipse.jem.internal.beaninfo.vm;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ModelingBeanInfo15.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:45:49 $ 
 */

import java.beans.BeanInfo;

/**
 * This was supposed to for 1.4 or above where it can use identity
 * to test for inherited features, but it still is not correct
 * in 1.4. See the header comments in ModelingBeanInfo.
 * @see org.eclipse.jem.internal.beaninfo.vm.ModelingBeanInfo
 */
public class ModelingBeanInfo15 extends ModelingBeanInfo {

	/**
	 * Constructor for ModelingBeanInfo15.
	 * @param beanInfo
	 */
	public ModelingBeanInfo15(BeanInfo beanInfo, int doFlags) {
		super(beanInfo, doFlags);
	}

	/**
	 * Constructor for ModelingBeanInfo15.
	 * @param beanInfo
	 * @param superBeanInfo
	 */
	public ModelingBeanInfo15(BeanInfo beanInfo, BeanInfo superBeanInfo, int doFlags) {
		super(beanInfo, superBeanInfo, doFlags);
	}
}
