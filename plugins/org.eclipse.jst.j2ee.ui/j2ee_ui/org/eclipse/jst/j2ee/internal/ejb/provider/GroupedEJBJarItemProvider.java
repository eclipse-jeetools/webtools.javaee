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
package org.eclipse.jst.j2ee.internal.ejb.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.provider.J2EEItemProvider;


/**
 * @author Sachin P Patel
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class GroupedEJBJarItemProvider extends EJBJarItemProvider {

	private GroupedSessionItemProvider sessionProvider = null;
	private GroupedEntityItemProvider entityProvider = null;
	private GroupedMessageItemProvider messageProvider = null;

	private static Hashtable sessionTable = new Hashtable();
	private static Hashtable entityTable = new Hashtable();
	private static Hashtable messageTable = new Hashtable();

	private static final String SESSION = EJBUIMessages.getResourceString("GroupedEJBJarItemProvider_UI_0"); //$NON-NLS-1$
	private static final String ENTITY = EJBUIMessages.getResourceString("GroupedEJBJarItemProvider_UI_1"); //$NON-NLS-1$
	private static final String MESSAGE = EJBUIMessages.getResourceString("GroupedEJBJarItemProvider_UI_2"); //$NON-NLS-1$

	//	Normally there is one instance of an item provider for all instances of the objecct
	//	in the tree. The item provider would be stateless. However, because we are doing tricks
	//	here, we are keeping track of all items this provider manages. The key in the map is the
	//	object (EJBJar), and the value is the children nodes that we inserted

	protected Map children = new HashMap();
	private boolean showAssemblyDescriptor;
	private boolean isDisposing;

	public GroupedEJBJarItemProvider(AdapterFactory adapterFactory, boolean showAssemblyDescriptor) {
		super(adapterFactory);
		this.showAssemblyDescriptor = showAssemblyDescriptor;
	}

	public Collection getChildren(Object object) {
		List result = initChildren(object);
		if (showAssemblyDescriptor) {
			if (((EJBJar) object).getAssemblyDescriptor() != null)
				result.add(((EJBJar) object).getAssemblyDescriptor());
		}
		return result;
	}

	protected List initChildren(Object object) {
		EJBJar ejbJar = (EJBJar) object;
		List allRootBeans = getAllRootBeans(ejbJar);

		boolean is20Jar = is20Jar(ejbJar);

		List localChildren = new ArrayList(5);

		List entityBeans = new ArrayList();
		List sessionBeans = new ArrayList();
		List messageBeans = new ArrayList();

		catagorizeBeans(allRootBeans, entityBeans, sessionBeans, messageBeans);

		//ENABLE FOR: NOT TO SHOW EMPTY GROUPS
		//if (sessionBeans.size() > 0) {
		if (sessionTable.get(ejbJar) == null) {
			//create new item provider instance
			sessionProvider = new GroupedSessionItemProvider(adapterFactory, null, getImage(SESSION), object, sessionBeans);
			sessionTable.put(ejbJar, sessionProvider);
			localChildren.add(sessionProvider);
		} else {
			//use existing instance from table
			localChildren.add(sessionTable.get(ejbJar));
		}
		//}

		//ENABLE FOR: NOT TO SHOW EMPTY GROUPS
		//if (entityBeans.size() > 0) {
		if (entityTable.get(ejbJar) == null) {
			//create new item provider instance
			entityProvider = new GroupedEntityItemProvider(adapterFactory, null, getImage(ENTITY), object, entityBeans);
			entityTable.put(ejbJar, entityProvider);
			localChildren.add(entityProvider);
		} else {
			//use existing instance from table
			localChildren.add(entityTable.get(ejbJar));
		}
		//}

		//ENABLE FOR: NOT TO SHOW EMPTY GROUPS
		//if (is20Jar && messageBeans.size() > 0) {
		if (is20Jar) {
			if (messageTable.get(ejbJar) == null) {
				//create new item provider instance
				messageProvider = new GroupedMessageItemProvider(adapterFactory, null, getImage(MESSAGE), object, messageBeans);
				messageTable.put(ejbJar, messageProvider);
				localChildren.add(messageProvider);
			} else {
				//use existing instance from table
				localChildren.add(messageTable.get(ejbJar));
			}
		}
		//}

		children.put(object, localChildren);

		return localChildren;
	}

	protected static List getAllRootBeans(EJBJar ejbJar) {
		return ejbJar.getEnterpriseBeans();
	}

	protected void catagorizeBeans(List allRootBeans, List entityBeans, List sessionBeans, List messageBeans) {
		for (int i = 0; i < allRootBeans.size(); i++) {
			if (((EnterpriseBean) allRootBeans.get(i)).isSession()) {
				sessionBeans.add(allRootBeans.get(i));
			} else if (((EnterpriseBean) allRootBeans.get(i)).isEntity()) {
				entityBeans.add(allRootBeans.get(i));
			} else if (((EnterpriseBean) allRootBeans.get(i)).isMessageDriven()) {
				messageBeans.add(allRootBeans.get(i));
			}
		}
	}

	static protected GroupedSessionItemProvider getSessionNode(Object object) {
		return (GroupedSessionItemProvider) sessionTable.get(object);
	}

	static protected GroupedEntityItemProvider getEntityNode(Object object) {
		return (GroupedEntityItemProvider) entityTable.get(object);
	}

	static protected GroupedMessageItemProvider getMessageNode(Object object) {
		return (GroupedMessageItemProvider) messageTable.get(object);
	}

	public Object getImage(String type) {
		if (type.equals(SESSION))
			return J2EEPlugin.getPlugin().getImage("sessionBean_obj"); //$NON-NLS-1$
		else if (type.equals(MESSAGE))
			return J2EEPlugin.getPlugin().getImage("message_bean_obj"); //$NON-NLS-1$
		else if (type.equals(ENTITY))
			return J2EEPlugin.getPlugin().getImage("entitybean_obj"); //$NON-NLS-1$
		else
			return null;
	}

	private boolean is20Jar(EJBJar ejbJar) {
		switch (ejbJar.getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				return false;
			case J2EEVersionConstants.EJB_2_0_ID :
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				return true;
		}
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER && notification.getOldValue() == this && !isDisposing) {
			removeTarget(notification);
			return;
		}
		if (notification.getFeature() == EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans()) {
			J2EEItemProvider provider = beansChanged((EJBJar) notification.getNotifier(), notification.getEventType(), notification.getOldValue(), notification.getNewValue(), notification.getPosition());

			//EJB's group has not been added yet, need to add group to tree before EJB can be added
			if (provider == null) {
				Notification msg = new NotificationImpl(Notification.ADD, null, getEJBItemProvider((EnterpriseBean) notification.getNewValue()), 1);
				NotificationWrapper notificationWrapper = new NotificationWrapper(notification.getNotifier(), msg);
				fireNotifyChanged(notificationWrapper);
				provider = beansChanged((EJBJar) notification.getNotifier(), notification.getEventType(), notification.getOldValue(), notification.getNewValue(), notification.getPosition());
			}

			//Fire notification for EJB add or remove
			NotificationWrapper notificationWrapper = new NotificationWrapper(provider, notification);
			fireNotifyChanged(notificationWrapper);

			//ENABLE FOR: NOT TO SHOW EMPTY GROUPS
			//If Group is empty remove the group
			/*
			 * if (provider != null && provider.getChildren().size() == 0) { Notification msg = new
			 * NotificationImpl(Notification.REMOVE, provider, null, 1); notificationWrapper = new
			 * NotificationWrapper(notification.getNotifier(), msg);
			 * fireNotifyChanged(notificationWrapper);
			 * 
			 * //Group is removed so flush out table entry if (provider instanceof
			 * GroupedSessionItemProvider) { sessionTable.remove(notification.getNotifier());
			 * provider = null; } else if (provider instanceof GroupedEntityItemProvider) {
			 * entityTable.remove(notification.getNotifier()); provider = null; } else if (provider
			 * instanceof GroupedMessageItemProvider) {
			 * messageTable.remove(notification.getNotifier()); provider = null; }
			 * 
			 * //If all groups are removed remove the extended children List allChildren = new
			 * ArrayList(this.getChildren((EJBJar) notification.getNotifier()));
			 * if(sessionTable.get(notification.getNotifier()) == null &&
			 * entityTable.get(notification.getNotifier()) == null &&
			 * messageTable.get(notification.getNotifier()) == null) { for(int i = 0; i <
			 * allChildren.size(); i++) { Notification message = new
			 * NotificationImpl(Notification.REMOVE, allChildren.get(i), null, 1);
			 * notificationWrapper = new NotificationWrapper(notification.getNotifier(), message);
			 * fireNotifyChanged(notificationWrapper); } this.getChildren((EJBJar)
			 * notification.getNotifier()).clear(); } }
			 */
		} else {
			super.notifyChanged(notification);
		}
	}

	protected J2EEItemProvider beansChanged(EJBJar ejbJar, int eventType, Object oldValue, Object newValue, int pos) {
		J2EEItemProvider provider = getItemProvider(ejbJar, oldValue, newValue);

		if (provider != null) {
			Collection grandChildren = provider.getChildren();

			switch (eventType) {
				case Notification.ADD : {
					if (!grandChildren.contains(newValue))
						grandChildren.add(newValue);

					break;
				}
				case Notification.ADD_MANY : {
					grandChildren.addAll((Collection) newValue);
					break;
				}
				case Notification.REMOVE : {
					grandChildren.remove(oldValue);
					break;
				}
				case Notification.REMOVE_MANY : {
					grandChildren.removeAll((Collection) oldValue);
					break;
				}
			}
		} else {
			//GroupedProvider for new bean does not exist, create one.
			List allRootBeans = getAllRootBeans(ejbJar);

			List entityBeans = new ArrayList();
			List sessionBeans = new ArrayList();
			List messageBeans = new ArrayList();

			catagorizeBeans(allRootBeans, entityBeans, sessionBeans, messageBeans);

			if (newValue instanceof Session) {
				sessionProvider = new GroupedSessionItemProvider(adapterFactory, null, getImage(SESSION), ejbJar, sessionBeans);
				sessionTable.put(ejbJar, sessionProvider);
			} else if (newValue instanceof Entity) {
				entityProvider = new GroupedEntityItemProvider(adapterFactory, null, getImage(ENTITY), ejbJar, entityBeans);
				entityTable.put(ejbJar, entityProvider);
			} else if (newValue instanceof MessageDriven) {
				messageProvider = new GroupedMessageItemProvider(adapterFactory, null, getImage(MESSAGE), ejbJar, messageBeans);
				messageTable.put(ejbJar, messageProvider);
			}
		}
		return provider;
	}

	static public J2EEItemProvider getEJBJarItemProvider(EJBJar ejbJar, Object bean) {
		J2EEItemProvider provider = null;
		if (ejbJar != null && bean != null) {
			if (bean instanceof Session) {
				provider = getSessionNode(ejbJar);
			} else if (bean instanceof Entity) {
				provider = getEntityNode(ejbJar);
			} else if (bean instanceof MessageDriven) {
				provider = getMessageNode(ejbJar);
			}
		}
		return provider;
	}

	static public J2EEItemProvider getEJBItemProvider(EnterpriseBean bean) {
		if (bean != null) {
			EJBJar ejbJar = bean.getEjbJar();
			return getEJBJarItemProvider(ejbJar, bean);
		}
		return null;
	}

	protected J2EEItemProvider getItemProvider(EJBJar ejbJar, Object oldValue, Object newValue) {
		if (newValue != null)
			return getEJBJarItemProvider(ejbJar, newValue);
		else if (oldValue != null)
			return getEJBJarItemProvider(ejbJar, oldValue);
		else
			return null;
	}

	public static boolean isRootBean(EnterpriseBean bean) {
		List allRootBeans = getAllRootBeans(bean.getEjbJar());
		if (allRootBeans != null && allRootBeans.contains(bean)) {
			return true;
		}
		return false;
	}

	//	Utility method for garbage collection - if EJBJar removed, remove
	//	all entires in table for EJBJar
	static public void flushOutTableEntriesForEJBJar(EJBJar ejbJar) {
		sessionTable.remove(ejbJar);
		entityTable.remove(ejbJar);
		messageTable.remove(ejbJar);
	}

	public boolean hasChildren(Object parent) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.provider.EJBJarItemProvider#removeTarget(org.eclipse.emf.common.notify.Notification)
	 */
	protected void removeTarget(Notification not) {
		if (not.getNotifier() instanceof EJBJar)
			flushOutTableEntriesForEJBJar((EJBJar) not.getNotifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		try {
			isDisposing = true;
			super.dispose();
		} finally {
			isDisposing = false;
		}
	}
}