/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Nov 19, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code
 * and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationOperation;

public class JavaUtilityJARProjectCreationOperationOld extends J2EEArtifactCreationOperationOld {
	/**
	 * @param dataModel
	 */
	public JavaUtilityJARProjectCreationOperationOld(J2EEArtifactCreationDataModelOld dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEProjectCreationOperation#createProject(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		JavaProjectCreationDataModel projectModel = (JavaProjectCreationDataModel) ((J2EEArtifactCreationDataModelOld) operationDataModel).getProjectDataModel();
		JavaProjectCreationOperation javaProjectOperation = new JavaProjectCreationOperation(projectModel);
		javaProjectOperation.doRun(monitor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createProject(monitor);
		addServerTarget(monitor);
	}
}