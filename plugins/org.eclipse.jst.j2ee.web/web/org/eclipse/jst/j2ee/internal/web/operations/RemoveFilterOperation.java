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
 * Created on Jun 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.impl.WebapplicationFactoryImpl;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class RemoveFilterOperation extends ModelModifierOperation {
	public RemoveFilterOperation(RemoveFilterDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		RemoveFilterDataModel model = (RemoveFilterDataModel) this.operationDataModel;
		boolean removeClass = model.getBooleanProperty(RemoveFilterDataModel.REMOVE_JAVA_CLASS);
		IProject project = model.getTargetProject();
		List FilterList = (List) model.getProperty(RemoveFilterDataModel.FILTER_LIST);
		int count = FilterList.size();
		for (int i = 0; i < count; i++) {
			Filter filter = (Filter) FilterList.get(i);
			WebApp webApp = (WebApp) filter.eContainer();
			// remove Filter
			ModifierHelper helper = createRemoveFilterHelper(webApp, filter);
			this.modifier.addHelper(helper);
			// collect the filter mappings to remove
			List filterMappings = webApp.getFilterMappings();
			for (int j = 0; j < filterMappings.size(); j++) {
				FilterMapping fm = (FilterMapping) filterMappings.get(j);
				if (fm.eIsSet(WebapplicationPackage.eINSTANCE.getFilterMapping_Filter()) && filter.equals(fm.getFilter())) {
					ModifierHelper helper1 = createRemoveFilterMappingHelper(webApp, fm);
					this.modifier.addHelper(helper1);
				}
			}
			if (removeClass) {
				try {
					JavaClass javaClass = filter.getFilterClass();
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

	private ModifierHelper createRemoveFilterHelper(WebApp webApp, Filter Filter) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Filters());
		helper.setValue(Filter);
		helper.doUnsetValue();
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