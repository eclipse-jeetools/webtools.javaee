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
import java.util.List;
import java.util.Map;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class StatelessSessionBeanClassVRule extends ASessionBeanClassVRule implements IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_STATELESS_SESSION_HOME, IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME, IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE, IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL};
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".sb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	private static final String SMSSGID = ".ssb"; //$NON-NLS-1$

	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_SESSIONBEAN};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = new long[]{LOCAL_COMPONENT_INTERFACE, REMOTE_COMPONENT_INTERFACE};
	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{EJBCREATE_NOPARM, CONSTRUCTOR_NOPARM, EJBREMOVE_NOPARM, EJBPASSIVATE_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{FINALIZE_NOPARM};
	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, CONSTRUCTOR_NOPARM, EJBCREATE_NOPARM, CONSTRUCTOR, EJBREMOVE_NOPARM, EJBACTIVATE_NOPARM, EJBPASSIVATE_NOPARM, FINALIZE_NOPARM, BUSINESS};

	private final boolean SHOULD_BE_ABSTRACT = false;
	private final boolean SHOULD_BE_FINAL = false;
	private final boolean SHOULD_BE_PUBLIC = true;
	private final boolean SHOULD_VALIDATE_TRANSIENT_FIELD = true;

	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2003E, new String[]{CHKJ2003E+SPEC});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});

		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});

		MESSAGE_IDS.put(CHKJ2050_constr, new String[]{CHKJ2050_constr+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbCreate, new String[]{CHKJ2050_ejbCreate+SMSSGID+SPEC});
		MESSAGE_IDS.put(CHKJ2050_ejbRemove, new String[]{CHKJ2050_ejbRemove+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbPassivate, new String[]{CHKJ2050_ejbPassivate+EXT});

		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103+SPEC});

		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2421, new String[]{CHKJ2421+BEXT, CHKJ2421+MEXT});
		MESSAGE_IDS.put(CHKJ2422, new String[]{CHKJ2422+BEXT, CHKJ2422+MEXT});
		MESSAGE_IDS.put(CHKJ2423, new String[]{CHKJ2423+BEXT, CHKJ2423+MEXT});
		MESSAGE_IDS.put(CHKJ2424, new String[]{CHKJ2424+BEXT, CHKJ2424+MEXT});
		MESSAGE_IDS.put(CHKJ2426, new String[]{CHKJ2426+BEXT, CHKJ2426+MEXT});
		MESSAGE_IDS.put(CHKJ2427, new String[]{CHKJ2427+BEXT, CHKJ2427+MEXT});
		MESSAGE_IDS.put(CHKJ2428, new String[]{CHKJ2428+BEXT, CHKJ2428+MEXT});

		MESSAGE_IDS.put(CHKJ2431, new String[]{CHKJ2431+BEXT, CHKJ2431+MEXT});
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2439, new String[]{CHKJ2439+BEXT, CHKJ2439+MEXT});

		MESSAGE_IDS.put(CHKJ2441, new String[]{CHKJ2441+BEXT, CHKJ2441+MEXT});
		MESSAGE_IDS.put(CHKJ2442, new String[]{CHKJ2442+BEXT, CHKJ2442+MEXT});
		MESSAGE_IDS.put(CHKJ2443, new String[]{CHKJ2443+BEXT, CHKJ2443+MEXT});

		MESSAGE_IDS.put(CHKJ2452, new String[]{CHKJ2452+BEXT, CHKJ2452+MEXT});
		MESSAGE_IDS.put(CHKJ2453, new String[]{CHKJ2453+BEXT, CHKJ2453+MEXT});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2476, new String[]{CHKJ2476+SMSSGID + ON_BASE+SPEC, CHKJ2476+SMSSGID+ON_THIS+SPEC});
		MESSAGE_IDS.put(CHKJ2492, new String[]{CHKJ2492+BEXT, CHKJ2492+MEXT});
		MESSAGE_IDS.put(CHKJ2493, new String[]{CHKJ2493+BEXT, CHKJ2493+MEXT});
		MESSAGE_IDS.put(CHKJ2494, new String[]{CHKJ2494+BEXT, CHKJ2494+MEXT});

		MESSAGE_IDS.put(CHKJ2500_ejbCreate, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_business, new String[]{CHKJ2500_business+BEXT, CHKJ2500_business+MEXT});
		MESSAGE_IDS.put(CHKJ2502_finalize, new String[]{CHKJ2502_finalize+BEXT, CHKJ2502_finalize+MEXT});

		MESSAGE_IDS.put(CHKJ2503_bus, new String[]{CHKJ2503_bus+BEXT, CHKJ2503_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbCreate, new String[]{CHKJ2503_ejbCreate+BEXT, CHKJ2503_ejbCreate+MEXT});

		MESSAGE_IDS.put(CHKJ2505_ejbRemove, new String[]{CHKJ2505_ejbRemove+BEXT, CHKJ2505_ejbRemove+MEXT});

		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
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
	
	public final boolean shouldValidateTransientField() {
		return SHOULD_VALIDATE_TRANSIENT_FIELD;
	}
	
	public final long[] getShouldNotBeSupertypes() {
		return SHOULD_NOT_BE_SUPERTYPES;
	}

	public final boolean shouldBeAbstract(JavaClass clazz) {
		return SHOULD_BE_ABSTRACT;
	}
	
	public final boolean shouldBeFinal(JavaClass clazz) {
		return SHOULD_BE_FINAL;
	}
	
	public final boolean shouldBePublic(JavaClass clazz) {
		return SHOULD_BE_PUBLIC;
	}

	public final int isRemote() {
		return NEITHER;
	}

	public final long[] getMethodsWhichMustExist() {
		return METHODS_WHICH_MUST_EXIST;
	}
	
	public final long[] getMethodsWhichMustNotExist() {
		return METHODS_WHICH_MUST_NOT_EXIST;
	}

	public final JavaHelpers getEjbCreateReturnType(EnterpriseBean bean, Method method) throws InvalidInputException {
		return ValidationRuleUtility.getType(ITypeConstants.VOID, bean);
	}

	public final String getEjbCreateReturnTypeName(EnterpriseBean bean, Method method) throws InvalidInputException {
		return ITypeConstants.VOID;
	}

	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
	
	public void validate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			validateEjbPostCreateMethod(vc, bean, clazz, method);
		}
		else if((methodType & EJBREMOVE_NOPARM) == EJBREMOVE_NOPARM) {
			validateEjbRemoveMethod(vc, bean, clazz, method);
		}
		else if((methodType & BUSINESS) == BUSINESS) {
			validateBusinessMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else {
			// Method isn't supposed to be here. Let the validateMethodsWhichMustNotExist
			// take care of it.
		}
	}

	public String getMatchingHomeMethodName(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			return getMatchingEjbCreateMethodName(vc, method);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			return getMatchingEjbPostCreateMethodName(vc, method);
		}
		else if((methodType & EJBREMOVE_NOPARM) == EJBREMOVE_NOPARM) {
			return getNoMatchingMethodName(vc, method);
		}
		else if((methodType & BUSINESS) == BUSINESS) {
			return getMatchingBusinessMethodName(vc, method);
		}
		else {
			// Method isn't supposed to be here. 
			return getNoMatchingMethodName(vc, method);
		}
	}
	
	public void validateEjbCreateMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if((method.listParametersWithoutReturn().length != 0) || (!IMethodAndFieldConstants.METHODNAME_EJBCREATE.equals(method.getName()))) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2476, IValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}
		else {
			super.validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.validation.core.core.ejb.ejb20.rules.IClassVRule#validate(org.eclipse.wst.validation.core.core.ejb.IValidationContext, org.eclipse.jst.j2ee.ejb.EnterpriseBean, org.eclipse.jem.internal.java.JavaClass)
	 */
	public void validate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// TODO Auto-generated method stub
		super.validate(vc, bean, clazz);
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
			JavaHelpers ss = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION, bean);
			if (ValidationRuleUtility.isAssignableFrom(bean.getEjbClass(), ss)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2003E, IValidationContext.ERROR, bean, bean.getEjbClass(), this);
				vc.addMessage(message);
			}
		}
		catch (InvalidInputException e) {
			String[] msgParm = { ValidationRuleUtility.getQualifiedName(bean.getEjbClass()), ValidationRuleUtility.getQualifiedName(e.getJavaClass())};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2907, IValidationContext.WARNING, bean, msgParm, this);
			vc.addMessage(message);
		}
	}

}
