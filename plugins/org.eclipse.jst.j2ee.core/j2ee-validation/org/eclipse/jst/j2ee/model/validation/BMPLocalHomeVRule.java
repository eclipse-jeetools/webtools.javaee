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
package org.eclipse.jst.j2ee.model.validation;

import java.util.HashMap;
import java.util.Map;



/**
 * @version 	1.0
 * @author
 */
public final class BMPLocalHomeVRule extends ABMPHomeVRule implements ILocalType, IEJBType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_BMP_LOCALHOME;
	private static final Object[] DEPENDS_ON = new Object[] { IValidationRuleList.EJB20_BMP_BEANCLASS, IValidationRuleList.EJB20_BMP_KEYCLASS };
	private static final Map MESSAGE_IDS;

	private static final String MSSGID = ".blh"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private final long[] SUPERTYPES = new long[] { JAVAX_EJB_EJBLOCALHOME };
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;

	private final long[] METHODS_WHICH_MUST_EXIST = new long[] { FINDBYPRIMARYKEY, FIND };
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = null;

	private final long[] KNOWN_METHOD_TYPES = new long[] {CLINIT, CREATE, FINDBYPRIMARYKEY, FIND, HOME };

	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2017, new String[] {CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});

		MESSAGE_IDS.put(CHKJ2050_findByPrimaryKey, new String[] {CHKJ2050_findByPrimaryKey+EXT});
		MESSAGE_IDS.put(CHKJ2050_find, new String[]{CHKJ2050_find+EXT});

		MESSAGE_IDS.put(CHKJ2104, new String[]{CHKJ2104+SPEC});
		MESSAGE_IDS.put(CHKJ2402, new String[] { CHKJ2402+BEXT, CHKJ2402+MEXT });
		MESSAGE_IDS.put(CHKJ2403, new String[] { CHKJ2403+BEXT, CHKJ2403+MEXT });
		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});

		MESSAGE_IDS.put(CHKJ2461, new String[]{CHKJ2461+BEXT, CHKJ2461+MEXT});
		MESSAGE_IDS.put(CHKJ2462, new String[] { CHKJ2462+BEXT, CHKJ2462+MEXT });
		MESSAGE_IDS.put(CHKJ2463, new String[] { CHKJ2463+BEXT, CHKJ2463+MEXT });
		MESSAGE_IDS.put(CHKJ2464, new String[] { CHKJ2464+BEXT, CHKJ2464+MEXT });
		MESSAGE_IDS.put(CHKJ2465, new String[] { CHKJ2465+BEXT, CHKJ2465+MEXT });
		MESSAGE_IDS.put(CHKJ2466, new String[]{CHKJ2466+BEXT, CHKJ2466+MEXT});
		MESSAGE_IDS.put(CHKJ2467, new String[] { CHKJ2467+BEXT, CHKJ2467+MEXT });

		MESSAGE_IDS.put(CHKJ2475, new String[]{CHKJ2475+BEXT, CHKJ2475+MEXT});
		MESSAGE_IDS.put(CHKJ2477, new String[] { CHKJ2477+BEXT, CHKJ2477+MEXT });
		MESSAGE_IDS.put(CHKJ2478, new String[]{CHKJ2478+BEXT, CHKJ2478+MEXT});
		MESSAGE_IDS.put(CHKJ2479, new String[] { CHKJ2479+BEXT, CHKJ2479+MEXT });

		MESSAGE_IDS.put(CHKJ2500_create, new String[]{CHKJ2500_create+BEXT, CHKJ2500_create+MEXT});
		MESSAGE_IDS.put(CHKJ2500_find, new String[]{CHKJ2500_find+BEXT, CHKJ2500_find+MEXT});
		MESSAGE_IDS.put(CHKJ2500_home, new String[]{CHKJ2500_home+BEXT, CHKJ2500_home+MEXT});
		MESSAGE_IDS.put(CHKJ2503_create, new String[] { CHKJ2503_create+BEXT, CHKJ2503_create+MEXT });
		MESSAGE_IDS.put(CHKJ2503_find, new String[] { CHKJ2503_find+BEXT, CHKJ2503_find+MEXT });
		MESSAGE_IDS.put(CHKJ2503_home, new String[] { CHKJ2503_home+BEXT, CHKJ2503_home+MEXT });
		MESSAGE_IDS.put(CHKJ2504_create, new String[] { CHKJ2504_create+BEXT, CHKJ2504_create+MEXT });
		MESSAGE_IDS.put(CHKJ2504_find, new String[] { CHKJ2504_find+BEXT, CHKJ2504_find+MEXT });
		MESSAGE_IDS.put(CHKJ2504_home, new String[] { CHKJ2504_home+BEXT, CHKJ2504_home+MEXT });

		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}

	public final long[] getBaseTypes() {
		return getSupertypes();
	}

	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final int getMessageRemoteExceptionSeverity() {
		return MESSAGE_REMOTE_EXCEPTION_SEVERITY;
	}

	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}

	public final Object getId() {
		return ID;
	}
	public final long[] getSupertypes() {
		return SUPERTYPES;
	}

	public final long[] getShouldNotBeSupertypes() {
		return SHOULD_NOT_BE_SUPERTYPES;
	}
	public final int isRemote() {
		return IS_REMOTE;
	}

	public final long[] getMethodsWhichMustExist() {
		return METHODS_WHICH_MUST_EXIST;
	}

	public final long[] getMethodsWhichMustNotExist() {
		return METHODS_WHICH_MUST_NOT_EXIST;
	}
	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
}
