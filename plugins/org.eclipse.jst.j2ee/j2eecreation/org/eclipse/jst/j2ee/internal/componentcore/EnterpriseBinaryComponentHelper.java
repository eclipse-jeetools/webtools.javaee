/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.wst.common.componentcore.internal.BinaryComponentHelper;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * @deprecated see {@link JavaEEBinaryComponentHelper}
 * @author jasholl
 *
 */
public abstract class EnterpriseBinaryComponentHelper extends BinaryComponentHelper {

	public static EnterpriseBinaryComponentHelper getHelper(IVirtualComponent aComponent){
		EnterpriseBinaryComponentHelper helper = null;
		if (JavaEEProjectUtilities.isEJBComponent(aComponent)) {
			helper = new EJBBinaryComponentHelper(aComponent);
		} else if (JavaEEProjectUtilities.isApplicationClientComponent(aComponent)) {
			helper = new AppClientBinaryComponentHelper(aComponent);
		} else if (JavaEEProjectUtilities.isJCAComponent(aComponent)) {
			helper = new JCABinaryComponentHelper(aComponent);
		} else if (JavaEEProjectUtilities.isDynamicWebComponent(aComponent)) {
			helper = new WebBinaryComponentHelper(aComponent);
		} else {
			helper = new UtilityBinaryComponentHelper(aComponent);
		}
		return helper;
	}
	
	IReferenceCountedArchive archive = null;

	protected EnterpriseBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	protected ComponentArchiveOptions getArchiveOptions() {
		ComponentArchiveOptions options = new ComponentArchiveOptions(getComponent());
		options.setIsReadOnly(true);
		options.setRendererType(ArchiveOptions.SAX);
		options.setUseJavaReflection(false);
		return options;
	}

	protected IReferenceCountedArchive getUniqueArchive() {
		try {
			return openArchive();
		} catch (OpenFailureException e) {
			J2EEPlugin.logError(e);
		}
		return null;
	}

	public Archive accessArchive() {
		IReferenceCountedArchive archive = getArchive();
		archive.access();
		if(!isPhysicallyOpen(archive)){
			physicallyOpen(archive);
		}
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
		java.io.File file = getComponent().getAdapter(java.io.File.class);
		return file.getAbsolutePath();
	}

	@Override
	public void dispose() {
		if (archive != null) {
			archive.close();
			archive = null;
		}
	}

	protected abstract ArchiveTypeDiscriminator getDiscriminator();

	protected IReferenceCountedArchive openArchive() throws OpenFailureException {
		ArchiveCache cache = ArchiveCache.getInstance();
		IReferenceCountedArchive archive = cache.getArchive(getComponent());
		if (archive != null) {
			archive.access();
			return archive;
		}
		return cache.openArchive(this);
	}

	boolean gotResource = false;
	
	@Override
	public Resource getResource(URI uri) {
		Resource resource = null;
		if(!isPhysicallyOpen(getArchive())){
			resource = getArchive().getResourceSet().getResource(uri, false);
			if(resource == null){
				physicallyOpen(getArchive());
			}
		}
		if(resource == null){
			resource = getArchive().getResourceSet().getResource(uri, true); 
		}
		
		return resource;
	}

	public void releaseAccess() {
		dispose();
	}

	private static void unloadArchive(IReferenceCountedArchive archive) {
		WorkbenchResourceHelperBase.removeAndUnloadAll(archive.getResourceSet().getResources(), archive.getResourceSet());
		archive.getLoadStrategy().setResourceSet(null);
		archive.setLoadStrategy(null);
	}
	
	private static boolean isPhysicallyOpen(IReferenceCountedArchive archive) {
		return ((BinaryZipFileLoadStrategy)archive.getLoadStrategy()).isPhysicallyOpen();
	}
	
	private static void physicallyOpen(IReferenceCountedArchive archive) {
		try {
			((BinaryZipFileLoadStrategy)archive.getLoadStrategy()).physicallyOpen();
		} catch (ZipException e) {
			J2EEPlugin.logError(e);
		} catch (IOException e) {
			J2EEPlugin.logError(e);
		}
	}
	
	protected static void physicallyClose(IReferenceCountedArchive archive) {
		((BinaryZipFileLoadStrategy)archive.getLoadStrategy()).physicallyClose();
	}
	
	protected void aboutToClose() {
		//default is to do nothing
	}
	
	protected void preCleanupAfterTempSave(String uri, File original, File destinationFile) {
		//default is to do nothing
	}
	protected void postCleanupAfterTempSave(String uri, File original, File destinationFile) {
		//default is to do nothing
	}

	private static class BinaryZipFileLoadStrategy extends ZipFileLoadStrategyImpl {
		
		private boolean physicallyOpen = true;
		
		public BinaryZipFileLoadStrategy(java.io.File file) throws IOException {
			super(file);
		}
		
		@Override
		public void close() {
			physicallyOpen = false;
			super.close();
		}

		public boolean isPhysicallyOpen(){
			return physicallyOpen;
		}
		
		public void physicallyOpen() throws ZipException, IOException{
			if(!isPhysicallyOpen()){
				physicallyOpen = true;
				setZipFile(ArchiveUtil.newZipFile(file));
			}
		}
		
		public void physicallyClose(){
			if(isPhysicallyOpen()){
				physicallyOpen = false;
				try{
					zipFile.close();
				}
				catch (Throwable t) {
					//Ignore
				}
			} 
		}
		
		@Override
		public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
			final boolean isPhysciallyOpen = isPhysicallyOpen();
			Exception caughtException = null;
			try {
				if (!isPhysciallyOpen) {
					physicallyOpen();
				}
				ZipEntry entry = getZipFile().getEntry(uri);
				if (entry == null)
					throw new FileNotFoundException(uri);

				return new java.io.BufferedInputStream(getZipFile().getInputStream(entry)) {
					@Override
					public void close() throws IOException {
						super.close();
						if (!isPhysciallyOpen ) {
							physicallyClose();
						}
					}
				};
			} catch (FileNotFoundException e) {
				caughtException = e;
				throw e;
			} catch (IllegalStateException zipClosed) {
				caughtException = zipClosed;
				throw new IOException(zipClosed.toString());
			} catch (Exception e) {
				caughtException = e;
				throw new IOException(e.toString());
			} finally {
				if (caughtException != null) {
					if (!isPhysciallyOpen) {
						physicallyClose();
					}
				}
			}
		}
	}
	
	public static class ArchiveCache {

		private static ArchiveCache instance = null;

		public static ArchiveCache getInstance() {
			if (instance == null) {
				instance = new ArchiveCache();
			}
			return instance;
		}

		protected Map componentsToArchives = new Hashtable();

		public synchronized IReferenceCountedArchive getArchive(IVirtualComponent component) {
			IReferenceCountedArchive archive = (IReferenceCountedArchive) componentsToArchives.get(component);
			return archive;
		}

		public synchronized void clearDisconnectedArchivesInEAR(IVirtualComponent earComponent) {
			if (componentsToArchives.isEmpty()) {
				return;
			}
			Set liveBinaryComponnts = new HashSet();
			IVirtualReference[] refs = earComponent.getReferences();
			IVirtualComponent component = null;
			for (int i = 0; i < refs.length; i++) {
				component = refs[i].getReferencedComponent();
				if (component.isBinary()) {
					liveBinaryComponnts.add(component);
				}
			}
			clearArchivesInProject(earComponent.getProject(), liveBinaryComponnts);
		}

		public synchronized void clearAllArchivesInProject(IProject earProject) {
			if (componentsToArchives.isEmpty()) {
				return;
			}
			clearArchivesInProject(earProject, null);
		}

		private void clearArchivesInProject(IProject earProject, Set excludeSet) {
			Iterator iterator = componentsToArchives.entrySet().iterator();
			IVirtualComponent component = null;
			IReferenceCountedArchive archive = null;
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry)iterator.next();
				component = (IVirtualComponent) entry.getKey();
				if (component.getProject().equals(earProject) && (excludeSet == null || !excludeSet.contains(component))) {
					archive = (IReferenceCountedArchive) entry.getValue();
					archive.forceClose();
					unloadArchive(archive);
					iterator.remove();
				}
			}
		}

		public synchronized IReferenceCountedArchive openArchive(EnterpriseBinaryComponentHelper helper) throws OpenFailureException {
			ArchiveOptions options = helper.getArchiveOptions();
			String archiveURI = helper.getArchiveURI();
			
			options.setLoadStrategy(createBinaryLoadStrategy(helper));

			Archive anArchive = CommonarchiveFactory.eINSTANCE.primOpenArchive(options, archiveURI);

			ArchiveTypeDiscriminator discriminator = helper.getDiscriminator();

			if (!discriminator.canImport(anArchive)) {
				anArchive.close();
				throw new OpenFailureException(discriminator.getUnableToOpenMessage());
			}
			IReferenceCountedArchive specificArchive = (IReferenceCountedArchive) discriminator.openArchive(anArchive);
			specificArchive.setEnterpriseBinaryComponentHelper(helper);
			specificArchive.initializeAfterOpen();
			specificArchive.access();
			componentsToArchives.put(helper.getComponent(), specificArchive);
			return specificArchive;
		}
	}
	
	protected static BinaryZipFileLoadStrategy createBinaryLoadStrategy(EnterpriseBinaryComponentHelper helper) throws OpenFailureException {
		String archiveURI = helper.getArchiveURI();
		String filename = archiveURI.replace('/', java.io.File.separatorChar);
		java.io.File file = new java.io.File(filename);
		if (!file.exists()) {
			throw new OpenFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_not_found_EXC_, (new Object[] { archiveURI, file.getAbsolutePath() }))); 
		}
		try {
			BinaryZipFileLoadStrategy strategy = new BinaryZipFileLoadStrategy(file);
			return strategy;
		} catch (IOException ex) {
			throw new OpenFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.could_not_open_EXC_, (new Object[] { archiveURI })), ex); 
		}
	}

	protected interface IReferenceCountedArchive extends Archive {

		/**
		 * Increases the reference count by one. A call to close will decriment
		 * the count by one. If after decrimenting the count the count is 0
		 * 
		 */
		public void access();

		public void forceClose();
		
		public void setEnterpriseBinaryComponentHelper(EnterpriseBinaryComponentHelper helper);
		
		public EnterpriseBinaryComponentHelper getEnterpriseBinaryComponentHelper();
		
	}

}
