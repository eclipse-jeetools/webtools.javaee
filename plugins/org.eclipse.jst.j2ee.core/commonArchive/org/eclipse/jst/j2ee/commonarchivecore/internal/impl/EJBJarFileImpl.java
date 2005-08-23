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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


/**
 * @generated
 */
public class EJBJarFileImpl extends ModuleFileImpl implements EJBJarFile {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EJBJar deploymentDescriptor = null;

	public EJBJarFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getEJBJarFile();
	}

	/**
	 * Used for tools performing selective import
	 */
	public List getAssociatedFiles(EnterpriseBean ejb) {

		List classNames = new java.util.ArrayList();
		List result = new ArrayList();
		if (ejb.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID) {
			classNames.add(ejb.getHomeInterfaceName());
			classNames.add(ejb.getRemoteInterfaceName());
		} else if (ejb.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
			if (ejb.hasRemoteClient()) {
				classNames.add(ejb.getHomeInterfaceName());
				classNames.add(ejb.getRemoteInterfaceName());
			}
			if (ejb.hasLocalClient()) {
				classNames.add(ejb.getLocalHomeInterfaceName());
				classNames.add(ejb.getLocalInterfaceName());
			}
		}
		classNames.add(ejb.getEjbClassName());

		if (ejb.isEntity()) {
			String className = ((Entity) ejb).getPrimaryKeyName();
			if (!className.startsWith("java"))//$NON-NLS-1$
				classNames.add(className);
		}
		for (int i = 0; i < classNames.size(); i++) {
			String className = (String) classNames.get(i);
			if (className == null)
				continue;
			String classUri = org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.classNameToUri(className);
			String javaUri = org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.classNameToJavaUri(className);
			try {
				result.add(getFile(classUri));
			} catch (java.io.FileNotFoundException iox) {
				//Do nothing - the file does not exist in this jar file
			}
			try {
				result.add(getFile(javaUri));
			} catch (java.io.FileNotFoundException iox) {
				//Do nothing - the file does not exist in this jar file
			}
		}
		return result;
	}

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */
	public EJBJar getDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		EJBJar dd = this.getDeploymentDescriptorGen();
		if (dd == null && canLazyInitialize()) {
			try {
				getImportStrategy().importMetaData();
			} catch (DeploymentDescriptorLoadException ex) {
				throw ex;
			} catch (Exception e) {
				throw new DeploymentDescriptorLoadException(getDeploymentDescriptorUri(), e);
			}
		}
		return this.getDeploymentDescriptorGen();
	}

	public java.lang.String getDeploymentDescriptorUri() {
		return J2EEConstants.EJBJAR_DD_URI;
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
	public boolean isEJBJarFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.EJBJarFile
	 */
	public boolean isImportedFrom10() {
		return getImportStrategy() != null && getImportStrategy().isEJB10();
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		EJBJar ejbJar = ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory().createEJBJar();
		resource.setID(ejbJar, J2EEConstants.EJBJAR_ID);
		setDeploymentDescriptorGen(ejbJar);
		resource.getContents().add(ejbJar);

		return ejbJar;
	}


	public void setDeploymentDescriptor(EJBJar l) {
		this.setDeploymentDescriptorGen(l);
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.EJB_JAR_FILE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.EJB_JAR_FILE__LAST_MODIFIED:
				return isSetLastModified();
			case CommonarchivePackage.EJB_JAR_FILE__SIZE:
				return isSetSize();
			case CommonarchivePackage.EJB_JAR_FILE__DIRECTORY_ENTRY:
				return isSetDirectoryEntry();
			case CommonarchivePackage.EJB_JAR_FILE__ORIGINAL_URI:
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.EJB_JAR_FILE__LOADING_CONTAINER:
				return loadingContainer != null;
			case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
				return getContainer() != null;
			case CommonarchivePackage.EJB_JAR_FILE__FILES:
				return files != null && !files.isEmpty();
			case CommonarchivePackage.EJB_JAR_FILE__TYPES:
				return types != null && !types.isEmpty();
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				return deploymentDescriptor != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.EJB_JAR_FILE__URI:
				setURI((String)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__LAST_MODIFIED:
				setLastModified(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.EJB_JAR_FILE__SIZE:
				setSize(((Long)newValue).longValue());
				return;
			case CommonarchivePackage.EJB_JAR_FILE__DIRECTORY_ENTRY:
				setDirectoryEntry(((Boolean)newValue).booleanValue());
				return;
			case CommonarchivePackage.EJB_JAR_FILE__ORIGINAL_URI:
				setOriginalURI((String)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__LOADING_CONTAINER:
				setLoadingContainer((Container)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
				setContainer((Container)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__FILES:
				getFiles().clear();
				getFiles().addAll((Collection)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection)newValue);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((EJBJar)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.EJB_JAR_FILE__URI:
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__LAST_MODIFIED:
				unsetLastModified();
				return;
			case CommonarchivePackage.EJB_JAR_FILE__SIZE:
				unsetSize();
				return;
			case CommonarchivePackage.EJB_JAR_FILE__DIRECTORY_ENTRY:
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.EJB_JAR_FILE__ORIGINAL_URI:
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__LOADING_CONTAINER:
				setLoadingContainer((Container)null);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
				setContainer((Container)null);
				return;
			case CommonarchivePackage.EJB_JAR_FILE__FILES:
				getFiles().clear();
				return;
			case CommonarchivePackage.EJB_JAR_FILE__TYPES:
				getTypes().clear();
				return;
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((EJBJar)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EJBJar getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			EJBJar oldDeploymentDescriptor = deploymentDescriptor;
			deploymentDescriptor = (EJBJar)eResolveProxy((InternalEObject)deploymentDescriptor);
			if (deploymentDescriptor != oldDeploymentDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
			}
		}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EJBJar basicGetDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDeploymentDescriptorGen(EJBJar newDeploymentDescriptor) {
		EJBJar oldDeploymentDescriptor = deploymentDescriptor;
		deploymentDescriptor = newDeploymentDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.EJB_JAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.EJB_JAR_FILE__FILES:
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
				case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
					return eBasicSetContainer(null, CommonarchivePackage.EJB_JAR_FILE__CONTAINER, msgs);
				case CommonarchivePackage.EJB_JAR_FILE__FILES:
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
				case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
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
			case CommonarchivePackage.EJB_JAR_FILE__URI:
				return getURI();
			case CommonarchivePackage.EJB_JAR_FILE__LAST_MODIFIED:
				return new Long(getLastModified());
			case CommonarchivePackage.EJB_JAR_FILE__SIZE:
				return new Long(getSize());
			case CommonarchivePackage.EJB_JAR_FILE__DIRECTORY_ENTRY:
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.EJB_JAR_FILE__ORIGINAL_URI:
				return getOriginalURI();
			case CommonarchivePackage.EJB_JAR_FILE__LOADING_CONTAINER:
				if (resolve) return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.EJB_JAR_FILE__CONTAINER:
				return getContainer();
			case CommonarchivePackage.EJB_JAR_FILE__FILES:
				return getFiles();
			case CommonarchivePackage.EJB_JAR_FILE__TYPES:
				return getTypes();
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				if (resolve) return getDeploymentDescriptor();
				return basicGetDeploymentDescriptor();
		}
		return eDynamicGet(eFeature, resolve);
	}

}
