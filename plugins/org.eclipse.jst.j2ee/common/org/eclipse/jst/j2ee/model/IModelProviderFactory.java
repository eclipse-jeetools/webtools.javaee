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
package org.eclipse.jst.j2ee.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Factory interface for creating IModelProvider instances.  
 * These factories are registered for a specific model domain/version
 *
 */
public interface IModelProviderFactory {
	
	/**
	 * @param aProject {@link IProject}
	 * @return {@link IModelProvider}
	 */
	IModelProvider create (IProject aProject);
	/**
	 * @param aComponent {@link IVirtualComponent}
	 * @return {@link IModelProvider}
	 */
	IModelProvider create (IVirtualComponent aComponent);

}
