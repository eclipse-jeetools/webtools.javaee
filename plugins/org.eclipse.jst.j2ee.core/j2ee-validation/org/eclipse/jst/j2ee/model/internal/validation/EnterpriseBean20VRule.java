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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.EnvEntryType;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.MessageLimitException;
import org.eclipse.wst.validation.core.ValidationException;

import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @version 	1.0
 * @author
 */
public class EnterpriseBean20VRule extends AValidationRule implements IMessagePrefixEjb20Constants {
	private List _securityRoles = null;
	private static final Map MESSAGE_IDS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_BMP_BEANCLASS, IValidationRuleList.EJB20_BMP_HOME, IValidationRuleList.EJB20_BMP_KEYCLASS, IValidationRuleList.EJB20_BMP_LOCAL, IValidationRuleList.EJB20_BMP_LOCALHOME, IValidationRuleList.EJB20_BMP_REMOTE, IValidationRuleList.EJB20_CMP_BEANCLASS, IValidationRuleList.EJB20_CMP_HOME, IValidationRuleList.EJB20_CMP_KEYCLASS, IValidationRuleList.EJB20_CMP_LOCAL, IValidationRuleList.EJB20_CMP_LOCALHOME, IValidationRuleList.EJB20_CMP_REMOTE, IValidationRuleList.EJB20_STATEFUL_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATEFUL_SESSION_HOME, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCAL, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCALHOME, IValidationRuleList.EJB20_STATEFUL_SESSION_REMOTE, IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATELESS_SESSION_HOME, IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL, IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME, IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE};
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2800_NAMED, new String[]{CHKJ2800_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2800_UNNAMED, new String[]{CHKJ2800_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2801, new String[]{CHKJ2801+SPEC});
		MESSAGE_IDS.put(CHKJ2802_NAMED, new String[]{CHKJ2802_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2802_UNNAMED, new String[]{CHKJ2802_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2803_NAMED, new String[]{CHKJ2803_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2803_UNNAMED, new String[]{CHKJ2803_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2804_NAMED, new String[]{CHKJ2804_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2804_UNNAMED, new String[]{CHKJ2804_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2805_NAMED, new String[]{CHKJ2805_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2805_UNNAMED, new String[]{CHKJ2805_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2806, new String[]{CHKJ2806+SPEC});
		MESSAGE_IDS.put(CHKJ2807, new String[]{CHKJ2807+SPEC});
		MESSAGE_IDS.put(CHKJ2808, new String[]{CHKJ2808+SPEC});
		MESSAGE_IDS.put(CHKJ2809, new String[]{CHKJ2809+SPEC});
		MESSAGE_IDS.put(CHKJ2810_NAMED, new String[]{CHKJ2810_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2810_UNNAMED, new String[]{CHKJ2810_UNNAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2813, new String[]{CHKJ2813+SPEC});
		MESSAGE_IDS.put(CHKJ2820, new String[]{CHKJ2820+SPEC});
		MESSAGE_IDS.put(CHKJ2822, new String[]{CHKJ2822+SPEC});
		MESSAGE_IDS.put(CHKJ2823, new String[]{CHKJ2823+SPEC});
		MESSAGE_IDS.put(CHKJ2824, new String[]{CHKJ2824+SPEC});
		MESSAGE_IDS.put(CHKJ2825, new String[]{CHKJ2825+SPEC});
		MESSAGE_IDS.put(CHKJ2826, new String[]{CHKJ2826+SPEC});
		MESSAGE_IDS.put(CHKJ2827, new String[]{CHKJ2827+SPEC});
		MESSAGE_IDS.put(CHKJ2828, new String[]{CHKJ2828+SPEC});
		MESSAGE_IDS.put(CHKJ2830, new String[]{CHKJ2830+SPEC});
		MESSAGE_IDS.put(CHKJ2831, new String[]{CHKJ2831+SPEC});
		MESSAGE_IDS.put(CHKJ2832, new String[]{CHKJ2832+SPEC});
		MESSAGE_IDS.put(CHKJ2833, new String[]{CHKJ2833+SPEC});
		MESSAGE_IDS.put(CHKJ2834, new String[]{CHKJ2834+SPEC});
		MESSAGE_IDS.put(CHKJ2835, new String[]{CHKJ2835+SPEC});
		MESSAGE_IDS.put(CHKJ2836, new String[]{CHKJ2836+SPEC});
		MESSAGE_IDS.put(CHKJ2837, new String[]{CHKJ2837+SPEC});
		MESSAGE_IDS.put(CHKJ2838, new String[]{CHKJ2838+SPEC});
		MESSAGE_IDS.put(CHKJ2839, new String[]{CHKJ2839+SPEC});
		MESSAGE_IDS.put(CHKJ2840, new String[]{CHKJ2840+SPEC});
		MESSAGE_IDS.put(CHKJ2841, new String[]{CHKJ2841+SPEC});
		MESSAGE_IDS.put(CHKJ2845, new String[]{CHKJ2845+SPEC});
		MESSAGE_IDS.put(CHKJ2846, new String[]{CHKJ2846+SPEC});
		MESSAGE_IDS.put(CHKJ2847, new String[]{CHKJ2847+SPEC});
		MESSAGE_IDS.put(CHKJ2852, new String[]{CHKJ2852});
		MESSAGE_IDS.put(CHKJ2854, new String[]{CHKJ2854+SPEC});
		MESSAGE_IDS.put(CHKJ2855, new String[]{CHKJ2855+SPEC});
		MESSAGE_IDS.put(CHKJ2856, new String[]{CHKJ2856+SPEC});
		MESSAGE_IDS.put(CHKJ2857, new String[]{CHKJ2857+SPEC});
		MESSAGE_IDS.put(CHKJ2858, new String[]{CHKJ2858+SPEC});
		MESSAGE_IDS.put(CHKJ2859, new String[]{CHKJ2859+SPEC});
		MESSAGE_IDS.put(CHKJ2860, new String[]{CHKJ2860+SPEC});
		MESSAGE_IDS.put(CHKJ2880, new String[]{"CHKJ2880.s"+SPEC, "CHKJ2880.b"+SPEC, "CHKJ2880.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2881, new String[]{"CHKJ2881.s"+SPEC, "CHKJ2881.b"+SPEC, "CHKJ2881.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2882, new String[]{"CHKJ2882.s"+SPEC, "CHKJ2882.b"+SPEC, "CHKJ2882.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2883, new String[]{"CHKJ2883.s"+SPEC, "CHKJ2883.b"+SPEC, "CHKJ2883.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2884, new String[]{"CHKJ2884.s"+SPEC, "CHKJ2884.b"+SPEC, "CHKJ2884.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2885, new String[]{"CHKJ2885.s"+SPEC, "CHKJ2885.b"+SPEC, "CHKJ2885.c"+SPEC}); // special case; need diff spec number for each type of bean //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public Object[] getDependsOn() {
		// EJBJar doesn't depend on anything else
		return DEPENDS_ON;
	}

	public Object getId() {
		return IValidationRuleList.EJB20_ENTERPRISEBEAN;
	}

	public Map getMessageIds() {
		return MESSAGE_IDS;
	}

	public Object getTarget(Object parent, Object clazz) {
		return parent; // The parent will be an EnterpriseBean.
	}

	/*
	 * @see IValidationRule#validate(IValidationContext, Object, Object)
		 */
	public void validate(IValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		Logger logger = vc.getMsgLogger();
		if(logger != null && logger.isLoggingLevel(Level.FINEST)) {
			LogEntry entry = vc.getLogEntry();
			entry.setSourceID("EnterpriseBean20VRule - validate"); //$NON-NLS-1$
			entry.setText(getClass().getName() + "::validate(" + targetParent + ", " + target); //$NON-NLS-1$ //$NON-NLS-2$
			logger.write(Level.FINEST, entry);
		}
		
		EJBJar ejbJar = null;
		if(targetParent instanceof EnterpriseBean) {
			// running as a dependent
			ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
		}
		else {
			ejbJar = (EJBJar)targetParent;
		}
		EnterpriseBean bean = (EnterpriseBean)target;
		
		validate(vc, ejbJar, bean);
	}
			
		
	public void validate(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) throws ValidationCancelledException, ValidationException {
		Logger logger = vc.getMsgLogger();
		try {
			// Check if the bean parts are reflected. Some rules can be validated
			// whether or not the bean is reflected.
			boolean reflected = validateBeanComponentsReflected(vc, ejbJar, bean);

			// These rules can be validated whether or not the bean is reflected.
			validateEJBNameElement(vc, ejbJar, bean);
			validateReentrantElement(vc, ejbJar, bean);
			validateSessionTypeElement(vc, ejbJar, bean);
			validateTransactionTypeElement(vc, ejbJar, bean);
			validatePersistenceTypeElement(vc, ejbJar, bean);
			validateEnvironmentEntries(vc, ejbJar, bean);

			// The rest of the rules cannot be validated because the bean isn't reflected.
			if (!reflected) {
				return;
			}
			
			validateLocalPairs(vc, ejbJar, bean);
			validateRemotePairs(vc, ejbJar, bean);
			validateLocalOrRemote(vc, ejbJar, bean);

			if (bean.isContainerManagedEntity()) {
				ContainerManagedEntity cmp = (ContainerManagedEntity) bean;
				if((cmp.getPrimKeyField() != null) && !cmp.getPrimKeyField().eIsProxy()) {
					// Don't validate the fields if it's neither a primitive primary key nor a compound primary key.
					// If the user was attempting a primitive primary key, but did a typo in ejb-jar.xml,
					// the user will see a lot of strange messages logged against the fields in the primitive primary key.
					validateCMPFieldElement(vc, ejbJar, cmp);
				}
				validateAbstractSchemaNameElement(vc, ejbJar, cmp);
				validateJavaIdentifier(vc, ejbJar, cmp);
			}

			validatePrimKeyClassElement(vc, ejbJar, bean);
			validateEJBRef(vc, ejbJar, bean);
		}
		catch (MessageLimitException e) {
			throw e;
		}
		catch(ValidationCancelledException e) {
			throw e;
		}
		/* unreachable catch block
		catch(ValidationException exc) {
			throw exc;
		}
		*/
		catch (Throwable exc) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2852, IValidationContext.WARNING, bean, new String[]{ArchiveConstants.EJBJAR_DD_SHORT_NAME, bean.getName()}, this);
			vc.addMessage(message);
			if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}

	/**
	 * If the bean components (home interface, remote interface, bean class, and primary
	 * key) can all be found and reflected, return true. Let the DDValidator
	 * report the error message against the bean if one of these types doesn't reflect.
	 */
	public boolean validateBeanComponentsReflected(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		// Don't need to check if the bean is null, because this method will
		// not be called if it is.
		boolean isValid = true;
		try {
			ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getEjbClass());
		}
		catch (InvalidInputException exc) {
			IMessage message = null;
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_EjbClass())) {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2802_NAMED, IValidationContext.ERROR, bean, new String[]{bean.getEjbClassName()}, this);
			}
			else {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2802_UNNAMED, IValidationContext.ERROR, bean, this);
			}
			vc.addMessage(message);
			isValid = false;
		}
		
		if(bean instanceof MessageDriven) {
			// don't need to check the rest
			return isValid;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getHomeInterface());
			}
		}
		catch (InvalidInputException exc) {
			IMessage message = null;
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface())) {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2803_NAMED, IValidationContext.ERROR, bean, new String[]{bean.getHomeInterfaceName()}, this);
			}
			else {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2803_UNNAMED, IValidationContext.ERROR, bean, this);
			}
			vc.addMessage(message);
			isValid = false;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getRemoteInterface());
			}
		}
		catch (InvalidInputException exc) {
			IMessage message = null;
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface())) {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2804_NAMED, IValidationContext.ERROR, bean, new String[]{bean.getRemoteInterfaceName()}, this);
			}
			else {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2804_UNNAMED, IValidationContext.ERROR, bean, this);
			}
			vc.addMessage(message);
			isValid = false;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getLocalHomeInterface());
			}
		}
		catch (InvalidInputException exc) {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface())) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2805_NAMED, IValidationContext.ERROR, bean, new String[]{bean.getLocalHomeInterfaceName()}, this);
				vc.addMessage(message);
				isValid = false;
			}
			else {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2805_UNNAMED, IValidationContext.ERROR, bean, this);
				vc.addMessage(message);
				isValid = false;
			}
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getLocalInterface());
			}
		}
		catch (InvalidInputException exc) {
			IMessage message = null;
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface())) {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2800_NAMED, IValidationContext.ERROR, bean, new String[]{bean.getLocalInterfaceName()}, this);
			}
			else {
				message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2800_UNNAMED, IValidationContext.ERROR, bean, this);
			}
			vc.addMessage(message);
			isValid = false;
		}

		if (bean.isEntity()) {
			JavaClass primaryKey = ((Entity) bean).getPrimaryKey();
			try {
				ValidationRuleUtility.isValidTypeHierarchy(bean, primaryKey);
			}
			catch (InvalidInputException exc) {
				IMessage message = null;
				if(((Entity)bean).eIsSet(EjbPackage.eINSTANCE.getEntity_PrimaryKey())) {
					message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2810_NAMED, IValidationContext.ERROR, bean, new String[]{((Entity)bean).getPrimaryKeyName()}, this);
				}
				else {
					message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2810_UNNAMED, IValidationContext.ERROR, bean, this);
				}
				vc.addMessage(message);
				isValid = false;
			}
		}

		return isValid;
	}

	public void validateCMPFieldElement(IValidationContext vc, EJBJar ejbJar, ContainerManagedEntity cmp) {
		// check syntax of tag is okay
		List fields = cmp.getPersistentAttributes();
		if ((fields == null) || (fields.size() == 0)) {
			// unlike EJB 1.1 CMPs, this is legal.
			return;
		}

		boolean mapsToMultipleFields = !ValidationRuleUtility.isPrimitivePrimaryKey(cmp);
		Iterator iterator = fields.iterator();
//		Field field = null;
		List fieldNames = new ArrayList(fields.size());
		while (iterator.hasNext()) {
			CMPAttribute attrib = (CMPAttribute) iterator.next();
			if (attrib == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("DDValidator.validateCMPFieldElement(EnterpriseBean)"); //$NON-NLS-1$
					entry.setText("CMPAttribute is null."); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}

			fieldNames.add(attrib.getName());

			JavaHelpers fieldType = attrib.getType();
			try {
				// If the field is not a valid type
				ValidationRuleUtility.isValidType(fieldType);
			}
			catch (InvalidInputException exc) {
				// field not identified
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2830, IValidationContext.WARNING, cmp, new String[] { attrib.getName()}, this);
				vc.addMessage(message);
				continue; // if you can't reflect it, then you can't validate it
			}
			
			try {
				if(!fieldType.isPrimitive() && 
				   !ValidationRuleUtility.isSerializable(fieldType, cmp)) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2855, IValidationContext.INFO, cmp, new String[] { attrib.getName()}, this);
					vc.addMessage(message);
				}
			}
			catch(InvalidInputException e) {
				String[] msgParm = (e.getJavaClass() == null) ? new String[]{} : new String[]{e.getJavaClass().getQualifiedName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2907, IValidationContext.WARNING, cmp, msgParm, this);
				vc.addMessage(message);
			}
		}

		// 9.4.7.2
		if (mapsToMultipleFields && !ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
			List primKeyFields = cmp.getPrimaryKey().getFieldsExtended();

			// (9.4.7.1 is validated in validatePrimKeyClassElement(bean)
			// check if the primary key class' fields exist in the container-managed fields
			iterator = primKeyFields.iterator();

			while (iterator.hasNext()) {
				Field keyField = (Field) iterator.next();
				if ((keyField.getName() != null) && (keyField.getName().equals(IMethodAndFieldConstants.FIELDNAME_SERIALVERSIONUID))) {
					// not a customer-entered field
					continue;
				}

				if (!fieldNames.contains(keyField.getName())) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2831, IValidationContext.WARNING, cmp, new String[] { keyField.getName()}, this);
					vc.addMessage(message);
				}
			}
		}
	}

	public void validateCMRFieldElement(IValidationContext vc, EJBJar ejbJar, ContainerManagedEntity cmp) {
		if (cmp == null)
			return; 
		switch(cmp.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID:
			case J2EEVersionConstants.EJB_1_1_ID:
				break;
			case J2EEVersionConstants.EJB_2_0_ID:
			case J2EEVersionConstants.EJB_2_1_ID: default:
				List fields = cmp.getCMRFields();
				Iterator iterator = fields.iterator();
				while(iterator.hasNext()) {
					CMRField field = (CMRField)iterator.next();
					String fieldName = field.getName();
					if((fieldName == null) || (fieldName.equals(""))) { //$NON-NLS-1$
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2857, IValidationContext.INFO, cmp, new String[] { field.getName()}, this);
						vc.addMessage(message);
						continue; // don't look for the get methods
					}
					if(!ValidationRuleUtility.isValidJavaIdentifier(fieldName)) {
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2857, IValidationContext.INFO, cmp, new String[] { field.getName()}, this);
						vc.addMessage(message);
					}
					
					try {
						JavaHelpers fieldType = field.getType();
						if(!ValidationRuleUtility.isAssignableFrom(fieldType, cmp.getLocalInterface()) &&
						    !ValidationRuleUtility.isAssignableFromCollection(fieldType, cmp) &&
						    !ValidationRuleUtility.isAssignableFromSet(fieldType, cmp)) {
							IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2856, IValidationContext.INFO, cmp, new String[] { field.getName()}, this);
							vc.addMessage(message);
						}
					}
					catch(InvalidInputException e) {
						String[] msgParm = (e.getJavaClass() == null) ? new String[]{} : new String[]{e.getJavaClass().getQualifiedName()};
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2907, IValidationContext.WARNING, cmp, msgParm, this);
						vc.addMessage(message);
					}
				}
				break;
		}
	}
	
	public void validateEJBNameElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean == null) {
			return;
		}

		String name = bean.getName();
		if ((name == null) || (name.equals(""))) { //$NON-NLS-1$
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2801, IValidationContext.ERROR, bean, this);
			vc.addMessage(message);
		}
		
		if(bean.isContainerManagedEntity() && bean.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {		
			if(!ValidationRuleUtility.isValidJavaIdentifier(name)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2813, IValidationContext.WARNING, bean, this);
				vc.addMessage(message);
			}
	
			// p. 466
			// "The name for an entity bean with cmp-version 2.x must conform
			// to the lexical rules for an NMTOKEN. The name for an entity bean with
			// cmp-version 2.x must not be a reserved literal in EJB QL.
			if(ValidationRuleUtility.isReservedWord(name)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2859, IValidationContext.INFO, bean, this);
				vc.addMessage(message);
			}
		}
	}
	
	public void validateAbstractSchemaNameElement(IValidationContext vc, EJBJar ejbJar, ContainerManagedEntity cmp) {
		if (cmp == null)
			return; 
		switch(cmp.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID:
			case J2EEVersionConstants.EJB_1_1_ID:
				break;
			case J2EEVersionConstants.EJB_2_0_ID:
			case J2EEVersionConstants.EJB_2_1_ID: default:

				String name = cmp.getAbstractSchemaName();
				if(!ValidationRuleUtility.isValidJavaIdentifier(name)) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2858, IValidationContext.WARNING, cmp, this);
					vc.addMessage(message);
				}
				
				// Check that the name is not a reserved word
				if(ValidationRuleUtility.isReservedWord(name)) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2860, IValidationContext.INFO, cmp, this);
					vc.addMessage(message);
				}
				break;			
		}
	}
	
	public void validateJavaIdentifier(IValidationContext vc, EJBJar ejbJar, ContainerManagedEntity cmp) {
		/*
		 * Don't duplicate the EJB QL validator's function.
		List queries = cmp.getQueries();
		Iterator iterator = queries.iterator();
		Query query = (Query)iterator.next();
		*/
	}
	

	/**
	 * Validate section 14.3 of the EJB 1.1 specification.
	 *
	 * 14.3 EJB References
	 *   14.3.1 Bean Provider's responsibilities
	 *     14.3.1.1 EJB reference programming interfaces
	 *     14.3.1.2 Declaration of EJB references in deployment descriptor
	 *   14.3.2 Application Assembler's responsibilities
	 *   14.3.3 Deployer's responsibility
	 *   14.3.4 Container Provider's responsibility
	 */
	public void validateEJBRef(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean == null) {
			return;
		}

		List ejbRefs = bean.getEjbRefs();
		if ((ejbRefs == null) || (ejbRefs.size() == 0)) {
			return;
		}

		Iterator iterator = ejbRefs.iterator();
		EjbRef ref = null;
		while (iterator.hasNext()) {
			ref = (EjbRef) iterator.next();
			if (ref == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("DDValidator.validateEJBRef(EnterpriseBean)"); //$NON-NLS-1$
					entry.setText("EjbRef is null in " + bean.getName()); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}
			// 14.3.1.1
			// Info mssg. "EJB spec recommends that all references to other enterprise beans be organized in the ejb subcontext of the bean's environment"
			EnterpriseBean namedEjb = null;
			if (ref.eIsSet(CommonPackage.eINSTANCE.getEjbRef_Link())) {
				// Load the EJB identified by the reference, if it exists.
				namedEjb = ejbJar.getEnterpiseBeanFromRef(ref);
				if (namedEjb != null) {
					// if the named EJB is null it could be in another EJBJar
					Integer ejbType = new Integer(ref.getType().getValue());
					boolean wrongType = false; // is the EJB, identified by ejb-link, of the type specified in the ejb-ref-type element?
					String type = (namedEjb instanceof Session) ? "Session" : "Entity"; //$NON-NLS-1$ //$NON-NLS-2$
					if (ejbType == null) {
						// Neither session nor entity?
						wrongType = true;
					}
					else if (ejbType.intValue() == EjbRefType.SESSION) {
						if (!(namedEjb instanceof Session)) {
							wrongType = true;
						}
					}
					else if (ejbType.intValue() == EjbRefType.ENTITY) {
						if (!(namedEjb instanceof Entity)) {
							wrongType = true;
						}
					}
					else {
						wrongType = true;
					}
					if (wrongType) {
						String[] parms = { namedEjb.getName(), type };
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2835, IValidationContext.INFO, bean, parms, this);
						vc.addMessage(message);
					}
				}
			}

			// 14.3.1.2, 14.3.2, 14.3.4
			// Check that ejb-ref-name, ejb-ref-type, home, and remote have been defined, and exist
			validateHomeRef(vc, ejbJar, ref, namedEjb);
			validateRemoteRef(vc, ejbJar, ref, namedEjb);

			// 14.3.1.1
			// Info mssg. "EJB spec recommends that all references to other enterprise beans be organized in the ejb subcontext of the bean's environment"
			if (ref.eIsSet(CommonPackage.eINSTANCE.getEjbRef_Name())) {
				// If ejb name isn't set, that error would have been caught by one of the validateRef methods
				// Don't duplicate that effort here.
				String ejbName = ref.getName();
				if (!ejbName.startsWith("ejb/")) { //$NON-NLS-1$
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2838, IValidationContext.INFO, bean, this);
					vc.addMessage(message);
				}
			}
		}
	}

	/**
	 * Validate section 14.3 of the EJB 1.1 specification.
	 *
	 * 14.2.1.2 Declaration of environment entries
	 */
	public void validateEnvironmentEntries(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean == null) {
			return;
		}

		List envEntries = bean.getEnvironmentProperties();
		if ((envEntries == null) || (envEntries.size() == 0)) {
			return;
		}

		EnvEntry envEntry = null;
		Iterator iterator = envEntries.iterator();
		DuplicatesTable envNames = new DuplicatesTable();
		while (iterator.hasNext()) {
			envEntry = (EnvEntry) iterator.next();
			if (envEntry == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("DDValidator.validateEnvironmentEntries(EnterpriseBean)"); //$NON-NLS-1$
					entry.setText("EjbEntry is null in " + bean.getName()); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}

			if (envEntry.getName() != null) {
				envNames.add(envEntry.getName());
			}
			else {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2839, IValidationContext.WARNING, bean, this);
				vc.addMessage(message);
			}

			if (envEntry.isSetType()) {
				// 14.2.1.2; type must be one of these types: String, Integer, Boolean, Double, Byte, Short, Long, and Float.
				int type = envEntry.getType().getValue();
				if (!((type == EnvEntryType.BOOLEAN) || (type == EnvEntryType.BYTE) || (type == EnvEntryType.DOUBLE) || (type == EnvEntryType.FLOAT) || (type == EnvEntryType.INTEGER) || (type == EnvEntryType.LONG) || (type == EnvEntryType.SHORT) || (type == EnvEntryType.STRING) || (type == EnvEntryType.CHARACTER))) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2840, IValidationContext.WARNING, bean, this);
					vc.addMessage(message);
				}
			}
			else {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2840, IValidationContext.WARNING, bean, this);
				vc.addMessage(message);
			}
		}

		if (envNames.containsDuplicates()) {
			Iterator dups = envNames.getDuplicates().iterator();
			while (dups.hasNext()) {
				String name = (String) dups.next();
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2841, IValidationContext.WARNING, bean, new String[] { name }, this);
				vc.addMessage(message);
			}
		}
	}

	/**
	 * If the metadata from the EjbRef is not valid, add a validation message.
	 *
	 * @parm EjbRef The <ejb-ref> element this method validates.
	 * @parm EnterpriseBean If the <ejb-ref> uses an <ejb-link>, this is the enterprise bean identified by that link. If the link doesn't exist, or if the user has identified a bean which doesn't exist, this parameter will be null.
	 */
	protected void validateHomeRef(IValidationContext vc, EJBJar ejbJar, EjbRef ref, EnterpriseBean namedEjb) {
		boolean validType = true;
		EnterpriseBean bean = (EnterpriseBean)ref.eContainer();
		if (ref.eIsSet(CommonPackage.eINSTANCE.getEjbRef_Home())) {
			try {
				String homeName = ref.getHome();
				JavaHelpers type = ValidationRuleUtility.getType(homeName, bean);
				// Check that the home specified in the <home> element of the <ejb-ref> element
				// exists.
				ValidationRuleUtility.isValidType(type);

				if (namedEjb != null) {
					// Check that the home specified in the <home> element
					// is assignable to the home used by the named EJB in the
					// <ejb-link> element.
					JavaClass intfType = namedEjb.getHomeInterface();
					ValidationRuleUtility.isValidType(intfType);

					// Make sure that the identified home/remote interface in the ejb-ref
					// is of the same type as the one used by the bean.
					if (!ValidationRuleUtility.isAssignableFrom(type, intfType)) {
						String[] parms = { namedEjb.getName(), intfType.getName()};
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2836, IValidationContext.INFO, bean, parms, this);
						vc.addMessage(message);
					}
				}
			}
			catch (InvalidInputException exc) {
				validType = false;
			}
		}
		else {
			validType = false;
		}

		if (!validType) {
			String[] parms = { ref.getHome()};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2832, IValidationContext.INFO, bean, parms, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * If the bean provides a local view, both parts of the view must exist.
	 * If this method is being called, then all parts of the bean are reflected.
	 */
	protected void validateLocalPairs(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if(bean instanceof MessageDriven) {
			// Message driven beans don't use local or remote views
			return;
		}
		
		JavaClass local = bean.getLocalInterface();
		JavaClass localHome = bean.getLocalHomeInterface();
		if((local != null ) && (localHome == null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2880, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}
		else if((local == null) && (localHome != null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2882, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}
		// otherwise either both are set or both are unset, and that's fine.
	}

	/**
	 * If the bean provides a remote view, both parts of the view must exist.
	 * If this method is being called, then all parts of the bean are reflected.
	 */
	protected void validateRemotePairs(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if(bean instanceof MessageDriven) {
			// Message driven beans don't use local or remote views
			return;
		}
		
		JavaClass remote = bean.getRemoteInterface();
		JavaClass home = bean.getHomeInterface();
		if((remote != null) && (home == null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2881, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}
		else if((remote == null) && (home != null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2883, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}
		// otherwise either both are set or both are unset, and that's fine.
	}

	/**
	 * The bean must provide either a local view, or a remote view, or both.
	 */
	protected void validateLocalOrRemote(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if(bean instanceof MessageDriven) {
			// Message driven beans don't use local or remote views
			return;
		}
		
		JavaClass local = bean.getLocalInterface();
		JavaClass localHome = bean.getLocalHomeInterface();
		JavaClass remote = bean.getRemoteInterface();
		JavaClass remoteHome = bean.getHomeInterface();

		if((localHome == null) && (remoteHome == null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2884, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}

		if((local == null) && (remote == null)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2885, IValidationContext.WARNING, bean, this);
			vc.addMessage(message);
		}
	}

	public void validatePersistenceTypeElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		// Attempt in vain to validate the persistence type element here, because 
		// if it's not specified, there's a syntax error, and the 
		// validateDeploymentDescriptor(IReporter, IHelper) would have been 
		// called instead.
		if (bean.isEntity()) {
			// check syntax
			Entity entityBean = (Entity) bean;
			if (!(entityBean.isContainerManagedEntity() || entityBean.isBeanManagedEntity())) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2809, IValidationContext.ERROR, bean, this);
				vc.addMessage(message);
			}
		}
	}

	/**
	 * If the primary key class reflected properly, return true. Else, return false.
	 */
	public void validatePrimKeyClassElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean.isEntity()) {
			// check syntax
			if (bean instanceof ContainerManagedEntity) {
				ContainerManagedEntity cmp = (ContainerManagedEntity) bean;
				CMPAttribute attr = cmp.getPrimKeyField();
				if((attr != null) && !attr.eIsProxy() && !isValidPrimKeyField(cmp,attr))  {
					// 9.4.7.1
					// user has specified both a prim-key-class and a primkey-field
					// can't use the CMPAttribute's field's name, because the primitive primary key returned is null
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2828, IValidationContext.ERROR, bean, this);
					vc.addMessage(message);
				}
			}
		}
	}
	
	/**
	 * If the prim key is specified in the xml, answer whether it is the same as the derived primaryKeyAttribute
	 */
	public boolean isValidPrimKeyField(ContainerManagedEntity cmp, CMPAttribute attr) {
		return cmp.getPrimaryKeyName().equals(attr.getType().getJavaName()); 
	}

	public void validateReentrantElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean.isEntity()) {
			// check syntax
			Entity entity = (Entity) bean;
			if (!entity.eIsSet(EjbPackage.eINSTANCE.getEntity_Reentrant())) {
				// Can only test if the reentrant attribute is set, because the model defaults it
				// to some boolean value if it isn't set.
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2806, IValidationContext.ERROR, bean, this);
				vc.addMessage(message);
			}
		}
	}

	/**
	 * If the metadata from the EjbRef is not valid, add a validation message.
	 *
	 * @parm EjbRef The ref whose home this method checks.
	 * @parm EnterpriseBean If the EjbRef uses an <ejb-link> element, this is the enterprise bean identified by that element. It may be null, if the user has made a mistake.
	 */
	protected void validateRemoteRef(IValidationContext vc, EJBJar ejbJar, EjbRef ref, EnterpriseBean namedEjb) {
		boolean validType = true;
		EnterpriseBean bean = (EnterpriseBean)ref.eContainer();
		if (ref.eIsSet(CommonPackage.eINSTANCE.getEjbRef_Remote())) {
			try {
				String remoteName = ref.getRemote();
				JavaHelpers type = ValidationRuleUtility.getType(remoteName, bean);
				// Check that the home specified in the <home> element of the <ejb-ref> element
				// exists.
				ValidationRuleUtility.isValidType(type);

				if (namedEjb != null) {
					// Check that the home specified in the <home> element
					// is assignable to the home used by the named EJB in the
					// <ejb-link> element.
					JavaClass intfType = namedEjb.getRemoteInterface();
					ValidationRuleUtility.isValidType(intfType);

					// Make sure that the identified home/remote interface in the ejb-ref
					// is of the same type as the one used by the bean.
					if (!ValidationRuleUtility.isAssignableFrom(type, intfType)) {
						String[] parms = { namedEjb.getName(), intfType.getName()};
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2837, IValidationContext.INFO, bean, parms, this);
						vc.addMessage(message);
					}
				}
			}
			catch (InvalidInputException exc) {
				validType = false;
			}
		}
		else {
			validType = false;
		}

		if (!validType) {
			String[] parms = { ref.getRemote()};
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2833, IValidationContext.INFO, bean, parms, this);
			vc.addMessage(message);
		}
	}

	/**
	 * 15.2.5.3 Declaration of security roles referenced from the bean's code
	 * The Bean Provider is responsible for declaring in the security-role-ref 
	 * elements of the deployment descriptor all the security role names used 
	 * in the enterprise bean code. Declaring the security roles references in
	 * the code allows the Application Assembler or Deployer to link the names 
	 * of the security roles used in the code to the security roles defined for 
	 * an assembled application through the security-role elements.
	 * The Bean Provider must declare each security role referenced in the code
	 * using the security-role-ref element as follows:
	 *      Declare the name of the security role using the role-name element. 
	 * The name must be the security role name that is used as a parameter to 
	 * the isCallerInRole(String role-Name) method.
	 *      Optional: Provide a description of the security role in the 
	 * description element. A security role reference, including the name defined 
	 * by the role-name element, is scoped to the session or entity bean element 
	 * whose declaration contains the security-role-ref element. The following 
	 * example illustrates how an enterprise bean's references to security roles 
	 * are declared in the deployment descriptor.
	 *    ...
	 *    <enterprise-beans>
	 *       ...
	 *       <entity>
	 *          <ejb-name>AardvarkPayroll</ejb-name>
	 *          <ejb-class>com.aardvark.payroll.PayrollBean</ejb-class>
	 *          ...
	 *          <security-role-ref>
	 *             <description>
	 *                 This security role should be assigned to the
	 *                 employees of the payroll department who are
	 *                 allowed to update employees' salaries.
	 *             </description>
	 *             <role-name>payroll</role-name>
	 *          </security-role-ref>
	 *          ...
	 *       </entity>
	 *       ...
	 *    </enterprise-beans>
	 *    ...
	 *
	 * The deployment descriptor above indicates that the enterprise bean 
	 * AardvarkPayroll makes the security check using isCallerInRole("payroll")
	 * in its business method.
	 *
	 *
	 * 15.3.3 Linking security role references to security roles
	 * If the Application Assembler defines the security-role elements in the 
	 * deployment descriptor, he or she is also responsible for linking all the 
	 * security role references declared in the security-role-ref elements to the 
	 * security roles defined in the security-role elements. The Application 
	 * Assembler links each security role reference to a security role using the 
	 * role-link element. The value of the role-link element must be the name of 
	 * one of the security roles defined in a security-role element.
	 * A role-link element must be used even if the value of role-name is the 
	 * same as the value of the role-link reference.
	 * The following deployment descriptor example shows how to link the security 
	 * role reference named payroll to the security role named payroll-department.
	 *    ...
	 *    <enterprise-beans>
	 *       ...
	 *       <entity>
	 *          <ejb-name>AardvarkPayroll</ejb-name>
	 *          <ejb-class>com.aardvark.payroll.PayrollBean</ejb-class>
	 *          ...
	 *          <security-role-ref>
	 *             <description>
	 *                This role should be assigned to the
	 *                employees of the payroll department.
	 *                Members of this role have access to
	 *                anyone's payroll record.
	 *
	 *                The role has been linked to the
	 *                payroll-department role.
	 *             </description>
	 *             <role-name>payroll</role-name>
	 *             <role-link>payroll-department</role-link>
	 *          </security-role-ref>
	 *          ...
	 *       </entity>
	 *       ...
	 *    </enterprise-beans>
	 *    ...
	 *
	 */
	public void validateSecurityRoleRefElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean == null) {
			return;
		}

		/**
		 * Need to build up a list of duplicate role names, but the validation message
		 * needs to be registered against the duplicate SecurityRoleRef instance.
		 * (Without the instance, we cannot get line numbers.)
		 *
		 * This class wrappers the SecurityRoleRef instance so that the wrapper's
		 * implemention of equals compares the names, but the validation message will
		 * still be able to get the ref from the duplicate name.
		 */
		class RoleRefWrapper {
			private SecurityRoleRef _ref = null;

			public RoleRefWrapper(SecurityRoleRef ref) {
				_ref = ref;
			}

			public boolean equals(Object o) {
				if (o instanceof RoleRefWrapper) {
					RoleRefWrapper other = (RoleRefWrapper) o;
					return _ref.getName().equals(other.getRoleRef().getName());
				}
				return false;
			}

			public SecurityRoleRef getRoleRef() {
				return _ref;
			}
		}

		boolean areSRolesDefined = ((_securityRoles != null) && (_securityRoles.size() > 0));

		List securityRoleRefs = bean.getSecurityRoleRefs();
		if ((securityRoleRefs != null) && (securityRoleRefs.size() != 0)) {
			// Check that each security role ref refers to a security role,
			// if security roles are defined in the assembly descriptor,
			// and that each referenced security role exists.
			DuplicatesTable roleRefNames = new DuplicatesTable();
			for (int i = 0; i < securityRoleRefs.size(); i++) {
				SecurityRoleRef ref = (SecurityRoleRef) securityRoleRefs.get(i);

				// Check that the role name is set (15.2.5.3)
				String roleName = ref.getName();
				if ((roleName == null) || (roleName.equals(""))) { //$NON-NLS-1$
					roleName = ""; //$NON-NLS-1$
					String beanName = (bean.getName() == null) ? "" : bean.getName(); //$NON-NLS-1$
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2822, IValidationContext.WARNING, ref, new String[] { beanName }, this);
					vc.addMessage(message);
				}
				else {
					// Build up the list of names, to check for duplicates
					roleRefNames.add(new RoleRefWrapper(ref));
				}

				// Check that the role link is set (15.3.3)
				String roleLink = ref.getLink();
				boolean isLinkDefined = ((ref.eIsSet(CommonPackage.eINSTANCE.getSecurityRoleRef_Link())) && (roleLink != null) && (!roleLink.equals(""))); //$NON-NLS-1$

				if ((areSRolesDefined) && (!isLinkDefined)) {
					// must have role link defined (15.3.3)
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2823, IValidationContext.WARNING, ref, this);
					vc.addMessage(message);
				}
				else if ((!areSRolesDefined) && (isLinkDefined)) {
					// must not have role link defined (15.2.5.3)
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2827, IValidationContext.WARNING, ref, this);
					vc.addMessage(message);
				}
				else if (areSRolesDefined && isLinkDefined) {
					// check that the role listed in the link exists. (15.3.3)
					Iterator iterator = _securityRoles.iterator();
					boolean roleExists = false;
					while(iterator.hasNext()) {
						SecurityRole role = (SecurityRole)iterator.next();
						if(role.getRoleName().equals(roleLink)) {
							roleExists = true;
							break;
						}
					}
					if (!roleExists) {
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2824, IValidationContext.WARNING, ref, new String[] { roleName }, this);
						vc.addMessage(message);
					}
				}
			}

			// Check for duplicates
			// Check that there are no duplicate role-names. (15.3.1)
			if (roleRefNames.containsDuplicates()) {
				List duplicates = roleRefNames.getDuplicates();
				Iterator iterator = duplicates.iterator();
				while (iterator.hasNext()) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2820, IValidationContext.WARNING, ((RoleRefWrapper) iterator.next()).getRoleRef(), this);
					vc.addMessage(message);
				}
			}
			roleRefNames.clear();
		}
	}

	/**
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
	public void validateSessionTypeElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean.isSession()) {
			Session session = (Session) bean;

			// check syntax
			boolean isValidSess = ValidationRuleUtility.isValidSessionTypeElement(session);
			if (!isValidSess) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2807, IValidationContext.ERROR, session, this);
				vc.addMessage(message);
			}

		}
	}
	public void validateTransactionTypeElement(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		if (bean.isSession()) {
			// check syntax
			Session sessionBean = (Session) bean;
			if (!ValidationRuleUtility.isValidTransactionTypeElement(sessionBean)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2808, IValidationContext.ERROR, bean, this);
				vc.addMessage(message);
			}
		}
	}
	
	/*
	 * @see IValidationRule#reset()
	 */
	public void reset() {
		super.reset();
		_securityRoles = null;
	}

	/*
	 * @see IValidationRule#preValidate(IValidationContext, Object, Object)
	 */
	public void preValidate(IValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);

		EJBJar ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
		if(ejbJar == null) {
			return;
		}
		
		if(ejbJar.getAssemblyDescriptor() == null) {
			return;
		}
		_securityRoles = ejbJar.getAssemblyDescriptor().getSecurityRoles();
	}

	/**
	 * Need to build up a list of duplicate EJB names, but the validation message
	 * needs to be registered against the duplicate EnterpriseBean instance.
	 * (Without the instance, we cannot get line numbers.)
	 *
	 * This class wrappers the EnterpriseBean instance so that the wrapper's
	 * implemention of equals compares the names, but the validation message will
	 * still be able to get the ref from the duplicate name.
	 */
	class EjbNameWrapper {
		private EnterpriseBean _bean = null;

		public EjbNameWrapper(EnterpriseBean bean) {
			_bean = bean;;
		}

		public boolean equals(Object o) {
			if (o instanceof EnterpriseBean) {
				EnterpriseBean other = (EnterpriseBean) o;
				return _bean.getName().equals(other.getName());
			}
			return false;
		}

		public EnterpriseBean getBean() {
			return _bean;
		}
	}

	class ASNameWrapper {
		private ContainerManagedEntity _bean = null;

		public ASNameWrapper(ContainerManagedEntity bean) {
			_bean = bean;
		}

		public boolean equals(Object o) {
			if (o instanceof ContainerManagedEntity) {
				ContainerManagedEntity other = (ContainerManagedEntity) o;
				return _bean.getAbstractSchemaName().equals(other.getAbstractSchemaName());
			}
			return false;
		}

		public EnterpriseBean getBean() {
			return _bean;
		}
	}
}
