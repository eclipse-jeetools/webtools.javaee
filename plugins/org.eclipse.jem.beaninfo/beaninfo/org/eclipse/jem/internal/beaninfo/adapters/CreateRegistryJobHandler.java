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
 *  $RCSfile: CreateRegistryJobHandler.java,v $
 *  $Revision: 1.4 $  $Date: 2004/06/04 15:29:34 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.*;
 

/**
 * This class is used by BeaninfoNature to handle the creation of the registry,
 * either as a job queued off to the UI thread, or directly in case either in
 * UI or UI not started. This class will be a singleton.
 * @since 1.0.0
 */
class CreateRegistryJobHandler {
	private static CreateRegistryJobHandler jobHandler = null;
	
	public static void createRegistry(BeaninfoNature nature) {
		synchronized (CreateRegistryJobHandler.class) {
			if (jobHandler == null) {
				if (Platform.getBundle("org.eclipse.ui") != null) {	//$NON-NLS-1$
					try {
						// There is a UI, it may not be active, but bring in UICreateRegistryJobHandler to do the
						// actual work since it can reference the UI.
						jobHandler = (CreateRegistryJobHandler) Class.forName("org.eclipse.jem.internal.beaninfo.adapters.UICreateRegistryJobHandler").newInstance(); //$NON-NLS-1$
					} catch (InstantiationException e) {
						jobHandler = new CreateRegistryJobHandler();
					} catch (IllegalAccessException e) {
						jobHandler = new CreateRegistryJobHandler();
					} catch (ClassNotFoundException e) {
						jobHandler = new CreateRegistryJobHandler();
					}
				}
			}
		}
		
		jobHandler.processCreateRegistry(nature);
	}
	
	/**
	 * Process the create of the registry. This should be overridden to
	 * do what the UI needs. The UI implimentation should call doCreateRegistry at the
	 * appropriate time.
	 * 
	 * @param nature
	 * 
	 * @since 1.0.0
	 */
	protected void processCreateRegistry(BeaninfoNature nature) {
		doCreateRegistry(nature, new NullProgressMonitor());
	}
	
	private int suspendedCount = 0;	// Number of nested suspends. Resume will occur only when it goes back to zero.
	/*
	 * Do the creation.
	 * 
	 * @param nature
	 * @param pm
	 * 
	 * @since 1.0.0
	 */
	protected final void doCreateRegistry(BeaninfoNature nature, IProgressMonitor pm) {
		pm.beginTask("", 400);	//$NON-NLS-1$
		IJobManager jobManager = Platform.getJobManager();
		Job currentJob = jobManager.currentJob();
		ISchedulingRule suspendedBuildRule = null;	// The build rule. This will be null if I don't suspend.
		try {
			if (currentJob == null || (!currentJob.belongsTo(ResourcesPlugin.FAMILY_AUTO_BUILD) && !currentJob.belongsTo(ResourcesPlugin.FAMILY_MANUAL_BUILD))) {
				// We are not in the build, so suspend the rule. But first, wait for the builds to complete. This
				// is because we need the builds to be completed (so that we see the changed files), but we can't
				// let it happen later. This is because there can be a deadlock between the build and this thread
				// if we let the build (through some builder which is trying to do introspection) lock on this
				// project after we've locked on this project. Therefor we need to complete the build, and then
				// stop it from running again until we've completed on this project.
				if (jobManager.find(ResourcesPlugin.FAMILY_AUTO_BUILD).length > 0 || jobManager.find(ResourcesPlugin.FAMILY_MANUAL_BUILD).length >0) {
					try {						
						jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, new SubProgressMonitor(pm, 100));
						jobManager.join(ResourcesPlugin.FAMILY_MANUAL_BUILD, new SubProgressMonitor(pm, 100));
					} catch (InterruptedException e) {
						// Canceled, go on.
					}
				} else
					pm.worked(200);
				
				synchronized(this) {
					++suspendedCount; // We need to keep track of nesting of suspends because Eclipse doesn't. The first resume will cause it to resume.
					suspendedBuildRule = ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
					jobManager.suspend(suspendedBuildRule, new SubProgressMonitor(pm, 100));
				}
			} else
				pm.worked(300);
			// Don't wait for the build to finish. Either we suspended the build, or we are in the build. In either case
			// we can't wait for the build or we would deadlock.
			nature.createRegistry(new SubProgressMonitor(pm, 100), false);	
		} finally {
			synchronized (this) {
				if (suspendedBuildRule != null) {
					if (--suspendedCount<= 0) {
						suspendedCount = 0;	// Just to be safe.
						jobManager.resume(suspendedBuildRule);
					}
				}
			}
			pm.done();
		}
	}

}
