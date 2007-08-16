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

import java.util.List;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * @version 	1.0
 * @author
 */
public abstract class AKeyClassVRule extends ATypeVRule {
	protected final static long[] BASE_TYPES = new long[]{ITypeConstants.JAVA_LANG_OBJECT};
	
	public long[] getBaseTypes() {
		return BASE_TYPES;
	}
	
	public final List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a key class' method is defined on another class 
		// of the bean.
		return null;
	}
	
	public final List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) {
		// Never check that a key class' field is defined on another class
		// of the bean.
		return null;
	}
	
	/*
	 * @see IClassVRule#validate(IEJBValidationContext, EnterpriseBean, JavaClass)
	 */
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// CHKJ2108 = {0} must be a legal Value Type in RMI-IIOP. Read section 9.8 of the EJB 2.0 specification.
		// CHKJ2241 = {0} must be a legal Value Type in RMI-IIOP. Read section 10.6.13 of the EJB 2.0 specification.
		// CHKJ2376 = {0} must be a legal Value Type in RMI-IIOP. Read section 12.2.12 of the EJB 2.0 specification.
		if(!ValidationRuleUtility.isLegalRMI_IIOPType(bean, clazz)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2019, IEJBValidationContext.INFO, bean, clazz, this);
			vc.addMessage(message);
		}
	}
	
	public long getFieldType(EnterpriseBean bean, JavaClass clazz, Field field) {
		if(field == null) {
			return EXCLUDED_FIELD;
		}
		else if(field.getName().equals(IMethodAndFieldConstants.FIELDNAME_SERIALVERSIONUID)) {
			return SERIALVERSIONUID;
		}
		else {
			return OTHER_FIELD;
		}	
	}
	
	public long getDefaultMethodType() {
		return NO_METHODS;
	}

	public final boolean followRemoteExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException {
		// Doesn't matter if the key class throws RemoteException or not
		return true;
	}
}
