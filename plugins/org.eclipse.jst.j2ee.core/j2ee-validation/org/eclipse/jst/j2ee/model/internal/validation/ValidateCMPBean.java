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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * This class checks entity bean classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * URL: http://java.sun.com/products/ejb/docs.html
 *
 *
 * All 9.2.X sections describe BMP requirements.
 * If a CMP requirement is different than these, then the differences are
 * documented in 9.4.X sections.
 */
public class ValidateCMPBean extends AValidateEntityBean implements IMessagePrefixEjb11Constants {
	private List _containerManagedFields = null;

	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private static final Object ID = IValidationRuleList.EJB11_CMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_CMP_HOME, IValidationRuleList.EJB11_CMP_REMOTE};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();

		MESSAGE_IDS.put(CHKJ2002, new String[]{CHKJ2002+BEXT, CHKJ2002+MEXT});
		MESSAGE_IDS.put(CHKJ2004, new String[]{CHKJ2004+BEXT, CHKJ2004+MEXT});
		MESSAGE_IDS.put(CHKJ2006, new String[]{CHKJ2006+EXT});
		MESSAGE_IDS.put(CHKJ2007, new String[]{CHKJ2007+EXT});
		
		MESSAGE_IDS.put(CHKJ2013, new String[]{CHKJ2013+EXT});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2028, new String[]{CHKJ2028+BEXT, CHKJ2028+MEXT});
		MESSAGE_IDS.put(CHKJ2029, new String[]{CHKJ2029+BEXT, CHKJ2029+MEXT});
		
		MESSAGE_IDS.put(CHKJ2032, new String[]{CHKJ2032+EXT});
		MESSAGE_IDS.put(CHKJ2033, new String[]{CHKJ2033+EXT});
		MESSAGE_IDS.put(CHKJ2034, new String[]{CHKJ2034+EXT});
		MESSAGE_IDS.put(CHKJ2035, new String[]{CHKJ2035+EXT});
		MESSAGE_IDS.put(CHKJ2036, new String[]{CHKJ2036+EXT});
		MESSAGE_IDS.put(CHKJ2037, new String[]{CHKJ2037+EXT});
		MESSAGE_IDS.put(CHKJ2038, new String[]{CHKJ2038+EXT});
		MESSAGE_IDS.put(CHKJ2039, new String[]{CHKJ2039+EXT});
		
		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103 + SPEC});
		MESSAGE_IDS.put(CHKJ2200, new String[]{CHKJ2200+ON_BASE+SPEC, CHKJ2200+ON_THIS+SPEC}); // CHKJ2200 is a special case. It's shared by all bean types.
		MESSAGE_IDS.put(CHKJ2201, new String[]{CHKJ2201+BEXT, CHKJ2201+MEXT});
		MESSAGE_IDS.put(CHKJ2202, new String[]{CHKJ2202+BEXT, CHKJ2202+MEXT});
		MESSAGE_IDS.put(CHKJ2203, new String[]{CHKJ2203+BEXT, CHKJ2203+MEXT});
		MESSAGE_IDS.put(CHKJ2207, new String[]{CHKJ2207+EXT, CHKJ2207+EXT}); // special case where the message id is the same regardless of whether or not the method is inherited
		
		MESSAGE_IDS.put(CHKJ2400_bus, new String[]{CHKJ2400_bus+BEXT, CHKJ2400_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbCreate, new String[]{CHKJ2400_ejbCreate+BEXT, CHKJ2400_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbFind, new String[]{CHKJ2400_ejbFind+BEXT, CHKJ2400_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbPostCreate, new String[]{CHKJ2400_ejbPostCreate+BEXT, CHKJ2400_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2406, new String[]{CHKJ2406+BEXT, CHKJ2406+MEXT});
		MESSAGE_IDS.put(CHKJ2408_bus, new String[]{CHKJ2408_bus+BEXT, CHKJ2408_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbCreate, new String[]{CHKJ2408_ejbCreate+BEXT, CHKJ2408_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbPostCreate, new String[]{CHKJ2408_ejbPostCreate+BEXT, CHKJ2408_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_bus, new String[]{CHKJ2409_bus+BEXT, CHKJ2409_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbCreate, new String[]{CHKJ2409_ejbCreate+BEXT, CHKJ2409_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbFind, new String[]{CHKJ2409_ejbFind+BEXT, CHKJ2409_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbPostCreate, new String[]{CHKJ2409_ejbPostCreate+BEXT, CHKJ2409_ejbPostCreate+MEXT});
		
		MESSAGE_IDS.put(CHKJ2410_bus, new String[]{CHKJ2410_bus+BEXT, CHKJ2410_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbCreate, new String[]{CHKJ2410_ejbCreate+BEXT, CHKJ2410_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbFind, new String[]{CHKJ2410_ejbFind+BEXT, CHKJ2410_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbPostCreate, new String[]{CHKJ2410_ejbPostCreate+BEXT, CHKJ2410_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});
		MESSAGE_IDS.put(CHKJ2418, new String[]{CHKJ2418+BEXT, CHKJ2418+MEXT});
		MESSAGE_IDS.put(CHKJ2420, new String[]{CHKJ2420+BEXT, CHKJ2420+MEXT});
		MESSAGE_IDS.put(CHKJ2432, new String[]{CHKJ2432+BEXT, CHKJ2432+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public void reset() {
		super.reset();
		_containerManagedFields = null;
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

	protected List getContainerManagedFields() {
		return _containerManagedFields;
	}
	
	protected boolean hasContainerManagedField() {
		return (getContainerManagedFields() != null && getContainerManagedFields().size() > 0);
	}
	
	public boolean isContainerManagedField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) {
		if (field == null) {
			return false;
		}
		return getContainerManagedFields() != null && getContainerManagedFields().contains(field.getName());
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.4.1
	 * 
	 * Return true if the field is the enterprise bean's home interface.
	 */
	protected boolean isContainerManagedHome_homeDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return false;
		}

		if (bean == null) {
			return false;
		}

		JavaClass homeIntf = bean.getHomeInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, homeIntf);

		return ValidationRuleUtility.isAssignableFrom(ValidationRuleUtility.getType(field), homeIntf);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.4.1
	 *
	 * Return true if the field is the enterprise bean's remote interface.
	 */
	protected boolean isContainerManagedRemote_remoteDep(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return false;
		}

		if (bean == null) {
			return false;
		}

		JavaClass remoteIntf = bean.getRemoteInterface();
		ValidationRuleUtility.isValidTypeHierarchy(bean, remoteIntf);

		return ValidationRuleUtility.isAssignableFrom(ValidationRuleUtility.getType(field), remoteIntf);
	}
	
	private List loadContainerManagedFields(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// The validation in this class, of the fields, is performed against the
		// container-managed fields, not the fields of this class directly.
		if (bean == null) {
			return Collections.EMPTY_LIST;
		}

		List fields = null;
		try {
			List cmpAttributes = ((ContainerManagedEntity) bean).getPersistentAttributes();
			if (cmpAttributes != null && !cmpAttributes.isEmpty()) {
				fields = new ArrayList(cmpAttributes.size());
				for (int i = 0; i < cmpAttributes.size(); i++)
					fields.add(((CMPAttribute) cmpAttributes.get(i)).getName());
			}
		}
		catch (Throwable exc) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
			fields = Collections.EMPTY_LIST;
		}
		return fields;
	}

	/**
	 * EJB 1.1 specification
	 * Section: 18.1.2
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		super.primValidate(vc, bean, clazz, field);

		vc.terminateIfCancelled();

		validateContainerManagedField(vc, bean, clazz, field);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.6
	 */
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP business method checks
		if (!isEjbRelationshipRoleMethod(vc, bean, clazz, method))
			super.validateBusinessMethod(vc, bean, clazz, method);

		// No specific CMP business method checks.
		// All of the points in 9.2.6 are common to both BMPs & CMPs.
	}
	
	protected void validateBusinessMethodNoRemoteException(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (!isEjbRelationshipRoleMethod(vc, bean, clazz, method))
			super.validateBusinessMethodNoRemoteException(vc, bean, clazz, method); // EJB 2.0 added "throws InvalidInputException above"
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.2
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// All of the above checks are performed by the parent.
		super.validateClass(vc, bean, clazz);

		validatePrimitivePrimaryKey(vc, bean, clazz); // if this class uses a primitive primary key, the type of the key must be the same as the type of the field
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.4.1
	 */
	protected void validateContainerManagedField(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (isContainerManagedField(vc, bean, clazz, field)) {
			if (field == null) {
				return;
			}

			vc.terminateIfCancelled();

			if (!ValidationRuleUtility.isPublic(field)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2203, IEJBValidationContext.ERROR, bean, clazz, field, this);
				vc.addMessage(message);
			}

			if (field.isTransient()) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2201, IEJBValidationContext.ERROR, bean, clazz, field, this);
				vc.addMessage(message);
			}

			// Check if it's a reference to the home or remote interface.
			validateContainerManagedField_dependent(vc, bean, clazz, field);
		}
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.4.1
	 *
	 * Return true if the field is either the enterprise bean's remote interface, 
	 * or the enterprise bean's home interface.
	 */
	protected void validateContainerManagedField_dependent(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (field == null) {
			return;
		}

		JavaHelpers fieldType = ValidationRuleUtility.getType(field);

		if (!ValidationRuleUtility.isSerializable(fieldType, bean)) {
			// If it were primitive, it would be serializable, so two checks were done in that one line of code.
			// 
			// Check if it's the enterprise bean's remote or home interface
			vc.terminateIfCancelled();
			if (!(isContainerManagedHome_homeDep(vc, bean, clazz, field)) || (isContainerManagedRemote_remoteDep(vc, bean, clazz, field))) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2202, IEJBValidationContext.WARNING, bean, clazz, field, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.4.6
	 */
	public void validateEjbFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			throw new InvalidInputException();
		}

		// Only BMPs implement finder methods.
		IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2004, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { clazz.getQualifiedName()}, this);
		vc.addMessage(message);
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 * Section: 9.2.4 and 9.4.2
	*/
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP ejbPostCreate method checks
		super.validateEjbPostCreateMethod(vc, bean, clazz, method);

		// No specific CMP ejbPostCreateMethod checks.
		// All of the points in 9.2.4 are common to both BMPs & CMPs.
	}
	
	protected void validatePrimitivePrimaryKey(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		ContainerManagedEntity cmp = (ContainerManagedEntity) bean; // bean is checked for null in AValidateEJB.validate() method, so don't need to check for it here.

		if (ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
			// primitive primary key

			// primary key that maps to a single field in the entity bean class
			// The field's type must be the primary key type.
			CMPAttribute keyAttribute = cmp.getPrimKeyField();
			Field keyField = (keyAttribute == null) ? null : keyAttribute.getField();
			JavaClass primaryKey = cmp.getPrimaryKey();
			if ((keyField == null) || !ValidationRuleUtility.isAssignableFrom((JavaHelpers)keyField.getEType(), primaryKey)) {
				String[] msgParm = { keyAttribute.getName(), primaryKey.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2207, IEJBValidationContext.ERROR, bean, clazz, keyField, msgParm, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Check that at least one field exists on the bean.
	 */
	public void verifyFieldExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		if (!hasContainerManagedField()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2032, IEJBValidationContext.WARNING, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		_containerManagedFields = loadContainerManagedFields(vc, (EnterpriseBean)targetParent, (JavaClass)target);
	}

}
