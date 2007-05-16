/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspXMLProcessor.java,v 1.1 2007/05/16 06:42:28 cbridgha Exp $
 */
package org.eclipse.jst.javaee.jsp.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JspXMLProcessor extends XMLProcessor {
	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JspXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		JspPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the JspResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new JspResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new JspResourceFactoryImpl());
		}
		return registrations;
	}

} //JspXMLProcessor
