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
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.client.ClientPackage;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


/**
 * @generated
 */
public class ApplicationClientFileImpl extends ModuleFileImpl implements ApplicationClientFile {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ApplicationClient deploymentDescriptor = null;

	public ApplicationClientFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getApplicationClientFile();
	}

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */
	public ApplicationClient getDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		ApplicationClient dd = this.getDeploymentDescriptorGen();
		if (dd == null && canLazyInitialize()) {
			try {
				getImportStrategy().importMetaData();
			} catch (Exception e) {
				throw new DeploymentDescriptorLoadException(getDeploymentDescriptorUri(), e);
			}
		}

		return this.getDeploymentDescriptorGen();
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	public java.lang.String getDeploymentDescriptorUri() {
		return J2EEConstants.APP_CLIENT_DD_URI;
	}

	/**
	 * Return the DeployementDescriptor.
	 */
	public EObject getStandardDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		return getDeploymentDescriptor();
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	public boolean isApplicationClientFile() {
		return true;
	}

	public boolean isDeploymentDescriptorSet() {
		return deploymentDescriptor != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		ApplicationClient aClient = ((ClientPackage) EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI)).getClientFactory().createApplicationClient();
		resource.setID(aClient, J2EEConstants.APP_CLIENT_ID);
		setDeploymentDescriptorGen(aClient);
		resource.getContents().add(aClient);
		return aClient;
	}

	public void setDeploymentDescriptor(ApplicationClient l) {
		this.setDeploymentDescriptorGen(l);
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
				case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER, msgs);
				case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
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
				case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
					return eBasicSetContainer(null, CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER, msgs);
				case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
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
				case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
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
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__URI :
				return getURI();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LAST_MODIFIED :
				return new Long(getLastModified());
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__SIZE :
				return new Long(getSize());
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DIRECTORY_ENTRY :
				return isDirectoryEntry() ? Boolean.TRUE : Boolean.FALSE;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__ORIGINAL_URI :
				return getOriginalURI();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LOADING_CONTAINER :
				if (resolve)
					return getLoadingContainer();
				return basicGetLoadingContainer();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
				return getContainer();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
				return getFiles();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__TYPES :
				return getTypes();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR :
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
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LAST_MODIFIED :
				return isSetLastModified();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__SIZE :
				return isSetSize();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DIRECTORY_ENTRY :
				return isSetDirectoryEntry();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__ORIGINAL_URI :
				return ORIGINAL_URI_EDEFAULT == null ? originalURI != null : !ORIGINAL_URI_EDEFAULT.equals(originalURI);
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LOADING_CONTAINER :
				return loadingContainer != null;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
				return getContainer() != null;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
				return files != null && !files.isEmpty();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__TYPES :
				return types != null && !types.isEmpty();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR :
				return deploymentDescriptor != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__URI :
				setURI((String) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LAST_MODIFIED :
				setLastModified(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__SIZE :
				setSize(((Long) newValue).longValue());
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DIRECTORY_ENTRY :
				setDirectoryEntry(((Boolean) newValue).booleanValue());
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__ORIGINAL_URI :
				setOriginalURI((String) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
				setContainer((Container) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
				getFiles().clear();
				getFiles().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__TYPES :
				getTypes().clear();
				getTypes().addAll((Collection) newValue);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((ApplicationClient) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__URI :
				setURI(URI_EDEFAULT);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LAST_MODIFIED :
				unsetLastModified();
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__SIZE :
				unsetSize();
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DIRECTORY_ENTRY :
				unsetDirectoryEntry();
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__ORIGINAL_URI :
				setOriginalURI(ORIGINAL_URI_EDEFAULT);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__LOADING_CONTAINER :
				setLoadingContainer((Container) null);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__CONTAINER :
				setContainer((Container) null);
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__FILES :
				getFiles().clear();
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__TYPES :
				getTypes().clear();
				return;
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR :
				setDeploymentDescriptor((ApplicationClient) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public ApplicationClient getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			ApplicationClient oldDeploymentDescriptor = deploymentDescriptor;
			deploymentDescriptor = (ApplicationClient) EcoreUtil.resolve(deploymentDescriptor, this);
			if (deploymentDescriptor != oldDeploymentDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
			}
		}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ApplicationClient basicGetDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDeploymentDescriptorGen(ApplicationClient newDeploymentDescriptor) {
		ApplicationClient oldDeploymentDescriptor = deploymentDescriptor;
		deploymentDescriptor = newDeploymentDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
	}

}
