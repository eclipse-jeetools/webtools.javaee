/**
 * <copyright>
 * </copyright>
 *
 * $Id: AsyncMethodType.java,v 1.1 2009/10/15 18:52:01 canderson Exp $
 */
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Async Method Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The async-methodType element specifies that a session
 *         bean method has asynchronous invocation semantics.
 *         
 *         The optional method-intf element constrains the async
 *         method behavior to the client views of the given method-intf 
 *         type.  This value must be either Remote or Local.  
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodParams <em>Method Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodIntf <em>Method Intf</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType()
 * @extends JavaEEObject
 * @generated
 */
public interface AsyncMethodType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Name</em>' attribute.
	 * @see #setMethodName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_MethodName()
	 * @generated
	 */
	String getMethodName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodName <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Name</em>' attribute.
	 * @see #getMethodName()
	 * @generated
	 */
	void setMethodName(String value);

	/**
	 * Returns the value of the '<em><b>Method Params</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Params</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Params</em>' containment reference.
	 * @see #setMethodParams(MethodParams)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_MethodParams()
	 * @generated
	 */
	MethodParams getMethodParams();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodParams <em>Method Params</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Params</em>' containment reference.
	 * @see #getMethodParams()
	 * @generated
	 */
	void setMethodParams(MethodParams value);

	/**
	 * Returns the value of the '<em><b>Method Intf</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.MethodInterfaceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Intf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Intf</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.MethodInterfaceType
	 * @see #isSetMethodIntf()
	 * @see #unsetMethodIntf()
	 * @see #setMethodIntf(MethodInterfaceType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_MethodIntf()
	 * @generated
	 */
	MethodInterfaceType getMethodIntf();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodIntf <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Intf</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.MethodInterfaceType
	 * @see #isSetMethodIntf()
	 * @see #unsetMethodIntf()
	 * @see #getMethodIntf()
	 * @generated
	 */
	void setMethodIntf(MethodInterfaceType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodIntf <em>Method Intf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMethodIntf()
	 * @see #getMethodIntf()
	 * @see #setMethodIntf(MethodInterfaceType)
	 * @generated
	 */
	void unsetMethodIntf();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getMethodIntf <em>Method Intf</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Method Intf</em>' attribute is set.
	 * @see #unsetMethodIntf()
	 * @see #getMethodIntf()
	 * @see #setMethodIntf(MethodInterfaceType)
	 * @generated
	 */
	boolean isSetMethodIntf();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAsyncMethodType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AsyncMethodType
