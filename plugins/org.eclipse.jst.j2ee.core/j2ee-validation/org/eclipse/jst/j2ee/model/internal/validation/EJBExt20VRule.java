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
import java.util.logging.Level;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.MessageLimitException;
import org.eclispe.wst.validation.internal.core.ValidationException;

/**
 * Validate the WebSphere-specific implementation of non-spec features, such
 * as component inheritance.
 */
public class EJBExt20VRule extends AValidationRule implements IMessagePrefixEjb20Constants {
	private static final Map MESSAGE_IDS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_STATELESS_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATELESS_SESSION_REMOTE, IValidationRuleList.EJB20_STATELESS_SESSION_HOME, IValidationRuleList.EJB20_STATELESS_SESSION_LOCAL, IValidationRuleList.EJB20_STATELESS_SESSION_LOCALHOME, IValidationRuleList.EJB20_STATEFUL_SESSION_BEANCLASS, IValidationRuleList.EJB20_STATEFUL_SESSION_REMOTE, IValidationRuleList.EJB20_STATEFUL_SESSION_HOME, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCAL, IValidationRuleList.EJB20_STATEFUL_SESSION_LOCALHOME, IValidationRuleList.EJB20_CMP_BEANCLASS, IValidationRuleList.EJB20_CMP_REMOTE, IValidationRuleList.EJB20_CMP_HOME, IValidationRuleList.EJB20_CMP_LOCAL, IValidationRuleList.EJB20_CMP_LOCALHOME, IValidationRuleList.EJB20_CMP_KEYCLASS, IValidationRuleList.EJB20_BMP_BEANCLASS, IValidationRuleList.EJB20_BMP_REMOTE, IValidationRuleList.EJB20_BMP_HOME, IValidationRuleList.EJB20_BMP_LOCAL, IValidationRuleList.EJB20_BMP_LOCALHOME, IValidationRuleList.EJB20_BMP_KEYCLASS, IValidationRuleList.EJB20_MESSAGE_BEANCLASS, IValidationRuleList.EJB20_EJBJAR};
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2106, new String[]{CHKJ2106+SPEC});
		MESSAGE_IDS.put(CHKJ2849, new String[]{CHKJ2849+SPEC});
		MESSAGE_IDS.put(CHKJ2852, new String[]{CHKJ2852});
	}
	
	public Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	public Object getId() {
		return IValidationRuleList.EJB20_EJBEXT;
	}
	
	public Map getMessageIds() {
		return MESSAGE_IDS;
	}

	public Object getTarget(Object parent, Object clazz) {
		return null;
	}

	/**
	 * If the bean components (home interface, remote interface, bean class, and primary
	 * key) can all be found and reflected, return true. Let the DDValidator
	 * report the error message against the bean if one of these types doesn't reflect.
	 */
	public boolean areBeanComponentsReflected(EnterpriseBean bean) {
		// Don't need to check if the bean is null, because this method will
		// not be called if it is.
		try {
			ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getEjbClass());
		}
		catch (InvalidInputException exc) {
			return false;
		}
		
		if(bean instanceof MessageDriven) {
			// don't need to check the rest
			return true;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getHomeInterface());
			}
		}
		catch (InvalidInputException exc) {
			return false;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getRemoteInterface());
			}
		}
		catch (InvalidInputException exc) {
			return false;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getLocalHomeInterface());
			}
		}
		catch (InvalidInputException exc) {
			return false;
		}

		try {
			if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface())) {
				ValidationRuleUtility.isValidTypeHierarchy(bean, bean.getLocalInterface());
			}
		}
		catch (InvalidInputException exc) {
			return false;
		}

		if (bean.isEntity()) {
			JavaClass primaryKey = ((Entity) bean).getPrimaryKey();
			try {
				if(((Entity)bean).eIsSet(EjbPackage.eINSTANCE.getEntity_PrimaryKey())) {
					ValidationRuleUtility.isValidTypeHierarchy(bean, primaryKey);
				}
			}
			catch (InvalidInputException exc) {
				return false;
			}
		}

		return true;
	}

	public void validate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		Logger logger = vc.getMsgLogger();
		if(logger != null && logger.isLoggingLevel(Level.FINEST)) {
			LogEntry entry = vc.getLogEntry();
			entry.setSourceID("EJB20Validator - validate"); //$NON-NLS-1$
			entry.setText(getClass().getName() + "::validate(" + targetParent + ", " + target); //$NON-NLS-1$ //$NON-NLS-2$
			logger.write(Level.FINEST, entry);
		}
		
		EJBJar ejbJar = null;
		if(targetParent == null) {
			ejbJar = (EJBJar)target;
			validate(vc, ejbJar);
		}
		else {
			// running as a dependent
			ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
			EnterpriseBean bean = (EnterpriseBean)targetParent;
			validate(vc, ejbJar, bean);
		}
	}
	
	/*
	 * @see IValidationRule#validate(IEJBValidationContext, Object, Object)
	 */
	public void validate(IEJBValidationContext vc, EJBJar ejbJar) throws ValidationCancelledException, ValidationException {
		List enterpriseBeans = ejbJar.getEnterpriseBeans();
		Iterator iterator = enterpriseBeans.iterator();
		EnterpriseBean bean = null;
		while(iterator.hasNext()) {
			bean = (EnterpriseBean)iterator.next();
			if(bean == null) {
				// If bean isn't valid, don't perform any of the other
				// validation checks on it. Let DDValidator output the error message.
				continue;
			}
			validate(vc, ejbJar, bean);
		}
	}
	
	public void validate(IEJBValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) throws ValidationCancelledException, ValidationException {
		try {
			// Check if the class exists, etc.
			if(!areBeanComponentsReflected(bean)) {
				// Something didn't reflect properly, so don't continue with the
				// rest of the checks. Some metadata will be nonsense.
				return;
			}
			
			// Component inheritance is now checked in each VRule instead of here.
			// This was necessary for incremental validation; if one class changed
			// in the bean, and the bean was a member of component inheritance, then
			// messages were added multiple times onto the classes which had not changed.
			// In order to avoid multiple messages, only the component inheritance of
			// the class which changed should be revalidated when the class changes.
			validateAppendixB(vc, ejbJar, bean); // validate the key class since that message is registered against ejb-jar.xml
		}
		catch(MessageLimitException e) {
			throw e;
		}
		catch(ValidationCancelledException e) {
			throw e;
		}
		/* unreachable catch block
		catch(ValidationException exc) {
			// If there's a problem, proceed with the next bean.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorConstants.CHKJ2852, IEJBValidationContext.WARNING, bean, new String[]{ArchiveConstants.EJBJAR_EXTENSIONS_SHORT_NAME, beanName}, this);
			vc.addMessage(message);
			if(logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
		}
		*/
		catch(Throwable exc) {
			// If there's a problem, proceed with the next bean.
			String superTypeName = getEJBInheritanceFileName();
			if(superTypeName == null)
			  superTypeName = "unknown super type"; //$NON-NLS-1$
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2852, IEJBValidationContext.WARNING, bean, new String[]{superTypeName, bean.getName()}, this);
			vc.addMessage(message);
			Logger logger = vc.getMsgLogger();
			if(logger != null && logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	protected void validateAppendixB(IEJBValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		// The Java inheritance structure must match the EJB inheritance structure.
		// e.g. if EJB B is a child of EJB A, then class B must be a child of class A.
		// B could be a grandchild (or great-grandchild or ...) of A.
		if(bean == null) {
			return;
		}
		if(!bean.isEntity()) {
			return;
		}
		JavaClass thisKey = ((Entity)bean).getPrimaryKey();
		EnterpriseBean supertype = getSuperType(bean);
		JavaClass parentKey = null;
		if (supertype != null) {
			// check this CMP's supertype
			// Key a Xchild of parent Key
			// In WSA EJB component inheritance, the root EJB must define the key.
			// The key is the same for all child EJBs.
			if (supertype instanceof Entity) {
				parentKey = ((Entity) supertype).getPrimaryKey();

				if ((thisKey == null) || (parentKey == null) || !thisKey.equals(parentKey)) {
					String[] msgParm = new String[] { bean.getName(), parentKey.getQualifiedName()};
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2106, IEJBValidationContext.ERROR, bean, msgParm, this);
					vc.addMessage(message);
				}
			}
		}
		
//		validateAppendixB(vc, supertype, parentKey);
	}
}
