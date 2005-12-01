/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jsp;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSP Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The jsp-configType is used to provide global configuration
 * information for the JSP files in a web application. It has
 * two subelements, taglib and jsp-property-group.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.JSPConfig#getTagLibs <em>Tag Libs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.JSPConfig#getPropertyGroups <em>Property Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.jsp.JspPackage#getJSPConfig()
 * @model
 * @generated
 * @since 1.0 */
public interface JSPConfig extends EObject{
	/**
	 * Returns the value of the '<em><b>Tag Libs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.jsp.TagLibRefType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Libs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Libs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jsp.JspPackage#getJSPConfig_TagLibs()
	 * @model type="org.eclipse.jst.j2ee.jsp.TagLibRefType" containment="true"
	 * @generated
	 */
	EList getTagLibs();

	/**
	 * Returns the value of the '<em><b>Property Groups</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Groups</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jsp.JspPackage#getJSPConfig_PropertyGroups()
	 * @model type="org.eclipse.jst.j2ee.jsp.JSPPropertyGroup" containment="true"
	 * @generated
	 */
	EList getPropertyGroups();

} // JSPConfig
