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
 
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.MessageLimitException;


/**
 * @version 	1.0
 * @author
 */
public interface IEJBInterfaceType extends IEJBType {
	public boolean isEJBInterfaceMethod(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException;
	public JavaHelpers getOverExposedLocalType(EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException, ValidationCancelledException;
	public void validateApplicationExceptionRules(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException, ValidationCancelledException, MessageLimitException;
	public void validateRMI_IIOPTypeRules(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists, boolean checkReturnType) throws InvalidInputException, ValidationCancelledException, MessageLimitException;
	public boolean followRemoteExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException;
	public boolean followRMI_IIOPInheritanceRules(EnterpriseBean bean, JavaClass clazz) throws InvalidInputException, ValidationCancelledException;
}
