/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 13, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.common.internal.modulecore.util;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateManifestOperation extends AbstractDataModelOperation {

	public UpdateManifestOperation(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable adaptable) throws ExecutionException {
		IFile file = (IFile)model.getProperty(UpdateManifestDataModelProperties.MANIFEST_FILE);
		
		String classPathValue = model.getStringProperty(UpdateManifestDataModelProperties.JAR_LIST_TEXT_UI);
		try {
			if(file != null && file.exists()) {
				ArchiveManifest mf = ManifestUtilities.readManifest(file);
				
				if (mf == null)
					mf = new ArchiveManifestImpl();
				mf.addVersionIfNecessary();
				if (model.getBooleanProperty(UpdateManifestDataModelProperties.MERGE)) {
					mf.mergeClassPath(ManifestUtilities.getTokens(classPathValue));
				} else {
					mf.setClassPath(classPathValue);
				}
				if (model.isPropertySet(UpdateManifestDataModelProperties.MAIN_CLASS)) {
					mf.setMainClass(model.getStringProperty(UpdateManifestDataModelProperties.MAIN_CLASS));
				}
	
				ManifestUtilities.writeManifest(file, mf);
			}
		} catch (java.io.IOException ex) {
			throw new ExecutionException(ex.getMessage(),ex);
		}
		return OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}
	
	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}
}
