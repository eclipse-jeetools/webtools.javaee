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
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.IMessage;


public abstract class ABMPHomeVRule extends AEntityHomeVRule {

	public final void validateMatchingReturnTypeMatches(IValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method homeMethod, Method beanMethod, List[] methodsExtendedLists) {
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, homeMethod, methodsExtendedLists, this);
		if((methodType & FIND) != FIND) {
			super.validateMatchingReturnTypeMatches(vc, bean, clazz, homeMethod, beanMethod, methodsExtendedLists);
		}
		else {
			JavaHelpers homeRetType = homeMethod.getReturnType();
			JavaHelpers beanRetType = beanMethod.getReturnType();
			if (!ValidationRuleUtility.isAssignableFrom(homeRetType, beanRetType)) {
				// emit the error only if it's a multi-finder. Single-finders are supposed to have different return types on the home & bean class.
				boolean homeRetTypeIsRemote = ValidationRuleUtility.isAssignableFrom(homeRetType, bean.getRemoteInterface());
				boolean homeRetTypeIsLocal =  ValidationRuleUtility.isAssignableFrom(homeRetType, bean.getLocalInterface());
				boolean beanRetTypeIsKey = ValidationRuleUtility.isAssignableFrom(beanRetType, ((Entity)bean).getPrimaryKey());
				if(!((homeRetTypeIsRemote || homeRetTypeIsLocal) && beanRetTypeIsKey)) {
					String retTypeName = null;
					if(beanRetTypeIsKey) {
						if((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
							retTypeName = (bean.getRemoteInterface() == null) ? IEJBValidatorConstants.NULL_REMOTE : bean.getRemoteInterface().getJavaName();
						}
						else {
							retTypeName = (bean.getLocalInterface() == null) ? IEJBValidatorConstants.NULL_LOCAL : bean.getLocalInterface().getJavaName();
						}
					}
					else {
						retTypeName = (beanRetType == null) ? "?" : beanRetType.getJavaName(); //$NON-NLS-1$
					}
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2479, IValidationContext.ERROR, bean, clazz, homeMethod, new String[] { retTypeName}, this);
					vc.addMessage(message);
				}
			}
		}
	}

}
