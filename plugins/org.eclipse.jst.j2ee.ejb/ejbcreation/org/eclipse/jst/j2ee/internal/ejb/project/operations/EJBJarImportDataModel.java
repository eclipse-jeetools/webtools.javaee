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

import org.eclipse.jst.j2ee.application.operations.ExtendedImportFactory;
import org.eclipse.jst.j2ee.application.operations.ExtendedImportRegistry;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBJarImportOperation;
import org.eclipse.wst.common.framework.operation.WTPOperation;

public class EJBJarImportDataModel extends J2EEModuleImportDataModel {

	protected J2EEProjectCreationDataModel createJ2EEProjectCreationDataModel() {
		EJBProjectCreationDataModel m = new EJBProjectCreationDataModel();
		m.setProperty(EJBProjectCreationDataModel.CREATE_CLIENT, Boolean.FALSE);
		return m;
	}

	protected int getType() {
		return XMLResource.EJB_TYPE;
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		OpenFailureException cachedException = null;
		try {
			moduleFile = CommonarchiveFactory.eINSTANCE.openEJBJarFile(getArchiveOptions(), uri);
		} catch (OpenFailureException e) {
			cachedException = e;
		}
		if (moduleFile == null) {
			List extendedFactories = ExtendedImportRegistry.getInstance().getFactories(ExtendedImportRegistry.EJB_TYPE);
			for (int i = 0; null == moduleFile && i < extendedFactories.size(); i++) {
				ExtendedImportFactory factory = (ExtendedImportFactory) extendedFactories.get(i);
				moduleFile = factory.openArchive(getArchiveOptions(), uri);
				setProperty(EJBJarImportDataModel.EXTENDED_IMPORT_FACTORY, factory);
			}
		}
		if (moduleFile == null) {
			if (cachedException != null) {
				throw cachedException;
			}
			return false;
		}
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new EJBJarImportOperation(this);
	}
}