/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationclientFactoryImpl.java,v 1.1 2007/05/16 06:42:40 cbridgha Exp $
 */
package org.eclipse.jst.javaee.applicationclient.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.javaee.applicationclient.*;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationclientFactoryImpl extends EFactoryImpl implements ApplicationclientFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ApplicationclientFactory init() {
		try {
			ApplicationclientFactory theApplicationclientFactory = (ApplicationclientFactory)EPackage.Registry.INSTANCE.getEFactory("http://java.sun.com/xml/ns/javaee/applicationclient"); //$NON-NLS-1$ 
			if (theApplicationclientFactory != null) {
				return theApplicationclientFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ApplicationclientFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationclientFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ApplicationclientPackage.APPLICATION_CLIENT: return (EObject)createApplicationClient();
			case ApplicationclientPackage.APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR: return (EObject)createApplicationClientDeploymentDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationClient createApplicationClient() {
		ApplicationClientImpl applicationClient = new ApplicationClientImpl();
		return applicationClient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationClientDeploymentDescriptor createApplicationClientDeploymentDescriptor() {
		ApplicationClientDeploymentDescriptorImpl applicationClientDeploymentDescriptor = new ApplicationClientDeploymentDescriptorImpl();
		return applicationClientDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationclientPackage getApplicationclientPackage() {
		return (ApplicationclientPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static ApplicationclientPackage getPackage() {
		return ApplicationclientPackage.eINSTANCE;
	}

} //ApplicationclientFactoryImpl
