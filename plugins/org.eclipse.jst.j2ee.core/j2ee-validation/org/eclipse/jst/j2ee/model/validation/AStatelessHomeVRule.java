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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public abstract class AStatelessHomeVRule extends ASessionHomeVRule {
	public void validateCreateMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// The create method must be a create() method.
		if((method.listParametersWithoutReturn().length != 0) || (!IMethodAndFieldConstants.METHODNAME_CREATE.equals(method.getName()))) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2476, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}
		else {
			super.validateCreateMethod(vc, bean, clazz, method);
		}
	}
}
