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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.IMessage;
import org.eclipse.wst.validation.internal.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class BMPBeanClassVRule extends AEntityBeanClassVRule implements IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_BMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_BMP_HOME, IValidationRuleList.EJB20_BMP_LOCALHOME, IValidationRuleList.EJB20_BMP_REMOTE, IValidationRuleList.EJB20_BMP_LOCAL, IValidationRuleList.EJB20_BMP_KEYCLASS};
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".bb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_ENTITYBEAN};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = new long[]{LOCAL_COMPONENT_INTERFACE, REMOTE_COMPONENT_INTERFACE};
	
	private final boolean SHOULD_BE_ABSTRACT = false;
	private final boolean SHOULD_BE_FINAL = false;
	private final boolean SHOULD_BE_PUBLIC = true;
	private final boolean SHOULD_VALIDATE_TRANSIENT_FIELD = false;
	
	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{CONSTRUCTOR_NOPARM, EJBFINDBYPRIMARYKEY, SETENTITYCONTEXT, UNSETENTITYCONTEXT_NOPARM, EJBACTIVATE_NOPARM, EJBPASSIVATE_NOPARM, EJBREMOVE_NOPARM, EJBLOAD_NOPARM, EJBSTORE_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{FINALIZE_NOPARM};
	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, CONSTRUCTOR_NOPARM, CONSTRUCTOR, EJBCREATE, EJBPOSTCREATE, EJBHOME, FINALIZE_NOPARM, EJBFINDBYPRIMARYKEY, EJBFIND, SETENTITYCONTEXT, UNSETENTITYCONTEXT_NOPARM, EJBACTIVATE_NOPARM, EJBPASSIVATE_NOPARM, EJBREMOVE_NOPARM, EJBLOAD_NOPARM, EJBSTORE_NOPARM, FINALIZE_NOPARM, BUSINESS};
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});

		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});

		MESSAGE_IDS.put(CHKJ2050_constr, new String[]{CHKJ2050_constr+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbCreate, new String[]{CHKJ2050_ejbCreate+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbFindByPrimaryKey, new String[]{CHKJ2050_ejbFindByPrimaryKey+EXT});
		MESSAGE_IDS.put(CHKJ2050_setEntityContext, new String[]{CHKJ2050_setEntityContext+EXT});
		MESSAGE_IDS.put(CHKJ2050_unsetEntityContext, new String[]{CHKJ2050_unsetEntityContext+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbActivate, new String[]{CHKJ2050_ejbActivate+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbPassivate, new String[]{CHKJ2050_ejbPassivate+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbRemove, new String[]{CHKJ2050_ejbRemove+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbLoad, new String[]{CHKJ2050_ejbLoad+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbStore, new String[]{CHKJ2050_ejbStore+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbSelect, new String[]{CHKJ2050_ejbSelect+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbFind, new String[]{CHKJ2050_ejbFind+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbPostCreate, new String[]{CHKJ2050_ejbPostCreate+EXT});

		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103+SPEC});
		
		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2407, new String[]{CHKJ2407+BEXT, CHKJ2407+MEXT});

		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2421, new String[]{CHKJ2421+BEXT, CHKJ2421+MEXT});
		MESSAGE_IDS.put(CHKJ2422, new String[]{CHKJ2422+BEXT, CHKJ2422+MEXT});
		MESSAGE_IDS.put(CHKJ2423, new String[]{CHKJ2423+BEXT, CHKJ2423+MEXT});
		MESSAGE_IDS.put(CHKJ2424, new String[]{CHKJ2424+BEXT, CHKJ2424+MEXT});
		MESSAGE_IDS.put(CHKJ2426, new String[]{CHKJ2426+BEXT, CHKJ2426+MEXT});
		MESSAGE_IDS.put(CHKJ2427, new String[]{CHKJ2427+BEXT, CHKJ2427+MEXT});
		MESSAGE_IDS.put(CHKJ2428, new String[]{CHKJ2428+BEXT, CHKJ2428+MEXT});

		MESSAGE_IDS.put(CHKJ2430, new String[]{CHKJ2430+BEXT, CHKJ2430+MEXT});
		MESSAGE_IDS.put(CHKJ2431, new String[]{CHKJ2431+BEXT, CHKJ2431+MEXT});
		MESSAGE_IDS.put(CHKJ2439, new String[]{CHKJ2439+BEXT, CHKJ2439+MEXT});

		MESSAGE_IDS.put(CHKJ2441, new String[]{CHKJ2441+BEXT, CHKJ2441+MEXT});
		MESSAGE_IDS.put(CHKJ2442, new String[]{CHKJ2442+BEXT, CHKJ2442+MEXT});
		MESSAGE_IDS.put(CHKJ2443, new String[]{CHKJ2443+BEXT, CHKJ2443+MEXT});

		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2457, new String[]{CHKJ2457+BEXT, CHKJ2457+MEXT});
		MESSAGE_IDS.put(CHKJ2458, new String[]{CHKJ2458+BEXT, CHKJ2458+MEXT});
		MESSAGE_IDS.put(CHKJ2459, new String[]{CHKJ2459+BEXT, CHKJ2459+MEXT});

		MESSAGE_IDS.put(CHKJ2478, new String[]{CHKJ2478+BEXT, CHKJ2478+MEXT});

		MESSAGE_IDS.put(CHKJ2492, new String[]{CHKJ2492+BEXT, CHKJ2492+MEXT});
		MESSAGE_IDS.put(CHKJ2493, new String[]{CHKJ2493+BEXT, CHKJ2493+MEXT});
		MESSAGE_IDS.put(CHKJ2494, new String[]{CHKJ2494+BEXT, CHKJ2494+MEXT});

		MESSAGE_IDS.put(CHKJ2500_ejbCreate, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_ejbFind, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_business, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2502_finalize, new String[]{CHKJ2502_finalize+BEXT, CHKJ2502_finalize+MEXT});

		MESSAGE_IDS.put(CHKJ2503_bus, new String[]{CHKJ2503_bus+BEXT, CHKJ2503_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbCreate, new String[]{CHKJ2503_ejbCreate+BEXT, CHKJ2503_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbPostCreate, new String[]{CHKJ2503_ejbPostCreate+BEXT, CHKJ2503_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbHome, new String[]{CHKJ2503_ejbHome+BEXT, CHKJ2503_ejbHome+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbFind, new String[]{CHKJ2503_ejbFind+BEXT, CHKJ2503_ejbFind+MEXT});

		MESSAGE_IDS.put(CHKJ2505_ejbRemove, new String[]{CHKJ2505_ejbRemove+BEXT, CHKJ2505_ejbRemove+MEXT});
		MESSAGE_IDS.put(CHKJ2505_ejbPostCreate, new String[]{CHKJ2505_ejbPostCreate+BEXT, CHKJ2505_ejbPostCreate+MEXT});
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
	public final boolean shouldValidateTransientField() {
		return SHOULD_VALIDATE_TRANSIENT_FIELD;
	}
	
	public final long[] getSupertypes() {
		return SUPERTYPES;
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
	
	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
	
	/*
	 * @see ABeanClassVRule#followEjbCreateReturnTypeRules(Method)
	 */
	public final JavaHelpers getEjbCreateReturnType(EnterpriseBean bean, Method method) {
		return ((Entity)bean).getPrimaryKey();
	}
	
	public final String getEjbCreateReturnTypeName(EnterpriseBean bean, Method method) {
		JavaHelpers retType = getEjbCreateReturnType(bean, method);
		if(retType == null) {
			return IEJBValidatorConstants.NULL_PRIMARY_KEY;
		}
		return retType.getJavaName();
	}
	
	public void validate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			validateEjbPostCreateMethod(vc, bean, clazz, method);
		}
		else if((methodType & EJBFIND) == EJBFIND) {
			validateEjbFindMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBHOME) == EJBHOME) {
			validateEjbHomeMethod(vc, bean, clazz, method, methodsExtendedLists);
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
	
	public final String getMatchingHomeMethodName(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			return getMatchingEjbCreateMethodName(vc, method);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			return getMatchingEjbPostCreateMethodName(vc, method);
		}
		else if((methodType & EJBFIND) == EJBFIND) {
			return getMatchingEjbFindMethodName(vc, method);
		}
		else if((methodType & EJBHOME) == EJBHOME) {
			return getMatchingEjbHomeMethodName(vc, method);
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
	
	public final void validateEjbFindMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// # ejbFind methods
		// IWAD4502 = This method must not exist on this class. Read section 10.5.5 of the EJB 2.0 specification.
		// IWAD4507 = The container provides the ejbFind method implementation. Read section 10.6.2 of the EJB 2.0 specification.
		// covered by the "must not exist" code?
		
		// IWAD4305 = This method must be public. Read section 12.2.5 of the EJB 2.0 specification.
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2457, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		
		// IWAD4306 = This method must not be final. Read section 12.2.5 of the EJB 2.0 specification.
		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2458, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		// IWAD4307 = This method must not be static. Read section 12.2.5 of the EJB 2.0 specification.
		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2459, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		// IWAD4308 = {0} must be a legal type for RMI-IIOP. Read section 12.2.5 of the EJB 2.0 specification.
		validateRMI_IIOPTypeRules(vc, bean, clazz, method, methodsExtendedLists, false); // do not check if the return type is serializable
		
		// IWAD4309 = This method must return {0} or a collection thereof. Read section 12.2.5 of the EJB 2.0 specification.
		// IWAD4312 = This method must return {0}. Read section 12.2.5 of the EJB 2.0 specification.
		JavaHelpers retType = ValidationRuleUtility.getType(method.getReturnType());
		JavaHelpers key = ((Entity)bean).getPrimaryKey();
		if(!(ValidationRuleUtility.isAssignableFromEnumeration(retType, bean) ||
			  ValidationRuleUtility.isAssignableFromCollection(retType, bean) ||
			  ValidationRuleUtility.isAssignableFrom(retType, key)
		   )) {
		   	String keyName = (key == null) ? IEJBValidatorConstants.NULL_PRIMARY_KEY : key.getJavaName();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2407, IValidationContext.WARNING, bean, clazz, method, new String[]{keyName}, this);
			vc.addMessage(message);
		}
		
		if(!ValidationRuleUtility.followsObjectNotFoundExceptionRules(bean, method)) {
			// IWAD4285 = This method must not throw javax.ejb.ObjectNotFoundException. Read section 12.1.8.4 of the EJB 2.0 specification.
			// IWAD4168 = This method must not throw ObjectNotFoundException. Read section 10.5.8.4 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2478, IValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		// IWAD4310 = This method must not throw java.rmi.RemoteException. Read section 12.2.5, 18.3.8, 18.6 of the EJB 2.0 specification.
		if(!followRemoteExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_ejbFind, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
}
