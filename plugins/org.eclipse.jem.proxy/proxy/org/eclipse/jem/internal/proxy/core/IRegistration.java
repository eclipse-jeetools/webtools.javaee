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
 *  $RCSfile: IRegistration.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
/**
 * This interface is used as a registration interface for a proxy vm implementation.
 * The implementation is called whenever it is required to startup a vm implementation.
 * This interface must be implemented by classes registered in the "org.eclipse.jem.proxy.proxyImplementations"
 * extension point.
 */

public interface IRegistration {
	
	/**
	 * This will be called to start vm implementation. It will be called
	 * from the Proxy plugin startVM request from the VM that the project
	 * is configured to run.
	 *
	 * contribs: Contributors to use, may be null.
	 * attachAWT: Whether AWT should be attached to this implementation.
	 * project: Project to start on. This will not be null and it will be a JavaProject.
	 * vmName: The name to give to this implementation (this will show up in the debugger in the normal implementation case).
	 * pm: ProgressMonitor to use while starting the implementation.
	 */
	public ProxyFactoryRegistry startImplementation(IConfigurationContributor[] contribs, boolean attachAWT, IProject project, String vmName, IProgressMonitor pm) throws CoreException;
}