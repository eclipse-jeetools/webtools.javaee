/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientComponentExportOperation extends J2EEArtifactExportOperation {

	public AppClientComponentExportOperation(IDataModel model) {
		super(model);
	}

	public void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			AppClientComponentLoadStrategyImpl ls = new AppClientComponentLoadStrategyImpl(getComponent());
			ls.setExportSource(isExportSource());
			setModuleFile(caf.openApplicationClientFile(ls, getDestinationPath().toOSString()));
			getModuleFile().saveAsNoReopen(getDestinationPath().toOSString());
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.ARCHIVE_OPERATION_OpeningArchive, e);
		}
	}

	protected String archiveString() {
		return AppClientArchiveOpsResourceHandler.Application_Client_File_UI_; 
	}

}
