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
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


public abstract interface IMethodType {
	public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method);

	public long getId();
	
	/**
	 * Return true if this method type is a default type for an enterprise bean's class.
	 * For example, if the method on the home is neither a create nor a find method, then
	 * it is assumed to be the default type, a home method.
	 */
	public boolean isDefaultType();
	
	public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists);

	public String getMessageId_messageMissing(); // required method is missing
	public String getMessageId_messageExists(); // method should not exist
	public String getMessageId_messageRemoteException(); // method throws (or doesn't throw) RemoteException
	public String getMessageId_messageMissingMatching(); // method is missing its matching method on the bean class
	public String getMessageId_messageRMI_IIOPParm(); // parameter or return type is not a legal RMI-IIOP type
}
