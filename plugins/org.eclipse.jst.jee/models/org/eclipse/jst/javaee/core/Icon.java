/**
 * <copyright>
 * </copyright>
 *
 * $Id: Icon.java,v 1.1 2007/03/20 18:04:35 jsholl Exp $
 */
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Icon</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The icon type contains small-icon and large-icon elements
 * 	that specify the file names for small and large GIF, JPEG,
 * 	or PNG icon images used to represent the parent element in a
 * 	GUI tool.
 * 
 * 	The xml:lang attribute defines the language that the
 * 	icon file names are provided in. Its value is "en" (English)
 * 	by default.
 * 
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
	 * 	    
	 * 
	 * 	      The small-icon element contains the name of a file
	 * 	      containing a small (16 x 16) icon image. The file
	 * 	      name is a relative path within the Deployment
	 * 	      Component's Deployment File.
	 * 
	 * 	      The image may be in the GIF, JPEG, or PNG format.
	 * 	      The icon can be used by tools.
	 * 
	 * 	      Example:
	 * 
	 * 	      &lt;small-icon&gt;employee-service-icon16x16.jpg&lt;/small-icon&gt;
	 * 
	 * 	      
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
	 * 	    
	 * 
	 * 	      The large-icon element contains the name of a file
	 * 	      containing a large
	 * 	      (32 x 32) icon image. The file name is a relative
	 * 	      path within the Deployment Component's Deployment
	 * 	      File.
	 * 
	 * 	      The image may be in the GIF, JPEG, or PNG format.
	 * 	      The icon can be used by tools.
	 * 
	 * 	      Example:
	 * 
	 * 	      &lt;large-icon&gt;employee-service-icon32x32.jpg&lt;/large-icon&gt;
	 * 
	 * 	      
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
	 * Attempting to install the relevant ISO 2- and 3-letter
	 *          codes as the enumerated possible values is probably never
	 *          going to be a realistic possibility.  See
	 *          RFC 3066 at http://www.ietf.org/rfc/rfc3066.txt and the IANA registry
	 *          at http://www.iana.org/assignments/lang-tag-apps.htm for
	 *          further information.
	 * 
	 *          The union allows for the 'un-declaration' of xml:lang with
	 *          the empty string.
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