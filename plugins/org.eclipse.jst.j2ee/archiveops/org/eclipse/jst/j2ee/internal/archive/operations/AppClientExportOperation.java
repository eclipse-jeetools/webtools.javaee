/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.application.operations.AppClientExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;


public class AppClientExportOperation extends J2EEExportOperation {
	/**
	 * @param model
	 */
	public AppClientExportOperation(AppClientExportDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEExportOperationNEW#export()
	 */
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
		return (ApplicationClientFile) moduleFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEExportOperationNEW#archiveString()
	 */
	protected String archiveString() {
		return AppClientArchiveOpsResourceHandler.getString("Application_Client_File_UI_"); //$NON-NLS-1$ = "Application Client File"
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEExportOperationNEW#createModuleFile()
	 */
	public void createModuleFile() throws SaveFailureException {

		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			ApplicationClientProjectLoadStrategyImpl ls = new ApplicationClientProjectLoadStrategyImpl(project);
			if (isExportSource())
				ls.setExportSource(true);
			moduleFile = caf.openApplicationClientFile(ls, getDestinationPath().toOSString());
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"), e); //$NON-NLS-1$
		}
	}

}