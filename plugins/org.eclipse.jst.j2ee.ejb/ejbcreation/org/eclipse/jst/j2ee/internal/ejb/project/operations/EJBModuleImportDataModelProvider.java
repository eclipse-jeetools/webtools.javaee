/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.List;

import org.eclipse.jst.j2ee.application.internal.operations.ExtendedImportFactory;
import org.eclipse.jst.j2ee.application.internal.operations.ExtendedImportRegistry;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBModuleImportOperationNew;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is used for to import EJB Modules (from EJB Jar files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EJBModuleImportDataModelProvider extends J2EEModuleImportDataModelProvider implements IEJBComponentImportDataModelProperties{

	protected int getType() {
		return XMLResource.EJB_TYPE;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		OpenFailureException cachedException = null;
		Archive archive = null;
		try {
			archive = CommonarchiveFactory.eINSTANCE.openEJBJarFile(getArchiveOptions(), uri);
		} catch (OpenFailureException e) {
			cachedException = e;
		}
		if (archive == null) {
			List extendedFactories = ExtendedImportRegistry.getInstance().getFactories(ExtendedImportRegistry.EJB_TYPE);
			for (int i = 0; null == getArchiveFile() && i < extendedFactories.size(); i++) {
				ExtendedImportFactory factory = (ExtendedImportFactory) extendedFactories.get(i);
				setArchiveFile(factory.openArchive(getArchiveOptions(), uri));
				setProperty(EXTENDED_IMPORT_FACTORY, factory);
			}
		}
		if (archive == null) {
			if (cachedException != null) {
				throw cachedException;
			}
		}
		return archive;
	}

	public IDataModelOperation getDefaultOperation() {
		return new EJBModuleImportOperationNew(model);
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
	}
}