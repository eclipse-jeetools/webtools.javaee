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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;

/**
 * @version 	1.0
 * @author
 */
public abstract class AHomeVRule extends AInterfaceTypeVRule {
	public Object getTarget(Object parent, Object clazz) {
		if(parent == null) {
			return null;
		}

		if((isRemote() & REMOTE) == REMOTE) {
			return ((EnterpriseBean)parent).getHomeInterface();
		}
		return ((EnterpriseBean)parent).getLocalHomeInterface();
	}
	
	public void validateFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		// By default, do nothing.
		// Let the entity rules override this method, and the session don't do anything
		// because validateMethodMustNotExist does what the sessions need.		
	}
	
	public boolean followsCreateExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException {
		return ValidationRuleUtility.throwsCreateException(bean, method);
	}
	
	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!ValidationRuleUtility.isLegalRMI_IIOPInheritance(clazz)) {
			// IWAD4334 = {0} must follow RMI-IIOP rules for remote interfaces. Read section 12.2.9 of the EJB 2.0 specification.
			// IWAD4217 = {0} must follow RMI-IIOP rules for remote interfaces. Read section 10.6.10 of the EJB 2.0 specification.
			// IWAD4062 = {0} must follow RMI-IIOP rules for remote interfaces. Read section 7.10.6 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2461, IEJBValidationContext.INFO, bean, clazz, this);
			vc.addMessage(message);
		}
		
		validateAppendixB(vc, bean, clazz);
	}
	
	public final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validate(vc, bean, clazz, method, methodsExtendedLists); // check application exceptions
		
		long methodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
		String nameOfMethodToFind = method.getName();
		boolean validateReturnTypeMatches = false;
		if((methodType & CREATE) == CREATE) {
			validateCreateMethod(vc, bean, clazz, method);
			
			StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_EJBC);
			// Strip the "c" off of "create" and replace with "ejbC"
			buffer.append(method.getName().substring(1));
			nameOfMethodToFind = buffer.toString();
		}
		else if((methodType & FIND) == FIND) {
			validateFindMethod(vc, bean, clazz, method);
			StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_EJBF);
			// Strip the "f" off of "find" and replace with "ejbF"
			buffer.append(method.getName().substring(1));
			nameOfMethodToFind = buffer.toString();
			
			validateReturnTypeMatches = true;
		}
		else if((methodType & HOME) == HOME) {
			// remove is a special method. See 9.5.3 for an example
	
			validateHomeMethod(vc, bean, clazz, method);
			
			// Section number stating this name convention?
			String methodName = method.getName();
			StringBuffer buffer = new StringBuffer(IMethodAndFieldConstants.PREFIX_EJBHOME);
			buffer.append(Character.toUpperCase(methodName.charAt(0)));
			buffer.append(method.getName().substring(1));
			nameOfMethodToFind = buffer.toString();
			
			validateReturnTypeMatches = true;
		}
		else {
			// Method shouldn't be here. ATypeVRule will catch.
			return;
		}
		
		if(!followRemoteExceptionRules(bean, method)) {
			IMethodType mType = MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedLists, this);
			if(mType == null) {
				// should never happen...
				Logger logger = vc.getMsgLogger();
				if(logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("AHomeVRule - validate method which must not exist"); //$NON-NLS-1$
					entry.setText("Method type is null: " + clazz.getQualifiedName() + "::" + method.getName() ); //$NON-NLS-1$  //$NON-NLS-2$
					logger.write(Level.FINEST, entry);
				}
			}
			else {
				IMessage message = MessageUtility.getUtility().getMessage(vc, mType.getMessageId_messageRemoteException(), getMessageRemoteExceptionSeverity(), bean, clazz, method, this);
				vc.addMessage(message);
			}
		}

		validateRMI_IIOPTypeRules(vc, bean, clazz, method, methodsExtendedLists, ((methodType & FIND) != FIND)); // do not check that the return type of a find method is serializable. If it's Collection or Set it won't be serializable, and if it doesn't return Collection, Set, or the component interface then the return type will be flagged.
		
		JavaHelpers local = getOverExposedLocalType(bean, clazz, method);
		if(local != null) {
			// IWAD4023 = {0} cannot be an argument or result type. Read section 6.7.2 of the EJB 2.0 specification.
			// IWAD4225 = This method must not expose the {0} type. Read section 10.6.10 of the EJB 2.0 specification.
			// IWAD4351 = This method must not expose the {0} type. Read section 12.2.9 of the EJB 2.0 specification.
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2466, IEJBValidationContext.INFO, bean, clazz, method, new String[]{local.getQualifiedName()}, this);
			vc.addMessage(message);
		}
		
		if(findMatchingMethod(methodType)) {
			validateMatchingBeanMethod(vc, bean, clazz, method, nameOfMethodToFind, validateReturnTypeMatches, methodsExtendedLists);
		}
	}
	
	public boolean findMatchingMethod(long methodType) {
		return true;
	}
	
	public final void validateMatchingBeanMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, String nameOfMethodToFind, boolean validateReturnTypeMatches, List[] methodsExtendedLists) throws ValidationCancelledException, InvalidInputException, ValidationException {
		Method match = ValidationRuleUtility.getMethod(method, nameOfMethodToFind, getBeanClassMethodsExtended(methodsExtendedLists));
		if(match == null) {
			IMethodType mType = MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedLists, this);
			if(mType == null) {
				// oops
				Logger logger = vc.getMsgLogger();
				if(logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("AHomeVRule - validate method"); //$NON-NLS-1$
					entry.setText("Method type is null: " + bean.getName() + "::" + clazz.getJavaName() + "::" + method.getMethodElementSignature()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					logger.write(Level.FINEST, entry);
				}
			}
			else {
				JavaClass beanClass = bean.getEjbClass();
				String beanClassName = (beanClass == null) ? IEJBValidatorConstants.NULL_BEAN_CLASS : beanClass.getJavaName();
				IMessage message = MessageUtility.getUtility().getMessage(vc, mType.getMessageId_messageMissingMatching(), IEJBValidationContext.WARNING, bean, clazz, method, new String[]{nameOfMethodToFind, beanClassName}, this);
				vc.addMessage(message);
			}
		}
		else {
			if(validateReturnTypeMatches) {
				validateMatchingReturnTypeMatches(vc, bean, clazz, method, match, methodsExtendedLists);
			}

			Set exceptions = ValidationRuleUtility.getNotSubsetExceptions(bean, match, method);
			Iterator eiterator = exceptions.iterator();
			while(eiterator.hasNext()) {
				JavaClass exception = (JavaClass)eiterator.next();
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2465, IEJBValidationContext.WARNING, bean, clazz, method, new String[]{exception.getJavaName(), match.getMethodElementSignature()}, this);
				vc.addMessage(message);
			}
		}
	}
	
	public void validateMatchingReturnTypeMatches(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method homeMethod, Method beanMethod, List[] methodsExtendedMethod) {
		if(!ValidationRuleUtility.isAssignableFrom(homeMethod.getReturnType(), beanMethod.getReturnType())) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2402, IEJBValidationContext.ERROR, bean, clazz, homeMethod, new String[]{beanMethod.getReturnType().getJavaName()}, this);
			vc.addMessage(message);
		}
	}
	
	public void validateCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException, ValidationException {
		if(!followsCreateExceptionRules(bean, method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2467, IEJBValidationContext.INFO, bean, clazz, method, this);
			vc.addMessage(message);
		}
		
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
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2402, IEJBValidationContext.ERROR, bean, clazz, method, new String[]{className}, this);
			vc.addMessage(message);
		}
	}
	
	public void validateHomeMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, ValidationException {
		// IWAD4350 = The return type must match the return type of {0}. Read section 12.2.9 of the EJB 2.0 specification.
		// The above check is done in the "validate(vc, bean, clazz, method) method.
	}

	protected boolean returnsComponentInterface(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException {
		JavaClass componentInterface = getComponentInterface(bean);
		return ValidationRuleUtility.isAssignableFrom(method.getReturnType(), componentInterface);
	}
	
	protected boolean returnsComponentInterfaceOrCollection(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws ValidationCancelledException, InvalidInputException {
		if(returnsComponentInterface(vc, bean, clazz, method)) {
			return true;
		}
		
		JavaHelpers returnParm = method.getReturnType();
		JavaHelpers javaUtilEnumeration = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_ENUMERATION, bean);
		if(ValidationRuleUtility.isAssignableFrom(returnParm, javaUtilEnumeration)) {
			return true;
		}
		
		JavaHelpers javaUtilCollection = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean);
		if(ValidationRuleUtility.isAssignableFrom(returnParm, javaUtilCollection)) {
			return true;
		}
		
		return false;
	}

	protected void validateAppendixB(IEJBValidationContext vc, EnterpriseBean bean, JavaClass thisHome) {
		// The Java inheritance structure must match the EJB inheritance structure.
		// e.g. if EJB B is a child of EJB A, then class B must be a child of class A.
		// B could be a grandchild (or great-grandchild or ...) of A.
		
		if(bean == null) {
			// bean has no supertype
			return;
		}
		EnterpriseBean supertype = getSuperType(bean);
		JavaClass parentHome = null;
		if (supertype != null) {
			// check this CMP's supertype
			parentHome = getHomeInterface(supertype);

			if(parentHome == null) {
				// child uses either local, or remote, but not both interfaces
				return;
			}
			
			// Home a Xchild of parent Home
			// In our EJB component inheritance structure, while it is legal for
			// a home to inherit from another home, (section B.2), it is not legal
			// for WSA component inheritance structure. 
			try {
				ValidationRuleUtility.isValidType(thisHome);
				ValidationRuleUtility.isValidType(parentHome);
				if (ValidationRuleUtility.isAssignableFrom(thisHome, parentHome)) {
					String[] msgParm = new String[] { thisHome.getQualifiedName(), parentHome.getQualifiedName()};
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2104, IEJBValidationContext.ERROR, bean, thisHome, msgParm, this);
					vc.addMessage(message);
				}
			}
			catch (InvalidInputException e) {
				String[] msgParm = { e.getJavaClass().getQualifiedName(), bean.getName()};
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2849, IEJBValidationContext.WARNING, bean, msgParm, this);
				vc.addMessage(message);
			}
		}
		
//		validateAppendixB(vc, supertype, parentHome);
	}
}
