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



import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;


/**
 * @generated
 */
public abstract class ContainerImpl extends FileImpl implements Container {


	/**
	 * Inner class which maintains the index for the domain's collection of nodes keyed by name.
	 */
	protected class FileNotificationAdapter extends AdapterImpl {
		public boolean isAdapterForType(Object type) {
			return (type == "FileNotificationAdapter");//$NON-NLS-1$
		}

		public void addIndexedFile(String newValue, Notifier notifier) {
			fileIndex.put(newValue, notifier);
			if (notifier.eAdapters() == null || !notifier.eAdapters().contains(this))
				notifier.eAdapters().add(this);
		}

		public void removeIndexedFile(String oldValue, Notifier notifier) {
			fileIndex.remove(oldValue);
			notifier.eAdapters().remove(this);
		}

		public void notifyChanged(Notification notification) {
			if (fileIndex == null || notification.getFeature() == null)
				return;
			//If the name changed, update the index
			if (notification.getFeature().equals(CommonarchivePackage.eINSTANCE.getFile_URI()) && ((File) notification.getNotifier()).getContainer() == ContainerImpl.this) {
				fileIndex.remove(notification.getOldValue());
				fileIndex.put(notification.getNewValue(), notification.getNotifier());
			}
			//Handle adds and removes
			if (notification.getFeature().equals(CommonarchivePackage.eINSTANCE.getContainer_Files()) && notification.getNotifier() == ContainerImpl.this) {
				switch (notification.getEventType()) {
					case Notification.ADD : {
						File file = (File) notification.getNewValue();
						addIndexedFile(file.getURI(), file);
						break;
					}
					case Notification.REMOVE : {
						removeIndexedFile(((File) notification.getOldValue()).getURI(), (File) notification.getOldValue());
						break;
					}
					case Notification.ADD_MANY : {
						filesAdded((List) notification.getNewValue());
						break;
					}
					case Notification.REMOVE_MANY : {
						filesRemoved((List) notification.getOldValue());
						break;
					}
					case Notification.MOVE : {
						break;
					}
					case Notification.SET : {
						if (notification.getPosition() != Notification.NO_INDEX) { //This is now a
																				   // replace in
																				   // MOF2
							File file = (File) notification.getNewValue();
							removeIndexedFile(((File) notification.getOldValue()).getURI(), (File) notification.getOldValue());
							addIndexedFile(file.getURI(), file);
						}
						break;
					}
				}
			}
		}

		public void filesAdded(List newFiles) {
			for (int i = 0; i < newFiles.size(); i++) {
				File file = (File) newFiles.get(i);
				addIndexedFile(file.getURI(), file);
			}
		}

		public void filesRemoved(List oldFiles) {
			for (int i = 0; i < oldFiles.size(); i++) {
				File file = (File) oldFiles.get(i);
				removeIndexedFile(file.getURI(), file);
			}
		}

		public void rebuildFileIndex() {
			removeAdaptersIfNecessary();
			fileIndex = new HashMap();

			// If the primary collection already has elements,
			//'reflect them in the index...
			if (getFiles().size() > 0) {
				Iterator i = getFiles().iterator();
				while (i.hasNext()) {
					File file = (File) i.next();
					addIndexedFile(file.getURI(), file);
				}
			}
		}

		public void removeAdaptersIfNecessary() {
			if (fileIndex == null)
				return;
			Iterator iter = fileIndex.values().iterator();
			while (iter.hasNext()) {
				File aFile = (File) iter.next();
				aFile.eAdapters().remove(this);
			}
		}
	}

	/** Implementer for loading entries in this container */
	protected LoadStrategy loadStrategy;
	/**
	 * Index to provide fast lookup by name of files.
	 */
	protected Map fileIndex;
	/**
	 * An adapter which maintains the file index
	 */
	protected FileNotificationAdapter fileIndexAdapter;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList files = null;

	public ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getContainer();
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean containsFile(java.lang.String aUri) {
		String key = aUri.startsWith("/") ? ArchiveUtil.truncateFromFrontIgnoreCase(aUri, "/") : aUri;//$NON-NLS-2$//$NON-NLS-1$
		if (isIndexed())
			return getFileIndex().containsKey(key);
		return getLoadStrategy().contains(key);

	}

	/**
	 * @see com.ibm.etools.commonarchive.Container
	 */
	public java.lang.String getAbsolutePath() throws java.io.FileNotFoundException {
		return getLoadStrategy().getAbsolutePath();
	}

	public File getFile(String URI) throws java.io.FileNotFoundException {
		if (!isIndexed()) {
			getFiles();
		}
		File file = (File) getFileIndex().get(URI);
		if (file == null) {
			throw new java.io.FileNotFoundException(URI);
		}
		return file;
	}

	/**
	 * Insert the method's description here. Creation date: (12/05/00 7:20:21 PM)
	 * 
	 * @return java.util.Map
	 */
	protected java.util.Map getFileIndex() {
		if (fileIndex == null)
			getFileIndexAdapter().rebuildFileIndex();
		return fileIndex;
	}

	/**
	 * Insert the method's description here. Creation date: (12/05/00 7:20:21 PM)
	 * 
	 * @return FileNotificationAdapter
	 */
	protected FileNotificationAdapter getFileIndexAdapter() {
		if (fileIndexAdapter == null) {
			fileIndexAdapter = new FileNotificationAdapter();
			eAdapters().add(fileIndexAdapter);
		}
		return fileIndexAdapter;
	}

	/**
	 * List is built on demand, by requesting from the load strategy.
	 */
	public EList getFiles() {
		EList filesList = this.getFilesGen();
		if (!isIndexed()) {
			if (filesList.isEmpty() && getLoadStrategy() != null) {
				filesList.addAll(getLoadStrategy().collectFiles());
			}
			//Causes the index to be built
			getFileIndex();
		}
		return filesList;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive Looks for a file with the given uri, and returns an
	 *      input stream; optimization: if the file list has not been built, goes directly to the
	 *      loadStrategy.
	 */
	public java.io.InputStream getInputStream(java.lang.String aUri) throws java.io.IOException, java.io.FileNotFoundException {
		if (isIndexed()) {
			return getFile(aUri).getInputStream();
		}
		return primGetInputStream(aUri);
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.LoadStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy getLoadStrategy() {
		return loadStrategy;
	}

	public boolean isContainer() {
		return true;
	}

	public boolean isIndexed() {
		return fileIndex != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive Goes directly to the loadStrategy.
	 */
	public java.io.InputStream primGetInputStream(java.lang.String aUri) throws java.io.IOException, java.io.FileNotFoundException {
		return getLoadStrategy().getInputStream(aUri);
	}

	public void rebuildFileIndex() {
		getFileIndexAdapter().rebuildFileIndex();
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newLoadStrategy
	 *            com.ibm.etools.archive.LoadStrategy
	 */
	public void setLoadStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy newLoadStrategy) {

		if (newLoadStrategy != null) {
			newLoadStrategy.setContainer(this);
			if (loadStrategy != null) {
				newLoadStrategy.setRendererType(loadStrategy.getRendererType());
				newLoadStrategy.setReadOnly(loadStrategy.isReadOnly());
				loadStrategy.setContainer(null);
				loadStrategy.close();
			}
		}
		loadStrategy = newLoadStrategy;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EList getFilesGen() {
		if (files == null) {
			files = new EObjectContainmentWithInverseEList(File.class, this, CommonarchivePackage.CONTAINER__FILES, CommonarchivePackage.FILE__CONTAINER);
		}
		return files;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.CONTAINER__CONTAINER :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.CONTAINER__CONTAINER, msgs);
				case CommonarchivePackage.CONTAINER__FILES :
					return ((InternalEList) getFiles()).basicAdd(otherEnd, msgs);
				default :
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.CONTAINER__CONTAINER :
					return eBasicSetContainer(null, CommonarchivePackage.CONTAINER__CONTAINER, msgs);
				case CommonarchivePackage.CONTAINER__FILES :
					return ((InternalEList) getFiles()).basicRemove(otherEnd, msgs);
				default :
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case CommonarchivePackage.CONTAINER__CONTAINER :
					return eContainer.eInverseRemove(this, CommonarchivePackage.CONTAINER__FILES, Container.class, msgs);
				default :
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CONTAINER__URI :
				return getURI();
			case CommonarchivePackage.CONTAINER__LAST_MODIFIED :
				return new Long(getLastModified());
			case CommonarchivePackage.CONTAINER__SIZE :
				return new Long(getSize());
			case CommonarchivePackage.CONTAINER__DIRECTORY_ENTRY :
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.CONTAINER__ORIGINAL_URI :
				return getOriginalURI();
			case CommonarchivePackage.CONTAINER__LOADING_CONTAINER :
				if (resolve)
					return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.CONTAINER__CONTAINER :
				return getContainer();
			case CommonarchivePackage.CONTAINER__FILES :
				return getFiles();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CONTAINER__URI :
				setURI((String) newValue);
				return;
			case CommonarchivePackage.CONTAINER__LAST_MODIFIED :
				setLastModified(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.CONTAINER__SIZE :
				setSize(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.CONTAINER__DIRECTORY_ENTRY :
				setDirectoryEntry(((Boolean) newValue).booleanValue());
				return;
			case CommonarchivePackage.CONTAINER__ORIGINAL_URI :
				setOriginalURI((String) newValue);
				return;
			case CommonarchivePackage.CONTAINER__LOADING_CONTAINER :
				setLoadingContainer((Container) newValue);
				return;
			case CommonarchivePackage.CONTAINER__CONTAINER :
				setContainer((Container) newValue);
				return;
			case CommonarchivePackage.CONTAINER__FILES :
				getFiles().clear();
				getFiles().addAll((Collection) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CONTAINER__URI :
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.CONTAINER__LAST_MODIFIED :
				unsetLastModified();
				return;
			case CommonarchivePackage.CONTAINER__SIZE :
				unsetSize();
				return;
			case CommonarchivePackage.CONTAINER__DIRECTORY_ENTRY :
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.CONTAINER__ORIGINAL_URI :
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.CONTAINER__LOADING_CONTAINER :
				setLoadingContainer((Container) null);
				return;
			case CommonarchivePackage.CONTAINER__CONTAINER :
				setContainer((Container) null);
				return;
			case CommonarchivePackage.CONTAINER__FILES :
				getFiles().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.CONTAINER__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.CONTAINER__LAST_MODIFIED :
				return isSetLastModified();
			case CommonarchivePackage.CONTAINER__SIZE :
				return isSetSize();
			case CommonarchivePackage.CONTAINER__DIRECTORY_ENTRY :
				return isSetDirectoryEntry();
			case CommonarchivePackage.CONTAINER__ORIGINAL_URI :
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.CONTAINER__LOADING_CONTAINER :
				return loadingContainer != null;
			case CommonarchivePackage.CONTAINER__CONTAINER :
				return getContainer() != null;
			case CommonarchivePackage.CONTAINER__FILES :
				return files != null && !files.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	public void clearFiles() {
		boolean oldDelivery = eDeliver();
		files.clear();
		eSetDeliver(oldDelivery);
		if (isIndexed()) {
			eAdapters().remove(fileIndexAdapter);
			fileIndexAdapter = null;
			fileIndex = null;
		}
	}
}
