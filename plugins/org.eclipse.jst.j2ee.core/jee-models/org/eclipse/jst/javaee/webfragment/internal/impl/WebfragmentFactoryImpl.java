/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebfragmentFactoryImpl.java,v 1.1 2009/10/15 18:52:20 canderson Exp $
 */
package org.eclipse.jst.javaee.webfragment.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.javaee.webfragment.*;

import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentFactory;
import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebfragmentFactoryImpl extends EFactoryImpl implements WebfragmentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebfragmentFactory init() {
		try {
			WebfragmentFactory theWebfragmentFactory = (WebfragmentFactory)EPackage.Registry.INSTANCE.getEFactory("http://java.sun.com/xml/ns/javaee/webfragment"); //$NON-NLS-1$ 
			if (theWebfragmentFactory != null) {
				return theWebfragmentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebfragmentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebfragmentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR: return (EObject)createWebAppDeploymentDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebAppDeploymentDescriptor createWebAppDeploymentDescriptor() {
		WebAppDeploymentDescriptorImpl webAppDeploymentDescriptor = new WebAppDeploymentDescriptorImpl();
		return webAppDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebfragmentPackage getWebfragmentPackage() {
		return (WebfragmentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WebfragmentPackage getPackage() {
		return WebfragmentPackage.eINSTANCE;
	}

} //WebfragmentFactoryImpl
