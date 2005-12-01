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
package org.eclipse.jst.j2ee.model.internal.validation;

import java.util.List;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public interface IClassVRule extends IValidationRule {
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException;

	// Because calling JavaClass::getMethodsExtended() is an expensive call, need to call it once
	// per class instead of multiple times per class. However, each class in the bean needs different
	// lists:
	//   1. Key class needs no lists.
	//   2. Home interface (remote & local) needs the bean class' getMethodsExtended list.
	//   3. Component interface (remote & local) needs the bean class' getMethodsExtended list.
	//   4. Bean class needs the remote home, local home, remote component, and local component's getMethodsExtended list.
	// Given that the lists are different depending on the class implementation, add a List[] as a final parameter, and each
	// implementation can figure out what the List[] needs to be populated with, if anything. (i.e., the
	// key class will probably pass in a null instead of an empty List.)
	// 
	// Similar reasoning for the List[] of getFieldsExtended().
	public List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException;
	public List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException;
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtended) throws ValidationCancelledException, InvalidInputException, ValidationException;
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtended) throws ValidationCancelledException, InvalidInputException, ValidationException;
	
	public void register(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtended) throws ValidationCancelledException, InvalidInputException, ValidationException;
	public void register(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtended) throws ValidationCancelledException, InvalidInputException, ValidationException;
}
