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
package org.eclipse.jst.j2ee.common.internal.provider;



import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.j2ee.common.util.CommonAdapterFactory;


/**
 * This is the factory that is used to provide the interfaces needed to support
 * {@link org.eclipse.jface.viewer.ContentViewer}s. The adapters generated by this factory convert
 * MOF adapter notificiations into {@link org.eclipse.jface.DomainEvent}s. The adapters also
 * support property sheets, see {@link com.ibm.itp.ui.api.propertysheet}. Note that most of the
 * adapters are shared among multiple instances.
 */
public class CommonItemProviderAdapterFactory extends CommonAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
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
	 * {@link #isFactoryForType isFactoryForType}.
	 */
	protected Collection supportedTypes = new ArrayList();
	/**
	 * This keeps track of the one adapter used for all {@link com.ibm.etools.common.SecurityRole}
	 * instances.
	 */
	protected SecurityRoleItemProvider securityRoleItemProvider;
	/**
	 * This keeps track of the one adapter used for all {@link com.ibm.etools.common.EjbRef}
	 * instances.
	 */
	protected EjbRefItemProvider ejbRefItemProvider;
	/**
	 * This keeps track of the one adapter used for all {@link com.ibm.etools.common.ResourceRef}
	 * instances.
	 */
	protected ResourceRefItemProvider resourceRefItemProvider;
	/**
	 * This keeps track of the one adapter used for all {@link com.ibm.etools.common.ResourceEnvRef}
	 * instances.
	 */
	protected ResourceEnvRefItemProvider resourceEnvRefItemProvider;
	/**
	 * This keeps track of the one adapter used for all {@link com.ibm.etools.common.EnvEntry}
	 * instances.
	 */
	//ItemProviders
	protected EnvEntryItemProvider envEntryItemProvider;

	protected SecurityIdentityItemProvider securityIdentityItemProvider;

	protected RunAsSpecifiedIdentityItemProvider runAsSpecifiedIdentityItemProvider;

	protected IdentityItemProvider identityItemProvider;

	protected UseCallerIdentityItemProvider useCallerIdentityItemProvider;
	/**
	 * This keeps track of the one adapter used for all
	 * {@link com.ibm.etools.common.SecurityRoleRef}instances.
	 */
	protected SecurityRoleRefItemProvider securityRoleRefItemProvider;
	protected Disposable disposable = new Disposable();

	/**
	 * This constructs an instance.
	 */
	public CommonItemProviderAdapterFactory() {
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(ITableItemLabelProvider.class);
	}

	public Adapter adapt(Notifier target, Object adapterKey) {
		return super.adapt(target, this);
	}

	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	public Adapter adaptNew(Notifier target, Object adapterType) {
		Adapter adapter = super.adaptNew(target, adapterType);
		disposable.add(adapter);
		return adapter;
	}

	/**
	 * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.EjbRef}.
	 */
	public Adapter createEjbRefAdapter() {
		if (ejbRefItemProvider == null) {
			ejbRefItemProvider = new EjbRefItemProvider(this);
		}

		return ejbRefItemProvider;
	}



	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.UseCallerIdentity}.
	 */
	public Adapter createUseCallerIdentityAdapter() {
		if (useCallerIdentityItemProvider == null) {
			useCallerIdentityItemProvider = new UseCallerIdentityItemProvider(this);
		}

		return useCallerIdentityItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.Description}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DescriptionItemProvider descriptionItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.Description}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createDescriptionAdapter() {
		if (descriptionItemProvider == null) {
			descriptionItemProvider = new DescriptionItemProvider(this);
		}

		return descriptionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.QName}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected QNameItemProvider qNameItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.common.QName}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createQNameAdapter() {
		if (qNameItemProvider == null) {
			qNameItemProvider = new QNameItemProvider(this);
		}

		return qNameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.Listener}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ListenerItemProvider listenerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.common.Listener}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createListenerAdapter() {
		if (listenerItemProvider == null) {
			listenerItemProvider = new ListenerItemProvider(this);
		}

		return listenerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.CompatibilityDescriptionGroup}
	 * instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CompatibilityDescriptionGroupItemProvider compatibilityDescriptionGroupItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.CompatibilityDescriptionGroup}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createCompatibilityDescriptionGroupAdapter() {
		if (compatibilityDescriptionGroupItemProvider == null) {
			compatibilityDescriptionGroupItemProvider = new CompatibilityDescriptionGroupItemProvider(this);
		}

		return compatibilityDescriptionGroupItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.EnvEntry}.
	 */
	public Adapter createEnvEntryAdapter() {
		if (envEntryItemProvider == null) {
			envEntryItemProvider = new EnvEntryItemProvider(this);
		}

		return envEntryItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.ResourceRef}.
	 */
	public Adapter createResourceRefAdapter() {
		if (resourceRefItemProvider == null) {
			resourceRefItemProvider = new ResourceRefItemProvider(this);
		}

		return resourceRefItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.ResourceEnvRef}.
	 */
	public Adapter createResourceEnvRefAdapter() {
		if (resourceEnvRefItemProvider == null) {
			resourceEnvRefItemProvider = new ResourceEnvRefItemProvider(this);
		}

		return resourceEnvRefItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.EJBLocalRef}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EJBLocalRefItemProvider ejbLocalRefItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.EJBLocalRef}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createEJBLocalRefAdapter() {
		if (ejbLocalRefItemProvider == null) {
			ejbLocalRefItemProvider = new EJBLocalRefItemProvider(this);
		}

		return ejbLocalRefItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.SecurityRole}.
	 */
	public Adapter createSecurityRoleAdapter() {
		if (securityRoleItemProvider == null) {
			securityRoleItemProvider = new SecurityRoleItemProvider(this);
		}

		return securityRoleItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.Identity}.
	 */
	public Adapter createIdentityAdapter() {
		if (identityItemProvider == null) {
			identityItemProvider = new IdentityItemProvider(this);
		}

		return identityItemProvider;
	}



	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.IconType}instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IconTypeItemProvider iconTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.jst.j2ee.internal.internal.common.IconType}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createIconTypeAdapter() {
		if (iconTypeItemProvider == null) {
			iconTypeItemProvider = new IconTypeItemProvider(this);
		}

		return iconTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.DisplayName}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DisplayNameItemProvider displayNameItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.DisplayName}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createDisplayNameAdapter() {
		if (displayNameItemProvider == null) {
			displayNameItemProvider = new DisplayNameItemProvider(this);
		}

		return displayNameItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.MessageDestinationRef}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MessageDestinationRefItemProvider messageDestinationRefItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.MessageDestinationRef}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createMessageDestinationRefAdapter() {
		if (messageDestinationRefItemProvider == null) {
			messageDestinationRefItemProvider = new MessageDestinationRefItemProvider(this);
		}

		return messageDestinationRefItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.MessageDestination}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MessageDestinationItemProvider messageDestinationItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.MessageDestination}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createMessageDestinationAdapter() {
		if (messageDestinationItemProvider == null) {
			messageDestinationItemProvider = new MessageDestinationItemProvider(this);
		}

		return messageDestinationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.ParamValue}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ParamValueItemProvider paramValueItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.ParamValue}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createParamValueAdapter() {
		if (paramValueItemProvider == null) {
			paramValueItemProvider = new ParamValueItemProvider(this);
		}

		return paramValueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.DescriptionGroup}instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DescriptionGroupItemProvider descriptionGroupItemProvider;

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.jst.j2ee.internal.internal.common.DescriptionGroup}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createDescriptionGroupAdapter() {
		if (descriptionGroupItemProvider == null) {
			descriptionGroupItemProvider = new DescriptionGroupItemProvider(this);
		}

		return descriptionGroupItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.RunAsSpecifiedIdentity}.
	 */
	public Adapter createRunAsSpecifiedIdentityAdapter() {
		if (runAsSpecifiedIdentityItemProvider == null) {
			runAsSpecifiedIdentityItemProvider = new RunAsSpecifiedIdentityItemProvider(this);
		}

		return runAsSpecifiedIdentityItemProvider;
	}

	public Adapter createSecurityIdentityAdapter() {
		if (securityIdentityItemProvider == null) {
			securityIdentityItemProvider = new SecurityIdentityItemProvider(this);
		}

		return securityIdentityItemProvider;
	}

	/**
	 * This creates an adapter for a {@link com.ibm.etools.common.SecurityRoleRef}.
	 */
	public Adapter createSecurityRoleRefAdapter() {
		if (securityRoleRefItemProvider == null) {
			securityRoleRefItemProvider = new SecurityRoleRefItemProvider(this);
		}

		return securityRoleRefItemProvider;
	}

	public void dispose() {
		disposable.dispose();
	}

	/**
	 * This returns the root adapter factory that contains the factory.
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return (parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory());
	}

	public boolean isFactoryForType(Object type) {
		return super.isFactoryForType(type) || supportedTypes.contains(type);
	}

	/**
	 * This removes a listener.
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier}and to {@link #parentAdapterFactory}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This sets the composed adapter factory that contains the factory.
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}
}