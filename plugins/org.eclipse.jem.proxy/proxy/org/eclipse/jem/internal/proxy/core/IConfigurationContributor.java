/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IConfigurationContributor.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:53:46 $ 
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
	 * Called first before any of the other calls. This can be used to do any internal initializations
	 * required by the other calls. That way the other calls can have the order changed as the implementation
	 * requires.
	 * <p>
	 * NOTE: For those who nest configuration contributors (i.e. within an implementation of a contributor, more
	 * contributors are discovered) then it is imperitive that when those contributors are discovered that this method
	 * be called on them too. This is necessary so the contract with contributors can be followed. It is best if the
	 * discoveries are actually done in the initialize method itself so that it can pass the launch info right there.
	 * 
	 * @param info info contains things like containers visible, etc.
	 * 
	 * @see IConfigurationContributionInfo
	 * @since 1.0.0
	 */
	public void initialize(IConfigurationContributionInfo info);
	
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
