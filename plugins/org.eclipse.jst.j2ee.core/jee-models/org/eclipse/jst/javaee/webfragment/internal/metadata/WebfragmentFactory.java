/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebfragmentFactory.java,v 1.1 2009/10/15 18:52:20 canderson Exp $
 */
package org.eclipse.jst.javaee.webfragment.internal.metadata;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.webfragment.WebAppDeploymentDescriptor;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage
 * @generated
 */
public interface WebfragmentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebfragmentFactory eINSTANCE = org.eclipse.jst.javaee.webfragment.internal.impl.WebfragmentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Web App Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web App Deployment Descriptor</em>'.
	 * @generated
	 */
	WebAppDeploymentDescriptor createWebAppDeploymentDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WebfragmentPackage getWebfragmentPackage();

} //WebfragmentFactory
