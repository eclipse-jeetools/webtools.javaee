/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 18, 2004
 *
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 *  
 */
public class AddWebLibraryProjectDataModel extends EditModelOperationDataModel {
	public static final String JAR_NAME = "AddWebLibraryProjectdataModel.JAR_NAME"; //$NON-NLS-1$
	public static final String JAVA_PROJECT_NAME = "AddWebLibraryProjectdataModel.JAVA_PROJECT_NAME"; //$NON-NLS-1$
	public static final String WEB_LIB_MODULE_REPO = "AddWebLibraryProjectdataModel.WEB_LIB_MODULE_REPO"; //$NON-NLS-1$
	public static final String WEB_LIB_MODULE_LIST = "AddWebLibraryProjectdataModel.WEB_LIB_MODULE_LIST"; //$NON-NLS-1$

	public class WebLibModuleRepository {
		private List webLibModules;
		private boolean modified = false;

		public boolean isModified() {
			return this.modified;
		}

		protected void setModified(boolean modified) {
			this.modified = modified;
		}

		public List getWebLibModules() {
			if (this.webLibModules == null) {
				this.webLibModules = new ArrayList();
				IProject project = getTargetProject();
				J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
				ILibModule[] modules = webNature.getLibModules();
				if (modules != null && modules.length > 0)
					this.webLibModules.addAll(Arrays.asList(modules));
			}
			return this.webLibModules;
		}

		public void addModule(ILibModule module) {
			getWebLibModules().add(module);
			setModified(true);
		}

		public void removeModule(ILibModule module) {
			getWebLibModules().remove(module);
			setModified(true);
		}

		protected void save() {
			// save with editmodel/web nature

		}
	}

	public class WebLibModuleResource extends ResourceImpl {

		private WebLibModuleRepository repository;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#isModified()
		 */
		public boolean isModified() {
			// return true if the meta object has been saved
			return (this.repository != null && this.repository.isModified());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doSave(java.io.OutputStream,
		 *      java.util.Map)
		 */
		protected void doSave(OutputStream outputStream, Map options) throws IOException {
			// TODO save the meta object here
			super.doSave(outputStream, options);
		}

		public WebLibModuleRepository getRepository() {
			if (this.repository == null)
				this.repository = new WebLibModuleRepository();
			return this.repository;
		}

		protected void setRepository(WebLibModuleRepository repository) {
			this.repository = repository;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(JAR_NAME);
		addValidBaseProperty(JAVA_PROJECT_NAME);
		addValidBaseProperty(WEB_LIB_MODULE_REPO);
		addValidBaseProperty(WEB_LIB_MODULE_LIST);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddWebLibraryProjectOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(JAR_NAME)) {
			return validateJarName(getStringProperty(propertyName));
		}
		if (propertyName.equals(JAVA_PROJECT_NAME)) {
			return validateJavaProjectName(getStringProperty(propertyName));
		}
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateJarName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_JAR_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		ILibModule[] libModules = getLibModules();
		boolean exists = false;
		for (int i = 0; i < libModules.length; i++) {
			if (name.equals(libModules[i].getJarName())) {
				exists = true;
				break;
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_JAR_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateJavaProjectName(String name) {
		if (name == null || name.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_JAVA_PROJECT_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		ILibModule[] libModules = getLibModules();
		boolean exists = false;
		for (int i = 0; i < libModules.length; i++) {
			if (name.equals(libModules[i].getProjectName())) {
				exists = true;
				break;
			}
		}
		if (exists) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_JAVA_PROJECT_NAME_EXIST, new String[]{name});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	public ILibModule[] getLibModules() {
		IProject project = getTargetProject();
		J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
		return webNature.getLibModules();
	}
}