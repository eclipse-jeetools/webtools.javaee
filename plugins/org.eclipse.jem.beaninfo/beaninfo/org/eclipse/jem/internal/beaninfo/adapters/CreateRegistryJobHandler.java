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
 *  $Revision: 1.3 $  $Date: 2004/06/02 19:42:39 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import org.eclipse.core.runtime.*;
 

/**
 * This class is used by BeaninfoNature to handle the creation of the registry,
 * either as a job queued off to the UI thread, or directly in case either in
 * UI or UI not started.
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
		// sync on nature because we aren't in the UI environment, so we can't sequentially do the create requests.
		// We would get a race condition in non UI environment. In UI environment we can be sure that the
		// create request will be processed sequentially (through the UI thread) and so not have a race. In that
		// case we mustn't get the hold on nature.
		synchronized (nature) {
			doCreateRegistry(nature, new NullProgressMonitor());
		}
	}
	
	/*
	 * Do the creation.
	 * 
	 * @param nature
	 * @param pm
	 * 
	 * @since 1.0.0
	 */
	protected final void doCreateRegistry(BeaninfoNature nature, IProgressMonitor pm) {
		nature.createRegistry(pm);
	}

}
