/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 5, 2003
 *  
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.FlexibleJ2EEEJBSaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperationNew;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EJBModuleImportOperationNew extends J2EEArtifactImportOperationNew {

	public EJBModuleImportOperationNew(IDataModel dataModel) {
		super(dataModel);
	}

	protected void importModuleFile(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		if (null == model.getProperty(IEJBComponentImportDataModelProperties.EXTENDED_IMPORT_FACTORY)) {
			super.importModuleFile(monitor);
			// EJBModuleImportDataModel dm = (EJBModuleImportDataModel) getOperationDataModel();
			// IProject project = dm.getProject();
			// dm.getJ2EEModuleCreationDataModel().getJ2EEVersion();
			// if (!J2EEPlugin.hasDevelopmentRole() && project != null &&
			// dm.getJ2EEModuleCreationDataModel().getJ2EEVersion() >=
			// J2EEVersionConstants.J2EE_1_3_ID) {
			// EJBUnknownPrimKeyHelper helper = new EJBUnknownPrimKeyHelper(dm);
			// helper.setUpUnknowPrimaryKey();
			// }
		}
		// else {
		// ExtendedImportFactory factory = (ExtendedImportFactory)
		// operationDataModel.getProperty(J2EEModuleImportDataModel.EXTENDED_IMPORT_FACTORY);
		// factory.importModuleFile((J2EEModuleImportDataModel) operationDataModel, monitor);
		// }
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		FlexibleJ2EEEJBSaveStrategyImpl saveStrat = new FlexibleJ2EEEJBSaveStrategyImpl(virtualComponent);
		return saveStrat;
	}
}