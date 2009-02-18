/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebFiltersGroupItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.FilterMappingItem;
import org.eclipse.jst.j2ee.internal.web.operations.IFilterMappingItem;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFiltersItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

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
		if (isServlet(obj)) {
			// set the class name
			classText.setText(getServletSimpleClassName(obj) + "Filter");
			// set the filter mapping
			String servletName = getServletName(obj);
			if (servletName != null) {
				List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
				mappings.add(new FilterMappingItem(IFilterMappingItem.SERVLET_NAME, servletName));
				model.setProperty(FILTER_MAPPINGS, mappings);
			}
		} else if (isWebFolder(obj)) {
			IFolder folder = (IFolder) obj;
			// set the class name
			String webFolderName = makeFirstCharUppercase(folder.getName());
			classText.setText(webFolderName + "Filter");
			// set the filter mapping
			String webFolderPath = getWebResourcePath(folder);
			List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
			mappings.add(new FilterMappingItem(IFilterMappingItem.URL_PATTERN, "/" + webFolderPath + "/*"));
			model.setProperty(FILTER_MAPPINGS, mappings);
		} else if (isWebResource(obj)) {
			IFile file = (IFile) obj;
			// set the class name
			String webResourceName = makeFirstCharUppercase(getFileNameWithouFileExtension(file));
			classText.setText(webResourceName + "Filter");
			// set the filter mapping
			String webResourcePath = getWebResourcePath(file);
			List<IFilterMappingItem> mappings = new ArrayList<IFilterMappingItem>();
			mappings.add(new FilterMappingItem(IFilterMappingItem.URL_PATTERN, "/" + webResourcePath));
			model.setProperty(FILTER_MAPPINGS, mappings);
		}
		
		return composite;
	}

	private Object getSelectedObject() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			return null;
		ISelection selection = window.getSelectionService().getSelection();
		if (selection == null)
			return null;
		if (!(selection instanceof IStructuredSelection)) 
			return null;
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() != 1)
			return null;
		return ssel.getFirstElement();
	}
	
	private IType getPrimaryType(ICompilationUnit cu) {
		return cu.getType(new Path(cu.getElementName()).removeFileExtension().toString());
	}
	
	private boolean isServlet(Object selectedObject) {
		if (selectedObject instanceof org.eclipse.jst.javaee.web.Servlet) 
			return true;
		
		if (selectedObject instanceof org.eclipse.jst.j2ee.webapplication.Servlet)
			return true;
		
		IJavaElement jelem = getJavaElement(selectedObject);
		
		try {
			if (jelem != null) {
				if (jelem instanceof ICompilationUnit) {
					ICompilationUnit cu = (ICompilationUnit) jelem;
					jelem = getPrimaryType(cu);
				}
				
				if (jelem instanceof IType && ((IType) jelem).isClass()) {
					IType type = (IType) jelem;
					ITypeHierarchy typeHierarchy = type.newTypeHierarchy(null);
					for (IType superType : typeHierarchy.getAllSuperInterfaces(type)) {
						if (IServletConstants.QUALIFIED_SERVLET.equals(superType.getFullyQualifiedName()))
							return true;
					}
				} 
			}
		} catch (JavaModelException e) {
			ServletUIPlugin.log(e);
		}
		
		return false;
	}
	
	private String getServletClass(Object obj) {
		if (obj instanceof org.eclipse.jst.javaee.web.Servlet) {
			org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) obj;
			return servlet.getServletClass();
		} 

		if (obj instanceof org.eclipse.jst.j2ee.webapplication.Servlet) {
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = (org.eclipse.jst.j2ee.webapplication.Servlet) obj;
			return servlet.getServletClass().getQualifiedName();
		}
		
		IJavaElement jelem = getJavaElement(obj); // should not be null, because isServletSelected() is true
		if (jelem instanceof ICompilationUnit) {
			ICompilationUnit cu = (ICompilationUnit) jelem;
			jelem = getPrimaryType(cu);
		}
		return ((IType) jelem).getFullyQualifiedName();
	}
	
	private String getServletSimpleClassName(Object obj) {
		return Signature.getSimpleName(getServletClass(obj));
	}
	
	private String getServletName(Object obj) {
		if (obj instanceof org.eclipse.jst.javaee.web.Servlet) {
			org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) obj;
			return servlet.getServletName();
		} 

		if (obj instanceof org.eclipse.jst.j2ee.webapplication.Servlet) {
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = (org.eclipse.jst.j2ee.webapplication.Servlet) obj;
			return servlet.getServletName();
		}
		
		String servletClass = getServletClass(obj);
		IProject project = getJavaElement(obj).getJavaProject().getProject();
		IModelProvider provider = ModelProviderManager.getModelProvider(project);
		Object modelObject = provider.getModelObject();
		if (modelObject instanceof org.eclipse.jst.javaee.web.WebApp) {
			org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) modelObject;
			Iterator servlets = webApp.getServlets().iterator();
			while (servlets.hasNext()) {
				org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) servlets.next();
				String qualified = servlet.getServletClass(); 
				if (qualified.equals(servletClass)) {
					return servlet.getServletName();
				}
			}
		} else if (modelObject instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) modelObject;
			Iterator servlets = webApp.getServlets().iterator();
			while (servlets.hasNext()) {
				org.eclipse.jst.j2ee.webapplication.Servlet servlet = (org.eclipse.jst.j2ee.webapplication.Servlet) servlets.next();
				String qualified = servlet.getServletClass().getQualifiedName(); 
				if (qualified.equals(servletClass)) {
					return servlet.getServletName();
				}
			}
		}
		
		return null;
	}
	
	private boolean isWebFolder(Object obj) {
		if (obj instanceof IFolder) {
			return isWebResource(obj);
		}
		return false;
	}

	private boolean isWebResource(Object obj) {
		if (obj instanceof IResource) {
			IResource resource = (IResource) obj;
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
			if (comp != null) {
				IPath rootPath = comp.getRootFolder().getWorkspaceRelativePath();
				IPath webInfPath = rootPath.append(J2EEConstants.WEB_INF);
				IPath metaInfPath = rootPath.append(J2EEConstants.META_INF);
				IPath resourcePath = resource.getFullPath();
				return rootPath.isPrefixOf(resourcePath) && 
						!rootPath.equals(resourcePath) &&
						!webInfPath.isPrefixOf(resourcePath) && 
						!metaInfPath.isPrefixOf(resourcePath);
			}
		}
		return false;
	}

	private String getWebResourcePath(IResource resource) {
		IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
		if (comp != null) {
			IPath rootPath = comp.getRootFolder().getWorkspaceRelativePath();
			return resource.getFullPath().makeRelativeTo(rootPath).toString();
		}
		return null;
	}
	
	public String makeFirstCharUppercase(String str) {
		if (str == null || str.length() == 0) 
			return str;
		
		StringBuilder builder = new StringBuilder(str);
		builder.setCharAt(0, Character.toUpperCase(builder.charAt(0)));
		return builder.toString();
	}
	
	public String getFileNameWithouFileExtension(IFile file) {
		String name = file.getName();
		String ext = file.getFileExtension();
		
		if (ext == null) 
			return name;
		
		return name.substring(0, name.length() - (ext.length() + 1));
	}
	
}
