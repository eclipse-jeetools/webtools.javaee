/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IUIRunner.java,v $
 *  $Revision: 1.1 $  $Date: 2004/06/04 15:29:38 $ 
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
