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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class CMPKeyClassVRule extends AKeyClassVRule implements IEJBType, IRemoteType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_CMP_KEYCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_CMP_BEANCLASS};
	private static final Map MESSAGE_IDS;
	
	private static final String MSSGID = ".kc"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private final long[] SUPERTYPES = null;
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;
	
	private final long[] METHODS_WHICH_MUST_EXIST = new long[]{HASHCODE_NOPARM, EQUALS, CONSTRUCTOR_NOPARM};
	private final long[] METHODS_WHICH_MUST_NOT_EXIST = null;

	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, HASHCODE_NOPARM, EQUALS, CONSTRUCTOR_NOPARM, CONSTRUCTOR};

	private Set _keyFields = null;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2050_constr, new String[]{CHKJ2050_constr+EXT});
		MESSAGE_IDS.put(CHKJ2050_hashCode, new String[]{CHKJ2050_hashCode+EXT});
		MESSAGE_IDS.put(CHKJ2050_equals, new String[]{CHKJ2050_equals+EXT});
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});

		MESSAGE_IDS.put(CHKJ2019, new String[]{CHKJ2019+EXT});

		MESSAGE_IDS.put(CHKJ2205, new String[]{CHKJ2205+BEXT, CHKJ2205+MEXT});
		MESSAGE_IDS.put(CHKJ2206, new String[]{CHKJ2206+BEXT, CHKJ2206+MEXT});

		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2829, new String[]{CHKJ2829+SPEC});
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

	public Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}
		
		ContainerManagedEntity cmp = (ContainerManagedEntity)parent;
		if(ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
			return null; // do not validate a primitive primary key
		}
		
		return cmp.getPrimaryKey();
	}
	

	/*
	 * @see IClassVRule#validate(IEJBValidationContext, EnterpriseBean, JavaClass, Method)
	 */
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// Nothing to do.
	}
	
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validate(vc, bean, clazz);

		// IWAD4251 = This class must be public. Read section 10.8.2 of the EJB 2.0 specification.
		if(!clazz.isPublic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2022, IEJBValidationContext.INFO, bean, clazz, this);
			vc.addMessage(message);
		}
		
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		if(!ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
			// primary key must map to at least one field on the bean 
			// But if it's an unknown key, there's no point checking java.lang.Object
			List primKeyFields = cmp.getKeyAttributes();
			if ((primKeyFields == null) || (primKeyFields.size() == 0)) {
				JavaClass primaryKey = cmp.getPrimaryKey(); // don't need to check ValidationRuleUtility.isValidType(primaryKey), because it's already been called in the validateDeploymentDescriptor method
				String beanName = (cmp.getName() == null) ? "null" : cmp.getName(); //$NON-NLS-1$
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2829, IEJBValidationContext.ERROR, cmp, primaryKey, new String[] { primaryKey.getName(), beanName }, this);
				vc.addMessage(message);
			}
		}
	}

	private Set getKeyFields(ContainerManagedEntity cmp) {
		if(_keyFields == null) {
			// Know that the traversal of the fields and methods is done sequentially.
			// i.e., that a class is validated according to one bean at at time.
			// Thus, we can cache the key field information to speed up subsequent calls.
			List fields = cmp.getKeyAttributes();
			Iterator iterator = fields.iterator();
			_keyFields = new HashSet();
			while(iterator.hasNext()) {
				CMPAttribute attrib = (CMPAttribute)iterator.next();
				_keyFields.add(attrib.getName());
			}
		}
		return _keyFields;
	}
	
	/*
	 * @see IClassVRule#validate(IEJBValidationContext, EnterpriseBean, JavaClass, Field)
	 */
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		long fieldType = MethodUtility.getUtility().getFieldTypeId(bean, clazz, field, this);
		if((fieldType & IMethodAndFieldConstants.SERIALVERSIONUID) == IMethodAndFieldConstants.SERIALVERSIONUID) {
			validateSerialVersionUID(vc, bean, clazz, field);
		}
		else {
			// IWAD4253 = This field must be public. Read section 10.8.1 of the EJB 2.0 specification.
			if(!ValidationRuleUtility.isPublic(field)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2205, IEJBValidationContext.WARNING, bean, clazz, field, this);
				vc.addMessage(message);
			}
			
			Set keyFields = getKeyFields((ContainerManagedEntity)bean);
			if(!keyFields.contains(field.getName())) {
				// IWAD4254 = This field is not a <cmp-field>. Read section 10.8.1 of the EJB 2.0 specification.
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2206, IEJBValidationContext.WARNING, bean, clazz, field, this);
				vc.addMessage(message);
			}
		}
	}
	
	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}
	
	/*
	 * @see IValidationRule#reset()
	 */
	public void reset() {
		super.reset();
		if(_keyFields != null) {
			_keyFields.clear();
			_keyFields = null; // in this case, clearing the Set isn't enough.
		}
	}
}
