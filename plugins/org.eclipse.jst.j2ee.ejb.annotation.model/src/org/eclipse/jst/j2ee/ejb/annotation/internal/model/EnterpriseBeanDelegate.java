/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;

/**
 * @author naci
 *
 */
public  abstract class EnterpriseBeanDelegate implements IEnterpriseBean, IDataModelListener {

	private final static String DEFAULT_DATA_MODEL="EnterpriseBeanDelegate.DATA_MODEL"; //$NON-NLS-1$
	
	private Map dataModels;
	private EnterpriseBean enterpriseBean;
	
	public EnterpriseBeanDelegate()
	{
		dataModels = new HashMap();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getEnterpriseBean()
	 */
	public EnterpriseBean getEnterpriseBean() {
		return enterpriseBean;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getVersionID()
	 */
	public int getVersionID() {
		return enterpriseBean.getVersionID();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModel()
	 */
	public IDataModel getDataModel() {
		return (IDataModel)dataModels.get(DEFAULT_DATA_MODEL);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModel()
	 */
	public void setDataModel(IDataModel dataModel) {
		if(this.getDataModel() != null)
			this.getDataModel().removeListener(this);
		dataModel.addListener(this);
		dataModels.put(DEFAULT_DATA_MODEL, dataModel);
		enterpriseBean.setName(dataModel.getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_NAME));
		enterpriseBean.setDescription(dataModel.getStringProperty(IEnterpriseBeanClassDataModelProperties.DESCRIPTION));
		enterpriseBean.setDisplayName(dataModel.getStringProperty(IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME));
		enterpriseBean.setEjbClassName(dataModel.getStringProperty(INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModelFor(java.lang.String)
	 */
	public IDataModel getDataModelFor(String feature) {
		return (IDataModel)dataModels.get(feature);
	}

	public void setEnterpriseBean(EnterpriseBean enterpriseBean) {
		this.enterpriseBean = enterpriseBean;
	}


	public String getJndiName() {
		return getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.JNDI_NAME);
	}
	
	public String getEjbName() {
		return getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_NAME);
	}

	public String getInterfaces() {
		return getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.INTERFACES_AS_STRING);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getSimpleClassName()
	 */
	public String getSimpleClassName() {
		return getDataModel().getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME);
	}


	public String getDisplayName() {
		return getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public String getDescription() {
		return getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.DESCRIPTION);
	}

	public abstract String getTransactionType() ;

	/**
	 * 
	 * This method permits us to keep emf model for the bean
	 * in sync with the  changes in the datamodel
	 */
	
	public void propertyChanged(DataModelEvent event) {
		String property = event.getPropertyName();
		Object propertyValue = event.getProperty();
		if( enterpriseBean == null)
			return;
		if( IEnterpriseBeanClassDataModelProperties.EJB_NAME.equals(property)){
			enterpriseBean.setName((String)propertyValue);
		}else if(IEnterpriseBeanClassDataModelProperties.DESCRIPTION.equals(property)){
			enterpriseBean.setDescription((String)propertyValue);
		}else if(IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME.equals(property)){
			enterpriseBean.setDisplayName((String)propertyValue);
		} else if(INewJavaClassDataModelProperties.CLASS_NAME.equals(property)){
			enterpriseBean.setEjbClassName(event.getDataModel().getStringProperty(INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME));
		} 
	}

	
}
