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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.io.FileNotFoundException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;
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
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getModuleFile();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.MODULE_FILE__CONTAINER:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.MODULE_FILE__CONTAINER, msgs);
				case CommonarchivePackage.MODULE_FILE__FILES:
					return ((InternalEList)getFiles()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.MODULE_FILE__CONTAINER:
					return eBasicSetContainer(null, CommonarchivePackage.MODULE_FILE__CONTAINER, msgs);
				case CommonarchivePackage.MODULE_FILE__FILES:
					return ((InternalEList)getFiles()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case CommonarchivePackage.MODULE_FILE__CONTAINER:
					return eContainer.eInverseRemove(this, CommonarchivePackage.CONTAINER__FILES, Container.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_FILE__URI:
				return getURI();
			case CommonarchivePackage.MODULE_FILE__LAST_MODIFIED:
				return new Long(getLastModified());
			case CommonarchivePackage.MODULE_FILE__SIZE:
				return new Long(getSize());
			case CommonarchivePackage.MODULE_FILE__DIRECTORY_ENTRY:
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.MODULE_FILE__ORIGINAL_URI:
				return getOriginalURI();
			case CommonarchivePackage.MODULE_FILE__LOADING_CONTAINER:
				if (resolve) return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.MODULE_FILE__CONTAINER:
				return getContainer();
			case CommonarchivePackage.MODULE_FILE__FILES:
				return getFiles();
			case CommonarchivePackage.MODULE_FILE__TYPES:
				return getTypes();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_FILE__URI:
				setURI((String)newValue);
				return;
			case CommonarchivePackage.MODULE_FILE__LAST_MODIFIED:
				setLastModified(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.MODULE_FILE__SIZE:
				setSize(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.MODULE_FILE__DIRECTORY_ENTRY:
				setDirectoryEntry(((Boolean)newValue).booleanValue());
				return;
			case CommonarchivePackage.MODULE_FILE__ORIGINAL_URI:
				setOriginalURI((String)newValue);
				return;
			case CommonarchivePackage.MODULE_FILE__LOADING_CONTAINER:
				setLoadingContainer((Container)newValue);
				return;
			case CommonarchivePackage.MODULE_FILE__CONTAINER:
				setContainer((Container)newValue);
				return;
			case CommonarchivePackage.MODULE_FILE__FILES:
				getFiles().clear();
				getFiles().addAll((Collection)newValue);
				return;
			case CommonarchivePackage.MODULE_FILE__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_FILE__URI:
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.MODULE_FILE__LAST_MODIFIED:
				unsetLastModified();
				return;
			case CommonarchivePackage.MODULE_FILE__SIZE:
				unsetSize();
				return;
			case CommonarchivePackage.MODULE_FILE__DIRECTORY_ENTRY:
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.MODULE_FILE__ORIGINAL_URI:
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.MODULE_FILE__LOADING_CONTAINER:
				setLoadingContainer((Container)null);
				return;
			case CommonarchivePackage.MODULE_FILE__CONTAINER:
				setContainer((Container)null);
				return;
			case CommonarchivePackage.MODULE_FILE__FILES:
				getFiles().clear();
				return;
			case CommonarchivePackage.MODULE_FILE__TYPES:
				getTypes().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_FILE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.MODULE_FILE__LAST_MODIFIED:
				return isSetLastModified();
			case CommonarchivePackage.MODULE_FILE__SIZE:
				return isSetSize();
			case CommonarchivePackage.MODULE_FILE__DIRECTORY_ENTRY:
				return isSetDirectoryEntry();
			case CommonarchivePackage.MODULE_FILE__ORIGINAL_URI:
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.MODULE_FILE__LOADING_CONTAINER:
				return loadingContainer != null;
			case CommonarchivePackage.MODULE_FILE__CONTAINER:
				return getContainer() != null;
			case CommonarchivePackage.MODULE_FILE__FILES:
				return files != null && !files.isEmpty();
			case CommonarchivePackage.MODULE_FILE__TYPES:
				return types != null && !types.isEmpty();
		}
		return eDynamicIsSet(eFeature);
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
