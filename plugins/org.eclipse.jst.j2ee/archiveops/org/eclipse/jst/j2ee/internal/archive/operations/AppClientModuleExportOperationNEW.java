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
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientModuleExportOperationNEW extends J2EEArtifactExportOperationNEW {

	public AppClientModuleExportOperationNEW(IDataModel model) {
		super(model);
	}

	public void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		exportApplicationClientProject();
	}

	public void exportApplicationClientProject() throws SaveFailureException {

		try {
			createModuleFile();
			getApplicationClientFile().saveAsNoReopen(getDestinationPath().toOSString());
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"), e);//$NON-NLS-1$
		}

	}

	public ApplicationClientFile getApplicationClientFile() {
		return (ApplicationClientFile) getModuleFile();
	}

	protected String archiveString() {
		return AppClientArchiveOpsResourceHandler.getString("Application_Client_File_UI_"); //$NON-NLS-1$ = "Application Client File"
	}

	public void createModuleFile() throws SaveFailureException {

		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			FlexibleApplicationClientLoadStrategyImpl ls = new FlexibleApplicationClientLoadStrategyImpl(getComponent());
			ls.setExportSource(isExportSource());
			setModuleFile(caf.openApplicationClientFile(ls, getDestinationPath().toOSString()));
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"), e); //$NON-NLS-1$
		}
	}

}
