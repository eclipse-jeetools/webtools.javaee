/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationclientXMLProcessor.java,v 1.1 2007/05/16 06:42:41 cbridgha Exp $
 */
package org.eclipse.jst.javaee.applicationclient.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationclientXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationclientXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ApplicationclientPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ApplicationclientResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ApplicationclientResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ApplicationclientResourceFactoryImpl());
		}
		return registrations;
	}

} //ApplicationclientXMLProcessor
