/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebXMLProcessor.java,v 1.1 2007/03/20 18:04:40 jsholl Exp $
 */
package org.eclipse.jst.javaee.web.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WebXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		WebPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the WebResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new WebResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new WebResourceFactoryImpl());
		}
		return registrations;
	}

} //WebXMLProcessor
