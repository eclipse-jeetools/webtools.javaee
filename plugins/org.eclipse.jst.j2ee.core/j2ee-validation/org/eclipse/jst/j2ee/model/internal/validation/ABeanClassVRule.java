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
package org.eclipse.jst.j2ee.model.internal.validation;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public abstract class ABeanClassVRule extends ATypeVRule implements IEJBType {
	protected final static long[] BASE_TYPES = new long[]{ITypeConstants.JAVA_LANG_OBJECT};
	protected final static int MESSAGE_REMOTE_EXCEPTION_SEVERITY = IEJBValidationContext.WARNING; // Deprecated use of RemoteException.
	
	public long[] getBaseTypes() {
		return BASE_TYPES;
	}
	
	public Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}
		
		return ((EnterpriseBean)parent).getEjbClass();
	}
	
	public long getDefaultMethodType() {
		return HELPER;
	}
	
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!followAbstractRules(clazz)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2014, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}
		
		if(!followPublicRules(clazz)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2022, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if(!followFinalRules(clazz)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2015, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}
		
		validateAppendixB(vc, bean, clazz);
	}
	
	public final static List getRemoteHomeMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[0];
	}
	
	public final static List getLocalHomeMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[1];
	}
	
	public final static List getRemoteComponentMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[2];
	}
	
	public final static List getLocalComponentMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[3];
	}
	
	public final static List getBeanClassMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[4];
	}
	
	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// A bean class needs the following classes' extended methods:
		//    1. remote home
		//    2. local home
		//    3. remote component
		//    4. local component
		//    5. bean class (when searching for a matching ejbPostCreate method).
		// If a particular bean doesn't use a remote view or local view,
		// then the corresponding entry in the array will be null. 
		List[] result = new List[5];
		JavaClass remoteHome = bean.getHomeInterface();
		if(remoteHome != null) {
			result[0] = remoteHome.getMethodsExtended();
		}
		else {
			result[0] = null;
		}
		
		JavaClass localHome = bean.getLocalHomeInterface();
		if(localHome != null) {
			result[1] = localHome.getMethodsExtended();
		}
		else {
			result[1] = null;
		}
		
		JavaClass remoteComponent = bean.getRemoteInterface();
		if(remoteComponent != null) {
			result[2] = remoteComponent.getMethodsExtended();
		}
		else {
			result[2] = null;
		}
		
		JavaClass localComponent = bean.getLocalInterface();
		if(localComponent != null) {
			result[3] = localComponent.getMethodsExtended();
		}
		else {
			result[3] = null;
		}
		
		JavaClass beanClass = bean.getEjbClass();
		if(beanClass != null) {
			result[4] = beanClass.getMethodsExtended();
		}
		else {
			result[4] = null;
		}
		
		return result;
	}
	
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a bean class' field is defined on another class
		// of the bean.
		return null;
	}
	
	protected abstract boolean shouldBeFinal(JavaClass clazz);
	protected abstract boolean shouldBePublic(JavaClass clazz);
	protected abstract boolean shouldBeAbstract(JavaClass clazz);
	protected abstract boolean shouldValidateTransientField();
	protected abstract JavaHelpers getEjbCreateReturnType(EnterpriseBean bean, Method method) throws InvalidInputException;
	protected abstract String getEjbCreateReturnTypeName(EnterpriseBean bean, Method method) throws InvalidInputException;
	public abstract String getMatchingHomeMethodName(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList);
	
	public String getMatchingEjbHomeMethodName(IEJBValidationContext vc, Method method) {
		// Section number stating this name convention?
		String methodName = method.getName();
		// Strip the "ejbHome" off, strip off the next character, and replace that character with a lowercase version of that character
		StringBuffer buffer = new StringBuffer(Character.toLowerCase(methodName.charAt(0)));
		buffer.append(method.getName().substring(8));
		return buffer.toString();
	}
	
	public String getMatchingEjbCreateMethodName(IEJBValidationContext vc, Method method) {
		StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_C);
		// Strip the "ejbC" off of "create" and replace with "c"
		buffer.append(method.getName().substring(4));
		return buffer.toString();
	}

	public String getMatchingEjbPostCreateMethodName(IEJBValidationContext vc, Method method) {
		StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_C);
		// Strip the "ejbPostC" off of "create" and replace with "c"
		buffer.append(method.getName().substring(8));
		return buffer.toString();
	}
	
	
	
	public String getMatchingEjbFindMethodName(IEJBValidationContext vc, Method method) {
		StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_F);
		// Strip the "ejbF" off of "ejbFind" and replace with "f"
		buffer.append(method.getName().substring(4));
		return buffer.toString();
	}
	
	public String getMatchingBusinessMethodName(IEJBValidationContext vc, Method method) {
		return method.getName();
	}
	
	/*
	 * The given method is not supposed to have a matching method.
	 */
	public String getNoMatchingMethodName(IEJBValidationContext vc, Method method) {
		return null; // not supposed to have a matching method
	}

	public final boolean followFinalRules(JavaClass clazz) {
		if(shouldBeFinal(clazz)) {
			return clazz.isFinal();
		}
		return !clazz.isFinal();
	}
	
	public final boolean followPublicRules(JavaClass clazz) {
		if(shouldBePublic(clazz)) {
			return clazz.isPublic();
		}
		return !clazz.isPublic();
	}
	
	public final boolean followAbstractRules(JavaClass clazz) {
		if(shouldBeAbstract(clazz)) {
			return clazz.isAbstract();
		}
		return !clazz.isAbstract();
	}
	
	
	public void validateEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2421, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2422, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2423, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		JavaHelpers retType = getEjbCreateReturnType(bean, method);
		if((retType == null) || (!ValidationRuleUtility.isAssignableFrom(method.getReturnType(), retType))) {
			String retTypeName = (retType == null) ? getEjbCreateReturnTypeName(bean, method) : retType.getJavaName();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2424, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{retTypeName}, this);
			vc.addMessage(message);
		}

		validateRMI_IIOPTypeRules(vc, bean, clazz, method, methodsExtendedLists, true);

		if(validateEjbCreateMethodExceptions()) {
			if(!followRemoteExceptionRules(bean, method)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_ejbCreate, IEJBValidationContext.WARNING, bean, clazz, method, this);
				vc.addMessage(message);
			}
		}
	}
	
	public boolean validateEjbCreateMethodExceptions() {
		return true;
	}
	
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2426, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2427, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2428, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(!method.isVoid()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2505_ejbPostCreate, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(!followRemoteExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_ejbPostCreate, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	public final void validateEjbHomeMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2431, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2439, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		validateRMI_IIOPTypeRules(vc, bean, clazz, method, methodsExtendedLists, true);

		if(!followRemoteExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_ejbHome, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		// "ejbHome" is 7 characters
		// method will follow the convention, "ejbHome" + Char.toUpper(0) + substring(1), so reverse that convention here
		StringBuffer buffer = new StringBuffer();
		buffer.append(Character.toLowerCase(method.getName().charAt(7))); // put the character back into lower-case
		buffer.append(method.getName().substring(8)); // substring starts at the character following "ejbHome" and the first character -- substring is 0 indexed
		String methodToMatchName = buffer.toString();
		
		Method matchingRemoteHomeMethod = ValidationRuleUtility.getMethod(method, methodToMatchName, getRemoteHomeMethodsExtended(methodsExtendedLists));
		if(matchingRemoteHomeMethod != null) {
			return;
		}
		
		Method matchingLocalHomeMethod = ValidationRuleUtility.getMethod(method, methodToMatchName, getLocalHomeMethodsExtended(methodsExtendedLists));
		if(matchingLocalHomeMethod != null) {
			return;
		}
		
		IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2430, IEJBValidationContext.INFO, bean, clazz, method, new String[]{methodToMatchName}, this);
		vc.addMessage(message);
	}
	
	public final void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// IWAD4048 = Business methods must not start with "ejb". Read section 7.10.4 of the EJB 2.0 specification.
		// IWAD4201 = This method name must not start with "ejb". Read section 10.6.8 of the EJB 2.0 specification.
		// IWAD4317 = The method name must not start with "ejb". Read section 12.2.7 of the EJB 2.0 specification.
		if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJB)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2411, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4049 = This method must be public. Read section 7.10.4 of the EJB 2.0 specification.
		// IWAD4202 = This method must be public. Read section 10.6.8 of the EJB 2.0 specification.
		// IWAD4318 = The method must be public. Read section 12.2.7 of the EJB 2.0 specification.
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2441, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4050 = This method must not be final. Read section 7.10.4 of the EJB 2.0 specification.
		// IWAD4203 = This method must not be final. Read section 10.6.8 of the EJB 2.0 specification.
		// IWAD4319 = The method must not be final. Read section 12.2.7 of the EJB 2.0 specification.
		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2442, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4051 = This method must not be static. Read section 7.10.4 of the EJB 2.0 specification.
		// IWAD4204 = This method must not be static. Read section 10.6.8 of the EJB 2.0 specification.
		// IWAD4320 = The method must not be static. Read section 12.2.7 of the EJB 2.0 specification.
		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2443, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4052 = {0} must be a legal RMI-IIOP type. Read section 7.10.4 of the EJB 2.0 specification.
		// IWAD4205 = {0} must be a legal RMI-IIOP type. Read section 10.6.8 of the EJB 2.0 specification.
		// IWAD4321 = {0} must be a legal type for RMI-IIOP. Read section 12.2.7 of the EJB 2.0 specification.
		validateRMI_IIOPTypeRules(vc, bean, clazz, method, methodsExtendedLists, true);
		
		// IWAD4053 = This method cannot throw java.rmi.RemoteException. Read section 7.10.4, 18.3.8, 18.6 of the EJB 2.0 specification.
		// IWAD4206 = This method must not throw java.rmi.RemoteException. Read section 10.6.8, 18.3.8, 18.6 of the EJB 2.0 specification.
		// IWAD4322 = This method must not throw java.rmi.RemoteException. Read section 12.2.7, 18.3.8, 18.6 of the EJB 2.0 specification.
		if(!followRemoteExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_bus, getMessageRemoteExceptionSeverity(), bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		validateMatchingComponentMethod(vc, bean, clazz, method, methodsExtendedLists);
	}

	public void validateEjbRemoveMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// IWAD4402 = This method must be public. Read section 15.7.5 of the EJB 2.0 specification.
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2492, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4403 = This method must not be final. Read section 15.7.5 of the EJB 2.0 specification.
		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2493, IEJBValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4404 = This method must not be static. Read section 15.7.5 of the EJB 2.0 specification.
		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2494, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	
		// IWAD4405 = This method must return void. Read section 15.7.5 of the EJB 2.0 specification.
		if(!method.isVoid()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2505_ejbRemove, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	/*
	 * @see IClassVRule#validate(IEJBValidationContext, JavaClass, Field)
	 */
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// IWAD4024 = A transient field should not be the {0} type. Read section 7.4.1 of the EJB 2.0 specification.
		// IWAD4025 = Transient fields are discouraged. Read section 7.4.1 of the EJB 2.0 specification.
		// IWAD4115 = {0} must not be defined in this class. Read section 10.3.1 of the EJB 2.0 specification.
		// IWAD4259 = <cmp-field> field must be the {0} type. Read section 11.2.1 of the EJB 2.0 specification.
		// IWAD4260 = <cmr-field> field must be the {0} type. Read section 11.2.1 of the EJB 2.0 specification.
		if(shouldValidateTransientField()) {
			validateTransientField(vc, bean, clazz, field);
		}
	}
	
	public void validateTransientField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// By default do nothing
	}
	
	public long getFieldType(EnterpriseBean bean, JavaClass clazz, Field field) {
		if(field == null) {
			return EXCLUDED_FIELD;
		}
		else if(field.getName().equals(IMethodAndFieldConstants.FIELDNAME_SERIALVERSIONUID)) {
			return SERIALVERSIONUID;
		}
		else {
			return OTHER_FIELD;
		}	
	}

	public void validateMatchingMethodExceptions(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, JavaClass matchingClazz, Method matchingMethod) {
		// Every exception thrown on the bean class' method must be thrown on the component/home method
		/* Don't check for this here - let the home & component do it.
		Set exceptions = ValidationRuleUtility.getNotSubsetExceptions(bean, method, matchingMethod);
		Iterator eiterator = exceptions.iterator();
		while(eiterator.hasNext()) {
		}
		*/
		
		// Every exception thrown by the component/home method should be thrown or there may be compile errors
		Set exceptions = ValidationRuleUtility.getNotSubsetSubtypeExceptions(bean, matchingMethod, method);
		Iterator eiterator = exceptions.iterator();
		while(eiterator.hasNext()) {
			JavaClass exception = (JavaClass)eiterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2456, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{exception.getJavaName(), matchingClazz.getJavaName()}, this);
			vc.addMessage(message);
		}
	}
	
	public final void validateMatchingHomeMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		Method remoteMethod = ValidationRuleUtility.getMethod(method, getMatchingHomeMethodName(vc, bean, clazz, method, methodsExtendedLists), getRemoteHomeMethodsExtended(methodsExtendedLists));
		if(remoteMethod != null) {
			validateMatchingMethodExceptions(vc, bean, clazz, method, bean.getHomeInterface(), remoteMethod);
		}
		
		Method localMethod = ValidationRuleUtility.getMethod(method, getMatchingHomeMethodName(vc, bean, clazz, method, methodsExtendedLists), getLocalHomeMethodsExtended(methodsExtendedLists));
		if(localMethod != null) {
			validateMatchingMethodExceptions(vc, bean, clazz, method, bean.getLocalHomeInterface(), localMethod);
		}
	}
	
	public final void validateMatchingComponentMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		Method remoteMethod = ValidationRuleUtility.getMethod(method, method.getName(), getRemoteComponentMethodsExtended(methodsExtendedLists));
		if(remoteMethod != null) {
			validateMatchingMethodExceptions(vc, bean, clazz, method, bean.getRemoteInterface(), remoteMethod);
		}
		
		Method localMethod = ValidationRuleUtility.getMethod(method, method.getName(), getLocalComponentMethodsExtended(methodsExtendedLists));
		if(localMethod != null) {
			validateMatchingMethodExceptions(vc, bean, clazz, method, bean.getLocalInterface(), localMethod);
		}
	}

	protected void validateAppendixB(IEJBValidationContext vc, EnterpriseBean bean, JavaClass thisEjbObject) {
		// The Java inheritance structure must match the EJB inheritance structure.
		// e.g. if EJB B is a child of EJB A, then class B must be a child of class A.
		// B could be a grandchild (or great-grandchild or ...) of A.
		if(bean == null) {
			return;
		}
		EnterpriseBean supertype = getSuperType(bean);
		JavaClass parentEjbObject = null;
		if (supertype != null) {
			parentEjbObject = supertype.getEjbClass();

			// EJBObject a Xchild of parent EJBObject (X = child, grandchild, great-grandchild, etc.)
			try {
				ValidationRuleUtility.isValidType(thisEjbObject);
				ValidationRuleUtility.isValidType(parentEjbObject);
				if (!ValidationRuleUtility.isAssignableFrom(thisEjbObject, parentEjbObject)) {
					String[] msgParm = new String[] { thisEjbObject.getQualifiedName(), parentEjbObject.getQualifiedName()};
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2103, IEJBValidationContext.ERROR, bean, thisEjbObject, msgParm, this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String[] msgParm = { e.getJavaClass().getQualifiedName(), bean.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2849, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}
	}

	public final boolean followRemoteExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException {
		// must not throw RemoteException
		return ValidationRuleUtility.doesNotThrowRemoteException(bean, method);
	}
}
