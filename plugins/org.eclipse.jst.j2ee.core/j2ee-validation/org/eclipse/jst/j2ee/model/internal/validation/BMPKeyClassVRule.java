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
import java.util.List;
import java.util.Map;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class BMPKeyClassVRule extends AKeyClassVRule implements IEJBType, IRemoteType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_BMP_KEYCLASS;
	private static final Object[] DEPENDS_ON = null;
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".kb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	
	private final long[] SUPERTYPES = null;
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;

	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{HASHCODE_NOPARM, EQUALS};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = null;

	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, HASHCODE_NOPARM, EQUALS, CONSTRUCTOR, CONSTRUCTOR_NOPARM};

	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2050_constr, new String[]{CHKJ2050_constr+EXT});
		MESSAGE_IDS.put(CHKJ2050_hashCode, new String[]{CHKJ2050_hashCode+EXT});
		MESSAGE_IDS.put(CHKJ2050_equals, new String[]{CHKJ2050_equals+EXT});

		MESSAGE_IDS.put(CHKJ2019, new String[]{CHKJ2019+EXT});

		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final int getMessageRemoteExceptionSeverity() {
		// Key methods are not checked for RemoteException, but to be consistent with the other VRules...
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
	
	public Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}
		
		return ((Entity)parent).getPrimaryKey();
	}
	
	/*
	 * @see IClassVRule#validate(IValidationContext, EnterpriseBean, JavaClass, Method)
	 */
	public final void validate(IValidationContext vc, EnterpriseBean bean, JavaClass parent, Method method, List[] methodExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// Do not perform any validation on the methods of this class (other than the
		// validation performed in AKeyClassVRule's check for the existence of hashCode()
		// and equals(Object)
	}

	/*
	 * @see IClassVRule#validate(IValidationContext, EnterpriseBean, JavaClass, Field)
	 */
	public final void validate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// Do not perform any validation on the fields of the BMP key class
		long fieldType = MethodUtility.getUtility().getFieldTypeId(bean, clazz, field, this);
		if((fieldType & IMethodAndFieldConstants.SERIALVERSIONUID) == IMethodAndFieldConstants.SERIALVERSIONUID) {
			validateSerialVersionUID(vc, bean, clazz, field);
		}
	}
}
