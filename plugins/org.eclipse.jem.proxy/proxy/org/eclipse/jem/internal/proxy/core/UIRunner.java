/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: UIRunner.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:53:46 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.runtime.*;
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
class UIRunner implements IUIRunner {
	
	/*
	 * Special class that takes a progress monitor 1 and only handles isCanceled from it,
	 * but everything else is forwarded to progress monitor 2. This allows the pm that
	 * is sent into handleBuild to signal a cancel even though the progress service
	 * sends in its own pm. 
	 * 
	 * @since 1.0.0
	 */
	private static class UIRunnerProgressMonitor extends ProgressMonitorWrapper {
		private IProgressMonitor pmcancel;
		
		public UIRunnerProgressMonitor(IProgressMonitor pmcancel, IProgressMonitor pmmain) {
			super(pmmain);
			this.pmcancel = pmcancel;
		}
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.core.runtime.IProgressMonitor#isCanceled()
		 */
		public boolean isCanceled() {
			return pmcancel.isCanceled() || super.isCanceled();
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.core.runtime.IProgressMonitor#setCanceled(boolean)
		 */
		public void setCanceled(boolean value) {
			super.setCanceled(value);
			pmcancel.setCanceled(value);	// Cancel it too now.
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IUIRunner#handleBuild(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void handleBuild(final IProgressMonitor pm) throws CoreException {
		if (!PlatformUI.isWorkbenchRunning() || Display.getCurrent() == null) {
			ProxyLaunchSupport.runBuild(pm);
		} else {
			pm.beginTask("", 100); //$NON-NLS-1$
			try {
				PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						IProgressMonitor uipm = new UIRunnerProgressMonitor(pm, monitor); 
						try {
							ProxyLaunchSupport.runBuild(uipm);
						} catch (CoreException e) {
							throw new InvocationTargetException(e);
						}
						if (uipm.isCanceled()) {
							pm.setCanceled(true);	// Make sure that cancel got through (could of come from monitor instead).
							throw new InterruptedException();
						}
					}
				});
			} catch (InvocationTargetException e) {
				if (e.getCause() instanceof CoreException)
					throw (CoreException) e.getCause();
				ProxyPlugin.getPlugin().getLogger().log(e.getCause(), Level.WARNING);
			} catch (InterruptedException e) {
				// It was canceled, launch will be canceled too. pm is already marked canceled and caller can check that
			}
			pm.done();
		}
	}
}
