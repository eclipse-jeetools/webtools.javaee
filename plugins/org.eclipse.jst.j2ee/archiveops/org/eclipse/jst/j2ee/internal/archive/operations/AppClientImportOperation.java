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
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientImportDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationOperation;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.SaveStrategy;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientImportOperation extends J2EEImportOperation {

	public AppClientImportOperation(AppClientImportDataModel model) {
		super(model);
	}

	protected SaveStrategy createSaveStrategy(IProject project) {
		ApplicationClientProjectSaveStrategyImpl saveStrat = new ApplicationClientProjectSaveStrategyImpl(project);
		return saveStrat;
	}

	protected void modifyStrategy(SaveStrategy saveStrat) {
		ApplicationClientProjectSaveStrategyImpl strategy = (ApplicationClientProjectSaveStrategyImpl) saveStrat;
		if (null != strategy.getOverwriteHandler()) {
			strategy.getOverwriteHandler().setAppClientSaveStrategy(strategy);
		}
	}

	protected void createModuleProject(J2EEProjectCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AppClientProjectCreationOperation op = new AppClientProjectCreationOperation((AppClientProjectCreationDataModel) model);
		op.run(monitor);
	}

}