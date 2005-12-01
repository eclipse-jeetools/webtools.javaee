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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.MultiplicityKind;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.MessageLimitException;

/**
 * @version 	1.0
 * @author
 */
public class EJBJar20VRule extends AValidationRule implements IMessagePrefixEjb20Constants {
	private static final Map MESSAGE_IDS;
	private DuplicatesTable _ejbName = null;
	private DuplicatesTable _asName = null;
	private DuplicatesTable _relationshipName = null;
	private DuplicatesTable _relationshipRoleName = null;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE, IValidationRuleList.EJB20_STATELESS_SESSION_HOME, IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL, IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME, IValidationRuleList.EJB20_STATEFUL_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATEFUL_SESSION_REMOTE, IValidationRuleList.EJB20_STATEFUL_SESSION_HOME, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCAL, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCALHOME, IValidationRuleList.EJB20_CMP_BEANCLASS, IValidationRuleList.EJB20_CMP_REMOTE, IValidationRuleList.EJB20_CMP_HOME, IValidationRuleList.EJB20_CMP_LOCAL, IValidationRuleList.EJB20_CMP_LOCALHOME, IValidationRuleList.EJB20_CMP_KEYCLASS, IValidationRuleList.EJB20_BMP_BEANCLASS, IValidationRuleList.EJB20_BMP_REMOTE, IValidationRuleList.EJB20_BMP_HOME, IValidationRuleList.EJB20_BMP_LOCAL, IValidationRuleList.EJB20_BMP_LOCALHOME, IValidationRuleList.EJB20_BMP_KEYCLASS, IValidationRuleList.EJB20_MESSAGE_BEANCLASS, IValidationRuleList.EJB20_EJBEXT};
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2800_NAMED, new String[]{CHKJ2800_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2800_UNNAMED, new String[]{CHKJ2800_UNNAMED+SPEC});
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
		MESSAGE_IDS.put(CHKJ2811_NAMED, new String[]{CHKJ2811_NAMED+SPEC});
		MESSAGE_IDS.put(CHKJ2814, new String[]{CHKJ2814+SPEC});
		MESSAGE_IDS.put(CHKJ2816, new String[]{CHKJ2816+SPEC});
		MESSAGE_IDS.put(CHKJ2817, new String[]{CHKJ2817+SPEC});
		MESSAGE_IDS.put(CHKJ2818, new String[]{CHKJ2818+SPEC});
		MESSAGE_IDS.put(CHKJ2820, new String[]{CHKJ2820+SPEC});
		MESSAGE_IDS.put(CHKJ2821, new String[]{CHKJ2821+SPEC});
		MESSAGE_IDS.put(CHKJ2822, new String[]{CHKJ2822+SPEC});
		MESSAGE_IDS.put(CHKJ2823, new String[]{CHKJ2823+SPEC});
		MESSAGE_IDS.put(CHKJ2824, new String[]{CHKJ2824+SPEC});
		MESSAGE_IDS.put(CHKJ2825, new String[]{CHKJ2825+SPEC});
		MESSAGE_IDS.put(CHKJ2826, new String[]{CHKJ2826+SPEC});
		MESSAGE_IDS.put(CHKJ2827, new String[]{CHKJ2827+SPEC});
		MESSAGE_IDS.put(CHKJ2828, new String[]{CHKJ2828+SPEC});
		MESSAGE_IDS.put(CHKJ2829, new String[]{CHKJ2829+SPEC});
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
		MESSAGE_IDS.put(CHKJ2841, new String[]{CHKJ2841+SPEC});
		MESSAGE_IDS.put(CHKJ2842, new String[]{CHKJ2842+SPEC});
		MESSAGE_IDS.put(CHKJ2843, new String[]{CHKJ2843+SPEC});
		MESSAGE_IDS.put(CHKJ2844, new String[]{CHKJ2844+SPEC});
		MESSAGE_IDS.put(CHKJ2845, new String[]{CHKJ2845+SPEC});
		MESSAGE_IDS.put(CHKJ2846, new String[]{CHKJ2846+SPEC});
		MESSAGE_IDS.put(CHKJ2847, new String[]{CHKJ2847+SPEC});
		MESSAGE_IDS.put(CHKJ2848, new String[]{CHKJ2848+SPEC});
		MESSAGE_IDS.put(CHKJ2850, new String[]{CHKJ2850+SPEC});
		MESSAGE_IDS.put(CHKJ2854, new String[]{CHKJ2854+SPEC});
		MESSAGE_IDS.put(CHKJ2855, new String[]{CHKJ2855+SPEC});
		MESSAGE_IDS.put(CHKJ2856, new String[]{CHKJ2856+SPEC});
		MESSAGE_IDS.put(CHKJ2857, new String[]{CHKJ2857+SPEC});
		MESSAGE_IDS.put(CHKJ2858, new String[]{CHKJ2858+SPEC});
		MESSAGE_IDS.put(CHKJ2859, new String[]{CHKJ2859+SPEC});
		MESSAGE_IDS.put(CHKJ2860, new String[]{CHKJ2860+SPEC});
		MESSAGE_IDS.put(CHKJ2866, new String[]{CHKJ2866+SPEC});
		MESSAGE_IDS.put(CHKJ2867, new String[]{CHKJ2867+SPEC});
		MESSAGE_IDS.put(CHKJ2868, new String[]{CHKJ2868+SPEC});
		MESSAGE_IDS.put(CHKJ2869, new String[]{CHKJ2869+SPEC});
		MESSAGE_IDS.put(CHKJ2871, new String[]{CHKJ2871+SPEC});
		MESSAGE_IDS.put(CHKJ2872, new String[]{CHKJ2872+SPEC});
		MESSAGE_IDS.put(CHKJ2873, new String[]{CHKJ2873+SPEC});
		MESSAGE_IDS.put(CHKJ2874, new String[]{CHKJ2874+SPEC});
		MESSAGE_IDS.put(CHKJ2875, new String[]{CHKJ2875 + SPEC});
		MESSAGE_IDS.put(CHKJ2895, new String[]{CHKJ2895+SPEC});
		MESSAGE_IDS.put(CHKJ2852, new String[]{CHKJ2852});
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public EJBJar20VRule() {
		_ejbName = new DuplicatesTable();
		_asName = new DuplicatesTable();
		_relationshipName = new DuplicatesTable();
		_relationshipRoleName = new DuplicatesTable();
	}
	
	public Object[] getDependsOn() {
		// EJBJar doesn't depend on anything else
		return DEPENDS_ON;
	}

	public Object getId() {
		return IValidationRuleList.EJB20_EJBJAR;
	}

	public Map getMessageIds() {
		return MESSAGE_IDS;
	}

	public Object getTarget(Object parent, Object clazz) {
		// Since this rule doesn't depend on another rule, this
		// method (which is called on a dependent rule) will never
		// be called.
		return null;
	}

	/*
	 * @see IValidationRule#reset()
	 */
	public void reset() {
		super.reset();
		_ejbName.clear();
		_asName.clear();
		_relationshipName.clear();
		_relationshipRoleName.clear();
	}

	protected void addInternalErrorMessage(IEJBValidationContext vc, Throwable exc) {
		IMessage mssg = vc.getMessage();
		mssg.setId(IEJBValidatorMessageConstants.CHKJ2900);
		vc.addMessage(mssg);

		if(exc != null) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	protected void logMissingRule(IEJBValidationContext vc, Object ruleId) {
		log(vc, ruleId + " = null"); //$NON-NLS-1$
		addInternalErrorMessage(vc, null);
	}
	
	protected void log(IEJBValidationContext vc, String message) {
		Logger logger = vc.getMsgLogger();
		if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
			logger.write(Level.SEVERE, message);
		}
	}
	
	/*
	 * @see IValidationRule#validate(IEJBValidationContext, Object, Object)
		 */
	public void validate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		Logger logger = vc.getMsgLogger();
		if(logger != null && logger.isLoggingLevel(Level.FINEST)) {
			LogEntry entry = vc.getLogEntry();
			entry.setSourceID("EJBJar20VRule - validate"); //$NON-NLS-1$
			entry.setText(getClass().getName() + "::validate(" + targetParent + ", " + target); //$NON-NLS-1$ //$NON-NLS-2$
			logger.write(Level.FINEST, entry);
		}
		
		EJBJar ejbJar = (EJBJar) target;
		List beans = ejbJar.getEnterpriseBeans();
		Iterator iterator = beans.iterator();
		EnterpriseBean bean = null;
		while (iterator.hasNext()) {
			try {
				bean = (EnterpriseBean) iterator.next();

				register(vc, ejbJar, bean);
	
				Object id = null;			
				switch(bean.getVersionID()) {
					case J2EEVersionConstants.EJB_1_0_ID:
					case J2EEVersionConstants.EJB_1_1_ID:
						id = IValidationRuleList.EJB11_ENTERPRISEBEAN;
						break;
					case J2EEVersionConstants.EJB_2_0_ID:
					case J2EEVersionConstants.EJB_2_1_ID: 
						id = IValidationRuleList.EJB20_ENTERPRISEBEAN;
						break; 
					default:
						// What version is it then?
						if(bean instanceof ContainerManagedEntity) {
							validateCMPVersionElement(vc, ejbJar, (ContainerManagedEntity)bean);
						}
						else {
							// log
							log(vc, "!bean.isVersion1_X() && !bean.isVersion2_X()"); //$NON-NLS-1$
						}
						continue;
				}				 
				
				IValidationRule vRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
				if(vRule == null) {
					logMissingRule(vc, id);
					continue;
				}
				try {
					vRule.preValidate(vc, ejbJar, bean);
					vRule.validate(vc, ejbJar, bean);
					vRule.postValidate(vc, ejbJar, bean);
				}
				catch(ValidationCancelledException exc) {
					// Clean up the messages which are on the task list? Or is it nicer to leave them behind?
					throw exc;
				}
				catch(MessageLimitException e) {
					throw e;
				}
				catch(ValidationException e) {
					throw e;
				}
				catch(Throwable exc) {
					addInternalErrorMessage(vc, exc);
				}
				finally {
					EJBValidationRuleFactory.getFactory().release(vRule);
				}
				
			}
			catch (MessageLimitException e) {
				throw e;
			}
			catch(ValidationCancelledException e) {
				throw e;
			}
			catch(ValidationException exc) {
				// something goes wrong, just proceed with the next bean
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2852, IEJBValidationContext.WARNING, bean, new String[]{J2EEConstants.EJBJAR_DD_SHORT_NAME, bean.getName()}, this);
				vc.addMessage(message);
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
			catch (Throwable exc) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2852, IEJBValidationContext.WARNING, bean, new String[]{J2EEConstants.EJBJAR_DD_SHORT_NAME, bean.getName()}, this);
				vc.addMessage(message);
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}

		// Since the assembly descriptor is not specific to a bean, validate it once, after all bean processing is complete.
		validateAssemblyDescriptorElement(vc, ejbJar);
		//TODO The Datasource validation will be done by the extensions
		validateUniqueEjbNames(vc, ejbJar);
		validateUniqueAbstractSchemaNames(vc, ejbJar);
		validateRelationships(vc, ejbJar);
		validateClientJAR(vc, ejbJar);
	}
	
	protected void register(IEJBValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		// To check if every bean name is unique, need to build a list
		_ejbName.add(new EjbNameWrapper(bean));
		
		if((bean instanceof ContainerManagedEntity) && bean.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
			// To check if every bean name is unique, need to build a list
			_asName.add(new ASNameWrapper((ContainerManagedEntity)bean));
		}
	}

	protected void validateCMPVersionElement(IEJBValidationContext vc, EJBJar ejbJar, ContainerManagedEntity cmp) {
		if(cmp.eIsSet(EjbPackage.eINSTANCE.getContainerManagedEntity_Version())) {
			String version = cmp.getVersion();
			if(! (ContainerManagedEntity.VERSION_1_X.equals(version) || ContainerManagedEntity.VERSION_2_X.equals(version))) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2872, IEJBValidationContext.ERROR, cmp, this);
				vc.addMessage(message);
			}			
		}
	}
	
		

	protected void validateAssemblyDescriptorElement(IEJBValidationContext vc, EJBJar ejbJar) {
		vc.terminateIfCancelled();

		// Validate the security roles, if they're defined in the assembly-descriptor.
		if (ejbJar == null) {
			// nothing to validate
			return;
		}

		/**
		 * Need to build up a list of duplicate role names, but the validation message
		 * needs to be registered against the duplicate SecurityRole instance.
		 * (Without the instance, we cannot get line numbers.)
		 *
		 * This class wrappers the SecurityRol instance so that the wrapper's
		 * implemention of equals compares the names, but the validation message will
		 * still be able to get the ref from the duplicate name.
		 */
		class RoleWrapper {
			private SecurityRole _role = null;

			public RoleWrapper(SecurityRole role) {
				_role = role;
			}

			public boolean equals(Object o) {
				if (o instanceof RoleWrapper) {
					RoleWrapper other = (RoleWrapper) o;
					return _role.getRoleName().equals(other.getRole().getRoleName());
				}
				return false;
			}

			public SecurityRole getRole() {
				return _role;
			}
		}

		AssemblyDescriptor assemblyDescriptor = ejbJar.getAssemblyDescriptor();
		if (assemblyDescriptor == null) {
			// nothing to validate
			return;
		}

		List roles = assemblyDescriptor.getSecurityRoles();
		if (roles != null) {
			DuplicatesTable roleNames = new DuplicatesTable();
			SecurityRole role = null;
			Iterator roleIt = roles.iterator();
			while (roleIt.hasNext()) {
				vc.terminateIfCancelled();
				// Check that the role-name element has been set
				role = (SecurityRole) roleIt.next();
				if ((role == null) || (!role.eIsSet(CommonPackage.eINSTANCE.getSecurityRole_RoleName())) || (role.getRoleName().equals(""))) { //$NON-NLS-1$
					// role-name not set
					if(role == null) {
						IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2825, IEJBValidationContext.WARNING, role, this);
						vc.addMessage(message);
					}
					else {
						IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2825, IEJBValidationContext.WARNING, assemblyDescriptor, this);
						vc.addMessage(message);
					}
				}
				else {
					// Build up hashtable to check for duplicate role-names.
					roleNames.add(new RoleWrapper(role));
				}
			}

			// Check that there are no duplicate role-names. (15.3.1)
			if (roleNames.containsDuplicates()) {
				List duplicates = roleNames.getDuplicates();
				Iterator iterator = duplicates.iterator();
				while (iterator.hasNext()) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2826, IEJBValidationContext.WARNING, ((RoleWrapper) iterator.next()).getRole(), this);
					vc.addMessage(message);
				}
			}
			roleNames.clear();
		}

		List methTrans = assemblyDescriptor.getMethodTransactions();
		MethodTransaction mt = null;
		Iterator iterator = methTrans.iterator();
		while (iterator.hasNext()) {
			vc.terminateIfCancelled();

			try {
				mt = (MethodTransaction) iterator.next();
			}
			catch (Throwable exc) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINER)) {
					logger.write(Level.FINER, exc);
				}
				mt = null;
			}

			if (mt == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("EJBJar20VRule.validateAssemblyDescriptorElement"); //$NON-NLS-1$
					entry.setText("mt is null"); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}

			boolean hasValidMethod = validateMethodElements(vc, ejbJar, mt.getMethodElements());
			if (!hasValidMethod) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2847, IEJBValidationContext.WARNING, mt, this);
				vc.addMessage(message);
			}
		}

		List methodPermissions = assemblyDescriptor.getMethodPermissions();
		iterator = methodPermissions.iterator();
		while (iterator.hasNext()) {
			MethodPermission mp = (MethodPermission) iterator.next();

			boolean hasValidMethod = validateMethodElements(vc, ejbJar, mp.getMethodElements());
			if (!hasValidMethod) {
				// 15.3.2, p. 229, a <method-permission> must have at least one method listed (and that method must be found)
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2846, IEJBValidationContext.WARNING, mp, this);
				vc.addMessage(message);
			}

			// at least one security-role must be defined
			List mproles = mp.getRoles();
			if (((mproles == null) || (mproles.size() == 0)) && (!mp.isSetUnchecked())) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2845, IEJBValidationContext.WARNING, mp, this);
				vc.addMessage(message);
			}
		}
	}

	protected void validateUniqueAbstractSchemaNames(IEJBValidationContext vc, EJBJar ejbJar) {
		List names = _asName.getDuplicates();
		if(names.size() == 0) {
			return;
		}
		
		Iterator iterator = names.iterator();
		while(iterator.hasNext()) {
			ASNameWrapper wrapper = (ASNameWrapper)iterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2854, IEJBValidationContext.ERROR, wrapper.getBean(), new String[]{wrapper.getBean().getAbstractSchemaName()}, this);
			vc.addMessage(message);
		}
	}
	
	protected void validateUniqueEjbNames(IEJBValidationContext vc, EJBJar ejbJar) {
		List names = _ejbName.getDuplicates();
		if(names.size() == 0) {
			return;
		}
		
		Iterator iterator = names.iterator();
		while(iterator.hasNext()) {
			EjbNameWrapper wrapper = (EjbNameWrapper)iterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2895, IEJBValidationContext.WARNING, wrapper.getBean(), new String[]{wrapper.getBean().getName()}, this);
			vc.addMessage(message);
		}
	}

	protected void validateRelationships(IEJBValidationContext vc, EJBJar ejbJar) {
		Relationships relationships = ejbJar.getRelationshipList();
		if(relationships == null) {
			return;
		}
		
		List ejbRelationList = relationships.getEjbRelations();
		Iterator iterator = ejbRelationList.iterator();
		while(iterator.hasNext()) {
			EJBRelation relation = (EJBRelation)iterator.next();
			
			_relationshipName.add(new EJBRelationNameWrapper(relation));
			
			boolean atLeastOneCmrFieldExists = false;
			List roles = relation.getRelationshipRoles();
			Iterator roleIterator = roles.iterator();
			while(roleIterator.hasNext()) {
				EJBRelationshipRole role = (EJBRelationshipRole)roleIterator.next();
				
				_relationshipRoleName.add(new EJBRelationshipRoleNameWrapper(role));

				boolean validMultiplicity = role.isSetMultiplicity();
				if(!validMultiplicity) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2818, IEJBValidationContext.INFO, role, this);
					vc.addMessage(message);
				}
	
				// 10.3.4.2 
				// The cascade-delete element can only be specified for an ejb-relationship-role
				// element contained in an ejb-relation element if the *other* ejb-relationship-role
				// element in the same ejb-relation element specifies a multiplicity of One.
				EJBRelationshipRole oppositeRole = role.getOpposite();
				if(role.isSetCascadeDelete()) {
					boolean isOtherMultiplicityOne = ((oppositeRole.isSetMultiplicity()) && (oppositeRole.getMultiplicity().getValue() == MultiplicityKind.ONE));
					if(!isOtherMultiplicityOne) {
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2848, IEJBValidationContext.INFO, role, this);
						vc.addMessage(message);
					}
				}
				
				CMRField cmrField = role.getCmrField();
				if(cmrField != null) {
					atLeastOneCmrFieldExists = true;
					if(cmrField.eIsSet(EjbPackage.eINSTANCE.getCMRField_CollectionType())) {
						String cmrFieldType = cmrField.getCollectionTypeName();
						if((!ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION.equals(cmrFieldType)) &&
							(!ITypeConstants.CLASSNAME_JAVA_UTIL_SET.equals(cmrFieldType))) {
							// CHKJ2868: The value of the <cmr-field-type> element must be either "java.util.Collection" or "java.util.Set" (EJB 2.0: 10.3.13, 22.5).
							IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2868, IEJBValidationContext.INFO, role, this);
							vc.addMessage(message);
						}
					}
				}
				
				RoleSource sourceRoleSource = role.getSource();
				if(sourceRoleSource == null) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2821, IEJBValidationContext.INFO, role, new String[]{role.getName()}, this);
					vc.addMessage(message);
					continue;
				}

				Entity sourceEntity = sourceRoleSource.getEntityBean();
				if(sourceEntity == null) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2821, IEJBValidationContext.INFO, role, new String[]{role.getName()}, this);
					vc.addMessage(message);
				}
				else if(sourceEntity.isContainerManagedEntity()) {
					// CHKJ2866: <cmr-field> {0} must be defined on {1} (EJB 2.0: 22.5).
					if(cmrField != null) {
						ContainerManagedEntity cmp = (ContainerManagedEntity)sourceEntity;
						List cmrFields = cmp.getCMRFields();
						boolean found_cmrField = false;
						if((cmrFields != null) && (cmrFields.size() > 0)) {
							found_cmrField = cmrFields.contains(cmrField);
						}
						
						if(!found_cmrField) {
							IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2866, IEJBValidationContext.INFO, role, new String[]{cmrField.getName(), sourceEntity.getName()}, this);
							vc.addMessage(message);
						}
					}
				}
				else {
					// CHKJ2871: <ejb-name> {0} must refer to a CMP bean (EJB 2.0: 10.3.2, 10.3.13).
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2871, IEJBValidationContext.INFO, role, new String[]{sourceEntity.getName()}, this);
					vc.addMessage(message);
				}
				
				RoleSource oppositeRoleSource = oppositeRole.getSource();
				if(oppositeRoleSource == null) {
					// Do not emit an error message - when we iterate to the role where
					// this is the source, this error will be detected.
					continue;
				}

				Entity targetEntity = oppositeRoleSource.getEntityBean();
				if(targetEntity == null) {
					// Do not emit an error message - when we iterate to the role where
					// this is the source, this error will be detected.
				}
				else if(targetEntity.getLocalInterface() == null) {
					// Does the target of this role have a local interface?
					// CHKJ2867: This bean can have only unidirectional relationships to other beans (EJB 2.0: 10.3.2).
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2867, IEJBValidationContext.INFO, oppositeRole, this);
					vc.addMessage(message);
				}
			}
			
			// Verify unique role names within the <ejb-relation>
			validateUniqueRelationshipRoleNames(vc, ejbJar);
			_relationshipRoleName.clear();
			
			if(!atLeastOneCmrFieldExists) {
				// CHKJ2869I: At least one <ejb-relationship-role> must define a <cmr-field> (EJB 2.0: 10.3.2, 10.3.13, 22.5).
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2869, IEJBValidationContext.INFO, relation, this);
				vc.addMessage(message);
			}
		}
		
		validateUniqueRelationNames(vc, ejbJar);
	}
	
	protected void validateUniqueRelationNames(IEJBValidationContext vc, EJBJar ejbJar) {
		List names = _relationshipName.getDuplicates();
		if(names.size() == 0) {
			return;
		}
		
		Iterator iterator = names.iterator();
		while(iterator.hasNext()) {
			EJBRelationNameWrapper wrapper = (EJBRelationNameWrapper)iterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2816, IEJBValidationContext.INFO, wrapper.getRelation(), new String[]{wrapper.getRelation().getName()}, this);
			vc.addMessage(message);
		}
	}
	
	protected void validateUniqueRelationshipRoleNames(IEJBValidationContext vc, EJBJar ejbJar) {
		List names = _relationshipRoleName.getDuplicates();
		if(names.size() == 0) {
			return;
		}
		
		Iterator iterator = names.iterator();
		while(iterator.hasNext()) {
			EJBRelationshipRoleNameWrapper wrapper = (EJBRelationshipRoleNameWrapper)iterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2817, IEJBValidationContext.INFO, wrapper.getRelationshipRole(), new String[]{wrapper.getRelationshipRole().getName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Both section 11.4.1 and 15.3.2 need the <method> element. Also refer
	 * to 16.5 for syntax.
	 *
	 * Return true if at least one of the methods referenced by this list of 
	 * MethodElement can be found.
	*/
	protected boolean validateMethodElements(IEJBValidationContext vc, EJBJar ejbJar, List elements) {
		if ((elements == null) || (elements.size() == 0)) {
			return false;
		}

		boolean hasValidMethod = false;
		Iterator iterator = elements.iterator();
		while (iterator.hasNext()) {
			vc.terminateIfCancelled();
			MethodElement element = (MethodElement) iterator.next();

			EnterpriseBean bean = element.getEnterpriseBean();
			if (bean == null) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2814, IEJBValidationContext.WARNING, element, this);
				vc.addMessage(message);
				continue;
			}

			if (element.getName() != null) {
				String name = element.getName();

				Method[] methods = element.getMethods(); // get all methods which will be retrieved for the given method-permission
				boolean hasMethods = ((methods != null) && (methods.length > 0));

				if (!hasMethods) {
					// warning
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2843, IEJBValidationContext.WARNING, element, new String[] { bean.getName()}, this);
					vc.addMessage(message);
				}
				else {
					hasValidMethod = true; // a <method-permission> must have at least one method (15.3.2, p.229)
					if (name.equals("*")) { //$NON-NLS-1$
						List params = element.getMethodParams();
						if ((params != null) && (params.size() > 0)) {
							// warning
							IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2842, IEJBValidationContext.WARNING, element, this);
							vc.addMessage(message);
						}
					}
				}
			}
			else {
				// error
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2844, IEJBValidationContext.WARNING, element, this);
				vc.addMessage(message);
			}
		}

		return hasValidMethod;
	}

	protected void validateClientJAR(IEJBValidationContext vc, EJBJar ejbJar) {
		String clientJARName = ejbJar.getEjbClientJar();
		if(clientJARName == null) {
			// No client JAR specified; everything's okay.
			return;
		}
		
		Boolean exists = (Boolean)vc.loadModel(EJBValidatorModelEnum.EJB_CLIENTJAR, new Object[]{clientJARName});
		if(exists == null) {
			// Helper doesn't support load model. WAS?
			// Can't perform this check, so just return.
			return;
		}
		
		if(!exists.booleanValue()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2875, IEJBValidationContext.ERROR, ejbJar, new String[]{clientJARName}, this);
			vc.addMessage(message);
		}
	}
	
	class ASNameWrapper {
		private ContainerManagedEntity _bean = null;

		public ASNameWrapper(ContainerManagedEntity bean) {
			_bean = bean;
		}

		public boolean equals(Object o) {
			if (o instanceof ASNameWrapper) {
				ASNameWrapper other = (ASNameWrapper)o;
				if((_bean.getAbstractSchemaName() == null) && (other.getBean().getAbstractSchemaName() == null)) {
					return true;
				}
				else if(_bean.getAbstractSchemaName() == null) {
					return false;
				}
				else if(other.getBean().getAbstractSchemaName() == null) {
					return false;
				}
				return _bean.getAbstractSchemaName().equals(other.getBean().getAbstractSchemaName());
			}
			return false;
		}

		public int hashCode() {
			if((getBean() != null) && (getBean().getAbstractSchemaName() != null)) {
				return getBean().getAbstractSchemaName().hashCode();
			}
			return super.hashCode();
		}

		public ContainerManagedEntity getBean() {
			return _bean;
		}
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
			_bean = bean;
		}

		public boolean equals(Object o) {
			if (o instanceof EjbNameWrapper) {
				EjbNameWrapper other = (EjbNameWrapper)o;
				if((_bean.getName() == null) && (other.getBean().getName() == null)) {
					return true;
				}
				else if(_bean.getName() == null) {
					return false;
				}
				else if(other.getBean().getName() == null) {
					return false;
				}
				return _bean.getName().equals(other.getBean().getName());
			}
			return false;
		}
		
		public int hashCode() {
			if((getBean() != null) && (getBean().getName() != null)) {
				return getBean().getName().hashCode();
			}
			return super.hashCode();
		}

		public EnterpriseBean getBean() {
			return _bean;
		}
	}

	/**
	 * Need to build up a list of duplicate relation names, but the validation message
	 * needs to be registered against the duplicate EnterpriseBean instance.
	 * (Without the instance, we cannot get line numbers.)
	 *
	 * This class wrappers the EnterpriseBean instance so that the wrapper's
	 * implemention of equals compares the names, but the validation message will
	 * still be able to get the ref from the duplicate name.
	 */
	class EJBRelationNameWrapper {
		private EJBRelation _relation = null;

		public EJBRelationNameWrapper(EJBRelation rel) {
			_relation = rel;
		}

		public boolean equals(Object o) {
			if (o instanceof EJBRelationNameWrapper) {
				EJBRelationNameWrapper other = (EJBRelationNameWrapper)o;
				if((_relation.getName() == null) && (other.getRelation().getName() == null)) {
					return true;
				}
				else if(_relation.getName() == null) {
					return false;
				}
				else if(other.getRelation().getName() == null) {
					return false;
				}
				return _relation.getName().equals(other.getRelation().getName());
			}
			return false;
		}
		
		public int hashCode() {
			if((getRelation() != null) && (getRelation().getName() != null)) {
				return getRelation().getName().hashCode();
			}
			return super.hashCode();
		}

		public EJBRelation getRelation() {
			return _relation;
		}
	}

	/**
	 * Need to build up a list of duplicate relation names, but the validation message
	 * needs to be registered against the duplicate EnterpriseBean instance.
	 * (Without the instance, we cannot get line numbers.)
	 *
	 * This class wrappers the EnterpriseBean instance so that the wrapper's
	 * implemention of equals compares the names, but the validation message will
	 * still be able to get the ref from the duplicate name.
	 */
	class EJBRelationshipRoleNameWrapper {
		private EJBRelationshipRole _relationshipRole = null;

		public EJBRelationshipRoleNameWrapper(EJBRelationshipRole role) {
			_relationshipRole = role;
		}

		public boolean equals(Object o) {
			if (o instanceof EJBRelationshipRoleNameWrapper) {
				EJBRelationshipRoleNameWrapper other = (EJBRelationshipRoleNameWrapper)o;
				if((_relationshipRole.getRoleName() == null) && (other.getRelationshipRole().getRoleName() == null)) {
					return true;
				}
				else if(_relationshipRole.getRoleName() == null) {
					return false;
				}
				else if(other.getRelationshipRole().getRoleName() == null) {
					return false;
				}
				return _relationshipRole.getRoleName().equals(other.getRelationshipRole().getRoleName());
			}
			return false;
		}
		
		public int hashCode() {
			if((getRelationshipRole() != null) && (getRelationshipRole().getRoleName() != null)) {
				return getRelationshipRole().getRoleName().hashCode();
			}
			return super.hashCode();
		}

		public EJBRelationshipRole getRelationshipRole() {
			return _relationshipRole;
		}
	}
}
