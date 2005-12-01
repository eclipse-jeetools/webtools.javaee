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
package org.eclipse.jst.j2ee.client;

import org.eclipse.jst.j2ee.internal.common.XMLResource;

/**
 * EMF Resource class the contrains App Client document
 *	@since 1.0
 */
public interface ApplicationClientResource extends XMLResource {
/**
 * Returns the one contained object in this resource's collection of root objects
 * @return ApplicationClient, the root object for the App Client document.
 */
public ApplicationClient getApplicationClient() ;
}


