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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;



public abstract class AEntityHomeVRule extends AHomeVRule {
	/**
	 * If the type of the method isn't listed in the KNOWN_METHODS, what type of method is it
	 * considered to be?
	 */
	public long getDefaultMethodType() {
		return HOME;
	}

	public final void validateFindByPrimaryKeyMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, ValidationException {
		JavaClass key = ((Entity)bean).getPrimaryKey();
		JavaParameter[] parms = method.listParametersWithoutReturn();
		if((parms.length != 1) || (!ValidationRuleUtility.isAssignableFrom(key, parms[0].getJavaType()))) {
			String keyName = (key == null) ? IEJBValidatorConstants.NULL_PRIMARY_KEY : key.getJavaName();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2475, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{keyName}, this);
			vc.addMessage(message);
		}
	}

	public void validateFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.followsFinderExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2477, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		if(!ValidationRuleUtility.followsObjectNotFoundExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2478, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
		if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
			validateFindByPrimaryKeyMethod(vc, bean, clazz, method);
			if(!returnsComponentInterface(vc, bean, clazz, method)) {
				JavaClass componentInterface = getComponentInterface(bean);
				String className = null;
				if(componentInterface != null) {
					className = componentInterface.getJavaName();
				}
				else {
					if((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
						className = IEJBValidatorConstants.NULL_REMOTE;
					}
					else {
						className = IEJBValidatorConstants.NULL_LOCAL;
					}
				}
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2479, IEJBValidationContext.ERROR, bean, clazz, method, new String[]{className}, this);
				vc.addMessage(message);
			}
		}
		else {
			if(!returnsComponentInterfaceOrCollection(vc, bean, clazz, method)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2403, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{getComponentInterface(bean).getName()}, this);
				vc.addMessage(message);
			}
		}
	}
	
	public void validateHomeMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, ValidationException {
		// IWAD4350 = The return type must match the return type of {0}. Read section 12.2.9 of the EJB 2.0 specification.
		// The above check is done in the "validate(vc, bean, clazz, method) method.
		if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_CREATE)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2462, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		else if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_FIND)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2463, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		else if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_REMOVE)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2464, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}

}
