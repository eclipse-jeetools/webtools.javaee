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

import java.util.Arrays;
import java.util.List;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;


public final class MethodUtility {
	private static MethodUtility _inst = null;
	private long[] _methodTypeIndex;
	private IMethodType[] _methodTypes;
	private long[] _fieldTypeIndex;
	private IFieldType[] _fieldTypes;
	
	private MethodUtility() {
		buildTypeList();
	}
	
	public static MethodUtility getUtility() {
		if(_inst == null) {
			_inst = new MethodUtility();
		}
		return _inst;
	}
	
	protected IMethodType[] getMethodTypes() {
		return _methodTypes;
	}

	public IFieldType getFieldType(long type) {
		int index = Arrays.binarySearch(_fieldTypeIndex, type);
		if(index > _fieldTypes.length) {
			return null;
		}
		
		if(index < 0) {
			return null;
		}
		
		if(_fieldTypes[index].getId() != type) {
			return null;
		}
		
		return _fieldTypes[index];
	}

	public IMethodType getMethodType(long type) {
		int index = Arrays.binarySearch(_methodTypeIndex, type);
		if(index > _methodTypes.length) {
			return null;
		}
		
		if(index < 0) {
			return null;
		}
		
		if(_methodTypes[index].getId() != type) {
			return null;
		}
		
		return _methodTypes[index];
	}
	
	public long getMethodTypeId(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists, IEJBType rule) {
		IMethodType mType = getMethodType(bean, clazz, method, methodsExtendedLists, rule);
		if(mType == null) {
			return rule.getDefaultMethodType();
		}
		
		return mType.getId();
	}

	public long getFieldTypeId(EnterpriseBean bean, JavaClass clazz, Field field, IEJBType rule) {
		IFieldType mType = getFieldType(bean, clazz, field, rule);
		if(mType == null) {
			return IMethodAndFieldConstants.EXCLUDED_FIELD;
		}
		
		return mType.getId();
	}
	
	public IFieldType getFieldType(EnterpriseBean bean, JavaClass clazz, Field field, IEJBType rule) {
		IFieldType[] knownFieldTypes = _fieldTypes;
		if(knownFieldTypes == null) {
			return null;
		}
		
		for(int i=0; i<knownFieldTypes.length; i++) {
			IFieldType mt = knownFieldTypes[i];
			if(mt == null) {
				continue;
			}
			
			if(mt.isFieldType(bean, clazz, field)) {
				return mt;
			}
		}
		
		// If it's not one of the recognized methods, which dynamically-named
		// method type is it?
		return null;
	}
	
	public IMethodType getMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists, IEJBType rule) {
		long[] knownMethodTypes = rule.getKnownMethodTypes();
		if(knownMethodTypes == null) {
			return null;
		}
		
		for(int i=0; i<knownMethodTypes.length; i++) {
			IMethodType mt = getMethodType(knownMethodTypes[i]);
			if(mt == null) {
				continue;
			}
			
			if(mt.isDefaultType()) {
				continue;
			}
			
			if(mt.isMethodType(bean, clazz, method, methodsExtendedLists)) {
				return mt;
			}
		}
		
		// If it's not one of the recognized methods, which dynamically-named
		// method type is it?
		return getMethodType(rule.getDefaultMethodType());
	}
	
	private void buildTypeList() {
		int typeCount = 29; // This is the number of identifiable methods in IMethodAndFieldConstants
		_methodTypeIndex = new long[typeCount];
		_methodTypes = new IMethodType[typeCount];
		int count = 0;
		
		int fieldTypeCount = 1; // This is the number of identifiable fields in IMethodAndFieldConstants
		_fieldTypeIndex = new long[fieldTypeCount];
		_fieldTypes = new IFieldType[fieldTypeCount];
		int fieldCount = 0;
		
		// The index of the type in the long[] is equal to the
		// index of the IMethodType in the IMethodType[]. By using
		// this technique instead of a HashMap, we don't need to
		// create an Integer every time we need to locate the 
		// IMethodType.
		_methodTypeIndex[count] = IMethodAndFieldConstants.ACCESSOR;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.ACCESSOR;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return ""; // this method will never be called //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_acc;
			}
			
			public String getMessageId_messageExists() {
				// Never statically check to see if this method exists
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ACCESSOR::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ACCESSOR::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ACCESSOR::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ACCESSOR::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				return false;
			}
			
			public String toString() {
				return String.valueOf(getId());
			}
		};
		
		_methodTypeIndex[count] = IMethodAndFieldConstants.BUSINESS;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.BUSINESS;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				if(method == null) {
					return ""; //$NON-NLS-1$
				}
				return method.getName();
			}
			
			public String getMessageId_messageMissing() {
				// Never check to see if a business method is missing
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "BUSINESS::getMessageId_messageMissing"; //$NON-NLS-1$
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if a business method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "BUSINESS::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_bus;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2504_business;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_business;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				// It's a business method if it's exposed on the component interface.
				// HACK ... depends on the fact that ABeanClassVRule, and its subtypes, are the only classes that will ever have a BUSINESS method.
				if(ValidationRuleUtility.getMethod(method, method.getName(), ABeanClassVRule.getRemoteComponentMethodsExtended(methodsExtendedLists)) != null) {
					return true;
				}
				
				if(ValidationRuleUtility.getMethod(method, method.getName(), ABeanClassVRule.getRemoteComponentMethodsExtended(methodsExtendedLists)) != null) {
					return true;
				}
				
				// internal helper method
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};
		
		_methodTypeIndex[count] = IMethodAndFieldConstants.CLINIT;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.CLINIT;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "clinit"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				// Never check to see if <clinit> is missing
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CLINIT::getMessageId_messageMissing"; //$NON-NLS-1$
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CLINIT::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CLINIT::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CLINIT::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CLINIT::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals("<clinit>")) { //$NON-NLS-1$
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};
		
		// Be careful to keep the order of these declarations the same as they are in 
		// IMethodAndFieldConstants. Each method type is an ascending number, and if they
		// go out of order, the binary search can't find the method type.
		_methodTypeIndex[count] = IMethodAndFieldConstants.CONSTRUCTOR;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.CONSTRUCTOR;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return clazz.getName();
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_constr;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.isConstructor()) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.CONSTRUCTOR_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.CONSTRUCTOR_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return clazz.getName() + "()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_constr;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR_NOPARM::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR_NOPARM::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR_NOPARM::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CONSTRUCTOR_NOPARM::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.isConstructor()) {
					// This class must define a public constructor which takes no parameters. Read section 7.10.1 of the EJB 2.0 specification.
					if(ValidationRuleUtility.isPublic(method)) {
						if(method.getParameters().size() == 0) {
							return true;
						}
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.CREATE;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.CREATE;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "create"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_create;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CREATE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_create;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2504_create;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_create;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_CREATE)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};
		
		_methodTypeIndex[count] = IMethodAndFieldConstants.CREATE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.CREATE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "create()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_create;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "CREATE_NOPARM::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_create;
			}
			
			public String getMessageId_messageMissingMatching() {
				return IMessagePrefixEjb20Constants.CHKJ2504_create;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_create;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_CREATE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};
		
		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBACTIVATE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBACTIVATE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbActivate()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbActivate;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBACTIVATE_NOPARM::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBACTIVATE_NOPARM::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBACTIVATE_NOPARM::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBACTIVATE_NOPARM::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBACTIVATE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBCREATE;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBCREATE;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbCreate"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbCreate;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBCREATE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbCreate;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBCREATE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_ejbCreate;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_EJBCREATE)) {
					return true;
				}
		
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBCREATE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBCREATE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbCreate()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbCreate;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBCREATE_NOPARM::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbCreate;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBCREATE_NOPARM::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBCREATE_NOPARM::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_EJBCREATE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBFIND;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBFIND;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				if(method == null) {
					return "ejbFind"; //$NON-NLS-1$
				}
				return method.getName();
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbFind;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
				return IMessagePrefixEjb20Constants.CHKJ2502_ejbFind;
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbFind;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBFIND::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_ejbFind;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJBFIND)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBFINDBYPRIMARYKEY;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBFINDBYPRIMARYKEY;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				StringBuffer buffer = new StringBuffer("ejbFindByPrimaryKey("); //$NON-NLS-1$
				String pkName = ""; //$NON-NLS-1$
				if(bean instanceof Entity) {
					JavaClass pk = ((Entity)bean).getPrimaryKey();
					pkName = ((pk == null) ? IEJBValidatorConstants.NULL_PRIMARY_KEY : pk.getQualifiedName());
				}
				buffer.append(pkName);
				buffer.append(")"); //$NON-NLS-1$
				return buffer.toString();
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbFindByPrimaryKey;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBFINDBYPRIMARYKEY::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbFind;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBFINDBYPRIMARYKEY::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBFINDBYPRIMARYKEY::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJBFIND)) {
					if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBFINDBYPRIMARYKEY)) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBHOME;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBHOME;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				if(method == null) {
					return "ejbHome"; //$NON-NLS-1$
				}
				return method.getName();
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbHome;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBHOME::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbHome;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBHOME::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_ejbHome;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJBHOME)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBLOAD_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBLOAD_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbLoad()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbLoad;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBLOAD::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBLOAD::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBLOAD::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBLOAD::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBLOAD)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBPASSIVATE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBPASSIVATE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbPassivate()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbPassivate;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPASSIVATE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPASSIVATE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPASSIVATE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPASSIVATE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBPASSIVATE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBPOSTCREATE;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBPOSTCREATE;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbPostCreate"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				// Can only dynamically check if ejbPostCreate is missing, 
				// based on what type of ejbCreate methods exist.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPOSTCREATE::getMessageId_messageMissing"; //$NON-NLS-1$
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPOSTCREATE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				return IMessagePrefixEjb20Constants.CHKJ2503_ejbPostCreate;
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPOSTCREATE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBPOSTCREATE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_EJBPOSTCREATE)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBREMOVE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBREMOVE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbRemove()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbRemove;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBREMOVE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBREMOVE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBREMOVE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBREMOVE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBREMOVE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBSELECT;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBSELECT;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				if(method == null) {
					return "ejbSelect"; //$NON-NLS-1$
				}
				return method.getName();
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbSelect;
			}
	
			public String getMessageId_messageExists() {
				return IMessagePrefixEjb20Constants.CHKJ2502_ejbSelect;
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSELECT::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSELECT::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSELECT::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_EJBSELECT)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EJBSTORE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EJBSTORE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "ejbStore()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_ejbStore;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSTORE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSTORE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSTORE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EJBSTORE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_EJBSTORE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.EQUALS;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.EQUALS;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "equals(Object)"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_equals;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EQUALS::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EQUALS::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EQUALS::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "EQUALS::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_EQUALS)) {
					JavaParameter[] parm = method.listParametersWithoutReturn();
					if(parm.length == 1) {
						try {
							JavaHelpers object = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, bean);
							JavaHelpers parmType = parm[0].getJavaType();
							
							if(object.equals(parmType)) {
								return true;
							}
						}
						catch(InvalidInputException e) {
							return false;
						}
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.FIND;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.FIND;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				if(method == null) {
					return "find"; //$NON-NLS-1$
				}
				return method.getName();
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_find;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "FIND::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_find;
			}
			
			public String getMessageId_messageMissingMatching() {
				return IMessagePrefixEjb20Constants.CHKJ2504_find;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_find;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_FIND)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.FINDBYPRIMARYKEY;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.FINDBYPRIMARYKEY;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "findByPrimaryKey"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_findByPrimaryKey;
			}
	
			public String getMessageId_messageExists() {
				return IMessagePrefixEjb20Constants.CHKJ2500_find;
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_find;
			} 
			
			public String getMessageId_messageMissingMatching() {
				return IMessagePrefixEjb20Constants.CHKJ2504_find;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				return IMessagePrefixEjb20Constants.CHKJ2500_find;
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().startsWith(IMethodAndFieldConstants.PREFIX_FIND)) {
					if(method.getName().startsWith(IMethodAndFieldConstants.METHODNAME_FINDBYPRIMARYKEY)) {
						return true;
					}
				}
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.FINALIZE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.FINALIZE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "finalize()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				// Never check to see if finalize is missing
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "FINALIZE::getMessageId_messageExists"; //$NON-NLS-1$
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
				return IMessagePrefixEjb20Constants.CHKJ2502_finalize;
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "FINALIZE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "FINALIZE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "FINALIZE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_FINALIZE)) {
					if(method.listParametersWithoutReturn().length ==0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.HASHCODE_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.HASHCODE_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "hashCode()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_hashCode;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "HASHCODE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "HASHCODE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "HASHCODE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "HASHCODE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_HASHCODE)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
		
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.HOME;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.HOME;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "home"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_home;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "HOME::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
				return IMessagePrefixEjb20Constants.CHKJ2503_home;
			}
			
			public String getMessageId_messageMissingMatching() {
				return IMessagePrefixEjb20Constants.CHKJ2504_home;
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
				return IMessagePrefixEjb20Constants.CHKJ2500_home;
			}
			
			public boolean isDefaultType() {
				return true;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				return true;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.ONMESSAGE;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.ONMESSAGE;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "onMessage"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_onMessage;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ONMESSAGE::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ONMESSAGE::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ONMESSAGE::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "ONMESSAGE::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_ONMESSAGE)) {
					JavaParameter[] parms = method.listParametersWithoutReturn();
					if(parms.length == 1) {
						try {
							JavaHelpers jmsMessage = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGE, bean);
							JavaHelpers parmType = parms[0].getJavaType();
							
							if(jmsMessage.equals(parmType)) {
								return true;
							}
						}
						catch(InvalidInputException e) {
							return false;
						}
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.SETENTITYCONTEXT;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.SETENTITYCONTEXT;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "setEntityContext"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_setEntityContext;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "SETENTITYCONTEXT::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "SETENTITYCONTEXT::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "SETENTITYCONTEXT::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "SETENTITYCONTEXT::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_SETENTITYCONTEXT)) {
					JavaParameter[] parm = method.listParametersWithoutReturn();
					if(parm.length == 1) {
						try {
							JavaHelpers entityContext = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVAX_EJB_ENTITYCONTEXT, bean);
							JavaHelpers parmType = parm[0].getJavaType();
							
							if(entityContext.equals(parmType)) {
								return true;
							}
						}
						catch(InvalidInputException e) {
							return false;
						}
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_methodTypeIndex[count] = IMethodAndFieldConstants.UNSETENTITYCONTEXT_NOPARM;
		_methodTypes[count++] = new IMethodType() {
			public long getId() {
				return IMethodAndFieldConstants.UNSETENTITYCONTEXT_NOPARM;
			}
			
			public String getMethodName(EnterpriseBean bean, JavaClass clazz, Method method) {
				return "unsetEntityContext()"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissing() {
				return IMessagePrefixEjb20Constants.CHKJ2050_unsetEntityContext;
			}
	
			public String getMessageId_messageExists() {
				// Never statically check to see if this method is present
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "UNSETENTITYCONTEXT::getMessageId_messageExists"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRemoteException() {
				// Don't need to know if this method throws a RemoteException.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "UNSETENTITYCONTEXT::getMessageId_messageRemoteException"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageMissingMatching() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "UNSETENTITYCONTEXT::getMessageId_messageMissingMatching"; //$NON-NLS-1$
			}
			
			public String getMessageId_messageRMI_IIOPParm() {
				// This method doesn't need a matching method.
//				return NO_MESSAGE_PREFIX;
				// Because it's difficult to find out which method has been called unexpectedly,
				// instead of returning the same string for all NO_MESSAGE_PREFIX cases,
				// instead return a message id which uniquely identifies which method was called.
				return "UNSETENTITYCONTEXT::getMessageId_messageRMI_IIOPParm"; //$NON-NLS-1$
			}
			
			public boolean isDefaultType() {
				return false;
			}
	
			public boolean isMethodType(EnterpriseBean bean, JavaClass clazz, Method method, List[] methodsExtendedLists) {
				if(method == null) {
					return false;
				}
				
				if(method.getName().equals(IMethodAndFieldConstants.METHODNAME_UNSETENTITYCONTEXT)) {
					if(method.listParametersWithoutReturn().length == 0) {
						return true;
					}
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

		_fieldTypeIndex[fieldCount] = IMethodAndFieldConstants.SERIALVERSIONUID;
		_fieldTypes[fieldCount++] = new IFieldType() {
			public long getId() {
				return IMethodAndFieldConstants.SERIALVERSIONUID;
			}
			
			public boolean isFieldType(EnterpriseBean bean, JavaClass clazz, Field field) {
				if(field == null) {
					return false;
				}
				
				if(field.getName().equals(IMethodAndFieldConstants.FIELDNAME_SERIALVERSIONUID)) {
					return true;
				}
				
				return false;
			}

			public String toString() {
				return String.valueOf(getId());
			}
		};

	}

}
