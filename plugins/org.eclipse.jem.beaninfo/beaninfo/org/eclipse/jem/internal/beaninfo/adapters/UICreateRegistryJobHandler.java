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
 *  $RCSfile: UICreateRegistryJobHandler.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:44:20 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin;
 

/**
 * This is the version which can actually reference and use the UI classes. It won't be
 * loaded except if ui plugin is available.
 * 
 * It will check to see if UI is running, and if it is not, then let super class handle.
 * If it is running, then if this is the UI thread, do special but run in ui, else if not then
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
				// We are in the UI, so try to run in UI. Really don't want to do this, but we need to
				// get the build rule, and no way to know if the UI thread already has the build rule.
				// It would be nice to spawn off thread to do it, but if we had the build rule we couldn't
				// get it for the spawned thread. So hopefully it won't be long.
				IProgressService ps = PlatformUI.getWorkbench().getProgressService();				
				final ISchedulingRule buildRule = ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();

// TODO Note: For now, runInUI is just way too annoying. It always flashes up. It is supposed to only flash up
// when it is blocked by the rule. I've opened 66683 to address this. I've also opened 66690 to be able
// to test if the thread has the rule so that runInUI will only be used if UI thread had the rule.
// For now take the risk that the UI thread won't ever have the rule. This is pretty likely to be true.
//				final boolean[] gotRuleLocally = new boolean[] {false};				
//				try {
//					ps.runInUI(ps, new IRunnableWithProgress() {
//
//						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//							gotRuleLocally[0] = true;
//							doCreateRegistry(nature, new NullProgressMonitor());
//						}
//					}, buildRule);
//				} catch (InvocationTargetException e) {
//					if (e.getCause() instanceof IllegalArgumentException && !gotRuleLocally[0]) { 
//						// Error was because rule conflicted with currect rules for this thread. So do in separate thread, should be able to get rule there.
						try {
							ps.busyCursorWhile(new IRunnableWithProgress() {

								public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
									IJobManager jm = Platform.getJobManager();
									monitor.beginTask("", 200);
									try {
										jm.beginRule(buildRule, new SubProgressMonitor(monitor, 100));
										doCreateRegistry(nature, new SubProgressMonitor(monitor, 100));
									} finally {
										jm.endRule(buildRule);
										monitor.done();
									}
								}
							});
						} catch (InterruptedException e1) {
						} catch (InvocationTargetException e2) {
							BeaninfoPlugin.getPlugin().getLogger().log(e2.getCause(), Level.WARNING);
						}
//					} else
//						BeaninfoPlugin.getPlugin().getLogger().log(e.getCause(), Level.WARNING);						
//				} catch (InterruptedException e) {
//				}
			}
		} else
			super.processCreateRegistry(nature);	// Workbench not running, do default.
	}
}
