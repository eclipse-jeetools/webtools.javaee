/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationclientFactory.java,v 1.1 2007/05/16 06:42:28 cbridgha Exp $
 */
package org.eclipse.jst.javaee.applicationclient;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage
 * @generated
 */
public interface ApplicationclientFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationclientFactory eINSTANCE = org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Application Client</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Client</em>'.
	 * @generated
	 */
	ApplicationClient createApplicationClient();

	/**
	 * Returns a new object of class '<em>Application Client Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Client Deployment Descriptor</em>'.
	 * @generated
	 */
	ApplicationClientDeploymentDescriptor createApplicationClientDeploymentDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ApplicationclientPackage getApplicationclientPackage();

} //ApplicationclientFactory
