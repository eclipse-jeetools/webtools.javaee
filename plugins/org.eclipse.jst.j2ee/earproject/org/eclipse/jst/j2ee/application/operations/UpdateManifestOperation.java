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
 * Created on Nov 13, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateManifestOperation extends WTPOperation {

	public UpdateManifestOperation(UpdateManifestDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		UpdateManifestDataModel dataModel = (UpdateManifestDataModel) operationDataModel;
		IProject project = dataModel.getProject();


		String manifestFolder = dataModel.getStringProperty(UpdateManifestDataModel.MANIFEST_FOLDER);
		IContainer container = project.getFolder( manifestFolder );
		IFile file = container.getFile( new Path(J2EEConstants.MANIFEST_SHORT_NAME));
		
		
		
		String classPathValue = dataModel.getClasspathAsString();
		try {
			ArchiveManifest mf = J2EEProjectUtilities.readManifest(file);
			
			if (mf == null)
				mf = new ArchiveManifestImpl();
			mf.addVersionIfNecessary();
			if (dataModel.getBooleanProperty(UpdateManifestDataModel.MERGE)) {
				mf.mergeClassPath(ArchiveUtil.getTokens(classPathValue));
			} else {
				mf.setClassPath(classPathValue);
			}
			if (dataModel.isSet(UpdateManifestDataModel.MAIN_CLASS)) {
				mf.setMainClass(dataModel.getStringProperty(UpdateManifestDataModel.MAIN_CLASS));
			}

			J2EEProjectUtilities.writeManifest(file, mf);
		} catch (java.io.IOException ex) {
			throw new WFTWrappedException(ex);
		}
	}

}