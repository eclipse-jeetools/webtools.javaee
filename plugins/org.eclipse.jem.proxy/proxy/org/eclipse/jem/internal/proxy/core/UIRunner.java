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
 *  $RCSfile: UIRunner.java,v $
 *  $Revision: 1.1 $  $Date: 2004/06/04 15:29:37 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
 

/**
 * The actual implementation of IUIRunner to run the build under
 * IProgressService control if in the UI thread.
 * 
 * <package-protected> because should only be used within here.
 * 
 * @since 1.0.0
 */
class UIRunner implements IUIRunner, IRunnableWithProgress {
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IUIRunner#handleBuild(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void handleBuild(IProgressMonitor pm) throws CoreException {
		if (!PlatformUI.isWorkbenchRunning() || Display.getCurrent() == null) {
			ProxyLaunchSupport.runBuild(pm);
		} else {
			pm.beginTask("", 100); //$NON-NLS-1$
			try {
				PlatformUI.getWorkbench().getProgressService().busyCursorWhile(this);
			} catch (InvocationTargetException e) {
				if (e.getCause() instanceof CoreException)
					throw (CoreException) e.getCause();
				ProxyPlugin.getPlugin().getLogger().log(e.getCause(), Level.WARNING);
			} catch (InterruptedException e) {
				// It was cancelled, so we just go on and launch.
			}
			pm.done();
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			ProxyLaunchSupport.runBuild(monitor);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
	}
}
