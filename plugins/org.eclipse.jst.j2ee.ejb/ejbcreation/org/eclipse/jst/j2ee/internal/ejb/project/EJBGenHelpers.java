/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jem.internal.adapters.jdom.JDOMAdaptor;
import org.eclipse.jem.internal.adapters.jdom.JavaClassJDOMAdaptor;
import org.eclipse.jem.internal.adapters.jdom.JavaMethodJDOMAdaptor;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.common.jdt.internal.integration.WorkingCopyProvider;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.wst.validation.internal.operations.EnabledValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Insert the type's description here. Creation date: (10/12/00 10:20:00 PM)
 * 
 * @author: Steve Wasleski
 */
public final class EJBGenHelpers {
	// Navigator keys
//	private static final String ENTITY_KEY_ATTRIBUTE_FIELDS_AS_PARMS = "EntityKeyAttributeFieldsAsParms"; //$NON-NLS-1$
//	private static final String ENTITY_KEY_ROLE_FIELDS_AS_ROUND_PARMS = "EntityKeyRoleFieldsAsRoundParms"; //$NON-NLS-1$
//	private static final String ENTITY_KEY_ROLE_FIELDS_AS_BEAN_PARMS = "EntityKeyRoleFieldsAsBeanParms"; //$NON-NLS-1$
//	private static final String ENTITY_NONKEY_REQUIRED_ATTRIBUTE_FIELDS_AS_PARMS = "EntityNonKeyRequiredAttributeFieldsAsParms"; //$NON-NLS-1$
//	private static final String ENTITY_NON_KEY_REQ_ROLE_FIELDS_AS_BEAN_PARMS = "EntityNonKeyRequiredRoleFieldsAsBeanParms"; //$NON-NLS-1$
//	private static final String ENTITY_KEY_FIELDS_AS_FLAT_PARMS = "EntityKeyFieldsAsFlatParms"; //$NON-NLS-1$
//	private static final String ENTITY_KEY_FIELDS_AS_ROUND_PARMS = "EntityKeyFieldsAsRoundParms"; //$NON-NLS-1$
//	private static final String ENTITY_KEY_FIELDS_AS_BEAN_PARMS = "EntityKeyFieldsAsBeanParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_ROLE_FIELDS_AS_FLAT_PARMS = "EntityRequiredRoleFieldsAsFlatParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_ROLE_FIELDS_AS_ROUND_PARMS = "EntityRequiredRoleFieldsAsRoundParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_ROLE_FIELDS_AS_BEAN_PARMS = "EntityRequiredRoleFieldsAsBeanParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_FIELDS_AS_FLAT_PARMS = "EntityRequiredFieldsAsFlatParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_FIELDS_AS_ROUND_PARMS = "EntityRequiredFieldsAsRoundParms"; //$NON-NLS-1$
//	private static final String ENTITY_REQ_FIELDS_AS_BEAN_PARMS = "EntityRequiredFieldsAsBeanParms"; //$NON-NLS-1$

	private static final String A = "a"; //$NON-NLS-1$
	private static final String AN = "an"; //$NON-NLS-1$
	private static final char[] VOWELS = new char[]{'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};

	/**
	 * EJBGenHelpers constructor comment.
	 */
	private EJBGenHelpers() {
		super();
	}

	/**
	 * Add a vowel prefix to
	 * 
	 * @aString.
	 */
	public static final String asParameterName(String aString) {
		String name = firstAsUppercase(aString);
		name = withVowelPrefix(name);
		return name;
	}

	/**
	 * Return aString where the first character is uppercased.
	 */
	public static final String firstAsUppercase(String aString) {
		if (aString != null && aString.length() > 0 && !Character.isUpperCase(aString.charAt(0))) {
			char[] chars = aString.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return String.valueOf(chars);
		}
		return aString;
	}

	protected static EjbModuleExtensionHelper getEJBModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Returns the primitive wrapper for the type.
	 */
	public final static String getPrimitiveWrapper(String type) {
		if (type.equals("boolean")) //$NON-NLS-1$
			return "java.lang.Boolean"; //$NON-NLS-1$
		if (type.equals("byte")) //$NON-NLS-1$
			return "java.lang.Byte"; //$NON-NLS-1$
		if (type.equals("char")) //$NON-NLS-1$
			return "java.lang.Character"; //$NON-NLS-1$
		if (type.equals("short")) //$NON-NLS-1$
			return "java.lang.Short"; //$NON-NLS-1$
		if (type.equals("int")) //$NON-NLS-1$
			return "java.lang.Integer"; //$NON-NLS-1$
		if (type.equals("long")) //$NON-NLS-1$
			return "java.lang.Long"; //$NON-NLS-1$
		if (type.equals("float")) //$NON-NLS-1$
			return "java.lang.Float"; //$NON-NLS-1$
		if (type.equals("double")) //$NON-NLS-1$
			return "java.lang.Double"; //$NON-NLS-1$
		return null;
	}

	/**
	 * Returns the primitive wrapper for the type.
	 */
	public final static String getPrimitiveWrapperShortName(String type) {
		if (type.equals("boolean")) //$NON-NLS-1$
			return "Boolean"; //$NON-NLS-1$
		if (type.equals("byte")) //$NON-NLS-1$
			return "Byte"; //$NON-NLS-1$
		if (type.equals("char")) //$NON-NLS-1$
			return "Character"; //$NON-NLS-1$
		if (type.equals("short")) //$NON-NLS-1$
			return "Short"; //$NON-NLS-1$
		if (type.equals("int")) //$NON-NLS-1$
			return "Integer";//$NON-NLS-1$
		if (type.equals("long")) //$NON-NLS-1$
			return "Long"; //$NON-NLS-1$
		if (type.equals("float")) //$NON-NLS-1$
			return "Float"; //$NON-NLS-1$
		if (type.equals("double")) //$NON-NLS-1$
			return "Double"; //$NON-NLS-1$
		return null;
	}

	/**
	 * Returns true if the type is a primitive.
	 */
	public final static boolean isPrimitive(String type) {
		return (type.equals("boolean") //$NON-NLS-1$
					|| type.equals("byte") //$NON-NLS-1$
					|| type.equals("char") //$NON-NLS-1$
					|| type.equals("short") //$NON-NLS-1$
					|| type.equals("int") //$NON-NLS-1$
					|| type.equals("long") //$NON-NLS-1$
					|| type.equals("float") //$NON-NLS-1$
		|| type.equals("double")); //$NON-NLS-1$
	}

	public static boolean isRoot(EnterpriseBean anEJB) {
		if (anEJB != null) {
			EnterpriseBean superType = getSupertype(anEJB);
			if (superType == null)
				return true;
		}
		return false;
	}

	protected static EnterpriseBean getSupertype(EnterpriseBean ejb) {
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(ejb);
		return null;
	}

	protected static EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Return aString where with a prefixed vowel (a or an).
	 */
	public static final boolean isVowel(char aChar) {
		for (int i = 0; i < VOWELS.length; i++) {
			if (aChar == VOWELS[i])
				return true;
		}
		return false;
	}

	/**
	 * Return aString where with a prefixed vowel (a or an).
	 */
	public static final String withVowelPrefix(String aString) {
		if (aString != null && aString.length() > 0) {
			char first = aString.charAt(0);
			if (isVowel(first))
				return AN + aString;
			return A + aString;
		}
		return aString;
	}

	/**
	 * Method isArray.
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isArray(String type) {
		if (type != null && type.length() > 0) {
			int index = type.indexOf('[');
			return index > -1;
		}
		return false;
	}

	public static ICompilationUnit getCompilationUnit(JavaClass javaClass) {
		IType type = getType(javaClass);
		if (type != null)
			return type.getCompilationUnit();
		return null;
	}

	public static IType getType(JavaClass javaClass) {
		if (javaClass != null) {
			JavaClassJDOMAdaptor adaptor = (JavaClassJDOMAdaptor) EcoreUtil.getRegisteredAdapter(javaClass, ReadAdaptor.TYPE_KEY);
			if (adaptor != null)
				return adaptor.getSourceType();
		}
		return null;
	}

	public static IMethod getMethod(Method javaMethod) {
		if (javaMethod != null) {
			JavaMethodJDOMAdaptor adaptor = (JavaMethodJDOMAdaptor) EcoreUtil.getRegisteredAdapter(javaMethod, ReadAdaptor.TYPE_KEY);
			if (adaptor != null)
				return adaptor.getSourceMethod();
		}
		return null;
	}

	public static ICompilationUnit getWorkingCopy(JavaClass javaClass, WorkingCopyProvider provider, Object shell) throws org.eclipse.core.runtime.CoreException {
		ICompilationUnit cu = getCompilationUnit(javaClass);
		IStatus status = validateEdit(cu, shell);
		if (cu != null && (status == null || status.isOK()))
			return provider.getWorkingCopy(cu, false);
		return null;
	}

	public static IMethod getMethod(MethodElement me, WorkingCopyProvider provider, Map typeCache, Object shell) throws CoreException {
		return getMethod(me, provider, typeCache, shell, true);
	}

	public static IMethod getMethod(MethodElement me, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		if (me != null)
			return getMethod(me, me.getTypeJavaClass(), provider, typeCache, shell, useWorkingCopy);
		return null;
	}

	public static IMethod getMethod(MethodElement me, JavaClass typeClass, WorkingCopyProvider provider, Object shell, Map typeCache) throws CoreException {
		return getMethod(me, typeClass, provider, typeCache, shell, false);
	}

	public static IMethod getMethod(MethodElement me, JavaClass typeClass, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		IMethod method = null;
		if (me != null) {
			IType type = getType(typeClass);
			if (type == null)
				return null;
			if (type.isBinary() || !useWorkingCopy)
				method = getMethod(me, type, typeCache);
			if (method == null) {
				ICompilationUnit cu = getWorkingCopy(typeClass, provider, shell);
				if (cu != null)
					method = getMethod(me, cu.findPrimaryType(), typeCache);
			}
		}
		return method;
	}

	private static IMethod getMethod(MethodElement me, IType type, Map typeCache) throws CoreException {
		IMethod[] children = type.getMethods();
		IMethod method;
		for (int i = 0; i < children.length; i++) {
			method = children[i];
			if (method.getElementName().equals(me.getName())) {
				String[] typeNames = method.getParameterTypes();
				List parms = me.getMethodParams();
				if (typeNames.length != parms.size())
					continue;
				String parm;
				String resolvedName;
				boolean isMatch = true;
				for (int j = 0; j < typeNames.length; j++) {
					parm = (String) parms.get(j);
					resolvedName = JDOMAdaptor.typeNameFromSignature(typeNames[j], type, typeCache);
					if (!resolvedName.equals(parm)) {
						isMatch = false;
						break;
					}
				}
				if (isMatch)
					return method;
			}
		}
		return null;
	}

	public static IMethod[] getMethods(QueryMethod query, WorkingCopyProvider provider, Map typeCache, Object shell) throws CoreException {
		return getMethods(query, provider, typeCache, shell, false);
	}

	public static IMethod[] getMethods(QueryMethod query, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		if (query != null) {
			JavaClass[] typeClasses = query.getClientTypeJavaClasses();
			if (typeClasses.length == 1)
				return new IMethod[]{getMethod(query, provider, typeCache, shell, useWorkingCopy)};
			List methods = new ArrayList(4);
			IMethod method;
			for (int i = 0; i < typeClasses.length; i++) {
				method = getMethod(query, typeClasses[i], provider, typeCache, shell, useWorkingCopy);
				if (method != null)
					methods.add(method);
			}
			IMethod[] result = new IMethod[methods.size()];
			methods.toArray(result);
			return result;
		}
		return null;
	}

	public static String typeNameFromSignature(String sig, IMethod method, Map typeCache) {
		if (sig != null && method != null) {
			IType type = method.getDeclaringType();
			if (type != null)
				return JDOMAdaptor.typeNameFromSignature(sig, type, typeCache);
		}
		return null;
	}

	/**
	 * The sourceMethod will be updated with the contents from the updateMethod. If the signature
	 * has changed, the sourceMethod will be updated.
	 */
	public static void editMethod(IMethod sourceMethod, IDOMMethod updateMethod, WorkingCopyProvider provider, Object shell) throws JavaModelException, CoreException {
		if (sourceMethod != null && updateMethod != null && !sourceMethod.isBinary()) {
			IMethod wcMethod = getWorkingCopyMethod(sourceMethod, provider, shell);
			IType type = wcMethod.getDeclaringType();
			IJavaElement sibling = getSibling(wcMethod, type);
			String formattedSource = ToolFactory.createCodeFormatter().format(updateMethod.getContents(), 1, null, null) + "\n"; //$NON-NLS-1$
			wcMethod.delete(true, null);
			type.createMethod(formattedSource, sibling, true, null);
		}
	}

	public static void editMethods(IMethod[] sourceMethods, IDOMMethod[] updateMethods, WorkingCopyProvider provider, Object shell) throws JavaModelException, CoreException {
		if (sourceMethods != null) {
			for (int i = 0; i < sourceMethods.length; i++) {
				editMethod(sourceMethods[i], updateMethods[i], provider, shell);
			}
		}
	}

	public static IMethod getWorkingCopyMethod(IMethod sourceMethod, WorkingCopyProvider provider, Object shell) throws CoreException {
		if (sourceMethod != null && provider != null) {
			ICompilationUnit cu = sourceMethod.getCompilationUnit();
			IStatus status = validateEdit(cu, shell);
			if (!cu.isWorkingCopy() && (status == null || status.isOK())) {
				ICompilationUnit wc = provider.getWorkingCopy(sourceMethod.getCompilationUnit(), false);
				IType type = wc.findPrimaryType();
				IMethod[] methods = type.findMethods(sourceMethod);
				if (methods.length > 0 && methods[0] != null)
					return methods[0];
			}
		}
		return sourceMethod;
	}

	public static IJavaElement getSibling(IMethod sourceMethod, IType type) throws JavaModelException {
		if (sourceMethod != null && type != null) {
			IJavaElement[] elements = type.getChildren();
			int max = elements.length - 1;
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] == sourceMethod && i != max)
					return elements[i + 1];
			}
		}
		return null;
	}

	public static IStatus validateEdit(ICompilationUnit cu, Object obj) {
		if (cu != null && !cu.isWorkingCopy()) {
			try {
				IResource res = cu.getUnderlyingResource();
				if (res.getType() == IResource.FILE && res.isReadOnly())
					return ResourcesPlugin.getWorkspace().validateEdit(new IFile[]{(IFile) res}, obj);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
		return null;
	}

	public static void runWithJavaCoreWithDelayedValidation(IWorkspaceRunnable workspaceRunnable, IProject project) {
		boolean needsSuspending = !ValidatorManager.getManager().isSuspended(project);
		try {
			if (needsSuspending) {
				ValidatorManager.getManager().suspendValidation(project, true);
			}
			JavaCore.run(workspaceRunnable, null);
		} catch (CoreException ex) {
			Logger.getLogger().logError(ex);
		} finally {
			if (needsSuspending) {
				ValidatorManager.getManager().suspendValidation(project, false);
			}
		}

		if (needsSuspending) {
			EnabledValidatorsOperation validationOperation = new EnabledValidatorsOperation(project, true);
			validationOperation.run(new NullProgressMonitor());
		}
	}

}