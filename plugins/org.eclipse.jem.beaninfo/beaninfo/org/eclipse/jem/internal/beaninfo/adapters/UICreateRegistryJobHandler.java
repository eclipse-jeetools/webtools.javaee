/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: UICreateRegistryJobHandler.java,v $
 *  $Revision: 1.9 $  $Date: 2006/05/17 20:13:00 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.*;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin;

/**
 * This is the version which can actually reference and use the UI classes. It won't be loaded except if ui plugin is available.
 * 
 * It will check to see if UI is running, and if it is not, then let super class handle. If it is running, then if this is the UI thread, do special
 * but run in ui, else if not then let super handle it normally.
 * 
 * @since 1.0.0
 */
class UICreateRegistryJobHandler extends CreateRegistryJobHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.beaninfo.adapters.CreateRegistryJobHandler#processCreateRegistry(org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature)
	 */
	protected void processCreateRegistry(final BeaninfoNature nature) {
		if (PlatformUI.isWorkbenchRunning()) {
			if (Display.getCurrent() == null)
				super.processCreateRegistry(nature); // We are not in the UI thread. Do normal.
			else {
				IProgressService ps = PlatformUI.getWorkbench().getProgressService();
				final ISchedulingRule buildRule = ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
				// First find out if we already have the build rule. If we do, we MUST run in the UI thread. Have no option, can't transfer the
				// rule to the thread and then back to us when done.
				// This means a busy cursor instead of a possible progress dialog to keep the UI active. Only possible deadlock
				// situation is if some other thread has the build rule (other than the build) and is doing an asyncExec or
				// is waiting on something from the UI thread.
				final IJobManager jobManager = Platform.getJobManager();
				Job currentJob = jobManager.currentJob();
				if (currentJob == null || currentJob.getRule() == null || !currentJob.getRule().contains(buildRule)) {
					// UI is not in a job, or it is and there is no rule, or there is a rule and it is not compatible with the buildRule.
					// In that case we need to run in another thread. That is so that we can put up a progress dialog if it takes
					// too long. 
					// If no rule then we can't run in UI because some other job may have the rule, and there is no way to test
					// for this. So we don't want to block the UI entirely waiting for the rule, so we can use progress dialog
					// for this. Also if we have a rule and it conflicts with build rule then we should also go to another thread.
					// busyCursorWhile will do this. It will run the create in another thread but keep the UI responsive with
					// progress dialog after a "long operation" timeout.
					try {
						ps.busyCursorWhile(new IRunnableWithProgress() {
	
							public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
								monitor.beginTask("", 200); //$NON-NLS-1$
								try {
									jobManager.beginRule(buildRule, new SubProgressMonitor(monitor, 100));
									doCreateRegistry(nature, new SubProgressMonitor(monitor, 100));
								} finally {
									jobManager.endRule(buildRule);
									monitor.done();
								}
							}
						});
					} catch (InterruptedException e1) {
					} catch (InvocationTargetException e2) {
						BeaninfoPlugin.getPlugin().getLogger().log(e2.getCause(), Level.WARNING);
					}
				} else {
					// We are a job in the UI and we have or can contain the build rule. Therefor we must run in the UI thread itself. Otherwise
					// there will be a deadlock because we have the build rule and the other thread won't be able to get it.
					// Note: There is ProgressService.runInUI() but that guy is complicated with some interactions I'm not sure about. Safer to do this.
					BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
						public void run() {
							try {
								jobManager.beginRule(buildRule, new NullProgressMonitor());
								doCreateRegistry(nature, new NullProgressMonitor());
							} finally {
								jobManager.endRule(buildRule);
							}
						}
					});
				}
			}
		} else
			super.processCreateRegistry(nature); // Workbench not running, do default.
	}
}
