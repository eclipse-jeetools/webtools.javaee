/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.SessionType;
import org.eclipse.jst.javaee.ejb.TransactionType;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationFactory;
import org.eclipse.jst.jee.model.internal.common.Result;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EjbAnnotationFactory extends AbstractAnnotationFactory {

	
	private static final String INIT = "Init"; //$NON-NLS-1$
	private static final String RUN_AS = "RunAs"; //$NON-NLS-1$
	private static final String RESOURCES = "Resources"; //$NON-NLS-1$
	private static final String CONTAINER = "CONTAINER"; //$NON-NLS-1$
	private static final String TRANSACTION_MANAGEMENT_TYPE_CONTAINER = "TransactionManagementType.CONTAINER"; //$NON-NLS-1$
	private static final String VALUE = "value"; //$NON-NLS-1$
	private static final String TRANSACTION_MANAGEMENT = "TransactionManagement"; //$NON-NLS-1$
	private static final String EJBS = "EJBs"; //$NON-NLS-1$
	private static final String DECLARE_ROLES = "DeclareRoles"; //$NON-NLS-1$
	private static final String LOCAL_HOME = "LocalHome"; //$NON-NLS-1$
	private static final String REMOTE_HOME = "RemoteHome"; //$NON-NLS-1$
	private static final String JAVAX_EJB_TIMER = "javax.ejb.Timer"; //$NON-NLS-1$
	private static final String TIMER = "Timer"; //$NON-NLS-1$
	private static final String RETAIN_IF_EXCEPTION = "retainIfException"; //$NON-NLS-1$
	private static final String REMOVE = "Remove"; //$NON-NLS-1$
	private static final String PRE_PASSIVATE = "PrePassivate"; //$NON-NLS-1$
	private static final String POST_ACTIVATE = "PostActivate"; //$NON-NLS-1$
	private static final String TIMEOUT = "Timeout"; //$NON-NLS-1$
	private static final String PRE_DESTROY = "PreDestroy"; //$NON-NLS-1$
	private static final String POST_CONSTRUCT = "PostConstruct"; //$NON-NLS-1$
	private static final String LOCAL = "Local"; //$NON-NLS-1$
	private static final String REMOTE = "Remote"; //$NON-NLS-1$
	private static final String RESOURCE = "Resource"; //$NON-NLS-1$
	private static final String EJB = "EJB"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String MAPPED_NAME = "mappedName"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String VOID_RETURN_TYPE = "V"; //$NON-NLS-1$
	private static final String JAVAX_EJB_STATEFUL = "javax.ejb.Stateful"; //$NON-NLS-1$
	private static final String JAVAX_EJB_SINGLETON = "javax.ejb.Singleton"; //$NON-NLS-1$
	private static final String STATEFUL = "Stateful"; //$NON-NLS-1$
	private static final String JAVAX_EJB_MESSAGE_DRIVEN = "javax.ejb.MessageDriven"; //$NON-NLS-1$
	private static final String MESSAGE_DRIVEN = "MessageDriven"; //$NON-NLS-1$
	private static final String JAVAX_EJB_STATELESS = "javax.ejb.Stateless"; //$NON-NLS-1$
	private static final String STATELESS = "Stateless"; //$NON-NLS-1$
	private static final String SINGLETON = "Singleton"; //$NON-NLS-1$
	
	private static final String CREATE_METHOD = "create"; //$NON-NLS-1$

	public static EjbAnnotationFactory createFactory() {
		return new EjbAnnotationFactory();
	}

	private EjbAnnotationFactory() {
	}

	/**
	 * Creates a java ee object (SessionBean, MessageDrivenBean) depending of
	 * the type and its annotations.
	 * 
	 * Always returns a non null result. The result is of type ({@link Result}
	 * and the java ee object created by this method is retrieved with
	 * {@link Result#getMainObject()}
	 * 
	 * @param type
	 * @return the result from creating a javaee object from this type
	 * @throws JavaModelException
	 */
	public Result createJavaeeObject(IType type) throws JavaModelException {
		Result result = new Result();
		for (IAnnotation annotation : type.getAnnotations()) {
			String annotationName = annotation.getElementName();
			if (STATELESS.equals(annotationName) || JAVAX_EJB_STATELESS.equals(annotationName)) {
				processStatelessBean(result, annotation, type);
				break;
			} else if (MESSAGE_DRIVEN.equals(annotationName) || JAVAX_EJB_MESSAGE_DRIVEN.equals(annotationName)) {
				processMessageDrivenBean(result, annotation, type);
				break;
			} else if (STATEFUL.equals(annotationName) || JAVAX_EJB_STATEFUL.equals(annotationName)) {
				processStatefulBean(result, annotation, type);
				break;
			} else if (SINGLETON.equals(annotationName) || JAVAX_EJB_SINGLETON.equals(annotationName)) {
				processSingletonBean(result, annotation, type);
				break;
			}
		}
		return result;
	}

	private void processMessageDrivenBean(Result result, IAnnotation annotation, IType type) throws JavaModelException {
		MessageDrivenBean mdb = createMDB(result, annotation, type);
		result.setMainObject(mdb);
	}

	private void processStatelessBean(Result result, IAnnotation annotation, IType type) throws JavaModelException {
		SessionBean sessionBean = createSession(result, SessionType.STATELESS_LITERAL, annotation, type);
		result.setMainObject(sessionBean);
	}
	
	private void processSingletonBean(Result result, IAnnotation annotation, IType type) throws JavaModelException {
		SessionBean sessionBean = createSession(result, SessionType.SINGLETON_LITERAL, annotation, type);
		result.setMainObject(sessionBean);
		
	}

	private void processStatefulBean(Result result, IAnnotation annotation, IType type) throws JavaModelException {
		SessionBean sessionBean = createSession(result, SessionType.STATEFUL_LITERAL, annotation, type);
		processStatefulMethods(sessionBean, type);
		result.setMainObject(sessionBean);
	}

	private void addInterfaces(List list, IType type, IMemberValuePair pair, Collection<IType> dependedTypes)
			throws JavaModelException {
		if (!isArrayOfObject(pair.getValue())) {
			return;
		}
		Object[] value = (Object[]) pair.getValue();
		IType interfaceType = null;
		for (int i = 0; i < value.length; i++) {
			interfaceType = resolveType(type, (String) value[i]);
			if (interfaceType != null) {
				list.add(interfaceType.getFullyQualifiedName());
				dependedTypes.add(interfaceType);
			} else
				list.add(value[i]);
		}
	}

	private static void addLifecycleMethod(List callbacks, IMethod method, IAnnotation annotation) {
		LifecycleCallback callback = JavaeeFactory.eINSTANCE.createLifecycleCallback();
		callback.setLifecycleCallbackClass(method.getDeclaringType().getFullyQualifiedName());
		callback.setLifecycleCallbackMethod(method.getElementName());
		callbacks.add(callback);
	}

	private MessageDrivenBean createMDB(Result result, IAnnotation annotation, IType type) throws JavaModelException {
		result.getDependedTypes().add(type);
		MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
		mdbBean.setEjbClass(type.getFullyQualifiedName());
		processMessageDrivenAnnotation(mdbBean, annotation, type);
		processTypeAnnotations(result, mdbBean, type);
		processFieldAnnotations(mdbBean, type, result.getDependedTypes());
		processMethodAnnotations(mdbBean, type, result.getDependedTypes());
		return mdbBean;
	}

	private SessionBean createSession(Result result, SessionType sessionType, IAnnotation annotation, IType type)
			throws JavaModelException {
		result.getDependedTypes().add(type);
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean.setSessionType(sessionType);
		processAnnotation(sessionBean, annotation, type);
		processTypeAnnotations(result, sessionBean, type);
		processFieldAnnotations(sessionBean, type, result.getDependedTypes());
		processMethodAnnotations(sessionBean, type, result.getDependedTypes());
		processImplementedInterfaces(sessionBean, type, result.getDependedTypes(), null);
		return sessionBean;
	}

	/**
	 * Checks if this method can be classified as a "lifecycle" method. One on
	 * which a lifecycle annotation like "@PostConstruct", "@PreDestory", etc
	 * will be valid.
	 * 
	 * Such method must have only one param, return type <code>void</code> and
	 * no exceptions thrown. It must not be static or final.
	 * 
	 * @param method
	 * @return true if this can be classified as lifecycle method
	 * @throws JavaModelException
	 */
	private static boolean isLifecycleMethod(IMethod method) throws JavaModelException {
		if (!VOID_RETURN_TYPE.equals(method.getReturnType()))
			return false;
		if ((method.getFlags() & Flags.AccStatic) != 0 || (method.getFlags() & Flags.AccFinal) != 0)
			return false;
		if (method.getNumberOfParameters() != 0)
			return false;
		if (method.getExceptionTypes().length != 0)
			return false;
		return true;
	}

	private void procesRemoteHomeAnnotation(IAnnotation annotation, SessionBean sessionBean, IType type,
			Collection<IType> dependedTypes) throws JavaModelException {
		IMemberValuePair[] pair = annotation.getMemberValuePairs();
		if (pair.length == 1) {
			String value = (String) pair[0].getValue();
			IType interfaceType = resolveType(type, value);
			if (interfaceType != null) {
				sessionBean.setHome(interfaceType.getFullyQualifiedName());
				dependedTypes.add(interfaceType);
				try {
					IMethod method = interfaceType.getMethod(CREATE_METHOD, null);
					if (method != null && !Signature.SIG_VOID.equals(method.getReturnType()) ){
						String componentType = Signature.toString(method.getReturnType());
						String[][] resolveType = interfaceType.resolveType(componentType);
						if (resolveType[0].length == 2){
							componentType = resolveType[0][0]+"." + resolveType[0][1]; //$NON-NLS-1$
						}


						sessionBean.setRemote(componentType);
					}
				} catch (CoreException e) {
					// nothing to do create method is missing and component interface cannot be extracted 
				}
			} else
				sessionBean.setHome(value);
		}
	}

	private void processActivationConfig(IAnnotation activationAnnotation, MessageDrivenBean mdbBean, IType type)
			throws JavaModelException {
		IMemberValuePair[] pairs = activationAnnotation.getMemberValuePairs();
		if (pairs.length != 2)
			return;
		String name = (String) getAnnotatedValue("propertyName", pairs); //$NON-NLS-1$
		String value = (String) getAnnotatedValue("propertyValue", pairs); //$NON-NLS-1$
		ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
		property.setActivationConfigPropertyName(name);
		property.setActivationConfigPropertyValue(value);
		if (mdbBean.getActivationConfig() == null)
			mdbBean.setActivationConfig(EjbFactory.eINSTANCE.createActivationConfig());
		mdbBean.getActivationConfig().getActivationConfigProperties().add(property);
	}

	private SessionBean processAnnotation(SessionBean sessionBean, IAnnotation annotation, IType type)
			throws JavaModelException {
		String fullyQualifiedName = type.getFullyQualifiedName();
		sessionBean.setEjbClass(fullyQualifiedName);
		sessionBean.setEjbName(type.getElementName());
		IMemberValuePair[] memberValuePairs = annotation.getMemberValuePairs();
		String name = (String) getAnnotatedValue(NAME, memberValuePairs);
		String mappedName = (String) getAnnotatedValue(MAPPED_NAME, memberValuePairs);
		String descr = (String) getAnnotatedValue(DESCRIPTION, memberValuePairs);
		if (name != null) {
			sessionBean.setEjbName(name);
		}
		if (mappedName != null) {
			sessionBean.setMappedName(mappedName);
		}
		if (descr != null) {
			Description description = JavaeeFactory.eINSTANCE.createDescription();
			description.setValue(descr);
			if (sessionBean.getDescriptions().isEmpty())
				sessionBean.getDescriptions().add(description);
			else
				sessionBean.getDescriptions().set(0, description);
		}
		return sessionBean;
	}

	private void processFieldAnnotations(JavaEEObject bean, IType type, Collection<IType> dependedTypes)
			throws JavaModelException {
		SessionBean sessionBean = null;
		MessageDrivenBean messageBean = null;
		List<EjbLocalRef> ejbRefs = null;
		List<ResourceRef> resourceRefs = null;
				if (SessionBean.class.isInstance(bean)) {
			sessionBean = (SessionBean) bean;
			ejbRefs = sessionBean.getEjbLocalRefs();
			resourceRefs = sessionBean.getResourceRefs();
		} else if (MessageDrivenBean.class.isInstance(bean)) {
			messageBean = (MessageDrivenBean) bean;
			ejbRefs = messageBean.getEjbLocalRefs();
			resourceRefs = messageBean.getResourceRefs();
		}
		for (IField field : type.getFields()) {
			for (IAnnotation annotation : field.getAnnotations()) {
				String annotationName = annotation.getElementName();
				if (EJB.equals(annotationName)) {
					processEjbAnnotation(annotation, ejbRefs, field, dependedTypes);
				} else if (RESOURCE.equals(annotationName)) {
					processResourceRefAnnotation(annotation, resourceRefs, field, dependedTypes);
				}
			}
		}
	}

	private void processImplementedInterfaces(SessionBean sessionBean, IType type, Collection<IType> dependedTypes,
			IProgressMonitor monitor) throws JavaModelException {
		List<String> superInterfaces = new ArrayList<String>(Arrays.asList(type.getSuperInterfaceNames()));

		for (String interfaceName : type.getSuperInterfaceNames()) {
			String[][] fullTypeName = type.resolveType(interfaceName);
			if (fullTypeName == null) {
				/*
				 * Can not be resolved. Maybe it does not exists. Continue.
				 */
				continue;
			}
			IType inter = type.getJavaProject().findType(fullTypeName[0][0], fullTypeName[0][1]);
			if (isDefinedInBeanClass(inter, sessionBean.getBusinessLocals())
					|| isDefinedInBeanClass(inter, sessionBean.getBusinessRemotes())) {
				superInterfaces.remove(interfaceName);
				continue;
			}
			String fullyQualifiedName = inter.getFullyQualifiedName();
			for (IAnnotation annotation : inter.getAnnotations()) {
				String annotationName = annotation.getElementName();
				if (REMOTE.equals(annotationName)) {
					sessionBean.getBusinessRemotes().add(fullyQualifiedName);
					if (dependedTypes != null)
						dependedTypes.add(inter);
					superInterfaces.remove(interfaceName);
				} else if (LOCAL.equals(annotationName)) {
					sessionBean.getBusinessLocals().add(fullyQualifiedName);
					if (dependedTypes != null)
						dependedTypes.add(inter);
					superInterfaces.remove(interfaceName);
				}
			}
			if (!sessionBean.getBusinessRemotes().contains(fullyQualifiedName)
					&& !sessionBean.getBusinessLocals().contains(fullyQualifiedName)) {
				sessionBean.getBusinessLocals().add(fullyQualifiedName);
				if (dependedTypes != null)
					dependedTypes.add(inter);
				superInterfaces.remove(interfaceName);
			}

		}
		for (String string : superInterfaces) {
			if (!sessionBean.getBusinessRemotes().contains(string) && !sessionBean.getBusinessLocals().contains(string)) {
				sessionBean.getBusinessLocals().add(string);
			}
		}
		// sessionBean.getBusinessLocals().addAll(superInterfaces);

	}

	private static boolean isDefinedInBeanClass(IType type, List list) {
		if (list == null) {
			return false;
		}

		for (Object object : list) {
			if (type.getFullyQualifiedName().equals(object.toString())) {
				return true;
			}
		}
		return false;
	}

	private void processLocalHomeAnnotation(IAnnotation annotation, SessionBean sessionBean, IType type,
			Collection<IType> dependedTypes) throws JavaModelException {
		IMemberValuePair[] pair = annotation.getMemberValuePairs();
		if (pair.length == 1) {
			String value = (String) pair[0].getValue();
			IType interfaceType = resolveType(type, value);
			if (interfaceType != null) {
				sessionBean.setLocalHome(interfaceType.getFullyQualifiedName());
				dependedTypes.add(interfaceType);
				try {
					IMethod method = interfaceType.getMethod(CREATE_METHOD, null);
					if (method != null && !Signature.SIG_VOID.equals(method.getReturnType()) ){
						String componentType = Signature.toString(method.getReturnType());
						String[][] resolveType = interfaceType.resolveType(componentType);
						if (resolveType[0].length == 2){
							componentType = resolveType[0][0]+"." + resolveType[0][1]; //$NON-NLS-1$
						}
						sessionBean.setLocal(componentType);	
					}
				} catch (CoreException e) {
					// nothing to do create method is missing and component interface cannot be extracted 
				}
			} else
				sessionBean.setLocalHome(value);
		}
	}

	private void processMessageDrivenAnnotation(MessageDrivenBean mdbBean, IAnnotation annotation, IType type)
			throws JavaModelException {
		IMemberValuePair[] pairs = annotation.getMemberValuePairs();
		String beanName = type.getElementName();
		String beanName2 = (String) getAnnotatedValue(NAME, pairs);
		mdbBean.setEjbName(beanName2 == null ? beanName : beanName2);
		String mappedName = (String) getAnnotatedValue(MAPPED_NAME, pairs);
		String descr = (String) getAnnotatedValue(DESCRIPTION, pairs);

		if (mappedName != null) {
			mdbBean.setMappedName(mappedName);
		}
		if (descr != null) {
			Description description = JavaeeFactory.eINSTANCE.createDescription();
			description.setValue(descr);
			if (mdbBean.getDescriptions().isEmpty())
				mdbBean.getDescriptions().add(description);
			else
				mdbBean.getDescriptions().set(0, description);
		}

		Object values = getAnnotatedValue("activationConfig", pairs); //$NON-NLS-1$
		if (!isArrayOfObject(values))
			return;
		for (Object activationAnnotation : (Object[]) values) {
			processActivationConfig((IAnnotation) activationAnnotation, mdbBean, type);
		}
	}

	@SuppressWarnings("unchecked")
	private void processMethodAnnotations(JavaEEObject bean, IType type, Collection<IType> dependedTypes)
			throws JavaModelException {
		SessionBean sessionBean = null;
		MessageDrivenBean messageBean = null;
		List<EjbLocalRef> ejbRefs = null;
		List<ResourceRef> resourceRefs = null;
		List<LifecycleCallback> postConstructs = null;
		List<LifecycleCallback> preDestroys = null;
		if (SessionBean.class.isInstance(bean)) {
			sessionBean = (SessionBean) bean;
			ejbRefs = sessionBean.getEjbLocalRefs();
			resourceRefs = sessionBean.getResourceRefs();
			postConstructs = sessionBean.getPostConstructs();
			preDestroys = sessionBean.getPreDestroys();
		} else if (MessageDrivenBean.class.isInstance(bean)) {
			messageBean = (MessageDrivenBean) bean;
			ejbRefs = messageBean.getEjbLocalRefs();
			resourceRefs = messageBean.getResourceRefs();
			postConstructs = messageBean.getPostConstructs();
			preDestroys =  messageBean.getPreDestroys();
		}
		for (IMethod method : type.getMethods()) {
			boolean isLifecycle = isLifecycleMethod(method);
			for (IAnnotation annotation : method.getAnnotations()) {
				String annotationName = annotation.getElementName();
				if (isLifecycle) {
					if (POST_CONSTRUCT.equals(annotationName)) {
						addLifecycleMethod(postConstructs, method, annotation);
						continue;
					} else if (PRE_DESTROY.equals(annotationName)) {
						addLifecycleMethod(preDestroys, method, annotation);
						continue;
					}
				}
				if (EJB.equals(annotationName)) {
					processEjbAnnotation(annotation, ejbRefs, method, dependedTypes);
				} else if (RESOURCE.equals(annotationName)) {
					processResourceRefAnnotation(annotation, resourceRefs, method, dependedTypes);
				} else if (sessionBean != null && sessionBean.getSessionType().getValue() == SessionType.STATELESS
						&& TIMEOUT.equals(annotationName)) {
					// time out annotations are set only ot stateless beans
					processTimeoutAnnotation(sessionBean, method, annotation);
				}
			}
		}
	}

	/**
	 * Process the methods of a stateful bean and looks for annotation that make
	 * sence only in the context of the stateful bean.
	 * 
	 * No checks for whether the bean is really a stateful are made.
	 * 
	 * @param bean
	 * @param type
	 * @throws JavaModelException
	 */
	private void processStatefulMethods(SessionBean bean, IType type) throws JavaModelException {
		IMethod[] methods = type.getMethods();
		boolean searchForInit = bean.getLocalHome() != null || bean.getHome() != null;
		for (IMethod method : methods) {
			/*
			 * Since the type maybe in a working copy the preferred way (and
			 * only one working) is to iterate through the annotations and check
			 * for the annotation name. In a working copy the call to
			 * annotation.exists() always return true so it is NOT possible to
			 * use the IAnnotation annotation =
			 * method.getAnnotation("AnnotationName") and then check for
			 * annotation.exists() - this will always return true in a working
			 * copy.
			 */
			for (IAnnotation annotation : method.getAnnotations()) {
				if (searchForInit) {
					if (annotation.getElementName().equals(INIT)) {
						if (!VOID_RETURN_TYPE.equals(method.getReturnType()))
							continue;
						InitMethodType initMethod = EjbFactory.eINSTANCE.createInitMethodType();
						NamedMethodType methodType = EjbFactory.eINSTANCE.createNamedMethodType();
						methodType.setMethodName(method.getElementName());
						MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
						for (String param : method.getParameterTypes()) {
							params.getMethodParams().add(Signature.toString(param));
						}
						methodType.setMethodParams(params);
						initMethod.setBeanMethod(methodType);
						bean.getInitMethods().add(initMethod);
					}
				}
				if (isLifecycleMethod(method) == false)
					continue;
				if (annotation.getElementName().equals(POST_ACTIVATE)) {
					addLifecycleMethod(bean.getPostActivates(), method, annotation);
				} else if (annotation.getElementName().equals(PRE_PASSIVATE)) {
					addLifecycleMethod(bean.getPrePassivates(), method, annotation);
				} else if (annotation.getElementName().equals(REMOVE)) {
					RemoveMethodType removeMethod = EjbFactory.eINSTANCE.createRemoveMethodType();
					Boolean retainIfException = (Boolean) getAnnotatedValue(RETAIN_IF_EXCEPTION, annotation
							.getMemberValuePairs());
					removeMethod.setRetainIfException(retainIfException != null ? retainIfException : false);
					NamedMethodType methodType = EjbFactory.eINSTANCE.createNamedMethodType();
					methodType.setMethodName(method.getElementName());
					removeMethod.setBeanMethod(methodType);
					bean.getRemoveMethods().add(removeMethod);
				}
			}
		}
	}

	private void processTimeoutAnnotation(SessionBean sessionBean, IMethod method, IAnnotation annotation)
			throws JavaModelException {
		if (method.getNumberOfParameters() != 1)
			return;
		String paramName = Signature.toString(method.getParameterTypes()[0]);
		if (!TIMER.equals(paramName) && !JAVAX_EJB_TIMER.equals(paramName))
			return;
		if (method.getExceptionTypes().length != 0)
			return;
		NamedMethodType methodType = EjbFactory.eINSTANCE.createNamedMethodType();
		methodType.setMethodName(method.getElementName());
		MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
		params.getMethodParams().add(paramName);
		methodType.setMethodParams(params);
		sessionBean.setTimeoutMethod(methodType);
	}

	private void processTypeAnnotations(Result result, JavaEEObject bean, IType type) throws JavaModelException {
		SessionBean sessionBean = null;
		MessageDrivenBean messageBean = null;
		List<EjbLocalRef> ejbRefs  = null;
		List<ResourceRef> resourceRefs  = null;
		if (SessionBean.class.isInstance(bean)) {
			sessionBean = (SessionBean) bean;
			ejbRefs = sessionBean.getEjbLocalRefs();
			resourceRefs = sessionBean.getResourceRefs();
		} else if (MessageDrivenBean.class.isInstance(bean)) {
			messageBean = (MessageDrivenBean) bean;
			ejbRefs = messageBean.getEjbLocalRefs();
			resourceRefs = messageBean.getResourceRefs();
		}
		
		for (IAnnotation annotation : type.getAnnotations()) {
			IMemberValuePair[] pairs = annotation.getMemberValuePairs();
			String annotationName = annotation.getElementName();
			if (sessionBean != null) {
				if (LOCAL.equals(annotationName)) {
					if (pairs.length == 1) {
						addInterfaces(sessionBean.getBusinessLocals(), type, pairs[0], result.getDependedTypes());
					}
				} else if (REMOTE.equals(annotationName)) {
					if (pairs.length == 1) {
						addInterfaces(sessionBean.getBusinessRemotes(), type, pairs[0], result.getDependedTypes());
					}
				} else if (REMOTE_HOME.equals(annotationName)) {
					procesRemoteHomeAnnotation(annotation, sessionBean, type, result.getDependedTypes());
				} else if (LOCAL_HOME.equals(annotationName)) {
					processLocalHomeAnnotation(annotation, sessionBean, type, result.getDependedTypes());
				} else if (DECLARE_ROLES.equals(annotationName)) {
					processDeclareRoles(result, sessionBean.getSecurityRoleRefs(), annotation, type);
				}
			}
			if (EJB.equals(annotationName)) {
				processEjbAnnotation(annotation, ejbRefs, type, result.getDependedTypes());
			} else if (EJBS.equals(annotationName)) {
				if (!isArrayOfObject(pairs[0].getValue()))
					return;
				Object[] values = (Object[]) pairs[0].getValue();
				for (Object ejbAnnotation : values) {
					processEjbAnnotation((IAnnotation) ejbAnnotation, ejbRefs, type, result.getDependedTypes());
				}
			} else if (TRANSACTION_MANAGEMENT.equals(annotationName)) {
				String value = (String) getAnnotatedValue(VALUE, pairs);
				TransactionType transaction = null;
				/*
				 * the default value is TransactionType.BEAN which is handled by
				 * the EMF. no need to check for this value
				 */
				if (TRANSACTION_MANAGEMENT_TYPE_CONTAINER.equals(value)) {
					transaction = TransactionType.CONTAINER_LITERAL;
				} else if (CONTAINER.equals(value)
						&& containsImport(type.getCompilationUnit(), TRANSACTION_MANAGEMENT_TYPE_CONTAINER)) {
					transaction = TransactionType.CONTAINER_LITERAL;
				}
				if (transaction != null) {
					if (sessionBean != null)
						sessionBean.setTransactionType(transaction);
					else if( messageBean != null)
						messageBean.setTransactionType(transaction);
				}
			} else if (RESOURCE.equals(annotationName)) {
				processResourceRefAnnotation(annotation, resourceRefs, type, result.getDependedTypes());
			} else if (RESOURCES.equals(annotationName)) {
				processResourcesAnnotation(annotation, resourceRefs, type, result.getDependedTypes());
			} else if (RUN_AS.equals(annotationName)) {
				SecurityIdentityType identity = EjbFactory.eINSTANCE.createSecurityIdentityType();
				RunAs runAs = JavaeeFactory.eINSTANCE.createRunAs();
				processRunAs(annotation, runAs);
				identity.setRunAs(runAs);
				if (identity.getRunAs().getRoleName() != null)
					if (sessionBean != null)
						sessionBean.setSecurityIdentities(identity);
					else if( messageBean != null)
						messageBean.setSecurityIdentity(identity);
			}
		}
	}

}
