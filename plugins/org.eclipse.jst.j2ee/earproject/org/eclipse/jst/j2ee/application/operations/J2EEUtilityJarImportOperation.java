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
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.BinaryProjectHelper;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaProjectSaveStrategyImpl;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEUtilityJarImportOperation extends WTPOperation {

	public J2EEUtilityJarImportOperation(J2EEUtilityJarImportDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEUtilityJarImportDataModel dataModel = (J2EEUtilityJarImportDataModel) operationDataModel;
		if (dataModel.getBooleanProperty(J2EEUtilityJarImportDataModel.OVERWRITE_PROJECT)) {
			IProject project = dataModel.getProject();
			if (project.exists()) {
				project.delete(true, true, new NullProgressMonitor());
			}
		}

		if (!dataModel.getJ2eeArtifactCreationDataModel().getTargetProject().exists()) {
			JavaUtilityJARProjectCreationOperation javaProjectCreationOp = new JavaUtilityJARProjectCreationOperation(dataModel.getJ2eeArtifactCreationDataModel());
			javaProjectCreationOp.run(monitor);
		}

		IProject javaProject = dataModel.getProject();
		Archive jarFile = dataModel.getArchiveFile();

		if (dataModel.getBooleanProperty(J2EEUtilityJarImportDataModel.PRESERVE_PROJECT_METADATA)) {
			BinaryProjectHelper binaryHelper = new BinaryProjectHelper();
			binaryHelper.importArchiveAsBinary(dataModel.getArchiveFile(), dataModel.getProject(), monitor);
			try {
				ProjectUtilities.forceClasspathReload(javaProject);
			} catch (JavaModelException ex) {
				Logger.getLogger().logError(ex);
			}

		} else {
			JavaProjectSaveStrategyImpl strat = new JavaProjectSaveStrategyImpl(javaProject);
			strat.setIncludeProjectMetaFiles(false);
			strat.setShouldIncludeImportedClasses(true);
			strat.setProgressMonitor(new SubProgressMonitor(monitor, 1));
			try {
				jarFile.save(strat);
				ProjectUtilities.appendJavaClassPath(javaProject, JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER"))); //$NON-NLS-1$)
				ProjectUtilities.forceClasspathReload(javaProject);
			} catch (SaveFailureException e) {
				Logger.getLogger().logError(e);
			}
		}
	}

}