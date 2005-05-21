/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.EJBAnnotationMessages;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewEJBJavaClassDataModel extends NewJavaClassDataModel implements IAnnotationsDataModel {
	
	/**
	 * Subclasses may extend this method to add their own specific data model properties as valid
	 * base properties.  This implementation adds the ejb specific properties to those added
	 * by the NewJavaClassDataModel.
	 * @see NewJavaClassDataModel#initValidBaseProperties()
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		// Add ejb specific properties defined in this data model
		addValidBaseProperty(USE_ANNOTATIONS);
	}
	
	/**
	 * Subclasses may extend this method to provide their own determination of whether or not
	 * certain properties should be disabled or enabled.  This method does not accept null parameter.
	 * It will not return null.  This implementation makes sure annotation support is only allowed
	 * on web projects of J2EE version 1.3 or higher.
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel#basicIsEnabled(String)
	 * @see IAnnotationsDataModel#USE_ANNOTATIONS
	 * 
	 * @param propertyName
	 * @return Boolean should property be enabled?
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		// Annotations should only be enabled on a valid j2ee project of version 1.3 or higher
		if (USE_ANNOTATIONS.equals(propertyName)) {
			return Boolean.FALSE;
//			if (!isAnnotationsSupported())
//				return Boolean.FALSE;
//			return Boolean.TRUE;
		}
		// Otherwise return super implementation
		return super.basicIsEnabled(propertyName);
	}
	
	protected boolean isAnnotationsSupported() {
		if (getTargetProject()==null || getComponent()==null) return true;
		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(getComponent());
			if (ejbEdit == null)
				return false;
			return ejbEdit.getJ2EEVersion() > J2EEVersionConstants.VERSION_1_2;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		boolean result = super.doSetProperty(propertyName, propertyValue);
		// After the property is set, if project changed, update the nature and the annotations enablement
		if (propertyName.equals(MODULE_NAME)) {
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CLASS_NAME)) {
			EjbCommonDataModel parentModel = (EjbCommonDataModel) getNestingModels().next();
			if (!parentModel.isSet(EjbCommonDataModel.EJB_NAME))
				parentModel.notifyDefaultChange(EjbCommonDataModel.EJB_NAME);
		}
		return result;
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.TRUE;
		else if (propertyName.equals(CLASS_NAME))
			return "Bean";
		return super.getDefaultProperty(propertyName);
	}
	
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(JAVA_PACKAGE))
			return validateEjbJavaPackage(getStringProperty(propertyName));

		return super.doValidateProperty(propertyName);
	}
	protected IStatus validateJavaClassName(String className) {
		IStatus status =  super.validateJavaClassName(className);
		if( status != WTPCommonPlugin.OK_STATUS)
			return status;
		
		if( className.equals("Bean") || className.equals("EJB") ){
			
		}else if( (className.endsWith("Bean") || className.endsWith("EJB"))  )
			return status;
		String msg = EJBAnnotationMessages.getResourceString(IEJBAnnotationConstants.ERR_CLASS_NAME_MUSTEND_WITH_BEAN) ;
		return WTPCommonPlugin.createErrorStatus(msg);
		
	}
	protected IStatus validateEjbJavaPackage(String packageName) {
		if (packageName != null && packageName.trim().length() > 0) {
			// Use standard java conventions to validate the package name
			IStatus javaStatus = JavaConventions.validatePackageName(packageName);
			if (javaStatus.getSeverity() == IStatus.ERROR) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_PACAKGE_NAME_INVALID) + javaStatus.getMessage();
				return WTPCommonPlugin.createErrorStatus(msg);
			} else if (javaStatus.getSeverity() == IStatus.WARNING) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_PACKAGE_NAME_WARNING) + javaStatus.getMessage();
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		if( packageName == null || packageName.trim().length() == 0  ){
			String msg = EJBAnnotationMessages.getResourceString(IEJBAnnotationConstants.ERR_MUST_ENTER_A_PACKAGE_NAME) ;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
		
	}

}