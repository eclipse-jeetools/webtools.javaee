/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.strategy;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.SaveFilterImpl;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;


/**
 * Abstract implementer off which any save strategy can subclass
 * 
 * @see com.ibm.etools.archive.SaveStrategy
 */

public abstract class SaveStrategyImpl extends ArchiveStrategyImpl implements SaveStrategy {
	protected SaveFilter filter;

	/**
	 * SaveStrategyImpl constructor comment.
	 */
	public SaveStrategyImpl() {
		super();
	}

	/**
	 * The default is to do nothing - subclasses should override as necessary
	 */
	public void close() throws java.io.IOException {
	}

	protected abstract SaveStrategy createNestedSaveStrategy(Archive anArchive) throws java.io.IOException;

	/**
	 * The default is to do nothing - subclasses should override as necessary
	 */
	public void finish() throws java.io.IOException {
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 4:11:28 PM)
	 * 
	 * @return com.ibm.etools.archive.SaveFilter
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.helpers.SaveFilter getFilter() {
		if (filter == null)
			filter = new SaveFilterImpl();
		return filter;
	}

	protected abstract OutputStream getOutputStreamForResource(Resource aResource) throws java.io.IOException;

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public boolean isDirectory() {
		return false;
	}

	protected boolean isLoadedResourceOrManifest(File aFile) {
		return getArchive().isMofResourceLoaded(aFile.getURI()) || aFile.getURI().equals(ArchiveConstants.MANIFEST_URI);
	}

	public void save() throws SaveFailureException {

		saveManifest();
		saveMofResources();
		saveFiles();

	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void save(Archive anArchive) throws SaveFailureException {
		SaveStrategy strat = null;
		try {
			strat = createNestedSaveStrategy(anArchive);
		} catch (java.io.IOException iox) {
			throw new SaveFailureException(anArchive.getURI(), iox);
		}
		anArchive.save(strat);

	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void save(File aFile, FileIterator iterator) throws SaveFailureException {
		if (aFile.isArchive() && shouldIterateOver((Archive) aFile))
			save((Archive) aFile);
		else {
			InputStream in = null;
			if (!aFile.isDirectoryEntry()) {
				try {
					in = iterator.getInputStream(aFile);
				} catch (IOException ex) {
					throw new SaveFailureException(aFile.getURI(), ex);
				}
			}
			save(aFile, in);
		}
	}

	public abstract void save(File aFile, InputStream in) throws SaveFailureException;

	protected void saveFiles() throws SaveFailureException {
		try {
			FileIterator iterator = getArchive().getFilesForSave();
			while (iterator.hasNext()) {
				File aFile = iterator.next();
				if (shouldSave(aFile))
					save(aFile, iterator);
			}
		} catch (IOException iox) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString("Error_occurred_iterating_f_EXC_"), iox); //$NON-NLS-1$ = "Error occurred iterating files"
		}
	}

	protected void saveManifest() throws SaveFailureException {
		if (!shouldSave(ArchiveConstants.MANIFEST_URI))
			return;
		ArchiveManifest mf = getArchive().getManifest();
		if (mf.getManifestVersion() == null || mf.getManifestVersion().equals(""))//$NON-NLS-1$
			mf.setManifestVersion("1.0");//$NON-NLS-1$
		save(mf);
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void saveMofResource(org.eclipse.emf.ecore.resource.Resource aResource) throws SaveFailureException {
		if (!shouldSave(aResource))
			return;
		setEncoding(aResource);
		try {
			boolean wasModified = aResource.isModified();
			OutputStream os = getOutputStreamForResource(aResource);
			saveMofResource(aResource, os);
			aResource.setModified(wasModified);
		} catch (Exception e) {
			throw new SaveFailureException(aResource.getURI().toString(), e);
		}
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	protected void saveMofResource(org.eclipse.emf.ecore.resource.Resource aResource, OutputStream os) throws IOException {
		aResource.save(os, Collections.EMPTY_MAP);
	}

	protected void saveMofResources() throws SaveFailureException {
		Iterator iterator = getArchive().getLoadedMofResources().iterator();
		//We have to go through this process because java resources could get added during the
		//process of iterating and saving, and adding to a collection while iterating causes a
		// failure
		List xmiResources = new ArrayList();
		//We will save the xmi resources first, then the xml resources. This way the
		//any necessary id's for referenced objects will have been generated prior to save
		List xmlResources = new ArrayList();
		Resource res = null;
		while (iterator.hasNext()) {
			res = (Resource) iterator.next();
			//		We don't want to save the java reflection resources or uri mapped resources
			if (!ArchiveUtil.isJavaResource(res) && !ArchiveUtil.isRegisteredURIMapping(res) && !ArchiveUtil.isPlatformMetaResource(res)) {
				Revisit.revisit();
				//For now, always use the mof5 format
				if (res instanceof CompatibilityXMIResource)
					((CompatibilityXMIResource) res).setFormat(CompatibilityXMIResource.FORMAT_MOF5);
				if (res instanceof TranslatorResource)
					xmlResources.add(res);
				else {
					xmiResources.add(res);
				}
			}
		}
		basicSaveMofResources(xmiResources);
		basicSaveMofResources(xmlResources);
	}

	protected void basicSaveMofResources(List resources) throws SaveFailureException {
		Resource res = null;
		for (int i = 0; i < resources.size(); i++) {
			res = (Resource) resources.get(i);
			saveMofResource(res);

		}
	}

	protected void setEncoding(Resource aResource) {
		if (aResource instanceof org.eclipse.emf.ecore.xmi.XMLResource)
			((org.eclipse.emf.ecore.xmi.XMLResource) aResource).setEncoding(archive.getXmlEncoding());
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 4:11:28 PM)
	 * 
	 * @param newFilter
	 *            com.ibm.etools.archive.SaveFilter
	 */
	public void setFilter(org.eclipse.jst.j2ee.commonarchivecore.helpers.SaveFilter newFilter) {
		filter = newFilter;
	}

	/**
	 * Answer whether the nested archive needs to be saved one file at a time, or if we can bulk
	 * save it from the original
	 */
	protected boolean shouldIterateOver(Archive anArchive) {
		return anArchive.getLoadStrategy().requiresIterationOnSave();
	}

	protected boolean shouldSave(File aFile) {
		boolean loaded = isLoadedResourceOrManifest(aFile);
		if (loaded) {
			Resource res = archive.getLoadStrategy().getExistingMofResource(aFile.getURI());
			if (res == null)
				return false;
			//must be manifest
			return !shouldSave(res);
		}

		if (getArchive().isModuleFile()) {
			ModuleFile m = (ModuleFile) getArchive();
			if (m.getExportStrategy() != null && m.getExportStrategy().hasSaved(aFile.getURI()))
				return false;
		}
		return shouldSave(aFile.getURI());
	}

	protected boolean shouldSave(Resource res) {
		if (!res.isModified() && getArchive().getOptions().saveOnlyDirtyMofResources())
			return false;

		return shouldSave(res.getURI().toString());
	}


	/**
	 * This is the one method through which all elements of an archive (file, nested archive, mof
	 * resource, or manifest) will be tested before saving. Subclasses can override to do something
	 * more specific
	 */
	protected boolean shouldSave(String uri) {
		return getFilter().shouldSave(uri, getArchive());
	}
}
