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
 *  $Revision: 1.3 $  $Date: 2004/06/04 15:29:34 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin;
 

/**
 * This is the version which can actually reference and use the UI classes. It won't be
 * loaded except if ui plugin is available.
 * 
 * It will check to see if UI is running, and if it is not, then let super class handle.
 * If it is running, then if this is the UI thread, use progress service, else if not then
 * let super handle it normally.
 * 
 * @since 1.0.0
 */
class UICreateRegistryJobHandler extends CreateRegistryJobHandler {
	
	

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.adapters.CreateRegistryJobHandler#processCreateRegistry(org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature)
	 */
	protected void processCreateRegistry(final BeaninfoNature nature) {
		if (PlatformUI.isWorkbenchRunning()) {
			if (Display.getCurrent() == null)
				super.processCreateRegistry(nature);	// We are not in the UI thread. Do normal.
			else {
				// We are in the UI, so use the progress service to farm off to another thread and keep the UI active, though disabled.
				try {
					PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

						public void run(IProgressMonitor monitor) throws InterruptedException {
							doCreateRegistry(nature, monitor);
							if (monitor.isCanceled())
								throw new InterruptedException();
						}
					});
				} catch (InvocationTargetException e) {
					BeaninfoPlugin.getPlugin().getLogger().log(e.getCause(), Level.WARNING);
				} catch (InterruptedException e) {
					// It was cancelled, so we just go on and launch.
				}
				
			}
		} else
			super.processCreateRegistry(nature);	// Workbench not running, do default.
	}
}
