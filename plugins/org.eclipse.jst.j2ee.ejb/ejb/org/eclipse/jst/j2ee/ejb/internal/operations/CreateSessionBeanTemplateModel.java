/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * Roberto Sanchez, rsanchez@mx1.ibm.com - Add remote and local annotations to bean class
 * IBM - Async and Non-persistent support
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.*;
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
	public static final String QUALIFIED_SINGLETON = "javax.ejb.Singleton"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL_BEAN = "javax.ejb.LocalBean"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL = "javax.ejb.Local"; //$NON-NLS-1$
	public static final String QUALIFIED_REMOTE = "javax.ejb.Remote"; //$NON-NLS-1$
	public static final String QUALIFIED_REMOTE_HOME = "javax.ejb.RemoteHome"; //$NON-NLS-1$
	public static final String QUALIFIED_LOCAL_HOME = "javax.ejb.LocalHome"; //$NON-NLS-1$
	public static final String QUALIFIED_ASYNCHRONOUS = "javax.ejb.Asynchronous"; //$NON-NLS-1$
	 
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT = "javax.ejb.TransactionManagement"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT_TYPE = "javax.ejb.TransactionManagementType"; //$NON-NLS-1$
	
	public static final String STATELESS_ANNOTATION = "@Stateless"; //$NON-NLS-1$
	public static final String STATEFUL_ANNOTATION = "@Stateful"; //$NON-NLS-1$
	public static final String SINGLETON_ANNOTATION = "@Singleton"; //$NON-NLS-1$

	protected BusinessInterface currentBusinessInterface = null;
	protected String localHomeClassName = null;
	protected String localComponentClassName = null;
	protected String remoteHomeClassName = null;
	protected String remoteComponentClassName = null;
	
	public CreateSessionBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		String stateType = dataModel.getStringProperty(STATE_TYPE);
		if (stateType.equals(StateType.STATELESS.toString())) {
			collection.add(QUALIFIED_STATELESS);
		} else if (stateType.equals(StateType.STATEFUL.toString())) {
			collection.add(QUALIFIED_STATEFUL);
		} else if (stateType.equals(StateType.SINGLETON.toString())) {
			collection.add(QUALIFIED_SINGLETON);
		} else {
			throw new IllegalStateException("illegal state type: " + stateType); //$NON-NLS-1$
		}
		
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
		
		if (isNoInterfaceChecked()) {
			collection.add(QUALIFIED_LOCAL_BEAN);
		}
		
		if (isAsynchronous()) {
			collection.add(QUALIFIED_ASYNCHRONOUS);
		}
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		if (addBusinessAnnotationToBeanClass()){
			for (BusinessInterface iface : interfaces) {
				if (iface.isLocal()) {
					collection.add(QUALIFIED_LOCAL);
				} else if (iface.isRemote()) { 
					collection.add(QUALIFIED_REMOTE);
				}
			}
		}
		else {
			for (BusinessInterface iface : interfaces) {
				if (iface.isLocal() && iface.exists()) {
					collection.add(QUALIFIED_LOCAL);
				} else if (iface.isRemote() && iface.exists()) { 
					collection.add(QUALIFIED_REMOTE);
				}
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
		String stateType = dataModel.getStringProperty(STATE_TYPE);
		
		String beanType;
		if (stateType.equals(StateType.STATELESS.toString())) {
			beanType = STATELESS_ANNOTATION;
		} else if (stateType.equals(StateType.STATEFUL.toString())) {
			beanType = STATEFUL_ANNOTATION;
		} else if (stateType.equals(StateType.SINGLETON.toString())) {
			beanType = SINGLETON_ANNOTATION;
		} else { 
			throw new IllegalStateException("illegal state type: " + stateType); //$NON-NLS-1$
		}
		
		return beanType;
	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessInterface> getBusinessInterfaces() {
		return (List<BusinessInterface>) dataModel.getProperty(INTERFACES);
	}
	
	public List<BusinessInterface> getLocalBusinessInterfaces() {
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (iface.isLocal())
				result.add(iface);
		}
		
		return result;
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
	
	public List<BusinessInterface> getRemoteBusinessInterfaces() {
		List<BusinessInterface> result = new ArrayList<BusinessInterface>();
		
		List<BusinessInterface> interfaces = getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (iface.isRemote())
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

	public boolean isNoInterfaceChecked() {
		return dataModel.getBooleanProperty(NO_INTERFACE);
	}
	
	public boolean isAsynchronous() {
		return dataModel.getBooleanProperty(ASYNC);
	}
	
	public Map<String, String> getClassAnnotationParams() {
		Map<String, String> result = new Hashtable<String, String>();
		String dispName = getProperty(EJB_NAME).trim();
		if (!dispName.equals(getClassName()) && (dispName.length() > 0))
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

	
	
	public String getLocalComponentPackage() {
		return Signature.getQualifier(localComponentClassName);
	}

	public String getLocalComponentClassSimpleName()
	{
		return Signature.getSimpleName(localComponentClassName);
	}

	public void setLocalComponentClassName(String localComponentClassName) {
		this.localComponentClassName = localComponentClassName;
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
	
	
	
	
	
	public String getRemoteComponentPackage() {
		return Signature.getQualifier(remoteComponentClassName);
	}

	public String getRemoteComponentClassSimpleName()
	{
		return Signature.getSimpleName(remoteComponentClassName);
	}

	public void setRemoteComponentClassName(String remoteComponentClassName) {
		this.remoteComponentClassName = remoteComponentClassName;
	}
	
	public boolean addBusinessAnnotationToBeanClass(){
		String prop = dataModel.getStringProperty(BUSINESS_INTERFACE_ANNOTATION_LOCATION);
		return (prop.equals(BusinessInterfaceAnnotationLocationType.BEAN_CLASS_ONLY.toString()) ||
				prop.equals(BusinessInterfaceAnnotationLocationType.BEAN_CLASS_AND_INTERFACE.toString()));	
	}
	
	public boolean addBusinessAnnotationToInterface(){
		String prop = dataModel.getStringProperty(BUSINESS_INTERFACE_ANNOTATION_LOCATION);
		return (prop.equals(BusinessInterfaceAnnotationLocationType.INTERFACE_ONLY.toString()) ||
				prop.equals(BusinessInterfaceAnnotationLocationType.BEAN_CLASS_AND_INTERFACE.toString()));	
	}
}
