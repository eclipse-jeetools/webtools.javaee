/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.FILTER_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.CHOOSE_FILTER_CLASS;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NEW_FILTER_WIZARD_WINDOW_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.USE_EXISTING_FILTER_CLASS;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebFiltersGroupItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.FilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.operations.IFilterMappingItem;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFiltersItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class NewFilterClassWizardPage extends NewWebClassWizardPage {
	
    private final static String[] FILTEREXTENSIONS = { "java" }; //$NON-NLS-1$
    
	public NewFilterClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected String getUseExistingCheckboxText() {
		return USE_EXISTING_FILTER_CLASS;
	}
	
	@Override
	protected String getUseExistingProperty() {
		return USE_EXISTING_CLASS;
	}
	
	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof WebFiltersGroupItemProvider) {
			WebApp webApp = (WebApp)((WebFiltersGroupItemProvider) selection).getParent();
			return ProjectUtilities.getProject(webApp);
		}else if(selection instanceof WebAppProvider){
			return ((WebAppProvider) selection).getProject();
		} if(selection instanceof GroupFiltersItemProvider){
			org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) ((GroupFiltersItemProvider) selection).getJavaEEObject();
			return ProjectUtilities.getProject(webApp);
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
	@Override
	protected void handleClassButtonSelected() {
        getControl().setCursor(new Cursor(getShell().getDisplay(), SWT.CURSOR_WAIT));
        IProject project = (IProject) model.getProperty(PROJECT);
        IVirtualComponent component = ComponentCore.createComponent(project);
        MultiSelectFilteredFilterFileSelectionDialog ms = new MultiSelectFilteredFilterFileSelectionDialog(
                getShell(),
                NEW_FILTER_WIZARD_WINDOW_TITLE,
                CHOOSE_FILTER_CLASS, 
                FILTEREXTENSIONS, 
                false, 
                project);
        IContainer root = component.getRootFolder().getUnderlyingFolder();
        ms.setInput(root);
        ms.open();
        if (ms.getReturnCode() == Window.OK) {
            String qualifiedClassName = ""; //$NON-NLS-1$
            IType type = (IType) ms.getFirstResult();
            if (type != null) {
                qualifiedClassName = type.getFullyQualifiedName();
            }
            existingClassText.setText(qualifiedClassName);
        }
        getControl().setCursor(null);
    }

	/* (non-Javadoc)
	 * @see org.eclipse.jst.servlet.ui.internal.wizard.NewWebClassWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		Object obj = getSelectedObject();
		if (isFilter(obj)) {
			checkExistingButton(true);
			existingClassText.setText(getFilterClass(obj));
		} else if (isServlet(obj)) {
			String servletName = getServletName(obj);
			// set the class name
			classText.setText(makeFirstCharUppercase(servletName) + "Filter"); //$NON-NLS-1$
			// set the filter mapping
			if (servletName != null) {
				List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
				mappings.add(new FilterMappingItem(IFilterMappingItem.SERVLET_NAME, servletName));
				model.setProperty(FILTER_MAPPINGS, mappings);
			}
		} else if (isWebFolder(obj)) {
			IFolder folder = (IFolder) obj;
			// set the class name
			String webFolderName = makeFirstCharUppercase(folder.getName());
			classText.setText(webFolderName + "Filter"); //$NON-NLS-1$
			// set the filter mapping
			String webFolderPath = getWebResourcePath(folder);
			List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
			mappings.add(new FilterMappingItem(IFilterMappingItem.URL_PATTERN, webFolderPath + "/*")); //$NON-NLS-1$
			model.setProperty(FILTER_MAPPINGS, mappings);
		} else if (WebUtilities.isWebResource(obj)) {
			IFile file = (IFile) obj;
			// set the class name
			String webResourceName = makeFirstCharUppercase(getFileNameWithouFileExtension(file));
			classText.setText(webResourceName + "Filter"); //$NON-NLS-1$
			// set the filter mapping
			String webResourcePath = getWebResourcePath(file);
			List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
			mappings.add(new FilterMappingItem(IFilterMappingItem.URL_PATTERN, webResourcePath));
			model.setProperty(FILTER_MAPPINGS, mappings);
		}
		
		return composite;
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		if (!result) 
			return false;

		if (JavaEEProjectUtilities.isWebFragmentProject(project))
			return true;
		
		// get the version of the web facet
		IProjectFacetVersion facetVersion = JavaEEProjectUtilities.getProjectFacetVersion(project, IJ2EEFacetConstants.DYNAMIC_WEB);
		if (facetVersion == null) 
			return false;
		
		// convert the version to an integer
		int version = J2EEVersionUtil.convertVersionStringToInt(facetVersion.getVersionString());
		
		// only web 2.3 and greater projects are valid
		return  version > J2EEVersionConstants.SERVLET_2_2;
	}
	
}
