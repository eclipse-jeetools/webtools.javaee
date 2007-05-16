/**
 * <copyright>
 * </copyright>
 *
 * $Id: JavaeeXMLProcessor.java,v 1.1 2007/05/16 06:42:26 cbridgha Exp $
 */
package org.eclipse.jst.javaee.core.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaeeXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		JavaeePackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the JavaeeResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new JavaeeResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new JavaeeResourceFactoryImpl());
		}
		return registrations;
	}

} //JavaeeXMLProcessor
