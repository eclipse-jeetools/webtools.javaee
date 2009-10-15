/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebfragmentXMLProcessor.java,v 1.1 2009/10/15 18:52:17 canderson Exp $
 */
package org.eclipse.jst.javaee.webfragment.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WebfragmentXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebfragmentXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		WebfragmentPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the WebfragmentResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new WebfragmentResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new WebfragmentResourceFactoryImpl());
		}
		return registrations;
	}

} //WebfragmentXMLProcessor
