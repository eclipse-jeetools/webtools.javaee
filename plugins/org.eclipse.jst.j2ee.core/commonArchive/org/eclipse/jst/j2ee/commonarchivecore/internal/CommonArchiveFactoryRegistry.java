/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Aug 7, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.commonarchivecore.internal;

import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CommonArchiveFactoryRegistry {

	public static CommonArchiveFactoryRegistry INSTANCE = new CommonArchiveFactoryRegistry();

	protected CommonarchiveFactory commonArchiveFactory = ((CommonarchiveFactoryImpl) CommonarchivePackage.eINSTANCE.getCommonarchiveFactory()).getDelegate();

	/**
	 * Constructor
	 */
	public CommonArchiveFactoryRegistry() {
		super();
	}

	public CommonarchiveFactory getCommonArchiveFactory() {
		return commonArchiveFactory;
	}

	public void setCommonArchiveFactory(CommonarchiveFactory factory) {
		commonArchiveFactory = factory;
	}
}
