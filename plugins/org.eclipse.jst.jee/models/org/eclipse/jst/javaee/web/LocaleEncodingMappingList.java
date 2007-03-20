/**
 * <copyright>
 * </copyright>
 *
 * $Id: LocaleEncodingMappingList.java,v 1.1 2007/03/20 18:04:39 jsholl Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locale Encoding Mapping List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The locale-encoding-mapping-list contains one or more
 * 	locale-encoding-mapping(s).
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList()
 * @extends JavaEEObject
 * @generated
 */
public interface LocaleEncodingMappingList extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Local Encoding Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.LocaleEncodingMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Encoding Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Encoding Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList_LocalEncodingMappings()
	 * @generated
	 */
	List getLocalEncodingMappings();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // LocaleEncodingMappingList