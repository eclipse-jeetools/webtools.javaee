package org.eclipse.jst.j2ee.model.internal.validation;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclispe.wst.validation.internal.core.ValidationException;


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
 *...
 * 9.2.9 Entity bean's primary key class
 *...
 *  - The primary key type must be a legal Value Type in RMI-IIOP.
 *...
 * 18.1.2 Programming restrictions
 *  This section describes the programming restrictions that a Bean Provider must follow to ensure that the
 *  enterprise bean is portable and can be deployed in any compliant EJB Container. The restrictions apply
 *  to the implementation of the business methods. Section 18.2, which describes the Container's view of
 *  these restrictions, defines the programming environment that all EJB Containers must provide.
 *
 *  - An enterprise Bean must not use read/write static fields. Using read-only static fields is
 *    allowed. Therefore, it is recommended that all static fields in the enterprise bean class be
 *    declared as final.
 *...
 *  - An enterprise Bean must not use thread synchronization primitives to synchronize execution of
 *    multiple instances.
 *...
 *  - An enterprise Bean must not use the AWT functionality to attempt to output information to a
 *    display, or to input information from a keyboard.
 *...
 *  - An enterprise bean must not use the java.io package to attempt to access files and directo-ries
 *    in the file system.
 *...
 *  - An enterprise bean must not attempt to listen on a socket, accept connections on a socket, or
 *    use a socket for multicast.
 *...
 *  - The enterprise bean must not attempt to query a class to obtain information about the declared
 *    members that are not otherwise accessible to the enterprise bean because of the security rules
 *    of the Java language. The enterprise bean must not attempt to use the Reflection API to access
 *    information that the security rules of the Java programming language make unavailable.
 *...
 *  - The enterprise bean must not attempt to create a class loader; obtain the current class loader;
 *    set the context class loader; set security manager; create a new security manager; stop the
 *    JVM; or change the input, output, and error streams.
 *...
 *  - The enterprise bean must not attempt to set the socket factory used by ServerSocket, Socket, or
 *    the stream handler factory used by URL.
 *...
 *  - The enterprise bean must not attempt to manage threads. The enterprise bean must not attempt
 *    to start, stop, suspend, or resume a thread; or to change a thread's priority or name. The enter-prise
 *    bean must not attempt to manage thread groups.
 *...
 *  - The enterprise bean must not attempt to directly read or write a file descriptor.
 *...
 *  - The enterprise bean must not attempt to obtain the security policy information for a particular
 *    code source.
 *...
 *  - The enterprise bean must not attempt to load a native library.
 *...
 *  - The enterprise bean must not attempt to gain access to packages and classes that the usual rules
 *    of the Java programming language make unavailable to the enterprise bean.
 *...
 *  - The enterprise bean must not attempt to define a class in a package.
 *...
 *  - The enterprise bean must not attempt to access or modify the security configuration objects
 *    (Policy, Security, Provider, Signer, and Identity).
 *...
 *  - The enterprise bean must not attempt to use the subclass and object substitution features of the
 *    Java Serialization Protocol.
 *...
 *  - The enterprise bean must not attempt to pass this as an argument or method result. The
 *    enterprise bean must pass the result of SessionContext.getEJBObject() or EntityContext.
 *    getEJBObject() instead.
 *
 */
public class ValidateBMPBean extends AValidateEntityBean implements IMessagePrefixEjb11Constants {
	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private static final Object ID = IValidationRuleList.EJB11_BMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_BMP_HOME, IValidationRuleList.EJB11_BMP_REMOTE, IValidationRuleList.EJB11_BMP_KEYCLASS};
	private static final Map MESSAGE_IDS;
	
	boolean hasPKMethod = false;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2002, new String[]{CHKJ2002+BEXT, CHKJ2002+MEXT});
		MESSAGE_IDS.put(CHKJ2006, new String[]{CHKJ2006+EXT});
		MESSAGE_IDS.put(CHKJ2007, new String[]{CHKJ2007+EXT});
		MESSAGE_IDS.put(CHKJ2009, new String[]{CHKJ2009+EXT});
		
		MESSAGE_IDS.put(CHKJ2013, new String[]{CHKJ2013+EXT});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2028, new String[]{CHKJ2028+BEXT, CHKJ2028+MEXT});
		MESSAGE_IDS.put(CHKJ2029, new String[]{CHKJ2029+BEXT, CHKJ2029+MEXT});
		
		MESSAGE_IDS.put(CHKJ2033, new String[]{CHKJ2033+EXT});
		MESSAGE_IDS.put(CHKJ2034, new String[]{CHKJ2034+EXT});
		MESSAGE_IDS.put(CHKJ2035, new String[]{CHKJ2035+EXT});
		MESSAGE_IDS.put(CHKJ2036, new String[]{CHKJ2036+EXT});
		MESSAGE_IDS.put(CHKJ2037, new String[]{CHKJ2037+EXT});
		MESSAGE_IDS.put(CHKJ2038, new String[]{CHKJ2038+EXT});
		MESSAGE_IDS.put(CHKJ2039, new String[]{CHKJ2039+EXT});
		
		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103 + SPEC});
		MESSAGE_IDS.put(CHKJ2200, new String[]{CHKJ2200+ON_BASE+SPEC, CHKJ2200+ON_THIS+SPEC}); // CHKJ2200 is a special case. It's shared by all bean types.
		
		MESSAGE_IDS.put(CHKJ2400_bus, new String[]{CHKJ2400_bus+BEXT, CHKJ2400_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbCreate, new String[]{CHKJ2400_ejbCreate+BEXT, CHKJ2400_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbFind, new String[]{CHKJ2400_ejbFind+BEXT, CHKJ2400_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbPostCreate, new String[]{CHKJ2400_ejbPostCreate+BEXT, CHKJ2400_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2406, new String[]{CHKJ2406+BEXT, CHKJ2406+MEXT});
		MESSAGE_IDS.put(CHKJ2407, new String[]{CHKJ2407+BEXT, CHKJ2407+MEXT});
		MESSAGE_IDS.put(CHKJ2408_bus, new String[]{CHKJ2408_bus+BEXT, CHKJ2408_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbCreate, new String[]{CHKJ2408_ejbCreate+BEXT, CHKJ2408_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbFind, new String[]{CHKJ2408_ejbFind+BEXT, CHKJ2408_ejbFind+MEXT});
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
	
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	public final Object getId() {
		return ID;
	}
	
	protected void incrementFindByPrimaryKeyCount(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}
		hasPKMethod = true;
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		super.primValidateExistence(vc, bean, clazz, ejbMethod);

		// BMPs must implement ejbFindByPrimaryKey. If it isn't implemented, validateMethodExists() will
		// output an error. (hasPKMethod = true implies implemented, otherwise not implemented)
		if (!hasPKMethod && IMethodAndFieldConstants.METHODNAME_EJBFINDBYPRIMARYKEY.equals(ejbMethod.getName())) {
			incrementFindByPrimaryKeyCount(vc, bean, clazz, ejbMethod);
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
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP business method checks
		super.validateBusinessMethod(vc, bean, clazz, method);

		// No specific BMP business method checks.
		// All of the points in 9.2.6 are common to both BMPs & CMPs.
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
	 */
	public void validateEjbFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// A finder method name must start with the prefix "ejbFind" 
		// (e.g. ejbFindByPrimaryKey, ejbFindLargeAccounts, ejbFindLateShipments).
		// The method which calls this method performs the above check.

		// Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
		// this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
		// be a single-object finder).
		if (method == null) {
			return;
		}

		vc.terminateIfCancelled();
		// A finder method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method argument types must be legal types for RMI-IIOP.
		validateLegalRMIMethodArguments(vc, bean, clazz, method);

		// The return type of a finder method must be the entity bean's primary key type, 
		// or a collection of primary keys (see Section Subsection 9.1.8).
		validateEjbFindMethod_key(vc, bean, clazz, method);

		// Compatibility Note: EJB 1.0 allowed the finder methods to throw the 
		// java.rmi.RemoteException to indicate a non-application exception. 
		// This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
		// should throw the javax.ejb.EJBException or another java.lang.RuntimeException
		// to indicate non-application exceptions to the Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_ejbFind);

		validateEjbFindMethod_homeDep(vc, bean, clazz, method);
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
	 */
	public void validateEjbFindMethod_key(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}
		// The return type of a finder method must be the entity bean's primary key type, 
		// or a collection of primary keys (see Section Subsection 9.1.8).
		vc.terminateIfCancelled();

		JavaHelpers returnType = method.getReturnType();

		JavaClass primaryKey = ((Entity) bean).getPrimaryKey();
		ValidationRuleUtility.isValidTypeHierarchy(bean, primaryKey);

		if( !(ValidationRuleUtility.isAssignableFrom(returnType, primaryKey) ||
			  ValidationRuleUtility.isAssignableFromCollection(returnType, bean) ||
			  ValidationRuleUtility.isAssignableFromEnumeration(returnType, bean)
		   )) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2407, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { primaryKey.getQualifiedName()}, this);
			vc.addMessage(message);
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
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP ejbPostCreate method checks
		super.validateEjbPostCreateMethod(vc, bean, clazz, method);

		// No specific BMP ejbPostCreate method checks.
		// All of the points in 9.2.4 are common to both BMPs & CMPs.
	}
	
	/**
	 * 9.2.5 ejbFind methods
	 *...
	 *   - Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
	 *     this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
	 *     be a single-object finder).
	 *...
	 */
	public void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		super.validateMethodExists(vc, bean, clazz);

		if (!hasPKMethod) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2009, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	public void verifyFieldExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		/*
		// Plus, check that at least one field exists on the bean.
		List fields = getFields();
		if((fields == null) || (fields.size() == 0)) {
			addValidationMessage(IEJBValidationContext.WARNING, IMessagePrefixEjb11Constants.EJB_BMP_NOFIELDS, new String[] {getModelObjectName()}, getModelObject());
			return;
		}
		*/
	}
	
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasPKMethod = false;
	}

}
