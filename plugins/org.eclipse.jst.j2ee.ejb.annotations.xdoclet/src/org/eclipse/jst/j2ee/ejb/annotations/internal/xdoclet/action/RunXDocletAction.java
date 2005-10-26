/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAntProjectBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

public class RunXDocletAction extends XDocletActionDelegate {

	public void run(IAction action) {

		if (project != null && (J2EEProjectUtilities.isEJBProject(project.getProject()) || J2EEProjectUtilities.isDynamicWebProject(project.getProject()))) {
			XDocletAntProjectBuilder builder = XDocletAntProjectBuilder.Factory.newInstance(project.getProject());
			IFile sourceFile = getFirstSourceFile();
			
			builder.buildUsingAnt(sourceFile, new NullProgressMonitor());
		}
	}

}
