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
package org.eclipse.jst.j2ee.ejb.internal.deployables;

import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.model.IProjectModule;

/**
 * @version 1.0
 * @author
 */
public class EJBDeployableFactory extends J2EEDeployableFactory {
	private static final String ID = "com.ibm.wtp.server.ejb"; //$NON-NLS-1$

	/**
	 * Constructor for EJBDeployableFactory.
	 */
	public EJBDeployableFactory() {
		super();
	}

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
	}

	/*
	 * @see J2EEDeployableFactory#getNatureID()
	 */
	public String getNatureID() {
		return IEJBNatureConstants.NATURE_ID;
	}

	/*
	 * @see J2EEDeployableFactory#createDeployable(J2EENature)
	 */
	public IProjectModule createModule(J2EENature nature) {
		return new EJBDeployable(nature, ID);
	}
}