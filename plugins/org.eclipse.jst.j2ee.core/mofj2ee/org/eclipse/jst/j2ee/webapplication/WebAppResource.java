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
 * Created on Mar 18, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.jst.j2ee.common.XMLResource;

/**
 * @author schacher
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface WebAppResource extends XMLResource {
	WebApp getWebApp();
	/**
	 * Return true if this Resource supports the Web 2.2 spec.
	 */
	boolean isWeb2_2();
	/**
	 * Return true if this Resource supports the Web 2.3 spec.
	 */
	boolean isWeb2_3();
	/**
	 * Return true if this Resource supports the Web 2.4 spec.
	 */
	boolean isWeb2_4();

}
