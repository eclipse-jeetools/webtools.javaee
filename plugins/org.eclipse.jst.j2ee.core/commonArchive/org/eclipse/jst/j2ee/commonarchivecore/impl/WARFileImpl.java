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
package org.eclipse.jst.j2ee.commonarchivecore.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.Container;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.util.WarFileDynamicClassLoader;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * @generated
 */
public class WARFileImpl extends ModuleFileImpl implements WARFile {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected WebApp deploymentDescriptor = null;
	protected List sourceFiles;

	public WARFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getWARFile();
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public File addCopyClass(File aFile) throws DuplicateObjectException {
		if (aFile.isReadOnlyDirectory())
			throw new IllegalArgumentException(CommonArchiveResourceHandler.getString("add_copy_class_dir_EXC_", (new Object[]{aFile.getURI()}))); //$NON-NLS-1$ = "Method addCopyClass not supported for directories :"
		return addCopyFileAddingPrefix(aFile, ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	protected File addCopyFileAddingPrefix(File aFile, String uriPrefix) throws DuplicateObjectException {
		String swizzledUri = aFile.getURI();
		if (!swizzledUri.startsWith(uriPrefix)) {
			swizzledUri = ArchiveUtil.concatUri(uriPrefix, swizzledUri, '/');
		}
		checkAddValid(swizzledUri);
		File copy = copy(aFile);
		copy.setURI(swizzledUri);
		getFiles().add(copy);
		return copy;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public File addCopyLib(File aFile) throws DuplicateObjectException {
		if (aFile.isReadOnlyDirectory())
			throw new IllegalArgumentException(CommonArchiveResourceHandler.getString("add_copy_lib_dir_EXC_", (new Object[]{aFile.getURI()}))); //$NON-NLS-1$ = "Method addCopyLib not supported for directories :"
		return addCopyFileAddingPrefix(aFile, ArchiveConstants.WEBAPP_LIB_URI);
	}

	protected RuntimeClasspathEntry createLibClasspathEntry(String libPath) {
		RuntimeClasspathEntry entry = createRuntimeClasspathEntry(libPath);
		entry.setWarFile(this);
		return entry;
	}

	/**
	 * Used internally for building the runtime class path
	 * 
	 * @return List of Strings, the absolute paths of all file entries (no directories)
	 */
	protected List getAllFileEntriesRecursive(java.io.File aFile, List aList) {
		if (!aFile.exists())
			return aList;
		//modified for botp 126140 05.29.02
		String name = aFile.getName();
		if (name.regionMatches(true, name.length() - 4, ".jar", 0, 4)) {//$NON-NLS-1$
			aList.add(aFile.getAbsolutePath());
		} else if (aFile.isDirectory()) {
			java.io.File[] children = aFile.listFiles();
			for (int i = 0; i < children.length; i++) {
				getAllFileEntriesRecursive(children[i], aList);
			}
		}
		return aList;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public java.util.List getClasses() {
		return filterFilesByPrefix(ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */
	public WebApp getDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		WebApp dd = this.getDeploymentDescriptorGen();
		if (dd == null && canLazyInitialize())
			try {
				getImportStrategy().importMetaData();
			} catch (Exception e) {
				throw new DeploymentDescriptorLoadException(getDeploymentDescriptorUri(), e);
			}
		return this.getDeploymentDescriptorGen();
	}

	public String getDeploymentDescriptorUri() {
		return ArchiveConstants.WEBAPP_DD_URI;
	}


	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public java.util.List getLibs() {
		return filterFilesByPrefix(ArchiveConstants.WEBAPP_LIB_URI);
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public List getLibArchives() {
		return filterFiles(ArchiveConstants.WEBAPP_LIB_URI, new String[]{"jar", "zip"}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public java.util.List getResources() {
		String[] prefixes = {ArchiveConstants.META_INF, ArchiveConstants.WEB_INF};
		return filterFilesWithoutPrefix(prefixes);
	}

	/**
	 * Overridden from superclass to include the claseses and libs of a WARFile There also is an
	 * assumption that this is an exploded war file The following is what should be returned:
	 * 
	 * 1) WAR directory + /WEB-INF/classes 2) all JAR files contained in [WAR directory +
	 * /WEB-INF/lib] 3) WAR directory
	 */
	public RuntimeClasspathEntry[] getLocalRuntimeClassPath() {

		String absolutePath;
		try {
			absolutePath = getLoadStrategy().getBinariesPath();
		} catch (IOException ex) {
			return new RuntimeClasspathEntry[0];
		}

		List entries = new ArrayList();

		if (getLoadStrategy().isDirectory()) {
			String classesPath = ArchiveUtil.getOSUri(absolutePath, ArchiveConstants.WEBAPP_CLASSES_URI);
			entries.add(createRuntimeClasspathEntry(classesPath));
			addWebLibs(absolutePath, entries);
		}

		entries.add(createRuntimeClasspathEntry(absolutePath));
		return (RuntimeClasspathEntry[]) entries.toArray(new RuntimeClasspathEntry[entries.size()]);
	}

	protected RuntimeClasspathEntry[] getDependencyClassPathAtThisLevel() {

		String absolutePath = internalGetBinariesPath();
		if (absolutePath == null)
			return emptyClasspath();
		RuntimeClasspathEntry[] mfEntries = super.getDependencyClassPathAtThisLevel();
		List entries = new ArrayList();
		entries.addAll(Arrays.asList(mfEntries));

		return (RuntimeClasspathEntry[]) entries.toArray(new RuntimeClasspathEntry[entries.size()]);
	}

	protected void addWebLibs(String absolutePath, List entries) {
		/*******************************************************************************************
		 * Optimization*********** We could have used the helper getlibs here; however, that could
		 * drag down performance as the full file list would have to get built, and this is runtime;
		 * if the classes portion is huge then building the file list would be slow; moreover, we
		 * would then have to clear the index from this war file. therefore, we'll do a little extra
		 * work to get the libs
		 ******************************************************************************************/
		java.io.File aFile = new java.io.File(ArchiveUtil.getOSUri(absolutePath, ArchiveConstants.WEBAPP_LIB_URI));
		List libs = getAllFileEntriesRecursive(aFile, new ArrayList());
		for (int i = 0; i < libs.size(); i++) {
			String libPath = (String) libs.get(i);
			entries.add(createLibClasspathEntry(libPath));
		}
		addLooseLibsToRuntimeClasspath(entries);
	}

	protected void addLooseLibsToRuntimeClasspath(List entries) {
		LooseWARFile loose = (LooseWARFile) getLoadStrategy().getLooseArchive();
		if (loose == null)
			return;

		List looseLibs = loose.getLooseLibs();
		for (int i = 0; i < looseLibs.size(); i++) {
			LooseLibrary lib = (LooseLibrary) looseLibs.get(i);
			entries.add(createLibClasspathEntry(lib.getBinariesPath()));
		}
	}

	protected List calculateSourceCandidateFiles() {
		List filesList = getFiles();
		List retFiles = new Vector();
		for (Iterator iter = filesList.iterator(); iter.hasNext();) {
			File file = (File) iter.next();
			String aUri = file.getURI();
			if (aUri.startsWith(ArchiveConstants.WEB_INF) && !aUri.startsWith(ArchiveConstants.WEBAPP_LIB_URI))
				retFiles.add(file);
		}
		return retFiles;
	}

	protected List getSourceCandidateFiles() {
		if (sourceFiles == null) {
			sourceFiles = calculateSourceCandidateFiles();
		}
		return sourceFiles;
	}

	protected String makeRelative(String pathString, String relToPathString) {

		if (pathString.startsWith(relToPathString))
			return ArchiveUtil.truncateFromFrontIgnoreCase(pathString, relToPathString);

		return pathString;
	}

	/**
	 * Return the source file that matches the output file passed in
	 * 
	 * @param aClassFile
	 *            The .class file or other output file to find the source for
	 * @return String The matching source. Null if there is no matching source found
	 */
	public File getSourceFile(File aClassFile) {
		List sourceFilesList = getSourceCandidateFiles();
		String classURI = aClassFile.getURI();
		String convertedClassURI = classURI;
		if (classURI.endsWith(".class")) //$NON-NLS-1$
			convertedClassURI = ArchiveUtil.classUriToJavaUri(aClassFile.getURI());
		convertedClassURI = makeRelative(convertedClassURI, ArchiveConstants.WEBAPP_CLASSES_URI);
		int bestLength = 32767;
		File bestMatch = null;
		File file = null;
		String aUri = null;
		for (Iterator iter = sourceFilesList.iterator(); iter.hasNext();) {
			file = (File) iter.next();
			aUri = file.getURI();
			if (aUri.endsWith(convertedClassURI) && !aUri.equals(classURI)) {
				int excessLength = aUri.length() - convertedClassURI.length();
				if (excessLength < bestLength) {
					bestMatch = file;
					bestLength = excessLength;
				}
			}
		}
		return bestMatch;
	}

	/**
	 * Return the DeployementDescriptor.
	 */
	public EObject getStandardDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		return getDeploymentDescriptor();
	}

	public boolean isDeploymentDescriptorSet() {
		return deploymentDescriptor != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	public boolean isWARFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		WebApp webApp = ((WebapplicationPackage) EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI)).getWebapplicationFactory().createWebApp();
		resource.setID(webApp, ArchiveConstants.WEBAPP_ID);
		setDeploymentDescriptorGen(webApp);
		resource.getContents().add(webApp);
		return webApp;
	}

	public void setDeploymentDescriptor(WebApp l) {
		this.setDeploymentDescriptorGen(l);
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.WAR_FILE__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.WAR_FILE__LAST_MODIFIED :
				return isSetLastModified();
			case CommonarchivePackage.WAR_FILE__SIZE :
				return isSetSize();
			case CommonarchivePackage.WAR_FILE__DIRECTORY_ENTRY :
				return isSetDirectoryEntry();
			case CommonarchivePackage.WAR_FILE__ORIGINAL_URI :
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.WAR_FILE__LOADING_CONTAINER :
				return loadingContainer != null;
			case CommonarchivePackage.WAR_FILE__CONTAINER :
				return getContainer() != null;
			case CommonarchivePackage.WAR_FILE__FILES :
				return files != null && !files.isEmpty();
			case CommonarchivePackage.WAR_FILE__TYPES :
				return types != null && !types.isEmpty();
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR :
				return deploymentDescriptor != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.WAR_FILE__URI :
				setURI((String) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__LAST_MODIFIED :
				setLastModified(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.WAR_FILE__SIZE :
				setSize(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.WAR_FILE__DIRECTORY_ENTRY :
				setDirectoryEntry(((Boolean) newValue).booleanValue());
				return;
			case CommonarchivePackage.WAR_FILE__ORIGINAL_URI :
				setOriginalURI((String) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__CONTAINER :
				setContainer((Container) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__FILES :
				getFiles().clear();
				getFiles().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__TYPES :
				getTypes().clear();
				getTypes().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((WebApp) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.WAR_FILE__URI :
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.WAR_FILE__LAST_MODIFIED :
				unsetLastModified();
				return;
			case CommonarchivePackage.WAR_FILE__SIZE :
				unsetSize();
				return;
			case CommonarchivePackage.WAR_FILE__DIRECTORY_ENTRY :
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.WAR_FILE__ORIGINAL_URI :
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.WAR_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) null);
				return;
			case CommonarchivePackage.WAR_FILE__CONTAINER :
				setContainer((Container) null);
				return;
			case CommonarchivePackage.WAR_FILE__FILES :
				getFiles().clear();
				return;
			case CommonarchivePackage.WAR_FILE__TYPES :
				getTypes().clear();
				return;
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((WebApp) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public WebApp getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			WebApp oldDeploymentDescriptor = deploymentDescriptor;
			deploymentDescriptor = (WebApp) EcoreUtil.resolve(deploymentDescriptor, this);
			if (deploymentDescriptor != oldDeploymentDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
			}
		}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public WebApp basicGetDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDeploymentDescriptorGen(WebApp newDeploymentDescriptor) {
		WebApp oldDeploymentDescriptor = deploymentDescriptor;
		deploymentDescriptor = newDeploymentDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.WAR_FILE__CONTAINER :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.WAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.WAR_FILE__FILES :
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
				case CommonarchivePackage.WAR_FILE__CONTAINER :
					return eBasicSetContainer(null, CommonarchivePackage.WAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.WAR_FILE__FILES :
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
				case CommonarchivePackage.WAR_FILE__CONTAINER :
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
			case CommonarchivePackage.WAR_FILE__URI :
				return getURI();
			case CommonarchivePackage.WAR_FILE__LAST_MODIFIED :
				return new Long(getLastModified());
			case CommonarchivePackage.WAR_FILE__SIZE :
				return new Long(getSize());
			case CommonarchivePackage.WAR_FILE__DIRECTORY_ENTRY :
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.WAR_FILE__ORIGINAL_URI :
				return getOriginalURI();
			case CommonarchivePackage.WAR_FILE__LOADING_CONTAINER :
				if (resolve)
					return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.WAR_FILE__CONTAINER :
				return getContainer();
			case CommonarchivePackage.WAR_FILE__FILES :
				return getFiles();
			case CommonarchivePackage.WAR_FILE__TYPES :
				return getTypes();
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR :
				if (resolve)
					return getDeploymentDescriptor();
				return basicGetDeploymentDescriptor();
		}
		return eDynamicGet(eFeature, resolve);
	}

	public ClassLoader createDynamicClassLoader(ClassLoader parentCl, ClassLoader extraCl) {
		return new WarFileDynamicClassLoader(this, parentCl, extraCl);
	}

}
