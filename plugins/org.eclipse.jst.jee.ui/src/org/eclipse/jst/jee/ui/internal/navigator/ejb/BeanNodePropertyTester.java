/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;

public class BeanNodePropertyTester extends PropertyTester {

	public static final String BEAN_TYPE = "beanType"; //$NON-NLS-1$
	public static final String IS_SESSION_BEAN = "isSessionBean"; //$NON-NLS-1$
	public static final String IS_MESSAGE_DRIVEN = "isMessageDrivenBean"; //$NON-NLS-1$

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {

		if (!(receiver instanceof BeanNode)) {
			return false;
		}

		Object adapterNode = ((BeanNode) receiver).getAdapterNode();

		String val = (String) expectedValue;
		if (BEAN_TYPE.equals(property)) {
			if (IS_SESSION_BEAN.equals(val)) {
				return isSessionBean(adapterNode);
			} else if (IS_MESSAGE_DRIVEN.equals(val)) {
				return isMessageDrivenBean(adapterNode);
			}
			return false;
		}
		return false;
	}

	private boolean isMessageDrivenBean(Object obj) {
		if (obj instanceof MessageDrivenBean) {
			return true;
		}
		return false;
	}

	private boolean isSessionBean(Object obj) {
		if (obj instanceof SessionBean)
			return true;
		return false;
	}

}
