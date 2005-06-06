/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;


import java.io.OutputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;

//TODO delete jsholl
/**
 * @deprecated
 *
 */
public class JavaProjectSaveStrategyImpl extends J2EESaveStrategyImpl {

	/**
	 * Constructor for JavaProjectSaveStrategyImpl.
	 */
	public JavaProjectSaveStrategyImpl(IProject p) {
		super(p);
	}

	/**
	 * @see J2EESaveStrategyImpl#getSourceURIConverter()
	 */
	public WorkbenchURIConverter getSourceURIConverter() {
		if (sourceURIConverter == null) {
			IContainer sourceFolder = JemProjectUtilities.getSourceFolderOrFirst(getProject(), null);
			sourceURIConverter = new WorkbenchURIConverterImpl(sourceFolder);
			sourceURIConverter.setForceSaveRelative(true);
		}
		return sourceURIConverter;
	}

	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		try {
			WorkbenchURIConverter conv = getSourceURIConverter();
			IFile iFile = conv.getOutputFileWithMappingApplied(ArchiveConstants.MANIFEST_URI);
			validateEdit(iFile);
			OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
			aManifest.write(out);
			out.close();
		} catch (Exception ex) {
			throw new SaveFailureException(ex);
		}
	}
}