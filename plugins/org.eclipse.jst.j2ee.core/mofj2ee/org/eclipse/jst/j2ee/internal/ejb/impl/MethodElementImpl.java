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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.util.MethodElementHelper;


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

 */
public class MethodElementImpl extends EObjectImpl implements MethodElement {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getParms() <em>Parms</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParms()
	 * @generated
	 * @ordered
	 */
	protected static final String PARMS_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String parms = PARMS_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final MethodElementKind TYPE_EDEFAULT = MethodElementKind.UNSPECIFIED_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected MethodElementKind type = TYPE_EDEFAULT;
	/**
	 * This is true if the Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet = false;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EnterpriseBean enterpriseBean = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public MethodElementImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getMethodElement();
	}

public void addMethodParams(String param) { 
	String oldParms = getParms();
	if (oldParms == null) {
		setParms(param);
	} else {
		setParms(oldParms.concat(" ").concat(param)); //$NON-NLS-1$
	}
}
/**
 * Set the params for this method element to an empty array, as opposed
 * to null.
 */
public void applyZeroParams() {
	setParms(""); //$NON-NLS-1$
}
public boolean equalSignature(MethodElement anotherMethodElement) {
	boolean equal = getName().equals(anotherMethodElement.getName());
	if (equal) {
		equal = parmsEqual(anotherMethodElement);
		if (equal) {
			if (getType() == null)
				equal = anotherMethodElement.getType() == null;
			else
				equal = getType().equals(anotherMethodElement.getType());
		}
	}
	return equal;
}
public java.util.List getMethodParams() { 
	StringTokenizer tok = getMethodParamsTokenizer();
	java.util.List v = new ArrayList();
	java.util.List paramsList = new ArrayList();
	String current = null;
	if (tok != null) {
		while (current != null || tok.hasMoreTokens()) {
			String peek = null;
			if (current == null)
				current = tok.nextToken();
			if (tok.hasMoreTokens()) {
				peek = tok.nextToken();
				if (peek.startsWith("[")) { //$NON-NLS-1$
					current += peek;
					peek = null;
				}
			}
			v.add(current);
			if (peek != null)
				current = peek;
			else
				current = null;
		}

	}
	
	/*
		 * This is a hack to make sure that for old XMI generated files, that ven if there was ',' separated 
		 * params, it parses them back out right.  To support 4.0.X AAT generated XMI files with runAs roles 
		 * for methods.
		 */
	for (int i = 0; i < v.size(); i++)
	{
		tok = new StringTokenizer((String)v.get(i),","); //$NON-NLS-1$
		if (tok != null)
		{
			while (tok.hasMoreTokens())
			{
				paramsList.add(tok.nextToken());
			}
		}
	}
	return paramsList;
}
private StringTokenizer getMethodParamsTokenizer() {
	//This method is a hack for now; the cardinality is wrong for the params
	String aParms = getParms();
	if (aParms == null || aParms.length() == 0) {
		return null;
	}
	return new StringTokenizer(getParms());
}
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
public Method[] getMethods() {
	EnterpriseBean ejb = getEnterpriseBean();
	if(ejb == null)
	    return new Method[0];
	List result = null;
	switch (getType().getValue()) {
		case MethodElementKind.HOME :
			{
				result = getMethods(ejb.getHomeInterface());
				break;
			}
		case MethodElementKind.REMOTE :
			{
				result = getMethods(ejb.getRemoteInterface());
				break;
			}
		case MethodElementKind.LOCAL_HOME :
			{
				result = getMethods(ejb.getLocalHomeInterface());
				break;
			}
		case MethodElementKind.LOCAL :
			{
				result = getMethods(ejb.getLocalInterface());
				break;
			}
		case MethodElementKind.SERVICE_ENDPOINT :
		   {
		    	if(ejb.isSession()) {
		    	    result = getMethods(((Session)ejb).getServiceEndpoint());
		    	    break;
		    	}
		   }
		case MethodElementKind.UNSPECIFIED :
			{
				if (ejb.isMessageDriven())
					result = getMethods(ejb.getEjbClass());
				else {
					result = new ArrayList();
					result.addAll(getMethods(ejb.getHomeInterface()));
					result.addAll(getMethods(ejb.getRemoteInterface()));
					result.addAll(getMethods(ejb.getLocalHomeInterface()));
					result.addAll(getMethods(ejb.getLocalInterface()));
				}
				break;
			}
	}
	return (Method[]) result.toArray(new Method[result.size()]);
}
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
private List getMethods(JavaClass javaClass) {
	if (javaClass == null) return Collections.EMPTY_LIST;
	List result = null;
	String methodName = getName().trim();
	if (name.equals("*"))  //$NON-NLS-1$
		result = javaClass.getPublicMethodsExtended();
	else if (hasMethodParams()) {
		result = new ArrayList();
		Method method = javaClass.getPublicMethodExtended(name, getMethodParams());
		if (method != null)
			result.add(method);
	} else
 		result = javaClass.getPublicMethodsExtendedNamed(methodName);
 
 	return result;
}
/**
 * Return the MethodElement that is most specific.
 */
public MethodElement getMostSpecific(MethodElement aMethodElement, JavaClass aClass) {
	if (aMethodElement == null) return this;
	if (aMethodElement.isDefault() && !isDefault())
		return this;
	if (!aMethodElement.isDefault() && isDefault())
		return aMethodElement;
	if (aMethodElement.hasMethodParams() && !hasMethodParams())
		return aMethodElement;
	if (!aMethodElement.hasMethodParams() && hasMethodParams())
		return this;
	if (isUnspecified() && !aMethodElement.isUnspecified())
		return aMethodElement;
	return this;
}
public static MethodElement getMostSpecificMethodElement(List methodElements, Method aMethod) {
	MethodElement specificME = null;
	if (aMethod != null) {
		Iterator it = methodElements.iterator();
		MethodElement me;
		while (it.hasNext()) {
			me = (MethodElement) it.next();
			if (me.represents(aMethod)) {
				if (me.uniquelyIdentifies(aMethod))
					return me;
				else if (specificME == null)
					specificME = me;
				else
					specificME = specificME.getMostSpecific(me, aMethod.getJavaClass());
			}
		}
	}
	return specificME;
}
protected String getParmsString() {
	String parmString = getParms();
	if (parmString == null)
		parmString = ""; //$NON-NLS-1$
	return parmString;
}
/**
 * Return a String array for the possible MethodElement type names.
 */
public static String[] getPossibleTypeNames() {
	EjbPackage pack = EjbFactoryImpl.getPackage();
	List literals = pack.getMethodElementKind().getELiterals();
	String[] names = new String[literals.size()];
	for (int i = 0; i < literals.size(); i++)
		names[i] = literals.get(i).toString();
	return names;
}
/**
 * Return the signature.
 * For example:  setTwoParamMethod(java.lang.String, java.lang.String)
 */
public String getSignature() {
	if (isDefault())
		return getName();
	StringBuffer buf = new StringBuffer();
	buf.append(getName());
	if (hasMethodParams()){
		buf.append(RIGHT_PAREN);
		StringTokenizer tok = getMethodParamsTokenizer();
		if (tok != null) {
			while (tok.hasMoreTokens()) {
				buf.append(tok.nextToken());
				if (tok.hasMoreTokens())
					buf.append(COMMA);		
			}			
		}
		buf.append(LEFT_PAREN);
	}
	return buf.toString();
}
// Returns null if the EEnum is UNSPECIFIED
// unless it is a MessageDriven bean.
public JavaClass getTypeJavaClass() {
	if (isHome())
		return getEnterpriseBean().getHomeInterface();
	else if (isRemote())
		return getEnterpriseBean().getRemoteInterface();
	else if (isLocalHome())
		return getEnterpriseBean().getLocalHomeInterface();
	else if (isLocal())
		return getEnterpriseBean().getLocalInterface();
	else if (isUnspecified() && getEnterpriseBean().isMessageDriven())
		return getEnterpriseBean().getEjbClass();
	else
		return null;
}
	/**
 * Answer whether method params apply to this method, e.g., it is specific to one
 * overloaded method, even if the method is a zero parameter method.  Answer false if no
 * parameters apply, that is, the method element applies to all overloaded methods with this name
 */
public boolean hasMethodParams() {
	return getParms() != null;
}
/**
 * Parse @aSignature setting the name and the params.
 * A signature example:  setTwoParamMethod(java.lang.String, java.lang.String)
 */
public void initializeFromSignature(String aSignature) {
	parseSignature(aSignature);
}
public boolean isDefault() {
    return JavaClass.DEFAULT_METHOD_NAME.equals(getName());
}
/**
 * Return true if this MethodElement and @anotherMethodElement
 * represent the same exact methods.
 */
public boolean isEquivalent(MethodElement anotherMethodElement) {
	boolean equal = equalSignature(anotherMethodElement);
	if (equal)
		equal = getEnterpriseBean() == anotherMethodElement.getEnterpriseBean();
	return equal;
}
public boolean isHome() {
	return getType().getValue() == MethodElementKind.HOME;
}
public boolean isRemote() {
	return getType().getValue() == MethodElementKind.REMOTE;
}
public boolean isUnspecified() {
	return getType().getValue() == MethodElementKind.UNSPECIFIED;
}
public boolean isLocalHome() {
	return getType().getValue() == MethodElementKind.LOCAL_HOME;
}
public boolean isLocal() {
	return getType().getValue() == MethodElementKind.LOCAL;
}
/**
 * Return true if this MethodElement represents one or more
 * methods.
 */
public boolean isValid() {
	return getMethods().length > 0;
}
/**
 * Return true only if all the parameters for @aMethod
 * matches the names in the list of parameters.
 */
public boolean matchesParams(Method aMethod) {
	if (aMethod == null) return false;
	List params = getMethodParams();
	JavaParameter[] methodParams = aMethod.listParametersWithoutReturn();
	if (params.size() != methodParams.length)
		return false;
	for (int i = 0; i < methodParams.length; i++){
		String parameterType = ((JavaHelpers)methodParams[i].getEType()).getQualifiedName();
		if (!params.get(i).equals(parameterType)) 
			return false;
	}
	return true;
}
/**
 * Return true if this MethodElement has the same basic signature as
 * @aMethod, ignoring the return type, thrown exceptions, and declaring class of 
 * this instance or @aMethod.  Return false, if params is null
 */
public boolean nameAndParamsEquals(Method aMethod) {
	if (aMethod != null) {
		if (getName().equals(aMethod.getName())) {
			if (hasMethodParams())
				return matchesParams(aMethod);
			return false;
		}
	}
	return false;
}
protected boolean parmsEqual(MethodElement me) {
	if (me == null) return false;
	List myParms, otherParms;
	myParms = getMethodParams();
	otherParms = me.getMethodParams();
	if (myParms.size() != otherParms.size()) return false;
	for (int i = 0; i < myParms.size(); i++){
		if (!myParms.get(i).equals(otherParms.get(i)))
			return false;
	}
	return true;
}
/**
 * Parse @aSignature setting the name and the params.
 * A signature example:  setTwoParamMethod(java.lang.String, java.lang.String)
 */
protected void parseSignature(String aSignature) {
	int index = aSignature.indexOf(RIGHT_PAREN);

	int endIndex = aSignature.indexOf(LEFT_PAREN);
	if (endIndex < 0)
	{
		endIndex = aSignature.length() - 1;	
	}	

	if (index < 0){
		setName(aSignature);
		setParms(null); //There are no parameters in the sig so set to null
	}
	else {
		String sigName = aSignature.substring(0, index);

		setName(sigName);
		String sigParms = aSignature.substring(index + 1, endIndex);

		if (sigParms.length() > 0) {
			char commaChar = COMMA.charAt(0);
			char[] sigParmsChars = sigParms.toCharArray();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < sigParmsChars.length; i++) {
				if (sigParmsChars[i] != commaChar) {
					buf.append(sigParmsChars[i]);
				} else {
					addMethodParams(buf.toString());
					buf = new StringBuffer();
				}
			}
			addMethodParams(buf.toString());
		} else
			applyZeroParams();
	}
}
public void removeMethodParams(String param) { 
	String myParams = getParms();
	if (myParams == null || myParams.length() == 0) {
		return;
	}
	StringTokenizer tok = new StringTokenizer(myParams);
	StringBuffer newParms = new StringBuffer();
	while (tok.hasMoreElements()) {
		String token = tok.nextToken();
		if (!token.equals(param)) {
			newParms.append(token);
			newParms.append(" "); //$NON-NLS-1$
		}
	}
	setParms(newParms.toString().trim());
}
/**
 * represents method comment.
 */
public boolean represents(Method aMethod) {
	if (aMethod != null) {
		if (isUnspecified() || typeClassImplementsInterface(aMethod.getJavaClass())) {
			if (isDefault())
				return true;
			else 
				if (getName().equals(aMethod.getName())) {
					if (hasMethodParams())
						return matchesParams(aMethod);
					return true;
				}
		}
	}					
	return false;
}
/**
 * Sets the id to be [MethodTransactionID| MethodPermissionID]_EJBNAME_MethodName,
 * or [MethodTransactionID| MethodPermissionID]_EJBNAME_MethodName_Parms, if parms exist
 */
public void setIdToReadableString() { 
	String aParms = getParms() == null ? "" : "_"+getParms().replace(' ', '_'); //$NON-NLS-1$ //$NON-NLS-2$
	String prefix = ""; //$NON-NLS-1$
	switch (MethodElementHelper.getContainedType(this)) {
		case MethodElementHelper.METHOD_PERMISSION :	
			prefix = ((XMIResource)eContainer.eResource()).getID(eContainer);
			break;
		case MethodElementHelper.METHOD_TRANSACTION :
			prefix = ((XMIResource)eContainer.eResource()).getID(eContainer);
			break;
	}
	((XMIResource)eResource()).setID(this,prefix + "_" + getEnterpriseBean().getName() + "_" + getName() + aParms); //$NON-NLS-1$ //$NON-NLS-2$
}
protected boolean typeClassImplementsInterface(JavaClass anInterface) {
	if (getTypeJavaClass() == null || anInterface == null) return false;
	return getTypeJavaClass().implementsInterface(anInterface);
}
/**
 * Return true if this MethodElement uniquely identifies
 * @aMethod.  Return false, even if the MethodElement represents
 * @aMethod (i.e., @aMethod is contained in its list of methods).
 */
public boolean uniquelyIdentifies(Method aMethod) {
	if (aMethod != null) {
		if (getTypeJavaClass() != null && 
	        typeClassImplementsInterface(aMethod.getJavaClass()) && 
	        getName().equals(aMethod.getName())) {
			if (hasMethodParams())
				return matchesParams(aMethod);
			return aMethod.listParametersWithoutReturn().length==0;
		}
	}
	return false;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The method-name element contains a name of an enterprise bean method,
	 * or the asterisk (*) character. The asterisk is used when the element
	 * denotes all the methods of an enterprise bean's remote and home
	 * interfaces.

	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_ELEMENT__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getParms() {
		return parms;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setParms(String newParms) {
		String oldParms = parms;
		parms = newParms;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_ELEMENT__PARMS, oldParms, parms));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public MethodElementKind getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(MethodElementKind newType) {
		MethodElementKind oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_ELEMENT__TYPE, oldType, type, !oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetType() {
		MethodElementKind oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.METHOD_ELEMENT__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description element is used by the ejb-jar file producer to provide text describing the parent element.  The description element should include any information that the ejb-jar file producer wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the ejb-jar file consumer will display the description when processing the parent element.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EnterpriseBean getEnterpriseBean() {
		if (enterpriseBean != null && enterpriseBean.eIsProxy()) {
			EnterpriseBean oldEnterpriseBean = enterpriseBean;
			enterpriseBean = (EnterpriseBean)eResolveProxy((InternalEObject)enterpriseBean);
			if (enterpriseBean != oldEnterpriseBean) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN, oldEnterpriseBean, enterpriseBean));
			}
		}
		return enterpriseBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnterpriseBean basicGetEnterpriseBean() {
		return enterpriseBean;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEnterpriseBean(EnterpriseBean newEnterpriseBean) {
		EnterpriseBean oldEnterpriseBean = enterpriseBean;
		enterpriseBean = newEnterpriseBean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN, oldEnterpriseBean, enterpriseBean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.METHOD_ELEMENT__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_ELEMENT__NAME:
				return getName();
			case EjbPackage.METHOD_ELEMENT__PARMS:
				return getParms();
			case EjbPackage.METHOD_ELEMENT__TYPE:
				return getType();
			case EjbPackage.METHOD_ELEMENT__DESCRIPTION:
				return getDescription();
			case EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN:
				if (resolve) return getEnterpriseBean();
				return basicGetEnterpriseBean();
			case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.METHOD_ELEMENT__PARMS:
				return PARMS_EDEFAULT == null ? parms != null : !PARMS_EDEFAULT.equals(parms);
			case EjbPackage.METHOD_ELEMENT__TYPE:
				return isSetType();
			case EjbPackage.METHOD_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN:
				return enterpriseBean != null;
			case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.METHOD_ELEMENT__PARMS:
				setParms((String)newValue);
				return;
			case EjbPackage.METHOD_ELEMENT__TYPE:
				setType((MethodElementKind)newValue);
				return;
			case EjbPackage.METHOD_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN:
				setEnterpriseBean((EnterpriseBean)newValue);
				return;
			case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.METHOD_ELEMENT__PARMS:
				setParms(PARMS_EDEFAULT);
				return;
			case EjbPackage.METHOD_ELEMENT__TYPE:
				unsetType();
				return;
			case EjbPackage.METHOD_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.METHOD_ELEMENT__ENTERPRISE_BEAN:
				setEnterpriseBean((EnterpriseBean)null);
				return;
			case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", parms: "); //$NON-NLS-1$
		result.append(parms);
		result.append(", type: "); //$NON-NLS-1$
		if (typeESet) result.append(type); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}






