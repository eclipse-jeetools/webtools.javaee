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
package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.BinaryComponentHelper;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public abstract class EnterpriseBinaryComponentHelper extends BinaryComponentHelper {

	private Archive archive = null;

	protected EnterpriseBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	protected ComponentArchiveOptions getArchiveOptions() {
		ComponentArchiveOptions options = new ComponentArchiveOptions(getComponent());
		options.setIsReadOnly(true);
		options.setRendererType(ArchiveOptions.SAX);
		return options;
	}

	public Archive getArchive() {
		if (archive == null) {
			String archiveURI = getArchiveURI();
			try {
				archive = openArchive(archiveURI);
			} catch (OpenFailureException e) {
				Logger.getLogger().logError(e);
			}
		}
		return archive;
	}

	protected boolean isArchiveValid() {
		if (archive != null) {
			return true;
		}
		Archive anArchive = null;
		try {
			anArchive = CommonarchiveFactory.eINSTANCE.primOpenArchive(getArchiveOptions(), getArchiveURI());
			ArchiveTypeDiscriminator disc = getDiscriminator();
			return disc.canImport(anArchive);
		} catch (Exception e) {
			return false;
		} finally {
			if (anArchive != null) {
				anArchive.close();
			}
		}
	}

	protected String getArchiveURI() {
		String archiveURI = null;
		VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) getComponent();
		java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
		if (diskFile.exists())
			archiveURI = diskFile.getAbsolutePath();
		else {
			IFile iFile = archiveComp.getUnderlyingWorkbenchFile();
			archiveURI = iFile.getRawLocation().toOSString();
		}
		return archiveURI;
	}

	public void dispose() {
		if (archive != null) {
			if (archive.isOpen()) {
				archive.close();
			}
			archive = null;
		}
	}

	protected abstract ArchiveTypeDiscriminator getDiscriminator();

	protected abstract Archive openArchive(String archiveURI) throws OpenFailureException;

	public Resource getResource(URI uri) {
		return getArchive().getResourceSet().getResource(uri, true);
	}

	public void releaseAccess(ArtifactEdit edit) {
	}

}
