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



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.WarFileDynamicClassLoader;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
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
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.WAR_FILE;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public File addCopyClass(File aFile) throws DuplicateObjectException {
		if (aFile.isReadOnlyDirectory())
			throw new IllegalArgumentException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.add_copy_class_dir_EXC_, (new Object[]{aFile.getURI()}))); // = "Method addCopyClass not supported for directories :"
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
			throw new IllegalArgumentException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.add_copy_lib_dir_EXC_, (new Object[]{aFile.getURI()}))); // = "Method addCopyLib not supported for directories :"
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

	@Override
	public String getDeploymentDescriptorUri() {
		return J2EEConstants.WEBAPP_DD_URI;
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
		String[] prefixes = {J2EEConstants.META_INF, J2EEConstants.WEB_INF};
		return filterFilesWithoutPrefix(prefixes);
	}

	/**
	 * Overridden from superclass to include the claseses and libs of a WARFile There also is an
	 * assumption that this is an exploded war file The following is what should be returned:
	 * 
	 * 1) WAR directory + /WEB-INF/classes 2) all JAR files contained in [WAR directory +
	 * /WEB-INF/lib] 3) WAR directory
	 */
	@Override
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

	@Override
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
			if (aUri.startsWith(J2EEConstants.WEB_INF) && !aUri.startsWith(ArchiveConstants.WEBAPP_LIB_URI))
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
		else 
			return null;
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

	@Override
	public boolean isDeploymentDescriptorSet() {
		return deploymentDescriptor != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	@Override
	public boolean isWARFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	@Override
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		WebApp webApp = ((WebapplicationPackage) EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI)).getWebapplicationFactory().createWebApp();
		resource.setID(webApp, J2EEConstants.WEBAPP_ID);
		setDeploymentDescriptorGen(webApp);
		resource.getContents().add(webApp);
		return webApp;
	}

	public void setDeploymentDescriptor(WebApp l) {
		this.setDeploymentDescriptorGen(l);
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public WebApp getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			InternalEObject oldDeploymentDescriptor = (InternalEObject)deploymentDescriptor;
			deploymentDescriptor = (WebApp)eResolveProxy(oldDeploymentDescriptor);
			if (deploymentDescriptor != oldDeploymentDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
			}
		}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR:
				if (resolve) return getDeploymentDescriptor();
				return basicGetDeploymentDescriptor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((WebApp)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((WebApp)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonarchivePackage.WAR_FILE__DEPLOYMENT_DESCRIPTOR:
				return deploymentDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public ClassLoader createDynamicClassLoader(ClassLoader parentCl, ClassLoader extraCl) {
		return new WarFileDynamicClassLoader(this, parentCl, extraCl);
	}

}
