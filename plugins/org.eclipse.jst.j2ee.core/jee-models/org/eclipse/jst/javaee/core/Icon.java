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
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Icon</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The icon type contains small-icon and large-icon elements
 *         that specify the file names for small and large GIF, JPEG,
 *         or PNG icon images used to represent the parent element in a
 *         GUI tool. 
 *         
 *         The xml:lang attribute defines the language that the
 *         icon file names are provided in. Its value is "en" (English)
 *         by default. 
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.Icon#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.Icon#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.Icon#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.Icon#getLang <em>Lang</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIcon()
 * @extends JavaEEObject
 * @generated
 */
public interface Icon extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The small-icon element contains the name of a file
	 *             containing a small (16 x 16) icon image. The file
	 *             name is a relative path within the Deployment
	 *             Component's Deployment File.
	 *             
	 *             The image may be in the GIF, JPEG, or PNG format.
	 *             The icon can be used by tools.
	 *             
	 *             Example:
	 *             
	 *             <small-icon>employee-service-icon16x16.jpg</small-icon>
	 *             
	 * 
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Small Icon</em>' attribute.
	 * @see #setSmallIcon(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIcon_SmallIcon()
	 * @generated
	 */
	String getSmallIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.Icon#getSmallIcon <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small Icon</em>' attribute.
	 * @see #getSmallIcon()
	 * @generated
	 */
	void setSmallIcon(String value);

	/**
	 * Returns the value of the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The large-icon element contains the name of a file
	 *             containing a large
	 *             (32 x 32) icon image. The file name is a relative 
	 *             path within the Deployment Component's Deployment
	 *             File.
	 *             
	 *             The image may be in the GIF, JPEG, or PNG format.
	 *             The icon can be used by tools.
	 *             
	 *             Example:
	 *             
	 *             <large-icon>employee-service-icon32x32.jpg</large-icon>
	 *             
	 * 
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Large Icon</em>' attribute.
	 * @see #setLargeIcon(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIcon_LargeIcon()
	 * @generated
	 */
	String getLargeIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.Icon#getLargeIcon <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Large Icon</em>' attribute.
	 * @see #getLargeIcon()
	 * @generated
	 */
	void setLargeIcon(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIcon_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.Icon#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *     <div xmlns="http://www.w3.org/1999/xhtml">
	 *      
	 *       <h3>lang (as an attribute name)</h3>
	 *       <p>
	 *        denotes an attribute whose value
	 *        is a language code for the natural language of the content of
	 *        any element; its value is inherited.  This name is reserved
	 *        by virtue of its definition in the XML specification.</p>
	 *      
	 *     </div>
	 *     <div xmlns="http://www.w3.org/1999/xhtml">
	 *      <h4>Notes</h4>
	 *      <p>
	 *       Attempting to install the relevant ISO 2- and 3-letter
	 *       codes as the enumerated possible values is probably never
	 *       going to be a realistic possibility.  
	 *      </p>
	 *      <p>
	 *       See BCP 47 at <a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
	 *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt</a>
	 *       and the IANA language subtag registry at
	 *       <a href="http://www.iana.org/assignments/language-subtag-registry">
	 *        http://www.iana.org/assignments/language-subtag-registry</a>
	 *       for further information.
	 *      </p>
	 *      <p>
	 *       The union allows for the 'un-declaration' of xml:lang with
	 *       the empty string.
	 *      </p>
	 *     </div>
	 *    
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getIcon_Lang()
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.Icon#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

} // Icon