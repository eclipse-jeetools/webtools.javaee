/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class EnterpriseBeanClassDataModel extends NewJavaClassDataModel implements IAnnotationsDataModel {

	public static final String EJB_TYPE = "IEjbCommonDataModel.EJB_TYPE"; //$NON-NLS-1$

	public static final String EJB_NAME = "IEjbCommonDataModel.EJB_NAME"; //$NON-NLS-1$

	public static final String JNDI_NAME = "IEjbCommonDataModel.JNDI_NAME"; //$NON-NLS-1$

	public static final String DISPLAY_NAME = "IEjbCommonDataModel.DISPLAY_NAME"; //$NON-NLS-1$

	public static final String DESCRIPTION = "IEjbCommonDataModel.DESCRIPTION"; //$NON-NLS-1$

	public static final String TRANSACTIONTYPE = "IEjbCommonDataModel.TRANSACTIONTYPE";//$NON-NLS-1$

	public static final String ANNOTATIONPROVIDER = "IEjbCommonDataModel.ANNOTATIONPROVIDER";//$NON-NLS-1$

	public static final String MODELDELEGATE = "IEjbCommonDataModel.MODELDELEGATE";//$NON-NLS-1$
	
	
	
	
	
	
	public EnterpriseBeanClassDataModel() {
		super();
		initializeDelegate();
	}

	protected abstract void initializeDelegate();
	
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
		addValidBaseProperty(EJB_TYPE);
		addValidBaseProperty(EJB_NAME);
		addValidBaseProperty(DISPLAY_NAME);
		addValidBaseProperty(JNDI_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(CLASS_NAME);
		addValidBaseProperty(TRANSACTIONTYPE);
		addValidBaseProperty(ANNOTATIONPROVIDER);
		addValidBaseProperty(MODELDELEGATE);
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
			ComponentHandle handle = ComponentHandle.create(getTargetProject(),getComponent().getName());
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(handle);
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

		boolean result = super.doSetProperty(propertyName, propertyValue);
		// After the property is set, if project changed, update the nature and the annotations enablement
		if (propertyName.equals(MODULE_NAME)) {
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CLASS_NAME)) {
			if (!isSet(EJB_NAME))
				notifyDefaultChange(EJB_NAME);
		} else if (propertyName.equals(EJB_NAME)) {
			if (!isSet(JNDI_NAME))
				notifyDefaultChange(JNDI_NAME);
			else if (!isSet(DISPLAY_NAME))
				notifyDefaultChange(DISPLAY_NAME);
			if (!isSet(DESCRIPTION))
				notifyDefaultChange(DESCRIPTION);
		}
		
		
		return result;
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.TRUE;
		else if (propertyName.equals(CLASS_NAME))
			return "MyBean";
		else if (propertyName.equals(EJB_NAME) ) {
			String className = getStringProperty(CLASS_NAME);
			if (className.endsWith("Bean"))
				className = className.substring(0,className.length()-4);
			return className;
		} else if (propertyName.equals(JNDI_NAME)) {
			return getProperty(EJB_NAME);
		} else if (propertyName.equals(EJB_TYPE)) {
			return "SessionBean";
		} else if (propertyName.equals(DISPLAY_NAME)) {
			return getProperty(EJB_NAME);
		} else if (propertyName.equals(DESCRIPTION)) {
			return "A session bean named "+getStringProperty(EJB_NAME);
		} else if(propertyName.equals(INTERFACES))
			return getEJBInterfaces();
		else if (propertyName.equals(ANNOTATIONPROVIDER))
		{
			String[] providers = AnnotationUtilities.getProviderNames();
			if(providers!= null && providers.length > 0)
				return providers[0];
		}else if( propertyName.equals(SOURCE_FOLDER)){
			try {
				Object srcFolder = super.getDefaultProperty(propertyName);
				return srcFolder;
			} catch (Exception e) {
			}
			return "";
		}

		return super.getDefaultProperty(propertyName);
	}
	
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(JAVA_PACKAGE))
			return validateEjbJavaPackage(getStringProperty(propertyName));
		if (propertyName.equals(EJB_NAME))
			return validateEJBName(getStringProperty(propertyName));
		if (propertyName.equals(EJB_TYPE))
			return validateEJBType(getStringProperty(propertyName));
		if (propertyName.equals(JNDI_NAME))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(DISPLAY_NAME))
			return validateDisplayName(getStringProperty(propertyName));
		if (propertyName.equals(CLASS_NAME))
			return validateClassName(getStringProperty(propertyName));
		if (propertyName.equals(TRANSACTIONTYPE))
			return validateTransaction(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}
	protected IStatus validateClassName(String className) {
		IStatus status =  super.validateJavaClassName(className);
		if( status != WTPCommonPlugin.OK_STATUS)
			return status;
		
		if( className.equals("Bean") || className.equals("EJB") ){
			
		}else if( (className.endsWith("Bean") || className.endsWith("EJB"))  )
			return status;
		String msg = IEJBAnnotationConstants.ERR_CLASS_NAME_MUSTEND_WITH_BEAN ;
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
			String msg = IEJBAnnotationConstants.ERR_MUST_ENTER_A_PACKAGE_NAME ;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
		
	}

	private IStatus validateEJBType(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_EJB_TYPE_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("SessionBean") >= 0 || prop.indexOf("MessageDrivenBean") >= 0|| prop.indexOf("EntityBean") >= 0) {
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_EJB_TYPE_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}



	private IStatus validateTransaction(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_TRANSACTION_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Container") >= 0 || prop.indexOf("Bean") >= 0) {
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_TRANSACTION_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}

	protected IStatus validateJndiName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_JNDI_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf(" ") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_JNDI_NAME_VALUE;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateEJBName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_EJB_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Bean") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_EJB_NAME_ENDS_WITH_BEAN;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateDisplayName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_DISPLAY_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (getTargetProject()==null )//|| getComponent()==null)
			return WTPCommonPlugin.OK_STATUS;
		ArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			EJBJar ejbJar = (EJBJar) edit.getContentModelRoot();
			if (ejbJar == null)
				return WTPCommonPlugin.OK_STATUS;
			List ejbs = ejbJar.getEnterpriseBeans();
			if (ejbs != null && ejbs.size() > 0) {
					for (int i = 0; i < ejbs.size(); i++) {
						EnterpriseBean ejb = (EnterpriseBean) ejbs.get(i);
						if (prop.equals(ejb.getDisplayName())) {
							String msg = IEJBAnnotationConstants.ERR_EJB_DISPLAY_NAME_USED;
							return WTPCommonPlugin.createErrorStatus(msg);
						}
					}
			}
		} finally {
			if (edit!=null)
				edit.dispose();
		}

		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateJavaClassName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(
					J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY,
					new String[] { prop });
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (getTargetProject()==null )//|| getComponent()==null)
			return WTPCommonPlugin.OK_STATUS;
		ArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			EJBJar ejbJar = (EJBJar) edit.getContentModelRoot();
			if (ejbJar == null)
				return WTPCommonPlugin.OK_STATUS;
			List ejbs = ejbJar.getEnterpriseBeans();
			if (ejbs != null && ejbs.size() > 0) {
					for (int i = 0; i < ejbs.size(); i++) {
						EnterpriseBean ejb = (EnterpriseBean) ejbs.get(i);
						if (prop.equals(ejb.getEjbClass().getQualifiedName())) {
							String msg = IEJBAnnotationConstants.ERR_EJB_CLASS_NAME_USED;
							return WTPCommonPlugin.createErrorStatus(msg);
						}
					}
			}
		} finally {
			if (edit!=null)
				edit.dispose();
		}

		return WTPCommonPlugin.OK_STATUS;
	}

	
	public String getJndiName() {
		return this.getStringProperty(MessageDrivenBeanDataModel.JNDI_NAME);
	}
	
	public String getEjbName() {
		return this.getStringProperty(MessageDrivenBeanDataModel.EJB_NAME);
	}

	public String getInterfaces() {
		List ints = (List)this.getProperty(INTERFACES);
		Iterator iterator =  ints.iterator();
		String intStr = (iterator.hasNext()? (String)iterator.next() : getDefaultInterfaces());
		while (iterator.hasNext()) {
			String intrfc = (String) iterator.next();
			intStr += ", " + intrfc ;
		}
		
		return intStr;
	}

	private String getDefaultInterfaces() {
		Iterator interfaces = getEJBInterfaces().iterator();
		String interfacesStr = (interfaces.hasNext() ? (String)interfaces.next(): "");
		while (interfaces.hasNext()) {
			interfacesStr = interfacesStr +", " +(String) interfaces.next();
			
		}
		return interfacesStr;
	}

	protected abstract List getEJBInterfaces() ;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getSimpleClassName()
	 */
	public String getSimpleClassName() {
		return this.getStringProperty(CLASS_NAME);
	}


	public String getDisplayName() {
		return this.getStringProperty(MessageDrivenBeanDataModel.DISPLAY_NAME);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public String getDescription() {
		return this.getStringProperty(MessageDrivenBeanDataModel.DESCRIPTION);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public IEnterpriseBean getDelegate() {
		return (IEnterpriseBean)this.getProperty(MessageDrivenBeanDataModel.MODELDELEGATE);
	}


}