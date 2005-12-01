/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;


public class MethodPermissionsContentProvider extends AbstractMethodsContentProvider {
	private static final EStructuralFeature PERMISSION_MES_SF = EjbFactoryImpl.getPackage().getMethodPermission_MethodElements();
	private static final EStructuralFeature PERMISSION_MES_UNCHECKED_SF = EjbFactoryImpl.getPackage().getMethodPermission_Unchecked();
	private static final EStructuralFeature PERMISSION_MES_ROLES_SF = EjbFactoryImpl.getPackage().getMethodPermission_Roles();
	private static final EStructuralFeature AD_MP_SF = EjbFactoryImpl.getPackage().getAssemblyDescriptor_MethodPermissions();

	/**
	 * Constructor for MethodPermissionsContentProvider.
	 * 
	 * @param adapterFactory
	 * @param root
	 * @param viewer
	 */
	public MethodPermissionsContentProvider(AdapterFactory adapterFactory, boolean root) {
		super(adapterFactory, root);
	}

	/**
	 * Constructor for MethodPermissionsContentProvider.
	 * 
	 * @param adapterFactory
	 */
	public MethodPermissionsContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MethodPermission)
			return getEnterpriseBeans((MethodPermission) parentElement);
		return super.getChildren(parentElement);
	}

	protected Object[] getEnterpriseBeans(MethodPermission mp) {
		List mes = mp.getMethodElements();
		return getEnterpriseBeans(mp, mes);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElements(EObject, EnterpriseBean)
	 */
	protected List getMethodElements(EObject aRefObject, EnterpriseBean anEJB) {
		if (!(aRefObject instanceof MethodPermission))
			return Collections.EMPTY_LIST;
		MethodPermission parentMP = (MethodPermission) aRefObject;
		if (parentMP == null)
			return Collections.EMPTY_LIST;
		return parentMP.getMethodElements(anEJB);
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		super.getElements(inputElement);
		if (inputElement instanceof EJBJar) {
			EJBJar jar = (EJBJar) inputElement;
			if (jar.getAssemblyDescriptor() != null)
				return jar.getAssemblyDescriptor().getMethodPermissions().toArray();
		}
		if (inputElement instanceof AssemblyDescriptor)
			return ((AssemblyDescriptor) inputElement).getMethodPermissions().toArray();
		return new Object[0];
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof MethodPermission)
			return !((MethodPermission) element).getMethodElements().isEmpty();
		return super.hasChildren(element);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElementsReference()
	 */
	protected EStructuralFeature getMethodElementsReference() {
		return PERMISSION_MES_SF;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.ui.providers.AbstractMethodsContentProvider#getMethodElementsContainerReference()
	 */
	protected EStructuralFeature getMethodElementsContainerReference() {
		return AD_MP_SF;
	}

	/**
	 * @see org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged(Notification)
	 */
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == PERMISSION_MES_SF || notification.getFeature() == AD_MP_SF)
			refreshTreeViewer();
		else if (notification.getOldValue() instanceof CMPAttribute && notification.getNewValue() == null)
			return;
		if (notification.getFeature() == PERMISSION_MES_UNCHECKED_SF || notification.getFeature() == PERMISSION_MES_ROLES_SF || notification.getFeature() == getMethodElementsReference() || notification.getFeature() == getMethodElementsContainerReference())
			super.notifyChanged(notification);
	}
}
