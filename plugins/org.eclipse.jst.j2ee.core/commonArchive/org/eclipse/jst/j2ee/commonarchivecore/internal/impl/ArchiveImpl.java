/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ArchiveTypeDiscriminatorRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ManifestException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ReopenException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntryImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.DirectorySaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipStreamSaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveFileDynamicClassLoader;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ClasspathUtil;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopyUtility;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;


/**
 * @generated
 */
public class ArchiveImpl extends ContainerImpl implements Archive {

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList types = null;

	/** Our specialized manifest */
	protected ArchiveManifest manifest;

	/** Implementer for saving this archive */
	protected SaveStrategy saveStrategy;

	/**
	 * Optional filter for saving a subset of files; filter will be applied for all save and extract
	 * invokations
	 */
	protected SaveFilter saveFilter;

	/** Encoding to be used for all xmi resources and xml dds; defaults to UTF-8 */
	protected String xmlEncoding = J2EEConstants.DEFAULT_XML_ENCODING;

	/** Custom class loader used to load classes from the archive */
	protected ClassLoader archiveClassLoader;

	/**
	 * path of the standard classpath format where the archive may look for classes not found in the
	 * system classpath or in the archive - used for java reflection
	 */
	protected String extraClasspath;

	protected ArchiveOptions options;

	public ArchiveImpl() {
		super();
		getCommonArchiveFactory().archiveOpened(this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getArchive();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTypes() {
		if (types == null) {
			types = new EDataTypeUniqueEList(String.class, this, CommonarchivePackage.ARCHIVE__TYPES);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.ARCHIVE__CONTAINER:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.ARCHIVE__CONTAINER, msgs);
				case CommonarchivePackage.ARCHIVE__FILES:
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
				case CommonarchivePackage.ARCHIVE__CONTAINER:
					return eBasicSetContainer(null, CommonarchivePackage.ARCHIVE__CONTAINER, msgs);
				case CommonarchivePackage.ARCHIVE__FILES:
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
				case CommonarchivePackage.ARCHIVE__CONTAINER:
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
			case CommonarchivePackage.ARCHIVE__URI:
				return getURI();
			case CommonarchivePackage.ARCHIVE__LAST_MODIFIED:
				return new Long(getLastModified());
			case CommonarchivePackage.ARCHIVE__SIZE:
				return new Long(getSize());
			case CommonarchivePackage.ARCHIVE__DIRECTORY_ENTRY:
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.ARCHIVE__ORIGINAL_URI:
				return getOriginalURI();
			case CommonarchivePackage.ARCHIVE__LOADING_CONTAINER:
				if (resolve) return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.ARCHIVE__CONTAINER:
				return getContainer();
			case CommonarchivePackage.ARCHIVE__FILES:
				return getFiles();
			case CommonarchivePackage.ARCHIVE__TYPES:
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
			case CommonarchivePackage.ARCHIVE__URI:
				setURI((String)newValue);
				return;
			case CommonarchivePackage.ARCHIVE__LAST_MODIFIED:
				setLastModified(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.ARCHIVE__SIZE:
				setSize(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.ARCHIVE__DIRECTORY_ENTRY:
				setDirectoryEntry(((Boolean)newValue).booleanValue());
				return;
			case CommonarchivePackage.ARCHIVE__ORIGINAL_URI:
				setOriginalURI((String)newValue);
				return;
			case CommonarchivePackage.ARCHIVE__LOADING_CONTAINER:
				setLoadingContainer((Container)newValue);
				return;
			case CommonarchivePackage.ARCHIVE__CONTAINER:
				setContainer((Container)newValue);
				return;
			case CommonarchivePackage.ARCHIVE__FILES:
				getFiles().clear();
				getFiles().addAll((Collection)newValue);
				return;
			case CommonarchivePackage.ARCHIVE__TYPES:
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
			case CommonarchivePackage.ARCHIVE__URI:
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.ARCHIVE__LAST_MODIFIED:
				unsetLastModified();
				return;
			case CommonarchivePackage.ARCHIVE__SIZE:
				unsetSize();
				return;
			case CommonarchivePackage.ARCHIVE__DIRECTORY_ENTRY:
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.ARCHIVE__ORIGINAL_URI:
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.ARCHIVE__LOADING_CONTAINER:
				setLoadingContainer((Container)null);
				return;
			case CommonarchivePackage.ARCHIVE__CONTAINER:
				setContainer((Container)null);
				return;
			case CommonarchivePackage.ARCHIVE__FILES:
				getFiles().clear();
				return;
			case CommonarchivePackage.ARCHIVE__TYPES:
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
			case CommonarchivePackage.ARCHIVE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.ARCHIVE__LAST_MODIFIED:
				return isSetLastModified();
			case CommonarchivePackage.ARCHIVE__SIZE:
				return isSetSize();
			case CommonarchivePackage.ARCHIVE__DIRECTORY_ENTRY:
				return isSetDirectoryEntry();
			case CommonarchivePackage.ARCHIVE__ORIGINAL_URI:
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.ARCHIVE__LOADING_CONTAINER:
				return loadingContainer != null;
			case CommonarchivePackage.ARCHIVE__CONTAINER:
				return getContainer() != null;
			case CommonarchivePackage.ARCHIVE__FILES:
				return files != null && !files.isEmpty();
			case CommonarchivePackage.ARCHIVE__TYPES:
				return types != null && !types.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (types: ");
		result.append(types);
		result.append(')');
		return result.toString();
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public Archive addCopy(Archive anArchive) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException {
		checkAddValid(anArchive);
		Archive copy = getCommonArchiveFactory().copy(anArchive);
		getFiles().add(copy);
		return copy;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive Adds a copy of the parameter to this archive
	 * @throws com.ibm.etools.archive.exception.DuplicateObjectException
	 *             if the archive already contains a file with the specified uri
	 */
	public File addCopy(File aFile) throws DuplicateObjectException {
		if (aFile.isReadOnlyDirectory()) {
			addCopy((ReadOnlyDirectory) aFile);
			return null;
		}
		checkAddValid(aFile);
		File copy = copy(aFile);
		getFiles().add(copy);
		return copy;
	}

	/**
	 * Get a flattened list from the directory, then addCopy the list
	 * 
	 * @throws com.ibm.etools.archive.exception.DuplicateObjectException
	 *             if a file with a uri that equals one of the nested files in the directory exists
	 * 
	 * @return java.util.List the copied files that were added to the archive
	 */
	public java.util.List addCopy(ReadOnlyDirectory dir) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException {
		return addCopyFiles(dir.getFilesRecursive());
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public List addCopyFiles(java.util.List list) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException {
		//Optimization - make sure the fileIndex is already built to speed up
		// containsFile
		getFiles();
		List copyList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			File aFile = (File) list.get(i);
			checkAddValid(aFile);
			copyList.add(copy(aFile));
		}
		getFiles().addAll(copyList);
		return copyList;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void addOrReplaceMofResource(org.eclipse.emf.ecore.resource.Resource aResource) {
		getLoadStrategy().addOrReplaceMofResource(aResource);
	}

	/**
	 * @deprecated Use {@link #getDependentOpenArchives()}
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean canClose() {
		return !getCommonArchiveFactory().getOpenArchivesDependingOn(this).isEmpty();
	}

	protected void checkAddValid(File aFile) throws DuplicateObjectException {
		checkAddValid(aFile.getURI());
	}

	protected void checkAddValid(String aUri) throws DuplicateObjectException {
		try {
			File f = getFile(aUri);
			if (f != null)
				throw new DuplicateObjectException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.duplicate_file_EXC_, (new Object[]{getURI(), aUri})), f); // = "The archive named {0} already contains a file named {1}"
		} catch (FileNotFoundException ok) {
			//Ignore
		}
	}

	protected void cleanupAfterTempSave(String aUri, java.io.File original, java.io.File destinationFile) throws SaveFailureException {

		checkWriteable(original);
		boolean deleteWorked = false;
		if (original.isDirectory() && !isRenameable(original)) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.unable_replace_EXC_, (new Object[]{original.getAbsolutePath()}))); // = "Unable to replace original archive "
		}

		for (int i = 0; i < 10; i++) {
			if (ArchiveUtil.delete(original)) {
				deleteWorked = true;
				break;
			}
			try {
				// TODO Major hack here; the problem is that a previous call
				// to close the source file may not yet have
				//been reflected in the os/vm; therefore a subsequent call
				// to delete fails. To get around this,
				//wait for a bit and retry; if it continues to fail, then
				// time out and throw an exception
				Thread.sleep(250);
			} catch (InterruptedException e) {
				//Ignore
			}
		}
		if (deleteWorked) {
			for (int i = 0; i < 10; i++) {
				if (destinationFile.renameTo(original))
					return;
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					//Ignore
				}
			}
		}
		throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.unable_replace_EXC_, (new Object[]{original.getAbsolutePath()}))); // = "Unable to replace original archive "
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void close() {
		getLoadStrategy().close();
		releaseClassLoader();
		getCommonArchiveFactory().archiveClosed(this);
		if (isIndexed()) {
			List archives = getArchiveFiles();
			for (int i = 0; i < archives.size(); i++) {
				((Archive) archives.get(i)).close();
			}
		}
	}

	protected File copy(File aFile) {
		File copy = null;
		if (aFile.isArchive())
			copy = getCommonArchiveFactory().copy((Archive) aFile);
		else
			copy = (File) EtoolsCopyUtility.createCopy(aFile);
		return copy;
	}

	protected LoadStrategy createLoadStrategyForReopen(Archive parent) throws IOException {
		LoadStrategy aLoadStrategy = null;
		LooseArchive loose = getLoadStrategy().getLooseArchive();

		if (loose != null) {
			aLoadStrategy = getCommonArchiveFactory().createLoadStrategy(loose.getBinariesPath());
			aLoadStrategy.setLooseArchive(loose);
		} else if (parent == null)
			aLoadStrategy = getCommonArchiveFactory().createLoadStrategy(getURI());
		else
			aLoadStrategy = getCommonArchiveFactory().createChildLoadStrategy(getURI(), parent.getLoadStrategy());

		return aLoadStrategy;
	}

	protected RuntimeClasspathEntry createRuntimeClasspathEntry(String absolutePath) {
		RuntimeClasspathEntry entry = new RuntimeClasspathEntryImpl();
		entry.setAbsolutePath(absolutePath);
		return entry;
	}

	/**
	 * Convert all the classpath entries to absolute paths
	 */
	protected List createRuntimeClasspathEntries(String[] entries, String parentPath) {

		List aList = new ArrayList(entries.length);
		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			/*
			 * Added for loose module support - if the cananonicalized entry resolves to an archive
			 * in the containing ear, then add the absolute path of that archive
			 */
			Archive dependentJar = resolveClasspathEntryInEAR(entry);
			if (dependentJar != null) {
				try {
					RuntimeClasspathEntry runEntry = createRuntimeClasspathEntry(dependentJar.getBinariesPath(), entry);
					runEntry.setReferencedArchive(dependentJar);
					aList.add(runEntry);
					continue;
				} catch (FileNotFoundException shouldntHappenInRuntime) {
					//Ignore
				}
			}
			//Otherwise, compute the absolute path of the entry relative to
			// this jar
			java.io.File aFile = new java.io.File(entry);
			String absPath = null;
			if (aFile.isAbsolute())
				absPath = aFile.getAbsolutePath();
			else {
				absPath = ArchiveUtil.getOSUri(parentPath, entry);
				absPath = ClasspathUtil.normalizePath(absPath);
			}
			aList.add(createRuntimeClasspathEntry(absPath, entry));
		}

		return aList;
	}

	protected RuntimeClasspathEntry createRuntimeClasspathEntry(String absolutePath, String manifestValue) {
		RuntimeClasspathEntry entry = createRuntimeClasspathEntry(absolutePath);
		entry.setManifestValue(manifestValue);
		return entry;
	}

	protected SaveStrategy createSaveStrategyForDirectory(java.io.File dir, int expansionFlags) {
		return new DirectorySaveStrategyImpl(dir.getAbsolutePath(), expansionFlags);
	}

	protected SaveStrategy createSaveStrategyForDirectory(String aUri, int expansionFlags) {
		return new DirectorySaveStrategyImpl(aUri, expansionFlags);
	}

	protected SaveStrategy createSaveStrategyForJar(java.io.File aFile) throws java.io.IOException {
		if (aFile.exists() && aFile.isDirectory())
			throw new IOException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_exist_as_dir_EXC_, (new Object[]{aFile.getAbsolutePath()})));// = "A file named {0} exists and is a directory"
		java.io.File parent = aFile.getParentFile();
		if (parent != null)
			parent.mkdirs();
		java.io.OutputStream out = new java.io.FileOutputStream(aFile);
		return new ZipStreamSaveStrategyImpl(out);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void extract(int expansionFlags) throws SaveFailureException, ReopenException {
		extractNoReopen(expansionFlags);
		reopen();
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void extractNoReopen(int expansionFlags) throws SaveFailureException {
		String aUri = getURI();
		java.io.File aDir = new java.io.File(aUri);
		boolean inUse = getLoadStrategy().isUsing(aDir);

		try {
			java.io.File destinationDir = inUse ? ArchiveUtil.createTempDirectory(aUri, aDir.getCanonicalFile().getParentFile()) : aDir;
			SaveStrategy aSaveStrategy = createSaveStrategyForDirectory(destinationDir, expansionFlags);
			save(aSaveStrategy);
			aSaveStrategy.close();
			close();
			if (inUse) {
				cleanupAfterTempSave(aUri, aDir, destinationDir);
			}
		} catch (java.io.IOException ex) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_, (new Object[]{uri})), ex); // = "Error saving "
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void extractTo(java.lang.String aUri, int expansionFlags) throws SaveFailureException {
		java.io.File aDir = new java.io.File(aUri);
		if (getLoadStrategy().isUsing(aDir))
			throw new SaveFailureException(CommonArchiveResourceHandler.Extract_destination_is_the_EXC_); // = "Extract destination is the same path as source file"

		try {
			SaveStrategy aSaveStrategy = createSaveStrategyForDirectory(aDir, expansionFlags);
			save(aSaveStrategy);
			aSaveStrategy.close();
		} catch (java.io.IOException ex) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_, (new Object[]{aUri})), ex); // = "Error saving "
		}

	}

	public java.util.List filterFilesByPrefix(String prefix) {
		return filterFiles(prefix, null);
	}

	public java.util.List filterFiles(String prefix, String[] suffixes) {
		List subset = new ArrayList();
		List theFiles = getFiles();
		for (int i = 0; i < theFiles.size(); i++) {
			File aFile = (File) theFiles.get(i);
			if (!aFile.isDirectoryEntry() && aFile.getURI().startsWith(prefix))
				if (suffixes == null || hasSuffix(aFile.getURI(), suffixes))
					subset.add(aFile);
		}
		return subset;
	}

	/**
	 * @param uri
	 * @param suffixes
	 * @return
	 */
	private boolean hasSuffix(String aUri, String[] suffixes) {
		for (int i = 0; i < suffixes.length; i++) {
			if (aUri.endsWith(suffixes[i]))
				return true;
		}
		return false;
	}

	public java.util.List filterFilesWithoutPrefix(String[] prefixes) {
		List subset = new ArrayList();
		List theFiles = getFiles();
		for (int i = 0; i < theFiles.size(); i++) {
			File aFile = (File) theFiles.get(i);
			if (aFile.isDirectoryEntry())
				continue;
			boolean shouldAdd = true;
			for (int j = 0; j < prefixes.length; j++) {
				if (aFile.getURI().startsWith(prefixes[j])) {
					shouldAdd = false;
					break;
				}
			}
			if (shouldAdd)
				subset.add(aFile);
		}
		return subset;
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.ClassLoader
	 */
	public java.lang.ClassLoader getArchiveClassLoader() {
		if (archiveClassLoader == null)
			initializeClassLoader();
		return archiveClassLoader;
	}

	/**
	 * @see com.ibm.etools.commonarchive.EARFile
	 */
	public java.util.List getArchiveFiles() {
		List archives = new ArrayList();
		List fileList = getFiles();
		for (int i = 0; i < fileList.size(); i++) {
			File aFile = (File) fileList.get(i);
			if (aFile.isArchive()) {
				archives.add(aFile);
			}
		}
		return archives;
	}

	/**
	 * Parse the manifest class path and the extra class path, and instantiate a URL classloader,
	 * with a parent of the archiveClassLoader
	 */
	protected ClassLoader getClassPathClassLoader(ClassLoader parentCl) {

		List classPathComponents = new ArrayList();
		if (getManifest() != null)
			classPathComponents.addAll(Arrays.asList(getManifest().getClassPathTokenized()));
		String extraCp = getExtraClasspath();
		if (extraCp != null)
			classPathComponents.addAll(Arrays.asList(ArchiveUtil.getTokens(extraCp, ";")));//$NON-NLS-1$

		java.net.URL[] urlArray = ArchiveUtil.toLocalURLs(classPathComponents, getRootForRelativeDependentJars());
		return new java.net.URLClassLoader(urlArray, parentCl);
	}

	public ResourceSet getResourceSet() {
		return getLoadStrategy().getResourceSet();
	}

	/**
	 * Helper method to determine the parent for the custom class loader used by this archive
	 */
	protected ClassLoader getDefaultClassLoader() {
		ClassLoader pluginClassLoader = getClass().getClassLoader();
		return pluginClassLoader == null ? ClassLoader.getSystemClassLoader() : pluginClassLoader;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public java.util.Set getDependentOpenArchives() {
		return getCommonArchiveFactory().getOpenArchivesDependingOn(this);
	}

	/**
	 * Convert all the classpath entries to absolute paths
	 */
	protected List getEntriesAsAbsolutePaths(String[] entries, String parentPath) {

		List aList = new ArrayList(entries.length);
		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			/*
			 * Added for loose module support - if the cananonicalized entry resolves to an archive
			 * in the containing ear, then add the absolute path of that archive
			 */
			Archive dependentJar = resolveClasspathEntryInEAR(entry);
			if (dependentJar != null) {
				try {
					aList.add(dependentJar.getAbsolutePath());
					continue;
				} catch (FileNotFoundException shouldntHappenInRuntime) {
					//Ignore
				}
			}
			//Otherwise, compute the absolute path of the entry relative to
			// this jar
			java.io.File aFile = new java.io.File(entry);
			if (aFile.isAbsolute())
				aList.add(aFile.getAbsolutePath());
			else
				aList.add(ArchiveUtil.getOSUri(parentPath, entry));
		}

		return aList;
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getExtraClasspath() {
		return extraClasspath;
	}

	/**
	 * Used internally by the framework, specifically as an optimization when saving/exploding
	 * archives with nested archives
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator getFilesForSave() throws IOException {
		return getLoadStrategy().getFileIterator();
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	public java.io.InputStream getInputStream() throws java.io.FileNotFoundException, java.io.IOException {
		if (getLoadingContainer() != null || getLoadStrategy() == null || getLoadStrategy().isDirectory())
			return super.getInputStream();

		//This archive was copied in; this operation is not supported for
		// module files
		if (isModuleFile() || !getOptions().isSaveLibrariesAsFiles())
			throw new IOException("Undefined state of nested archive"); //$NON-NLS-1$

		//We have to find the absolute path of the original archive from which
		// this was copied,
		//if it is known

		List list = getFiles();

		String absolutePath = null;
		for (int i = 0; i < list.size(); i++) {
			File aFile = (File) list.get(i);
			if (aFile.isArchive())
				continue;
			absolutePath = aFile.getLoadingContainer().getAbsolutePath();
		}

		return new FileInputStream(absolutePath);
	}

	/**
	 * @see LoadStrategy#getResourceInputStream(String)
	 */
	public InputStream getResourceInputStream(String aUri) throws IOException {
		return getLoadStrategy().getResourceInputStream(aUri);
	}

	protected JavaJDKAdapterFactory getJavaAdapterFactory() {
		return (JavaJDKAdapterFactory) EcoreUtil.getAdapterFactory(getLoadStrategy().getResourceSet().getAdapterFactories(), ReadAdaptor.TYPE_KEY);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive returns an immutable collection of the loaded
	 *      resources in the resource set
	 */
	public Collection getLoadedMofResources() {
		return getLoadStrategy().getLoadedMofResources();
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 */
	public ArchiveManifest getManifest() {
		if (manifest == null) {
			InputStream in = null;
			try {
				in = getInputStream(J2EEConstants.MANIFEST_URI);
				makeManifest(in);
			} catch (FileNotFoundException ex) {
				makeManifest();
			} catch (Resource.IOWrappedException ex) {
				WrappedException wrapEx = new WrappedException((ex).getWrappedException());
				if (ExtendedEcoreUtil.getFileNotFoundDetector().isFileNotFound(wrapEx))
					makeManifest();
				else
					throw new ManifestException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.io_ex_manifest_EXC_, (new Object[]{getURI()})), ex); // = "An IOException occurred reading the manifest: "
			} catch (IOException ex) {
				throw new ManifestException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.io_ex_manifest_EXC_, (new Object[]{getURI()})), ex); // = "An IOException occurred reading the manifest: "
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException iox) {
						//Ignore
					}
			}
		}
		//This is a hack because of the fact that the manifest does not
		// serialize correctly if
		//The version is not set. In addition to saves, the serialization is
		// used for copy
		if (manifest.getManifestVersion() == null || manifest.getManifestVersion().equals("")) //$NON-NLS-1$
			manifest.setManifestVersion("1.0");//$NON-NLS-1$
		return manifest;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public org.eclipse.emf.ecore.resource.Resource getMofResource(java.lang.String aUri) throws FileNotFoundException, ResourceLoadException {
		return getLoadStrategy().getMofResource(aUri);
	}

	protected Resource getMofResourceMakeIfNecessary(String aUri) {
		if (getLoadStrategy() == null)
			return null;
		Resource resource = null;
		try {
			resource = getMofResource(aUri);
		} catch (java.io.FileNotFoundException ex) {
			try {
				resource = makeMofResource(aUri);
			} catch (DuplicateObjectException dox) {
				//We just checked for this; it won't happen
			}
		}
		return resource;
	}

	public org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions getOptions() {
		if (options == null) {
			options = new ArchiveOptions();
		}
		return options;
	}

	/**
	 * When looking at the class path of this jar (from the manifest), some of the elements may have
	 * a relative path, thus we need to determine the install location of this jar. If the absolute
	 * path from which the archive was loaded, return the parent directory of that path; otherwise,
	 * see if the containing archive has an absolute path; if neither work, default to the current
	 * working directory
	 */
	public String getRootForRelativeDependentJars() {
		String path = null;
		Container theContainer = this;
		while (theContainer != null && path == null) {
			try {
				path = theContainer.getAbsolutePath();
			} catch (FileNotFoundException ex) {
				//Ignore
			}
			theContainer = theContainer.getLoadingContainer();
		}
		if (path == null) {
			path = System.getProperty("user.dir");//$NON-NLS-1$
			if (path == null)
				//At this point what else can we do?
				return "";//$NON-NLS-1$
			return new java.io.File(path).getAbsolutePath();
		}
		return new java.io.File(path).getParentFile().getAbsolutePath();
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public java.lang.String[] getRuntimeClassPath() {

		String absolutePath;
		try {
			absolutePath = getBinariesPath();
		} catch (IOException ex) {
			return new String[0];
		}

		List entries = new ArrayList();
		entries.add(absolutePath);

		String parentPath = new java.io.File(absolutePath).getParentFile().getAbsolutePath();
		String[] mfEntries = getManifest().getClassPathTokenized();
		entries.addAll(getEntriesAsAbsolutePaths(mfEntries, parentPath));

		return (String[]) entries.toArray(new String[entries.size()]);
	}

	/**
	 * Optional filter for saving a subset of files; filter will be applied for all save and extract
	 * invokations
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter getSaveFilter() {
		return saveFilter;
	}

	/**
	 * Insert the method's description here. Creation date: (12/04/00 3:31:32 PM)
	 * 
	 * @return com.ibm.etools.archive.SaveStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy getSaveStrategy() {
		return saveStrategy;
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getXmlEncoding() {
		return xmlEncoding;
	}

	/**
	 * The default is to do nothing; subclasses may override as necessary
	 * 
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void initializeAfterOpen() {
		//Default
	}

	public void initializeClassLoader() {
		//Some load strategies may provide a mof context for which
		//an alternate class loader is not necessary
		if (!shouldUseJavaReflection())
			return;
		ClassLoader extraCl = null;
		ClassLoader defaultCl = getDefaultClassLoader();
		if (getContainer() == null || !getContainer().isEARFile())
			extraCl = getClassPathClassLoader(defaultCl);
		ClassLoader cl = createDynamicClassLoader(defaultCl, extraCl);
		setArchiveClassLoader(cl);
		JavaJDKAdapterFactory factory = getJavaAdapterFactory();
		factory.setContextClassLoader(cl);
		factory.flushAll();
	}

	public ClassLoader createDynamicClassLoader(ClassLoader parentCl, ClassLoader extraCl) {
		return new ArchiveFileDynamicClassLoader(this, parentCl, extraCl);
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	public boolean isArchive() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean isDuplicate(java.lang.String aUri) {
		return containsFile(aUri) || isMofResourceLoaded(aUri) || J2EEConstants.MANIFEST_URI.equals(aUri);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean isManifestSet() {
		return manifest != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean isMofResourceLoaded(java.lang.String aUri) {
		return getLoadStrategy().isMofResourceLoaded(aUri);
	}

	/**
	 * By default just test the extension of the uri for one of the known archive types; subclasses
	 * may which to override.
	 * 
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean isNestedArchive(java.lang.String aUri) {
		//110390.3 Error loading alt-bindings
		/*
		 * Don't infer that a folder which ends with .jar is an exploded archive; EAR file will do
		 * that IF the folder is declared as a module in the EAR
		 */
		if (getLoadStrategy().isDirectory()) {
			try {
				String path = ArchiveUtil.getOSUri(getAbsolutePath(), aUri);
				java.io.File ioFile = new java.io.File(path);
				if (!ioFile.exists() || (ioFile.isDirectory() && aUri.startsWith(J2EEConstants.ALT_INF)))
					return false;
			} catch (IOException ex) {
				return false;
			}
		}
		return ArchiveTypeDiscriminatorRegistry.INSTANCE.isKnownArchiveType(aUri);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public boolean isOpen() {
		return getLoadStrategy() != null && getLoadStrategy().isOpen();
	}

	public ArchiveManifest makeManifest() {
		ArchiveManifest mf = new ArchiveManifestImpl();
		setManifest(mf);
		return mf;
	}

	public ArchiveManifest makeManifest(InputStream in) throws IOException {
		ArchiveManifest mf = new ArchiveManifestImpl(in);
		setManifest(mf);
		return mf;
	}

	public Resource makeMofResource(String aUri) throws DuplicateObjectException {
		return makeMofResource(aUri, null);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive#makeMofResource(String, EList)
	 */
	public Resource makeMofResource(String aUri, EList extent) throws DuplicateObjectException {
		if (isDuplicate(aUri))
			throw new DuplicateObjectException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.duplicate_entry_EXC_, (new Object[]{aUri, getURI()}))); // = "A file or resource with uri {0} already exists in the archive named {1}"
		return getLoadStrategy().makeMofResource(aUri, extent);
	}

	/**
	 * @see Archive
	 */
	public Archive openNestedArchive(String aUri) throws OpenFailureException {
		return getCommonArchiveFactory().openNestedArchive(aUri, this);
	}

	/**
	 * @see Archive
	 */
	public Archive openNestedArchive(LooseArchive loose) throws OpenFailureException {
		return getCommonArchiveFactory().openNestedArchive(loose, this);
	}

	/**
	 * Set the value of the extra class path with no refresh of the class loader
	 */
	public void primSetExtraClasspath(java.lang.String newExtraClasspath) {
		extraClasspath = newExtraClasspath;
	}

	/**
	 * Remove references to the archive class loader to prevent gc problems or problems with temp
	 * files not getting deleted
	 */
	public void releaseClassLoader() {
		if (archiveClassLoader != null) {
			setArchiveClassLoader(null);
			getJavaAdapterFactory().setContextClassLoader(null);
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void remove(File aFile) {
		getFiles().remove(aFile);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void reopen() throws ReopenException {
		reopen(null);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void reopen(Archive parent) throws ReopenException {
		LoadStrategy aLoadStrategy = null;
		try {
			aLoadStrategy = createLoadStrategyForReopen(parent);
		} catch (IOException ex) {
			throw new ReopenException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.io_ex_reopen_EXC_, (new Object[]{getURI()})), ex); // = "IOException occurred while reopening "
		}
		//PQ54572
		LoadStrategy current = getLoadStrategy();
		if (current != null) {
			aLoadStrategy.setResourceSet(current.primGetResourceSet());
			/*
			 * fixing problem with orphaned load strategy listening to the resource set
			 */
			current.setResourceSet(null);
		}

		setLoadStrategy(aLoadStrategy);
		initializeClassLoader();
		if (!isIndexed())
			return;
		List fileList = getFiles();
		for (int i = 0; i < fileList.size(); i++) {
			File f = (File) fileList.get(i);
			f.setOriginalURI(f.getURI());
			f.setLoadingContainer(this);
			if (f.isArchive())
				((Archive) f).reopen(this);
		}
		getCommonArchiveFactory().archiveOpened(this);
	}

	protected void replaceRoot(Resource aResource, EObject root) {
		if (aResource == null)
			return;
		EList extent = aResource.getContents();
		EObject existingRoot = null;
		if (!extent.isEmpty()) {
			existingRoot = (EObject) extent.get(0);
			if (existingRoot == root)
				return;
			extent.remove(0);
		}
		if (root != null)
			extent.add(0, root);
	}

	protected Archive resolveClasspathEntryInEAR(String entry) {
		/*
		 * Added to support runtime classpath for loose modules
		 */
		Container parent = getContainer();
		if (parent == null || !parent.isEARFile())
			return null;

		String aUri = ArchiveUtil.deriveEARRelativeURI(entry, this);
		if (aUri == null)
			return null;

		File aFile = null;
		try {
			aFile = parent.getFile(aUri);
		} catch (FileNotFoundException ex) {
			return null;
		}

		return aFile.isArchive() ? (Archive) aFile : null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void save() throws SaveFailureException, ReopenException {
		saveAs(getURI());
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void save(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy aStrategy) throws SaveFailureException {
		setSaveStrategy(aStrategy);
		SaveFilter existingFilter = aStrategy.getFilter();
		boolean oldDelivery = eDeliver();
		try {
			if (getOptions().isReadOnly())
				eSetDeliver(false);
			aStrategy.setFilter(getSaveFilter());
			aStrategy.save();
			try {
				aStrategy.finish();
			} catch (java.io.IOException iox) {
				throw new SaveFailureException(getURI(), iox);
			}
		} finally {
			//We have to leave the file index if we are a directory because we
			// might have
			//open file handles to archives
			if (getOptions().isReadOnly() && !getLoadStrategy().isDirectory()) {
				files.clear();
				//((BasicEList)files).setListImplementation(new ArrayList(0));
				eSetDeliver(oldDelivery);
				eAdapters().remove(getFileIndexAdapter());
				fileIndexAdapter = null;
				fileIndex = null;
			}
			setSaveStrategy(null);
			aStrategy.setFilter(existingFilter);
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void saveAs(String aUri) throws SaveFailureException, ReopenException {
		saveAsNoReopen(aUri);
		reopen();
	}

	/**
	 * If we can rename it then we can delete it
	 */
	protected boolean isRenameable(java.io.File orig) {
		java.io.File origCopy1 = null;
		java.io.File origCopy2 = null;
		try {
			origCopy1 = orig.getCanonicalFile();
			origCopy2 = orig.getCanonicalFile();
		} catch (java.io.IOException ex) {
			return false;
		}
		String name = null;
		String baseName = "save.tmp"; //$NON-NLS-1$
		try {
			if (orig.getParent() != null)
				baseName = new java.io.File(orig.getParent(), baseName).getCanonicalPath();
		} catch (java.io.IOException ex) {
			return false;
		}

		java.io.File temp = null;
		int index = 0;
		do {
			name = baseName + index;
			temp = new java.io.File(name);
			index++;
		} while (temp.exists());
		return origCopy1.renameTo(temp) && temp.renameTo(origCopy2);
	}

	protected void checkWriteable(java.io.File dest) throws SaveFailureException {
		List locked = ArchiveUtil.getWriteProtectedFiles(dest, null);
		if (locked.isEmpty())
			return;

		StringBuffer msg = new StringBuffer();
		msg.append("Cannot write to file: "); //$NON-NLS-1$
		msg.append(dest.getAbsolutePath());
		msg.append('\n');
		msg.append("One or more files is write protected or locked:"); //$NON-NLS-1$
		msg.append('\n');
		for (int i = 0; i < locked.size(); i++) {
			java.io.File aFile = (java.io.File) locked.get(i);
			msg.append(aFile.getAbsolutePath());
			msg.append('\n');
		}
		throw new SaveFailureException(msg.toString());
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void saveAsNoReopen(String aUri) throws SaveFailureException {
		java.io.File aFile = new java.io.File(aUri);
		checkWriteable(aFile);
		boolean fileExisted = aFile.exists();
		//botp 142149
		//boolean inUse = getLoadStrategy().isUsing(aFile);
		SaveStrategy aSaveStrategy = null;
		try {
			try {
				java.io.File destinationFile = fileExisted ? ArchiveUtil.createTempFile(aUri, aFile.getCanonicalFile().getParentFile()) : aFile;
				aSaveStrategy = createSaveStrategyForJar(destinationFile);
				save(aSaveStrategy);
				aSaveStrategy.close();
				this.close();
				if (fileExisted) {
					cleanupAfterTempSave(aUri, aFile, destinationFile);
				}
			} catch (java.io.IOException ex) {
				throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_, (new Object[]{aUri})), ex); // = "Error saving "
			}
		} catch (SaveFailureException failure) {
			try {
				if (aSaveStrategy != null)
					aSaveStrategy.close();
			} catch (IOException weTried) {
				//Ignore
			}
			if (!fileExisted)
				aFile.delete();
			throw failure;
		}

		setURI(aUri);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void saveNoReopen() throws SaveFailureException {
		saveAsNoReopen(getURI());
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newArchiveClassLoader
	 *            java.lang.ClassLoader
	 */
	public void setArchiveClassLoader(java.lang.ClassLoader newArchiveClassLoader) {
		archiveClassLoader = newArchiveClassLoader;
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newExtraClasspath
	 *            java.lang.String
	 */
	public void setExtraClasspath(java.lang.String newExtraClasspath) {
		primSetExtraClasspath(newExtraClasspath);
		//Optimization - only re init if a cl exists; otherwise it will init on
		// demand
		if (archiveClassLoader != null)
			initializeClassLoader();
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 */
	public void setManifest(ArchiveManifest newManifest) {
		manifest = newManifest;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void setManifest(java.util.jar.Manifest aManifest) {
		setManifest((ArchiveManifest) new org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl(aManifest));
	}

	/**
	 * Sets the Class-path manifest entry, rebuilds the class loader, and refreshes any reflected
	 * java classes
	 */
	public void setManifestClassPathAndRefresh(String classpath) {
		ArchiveManifest mf = getManifest();
		if (manifest == null) {
			makeManifest();
		}
		mf.setClassPath(classpath);
		//Optimization - only re init if a cl exists; otherwise it will init on
		// demand
		if (archiveClassLoader != null)
			initializeClassLoader();
	}

	public void setOptions(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions newOptions) {
		options = newOptions;
	}

	/**
	 * Optional filter for saving a subset of files; filter will be applied for all save and extract
	 * invokations
	 */
	public void setSaveFilter(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter newSaveFilter) {
		saveFilter = newSaveFilter;
	}

	/**
	 * Insert the method's description here. Creation date: (12/04/00 3:31:32 PM)
	 * 
	 * @param newSaveStrategy
	 *            com.ibm.etools.archive.SaveStrategy
	 */
	public void setSaveStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy newSaveStrategy) {
		saveStrategy = newSaveStrategy;
		if (newSaveStrategy != null) {
			newSaveStrategy.setArchive(this);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newXmlEncoding
	 *            java.lang.String
	 */
	public void setXmlEncoding(java.lang.String newXmlEncoding) {
		xmlEncoding = newXmlEncoding;
	}

	/**
	 * Determine whether java reflection should be set up for this archive
	 */
	public boolean shouldUseJavaReflection() {
		return getOptions().useJavaReflection() && getLoadStrategy().isClassLoaderNeeded();
	}

	protected void throwResourceLoadException(String resourceUri, Exception ex) throws ResourceLoadException {
		throw new ResourceLoadException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.load_resource_EXC_, (new Object[]{resourceUri, getURI()})), ex); // = "Could not load resource "{0}" in archive "{1}""
	}

	public String getResourcesPath() throws FileNotFoundException {
		return getLoadStrategy().getResourcesPath();
	}

	public String getBinariesPath() throws FileNotFoundException {
		return getLoadStrategy().getBinariesPath();
	}

	protected RuntimeClasspathEntry[] emptyClasspath() {
		return new RuntimeClasspathEntry[0];
	}

	protected String internalGetBinariesPath() {
		try {
			return getBinariesPath();
		} catch (FileNotFoundException ex) {
			return null;
		}
	}

	/**
	 * By default return just the contents of this archive
	 */
	public RuntimeClasspathEntry[] getLocalRuntimeClassPath() {

		String absolutePath = internalGetBinariesPath();
		if (absolutePath == null)
			return emptyClasspath();
		return new RuntimeClasspathEntry[]{createRuntimeClasspathEntry(absolutePath)};
	}

	protected RuntimeClasspathEntry[] getDependencyClassPathAtThisLevel() {
		String absolutePath = internalGetBinariesPath();
		if (absolutePath == null)
			return emptyClasspath();
		String parentPath = new java.io.File(absolutePath).getParentFile().getAbsolutePath();
		String[] mfEntries = getManifest().getClassPathTokenized();
		if (mfEntries.length == 0)
			return emptyClasspath();
		List entries = new ArrayList();
		entries.addAll(createRuntimeClasspathEntries(mfEntries, parentPath));

		return (RuntimeClasspathEntry[]) entries.toArray(new RuntimeClasspathEntry[entries.size()]);
	}

	public RuntimeClasspathEntry[] getFullRuntimeClassPath() {
		return concat(getLocalRuntimeClassPath(), getDependencyClassPath());
	}

	protected RuntimeClasspathEntry[] concat(RuntimeClasspathEntry[] array1, RuntimeClasspathEntry[] array2) {
		List temp = new ArrayList();
		temp.addAll(Arrays.asList(array1));
		temp.addAll(Arrays.asList(array2));
		return (RuntimeClasspathEntry[]) temp.toArray(new RuntimeClasspathEntry[temp.size()]);
	}

	public RuntimeClasspathEntry[] getDependencyClassPath() {
		List entries = new ArrayList();
		Set visited = new HashSet();
		Set processedEntries = new HashSet();
		visited.add(this);
		getDependencyClassPath(visited, entries, processedEntries, this);
		return (RuntimeClasspathEntry[]) entries.toArray(new RuntimeClasspathEntry[entries.size()]);
	}

	protected void getDependencyClassPath(Set visitedArchives, List entries, Set processedEntries, Archive current) {

		RuntimeClasspathEntry[] local = ((ArchiveImpl) current).getDependencyClassPathAtThisLevel();
		for (int i = 0; i < local.length; i++) {
			RuntimeClasspathEntry entry = local[i];
			if (!processedEntries.contains(entry)) {
				entries.add(entry);
				processedEntries.add(entry);
			}
			Archive resolved = entry.getReferencedArchive();
			if (resolved == null)
				ClasspathUtil.processManifest(entry.getAbsolutePath(), entries, processedEntries);
			else if (!visitedArchives.contains(resolved)) {
				visitedArchives.add(resolved);
				getDependencyClassPath(visitedArchives, entries, processedEntries, resolved);
			}

		}
	}

	protected EARFile getEARFile() {
		Container parent = getContainer();
		if (parent == null || !(parent instanceof EARFile))
			return null;
		return (EARFile) parent;
	}

	protected Archive getResolvedArchive(String mfValue, EARFile ear) {
		String aUri = ArchiveUtil.deriveEARRelativeURI(mfValue, this);
		if (aUri == null)
			return null;
		try {
			return (Archive) ear.getFile(aUri);
		} catch (FileNotFoundException ex) {
			return null;
		} catch (ClassCastException ex2) {
			return null;
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive#hasClasspathVisibilityTo(Archive)
	 */
	public boolean hasClasspathVisibilityTo(Archive other) {
		if (other == null)
			return false;
		EARFile ear = getEARFile();
		if (ear == null)
			return false;
		Set visited = new HashSet();
		return hasClasspathVisibilityTo(other, visited, ear);
	}

	public boolean hasClasspathVisibilityTo(Archive other, Set visited, EARFile ear) {
		if (this == other)
			return true;
		if (visited.contains(this))
			return false;
		visited.add(this);
		String[] mfEntries = getManifest().getClassPathTokenized();
		for (int i = 0; i < mfEntries.length; i++) {
			Archive anArchive = getResolvedArchive(mfEntries[i], ear);
			if (anArchive != null && anArchive.hasClasspathVisibilityTo(other, visited, ear))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.commonarchivecore.Archive#isType(java.lang.String)
	 */
	public boolean isType(String type) {

		return (types != null && getTypes().contains(type));
	}

}
