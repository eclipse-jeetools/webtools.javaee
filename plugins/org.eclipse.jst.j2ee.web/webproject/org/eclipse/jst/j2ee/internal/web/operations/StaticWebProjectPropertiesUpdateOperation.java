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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.web.archive.operations.ContextRootUpdateOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebContentNameUpdateOperation;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;

/**
 * @version 1.0
 * @author
 */
public class StaticWebProjectPropertiesUpdateOperation implements IHeadlessRunnableWithProgress {

	protected WebProjectInfo webProjectInfo;
	protected StaticWebNatureRuntime nature;

	public StaticWebProjectPropertiesUpdateOperation(WebProjectInfo projectInfo) {
		webProjectInfo = projectInfo;
		IProject project = webProjectInfo.getProject();
		nature = (StaticWebNatureRuntime) WebNatureRuntimeUtilities.getRuntime(project);
	}

	/*
	 * @see IHeadlessRunnableWithProgress#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

		IProject project = webProjectInfo.getProject();

		// Update the context root
		String contextRoot = webProjectInfo.getContextRoot();
		if (contextRoot != null) {
			new ContextRootUpdateOperation(project, contextRoot).run(monitor);
		}

		// Update the web content name
		String webContentName = webProjectInfo.getWebContentName();
		if (webContentName != null) {
			new WebContentNameUpdateOperation(project, webContentName).run(monitor);
		}



	}

}