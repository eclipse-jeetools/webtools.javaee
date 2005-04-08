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

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.MessageLimitException;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;


/*
 * This class is a hack around the Java lack of support for multiple inheritance.
 * All if statements which object-oriented programming would normally replace
 * are contained in this class.
 */
public abstract class AInterfaceTypeVRule extends ATypeVRule implements IEJBInterfaceType {
	public final static List getBeanClassMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[0];
	}
	
	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// A home or component class needs the following classes' extended methods:
		//    1. bean class
		JavaClass beanClass = bean.getEjbClass();
		if(beanClass == null) {
			throw new InvalidInputException();
		}
		List[] result = new List[1];
		result[0] = beanClass.getMethodsExtended();
		return result;
	}
	
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a home or component's field is defined on another class
		// of the bean.
		return null;
	}
	
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		//Default
	}
	
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		validateApplicationExceptionRules(vc, bean, clazz, method);
	}
	
	public final boolean isEJBInterfaceMethod(EnterpriseBean bean, Method method) throws InvalidInputException {
		long[] superTypes = getSupertypes();
		for(int i=0; i<superTypes.length; i++) {
			if(ClassUtility.getUtility().isClassType(bean, method.getJavaClass(), superTypes[i])) {
				return true;
			}
		}
		return false;
	}
	
	public final JavaHelpers getOverExposedLocalType(EnterpriseBean bean, JavaClass clazz, Method method) {
		if((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			// need to check that the method doesn't expose any of the local types of the bean
			JavaParameter[] parms = method.listParametersWithoutReturn();
			if(parms != null) {
				for(int i=0; i<parms.length; i++) {
					JavaParameter parm = parms[i];
					if(ValidationRuleUtility.isLocalType(bean, parm.getJavaType())) {
						return parm.getJavaType();
					}
				}
			}
			
			// Now check the return type
			JavaHelpers parm = method.getReturnType();
			if(ValidationRuleUtility.isLocalType(bean, parm)) {
				return parm;
			}
			
			return null;
		}
		return null;
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
	
	public void validateApplicationExceptionRules(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, MessageLimitException  {
		List exceptions = method.getJavaExceptions();
		if(exceptions.size() == 0) {
			return;
		}
		
		// IWAD4419 = {0} must be a subclass of java.lang.Exception. Read section 18.1.1, 18.2.1 of the EJB 2.0 specification.
		Iterator iterator = exceptions.iterator();
		try {
			JavaHelpers javaLangException = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_EXCEPTION, bean);
			JavaHelpers javaLangRuntimeException = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_RUNTIMEEXCEPTION, bean);
			
			while(iterator.hasNext()) {
				JavaClass exception = (JavaClass)iterator.next();
				
				if(!ValidationRuleUtility.isApplicationException(exception, bean)) {
					continue;
				}
				
				if(!ValidationRuleUtility.isAssignableFrom(exception, javaLangException)) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2404, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{exception.getName()}, this);
					vc.addMessage(message);
					
					// no point checking the rest
					continue;
				}
				
				// IWAD4420 = {0} must not be a subclass of java.lang.RuntimeException. Read section 18.1.1, 18.2.1 of the EJB 2.0 specification.
				if(ValidationRuleUtility.isAssignableFrom(exception, javaLangRuntimeException)) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2416, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{exception.getName()}, this);
					vc.addMessage(message);
				}
			}
			return;
		}
		catch(InvalidInputException exc) {
			// vc.addMessage("Cannot validate exceptions because the {0} type cannot be reflected. Check the classpath.");
			// Don't add any message other than the "cannot validate" message.
			return;
		}
	}
	
}
