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
 * Created on Nov 13, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleExtension;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.internal.moduleextension.JcaModuleExtension;
import org.eclipse.wst.common.frameworks.internal.activities.WTPActivityBridge;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class DefaultModuleProjectCreationOperation extends WTPOperation {

	/**
	 * This activity no longer exists use ENTERPRISE_JAVA instead
	 * 
	 * @deprecated
	 */
	public static final String APP_CLIENT_DEV_ACTIVITY_ID = "org.eclipse.jst.j2ee.application.client.development"; //$NON-NLS-1$
	/**
	 * This activity no longer exists use ENTERPRISE_JAVA instead
	 * 
	 * @deprecated
	 */
	public static final String EJB_DEV_ACTIVITY_ID = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$
	/**
	 * This activity no longer exists use ENTERPRISE_JAVA instead
	 * 
	 * @deprecated
	 */
	public static final String JCA_DEV_ACTIVITY_ID = "com.ibm.wtp.jca.development"; //$NON-NLS-1$
	public static final String WEB_DEV_ACTIVITY_ID = "com.ibm.wtp.web.development"; //$NON-NLS-1$
	public static final String ENTERPRISE_JAVA = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$

	/**
	 * @param operationDataModel
	 */
	public DefaultModuleProjectCreationOperation(DefaultModuleProjectCreationDataModel operationDataModel) {
		super(operationDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		DefaultModuleProjectCreationDataModel model = (DefaultModuleProjectCreationDataModel) operationDataModel;
		if (model.getEjbModel() != null && model.getBooleanProperty(DefaultModuleProjectCreationDataModel.CREATE_EJB))
			createEJBModuleProject(model.getEjbModel(), monitor);
		if (model.getWebModel() != null && model.getBooleanProperty(DefaultModuleProjectCreationDataModel.CREATE_WEB))
			createWebModuleProject(model.getWebModel(), monitor);
		if (model.getJCAModel() != null && model.getBooleanProperty(DefaultModuleProjectCreationDataModel.CREATE_CONNECTOR))
			createRarModuleProject(model.getJCAModel(), monitor);
		if (model.getClientModel() != null && model.getBooleanProperty(DefaultModuleProjectCreationDataModel.CREATE_APPCLIENT))
			createAppClientModuleProject(model.getClientModel(), monitor);
	}

	/**
	 * @param model
	 */
	private void createEJBModuleProject(J2EEModuleCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EarModuleExtension ext = EarModuleManager.getEJBModuleExtension();
		runModuleExtensionOperation(ext, model, monitor);
		WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
	}

	/**
	 * @param model
	 */
	private void createWebModuleProject(J2EEModuleCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EarModuleExtension ext = EarModuleManager.getWebModuleExtension();
		runModuleExtensionOperation(ext, model, monitor);
		WTPActivityBridge.getInstance().enableActivity(WEB_DEV_ACTIVITY_ID, true);
	}

	/**
	 * @param model
	 */
	private void createRarModuleProject(J2EEModuleCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		JcaModuleExtension jcaExt = EarModuleManager.getJCAModuleExtension();
		runModuleExtensionOperation(jcaExt, model, monitor);
		WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
	}

	private void runModuleExtensionOperation(EarModuleExtension extension, J2EEModuleCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		if (extension != null) {
			J2EEModuleCreationOperationOld op = extension.createProjectCreationOperation(model);
			if (op != null)
				op.doRun(monitor);
		}
	}

	/**
	 * @param model
	 */
	private void createAppClientModuleProject(J2EEModuleCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AppClientModuleCreationOperation op = new AppClientModuleCreationOperation((AppClientModuleCreationDataModel) model);
		op.doRun(monitor);
		WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);

	}
}