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
import java.util.logging.Level;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.core.IFileDelta;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Core EJB Validation rules
 */
public class EJBValidationRuleFactory extends AbstractEJBValidationRuleFactory {
	private static EJBValidationRuleFactory _inst = null;
	private Logger logger = null;
	
	public EJBValidationRuleFactory() {
		createRuleList();
	}
	
	public static EJBValidationRuleFactory getFactory() {
		if(_inst == null) {
			_inst = new EJBValidationRuleFactory();
		}
		return _inst;
	}
	protected Logger getMsgLogger(){
	    if(logger == null)
	        logger = Logger.getLogger(IEJBValidatorConstants.J2EE_CORE_PLUGIN);
	    return logger;
	}
	private void createRuleList() {
		_ruleList = new HashMap();

		_ruleList.put(IValidationRuleList.EJB11_EJBJAR, new org.eclipse.jst.j2ee.model.validation.EJBJar11VRule());
		_ruleList.put(IValidationRuleList.EJB20_EJBJAR, new org.eclipse.jst.j2ee.model.validation.EJBJar20VRule());
	
		_ruleList.put(IValidationRuleList.EJB11_ENTERPRISEBEAN, new org.eclipse.jst.j2ee.model.validation.EnterpriseBean11VRule());
		_ruleList.put(IValidationRuleList.EJB20_ENTERPRISEBEAN, new org.eclipse.jst.j2ee.model.validation.EnterpriseBean20VRule());
		
		_ruleList.put(IValidationRuleList.EJB11_SESSION_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.ValidateSessionBean());
		_ruleList.put(IValidationRuleList.EJB11_SESSION_REMOTE, new org.eclipse.jst.j2ee.model.validation.ValidateSessionRemote());
		_ruleList.put(IValidationRuleList.EJB11_SESSION_HOME, new org.eclipse.jst.j2ee.model.validation.ValidateSessionHome());
		
		_ruleList.put(IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.StatelessSessionBeanClassVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE, new org.eclipse.jst.j2ee.model.validation.StatelessSessionRemoteComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATELESS_SESSION_HOME, new org.eclipse.jst.j2ee.model.validation.StatelessSessionRemoteHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL, new org.eclipse.jst.j2ee.model.validation.StatelessSessionLocalComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME, new org.eclipse.jst.j2ee.model.validation.StatelessSessionLocalHomeVRule());
		
		_ruleList.put(IValidationRuleList.EJB20_STATEFUL_SESSION_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.StatefulSessionBeanClassVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATEFUL_SESSION_REMOTE, new org.eclipse.jst.j2ee.model.validation.StatefulSessionRemoteComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATEFUL_SESSION_HOME, new org.eclipse.jst.j2ee.model.validation.StatefulSessionRemoteHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATEFUL_SESSION_LOCAL, new org.eclipse.jst.j2ee.model.validation.StatefulSessionLocalComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_STATEFUL_SESSION_LOCALHOME, new org.eclipse.jst.j2ee.model.validation.StatefulSessionLocalHomeVRule());
		
		_ruleList.put(IValidationRuleList.EJB11_CMP_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.ValidateCMPBean());
		_ruleList.put(IValidationRuleList.EJB11_CMP_REMOTE, new org.eclipse.jst.j2ee.model.validation.ValidateCMPRemote());
		_ruleList.put(IValidationRuleList.EJB11_CMP_KEYCLASS, new org.eclipse.jst.j2ee.model.validation.ValidateCMPKey());
		
		_ruleList.put(IValidationRuleList.EJB20_CMP_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.CMPBeanClassVRule());
		_ruleList.put(IValidationRuleList.EJB20_CMP_REMOTE, new org.eclipse.jst.j2ee.model.validation.CMPRemoteComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_CMP_HOME, new org.eclipse.jst.j2ee.model.validation.CMPRemoteHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_CMP_LOCAL, new org.eclipse.jst.j2ee.model.validation.CMPLocalComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_CMP_LOCALHOME, new org.eclipse.jst.j2ee.model.validation.CMPLocalHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_CMP_KEYCLASS, new org.eclipse.jst.j2ee.model.validation.CMPKeyClassVRule());
		
		_ruleList.put(IValidationRuleList.EJB11_BMP_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.ValidateBMPBean());
		_ruleList.put(IValidationRuleList.EJB11_BMP_REMOTE, new org.eclipse.jst.j2ee.model.validation.ValidateBMPRemote());
		_ruleList.put(IValidationRuleList.EJB11_BMP_HOME, new org.eclipse.jst.j2ee.model.validation.ValidateBMPHome());
		_ruleList.put(IValidationRuleList.EJB11_BMP_KEYCLASS, new org.eclipse.jst.j2ee.model.validation.ValidateBMPKey());

		_ruleList.put(IValidationRuleList.EJB20_BMP_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.BMPBeanClassVRule());
		_ruleList.put(IValidationRuleList.EJB20_BMP_REMOTE, new org.eclipse.jst.j2ee.model.validation.BMPRemoteComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_BMP_HOME, new org.eclipse.jst.j2ee.model.validation.BMPRemoteHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_BMP_LOCAL, new org.eclipse.jst.j2ee.model.validation.BMPLocalComponentVRule());
		_ruleList.put(IValidationRuleList.EJB20_BMP_LOCALHOME, new org.eclipse.jst.j2ee.model.validation.BMPLocalHomeVRule());
		_ruleList.put(IValidationRuleList.EJB20_BMP_KEYCLASS, new org.eclipse.jst.j2ee.model.validation.BMPKeyClassVRule());
		
		_ruleList.put(IValidationRuleList.EJB20_MESSAGE_BEANCLASS, new org.eclipse.jst.j2ee.model.validation.MessageDrivenBeanClassVRule());
		
		addDependentRuleToRuleList();
		logAllDependentRules();
	}
	
	/**
	 * If the IFileDelta represents a static load model (i.e., the file name is always the same),
	 * then return the Integer which loads that model. Otherwise return null.
	 */
	public final Object getRuleId(IValidationContext vc, IFileDelta delta) {
		if(delta == null) {
			Logger aLogger = vc.getMsgLogger();
			if (aLogger != null && aLogger.isLoggingLevel(Level.FINE)) {
				aLogger.write(Level.FINE, "Cannot load rule from delta: " + delta); //$NON-NLS-1$
			}
			return null;
		}
		
		return getRuleId(vc, delta.getFileName());
	}
	
	/**
	 * If the IFileDelta represents a static load model (i.e., the file name is always the same),
	 * then return the Integer which loads that model. Otherwise return null.
	 */
	public  Object getRuleId(IValidationContext vc, String fileName) {
		if(fileName == null) {
			Logger aLogger = vc.getMsgLogger();
			if (aLogger != null && aLogger.isLoggingLevel(Level.FINE)) {
				aLogger.write(Level.FINE, "Cannot load rule from fileName: " + fileName); //$NON-NLS-1$
			}
			return null;
		}

		EJBJar ejbJar = getEJBJar(vc);

		if(fileName.endsWith(ArchiveConstants.EJBJAR_DD_SHORT_NAME)) {
			if(ejbJar.getVersionID() == J2EEVersionConstants.EJB_1_1_ID) {
				return IValidationRuleList.EJB11_EJBJAR;
			}
			return IValidationRuleList.EJB20_EJBJAR;
		}
		return null; // must be a JavaClass
	}
	
	/**
	 * If the IFileDelta represents a dynamic load model (i.e., the file name can be changed by the
	 * user), then return the Integer which loads that model. Return null if no such model exists.
	 */
	public final Object getRuleId(IValidationContext vc, JavaClass clazz, EnterpriseBean bean) {
		// Return the rule id which will validate the JavaClass as the 
		// type which it is used as by the EnterpriseBean.
		if((clazz == null) || (bean == null)) {
			Logger aLogger = vc.getMsgLogger();
			if (aLogger != null && aLogger.isLoggingLevel(Level.FINE)) {
				aLogger.write(Level.FINE, "Cannot load rule because either bean (" + bean + ") or class (" + clazz + ") is null"); //$NON-NLS-1$  //$NON-NLS-2$  //$NON-NLS-3$
			}
			return null;
		}
		
		boolean isStatelessSession = (bean.isSession() && ValidationRuleUtility.isStateless(bean));
		boolean isStatefulSession = (bean.isSession() && ValidationRuleUtility.isStateful(bean));
		
		if(bean.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
			if(clazz.equals(bean.getEjbClass())) {
				if(isStatelessSession) {
						return IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS;
				}
				else if(isStatefulSession) {
						return IValidationRuleList.EJB20_STATEFUL_SESSION_BEANCLASS;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB20_CMP_BEANCLASS;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB20_BMP_BEANCLASS;
				}
				else if(bean.isMessageDriven()) {
					return IValidationRuleList.EJB20_MESSAGE_BEANCLASS;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger != null && aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(bean.getEjbClass()"); //$NON-NLS-1$
					}
					
					return null;
				}
			}
			else if(clazz.equals(bean.getRemoteInterface())) {
				if(isStatelessSession) {
					return IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE;
				}
				else if(isStatefulSession) {
					return IValidationRuleList.EJB20_STATEFUL_SESSION_REMOTE;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB20_CMP_REMOTE;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB20_BMP_REMOTE;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(bean.getRemoteInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(clazz.equals(bean.getHomeInterface())) {
				if(isStatelessSession) {
					return IValidationRuleList.EJB20_STATELESS_SESSION_HOME;
				}
				else if(isStatefulSession) {
					return IValidationRuleList.EJB20_STATEFUL_SESSION_HOME;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB20_CMP_HOME;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB20_BMP_HOME;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(bean.getHomeInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(clazz.equals(bean.getLocalHomeInterface())) {
				if(isStatelessSession) {
					return IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME;
				}
				else if(isStatefulSession) {
					return IValidationRuleList.EJB20_STATEFUL_SESSION_LOCALHOME;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB20_CMP_LOCALHOME;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB20_BMP_LOCALHOME;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(bean.getLocalHomeInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(clazz.equals(bean.getLocalInterface())) {
				if(isStatelessSession) {
					return IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL;
				}
				else if(isStatefulSession) {
					return IValidationRuleList.EJB20_STATEFUL_SESSION_LOCAL;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB20_CMP_LOCAL;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB20_BMP_LOCAL;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(bean.getLocalInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(bean instanceof Entity) {
				if(clazz.equals(((Entity)bean).getPrimaryKey())) {
					if(bean.isContainerManagedEntity()) {
						ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
						// If it's a primitive primary key, it's exempt from these checks.
						if(ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
							return null;
						}
						else if(ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
							return null;
						}
						return IValidationRuleList.EJB20_CMP_KEYCLASS;
					}
					else if(bean.isBeanManagedEntity()) {
						return IValidationRuleList.EJB20_BMP_KEYCLASS;
					}
					else {
						Logger aLogger = vc.getMsgLogger();
						if (aLogger.isLoggingLevel(Level.FINE)) {
							aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X() && clazz.equals(((Entity)bean).getPrimaryKey()"); //$NON-NLS-1$
						}
						return null;
					}
				}
			}
			
			Logger aLogger = vc.getMsgLogger();
			if (aLogger.isLoggingLevel(Level.FINE)) {
				aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion2_X()"); //$NON-NLS-1$
			}
			return null;
		}
		else if(bean.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID) {
			if(clazz.equals(bean.getEjbClass())) {
				if(bean.isSession()) {
						return IValidationRuleList.EJB11_SESSION_BEANCLASS;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB11_CMP_BEANCLASS;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB11_BMP_BEANCLASS;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion1_X() && clazz.equals(bean.getEjbClass()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(clazz.equals(bean.getRemoteInterface())) {
				if(bean.isSession()) {
					return IValidationRuleList.EJB11_SESSION_REMOTE;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB11_CMP_REMOTE;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB11_BMP_REMOTE;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion1_X() && clazz.equals(bean.getRemoteInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(clazz.equals(bean.getHomeInterface())) {
				if(bean.isSession()) {
					return IValidationRuleList.EJB11_SESSION_HOME;
				}
				else if(bean.isContainerManagedEntity()) {
					return IValidationRuleList.EJB11_CMP_HOME;
				}
				else if(bean.isBeanManagedEntity()) {
					return IValidationRuleList.EJB11_BMP_HOME;
				}
				else {
					Logger aLogger = vc.getMsgLogger();
					if (aLogger.isLoggingLevel(Level.FINE)) {
						aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion1_X() && clazz.equals(bean.getHomeInterface()"); //$NON-NLS-1$
					}
					return null;
				}
			}
			else if(bean instanceof Entity) {
				if(clazz.equals(((Entity)bean).getPrimaryKey())) {
					if(bean.isContainerManagedEntity()) {
						ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
						// If it's a primitive primary key, it's exempt from these checks.
						if(ValidationRuleUtility.isPrimitivePrimaryKey(cmp)) {
							return null;
						}
						else if(ValidationRuleUtility.usesUnknownPrimaryKey(cmp)) {
							return null;
						}
						return IValidationRuleList.EJB11_CMP_KEYCLASS;
					}
					else if(bean.isBeanManagedEntity()) {
						return IValidationRuleList.EJB11_BMP_KEYCLASS;
					}
					else {
						Logger aLogger = vc.getMsgLogger();
						if (aLogger.isLoggingLevel(Level.FINE)) {
							aLogger.write(Level.FINE, "Cannot load rule: bean.isVersion1_X() && clazz.equals(((Entity)bean).getPrimaryKey()"); //$NON-NLS-1$
						}
						return null;
					}
				}
			}
		}
		
		Logger aLogger = vc.getMsgLogger();
		if (aLogger.isLoggingLevel(Level.FINE)) {
			aLogger.write(Level.FINE, "Cannot load rule: !bean.isVersion1_X() && !bean.isVersion2_X()"); //$NON-NLS-1$
		}
		return null;
	}
}
