/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deploy;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/*
 * Deployer interface for the J2EEDeploymentFramework
 */
/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 * @deprecated - Change to use the org.eclipse.wst.common.emf.utilities.ICommand interface
 */
public interface J2EEDeployer {
	/*
	 * This will contain the multi-selection of objects to deploy. This selection could be used to
	 * filter elements within a Module. Any other setup code should be done here...
	 */
	void init(Object[] selection);

	/*
	 * This will visit each extension's deploy operation on the deployable object. A Multi-Status
	 * should be returned
	 */
	IStatus visit(EJBJar deployable) throws FatalDeployerException;

	IStatus visit(ApplicationClient deployable) throws FatalDeployerException;

	IStatus visit(Application deployable) throws FatalDeployerException;

	IStatus visit(WebApp deployable) throws FatalDeployerException;

	IStatus visit(Connector deployable) throws FatalDeployerException;

	/*
	 * Any cleanup should be done here...
	 */
	void finish();


}