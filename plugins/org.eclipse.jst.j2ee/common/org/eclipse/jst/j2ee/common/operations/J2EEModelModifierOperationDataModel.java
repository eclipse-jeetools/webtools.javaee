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
 * Created on Dec 4, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.common.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel;

import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModelModifierOperationDataModel extends ModelModifierOperationDataModel {
	protected J2EENature j2eeNature;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(PROJECT_NAME))
			updateJ2EENature();
		return notify;
	}

	/**
	 *  
	 */
	private void updateJ2EENature() {
		j2eeNature = J2EENature.getRegisteredRuntime(getTargetProject());
		String key = null;
		if (j2eeNature != null)
			key = j2eeNature.getEditModelKey();
		setProperty(EDIT_MODEL_ID, key);
	}

	/**
	 * This will be the type of the deployment descriptor docuemnt.
	 * 
	 * @see XMLResource#APP_CLIENT_TYPE
	 * @see XMLResource#APPLICATION_TYPE
	 * @see XMLResource#EJB_TYPE
	 * @see XMLResource#WEB_APP_TYPE
	 * @see XMLResource#RAR_TYPE
	 */
	public int getDeploymentDescriptorType() {
		if (j2eeNature != null)
			return j2eeNature.getDeploymentDescriptorType();
		return -1;
	}

	public EObject getDeploymentDescriptorRoot() {
		if (j2eeNature != null)
			return j2eeNature.getDeploymentDescriptorRoot();
		return null;
	}

	public String getServerTargetID() {
		if (j2eeNature != null) {
			IRuntime target = ServerCore.getProjectProperties(j2eeNature.getProject()).getRuntimeTarget();
			if (null != target) {
				return target.getId();
			}
		}
		return null;
	}

	public String getServerTargetTypeID() {
		if (j2eeNature != null) {
			IRuntime target = ServerCore.getProjectProperties(j2eeNature.getProject()).getRuntimeTarget();
			if (null != target) {
				return target.getRuntimeType().getId();
			}
		}
		return null;
	}

}