/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateSessionBeanTemplateModel extends
		CreateEnterpriseBeanTemplateModel implements
		INewSessionBeanClassDataModelProperties {

	private static final String ATT_MAPPED_NAME = "mappedName"; //$NON-NLS-1$
	private static final String ATT_NAME = "name"; //$NON-NLS-1$
	
	public static final String QUALIFIED_STATELESS = "javax.ejb.Stateless"; //$NON-NLS-1$
	public static final String QUALIFIED_STATEFUL = "javax.ejb.Stateful"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL = "javax.ejb.Local"; //$NON-NLS-1$
	public static final String QUALIFIED_REMOTE = "javax.ejb.Remote"; //$NON-NLS-1$
	public static final String QUALIFIED_REMOTE_HOME = "javax.ejb.RemoteHome"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL_HOME = "javax.ejb.LocalHome"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT = "javax.ejb.TransactionManagement"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT_TYPE = "javax.ejb.TransactionManagementType"; //$NON-NLS-1$
	
	public static final String STATELESS_ANNOTATION = "@Stateless"; //$NON-NLS-1$
	public static final String STATEFUL_ANNOTATION = "@Stateful"; //$NON-NLS-1$

	public CreateSessionBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		List<BusinessInterface> biInterfaces = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		for (BusinessInterface iface : biInterfaces) {
			if (iface.getInterfaceType() != null) {
				collection.add(iface.getInterfaceName());
			}
		}
		
		int stateType = dataModel.getIntProperty(STATE_TYPE);
		if (stateType == NewSessionBeanClassDataModelProvider.STATE_TYPE_STATELESS_INDEX)
			collection.add(QUALIFIED_STATELESS);
		else if (stateType == NewSessionBeanClassDataModelProvider.STATE_TYPE_STATEFUL_INDEX)
			collection.add(QUALIFIED_STATEFUL);
		else
			throw new IllegalStateException("illegal state type: " + stateType);
		
		if (!isContainerType()) {
			collection.add(QUALIFIED_TRANSACTION_MANAGEMENT);
			collection.add(QUALIFIED_TRANSACTION_MANAGEMENT_TYPE);
		}
		
		if (isRemoteHome()) {
			collection.add(QUALIFIED_REMOTE_HOME);
		}
		
		if (isLocalHome()) {
			collection.add(QUALIFIED_LOCAL_HOME);
		}
		
		if (getBusinessRemoteList().size() > 0) {
			collection.add(QUALIFIED_REMOTE);
		}
		
		if (getBusinessLocalList().size() > 0) {
			collection.add(QUALIFIED_LOCAL);
		}
		
		return collection;
	}
	
	public String getClassAnnotation() {
		int stateType = dataModel.getIntProperty(STATE_TYPE);
		
		String beanType;
		if (stateType == NewSessionBeanClassDataModelProvider.STATE_TYPE_STATELESS_INDEX)
			beanType = STATELESS_ANNOTATION;
		else if (stateType == NewSessionBeanClassDataModelProvider.STATE_TYPE_STATEFUL_INDEX)
			beanType = STATEFUL_ANNOTATION;
		else 
			throw new IllegalStateException("illegal state type: " + stateType);
		
		return beanType;
	}
	
	public List<BusinessInterface> getBusinessRemoteList() {
		List<BusinessInterface> list = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		for (BusinessInterface iface : list) {
			if (iface.getInterfaceType() != null && iface.isRemote()) {
				result.add(iface);
			}
		}
		return result; 
	}
	
	public List<BusinessInterface> getBusinessLocalList() {
		List<BusinessInterface> list = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		for (BusinessInterface iface : list) {
			if (iface.getInterfaceType() != null && iface.isLocal()) {
				result.add(iface);
			}
		}
		return result; 
	}
	public List<String> getInterfaces() {
		List<String> result = new ArrayList<String>();
		List<String> superList = super.getInterfaces();
		if (!superList.isEmpty()) {
			result.addAll(superList);
		}
		
		List<BusinessInterface> tmpList = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		for (BusinessInterface iface : tmpList) {
			result.add(iface.getSimpleName());
		}
		
		return result;
	}

	public String getRemoteInterface() {
		String result = null;
		List<BusinessInterface> biInterface = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		for (BusinessInterface iface : biInterface) {
			if ((iface.getInterfaceType() == null) && (iface.isRemote())) {
				result = iface.getSimpleName();
			}
		}
		return result;
	}

	public String getLocalInterface() {
		String result = null;
		List<BusinessInterface> biInterface = (List<BusinessInterface>) dataModel.getProperty(BUSINESSINTERFACES);
		for (BusinessInterface iface : biInterface) {
			if ((iface.getInterfaceType() == null) && (iface.isLocal())) {
				result = iface.getSimpleName();
			}
		}
		return result;
	}
	
	public boolean isLocalHome() {
		return dataModel.getBooleanProperty(LOCAL_HOME);
	}
	
	public boolean isRemoteHome() {
		return dataModel.getBooleanProperty(REMOTE_HOME);
	}
	
	public boolean isContainerType() {
		int transactionType = dataModel.getIntProperty(TRANSACTION_TYPE);
		return transactionType == NewSessionBeanClassDataModelProvider.TRANSACTION_TYPE_CONTAINER_INDEX;
	}
	
	public Map<String, String> getAnnotationParameters() {
		Map<String, String> result = new Hashtable<String, String>();
		String dispName = getProperty(EJB_NAME);
		if (!dispName.equals(getClassName()))
			result.put(ATT_NAME, dispName);
		String mappedName = getProperty(MAPPED_NAME).trim();
		if (mappedName != null && mappedName.length() > 0) {
			result.put(ATT_MAPPED_NAME, mappedName);
		}
		return result;
	}
}
