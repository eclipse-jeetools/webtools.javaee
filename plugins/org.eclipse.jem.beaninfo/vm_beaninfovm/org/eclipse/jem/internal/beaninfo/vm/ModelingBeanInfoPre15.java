package org.eclipse.jem.internal.beaninfo.vm;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ModelingBeanInfoPre15.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/04 23:11:53 $ 
 */

import java.beans.BeanInfo;

/**
 * This is the modeling BeanInfo for Pre-JDK 1.4.
 */
public class ModelingBeanInfoPre15 extends ModelingBeanInfo {

	public ModelingBeanInfoPre15(BeanInfo beanInfo) {
		super(beanInfo);
	}

	public ModelingBeanInfoPre15(BeanInfo beanInfo, BeanInfo superBeanInfo) {
		super(beanInfo, superBeanInfo);
	}
}
