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
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks entity bean classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * The following paragraph is taken from
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * Copyright 1999 Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, U.S.A.
 * All rights reserved.
 *
 *
 * All 9.2.X sections describe BMP requirements. (And the bulk of those
 * are implemented in ValidateKeyClass.)
 * If a CMP requirement is different than these, then the differences are
 * documented in 9.4.X sections.
 *
 * 9.4.7 primary key type
 *   - The container must be able to manipulate the primary key type. Therefore, 
 *     the primary key type for an entity bean with container-managed persistence 
 *     must follow the rules in this subsection, in addition to those specified in 
 *     Subsection 9.2.9.
 *
 *     There are two ways to specify a primary key class for an entity bean with container-managed persistence:
 *        - Primary key that maps to a single field in the entity bean class.
 *        - Primary key that maps to multiple fields in the entity bean class.
 *     The second method is necessary for implementing compound keys, and the first method is convenient for
 *     single-field keys. Without the first method, simple types such as String would have to be wrapped in a
 *     user-defined class.
 * 
 *     9.4.7.1 Primary key that maps to a single field in the entity bean class
 *     The Bean Provider uses the primkey-field element of the deployment descriptor to specify the
 *     container-managed field of the entity bean class that contains the primary key. The field's type must be
 *     the primary key type.
 *
 *     9.4.7.2 Primary key that maps to multiple fields in the entity bean class
 *     The primary key class must be public, and must have a public constructor with no parameters.
 *     All fields in the primary key class must be declared as public.
 *     The names of the fields in the primary key class must be a subset of the names of the container-managed
 *     fields. (This allows the container to extract the primary key fields from an instance's container-managed
 *     fields, and vice versa.)
 *...
 */
public class ValidateCMPKey extends AValidateKeyClass implements IMessagePrefixEjb11Constants {
	private boolean hasAConstructor = false;
	private boolean hasDefaultConstructor = false;
	private Set _beanFieldNames = new HashSet();

	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type

	private static final Object ID = IValidationRuleList.EJB11_CMP_KEYCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_CMP_BEANCLASS};
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2001, new String[]{CHKJ2001+EXT});

		MESSAGE_IDS.put(CHKJ2019, new String[]{CHKJ2019+EXT});

		MESSAGE_IDS.put(CHKJ2020, new String[]{CHKJ2020+EXT});
		MESSAGE_IDS.put(CHKJ2021, new String[]{CHKJ2021+EXT});

		MESSAGE_IDS.put(CHKJ2205, new String[]{CHKJ2205+BEXT, CHKJ2205+MEXT});
		MESSAGE_IDS.put(CHKJ2206, new String[]{CHKJ2206+BEXT, CHKJ2206+MEXT}); // special case where the id is the same regardless of whether the method is inherited or not
		
//AValidateEJB method not used		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
//AValidateEJB method not used		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
//AValidateEJB method not used		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2829, new String[]{CHKJ2829 + SPEC});
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public void reset() {
		super.reset();
		_beanFieldNames.clear();
	}
	
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final Object[] getDependsOn() {
		return DEPENDS_ON;
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
	
	public final Object getId() {
		return ID;
	}

	/*
	 * 9.4.7.1 Primary key that maps to a single field in the entity bean class
	 *     The Bean Provider uses the primkey-field element of the deployment descriptor to specify the
	 *     container-managed field of the entity bean class that contains the primary key. The field's type must be
	 *     the primary key type.
	 *
	 * 9.4.7.2 Primary key that maps to multiple fields in the entity bean class
	 *     The primary key class must be public, and must have a public constructor with no parameters.
	 *     All fields in the primary key class must be declared as public.
	 *     The names of the fields in the primary key class must be a subset of the names of the container-managed
	 *     fields. (This allows the container to extract the primary key fields from an instance's container-managed
	 *     fields, and vice versa.)
	 */
	protected void buildFieldNameList(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Build up the list of field names to be used in the field validation.
		vc.terminateIfCancelled();

		ContainerManagedEntity cmp = (ContainerManagedEntity) bean;
		if (cmp == null) {
			// Let the class validation throw the exception
			return;
		}

		if (!ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
			List attributes = cmp.getPersistentAttributes();
			CMPAttribute attribute = null;
			Iterator iterator = attributes.iterator();
			while (iterator.hasNext()) {
				attribute = (CMPAttribute) iterator.next();
				try {
					// These are different fields than the ones validated by this
					// valImpl class, so don't need to worry about duplicate reflection
					// warnings logged against the same object.
					ValidationRuleUtility.isValidType(attribute.getType());
					_beanFieldNames.add(attribute.getName());
				}
				catch (InvalidInputException e) {
					//TODO (Dan) Change to use the attribute directly and not the field.
					reflectionWarning(vc, bean, clazz , attribute.getField(), e);
				}
			}
		}
	}
	
	/**
	 * This method actually does the validation.
	 *
	 * 9.4.7.2 Primary key that maps to multiple fields in the entity bean class
	 *     The primary key class must be public, and must have a public constructor with no parameters.
	 *     All fields in the primary key class must be declared as public.
	 *     The names of the fields in the primary key class must be a subset of the names of the container-managed
	 *     fields. (This allows the container to extract the primary key fields from an instance's container-managed
	 *     fields, and vice versa.)
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		// All fields in the primary key class must be declared as public.
		if (!ValidationRuleUtility.isPublic(field)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2205, IEJBValidationContext.WARNING, bean, clazz, field, this);
			vc.addMessage(message);
		}

		// The names of the fields in the primary key class must be a subset of the names of the container-managed
		// fields. (This allows the container to extract the primary key fields from an instance's container-managed
		// fields, and vice versa.)
		ContainerManagedEntity cmp = (ContainerManagedEntity) bean;
		// Don't need to check if cmp is null, because this method is called only by validateFields(),
		// and validateFields() won't call this method if the bean is null.

		if (!_beanFieldNames.contains(field.getName())) {
			JavaClass ejbClass = cmp.getEjbClass();
			ValidationRuleUtility.isValidType(ejbClass);
			String[] msgParm = { cmp.getName(), cmp.getEjbClass().getName()};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2206, IEJBValidationContext.WARNING, bean, clazz, field, msgParm, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * This method actually does the validation.
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		// super.primValidate(ejbMethod);

		//Nothing to do.
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		// Can't invoke an abstract method
		//super.validateExistence(ejbMethod);

		if (ejbMethod.isConstructor()) {
			// These booleans are used in the validateMethodExists() checks.
			hasAConstructor = true;
			if (ValidationRuleUtility.isPublic(ejbMethod) && (ejbMethod.listParametersWithoutReturn().length == 0)) {
				hasDefaultConstructor = true;
			}
		}
	}
	
	/**
	 * 9.4.7.2 Primary key that maps to multiple fields in the entity bean class
	 *     The primary key class must be public, and must have a public constructor with no parameters.
	 *     All fields in the primary key class must be declared as public.
	 *     The names of the fields in the primary key class must be a subset of the names of the container-managed
	 *     fields. (This allows the container to extract the primary key fields from an instance's container-managed
	 *     fields, and vice versa.)
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		super.validateClass(vc, bean, clazz);

		vc.terminateIfCancelled();

		// The primary key class must be public
		if (!clazz.isPublic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2020, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}

		buildFieldNameList(vc, bean, clazz);

		// Doesn't make sense to check for cmp key attributes if it's not a valid prim key field.
		// primary key must map to at least one field on the bean 
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		if(!ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
			// primary key must map to at least one field on the bean 
			// But if it's an unknown key, there's no point checking java.lang.Object
			List primKeyFields = cmp.getKeyAttributes();
			if ((primKeyFields == null) || (primKeyFields.size() == 0)) {
				JavaClass primaryKey = cmp.getPrimaryKey(); // don't need to check MOFHelper.isValidType(primaryKey), because it's already been called in the validateDeploymentDescriptor method
				String beanName = (cmp.getName() == null) ? "null" : cmp.getName(); //$NON-NLS-1$
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2829, IEJBValidationContext.ERROR, bean, primaryKey, new String[] { primaryKey.getName(), beanName }, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 *  9.4.7.2 Primary key that maps to multiple fields in the entity bean class
	 *     The primary key class must be public, and must have a public constructor with no parameters.
	 */
	public void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		super.validateMethodExists(vc, bean, clazz);

		// If the class has no constructors defined, Java inserts a public constructor with no arguments.
		// But if the class has at least one constructor defined, Java will not insert a constructor.
		if (!hasDefaultConstructor && hasAConstructor) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2021, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasAConstructor = false;
		hasDefaultConstructor = false;
	}
}
