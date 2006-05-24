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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks entity bean classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * URL: http://java.sun.com/products/ejb/docs.html
 *
 * All 9.2.X sections describe BMP requirements.
 * If a CMP requirement is different than these, then the differences are
 * documented in 9.4.X sections.
 */
public abstract class AValidateEntityBean extends AValidateBean {
	protected final static String JAVAX_EJB_ENTITYBEAN = "javax.ejb.EntityBean"; //$NON-NLS-1$
	protected final static String SETENTITYCONTEXT = "setEntityContext"; //$NON-NLS-1$
	protected final static String UNSETENTITYCONTEXT = "unsetEntityContext"; //$NON-NLS-1$
	protected final static String EJBLOAD = "ejbLoad"; //$NON-NLS-1$
	protected final static String EJBSTORE = "ejbStore"; //$NON-NLS-1$
	protected final static String JAVAX_EJB_ENTITYCONTEXT = "javax.ejb.EntityContext"; //$NON-NLS-1$
	private boolean hasValidConstructor = false;
	private boolean hasAConstructor = false;
	private boolean hasSetEntityContext = false;
	private boolean hasUnsetEntityContext = false;
	private boolean hasEjbActivate = false;
	private boolean hasEjbPassivate = false;
	private boolean hasEjbRemove = false;
	private boolean hasEjbLoad = false;
	private boolean hasEjbStore = false;

	/**
	 * Given a bean's ejbFind method, return the matching find method from
	 * the home, if it exists. If not, return null.
	 */
	public Method getMatchingHomeFindMethodExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return null;
		}

		if (bean == null) {
			return null;
		}

		return ValidationRuleUtility.getMethodExtended(bean.getHomeInterface(), method, IMethodAndFieldConstants.PREFIX_FIND);
	}
	/**
	 * getParentName method comment.
	 */
	protected String getParentName() {
		return JAVAX_EJB_ENTITYBEAN;
	}
	
	public boolean isFrameworkMethod(String name) {
		if (name == null) {
			return false;
		}

		if (super.isFrameworkMethod(name)) {
			return true;
		}
		// check for entity-specific methods
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBLOAD)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBSTORE)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_SETENTITYCONTEXT)) {
			return true;
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_UNSETENTITYCONTEXT)) {
			return true;
		}

		return false;
	}
	
	/**
	 * Checks that the methods in the entity bean class follow the EJB 1.1. specification,
	 * and that there are no missing required methods.
	 * Section: 9.2.2
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.primValidate(ejbMethod);

		vc.terminateIfCancelled();

		String name = ejbMethod.getName();
		if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBCREATE))
			validateEjbCreateMethod(vc, bean, clazz, ejbMethod);
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE))
			validateEjbPostCreateMethod(vc, bean, clazz, ejbMethod);
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_FINALIZE))
			validateFinalize(vc, bean, clazz, ejbMethod);
		else if (name.startsWith(IMethodAndFieldConstants.PREFIX_EJBFIND))
			validateEjbFindMethod(vc, bean, clazz, ejbMethod);
		else if (isBusinessMethod(vc, bean, clazz, ejbMethod))
			validateBusinessMethod(vc, bean, clazz, ejbMethod);
		else
			validateHelperMethod(vc, bean, clazz, ejbMethod);

		vc.terminateIfCancelled();
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		vc.terminateIfCancelled();

		String name = ejbMethod.getName();
		if (!hasSetEntityContext && name.equals(IMethodAndFieldConstants.METHODNAME_SETENTITYCONTEXT)) {
			JavaParameter[] params = ejbMethod.listParametersWithoutReturn();
			if (params.length == 1) {
				if (ValidationRuleUtility.isAssignableFrom(params[0].getJavaType(), ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_ENTITYCONTEXT, bean))) {
					hasSetEntityContext = true;
				}
			}
		}
		else if (!hasUnsetEntityContext && name.equals(IMethodAndFieldConstants.METHODNAME_UNSETENTITYCONTEXT)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasUnsetEntityContext = true;
			}
		}
		else if (!hasEjbActivate && name.equals(IMethodAndFieldConstants.METHODNAME_EJBACTIVATE)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasEjbActivate = true;
			}
		}
		else if (!hasEjbPassivate && name.equals(IMethodAndFieldConstants.METHODNAME_EJBPASSIVATE)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasEjbPassivate = true;
			}
		}
		else if (!hasEjbRemove && name.equals(IMethodAndFieldConstants.METHODNAME_EJBREMOVE)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasEjbRemove = true;
			}
		}
		else if (!hasEjbLoad && name.equals(IMethodAndFieldConstants.METHODNAME_EJBLOAD)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasEjbLoad = true;
			}
		}
		else if (!hasEjbStore && name.equals(IMethodAndFieldConstants.METHODNAME_EJBSTORE)) {
			if (ejbMethod.listParametersWithoutReturn().length == 0) {
				hasEjbStore = true;
			}
		}
		else if (!hasValidConstructor && ejbMethod.isConstructor()) {
			hasAConstructor = true;
			if (ValidationRuleUtility.isPublic(ejbMethod) && (ejbMethod.listParametersWithoutReturn().length == 0)) {
				hasValidConstructor = true;
			}
		}

		vc.terminateIfCancelled();

	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.6
	 */
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		super.validateBusinessMethod(vc, bean, clazz, method); // make sure that name does not start with 'ejb'

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_bus, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_bus, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_bus, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method argument and return value types must be legal types for RMI-IIOP.
		validateLegalRMIMethodWithoutExceptions(vc, bean, clazz, method);

		// The throws clause may define arbitrary application specific exceptions.
		// Compatibility Note: EJB 1.0 allowed the business methods to throw the 
		// java.rmi.RemoteException to indicate a non-application exception. This 
		// practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
		// should throw the javax.ejb.EJBException or another java.lang.RuntimeException
		// to indicate non-application exceptions to the Container (see Section 12.2.2).
		validateBusinessMethodNoRemoteException(vc, bean, clazz, method);
	}
	
	protected void validateBusinessMethodNoRemoteException(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// EJB 2.0 added "throws InvalidInputException" above
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_bus);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.2
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// All of the above checks are performed by ValidateBean.
		super.validateClass(vc, bean, clazz);

		// Plus, check that at least one field exists on the bean.
		verifyFieldExists(vc, bean, clazz);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.3, 9.2.8 and 9.4.2
	 */
	public void validateEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		// The method which calls this method has already tested that the method name is ejbCreate.
		if (method == null)
			return;

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method argument and return value types must be legal types for RMI-IIOP.
		// But if the bean uses java.lang.Object for a key, don't check if it's serializable.
		if(ValidationRuleUtility.usesUnknownPrimaryKey(bean)) {
			validateLegalRMIMethodArguments(vc, bean, clazz, method);
		}
		else {
			validateLegalRMIMethodWithoutExceptions(vc, bean, clazz, method);
		}	
		
		// The return type must be the entity bean's primary key type; unless this is
		// a CMP, and an unkonwn primary key class is used. Read section 9.4.7.3.
		validateEjbCreateMethod_keyDep(vc, bean, clazz, method);

		// The throws clause may define arbitrary application specific exceptions, 
		// including the javax.ejb.CreateException.
		// Compatibility Note: EJB 1.0 allowed the ejbPostCreate method to throw 
		// the java.rmi.RemoteException to indicate a non-application exception. 
		// This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise 
		// bean should throw the javax.ejb.EJBException or another 
		// java.lang.RuntimeException to indicate non-application exceptions to the 
		// Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_ejbCreate);

		// Verify that there is a matching ejbPostCreate method for this ejbCreate method.
		Method ejbPostCreateMethod = ValidationRuleUtility.getMethodExtended(clazz, IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE, method.listParametersWithoutReturn());
		if (ejbPostCreateMethod == null) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2002, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// Check for a matching create method on the home, and that the exceptions on
		// this method are a subset of the exceptions of the matching method on the home.
		validateEjbCreateMethod_homeDep(vc, bean, clazz, method);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.3, 9.2.8 and 9.4.2
	 */
	public void validateEjbCreateMethod_keyDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		// The method which calls this method has already tested that the method name is ejbCreate.
		if (method == null)
			return;

		// Unknown primary key class (section 9.4.7.3) is also validated by this
		// check, because the primary key must be of type java.lang.Object, and
		// the ejbCreate must return java.lang.Object.
		JavaHelpers primaryKey = null;
		if(ValidationRuleUtility.usesUnknownPrimaryKey(bean)) {
			primaryKey = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, bean);
		}
		else {
			primaryKey = ((Entity) bean).getPrimaryKey();
		}

		// The return type must be the entity bean's primary key type.
		vc.terminateIfCancelled();
		JavaHelpers parmType = method.getReturnType();

		if (!ValidationRuleUtility.isAssignableFrom(parmType, primaryKey)) {
			// if the parameter type is java.lang.Object, could be section 9.4.7.3
			String keyName = (primaryKey == null) ? IEJBValidatorConstants.NULL_PRIMARY_KEY : primaryKey.getJavaName();
			String[] msgParm = {keyName};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2406, IEJBValidationContext.WARNING, bean, clazz, method, msgParm, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.5, 9.4.6 and 9.4.7.3
	 */
	public void validateEjbFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// This method is a no-op so that the dependency classes don't have to implement it.
		// (This method will never be called in a dependency class.)
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.5, 9.4.6 and 9.4.7.3
	 */
	public void validateEjbFindMethod_homeDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// All the exceptions defined in the throws clause of the matching ejbCreate 
		// and ejbPostCreate methods of the enterprise Bean class must be included in 
		// the throws clause of the matching create method of the home interface 
		// (i.e the set of exceptions defined for the create method must be a superset
		// of the union of exceptions defined for the ejbCreate and ejbPostCreate methods)
		JavaClass home = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, home);

		String methodName = IMethodAndFieldConstants.PREFIX_F + method.getName().substring(4); // e.g. if the home method is named findX, then the bean method will be named ejbFindX
		Method homeMethod = ValidationRuleUtility.getMethodExtended(home, method, methodName);
		if (homeMethod == null) {
			// Then this method shouldn't have been called; unless the method exists on the remote, this bean method isn't a business method.
			return;
		}
		Set exceptions = ValidationRuleUtility.getNotSubsetExceptions(bean, method, homeMethod);
		if (exceptions.size() > 0) {
			Iterator iterator = exceptions.iterator();
			while (iterator.hasNext()) {
				JavaClass exc = (JavaClass) iterator.next();
				String[] msgParm = { exc.getQualifiedName(), home.getQualifiedName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2420, IEJBValidationContext.ERROR, bean, clazz, method, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 * Section: 9.2.4
	*/
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		// The method which calls this method has already tested that the method name is ejbPostCreate.
		if (method == null)
			return;

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbPostCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbPostCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbPostCreate, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		vc.terminateIfCancelled();

		// The return type must be void.
		JavaHelpers parmType = method.getReturnType();
		String returnTypeName = ((parmType == null) ? "" : parmType.getQualifiedName()); //$NON-NLS-1$
		if (!returnTypeName.equals(ITypeConstants.VOID)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2418, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The throws clause may define arbitrary application specific exceptions, 
		// including the javax.ejb.CreateException.
		// Compatibility Note: EJB 1.0 allowed the ejbPostCreate method to throw 
		// the java.rmi.RemoteException to indicate a non-application exception. 
		// This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise 
		// bean should throw the javax.ejb.EJBException or another 
		// java.lang.RuntimeException to indicate non-application exceptions to the 
		// Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_ejbPostCreate);

		// Verify that the ejbPostCreate method has a matching ejbCreate method.
		Method ejbCreateMethod = ValidationRuleUtility.getMethodExtended(clazz, IMethodAndFieldConstants.METHODNAME_EJBCREATE, method.listParametersWithoutReturn());
		if (ejbCreateMethod == null) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2028, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		validateEjbPostCreateMethod_homeDep(vc, bean, clazz, method);
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 * Section: 9.2.4
	*/
	public void validateEjbPostCreateMethod_homeDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		// The method which calls this method has already tested that the method name is ejbPostCreate.
		if (method == null) {
			return;
		}

		JavaClass homeIntf = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, homeIntf);

		Method createMethod = getMatchingHomeCreateMethodExtended(vc, bean, clazz, method);
		if (createMethod == null) {
			// If the ejbCreate method is inherited from a component parent, it is likely
			// that the corresponding create method does not exist on the home.
			// Since our tools generate the component inheritance code, suppress this warning.

			// ejbCreate methods which are inherited via component inheritance, but do not
			// have a method on their immediate home, are filtered out via the isValid(Method) method.

			// The validateEjbCreateMethod checks for a matching create method, but just in
			// case the ejbCreate method is missing, check for it here too.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2029, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { homeIntf.getName()}, this);
			vc.addMessage(message);

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
				String[] msgParm = { exc.getQualifiedName(), homeIntf.getQualifiedName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2420, IEJBValidationContext.ERROR, bean, clazz, method, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.2
	 */
	protected void validateFinalize(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		// If it's "finalize()", the ejb bean shouldn't have the method.
		if (method.listParametersWithoutReturn().length == 0) {
			// This is a warning, not an error, because EJB 1.0 allowed the finalize() method to be called. EJB 1.1 (section 6.10.2) specifically prohibits it.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2007, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.2
	 */
	protected void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		final String[] modelObjectName = new String[] { clazz.getQualifiedName()};
		if (!hasValidConstructor && hasAConstructor) {
			// If a public constructor with no arguments does not exist explicitly,
			// Java will insert one as long as there are no constructors defined in the
			// class. If there is a constructor, Java does not insert a default constructor.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2006, IEJBValidationContext.ERROR, bean, clazz, modelObjectName, this);
			vc.addMessage(message);
		}

		if (!hasSetEntityContext) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2033, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasUnsetEntityContext) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2034, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbActivate) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2035, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbPassivate) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2036, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbRemove) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2037, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbLoad) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2038, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbStore) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2039, IEJBValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Check that at least one field exists on the bean class.
	 */
	public abstract void verifyFieldExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException;

	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasValidConstructor = false;
		hasAConstructor = false;
		hasSetEntityContext = false;
		hasUnsetEntityContext = false;
		hasEjbActivate = false;
		hasEjbPassivate = false;
		hasEjbRemove = false;
		hasEjbLoad = false;
		hasEjbStore = false;
		
	}

}
