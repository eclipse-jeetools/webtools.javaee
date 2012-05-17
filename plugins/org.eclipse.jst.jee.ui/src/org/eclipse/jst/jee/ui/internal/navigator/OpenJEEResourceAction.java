/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.EditorUtility;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenAction;
import org.eclipse.jst.j2ee.internal.actions.ComponentEditorInput;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.componentcore.ComponentArchiveOptions;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl;
import org.eclipse.jst.javaee.core.internal.impl.ResourceRefImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.ui.internal.navigator.appclient.GroupAppClientProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.BeanInterfaceNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.BeanNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.GroupEJBProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ra.RaGroupContentProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebArtifactNode;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/**
 * Open Action for Deployment descriptor tree nodes. 
 * 
 * @author Dimitar Giormov
 *
 */
public class OpenJEEResourceAction extends AbstractOpenAction {

	public static final String ID = "com.sap.ide.j2ee.ui.actions.OpenJEEResourceAction"; //$NON-NLS-1$
	public static final String JAVA_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.ejb.ui.java.EnterpriseBeanJavaEditor"; //$NON-NLS-1$
	public static final String BASE_JAVA_EDITOR_ID = "org.eclipse.jdt.ui.CompilationUnitEditor"; //$NON-NLS-1$

	protected static IEditorDescriptor javaEditorDescriptor;
	protected static IEditorDescriptor baseJavaEditorDescriptor;

	/**
	 * Create an instance of this class
	 */
	public OpenJEEResourceAction() {
		super("Open"); //$NON-NLS-1$
	}

	/**
	 * Returns the action ID.
	 */
	public String getID() {
		return ID;
	}

	public static IEditorDescriptor getJavaEditorDescriptor() {
		if (javaEditorDescriptor == null)
			javaEditorDescriptor = findEditorDescriptor(JAVA_EDITOR_ID);
		return javaEditorDescriptor;
	}

	public static IEditorDescriptor getBaseJavaEditorDescriptor() {
		if (baseJavaEditorDescriptor == null)
			baseJavaEditorDescriptor = findEditorDescriptor(BASE_JAVA_EDITOR_ID);
		return baseJavaEditorDescriptor;
	}

	protected void openAppropriateEditor(String c) {
		if(getStructuredSelection() instanceof TreeSelection){
			TreePath path = ((TreeSelection) getStructuredSelection()).getPaths()[0];
			IProject project = null;
			
			while (path != null) {
				Object segment = path.getLastSegment();
				if (segment instanceof IProject) {
					project = (IProject) segment;
					break;
				}
				path = path.getParentPath();
			}

			if (project != null) {
				try {
					IType findType = JavaCore.create(project).findType(c);
					if(findType == null){
						return;
					}
					openAppropriateEditor(findType.getResource());
				} catch (JavaModelException e) {
					JEEUIPlugin.logError("Error during open editor", e); //$NON-NLS-1$
				}
			}
		}
	}
	
	protected void openAppropriateEditor(IVirtualComponent c) {
		if (c == null) {
			return;
		}
		IWorkbenchPage page = null;
		IEditorPart editor = null;
		try {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage();
			editor = page.openEditor(new ComponentEditorInput(c),
					currentDescriptor.getId());
			if (editor instanceof ISetSelectionTarget)
				((ISetSelectionTarget) editor)
				.selectReveal(getStructuredSelection());
		} catch (Exception e) {
			MessageDialog
			.openError(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					J2EEUIMessages
					.getResourceString("Problems_Opening_Editor_ERROR_"), e.getMessage()); //$NON-NLS-1$ = "Problems Opening Editor"
		}
	}

	protected void openAppropriateEditor(IJavaElement element) {
		if (element == null) {
			return;
		}
		try {
			JavaUI.openInEditor(element);
		} catch (Exception e) {
			MessageDialog
			.openError(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					J2EEUIMessages
					.getResourceString("Problems_Opening_Editor_ERROR_"), e.getMessage()); //$NON-NLS-1$ = "Problems Opening Editor"
		}
	}

	/**
	 * open the appropriate editor
	 */
	protected void openAppropriateEditor(IResource r) {
		if (r == null)
			return;
		IWorkbenchPage page = null;
		IEditorPart editor = null;
		try {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage();
			String ID = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(r.getLocation()
					.toPortableString()).getId();
			editor = page.openEditor(new FileEditorInput((IFile) r), ID);
			if (editor instanceof ISetSelectionTarget)
				((ISetSelectionTarget) editor)
				.selectReveal(getStructuredSelection());
		} catch (Exception e) {
			MessageDialog
			.openError(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					J2EEUIMessages
					.getResourceString("Problems_Opening_Editor_ERROR_"), e.getMessage()); //$NON-NLS-1$ = "Problems Opening Editor"
		}
	}

	/**
	 * The user has invoked this action
	 */
	@Override
	public void run() {
		if (!isEnabled())
			return;

		if (srcObject instanceof J2EEJavaClassProviderHelper) {
			((J2EEJavaClassProviderHelper) srcObject).openInEditor();
			return;
		}
		
		//[Bug 240512] deal with if any of these node types ndo not have an associated class
		if (srcObject instanceof SessionBean ||
				srcObject instanceof MessageDrivenBean ||
				srcObject instanceof EntityBean ||
				srcObject instanceof Servlet ||
				srcObject instanceof Filter ||
				srcObject instanceof Listener){
			
			String name = ""; //$NON-NLS-1$
			if( srcObject instanceof SessionBean ){
				SessionBean bean = (SessionBean)srcObject;
				name = bean.getEjbClass();
			} else if(srcObject instanceof MessageDrivenBean){
				MessageDrivenBean  bean = (MessageDrivenBean)srcObject;
				name = bean.getEjbClass();
			} else if(srcObject instanceof EntityBean){
				EntityBean bean = (EntityBean)srcObject;
				name = bean.getEjbClass();
			} else if(srcObject instanceof Servlet){
				Servlet servlet = (Servlet)srcObject;
				name = servlet.getServletClass();
			} else if(srcObject instanceof Filter) {
	        	Filter filter = (Filter)srcObject;		
	        	name = filter.getFilterClass();
			} else if(srcObject instanceof Listener){
	        	Listener listener = (Listener)srcObject;
	        	name = listener.getListenerClass();
			}
			
			IResource resource = WorkbenchResourceHelper.getFile((EObject)srcObject);
			if (resource == null)
				return;
			IProject project = resource.getProject();			
			IJavaProject javaProject = JavaCore.create(project);
			if(javaProject.exists()){
				IType type = null;
				try {
					//if name is null then can't get type
					if(name != null) {
						type = javaProject.findType( name );
					}
					
					//if type is null then can't open its editor, so open editor for the resource
					if(type != null) {
						ICompilationUnit cu = type.getCompilationUnit();						
						if (cu != null) {
							EditorUtility.openInEditor(cu);
						} else {
							EditorUtility.openInEditor(type);
						}
					} else{
						openAppropriateEditor(resource);
					}
				} catch (JavaModelException e) {
					JEEUIPlugin.logError(e.getMessage(), e);
				} catch (PartInitException e) {
					JEEUIPlugin.logError(e.getMessage(), e);
				}
			}
			return;
		}
		
		
		if (srcObject instanceof EObject) {
			if(srcObject instanceof EjbLocalRefImpl || srcObject instanceof ResourceRefImpl){
				IResource resource = WorkbenchResourceHelper.getFile((EObject)srcObject);
				if (resource == null)
				{
					openEObject((EObject) srcObject);
					return;
				}
				IProject project = resource.getProject();
				
				EJBJar ejbJar = (EJBJar) ModelProviderManager.getModelProvider(project).getModelObject(new Path(J2EEConstants.EJBJAR_DD_URI));
				
				if(srcObject instanceof EjbLocalRefImpl){
					openEjbLocalRefNode(resource, ejbJar);
				} else if(srcObject instanceof ResourceRefImpl){
					openResourceRefNode(resource, ejbJar);
				}
			} else {			
			    openEObject((EObject) srcObject);
			}
		} else if (srcObject instanceof BeanInterfaceNode) {
			openAppropriateEditor(((BeanInterfaceNode) srcObject).get_fqn());
			return;
		} else if (srcObject instanceof BeanNode) {
			openAppropriateEditor(((BeanNode) srcObject).getEjbClassQualifiedName());
			return;
		} else if (srcObject instanceof WebAppProvider) {
			IFile file = ((WebAppProvider) srcObject).getDDFile();
			if (file.isAccessible()){				
				openAppropriateEditor(file);
				return;
			}
		} else if(srcObject instanceof WebArtifactNode){ 
 			openEObject((EObject) ((WebArtifactNode)srcObject).getJavaEEObject()); 

		} else if (srcObject instanceof GroupEJBProvider) {
			openEObject((EObject) ((GroupEJBProvider)srcObject).getEjbJar());
		} else if (srcObject instanceof GroupEARProvider) {
			IFile file = ((GroupEARProvider) srcObject).getDDFile();
			if (file.isAccessible()){
				openAppropriateEditor(file);
				return;
			}
		} else if (srcObject instanceof GroupAppClientProvider) {
			IFile file = ((GroupAppClientProvider) srcObject).getDDFile();
			if (file.isAccessible()){
				openAppropriateEditor(file);
				return;
			}
		} else if (srcObject instanceof AbstractGroupProvider){ 
			openEObject((EObject) ((AbstractGroupProvider)srcObject).getJavaEEObject()); 
		} else if (srcObject instanceof Resource)
			openAppropriateEditor(WorkbenchResourceHelper
					.getFile((Resource) srcObject));
	}

	private void openResourceRefNode(IResource resource, EJBJar ejbJar) {
		if (ejbJar.getEnterpriseBeans() != null) {
			List<SessionBean> beans = ejbJar.getEnterpriseBeans().getSessionBeans();
			for (SessionBean bean : beans) {
				for (ResourceRef localref : bean.getResourceRefs()) {
					if (localref.getResRefName().equals(((ResourceRef) srcObject).getResRefName())) {
						openAppropriateEditor(resource);
						return;
					}
				}
			}
		}
		if (((ResourceRefImpl) srcObject).eContainer() instanceof SessionBean) {
			openAppropriateEditor(((SessionBean) (((ResourceRefImpl) srcObject).eContainer())).getEjbClass());
			return;
		}
	}

	private void openEjbLocalRefNode(IResource resource, EJBJar ejbJar) {
		if (ejbJar.getEnterpriseBeans() != null) {
			List<SessionBean> beans = ejbJar.getEnterpriseBeans().getSessionBeans();
			for (SessionBean bean : beans) {
				for (EjbLocalRef localref : bean.getEjbLocalRefs()) {
					if (localref.getEjbRefName().equals(((EjbLocalRef) srcObject).getEjbRefName())) {
						openAppropriateEditor(resource);
						return;
					}
				}
			}
		}
		if (((EjbLocalRefImpl) srcObject).eContainer() instanceof SessionBean) {
			openAppropriateEditor(((SessionBean) (((EjbLocalRefImpl) srcObject).eContainer())).getEjbClass());
		    return;
		}
	}

	private void openEObject(EObject _srcObject) {
		EObject ro = _srcObject;
		IResource resource = WorkbenchResourceHelper
		.getFile(_srcObject);
		if (resource != null) {
			openAppropriateEditor(resource);
		} else {
			ModuleFile moduleFile = ArchiveUtil.getModuleFile(ro);
			if (moduleFile != null) {
				ArchiveOptions options = moduleFile.getOptions();
				if (options instanceof ComponentArchiveOptions) {
					IVirtualComponent component = ((ComponentArchiveOptions) options)
					.getComponent();
					openAppropriateEditor(component);
				}
			} else {
				IArchive archive = JavaEEArchiveUtilities.findArchive(ro);
				if(archive != null){
					IVirtualComponent component = JavaEEArchiveUtilities.findComponent(archive);
					if(component != null){
						openAppropriateEditor(component);
					}
				}
			}
		}
		
	}

	/**
	 * The structured selection has changed in the workbench. Subclasses should
	 * override this method to react to the change. Returns true if the action
	 * should be enabled for this selection, and false otherwise.
	 * 
	 * When this method is overridden, the super method must always be invoked.
	 * If the super method returns false, this method must also return false.
	 * 
	 * @param sel
	 *            the new structured selection
	 */
	@Override
	public boolean updateSelection(IStructuredSelection s) {
		if (!super.updateSelection(s))
			return false;

		// Make sure this is one of the selections we can handle,
		// then set the source object as is. The run() will do the hard stuff.
		Object obj = s.getFirstElement();
		setText("Open"); //$NON-NLS-1$

		if (obj instanceof J2EEJavaClassProviderHelper)
			currentDescriptor = getJavaEditorDescriptor();
		else if (obj instanceof BeanLink)
			currentDescriptor = getBaseJavaEditorDescriptor();
		else if (obj instanceof EObject) {
			IEditorRegistry registry = PlatformUI.getWorkbench()
			.getEditorRegistry();
			IFile file = WorkbenchResourceHelper.getFile((EObject) obj);
			if (file != null) {
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			} else {
				if(((EObject) obj).eResource() != null){
					String name = (new Path(((EObject) obj).eResource().getURI()
							.toString())).lastSegment();
					currentDescriptor = registry.getDefaultEditor(name, null);
				}
			}
		} else if (obj instanceof Resource) {
			IEditorRegistry registry = PlatformUI.getWorkbench()
			.getEditorRegistry();
			IFile file = WorkbenchResourceHelper.getFile((Resource) obj);
			IContentType contentType = IDE.getContentType(file);
			currentDescriptor = registry.getDefaultEditor(file.getName(),
					contentType);
		} else if (obj instanceof GroupEARProvider) {
			IFile file = ((GroupEARProvider) obj).getDDFile();
			if (file.isAccessible()){
				IEditorRegistry registry = PlatformUI.getWorkbench()
				.getEditorRegistry();
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			}else{
				currentDescriptor = null;
				return false;
			}
		} else if (obj instanceof GroupEJBProvider) {
			IFile file = WorkbenchResourceHelper.getFile((EObject)((GroupEJBProvider)obj).getEjbJar());
			if (file.isAccessible()){
				IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			}else{
				currentDescriptor = null;
				return false;
			}
		} else if (obj instanceof GroupAppClientProvider) {
			IFile file = ((GroupAppClientProvider) obj).getDDFile();
			if (file.isAccessible()){
				IEditorRegistry registry = PlatformUI.getWorkbench()
				.getEditorRegistry();
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			}else{
				currentDescriptor = null;
				return false;
			}
		} else if (obj instanceof WebAppProvider) {
			IFile file = ((WebAppProvider) obj).getDDFile();
			if (file.isAccessible()){
				IEditorRegistry registry = PlatformUI.getWorkbench()
				.getEditorRegistry();
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			} else{
				currentDescriptor = null;
				return false;
			}
		} else if (obj instanceof RaGroupContentProvider) {
			IFile file = WorkbenchResourceHelper.getFile((EObject) ((AbstractGroupProvider)obj).getJavaEEObject());
			if (file.isAccessible()){
				IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			}else{
				currentDescriptor = null;
				return false;
			}
		} else if (obj instanceof BeanNode) {

			IEditorRegistry registry = PlatformUI.getWorkbench()
			.getEditorRegistry();
			JavaEEObject enterpriseBean = ((BeanNode) obj).getEnterpriseBean();
			IFile file = WorkbenchResourceHelper.getFile((EObject)enterpriseBean);
			if (file != null) {
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			} else {
				if(((EObject) enterpriseBean).eResource() != null){
					String name = (new Path(((EObject) enterpriseBean).eResource().getURI()
							.toString())).lastSegment();
					currentDescriptor = registry.getDefaultEditor(name, null);
				}
			}      
		} else  if (obj instanceof BeanInterfaceNode) {

			IEditorRegistry registry = PlatformUI.getWorkbench()
			.getEditorRegistry();
			JavaEEObject beanInterface = (JavaEEObject) ((BeanInterfaceNode) obj).getAdapterNode();
			IFile file = WorkbenchResourceHelper.getFile((EObject)beanInterface);
			if (file != null) {
				IContentType contentType = IDE.getContentType(file);
				currentDescriptor = registry.getDefaultEditor(file.getName(),
						contentType);
			} else {
				if(((EObject) beanInterface).eResource() != null){
					String name = (new Path(((EObject) beanInterface).eResource().getURI().toString())).lastSegment();
					currentDescriptor = registry.getDefaultEditor(name, null);
				} else {
					String fqn = ((BeanInterfaceNode) obj).get_fqn();
					if (fqn != null){
						currentDescriptor = registry.getDefaultEditor(((BeanInterfaceNode) obj).getText(), null);
//						return true;
					} else {
						return false;
					}
					
				}
			}
		}
		setAttributesFromDescriptor();
		srcObject = obj;
		return true;
	}
}
