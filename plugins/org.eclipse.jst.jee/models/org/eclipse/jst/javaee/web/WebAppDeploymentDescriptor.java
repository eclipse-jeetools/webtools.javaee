/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebAppDeploymentDescriptor.java,v 1.1 2007/03/20 18:04:39 jsholl Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.Map;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>App Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getWebApp <em>Web App</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppDeploymentDescriptor()
 * @extends JavaEEObject
 * @generated
 */
public interface WebAppDeploymentDescriptor extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppDeploymentDescriptor_Mixed()
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppDeploymentDescriptor_XMLNSPrefixMap()
	 * @generated
	 */
	Map getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppDeploymentDescriptor_XSISchemaLocation()
	 * @generated
	 */
	Map getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Web App</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	The web-app element is the root of the deployment
	 * 	descriptor for a web application.  Note that the sub-elements
	 * 	of this element can be in the arbitrary order. Because of
	 * 	that, the multiplicity of the elements of distributable,
	 * 	session-config, welcome-file-list, jsp-config, login-config,
	 * 	and locale-encoding-mapping-list was changed from "?" to "*"
	 * 	in this schema.  However, the deployment descriptor instance
	 * 	file must not contain multiple elements of session-config,
	 * 	jsp-config, and login-config. When there are multiple elements of
	 * 	welcome-file-list or locale-encoding-mapping-list, the container
	 * 	must concatenate the element contents.  The multiple occurence
	 * 	of the element distributable is redundant and the container
	 * 	treats that case exactly in the same way when there is only
	 * 	one distributable.
	 * 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Web App</em>' containment reference.
	 * @see #setWebApp(WebApp)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebAppDeploymentDescriptor_WebApp()
	 * @generated
	 */
	WebApp getWebApp();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getWebApp <em>Web App</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web App</em>' containment reference.
	 * @see #getWebApp()
	 * @generated
	 */
	void setWebApp(WebApp value);

} // WebAppDeploymentDescriptor