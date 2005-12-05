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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


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
 * 6.10.6 Session beans home interface
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
 */
public class ValidateSessionHome extends AValidateHome implements IMessagePrefixEjb11Constants {
	private boolean hasDefaultCreateMethod = false;
	private Set createMethods = null;

	private static final String MSSGID = ".sh"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private static final Object ID = IValidationRuleList.EJB11_SESSION_HOME;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_SESSION_BEANCLASS, IValidationRuleList.EJB11_SESSION_REMOTE};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2005, new String[]{CHKJ2005+BEXT, CHKJ2005+MEXT});
		MESSAGE_IDS.put(CHKJ2010, new String[]{CHKJ2010+EXT});
		
		MESSAGE_IDS.put(CHKJ2011, new String[]{CHKJ2011+EXT});
		MESSAGE_IDS.put(CHKJ2012, new String[]{CHKJ2012+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2024, new String[]{CHKJ2024+BEXT, CHKJ2024+MEXT});
		MESSAGE_IDS.put(CHKJ2026, new String[]{CHKJ2026+BEXT, CHKJ2026+MEXT});
		
		MESSAGE_IDS.put(CHKJ2104, new String[]{CHKJ2104 + SPEC});

		MESSAGE_IDS.put(CHKJ2402, new String[]{CHKJ2402+BEXT, CHKJ2402+MEXT});
		MESSAGE_IDS.put(CHKJ2403, new String[]{CHKJ2403+BEXT, CHKJ2403+MEXT});
		MESSAGE_IDS.put(CHKJ2405, new String[]{CHKJ2405+BEXT, CHKJ2405+MEXT});

		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});
		MESSAGE_IDS.put(CHKJ2415, new String[]{CHKJ2415+BEXT, CHKJ2415+MEXT});
		MESSAGE_IDS.put(CHKJ2419, new String[]{CHKJ2419+BEXT, CHKJ2419+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
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

	public ValidateSessionHome() {
		createMethods = new HashSet();
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

	/**
	 * Check the session home interface's methods follow the EJB 1.1 spec.
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
	 * ...
	 *   - The methods defined in this interface must follow the rules for RMI/IIOP. 
	 *     This means that their argument and return values must be of valid types 
	 *     for RMI/IIOP, and that their throws clause must include the java.rmi.RemoteException.
	 * ...
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
	 * ...
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method hiMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		// super.primValidate(hiMethod);

		vc.terminateIfCancelled();

		String name = hiMethod.getName();
		// Home interface should only declare create methods
		if (name.equals(IMethodAndFieldConstants.METHODNAME_CREATE)) {
			validateCreateMethod(vc, bean, clazz, hiMethod);
		}
		else {
			// Home interface should only declare create methods
			// (No methods other than create methods are listed in section 6.10.6,
			// but other sections of the spec, e.g. 9.2.2, explicitly list each type
			// of method which can be on a class/interface.)
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2419, IEJBValidationContext.ERROR, bean, clazz, hiMethod, this);
			vc.addMessage(message);
		}

		vc.terminateIfCancelled();
	}

	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		if (IMethodAndFieldConstants.METHODNAME_CREATE.equals(ejbMethod.getName()))
			incrementCreateMethodCount(vc, bean, clazz, ejbMethod);
	}
	
	/**
	 * Check that the session home class definition follows the EJB 1.1 spec.
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
	 * 6.10.6 Session bean's home interface
	 * The following are the requirements for the session bean's home interface:
	 *   - The interface must extend the javax.ejb.EJBHome interface.
	 * ...
	 *   - The home interface is allowed to have superinterfaces. Use of interface 
	 *     inheritance is subject to the RMI/IIOP rules for the definition of 
	 *     remote interfaces.
	 * ...
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		vc.terminateIfCancelled();
		// Both of the above checks are performed by the ValidateHome class's validateClass method.
		super.validateClass(vc, bean, clazz);
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
	protected void validateCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}

		// The methods defined in this interface must follow the rules for RMI/IIOP. 
		// This means that their argument and return values must be of valid types 
		// for RMI/IIOP, and that their throws clause must include the java.rmi.RemoteException.
		validateLegalRMIMethod(vc, bean, clazz, method);

		// Each create method must be named "create", and it must match one of 
		// the ejbCreate methods defined in the session bean class. The matching 
		// ejbCreate method must have the same number and types of arguments. 
		// (Note that the return type is different.)
		// All the exceptions defined in the throws clause of an ejbCreate method 
		// of the session bean class must be defined in the throws clause of the 
		// matching create method of the home interface.
		// The throws clause must include javax.ejb.CreateException.
		validateCreateMethod_beanDep(vc, bean, clazz, method);

		// The throws clause must include javax.ejb.CreateException.
		if (!ValidationRuleUtility.throwsCreateException(bean, method)) {
			String[] msgParm = { ITypeConstants.CLASSNAME_JAVAX_EJB_CREATEEXCEPTION };
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2414, IEJBValidationContext.ERROR, bean, clazz, method, msgParm, this);
			vc.addMessage(message);
		}

		// Perform the checks which depend on another class in the enterprise bean.
		validateCreateMethod_remoteDep(vc, bean, clazz, method);
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
	 *   - A session bean's home interface must define one or more create(...) methods.
	 */
	protected void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		final String[] modelObjectName = new String[] { clazz.getQualifiedName()};

		// A session bean's home must define one or more create methods.
		if (!hasCreateMethod()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2010, IEJBValidationContext.ERROR, bean, clazz, modelObjectName, this);
			vc.addMessage(message);
		}

		// The home interface of a stateless session bean must have one create 
		// method that takes no arguments and returns the session bean's remote 
		// interface. There can be no other create methods in the home interface. 
		if (ValidationRuleUtility.isStateless(bean) && (createMethods.size() > 0)) {
			Iterator iterator = createMethods.iterator();
			while (iterator.hasNext()) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2024, IEJBValidationContext.ERROR, bean, clazz, (Method) iterator.next(), modelObjectName, this);
				vc.addMessage(message);
			}
		}
	}
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasDefaultCreateMethod = false;
	}

}
