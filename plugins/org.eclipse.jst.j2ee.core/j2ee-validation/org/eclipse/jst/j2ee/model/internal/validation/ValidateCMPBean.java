package org.eclipse.jst.j2ee.model.internal.validation;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

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
 *
 * 9.4.1 Container-managed fields
 *...
 * The fields must be defined in the entity bean class as public, and must not be 
 * defined as transient.
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
public class ValidateCMPBean extends AValidateEntityBean implements IMessagePrefixEjb11Constants {
	private List _containerManagedFields = null;

	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private static final Object ID = IValidationRuleList.EJB11_CMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_CMP_HOME, IValidationRuleList.EJB11_CMP_REMOTE};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2002, new String[]{CHKJ2002+BEXT, CHKJ2002+MEXT});
		MESSAGE_IDS.put(CHKJ2004, new String[]{CHKJ2004+BEXT, CHKJ2004+MEXT});
		MESSAGE_IDS.put(CHKJ2006, new String[]{CHKJ2006+EXT});
		MESSAGE_IDS.put(CHKJ2007, new String[]{CHKJ2007+EXT});
		
		MESSAGE_IDS.put(CHKJ2013, new String[]{CHKJ2013+EXT});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2028, new String[]{CHKJ2028+BEXT, CHKJ2028+MEXT});
		MESSAGE_IDS.put(CHKJ2029, new String[]{CHKJ2029+BEXT, CHKJ2029+MEXT});
		
		MESSAGE_IDS.put(CHKJ2032, new String[]{CHKJ2032+EXT});
		MESSAGE_IDS.put(CHKJ2033, new String[]{CHKJ2033+EXT});
		MESSAGE_IDS.put(CHKJ2034, new String[]{CHKJ2034+EXT});
		MESSAGE_IDS.put(CHKJ2035, new String[]{CHKJ2035+EXT});
		MESSAGE_IDS.put(CHKJ2036, new String[]{CHKJ2036+EXT});
		MESSAGE_IDS.put(CHKJ2037, new String[]{CHKJ2037+EXT});
		MESSAGE_IDS.put(CHKJ2038, new String[]{CHKJ2038+EXT});
		MESSAGE_IDS.put(CHKJ2039, new String[]{CHKJ2039+EXT});
		
		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103 + SPEC});
		MESSAGE_IDS.put(CHKJ2200, new String[]{CHKJ2200+ON_BASE+SPEC, CHKJ2200+ON_THIS+SPEC}); // CHKJ2200 is a special case. It's shared by all bean types.
		MESSAGE_IDS.put(CHKJ2201, new String[]{CHKJ2201+BEXT, CHKJ2201+MEXT});
		MESSAGE_IDS.put(CHKJ2202, new String[]{CHKJ2202+BEXT, CHKJ2202+MEXT});
		MESSAGE_IDS.put(CHKJ2203, new String[]{CHKJ2203+BEXT, CHKJ2203+MEXT});
		MESSAGE_IDS.put(CHKJ2207, new String[]{CHKJ2207+EXT, CHKJ2207+EXT}); // special case where the message id is the same regardless of whether or not the method is inherited
		
		MESSAGE_IDS.put(CHKJ2400_bus, new String[]{CHKJ2400_bus+BEXT, CHKJ2400_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbCreate, new String[]{CHKJ2400_ejbCreate+BEXT, CHKJ2400_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbFind, new String[]{CHKJ2400_ejbFind+BEXT, CHKJ2400_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbPostCreate, new String[]{CHKJ2400_ejbPostCreate+BEXT, CHKJ2400_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2406, new String[]{CHKJ2406+BEXT, CHKJ2406+MEXT});
		MESSAGE_IDS.put(CHKJ2408_bus, new String[]{CHKJ2408_bus+BEXT, CHKJ2408_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbCreate, new String[]{CHKJ2408_ejbCreate+BEXT, CHKJ2408_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbPostCreate, new String[]{CHKJ2408_ejbPostCreate+BEXT, CHKJ2408_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_bus, new String[]{CHKJ2409_bus+BEXT, CHKJ2409_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbCreate, new String[]{CHKJ2409_ejbCreate+BEXT, CHKJ2409_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbFind, new String[]{CHKJ2409_ejbFind+BEXT, CHKJ2409_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbPostCreate, new String[]{CHKJ2409_ejbPostCreate+BEXT, CHKJ2409_ejbPostCreate+MEXT});
		
		MESSAGE_IDS.put(CHKJ2410_bus, new String[]{CHKJ2410_bus+BEXT, CHKJ2410_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbCreate, new String[]{CHKJ2410_ejbCreate+BEXT, CHKJ2410_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbFind, new String[]{CHKJ2410_ejbFind+BEXT, CHKJ2410_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbPostCreate, new String[]{CHKJ2410_ejbPostCreate+BEXT, CHKJ2410_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});
		MESSAGE_IDS.put(CHKJ2418, new String[]{CHKJ2418+BEXT, CHKJ2418+MEXT});
		MESSAGE_IDS.put(CHKJ2420, new String[]{CHKJ2420+BEXT, CHKJ2420+MEXT});
		MESSAGE_IDS.put(CHKJ2432, new String[]{CHKJ2432+BEXT, CHKJ2432+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public void reset() {
		super.reset();
		_containerManagedFields = null;
	}

	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	public final Object getId() {
		return ID;
	}

	protected List getContainerManagedFields() {
		return _containerManagedFields;
	}
	
	protected boolean hasContainerManagedField() {
		return (getContainerManagedFields() != null && getContainerManagedFields().size() > 0);
	}
	
	public boolean isContainerManagedField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) {
		if (field == null) {
			return false;
		}
		return getContainerManagedFields() != null && getContainerManagedFields().contains(field.getName());
	}
	
	/**
	 * 9.4.1 Container-managed fields
	 *...
	 * The fields must be defined in the entity bean class as public, and must not be 
	 * defined as transient.
	 *...
	 *   - The following requirements ensure that an entity bean can be deployed in any 
	 *     compliant container.
	 *        - The Bean Provider must ensure that the Java programming language types 
	 *          assigned to the container-managed fields are restricted to the following: 
	 *          Java programming language primitive types, Java programming language 
	 *          serializable types, and references of enterprise beans' remote or home 
	 *          interfaces.
	 *...
	 *
	 * Return true if the field is the enterprise bean's home interface.
	 */
	protected boolean isContainerManagedHome_homeDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return false;
		}

		if (bean == null) {
			return false;
		}

		JavaClass homeIntf = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, homeIntf);

		return ValidationRuleUtility.isAssignableFrom(ValidationRuleUtility.getType(field), homeIntf);
	}
	
	/**
	 * 9.4.1 Container-managed fields
	 *...
	 * The fields must be defined in the entity bean class as public, and must not be 
	 * defined as transient.
	 *...
	 *   - The following requirements ensure that an entity bean can be deployed in any 
	 *     compliant container.
	 *        - The Bean Provider must ensure that the Java programming language types 
	 *          assigned to the container-managed fields are restricted to the following: 
	 *          Java programming language primitive types, Java programming language 
	 *          serializable types, and references of enterprise beans' remote or home 
	 *          interfaces.
	 *...
	 *
	 * Return true if the field is the enterprise bean's remote interface.
	 */
	protected boolean isContainerManagedRemote_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return false;
		}

		if (bean == null) {
			return false;
		}

		JavaClass remoteIntf = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteIntf);

		return ValidationRuleUtility.isAssignableFrom(ValidationRuleUtility.getType(field), remoteIntf);
	}
	
	private List loadContainerManagedFields(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// The validation in this class, of the fields, is performed against the
		// container-managed fields, not the fields of this class directly.
		if (bean == null) {
			return Collections.EMPTY_LIST;
		}

		List fields = null;
		try {
			List cmpAttributes = ((ContainerManagedEntity) bean).getPersistentAttributes();
			if (cmpAttributes != null && !cmpAttributes.isEmpty()) {
				fields = new ArrayList(cmpAttributes.size());
				for (int i = 0; i < cmpAttributes.size(); i++)
					fields.add(((CMPAttribute) cmpAttributes.get(i)).getName());
			}
		}
		catch (Throwable exc) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
			fields = Collections.EMPTY_LIST;
		}
		return fields;
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
		super.primValidate(vc, bean, clazz, field);

		vc.terminateIfCancelled();

		validateContainerManagedField(vc, bean, clazz, field);
	}
	
	/**
	 * 9.2.6 Business methods
	 *   - The entity bean class may define zero or more business methods whose signatures 
	 *     must follow these rules:
	 *        - The method names can be arbitrary, but they must not start with ejb to 
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
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP business method checks
		if (!isEjbRelationshipRoleMethod(vc, bean, clazz, method))
			super.validateBusinessMethod(vc, bean, clazz, method);

		// No specific CMP business method checks.
		// All of the points in 9.2.6 are common to both BMPs & CMPs.
	}
	
	protected void validateBusinessMethodNoRemoteException(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (!isEjbRelationshipRoleMethod(vc, bean, clazz, method))
			super.validateBusinessMethodNoRemoteException(vc, bean, clazz, method); // EJB 2.0 added "throws InvalidInputException above"
	}
	
	/**
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
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// All of the above checks are performed by the parent.
		super.validateClass(vc, bean, clazz);

		validatePrimitivePrimaryKey(vc, bean, clazz); // if this class uses a primitive primary key, the type of the key must be the same as the type of the field
	}
	
	/**
	 * 9.4.1 Container-managed fields
	 *...
	 * The fields must be defined in the entity bean class as public, and must not be 
	 * defined as transient.
	 *...
	 *   - The following requirements ensure that an entity bean can be deployed in any 
	 *     compliant container.
	 *        - The Bean Provider must ensure that the Java programming language types 
	 *          assigned to the container-managed fields are restricted to the following: 
	 *          Java programming language primitive types, Java programming language 
	 *          serializable types, and references of enterprise beans' remote or home 
	 *          interfaces.
	 *...
	 */
	protected void validateContainerManagedField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (isContainerManagedField(vc, bean, clazz, field)) {
			if (field == null) {
				return;
			}

			vc.terminateIfCancelled();

			if (!ValidationRuleUtility.isPublic(field)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2203, IEJBValidationContext.ERROR, bean, clazz, field, this);
				vc.addMessage(message);
			}

			if (field.isTransient()) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2201, IEJBValidationContext.ERROR, bean, clazz, field, this);
				vc.addMessage(message);
			}

			// Check if it's a reference to the home or remote interface.
			validateContainerManagedField_dependent(vc, bean, clazz, field);
		}
	}
	
	/**
	 * 9.4.1 Container-managed fields
	 *...
	 * The fields must be defined in the entity bean class as public, and must not be 
	 * defined as transient.
	 *...
	 *   - The following requirements ensure that an entity bean can be deployed in any 
	 *     compliant container.
	 *        - The Bean Provider must ensure that the Java programming language types 
	 *          assigned to the container-managed fields are restricted to the following: 
	 *          Java programming language primitive types, Java programming language 
	 *          serializable types, and references of enterprise beans' remote or home 
	 *          interfaces.
	 *...
	 *
	 * Return true if the field is either the enterprise bean's remote interface, 
	 * or the enterprise bean's home interface.
	 */
	protected void validateContainerManagedField_dependent(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return;
		}

		JavaHelpers fieldType = ValidationRuleUtility.getType(field);

		if (!ValidationRuleUtility.isSerializable(fieldType, bean)) {
			// If it were primitive, it would be serializable, so two checks were done in that one line of code.
			// 
			// Check if it's the enterprise bean's remote or home interface
			vc.terminateIfCancelled();
			if (!(isContainerManagedHome_homeDep(vc, bean, clazz, field)) || (isContainerManagedRemote_remoteDep(vc, bean, clazz, field))) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2202, IEJBValidationContext.WARNING, bean, clazz, field, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * 9.4.6 finder methods
	 *   - The entity Bean Provider does not write the finder (ejbFind<METHOD>(...)) methods.
	 */
	public void validateEjbFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			throw new InvalidInputException();
		}

		// Only BMPs implement finder methods.
		IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2004, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { clazz.getQualifiedName()}, this);
		vc.addMessage(message);
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
	 * 9.4.2 ejbCreate, ejbPostCreate
	 *...
	 *   - The ejbCreate(...) methods must be defined to return the primary key class type. 
	 *     The implementation of the ejbCreate(...) methods should be coded to return a null. 
	 *     The returned value is ignored by the Container.
	 *...
	*/
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP ejbPostCreate method checks
		super.validateEjbPostCreateMethod(vc, bean, clazz, method);

		// No specific CMP ejbPostCreateMethod checks.
		// All of the points in 9.2.4 are common to both BMPs & CMPs.
	}
	
	protected void validatePrimitivePrimaryKey(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		ContainerManagedEntity cmp = (ContainerManagedEntity) bean; // bean is checked for null in AValidateEJB.validate() method, so don't need to check for it here.

		if (ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
			// primitive primary key

			// primary key that maps to a single field in the entity bean class
			// The field's type must be the primary key type.
			CMPAttribute keyAttribute = cmp.getPrimKeyField();
			Field keyField = (keyAttribute == null) ? null : keyAttribute.getField();
			JavaClass primaryKey = cmp.getPrimaryKey();
			if ((keyField == null) || !ValidationRuleUtility.isAssignableFrom((JavaHelpers)keyField.getEType(), primaryKey)) {
				String[] msgParm = { keyAttribute.getName(), primaryKey.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2207, IEJBValidationContext.ERROR, bean, clazz, keyField, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Check that at least one field exists on the bean.
	 */
	public void verifyFieldExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		if (!hasContainerManagedField()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2032, IEJBValidationContext.WARNING, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		_containerManagedFields = loadContainerManagedFields(vc, (EnterpriseBean)targetParent, (JavaClass)target);
	}

}
