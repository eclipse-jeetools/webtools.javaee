/***********************************************************************
 * Copyright (c) 2008, 2019 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.common;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.ResAuthType;
import org.eclipse.jst.javaee.core.ResSharingScopeType;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.SessionBean;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public abstract class AbstractAnnotationFactory {

	/**
	 * @param value
	 * @return true if "value" is an array of objects.
	 */
	protected static boolean isArrayOfObject(Object value) {
		return Object[].class.isInstance(value);
	}

	protected Object getAnnotatedValue(String name, IMemberValuePair[] memberValuePairs) throws JavaModelException {
		for (IMemberValuePair pair : memberValuePairs) {
			if (name.equals(pair.getMemberName())) {
				return pair.getValue();
			}
		}
		return null;
	}

	protected boolean containsImport(ICompilationUnit unit, String importt) throws JavaModelException {
		for (IImportDeclaration declaration : unit.getImports()) {
			if (declaration.getElementName().equals(importt))
				return true;
		}
		return false;
	}

	/**
	 * Resolve the given <code>toResolve</code> string to an IType in the
	 * context of <code>declaringType</code>
	 * 
	 * @param declaringType
	 * @param toResolve
	 *            the type that should be resolved.
	 * @return the resolved type or <code>null</code> if such type can not be
	 *         resolved. Returns <code>null</code> if toResolve is
	 *         <code>null</code>
	 * @throws JavaModelException
	 */
	protected IType resolveType(IType declaringType, String toResolve) throws JavaModelException {
		if (toResolve == null)
			return null;
		String[][] fullTypeName = declaringType.resolveType(toResolve);
		if (fullTypeName != null)
			return declaringType.getJavaProject().findType(fullTypeName[0][0], fullTypeName[0][1]);
		return null;
	}

	protected void processEjbAnnotation(IAnnotation annotation, List<EjbLocalRef> localRefs, IMember member,
			Collection<IType> dependedTypes) throws JavaModelException {
		int memberType = member.getElementType();
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();

		String beanInterfaceValue = (String) getAnnotatedValue("beanInterface", pairs); //$NON-NLS-1$
		beanInterfaceValue = internalProcessInjection(beanInterfaceValue, member, dependedTypes);
		if (beanInterfaceValue == null)
			return;

		/*
		 * The name of the reference should be the value of the "name"
		 * attribute. If there is no "name" attribute then the name of the
		 * reference is the qualified name of the member. Check this at
		 * Enterprise Java Beans, 3.0, Section 14.1.5.3
		 */
		String refName = (String) getAnnotatedValue("name", pairs); //$NON-NLS-1$
		if (refName == null) {
			refName = getMemberQualifiedName(member);
		}
		EjbLocalRef ref = JavaeeFactory.eINSTANCE.createEjbLocalRef();
		ref.setEjbRefName(refName);
		localRefs.add(ref);
		ref.setLocal(beanInterfaceValue);
		ref.setLocalHome(beanInterfaceValue);
		ref.setEjbLink((String) getAnnotatedValue("beanName", pairs)); //$NON-NLS-1$
		ref.setMappedName((String) getAnnotatedValue("mappedName", pairs)); //$NON-NLS-1$
		if (memberType == IJavaElement.METHOD || memberType == IJavaElement.FIELD) {
			createInjectionTarget(refName, ref.getInjectionTargets(), annotation);
		}
	}

	private void createInjectionTarget(String refName, List<InjectionTarget> injectionTargets, IAnnotation annotation) {
		InjectionTarget injectionTarget = JavaeeFactory.eINSTANCE.createInjectionTarget();
		int index = refName.indexOf('/');
		if (index != -1) {
			injectionTarget.setInjectionTargetClass(refName.substring(0, index));
			injectionTarget.setInjectionTargetName(refName.substring(index + 1));
		} else {
			injectionTarget.setInjectionTargetName(refName);
			injectionTarget.setInjectionTargetClass("");//$NON-NLS-1$
		}
		injectionTargets.add(injectionTarget);
	}

	/**
	 * Resource annotation can be placed on class, method, field.
	 * 
	 * Checks are made if the resource annotation is valid.
	 * <p>
	 * If on class there should be a "type" attribute. If on method the method
	 * must have one param with type that is an interface. If on field the field
	 * type must be an interface.
	 * 
	 * If the type of the method/field can not be resolved the result will
	 * contain the unresolved value.
	 * 
	 * In case the type of method/field is array, wildcard, simple type this is
	 * not a place for the annotation.
	 * </p>
	 * 
	 * <p>
	 * In case of method/field the type specified using the "type" attribute has
	 * a higher priority that the method/field type.
	 * </p>
	 * 
	 * <p>
	 * Only resolved types are added to dependedTypes
	 * </p>
	 * 
	 * @param sessionBean
	 * @param member
	 * @param annotation
	 * @param dependedTypes
	 * @throws JavaModelException
	 */
	protected void processResourceRefAnnotation(IAnnotation annotation, List<ResourceRef> resourceRefs, IMember member,
			Collection<IType> dependedTypes) throws JavaModelException {

		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		String specifiedType = (String) getAnnotatedValue("type", pairs); //$NON-NLS-1$
		specifiedType = internalProcessInjection(specifiedType, member, dependedTypes);
		if (specifiedType == null)
			return;
		String refName = (String) getAnnotatedValue("name", pairs); //$NON-NLS-1$
		if (refName == null)
			refName = getMemberQualifiedName(member);
		ResourceRef ref = JavaeeFactory.eINSTANCE.createResourceRef();
		ref.setResRefName(refName);
		ref.setResType(specifiedType);
		ref.setMappedName((String) getAnnotatedValue("mappedName", pairs)); //$NON-NLS-1$
		String description = (String) getAnnotatedValue("description", pairs); //$NON-NLS-1$
		if (description != null) {
			Description desc = JavaeeFactory.eINSTANCE.createDescription();
			desc.setValue(description);
			ref.getDescriptions().clear();
			ref.getDescriptions().add(desc);
		}
		if (member.getElementType() == IJavaElement.METHOD || member.getElementType() == IJavaElement.FIELD) {
			createInjectionTarget(refName, ref.getInjectionTargets(), annotation);
		}
		String value = (String) getAnnotatedValue("authenticationType", pairs); //$NON-NLS-1$
		/*
		 * the default value is AuthenticationType.APPLICATION which is handled
		 * by the EMF. no need to check for this value
		 */
		if ("AuthenticationType.CONTAINER".equals(value)) { //$NON-NLS-1$
			ref.setResAuth(ResAuthType.CONTAINER_LITERAL);
		} else if ("CONTAINER".equals(value) //$NON-NLS-1$
				&& containsImport(member.getCompilationUnit(), "AuthenticationType.CONTAINER")) { //$NON-NLS-1$
			ref.setResAuth(ResAuthType.CONTAINER_LITERAL);
		}
		Boolean shareable = (Boolean) getAnnotatedValue("shareable", pairs); //$NON-NLS-1$
		/*
		 * The default value for sharable is true. Check and process only
		 * unsharable
		 */
		if (Boolean.FALSE.equals(shareable))
			ref.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

		resourceRefs.add(ref);
	}

	/**
	 * The method has the task of processing the member along with the specified
	 * member and return a String. The result is to be used as a reference value
	 * for the injection on this member. Usage are the
	 * 
	 * @EJB and
	 * @Resource annotations.
	 * 
	 * <p>
	 * If the specifiedType is <code>null</code> and member is of type
	 * IJavaElement.TYPE the method returns <code>null</code>
	 * </p>
	 * 
	 * <p>
	 * If the type of the member can be resolved and is an interface the method
	 * returns <code>null</code>. Here the "type" of the member is the result
	 * from {@link #getUnresolvedType(IMember)}
	 * </p>
	 * 
	 * 
	 * Only if the specifiedType can be calculated and is resolved it is added
	 * to the dependedTypes. If the specifiedType can not be resolved nothing is
	 * added to dependedTypes.
	 * 
	 * @see {@link #processEjbAnnotation(IAnnotation, SessionBean, IMember, Collection)}
	 * @see #processResourceRefAnnotation(SessionBean, IMember, IAnnotation,
	 *      Collection)
	 * 
	 * @param specifiedType
	 * @param member
	 * @param dependedTypes
	 * @return
	 * @throws JavaModelException
	 */
	private String internalProcessInjection(final String specifiedType, IMember member, Collection<IType> dependedTypes)
			throws JavaModelException {
		boolean methodOrField = member.getElementType() == IJavaElement.METHOD
				|| member.getElementType() == IJavaElement.FIELD;
		IType declaringType = (IType) (member.getElementType() == IJavaElement.TYPE ? member : member
				.getDeclaringType());
		String memberType = getUnresolvedType(member);
		// not type for this member can be retrieved. If member is a method or
		// field this means there is an error.
		if (getClassTypeSignature(memberType) == null && methodOrField)
			return null;

		// both type are null. This is not a valid case. This will hapen for a
		// type without specified type.
		if (specifiedType == null && memberType == null)
			return null;

		String innerSpecifiedType = specifiedType;
		
		IType resolvedType = resolveType(declaringType, memberType);
		// we were able to get a type for the param of a method or type of
		// a field.
		// check if it is an interface. It might not be resolved, but we have a
		// value
		// for unresolved.
		if (methodOrField) {
			// if the resolved type is not null and it is not an interface this
			// annotation is not valid
			if (resolvedType != null) {
				if (resolvedType.isInterface())
					memberType = resolvedType.getFullyQualifiedName();
				else
					// invalid - if the method is with param that is not an
					// interface. Or the type of the field is not an interface.
					return null;
			}
		}
		// from now one use only the specified type for type resolving. If there
		// is no specified type use the member type. The check for whether they
		// were both null is previously made
		IType resolvedSpecifiedType = null;
		if (innerSpecifiedType == null) {
			innerSpecifiedType = memberType;
			resolvedSpecifiedType = resolvedType;
		} else
			resolvedSpecifiedType = resolveType(declaringType, innerSpecifiedType);
		if (resolvedSpecifiedType != null) {
			if (resolvedSpecifiedType.isInterface()) {
				innerSpecifiedType = resolvedSpecifiedType.getFullyQualifiedName();
				dependedTypes.add(resolvedSpecifiedType);
			} else
				// we have resolved the specified type and it is not an
				// interface. Not a valid annotation.
				return null;
		}
		return innerSpecifiedType;
	}

	/**
	 * This method returns a qualified name for this member. The name is to be
	 * used as ejb-ref-name.
	 * 
	 * If the member is a type then fullyQualifiedName of the type is returned.
	 * 
	 * If the member is a <code>field</code> declared in a <code>type</code>
	 * then the result is
	 * <code>type.getFullyQualifiedName() +"/" + field.elementName</code>
	 * 
	 * If the member is a <code>method</code> declared in a <code>type</code>
	 * and method name begins with "set" then: for type name =
	 * "org.eclipse.Bean" and method name = "setMethodOne()" the result is
	 * "org.eclipse.Bean/methodOne"
	 * 
	 * Check this at Enterprise Java Beans, 3.0, Section 14.1.5.3
	 * 
	 * @param member
	 * @return
	 */
	private String getMemberQualifiedName(IMember member) {
		String memberName = member.getElementName();
		int elementType = member.getElementType();
		if (elementType == IJavaElement.METHOD && memberName.startsWith("set")) { //$NON-NLS-1$
			char ch = Character.toLowerCase(memberName.charAt(3));
			memberName = ch + memberName.substring(4);
		}
		return elementType == IJavaElement.TYPE ? ((IType) member).getFullyQualifiedName() : member.getDeclaringType()
				.getFullyQualifiedName()
				+ "/" + memberName; //$NON-NLS-1$
	}

	/**
	 * Return the javaee type of this member. For types return <code>null</code>.
	 * For methods with one param return the java type of this param. For fields
	 * return the return the java type of the field.
	 * 
	 * If the result is <code>null</code> then this member is not valid and a
	 * javaee type can not be returned. This may happen for a method with more
	 * then one param or for a field with a class type or primitive type
	 * 
	 * @param member
	 * @param memberType
	 * 
	 * @return
	 * @throws JavaModelException
	 */
	private String getUnresolvedType(IMember member) throws JavaModelException {
		int memberType = member.getElementType();
		String unresolvedTypeName = null;
		if (memberType == IJavaElement.FIELD) {
			unresolvedTypeName = Signature.toString(((IField) member).getTypeSignature());
		} else if (memberType == IJavaElement.METHOD) {
			IMethod method = (IMethod) member;
			if (method.getNumberOfParameters() != 1)
				return null;
			unresolvedTypeName = Signature.toString(method.getParameterTypes()[0]);
		} else if (memberType == IJavaElement.TYPE)
			return null;
		return unresolvedTypeName;
	}

	/**
	 * Returns the type signature for toResolve only if toResolve is a class or
	 * interface.
	 * 
	 * @param toResolve
	 * @return <code>null</code> if toResolve is <code>null</code> or simple
	 *         type, array type, wildcard type
	 * 
	 */
	private String getClassTypeSignature(String toResolve) {
		if (toResolve == null)
			return null;
		
		String innerToResolve = Signature.createTypeSignature(toResolve, false);
		if (Signature.getTypeSignatureKind(innerToResolve) != Signature.CLASS_TYPE_SIGNATURE)
			return null;
		return innerToResolve;
	}

	protected void processDeclareRoles(Result result, List<SecurityRoleRef> securityRoleRefs, IAnnotation annotation,
			IType type) throws JavaModelException {
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		Object values = getAnnotatedValue("value", pairs); //$NON-NLS-1$
		if (!isArrayOfObject(values))
			return;
		for (Object roleName : (Object[]) values) {
			SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
			role.setRoleName((String) roleName);
			result.getAdditional().add(role);
			SecurityRoleRef ref = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
			ref.setRoleName((String) roleName);
			securityRoleRefs.add(ref);
		}
	}

	protected void processResourcesAnnotation(IAnnotation annotation, List<ResourceRef> resourceRefs, IType type,
			Collection<IType> dependedTypes) throws JavaModelException {
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		if (!isArrayOfObject(pairs[0].getValue()))
			return;
		Object[] values = (Object[]) pairs[0].getValue();
		for (Object resourceAnnotation : values) {
			processResourceRefAnnotation((IAnnotation) resourceAnnotation, resourceRefs, type, dependedTypes);
		}
	}

	protected void processRunAs(IAnnotation annotation,RunAs runAs) throws JavaModelException {
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		if (pairs.length == 1) {
			String value = (String) getAnnotatedValue("value", pairs); //$NON-NLS-1$
			runAs.setRoleName(value);
		}
	}
}
