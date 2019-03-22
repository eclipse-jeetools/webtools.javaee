/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.application;

public interface ICommonModule {
	
	void setUri(String value);

	String getUri();
	
	/**
	 * Returns boolean value if this Module represents a ConnectorModule
	 * @return boolean value
	 */
	public boolean isConnectorModule();
	
	/**
	 * Returns boolean value if this Module represents a EjbModule
	 * @return boolean value
	 */
	public boolean isEjbModule();
	
	/**
	 * Returns boolean value if this Module represents a JavaClientModule(ApplicationClient)
	 * @return boolean value
	 */
	public boolean isJavaModule();
	
	/**
	 * Returns boolean value if this Module represents a WebModule
	 * @return boolean value
	 */
	public boolean isWebModule();
}
