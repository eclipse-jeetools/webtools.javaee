/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;


public class SessionBeanDataModel extends EjbCommonDataModel implements IAnnotationsDataModel {
	public final static String EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] EJB_INTERFACES = {"javax.ejb.SessionBean" //$NON-NLS-1$
	};

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddSessionBeanOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		return super.doValidateProperty(propertyName);
	}


	public String getEjbSuperclassName() {
		return EJB_SUPERCLASS;
	}

	public List getEJBInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < EJB_INTERFACES.length; i++) {
				this.interfaceList.add(EJB_INTERFACES[i]);
			}
		}
		return this.interfaceList;
	}


	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (this.j2eeNature.getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3) {
				if (getBooleanProperty(USE_ANNOTATIONS))
					setBooleanProperty(USE_ANNOTATIONS, false);
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(USE_ANNOTATIONS)) {
			if (((Boolean) propertyValue).booleanValue() && this.j2eeNature.getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return true;
			notifyEnablementChange(USE_ANNOTATIONS);
		}
		return super.doSetProperty(propertyName, propertyValue);
	}
}