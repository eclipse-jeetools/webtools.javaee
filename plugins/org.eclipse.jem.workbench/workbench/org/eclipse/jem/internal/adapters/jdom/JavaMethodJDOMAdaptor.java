/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaMethodJDOMAdaptor.java,v $
 *  $Revision: 1.7 $  $Date: 2005/02/03 21:19:40 $ 
 */
package org.eclipse.jem.internal.adapters.jdom;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.internal.java.adapters.IJavaMethodAdapter;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
import org.eclipse.jem.java.*;
import org.eclipse.jem.java.impl.MethodImpl;

/**
 * Java Method Reflection Adapter for JDOM (i.e. JDT model)
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public class JavaMethodJDOMAdaptor extends JDOMAdaptor implements IJavaMethodAdapter  {

	protected IMethod sourceMethod = null;

	protected IType parentType = null;

	public JavaMethodJDOMAdaptor(Notifier target, IJavaProject workingProject) {
		super(target, workingProject);
	}

	
	protected boolean flushReflectedValues(boolean clearCachedModelObject) {
		if (clearCachedModelObject)
			clearSource();
		MethodImpl method = (MethodImpl) getTarget();
		method.setIsGenerated(false);
		method.setFinal(false);
		method.setNative(false);
		method.setStatic(false);
		method.setSynchronized(false);
		method.setConstructor(false);
		method.setAbstract(false);
		method.setJavaVisibility(JavaVisibilityKind.PUBLIC_LITERAL);
		method.setEType(null);
		method.getParametersGen().clear();
		method.getJavaExceptionsGen().clear();		
		parentType = null;
		return true;
	}
	
	protected void postFlushReflectedValuesIfNecessary(boolean isExisting) {
		((MethodImpl) getTarget()).setReflected(false);
		super.postFlushReflectedValuesIfNecessary(isExisting);
	}	
	/**
	 * addExceptions - reflect our exception list
	 */
	protected void addExceptions() {
		try {
			IMethod sourceMethod = getSourceMethod();
			String[] exceptionNames = sourceMethod.getExceptionTypes();
			List exceptions = ((MethodImpl) getTarget()).getJavaExceptionsGen();
			for (int i = 0; i < exceptionNames.length; i++) {
				exceptions.add(createJavaClassRef(typeNameFromSignature(exceptionNames[i])));
			}
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
	}

	/**
	 * addParameters - reflect our parms
	 */
	protected void addParameters() {
		String[] parmNames = new String[0], parmTypeNames = getSourceMethod().getParameterTypes();
		try {
			parmNames = getSourceMethod().getParameterNames();
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
		// Temp hack to work around a JavaModel bug, above call on a Binary method may return null
		if (parmNames == null || parmNames.length == 0) {
			parmNames = new String[parmTypeNames.length];
			for (int i = 0; i < parmTypeNames.length; i++) {
				parmNames[i] = "arg" + i;//$NON-NLS-1$
			}
		}
		MethodImpl javaMethodTarget = (MethodImpl) getTarget();
		List params = javaMethodTarget.getParametersGen();
		for (int i = 0; i < parmNames.length; i++) {
			params.add(createJavaParameter(javaMethodTarget, parmNames[i], typeNameFromSignature(parmTypeNames[i])));
		}
	}

	protected void clearSource() {
		sourceMethod = null;
	}

	protected JavaClass getContainingJavaClass() {
		return ((Method) getTarget()).getContainingJavaClass();
	}

	/**
	 * getParentType - return the IType which corresponds to our parent JavaClass we're going to do this a lot, so cache it.
	 */
	protected IType getParentType() {
		if (parentType == null) {
			Method targetMethod = (Method) getTarget();
			JavaClass parentJavaClass = targetMethod.getContainingJavaClass();
			JavaClassJDOMAdaptor pa = (JavaClassJDOMAdaptor) EcoreUtil.getAdapter(parentJavaClass.eAdapters(), ReadAdaptor.TYPE_KEY);
			if (pa != null)
				parentType = pa.getSourceType();
		}
		return parentType;
	}

	/**
	 * getParmTypeSignatures - return an array of Strings (in Signature format) for our parameter types For reflection purposes, we can only rely on
	 * our UUID, since our parms may not yet be known. see org.eclipse.jdt.core.SourceMapper.convertTypeNamesToSigs()
	 */
	protected String[] getParmTypeSignatures() {
		Method javaMethodTarget = (Method) getTarget();
		String[] typeNames = getTypeNamesFromMethodID(((XMIResource) javaMethodTarget.eResource()).getID(javaMethodTarget));
		if (typeNames == null)
			return emptyStringArray;
		int n = typeNames.length;
		if (n == 0)
			return emptyStringArray;
		String[] typeSigs = new String[n];
		try {
			for (int i = 0; i < n; ++i) {
				typeSigs[i] = Signature.createTypeSignature(new String(typeNames[i]), getParentType().isBinary());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return typeSigs;
	}

	public Object getReflectionSource() {
		return getSourceMethod();
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#hasReflectionSource()
     */
    public boolean hasCachedReflectionSource() {
        return sourceMethod != null;
    }

	/*
	 * Used by Java Class JDOM adapter to create and set with a source method/
	 */	
	public void primSetMethod(IMethod method) {
		sourceMethod = method;
	}
	/**
	 * getsourceMethod - return the IMethod which describes our implementing method
	 */
	public IMethod getSourceMethod() {
		if ((sourceMethod == null) || (!sourceMethod.exists())) {
			try {
				IType parent = this.getParentType();
				if (parent != null) {
					String[] parmNames = this.getParmTypeSignatures();
					sourceMethod = JDOMSearchHelper.searchForMatchingMethod(parent, ((Method) getTarget()).getName(), parmNames);
				}
			} catch (JavaModelException e) {
				//do nothing
			}
		}
		return sourceMethod;
	}

	protected IType getType() {
		return getParentType();
	}

	protected Map getTypeResolutionCache() {
		Method method = (Method) getTarget();
		if (method != null) {
			JavaClass javaClass = method.getJavaClass();
			if (javaClass != null) {
				JDOMAdaptor classAdaptor = (JDOMAdaptor) retrieveAdaptorFrom(javaClass);
				if (classAdaptor != null)
					return classAdaptor.getTypeResolutionCache();
			}
		}
		return null;
	}

	/**
	 * getValueIn method comment.
	 */
	public Object getValueIn(EObject object, EObject attribute) {
		// At this point, this adapter does not dynamically compute any values,
		// all values are pushed back into the target on the initial call.
		return super.getValueIn(object, attribute);
	}

	/**
	 * reflectValues - template method, subclasses override to pump values into target. on entry: UUID, name, containing package (and qualified name),
	 * and document must be set. Method adaptor: - set modifiers - set name - set return type - add parameters - add exceptions
	 */
	public boolean reflectValues() {
		super.reflectValues();
		if (getSourceProject() != null && getSourceMethod() != null && sourceMethod.exists()) {
			setModifiers();
			setNaming();
			setReturnType();
			addParameters();
			addExceptions();
			return true;
		}
		return false;
	}

	/**
	 * Set the generated flag if @generated is found in the source.
	 */
	protected void setGeneratedFlag() {
		Method methodTarget = (Method) getTarget();
		try {
			String source = getSourceMethod().getSource();
			if (source != null) {
				int index = source.indexOf(Method.GENERATED_COMMENT_TAG);
				if (index > 0)
					methodTarget.setIsGenerated(true);
			}
		} catch (JavaModelException npe) {
			//System.out.println(ResourceHandler.getString("Error_Setting_GenFlag_ERROR_", new Object[]
			// {((XMIResource)methodTarget.eResource()).getID(methodTarget), npe.getMessage()})); //$NON-NLS-1$ = "error setting the generated flag on
			// {0}, exception: {1}"
		}
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaMethodAdapter#reflectGeneratedIfNecessary()
	 */
	public boolean reflectGeneratedIfNecessary() {
		if (reflectValuesIfNecessary()) {
			setGeneratedFlag();
			return true;
		}
		return false;
	}
	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		Method methodTarget = (Method) getTarget();
		try {
			methodTarget.setFinal(Flags.isFinal(getSourceMethod().getFlags()));
			methodTarget.setNative(Flags.isNative(getSourceMethod().getFlags()));
			methodTarget.setStatic(Flags.isStatic(getSourceMethod().getFlags()));
			methodTarget.setSynchronized(Flags.isSynchronized(getSourceMethod().getFlags()));
			methodTarget.setConstructor(getSourceMethod().isConstructor());

			JavaClass javaClass = getContainingJavaClass();
			//Set abstract
			if (javaClass.getKind().getValue() == TypeKind.INTERFACE)
				methodTarget.setAbstract(true);
			else
				methodTarget.setAbstract(Flags.isAbstract(getSourceMethod().getFlags()));
			// Set visibility
			if (javaClass.getKind().getValue() == TypeKind.INTERFACE || Flags.isPublic(getSourceMethod().getFlags()))
				methodTarget.setJavaVisibility(JavaVisibilityKind.PUBLIC_LITERAL);
			else if (Flags.isPrivate(getSourceMethod().getFlags()))
				methodTarget.setJavaVisibility(JavaVisibilityKind.PRIVATE_LITERAL);
			else if (Flags.isProtected(getSourceMethod().getFlags()))
				methodTarget.setJavaVisibility(JavaVisibilityKind.PROTECTED_LITERAL);
			else
				//Visibility must be package
				methodTarget.setJavaVisibility(JavaVisibilityKind.PACKAGE_LITERAL);
		} catch (JavaModelException npe) {
			System.out
					.println(ResourceHandler
							.getString(
									"Error_Introspecting_Flags_ERROR_", (new Object[] { ((XMIResource) methodTarget.eResource()).getID(methodTarget), npe.getMessage()}))); //$NON-NLS-1$ = "error introspecting flags on {0}, exception: {1}"
		}
	}

	/**
	 * setNaming - set the naming values here - qualified name must be set first, that is the path to the real Java class - ID - name-based UUID
	 */
	protected void setNaming() {
		//
		//	naming is currently a no-op since the name and UUID must be set prior to reflection
		//	...and ID is redundant with UUID.
		//	javaFieldTarget.setID(parent.getQualifiedName() + "_" + javaFieldTarget.getName());
	}

	/**
	 * setType - set our return type here
	 */
	protected void setReturnType() {
		String typeName = null;
		try {
			typeName = typeNameFromSignature(getSourceMethod().getReturnType());
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
		if (typeName != null) {
			Method javaMethodTarget = (Method) getTarget();
			javaMethodTarget.setEType(createJavaClassRef(typeName));
		}
	}

	/**
	 * Insert the method's description here. Creation date: (10/3/2001 10:08:34 AM)
	 * 
	 * @param newSourceMethod
	 *            org.eclipse.jdt.core.IMethod
	 */
	public void setSourceMethod(org.eclipse.jdt.core.IMethod newSourceMethod) {
		sourceMethod = newSourceMethod;
	}
}
