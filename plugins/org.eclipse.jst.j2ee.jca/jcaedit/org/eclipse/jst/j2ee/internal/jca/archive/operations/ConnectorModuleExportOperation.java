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
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactExportOperation;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public final class ConnectorModuleExportOperation extends J2EEArtifactExportOperation {

	/**
	 * @param model
	 */
	public ConnectorModuleExportOperation(J2EEArtifactExportDataModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEExportOperationNEW#archiveString()
	 */
	protected String archiveString() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEExportOperationNEW#createModuleFile()
	 */
	public void createModuleFile() throws SaveFailureException {

		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			RARProjectLoadStrategyImpl ls = new RARProjectLoadStrategyImpl(project);
			if (isExportSource()) {
				ls.setExportSource(true);
			}
			moduleFile = caf.openRARFile(ls, getDestinationPath().toOSString());
			// moduleFile.setSaveFilter(getFilter());
		} catch (Exception e) {
			throw new SaveFailureException(IConnectorNatureConstants.ARCHIVE_OPERATION_OPENINGARCHIVE, e);
		}

	}

	/**
	 * General export method
	 * 
	 * @throws SaveFailureException
	 * @throws CoreException
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	public void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		exportRARProject();
	}

	/**
	 * Does export operation.
	 * 
	 * @throws SaveFailureException
	 */
	public void exportRARProject() throws SaveFailureException {

		try {
			createModuleFile();
			getConnectorFile().saveAsNoReopen(getDestinationPath().toOSString());
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(IConnectorNatureConstants.ARCHIVE_OPERATION_OPENINGARCHIVE, e);
		}// try

	}

	/**
	 * Returns a Connector file.
	 * 
	 * @return RARFile
	 */
	public RARFile getConnectorFile() {
		return (RARFile) moduleFile;
	}

}