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
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * URL: http://java.sun.com/products/ejb/docs.html
 * Section: 9.2.8
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
	 * EJB 1.1 specification
	 * Section: 9.2.8
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
	 * EJB 1.1 specification
	 * Section: 9.2.8
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
