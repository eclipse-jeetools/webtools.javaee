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


import java.util.Arrays;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


public final class ClassUtility {
	public static long supertype_counter = 0x1; // used by IMethodAndFieldConstants.java
	private long[] _classTypeIndex;
	private AClassType[] _classNames;
	
	private abstract class AClassType {
		public abstract String getName(EnterpriseBean bean) throws InvalidInputException;
		public abstract long getId();

		public JavaHelpers getClass(EnterpriseBean bean) throws InvalidInputException {
			return ValidationRuleUtility.getType(getName(bean), bean);
		}
		
		/**
		 * Is this class type equal to the clazz parameter.
		 */
		public boolean equals(EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
			if(clazz == null) {
				return false;
			}
			
			JavaHelpers thisClass = getClass(bean);
			if(thisClass == null) {
				return false;
			}
			
			return clazz.equals(thisClass);
		}
	}
	
	private static ClassUtility _inst = null;
	
	private ClassUtility() {
		buildTypeList();
	}
	
	public static ClassUtility getUtility() {
		if(_inst == null) {
			_inst = new ClassUtility();
		}
		return _inst;
	}

	/**
	 * Return true if the clazz is the same as the class, identified by the "type"
	 * parameter, in the bean.
	 */
	public boolean isClassType(EnterpriseBean bean, JavaClass clazz, long type) throws InvalidInputException {
		AClassType ct = getClassType(type);
		if(ct == null) {
			// Unknown type
			return false;
		}
		
		return ct.equals(bean, clazz);
	}

	public String getClassName(EnterpriseBean bean, long type) throws InvalidInputException {
		AClassType ct = getClassType(type);
		if(ct == null) {
			return null;
		}
		
		return ct.getName(bean);
	}

	public AClassType getClassType(long type) {
		int index = Arrays.binarySearch(_classTypeIndex, type);
		if(index > _classNames.length) {
			return null;
		}
		
		if(index < 0) {
			return null;
		}
		
		if(_classNames[index].getId() != type) {
			return null;
		}
		
		return _classNames[index];
	}

	public JavaHelpers getSupertype(EnterpriseBean bean, long type) throws InvalidInputException {
		AClassType ct = getClassType(type);
		if(ct == null) {
			// No supertype
			return null;
		}
		
		return ct.getClass(bean);
	}
	
	private void buildTypeList() {
		int typeCount = 12; // This is the number of identifiable classes in ITypeConstants
		_classTypeIndex = new long[typeCount];
		_classNames = new AClassType[typeCount];
		int count = 0;
		
		// The index of the type in the long[] is equal to the
		// index of the IMethodType in the IMethodType[]. By using
		// this technique instead of a HashMap, we don't need to
		// create an Integer every time we need to locate the 
		// IMethodType.
		_classTypeIndex[count] = ITypeConstants.LOCAL_COMPONENT_INTERFACE;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				JavaHelpers clazz = getClass(bean);
				if(clazz == null) {
					return null;
				}
				return clazz.getQualifiedName();
			}
			
			public long getId() {
				return ITypeConstants.LOCAL_COMPONENT_INTERFACE;
			}
			
			public JavaHelpers getClass(EnterpriseBean bean) {
				if(bean == null) {
					return null;
				}
				
				if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface())) {
					JavaClass result = bean.getLocalInterface();
					try {
						ValidationRuleUtility.isValidTypeHierarchy(bean, result);
					}
					catch(InvalidInputException e) {
						return null;
					}
					return result;
				}
				
				return null;
			}
		};
		
		_classTypeIndex[count] = ITypeConstants.REMOTE_COMPONENT_INTERFACE;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				JavaHelpers clazz = getClass(bean);
				if(clazz == null) {
					return null;
				}
				return clazz.getQualifiedName();
			}
			
			public long getId() {
				return ITypeConstants.REMOTE_COMPONENT_INTERFACE;
			}
			
			public JavaHelpers getClass(EnterpriseBean bean) {
				if(bean == null) {
					return null;
				}
				
				if(bean.eIsSet(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface())) {
					JavaClass result = bean.getRemoteInterface();
					try {
						ValidationRuleUtility.isValidTypeHierarchy(bean, result);
					}
					catch(InvalidInputException e) {
						return null;
					}
					return result;
				}
				
				return null;
			}
		};
		
		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_ENTITYBEAN;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_ENTITYBEAN;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_ENTITYBEAN;
			}
		};
	
		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_EJBLOCALHOME;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALHOME;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_EJBLOCALHOME;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_EJBLOCALOBJECT;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALOBJECT;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_EJBLOCALOBJECT;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_EJBHOME;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_EJBHOME;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_EJBHOME;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_EJBOBJECT;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_EJBOBJECT;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_EJBOBJECT;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVA_LANG_OBJECT;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT;
			}
			
			public long getId() {
				return ITypeConstants.JAVA_LANG_OBJECT;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_MESSAGEDRIVENBEAN;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_MESSAGEDRIVENBEAN;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_MESSAGEDRIVENBEAN;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_JMS_MESSAGELISTENER;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGELISTENER;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_JMS_MESSAGELISTENER;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_SESSIONBEAN;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONBEAN;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_SESSIONBEAN;
			}
		};

		_classTypeIndex[count] = ITypeConstants.JAVAX_EJB_SESSIONSYNCHRONIZATION;
		_classNames[count++] = new AClassType() {
			public String getName(EnterpriseBean bean) {
				return ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION;
			}
			
			public long getId() {
				return ITypeConstants.JAVAX_EJB_SESSIONSYNCHRONIZATION;
			}
		};
	}
}
