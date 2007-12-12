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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateSessionBeanTemplateModel extends CreateEnterpriseBeanTemplateModel {

	private static final String MAP_NAME = "mappedName"; //$NON-NLS-1$
	private static final String EJB_NAME = "name"; //$NON-NLS-1$
	public static final String QUALIFIED_STATELESS_BEAN = "javax.ejb.Stateless"; //$NON-NLS-1$
	public static final String QUALIFIED_STATEFUL_BEAN = "javax.ejb.Stateful"; //$NON-NLS-1$
	
	public static final String QUALIFIED_REMOTE_HOME = "javax.ejb.RemoteHome"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL_HOME = "javax.ejb.LocalHome"; //$NON-NLS-1$
	public static final String TRANSACTION_MANAGEMENT = "javax.ejb.TransactionManagement"; //$NON-NLS-1$
	public static final String TRANSACTION_MANAGEMENT_TYPE = "javax.ejb.TransactionManagementType"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL = "javax.ejb.Local"; //$NON-NLS-1$
	public static final String QUALIFIED_REMOTE = "javax.ejb.Remote"; //$NON-NLS-1$
	
	public static final String STATELESS_BEAN_TYPE = "@Stateless"; //$NON-NLS-1$
	public static final String STATEFUL_BEAN_TYPE = "@Stateful"; //$NON-NLS-1$

	public CreateSessionBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		List<RemoteLocalInterface> biInterface = (List<RemoteLocalInterface>) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		for (Iterator i = biInterface.iterator();i.hasNext();){
			RemoteLocalInterface tempInterface = (RemoteLocalInterface) i.next();
			if (tempInterface.getInterfaceType() != null){
				collection.add(tempInterface.getInterfaceName());
			}
		}
		String stateType = getProperty(INewSessionBeanClassDataModelProperties.STATE_TYPE);
		if (stateType.equals("Stateless Session Bean")){
			collection.add(QUALIFIED_STATELESS_BEAN);
		}
		else {
			collection.add(QUALIFIED_STATEFUL_BEAN);
		}
		if (!isContainerType()){
			collection.add(TRANSACTION_MANAGEMENT);
			collection.add(TRANSACTION_MANAGEMENT_TYPE);
		}
		if (isRemoteHome()){
			collection.add(QUALIFIED_REMOTE_HOME);
		}
		if (isLocalHome()){
			collection.add(QUALIFIED_LOCAL_HOME);
		}
		if (getBusnessRemoteList().size() > 0){
			collection.add(QUALIFIED_REMOTE);
		}
		if (getBusnessLocalList().size() > 0){
			collection.add(QUALIFIED_LOCAL);
		}
		return collection;
	}
	
	public String getBeanType() {
		String stateType = getProperty(INewSessionBeanClassDataModelProperties.STATE_TYPE);
		String beanType;
		if (stateType.equals("Stateless Session Bean")){
			beanType = STATELESS_BEAN_TYPE;
		}
		else {
			beanType = STATEFUL_BEAN_TYPE;
		}
		return beanType;
	}

	public String getBeanDisplayName(){
		return getProperty(INewSessionBeanClassDataModelProperties.EJB_NAME);
	}
	
	@SuppressWarnings("unchecked")
	public List<RemoteLocalInterface> getBusnessRemoteList(){
		List<RemoteLocalInterface> list = (List<RemoteLocalInterface>) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		List listRemoteInterface = new ArrayList();
		for (Iterator<RemoteLocalInterface> i = list.iterator();i.hasNext();){
			RemoteLocalInterface tmp = (RemoteLocalInterface) i.next();
			if (tmp.getInterfaceType() != null && tmp.isRemote()){
				listRemoteInterface.add(tmp);
			}
		}
		return listRemoteInterface; 
	}
	
	@SuppressWarnings("unchecked")
	public List<RemoteLocalInterface> getBusnessLocalList(){
		List<RemoteLocalInterface> list = (List<RemoteLocalInterface>) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		List listRemoteInterface = new ArrayList();
		for (Iterator<RemoteLocalInterface> i = list.iterator();i.hasNext();){
			RemoteLocalInterface tmp = (RemoteLocalInterface) i.next();
			if (tmp.getInterfaceType() != null && tmp.isLocal()){
				listRemoteInterface.add(tmp);
			}
		}
		return listRemoteInterface; 
	}
	@SuppressWarnings("unchecked")
	public List<String> getInterfaces(){
		List<String> result = new ArrayList<String>();
		List<String> superList = super.getInterfaces();
		if (!superList.isEmpty()){
			result.addAll(superList);
		}
		List<RemoteLocalInterface> tmpList = (List<RemoteLocalInterface>) dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		
		for (Iterator<RemoteLocalInterface> i = tmpList.iterator();i.hasNext();){
			RemoteLocalInterface tmp = (RemoteLocalInterface) i.next();
			result.add(tmp.getSimpleName());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getRemoteInterface(){
		String result = null;
		List<RemoteLocalInterface> biInterface = (List<RemoteLocalInterface>) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		for (Iterator<RemoteLocalInterface> i = biInterface.iterator();i.hasNext();){
			RemoteLocalInterface tmp = (RemoteLocalInterface) i.next();
			if ((tmp.getInterfaceType() == null) && (tmp.isRemote())){
				result = tmp.getSimpleName();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getLocalInterface(){
		String result = null;
		List<RemoteLocalInterface> biInterface = (List<RemoteLocalInterface>) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.BUSSNESINTERFACE_LIST);
		for (Iterator<RemoteLocalInterface> i = biInterface.iterator();i.hasNext();){
			RemoteLocalInterface tmp = (RemoteLocalInterface) i.next();
			if ((tmp.getInterfaceType() == null) && (tmp.isLocal())){
				result = tmp.getSimpleName();
			}
		}
		return result;
	}
	public Boolean isLocalHome(){
		return (Boolean) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.LOCAL_HOME);
	}
	
	public Boolean isRemoteHome(){
		return (Boolean) this.dataModel.getProperty(INewSessionBeanClassDataModelProperties.REMOTE_HOME);
	}
	
	public Boolean isContainerType(){
		String transaction_type = this.dataModel.getStringProperty(INewSessionBeanClassDataModelProperties.TRANSACTION_TYPE);
		return transaction_type.equals("Container");
	}
	public Map<String, String> getAnnotationParameters(){
		Map<String, String> result = new Hashtable<String, String>();
		String dispName = this.dataModel.getStringProperty(INewSessionBeanClassDataModelProperties.EJB_NAME);
		if (!dispName.equals(getClassName()))
			result.put(EJB_NAME, dispName);
		String mapName = this.dataModel.getStringProperty(INewSessionBeanClassDataModelProperties.MAPPED_NAME).trim();
		if (mapName != null && mapName.length() > 0){
			result.put(MAP_NAME, mapName);
		}
		return result;
	}
}
