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

import java.util.HashSet;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks entity home classes for errors or potential errors.
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
public abstract class AValidateEntityHome extends AValidateHome {
	private HashSet findByPKMethods = null;

	public AValidateEntityHome() {
		findByPKMethods = new HashSet();
	}

	public void reset() {
		super.reset();
		findByPKMethods.clear();
	}

	protected void incrementFindByPrimaryKeyCount(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		// Need to track number of findByPrimaryKey methods for the
		// validateMethodExists() checks. Do not check only the current
		// class, or a findByPrimaryKey which is inherited from a parent interface
		// will not be detected.
		if (method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
			findByPKMethods.add(method);
		}
	}
	
	/**
	 * This method returns true if the given method returns the remote interface,
	 * and false otherwise.
	 */
	public boolean isSingleObjectFinder(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return false;
		}

		// The findByPrimaryKey method is always a single-object finder.
		JavaClass remoteIntf = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteIntf);

		if (!ValidationRuleUtility.isAssignableFrom(method.getReturnType(), remoteIntf)) {
			return false;
		}

		// The method is a single-object finder.
		return true;
	}
	
	/**
	 * This method checks that the entity home's methods comply with the EJB 1.1 specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *...
	 *   - The methods defined in this interface must follow the rules for RMI-IIOP. 
	 *     This means that their argument and return types must be of valid types for 
	 *     RMI-IIOP, and that their throws clause must include the java.rmi.RemoteException.
	 *...
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
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method hiMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		// super.primValidate(hiMethod);

		vc.terminateIfCancelled();

		String name = hiMethod.getName();

		// Each method defined in the home interface must be one of the following:
		//   - A create method.
		//   - A finder method.
		if (name.equals(IMethodAndFieldConstants.METHODNAME_CREATE))
			validateCreateMethod(vc, bean, clazz, hiMethod);
		else if (name.startsWith(IMethodAndFieldConstants.PREFIX_FIND))
			validateFindMethod(vc, bean, clazz, hiMethod);
		else {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2005, IEJBValidationContext.ERROR, bean, clazz, hiMethod, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		if (IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY.equals(ejbMethod.getName()))
			incrementFindByPrimaryKeyCount(vc, bean, clazz, ejbMethod);
	}
	
	/**
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - The interface must extend the javax.ejb.EJBHome interface.
	 *...
	 *   - The home interface is allowed to have superinterfaces. Use of interface 
	 *     inheritance is subject to the RMI-IIOP rules for the definition of remote interfaces.
	 *...
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		super.validateClass(vc, bean, clazz);
		// The superclass checks that the home extends javax.ejb.EJBHome,
		// and that the interface inheritance complies with RMI-IIOP rules.
	}
	
	/**
	 * Checks that the create method on the entity home follows the EJB 1.1. specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
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
	 */
	public void validateCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		// Each create method must be named "create", and it must match one of the 
		// ejbCreate methods defined in the enterprise Bean class. The matching 
		// ejbCreate method must have the same number and types of its arguments. 
		// (Note that the return type is different.)
		//
		// All the exceptions defined in the throws clause of the matching ejbCreate 
		// and ejbPostCreate methods of the enterprise Bean class must be included in 
		// the throws clause of the matching create method of the home interface 
		// (i.e the set of exceptions defined for the create method must be a superset
		// of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
		validateCreateMethod_beanDep(vc, bean, clazz, method);

		vc.terminateIfCancelled();

		// The return type for a create method must be the entity bean's remote interface type.
		validateCreateMethod_remoteDep(vc, bean, clazz, method);

		// The throws clause of a create method must include the javax.ejb.CreateException.
		if (!ValidationRuleUtility.throwsCreateException(bean, method)) {
			String[] msgParm = { ITypeConstants.CLASSNAME_JAVAX_EJB_CREATEEXCEPTION };
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2414, IEJBValidationContext.ERROR, bean, clazz, method, msgParm, this);
			vc.addMessage(message);
		}

		// The methods defined in this interface must follow the rules for RMI-IIOP. 
		// This means that their argument and return types must be of valid types for 
		// RMI-IIOP, and that their throws clause must include the java.rmi.RemoteException.
		validateLegalRMIMethod(vc, bean, clazz, method);

	}
	
	/**
	 * Checks that the create method on the entity home follows the EJB 1.1. specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
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
	 */
	public void validateCreateMethod_beanDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		// Each create method must be named "create", and it must match one of the 
		// ejbCreate methods defined in the enterprise Bean class. The matching 
		// ejbCreate method must have the same number and types of its arguments. 
		// (Note that the return type is different.)
		super.validateCreateMethod_beanDep(vc, bean, clazz, method);

		//
		// All the exceptions defined in the throws clause of the matching ejbCreate 
		// and ejbPostCreate methods of the enterprise Bean class must be included in 
		// the throws clause of the matching create method of the home interface 
		// (i.e the set of exceptions defined for the create method must be a superset
		// of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
		validateMatchingBeanPostCreateMethod(vc, bean, clazz, method);
	}
	
	/**
	 * In addition to regular find rules, findByPrimaryKey needs to follow some other rules.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *...
	 *   - The home interface must always include the findByPrimaryKey method, which is 
	 *     always a single-object finder. The method must declare the primary key class 
	 *     as the method argument.
	 */
	public void validateFindByPrimaryKeyMethod_keyDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		boolean hasValidFBPKMethod = false;
		Entity entityBean = (Entity) bean; // bean is checked for null in AValidateEJB.validate() method, so don't need to check for it here.
		JavaClass keyClass = entityBean.getPrimaryKey();
		
		ValidationRuleUtility.isValidTypeHierarchy(bean, keyClass);

		vc.terminateIfCancelled();

		// The method must declare the primary key class as the method argument.
		// This check also validates case 9.4.7.3 (CMP unknown primary key class,
		// which must be declared as java.lang.Object.)
		JavaParameter[] parms = method.listParametersWithoutReturn();
		if (parms.length == 1) {
			JavaHelpers type = parms[0].getJavaType();
			if (ValidationRuleUtility.isAssignableFrom(type, keyClass)) {
				// check that it's a single-object finder
				if (isSingleObjectFinder(vc, bean, clazz, method)) {
					hasValidFBPKMethod = true;
				}
			}
			else {
				// check if it's java.lang.Object. If it is, this could be a case of 9.4.7.3.
				if (ValidationRuleUtility.isSameType(type, ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, entityBean))) {
					hasValidFBPKMethod = true;
				}
			}
		}

		if (!hasValidFBPKMethod) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2405, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { keyClass.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * In addition to regular find rules, findByPrimaryKey needs to follow some other rules.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *...
	 *   - The home interface must always include the findByPrimaryKey method, which is 
	 *     always a single-object finder. The method must declare the primary key class 
	 *     as the method argument.
	 */
	public void validateFindByPrimaryKeyMethod_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (!isSingleObjectFinder(vc, bean, clazz, method)) {
			String remoteIntfName = bean.getRemoteInterface().getName(); // Can assume that remote interface isn't null, or isSingleObjectFinder would have thrown an InvalidInputException.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2403, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { remoteIntfName }, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Checks that the finder method on the entity home class follows the EJB 1.1 specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *...
	 *   - Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
	 *     must match one of the ejbFind<METHOD> methods defined in the entity bean class 
	 *     (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
	 *     same number and types of arguments. (Note that the return type may be different.)
	 *   - The return type for a find<METHOD> method must be the entity bean's remote 
	 *     interface type (for a single-object finder), or a collection thereof (for a 
	 *     multi-object finder).
	 *...
	 *   - All the exceptions defined in the throws clause of an ejbFind method of the 
	 *     entity bean class must be included in the throws clause of the matching find 
	 *     method of the home interface.
	 *   - The throws clause of a finder method must include the javax.ejb.FinderException. 
	 */
	public void validateFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		// The return type for a find<METHOD> method must be the entity bean's remote 
		// interface type (for a single-object finder), or a collection thereof (for a 
		// multi-object finder).
		//
		// Whether or not the parameter is a type or an array of types, the following
		// call will return the base type. i.e., if it's an array of "MyClass",
		// the returnTypeName will be set to "MyClass", not an array type.
		validateFindMethod_remoteDep(vc, bean, clazz, method);

		// The throws clause of a finder method must include the javax.ejb.FinderException. 
		if (!ValidationRuleUtility.throwsFinderException(bean, method)) {
			String[] msgParm = { ITypeConstants.CLASSNAME_JAVAX_EJB_FINDEREXCEPTION };
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2414, IEJBValidationContext.ERROR, bean, clazz, method, msgParm, this);
			vc.addMessage(message);
		}

		// The home interface must always include the findByPrimaryKey method, which is 
		// always a single-object finder. The method must declare the primary key class 
		// as the method argument.
		boolean validateRMIParameters = true; // should the parameters be validated to see if they follow RMI-IIOP rules?
		boolean validateRMIRetType = true; // should the return type be validated to see if it follows RMI-IIOP rules?
		if (method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
			// - The home interface must always include the findByPrimaryKey method, which is 
			// always a single-object finder. The method must declare the primary key class 
			// as the method argument.
			//
			// The findByPrimaryKey method is always a single-object finder.
			// The call to validateFindByPrimaryKeyMethod_remoteDep is done in
			// validateFindMethod_remoteDep(method);
			//validateFindByPrimaryKeyMethod_remoteDep(method);

			// The method must declare the primary key class as the method argument.
			validateFindByPrimaryKeyMethod_keyDep(vc, bean, clazz, method);

			if(ValidationRuleUtility.usesUnknownPrimaryKey(bean)) {
				validateRMIParameters = false;
			}
		}
					
		// The methods defined in this interface must follow the rules for RMI-IIOP. 
		// This means that their argument and return types must be of valid types for 
		// RMI-IIOP, and that their throws clause must include the java.rmi.RemoteException.
		// However, the return type of "Enumeration" or "Collection" is exempted from this
		// check, because the spec states, in 9.1.8.2, that Enumeration or Collection must be returned
		// for multi-object finders.
		JavaHelpers retType = method.getReturnType();
		validateRMIRetType = !((ValidationRuleUtility.isAssignableFrom(retType, ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_ENUMERATION, bean))) || 
								(ValidationRuleUtility.isAssignableFrom(retType, ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean))));
		
		if(validateRMIParameters) {
			validateLegalRMIMethodArguments(vc, bean, clazz, method);
		}
		
		if(validateRMIRetType) {
			validateLegalRMIMethodReturnType(vc, bean, clazz, method);
		}
		
		validateLegalRMIMethodExceptions(vc, bean, clazz, method);
	}
	
	/**
	 * Checks that the finder method on the entity home class follows the EJB 1.1 specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *...
	 *   - Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
	 *     must match one of the ejbFind<METHOD> methods defined in the entity bean class 
	 *     (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
	 *     same number and types of arguments. (Note that the return type may be different.)
	 *   - The return type for a find<METHOD> method must be the entity bean's remote 
	 *     interface type (for a single-object finder), or a collection thereof (for a 
	 *     multi-object finder).
	 *...
	 *   - All the exceptions defined in the throws clause of an ejbFind method of the 
	 *     entity bean class must be included in the throws clause of the matching find 
	 *     method of the home interface.
	 *   - The throws clause of a finder method must include the javax.ejb.FinderException. 
	 */
	public void validateFindMethod_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		// The return type for a find<METHOD> method must be the entity bean's remote 
		// interface type (for a single-object finder), or a collection thereof (for a 
		// multi-object finder).
		//
		// Whether or not the parameter is a type or an array of types, the following
		// call will return the base type. i.e., if it's an array of "MyClass",
		// the returnTypeName will be set to "MyClass", not an array type.
		JavaClass remoteIntf = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteIntf);

		// Perform this check for all finders but the findByPrimaryKey method.
		// The findByPrimaryKey method must return the remote interface, because the method is
		// a single-object finder method; but other finders may return a Collection or Enumeration.
		if (!method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
			JavaHelpers returnType = method.getReturnType();
			
			if (! (ValidationRuleUtility.isAssignableFromCollection(returnType, bean) || 
			       ValidationRuleUtility.isAssignableFromEnumeration(returnType, bean) || 
			       ValidationRuleUtility.isAssignableFrom(returnType, remoteIntf))) {
				String[] msgParm = { remoteIntf.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2403, IEJBValidationContext.WARNING, bean, clazz, method, msgParm, this);
				vc.addMessage(message);
			}
		}

		// The home interface must always include the findByPrimaryKey method, which is 
		// always a single-object finder. The method must declare the primary key class 
		// as the method argument.
		if (method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
			validateFindByPrimaryKeyMethod_remoteDep(vc, bean, clazz, method);
		}
	}

	public void validateMatchingBeanCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		super.validateMatchingBeanCreateMethod(vc, bean, clazz, method);
		JavaClass beanClass = bean.getEjbClass();
		Method ejbCreateMethod = ValidationRuleUtility.getMethodExtended(beanClass, method, IMethodAndFieldConstants.METHODNAME_EJBCREATE);
		if (ejbCreateMethod == null) {
			// already reported in super
			return;
		}
	}
	
	/**
	 * Checks that the finder method on the entity home class follows the EJB 1.1 specification.
	 *
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
	 *     must match one of the ejbFind<METHOD> methods defined in the entity bean class 
	 *     (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
	 *     same number and types of arguments. (Note that the return type may be different.)
	 *...
	 *   - All the exceptions defined in the throws clause of an ejbFind method of the 
	 *     entity bean class must be included in the throws clause of the matching find 
	 *     method of the home interface.
	 *...
	 */
	public void validateMatchingBeanFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method homeMethod) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (homeMethod == null) {
			return;
		}

		// Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
		// must match one of the ejbFind<METHOD> methods defined in the entity bean class 
		// (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
		// same number and types of arguments. (Note that the return type may be different.)
		String findMethodName = IMethodAndFieldConstants.PREFIX_EJBF + (homeMethod.getName()).substring(1); // e.g. if the home method is named findX, then the bean method will be named ejbFindX

		JavaClass beanClass = bean.getEjbClass();
		ValidationRuleUtility.isValidTypeHierarchy(bean, beanClass);

		Method beanMethod = ValidationRuleUtility.getMethodExtended(beanClass, homeMethod, findMethodName);
		if (beanMethod == null) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2030, IEJBValidationContext.ERROR, bean, clazz, homeMethod, new String[] { beanClass.getName()}, this);
			vc.addMessage(message);
			return;
		}

		// Check if return types match
		vc.terminateIfCancelled();
		JavaHelpers homeRetType = homeMethod.getReturnType();
		JavaHelpers beanRetType = beanMethod.getReturnType();
		if (!ValidationRuleUtility.isAssignableFrom(homeRetType, beanRetType)) {
			// emit the error only if it's a multi-finder. Single-finders are supposed to have different return types on the home & bean class.
			boolean homeRetTypeIsRemote = ValidationRuleUtility.isAssignableFrom(homeRetType, bean.getRemoteInterface());
			boolean beanRetTypeIsKey = ValidationRuleUtility.isAssignableFrom(beanRetType, ((Entity)bean).getPrimaryKey());
			if(!(homeRetTypeIsRemote && beanRetTypeIsKey)) {
				JavaHelpers retType = (beanRetTypeIsKey) ? bean.getRemoteInterface() : beanRetType; // if the bean class returns a key, the home must return the remote; if the bean class returns a Coll/Enum, the home must return the same
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2415, IEJBValidationContext.ERROR, bean, clazz, homeMethod, new String[] { retType.getJavaName(), beanClass.getName()}, this);
				vc.addMessage(message);
			}
		}

		// All the exceptions defined in the throws clause of an ejbFind method of the 
		// entity bean class must be included in the throws clause of the matching find
		// method of the home interface.
		/*
		// Don't check for the exceptions here - let the bean class do it. When the home
		// changes, a "dependent" validation of the bean's checks should be done automatically.
		// If it is checked in this method as well, there are duplicate messages on the task list.
		HashSet exceptions = getNotSubsetExceptions(homeMethod, beanMethod);
		if(exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while(iterator.hasNext()) {
				JavaClass exc = (JavaClass)iterator.next();
				String[] msgParm = {exc.getQualifiedName()};
				addValidationMessage(IEJBValidationContext.ERROR, IMessagePrefixEjb11Constants.EJB_METHOD_THROW_NOTHI_EXCEP, msgParm, beanMethod, EJB_BEAN_GROUP);
			}
		}	
		*/
	}
	
	/**
	 * Checks that the create method on the entity home has a matching ejbCreate and ejbPostCreate on the bean.
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
	 *...
	 */
	public void validateMatchingBeanPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method homeMethod) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (homeMethod == null) {
			return;
		}

		// Each create method must be named "create", and it must match one of the 
		// ejbCreate methods defined in the enterprise Bean class. The matching 
		// ejbCreate method must have the same number and types of its arguments. 
		// (Note that the return type is different.)
		JavaClass beanClass = bean.getEjbClass();
		ValidationRuleUtility.isValidTypeHierarchy(bean, beanClass);

		Method beanMethod = ValidationRuleUtility.getMethodExtended(beanClass, homeMethod, IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE);
		if (beanMethod == null) {
			// Let the bean class report this message. Otherwise, when the bean class reports it, this message looks like a duplicate.
			/*
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2031, IEJBValidationContext.WARNING, bean, clazz, homeMethod, new String[] { beanClass.getName()}, this);
			vc.addMessage(message);
			*/
			return;
		}

		// All the exceptions defined in the throws clause of the matching ejbCreate 
		// and ejbPostCreate methods of the enterprise Bean class must be included in 
		// the throws clause of the matching create method of the home interface 
		// (i.e the set of exceptions defined for the create method must be a superset
		// of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
		/*
		// Don't check for the exceptions here - let the bean class do it. When the home
		// changes, a "dependent" validation of the bean's checks should be done automatically.
		// If it is checked in this method as well, there are duplicate messages on the task list.
		HashSet exceptions = getNotSubsetExceptions(homeMethod, beanMethod);
		if(exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while(iterator.hasNext()) {
				JavaClass exc = (JavaClass)iterator.next();
				String[] msgParm = {exc.getQualifiedName()};
				addValidationMessage(IEJBValidationContext.ERROR, IMessagePrefixEjb11Constants.EJB_METHOD_THROW_NOTHI_EXCEP, msgParm, beanMethod, EJB_BEAN_GROUP);
			}
		}
		*/
	}
	
	/**
	 * 9.2.8 Entity bean's home interface
	 * The following are the requirements for the entity bean's home interface:
	 *   - The home interface must always include the findByPrimaryKey method, which is 
	 *     always a single-object finder. The method must declare the primary key class 
	 *     as the method argument.
	 */
	protected void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// - The home interface must always include the findByPrimaryKey method, which is 
		// always a single-object finder. The method must declare the primary key class 
		// as the method argument.
		if (findByPKMethods.size() == 0) {
			// must have one findByPrimaryKey method
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2011, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
		/*
		// Not sure if this check is useful or not, because it is legal for a child home to declare a findByPrimaryKey,
		// and also for a parent home to declare a findByPrimaryKey.
		else if ( findByPKMethods.size() > 1 ) {
			// if the home interface has multiple findByPrimaryKey methods (implied by 9.2.8)
			Iterator iterator = findByPKMethods.iterator();
			while(iterator.hasNext()) {
				addValidationMessage(IEJBValidationContext.WARNING, IMessagePrefixEjb11Constants.EJB_HAS_MULTIPLE_PK_METHODS, (Method)iterator.next());
			}
		}
		*/
	}
}
