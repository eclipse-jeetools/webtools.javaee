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
package org.eclipse.jst.j2ee.internal.jca.archive.operations;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.EJBArchiveOpsResourceHandler;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EESaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;

import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Implements the save strategy for RAR projects
 */
public class RARProjectSaveStrategyImpl extends J2EESaveStrategyImpl {

	private ConnectorNatureRuntime connRT;

	/**
	 * Default constructor.
	 * 
	 * @param IProject
	 *            aProject - A project using this save strategy.
	 */
	public RARProjectSaveStrategyImpl(IProject aProject) {
		super(aProject);
		this.setConnectorNatureRuntime(ConnectorNatureRuntime.getRuntime(this.getProject()));
	} // RARProjectSaveStrategyImpl

	/**
	 * save method comment.
	 */
	public void initializeMofResourceURIList() {

		if (mofResourceURIList == null) {
			mofResourceURIList = new ArrayList();
			mofResourceURIList.add(J2EEConstants.RAR_DD_URI);
		}
	}

	/**
	 * Returns the source URI
	 * 
	 * @return WorkbenchURIConverter
	 */
	public WorkbenchURIConverter getSourceURIConverter() {
		if (sourceURIConverter == null) {
			ConnectorNatureRuntime enr = ConnectorNatureRuntime.getRuntime(project);
			sourceURIConverter = new WorkbenchURIConverterImpl(enr.getSourceFolder());
			sourceURIConverter.setForceSaveRelative(true);
		}
		return sourceURIConverter;
	} // getSourceURIConverter

	/**
	 * saves the archive
	 */
	public void save(File aFile, InputStream in) throws SaveFailureException {
		String currentUri = aFile.getURI();
		try {
			String displayString = EJBArchiveOpsResourceHandler.getString("IMPORT_OPERATION_STRING"); //$NON-NLS-1$
			progressMonitor.subTask(displayString + aFile.getURI());
			WorkbenchURIConverter conv = null;
			if (isProjectMetaFile(aFile.getURI()))
				conv = getProjectMetaURIConverter();
			else
				conv = getSourceURIConverter();
			OutputStream out = null;

			IFile iFile = conv.getOutputFileWithMappingApplied(currentUri);
			validateEdit(iFile);
			out = new WorkbenchByteArrayOutputStream(iFile);
			ArchiveUtil.copy(in, out);
			worked(1);
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveFile") + aFile.getName(); //$NON-NLS-1$
			throw new SaveFailureException(errorString, e);
		} // try

	} // save

	/**
	 * Gets the project name
	 * 
	 * @return String
	 */
	public String getProjectName() {
		return this.getArchive().getName().substring(0, this.getArchive().getName().indexOf(".")); //$NON-NLS-1$
	} // getProjectName

	protected boolean shouldOverwrite(String uri) {
		if (mofResourceURIList != null && mofResourceURIList.contains(uri))
			return true;
		return super.shouldOverwrite(uri);
	}

	/**
	 * Gets the connection runtime nature .
	 * 
	 * @return ConnectorNatureRuntime
	 */
	public ConnectorNatureRuntime getConnectorNatureRuntime() {
		return connRT;
	} // getConnectorNatureRuntime

	/**
	 * Sets the connection runtime nature
	 * 
	 * @param ConnectorNatureRuntime
	 *            connRT - The connector nature runtime.
	 */
	public void setConnectorNatureRuntime(ConnectorNatureRuntime connRT) {
		this.connRT = connRT;
	} // setConnectorNatureRuntime

} // RARProjectSaveStrategyImpl
