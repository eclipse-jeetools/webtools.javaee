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
package org.eclipse.jst.j2ee.model.validation;
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 */
public abstract class ACMPHomeVRule extends AEntityHomeVRule {

	public final void validateFindMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validateFindMethod(vc, bean, clazz, method);
		
		// Check that this method is associated with a query element in ejb-jar.xml
		// findByPrimaryKey does not need a query element (10.5.6)
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedList, this);
		if((methodType & IMethodAndFieldConstants.FINDBYPRIMARYKEY) == IMethodAndFieldConstants.FINDBYPRIMARYKEY) {
			return;
		}
		
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		if(!ValidationRuleUtility.isAssociatedWithQuery(cmp, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2495, IValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}

	public boolean findMatchingMethod(long methodType) {
		if((methodType & FIND) == IMethodAndFieldConstants.FIND) {
			return false;
		}
		return super.findMatchingMethod(methodType);
	}
	

	/**
	 * @see org.eclipse.wst.validation.core.core.ejb.ejb20.rules.impl.AHomeVRule#returnsComponentInterfaceOrCollection(IValidationContext, EnterpriseBean, JavaClass, Method)
	 */
	protected boolean returnsComponentInterfaceOrCollection(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException {
		if(returnsComponentInterface(vc, bean, clazz, method)) {
			return true;
		}
		
		JavaHelpers returnParm = method.getReturnType();
		JavaHelpers javaUtilCollection = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean);
		if(ValidationRuleUtility.isAssignableFrom(returnParm, javaUtilCollection)) {
			return true;
		}
		
		return false;
	}

	public final void validateMatchingReturnTypeMatches(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method homeMethod, Method beanMethod, List[] methodsExtendedList) {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, homeMethod, methodsExtendedList, this);
		if((methodType & FIND) != FIND) {
			super.validateMatchingReturnTypeMatches(vc, bean, clazz, homeMethod, beanMethod, methodsExtendedList);
		}
		// do not validate the return type of find methods because there should not be an implementation on the bean class
	}
}
