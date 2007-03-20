/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspFactoryImpl.java,v 1.1 2007/03/20 18:04:43 jsholl Exp $
 */
package org.eclipse.jst.javaee.jsp.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeeFactory;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

import org.eclipse.jst.javaee.jsp.*;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspFactory;
import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JspFactoryImpl extends EFactoryImpl implements JspFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JspFactory init() {
		try {
			JspFactory theJspFactory = (JspFactory)EPackage.Registry.INSTANCE.getEFactory("http://java.sun.com/xml/ns/javaee/jsp"); //$NON-NLS-1$ 
			if (theJspFactory != null) {
				return theJspFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JspFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JspPackage.JSP_CONFIG: return (EObject)createJspConfig();
			case JspPackage.JSP_PROPERTY_GROUP: return (EObject)createJspPropertyGroup();
			case JspPackage.TAG_LIB: return (EObject)createTagLib();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JspPackage.JSP_FILE_TYPE:
				return createJspFileTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JspPackage.JSP_FILE_TYPE:
				return convertJspFileTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspConfig createJspConfig() {
		JspConfigImpl jspConfig = new JspConfigImpl();
		return jspConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspPropertyGroup createJspPropertyGroup() {
		JspPropertyGroupImpl jspPropertyGroup = new JspPropertyGroupImpl();
		return jspPropertyGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagLib createTagLib() {
		TagLibImpl tagLib = new TagLibImpl();
		return tagLib;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createJspFileTypeFromString(EDataType eDataType, String initialValue) {
		return (String)JavaeeFactory.eINSTANCE.createFromString(JavaeePackage.Literals.PATH_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJspFileTypeToString(EDataType eDataType, Object instanceValue) {
		return JavaeeFactory.eINSTANCE.convertToString(JavaeePackage.Literals.PATH_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspPackage getJspPackage() {
		return (JspPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static JspPackage getPackage() {
		return JspPackage.eINSTANCE;
	}

} //JspFactoryImpl
