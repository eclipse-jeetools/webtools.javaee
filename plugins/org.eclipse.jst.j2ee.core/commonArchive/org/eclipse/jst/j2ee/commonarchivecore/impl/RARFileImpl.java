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



import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.Container;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.ConnectorDirectorySaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.util.RarFileDynamicClassLoader;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * @generated
 */
public class RARFileImpl extends ModuleFileImpl implements RARFile {

	/**
	 * The cached value of the '{@link #getDeploymentDescriptor() <em>Deployment Descriptor</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDeploymentDescriptor()
	 * @generated
	 * @ordered
	 */
	protected Connector deploymentDescriptor = null;

	public RARFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getRARFile();
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public File addCopyClass(File aFile) throws org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException {
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

	protected SaveStrategy createSaveStrategyForConnectorDirectory(java.io.File dir, int expansionFlags) {
		return new ConnectorDirectorySaveStrategyImpl(dir.getAbsolutePath(), expansionFlags);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public void extractToConnectorDirectory(java.lang.String aUri, int expansionFlags) throws SaveFailureException {
		java.io.File aDir = new java.io.File(aUri);
		if (getLoadStrategy().isUsing(aDir))
			throw new SaveFailureException(CommonArchiveResourceHandler.getString("Extract_destination_is_the_EXC_")); //$NON-NLS-1$ = "Extract destination is the same path as source file"

		try {
			SaveStrategy aSaveStrategy = createSaveStrategyForConnectorDirectory(aDir, expansionFlags);
			save(aSaveStrategy);
			aSaveStrategy.close();
		} catch (java.io.IOException ex) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString("error_saving_EXC_", (new Object[]{uri})), ex); //$NON-NLS-1$ = "Error saving "
		}

	}

	/**
	 * @see com.ibm.etools.commonarchive.RARFile
	 */
	public java.util.List getClasses() {
		return filterFilesByPrefix(ArchiveConstants.RAR_CLASSES_URI);
	}

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */
	public Connector getDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		Connector dd = deploymentDescriptor;
		if (dd == null && canLazyInitialize())
			try {
				getImportStrategy().importMetaData();
			} catch (Exception e) {
				throw new DeploymentDescriptorLoadException(getDeploymentDescriptorUri(), e);
			}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Connector basicGetDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	public String getDeploymentDescriptorUri() {
		return ArchiveConstants.RAR_DD_URI;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public java.util.List getResources() {
		String[] prefixes = {ArchiveConstants.META_INF, ArchiveConstants.WEB_INF};
		return filterFilesWithoutPrefix(prefixes);
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
	public boolean isRARFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		JcaPackage p = (JcaPackage) EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);
		JcaFactory fct = p.getJcaFactory();
		Connector connector = fct.createConnector();
		setDeploymentDescriptor(connector);
		resource.getContents().add(connector);
		return connector;
	}


	public void setDeploymentDescriptor(Connector l) {
		deploymentDescriptor = l;
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.RAR_FILE__CONTAINER :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.RAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.RAR_FILE__FILES :
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
				case CommonarchivePackage.RAR_FILE__CONTAINER :
					return eBasicSetContainer(null, CommonarchivePackage.RAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.RAR_FILE__FILES :
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
				case CommonarchivePackage.RAR_FILE__CONTAINER :
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
			case CommonarchivePackage.RAR_FILE__URI :
				return getURI();
			case CommonarchivePackage.RAR_FILE__LAST_MODIFIED :
				return new Long(getLastModified());
			case CommonarchivePackage.RAR_FILE__SIZE :
				return new Long(getSize());
			case CommonarchivePackage.RAR_FILE__DIRECTORY_ENTRY :
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.RAR_FILE__ORIGINAL_URI :
				return getOriginalURI();
			case CommonarchivePackage.RAR_FILE__LOADING_CONTAINER :
				if (resolve)
					return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.RAR_FILE__CONTAINER :
				return getContainer();
			case CommonarchivePackage.RAR_FILE__FILES :
				return getFiles();
			case CommonarchivePackage.RAR_FILE__TYPES :
				return getTypes();
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR :
				if (resolve)
					return getDeploymentDescriptor();
				return basicGetDeploymentDescriptor();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.RAR_FILE__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.RAR_FILE__LAST_MODIFIED :
				return isSetLastModified();
			case CommonarchivePackage.RAR_FILE__SIZE :
				return isSetSize();
			case CommonarchivePackage.RAR_FILE__DIRECTORY_ENTRY :
				return isSetDirectoryEntry();
			case CommonarchivePackage.RAR_FILE__ORIGINAL_URI :
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.RAR_FILE__LOADING_CONTAINER :
				return loadingContainer != null;
			case CommonarchivePackage.RAR_FILE__CONTAINER :
				return getContainer() != null;
			case CommonarchivePackage.RAR_FILE__FILES :
				return files != null && !files.isEmpty();
			case CommonarchivePackage.RAR_FILE__TYPES :
				return types != null && !types.isEmpty();
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR :
				return deploymentDescriptor != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.RAR_FILE__URI :
				setURI((String) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__LAST_MODIFIED :
				setLastModified(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.RAR_FILE__SIZE :
				setSize(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.RAR_FILE__DIRECTORY_ENTRY :
				setDirectoryEntry(((Boolean) newValue).booleanValue());
				return;
			case CommonarchivePackage.RAR_FILE__ORIGINAL_URI :
				setOriginalURI((String) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__CONTAINER :
				setContainer((Container) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__FILES :
				getFiles().clear();
				getFiles().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__TYPES :
				getTypes().clear();
				getTypes().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((Connector) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.RAR_FILE__URI :
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.RAR_FILE__LAST_MODIFIED :
				unsetLastModified();
				return;
			case CommonarchivePackage.RAR_FILE__SIZE :
				unsetSize();
				return;
			case CommonarchivePackage.RAR_FILE__DIRECTORY_ENTRY :
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.RAR_FILE__ORIGINAL_URI :
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.RAR_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) null);
				return;
			case CommonarchivePackage.RAR_FILE__CONTAINER :
				setContainer((Container) null);
				return;
			case CommonarchivePackage.RAR_FILE__FILES :
				getFiles().clear();
				return;
			case CommonarchivePackage.RAR_FILE__TYPES :
				getTypes().clear();
				return;
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((Connector) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	public ClassLoader createDynamicClassLoader(ClassLoader parentCl, ClassLoader extraCl) {
		return new RarFileDynamicClassLoader(this, parentCl, extraCl);
	}

}
