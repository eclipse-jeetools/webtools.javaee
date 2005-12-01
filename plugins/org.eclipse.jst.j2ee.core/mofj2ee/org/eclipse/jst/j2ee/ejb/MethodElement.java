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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
/**
 * The method element is used to denote a method of an enterprise bean's
 * home or remote interface, or a set of methods. The ejb-name element
 * must be the name of one of the enterprise beans in declared in the
 * deployment descriptor; the optional method-intf element allows to
 * distinguish between a method with the same signature that is defined in
 * both the home and remote interface; the method-name element specifies
 * the method name; and the optional method-params elements identify a
 * single method among multiple methods with an overloaded method name.
 * 
 * There are three possible styles of the method element syntax:
 * 
 * 1. 	<method>
 * 		<ejb-name>EJBNAME<//ejb-name>
 *    		<method-name>*<//method-name>
 * 	<//method>
 * 
 *    This style is used to refer to all the methods of the specified
 *    enterprise bean's home and remote interfaces.
 * 
 * 2. 	<method>
 * 		<ejb-name>EJBNAME<//ejb-name>
 *    		<method-name>METHOD<//method-name>
 * 	<//method>>
 * 
 *    This style is used to refer to the specified method of the
 *    specified enterprise bean. If there are multiple methods with
 *    the same overloaded name, the element of this style refers to
 *    all the methods with the overloaded name.
 * 
 * 
 * 
 * 
 * 
 * 3. 	<method>
 * 		<ejb-name>EJBNAME<//ejb-name>
 *    		<method-name>METHOD<//method-name>
 * 		<method-params>
 *    			<method-param>PARAM-1<//method-param>
 *    			<method-param>PARAM-2<//method-param>
 *           			...
 *    			<method-param>PARAM-n<//method-param>
 * 		<//method-params>
 * 	<method>	
 * 
 *    This style is used to refer to a single method within a set of
 *    methods with an overloaded name. PARAM-1 through PARAM-n are the
 *    fully-qualified Java types of the method's input parameters (if
 *    the method has no input arguments, the method-params element
 *    contains no method-param elements). Arrays are specified by the
 *    array element's type, followed by one or more pair of square
 *    brackets (e.g. int[][]).
 * 
 * 
 * Used in: method-permission and container-transaction
 * 
 * Examples:
 * 
 *     Style 1: The following method element refers to all the methods of
 * 		the EmployeeService bean's home and remote interfaces:
 * 
 * 		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-name>*<//method-name>
 * 		<//method>
 * 
 * 	Style 2: The following method element refers to all the create
 * 		methods of the EmployeeService bean's home interface:
 * 
 *     		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-name>create<//method-name>
 * 		<//method>
 * 
 * 	Style 3: The following method element refers to the
 * 		create(String firstName, String LastName) method of the
 * 	 	EmployeeService bean's home interface.
 * 
 *     		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-name>create<//method-name>
 * 			<method-params>
 * 				<method-param>java.lang.String<//method-param>
 * 				<method-param>java.lang.String<//method-param>
 * 			<//method-params>
 * 		<//method>
 * 
 * 	
 * 	The following example illustrates a Style 3 element with
 * 	more complex parameter types. The method
 * 			foobar(char s, int i, int[] iar, mypackage.MyClass mycl,
 * 				mypackage.MyClass[][] myclaar)
 *         would be specified as:
 * 
 *     		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-name>foobar<//method-name>
 * 			<method-params>
 * 				<method-param>char<//method-param>
 * 				<method-param>int<//method-param>
 * 				<method-param>int[]<//method-param>
 * 				<method-param>mypackage.MyClass<//method-param>
 * 				<method-param>mypackage.MyClass[][]<//method-param>
 * 			<//method-params>
 * 		<//method>
 * 
 * 	The optional method-intf element can be used when it becomes
 *    necessary to differentiate between a method defined in the home
 *    interface and a method with the same name and signature that is
 *    defined in the remote interface.
 * 
 * 	For example, the method element
 * 
 *    		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-intf>Remote<//method-intf>
 * 			<method-name>create<//method-name>
 * 			<method-params>
 * 				<method-param>java.lang.String<//method-param>
 * 				<method-param>java.lang.String<//method-param>
 * 			<//method-params>
 * 		<//method>
 * 
 * 	can be used to differentiate the create(String, String) method
 *    defined in the remote interface from the create(String, String)
 *    method defined in the home interface, which would be defined as
 * 
 *    		<method>
 * 			<ejb-name>EmployeeService<//ejb-name>
 * 			<method-intf>Home<//method-intf>
 * 			<method-name>create<//method-name>
 * 			<method-params>
 * 				<method-param>java.lang.String<//method-param>
 * 				<method-param>java.lang.String<//method-param>
 * 			<//method-params>
 * 		<//method>

 * @since 1.0 */
public interface MethodElement extends EObject{

	public static final String RIGHT_PAREN = "("; //$NON-NLS-1$
	public static final String LEFT_PAREN = ")"; //$NON-NLS-1$
	public static final String COMMA = ","; //$NON-NLS-1$
	public void addMethodParams(String param);
/**
 * Set the params for this method element to an empty array, as opposed
 * to null.
 */
public void applyZeroParams();
/**
 * Return true if this MethodElement and @anotherMethodElement
 * have the same name, parameters, and type.
 */
boolean equalSignature(MethodElement anotherMethodElement) ;
	java.util.List getMethodParams() ;/**
 * Answer whether method params apply to this method, e.g., it is specific to one
 * overloaded method, even if the method is a zero parameter method.  Answer false if no
 * parameters apply, that is, the method element applies to all overloaded methods with this name
 */ 
/**
 * Answer a list of all the methods for which this method element applies.  The following rules are used:
 *
 * 1)  If the method element type is unspecified, the methods are obtained from the remote interface of the ejb;
 *		If it is specified, then the appropriate interface is used
 *
 * 2)  If the method name = "*", then all the PUBLIC methods for that interface are returned
 *
 * 3)  If the method name is specified, and no method params are specified, then all public methods for the interface
 *      having the same name are returned.
 *
 * 4)  If the method name and params are specified, then a zero or one element array is returned, containing the one and only method
 *      on the interface with the appropriate signature, if it exists
 */
public Method[] getMethods();
/**
 * Return the MethodElement that is most specific.
 */
MethodElement getMostSpecific(MethodElement aMethodElement, JavaClass aJavaClass);
/**
 * Return the signature.
 * For example:  setTwoParamMethod(java.lang.String, java.lang.String)
 */
String getSignature() ;
/**
 * Return the type cast to a JavaClass.
 */
JavaClass getTypeJavaClass() ;
public boolean hasMethodParams();
/**
 * Parse @aSignature setting the name and the params.
 * A signature example:  setTwoParamMethod(java.lang.String, java.lang.String)
 */
void initializeFromSignature(String aSignature) ;
boolean isDefault();
/**
 * Return true if this MethodElement and @anotherMethodElement
 * represent the same exact methods.
 */
boolean isEquivalent(MethodElement anotherMethodElement) ;
boolean isHome() ;
boolean isRemote() ;
boolean isUnspecified() ;
boolean isLocalHome() ;
boolean isLocal() ;
/**
 * Return true if this MethodElement represents one or more
 * methods.
 */
public boolean isValid() ;
/**
 * Return true if this MethodElement has the same basic signature as
 * @aMethod, ignoring the return type, thrown exceptions, and declaring class of 
 * this instance or @aMethod.  Return false, if params is null
 */
public boolean nameAndParamsEquals(Method aMethod);
	public void removeMethodParams(String param);
boolean represents(Method aMethod);
public void setIdToReadableString();
/**
 * Return true if this MethodElement uniquely identifies
 * @aMethod.  Return false, even if the MethodElement represents
 * @aMethod (i.e., @aMethod is contained in its list of methods).
 */
boolean uniquelyIdentifies(Method aMethod);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * The method-name element contains a name of an enterprise bean method,
	 * or the asterisk (*) character. The asterisk is used when the element
	 * denotes all the methods of an enterprise bean's remote and home
	 * interfaces.

	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Parms attribute
	 * The method-params element contains a list of the fully-qualified Java type
	 * names of the method parameters.  In the current implementation this is a space
	 * delimitted String.  A null string indicates a generic method element that can
	 * apply to multiple methods with the same name.  An empty string indicates a
	 * method with zero parameters.  
	 */
	String getParms();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Parms attribute
	 */
	void setParms(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Type attribute
	 */
	MethodElementKind getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodElement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.ejb.MethodElementKind
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(MethodElementKind value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodElement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(MethodElementKind)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodElement#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(MethodElementKind)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description element is used by the ejb-jar file producer to provide text
	 * describing the parent element.  The description element should include any
	 * information that the ejb-jar file producer wants to provide to the consumer of
	 * the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the
	 * ejb-jar file consumer will display the description when processing the parent
	 * element.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The EnterpriseBean reference
	 */
	EnterpriseBean getEnterpriseBean();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the EnterpriseBean reference
	 */
	void setEnterpriseBean(EnterpriseBean value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getMethodElement_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





