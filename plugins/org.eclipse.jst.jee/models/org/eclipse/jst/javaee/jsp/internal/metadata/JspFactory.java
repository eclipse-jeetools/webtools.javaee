/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspFactory.java,v 1.1 2007/03/20 18:04:44 jsholl Exp $
 */
package org.eclipse.jst.javaee.jsp.internal.metadata;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.jsp.JspConfig;
import org.eclipse.jst.javaee.jsp.JspPropertyGroup;
import org.eclipse.jst.javaee.jsp.TagLib;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage
 * @generated
 */
public interface JspFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JspFactory eINSTANCE = org.eclipse.jst.javaee.jsp.internal.impl.JspFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Config</em>'.
	 * @generated
	 */
	JspConfig createJspConfig();

	/**
	 * Returns a new object of class '<em>Property Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Group</em>'.
	 * @generated
	 */
	JspPropertyGroup createJspPropertyGroup();

	/**
	 * Returns a new object of class '<em>Tag Lib</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Lib</em>'.
	 * @generated
	 */
	TagLib createTagLib();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JspPackage getJspPackage();

} //JspFactory
