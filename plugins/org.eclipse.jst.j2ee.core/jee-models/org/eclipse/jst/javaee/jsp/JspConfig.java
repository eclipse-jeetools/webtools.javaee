/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspConfig.java,v 1.1 2007/05/16 06:42:42 cbridgha Exp $
 */
package org.eclipse.jst.javaee.jsp;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The jsp-configType is used to provide global configuration
 * 	information for the JSP files in a web application. It has
 * 	two subelements, taglib and jsp-property-group.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspConfig#getTagLibs <em>Tag Libs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspConfig#getJspPropertyGroups <em>Jsp Property Groups</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspConfig#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspConfig()
 * @extends JavaEEObject
 * @generated
 */
public interface JspConfig extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Tag Libs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jsp.TagLib}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Libs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Libs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspConfig_TagLibs()
	 * @generated
	 */
	List getTagLibs();

	/**
	 * Returns the value of the '<em><b>Jsp Property Groups</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jsp.JspPropertyGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jsp Property Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jsp Property Groups</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspConfig_JspPropertyGroups()
	 * @generated
	 */
	List getJspPropertyGroups();

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
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspConfig_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspConfig#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // JspConfig