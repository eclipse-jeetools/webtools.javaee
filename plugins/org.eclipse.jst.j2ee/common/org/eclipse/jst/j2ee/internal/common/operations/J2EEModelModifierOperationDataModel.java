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
package org.eclipse.jst.j2ee.internal.common.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.operation.ModelModifierOperationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModelModifierOperationDataModel extends ModelModifierOperationDataModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		return notify;
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
	public int getDeploymentDescriptorType(){
		String componentType = getComponent().getComponentTypeId();
		if (componentType.equals(IModuleConstants.JST_EAR_MODULE))
			return XMLResource.APPLICATION_TYPE;
		if (componentType.equals(IModuleConstants.JST_APPCLIENT_MODULE))
			return XMLResource.APP_CLIENT_TYPE;
		if (componentType.equals(IModuleConstants.JST_EJB_MODULE))
			return XMLResource.EJB_TYPE;
		if (componentType.equals(IModuleConstants.JST_CONNECTOR_MODULE))
			return XMLResource.RAR_TYPE;
		if (componentType.equals(IModuleConstants.JST_WEB_MODULE))
			return XMLResource.WEB_APP_TYPE;
		return -1;
	}

	public EObject getDeploymentDescriptorRoot() {
		ArtifactEdit edit = null;
		try {
			edit = ComponentUtilities.getArtifactEditForRead(getComponent());
			return edit.getContentModelRoot();
		} finally {
			if (edit != null)
				edit.dispose();
		}
	}

	public String getServerTargetID() {
		IRuntime target = ServerCore.getProjectProperties(getComponent().getProject()).getRuntimeTarget();
		if (null != target) {
			return target.getId();
		}
		return null;
	}

	public String getServerTargetTypeID() {
		IRuntime target = ServerCore.getProjectProperties(getComponent().getProject()).getRuntimeTarget();
		if (null != target) {
			return target.getRuntimeType().getId();
		}
		return null;
	}

}