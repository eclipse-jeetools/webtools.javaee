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


import java.util.HashMap;
import java.util.Map;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * Validate a BMP key.
 */
public class ValidateBMPKey extends AValidateKeyClass implements IMessagePrefixEjb11Constants {
	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages

	private static final Object ID = IValidationRuleList.EJB11_BMP_KEYCLASS;
	private static final Object[] DEPENDS_ON = null;
	private static final Map MESSAGE_IDS;

	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2001, new String[]{CHKJ2001+EXT});

		MESSAGE_IDS.put(CHKJ2019, new String[]{CHKJ2019+EXT});

//Don't use that AValidateEJB method		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+EXT});
//Don't use that AValidateEJB method		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+EXT});
//Don't use that AValidateEJB method		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+EXT});

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
	 * This method actually does the validation.
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.primValidate(ejbMethod);

		//Nothing to do.
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		// Nothing to do
	}
}
