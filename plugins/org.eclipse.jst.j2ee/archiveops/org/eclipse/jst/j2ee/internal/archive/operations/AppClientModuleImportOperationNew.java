/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 4, 2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientModuleImportOperationNew extends J2EEArtifactImportOperationNew {

	public AppClientModuleImportOperationNew(IDataModel model) {
		super(model);
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		FlexibleJ2EEAppClientSaveStrategyImpl saveStrat = new FlexibleJ2EEAppClientSaveStrategyImpl(virtualComponent);
		return saveStrat;
	}

	protected void createModuleProject(J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEComponentCreationOperation op = (J2EEComponentCreationOperation)model.getDefaultOperation();
		op.run(monitor);
	}

}