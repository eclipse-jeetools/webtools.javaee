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
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelListener;

/**
 * @author naci
 *
 */
public  abstract class EnterpriseBeanDelegate implements IEnterpriseBean, WTPOperationDataModelListener {

	private final static String DEFAULT_DATA_MODEL="EnterpriseBeanDelegate.DATA_MODEL";
	
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
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModel()
	 */
	public EnterpriseBeanClassDataModel getDataModel() {
		return (EnterpriseBeanClassDataModel)dataModels.get(DEFAULT_DATA_MODEL);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModel()
	 */
	public void setDataModel(EnterpriseBeanClassDataModel dataModel) {
		if(this.getDataModel() != null)
			this.getDataModel().removeListener(this);
		dataModel.addListener(this);
		dataModels.put(DEFAULT_DATA_MODEL, dataModel);
		enterpriseBean.setName((String)dataModel.getEjbName());
		enterpriseBean.setDescription((String)dataModel.getDescription());
		enterpriseBean.setDisplayName((String)dataModel.getDisplayName());
		enterpriseBean.setEjbClassName(dataModel.getQualifiedClassName());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean#getDataModelFor(java.lang.String)
	 */
	public WTPOperationDataModel getDataModelFor(String feature) {
		return (WTPOperationDataModel)dataModels.get(feature);
	}

	public void setEnterpriseBean(EnterpriseBean enterpriseBean) {
		this.enterpriseBean = enterpriseBean;
	}


	public String getJndiName() {
		return this.getDataModel().getJndiName();
	}
	
	public String getEjbName() {
		return this.getDataModel().getEjbName();
	}

	public String getInterfaces() {
		return this.getDataModel().getInterfaces();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getSimpleClassName()
	 */
	public String getSimpleClassName() {
		return this.getDataModel().getSimpleClassName();
	}


	public String getDisplayName() {
		return this.getDataModel().getDisplayName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public String getDescription() {
		return this.getDataModel().getDescription();
	}

	public abstract String getTransactionType() ;

	/**
	 * 
	 * This method permits us to keep emf model for the bean
	 * in sync with the  changes in the datamodel
	 */
	
	public void propertyChanged(WTPOperationDataModelEvent event) {
		String property = event.getPropertyName();
		Object propertyValue = event.getProperty();
		if( enterpriseBean == null)
			return;
		
		if( EnterpriseBeanClassDataModel.EJB_NAME.equals(property)){
			enterpriseBean.setName((String)propertyValue);
		}else if(EnterpriseBeanClassDataModel.DESCRIPTION.equals(property)){
			enterpriseBean.setDescription((String)propertyValue);
		}else if(EnterpriseBeanClassDataModel.DISPLAY_NAME.equals(property)){
			enterpriseBean.setDisplayName((String)propertyValue);
		} else if(EnterpriseBeanClassDataModel.CLASS_NAME.equals(property)){
			enterpriseBean.setEjbClassName(((EnterpriseBeanClassDataModel)event.getDataModel()).getQualifiedClassName());
		} 
	}

	
}
