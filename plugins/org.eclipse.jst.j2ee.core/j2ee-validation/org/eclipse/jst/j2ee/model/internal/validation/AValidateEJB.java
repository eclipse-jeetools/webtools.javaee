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


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Insert the type's description here.
 * Creation date: (1/5/01 11:51:09 AM)
 * @author: Administrator
 */
public abstract class AValidateEJB extends AValidationRule {
	private List roleMethodNames;
	
	public void reset() {
		super.reset();
		if(roleMethodNames != null) {
			roleMethodNames.clear();
			roleMethodNames = null; // in this case, clearing the list isn't enough, because it is legal to have zero role names. Must be null or we'll keep trying to reload the role names just because there are zero role names.
		}
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (9/5/2001 12:36:39 PM)
	 * @return java.util.List
	 */
	protected List getRoleMethodNames(IEJBValidationContext vc, EnterpriseBean bean) {
		try {
			if (roleMethodNames == null && bean != null) {
				roleMethodNames = getRoleMethodNamesExtended(bean);
			}
			return roleMethodNames;
		}
		catch (Throwable exc) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
			return Collections.EMPTY_LIST;
		}
	}
	
	protected boolean isEjbRelationshipRoleMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null)
			return false;
		List aRoleMethodNames = getRoleMethodNames(vc, bean);
		if(aRoleMethodNames != null && !aRoleMethodNames.isEmpty())
		  return aRoleMethodNames.contains(method.getName());
		return false;
	}
	
	/**
	 * Return true if the field can, and should, be validated.
	 * Filter out faulty fields (i.e., null), and fields which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	protected boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtendedLists) throws InvalidInputException {
		// The following call checks if the type reflected properly
		ValidationRuleUtility.isValidType(ValidationRuleUtility.getType(field));

		if (field.getName().equals(IMethodAndFieldConstants.FIELDNAME_SERIALVERSIONUID)) {
			return false;
		}

		return true;
	}
	
	/**
	 * Return true if the method can, and should, be validated.
	 * Filter out faulty methods (i.e., null), and methods which
	 * belong to the base type, whatever that is. (e.g. java.lang.Object)
	 */
	protected boolean isValid(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws InvalidInputException {
		if (method == null) {
			throw new InvalidInputException();
		}
		return true;
	}

	/**
	 * This method actually does the validation. Since many of the classes do
	 * not validate fields now, implement this method as a no-op so that the
	 * classes compile. 
	 */
	public void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		//Default
	}
	
	/**
	 * This method actually does the validation.
	 */
	public abstract void primValidate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException;
	
	/**
	 * This method does the actual checking (if ejbMethod exists).
	 */
	protected abstract void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException;
	
	protected abstract List[] getMethodsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz);
	protected abstract List[] getFieldsExtended(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz);
	
	protected final void reflectionError(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz)  {
		vc.removeMessages(clazz);
	
		IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2907, IEJBValidationContext.ERROR, bean, clazz, new String[]{clazz.getQualifiedName()}, this);
		vc.addMessage(message);
	}
	
	protected final void reflectionWarning(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, InvalidInputException e) {
		// One of two cases where we care if the type is on this class or not (i.e., not leaving it up to MessageUtility)
		String[] msgParm = null;
		String javaClassName = (e.getJavaClass() == null) ? "?" : e.getJavaClass().getQualifiedName(); //$NON-NLS-1$
		if(ValidationRuleUtility.onClass(clazz, field)) {
			msgParm = new String[] {String.valueOf(field.getName()), String.valueOf(javaClassName)};
		}
		else {
			msgParm = new String[] {javaClassName}; // MessageUtility will substitute in the name of the field
		}
		IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2433, IEJBValidationContext.WARNING, bean, clazz, field, msgParm, this);
		vc.addMessage(message);
	}
	
	protected final void reflectionWarning(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, InvalidInputException e) {
		// One of two cases where we care if the type is on this class or not (i.e., not leaving it up to MessageUtility)
		String[] msgParm = null;
		String javaClassName = (e.getJavaClass() == null) ? "?" : e.getJavaClass().getQualifiedName(); //$NON-NLS-1$
		if(ValidationRuleUtility.onClass(clazz, method)) {
			msgParm = new String[] {method.getMethodElementSignature(), javaClassName};
		}
		else {
			msgParm = new String[] {javaClassName}; // MessageUtility will substitute in the name of the field
		}
		IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2433, IEJBValidationContext.WARNING, bean, clazz, method, msgParm, this);
		vc.addMessage(message);
	}
	
	public final void validate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationException {
		vc.terminateIfCancelled();
		
		EnterpriseBean bean = (EnterpriseBean)targetParent;
		JavaClass clazz = (JavaClass)target;
		
		try {
			// check if all of the types in the bean reflected properly
			ValidationRuleUtility.isValidTypeHierarchy(bean, clazz);
			
			vc.subtask(IEJBValidatorConstants.STATUS_VALIDATING, new String[]{clazz.getQualifiedName()});
			validateClass(vc, bean, clazz);
			validateMethods(vc, bean, clazz);
			validateFields(vc, bean, clazz);
		}
		catch(InvalidInputException e) {
			reflectionError(vc, bean, clazz);
			return;
		}
		catch(ValidationCancelledException e) {
			throw e;
		}
	}

	/**
	 * This method should be called to avoid validating a Field more
	 * than once.  This is not used for checking the existence of a field.
	 */
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtendedList) throws InvalidInputException {
		if (isValid(vc, bean, clazz, field, fieldsExtendedList)) {
			primValidate(vc, bean, clazz, field);
		}
	}
	
	/**
	 * This method should be called to avoid validating a Method more
	 * than once.  This is not used for checking the existence of a method.
	 */
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod, List[] methodsExtendedList) throws InvalidInputException {
		if (isValid(vc, bean, clazz, ejbMethod, methodsExtendedList)) {
			primValidate(vc, bean, clazz, ejbMethod);
		}
	}
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected final void validateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod, List[] methodsExtendedList) throws InvalidInputException {
		if (isValid(vc, bean, clazz, ejbMethod, methodsExtendedList)) {
			primValidateExistence(vc, bean, clazz, ejbMethod);
		}
	}
	
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		//Default
	}
	
	public final void validateFields(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz)  throws ValidationCancelledException, InvalidInputException, ValidationException {
		vc.terminateIfCancelled();

		// Whenever looping over an entire list, always use an 
		// iterator instead of a for loop, for performance reasons. 
		// If the list is an instance of a linked list, for example,
		// the get(i) call can take  a long time. 
		List fields = clazz.getFieldsExtended();
		Iterator iterator = fields.iterator();
		Field field = null;
		List[] fieldsExtendedList = getFieldsExtended(vc, bean, clazz);
		while (iterator.hasNext()) {
			vc.terminateIfCancelled();
			field = (Field) iterator.next();
			if (field == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("AValidateEJB.validateFields()"); //$NON-NLS-1$
					entry.setText("A field is null on " + clazz); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}
			try {
				validate(vc, bean, clazz, field, fieldsExtendedList);
			}
			catch (InvalidInputException e) {
				reflectionWarning(vc, bean, clazz, field, e);
			}
		}
		vc.terminateIfCancelled();
	}
	
	/**
	 * Verifies that a given method is RMI legal.
	 * This means that their argument and return values must be of valid types 
	 * for RMI/IIOP, and their throws clause must include the java.rmi.RemoteException.
	 */
	public final void validateLegalRMIMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		validateLegalRMIMethodWithoutExceptions(vc, bean, clazz, method);
		validateLegalRMIMethodExceptions(vc, bean, clazz, method);
	}
	
	/**
	 * Verifies that a given method is RMI legal.
	 * This means that their argument and return values must be of valid types 
	 * for RMI/IIOP, and their throws clause must include the java.rmi.RemoteException.
	 */
	public final void validateLegalRMIMethodArguments(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (method == null) {
			return;
		}

		JavaParameter[] args = method.listParametersWithoutReturn();
		for (int i = 0; i < args.length; i++) {
			vc.terminateIfCancelled();

			if (!ValidationRuleUtility.isLegalRMI_IIOPType(bean, args[i].getJavaType())) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2413, IEJBValidationContext.INFO, bean, clazz, method, new String[]{args[i].getQualifiedName()}, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * Verifies that a given method is RMI legal.
	 * This means that their argument and return values must be of valid types 
	 * for RMI/IIOP, and their throws clause must include the java.rmi.RemoteException.
	 */
	public final void validateLegalRMIMethodExceptions(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();

		if (method == null) {
			return;
		}

		if (!ValidationRuleUtility.throwsRemoteExceptionOrParent(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2414, IEJBValidationContext.ERROR, bean, clazz, method, new String[]{ITypeConstants.CLASSNAME_JAVA_RMI_REMOTEEXCEPTION}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Verifies that a given method is RMI legal, but doesn't check the exceptions.
	 * This means that the method's argument and return values must be of valid types 
	 * for RMI/IIOP.
	 */
	public final void validateLegalRMIMethodReturnType(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		if (method == null) {
			return;
		}

		if (!ValidationRuleUtility.isLegalRMI_IIOPType(bean, method.getReturnType())) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2412, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Verifies that a given method is RMI legal, but doesn't check the exceptions.
	 * This means that the method's argument and return values must be of valid types 
	 * for RMI/IIOP.
	 */
	public final void validateLegalRMIMethodWithoutExceptions(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		vc.terminateIfCancelled();
		validateLegalRMIMethodArguments(vc, bean, clazz, method);
		validateLegalRMIMethodReturnType(vc, bean, clazz, method);
	}
	
	/**
	 * Final check to see if required methods were detected.
	 */
	protected abstract void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException;
	
	public final void validateMethods(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz)  throws ValidationCancelledException, InvalidInputException, ValidationException {
		vc.terminateIfCancelled();

		// Whenever looping over an entire list, always use an 
		// iterator instead of a for loop, for performance reasons. 
		// If the list is an instance of a linked list, for example,
		// the get(i) call can take  a long time. 
		Method[] ejbMethods = clazz.listMethodExtended();
		Method method = null;
		List[] methodsExtendedList = getMethodsExtended(vc, bean, clazz);
		for(int i=0; i<ejbMethods.length; i++) {
			vc.terminateIfCancelled();
			method = ejbMethods[i];
			if (method == null) {
				// No point adding a validation message, because the user can't do anything.
				// Log this condition.
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("AValidateEJB.validateMethods()"); //$NON-NLS-1$
					entry.setText("On " + clazz + ", there is a null method."); //$NON-NLS-1$  //$NON-NLS-2$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}
			try {
				validateExistence(vc, bean, clazz, method, methodsExtendedList);
				validate(vc, bean, clazz, method, methodsExtendedList);
			}
			catch (InvalidInputException e) {
				reflectionWarning(vc, bean, clazz, method, e);
			}
		}
		validateMethodExists(vc, bean, clazz);
		vc.terminateIfCancelled();
	}
}
