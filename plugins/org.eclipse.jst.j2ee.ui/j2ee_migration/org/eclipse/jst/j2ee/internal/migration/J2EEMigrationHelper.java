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
/*
 * Created on Feb 23, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEMigrationHelper {

	public static final String IMPORTED_JAR_SUFFIX = ".imported_classes.jar"; //$NON-NLS-1$

	/**
	 *  
	 */
	public J2EEMigrationHelper() {
		super();
	}

	public static IRuntime getTargetRuntime(IProject project) {
		return ServerCore.getProjectProperties(project).getRuntimeTarget();
	}

	/**
	 * @param bean
	 * @return
	 */
	protected static boolean isEJB1_X(EnterpriseBean bean) {
		return (bean.getVersionID() == J2EEVersionConstants.EJB_1_1_ID || bean.getVersionID() == J2EEVersionConstants.EJB_1_0_ID);

	}

	public static J2EENature getCurrentProjectNature(J2EEMigrationConfig config) {
		if (config != null) {
			IProject project = config.getTargetProject();
			return J2EENature.getRegisteredRuntime(project);
		}
		return null;
	}

	/**
	 * @return
	 */
	public static int getDeploymentDescriptorType(J2EEMigrationConfig config) {
		J2EENature nature = getCurrentProjectNature(config);
		if (nature == null)
			return -1;
		return nature.getDeploymentDescriptorType();
	}

	/**
	 * @param ejb
	 * @return
	 */
	public static boolean isEJB2_X(EnterpriseBean bean) {
		return (bean.getVersionID() == J2EEVersionConstants.EJB_2_0_ID);
	}

}