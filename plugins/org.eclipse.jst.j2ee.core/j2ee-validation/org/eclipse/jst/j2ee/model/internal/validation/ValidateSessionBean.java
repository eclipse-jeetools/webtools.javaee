package org.eclipse.jst.j2ee.model.internal.validation;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * This class checks Session home interface classes for errors or potential errors.
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
 * 6.10.2 Session bean class
 * The following are the requirements for session bean class:
 * 		- The class must implement, directly or indirectly, the javax.ejb.SessionBean 
 *        interface.
 * 		- The class must be defined as public, must not be final, and must not 
 *        be abstract.
 * 		- The class must have a public constructor that takes no parameters. The 
 *        Container uses this constructor to create instances of the session bean class.
 * 		- The class must not define the finalize() method.
 * 		- The class may, but is not required to, implement the session bean's 
 *        remote interface [4] .
 * 		- The class must implement the business methods and the ejbCreate methods.
 * 		- If the class is a stateful session bean, it may optionally implement 
 *        the javax.ejb.SessionSynchronization interface.
 * 		- The session bean class may have superclasses and/or superinterfaces. If the 
 *        session bean has superclasses, then the business methods, the ejbCreate methods, 
 *        the methods of the SessionBean interface, and the methods of the optional 
 *        SessionSynchronization interface may be defined in the session bean class, 
 *        or in any of its superclasses.
 * 		- The session bean class is allowed to implement other methods (for example 
 *        helper methods invoked internally by the business methods) in addition to 
 *        the methods required by the EJB specification.
 *
 *		[4] If the session bean class does implement the remote interface, care must 
 *          be taken to avoid passing of this as a method argument or result. This 
 *          potential error can be avoided by choosing not to implement the remote 
 *          interface in the session bean class.
 *
 * 6.10.3 ejbCreate methods
 * The session bean class must define one or more ejbCreate(...) methods whose signatures must
 * follow these rules:
 * 		- The method name must be ejbCreate.
 * 		- The method must be declared as public.
 * 		- The method must not be declared as final or static.
 * 		- The return type must be void.
 * 		- The method arguments must be legal types for RMI/IIOP.
 * 		- The throws clause may define arbitrary application exceptions, possibly 
 *        including the javax.ejb.CreateException. 
 * Compatibility Note: EJB 1.0 allowed the ejbCreate method to throw the java.rmi.RemoteException 
 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 
 * compliant enterprise bean should throw the javax.ejb.EJBException or another RuntimeException 
 * to indicate non-application exceptions to the Container (see Section 12.2.2).
 *
 * 6.10.4 Business methods
 * The session bean class may define zero or more business methods whose signatures must follow these rules:
 * 		- The method names can be arbitrary, but they must not start with "ejb" to avoid conflicts with the callback methods used by the EJB architecture.
 * 		- The business method must be declared as public.
 * 		- The method must not be declared as final or static.
 * 		- The argument and return value types for a method must be legal types for RMI/IIOP.
 * 		- The throws clause may define arbitrary application exceptions.
 * Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
 * enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
 * indicate non-application exceptions to the Container (see Section 12.2.2).
 */
public class ValidateSessionBean extends AValidateBean implements IMessagePrefixEjb11Constants {
	private boolean hasValidConstructor = false;
	private boolean hasAConstructor = false;
	private boolean hasDefaultCreateMethod = false;
	private Set createMethods = null;

	private static final String MSSGID = ".sb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private static final Object ID = IValidationRuleList.EJB11_SESSION_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_SESSION_HOME, IValidationRuleList.EJB11_SESSION_REMOTE};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2003I, new String[]{CHKJ2003I + SPEC});
		MESSAGE_IDS.put(CHKJ2003E, new String[]{CHKJ2003E + SPEC});

		MESSAGE_IDS.put(CHKJ2006, new String[]{CHKJ2006+EXT});
		MESSAGE_IDS.put(CHKJ2007, new String[]{CHKJ2007+EXT});
		MESSAGE_IDS.put(CHKJ2008, new String[]{CHKJ2008+EXT});

		MESSAGE_IDS.put(CHKJ2013, new String[]{CHKJ2013+EXT});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});

		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2025, new String[]{CHKJ2025+BEXT, CHKJ2025+MEXT});
		
		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});

		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103 + SPEC});
		MESSAGE_IDS.put(CHKJ2200, new String[]{CHKJ2200+ON_BASE+SPEC, CHKJ2200+ON_THIS+SPEC}); // CHKJ2200 is a special case. It's shared by all bean types.

		MESSAGE_IDS.put(CHKJ2400_bus, new String[]{CHKJ2400_bus+BEXT, CHKJ2400_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbCreate, new String[]{CHKJ2400_ejbCreate+BEXT, CHKJ2400_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2401, new String[]{CHKJ2401+BEXT, CHKJ2401+MEXT});
		MESSAGE_IDS.put(CHKJ2408_bus, new String[]{CHKJ2408_bus+BEXT, CHKJ2408_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbCreate, new String[]{CHKJ2408_ejbCreate+BEXT, CHKJ2408_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_bus, new String[]{CHKJ2409_bus+BEXT, CHKJ2409_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbCreate, new String[]{CHKJ2409_ejbCreate+BEXT, CHKJ2409_ejbCreate+MEXT});

		MESSAGE_IDS.put(CHKJ2410_bus, new String[]{CHKJ2410_bus+BEXT, CHKJ2410_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbCreate, new String[]{CHKJ2410_ejbCreate+BEXT, CHKJ2410_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});

		MESSAGE_IDS.put(CHKJ2420, new String[]{CHKJ2420+BEXT, CHKJ2420+MEXT});

		MESSAGE_IDS.put(CHKJ2432, new String[]{CHKJ2432+BEXT, CHKJ2432+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public void reset() {
		super.reset();
		createMethods.clear();
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

	public ValidateSessionBean() {
		createMethods = new HashSet();
	}
	
	protected final String getParentName() {
		return ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONBEAN;
	}
	
	protected boolean hasCreateMethod() {
		if (hasDefaultCreateMethod == true)
			return true;
		if (createMethods.size() > 0)
			return true;
		return false;
	}

	protected void incrementCreateMethodCount(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		if ((method.listParametersWithoutReturn().length == 0) && (!hasDefaultCreateMethod)) {
			hasDefaultCreateMethod = true;
		}
		else {
			// add it to the list, for reporting extraneous create methods, for stateless session beans (section 6.8)
			createMethods.add(method);
		}
	}

	public boolean isFrameworkMethod(String name) {
		if (super.isFrameworkMethod(name)) {
			return true;
		}

		// check for session-specific methods
		if (name.equals(IMethodAndFieldConstants.METHODNAME_SETSESSIONCONTEXT)) {
			return true;
		}

		return false;
	}

	/**
	 * Check that the ejbCreate methods and business methods follow the EJB 1.1 spec.
	 *
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
	 * 6.10.2 Session bean class
	 * The following are the requirements for session bean class:
	 * ...
	 * 		- The class must have a public constructor that takes no parameters. The 
	 *        Container uses this constructor to create instances of the session bean class.
	 * 		- The class must not define the finalize() method.
	 * ...
	 * 		- The class must implement the business methods and the ejbCreate methods.
	 * ...
	 * 		- The session bean class is allowed to implement other methods (for example 
	 *        helper methods invoked internally by the business methods) in addition to 
	 *        the methods required by the EJB specification.
	 * ...
	 *
	 * 6.10.3 ejbCreate methods
	 * The session bean class must define one or more ejbCreate(...) methods whose signatures must
	 * follow these rules:
	 * 		- The method name must be ejbCreate.
	 * 		- The method must be declared as public.
	 * 		- The method must not be declared as final or static.
	 * 		- The return type must be void.
	 * 		- The method arguments must be legal types for RMI/IIOP.
	 * 		- The throws clause may define arbitrary application exceptions, possibly 
	 *        including the javax.ejb.CreateException. 
	 * Compatibility Note: EJB 1.0 allowed the ejbCreate method to throw the java.rmi.RemoteException 
	 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 
	 * compliant enterprise bean should throw the javax.ejb.EJBException or another RuntimeException 
	 * to indicate non-application exceptions to the Container (see Section 12.2.2).
	 *
	 * 6.10.4 Business methods
	 * The session bean class may define zero or more business methods whose signatures must follow these rules:
	 * 		- The method names can be arbitrary, but they must not start with "ejb" to avoid conflicts with the callback methods used by the EJB architecture.
	 * 		- The business method must be declared as public.
	 * 		- The method must not be declared as final or static.
	 * 		- The argument and return value types for a method must be legal types for RMI/IIOP.
	 * 		- The throws clause may define arbitrary application exceptions.
	 * Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
	 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
	 * enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
	 * indicate non-application exceptions to the Container (see Section 12.2.2).
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		// super.primValidate(ejbMethod);

		vc.terminateIfCancelled();

		String name = ejbMethod.getName();
		if (name.equals(IMethodAndFieldConstants.METHODNAME_EJBCREATE)) {
			validateEjbCreateMethod(vc, bean, clazz, ejbMethod);
		}
		else if (name.equals(IMethodAndFieldConstants.METHODNAME_FINALIZE)) {
			validateFinalizeMethod(vc, bean, clazz, ejbMethod);
		}
		else if (ejbMethod.isConstructor()) {
			validateConstructor(vc, bean, clazz, ejbMethod);
		}
		else if (isBusinessMethod(vc, bean, clazz, ejbMethod)) {
			validateBusinessMethod(vc, bean, clazz, ejbMethod);
		}
		else if (!isFrameworkMethod(name)) {
			validateHelperMethod(vc, bean, clazz, ejbMethod);
		}

		vc.terminateIfCancelled();

	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		if (IMethodAndFieldConstants.METHODNAME_EJBCREATE.equals(ejbMethod.getName()))
			incrementCreateMethodCount(vc, bean, clazz, ejbMethod);
		else if (ejbMethod.isConstructor())
			validateConstructor(vc, bean, clazz, ejbMethod);
	}
	
	/**
	 * Test that the supplied business method follows the EJB 1.1 spec rules.
	 *
	 * 6.10.4 Business methods
	 * The session bean class may define zero or more business methods whose signatures must follow these rules:
	 * 		- The method names can be arbitrary, but they must not start with "ejb" to avoid conflicts with the callback methods used by the EJB architecture.
	 * 		- The business method must be declared as public.
	 * 		- The method must not be declared as final or static.
	 * 		- The argument and return value types for a method must be legal types for RMI/IIOP.
	 * 		- The throws clause may define arbitrary application exceptions.
	 * Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
	 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
	 * enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
	 * indicate non-application exceptions to the Container (see Section 12.2.2).
	 */
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		super.validateBusinessMethod(vc, bean, clazz, method); // check method name does not start with 'ejb'	

		// The method arguments and return value types must be legal types for RMI/IIOP.
		validateLegalRMIMethodWithoutExceptions(vc, bean, clazz, method);

		// The rest of the checks are performed in the validateMethod method.
		validateBusinessModifiersAndExceptions(vc, bean, clazz, method);
	}
	
	/**
	 * Check that the session bean class definition follows the EJB 1.1 spec.
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
	 *		[4] If the session bean class does implement the remote interface, care must 
	 *          be taken to avoid passing of this as a method argument or result. This 
	 *          potential error can be avoided by choosing not to implement the remote 
	 *          interface in the session bean class.
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();
		super.validateClass(vc, bean, clazz);
		// super.validateClass() performs the following checks:
		// 		- The class must implement, directly or indirectly, the javax.ejb.SessionBean 
		//        interface.
		//   	- The class must be defined as public, must not be final, and must not 
		//        be abstract.

		// If the class is a stateful session bean, it may optionally implement 
		// the javax.ejb.SessionSynchronization interface.

		Session session = (Session)bean;
		boolean isStateless = ValidationRuleUtility.isStateless(bean);
		boolean isBeanManagedStateful = (ValidationRuleUtility.isBeanManagedSession(session) && ValidationRuleUtility.isStateful(session));

		if (isStateless) {
			/*
			 * 6.5.3 The optional SessionSynchronization interface
			 *...
			 * Only a stateful Session bean with container-managed transaction demarcation may 
			 * implement the SessionSynchronization interface.
			 *...
			 * There is no need for a Session bean with bean-managed transaction to rely on the 
			 * synchronization call backs because the bean is in control of the commit the bean 
			 * knows when the transaction is about to be committed and it knows the outcome of the 
			 * transaction commit.
			 *...
			 */
			try {
				if (ValidationRuleUtility.isAssignableFrom(bean.getEjbClass(), ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION, bean))) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2003E, IEJBValidationContext.ERROR, bean, bean.getEjbClass(), this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String beanClassName = (bean.getEjbClass() == null) ? IEJBValidatorConstants.NULL_BEAN_CLASS : bean.getEjbClass().getJavaName();
				String eClass = (e.getJavaClass() == null) ? ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION : e.getJavaClass().getJavaName(); // Since only getType throws InvalidInputException, this cannot have been thrown because of the bean class
				String[] msgParm = { beanClassName, eClass };
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2433, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}
		else if (isBeanManagedStateful) {
			/*
			 * 6.5.3 The optional SessionSynchronization interface
			 *...
			 * Only a stateful Session bean with container-managed transaction demarcation may 
			 * implement the SessionSynchronization interface.
			 *...
			 * There is no need for a Session bean with bean-managed transaction to rely on the 
			 * synchronization call backs because the bean is in control of the commit the bean 
			 * knows when the transaction is about to be committed and it knows the outcome of the 
			 * transaction commit.
			 *...
			 */
			try {
				if (ValidationRuleUtility.isAssignableFrom(bean.getEjbClass(), ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION, bean))) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2003I, IEJBValidationContext.INFO, bean, bean.getEjbClass(), this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String[] msgParm = { bean.getEjbClass().getQualifiedName(), e.getJavaClass().getQualifiedName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2907, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}

		// The session bean class may have superclasses and/or superinterfaces. If the 
		// session bean has superclasses, then the business methods, the ejbCreate methods, 
		// the methods of the SessionBean interface, and the methods of the optional 
		// SessionSynchronization interface may be defined in the session bean class, 
		// or in any of its superclasses.
		//  - this is taken care of by the EJB MOF model. When asked if a JavaClass has a method,
		//    the model checks the class' parents too.

		// The class may, but is not required to, implement the session bean's 
		// remote interface [4] .
		// [4] If the session bean class does implement the remote interface, care must 
		// be taken to avoid passing of this as a method argument or result. This 
		// potential error can be avoided by choosing not to implement the remote 
		// interface in the session bean class.
		validateClass_remoteDep(vc, bean, clazz);
	}
	
	/**
	 * Check that the session bean class definition follows the EJB 1.1 spec.
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
	 *		[4] If the session bean class does implement the remote interface, care must 
	 *          be taken to avoid passing of this as a method argument or result. This 
	 *          potential error can be avoided by choosing not to implement the remote 
	 *          interface in the session bean class.
	 */
	public void validateClass_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();
		// The class may, but is not required to, implement the session bean's 
		// remote interface [4] .
		// [4] If the session bean class does implement the remote interface, care must 
		// be taken to avoid passing of this as a method argument or result. This 
		// potential error can be avoided by choosing not to implement the remote 
		// interface in the session bean class.
		JavaClass remote = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remote);
		if (ValidationRuleUtility.isAssignableFrom(clazz, remote)) {
			String[] msgParm = {clazz.getQualifiedName(), remote.getQualifiedName()};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2040, IEJBValidationContext.INFO, bean, clazz, msgParm, this);
			vc.addMessage(message);
		}

	}
	
	protected void validateConstructor(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		hasAConstructor = true;
		if (!hasValidConstructor && ValidationRuleUtility.isPublic(method) && (method.listParametersWithoutReturn().length == 0)) {
			hasValidConstructor = true;
		}
	}
	
	/**
	 * Test that the supplied ejbCreate method follows the EJB 1.1 spec rules.
	 *
	 * 6.10.3 ejbCreate methods
	 * The session bean class must define one or more ejbCreate(...) methods whose signatures must
	 * follow these rules:
	 * 		- The method name must be ejbCreate.
	 * 		- The method must be declared as public.
	 * 		- The method must not be declared as final or static.
	 * 		- The return type must be void.
	 * 		- The method arguments must be legal types for RMI/IIOP.
	 * 		- The throws clause may define arbitrary application exceptions, possibly 
	 *        including the javax.ejb.CreateException. 
	 * Compatibility Note: EJB 1.0 allowed the ejbCreate method to throw the java.rmi.RemoteException 
	 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 
	 * compliant enterprise bean should throw the javax.ejb.EJBException or another RuntimeException 
	 * to indicate non-application exceptions to the Container (see Section 12.2.2).
	 */
	public void validateEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		// The method which calls this method has already tested that the method name is ejbCreate.
		if (method == null) {
			return;
		}

		// The return type must be void.
		JavaHelpers parmType = method.getReturnType();
		String returnTypeName = ((parmType == null) ? "" : parmType.getQualifiedName()); //$NON-NLS-1$

		if (!returnTypeName.equals(ITypeConstants.VOID)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2401, IEJBValidationContext.ERROR, bean, clazz, method, new String[] {clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}

		// The method arguments must be legal types for RMI/IIOP.
		validateLegalRMIMethodArguments(vc, bean, clazz, method);

		validateEjbCreateModifiersAndExceptions(vc, bean, clazz, method);

		validateEjbCreateMethod_homeDep(vc, bean, clazz, method);
	}
	
	/**
	 *...
	 * 6.10.2 Session bean class
	 * The following are the requirements for session bean class:
	 * ...
	 * 		- The class must not define the finalize() method.
	 * ...
	 */
	protected void validateFinalizeMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}

		if (method.listParametersWithoutReturn().length == 0) {
			// This is a warning, not an error, because EJB 1.0 allowed the finalize() method to be called. EJB 1.1 (section 6.10.2) specifically prohibits it.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2007, IEJBValidationContext.WARNING, bean, clazz, method, new String[] {clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * 6.8 Stateless session beans
	 *...
	 * The session bean class must define a single ejbCreate method that takes 
	 * no arguments.
	 *...
	 * 6.10.2 Session bean class
	 * The following are the requirements for session bean class:
	 * ...
	 * 		- The class must have a public constructor that takes no parameters. The 
	 *        Container uses this constructor to create instances of the session bean class.
	 * ...
	 * 6.10.3 ejbCreate methods
	 * The session bean class must define one or more ejbCreate(...) methods whose signatures must
	 * follow these rules:
	 *...
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

		if (!hasCreateMethod()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2008, IEJBValidationContext.ERROR, bean, clazz, modelObjectName, this);
			vc.addMessage(message);
		}

		// The session bean class must define a single ejbCreate method that takes 
		// no arguments.
		if (ValidationRuleUtility.isStateless(bean) && (createMethods.size() > 0)) {
			Iterator iterator = createMethods.iterator();
			while (iterator.hasNext()) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2025, IEJBValidationContext.ERROR, bean, clazz, (Method) iterator.next(), modelObjectName, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Test that the supplied method follows the EJB 1.1 spec rules.
	 *
	 * ejbCreate and business methods follow some of the same rules. This method checks those rules.
	 * (Part of sections 6.10.3 and 6.10.4)
	 *
	 * 		- The business method must be declared as public.
	 * 		- The method must not be declared as final or static.
	 * 		- The argument and return value types for a method must be legal types for RMI/IIOP.
	 * Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
	 * to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
	 * enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
	 * indicate non-application exceptions to the Container (see Section 12.2.2).
	 */
	public void validateBusinessModifiersAndExceptions(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// EJB 2.0 added "throws InvalidInputException" above
		vc.terminateIfCancelled();
		if (method == null)
			return;

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

		// The throws clause may define arbitrary application exceptions, possibly 
		// including the javax.ejb.CreateException. 
		// Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
		// to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
		// enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
		// indicate non-application exceptions to the Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_bus);
	}

	public void validateEjbCreateModifiersAndExceptions(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// EJB 2.0 added "throws InvalidInputException" above
		vc.terminateIfCancelled();
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

		// The throws clause may define arbitrary application exceptions, possibly 
		// including the javax.ejb.CreateException. 
		// Compatibility Note: EJB 1.0 allowed the business methods to throw the java.rmi.RemoteException 
		// to indicate a non-application exception. This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant
		// enterprise bean should throw the javax.ejb.EJBException or another RuntimeException to
		// indicate non-application exceptions to the Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_ejbCreate);
	}
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasValidConstructor = false;
		hasAConstructor = false;
		hasDefaultCreateMethod = false;
	}

}
