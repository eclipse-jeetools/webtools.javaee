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
package org.eclipse.jst.j2ee.internal.archive.operations;


import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;

import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Insert the type's description here. Creation date: (4/4/2001 11:14:29 AM)
 * 
 * @author: Administrator
 */
public class ApplicationClientProjectSaveStrategyImpl extends org.eclipse.jst.j2ee.internal.archive.operations.J2EESaveStrategyImpl {
	/**
	 * ApplicationClientProjectSaveStrategyImpl constructor comment.
	 */
	public ApplicationClientProjectSaveStrategyImpl(IProject aProject) {
		super(aProject);
		initializeMofResourceURIList();
	}

	/**
	 * save method comment.
	 */
	public String getProjectName(Archive anArchive) {
		return anArchive.getName().substring(0, anArchive.getName().indexOf(".")); //$NON-NLS-1$

	}

	/**
	 * getSourceURIConverter method comment.
	 */
	public WorkbenchURIConverter getSourceURIConverter() {
		if (sourceURIConverter == null) {
			ApplicationClientNatureRuntime enr = ApplicationClientNatureRuntime.getRuntime(project);
			sourceURIConverter = new WorkbenchURIConverterImpl(enr.getSourceFolder());
			sourceURIConverter.setForceSaveRelative(true);
		}
		return sourceURIConverter;
	}

	protected boolean shouldOverwrite(String uri) {
		if (mofResourceURIList != null && mofResourceURIList.contains(uri))
			return true;
		return super.shouldOverwrite(uri);
	}

	/**
	 * save method comment.
	 */
	public void initializeMofResourceURIList() {

		if (mofResourceURIList == null) {
			mofResourceURIList = new ArrayList();
			mofResourceURIList.add(ArchiveConstants.APP_CLIENT_DD_URI);

		}
	}

}