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
 * Created on Oct 31, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationOperationOld;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

public class EJBModuleCreationOperationOld extends J2EEModuleCreationOperationOld {
	public static final String DEFAULT_SESSION_BEAN_NAME = "DefaultSession"; //$NON-NLS-1$
	
	public EJBModuleCreationOperationOld(EJBModuleCreationDataModelOld dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute(monitor);
		//createDefaultSessionBean(monitor);
		if (((EJBModuleCreationDataModelOld) operationDataModel).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			addAnnotationsBuilder();
		if (((EJBModuleCreationDataModelOld) operationDataModel).getBooleanProperty(EJBModuleCreationDataModelOld.CREATE_CLIENT))
			createClientJar(monitor);

	}
	
	//Removed by NSS
	//Disabled Bean Creation for WTP
	//Also removed Line 45.
	/**
	 * @param monitor
	 */
	/*private void createDefaultSessionBean(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		EJBModuleCreationDataModel dataModel = (EJBModuleCreationDataModel) operationDataModel;
		if (dataModel.getBooleanProperty(EJBModuleCreationDataModel.CREATE_DEFAULT_SESSION_BEAN)) {
			CreateSessionBeanDataModel ejbCreationDataModel = new CreateSessionBeanDataModel();
			ejbCreationDataModel.setProperty(EditModelOperationDataModel.PROJECT_NAME, dataModel.getProjectDataModel().getProject().getName());
			ejbCreationDataModel.setProperty(CreateEnterpriseBeanDataModel.BEAN_NAME, DEFAULT_SESSION_BEAN_NAME); //$NON-NLS-1$
			ejbCreationDataModel.getDefaultOperation().run(monitor);
		}

	}*/

	/**
	 *  
	 */
	private void createClientJar(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EJBClientJarCreationOperation clientOp = new EJBClientJarCreationOperation(((EJBModuleCreationDataModelOld) operationDataModel).getNestEJBClientProjectDM());
		clientOp.doRun(monitor);
	}

	/**
	 * @param monitor
	 */
	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EditModelOperation op = new EditModelOperation((J2EEModuleCreationDataModelOld) operationDataModel) {
			protected void execute(IProgressMonitor pm) throws CoreException, InvocationTargetException, InterruptedException {
				EJBEditModel model = (EJBEditModel) editModel;

				IFolder metainf = model.getEJBNature().getEMFRoot().getFolder(new Path(J2EEConstants.META_INF));
				if (!metainf.exists()) {
					metainf.create(true, true, null);
				}

				model.makeDeploymentDescriptorWithRoot();

			}
		};
		op.doRun(monitor);
	}
	
	

}