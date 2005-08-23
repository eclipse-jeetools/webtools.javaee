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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.internal.impl.LocalModelledPersistentAttributeFilter;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * @version 	1.0
 * @author
 */
public final class CMPBeanClassVRule extends AEntityBeanClassVRule implements IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_CMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_CMP_HOME, IValidationRuleList.EJB20_CMP_LOCALHOME, IValidationRuleList.EJB20_CMP_REMOTE, IValidationRuleList.EJB20_CMP_LOCAL, IValidationRuleList.EJB20_CMP_KEYCLASS};
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".cb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_ENTITYBEAN};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = new long[]{LOCAL_COMPONENT_INTERFACE, REMOTE_COMPONENT_INTERFACE};
	
	private final boolean SHOULD_BE_ABSTRACT = true;
	private final boolean SHOULD_BE_FINAL = false;
	private final boolean SHOULD_BE_PUBLIC = true;
	private final boolean SHOULD_VALIDATE_TRANSIENT_FIELD = false;
	
	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{CONSTRUCTOR_NOPARM, SETENTITYCONTEXT, UNSETENTITYCONTEXT_NOPARM, EJBACTIVATE_NOPARM, EJBPASSIVATE_NOPARM, EJBREMOVE_NOPARM, EJBLOAD_NOPARM, EJBSTORE_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{EJBFIND, FINALIZE_NOPARM};
	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, CONSTRUCTOR_NOPARM, CONSTRUCTOR, EJBCREATE, EJBPOSTCREATE, EJBHOME, FINALIZE_NOPARM, EJBFIND, ACCESSOR, EJBFINDBYPRIMARYKEY, SETENTITYCONTEXT, UNSETENTITYCONTEXT_NOPARM, EJBACTIVATE_NOPARM, EJBPASSIVATE_NOPARM, EJBREMOVE_NOPARM, EJBLOAD_NOPARM, EJBSTORE_NOPARM, EJBSELECT, FINALIZE_NOPARM, BUSINESS};

	static {
		MESSAGE_IDS = new HashMap();
		
		// Add these checks in the code later
		MESSAGE_IDS.put(CHKJ2481, new String[]{CHKJ2481+BEXT, CHKJ2481+MEXT});
		MESSAGE_IDS.put(CHKJ2482, new String[]{CHKJ2482+BEXT, CHKJ2482+MEXT});
		MESSAGE_IDS.put(CHKJ2483, new String[]{CHKJ2483+BEXT, CHKJ2483+MEXT});
		MESSAGE_IDS.put(CHKJ2484, new String[]{CHKJ2484+BEXT, CHKJ2484+MEXT});
		MESSAGE_IDS.put(CHKJ2489, new String[]{CHKJ2489+BEXT, CHKJ2489+MEXT});
		MESSAGE_IDS.put(CHKJ2205, new String[]{CHKJ2205+BEXT, CHKJ2205+MEXT});
		MESSAGE_IDS.put(CHKJ2206, new String[]{CHKJ2206+BEXT, CHKJ2206+MEXT});
		// end add later

		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});
		
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		
		MESSAGE_IDS.put(CHKJ2040, new String[]{CHKJ2040+EXT});
		
		MESSAGE_IDS.put(CHKJ2050_acc, new String[]{CHKJ2050_acc+EXT});
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
		MESSAGE_IDS.put(CHKJ2449, new String[]{CHKJ2449+BEXT, CHKJ2449+MEXT});

		MESSAGE_IDS.put(CHKJ2450, new String[]{CHKJ2450+BEXT, CHKJ2450+MEXT});
		MESSAGE_IDS.put(CHKJ2454, new String[]{CHKJ2454+BEXT, CHKJ2454+MEXT});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2478, new String[]{CHKJ2478+BEXT, CHKJ2478+MEXT});

		MESSAGE_IDS.put(CHKJ2480, new String[]{CHKJ2480+BEXT, CHKJ2480+MEXT});
		MESSAGE_IDS.put(CHKJ2485, new String[]{CHKJ2485+BEXT, CHKJ2485+MEXT});
		MESSAGE_IDS.put(CHKJ2486, new String[]{CHKJ2486+BEXT, CHKJ2486+MEXT});
		MESSAGE_IDS.put(CHKJ2487, new String[]{CHKJ2487+BEXT, CHKJ2487+MEXT});
		MESSAGE_IDS.put(CHKJ2488, new String[]{CHKJ2488+BEXT, CHKJ2488+MEXT});

		MESSAGE_IDS.put(CHKJ2492, new String[]{CHKJ2492+BEXT, CHKJ2492+MEXT});
		MESSAGE_IDS.put(CHKJ2493, new String[]{CHKJ2493+BEXT, CHKJ2493+MEXT});
		MESSAGE_IDS.put(CHKJ2494, new String[]{CHKJ2494+BEXT, CHKJ2494+MEXT});
		MESSAGE_IDS.put(CHKJ2496, new String[]{CHKJ2496+BEXT, CHKJ2496+MEXT});
		MESSAGE_IDS.put(CHKJ2497, new String[]{CHKJ2497+BEXT, CHKJ2497+MEXT});

		MESSAGE_IDS.put(CHKJ2500_ejbCreate, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_ejbFind, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_business, new String[]{CHKJ2500_ejbCreate+BEXT, CHKJ2500_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2500_ejbHome, new String[]{CHKJ2500_ejbHome+BEXT, CHKJ2500_ejbHome+MEXT});
		MESSAGE_IDS.put(CHKJ2502_finalize, new String[]{CHKJ2502_finalize+BEXT, CHKJ2502_finalize+MEXT});
		MESSAGE_IDS.put(CHKJ2502_ejbFind, new String[]{CHKJ2502_ejbFind+BEXT, CHKJ2502_ejbFind+MEXT});

		MESSAGE_IDS.put(CHKJ2503_bus, new String[]{CHKJ2503_bus+BEXT, CHKJ2503_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbCreate, new String[]{CHKJ2503_ejbCreate+BEXT, CHKJ2503_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbPostCreate, new String[]{CHKJ2503_ejbPostCreate+BEXT, CHKJ2503_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2503_ejbHome, new String[]{CHKJ2503_ejbHome+BEXT, CHKJ2503_ejbHome+MEXT});

		MESSAGE_IDS.put(CHKJ2505_ejbPostCreate, new String[]{CHKJ2505_ejbPostCreate+BEXT, CHKJ2505_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2505_ejbRemove, new String[]{CHKJ2505_ejbRemove+BEXT, CHKJ2505_ejbRemove+MEXT});
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
	
	public final JavaHelpers getEjbCreateReturnType(EnterpriseBean bean, Method method) {
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		if(ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
			try {
				return ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, cmp);
			}
			catch(InvalidInputException e) {
				// problems....
				// let this fall through to the default (getPrimaryKey())
			}
		}
		return ((Entity)bean).getPrimaryKey();
	}
	
	public final String getEjbCreateReturnTypeName(EnterpriseBean bean, Method method) {
		JavaHelpers retType = getEjbCreateReturnType(bean, method);
		if(retType == null) {
			return IEJBValidatorConstants.NULL_PRIMARY_KEY;
		}
		return retType.getJavaName();
	}
	
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validate(vc, bean, clazz);
		
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		
		// 10.3.1, container-managed persistent fields and container-managed relationship 
		// fields must *not* be defined in the entity bean class. 
		List cmrFields = cmp.getCMRFields();
		Iterator iterator = cmrFields.iterator();
		while(iterator.hasNext()) {
			CMRField cmrField = (CMRField)iterator.next();
			if((cmrField != null) && (!cmrField.isDerived())) {
				validateCmrField(vc, cmp, clazz, cmrField);
			}
		}
		
		List cmpFields = cmp.getFilteredFeatures(LocalModelledPersistentAttributeFilter.singleton());
		if(cmpFields != null && !cmpFields.isEmpty()) {
		iterator = cmpFields.iterator();
		while(iterator.hasNext()) {
			CMPAttribute cmpField = (CMPAttribute)iterator.next();
			if((cmpField != null) && (!cmpField.isDerived())) {
				validateCmpField(vc, cmp, clazz, cmpField);
			}
		  }
		}
	}
	
	public void validateEjbCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedList);
		
		if(!ValidationRuleUtility.throwsCreateException(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2497, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	public final void validateEjbSelectMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass parent, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// # ejbSelect<METHOD> checks
		// IWAD4154 = {0} must exist. Read section 10.5.2 of the EJB 2.0 specification.
		// IWAD4502 = This method must not exist on this class. Read section 10.5.5 of the EJB 2.0 specification.
		// IWAD4197 = An ejbSelect method must exist. Read section 10.6.7 of the EJB 2.0 specification.
		
		JavaHelpers returnType = method.getReturnType();
		JavaHelpers javaUtilCollection = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean);
		if(!ValidationRuleUtility.isAssignableFrom(returnType, bean.getLocalInterface()) &&
			!ValidationRuleUtility.isAssignableFrom(returnType, javaUtilCollection) ) {
			// IWAD4160 = Type {0} cannot be returned by a select method. Read section 10.5.7 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2486, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
		
		if(!ValidationRuleUtility.isPublic(method)) {
			// IWAD4198 = This method must be public. Read section 10.6.7 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2487, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
		
		if(!method.isAbstract()) {
			// IWAD4199 = This method must be abstract. Read section 10.6.7 of the EJB 2.0 specification.
			// IWAD4158 = {0} must be abstract. Read section 10.5.7 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2485, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
		
		if(!ValidationRuleUtility.throwsFinderException(bean, method)) {
			// IWAD4200 = This method must throw javax.ejb.FinderException. Read section 10.6.7 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2488, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
		
		if(!ValidationRuleUtility.followsObjectNotFoundExceptionRules(bean, method)) {
			// IWAD4285 = This method must not throw javax.ejb.ObjectNotFoundException. Read section 12.1.8.4 of the EJB 2.0 specification.
			// IWAD4168 = This method must not throw ObjectNotFoundException. Read section 10.5.8.4 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2478, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
		
		// Check method is associated with a query element in ejb-jar.xml.
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		if(!ValidationRuleUtility.isAssociatedWithQuery(cmp, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2496, IEJBValidationContext.INFO, bean, parent, method, this);
			vc.addMessage(message);
		}
	}
	
	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
	
	public void validateCmpField(IEJBValidationContext vc, ContainerManagedEntity cmp, JavaClass clazz, CMPAttribute attrib) throws ValidationCancelledException, InvalidInputException, ValidationException {
		String fieldName = attrib.getName();
		if((fieldName == null) || (fieldName.equals(""))) { //$NON-NLS-1$
			// let the EJBJarVRule report this
			return;
		}
		
		if(!Character.isLowerCase(fieldName.charAt(0))) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2480, IEJBValidationContext.INFO, cmp, clazz, attrib.getField(), this);
			vc.addMessage(message);
		}
		
		// Section 10.8.3
		if(!ValidationRuleUtility.isUnknownPrimaryKey(attrib)) {
			JavaClass ejbClass = cmp.getEjbClass();
			Method getMethod = ValidationRuleUtility.getMethodExtended(ejbClass, attrib.getGetterName(), new JavaHelpers[0], attrib.getType());
			if(getMethod == null) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2050_acc, IEJBValidationContext.ERROR, cmp, clazz, new String[]{attrib.getGetterName()}, this);
				vc.addMessage(message);
			}
			else {
				validateCMPAccessorMethod(vc, cmp, clazz, getMethod, attrib);
			}
			
			JavaHelpers[] setMethodParms = new JavaHelpers[]{attrib.getType()};
			Method setMethod = ValidationRuleUtility.getMethodExtended(ejbClass, attrib.getSetterName(), setMethodParms);
			if(setMethod == null) {
				String setterName = attrib.getSetterName() + "(" + ValidationRuleUtility.getParmsAsString(setMethodParms) + ")"; //$NON-NLS-1$  //$NON-NLS-2$
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2050_acc, IEJBValidationContext.ERROR, cmp, clazz, new String[]{setterName}, this);
				vc.addMessage(message);
			}
			else {
				validateCMPAccessorMethod(vc, cmp, clazz, setMethod, attrib);
			}
		}

		Field field = attrib.getField();
		if ((field != null) && (clazz.getFieldExtended(attrib.getName()) != null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2454, IEJBValidationContext.WARNING, cmp, clazz, field, new String[]{attrib.getName()}, this);
			vc.addMessage(message);
		}
			
	}
	
	public void validateCmrField(IEJBValidationContext vc, ContainerManagedEntity cmp, JavaClass clazz, CMRField cmrField) throws ValidationCancelledException, InvalidInputException, ValidationException {
		String fieldName = cmrField.getName();
		if((fieldName == null) || (fieldName.equals(""))) { //$NON-NLS-1$
			// let the EJBJarVRule report this
			return;
		}
		
		if(!Character.isLowerCase(fieldName.charAt(0))) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2480, IEJBValidationContext.INFO, cmp, clazz, cmrField.getField(), this);
			vc.addMessage(message);
		}
		
		JavaClass ejbClass = cmp.getEjbClass();
		JavaHelpers localType = ValidationRuleUtility.getCMRFieldType(vc, cmp, clazz, cmrField);
		if(localType == null) {
			// user made a mistake in ejb-jar.xml by trying to create a relation
			// to a CMP which doesn't have a local interface.
			// Don't report an error here; let the DD validation report the error.
			return;
		}
		Method getMethod = ValidationRuleUtility.getMethodExtended(ejbClass, cmrField.getGetterName(), new JavaHelpers[0], localType);
		if(getMethod == null) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2050_acc, IEJBValidationContext.ERROR, cmp, clazz, new String[]{cmrField.getGetterName()}, this);
			vc.addMessage(message);
		}
		else {
			validateCMRAccessorMethod(vc, cmp, clazz, getMethod, cmrField);
		}
		
		JavaHelpers[] setMethodParms = new JavaHelpers[]{localType};
		Method setMethod = ValidationRuleUtility.getMethodExtended(ejbClass, cmrField.getSetterName(), setMethodParms);
		if(setMethod == null) {
			String setterName = cmrField.getSetterName() + "(" + ValidationRuleUtility.getParmsAsString(setMethodParms) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2050_acc, IEJBValidationContext.ERROR, cmp, clazz, new String[]{setterName}, this);
			vc.addMessage(message);
		}
		else {
			validateCMRAccessorMethod(vc, cmp, clazz, setMethod, cmrField);
		}
		
		Field field = cmrField.getField();
		if ((field != null) && (clazz.getFieldExtended(cmrField.getName()) != null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2454, IEJBValidationContext.WARNING, cmp, clazz, field, new String[]{cmrField.getName()}, this);
			vc.addMessage(message);
		}
	}
	
	protected void validateCMRAccessorMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, CMRField field) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2449, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
				 
		if(!method.isAbstract()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2450, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	protected void validateCMPAccessorMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, CMPAttribute field) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2449, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
				 
		if(!method.isAbstract()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2450, IEJBValidationContext.WARNING, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			validateEjbCreateMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			validateEjbPostCreateMethod(vc, bean, clazz, method);
		}
		else if((methodType & EJBHOME) == EJBHOME) {
			validateEjbHomeMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else if((methodType & EJBREMOVE_NOPARM) == EJBREMOVE_NOPARM) {
			validateEjbRemoveMethod(vc, bean, clazz, method);
		}
		else if((methodType & ACCESSOR) == ACCESSOR) {
			// This has already been validated in the validate(vc, bean, clazz) method
		}
		else if((methodType & BUSINESS) == BUSINESS) {
			validateBusinessMethod(vc, bean, clazz, method, methodsExtendedLists);
		}
		else {
			// Method isn't supposed to be here. Let the validateMethodsWhichMustNotExist
			// take care of it.
		}
	}

	public String getMatchingHomeMethodName(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedList, this);
		
		if((methodType & EJBCREATE) == EJBCREATE) {
			return getMatchingEjbCreateMethodName(vc, method);
		}
		else if((methodType & EJBPOSTCREATE) == EJBPOSTCREATE) {
			return getMatchingEjbPostCreateMethodName(vc, method);
		}
		else if((methodType & EJBHOME) == EJBHOME) {
			return getMatchingEjbHomeMethodName(vc, method);
		}
		else if((methodType & EJBREMOVE_NOPARM) == EJBREMOVE_NOPARM) {
			return getNoMatchingMethodName(vc, method);
		}
		else if((methodType & ACCESSOR) == ACCESSOR) {
			// This has already been validated in the validate(vc, bean, clazz) method
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
	
}
