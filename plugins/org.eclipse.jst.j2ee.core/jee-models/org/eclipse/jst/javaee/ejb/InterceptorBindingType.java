/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interceptor Binding Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         [
 *         The interceptor-bindingType element describes the binding of
 *         interceptor classes to beans within the ejb-jar file or .war file.
 *         It consists of :
 *         
 *         - An optional description.
 *         - The name of an ejb within the module or the wildcard value "*",
 *         which is used to define interceptors that are bound to all
 *         beans in the ejb-jar file or .war file.
 *         - A list of interceptor classes that are bound to the contents of
 *         the ejb-name element or a specification of the total ordering
 *         over the interceptors defined for the given level and above.
 *         - An optional exclude-default-interceptors element.  If set to true,
 *         specifies that default interceptors are not to be applied to 
 *         a bean-class and/or business method.
 *         - An optional exclude-class-interceptors element.  If set to true,
 *         specifies that class interceptors are not to be applied to 
 *         a business method.
 *         - An optional set of method elements for describing the name/params
 *         of a method-level interceptor.
 *         
 *         Interceptors bound to all classes using the wildcard syntax
 *         "*" are default interceptors for the components in the ejb-jar file or .war file. 
 *         In addition, interceptors may be bound at the level of the bean
 *         class (class-level interceptors) or business methods (method-level
 *         interceptors ).
 *         
 *         The binding of interceptors to classes is additive.  If interceptors
 *         are bound at the class-level and/or default-level as well as the
 *         method-level, both class-level and/or default-level as well as
 *         method-level will apply.
 *         
 *         The method-name element may be used to bind a constructor-level 
 *         interceptor using the unqualified name of the bean class as the value; 
 *         the optional method-params elements identify the constructor if a bean 
 *         class has a constructor annotated with the Inject annotation in addition 
 *         to a no-arg constructor. 
 *         
 *         There are four possible styles of the interceptor element syntax :
 *         
 *         1.
 *         <interceptor-binding>
 *         <ejb-name>*</ejb-name>
 *         <interceptor-class>INTERCEPTOR</interceptor-class>
 *         </interceptor-binding>
 *         
 *         Specifying the ejb-name as the wildcard value "*" designates
 *         default interceptors (interceptors that apply to all session and
 *         message-driven beans contained in the ejb-jar file or .war file).
 *         
 *         2. 
 *         <interceptor-binding>
 *         <ejb-name>EJBNAME</ejb-name>
 *         <interceptor-class>INTERCEPTOR</interceptor-class>
 *         </interceptor-binding>
 *         
 *         This style is used to refer to interceptors associated with the
 *         specified enterprise bean(class-level interceptors).
 *         
 *         3. 
 *         <interceptor-binding>
 *         <ejb-name>EJBNAME</ejb-name>
 *         <interceptor-class>INTERCEPTOR</interceptor-class>
 *         <method>
 *         <method-name>METHOD</method-name>
 *         </method>
 *         </interceptor-binding>
 *         
 *         This style is used to associate a method-level interceptor with 
 *         the specified enterprise bean.  If there are multiple methods
 *         with the same overloaded name, the element of this style refers
 *         to all the methods with the overloaded name.  Method-level
 *         interceptors can only be associated with business methods of the
 *         bean class.   Note that the wildcard value "*" cannot be used
 *         to specify method-level interceptors.
 *         
 *         4. 
 *         <interceptor-binding>
 *         <ejb-name>EJBNAME</ejb-name>
 *         <interceptor-class>INTERCEPTOR</interceptor-class>
 *         <method>
 *         <method-name>METHOD</method-name>
 *         <method-params>
 *         <method-param>PARAM-1</method-param>
 *         <method-param>PARAM-2</method-param>
 *         ...
 *         <method-param>PARAM-N</method-param>
 *         </method-params>
 *         </method>
 *         </interceptor-binding>
 *         
 *         This style is used to associate a method-level interceptor with 
 *         the specified method of the specified enterprise bean.  This 
 *         style is used to refer to a single method within a set of methods
 *         with an overloaded name.  The values PARAM-1 through PARAM-N
 *         are the fully-qualified Java types of the method's input parameters
 *         (if the method has no input arguments, the method-params element
 *         contains no method-param elements). Arrays are specified by the
 *         array element's type, followed by one or more pair of square 
 *         brackets (e.g. int[][]).
 *         
 * 
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getEjbName <em>Ejb Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getInterceptorClasses <em>Interceptor Classes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getInterceptorOrder <em>Interceptor Order</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeDefaultInterceptors <em>Exclude Default Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeClassInterceptors <em>Exclude Class Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType()
 * @extends JavaEEObject
 * @generated
 */
public interface InterceptorBindingType extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Ejb Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Name</em>' attribute.
	 * @see #setEjbName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_EjbName()
	 * @generated
	 */
	String getEjbName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getEjbName <em>Ejb Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Name</em>' attribute.
	 * @see #getEjbName()
	 * @generated
	 */
	void setEjbName(String value);

	/**
	 * Returns the value of the '<em><b>Interceptor Classes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interceptor Classes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interceptor Classes</em>' attribute list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_InterceptorClasses()
	 * @generated
	 */
	List<String> getInterceptorClasses();

	/**
	 * Returns the value of the '<em><b>Interceptor Order</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interceptor Order</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interceptor Order</em>' containment reference.
	 * @see #setInterceptorOrder(InterceptorOrderType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_InterceptorOrder()
	 * @generated
	 */
	InterceptorOrderType getInterceptorOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getInterceptorOrder <em>Interceptor Order</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interceptor Order</em>' containment reference.
	 * @see #getInterceptorOrder()
	 * @generated
	 */
	void setInterceptorOrder(InterceptorOrderType value);

	/**
	 * Returns the value of the '<em><b>Exclude Default Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude Default Interceptors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude Default Interceptors</em>' attribute.
	 * @see #isSetExcludeDefaultInterceptors()
	 * @see #unsetExcludeDefaultInterceptors()
	 * @see #setExcludeDefaultInterceptors(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_ExcludeDefaultInterceptors()
	 * @generated
	 */
	boolean isExcludeDefaultInterceptors();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeDefaultInterceptors <em>Exclude Default Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude Default Interceptors</em>' attribute.
	 * @see #isSetExcludeDefaultInterceptors()
	 * @see #unsetExcludeDefaultInterceptors()
	 * @see #isExcludeDefaultInterceptors()
	 * @generated
	 */
	void setExcludeDefaultInterceptors(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeDefaultInterceptors <em>Exclude Default Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExcludeDefaultInterceptors()
	 * @see #isExcludeDefaultInterceptors()
	 * @see #setExcludeDefaultInterceptors(boolean)
	 * @generated
	 */
	void unsetExcludeDefaultInterceptors();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeDefaultInterceptors <em>Exclude Default Interceptors</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Exclude Default Interceptors</em>' attribute is set.
	 * @see #unsetExcludeDefaultInterceptors()
	 * @see #isExcludeDefaultInterceptors()
	 * @see #setExcludeDefaultInterceptors(boolean)
	 * @generated
	 */
	boolean isSetExcludeDefaultInterceptors();

	/**
	 * Returns the value of the '<em><b>Exclude Class Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude Class Interceptors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude Class Interceptors</em>' attribute.
	 * @see #isSetExcludeClassInterceptors()
	 * @see #unsetExcludeClassInterceptors()
	 * @see #setExcludeClassInterceptors(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_ExcludeClassInterceptors()
	 * @generated
	 */
	boolean isExcludeClassInterceptors();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeClassInterceptors <em>Exclude Class Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude Class Interceptors</em>' attribute.
	 * @see #isSetExcludeClassInterceptors()
	 * @see #unsetExcludeClassInterceptors()
	 * @see #isExcludeClassInterceptors()
	 * @generated
	 */
	void setExcludeClassInterceptors(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeClassInterceptors <em>Exclude Class Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExcludeClassInterceptors()
	 * @see #isExcludeClassInterceptors()
	 * @see #setExcludeClassInterceptors(boolean)
	 * @generated
	 */
	void unsetExcludeClassInterceptors();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#isExcludeClassInterceptors <em>Exclude Class Interceptors</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Exclude Class Interceptors</em>' attribute is set.
	 * @see #unsetExcludeClassInterceptors()
	 * @see #isExcludeClassInterceptors()
	 * @see #setExcludeClassInterceptors(boolean)
	 * @generated
	 */
	boolean isSetExcludeClassInterceptors();

	/**
	 * Returns the value of the '<em><b>Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' containment reference.
	 * @see #setMethod(NamedMethodType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_Method()
	 * @generated
	 */
	NamedMethodType getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getMethod <em>Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' containment reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(NamedMethodType value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getInterceptorBindingType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // InterceptorBindingType