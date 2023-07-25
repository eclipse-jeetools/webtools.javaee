/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ConnectorDirectorySaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.RarFileDynamicClassLoader;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * @generated
 */
public class RARFileImpl extends ModuleFileImpl implements RARFile {

	/**
	 * The cached value of the '{@link #getDeploymentDescriptor() <em>Deployment Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.RAR_FILE;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public File addCopyClass(File aFile) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException {
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

	protected SaveStrategy createSaveStrategyForConnectorDirectory(java.io.File dir, int expansionFlags) {
		return new ConnectorDirectorySaveStrategyImpl(dir.getAbsolutePath(), expansionFlags);
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	@Override
	public void extractToConnectorDirectory(java.lang.String aUri, int expansionFlags) throws SaveFailureException {
		java.io.File aDir = new java.io.File(aUri);
		if (getLoadStrategy().isUsing(aDir))
			throw new SaveFailureException(CommonArchiveResourceHandler.Extract_destination_is_the_EXC_); // = "Extract destination is the same path as source file"

		try {
			SaveStrategy aSaveStrategy = createSaveStrategyForConnectorDirectory(aDir, expansionFlags);
			save(aSaveStrategy);
			aSaveStrategy.close();
		} catch (java.io.IOException ex) {
			throw new SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_, (new Object[]{uri})), ex); // = "Error saving "
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
	@Override
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
	 * @generated
	 */
	public Connector basicGetDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	@Override
	public String getDeploymentDescriptorUri() {
		return J2EEConstants.RAR_DD_URI;
	}

	/**
	 * @see com.ibm.etools.commonarchive.WARFile
	 */
	public java.util.List getResources() {
		String[] prefixes = {J2EEConstants.META_INF, J2EEConstants.WEB_INF};
		return filterFilesWithoutPrefix(prefixes);
	}

	/**
	 * Return the DeployementDescriptor.
	 */
	@Override
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
	public boolean isRARFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	@Override
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		JcaPackage p = (JcaPackage) EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);
		JcaFactory fct = p.getJcaFactory();
		Connector connector = fct.createConnector();
		setDeploymentDescriptor(connector);
		resource.getContents().add(connector);
		return connector;
	}


	@Override
	public void setDeploymentDescriptor(Connector l) {
		deploymentDescriptor = l;
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR:
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
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((Connector)newValue);
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
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((Connector)null);
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
			case CommonarchivePackage.RAR_FILE__DEPLOYMENT_DESCRIPTOR:
				return deploymentDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public ClassLoader createDynamicClassLoader(ClassLoader parentCl, ClassLoader extraCl) {
		return new RarFileDynamicClassLoader(this, parentCl, extraCl);
	}

}
