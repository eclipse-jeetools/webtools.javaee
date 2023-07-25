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



import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
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
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.EJB_JAR_FILE;
	}

	/**
	 * Used for tools performing selective import
	 */
	@Override
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
	@Override
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

	@Override
	public java.lang.String getDeploymentDescriptorUri() {
		return J2EEConstants.EJBJAR_DD_URI;
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
	public boolean isEJBJarFile() {
		return true;
	}

	/**
	 * @see com.ibm.etools.commonarchive.EJBJarFile
	 */
	@Override
	public boolean isImportedFrom10() {
		return getImportStrategy() != null && getImportStrategy().isEJB10();
	}

	/**
	 * @see com.ibm.etools.commonarchive.impl.ModuleFileImpl
	 */
	@Override
	public org.eclipse.emf.ecore.EObject makeDeploymentDescriptor(XMLResource resource) {
		EJBJar ejbJar = ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory().createEJBJar();
		resource.setID(ejbJar, J2EEConstants.EJBJAR_ID);
		setDeploymentDescriptorGen(ejbJar);
		resource.getContents().add(ejbJar);

		return ejbJar;
	}


	@Override
	public void setDeploymentDescriptor(EJBJar l) {
		this.setDeploymentDescriptorGen(l);
		replaceRoot(getMofResourceMakeIfNecessary(getDeploymentDescriptorUri()), l);
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EJBJar getDeploymentDescriptorGen() {
		if (deploymentDescriptor != null && deploymentDescriptor.eIsProxy()) {
			InternalEObject oldDeploymentDescriptor = (InternalEObject)deploymentDescriptor;
			deploymentDescriptor = (EJBJar)eResolveProxy(oldDeploymentDescriptor);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
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
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((EJBJar)newValue);
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
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				setDeploymentDescriptor((EJBJar)null);
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
			case CommonarchivePackage.EJB_JAR_FILE__DEPLOYMENT_DESCRIPTOR:
				return deploymentDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

}
