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
package org.eclipse.jst.j2ee.internal.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.internal.adapters.jdom.JDOMAdaptor;
import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.nature.EMFNature;

/**
 * This adapter is used to listen for changes to the ejb class or the primary key class in order to
 * reflect the proper CMP field types and the proper key attributes.
 */
public class CMPJavaChangeSynchronizationAdapter extends AdapterImpl {

	private static final String J2EE_PROJ_MIGRATION_ADAPTER = "J2EEProjectMigration"; //$NON-NLS-1$

	protected static EjbPackage EJB_PACK = EjbFactoryImpl.getPackage();

	private static HashMap enablementMap = new HashMap();

	public static void disable(ContainerManagedEntity cmp) {
		enablementMap.put(cmp, Boolean.FALSE);
	}

	public static void enable(ContainerManagedEntity cmp) {
		enablementMap.put(cmp, Boolean.TRUE);
	}

	public static boolean isEnabled(ContainerManagedEntity cmp) {
		if (!enablementMap.containsKey(cmp))
			return true;
		return ((Boolean) enablementMap.get(cmp)).booleanValue();
	}

	protected Resource cmpResource;

	private List foundKeys = new ArrayList();

	public CMPJavaChangeSynchronizationAdapter() {
		super();
	} // EJBKeyShapeMaintenanceAdapter

	/**
	 * Notifies if there is change in the key shape.
	 * 
	 * @param Notification
	 *            msg - Message indicates what has changed.
	 */
	public void notifyChanged(Notification msg) {
		if (!isEnabled(getCMP())) {
			enable(getCMP());
			return;
		}
		switch (msg.getEventType()) {
			case Notification.SET :
				setNotification(msg);
				break;
			case Notification.UNSET :
				unsetNotification(msg);
				break;
			//			case Notification.TOUCH :
			//				touchNotification(msg);
			//				break;
			case Notification.REMOVING_ADAPTER :
				removeAdapterNotification(msg);
				break;
		} //  switch
	} // notifyChanged

	/**
	 * Use if the there was an set notification.
	 * 
	 * @param Notification
	 *            msg - Message indicates what has changed.
	 */
	protected void setNotification(Notification msg) {
		EStructuralFeature sf = (EStructuralFeature) msg.getFeature();
		if (sf == EJB_PACK.getEnterpriseBean_EjbClass() || sf == EJB_PACK.getEntity_PrimaryKey()) {
			if (!isMigrating()) {
				removeAttributeMaintenanceAdapter((Notifier) msg.getOldValue());
				addAttributeMaintenanceAdapter((Notifier) msg.getNewValue());
				touchKeyShapeAdapter(msg);
			}
		} else if (sf == EJB_PACK.getEntity_PrimaryKey()) {
			removeAttributeMaintenanceAdapter((Notifier) msg.getOldValue());
			if (shouldAddToPrimaryKeyClass((ContainerManagedEntity) getTarget()))
				addAttributeMaintenanceAdapter((Notifier) msg.getNewValue());
		} else {
			touchNotification(msg); //In EMF a TOUCH is a SET.
		}
	} // setNotification

	/**
	 * @return
	 */
	private boolean isMigrating() {
		EJBJar jar = ((ContainerManagedEntity) getTarget()).getEjbJar();
		if (jar != null)
			return EcoreUtil.getExistingAdapter(jar, J2EE_PROJ_MIGRATION_ADAPTER) != null;
		return false;
	}

	// setNotification
	/**
	 * Use if the there was an unset notification.
	 * 
	 * @param Notification
	 *            msg - Message indicates what has changed.
	 */
	protected void unsetNotification(Notification msg) {
		EStructuralFeature sf = (EStructuralFeature) msg.getFeature();
		if ((sf == EJB_PACK.getEnterpriseBean_EjbClass() || sf == EJB_PACK.getEntity_PrimaryKey()) && !isMigrating()) {
			removeAttributeMaintenanceAdapter((Notifier) msg.getOldValue());
		}
	} // unsetNotification

	/**
	 * Use if the there was an touch notification.
	 * 
	 * @param Notification
	 *            msg - Message indicates what has changed.
	 */
	protected void touchNotification(Notification msg) {
		if (((EStructuralFeature) msg.getFeature() == JDOMAdaptor.FLUSH_NEW_REFLECTION_SF || (EStructuralFeature) msg.getFeature() == JDOMAdaptor.FLUSH_REFLECTION_SF) && !isMigrating()) {
			if (msg.getNotifier() == ((ContainerManagedEntity) getTarget()).getEjbClass()) {
				touchBeanAdapter(msg);
			} else if (msg.getNotifier() == ((ContainerManagedEntity) getTarget()).getPrimaryKey()) {
				touchKeyShapeAdapter(msg);
			} // if
			//if the notifier is eq the primary key
			//else ejb bean class flush
		} // if
	} // touchNotification

	protected void removeAdapterNotification(Notification msg) {
		if (msg.getOldValue() == this && msg.getNotifier() instanceof ContainerManagedEntity) {
			ContainerManagedEntity cmp = (ContainerManagedEntity) msg.getNotifier();
			if (cmp.getEjbClass() != null)
				cmp.getEjbClass().eAdapters().remove(this);
			if (cmp.getPrimaryKey() != null)
				cmp.getPrimaryKey().eAdapters().remove(this);
		}
	}

	/**
	 * Adds the key shape adapter
	 * 
	 * @param Notifier
	 *            notifier - The current notifier.
	 */
	protected void addAttributeMaintenanceAdapter(Notifier notifier) {
		if (notifier != null && !notifier.eAdapters().contains(this))
			notifier.eAdapters().add(this);
	}

	/**
	 * Remove the key shape adapter
	 */
	protected void removeAttributeMaintenanceAdapter(Notifier notifier) {
		if (notifier != null)
			notifier.eAdapters().remove(this);
	}

	/**
	 * The key has been touched.
	 * 
	 * @param Notification
	 *            msg - The notification message.
	 */
	protected void touchKeyShapeAdapter(Notification msg) {
		if (msg == null)
			return;
		ContainerManagedEntity cmp = (ContainerManagedEntity) getTarget();
		JavaClass primaryKeyClass = cmp.getPrimaryKey();
		//Synch the two lists.
		synchCMPandKeyAttributes(cmp, primaryKeyClass);
	} // touchKeyShapeAdapter

	/**
	 * The ejb class has been flushed.
	 * 
	 * @param Notification
	 *            msg - The notification message.
	 */
	protected void touchBeanAdapter(Notification msg) {
		if (msg == null)
			return;
		ContainerManagedEntity cmp = (ContainerManagedEntity) getTarget();
		List cmpAttributes = cmp.getPersistentAttributes();
		if (cmpAttributes != null || !cmpAttributes.isEmpty()) {
			boolean modFlag = getCurrentModificationFlag();
			try {
				for (int i = 0; i < cmpAttributes.size(); i++) {
					CMPAttribute cmpAttr = (CMPAttribute) cmpAttributes.get(i);
					if (cmpAttr != null && !cmpAttr.isDerived()) {
						cmpAttr.setEType(null);
					}
				}
			} finally {
				setModificationFlag(modFlag);
			}
		}
		synchCMPandKeyAttributes(cmp, cmp.getPrimaryKey());
	}

	/**
	 * Synchronzies the cmp and primary key class attributes.
	 * 
	 * @param ContainerManagedEntity
	 *            cmp - The cmp.
	 * @param JavaClass
	 *            primaryKeyClass
	 */
	protected void synchCMPandKeyAttributes(ContainerManagedEntity cmp, JavaClass primaryKeyClass) {
		if (cmp == null || primaryKeyClass == null || cmp.getPrimKeyField() != null || primaryKeyClass.getQualifiedName().startsWith("java.lang")) //$NON-NLS-1$
			return;

		// make sure we flush the primary key to get latest updates if no save
		// took place
		flushPrimaryKeyClass(primaryKeyClass);

		List cmpAttributes = cmp.getPersistentAttributes();
		if (cmpAttributes != null || !cmpAttributes.isEmpty()) {
			foundKeys.clear();
			List keyAttributesList = cmp.getKeyAttributes();
			foundKeys.addAll(keyAttributesList);
			boolean modFlag = getCurrentModificationFlag();
			try {
				List keyFields = primaryKeyClass.getFieldsExtended();
				for (int i = 0; i < keyFields.size(); i++) {
					Field field = (Field) keyFields.get(i);
					if (field.getJavaVisibility() != JavaVisibilityKind.PUBLIC_LITERAL)
						continue;
					String fieldName = field.getName();
					if (fieldName != null) {
						CMPAttribute keyAttr = cmp.getKeyAttribute(fieldName);
						if (keyAttr == null)
							cmp.addKeyAttributeName(fieldName);
						else
							foundKeys.remove(keyAttr);
					}
				}
				if (!foundKeys.isEmpty())
					keyAttributesList.removeAll(foundKeys);
			} finally {
				setModificationFlag(modFlag);
			}
		}
	}

	public void setTarget(Notifier newTarget) {
		boolean isChanged = getTarget() != newTarget;
		if (newTarget == null) {
			super.setTarget(newTarget);
			cmpResource = null;
		} else if (newTarget instanceof ContainerManagedEntity) {
			//TODO - uncomment when compile errors are gone
			if (false)/* (EJBCommandCopier.isCodegenCopy(newTarget)) */
				newTarget.eAdapters().remove(this);
			else {
				Resource res = ((ContainerManagedEntity) newTarget).eResource();
				if (res != null && res.getResourceSet() != null) {
					super.setTarget(newTarget);
					if (isChanged)
						initializeTarget();
				}
			}
		}
	}

	/**
	 *  
	 */
	private void initializeTarget() {
		ContainerManagedEntity cmp = (ContainerManagedEntity) getTarget();
		addAttributeMaintenanceAdapter(cmp.getEjbClass());
		if (shouldAddToPrimaryKeyClass(cmp))
			addAttributeMaintenanceAdapter(cmp.getPrimaryKey());
	}

	private boolean shouldAddToPrimaryKeyClass(ContainerManagedEntity cmp) {
		return cmp.getPrimKeyField() == null && cmp.getPrimaryKey() != null && !cmp.getPrimaryKey().getQualifiedName().startsWith("java.lang"); //$NON-NLS-1$
	}

	protected Resource getCMPResource() {
		if (cmpResource == null && getTarget() != null)
			cmpResource = ((EObject) getTarget()).eResource();
		return cmpResource;
	}

	protected boolean getCurrentModificationFlag() {
		return getCMPResource() != null && cmpResource.getContents() != null ? cmpResource.isModified() : false;
	}

	protected void setModificationFlag(boolean aBoolean) {
		if (getCMPResource() != null)
			getCMPResource().setModified(aBoolean);
	}

	public ContainerManagedEntity getCMP() {
		if (getTarget() instanceof ContainerManagedEntity)
			return (ContainerManagedEntity) getTarget();
		return null;
	}

	protected boolean flushPrimaryKeyClass(JavaClass primaryKeyClass) {
		// ensure the primary key class is flushed and reloaded to get latest
		// content
		IProject p = ProjectUtilities.getProject(primaryKeyClass);
		try {
			List natures = EMFNature.getRegisteredRuntimes(p);
			EMFNature nature = null;
			for (int i = 0; i < natures.size(); i++) {
				if (natures.get(i) instanceof EMFNature)
					nature = (EMFNature) natures.get(i);
			}
			List adapterFactories = nature.getResourceSet().getAdapterFactories();
			AdapterFactory factory = EcoreUtil.getAdapterFactory(adapterFactories, ReadAdaptor.TYPE_KEY);
			if (factory instanceof JavaJDOMAdapterFactory) {
				JavaJDOMAdapterFactory javaFactory = (JavaJDOMAdapterFactory) factory;
				javaFactory.flushReflectionNoNotification(primaryKeyClass.getQualifiedName());
				return true;
			}
		} catch (Exception e) {
			// We don't really care what the exception was, we'll just bail out
			// and return false;
		}
		return false;
	}
}