/**
 * <copyright>
 * </copyright>
 *
 * $Id: EjbXMLProcessor.java,v 1.1 2007/03/20 18:04:41 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EjbXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		EjbPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the EjbResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new EjbResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new EjbResourceFactoryImpl());
		}
		return registrations;
	}

} //EjbXMLProcessor
