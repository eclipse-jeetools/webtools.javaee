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
package org.eclipse.jst.j2ee.internal.ejb.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;


public class CMPHelper {
	
	public static final String GET = "get"; //$NON-NLS-1$
	public static final String SET = "set"; //$NON-NLS-1$
	protected static Comparator methodComparator = new Comparator() {
		/**
		 * @see Comparator#compare(Object, Object)
		 */
		public int compare(Object o1, Object o2) {
			Method m1 = (Method) o1;
			Method m2 = (Method) o2;
			String s1 = m1.getName();
			String s2 = m2.getName();
			if (s1 == null)
				return "".compareTo(s2); //$NON-NLS-1$
			return s1.compareTo(s2);
		}
	};
	
	public interface CMPFieldExistTester {
		boolean isExisting(Field aField);
		boolean isExisting(Method aMethod);
	};

	protected CMPHelper() {
		super();
	}
	
	/**
	 * For CMP 2.0 beans, the bean class is abstract and
	 * contains abstract get/set methods for the fields
	 * This infers the those fields based on the get/set methods
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMP20FieldDescriptors(JavaClass beanClass) {
		return getAvailableCMP20FieldDescriptors(beanClass, null);
	}
	
	/**
	 * For CMP 2.0 beans, the bean class is abstract and
	 * contains abstract get/set methods for the fields
	 * This infers the those fields based on the get/set methods
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMP20FieldDescriptors(JavaClass beanClass, CMPHelper.CMPFieldExistTester fieldTester) {
		if (beanClass == null)
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		Map getters = new HashMap();	
		List methods = beanClass.getPublicMethodsExtended();
		Collections.sort(methods, methodComparator);
		for (int i = 0; i < methods.size(); i++) {
			Method aMethod = (Method) methods.get(i);
			if (isGetter(aMethod) && aMethod.isAbstract() && !aMethod.isStatic() && 
				(fieldTester == null || fieldTester.isExisting(aMethod))) 
				getters.put(getFieldName(aMethod), aMethod);
			else if (isSetter(aMethod) && aMethod.isAbstract() && !aMethod.isStatic()) {
				String fieldName = getFieldName(aMethod);
				Method getter = (Method) getters.get(fieldName);
				if (typesAgree(getter, aMethod)) {
					String typeName = getReturnTypeName(getter);
					if (typeName != null && (fieldTester == null || fieldTester.isExisting(getter)))
						result.add(createFieldDescriptor(fieldName, typeName));
				}
			}
		}
		return result;	
	}
	
	/**
	 * For CMP 1.1 beans, the list is simply the fields, extended, 
	 * on the bean class
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMP11FieldDescriptors(JavaClass beanClass) {
		return getAvailableCMP11FieldDescriptors(beanClass, null);
	}
	
	/**
	 * For CMP 1.1 beans, the list is simply the fields, extended, 
	 * on the bean class
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMP11FieldDescriptors(JavaClass beanClass, CMPHelper.CMPFieldExistTester fieldTester) {
		if (beanClass == null)
			return Collections.EMPTY_LIST;
		List fields = beanClass.getFieldsExtended();
		List result = new ArrayList();
		for (int i = 0; i < fields.size(); i++) {
			Field aField = (Field) fields.get(i);
			if (!isPublic(aField) || aField.isStatic() || aField.isTransient() ||
			(fieldTester != null && !fieldTester.isExisting(aField)))
				continue;
			String name = aField.getName();
			String typeName = getTypeName(aField);
			if (typeName != null && name != null)
				result.add(createFieldDescriptor(name, typeName));
		}
		return result;
	}	
	
	/**
	 * Get a list of available cmp fields for the CMP bean; if the bean
	 * is v1.1, this is the fields on the bean class; if it is 2.0, this 
	 * is defined by the abstract get/set methods on the bean class
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMPFieldDescriptors(ContainerManagedEntity cmpBean) {
		return getAvailableCMPFieldDescriptors(cmpBean, null);
	}
	
	/**
	 * Get a list of available cmp fields for the CMP bean; if the bean
	 * is v1.1, this is the fields on the bean class; if it is 2.0, this 
	 * is defined by the abstract get/set methods on the bean class
	 * 
	 * @return java.util.List of {@link CMPFieldDescriptor}
	 */
	public static List getAvailableCMPFieldDescriptors(ContainerManagedEntity cmpBean, CMPHelper.CMPFieldExistTester fieldTester) {
		JavaClass beanClass = cmpBean.getEjbClass();
		if (beanClass == null)
			return Collections.EMPTY_LIST;
		switch(cmpBean.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID:
			case J2EEVersionConstants.EJB_1_1_ID:
				return getAvailableCMP11FieldDescriptors(beanClass, fieldTester); 
			case J2EEVersionConstants.EJB_2_0_ID:
			case J2EEVersionConstants.EJB_2_1_ID: default:
				return getAvailableCMP20FieldDescriptors(beanClass, fieldTester); 
		}  
	}
	
	/**
	 * Does the method name start with "get", and is the method a zero arg
	 * method, that does not return void?
	 */
	protected static boolean isGetter(Method aMethod) {
		String name = aMethod.getName();
		return name != null &&
			name.startsWith(GET) &&
			name.length() > 3 &&
			aMethod.listParametersWithoutReturn().length==0 &&
			!aMethod.isVoid();		
	}
	
	
	/**
	 * Does the method name start with "get", and is the method a one arg
	 * method, that is void
	 */
	protected static boolean isSetter(Method aMethod) {
		String name = aMethod.getName();
		return name != null &&
			name.startsWith(SET) &&
			name.length() > 3 &&
			aMethod.listParametersWithoutReturn().length == 1 &&
			aMethod.isVoid();
	}
			
	protected static String getFieldName(Method aMethod) {
		if (aMethod == null)
			return null;
			
		return getFieldName(aMethod.getName());
	}
	
	public static String getFieldName(String methodName) {
		if (methodName == null)
			return null;
		StringBuffer sb = new StringBuffer(methodName);
		sb.delete(0, 3);
		char lower = Character.toLowerCase(sb.charAt(0));
		sb.setCharAt(0, lower);
		return sb.toString();
	}
	
	protected static String getReturnTypeName(Method getter) {
		if (getter == null)
			return null;
		JavaHelpers retType = getter.getReturnType();
		return getTypeName(retType);
	}
	
	protected static String getTypeName(Field aField) {
		if (aField == null)
			return null;
		JavaHelpers type = (JavaHelpers)aField.getEType();
		return getTypeName(type);
	}
	
	protected static String getTypeName(JavaHelpers helpers) {
		if (helpers == null)
			return null;
		return helpers.getQualifiedName();
	}
	/**
	 * Precondition: setter must take one parameter, and getter must return
	 * a type, not void
	 */
	protected static boolean typesAgree(Method getter, Method setter) {
		if (getter == null || getter.isVoid() || setter == null)
			return false;
			
		JavaParameter[] parameters = setter.listParametersWithoutReturn();
		if (parameters.length != 1)
			return false;
			
		JavaParameter param = parameters[0];
		return param.getEType() != null &&
				param.getEType() == getter.getReturnType();							
	}
	
	protected static CMPFieldDescriptor createFieldDescriptor(String name, String typeName) {
		CMPFieldDescriptor desc = new CMPFieldDescriptor();
		desc.setName(name);
		desc.setType(typeName);
		return desc;
	}
	
	protected static boolean isPublic(Field field) {
		if (field == null)
			return false;
		return (JavaVisibilityKind.PUBLIC_LITERAL.getValue() == field.getJavaVisibility().getValue());
	}
		
		

}

