/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EjbMethodElementComparator;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.common.frameworks.internal.ui.DisplayUtility;


public abstract class AbstractMethodsContentProvider extends AdapterFactoryContentProvider {
	protected static EStructuralFeature ME_EJB_SF = EjbFactoryImpl.getPackage().getMethodElement_EnterpriseBean();
	protected static EStructuralFeature JAR_ASSEMBLY_SF = EjbFactoryImpl.getPackage().getEJBJar_AssemblyDescriptor();
	private static final EStructuralFeature ECORE_BEAN_NAME = EcorePackage.eINSTANCE.getENamedElement_Name(); //ENAMED_ELEMENT__NAME
	protected Comparator meComparator;
	protected boolean isRoot = true;

	public class EJBMethodItem {
		public EObject refObject;
		public EnterpriseBean ejb;

		public EJBMethodItem(EObject aRefObject, EnterpriseBean anEJB) {
			refObject = aRefObject;
			ejb = anEJB;
		}

	}

	/**
	 * Constructor for AbstractMethodsContentProvider.
	 * 
	 * @param adapterFactory
	 */
	public AbstractMethodsContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public AbstractMethodsContentProvider(AdapterFactory adapterFactory, boolean root) {
		super(adapterFactory);
		isRoot = root;
	}

	protected Comparator getEnterpriseBeanComparator() {
		return EJBNameComparator.singleton();
	}

	protected Comparator getMethodElementComparator() {
		if (meComparator == null)
			meComparator = new EjbMethodElementComparator();
		return meComparator;
	}

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof AbstractMethodsContentProvider.EJBMethodItem)
			return getMethodElements((AbstractMethodsContentProvider.EJBMethodItem) parentElement);
		return new Object[0];
	}

	protected Object[] getMethodElements(AbstractMethodsContentProvider.EJBMethodItem ejbItem) {
		EObject refObject = ejbItem.refObject;
		if (refObject == null)
			return new Object[0];
		List elements = getMethodElements(refObject, ejbItem.ejb);
		Object[] result = elements.toArray();
		if (result.length == 1)
			return result;
		Arrays.sort(result, getMethodElementComparator());
		return result;
	}

	protected abstract List getMethodElements(EObject aRefObject, EnterpriseBean anEJB);

	protected Object[] getEnterpriseBeans(EObject parentElement, List someMethodElements) {
		if (someMethodElements.isEmpty())
			return new Object[0];
		Set ejbs = new HashSet();
		EnterpriseBean ejb = null;
		MethodElement me = null;
		for (int i = 0; i < someMethodElements.size(); i++) {
			me = (MethodElement) someMethodElements.get(i);
			ejb = me.getEnterpriseBean();
			if (ejb != null)
				ejbs.add(ejb);
		}
		Object[] result = ejbs.toArray();
		if (result.length != 1)
			Arrays.sort(result, getEnterpriseBeanComparator());
		ejb = null;
		for (int i = 0; i < result.length; i++) {
			ejb = (EnterpriseBean) result[i];
			result[i] = new EJBMethodItem(parentElement, ejb);
		}
		return result;
	}

	protected AssemblyDescriptor getAssemblyDescriptor(EnterpriseBean ejb) {
		if (ejb == null)
			return null;
		return ejb.getEjbJar().getAssemblyDescriptor();
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof AbstractMethodsContentProvider.EJBMethodItem)
			return true;
		return false;
	}

	protected TreeViewer getTreeViewer() {
		return (TreeViewer) viewer;
	}

	protected void refreshTreeViewer(Object obj) {
		if (viewer != null)
			DisplayUtility.asyncExec(createRefreshTreeRunnable(obj));
	}

	protected void refreshTreeViewer() {
		if (viewer != null)
			DisplayUtility.asyncExec(createRefreshTreeRunnable(null));
	}

	protected void refreshTreeViewer(MethodElement me) {
		refreshTreeViewer(me.eContainer());
	}

	protected void refreshTreeViewer(List aList) {
		if (aList.isEmpty())
			return;
		refreshTreeViewer((MethodElement) aList.get(0));
	}

	protected void addToTreeViewer(final MethodElement me) {
		if (viewer == null)
			return;
		DisplayUtility.asyncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				// findMethodItem() should be run in a Display thread
				EJBMethodItem item = findMethodItem(me);
				if (item != null)
					DisplayUtility.asyncExec(createAddItemTreeRunnable(item, me));
				else
					refreshTreeViewer(me.eContainer());

			}
		});
	}

	protected void removeFromTreeViewer(final EObject parent, final MethodElement me) {
		if (viewer == null)
			return;
		DisplayUtility.asyncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				EnterpriseBean ejb = me.getEnterpriseBean();
				EJBMethodItem item = findMethodItem(ejb, parent);
				if (item != null)
					refreshTreeViewer(item);
				else
					refreshTreeViewer((Object) null);
			}
		});
	}

	protected EJBMethodItem findMethodItem(MethodElement me) {
		return findMethodItem(me.getEnterpriseBean(), me.eContainer());
	}

	protected EJBMethodItem findMethodItem(EnterpriseBean ejb, EObject parentMethod) {
		TreeItem[] items = getTreeViewer().getTree().getItems();
		return findMethodItem(ejb, parentMethod, items);
	}

	protected EJBMethodItem findMethodItem(EnterpriseBean ejb, EObject parentMethod, TreeItem[] items) {
		TreeItem item = null;
		Object data = null;
		EJBMethodItem methodItem = null, found = null;
		for (int i = 0; i < items.length; i++) {
			item = items[i];
			data = item.getData();
			if (data instanceof EJBMethodItem) {
				methodItem = (EJBMethodItem) data;
				if (methodItem.ejb == ejb && methodItem.refObject == parentMethod)
					return methodItem;
			}
			found = findMethodItem(ejb, parentMethod, item.getItems());
			if (found != null)
				return found;
		}
		return null;
	}

	/*
	 * @see INotifyChangedListener#notifyChanged(new ENotificationImpl((InternalEObject)Object,
	 *      int,(EStructuralFeature) Object, Object, Object, int))
	 */
	public void notifyChanged(Notification notifier) {
		if (notifier.getEventType() == Notification.RESOLVE)
			return;
		else if (notifier.getOldValue() instanceof CMPAttribute && notifier.getNewValue() == null)
			return;
		else if (notifier.getFeature() == ME_EJB_SF) {
			refreshTreeViewer((MethodElement) notifier.getNotifier());
			return;
		} else if (notifier.getFeature() == ECORE_BEAN_NAME) {
			refreshTreeViewer();
			return;
		} else if (getMethodElementsReference() == notifier.getFeature()) {
			if (notifier.getEventType() == Notification.ADD)
				addToTreeViewer((MethodElement) notifier.getNewValue());
			else if (notifier.getEventType() == Notification.REMOVE)
				removeFromTreeViewer((EObject) notifier.getNotifier(), (MethodElement) notifier.getOldValue());
			return;
		} else if (notifier.getFeature() == JAR_ASSEMBLY_SF && isRoot) {
			updateTreeInput(notifier.getNewValue());
		} else if (notifier.getFeature() == getMethodElementsContainerReference())
			super.notifyChanged(notifier);
	}

	/**
	 * @param notifier
	 */
	protected void updateTreeInput(final Object target) {
		DisplayUtility.asyncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {

				getTreeViewer().setInput(target); //reset the input
				getTreeViewer().refresh(target);

			}
		});
	}

	private Runnable createAddItemTreeRunnable(final Object parent, final MethodElement me) {
		return new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				getTreeViewer().add(parent, me);
			}
		};
	}

	private Runnable createRefreshTreeRunnable(final Object target) {
		return new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				if (target != null)
					getTreeViewer().refresh(target);
				else
					getTreeViewer().refresh();
			}
		};
	}

	protected abstract EStructuralFeature getMethodElementsReference();

	protected abstract EStructuralFeature getMethodElementsContainerReference();
}