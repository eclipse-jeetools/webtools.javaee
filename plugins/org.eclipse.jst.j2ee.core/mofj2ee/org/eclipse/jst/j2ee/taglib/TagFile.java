/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.taglib;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.DescriptionGroup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4
 * Defines an action in this tag library that is implemented
 * as a .tag file.
 * 
 * The tag-file element has two required subelements:
 * 
 * name    The unique action name
 * 
 * path    Where to find the .tag file implementing this
 *         action, relative to the root of the web application or
 * 	the root of the JAR file for a tag library packaged in
 * 	a JAR.  This must begin with /WEB-INF/tags if the .tag
 * 	file resides in the WAR, or /META-INF/tags if the .tag
 * 	file resides in a JAR.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.TagFile#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.TagFile#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.TagFile#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.taglib.TagFile#getTagExtensions <em>Tag Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getTagFile()
 * @model 
 * @generated
 */
public interface TagFile extends DescriptionGroup{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getTagFile_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.TagFile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getTagFile_Path()
	 * @model 
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.TagFile#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example</em>' attribute.
	 * @see #setExample(String)
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getTagFile_Example()
	 * @model 
	 * @generated
	 */
	String getExample();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.taglib.TagFile#getExample <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example</em>' attribute.
	 * @see #getExample()
	 * @generated
	 */
	void setExample(String value);

	/**
	 * Returns the value of the '<em><b>Tag Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.taglib.TldExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Extensions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.taglib.TaglibPackage#getTagFile_TagExtensions()
	 * @model type="org.eclipse.jst.j2ee.taglib.TldExtension" containment="true"
	 * @generated
	 */
	EList getTagExtensions();

} // TagFile
