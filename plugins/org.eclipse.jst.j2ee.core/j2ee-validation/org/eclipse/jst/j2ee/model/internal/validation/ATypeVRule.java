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
import java.util.logging.Level;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.MessageLimitException;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;

import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;


/**
 * @version 1.0
 * @author
 */
public abstract class ATypeVRule extends AValidationRule implements IClassVRule, IEJBType {
	private static final int NO_FIELDS = 0;

	private long _methodList = NO_METHODS;
	private long _fieldList = NO_FIELDS;

	public final void validate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationException {
		vc.subtask(IEJBValidatorConstants.STATUS_VALIDATING, new String[]{((JavaClass) target).getJavaName()});

		Logger logger = vc.getMsgLogger();
		if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
			LogEntry entry = vc.getLogEntry();
			entry.setSourceID("EJB20Validator - validate"); //$NON-NLS-1$
			entry.setText(getClass().getName() + "::validate(" + targetParent + ", " + target); //$NON-NLS-1$ //$NON-NLS-2$
			logger.write(Level.FINEST, entry);
		}

		try {
			vc.terminateIfCancelled();

			EnterpriseBean bean = (EnterpriseBean) targetParent;
			JavaClass clazz = (JavaClass) target;

			try {
				//clean up old reflection error on the clazz that is getting validated
				vc.removeMessages(clazz);
				// Check that all of the types in the bean reflected correctly,
				// and that the types' parents reflected correctly too.
				ValidationRuleUtility.isValidTypeHierarchy(bean, clazz);
			} catch (InvalidInputException exc) {
				reflectionError(vc, bean, clazz);
				return;
			}

			if (ValidationRuleUtility.isUnnamedPackage(clazz.getJavaPackage())) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2041, IEJBValidationContext.INFO, bean, clazz, this);
				vc.addMessage(message);
			}

			try {
				validate(vc, bean, clazz);
				vc.terminateIfCancelled();

				List[] methodsExtendedList = getMethodsExtended(vc, bean, clazz);
				validate(vc, bean, clazz, clazz.listMethodExtended(), methodsExtendedList);
				vc.terminateIfCancelled();

				List[] fieldsExtendedList = getFieldsExtended(vc, bean, clazz);
				validate(vc, bean, clazz, clazz.listFieldExtended(), fieldsExtendedList);
				vc.terminateIfCancelled();

				validateSupertypes(vc, bean, clazz);
				vc.terminateIfCancelled();

				validateMethodsWhichMustExist(vc, bean, clazz);
				vc.terminateIfCancelled();
			} catch (InvalidInputException exc) {
				reflectionError(vc, bean, clazz);
				return;
			}
		} catch (ValidationCancelledException exc) {
			// remove any existing messages since they may be faulty
		}
	}

	protected final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field[] fields, List[] fieldsExtendedLists) throws ValidationException {
		try {
			vc.terminateIfCancelled();

			Field field = null;
			for (int i = 0; i < fields.length; i++) {
				vc.terminateIfCancelled();
				field = fields[i];
				if (field == null) {
					Logger logger = vc.getMsgLogger();
					if (logger != null && logger.isLoggingLevel(Level.FINE)) {
						LogEntry entry = vc.getLogEntry();
						entry.setSourceID("EJB20Validator - validate fields"); //$NON-NLS-1$
						entry.setText("A field is null on " + clazz.getQualifiedName()); //$NON-NLS-1$
						logger.write(Level.FINE, entry);
					}
					continue;
				}
				try {
					Logger logger = vc.getMsgLogger();
					if (logger.isLoggingLevel(Level.FINEST)) {
						LogEntry entry = vc.getLogEntry();
						entry.setSourceID("EJB20Validator - validate fields"); //$NON-NLS-1$
						entry.setText("Validating field " + field.getName() + "on clazz " + clazz + " and isValid returns " + isValid(bean, clazz, field)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						logger.write(Level.FINEST, entry);
					}

					if (isValid(bean, clazz, field)) {
						register(vc, bean, clazz, field, fieldsExtendedLists);
						validate(vc, bean, clazz, field, fieldsExtendedLists);
					}
				} catch (InvalidInputException e) {
					reflectionWarning(vc, bean, clazz, field, e);
				}
			}
			vc.terminateIfCancelled();
		} catch (ValidationCancelledException exc) {
			// remove any existing messages since they may be faulty
		}

	}

	protected final boolean isValid(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws InvalidInputException {
		if (isOwnedByBase(bean, method)) {
			return false;
		}

		return ((getMethodType(bean, clazz, method, methodsExtendedLists) & EXCLUDED_METHOD) != EXCLUDED_METHOD);
	}

	protected final boolean isOwnedByBase(EnterpriseBean bean, Field field) throws InvalidInputException {
		if (field == null) {
			return false;
		}

		long[] baseTypes = getBaseTypes();
		if (baseTypes == null) {
			// no parents
			return false;
		}

		for (int i = 0; i < baseTypes.length; i++) {
			if (ClassUtility.getUtility().isClassType(bean, field.getJavaClass(), baseTypes[i])) {
				return true;
			}
		}
		return false;
	}

	protected final boolean isOwnedByBase(EnterpriseBean bean, Method method) throws InvalidInputException {
		if (method == null) {
			return false;
		}

		long[] baseTypes = getBaseTypes();
		if (baseTypes == null) {
			// no parents
			return false;
		}

		for (int i = 0; i < baseTypes.length; i++) {
			if (ClassUtility.getUtility().isClassType(bean, method.getJavaClass(), baseTypes[i])) {
				return true;
			}
		}
		return false;
	}

	protected final boolean isValid(EnterpriseBean bean, JavaClass clazz, Field field) throws InvalidInputException {
		if (isOwnedByBase(bean, field)) {
			return false;
		}

		return ((getFieldType(bean, clazz, field) & EXCLUDED_FIELD) != EXCLUDED_FIELD);
	}

	protected final void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method[] methods, List[] methodsExtendedLists) throws ValidationException {
		try {
			vc.terminateIfCancelled();

			Method method = null;
			for (int i = 0; i < methods.length; i++) {
				vc.terminateIfCancelled();
				method = methods[i];
				if (method == null) {
					// No point adding a validation message, because the user can't do anything.
					// Log this condition.
					Logger logger = vc.getMsgLogger();
					if (logger != null && logger.isLoggingLevel(Level.FINE)) {
						LogEntry entry = vc.getLogEntry();
						entry.setSourceID("EJB20Validator - validate methods"); //$NON-NLS-1$
						entry.setText("On " + clazz.getQualifiedName() + ", there is a null method."); //$NON-NLS-1$ //$NON-NLS-2$
						logger.write(Level.FINE, entry);
					}
					continue;
				}
				try {
					Logger logger = vc.getMsgLogger();
					if (logger.isLoggingLevel(Level.FINEST)) {
						LogEntry entry = vc.getLogEntry();
						entry.setSourceID("EJB20Validator - validate methods"); //$NON-NLS-1$
						entry.setText("Validating method " + method.getMethodElementSignature() + " on clazz " + clazz + " and isValid returns " + isValid(bean, clazz, method, methodsExtendedLists)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						logger.write(Level.FINEST, entry);
					}

					if (isValid(bean, clazz, method, methodsExtendedLists)) {
						register(vc, bean, clazz, method, methodsExtendedLists);
						if (isMethodAllowedToExist(bean, clazz, method, methodsExtendedLists)) {
							validate(vc, bean, clazz, method, methodsExtendedLists);
						} else {
							validateMethodWhichMustNotExist(vc, bean, clazz, method, methodsExtendedLists);
						}
					} else {
						if (!isOwnedByBase(bean, method)) {
							if ((MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this) & EXCLUDED_METHOD) == EXCLUDED_METHOD) {
								// unidentified method excluded
								IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2502, IEJBValidationContext.INFO, bean, clazz, method, new String[]{method.getName()}, this);
								vc.addMessage(message);
							}
						}
					}
				} catch (InvalidInputException e) {
					reflectionWarning(vc, bean, clazz, method, e);
				}

			}
			vc.terminateIfCancelled();
		} catch (ValidationCancelledException exc) {
			// remove any existing messages since they may be faulty
		}

	}

	public final void register(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, List[] fieldsExtendedList) throws InvalidInputException, ValidationCancelledException, ValidationException {
		_fieldList = (_fieldList | getFieldType(bean, clazz, field));
	}

	private boolean isDefaultConstructor(long methodType) {
		return ((methodType & IMethodAndFieldConstants.CONSTRUCTOR_NOPARM) == IMethodAndFieldConstants.CONSTRUCTOR_NOPARM);
	}

	private boolean doesPublicDefaultConstructorExist() {
		if ((_methodList & IMethodAndFieldConstants.CONSTRUCTOR_NOPARM) == IMethodAndFieldConstants.CONSTRUCTOR_NOPARM) {
			return true;
		}

		return false;
	}

	private boolean isDefaultConstructorInsertedByCompiler() {
		// If the default constructor is not in the list, and if there are no constructors,
		// the Java compiler will insert a default constructor.
		if ((_methodList & IMethodAndFieldConstants.CONSTRUCTOR) == IMethodAndFieldConstants.CONSTRUCTOR) {
			// There is a constructor, so return false.
			return false;
		}

		return true;
	}

	protected final boolean isMethodAllowedToExist(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
		long[] methods = getMethodsWhichMustNotExist();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				long methodType = methods[i];
				long existingMethodType = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedLists, this);
				if ((existingMethodType & methodType) == methodType) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * @see IValidationRule#reset()
	 */
	public void validateMethodsWhichMustExist(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException, ValidationCancelledException, ValidationException {
		long[] methods = getMethodsWhichMustExist();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				long methodType = methods[i];
				boolean emitWarning = false;
				if (isDefaultConstructor(methodType)) {
					if (!doesPublicDefaultConstructorExist() && !isDefaultConstructorInsertedByCompiler()) {
						emitWarning = true;
					}
				} else {
					emitWarning = !exists(methodType);
				}

				if (emitWarning) {
					// IWAD4041 = This class must define a public constructor which takes no
					// parameters. Read section 7.10.1 of the EJB 2.0 specification.
					// IWAD4142 = {0} must exist. Read section 10.5.2 of the EJB 2.0 specification.
					// IWAD4173 = {0} must exist. Read section 10.6.2 of the EJB 2.0 specification.
					// IWAD4271 = {0} must exist. Read section 12.1.4.1 of the EJB 2.0
					// specification.
					// IWAD4290 = {0} must exist. Read section 12.2.2 of the EJB 2.0 specification.
					// IWAD4388 = {0} must exist. Read section 15.7.2 of the EJB 2.0 specification.
					IMethodType mType = MethodUtility.getUtility().getMethodType(methodType);
					if (mType == null) {
						// ummmm....oops. This case should never happen.
						Logger logger = vc.getMsgLogger();
						if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
							LogEntry entry = vc.getLogEntry();
							entry.setSourceID("EJB20Validator - validate methods which must exist"); //$NON-NLS-1$
							entry.setText("Method type is null: " + methodType); //$NON-NLS-1$
							logger.write(Level.FINEST, entry);
						}
						continue;
					}

					IMessage message = MessageUtility.getUtility().getMessage(vc, mType.getMessageId_messageMissing(), IEJBValidationContext.WARNING, bean, clazz, new String[]{mType.getMethodName(bean, clazz, null)}, this);
					vc.addMessage(message);
				}
			}
		}
	}

	public final void validateMethodWhichMustNotExist(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) throws InvalidInputException, ValidationCancelledException, ValidationException {
		IMethodType mType = MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedLists, this);
		if (mType == null) {
			// what happened?
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = vc.getLogEntry();
				entry.setSourceID("EJB20Validator - validate method which must not exist"); //$NON-NLS-1$
				entry.setText("Method type is null: " + MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedLists, this)); //$NON-NLS-1$
				logger.write(Level.FINEST, entry);
			}
			return;
		}


		IMessage message = MessageUtility.getUtility().getMessage(vc, mType.getMessageId_messageExists(), IEJBValidationContext.INFO, bean, clazz, method, new String[]{mType.getMethodName(bean, clazz, method)}, this);
		vc.addMessage(message);
	}

	public abstract long getFieldType(EnterpriseBean bean, JavaClass clazz, Field field);

	public abstract long[] getBaseTypes();

	public abstract int getMessageRemoteExceptionSeverity(); // is a missing (or present)
															 // RemoteException a warning or an
															 // error? See ILocalType and
															 // IRemoteType.

	public final long getMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) {
		if (method == null) {
			return EXCLUDED_METHOD;
		}
		long id = MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedList, this);
		if ((id & CLINIT) == CLINIT) {
			return EXCLUDED_METHOD;
		}
		return id;
	}

	public final void register(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList) throws InvalidInputException, ValidationCancelledException, ValidationException {
		_methodList = (_methodList | MethodUtility.getUtility().getMethodTypeId(bean, clazz, method, methodsExtendedList, this));
	}

	public final void validateSupertypes(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException, ValidationCancelledException, ValidationException {
		validateShouldBeSuperTypes(vc, bean, clazz);
		validateShouldNotBeSuperTypes(vc, bean, clazz);
	}

	/**
	 * @param vc
	 * @param bean
	 * @param clazz
	 * @throws InvalidInputException
	 */
	protected void validateShouldNotBeSuperTypes(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		long[] types;
		// Test for types which can be supertypes, though it's either not recommended or should be
		// coded with caution
		types = getShouldNotBeSupertypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				JavaHelpers superType = ClassUtility.getUtility().getSupertype(bean, types[i]);
				if (superType == null) {
					// no supertype
					continue;
				}

				if (ValidationRuleUtility.isAssignableFrom(clazz, superType)) {
					// IWAD4179 = Because this class implements {0}, "this" must not be passed as a
					// method argument or result. Read section 10.6.2 of the EJB 2.0 specification.
					// IWAD4292 = Because {0} implements {1}, "this" must not be passed as a method
					// argument or result. Read section 12.2.2 of the EJB 2.0 specification.
					// IWAD4043 = Because {0} implements {1}, "this" must not be passed as a method
					// argument or result. Read section 7.10.2 of the EJB 2.0 specification.
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2040, IEJBValidationContext.INFO, bean, clazz, this);
					vc.addMessage(message);
				}
			}
		}
	}

	/**
	 * @param vc
	 * @param bean
	 * @param clazz
	 * @throws InvalidInputException
	 */
	protected void validateShouldBeSuperTypes(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		long[] types = getSupertypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				JavaHelpers superType = ClassUtility.getUtility().getSupertype(bean, types[i]);
				if (superType == null) {
					// no supertype
					continue;
				}

				if (!ValidationRuleUtility.isAssignableFrom(clazz, superType)) {
					// IWAD4026 = This class must implement javax.ejb.SessionBean. Read section
					// 7.5.1, 7.10.2 of the EJB 2.0 specification.
					// IWAD4379 = This class must implement javax.ejb.MessageDrivenBean. Read
					// section 15.4.1, 15.7.2 of the EJB 2.0 specification.
					// IWAD4380 = This class must implement javax.jms.MessageListener. Read section
					// 15.4.1, 15.7.2 of the EJB 2.0 specification.
					// IWAD4170 = This class must implement java.ejb.EntityBean. Read section 10.6.2
					// of the EJB 2.0 specification.
					// IWAD4286 = This class must implement java.ejb.EntityBean. Read section 12.2.2
					// of the EJB 2.0 specification.
					IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2017, IEJBValidationContext.ERROR, bean, clazz, new String[]{superType.getJavaName()}, this);
					vc.addMessage(message);
				}
			}
		}
	}

	public void reset() {
		super.reset();
		_methodList = NO_METHODS;
		_fieldList = NO_FIELDS;
	}

	protected final void reflectionError(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws MessageLimitException {
		IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2907, IEJBValidationContext.ERROR, bean, clazz, new String[]{clazz.getQualifiedName()}, this);
		vc.addMessage(message);
	}

	protected final void reflectionWarning(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field, InvalidInputException exc) throws MessageLimitException {
		vc.removeMessages(field);
		vc.removeMessages(clazz, field.getName());
		IMessage message = getReflectionWarning(vc, exc, bean, clazz, field);
		vc.addMessage(message);
	}

	protected final void reflectionWarning(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, InvalidInputException exc) throws MessageLimitException {
		vc.removeMessages(method);
		vc.removeMessages(clazz, method.getSignature());
		IMessage message = getReflectionWarning(vc, exc, bean, clazz, method);
		vc.addMessage(message);
	}

	protected final IMessage getReflectionWarning(IEJBValidationContext vc, InvalidInputException exc, EnterpriseBean bean, JavaClass clazz, EObject fieldOrMethod) {
		if (exc == null) {
			return null;
		}

		JavaClass badClazz = exc.getJavaClass();
		String typeName = (badClazz == null) ? "?" : badClazz.getQualifiedName(); //$NON-NLS-1$
		IMessage message = null;

		if (fieldOrMethod instanceof Field) {
			String name = ((Field) fieldOrMethod).getName();
			message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2433, IEJBValidationContext.WARNING, bean, clazz, (Field) fieldOrMethod, new String[]{name, typeName}, this);
		} else if (fieldOrMethod instanceof Method) {
			String name = ((Method) fieldOrMethod).getMethodElementSignature();
			message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2433, IEJBValidationContext.WARNING, bean, clazz, (Method) fieldOrMethod, new String[]{name, typeName}, this);
		} else {
			String name = ((XMIResource) fieldOrMethod.eResource()).getID(fieldOrMethod);
			message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2433, IEJBValidationContext.WARNING, bean, clazz, new String[]{name, typeName}, this);
		}
		if (badClazz == null) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = vc.getLogEntry();
				entry.setSourceID("getMessageId(IEJBValidationContext, InvalidInputException, boolean)"); //$NON-NLS-1$
				entry.setText("InvalidInputException thrown on unknown class"); //$NON-NLS-1$
				entry.setTargetException(exc);
				logger.write(Level.FINEST, entry);
			}
		}

		return message;
	}


	public final JavaClass getComponentInterface(EnterpriseBean bean) {
		if ((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			return bean.getRemoteInterface();
		}
		return bean.getLocalInterface();
	}

	public final boolean exists(long methodType) {
		return ((_methodList & methodType) == methodType);
	}

	public final JavaClass getHomeInterface(EnterpriseBean bean) {
		if ((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			return bean.getHomeInterface();
		}
		return bean.getLocalHomeInterface();
	}

	public boolean followRemoteExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException, ValidationCancelledException {
		if ((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			// must throw RemoteException or one of its parents (IOException or Exception)
			return ValidationRuleUtility.throwsRemoteExceptionOrParent(bean, method);
		} else if ((isRemote() & IEJBType.LOCAL) == IEJBType.LOCAL) {
			// must not throw RemoteException
			return ValidationRuleUtility.doesNotThrowRemoteException(bean, method);
		}

		return true;
	}

	public final void validateRMI_IIOPTypeRules(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedList, boolean checkReturnType) throws InvalidInputException, MessageLimitException {
		if ((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			JavaParameter[] parms = method.listParametersWithoutReturn();

			for (int i = 0; i < parms.length; i++) {
				JavaParameter parm = parms[i];
				if (!ValidationRuleUtility.isLegalRMI_IIOPType(bean, parm.getJavaType())) {
					IMethodType mt = MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedList, this);
					if (mt == null) {
						// log
					} else {
						IMessage message = MessageUtility.getUtility().getMessage(vc, mt.getMessageId_messageRMI_IIOPParm(), IEJBValidationContext.INFO, bean, clazz, method, new String[]{parm.getJavaType().getJavaName()}, this); // Can't
																																																								  // use
																																																								  // the
																																																								  // name
																																																								  // of
																																																								  // the
																																																								  // field,
																																																								  // because
																																																								  // when
																																																								  // it's
																																																								  // the
																																																								  // return
																																																								  // type
																																																								  // of
																																																								  // the
																																																								  // method,
																																																								  // it's
																																																								  // confusing
																																																								  // to
																																																								  // see
																																																								  // "result"
																																																								  // as
																																																								  // the
																																																								  // name
																																																								  // of
																																																								  // the
																																																								  // field.
						vc.addMessage(message);
					}
				}
			}

			if (checkReturnType) {
				if (!ValidationRuleUtility.isLegalRMI_IIOPType(bean, method.getReturnType())) {
					IMethodType mt = MethodUtility.getUtility().getMethodType(bean, clazz, method, methodsExtendedList, this);
					if (mt == null) {
						// log
					} else {
						IMessage message = MessageUtility.getUtility().getMessage(vc, mt.getMessageId_messageRMI_IIOPParm(), IEJBValidationContext.INFO, bean, clazz, method, new String[]{method.getReturnType().getJavaName()}, this); // Can't
																																																									  // use
																																																									  // the
																																																									  // name
																																																									  // of
																																																									  // the
																																																									  // field,
																																																									  // because
																																																									  // when
																																																									  // it's
																																																									  // the
																																																									  // return
																																																									  // type
																																																									  // of
																																																									  // the
																																																									  // method,
																																																									  // it's
																																																									  // confusing
																																																									  // to
																																																									  // see
																																																									  // "result"
																																																									  // as
																																																									  // the
																																																									  // name
																																																									  // of
																																																									  // the
																																																									  // field.
						vc.addMessage(message);
					}
				}
			}
		} else {
			// don't need to check
			return;
		}
	}

	public final boolean followRMI_IIOPInheritanceRules(EnterpriseBean bean, JavaClass clazz) {
		if ((isRemote() & IEJBType.REMOTE) == IEJBType.REMOTE) {
			return ValidationRuleUtility.isLegalRMI_IIOPInheritance(clazz);
		}
		return true;
	}

	public void validateSerialVersionUID(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Field field) {
		/*
		 * For now, do nothing. In future, check if the field is public & static?
		 */
	}

}
