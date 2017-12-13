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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAnnotationPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAntProjectBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

public class RunXDocletAction extends XDocletActionDelegate {

	public void run(IAction action) {

		if (project != null && (J2EEProjectUtilities.isEJBProject(project) || J2EEProjectUtilities.isDynamicWebProject(project))) {

			Job buildJob = new Job("XDoclet") {
				protected IStatus run(IProgressMonitor monitor) {
					IStatus result = Status.OK_STATUS;
					try {
						XDocletAntProjectBuilder builder = XDocletAntProjectBuilder.Factory.newInstance(project);
						IFile sourceFile = getFirstSourceFile();

						builder.buildUsingAnt(sourceFile, new NullProgressMonitor());
					} catch (Exception e) {
						result = new Status(IStatus.ERROR, XDocletAnnotationPlugin.PLUGINID, IStatus.ERROR, "", e); //$NON-NLS-1$
						Logger.logException(e);
					} finally {

					}
					return result;
				}
			};

			try {
				buildJob.setUser(true);
				buildJob.schedule();
			} catch (Exception e) {
			}
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		IFile sourceFile = getFirstSourceFile();
		action.setEnabled(sourceFile != null);
	}

}
