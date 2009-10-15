/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebappXMLProcessor.java,v 1.1 2009/10/15 18:52:04 canderson Exp $
 */
package org.eclipse.jst.javaee.webapp.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.webapp.internal.metadata.WebappPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WebappXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebappXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		WebappPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the WebappResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new WebappResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new WebappResourceFactoryImpl());
		}
		return registrations;
	}

} //WebappXMLProcessor
