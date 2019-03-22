/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.io.FileNotFoundException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ExportStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


/**
 * @generated
 */
public abstract class ModuleFileImpl extends ArchiveImpl implements ModuleFile {

	/** Implementer for extracting meta-data from this archive into the root object */
	protected ImportStrategy importStrategy;
	/** Implementer for adding meta-data to an about-to-be-exported archive */
	protected ExportStrategy exportStrategy;

	public ModuleFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.MODULE_FILE;
	}

	protected boolean canLazyInitialize() {
		return getImportStrategy() != null;
	}

	public Resource getDeploymentDescriptorResource() throws java.io.FileNotFoundException, ResourceLoadException {
		return getMofResource(getDeploymentDescriptorUri());
	}

	/**
	 * Subclasses must override
	 */
	public abstract String getDeploymentDescriptorUri();

	/**
	 * @see com.ibm.etools.commonarchive.ModuleFile
	 */
	@Override
	public EARFile getEARFile() {
		Container aContainer = getContainer();
		if (aContainer == null || !aContainer.isEARFile()) {
			return null;
		}
		return ((EARFile) aContainer);
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.ExportStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ExportStrategy getExportStrategy() {
		return exportStrategy;
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.ImportStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy getImportStrategy() {
		return importStrategy;
	}

	/**
	 * Returns the specification version of the module file, or empty string if unknown version. For
	 * example, "2.0"
	 * 
	 * @deprecated, Use getDeploymentDescriptorResource().getModuleVersionID();
	 */
	public String getSpecVersion() {
		float ver = getSpecVersionID();
		Float specVersion = new Float(ver / 10);
		return specVersion.toString();
	}

	/**
	 * Return the version ID of the module For example, "20"
	 * 
	 * @return int
	 */
	public int getSpecVersionID() {
		try {
			return ((XMLResource) getDeploymentDescriptorResource()).getModuleVersionID();
		} catch (Exception e) {
			throw new ArchiveRuntimeException(e);
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.ModuleFile
	 */
	public abstract boolean isDeploymentDescriptorSet();

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	@Override
	public boolean isModuleFile() {
		return true;
	}



	/**
	 * subclasses must override
	 */
	public abstract EObject makeDeploymentDescriptor(XMLResource resource);

	public Resource makeDeploymentDescriptorResource() {
		XMLResource resource = null;
		try {
			resource = (XMLResource) makeMofResource(getDeploymentDescriptorUri());
		} catch (DuplicateObjectException ex) {
			try {
				return getDeploymentDescriptorResource();
			} catch (java.io.FileNotFoundException fnfEx) {
				//Ignore
			}
		}
		makeDeploymentDescriptor(resource);
		return resource;
	}

	@Override
	public void save(SaveStrategy aSaveStrategy) throws SaveFailureException {
		setSaveStrategy(aSaveStrategy);
		if (getExportStrategy() != null)
			getExportStrategy().preSave(aSaveStrategy);
		super.save(aSaveStrategy);
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newExportStrategy
	 *            com.ibm.etools.archive.ExportStrategy
	 */
	public void setExportStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ExportStrategy newExportStrategy) {
		exportStrategy = newExportStrategy;
		if (newExportStrategy != null) {
			newExportStrategy.setArchive(this);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newImportStrategy
	 *            com.ibm.etools.archive.ImportStrategy
	 */
	public void setImportStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy newImportStrategy) {
		importStrategy = newImportStrategy;
		if (newImportStrategy != null) {
			newImportStrategy.setArchive(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.commonarchive.ModuleFile#setJ2EEVersion(int)
	 */
	public void setJ2EEVersion(int versionID) {
		try {
			((XMLResource) getDeploymentDescriptorResource()).setVersionID(versionID);
		} catch (ResourceLoadException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw new ArchiveRuntimeException(e);
		}
	}

}
