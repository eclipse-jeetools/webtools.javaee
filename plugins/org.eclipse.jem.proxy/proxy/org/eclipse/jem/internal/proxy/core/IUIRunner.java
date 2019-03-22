/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
 

/**
 * Interface that doesn't require UI. The implementation will. This will
 * only be used if within UI environment.
 * 
 * <package-protected> because should only be used within here.
 * 
 * @since 1.0.0
 */
interface IUIRunner {
	/**
	 * Handle the build. If not in UI thread, just call back to ProxyLaunchSupport
	 * to handle the build. If on UI thread, then use the IProgressService to do it.
	 * This will keep the UI from "locking", though it will be disabled it won't deadlock.
	 * 
	 * @param pm
	 * 
	 * @since 1.0.0
	 */
	public void handleBuild(IProgressMonitor pm) throws CoreException;
}
