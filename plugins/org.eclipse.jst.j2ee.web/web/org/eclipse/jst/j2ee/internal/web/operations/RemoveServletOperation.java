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
 * Created on May 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.impl.WebapplicationFactoryImpl;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class RemoveServletOperation extends ModelModifierOperation {
	public RemoveServletOperation(RemoveServletDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		RemoveServletDataModel model = (RemoveServletDataModel) this.operationDataModel;
		boolean removeClass = model.getBooleanProperty(RemoveServletDataModel.REMOVE_JAVA_CLASS);
		IProject project = model.getTargetProject();
		J2EEWebNatureRuntime nature = (J2EEWebNatureRuntime) J2EEWebNatureRuntimeUtilities.getRuntime(project);
		boolean isServlet2_3OrGreater = nature.getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID;
		List servletList = (List) model.getProperty(RemoveServletDataModel.SERVLET_LIST);
		int count = servletList.size();
		for (int i = 0; i < count; i++) {
			Servlet servlet = (Servlet) servletList.get(i);
			WebApp webApp = (WebApp) servlet.eContainer();
			// remove servlet
			ModifierHelper helper = createRemoveServletHelper(webApp, servlet);
			this.modifier.addHelper(helper);
			// collect the servlet mappings to remove
			List servletMappings = webApp.getServletMappings();
			for (int j = 0; j < servletMappings.size(); j++) {
				ServletMapping sm = (ServletMapping) servletMappings.get(j);
				if (sm.eIsSet(WebapplicationPackage.eINSTANCE.getServletMapping_Servlet()) && servlet.equals(sm.getServlet())) {
					ModifierHelper helper1 = createRemoveServletMappingHelper(webApp, sm);
					this.modifier.addHelper(helper1);
				}
			}
			// collect the filter mappings to remove
			if (isServlet2_3OrGreater) {
				List filterMappings = webApp.getFilterMappings();
				for (int j = 0; j < filterMappings.size(); j++) {
					FilterMapping fm = (FilterMapping) filterMappings.get(j);
					if (fm.eIsSet(WebapplicationPackage.eINSTANCE.getFilterMapping_Servlet()) && servlet.equals(fm.getServlet())) {
						ModifierHelper helper1 = createRemoveFilterMappingHelper(webApp, fm);
						this.modifier.addHelper(helper1);
					}
				}
			}
			if (removeClass) {
				try {
					JavaClass javaClass = servlet.getServletClass();
					String qualifiedName = javaClass.getQualifiedName();
					qualifiedName = qualifiedName.replace('.', IPath.SEPARATOR) + ".java"; //$NON-NLS-1$
					List sourceFolders = ProjectUtilities.getSourceContainers(project);
					for (int j = 0; j < sourceFolders.size(); j++) {
						IFolder sourceFolder = (IFolder) sourceFolders.get(j);
						IFile file = sourceFolder.getFile(qualifiedName);
						if (file.exists()) {
							file.delete(true, null);
							break;
						}
					}
				} catch (CoreException ex) {
					Logger.getLogger().log(ex);
				}
			}
		}
	}

	private ModifierHelper createRemoveServletHelper(WebApp webApp, Servlet servlet) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Servlets());
		helper.setValue(servlet);
		helper.doUnsetValue();
		return helper;
	}

	private ModifierHelper createRemoveServletMappingHelper(WebApp webApp, ServletMapping servletMapping) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationFactoryImpl.getPackage().getWebApp_ServletMappings());
		helper.setValue(servletMapping);
		helper.doUnsetValue();
		this.modifier.addHelper(helper);
		return helper;
	}

	private ModifierHelper createRemoveFilterMappingHelper(WebApp webApp, FilterMapping filterMapping) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationFactoryImpl.getPackage().getWebApp_FilterMappings());
		helper.setValue(filterMapping);
		helper.doUnsetValue();
		this.modifier.addHelper(helper);
		return helper;
	}
}