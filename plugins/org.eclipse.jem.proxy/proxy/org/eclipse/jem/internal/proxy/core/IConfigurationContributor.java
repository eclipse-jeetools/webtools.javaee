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
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
/**
 * This interface is used to contribute to the classpath that will be
 * used to start a remote vm. In addition it allows contributions
 * to the VMRunnerConfiguration and to the registry. 
 */
public interface IConfigurationContributor {
	/**
	 * Contribute classpaths to the list of classpaths that is passed in.
	 * Must use the controller to add to the classpaths.
	 */
	public void contributeClasspaths(List classpaths, IClasspathContributionController controller) throws CoreException;
	
	/**
	 * Contribute to the VMRunnerConfiguration. This allows
	 * the addition/modification of the arguments for example.
	 * It will be called after system updates are made to the configuration.
	 */
	public void contributeToConfiguration(VMRunnerConfiguration config);
	
	/**
	 * Contribute to the registry once it has been initialized.
	 */
	public void contributeToRegistry(ProxyFactoryRegistry registry);
}