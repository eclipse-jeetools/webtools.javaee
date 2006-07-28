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

import java.util.Hashtable;

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

	private IReferenceCountedArchive archive = null;

	protected EnterpriseBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	protected ComponentArchiveOptions getArchiveOptions() {
		ComponentArchiveOptions options = new ComponentArchiveOptions(getComponent());
		options.setIsReadOnly(true);
		options.setRendererType(ArchiveOptions.SAX);
		return options;
	}

	protected IReferenceCountedArchive getUniqueArchive() {
		String archiveURI = getArchiveURI();
		try {
			return openArchive(archiveURI);
		} catch (OpenFailureException e) {
			Logger.getLogger().logError(e);
		}
		return null;
	}

	public Archive accessArchive() {
		IReferenceCountedArchive archive = getArchive();
		archive.access();
		return archive;
	}
	
	protected IReferenceCountedArchive getArchive() {
		if (archive == null) {
			archive = getUniqueArchive();
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
			return null == disc || disc.canImport(anArchive);
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
			} else {
				System.out.println("Somebody else closed this before it should have.");
			}
			archive = null;
		}
	}
	
	protected abstract ArchiveTypeDiscriminator getDiscriminator();
	
	protected IReferenceCountedArchive openArchive(String archiveURI) throws OpenFailureException {
		ArchiveCache cache = ArchiveCache.getInstance();
		IReferenceCountedArchive archive = cache.getArchive(archiveURI);
		if(archive != null){
			archive.access();
			return archive;
		} 
		return cache.openArchive(this, archiveURI);
	}

	public Resource getResource(URI uri) {
		return getArchive().getResourceSet().getResource(uri, true);
	}

	public void releaseAccess(ArtifactEdit edit) {
		dispose();
	}
	
	protected static class ArchiveCache {
		
		private static ArchiveCache instance = null;
		
		public static ArchiveCache getInstance() {
			if(instance == null) {
				instance = new ArchiveCache();
			}
			return instance;
		}
		//necessary to contain two mappings in case the archive changes its URI
		protected Hashtable keysToArchives = new Hashtable();
		protected Hashtable archivesToKeys = new Hashtable();
		
		public synchronized IReferenceCountedArchive getArchive(String archiveURI) {
			IReferenceCountedArchive archive = null;
			if(keysToArchives.containsKey(archiveURI)){
				archive = (IReferenceCountedArchive)keysToArchives.get(archiveURI);
			}
			return archive;
		}
		
		public synchronized IReferenceCountedArchive openArchive(EnterpriseBinaryComponentHelper helper, String archiveURI) throws OpenFailureException {
			ArchiveOptions options = helper.getArchiveOptions();
			Archive anArchive = CommonarchiveFactory.eINSTANCE.primOpenArchive(options, archiveURI);
			
			ArchiveTypeDiscriminator discriminator = helper.getDiscriminator();
			
			if (!discriminator.canImport(anArchive)) {
				anArchive.close();
				throw new OpenFailureException(discriminator.getUnableToOpenMessage());
			}
			IReferenceCountedArchive specificArchive = (IReferenceCountedArchive)discriminator.openArchive(anArchive);
			specificArchive.initializeAfterOpen();
			specificArchive.access();
			keysToArchives.put(archiveURI, specificArchive);
			archivesToKeys.put(specificArchive, archiveURI);
			return specificArchive;
		}
	
		public synchronized void removeArchive(IReferenceCountedArchive archive) {
			Object uri = archivesToKeys.remove(archive);
			keysToArchives.remove(uri);
		}
	}
	
	
	protected interface IReferenceCountedArchive extends Archive {
		
		/**
		 * Increases the reference count by one.  A call to close will decriment the count by one.  If after decrimenting the count the count is 0 
		 *
		 */
		public void access();
		
	}

}
