/**
 * <copyright>
 * </copyright>
 *
 * $Id: CookieConfigType.java,v 1.1 2009/10/15 18:52:03 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.math.BigInteger;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cookie Config Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The cookie-configType defines the configuration for the
 *         session tracking cookies of this web application.
 *         
 *         Used in: session-config
 *         
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly <em>Http Only</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#isSecure <em>Secure</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getMaxAge <em>Max Age</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.CookieConfigType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType()
 * @extends JavaEEObject
 * @generated
 */
public interface CookieConfigType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The name that will be assigned to any session tracking
	 *             cookies created by this web application.
	 *             The default is JSESSIONID
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(CookieNameType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Name()
	 * @generated
	 */
	CookieNameType getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(CookieNameType value);

	/**
	 * Returns the value of the '<em><b>Domain</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The domain name that will be assigned to any session tracking
	 *             cookies created by this web application.
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Domain</em>' containment reference.
	 * @see #setDomain(CookieDomainType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Domain()
	 * @generated
	 */
	CookieDomainType getDomain();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getDomain <em>Domain</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain</em>' containment reference.
	 * @see #getDomain()
	 * @generated
	 */
	void setDomain(CookieDomainType value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The path that will be assigned to any session tracking
	 *             cookies created by this web application.
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Path</em>' containment reference.
	 * @see #setPath(CookiePathType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Path()
	 * @generated
	 */
	CookiePathType getPath();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getPath <em>Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' containment reference.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(CookiePathType value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The comment that will be assigned to any session tracking
	 *             cookies created by this web application.
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Comment</em>' containment reference.
	 * @see #setComment(CookieCommentType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Comment()
	 * @generated
	 */
	CookieCommentType getComment();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getComment <em>Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' containment reference.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(CookieCommentType value);

	/**
	 * Returns the value of the '<em><b>Http Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Specifies whether any session tracking cookies created 
	 *             by this web application will be marked as HttpOnly
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Http Only</em>' attribute.
	 * @see #isSetHttpOnly()
	 * @see #unsetHttpOnly()
	 * @see #setHttpOnly(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_HttpOnly()
	 * @generated
	 */
	boolean isHttpOnly();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly <em>Http Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Only</em>' attribute.
	 * @see #isSetHttpOnly()
	 * @see #unsetHttpOnly()
	 * @see #isHttpOnly()
	 * @generated
	 */
	void setHttpOnly(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly <em>Http Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHttpOnly()
	 * @see #isHttpOnly()
	 * @see #setHttpOnly(boolean)
	 * @generated
	 */
	void unsetHttpOnly();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly <em>Http Only</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Http Only</em>' attribute is set.
	 * @see #unsetHttpOnly()
	 * @see #isHttpOnly()
	 * @see #setHttpOnly(boolean)
	 * @generated
	 */
	boolean isSetHttpOnly();

	/**
	 * Returns the value of the '<em><b>Secure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Specifies whether any session tracking cookies created 
	 *             by this web application will be marked as secure.
	 *             When true, all session tracking cookies must be marked
	 *             as secure independent of the nature of the request that
	 *             initiated the corresponding session.
	 *             When false, the session cookie should only be marked secure
	 *             if the request that initiated the session was secure.
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Secure</em>' attribute.
	 * @see #isSetSecure()
	 * @see #unsetSecure()
	 * @see #setSecure(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Secure()
	 * @generated
	 */
	boolean isSecure();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isSecure <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secure</em>' attribute.
	 * @see #isSetSecure()
	 * @see #unsetSecure()
	 * @see #isSecure()
	 * @generated
	 */
	void setSecure(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isSecure <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSecure()
	 * @see #isSecure()
	 * @see #setSecure(boolean)
	 * @generated
	 */
	void unsetSecure();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#isSecure <em>Secure</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Secure</em>' attribute is set.
	 * @see #unsetSecure()
	 * @see #isSecure()
	 * @see #setSecure(boolean)
	 * @generated
	 */
	boolean isSetSecure();

	/**
	 * Returns the value of the '<em><b>Max Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The lifetime (in seconds) that will be assigned to any
	 *             session tracking cookies created by this web application.
	 *             Default is -1
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Age</em>' attribute.
	 * @see #setMaxAge(BigInteger)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_MaxAge()
	 * @generated
	 */
	BigInteger getMaxAge();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getMaxAge <em>Max Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Age</em>' attribute.
	 * @see #getMaxAge()
	 * @generated
	 */
	void setMaxAge(BigInteger value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getCookieConfigType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.CookieConfigType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // CookieConfigType
