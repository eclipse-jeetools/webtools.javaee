package org.eclipse.jem.internal.adapters.jdom;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaClassJDOMAdaptor.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/13 21:12:11 $ 
 */

import java.util.*;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.core.*;
import org.eclipse.wtp.common.UIContextDetermination;

import org.eclipse.jem.internal.core.MsgLogger;
import org.eclipse.jem.java.*;
import org.eclipse.jem.internal.java.adapters.*;
import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
import org.eclipse.jem.java.impl.JavaClassImpl;
import org.eclipse.jem.internal.plugin.JavaPlugin;


public class JavaClassJDOMAdaptor extends JDOMAdaptor implements IJavaClassAdaptor {
	private static final String OBJECT_TYPE_NAME = "java.lang.Object"; //$NON-NLS-1$

	protected IType sourceType = null;
	protected JavaReflectionAdapterFactory adapterFactory;
	private Map typeResolutionCache = new HashMap(25);
	public JavaClassJDOMAdaptor(Notifier target, IJavaProject workingProject, JavaReflectionAdapterFactory inFactory) {
		super(target, workingProject);
		setAdapterFactory(inFactory);
	}
	/**
	 * addFields - reflect our fields
	 */
	protected void addFields() {
		try {
			XMIResource resource = (XMIResource) getJavaClassTarget().eResource();
			IField[] fields = getSourceType().getFields();
			List targetFields = getJavaClassTarget().getFieldsGen();
			for (int i = 0; i < fields.length; i++) {
				targetFields.add(createJavaField(fields[i], resource));
			}
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
	}
	/**
	 * addMethods - reflect our methods
	 */
	protected void addMethods() {
		try {
			XMIResource resource = (XMIResource) getJavaClassTarget().eResource();
			IMethod[] methods = getSourceType().getMethods();
			List targetMethods = getJavaClassTarget().getMethodsGen();
			Method method = null;
			JavaMethodJDOMAdaptor adaptor = null;
			for (int i = 0; i < methods.length; i++) {
				adaptor = null;
				method = createJavaMethod(methods[i], resource);
				targetMethods.add(method);
				adaptor = (JavaMethodJDOMAdaptor) retrieveAdaptorFrom(method);
				if (adaptor != null)
					adaptor.setSourceMethod(methods[i]);
			}
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
	}
	/**
	 * Clear source Type ;
	 */
	protected void clearSource() {
		sourceType = null;
	}

	/**
	 * Clear the reflected fields list.
	 */
	protected boolean flushFields() {
		getJavaClassTarget().getFieldsGen().clear();
		return true;
	}
	/**
	 * Clear the implements list.
	 */
	protected boolean flushImplements() {
		getJavaClassTarget().getImplementsInterfacesGen().clear();
		return true;
	}
	/**
	 * Clear the reflected methods list.
	 */
	protected boolean flushMethods() {
		getJavaClassTarget().getMethodsGen().clear();
		return true;
	}
	protected boolean flushModifiers() {
		JavaClass javaClassTarget = (JavaClass) getTarget();
		javaClassTarget.setAbstract(false);
		javaClassTarget.setFinal(false);
		javaClassTarget.setPublic(false);
		javaClassTarget.setKind(TypeKind.UNDEFINED_LITERAL);
		return true;
	}
	protected boolean flushInnerClasses() {
		getJavaClassTarget().getDeclaredClassesGen().clear();
		return true;
	}
	/**
	 * Clear the reflected values.
	 */
	protected boolean flushReflectedValues(boolean clearCachedModelObject) {
		if (clearCachedModelObject)
			setSourceType(null);
		typeResolutionCache.clear();
		return primFlushReflectedValues();
	}

	/**
	 * @see com.ibm.etools.java.adapters.JavaReflectionAdaptor#postFlushReflectedValuesIfNecessary()
	 */
	protected void postFlushReflectedValuesIfNecessary(boolean isExisting) {
		getJavaClassTarget().setReflected(false);
		super.postFlushReflectedValuesIfNecessary(isExisting);
	}

	/**
	 * Set the supertype to be null.
	 */
	protected boolean flushSuper() {
		List targetSupers = getJavaClassTarget().getESuperTypesGen();
		targetSupers.clear();
		return true;
	}
	protected JavaReflectionAdapterFactory getAdapterFactory() {
		return adapterFactory;
	}
	/**
	 * getBinaryType - return the IType which describes our existing Java class file
	 */
	protected IType getBinaryType() {
		return this.getBinaryType(((JavaClass) getTarget()).getQualifiedName());
	}
	/**
	 * Return the target typed to a JavaClass.
	 */
	protected JavaClassImpl getJavaClassTarget() {
		return (JavaClassImpl) getTarget();
	}
	public Object getReflectionSource() {
		return getSourceType();
	}
	/**
	 * getSourceType - return the IType which describes our existing Java class or source file
	 */
	public IType getSourceType() {
		if (sourceType == null) {
			JavaClassImpl javaClass = (JavaClassImpl) getTarget();
			sourceType = JDOMSearchHelper.findType(javaClass.getJavaPackage().getName(), javaClass.primGetName(), getSourceProject());
		}
		return sourceType;
	}
	/**
	 * getSourceType - return the IType which describes our existing Java class or source file
	 */
	protected IType getType() {
		return getSourceType();
	}
	protected Map getTypeResolutionCache() {
		return typeResolutionCache;
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
	 * Return true if the sourceType is null or if
	 * it is a binary type.
	 */
	public boolean isSourceTypeFromBinary() {
		if (getSourceType() == null)
			return false; //must be new?
		return getSourceType().isBinary();
	}
	/**
	 * Clear the reflected values.
	 */
	protected boolean primFlushReflectedValues() {
		boolean result = flushModifiers();
		result &= flushSuper();
		result &= flushImplements();
		result &= flushMethods();
		result &= flushFields();
		result &= flushInnerClasses();
		return result;
	}

	protected JavaClass reflectJavaClass(String qualifiedName) {
		IType type = JDOMSearchHelper.findType(qualifiedName, true, getSourceProject(), this);
		if (type != null)
			return reflectJavaClass(type);
		else
			return createJavaClassRef(qualifiedName);
	}
	protected JavaClass reflectJavaClass(IType aType) {
		if (aType != null) {
			JavaClass javaClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType(aType.getFullyQualifiedName(), (EObject) getTarget());
			if (javaClass != null) {
				JavaClassJDOMAdaptor adaptor = (JavaClassJDOMAdaptor) EcoreUtil.getAdapter(javaClass.eAdapters(), ReadAdaptor.TYPE_KEY);
				if (adaptor != null)
					adaptor.setSourceType(aType);
			}
			return javaClass;
		}
		return null;
	}
	/**
	 * reflectValues - template method, subclasses override to pump values into target.
	 * on entry: name, containing package (and qualified name), and document must be set.
	 * Return true always and the JavaReflectionSynchronizer will flush with the type can
	 * be found again.  In headless mode, return true only if the type is found.  This is
	 * needed becauce most headless tasks are done under one operation and the JavaReflectionSynchronizer
	 * may not have a chance to flush a bad reflection before the real type needs to be found and can be found.
	 * JavaClass adaptor:
	 * - set modifiers
	 * - set name
	 * - set reference to super
	 * - create methods
	 * - create fields
	 * - add imports
	 */
	public boolean reflectValues() {
		super.reflectValues();
		primFlushReflectedValues();
		boolean isHeadless = UIContextDetermination.getCurrentContext() == UIContextDetermination.HEADLESS_CONTEXT;
		if (getSourceProject() != null && getSourceType() != null && getSourceType().exists()) {
			setModifiers();
			setNaming();
			try {
				setSuper();
			} catch (InheritanceCycleException e) {
				JavaPlugin.getDefault().getMsgLogger().log(e);
			}
			setImplements();
			addMethods();
			addFields();
			reflectInnerClasses();
			//addImports();
			if (isHeadless) {
				registerWithFactory();
				return true;
			}
		}
		if (isHeadless)
			return false;
		else {
			registerWithFactory();
			return true;
		}
	}
	private void registerWithFactory() {
		getAdapterFactory().registerReflection(getJavaClassTarget().getQualifiedNameForReflection(), this);
	}

	/**
	 * @see com.ibm.etools.java.adapters.ReflectionAdaptor#notifyChanged(new ENotificationImpl((InternalEObject)Notifier, int,(EStructuralFeature) EObject, Object, Object, int))
	 */
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER
			&& notification.getOldValue() == this
			&& notification.getNotifier() == getTarget())
			getAdapterFactory().unregisterReflection(getJavaClassTarget().getQualifiedNameForReflection());

	}

	protected void setAdapterFactory(JavaReflectionAdapterFactory inFactory) {
		adapterFactory = inFactory;
	}
	/**
	 * setImplements - set our implemented/super interfaces here
	 * For an interface, these are superclasses.
	 * For a class, these are implemented interfaces.
	 */
	protected void setImplements() {
		try {
			String[] interfaceNames = getSourceType().getSuperInterfaceNames();
			JavaClass ref;
			// needs work, the names above will be simple names if we are relfecting from a source file
			List list = getJavaClassTarget().getImplementsInterfacesGen();
			for (int i = 0; i < interfaceNames.length; i++) {
				ref = reflectJavaClass(interfaceNames[i]);
				list.add(ref);
			}
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
	}
	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		JavaClass javaClassTarget = (JavaClass) getTarget();
		try {
			javaClassTarget.setAbstract(Flags.isAbstract(getSourceType().getFlags()));
			javaClassTarget.setFinal(Flags.isFinal(getSourceType().getFlags()));
			javaClassTarget.setPublic(Flags.isPublic(getSourceType().getFlags()));
			// Set type to class or interface, not yet handling EXCEPTION
			if (getSourceType().isClass())
				javaClassTarget.setKind(TypeKind.CLASS_LITERAL);
			else
				javaClassTarget.setKind(TypeKind.INTERFACE_LITERAL);
		} catch (JavaModelException npe) {
			JavaPlugin.getDefault().getMsgLogger().log(ResourceHandler.getString("Error_Introspecting_Flags_ERROR_", new Object[] { javaClassTarget.getQualifiedName(), npe.getMessage()}), MsgLogger.LOG_WARNING); //$NON-NLS-1$ = "error introspecting flags on {0}"
		}
	}
	/**
	 * setNaming - set the naming values here
	 * 	- qualified name (package name + name) must be set first, that is the path to the real Java class
	 *	- ID - simple name, identity within a package document
	 * 	- null UUID
	 */
	protected void setNaming() {
		/* Naming has been provided by the JavaReflectionKey 
		JavaClass javaClassTarget = (JavaClass) getTarget();
		String packageName = getSourceType().getPackageFragment().getElementName();
		javaClassTarget.refSetUUID((String)null);
		((XMIResource)javaClassTarget.eResource()).setID(javaClassTarget,getSourceType().getElementName());
		*/
	}
	protected void setSourceType(IType aType) {
		sourceType = aType;
	}
	/**
	 * setSuper - set our supertype here, implemented interface are handled separately
	 */
	protected void setSuper() throws InheritanceCycleException {
		String superName = null;
		try {
			if (!getSourceType().isInterface()) {
				superName = getSourceType().getSuperclassName();
				//Source files return null if extends does not exist.
				if (superName == null && !getSourceType().getFullyQualifiedName().equals(OBJECT_TYPE_NAME))
					superName = OBJECT_TYPE_NAME;
				if (superName != null) {
					JavaClass javaClassTarget = (JavaClass) getTarget();
					javaClassTarget.setSupertype(reflectJavaClass(superName));
				}
			}
		} catch (JavaModelException npe) {
		}
	}
	/**
	 * Return true if the sourceType can be found.
	 */
	public boolean sourceTypeExists() {
		return getSourceType() != null;
	}
	protected void reflectInnerClasses() {
		IType[] innerClasses = null;
		try {
			innerClasses = getSourceType().getTypes();
		} catch (JavaModelException e) {
		}
		if (innerClasses != null && innerClasses.length != 0) {
			List declaredClasses = getJavaClassTarget().getDeclaredClassesGen();
			JavaClass inner;
			ResourceSet set = getTargetResource().getResourceSet();
			String packageName = getSourceType().getPackageFragment().getElementName();
			for (int i = 0; i < innerClasses.length; i++) {
				inner = (JavaClass) JavaRefFactory.eINSTANCE.reflectType(packageName, innerClasses[i].getTypeQualifiedName(), set);
				declaredClasses.add(inner);
			}
		}
	}
}
