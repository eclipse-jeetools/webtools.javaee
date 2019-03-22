/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * This class is meant to mark a virtual component as
 * a type of virtual component which can provide 
 * classpath dependencies to be included in a parent component 
 * 
 * @author rob.stryker@redhat.com
 *
 */
public interface IClasspathDependencyProvider extends IVirtualComponent {
	/**
	 * Return a list of references which should be exported into a 
	 * parent module's appropriate folder for classpath entities. 
	 * 
	 * @return
	 */
	public IVirtualReference[] getJavaClasspathReferences();
}
