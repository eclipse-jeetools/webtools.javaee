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
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationOperation;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class JavaUtilityComponentCreationOperation extends ComponentCreationOperation {
	/**
	 * @param dataModel
	 */
	public JavaUtilityComponentCreationOperation(JavaComponentCreationDataModel dataModel) {
		super(dataModel);
	}


	 protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	        super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
	    }


	protected void createAndLinkJ2EEComponents() throws CoreException {
		// TODO Auto-generated method stub
		
	}


	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		
	}


	protected String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
}