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
 *  $RCSfile: CreateRegistryJobHandler.java,v $
 *  $Revision: 1.13 $  $Date: 2005/06/21 19:17:03 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import java.util.logging.Level;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.*;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin;
import org.eclipse.jem.util.logger.proxy.Logger;

 

/**
 * This class is used by BeaninfoNature to handle the creation of the registry,
 * This class will be a singleton. It is needed to handle if UI active without
 * requiring UI plugin. (So headless will work too). The subclass <code>UICreateRegistryJobHandler</code>
 * will be instantiated in case of UI active.
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
		
		// See if Autobuild sleeping or waiting. This could be a race condition for us. We can't wait for it
		// because we may already have the build rule locked by our thread. No way of testing this if beginRule was used.
		// We can test if we are a build job (not an inline build), and if so, just go on.
		// Maybe we can figure out in future if we find race condition happens significant amount of time.
		IJobManager jobManager = Platform.getJobManager();
		Job currentJob = jobManager.currentJob();
		if (currentJob == null || (!currentJob.belongsTo(ResourcesPlugin.FAMILY_AUTO_BUILD) && !currentJob.belongsTo(ResourcesPlugin.FAMILY_MANUAL_BUILD))) {
			// See if autojob is waiting or sleeping.
			// Give it up to a second at .2 second intervals to try (i.e. 5 tries)
			int tries = 5;
			while (isAutoWaiting() && --tries>0) {
				try {
					Thread.sleep(200);	// Wait just .2 seconds to give build a chance to start. If it is still not started, then just go on.
				} catch (InterruptedException e) {
				}
			}
			if (tries==0) {
				Logger logger = BeaninfoPlugin.getPlugin().getLogger();
				if (logger.isLoggingLevel(Level.WARNING))
					logger.log("Build job waiting when trying to start beaninfo registry. Possible race.", Level.WARNING);	// $NON-NLS-1$ //$NON-NLS-1$
			}
		}
		
		jobHandler.processCreateRegistry(nature);
	}

	private static boolean isAutoWaiting() {
		Job[] autojobs = Platform.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);
		for (int i = 0; i < autojobs.length; i++) {
			int state = autojobs[i].getState();
			if (state == Job.WAITING || state == Job.SLEEPING) 
				return true;
		}
		return false;
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
	protected void processCreateRegistry(final BeaninfoNature nature) {
		IJobManager jobManager = Platform.getJobManager();
		ISchedulingRule buildRule = ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
		boolean gotRuleLocally = true;
		try {
			try {
				jobManager.beginRule(buildRule, new NullProgressMonitor());
			} catch (IllegalArgumentException e) {
				gotRuleLocally = false;	// This thread already had a rule, and it conflicted with the build rule, so we need to spawn off.
			}
			if (gotRuleLocally)
				doCreateRegistry(nature, new NullProgressMonitor());
		} finally {
			jobManager.endRule(buildRule);	// Whether we got the rule or not, we must do endrule.
		}
		
		if (!gotRuleLocally) {
			// Spawn off to a job and wait for it. Hopefully we don't have a deadlock somewhere.
			Job doCreateJob = new Job(BeanInfoAdapterMessages.UICreateRegistryJobHandler_StartBeaninfoRegistry) { 

				protected IStatus run(IProgressMonitor monitor) {
					doCreateRegistry(nature, monitor);
					return Status.OK_STATUS;
				}
			};
			doCreateJob.schedule();
			while (true) {
				try {
					doCreateJob.join();
					break;
				} catch (InterruptedException e) {
				}
			}
		}
	}
		
	/*
	 * Do the creation. It is expected that the build rule has already been given to this thread.
	 * It is important that the build rule be given to this thread. This is so that a build won't
	 * start trying to create the same registry (which has happened in the past) at the same time
	 * a different thread was trying to start the registry. You would either have a deadlock, or 
	 * a race and get two different registries started.
	 * 
	 * The build rule also means that all beaninfo registry creations will be serialized and have
	 * a race condition. The unfortunate part is that two independent project's registries can't be
	 * created at same time. But that is the result of the build rule. We can't allow the builds, so
	 * we need to stop all parallel beaninfo registry creations.
	 * 
	 * @param nature
	 * @param pm
	 * 
	 * @since 1.0.0
	 */
	protected final void doCreateRegistry(BeaninfoNature nature, IProgressMonitor pm) {
		pm.beginTask("", 100);	//$NON-NLS-1$
		try {
			nature.createRegistry(new SubProgressMonitor(pm, 100));	
		} finally {
			pm.done();
		}
	}

}
