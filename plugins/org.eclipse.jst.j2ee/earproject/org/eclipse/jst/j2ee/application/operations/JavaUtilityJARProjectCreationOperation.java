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
 * Created on Nov 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationDataModel;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationOperation;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class JavaUtilityJARProjectCreationOperation extends J2EEProjectCreationOperation {
	/**
	 * @param dataModel
	 */
	public JavaUtilityJARProjectCreationOperation(J2EEProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationOperation#createProject(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		JavaProjectCreationDataModel projectModel = (JavaProjectCreationDataModel) ((J2EEProjectCreationDataModel) operationDataModel).getProjectDataModel();
		JavaProjectCreationOperation javaProjectOperation = new JavaProjectCreationOperation(projectModel);
		javaProjectOperation.doRun(monitor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createProject(monitor);
		addServerTarget(monitor);
	}
}