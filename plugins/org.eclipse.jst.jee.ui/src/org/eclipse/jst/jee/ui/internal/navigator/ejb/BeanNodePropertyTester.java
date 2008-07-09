package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;

public class BeanNodePropertyTester extends PropertyTester {

	public static final String BEAN_TYPE = "beanType"; //$NON-NLS-1$
	public static final String IS_SESSION_BEAN = "isSessionBean"; //$NON-NLS-1$
	public static final String IS_MESSAGE_DRIVEN = "isMessageDrivenBean"; //$NON-NLS-1$

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
