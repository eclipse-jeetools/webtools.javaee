/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks remote interface classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 * The checks for a session remote interface and an entity remote interface are the same
 * in EJB 1.1.
 *
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * URL: http://java.sun.com/products/ejb/docs.html
 * Section: 6.10.5 and 9.2.7
 */
public abstract class AValidateRemote extends AValidateEJB {
	@Override
	public final Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}

		return ((EnterpriseBean)parent).getRemoteInterface();
	}

	@Override
	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz)  {
		// A home or component class needs the following classes' extended methods:
		//    1. bean class
		List[] result = new List[1];
		JavaClass beanClass = bean.getEjbClass();
		if(beanClass == null) {
			result[0] = null;
		}
		else {
			result[0] = beanClass.getMethodsExtended();
		}
		return result;
	}
	
	@Override
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a home or component's field is defined on another class
		// of the bean.
		return null;
	}
	
	/**
	 * Return true if the method can, and should, be validated.
	 * Filter out faulty methods (i.e., null), and methods which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	@Override
	protected boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws InvalidInputException {
		if (super.isValid(vc, bean, clazz, method, methodsExtendedList)) {
			// Exclude root remote interface methods 
			if (ValidationRuleUtility.isEJBObjectMethod(bean, method)) {
				return false;
			}
			else if (isEjbRelationshipRoleMethod(vc, bean, clazz, method)) {
				return false;
			}
			else if (ValidationRuleUtility.isClinitMethod(bean, method)) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check the remote interface's methods against the EJB 1.1 spec.
	 * Section: 6.10.5 and 9.2.7
	 */
	@Override
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method riMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		// super.validateExistence(riMethod);

		vc.terminateIfCancelled();

		// The methods defined in this interface must follow the rules for RMI/IIOP.
		validateLegalRMIMethod(vc, bean, clazz, riMethod);

		// For each method defined in the remote interface, there must be a matching
		// method in the session bean's class. 
		validateMethod_beanDep(vc, bean, clazz, riMethod);

	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	@Override
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		//There are no required methods.
	}
	
	/**
	 * Check that the remote interface class definition follows the EJB 1.1 spec.
	 *
	 * Enterprise JavaBeans Specification ("Specification")
	 * Version: 1.1
	 * Status: Final Release
	 * Release: 12/17/99
	 * URL: http://java.sun.com/products/ejb/docs.html
	 * Section: 6.10.5 and 9.2.7
	 */
	@Override
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();

		// It must be an interface, not a class.
		if (!clazz.isInterface()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2012, IEJBValidationContext.ERROR, bean, clazz, this);
			vc.addMessage(message);
		}

		// The interface must extend the javax.ejb.EJBObject interface.
		JavaHelpers javaxEjbObject = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBOBJECT, bean);
		if (!ValidationRuleUtility.isAssignableFrom(clazz, javaxEjbObject)) {
			String[] msgParm = { ITypeConstants.CLASSNAME_JAVAX_EJB_EJBOBJECT };
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2017, IEJBValidationContext.ERROR, bean, clazz, msgParm, this);
			vc.addMessage(message);
		}

		if (ValidationRuleUtility.isUnnamedPackage(clazz.getJavaPackage())) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2041, IEJBValidationContext.INFO, bean, clazz, this);
			vc.addMessage(message);
		}

		validateAppendixB(vc, bean, clazz);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 6.10.5 and 9.2.7
	 */
	public void validateMatchingBeanMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method remoteMethod) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (remoteMethod == null) {
			return;
		}

		JavaClass beanClass = bean.getEjbClass();
		ValidationRuleUtility.isValidTypeHierarchy(bean, beanClass);

		Method beanMethod = ValidationRuleUtility.getMethodExtended(beanClass, remoteMethod, remoteMethod.getName());
		if (beanMethod == null) {
			String[] msgParm = { beanClass.getName()};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2023, IEJBValidationContext.ERROR, bean, clazz, remoteMethod, msgParm, this);
			vc.addMessage(message);
			return;
		}

		ValidationRuleUtility.isValidType(ValidationRuleUtility.getType(remoteMethod.getReturnType()));

		try {
			ValidationRuleUtility.isValidType(ValidationRuleUtility.getType(beanMethod.getReturnType()));
		}
		catch (InvalidInputException e) {
			// Let the validation of the bean class report the reflection warning.
			// Don't do any validation based on that method.
			return;
		}

		// Check if return types match
		vc.terminateIfCancelled();
		if (!ValidationRuleUtility.isAssignableFrom(remoteMethod.getReturnType(), beanMethod.getReturnType())) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2415, IEJBValidationContext.ERROR, bean, clazz, remoteMethod, new String[] { beanMethod.getReturnType().getJavaName(), beanClass.getName()}, this);
			vc.addMessage(message);
		}


		// Section 6.10.5 (session), 9.2.7 (entity), declare that all exceptions declared
		// on the ejbCreate, ejbPostCreate methods must be defined in the throws clause of
		// the matching create of the remote interface.
		/*
		// Don't check for the exceptions here - let the bean class do it. When the remote
		// changes, a "dependent" validation of the bean's checks should be done automatically.
		// If it is checked in this method as well, there are duplicate messages on the task list.
		HashSet exceptions = getNotSubsetExceptions(remoteMethod, beanMethod);
		if(exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while(iterator.hasNext()) {
				JavaClass exc = (JavaClass)iterator.next();
				String[] msgParm = {exc.getQualifiedName()};
				addValidationMessage(IEJBValidationContext.ERROR, IMessagePrefixEjb11Constants.EJB_METHOD_THROW_NOTRI_EXCEP, msgParm, beanMethod, EJB_BEAN_GROUP);
			}
		}	
		*/
	}
	
	/**
	 * Check the remote interface's methods against the EJB 1.1 spec.
	 * Section: 6.10.5 and 9.2.7
	 */
	public void validateMethod_beanDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method riMethod) throws InvalidInputException {
		vc.terminateIfCancelled();
		// For each method defined in the remote interface, there must be a matching
		// method in the session bean's class. 
		validateMatchingBeanMethod(vc, bean, clazz, riMethod);
	}
	
	/**
	 * Final check to see if required methods were detected.
	 */
	@Override
	protected void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		//There are no required methods.
	}

	protected void validateAppendixB(IEJBValidationContext vc, EnterpriseBean bean, JavaClass thisRemote) {
		// The Java inheritance structure must match the EJB inheritance structure.
		// e.g. if EJB B is a child of EJB A, then class B must be a child of class A.
		// B could be a grandchild (or great-grandchild or ...) of A.
		if(bean == null) {
			return;
		}
		EnterpriseBean supertype = getSuperType(bean);
		JavaClass parentRemote = null;
		if (supertype != null) {
			parentRemote = supertype.getRemoteInterface();

			// Remote a Xchild of parent Remote
			try {
				ValidationRuleUtility.isValidType(thisRemote);
				ValidationRuleUtility.isValidType(parentRemote);
				if (!ValidationRuleUtility.isAssignableFrom(thisRemote, parentRemote)) {
					String[] msgParm = new String[] { thisRemote.getQualifiedName(), parentRemote.getQualifiedName()};
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2105, IEJBValidationContext.ERROR, bean, thisRemote, msgParm, this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String[] msgParm = { e.getJavaClass().getQualifiedName(), bean.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2849, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
}
