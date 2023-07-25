/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRoleRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Servlet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The servletType is used to declare a servlet.
 *         It contains the declarative data of a
 *         servlet. If a jsp-file is specified and the load-on-startup
 *         element is present, then the JSP should be precompiled and
 *         loaded.
 *         
 *         Used in: web-app
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getServletName <em>Servlet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getServletClass <em>Servlet Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getJspFile <em>Jsp File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getLoadOnStartup <em>Load On Startup</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#isAsyncSupported <em>Async Supported</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getMultipartConfig <em>Multipart Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Servlet#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet()
 * @extends JavaEEObject
 * @generated
 */
public interface Servlet extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Servlet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servlet Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlet Name</em>' attribute.
	 * @see #setServletName(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_ServletName()
	 * @generated
	 */
	String getServletName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getServletName <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servlet Name</em>' attribute.
	 * @see #getServletName()
	 * @generated
	 */
	void setServletName(String value);

	/**
	 * Returns the value of the '<em><b>Servlet Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The servlet-class element contains the fully
	 *               qualified class name of the servlet.
	 *               
	 *               @since Java EE 5, Web 2.5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Servlet Class</em>' attribute.
	 * @see #setServletClass(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_ServletClass()
	 * @generated
	 */
	String getServletClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getServletClass <em>Servlet Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servlet Class</em>' attribute.
	 * @see #getServletClass()
	 * @generated
	 */
	void setServletClass(String value);

	/**
	 * Returns the value of the '<em><b>Jsp File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jsp File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jsp File</em>' attribute.
	 * @see #setJspFile(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_JspFile()
	 * @generated
	 */
	String getJspFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getJspFile <em>Jsp File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jsp File</em>' attribute.
	 * @see #getJspFile()
	 * @generated
	 */
	void setJspFile(String value);

	/**
	 * Returns the value of the '<em><b>Init Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Params</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Params</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_InitParams()
	 * @generated
	 */
	List<ParamValue> getInitParams();

	/**
	 * Returns the value of the '<em><b>Load On Startup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The load-on-startup element indicates that this
	 *             servlet should be loaded (instantiated and have
	 *             its init() called) on the startup of the web
	 *             application. The optional contents of these
	 *             element must be an integer indicating the order in
	 *             which the servlet should be loaded. If the value
	 *             is a negative integer, or the element is not
	 *             present, the container is free to load the servlet
	 *             whenever it chooses. If the value is a positive
	 *             integer or 0, the container must load and
	 *             initialize the servlet as the application is
	 *             deployed. The container must guarantee that
	 *             servlets marked with lower integers are loaded
	 *             before servlets marked with higher integers. The
	 *             container may choose the order of loading of
	 *             servlets with the same load-on-start-up value.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Load On Startup</em>' attribute.
	 * @see #setLoadOnStartup(Object)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_LoadOnStartup()
	 * @generated
	 */
	Object getLoadOnStartup();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getLoadOnStartup <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load On Startup</em>' attribute.
	 * @see #getLoadOnStartup()
	 * @generated
	 */
	void setLoadOnStartup(Object value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_Enabled()
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isEnabled <em>Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Async Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Async Supported</em>' attribute.
	 * @see #isSetAsyncSupported()
	 * @see #unsetAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_AsyncSupported()
	 * @generated
	 */
	boolean isAsyncSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isAsyncSupported <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async Supported</em>' attribute.
	 * @see #isSetAsyncSupported()
	 * @see #unsetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @generated
	 */
	void setAsyncSupported(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isAsyncSupported <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @generated
	 */
	void unsetAsyncSupported();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.Servlet#isAsyncSupported <em>Async Supported</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Async Supported</em>' attribute is set.
	 * @see #unsetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @generated
	 */
	boolean isSetAsyncSupported();

	/**
	 * Returns the value of the '<em><b>Run As</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Run As</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run As</em>' containment reference.
	 * @see #setRunAs(RunAs)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_RunAs()
	 * @generated
	 */
	RunAs getRunAs();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getRunAs <em>Run As</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run As</em>' containment reference.
	 * @see #getRunAs()
	 * @generated
	 */
	void setRunAs(RunAs value);

	/**
	 * Returns the value of the '<em><b>Security Role Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRoleRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Role Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Role Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_SecurityRoleRefs()
	 * @generated
	 */
	List<SecurityRoleRef> getSecurityRoleRefs();

	/**
	 * Returns the value of the '<em><b>Multipart Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multipart Config</em>' containment reference.
	 * @see #setMultipartConfig(MultipartConfigType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_MultipartConfig()
	 * @generated
	 */
	MultipartConfigType getMultipartConfig();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getMultipartConfig <em>Multipart Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multipart Config</em>' containment reference.
	 * @see #getMultipartConfig()
	 * @generated
	 */
	void setMultipartConfig(MultipartConfigType value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServlet_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Servlet#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Servlet