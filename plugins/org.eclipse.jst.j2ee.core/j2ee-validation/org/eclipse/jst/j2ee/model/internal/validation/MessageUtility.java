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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.wst.validation.core.IMessage;



/**
 * @version 	1.0
 * @author
 */
public final class MessageUtility {
	public static final String NO_MESSAGE_ID = ""; //$NON-NLS-1$
	
	private static MessageUtility inst = null;

	private MessageUtility() {
	}
	
	public static MessageUtility getUtility() {
		if(inst == null) {
			inst = new MessageUtility();
		}
		return inst;
	}
	
	public static String getGroupName(Object targetParent) {
		if(targetParent == null) {
			// No context to associate the message with
			return null;
		}
		
		// So far, the only target parent which is used as a group name is the EnterpriseBean
		if(targetParent instanceof EnterpriseBean) {
			EnterpriseBean bean = (EnterpriseBean)targetParent;
			if(bean == null) {
				return null;
			}
			
			if(bean.getName() != null) {
				return bean.getName();
			}
			return ((XMIResource)bean.eResource()).getID(bean);
		}
		
		return null;
	}
	
	protected String getMessageId(Object messageNumber, EnterpriseBean bean, IValidationRule rule) {
		if(messageNumber == null) {
			return NO_MESSAGE_ID;
		}
		
		Map ids = rule.getMessageIds();
		if((ids == null) || (ids.size() == 0)) {
			return messageNumber.toString();
		}
		
		int index = -1;

		String[] result = (String[])ids.get(messageNumber);
		if(result == null) {
			return messageNumber.toString();
		}
		else if(result.length == 1) {
			if(result[0].equals("")) { //$NON-NLS-1$
				return messageNumber.toString();
			}
			return result[0];
		}
		else {
			// By convention, this is the order of the entries in the String[]
			if(bean.isSession()) {
				index = 0;
			}
			else if(bean.isContainerManagedEntity()) {
				index = 1;
			}
			else if(bean.isBeanManagedEntity()) {
				index = 2;
			}
			else if(bean.isMessageDriven()) {
				index = 3;
			}
			else {
				// what the heck is it?
				return messageNumber.toString();
			}
	
			if((result.length <= index) || (result[index].equals(""))) { //$NON-NLS-1$
				return messageNumber.toString();
			}
			
			return result[index];
		}
	}
	
	/*
	 * Use this method when the message is not registered on a method.
	 */	
	protected String getMessageId(Object messageNumber, IValidationRule rule) {
		if(messageNumber == null) {
			return NO_MESSAGE_ID;
		}
		
		Map ids = rule.getMessageIds();
		if((ids == null) || (ids.size() == 0)) {
			return messageNumber.toString();
		}
		
		String[] result = (String[])ids.get(messageNumber);
		if((result == null) || (result.length == 0) || (result[0].equals(""))) { //$NON-NLS-1$
			return messageNumber.toString();
		}
		return result[0];
	}
	
	/*
	 * Use this method when the message is registered on a method.
	 */
	protected String getMessageId(Object messageNumber, IValidationRule rule, boolean isMethodOnClass) {
		if(messageNumber == null) {
			return NO_MESSAGE_ID;
		}
		
		Map ids = rule.getMessageIds();
		if((ids == null) || (ids.size() == 0)) {
			return messageNumber.toString();
		}
		
		String[] messages = (String[])ids.get(messageNumber);
		String result = null;
		if(messages != null) {
			if(isMethodOnClass && messages.length == 2) {
				result = messages[1];
			}
			else if (messages.length >= 1) {
				result = messages[0];
			}
		}
		
		if((result == null) || (result.equals(""))) { //$NON-NLS-1$
			return messageNumber.toString();
		}
		
		return result;
	}
	
	protected IMessage getMessage(IValidationContext vc, int severity, String id, String[] parms, Object target, String groupName) {
		IMessage message = vc.getMessage();
		message.setSeverity(severity);
		message.setId(id);
		message.setParams(parms);
		message.setTargetObject(target);
		message.setGroupName(groupName);
		return message;
	}
	
	/** 
	 * For use only by the DD VRules.
	 */
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBJar target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, SecurityRole target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, SecurityRoleRef target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, MethodElement target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, MethodTransaction target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, MethodPermission target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBRelationshipRole target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, CommonRelationshipRole target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBRelation target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, AssemblyDescriptor target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, null);
	}

	/** 
	 * For use only by the DD VRules.
	 */
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBJar target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, MethodElement target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBRelationshipRole target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, SecurityRoleRef target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EJBRelation target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, null);
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, Object target, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, target, getGroupName(bean));
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, Object target, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, parms, target, getGroupName(bean));
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, IValidationRule rule) {
		String id = getMessageId(messageNumber, bean, rule);
		return getMessage(vc, severity, id, null, bean, getGroupName(bean));
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, String[] parms, IValidationRule rule) {
		String id = getMessageId(messageNumber, bean, rule);
		return getMessage(vc, severity, id, parms, bean, getGroupName(bean));
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, null, clazz, getGroupName(bean));
	}

	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, String[] additionalParms, IValidationRule rule) {
		String id = getMessageId(messageNumber, rule);
		return getMessage(vc, severity, id, additionalParms, clazz, getGroupName(bean));
	}
	
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, Method method, IValidationRule rule) {
		if(method == null) {
			return getMessage(vc, messageNumber, severity, bean, clazz, rule);
		}
		
		return getMessage(vc, messageNumber, severity, null, clazz, method, method.getMethodElementSignature(), ValidationRuleUtility.onClass(clazz, method), getGroupName(bean), rule);
	}
		
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, Method method, String[] additionalParms, IValidationRule rule) {
		if(method == null) {
			return getMessage(vc, messageNumber, severity, bean, clazz, additionalParms, rule);
		}
		
		return getMessage(vc, messageNumber, severity, additionalParms, clazz, method, method.getMethodElementSignature(), ValidationRuleUtility.onClass(clazz, method), getGroupName(bean), rule);
	}
		
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, Field field, IValidationRule rule) {
		if(field == null) {
			return getMessage(vc, messageNumber, severity, bean, clazz, rule);
		}
		
		return getMessage(vc, messageNumber, severity, null, clazz, field, field.getName(), ValidationRuleUtility.onClass(clazz, field), getGroupName(bean), rule);
	}
		
	public IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, EnterpriseBean bean, JavaClass clazz, Field field, String[] additionalParms, IValidationRule rule) {
		if(field == null) {
			return getMessage(vc, messageNumber, severity, bean, clazz, additionalParms, rule);
		}
		
		return getMessage(vc, messageNumber, severity, additionalParms, clazz, field, field.getName(), ValidationRuleUtility.onClass(clazz, field), getGroupName(bean), rule);
	}
	
	protected IMessage getMessage(IValidationContext vc, Object messageNumber, int severity, String[] additionalParms, JavaClass clazz, EObject fieldOrMethod, String fieldOrMethodName, boolean isMethodOnClass, String groupName, IValidationRule rule) {
		if(fieldOrMethod == null) {
			String id = getMessageId(messageNumber, rule);
			return getMessage(vc, severity, id, additionalParms, clazz, groupName);
		}
		
		String[] parms = null;
		EObject target = null;
		if(isMethodOnClass) {
			// leave parms null since no parms are needed
			target = fieldOrMethod;
			parms = additionalParms;
		}
		else {
			if(additionalParms == null) {
				parms = new String[]{fieldOrMethodName};
			}
			else {
				parms = new String[additionalParms.length + 1];
				parms[0] = fieldOrMethodName;
				System.arraycopy(additionalParms, 0, parms, 1, additionalParms.length);
			}
			target = clazz;
		}

		String id = getMessageId(messageNumber, rule, isMethodOnClass);
		return getMessage(vc, severity, id, parms, target, groupName);
	}
}
