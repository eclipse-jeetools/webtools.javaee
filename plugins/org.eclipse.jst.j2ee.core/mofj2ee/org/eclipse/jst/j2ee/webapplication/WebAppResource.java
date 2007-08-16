/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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

import org.eclipse.jst.j2ee.internal.common.XMLResource;

/**
 * @author schacher
 * @since 1.0
 */
public interface WebAppResource extends XMLResource {
	
	/**
	 * Returns reference to the model object WebApp
	 * @return reference to  model object WebApp
	 */
	WebApp getWebApp();
	/**
	 * Return true if this Resource supports the Web 2.2 spec.
	 * @return boolean value
	 */
	boolean isWeb2_2();
	/**
	 * Return true if this Resource supports the Web 2.3 spec.
	 * @return boolean value
	 */
	boolean isWeb2_3();
	/**
	 * Return true if this Resource supports the Web 2.4 spec.
	 * @return boolean value
	 */
	boolean isWeb2_4();

}
