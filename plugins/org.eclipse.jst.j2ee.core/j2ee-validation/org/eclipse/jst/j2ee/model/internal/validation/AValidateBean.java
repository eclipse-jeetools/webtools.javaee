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


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.IMessage;


/**
 * Performs validation common to session and entity beans.
 */
public abstract class AValidateBean extends AValidateEJB {
	protected HashSet componentParents = null; // The JavaClass instances which are parents of this JavaClass instance, if this JavaClass instance is part of a component inheritance hierarchy.
	
	public AValidateBean() {
		componentParents = new HashSet();
	}
	
	public void reset() {
		super.reset();
		componentParents.clear();
	}
	
	protected void buildComponentParents(EnterpriseBean bean) {
		if (bean == null) {
			return;
		}
		EnterpriseBean supertype = getSuperType(bean);
		if (supertype == null) {
			return;
		}

		// check this CMP's supertype
		JavaClass thisEjbObject = bean.getEjbClass();
		JavaClass parentEjbObject = supertype.getEjbClass();

		// EJBObject a Xchild of parent EJBObject (X = child, grandchild, great-grandchild, etc.)
		if (ValidationRuleUtility.isAssignableFrom(thisEjbObject, parentEjbObject)) {
			componentParents.add(parentEjbObject);
		}
		buildComponentParents(supertype);
	}

	public final Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}

		return ((EnterpriseBean)parent).getEjbClass();
	}
	
	public final static List getRemoteHomeMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[0];
	}
	
	public final static List getRemoteComponentMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[1];
	}
	
	public final static List getBeanClassMethodsExtended(List[] methodsExtendedList) {
		return methodsExtendedList[2];
	}
	
	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// A bean class needs the following classes' extended methods:
		//    1. home
		//    2. remote
		//    3. bean class (when searching for a matching ejbPostCreate method).
		// If a particular bean doesn't use a remote view or local view,
		// then the corresponding entry in the array will be null. 
		List[] result = new List[3];
		JavaClass remoteHome = bean.getHomeInterface();
		if(remoteHome != null) {
			result[0] = remoteHome.getMethodsExtended();
		}
		else {
			result[0] = null;
		}
		
		JavaClass remoteComponent = bean.getRemoteInterface();
		if(remoteComponent != null) {
			result[1] = remoteComponent.getMethodsExtended();
		}
		else {
			result[1] = null;
		}
		
		JavaClass beanClass = bean.getEjbClass();
		if(beanClass != null) {
			result[2] = beanClass.getMethodsExtended();
		}
		else {
			result[2] = null;
		}
		
		return result;
	}
	
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a bean class' field is defined on another class
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
	public final Method getMatchingHomeCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws InvalidInputException {
		vc.terminateIfCancelled();
		
		if (method == null) {
			return null;
		}

		if (bean == null) {
			return null;
		}

		JavaClass homeIntf = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, homeIntf);

		// check only the current class, not the parents in this case.
		// This method is used in the component inheritance hierarchy tests.
		return ValidationRuleUtility.getMethod(method, IMethodAndFieldConstants.METHODNAME_CREATE, homeIntf.getMethods());
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
	public Method getMatchingHomeCreateMethodExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc. terminateIfCancelled();
		if (method == null) {
			return null;
		}

		if (bean == null) {
			return null;
		}

		JavaClass homeIntf = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, homeIntf);

		return ValidationRuleUtility.getMethodExtended(homeIntf, method, IMethodAndFieldConstants.METHODNAME_CREATE);
	}
	
	/**
	 * Return the name of the parent which the bean must implement.
	 * For entity beans, it's javax.ejb.EntityBean.
	 * For session beans, it's javax.ejb.SessionBean.
	 */
	protected abstract String getParentName();
	
	/**
	 * Returns true if the method passed in is a business method.
	 *
	 * Although the EJB 1.1 spec implies that all non-framework methods are business methods,
	 * developers may create methods on the bean which are meant for internal use only. This
	 * method (isBusinessMethod) separates the "internal helper" methods from the business
	 * methods by checking if a corresponding method exists on the remote interface. If it
	 * does, consider this a business method. 
	 */
	public boolean isBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (method == null) {
			return false;
		}
		String name = method.getName();

		if (isFrameworkMethod(name)) {
			return false;
		}

		if (method.isConstructor()) {
			return false;
		}

		// Check if there's a matching method on the remote interface.
		if (bean == null) {
			return false;
		}

		JavaClass remoteInterface = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteInterface);
		Method remoteInterfaceMethod = ValidationRuleUtility.getMethodExtended(remoteInterface, name, method.listParametersWithoutReturn());
		if ((remoteInterfaceMethod == null) || (ValidationRuleUtility.isEJBObjectMethod(bean, remoteInterfaceMethod))) {
			// no matching method
			// filter out methods from javax.ejb.EJBObject
			return false;
		}

		return true;
	}
	
	/**
	 * Return true if the method passed in is inherited from a parent in a
	 * component inheritance hierarchy.
	 */
	protected boolean isComponentInherited(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method meth) throws InvalidInputException {
		if (componentParents == null) {
			// Not part of a component inheritance hierarchy
			return false;
		}

		return (componentParents.contains(meth.getContainingJavaClass()));
	}
	
	public boolean isFrameworkMethod(String name) {
		if (name == null) {
			return false;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBACTIVATE)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBPASSIVATE)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBREMOVE)) {
			return true;
		}
		else if (name.startsWith(IMethodAndFieldConstants.PREFIX_EJBFIND)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBCREATE)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_FINALIZE)) {
			return true;
		}
		return false;

	}
	
	/**
	 * Return true if the field can, and should, be validated.
	 * Filter out faulty fields (i.e., null), and fields which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	protected boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtendedList) throws InvalidInputException {
		if (super.isValid(vc, bean, clazz, field, fieldsExtendedList)) {
			// exclude root object methods
			if (ValidationRuleUtility.isJavaLangObjectField(bean, field)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Return true if the method can, and should, be validated.
	 * Filter out faulty methods (i.e., null), and methods which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	protected final boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws InvalidInputException {
		if (super.isValid(vc, bean, clazz, method, methodsExtendedList)) {
			// exclude root object methods
			if (ValidationRuleUtility.isJavaLangObjectMethod(bean, method)) {
				return false;
			}
			else if (method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBCREATE)) {
				return isValidEjbCreateMethod(vc, bean, clazz, method, methodsExtendedList);
			}
			else if (method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE)) {
				return isValidEjbCreateMethod(vc, bean, clazz, method, methodsExtendedList);
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return true if the ejbCreate method passed in is not inherited 
	 * from a parent in a component inheritance hierarchy, or is inherited,
	 * but is also defined on this bean's home interface.
	 *
	 * This method is used to exclude ejbCreate methods which are inherited
	 * from a parent in a component inheritance hierarchy, and are not
	 * exposed anywhere on this bean class instance itself. (i.e., inherited,
	 * but never used.) Since we generate the code for these methods, we
	 * should not flag them as warnings.
	 */
	protected final boolean isValidEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method meth, List[] methodsExtendedLists) throws InvalidInputException {
		boolean isValid = true;
		boolean isComp = isComponentInherited(vc, bean, clazz, meth);
		if (isComp) {
			Method homeMeth = getMatchingHomeCreateMethod(vc, bean, clazz, meth, methodsExtendedLists);
			if (homeMeth == null) {
				isValid = false;
			}
		}

		return isValid;
	}
	
	/**
	 * 18.1.2 Programming restrictions
	 *...
	 *    - An enterprise Bean must not use read/write static fields. Using read-only static fields is
	 *   allowed. Therefore, it is recommended that all static fields in the enterprise bean class be
	 *   declared as final.
	 *...
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		//super.primValidate(ejbMethod);

		vc.terminateIfCancelled();

		validateStaticField(vc, bean, clazz, field);
	}
	
	/**
	 * 6.10.4 Business methods
	 * The session bean class may define zero or more business methods whose signatures must follow these rules:
	 * 		- The method names can be arbitrary, but they must not start with "ejb" to avoid conflicts with the callback methods used by the EJB architecture.
	 *
	 * 9.2.6 Business methods
	 *   - The entity bean class may define zero or more business methods whose signatures 
	 *     must follow these rules:
	 *        - The method names can be arbitrary, but they must not start with 'ejb' to 
	 *          avoid conflicts with the callback methods used by the EJB architecture.
	 */
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// The method names can be arbitrary, but they must not start with "ejb" to avoid conflicts with the callback methods used by the EJB architecture.
		if (method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJB)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2411, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// Section 6.10.5 (session), 9.2.7 (entity), declare that all exceptions declared
		// on the ejbCreate, ejbPostCreate methods must be defined in the throws clause of
		// the matching create of the remote interface.

		JavaClass remote = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remote);

		Method remoteMethod = ValidationRuleUtility.getMethodExtended(remote, method, method.getName());
		if (remoteMethod == null) {
			// Then this method shouldn't have been called; unless the method exists on the remote, this bean method isn't a business method.
			return;
		}
		
		Set exceptions = ValidationRuleUtility.getNotSubsetExceptions(bean, method, remoteMethod);
		if (exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while (iterator.hasNext()) {
				JavaClass exc = (JavaClass) iterator.next();
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2432, IEJBValidationContext.ERROR, bean, clazz, method, new String[] { remote.getQualifiedName(), exc.getQualifiedName()}, this);
				vc.addMessage(message);
			}
		}
		
		validateMatchingMethodExceptions(vc, bean, clazz, method, bean.getRemoteInterface(), remoteMethod);
	}
	
	/**
	 * 6.10.2 Session bean class
	 * The following are the requirements for session bean class:
	 * 		- The class must implement, directly or indirectly, the javax.ejb.SessionBean 
	 *        interface.
	 * 		- The class must be defined as public, must not be final, and must not 
	 *        be abstract.
	 * 		- The class may, but is not required to, implement the session bean's 
	 *        remote interface [4] .
	 * 		- If the class is a stateful session bean, it may optionally implement 
	 *        the javax.ejb.SessionSynchronization interface.
	 * 		- The session bean class may have superclasses and/or superinterfaces. If the 
	 *        session bean has superclasses, then the business methods, the ejbCreate methods, 
	 *        the methods of the SessionBean interface, and the methods of the optional 
	 *        SessionSynchronization interface may be defined in the session bean class, 
	 *        or in any of its superclasses.
	 *
	 *...
	 * 9.2.2 Enterprise bean class
	 *   - The following are the requirements for an entity bean class:
	 *      - The class must implement, directly or indirectly, the javax.ejb.EntityBean interface.
	 *      - The class must be defined as public and must not be abstract.
	 *      - The class must not be defined as final.
	 *...
	 *   - The class may, but is not required to, implement the entity bean's remote interface [9]. 
	 *     If the class implements the entity bean's remote interface, the class must provide no-op 
	 *     implementations of the methods defined in the javax.ejb.EJBObject interface. The container 
	 *     will never invoke these methods on the bean instances at runtime.
	 *   - A no-op implementation of these methods is required to avoid 
	 *     defining the entity bean class as abstract.
	 *   - The entity bean class must implement the business methods, and the 
	 *     ejbCreate, ejbPostCreate, and ejbFind<METHOD> methods as described 
	 *     later in this section.
	 *   - The entity bean class may have superclasses and/or superinterfaces. 
	 *     If the entity bean has superclasses, the business methods, the 
	 *     ejbCreate and ejbPostCreate methods, the finder methods, and the
	 *     methods of the EntityBean interface may be implemented in the 
	 *     enterprise bean class or in any of its superclasses.
	 *   - The entity bean class is allowed to implement other methods (for 
	 *     example helper methods invoked internally by the business methods) 
	 *     in addition to the methods required by the EJB specification.
	 *...
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();

		buildComponentParents(bean);

		// The class must implement, directly or indirectly, the javax.ejb.EntityBean (for entity beans), 
		// or the javax.ejb.SessionBean (for session beans), interface.
		if (!ValidationRuleUtility.isAssignableFrom(clazz, ValidationRuleUtility.getType(getParentName(), bean))) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2013, IEJBValidationContext.ERROR, bean, clazz, new String[] {clazz.getQualifiedName(), getParentName()}, this);
			vc.addMessage(message);
		}

		// For both entity and session beans, the class must be public.
		if (!clazz.isPublic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2022, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}

		// For both entity and session beans, the class must not be abstract.
		if (clazz.isAbstract()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2014, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}

		// For both entity and session beans, the class must not be final.
		if (clazz.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2015, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}

		if (ValidationRuleUtility.isUnnamedPackage(clazz.getJavaPackage())) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2041, IEJBValidationContext.INFO, bean, clazz, this);
			vc.addMessage(message);
		}
		
		validateAppendixB(vc, bean, clazz);
	}
	
	/**
	 * 9.2.3 ejbCreate methods
	 *   - The entity bean class may define zero or more ejbCreate(...) methods whose signatures 
	 *     must follow these rules:
	 *       - The method name must be ejbCreate.
	 *       - The method must be declared as public.
	 *       - The method must not be declared as final or static.
	 *       - The return type must be the entity bean's primary key type.
	 *       - The method argument and return value types must be legal types for RMI-IIOP.
	 *       - The throws clause may define arbitrary application specific exceptions, 
	 *         including the javax.ejb.CreateException.
	 *       - Compatibility Note: EJB 1.0 allowed the ejbCreate method to throw the 
	 *         java.rmi.RemoteException to indicate a non-application exception. This 
	 *         practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
	 *         should throw the javax.ejb.EJBException or another java.lang.RuntimeException
	 *         to indicate non-application exceptions to the Container (see Section 12.2.2).
	 *   - The entity object created by the ejbCreate method must have a unique primary key. 
	 *     This means that the primary key must be different from the primary keys of all 
	 *     the existing entity objects within the same home. The ejbCreate method should 
	 *     throw the DuplicateKeyException on an attempt to create an entity object with 
	 *     a duplicate primary key. However, it is legal to reuse the primary key of a
	 *     previously removed entity object.
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
	 *...
	 *
	 * 9.4.2 ejbCreate, ejbPostCreate
	 *...
	 *   - The ejbCreate(...) methods must be defined to return the primary key class type. 
	 *     The implementation of the ejbCreate(...) methods should be coded to return a null. 
	 *     The returned value is ignored by the Container.
	 *...
	 */
	public void validateEjbCreateMethod_homeDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		// The method which calls this method must have already tested that the method name is ejbCreate.
		if (method == null) {
			return;
		}

		Method createMethod = getMatchingHomeCreateMethodExtended(vc, bean, clazz, method);
		if (createMethod == null) {
			// Can't check the exceptions of a method which doesn't exist.
			return;
		}

		// Whether this is from a component inheritance or not, if the method exists on the
		// home, check that the method follows the spec.

		// Section 6.10.6 (session), 9.2.8 (entity), declare that all exceptions declared
		// on the ejbCreate, ejbPostCreate methods must be defined in the throws clause of
		// the matching create of the home interface.
		Set exceptions = ValidationRuleUtility.getNotSubsetExceptions(bean, method, createMethod);
		if (exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while (iterator.hasNext()) {
				JavaClass exc = (JavaClass) iterator.next();
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2420, IEJBValidationContext.ERROR, bean, clazz, method, new String[] { exc.getQualifiedName(), bean.getHomeInterface().getQualifiedName()}, this);
				vc.addMessage(message);
			}
		}

	}
	
	public void validateHelperMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
	}
	
	public void validateNoRemoteException(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, String messagePrefix) throws InvalidInputException {
		// EJB 2.0 added "throws InvalidInputException" above
		if (method == null) {
			return;
		}

		if(!ValidationRuleUtility.doesNotThrowRemoteException(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, messagePrefix, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

	}
	
	/**
	 * 18.1.2 Programming restrictions
	 *...
	 *    - An enterprise Bean must not use read/write static fields. Using read-only static fields is
	 *   allowed. Therefore, it is recommended that all static fields in the enterprise bean class be
	 *   declared as final.
	 *...
	 */
	protected void validateStaticField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return;
		}

		// An enterprise Bean must not use read/write static fields. Using read-only static fields is
		// allowed. Therefore, it is recommended that all static fields in the enterprise bean class be
		// declared as final.
		if (field.isStatic() && !field.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2200, IEJBValidationContext.WARNING, bean, clazz, field, this);
			vc.addMessage(message);
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2456, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{exception.getJavaName(), matchingClazz.getJavaName()}, this);
			vc.addMessage(message);
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
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2103, IEJBValidationContext.ERROR, bean, thisEjbObject, msgParm, this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String[] msgParm = { e.getJavaClass().getQualifiedName(), bean.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2849, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}
		
//		validateAppendixB(vc, supertype, parentEjbObject);
	}
}
