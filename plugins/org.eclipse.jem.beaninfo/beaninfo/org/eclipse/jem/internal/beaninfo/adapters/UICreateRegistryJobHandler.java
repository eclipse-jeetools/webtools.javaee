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
 *  $RCSfile: UICreateRegistryJobHandler.java,v $
 *  $Revision: 1.2 $  $Date: 2004/06/02 19:42:39 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
 

/**
 * This is the version which can actually reference and use the UI classes. It won't be
 * loaded except if ui plugin is available.
 * 
 * It will check to see if UI is running, and if it is not, then let super class handle.
 * If it is running, then if this is the UI thread, just do the creation, else if not
 * UI thread, create a job that will do a syncexec to the ui thread to do it.
 * 
 * @since 1.0.0
 */
class UICreateRegistryJobHandler extends CreateRegistryJobHandler {
	
	

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.adapters.CreateRegistryJobHandler#processCreateRegistry(org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature)
	 */
	protected void processCreateRegistry(final BeaninfoNature nature) {
		if (PlatformUI.isWorkbenchRunning()) {
			final Display display = PlatformUI.getWorkbench().getDisplay();
			if (display.getThread() == Thread.currentThread())
				doCreateRegistry(nature, new NullProgressMonitor());	// We are in the UI thread, so just do it.
			else {
				// We are not in the UI thread, so farm it off to a job to syncit.
				// Use it as a job so that if it takes long enough, the progress view will be shown.
				Job createJob = new Job(BeanInfoAdapterMessages.getString("UICreateRegistryJobHandler.StartBeaninfoRegistry")) { //$NON-NLS-1$
					protected IStatus run(final IProgressMonitor monitor) {
						display.syncExec(new Runnable() {
							public void run() {
								doCreateRegistry(nature, monitor);
							}
						});
						return Status.OK_STATUS;
					}
				};
				createJob.schedule();
				while (true) {
					try {
						createJob.join();
						break;
					} catch (InterruptedException e) {
					}
				}
			}
		} else
			super.processCreateRegistry(nature);	// Workbench not running, do default.
	}
}
