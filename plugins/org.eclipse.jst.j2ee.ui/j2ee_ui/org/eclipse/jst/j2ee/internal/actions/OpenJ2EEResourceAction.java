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
package org.eclipse.jst.j2ee.internal.actions;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Action for opening a J2EE resource from the J2EE navigator.
 */
public class OpenJ2EEResourceAction extends AbstractOpenAction {
	public static final String ID = "org.eclipse.jst.j2ee.internal.internal.ui.actions.OpenJ2EEResourceAction"; //$NON-NLS-1$
	public static final String EJB_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.ejb.ui.presentation.EJBMultiEditor"; //$NON-NLS-1$
	public static final String WEB_EDITOR_ID = "com.ibm.etools.web.ui.presentation.WebEditor"; //$NON-NLS-1$
	public static final String APP_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.application.presentation.ApplicationEditor"; //$NON-NLS-1$
	public static final String APP_CLIENT_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.application.client.presentation.ApplicationClientEditor"; //$NON-NLS-1$
	public static final String JAVA_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.ejb.ui.java.EnterpriseBeanJavaEditor"; //$NON-NLS-1$
	public static final String EJB_MAP_EDITOR_ID = "com.ibm.etools.ejbrdbmapping.presentation.EjbRdbMappingEditorID"; //$NON-NLS-1$
	public static final String CONNECTOR_EDITOR_ID = "org.eclipse.wst.xml.ui.StructuredTextEditorXML"; //$NON-NLS-1$
	public static final String WEB_SERVICE_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.webservice.atk.ui.editor.ws.WebServicesEditor"; //$NON-NLS-1$
	public static final String BASE_JAVA_EDITOR_ID = "org.eclipse.jdt.ui.CompilationUnitEditor"; //$NON-NLS-1$
	public static final String WSDL_EDITOR_ID = "com.ibm.etools.wsdleditor.WSDLEditor"; //$NON-NLS-1$

	protected static IEditorDescriptor ejbEditorDescriptor;
	protected static IEditorDescriptor ejbMapEditorDescriptor;
	protected static IEditorDescriptor webEditorDescriptor;
	protected static IEditorDescriptor appEditorDescriptor;
	protected static IEditorDescriptor javaEditorDescriptor;
	protected static IEditorDescriptor baseJavaEditorDescriptor;
	protected static IEditorDescriptor appClientEditorDescriptor;
	protected static IEditorDescriptor connectorEditorDescriptor;
	protected static IEditorDescriptor webServiceDescriptor;
	protected static IEditorDescriptor wsdlDescriptor;

	/**
	 * Create an instance of this class
	 */
	public OpenJ2EEResourceAction() {
		super(""); //$NON-NLS-1$
	}

	public static IEditorDescriptor getAppEditorDescriptor() {
		if (appEditorDescriptor == null)
			appEditorDescriptor = findEditorDescriptor(APP_EDITOR_ID);
		return appEditorDescriptor;
	}

	public static IEditorDescriptor getAppClientEditorDescriptor() {
		if (appClientEditorDescriptor == null)
			appClientEditorDescriptor = findEditorDescriptor(APP_CLIENT_EDITOR_ID);
		return appClientEditorDescriptor;
	}

	public static IEditorDescriptor getEjbEditorDescriptor() {
		if (ejbEditorDescriptor == null)
			ejbEditorDescriptor = findEditorDescriptor(EJB_EDITOR_ID);
		return ejbEditorDescriptor;
	}

	public static IEditorDescriptor getEjbMapEditorDescriptor() {
		if (ejbMapEditorDescriptor == null)
			ejbMapEditorDescriptor = findEditorDescriptor(EJB_MAP_EDITOR_ID);
		return ejbMapEditorDescriptor;
	}

	/**
	 * Gets the connector editor descriptor.
	 * 
	 * @return IEditorDescriptor
	 */
	public static IEditorDescriptor getConnectorDescriptor() {
		if (connectorEditorDescriptor == null)
			connectorEditorDescriptor = findEditorDescriptor(CONNECTOR_EDITOR_ID);
		return connectorEditorDescriptor;
	}// getConnectorDescriptor

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

	public static IEditorDescriptor getWebEditorDescriptor() {
		if (webEditorDescriptor == null)
			webEditorDescriptor = findEditorDescriptor(WEB_EDITOR_ID);
		return webEditorDescriptor;
	}

	public static IEditorDescriptor getWebServiceDescriptor() {
		if (webServiceDescriptor == null)
			webServiceDescriptor = findEditorDescriptor(WEB_SERVICE_EDITOR_ID);
		return webServiceDescriptor;
	}

	public static IEditorDescriptor getWsdlDescriptor() {
		if (wsdlDescriptor == null)
			wsdlDescriptor = findEditorDescriptor(WSDL_EDITOR_ID);
		return wsdlDescriptor;
	}

	/**
	 * The user has invoked this action
	 */
	public void run() {
		if (!isEnabled())
			return;

		IResource r = null;
		if (srcObject instanceof J2EEJavaClassProviderHelper) {
			((J2EEJavaClassProviderHelper) srcObject).openInEditor();
			return;
		}

		// Handle wsdl case, non emf object
		// TODO WebServices for M3
		//		if (srcObject instanceof WSDLResourceImpl || isWsdlInstance(srcObject)) {
		//			openWsdlEditor();
		//			return;
		//		}

		EObject ro = (EObject) srcObject;
		IProject p = ProjectUtilities.getProject(ro);
		J2EENature nature = J2EENature.getRegisteredRuntime(p);

		// TODO WebServices for M3
		//		if (ro instanceof Definition || ro instanceof Service || ro instanceof Handler)
		//			r = getWebService(p); else
		if (ro instanceof BeanLink) {
			openBeanLinkInJavaEditor((BeanLink) ro, p);
			return;
		} else if (nature instanceof J2EEModuleNature && ((J2EEModuleNature) nature).isBinaryProject())
			r = ((J2EEModuleNature) nature).getBinaryProjectInputJARResource();
		else
			r = getFileFromJ2EEEditModel(nature);

		openAppropriateEditor(r);
	}

	// TODO WebServices for M3
	//	private void openWsdlEditor() {
	//		WSDLResourceImpl wsdl = null;
	//		if (srcObject instanceof WSDLResourceImpl)
	//			wsdl = (WSDLResourceImpl) srcObject;
	//		else if (srcObject instanceof Service)
	//			wsdl = (WSDLResourceImpl) ((Service) srcObject).eResource();
	//		if (wsdl != null) {
	//			IResource r = WorkbenchResourceHelper.getFile(wsdl);
	//			openAppropriateEditor(r);
	//		}
	//	}

	/**
	 * Get file from the J2EE EditModel for root object
	 */
	private IResource getFileFromJ2EEEditModel(J2EENature nature) {
		EditModel editModel = null;
		Resource eResource = null;
		try {
			editModel = nature.getJ2EEEditModelForRead(this);
			srcObject = editModel.getPrimaryRootObject();
			eResource = editModel.getPrimaryResource();
		} finally {
			if (editModel != null)
				editModel.releaseAccess(this);
		}

		return (eResource == null) ? null : WorkbenchResourceHelper.getFile(eResource);
	}

	/**
	 * open the appropriate editor
	 */
	private void openAppropriateEditor(IResource r) {
		//TODO Temp added SSE Editor override for missing editors
		if (r == null)
			return;
		IWorkbenchPage page = null;
		IEditorPart editor = null;
		try {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			editor = page.openEditor(new FileEditorInput((IFile) r), currentDescriptor.getId());
		} catch (Exception e) {
			// Editor not found - Use the source editor
			currentDescriptor = getConnectorDescriptor();
		}
		try {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			editor = page.openEditor(new FileEditorInput((IFile) r), currentDescriptor.getId());
			if (editor instanceof ISetSelectionTarget)
				((ISetSelectionTarget) editor).selectReveal(getStructuredSelection());
		} catch (Exception e) {
			MessageDialog.openError(page.getWorkbenchWindow().getShell(), J2EEUIMessages.getResourceString("Problems_Opening_Editor_ERROR_"), e.getMessage()); //$NON-NLS-1$ = "Problems Opening Editor"
		}
	}

	/**
	 * @param link
	 */
	private void openBeanLinkInJavaEditor(BeanLink link, IProject p) {
		String linkName = null;
		JavaClass javaClass = null;
		// Handle EJB Link case
		if (link instanceof EJBLink) {
			linkName = ((EJBLink) link).getEjbLink();
			EJBJar ejbJar = EJBNatureRuntime.getRuntime(p).getEJBJar();
			if (ejbJar == null)
				return;
			EnterpriseBean bean = ejbJar.getEnterpriseBeanNamed(linkName);
			if (bean == null)
				return;
			javaClass = bean.getEjbClass();
		}
		// Handle Servlet Link case
		else {
			linkName = ((ServletLink) link).getServletLink();
			WebApp webApp = J2EEWebNatureRuntime.getRuntime(p).getWebApp();
			if (webApp == null)
				return;
			Servlet servlet = webApp.getServletNamed(linkName);
			if (servlet == null)
				return;
			javaClass = servlet.getServletClass();
		}
		// Open java editor on the selected objects associated java file
		try {
			J2EEEditorUtility.openInEditor(javaClass, p);
		} catch (Exception cantOpen) {
			cantOpen.printStackTrace();
		}
	}

	/**
	 * Return the web service file
	 */
	private IResource getWebService(IProject p) {
		List files = ProjectUtilities.getAllProjectFiles(p);
		for (int i = 0; i < files.size(); i++) {
			IFile file = (IFile) files.get(i);
			if (file.getFullPath().toString().endsWith("webservices.xml")) //$NON-NLS-1$
				return file;
		}
		return null;
	}

	/**
	 * The structured selection has changed in the workbench. Subclasses should override this method
	 * to react to the change. Returns true if the action should be enabled for this selection, and
	 * false otherwise.
	 * 
	 * When this method is overridden, the super method must always be invoked. If the super method
	 * returns false, this method must also return false.
	 * 
	 * @param sel
	 *            the new structured selection
	 */
	public boolean updateSelection(IStructuredSelection s) {
		if (!super.updateSelection(s))
			return false;

		// Make sure this is one of the selections we can handle,
		// then set the source object as is. The run() will do the hard stuff.
		Object obj = s.getFirstElement();
		if (isEJBInstance(obj) && isEJBAvailable())
			currentDescriptor = getEjbEditorDescriptor();
		else if (isWebInstance(obj))
			currentDescriptor = getWebEditorDescriptor();
		else if (isApplicationInstance(obj))
			currentDescriptor = getAppEditorDescriptor();
		else if (obj instanceof J2EEJavaClassProviderHelper)
			currentDescriptor = getJavaEditorDescriptor();
		else if (isApplicationClientInstance(obj))
			currentDescriptor = getAppClientEditorDescriptor();
		else if (isConnectorInstance(obj))
			currentDescriptor = getConnectorDescriptor();
		//else if ((obj instanceof EObject) &&
		// (((EObject)obj).eResource().getURI().getExtension().equals("mapxmi"))) //$NON-NLS-1$
		//	currentDescriptor = getEjbMapEditorDescriptor();
		else if (obj instanceof BeanLink)
			currentDescriptor = getBaseJavaEditorDescriptor();
		// TODO WebServices for M3
		//		else if (isWsdlInstance(obj))
		//			currentDescriptor = getWsdlDescriptor();
		//		else if (isWebserviceInstance(obj))
		//			currentDescriptor = getWebServiceDescriptor();
		else {
			currentDescriptor = null;
			return false;
		}

		setAttributesFromDescriptor();
		srcObject = obj;
		return true;
	}

	/**
	 * Checks if the the object is an intance of a connector project.
	 * 
	 * @param Object
	 *            obj - The object to check instanceof.
	 */
	protected boolean isConnectorInstance(Object obj) {
		EObject root = getRootObject(obj);
		return root instanceof Connector;
	}// isConnectorInstance

	protected boolean isEJBInstance(Object obj) {
		EObject root = getRootObject(obj);

		return root instanceof EJBJar || is13ServiceRefForNature(IEJBNatureConstants.NATURE_ID, root);
	}

	protected boolean isApplicationInstance(Object obj) {
		return obj instanceof Application || obj instanceof Module || obj instanceof SecurityRole;
	}

	protected boolean isApplicationClientInstance(Object obj) {
		EObject root = getRootObject(obj);
		return root instanceof ApplicationClient || is13ServiceRefForNature(IApplicationClientNatureConstants.NATURE_ID, root);
	}

	protected boolean isWebInstance(Object obj) {
		EObject root = getRootObject(obj);
		return root instanceof WebApp || is13ServiceRefForNature(IWebNatureConstants.J2EE_NATURE_ID, root);
	}

	/**
	 * Checks if the the object is an intance of a connector project.
	 * 
	 * @param Object
	 *            obj - The object to check instanceof.
	 */
	// TODO WebServices for M3
	//	protected boolean isWebserviceInstance(Object obj) {
	//		EObject root = getRootObject(obj);
	//		return (root instanceof Definition || root instanceof WebServices);
	//	}
	// TODO WebServices for M3
	//	protected boolean isWsdlInstance(Object obj) {
	//		EObject root = getRootObject(obj);
	//		IProject p = ProjectUtilities.getProject(root);
	//		Object ws = getWebService(p);
	//		return (obj instanceof WSDLResourceImpl) || (root instanceof Definition && ws == null);
	//	}
	protected boolean is13ServiceRefForNature(String natureId, EObject root) {
		try {
			if (root instanceof WebServicesClient) {
				IProject project = ProjectUtilities.getProject(root);
				return project.hasNature(natureId);
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	protected EObject getRootObject(Object obj) {
		if (obj instanceof EObject) {
			EObject refObj = (EObject) obj;
			while (refObj != null && refObj.eContainer() != null)
				refObj = refObj.eContainer();
			return refObj;
		}
		return null;
	}

	protected boolean isEJBAvailable() {
		return J2EEPlugin.isEJBSupportAvailable();
	}

}