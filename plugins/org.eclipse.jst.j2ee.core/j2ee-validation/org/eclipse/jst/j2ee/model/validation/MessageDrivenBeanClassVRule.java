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
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class MessageDrivenBeanClassVRule extends ABeanClassVRule implements IEJBType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_MESSAGE_BEANCLASS;
	private static final Object[] DEPENDS_ON = null;
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".mb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_MESSAGEDRIVENBEAN, JAVAX_JMS_MESSAGELISTENER};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;

	private final boolean SHOULD_VALIDATE_TRANSIENT_FIELD = false;
	private final boolean SHOULD_BE_ABSTRACT = false;
	private final int IS_REMOTE = NEITHER;
	
	private final boolean SHOULD_BE_FINAL = false;
	private final boolean SHOULD_BE_PUBLIC = true;

	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{ONMESSAGE, CONSTRUCTOR_NOPARM, EJBCREATE_NOPARM, EJBREMOVE_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{FINALIZE_NOPARM};
	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, ONMESSAGE, CONSTRUCTOR_NOPARM, CONSTRUCTOR, EJBCREATE_NOPARM, EJBCREATE, EJBREMOVE_NOPARM, FINALIZE_NOPARM};
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2050_constr, new String[]{CHKJ2050_constr+EXT});
		MESSAGE_IDS.put(CHKJ2050_onMessage, new String[]{CHKJ2050_onMessage+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbCreate, new String[]{CHKJ2050_ejbCreate+EXT});
		MESSAGE_IDS.put(CHKJ2050_setEntityContext, new String[]{CHKJ2050_setEntityContext+EXT});
		MESSAGE_IDS.put(CHKJ2050_unsetEntityContext, new String[]{CHKJ2050_unsetEntityContext+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbActivate, new String[]{CHKJ2050_ejbActivate+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbPassivate, new String[]{CHKJ2050_ejbPassivate+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbRemove, new String[]{CHKJ2050_ejbRemove+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbLoad, new String[]{CHKJ2050_ejbLoad+EXT});
		MESSAGE_IDS.put(CHKJ2050_ejbStore, new String[]{CHKJ2050_ejbStore+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});
		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});
		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});

		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103+SPEC});
		MESSAGE_IDS.put(CHKJ2421, new String[]{CHKJ2421+BEXT, CHKJ2421+MEXT});
		MESSAGE_IDS.put(CHKJ2422, new String[]{CHKJ2422+BEXT, CHKJ2422+MEXT});
		MESSAGE_IDS.put(CHKJ2423, new String[]{CHKJ2423+BEXT, CHKJ2423+MEXT});
		MESSAGE_IDS.put(CHKJ2424, new String[]{CHKJ2424+BEXT, CHKJ2424+MEXT});
		MESSAGE_IDS.put(CHKJ2426, new String[]{CHKJ2426+BEXT, CHKJ2426+MEXT});

		MESSAGE_IDS.put(CHKJ2427, new String[]{CHKJ2427+BEXT, CHKJ2427+MEXT});
		MESSAGE_IDS.put(CHKJ2428, new String[]{CHKJ2428+BEXT, CHKJ2428+MEXT});
		MESSAGE_IDS.put(CHKJ2431, new String[]{CHKJ2431+BEXT, CHKJ2431+MEXT});
		MESSAGE_IDS.put(CHKJ2439, new String[]{CHKJ2439+BEXT, CHKJ2439+MEXT});

		MESSAGE_IDS.put(CHKJ2445, new String[]{CHKJ2445+BEXT, CHKJ2445+MEXT});
		MESSAGE_IDS.put(CHKJ2446, new String[]{CHKJ2446+BEXT, CHKJ2446+MEXT});
		MESSAGE_IDS.put(CHKJ2447, new String[]{CHKJ2447+BEXT, CHKJ2447+MEXT});
		MESSAGE_IDS.put(CHKJ2501_ejbCreate, new String[]{CHKJ2501_ejbCreate+BEXT, CHKJ2501_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2501_ejbRemove, new String[]{CHKJ2501_ejbRemove+BEXT, CHKJ2501_ejbRemove+MEXT});
		MESSAGE_IDS.put(CHKJ2501_onMessage, new String[]{CHKJ2501_onMessage+BEXT, CHKJ2501_onMessage+MEXT});

		MESSAGE_IDS.put(CHKJ2492, new String[]{CHKJ2492+BEXT, CHKJ2492+MEXT});
		MESSAGE_IDS.put(CHKJ2493, new String[]{CHKJ2493+BEXT, CHKJ2493+MEXT});
		MESSAGE_IDS.put(CHKJ2494, new String[]{CHKJ2494+BEXT, CHKJ2494+MEXT});

		MESSAGE_IDS.put(CHKJ2502_finalize, new String[]{CHKJ2502_finalize+BEXT, CHKJ2502_finalize+MEXT});

		MESSAGE_IDS.put(CHKJ2503_onMessage, new String[]{CHKJ2503_onMessage+BEXT, CHKJ2503_onMessage+MEXT});

		MESSAGE_IDS.put(CHKJ2505_ejbRemove, new String[]{CHKJ2505_ejbRemove+BEXT, CHKJ2505_ejbRemove+MEXT});
		MESSAGE_IDS.put(CHKJ2505_onMessage, new String[]{CHKJ2505_onMessage+BEXT, CHKJ2505_onMessage+MEXT});

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
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

	public final boolean shouldValidateTransientField() {
		return SHOULD_VALIDATE_TRANSIENT_FIELD;
	}
	
	public final long[] getSupertypes() {
		return SUPERTYPES;
	}
	
	public final long[] getShouldNotBeSupertypes() {
		return SHOULD_NOT_BE_SUPERTYPES;
	}

	public final boolean followEjbCreateReturnTypeRules(EnterpriseBean bean, Method method) {
		return method.isVoid();
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
		return IS_REMOTE;
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
	
	public final void validate(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & ONMESSAGE) == ONMESSAGE) {
			validateOnMessageMethod(vc, bean, clazz, method);
		}
		else if((methodType & EJBCREATE) == EJBCREATE) {
			validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBREMOVE_NOPARM) == EJBREMOVE_NOPARM) {
			validateEjbRemoveMethod(vc, bean, clazz, method);
		}
		else {
			// Method isn't supposed to be here. Let the validateMethodsWhichMustNotExist
			// take care of it.
		}
	}
	
	public String getMatchingHomeMethodName(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		return getNoMatchingMethodName(vc, method);
	}

	public void validateEjbCreateMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		
		if(ValidationRuleUtility.throwsApplicationException(method, bean)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2501_ejbCreate, IValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	public void validateEjbRemoveMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validateEjbRemoveMethod(vc, bean, clazz, method);
		
		if(ValidationRuleUtility.throwsApplicationException(method, bean)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2501_ejbRemove, IValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}

	public boolean validateEjbCreateMethodExceptions() {
		// Don't check for CreateException
		return false;
	}
	
	/**
	 * @param vc
	 * @param bean
	 * @param clazz
	 * @throws InvalidInputException
	 */
	protected void validateShouldBeSuperTypes(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		if((bean.getVersionID() == J2EEVersionConstants.EJB_2_0_ID) || (bean.getVersionID() == J2EEVersionConstants.EJB_2_1_ID)){		
			JavaClass jclass = ((MessageDriven)bean).getMessagingType();
			if( jclass != null ){
				if (jclass.getQualifiedName().equals(ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGELISTENER)){
					super.validateShouldBeSuperTypes(vc,bean,clazz);
				}
			}
		}
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.model.validation.ejb.ejb20rules.impl.ATypeVRule#validateMethodsWhichMustExist(org.eclipse.jst.j2ee.model.validation.IValidationContext, org.eclipse.jst.j2ee.ejb.EnterpriseBean, org.eclipse.jem.java.JavaClass)
     */
    public void validateMethodsWhichMustExist(IValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException,
            ValidationCancelledException, ValidationException {
    	if((bean.getVersionID() == J2EEVersionConstants.EJB_2_0_ID) || (bean.getVersionID() == J2EEVersionConstants.EJB_2_1_ID)){		
			JavaClass jclass = ((MessageDriven)bean).getMessagingType();
			if( jclass != null ){
				if (jclass.getQualifiedName().equals(ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGELISTENER)){
				    super.validateMethodsWhichMustExist(vc, bean, clazz);
				}
			}
		}
      
    }
	public void validateOnMessageMethod(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// IWAD4396 = This method must be public. Read section 15.7.4 of the EJB 2.0 specification.
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2445, IValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4397 = This method must not be final. Read section 15.7.4 of the EJB 2.0 specification.
		if(method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2446, IValidationContext.WARNING, bean, clazz, method, this);		
			vc.addMessage(message);
		}
		
		// IWAD4398 = This method must not be static. Read section 15.7.4 of the EJB 2.0 specification.
		if(method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2447, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		// IWAD4399 = This method must return void. Read section 15.7.4 of the EJB 2.0 specification.
		// IWAD4400 = This method must not throw application exceptions. Read section 15.7.4 of the EJB 2.0 specification.
		if(!method.isVoid()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2505_onMessage, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		if(ValidationRuleUtility.throwsApplicationException(method, bean)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2501_onMessage, IValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		if(!followRemoteExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2503_onMessage, IValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
}
