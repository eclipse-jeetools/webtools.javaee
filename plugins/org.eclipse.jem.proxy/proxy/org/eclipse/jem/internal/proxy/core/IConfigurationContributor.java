package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IConfigurationContributor.java,v $
 *  $Revision: 1.2 $  $Date: 2004/03/04 16:14:04 $ 
 */


import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;


/**
 * This interface is used to contribute to the configuration that will be
 * used to start a proxy registry. 
 * 
 * @since 1.0.0
 */
public interface IConfigurationContributor {
	/**
	 * Contribute to the classpath. The controller is used to do the actual contribution.
	 * 
	 * @param controller
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public void contributeClasspaths(IConfigurationContributionController controller) throws CoreException;

	/**
	 * Contribute updates to the configuration. It will be called before the launch has started.
	 * 
	 * @param config
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public void contributeToConfiguration(ILaunchConfigurationWorkingCopy config) throws CoreException;
	
	/**
	 * Contribute to the registry (or interact with the remote vm) after the registry has been
	 * created.
	 * 
	 * @param registry
	 * 
	 * @since 1.0.0
	 */
	public void contributeToRegistry(ProxyFactoryRegistry registry);
}