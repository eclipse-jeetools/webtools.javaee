/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.util.JaxrpcmapAdapterFactory;


/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The
 * adapters generated by this factory convert EMF adapter notifications into calls to
 * { @link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class JaxrpcmapItemProviderAdapterFactory extends JaxrpcmapAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by
	 * { @link #isFactoryForType isFactoryForType}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection supportedTypes = new ArrayList();

	/**
	 * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JaxrpcmapItemProviderAdapterFactory() {
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.JavaWSDLMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected JavaWSDLMappingItemProvider javaWSDLMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.JavaWSDLMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createJavaWSDLMappingAdapter() {
		if (javaWSDLMappingItemProvider == null) {
			javaWSDLMappingItemProvider = new JavaWSDLMappingItemProvider(this);
		}

		return javaWSDLMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.PackageMapping}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PackageMappingItemProvider packageMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.PackageMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createPackageMappingAdapter() {
		if (packageMappingItemProvider == null) {
			packageMappingItemProvider = new PackageMappingItemProvider(this);
		}

		return packageMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.JavaXMLTypeMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected JavaXMLTypeMappingItemProvider javaXMLTypeMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.JavaXMLTypeMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createJavaXMLTypeMappingAdapter() {
		if (javaXMLTypeMappingItemProvider == null) {
			javaXMLTypeMappingItemProvider = new JavaXMLTypeMappingItemProvider(this);
		}

		return javaXMLTypeMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ExceptionMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ExceptionMappingItemProvider exceptionMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ExceptionMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createExceptionMappingAdapter() {
		if (exceptionMappingItemProvider == null) {
			exceptionMappingItemProvider = new ExceptionMappingItemProvider(this);
		}

		return exceptionMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceInterfaceMapping}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ServiceInterfaceMappingItemProvider serviceInterfaceMappingItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceInterfaceMapping}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createServiceInterfaceMappingAdapter() {
		if (serviceInterfaceMappingItemProvider == null) {
			serviceInterfaceMappingItemProvider = new ServiceInterfaceMappingItemProvider(this);
		}

		return serviceInterfaceMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ServiceEndpointInterfaceMappingItemProvider serviceEndpointInterfaceMappingItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createServiceEndpointInterfaceMappingAdapter() {
		if (serviceEndpointInterfaceMappingItemProvider == null) {
			serviceEndpointInterfaceMappingItemProvider = new ServiceEndpointInterfaceMappingItemProvider(this);
		}

		return serviceEndpointInterfaceMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.RootTypeQname}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RootTypeQnameItemProvider rootTypeQnameItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.RootTypeQname}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createRootTypeQnameAdapter() {
		if (rootTypeQnameItemProvider == null) {
			rootTypeQnameItemProvider = new RootTypeQnameItemProvider(this);
		}

		return rootTypeQnameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.VariableMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected VariableMappingItemProvider variableMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.VariableMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createVariableMappingAdapter() {
		if (variableMappingItemProvider == null) {
			variableMappingItemProvider = new VariableMappingItemProvider(this);
		}

		return variableMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessage}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLMessageItemProvider wsdlMessageItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessage}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLMessageAdapter() {
		if (wsdlMessageItemProvider == null) {
			wsdlMessageItemProvider = new WSDLMessageItemProvider(this);
		}

		return wsdlMessageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ConstructorParameterOrder}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ConstructorParameterOrderItemProvider constructorParameterOrderItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ConstructorParameterOrder}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createConstructorParameterOrderAdapter() {
		if (constructorParameterOrderItemProvider == null) {
			constructorParameterOrderItemProvider = new ConstructorParameterOrderItemProvider(this);
		}

		return constructorParameterOrderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ElementName}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ElementNameItemProvider elementNameItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ElementName}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createElementNameAdapter() {
		if (elementNameItemProvider == null) {
			elementNameItemProvider = new ElementNameItemProvider(this);
		}

		return elementNameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLServiceName}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLServiceNameItemProvider wsdlServiceNameItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLServiceName}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLServiceNameAdapter() {
		if (wsdlServiceNameItemProvider == null) {
			wsdlServiceNameItemProvider = new WSDLServiceNameItemProvider(this);
		}

		return wsdlServiceNameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.PortMapping}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PortMappingItemProvider portMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.PortMapping}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createPortMappingAdapter() {
		if (portMappingItemProvider == null) {
			portMappingItemProvider = new PortMappingItemProvider(this);
		}

		return portMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLPortType}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLPortTypeItemProvider wsdlPortTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLPortType}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLPortTypeAdapter() {
		if (wsdlPortTypeItemProvider == null) {
			wsdlPortTypeItemProvider = new WSDLPortTypeItemProvider(this);
		}

		return wsdlPortTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLBinding}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLBindingItemProvider wsdlBindingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLBinding}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLBindingAdapter() {
		if (wsdlBindingItemProvider == null) {
			wsdlBindingItemProvider = new WSDLBindingItemProvider(this);
		}

		return wsdlBindingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ServiceEndpointMethodMappingItemProvider serviceEndpointMethodMappingItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createServiceEndpointMethodMappingAdapter() {
		if (serviceEndpointMethodMappingItemProvider == null) {
			serviceEndpointMethodMappingItemProvider = new ServiceEndpointMethodMappingItemProvider(this);
		}

		return serviceEndpointMethodMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLOperation}instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLOperationItemProvider wsdlOperationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLOperation}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLOperationAdapter() {
		if (wsdlOperationItemProvider == null) {
			wsdlOperationItemProvider = new WSDLOperationItemProvider(this);
		}

		return wsdlOperationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.MethodParamPartsMapping}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MethodParamPartsMappingItemProvider methodParamPartsMappingItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.MethodParamPartsMapping}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createMethodParamPartsMappingAdapter() {
		if (methodParamPartsMappingItemProvider == null) {
			methodParamPartsMappingItemProvider = new MethodParamPartsMappingItemProvider(this);
		}

		return methodParamPartsMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLReturnValueMapping}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLReturnValueMappingItemProvider wsdlReturnValueMappingItemProvider;

	/**
	 * This creates an adapter for a
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLReturnValueMapping}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLReturnValueMappingAdapter() {
		if (wsdlReturnValueMappingItemProvider == null) {
			wsdlReturnValueMappingItemProvider = new WSDLReturnValueMappingItemProvider(this);
		}

		return wsdlReturnValueMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessageMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLMessageMappingItemProvider wsdlMessageMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessageMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLMessageMappingAdapter() {
		if (wsdlMessageMappingItemProvider == null) {
			wsdlMessageMappingItemProvider = new WSDLMessageMappingItemProvider(this);
		}

		return wsdlMessageMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessagePartName}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WSDLMessagePartNameItemProvider wsdlMessagePartNameItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.WSDLMessagePartName}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createWSDLMessagePartNameAdapter() {
		if (wsdlMessagePartNameItemProvider == null) {
			wsdlMessagePartNameItemProvider = new WSDLMessagePartNameItemProvider(this);
		}

		return wsdlMessagePartNameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * { @link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.InterfaceMapping}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InterfaceMappingItemProvider interfaceMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.InterfaceMapping}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createInterfaceMappingAdapter() {
		if (interfaceMappingItemProvider == null) {
			interfaceMappingItemProvider = new InterfaceMappingItemProvider(this);
		}

		return interfaceMappingItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier}and to {@link #parentAdapterFactory}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

}
