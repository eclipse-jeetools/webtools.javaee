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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks home classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * The following paragraph is taken from
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * Copyright 1999 Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, U.S.A.
 * All rights reserved.
 *
 * 6.8 Stateless session beans
 *...
 * The home interface of a stateless session bean must have one create 
 * method that takes no arguments and returns the session bean's remote 
 * interface. There can be no other create methods in the home interface. 
 * The session bean class must define a single ejbCreate method that takes 
 * no arguments.
 *...
 *
 * 6.10.6 Session bean's home interface
 * The following are the requirements for the session bean's home interface:
 *   - The interface must extend the javax.ejb.EJBHome interface.
 *   - The methods defined in this interface must follow the rules for RMI/IIOP. 
 *     This means that their argument and return values must be of valid types 
 *     for RMI/IIOP, and that their throws clause must include the java.rmi.RemoteException.
 *   - The home interface is allowed to have superinterfaces. Use of interface 
 *     inheritance is subject to the RMI/IIOP rules for the definition of 
 *     remote interfaces.
 *   - A session bean's home interface must define one or more create(...) methods.
 *   - Each create method must be named "create", and it must match one of 
 *     the ejbCreate methods defined in the session bean class. The matching 
 *     ejbCreate method must have the same number and types of arguments. 
 *     (Note that the return type is different.)
 *   - The return type for a create method must be the session bean's remote 
 *     interface type.
 *   - All the exceptions defined in the throws clause of an ejbCreate method 
 *     of the session bean class must be defined in the throws clause of the 
 *     matching create method of the home interface.
 *   - The throws clause must include javax.ejb.CreateException.
 *...
 *
 * 9.2.8 Entity bean's home interface
 * The following are the requirements for the entity bean's home interface:
 *   - The interface must extend the javax.ejb.EJBHome interface.
 *   - The methods defined in this interface must follow the rules for RMI-IIOP. 
 *     This means that their argument and return types must be of valid types for 
 *     RMI-IIOP, and that their throws clause must include the java.rmi.RemoteException.
 *   - The home interface is allowed to have superinterfaces. Use of interface 
 *     inheritance is subject to the RMI-IIOP rules for the definition of remote interfaces.
 *   - Each method defined in the home interface must be one of the following:
 *      - A create method.
 *      - A finder method.
 *   - Each create method must be named "create", and it must match one of the 
 *     ejbCreate methods defined in the enterprise Bean class. The matching 
 *     ejbCreate method must have the same number and types of its arguments. 
 *     (Note that the return type is different.)
 *   - The return type for a create method must be the entity bean's remote interface type.
 *   - All the exceptions defined in the throws clause of the matching ejbCreate 
 *     and ejbPostCreate methods of the enterprise Bean class must be included in 
 *     the throws clause of the matching create method of the home interface 
 *     (i.e the set of exceptions defined for the create method must be a superset
 *     of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
 *   - The throws clause of a create method must include the javax.ejb.CreateException.
 *   - Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
 *     must match one of the ejbFind<METHOD> methods defined in the entity bean class 
 *     (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
 *     same number and types of arguments. (Note that the return type may be different.)
 *   - The return type for a find<METHOD> method must be the entity bean's remote 
 *     interface type (for a single-object finder), or a collection thereof (for a 
 *     multi-object finder).
 *   - The home interface must always include the findByPrimaryKey method, which is 
 *     always a single-object finder. The method must declare the primary key class 
 *     as the method argument.
 *   - All the exceptions defined in the throws clause of an ejbFind method of the 
 *     entity bean class must be included in the throws clause of the matching find 
 *     method of the home interface.
 *   - The throws clause of a finder method must include the javax.ejb.FinderException. 
 */
public abstract class AValidateHome extends AValidateEJB {
	public final Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}

		return ((EnterpriseBean)parent).getHomeInterface();
	}

	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
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
	
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a home or component's field is defined on another class
		// of the bean.
		return null;
	}
	
	/**
	 * For the check that the ejbCreate method has a matching create method on the home,
	 * this method, given the bean method, returns the corresponding home method.
	 *
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - Each create method must be named "create", and it must match one of 
	 *     the ejbCreate methods defined in the session bean class. The matching 
	 *     ejbCreate method must have the same number and types of arguments. 
	 *     (Note that the return type is different.)
	 *   - All the exceptions defined in the throws clause of an ejbCreate method 
	 *     of the session bean class must be defined in the throws clause of the 
	 *     matching create method of the home interface.
	 *...
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - Each create method must be named "create", and it must match one of the 
	 *     ejbCreate methods defined in the enterprise Bean class. The matching 
	 *     ejbCreate method must have the same number and types of its arguments. 
	 *     (Note that the return type is different.)
	 *   - All the exceptions defined in the throws clause of the matching ejbCreate 
	 *     and ejbPostCreate methods of the enterprise Bean class must be included in 
	 *     the throws clause of the matching create method of the home interface 
	 *     (i.e the set of exceptions defined for the create method must be a superset
	 *     of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
	 */
	public Method getMatchingBeanEjbCreateMethodExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return null;
		}

		if (bean == null) {
			return null;
		}

		return ValidationRuleUtility.getMethodExtended(bean.getEjbClass(), method, IMethodAndFieldConstants.METHODNAME_EJBCREATE);
	}
	
	/**
	 * Return true if the method can, and should, be validated.
	 * Filter out faulty methods (i.e., null), and methods which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	protected boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws InvalidInputException {
		if (super.isValid(vc, bean, clazz, method, methodsExtendedList)) {
			// Exclude root remote interface methods
			if (ValidationRuleUtility.isEJBHomeMethod(bean, method)) {
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
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - The interface must extend the javax.ejb.EJBHome interface.
	 *...
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - The interface must extend the javax.ejb.EJBHome interface.
	 *...
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();

		// home interface must be an interface
		if (!clazz.isInterface()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2012, IEJBValidationContext.ERROR, bean, clazz, this);
			vc.addMessage(message);
		}

		// home interface must inherit javax.ejb.EJBHome.class
		if (!ValidationRuleUtility.isAssignableFrom(clazz, ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBHOME, bean))) {
			String[] msgParm = { ITypeConstants.CLASSNAME_JAVAX_EJB_EJBHOME };
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
	 * 6.8 Stateless session beans
	 *...
	 * The home interface of a stateless session bean must have one create 
	 * method that takes no arguments and returns the session bean's remote 
	 * interface. There can be no other create methods in the home interface. 
	 * The session bean class must define a single ejbCreate method that takes 
	 * no arguments.
	 *...
	 *
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - The methods defined in this interface must follow the rules for RMI/IIOP. 
	 *     This means that their argument and return values must be of valid types 
	 *     for RMI/IIOP, and that their throws clause must include the java.rmi.RemoteException.
	 *   - A session bean's home interface must define one or more create(...) methods.
	 *   - Each create method must be named "create", and it must match one of 
	 *     the ejbCreate methods defined in the session bean class. The matching 
	 *     ejbCreate method must have the same number and types of arguments. 
	 *     (Note that the return type is different.)
	 *   - The return type for a create method must be the session bean's remote 
	 *     interface type.
	 *   - All the exceptions defined in the throws clause of an ejbCreate method 
	 *     of the session bean class must be defined in the throws clause of the 
	 *     matching create method of the home interface.
	 *   - The throws clause must include javax.ejb.CreateException. 
	 */
	protected void validateCreateMethod_beanDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}

		// Each create method must be named "create", and it must match one of 
		// the ejbCreate methods defined in the session bean class. The matching 
		// ejbCreate method must have the same number and types of arguments. 
		// (Note that the return type is different.)
		// All the exceptions defined in the throws clause of an ejbCreate method 
		// of the session bean class must be defined in the throws clause of the 
		// matching create method of the home interface.
		// The throws clause must include javax.ejb.CreateException.
		validateMatchingBeanCreateMethod(vc, bean, clazz, method);
	}
	
	/**
	 * 6.8 Stateless session beans
	 *...
	 * The home interface of a stateless session bean must have one create 
	 * method that takes no arguments and returns the session bean's remote 
	 * interface. There can be no other create methods in the home interface. 
	 * The session bean class must define a single ejbCreate method that takes 
	 * no arguments.
	 *...
	 *
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - The methods defined in this interface must follow the rules for RMI/IIOP. 
	 *     This means that their argument and return values must be of valid types 
	 *     for RMI/IIOP, and that their throws clause must include the java.rmi.RemoteException.
	 *   - A session bean's home interface must define one or more create(...) methods.
	 *   - Each create method must be named "create", and it must match one of 
	 *     the ejbCreate methods defined in the session bean class. The matching 
	 *     ejbCreate method must have the same number and types of arguments. 
	 *     (Note that the return type is different.)
	 *   - The return type for a create method must be the session bean's remote 
	 *     interface type.
	 *   - All the exceptions defined in the throws clause of an ejbCreate method 
	 *     of the session bean class must be defined in the throws clause of the 
	 *     matching create method of the home interface.
	 *   - The throws clause must include javax.ejb.CreateException.
	 *
	 *
	 * 9.2.8 Entity bean's home interface
	 *    The following are the requirements for the entity bean's home interface:
	 * ...
	 *    The return type for a create method must be the entity bean's remote interface type.
	 * ...
	 * The return type for a find<METHOD> method must be the entity bean's remote interface type (for a
	 * single-object finder), or a collection thereof (for a multi-object finder).
	 *...
	 */
	protected void validateCreateMethod_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}

		JavaClass remoteIntf = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteIntf);

		// The return type for a create method must be the remote interface type.
		if (!ValidationRuleUtility.isAssignableFrom(method.getReturnType(), remoteIntf)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2402, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { remoteIntf.getName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - Each create method must be named "create", and it must match one of 
	 *     the ejbCreate methods defined in the session bean class. The matching 
	 *     ejbCreate method must have the same number and types of arguments. 
	 *     (Note that the return type is different.)
	 *...
	 *   - All the exceptions defined in the throws clause of an ejbCreate method 
	 *     of the session bean class must be defined in the throws clause of the 
	 *     matching create method of the home interface.
	 *...
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - Each create method must be named "create", and it must match one of the 
	 *     ejbCreate methods defined in the enterprise Bean class. The matching 
	 *     ejbCreate method must have the same number and types of its arguments. 
	 *     (Note that the return type is different.)
	 *...
	 *   - All the exceptions defined in the throws clause of the matching ejbCreate 
	 *     and ejbPostCreate methods of the enterprise Bean class must be included in 
	 *     the throws clause of the matching create method of the home interface 
	 *     (i.e the set of exceptions defined for the create method must be a superset
	 *     of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
	 */
	public void validateMatchingBeanCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (method == null) {
			return;
		}

		// Each create method must be named "create", and it must match one of the 
		// ejbCreate methods defined in the enterprise Bean class. The matching 
		// ejbCreate method must have the same number and types of its arguments. 
		// (Note that the return type is different.)
		JavaClass beanClass = bean.getEjbClass();
		ValidationRuleUtility.isValidTypeHierarchy(bean, beanClass);

		Method ejbCreateMethod = ValidationRuleUtility.getMethodExtended(beanClass, method, IMethodAndFieldConstants.METHODNAME_EJBCREATE);
		if (ejbCreateMethod == null) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2026, IEJBValidationContext.ERROR, bean, clazz, method, new String[] { beanClass.getName()}, this);
			vc.addMessage(message);
			return;
		}

		// Section 6.10.6 (session), 9.2.8 (entity), declare that all exceptions declared
		// on the ejbCreate, ejbPostCreate methods must be defined in the throws clause of
		// the matching create of the home interface.
		/*
		// Don't check for the exceptions here - let the bean class do it. When the home
		// changes, a "dependent" validation of the bean's checks should be done automatically.
		// If it is checked in this method as well, there are duplicate messages on the task list.
		HashSet exceptions = getNotSubsetExceptions(method, ejbCreateMethod);
		if(exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while(iterator.hasNext()) {
				JavaClass exc = (JavaClass)iterator.next();
				String[] msgParm = {exc.getQualifiedName()};
				addValidationMessage(IEJBValidationContext.ERROR, IMessagePrefixEjb11Constants.EJB_METHOD_THROW_NOTHI_EXCEP, msgParm, ejbCreateMethod, EJB_HOME_GROUP); // since we're adding the message to the bean class, we need to mark it like the bean class would; i.e., a home-dependent message
			}
		}
		*/
	}
	
	protected void validateAppendixB(IEJBValidationContext vc, EnterpriseBean bean, JavaClass thisHome) {
		// The Java inheritance structure must match the EJB inheritance structure.
		// e.g. if EJB B is a child of EJB A, then class B must be a child of class A.
		// B could be a grandchild (or great-grandchild or ...) of A.
		
		if(bean == null) {
			// bean has no supertype
			return;
		}
		EnterpriseBean	supertype = getSuperType(bean);
		JavaClass parentHome = null;
		if (supertype != null) {
			// check this CMP's supertype
			parentHome = supertype.getHomeInterface();

			// Home a Xchild of parent Home
			// In our EJB component inheritance structure, while it is legal for
			// a home to inherit from another home, (section B.2), it is not legal
			// for WSA component inheritance structure. 
			try {
				ValidationRuleUtility.isValidType(thisHome);
				ValidationRuleUtility.isValidType(parentHome);
				if (ValidationRuleUtility.isAssignableFrom(thisHome, parentHome)) {
					String[] msgParm = new String[] { thisHome.getQualifiedName(), parentHome.getQualifiedName()};
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2104, IEJBValidationContext.ERROR, bean, thisHome, msgParm, this);
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
