package org.eclipse.jem.internal.beaninfo.adapters;
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
 *  $RCSfile: BeaninfoProxyConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * This holds the proxy constants for access in beaninfo.
 * It is created on a per-registry basis and stored in the
 * constants section of the registry so that they can be
 * accessed as needed.
 *
 * To gain access to these constants, use the static accessor
 * method "getConstants()" so that the instance is not created
 * until actually needed.
 */

public final class BeaninfoProxyConstants {
		
	private static final String REGISTRY_KEY = "org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants:"; //$NON-NLS-1$
			
	final IBeanTypeProxy modelingBeaninfo;
	final IMethodProxy introspectProxy;
	final IMethodProxy getIsMergeInheritedPropertiesProxy;
	final IMethodProxy getIsMergeInheritedMethodsProxy;	
	final IMethodProxy getIsMergeInheritedEventsProxy;	
	

	final IMethodProxy getBeanInfoSearchPathProxy;
	final IMethodProxy setBeanInfoSearchPathProxy;
	
	final IMethodProxy getNameProxy;
	final IMethodProxy getDisplayNameProxy;
	final IMethodProxy getIsExpertProxy;
	final IMethodProxy getIsHiddenProxy;
	final IMethodProxy getIsPreferredProxy;
	final IMethodProxy getShortDescriptionProxy;
	final IMethodProxy getAttributeNamesProxy;
	final IMethodProxy getValueProxy;

	final IMethodProxy getBeanDescriptorProxy;
	final IMethodProxy getCustomizerClassProxy;	
	
	final IMethodProxy getPropertyDescriptorsProxy;
	final IMethodProxy getInheritedPropertyDescriptorsProxy;	
	final IMethodProxy getReadMethodProxy;	
	final IMethodProxy getWriteMethodProxy;
	final IMethodProxy getPropertyTypeProxy;
	final IMethodProxy getIsBoundProxy;
	final IMethodProxy getIsConstrainedProxy;
	final IMethodProxy getPropertyEditorClassProxy;
	
	final IMethodProxy getIndexedReadMethodProxy;
	final IMethodProxy getIndexedWriteMethodProxy;
	final IMethodProxy getIndexedPropertyTypeProxy;
	
	final IMethodProxy getMethodDescriptorsProxy;
	final IMethodProxy getInheritedMethodDescriptorsProxy;	
	final IMethodProxy getMethodProxy;
	final IMethodProxy getParameterDescriptorsProxy;
	
	final IMethodProxy getEventSetDescriptorsProxy;
	final IMethodProxy getInheritedEventSetDescriptorsProxy;	
	final IMethodProxy getIsInDefaultEventSetProxy;
	final IMethodProxy getIsUnicastProxy;
	final IMethodProxy getAddListenerMethodProxy;		
	final IMethodProxy getRemoveListenerMethodProxy;	
	final IMethodProxy getListenerMethodDescriptorsProxy;
	final IMethodProxy getListenerTypeProxy;		
	
/**
 * Get the constants instance for the specified registry.
 */
public static BeaninfoProxyConstants getConstants(ProxyFactoryRegistry registry) {
	BeaninfoProxyConstants constants = (BeaninfoProxyConstants) registry.getConstants(REGISTRY_KEY);
	if (constants == null)
		registry.registerConstants(REGISTRY_KEY, constants = new BeaninfoProxyConstants(registry));	
	return constants;
}
		

public BeaninfoProxyConstants(ProxyFactoryRegistry registry) {
	
	IStandardBeanTypeProxyFactory typeFactory = registry.getBeanTypeProxyFactory();
	
	IBeanTypeProxy introspector = typeFactory.getBeanTypeProxy("java.beans.Introspector"); //$NON-NLS-1$
	getBeanInfoSearchPathProxy = introspector.getMethodProxy("getBeanInfoSearchPath"); //$NON-NLS-1$
	setBeanInfoSearchPathProxy = introspector.getMethodProxy("setBeanInfoSearchPath", "[Ljava.lang.String;");  //$NON-NLS-1$ //$NON-NLS-2$
	
	modelingBeaninfo = typeFactory.getBeanTypeProxy("org.eclipse.jem.internal.beaninfo.vm.ModelingBeanInfo");//$NON-NLS-1$
	introspectProxy = modelingBeaninfo.getMethodProxy("introspect", new String[] {"java.lang.Class", "boolean"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	getBeanDescriptorProxy = modelingBeaninfo.getMethodProxy("getBeanDescriptor"); //$NON-NLS-1$
	getIsMergeInheritedMethodsProxy = modelingBeaninfo.getMethodProxy("isMergeInheritedMethods");		 //$NON-NLS-1$
	getIsMergeInheritedPropertiesProxy = modelingBeaninfo.getMethodProxy("isMergeInheritedProperties"); //$NON-NLS-1$
	getIsMergeInheritedEventsProxy = modelingBeaninfo.getMethodProxy("isMergeInheritedEvents"); //$NON-NLS-1$	
	getPropertyDescriptorsProxy = modelingBeaninfo.getMethodProxy("getPropertyDescriptors"); //$NON-NLS-1$
	getInheritedPropertyDescriptorsProxy = modelingBeaninfo.getMethodProxy("getInheritedPropertyDescriptors"); //$NON-NLS-1$	
	getMethodDescriptorsProxy = modelingBeaninfo.getMethodProxy("getMethodDescriptors"); //$NON-NLS-1$
	getInheritedMethodDescriptorsProxy = modelingBeaninfo.getMethodProxy("getInheritedMethodDescriptors"); //$NON-NLS-1$		
	getEventSetDescriptorsProxy = modelingBeaninfo.getMethodProxy("getEventSetDescriptors");	 //$NON-NLS-1$
	getInheritedEventSetDescriptorsProxy = modelingBeaninfo.getMethodProxy("getInheritedEventSetDescriptors");	 //$NON-NLS-1$	
	
	IBeanTypeProxy featureDescriptor = typeFactory.getBeanTypeProxy("java.beans.FeatureDescriptor"); //$NON-NLS-1$
	getNameProxy = featureDescriptor.getMethodProxy("getName");	 //$NON-NLS-1$
	getDisplayNameProxy = featureDescriptor.getMethodProxy("getDisplayName"); //$NON-NLS-1$
	getShortDescriptionProxy = featureDescriptor.getMethodProxy("getShortDescription"); //$NON-NLS-1$
	getIsExpertProxy = featureDescriptor.getMethodProxy("isExpert");	 //$NON-NLS-1$
	getIsHiddenProxy = featureDescriptor.getMethodProxy("isHidden");	 //$NON-NLS-1$
	getIsPreferredProxy = featureDescriptor.getMethodProxy("isPreferred"); //$NON-NLS-1$
	getAttributeNamesProxy = featureDescriptor.getMethodProxy("attributeNames"); //$NON-NLS-1$
	getValueProxy = featureDescriptor.getMethodProxy("getValue", "java.lang.String");	 //$NON-NLS-1$ //$NON-NLS-2$
	
	getCustomizerClassProxy = typeFactory.getBeanTypeProxy("java.beans.BeanDescriptor").getMethodProxy("getCustomizerClass"); //$NON-NLS-1$ //$NON-NLS-2$
	
	IBeanTypeProxy propertyDescriptor = typeFactory.getBeanTypeProxy("java.beans.PropertyDescriptor"); //$NON-NLS-1$
	getReadMethodProxy = propertyDescriptor.getMethodProxy("getReadMethod");	 //$NON-NLS-1$
	getWriteMethodProxy = propertyDescriptor.getMethodProxy("getWriteMethod"); //$NON-NLS-1$
	getPropertyTypeProxy = propertyDescriptor.getMethodProxy("getPropertyType"); //$NON-NLS-1$
	getIsBoundProxy = propertyDescriptor.getMethodProxy("isBound");	 //$NON-NLS-1$
	getIsConstrainedProxy = propertyDescriptor.getMethodProxy("isConstrained");	 //$NON-NLS-1$
	getPropertyEditorClassProxy = propertyDescriptor.getMethodProxy("getPropertyEditorClass");	 //$NON-NLS-1$
	
	IBeanTypeProxy indexedPropertyDescriptor = typeFactory.getBeanTypeProxy("java.beans.IndexedPropertyDescriptor");	 //$NON-NLS-1$
	getIndexedReadMethodProxy = indexedPropertyDescriptor.getMethodProxy("getIndexedReadMethod"); //$NON-NLS-1$
	getIndexedWriteMethodProxy = indexedPropertyDescriptor.getMethodProxy("getIndexedWriteMethod"); //$NON-NLS-1$
	getIndexedPropertyTypeProxy = indexedPropertyDescriptor.getMethodProxy("getIndexedPropertyType");	 //$NON-NLS-1$
	
	IBeanTypeProxy methodDescriptor = typeFactory.getBeanTypeProxy("java.beans.MethodDescriptor"); //$NON-NLS-1$
	getMethodProxy = methodDescriptor.getMethodProxy("getMethod"); //$NON-NLS-1$
	getParameterDescriptorsProxy = methodDescriptor.getMethodProxy("getParameterDescriptors"); //$NON-NLS-1$
	
	IBeanTypeProxy eventSetDescriptor = typeFactory.getBeanTypeProxy("java.beans.EventSetDescriptor"); //$NON-NLS-1$
	getIsInDefaultEventSetProxy = eventSetDescriptor.getMethodProxy("isInDefaultEventSet"); //$NON-NLS-1$
	getIsUnicastProxy = eventSetDescriptor.getMethodProxy("isUnicast");	 //$NON-NLS-1$
	getAddListenerMethodProxy = eventSetDescriptor.getMethodProxy("getAddListenerMethod");		 //$NON-NLS-1$
	getRemoveListenerMethodProxy = eventSetDescriptor.getMethodProxy("getRemoveListenerMethod");			 //$NON-NLS-1$
	getListenerMethodDescriptorsProxy = eventSetDescriptor.getMethodProxy("getListenerMethodDescriptors"); //$NON-NLS-1$
	getListenerTypeProxy = eventSetDescriptor.getMethodProxy("getListenerType"); //$NON-NLS-1$	

}

public IMethodProxy getGetBeanInfoSearchPathProxy() {
	return getBeanInfoSearchPathProxy;
}

public IMethodProxy getSetBeanInfoSearchPathProxy() {
	return setBeanInfoSearchPathProxy;
}

public IBeanTypeProxy getModelingBeaninfoProxy() {
	return modelingBeaninfo;
}

public IMethodProxy getBeanDescriptorProxy() {
	return getBeanDescriptorProxy;
}

public IMethodProxy getIntrospectProxy() {
	return introspectProxy;
}

public IMethodProxy getNameProxy() {
	return getNameProxy;
}

public IMethodProxy getDisplayNameProxy() {
	return getDisplayNameProxy;
}

public IMethodProxy getShortDescriptionProxy() {
	return getShortDescriptionProxy;
}

public IMethodProxy getAttributeNamesProxy() {
	return getAttributeNamesProxy;
}

public IMethodProxy getValueProxy() {
	return getValueProxy;
}

public IMethodProxy getIsExpertProxy() {
	return getIsExpertProxy;
}

public IMethodProxy getIsHiddenProxy() {
	return getIsHiddenProxy;
}

public IMethodProxy getIsPreferredProxy() {
	return getIsPreferredProxy;
}


public IMethodProxy getCustomizerClassProxy() {
	return getCustomizerClassProxy;
}

public IMethodProxy getIsMergeInheritedMethodsProxy() {
	return getIsMergeInheritedMethodsProxy;
}

public IMethodProxy getIsMergeInheritedPropertiesProxy() {
	return getIsMergeInheritedPropertiesProxy;
}

public IMethodProxy getIsMergeInheritedEventsProxy() {
	return getIsMergeInheritedEventsProxy;
}

public IMethodProxy getPropertyDescriptorsProxy() {
	return getPropertyDescriptorsProxy;
}

public IMethodProxy getInheritedPropertyDescriptorsProxy() {
	return getInheritedPropertyDescriptorsProxy;
}

public IMethodProxy getReadMethodProxy() {
	return getReadMethodProxy;
}

public IMethodProxy getWriteMethodProxy() {
	return getWriteMethodProxy;
}

public IMethodProxy getPropertyTypeProxy() {
	return getPropertyTypeProxy;
}

public IMethodProxy getIsBoundProxy() {
	return getIsBoundProxy;
}

public IMethodProxy getIsConstrainedProxy() {
	return getIsConstrainedProxy;
}

public IMethodProxy getPropertyEditorClassProxy() {
	return getPropertyEditorClassProxy;
}

public IMethodProxy getIndexedReadMethodProxy() {
	return getIndexedReadMethodProxy;
}

public IMethodProxy getIndexedWriteMethodProxy() {
	return getIndexedWriteMethodProxy;
}

public IMethodProxy getIndexedPropertyTypeProxy() {
	return getIndexedPropertyTypeProxy;
}

public IMethodProxy getMethodDescriptorsProxy() {
	return getMethodDescriptorsProxy;
}

public IMethodProxy getInheritedMethodDescriptorsProxy() {
	return getInheritedMethodDescriptorsProxy;
}

public IMethodProxy getMethodProxy() {
	return getMethodProxy;
}

public IMethodProxy getParameterDescriptorsProxy() {
	return getParameterDescriptorsProxy;
}

public IMethodProxy getEventSetDescriptorsProxy() {
	return getEventSetDescriptorsProxy;
}

public IMethodProxy getInheritedEventSetDescriptorsProxy() {
	return getInheritedEventSetDescriptorsProxy;
}

public IMethodProxy getIsInDefaultEventSetProxy() {
	return getIsInDefaultEventSetProxy;
}

public IMethodProxy getIsUnicastProxy() {
	return getIsUnicastProxy;
}

public IMethodProxy getAddListenerMethodProxy() {
	return getAddListenerMethodProxy;
}

public IMethodProxy getRemoveListenerMethodProxy() {
	return getRemoveListenerMethodProxy;
}

public IMethodProxy getListenerMethodDescriptorsProxy() {
	return getListenerMethodDescriptorsProxy;
}

public IMethodProxy getListenerTypeProxy() {
	return getListenerTypeProxy;
}
}