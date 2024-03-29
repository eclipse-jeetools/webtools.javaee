/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application;

import org.eclipse.jst.j2ee.internal.common.XMLResource;
/**
 * The represents the deployment descriptor of the EAR.

 * @since 1.0 */

public interface ApplicationResource extends XMLResource {
	
	  /**
	   * The application deployment descriptor 
	   * @since 1.0
	   * @return The deployment descriptor of the EAR, application deployment descriptor
	   */
	@Override
	public Application getApplication() ;
}

