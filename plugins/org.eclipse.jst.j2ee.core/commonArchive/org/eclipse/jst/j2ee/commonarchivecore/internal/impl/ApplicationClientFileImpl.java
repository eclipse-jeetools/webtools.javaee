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



import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
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
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.APPLICATION_CLIENT_FILE;
	}

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */
	@Override
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
	@Override
	public java.lang.String getDeploymentDescriptorUri() {
		return J2EEConstants.APP_CLIENT_DD_URI;
	}

	/**
	 * Return the DeployementDescriptor.
	 */
	@Override
	public EObject getStandardDeploymentDescriptor() throws DeploymentDescriptorLoadException {
		return getDeploymentDescriptor();
	}

	/**
	 * @see com.ibm.etools.commonarchive.File
	 */
	@Override
	public boolean isApplicationClientFile() {
		return true;
	}

	@Override
	public boolean isDeploymentDescriptorSet() {
		return deploymentDescriptor != null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	@Override
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		ApplicationClient aClient = ((ClientPackage) EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI)).getClientFactory().createApplicationClient();
		resource.setID(aClient, J2EEConstants.APP_CLIENT_ID);
		setDeploymentDescriptorGen(aClient);
		resource.getContents().add(aClient);
		return aClient;
	}

	@Override
	public void setDeploymentDescriptor(ApplicationClient l) {
		this.setDeploymentDescriptorGen(l);
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public ApplicationClient getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			InternalEObject oldDeploymentDescriptor = (InternalEObject)deploymentDescriptor;
			deploymentDescriptor = (ApplicationClient)eResolveProxy(oldDeploymentDescriptor);
			if (deploymentDescriptor != oldDeploymentDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR, oldDeploymentDescriptor, deploymentDescriptor));
			}
		}
		return deploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR:
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
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((ApplicationClient)newValue);
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
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((ApplicationClient)null);
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
			case CommonarchivePackage.APPLICATION_CLIENT_FILE__DEPLOYMENT_DESCRIPTOR:
				return deploymentDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

}
