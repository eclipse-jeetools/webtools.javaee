package org.eclipse.jst.j2ee.model.validation;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * This class checks entity bean classes for errors or potential errors.
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
 *
 * All 9.2.X sections describe BMP requirements.
 * If a CMP requirement is different than these, then the differences are
 * documented in 9.4.X sections.
 *
 * 9.1.5.1 Entity bean instance's view: ... The entity Bean Provider is responsible
 *    for implementing the following methods in the entity bean class:...
 *
 * 9.2.2 Enterprise bean class
 *   - The following are the requirements for an entity bean class:
 *      - The class must implement, directly or indirectly, the javax.ejb.EntityBean interface.
 *      - The class must be defined as public and must not be abstract.
 *      - The class must not be defined as final.
 *      - The class must define a public constructor that takes no arguments.
 *      - The class must not define the finalize() method.
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
 *
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
 * 9.2.4 ejbPostCreate methods
 *   - For each ejbCreate(...) method, the entity bean class must define a matching 
 *     ejbPostCreate(...) method, using the following rules:
 *        - The method name must be ejbPostCreate.
 *        - The method must be declared as public.
 *        - The method must not be declared as final or static.
 *        - The return type must be void.
 *        - The method arguments must be the same as the arguments of the matching ejbCreate(...) method.
 *        - The throws clause may define arbitrary application specific exceptions, including the javax.ejb.CreateException.
 *        - Compatibility Note: EJB 1.0 allowed the ejbPostCreate method to throw the 
 *          java.rmi.RemoteExceptionto indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1
 *          compliant enterprise bean should throw the javax.ejb.EJBException or another java.lang.RuntimeException
 *          to indicate non-application exceptions to the Container (see Section 12.2.2).
 *
 * 9.2.5 ejbFind methods
 *   - The entity bean class may also define additional ejbFind<METHOD>(...) finder methods.
 *   - The signatures of the finder methods must follow the following rules:
 *   - A finder method name must start with the prefix "ejbFind" 
 *     (e.g. ejbFindByPrimaryKey, ejbFindLargeAccounts, ejbFindLateShipments).
 *   - A finder method must be declared as public.
 *   - The method must not be declared as final or static.
 *   - The method argument types must be legal types for RMI-IIOP.
 *   - The return type of a finder method must be the entity bean's primary key type, 
 *     or a collection of primary keys (see Section Subsection 9.1.8).
 *   - The throws clause may define arbitrary application specific exceptions, including 
 *     the javax.ejb.FinderException.
 *   - Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
 *     this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
 *     be a single-object finder).
 *   - Compatibility Note: EJB 1.0 allowed the finder methods to throw the 
 *     java.rmi.RemoteException to indicate a non-application exception. 
 *     This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
 *     should throw the javax.ejb.EJBException or another java.lang.RuntimeException
 *     to indicate non-application exceptions to the Container (see Section 12.2.2).
 *
 * 9.2.6 Business methods
 *   - The entity bean class may define zero or more business methods whose signatures 
 *     must follow these rules:
 *        - The method names can be arbitrary, but they must not start with 'ejb' to 
 *          avoid conflicts with the callback methods used by the EJB architecture.
 *        - The business method must be declared as public.
 *        - The method must not be declared as final or static.
 *        - The method argument and return value types must be legal types for RMI-IIOP.
 *        - The throws clause may define arbitrary application specific exceptions.
 *        - Compatibility Note: EJB 1.0 allowed the business methods to throw the 
 *          java.rmi.RemoteException to indicate a non-application exception. This 
 *          practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
 *          should throw the javax.ejb.EJBException or another java.lang.RuntimeException
 *          to indicate non-application exceptions to the Container (see Section 12.2.2).
 *...
 * 9.2.9 Entity bean's primary key class
 *...
 *  - The primary key type must be a legal Value Type in RMI-IIOP.
 *...
 *
 * 9.4.1 Container-managed fields
 *...
 *   - The following requirements ensure that an entity bean can be deployed in any 
 *     compliant container.
 *        - The Bean Provider must ensure that the Java programming language types 
 *          assigned to the container-managed fields are restricted to the following: 
 *          Java programming language primitive types, Java programming language 
 *          serializable types, and references of enterprise beans' remote or home 
 *          interfaces.
 *        - The Container Provider may, but is not required to, use Java programming 
 *          language Serialization to store the container-managed fields in the database. 
 *          If the container chooses a different approach, the effect should be equivalent 
 *          to that of Java programming language Serialization. The Container must also be 
 *          capable of persisting references to enterprise beans' remote and home interfaces 
 *          (for example, by storing their handle or primary key).
 *...
 *
 * 9.4.2 ejbCreate, ejbPostCreate
 *...
 *   - The ejbCreate(...) methods must be defined to return the primary key class type. 
 *     The implementation of the ejbCreate(...) methods should be coded to return a null. 
 *     The returned value is ignored by the Container.
 *...
 *
 * 9.4.6 finder methods
 *   - The entity Bean Provider does not write the finder (ejbFind<METHOD>(...)) methods.
 *...
 * 9.4.7 primary key type
 *   - The container must be able to manipulate the primary key type. Therefore, 
 *     the primary key type for an entity bean with container-managed persistence 
 *     must follow the rules in this subsection, in addition to those specified in 
 *     Subsection 9.2.9.
 *
 *     There are two ways to specify a primary key class for an entity bean with container-managed persistence:
 *        - Primary key that maps to a single field in the entity bean class.
 *        - Primary key that maps to multiple fields in the entity bean class.
 *     The second method is necessary for implementing compound keys, and the first method is convenient for
 *     single-field keys. Without the first method, simple types such as String would have to be wrapped in a
 *     user-defined class.
 * 
 *     9.4.7.1 Primary key that maps to a single field in the entity bean class
 *     The Bean Provider uses the primkey-field element of the deployment descriptor to specify the
 *     container-managed field of the entity bean class that contains the primary key. The field's type must be
 *     the primary key type.
 *
 *     9.4.7.2 Primary key that maps to multiple fields in the entity bean class
 *     The primary key class must be public, and must have a public constructor with no parameters.
 *     All fields in the primary key class must be declared as public.
 *     The names of the fields in the primary key class must be a subset of the names of the container-managed
 *     fields. (This allows the container to extract the primary key fields from an instance's container-managed
 *     fields, and vice versa.)
 *
 *     9.4.7.3 Special case: Unknown primary key class
 *     In special situations, the entity Bean Provider may choose not to specify the primary key class for an
 *     entity bean with container-managed persistence. This case usually happens when the entity bean does
 *     not have a natural primary key, and the Bean Provider wants to allow the Deployer to select the primary
 *     key fields at deployment time. The entity bean's primary key type will usually be derived from the primary
 *     key type used by the underlying database system that stores the entity objects. The primary key
 *     used by the database system may not be known to the Bean Provider.
 *     When defining the primary key for the enterprise bean, the Deployer may sometimes need to subclass
 *     the entity bean class to add additional container-managed fields (this typically happens for entity beans
 *     that do not have a natural primary key, and the primary keys are system-generated by the underlying
 *     database system that stores the entity objects).
 *     In this special case, the type of the argument of the findByPrimaryKey method must be declared as
 *     java.lang.Object, and the return value of ejbCreate() must be declared as
 *     java.lang.Object. The Bean Provider must specify the primary key class in the deployment
 *     descriptor as of the type java.lang.Object.
 *     The primary key class is specified at deployment time in the situations when the Bean Provider develops
 *     an entity bean that is intended to be used with multiple back-ends that provide persistence, and when
 *     these multiple back-ends require different primary key structures.
 *     Use of entity beans with a deferred primary key type specification limits the client application programming
 *     model, because the clients written prior to deployment of the entity bean may not use, in general,
 *     the methods that rely on the knowledge of the primary key type.
 *     The implementation of the enterprise bean class methods must be done carefully. For example, the meth-ods
 *     should not depend on the type of the object returned from EntityContext.getPrimaryKey(), because
 *     the return type is determined by the Deployer after the EJB class has been written.
 *
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
	public Method getMatchingHomeFindMethodExtended(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
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
	 *
	 * 9.2.2 Enterprise bean class
	 *...
	 *   - The following are the requirements for an entity bean class:
	 *...
	 *      - The class must define a public constructor that takes no arguments.
	 *      - The class must not define the finalize() method.
	 *...
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
	 */
	public void primValidate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
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
	protected void primValidateExistence(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
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
	 * 9.2.6 Business methods
	 *   - The entity bean class may define zero or more business methods whose signatures 
	 *     must follow these rules:
	 *        - The method names can be arbitrary, but they must not start with 'ejb' to 
	 *          avoid conflicts with the callback methods used by the EJB architecture.
	 *        - The business method must be declared as public.
	 *        - The method must not be declared as final or static.
	 *        - The method argument and return value types must be legal types for RMI-IIOP.
	 *        - The throws clause may define arbitrary application specific exceptions.
	 *        - Compatibility Note: EJB 1.0 allowed the business methods to throw the 
	 *          java.rmi.RemoteException to indicate a non-application exception. This 
	 *          practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
	 *          should throw the javax.ejb.EJBException or another java.lang.RuntimeException
	 *          to indicate non-application exceptions to the Container (see Section 12.2.2).
	 */
	public void validateBusinessMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		super.validateBusinessMethod(vc, bean, clazz, method); // make sure that name does not start with 'ejb'

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_bus, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_bus, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_bus, IValidationContext.ERROR, bean, clazz, method, this);
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
	
	protected void validateBusinessMethodNoRemoteException(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// EJB 2.0 added "throws InvalidInputException" above
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_bus);
	}
	
	/**
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
	 */
	public void validateClass(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// All of the above checks are performed by ValidateBean.
		super.validateClass(vc, bean, clazz);

		// Plus, check that at least one field exists on the bean.
		verifyFieldExists(vc, bean, clazz);
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
	public void validateEjbCreateMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		// The method which calls this method has already tested that the method name is ejbCreate.
		if (method == null)
			return;

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbCreate, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbCreate, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbCreate, IValidationContext.ERROR, bean, clazz, method, this);
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2002, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// Check for a matching create method on the home, and that the exceptions on
		// this method are a subset of the exceptions of the matching method on the home.
		validateEjbCreateMethod_homeDep(vc, bean, clazz, method);
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
	public void validateEjbCreateMethod_keyDep(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2406, IValidationContext.WARNING, bean, clazz, method, msgParm, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * 9.2.5 ejbFind methods
	 *   - The entity bean class may also define additional ejbFind<METHOD>(...) finder methods.
	 *   - The signatures of the finder methods must follow the following rules:
	 *   - A finder method name must start with the prefix "ejbFind" 
	 *     (e.g. ejbFindByPrimaryKey, ejbFindLargeAccounts, ejbFindLateShipments).
	 *   - A finder method must be declared as public.
	 *   - The method must not be declared as final or static.
	 *   - The method argument types must be legal types for RMI-IIOP.
	 *   - The return type of a finder method must be the entity bean's primary key type, 
	 *     or a collection of primary keys (see Section Subsection 9.1.8).
	 *   - The throws clause may define arbitrary application specific exceptions, including 
	 *     the javax.ejb.FinderException.
	 *   - Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
	 *     this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
	 *     be a single-object finder).
	 *   - Compatibility Note: EJB 1.0 allowed the finder methods to throw the 
	 *     java.rmi.RemoteException to indicate a non-application exception. 
	 *     This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
	 *     should throw the javax.ejb.EJBException or another java.lang.RuntimeException
	 *     to indicate non-application exceptions to the Container (see Section 12.2.2).
	 *
	 * 9.4.6 finder methods
	 *   - The entity Bean Provider does not write the finder (ejbFind<METHOD>(...)) methods.
	 *
	 * 9.4.7.3 (CMP) Special case: Unknown primary key class.
	 *   - In this special case, the type of the argument of the findByPrimaryKey method 
	 *     must be declared as java.lang.Object, and the return value of ejbCreate() must 
	 *     be declared as java.lang.Object. The Bean Provider must specify the primary key 
	 *     class in the deployment descriptor as of the type java.lang.Object.
	 *  (This does not need to be validated directly, since CMPs don't implement finder methods.
	 */
	public void validateEjbFindMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// This method is a no-op so that the dependency classes don't have to implement it.
		// (This method will never be called in a dependency class.)
	}
	
	/**
	 * 9.2.5 ejbFind methods
	 *   - The entity bean class may also define additional ejbFind<METHOD>(...) finder methods.
	 *   - The signatures of the finder methods must follow the following rules:
	 *   - A finder method name must start with the prefix "ejbFind" 
	 *     (e.g. ejbFindByPrimaryKey, ejbFindLargeAccounts, ejbFindLateShipments).
	 *   - A finder method must be declared as public.
	 *   - The method must not be declared as final or static.
	 *   - The method argument types must be legal types for RMI-IIOP.
	 *   - The return type of a finder method must be the entity bean's primary key type, 
	 *     or a collection of primary keys (see Section Subsection 9.1.8).
	 *   - The throws clause may define arbitrary application specific exceptions, including 
	 *     the javax.ejb.FinderException.
	 *   - Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
	 *     this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
	 *     be a single-object finder).
	 *   - Compatibility Note: EJB 1.0 allowed the finder methods to throw the 
	 *     java.rmi.RemoteException to indicate a non-application exception. 
	 *     This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
	 *     should throw the javax.ejb.EJBException or another java.lang.RuntimeException
	 *     to indicate non-application exceptions to the Container (see Section 12.2.2).
	 *
	 * 9.4.6 finder methods
	 *   - The entity Bean Provider does not write the finder (ejbFind<METHOD>(...)) methods.
	 *
	 * 9.4.7.3 (CMP) Special case: Unknown primary key class.
	 *   - In this special case, the type of the argument of the findByPrimaryKey method 
	 *     must be declared as java.lang.Object, and the return value of ejbCreate() must 
	 *     be declared as java.lang.Object. The Bean Provider must specify the primary key 
	 *     class in the deployment descriptor as of the type java.lang.Object.
	 *  (This does not need to be validated directly, since CMPs don't implement finder methods.
	 */
	public void validateEjbFindMethod_homeDep(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
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
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2420, IValidationContext.ERROR, bean, clazz, method, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 *
	 * 9.2.4 ejbPostCreate methods
	 * - For each ejbCreate(...) method, the entity bean class must define a matching 
	 *   ejbPostCreate(...) method, using the following rules:
	 *    - The method name must be ejbPostCreate.
	 *    - The method must be declared as public.
	 *    - The method must not be declared as final or static.
	 *    - The return type must be void.
	 *    - The method arguments must be the same as the arguments of the matching 
	 *      ejbCreate(...) method.
	 *    - The throws clause may define arbitrary application specific exceptions, 
	 *      including the javax.ejb.CreateException.
	 *      Compatibility Note: EJB 1.0 allowed the ejbPostCreate method to throw 
	 *      the java.rmi.RemoteException to indicate a non-application exception. 
	 *      This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise 
	 *      bean should throw the javax.ejb.EJBException or another 
	 *      java.lang.RuntimeException to indicate non-application exceptions to the 
	 *      Container (see Section 12.2.2).
	 *...
	*/
	public void validateEjbPostCreateMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		// The method which calls this method has already tested that the method name is ejbPostCreate.
		if (method == null)
			return;

		// The method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbPostCreate, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbPostCreate, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbPostCreate, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		vc.terminateIfCancelled();

		// The return type must be void.
		JavaHelpers parmType = method.getReturnType();
		String returnTypeName = ((parmType == null) ? "" : parmType.getQualifiedName()); //$NON-NLS-1$
		if (!returnTypeName.equals(ITypeConstants.VOID)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2418, IValidationContext.ERROR, bean, clazz, method, this);
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2028, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}

		validateEjbPostCreateMethod_homeDep(vc, bean, clazz, method);
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 *
	 * 9.2.4 ejbPostCreate methods
	 * - For each ejbCreate(...) method, the entity bean class must define a matching 
	 *   ejbPostCreate(...) method, using the following rules:
	 *    - The method name must be ejbPostCreate.
	 *    - The method must be declared as public.
	 *    - The method must not be declared as final or static.
	 *    - The return type must be void.
	 *    - The method arguments must be the same as the arguments of the matching 
	 *      ejbCreate(...) method.
	 *    - The throws clause may define arbitrary application specific exceptions, 
	 *      including the javax.ejb.CreateException.
	 *      Compatibility Note: EJB 1.0 allowed the ejbPostCreate method to throw 
	 *      the java.rmi.RemoteException to indicate a non-application exception. 
	 *      This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise 
	 *      bean should throw the javax.ejb.EJBException or another 
	 *      java.lang.RuntimeException to indicate non-application exceptions to the 
	 *      Container (see Section 12.2.2).
	 *...
	*/
	public void validateEjbPostCreateMethod_homeDep(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2029, IValidationContext.WARNING, bean, clazz, method, new String[] { homeIntf.getName()}, this);
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
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2420, IValidationContext.ERROR, bean, clazz, method, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * 9.2.2 Enterprise bean class
	 *...
	 *   - The following are the requirements for an entity bean class:
	 *...
	 *      - The class must not define the finalize() method.
	 *...
	 */
	protected void validateFinalize(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		// If it's "finalize()", the ejb bean shouldn't have the method.
		if (method.listParametersWithoutReturn().length == 0) {
			// This is a warning, not an error, because EJB 1.0 allowed the finalize() method to be called. EJB 1.1 (section 6.10.2) specifically prohibits it.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2007, IValidationContext.WARNING, bean, clazz, method, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * 9.2.2 Enterprise bean class
	 *...
	 *   - The following are the requirements for an entity bean class:
	 *...
	 *      - The class must define a public constructor that takes no arguments.
	 *...
	 *
	 * EJB spec 1.1, p. 105 explains why this method does not check for at least one ejbCreate method.
	 *    [6] An entity enterprise Bean has no ejbCreate(...) and ejbPostCreate(...) 
	 *    methods if it does not define any create methods in its home interface. 
	 *    Such an entity enterprise Bean does not allow the clients to create new 
	 *    EJB objects. The enterprise Bean restricts the clients to accessing entities 
	 *    that were created through direct database inserts.
	 */
	protected void validateMethodExists(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		final String[] modelObjectName = new String[] { clazz.getQualifiedName()};
		if (!hasValidConstructor && hasAConstructor) {
			// If a public constructor with no arguments does not exist explicitly,
			// Java will insert one as long as there are no constructors defined in the
			// class. If there is a constructor, Java does not insert a default constructor.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2006, IValidationContext.ERROR, bean, clazz, modelObjectName, this);
			vc.addMessage(message);
		}

		if (!hasSetEntityContext) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2033, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasUnsetEntityContext) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2034, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbActivate) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2035, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbPassivate) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2036, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbRemove) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2037, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbLoad) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2038, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}

		if (!hasEjbStore) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2039, IValidationContext.WARNING, bean, clazz, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Check that at least one field exists on the bean class.
	 */
	public abstract void verifyFieldExists(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException;

	/*
	 * @see IValidationRule#preValidate(IValidationContext, Object, Object)
	 */
	public void preValidate(IValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
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
