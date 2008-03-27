/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACES;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.Signature;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateSessionBeanTemplateModel extends
		CreateEnterpriseBeanTemplateModel {

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

	protected BusinessInterface currentBusinessInterface = null;
	protected String localHomeClassName = null;
	protected String remoteHomeClassName = null;
	
	public CreateSessionBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
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
		
		if (isRemoteHomeChecked()) {
			collection.add(QUALIFIED_REMOTE_HOME);
			collection.add(remoteHomeClassName);
		}
		
		if (isLocalHomeChecked()) {
			collection.add(QUALIFIED_LOCAL_HOME);
			collection.add(localHomeClassName);
		}
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (iface.isLocal() && iface.exists()) {
				collection.add(QUALIFIED_LOCAL);
			} else if (iface.isRemote() && iface.exists()) { 
				collection.add(QUALIFIED_REMOTE);
			}
		}
		
		return collection;
	}
	
	@Override
	public List<String> getQualifiedInterfaces() {
		List<String> result = new ArrayList<String>();
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			result.add(iface.getFullyQualifiedName());
		}
		
		return result;
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
	
	@SuppressWarnings("unchecked")
	public List<BusinessInterface> getBusinessInterfaces() {
		return (List<BusinessInterface>) dataModel.getProperty(BUSINESS_INTERFACES);
	}
	
	public List<BusinessInterface> getExistingLocalBusinessInterfaces() {
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (iface.isLocal() && iface.exists())
				result.add(iface);
		}
		
		return result;
	}
	
	public List<BusinessInterface> getExistingRemoteBusinessInterfaces() {
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (iface.isRemote() && iface.exists())
				result.add(iface);
		}
		
		return result;
	}
	
	public boolean isLocalHomeChecked() {
		return dataModel.getBooleanProperty(LOCAL_HOME);
	}
	
	public boolean isRemoteHomeChecked() {
		return dataModel.getBooleanProperty(REMOTE_HOME);
	}
	
	public Map<String, String> getClassAnnotationParams() {
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

	public void setCurrentBusinessInterface(BusinessInterface newBI)
	{
		currentBusinessInterface = newBI;
	}

	public String getBusinessInterfaceJavaPackageName() {
		String packageName = null;
		if (currentBusinessInterface != null)
		{
			packageName = Signature.getQualifier(currentBusinessInterface.getFullyQualifiedName());
		}
		return packageName;
	}

	public String getBusinessInterfaceClassName() {
		String className = null;
		if (currentBusinessInterface != null)
		{
			className = currentBusinessInterface.getSimpleName();
		}
		return className;
	}

	public String getLocalHomePackage() {
		return Signature.getQualifier(localHomeClassName);
	}

	public String getLocalHomeClassSimpleName()
	{
		return Signature.getSimpleName(localHomeClassName);
	}

	public void setLocalHomeClassName(String localHomeClassName) {
		this.localHomeClassName = localHomeClassName;
	}

	public String getRemoteHomePackage() {
		return Signature.getQualifier(remoteHomeClassName);
	}

	public String getRemoteHomeClassSimpleName()
	{
		return Signature.getSimpleName(remoteHomeClassName);
	}

	public void setRemoteHomeClassName(String remoteHomeClassName) {
		this.remoteHomeClassName = remoteHomeClassName;
	}
}
