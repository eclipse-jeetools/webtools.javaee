/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation.modulemap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;


/**
 * @lastgen class UtilityJARMappingImpl extends EObjectImpl implements UtilityJARMapping, EObject {}
 * @deprecated
 * Use
 * <p>
 * Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .wtpmodules file
 */
public class UtilityJARMappingImpl extends EObjectImpl implements UtilityJARMapping {
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected UtilityJARMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ModulemapPackage.eINSTANCE.getUtilityJARMapping();
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME, oldProjectName, projectName));
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModulemapPackage.UTILITY_JAR_MAPPING__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME :
				return getProjectName();
			case ModulemapPackage.UTILITY_JAR_MAPPING__URI :
				return getUri();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME :
				return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
			case ModulemapPackage.UTILITY_JAR_MAPPING__URI :
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME :
				setProjectName((String) newValue);
				return;
			case ModulemapPackage.UTILITY_JAR_MAPPING__URI :
				setUri((String) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature.getFeatureID(), eFeature.getContainerClass())) {
			case ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME :
				setProjectName(PROJECT_NAME_EDEFAULT);
				return;
			case ModulemapPackage.UTILITY_JAR_MAPPING__URI :
				setUri(URI_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(EARCreationResourceHandler.getString("UtilityJARMappingImpl_UI_0", new Object[]{projectName, uri})); //$NON-NLS-1$
		return result.toString();
	}

} //UtilityJARMappingImpl
