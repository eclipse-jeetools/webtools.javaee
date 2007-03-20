/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationXMLProcessor.java,v 1.1 2007/03/20 18:04:44 jsholl Exp $
 */
package org.eclipse.jst.javaee.application.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ApplicationPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ApplicationResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ApplicationResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ApplicationResourceFactoryImpl());
		}
		return registrations;
	}

} //ApplicationXMLProcessor
