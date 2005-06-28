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
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public abstract class J2EEComponentSaveStrategyImpl extends ComponentSaveStrategyImpl {

	public J2EEComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualFile vFile = rootFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile iFile = vFile.getUnderlyingFile();
		validateEdit(iFile);
		OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
		try {
			aManifest.write(out);
		} catch (IOException e) {
			Logger.getLogger().logError(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				Logger.getLogger().logError(e);
			}
		}
	}
}
