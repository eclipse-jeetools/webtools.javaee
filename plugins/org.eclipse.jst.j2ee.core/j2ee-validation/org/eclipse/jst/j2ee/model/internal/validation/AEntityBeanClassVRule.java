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

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclispe.wst.validation.internal.core.ValidationException;


/**
 */
public abstract class AEntityBeanClassVRule extends ABeanClassVRule {
	private static final String missingMethodTemplate = "{0}({1})"; //$NON-NLS-1$
	
	public void validateEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws ValidationCancelledException, ValidationCancelledException, InvalidInputException, ValidationException {
		super.validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedList);
		
		validateMatchingEjbPostCreateMethod(vc, bean, clazz, method, methodsExtendedList);
	}
	
	public final void validateMatchingEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		String matchingMethodName = IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE + method.getName().substring(9); // "ejbCreate" is 9 characters long, so strip off characters 0..8 inclusive
		Method ejbPostCreateMethod = ValidationRuleUtility.getMethod(method, matchingMethodName, getBeanClassMethodsExtended(methodsExtendedLists));
		if(ejbPostCreateMethod == null) {
			String missingMethod = MessageFormat.format(missingMethodTemplate, new String[]{matchingMethodName, ValidationRuleUtility.getParmsAsString(method)});
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2050_ejbPostCreate, IEJBValidationContext.ERROR, bean, clazz, method, new String[]{missingMethod}, this);
			vc.addMessage(message);
		}
	}
	
}
