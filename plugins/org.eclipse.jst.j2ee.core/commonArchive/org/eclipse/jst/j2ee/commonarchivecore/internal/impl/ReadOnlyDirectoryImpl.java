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



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;

/**
 * @generated
 */
public class ReadOnlyDirectoryImpl extends ContainerImpl implements ReadOnlyDirectory {

	public ReadOnlyDirectoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getReadOnlyDirectory();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER, msgs);
				case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
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
				case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
					return eBasicSetContainer(null, CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER, msgs);
				case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
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
				case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
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
			case CommonarchivePackage.READ_ONLY_DIRECTORY__URI:
				return getURI();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LAST_MODIFIED:
				return new Long(getLastModified());
			case CommonarchivePackage.READ_ONLY_DIRECTORY__SIZE:
				return new Long(getSize());
			case CommonarchivePackage.READ_ONLY_DIRECTORY__DIRECTORY_ENTRY:
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__ORIGINAL_URI:
				return getOriginalURI();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LOADING_CONTAINER:
				if (resolve) return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
				return getContainer();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
				return getFiles();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.READ_ONLY_DIRECTORY__URI:
				setURI((String)newValue);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LAST_MODIFIED:
				setLastModified(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__SIZE:
				setSize(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__DIRECTORY_ENTRY:
				setDirectoryEntry(((Boolean)newValue).booleanValue());
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__ORIGINAL_URI:
				setOriginalURI((String)newValue);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LOADING_CONTAINER:
				setLoadingContainer((Container)newValue);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
				setContainer((Container)newValue);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
				getFiles().clear();
				getFiles().addAll((Collection)newValue);
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
			case CommonarchivePackage.READ_ONLY_DIRECTORY__URI:
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LAST_MODIFIED:
				unsetLastModified();
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__SIZE:
				unsetSize();
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__DIRECTORY_ENTRY:
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__ORIGINAL_URI:
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LOADING_CONTAINER:
				setLoadingContainer((Container)null);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
				setContainer((Container)null);
				return;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
				getFiles().clear();
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
			case CommonarchivePackage.READ_ONLY_DIRECTORY__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LAST_MODIFIED:
				return isSetLastModified();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__SIZE:
				return isSetSize();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__DIRECTORY_ENTRY:
				return isSetDirectoryEntry();
			case CommonarchivePackage.READ_ONLY_DIRECTORY__ORIGINAL_URI:
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.READ_ONLY_DIRECTORY__LOADING_CONTAINER:
				return loadingContainer != null;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__CONTAINER:
				return getContainer() != null;
			case CommonarchivePackage.READ_ONLY_DIRECTORY__FILES:
				return files != null && !files.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean containsFile(java.lang.String aUri) {
		getFiles();
		return getFileIndex().containsKey(aUri);
	}

	/**
	 * containsFileInRootOrSubdirectory method comment.
	 */
	public boolean containsFileInSelfOrSubdirectory(java.lang.String aUri) {
		return getLoadStrategy().contains(aUri);
	}

	/**
	 * getFileInSelfOrSubdirectory method comment.
	 */
	public File getFileInSelfOrSubdirectory(java.lang.String aUri) throws java.io.FileNotFoundException {
		if (!containsFileInSelfOrSubdirectory(aUri))
			throw new java.io.FileNotFoundException(aUri);

		if (containsFile(aUri))
			return getFile(aUri);

		List subdirs = getReadOnlyDirectories();
		for (int i = 0; i < subdirs.size(); i++) {
			ReadOnlyDirectory subdir = (ReadOnlyDirectory) subdirs.get(i);
			if (subdir.containsFileInSelfOrSubdirectory(aUri))
				return subdir.getFileInSelfOrSubdirectory(aUri);
		}
		throw new java.io.FileNotFoundException(aUri);
	}

	/**
	 * Returns a flat list of all the files contained in this directory and subdirectories, with the
	 * directories filtered out, as the list would appear in an archive
	 */
	public java.util.List getFilesRecursive() {
		List allFiles = new ArrayList();
		List filesList = getFiles();
		for (int i = 0; i < filesList.size(); i++) {
			File aFile = (File) filesList.get(i);
			if (aFile.isReadOnlyDirectory())
				allFiles.addAll(((ReadOnlyDirectory) aFile).getFilesRecursive());
			else
				allFiles.add(aFile);
		}
		return allFiles;
	}

	/**
	 * Return a filtered list on the files with just the instances of ReadOnlyDirectory
	 */
	public List getReadOnlyDirectories() {
		List filtered = new ArrayList();
		List filesList = getFiles();
		for (int i = 0; i < filesList.size(); i++) {
			File aFile = (File) filesList.get(i);
			if (aFile.isReadOnlyDirectory())
				filtered.add(aFile);
		}
		return filtered;
	}

	/**
	 * isReadOnlyDirectory method comment.
	 */
	public boolean isReadOnlyDirectory() {
		return true;
	}
}
