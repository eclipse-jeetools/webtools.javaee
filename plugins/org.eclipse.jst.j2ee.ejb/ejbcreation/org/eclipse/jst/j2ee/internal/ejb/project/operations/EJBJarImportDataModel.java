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
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBJarImportOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public final class EJBJarImportDataModel extends J2EEModuleImportDataModel {

	protected J2EEArtifactCreationDataModel createJ2EEProjectCreationDataModel() {
		EJBModuleCreationDataModel m = new EJBModuleCreationDataModel();
		m.setProperty(EJBModuleCreationDataModel.CREATE_CLIENT, Boolean.FALSE);
		return m;
	}

	protected int getType() {
		return XMLResource.EJB_TYPE;
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		OpenFailureException cachedException = null;
		try {
			setArchiveFile(CommonarchiveFactory.eINSTANCE.openEJBJarFile(getArchiveOptions(), uri));
		} catch (OpenFailureException e) {
			cachedException = e;
		}
		if (getArchiveFile() == null) {
			List extendedFactories = ExtendedImportRegistry.getInstance().getFactories(ExtendedImportRegistry.EJB_TYPE);
			for (int i = 0; null == getArchiveFile() && i < extendedFactories.size(); i++) {
				ExtendedImportFactory factory = (ExtendedImportFactory) extendedFactories.get(i);
				setArchiveFile(factory.openArchive(getArchiveOptions(), uri));
				setProperty(EJBJarImportDataModel.EXTENDED_IMPORT_FACTORY, factory);
			}
		}
		if (getArchiveFile() == null) {
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