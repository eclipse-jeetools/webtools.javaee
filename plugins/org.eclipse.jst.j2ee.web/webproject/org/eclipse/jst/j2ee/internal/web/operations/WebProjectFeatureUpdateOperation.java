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
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.frameworks.operations.IHeadlessRunnableWithProgress;


/**
 * @version 1.0
 * @author
 */
public class WebProjectFeatureUpdateOperation implements IHeadlessRunnableWithProgress {

	protected WebProjectInfo webProjectInfo;
	protected J2EEWebNatureRuntime nature;


	public WebProjectFeatureUpdateOperation(WebProjectInfo projectInfo) {
		webProjectInfo = projectInfo;
		IProject project = webProjectInfo.getProject();
		nature = (J2EEWebNatureRuntime) J2EEWebNatureRuntimeUtilities.getRuntime(project);
	}

	/*
	 * @see IHeadlessRunnableWithProgress#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

		String[] featureIDs = webProjectInfo.getFeatureIds();

		try {

			if (featureIDs != null && featureIDs.length > 0)
				nature.setFeatureIds(featureIDs);

		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}

	}


}