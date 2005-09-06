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
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class EnterpriseBeanClassDataModelProvider extends NewJavaClassDataModelProvider implements IEnterpriseBeanClassDataModelProperties {

	public EnterpriseBeanClassDataModelProvider() {
		super();
		// initializeDelegate();
	}

	protected abstract void initializeDelegate();

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base
	 * properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(EJB_TYPE);
		propertyNames.add(EJB_NAME);
		propertyNames.add(DISPLAY_NAME);
		propertyNames.add(JNDI_NAME);
		propertyNames.add(DESCRIPTION);
		propertyNames.add(CLASS_NAME);
		propertyNames.add(TRANSACTIONTYPE);
		propertyNames.add(ANNOTATIONPROVIDER);
		propertyNames.add(MODELDELEGATE);
		propertyNames.add(INTERFACES_AS_STRING);
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own determination of whether or not
	 * certain properties should be disabled or enabled. This method does not accept null parameter.
	 * It will not return null. This implementation makes sure annotation support is only allowed on
	 * web projects of J2EE version 1.3 or higher.
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel#basicIsEnabled(String)
	 * @see IAnnotationsDataModel#USE_ANNOTATIONS
	 * 
	 * @param propertyName
	 * @return Boolean should property be enabled?
	 */
	public boolean isPropertyEnabled(String propertyName) {
		// Annotations should only be enabled on a valid j2ee project of version 1.3 or higher
		if (USE_ANNOTATIONS.equals(propertyName)) {
			return true;
			// if (!isAnnotationsSupported())
			// return Boolean.FALSE;
			// return Boolean.TRUE;
		}
		// Otherwise return super implementation
		return super.isPropertyEnabled(propertyName);
	}


	protected boolean isAnnotationsSupported() {
		if (getTargetProject() == null || getTargetComponent() == null)
			return true;
		EJBArtifactEdit ejbEdit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(getTargetProject(), getTargetComponent().getName());
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

	public boolean propertySet(String propertyName, Object propertyValue) {

		boolean result = super.propertySet(propertyName, propertyValue);
		// After the property is set, if project changed, update the nature and the annotations
		// enablement
		if (propertyName.equals(COMPONENT_NAME)) {
			getDataModel().notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
		} else if (propertyName.equals(CLASS_NAME)) {
			if (!isPropertySet(EJB_NAME))
				getDataModel().notifyPropertyChange(EJB_NAME, IDataModel.DEFAULT_CHG);
		} else if (propertyName.equals(EJB_NAME)) {
			if (!isPropertySet(JNDI_NAME))
				getDataModel().notifyPropertyChange(JNDI_NAME, IDataModel.DEFAULT_CHG);
			else if (!isPropertySet(DISPLAY_NAME))
				getDataModel().notifyPropertyChange(DISPLAY_NAME, IDataModel.DEFAULT_CHG);
			if (!isPropertySet(DESCRIPTION))
				getDataModel().notifyPropertyChange(DESCRIPTION, IDataModel.DEFAULT_CHG);
		}


		return result;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.TRUE;
		else if (propertyName.equals(CLASS_NAME))
			return "MyBean";
		else if (propertyName.equals(EJB_NAME)) {
			String className = getStringProperty(CLASS_NAME);
			if (className.endsWith("Bean"))
				className = className.substring(0, className.length() - 4);
			return className;
		} else if (propertyName.equals(JNDI_NAME)) {
			return getProperty(EJB_NAME);
		} else if (propertyName.equals(TRANSACTIONTYPE)) {
			return TransactionType.CONTAINER_LITERAL.getName();
		} else if (propertyName.equals(EJB_TYPE)) {
			return "SessionBean";
		} else if (propertyName.equals(DISPLAY_NAME)) {
			return getProperty(EJB_NAME);
		} else if (propertyName.equals(DESCRIPTION)) {
			return "A session bean named " + getStringProperty(EJB_NAME);
		} else if (propertyName.equals(INTERFACES))
			return getEJBInterfaces();
		else if (propertyName.equals(ANNOTATIONPROVIDER)) {
			String[] providers = AnnotationUtilities.getProviderNames();
			if (providers != null && providers.length > 0)
				return providers[0];
		} else if (propertyName.equals(SOURCE_FOLDER)) {
			try {
				Object srcFolder = super.getDefaultProperty(propertyName);
				return srcFolder;
			} catch (Exception e) {// Ignore
			}
			return ""; //$NON-NLS-1$
		} else if (propertyName.equals(INTERFACES_AS_STRING))
			return getInterfacesString();
		else if (propertyName.equals(MODELDELEGATE)) {
			initializeDelegate();
			return getProperty(MODELDELEGATE);
		}
		return super.getDefaultProperty(propertyName);
	}

	public IStatus validate(String propertyName) {
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
		return super.validate(propertyName);
	}

	protected IStatus validateClassName(String className) {
		IStatus status = super.validateJavaClassName(className);
		if (status.isOK())
			status = canCreateTypeInClasspath(className);
		if (status != WTPCommonPlugin.OK_STATUS)
			return status;

		if (className.equals("Bean") || className.equals("EJB")) {

		} else if ((className.endsWith("Bean") || className.endsWith("EJB")))
			return status;
		String msg = IEJBAnnotationConstants.ERR_CLASS_NAME_MUSTEND_WITH_BEAN;
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
		if (packageName == null || packageName.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_MUST_ENTER_A_PACKAGE_NAME;
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
		if (prop.indexOf("SessionBean") >= 0 || prop.indexOf("MessageDrivenBean") >= 0 || prop.indexOf("EntityBean") >= 0) {
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
		if (getTargetProject() == null)// || getComponent()==null)
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
			if (edit != null)
				edit.dispose();
		}

		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateJavaClassName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY, new String[]{prop});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (getTargetProject() == null)// || getComponent()==null)
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
			if (edit != null)
				edit.dispose();
		}

		return WTPCommonPlugin.OK_STATUS;
	}

	protected String getInterfacesString() {
		List ints = (List) this.getProperty(INTERFACES);
		Iterator iterator = ints.iterator();
		String intStr = (iterator.hasNext() ? (String) iterator.next() : getDefaultInterfaces());
		while (iterator.hasNext()) {
			String intrfc = (String) iterator.next();
			intStr += ", " + intrfc;
		}

		return intStr;
	}

	private String getDefaultInterfaces() {
		Iterator interfaces = getEJBInterfaces().iterator();
		String interfacesStr = (interfaces.hasNext() ? (String) interfaces.next() : "");
		while (interfaces.hasNext()) {
			interfacesStr = interfacesStr + ", " + (String) interfaces.next();

		}
		return interfacesStr;
	}

	protected abstract List getEJBInterfaces();

}