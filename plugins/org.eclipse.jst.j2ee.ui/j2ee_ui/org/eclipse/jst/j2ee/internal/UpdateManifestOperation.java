/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateManifestOperation implements org.eclipse.jface.operation.IRunnableWithProgress {
	protected String projectName;
	protected String classPathValue;
	protected boolean replace;
/**
 * UpdateManifestOperation constructor comment.
 */
public UpdateManifestOperation(String aProjectName, String aSpaceDelimitedPath, boolean replaceInsteadOfMerge) {
	super();
	projectName = aProjectName;
	classPathValue = aSpaceDelimitedPath;
	replace = replaceInsteadOfMerge;
}
protected IProject getProject() {
	return J2EEPlugin.getWorkspace().getRoot().getProject(projectName);
}
/**
 * Runs this operation.  Progress should be reported to the given progress monitor.
 * This method is usually invoked by an <code>IRunnableContext</code>'s <code>run</code> method,
 * which supplies the progress monitor.
 * A request to cancel the operation should be honored and acknowledged 
 * by throwing <code>InterruptedException</code>.
 *
 * @param monitor the progress monitor to use to display progress and receive
 *   requests for cancelation
 * @exception InvocationTargetException if the run method must propagate a checked exception,
 * 	it should wrap it inside an <code>InvocationTargetException</code>; runtime exceptions are automatically
 *  wrapped in an <code>InvocationTargetException</code> by the calling context
 * @exception InterruptedException if the operation detects a request to cancel, 
 *  using <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing 
 *  <code>InterruptedException</code>
 *
 * @see IRunnableContext#run
 */
public void run(org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException, InterruptedException {
	IProject p = getProject();
	try {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(p);
		if (mf == null)
			mf = new ArchiveManifestImpl();
		mf.addVersionIfNecessary();
		if (replace)
			mf.setClassPath(classPathValue);
		else
			mf.mergeClassPath(ArchiveUtil.getTokens(classPathValue));
		J2EEProjectUtilities.writeManifest(p, mf);
	} catch (java.io.IOException ex) {
		throw new WFTWrappedException(ex);
	}
}
}
