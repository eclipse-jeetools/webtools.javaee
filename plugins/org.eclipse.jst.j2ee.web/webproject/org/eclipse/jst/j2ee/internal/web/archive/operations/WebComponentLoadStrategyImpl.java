/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.ComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class WebComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	public WebComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public List getFiles() {
		super.getFiles();
		addLooseLibJARsToFiles();
		return filesHolder.getFiles();
	}

	public IVirtualReference[] getLibModules() {
		WebArtifactEdit webArtifactEdit = null;
		try {
			webArtifactEdit = (WebArtifactEdit) ComponentUtilities.getArtifactEditForRead(getComponent());
			if (webArtifactEdit != null)
				return webArtifactEdit.getLibModules();
		} finally {
			if (webArtifactEdit != null)
				webArtifactEdit.dispose();
		}
		return null;

	}

	public void addLooseLibJARsToFiles() {
		IVirtualReference[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			IVirtualReference iLibModule = libModules[i];
			IVirtualComponent looseComponent = iLibModule.getReferencedComponent();
			if (looseComponent.isBinary()) {
				VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) looseComponent;
				java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
				String uri = iLibModule.getRuntimePath().makeRelative().toString() + "/" + diskFile.getName(); //$NON-NLS-1$
				addExternalFile(uri, diskFile);
			} else {
				String uri = iLibModule.getRuntimePath().makeRelative().toString() + "/" + looseComponent.getName() + ".jar";  //$NON-NLS-1$//$NON-NLS-2$
				try {
					Archive utilJAR = J2EEProjectUtilities.asArchive(uri, looseComponent.getProject(), isExportSource());
					if (utilJAR == null)
						continue;
					filesHolder.addFile(utilJAR);
				} catch (OpenFailureException oe) {
					String message = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.UNABLE_TO_LOAD_MODULE_ERROR_, new Object[]{uri, getComponent().getProject().getName(), oe.getConcatenatedMessages()}); //$NON-NLS-1$
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logTrace(message);
				}
			}
		}
	}

}
