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
import java.util.Map;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * This class checks bean managed entity home classes for errors or potential errors.
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
public class ValidateBMPHome extends AValidateEntityHome implements IMessagePrefixEjb11Constants {
	private static final String MSSGID = ".eh"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private static final Object ID = IValidationRuleList.EJB11_BMP_HOME;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_BMP_BEANCLASS, IValidationRuleList.EJB11_BMP_KEYCLASS};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2005, new String[]{CHKJ2005+BEXT, CHKJ2005+MEXT});
		
		MESSAGE_IDS.put(CHKJ2011, new String[]{CHKJ2011+EXT});
		MESSAGE_IDS.put(CHKJ2012, new String[]{CHKJ2012+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2026, new String[]{CHKJ2026+BEXT, CHKJ2026+MEXT});
		
		MESSAGE_IDS.put(CHKJ2030, new String[]{CHKJ2030+BEXT, CHKJ2030+MEXT});

		MESSAGE_IDS.put(CHKJ2104, new String[]{CHKJ2104 + SPEC});
		MESSAGE_IDS.put(CHKJ2402, new String[]{CHKJ2402+BEXT, CHKJ2402+MEXT});
		MESSAGE_IDS.put(CHKJ2403, new String[]{CHKJ2403+BEXT, CHKJ2403+MEXT});
		MESSAGE_IDS.put(CHKJ2405, new String[]{CHKJ2405+BEXT, CHKJ2405+MEXT});

		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});
		MESSAGE_IDS.put(CHKJ2415, new String[]{CHKJ2415+BEXT, CHKJ2415+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
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

	/**
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
	public void validateFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		super.validateFindMethod(vc, bean, clazz, method);

		if (method == null) {
			return;
		}

		// The verifyMatchingBeanFindMethod checks for the two following requirements:
		// 
		// Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
		// must match one of the ejbFind<METHOD> methods defined in the entity bean class 
		// (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
		// same number and types of arguments. (Note that the return type may be different.)
		// 
		// All the exceptions defined in the throws clause of an ejbFind method of the 
		// entity bean class must be included in the throws clause of the matching find
		// method of the home interface.
		validateFindMethod_beanDep(vc, bean, clazz, method);

	}
	
	/**
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
	public void validateFindMethod_beanDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}

		// The verifyMatchingBeanFindMethod checks for the two following requirements:
		// 
		// Each finder method must be named "find<METHOD>" (e.g. findLargeAccounts), and it
		// must match one of the ejbFind<METHOD> methods defined in the entity bean class 
		// (e.g. ejbFindLargeAccounts). The matching ejbFind<METHOD> method must have the 
		// same number and types of arguments. (Note that the return type may be different.)
		// 
		// All the exceptions defined in the throws clause of an ejbFind method of the 
		// entity bean class must be included in the throws clause of the matching find
		// method of the home interface.
		validateMatchingBeanFindMethod(vc, bean, clazz, method);

	}
}
