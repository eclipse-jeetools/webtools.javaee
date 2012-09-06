/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * This class is meant to mark a virtual component as
 * a type of virtual component which can receive and handle 
 * classpath dependencies from child references 
 * 
 * @author rob.stryker@redhat.com
 *
 */
public interface IClasspathDependencyReceiver extends IVirtualComponent {
	/**
	 * Runtime path used to indicate that the resolved contributions from a classpath entry should be
	 * mapped into the exported/deployed structure of the parent module at the same location that the target module
	 * is mapped.
	 */
	public static final String RUNTIME_MAPPING_INTO_CONTAINER = "../"; //$NON-NLS-1$
	
	/**
	 * IPath value of RUNTIME_MAPPING_INTO_CONTAINER.
	 */
	public static final IPath RUNTIME_MAPPING_INTO_CONTAINER_PATH = new Path(RUNTIME_MAPPING_INTO_CONTAINER);

	
	/**
	 * Returns whether this virtual component can accept
	 * classpath references from children. 
	 * 
	 * @return
	 */
	public boolean canReceiveClasspathDependencies();
	
	/**
	 * Return the preferred folder to place child classpath
	 * dependencies, or null if they can go wherever the reference directs
	 * 
	 * @return
	 */
	public IPath getClasspathFolderPath(IClasspathDependencyComponent component);
}
