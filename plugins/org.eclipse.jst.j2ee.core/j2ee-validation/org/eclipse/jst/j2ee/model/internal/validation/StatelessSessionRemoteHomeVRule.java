/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import java.util.HashMap;
import java.util.Map;



/**
 * @version 	1.0
 * @author
 */
public final class StatelessSessionRemoteHomeVRule extends AStatelessHomeVRule implements IRemoteType, IHomeType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_STATELESS_SESSION_HOME;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS};
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".srh"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	private static final String SMSSGID = ".ssrh"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$

	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_EJBHOME};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;
	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{CREATE_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{};

	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, CREATE_NOPARM, CREATE, FINALIZE_NOPARM};

	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});

		MESSAGE_IDS.put(CHKJ2050_create, new String[]{CHKJ2050_create+SMSSGID+SPEC});

		MESSAGE_IDS.put(CHKJ2104, new String[]{CHKJ2104+SPEC});
		MESSAGE_IDS.put(CHKJ2402, new String[]{CHKJ2402+SMSSGID+ON_BASE+SPEC, CHKJ2402+SMSSGID+ON_THIS+SPEC});
		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});

		MESSAGE_IDS.put(CHKJ2461, new String[]{CHKJ2461+BEXT, CHKJ2461+MEXT});
		MESSAGE_IDS.put(CHKJ2465, new String[]{CHKJ2465+BEXT, CHKJ2465+MEXT});
		MESSAGE_IDS.put(CHKJ2466, new String[]{CHKJ2466+BEXT, CHKJ2466+MEXT});
		MESSAGE_IDS.put(CHKJ2467, new String[]{CHKJ2467+BEXT, CHKJ2467+MEXT});
		
		MESSAGE_IDS.put(CHKJ2476, new String[]{CHKJ2476+BEXT, CHKJ2476+MEXT});
		
		MESSAGE_IDS.put(CHKJ2500_create, new String[]{CHKJ2500_create+BEXT, CHKJ2500_create+MEXT});
		MESSAGE_IDS.put(CHKJ2500_home, new String[]{CHKJ2500_home+BEXT, CHKJ2500_home+MEXT});
		MESSAGE_IDS.put(CHKJ2502, new String[]{CHKJ2502+BEXT, CHKJ2502+MEXT});
		MESSAGE_IDS.put(CHKJ2503_create, new String[]{CHKJ2503_create+BEXT, CHKJ2503_create+MEXT});
		MESSAGE_IDS.put(CHKJ2504_create, new String[]{CHKJ2504_create+BEXT, CHKJ2504_create+MEXT});

		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	@Override
	public final long[] getBaseTypes() {
		return getSupertypes();
	}
	
	@Override
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	@Override
	public final int getMessageRemoteExceptionSeverity() {
		return MESSAGE_REMOTE_EXCEPTION_SEVERITY;
	}

	@Override
	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	@Override
	public final Object getId() {
		return ID;
	}

	@Override
	public final long[] getSupertypes() {
		return SUPERTYPES;
	}
	
	@Override
	public final long[] getShouldNotBeSupertypes() {
		return SHOULD_NOT_BE_SUPERTYPES;
	}

	@Override
	public final int isRemote() {
		return IS_REMOTE;
	}
	
	@Override
	public final long[] getMethodsWhichMustExist() {
		return METHODS_WHICH_MUST_EXIST;
	}
	
	@Override
	public final long[] getMethodsWhichMustNotExist() {
		return METHODS_WHICH_MUST_NOT_EXIST;
	}

	@Override
	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
}
