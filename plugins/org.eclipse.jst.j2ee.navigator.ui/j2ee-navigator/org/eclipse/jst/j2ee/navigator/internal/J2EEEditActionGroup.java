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
 * Created on Feb 5, 2004
 *  
 */
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.actions.ImportClassesAction;
import org.eclipse.jst.j2ee.internal.actions.J2EERenameAction;
import org.eclipse.jst.j2ee.internal.actions.OpenJ2EEResourceAction;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.provider.J2EEUtilityJarItemProvider;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.wst.common.framework.AdaptabilityUtility;
import org.eclipse.wst.common.navigator.actions.NewWizardActionGroup;
import org.eclipse.wst.common.navigator.actions.WizardActionGroup;
import org.eclipse.wst.common.navigator.views.actions.CommonEditActionGroup;
import org.eclipse.wst.common.ui.WTPGenericActionIds;
import org.eclipse.wst.common.ui.WTPOptionalOperationAction;

/**
 * @author mdelder
 *  
 */
public class J2EEEditActionGroup extends CommonEditActionGroup implements ISelectionChangedListener {

	protected static final String NEW_EAR_PROJECT_WIZARD_ID = "org.eclipse.jst.j2ee.internal.earProjectWizard"; //$NON-NLS-1$

	protected static final String NEW_APPCLIENT_PROJECT_WIZARD_ID = "org.eclipse.jst.j2ee.internal.appclientProjectWizard"; //$NON-NLS-1$

	protected static final String NEW_JCA_PROJECT_WIZARD_ID = "org.eclipse.jst.j2ee.internal.jcaProjectWizard"; //$NON-NLS-1$

	protected static final String NEW_EJB_PROJECT_WIZARD_ID = "org.eclipse.jst.j2ee.internal.ejb.ui.util.ejbProjectWizard"; //$NON-NLS-1$

	protected static final String NEW_WEB_PROJECT_WIZARD_ID = "org.eclipse.jst.j2ee.internal.webProjectWizard"; //$NON-NLS-1$

	protected static final String NEW_EAR_WIZARD_ID = ""; //$NON-NLS-1$

	protected static final String NEW_APPCLIENT_WIZARD_ID = ""; //$NON-NLS-1$

	protected static final String NEW_CONNECTOR_WIZARD_ID = ""; //$NON-NLS-1$

	protected static final String NEW_ENTERPRISE_BEAN_WIZARD_ID = "org.eclipse.jst.j2ee.internal.ejb.ui.util.createEJBWizard"; //$NON-NLS-1$

	protected static final String NEW_WEB_MODULE_WIZARD_ID = ""; //$NON-NLS-1$

	protected static final String IMPORT_EAR_WIZARD_ID = "EARImportWizard"; //$NON-NLS-1$

	protected static final String IMPORT_APPCLIENT_WIZARD_ID = "ApplicationClientImportWizard"; //$NON-NLS-1$

	protected static final String IMPORT_CONNECTOR_WIZARD_ID = "ConnectorImportWizard"; //$NON-NLS-1$

	protected static final String IMPORT_ENTERPRISE_BEAN_WIZARD_ID = "EJBImportWizard"; //$NON-NLS-1$

	protected static final String IMPORT_WEB_MODULE_WIZARD_ID = "WarImportWizard"; //$NON-NLS-1$

	protected static final String IMPORT_UTILITY_JAR_WIZARD_ID = "J2EEUtilityJarImportWizard"; //$NON-NLS-1$

	protected static final String EXPORT_EAR_WIZARD_ID = "EARExportWizard"; //$NON-NLS-1$

	protected static final String EXPORT_APPCLIENT_WIZARD_ID = "ApplicationClientExportWizard"; //$NON-NLS-1$

	protected static final String EXPORT_CONNECTOR_WIZARD_ID = "ConnectorExportWizard"; //$NON-NLS-1$

	protected static final String EXPORT_ENTERPRISE_BEAN_WIZARD_ID = "EJBExportWizard"; //$NON-NLS-1$

	protected static final String EXPORT_WEB_MODULE_WIZARD_ID = "WarExportWizard"; //$NON-NLS-1$

	protected static final String[] EAR_GROUP_IDS = new String[]{NEW_EAR_PROJECT_WIZARD_ID};

	protected static final String[] APPCLIENT_GROUP_IDS = new String[]{NEW_APPCLIENT_PROJECT_WIZARD_ID};

	protected static final String[] JCA_GROUP_IDS = new String[]{NEW_JCA_PROJECT_WIZARD_ID};

	protected static final String[] EJB_GROUP_IDS = new String[]{NEW_EJB_PROJECT_WIZARD_ID};

	protected static final String[] WEB_GROUP_IDS = new String[]{NEW_WEB_PROJECT_WIZARD_ID};

	protected static final String[] EAR_PROJECT_IDS = new String[]{NEW_APPCLIENT_PROJECT_WIZARD_ID, NEW_JCA_PROJECT_WIZARD_ID, NEW_EJB_PROJECT_WIZARD_ID, NEW_WEB_PROJECT_WIZARD_ID};

	protected static final String[] APPCLIENT_PROJECT_IDS = new String[]{NEW_APPCLIENT_WIZARD_ID};

	protected static final String[] JCA_PROJECT_IDS = new String[]{NEW_CONNECTOR_WIZARD_ID};

	protected static final String[] EJB_PROJECT_IDS = new String[]{NEW_ENTERPRISE_BEAN_WIZARD_ID};

	protected static final String[] WEB_PROJECT_IDS = new String[]{NEW_WEB_MODULE_WIZARD_ID};

	protected static final String[] EAR_IMPORT_IDS = new String[]{IMPORT_APPCLIENT_WIZARD_ID, IMPORT_CONNECTOR_WIZARD_ID, IMPORT_ENTERPRISE_BEAN_WIZARD_ID, IMPORT_WEB_MODULE_WIZARD_ID, IMPORT_UTILITY_JAR_WIZARD_ID};

	protected static final String[] APPCLIENT_IMPORT_IDS = new String[]{IMPORT_APPCLIENT_WIZARD_ID};

	protected static final String[] JCA_IMPORT_IDS = new String[]{IMPORT_CONNECTOR_WIZARD_ID};

	protected static final String[] EJB_IMPORT_IDS = new String[]{IMPORT_ENTERPRISE_BEAN_WIZARD_ID};

	protected static final String[] WEB_IMPORT_IDS = new String[]{IMPORT_WEB_MODULE_WIZARD_ID};

	protected static final String[] EAR_EXPORT_IDS = new String[]{EXPORT_EAR_WIZARD_ID};

	protected static final String[] APPCLIENT_EXPORT_IDS = new String[]{EXPORT_APPCLIENT_WIZARD_ID};

	protected static final String[] JCA_EXPORT_IDS = new String[]{EXPORT_CONNECTOR_WIZARD_ID};

	protected static final String[] EJB_EXPORT_IDS = new String[]{EXPORT_ENTERPRISE_BEAN_WIZARD_ID};

	protected static final String[] WEB_EXPORT_IDS = new String[]{EXPORT_WEB_MODULE_WIZARD_ID};

	private OpenJ2EEResourceAction openAction;

	private J2EERenameAction renameAction;

	// private DeleteEnterpriseBeanAction deleteEJBAction;

	/*
	 * private J2EEDeleteAction deleteModuleAction;
	 * 
	 * private DeleteResourceAction deleteResourceAction;
	 */

	private WizardActionGroup newWizardActionGroup;

	private WizardActionGroup importWizardActionGroup;

	private WizardActionGroup exportWizardActionGroup;

	private ImportClassesAction importClassesAction;

	private J2EENavigatorContentExtension containingExtension;

	private WTPOptionalOperationAction genericDelete;

	public J2EEEditActionGroup(J2EENavigatorContentExtension containingExtension) {
		super(containingExtension.getExtensionSite());
		this.containingExtension = containingExtension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonNavigatorActionGroup#makeActions()
	 */
	protected void makeActions() {
		openAction = new OpenJ2EEResourceAction();
		renameAction = new J2EERenameAction(getExtensionSite().getViewSite(), getExtensionSite().getShell());
		//deleteEJBAction = new
		// DeleteEnterpriseBeanAction(getExtensionSite().getNavigatorViewPart().getSite());
		/*
		 * deleteModuleAction = new J2EEDeleteAction(getExtensionSite().getViewSite(),
		 * getExtensionSite().getShell()); deleteResourceAction = new
		 * DeleteResourceAction(getExtensionSite().getShell());
		 */
		genericDelete = new WTPOptionalOperationAction(WTPGenericActionIds.DELETE, WorkbenchMessages.getString("Workbench.delete")); //$NON-NLS-1$
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		genericDelete.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		genericDelete.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		genericDelete.setWorkbenchSite(getExtensionSite().getViewSite());

		IWorkbenchWindow window = getExtensionSite().getViewSite().getWorkbenchWindow();
		newWizardActionGroup = new NewWizardActionGroup(window);

		importWizardActionGroup = new WizardActionGroup(window, WizardActionGroup.IMPORT_WIZARD);
		exportWizardActionGroup = new WizardActionGroup(window, WizardActionGroup.EXPORT_WIZARD);

		importClassesAction = new ImportClassesAction();

		getExtensionSite().getSelectionProvider().addSelectionChangedListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		renameAction.selectionChanged(event);
		//deleteEJBAction.selectionChanged(event);
		genericDelete.selectionChanged(genericDelete, event.getSelection());
		/* deleteResourceAction.selectionChanged(event); */

		IActionBars actionBars = getExtensionSite().getViewSite().getActionBars();
		//if(deleteEJBAction.isEnabled()) {
		//    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteEJBAction);
		//    actionBars.updateActionBars();
		//} else
		if (genericDelete.isEnabled()) {
			actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), genericDelete);
			actionBars.updateActionBars();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#isEnabled(int)
	 */
	public boolean isEditActionEnabled(int actionType) {
		switch (actionType) {
			case DELETE_ACTION :
			/*
			 * deleteModuleAction.selectionChanged((ISelection) selection); return
			 * deleteModuleAction.isEnabled();
			 */
			default :
				return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonNavigatorActionGroup#handlesKeyStroke(org.eclipse.swt.events.KeyEvent)
	 */
	public boolean handlesKeyPressed(KeyEvent event, IStructuredSelection selection) {
		return super.handlesKeyPressed(event, selection) || ((event.character == SWT.DEL && event.stateMask == 0) && isValidDeleteSelection(selection));
	}

	/**
	 * @param selection
	 * @return
	 */
	protected boolean isValidDeleteSelection(IStructuredSelection selection) {
		/*
		 * Object[] items = selection.toArray(); for(int i=0; i <items.length; i++) { if( !(items[i]
		 * instanceof EnterpriseBean || CommonUtil.isDeploymentDescriptorRoot(items[i], true) ||
		 * isJ2EEProject(items[i]) ))
		 */return false;
		/*
		 * } return true;
		 */
	}

	protected boolean isJ2EEProject(Object o) {
		boolean retVal = false;
		if (o instanceof JavaProject) {
			o = ((JavaProject) o).getProject();
		}
		if (o instanceof IProject) {
			IProject project = (IProject) o;
			try {
				if (EJBNatureRuntime.hasRuntime(project)) {
					retVal = true;
				} else if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
					retVal = true;
				} else if (ApplicationClientNatureRuntime.hasRuntime(project)) {
					retVal = true;
				} else if (ConnectorNatureRuntime.hasRuntime(project)) {
					retVal = true;
				} else if (EARNatureRuntime.hasRuntime(project))
					retVal = true;
			} catch (CoreException ex) {
				retVal = false;
			}
		}
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonNavigatorActionGroup#handleKeyPressed(org.eclipse.swt.events.KeyEvent)
	 */
	public void handleKeyPressed(KeyEvent event) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (event.character == SWT.DEL && event.stateMask == 0)
			delete(selection);
	}

	protected boolean isUtilityJars(Object[] items) {
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] == null || items[i].getClass() != J2EEUtilityJarItemProvider.class) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonNavigatorActionGroup#runDefaultAction(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void runDefaultAction(IStructuredSelection selection) {
		openAction.selectionChanged(selection);
		if (openAction.isEnabled())
			openAction.run();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		fillEditContextMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#fillNewMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillNewMenu(IMenuManager menu) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object element = selection.getFirstElement();

		Object rootObject = getRootObject();
		if (rootObject != null) {
			if (rootObject instanceof Application)
				newWizardActionGroup.setWizardActionIds(EAR_PROJECT_IDS);
			else if (rootObject instanceof ApplicationClient)
				newWizardActionGroup.setWizardActionIds(APPCLIENT_PROJECT_IDS);
			else if (rootObject instanceof Connector)
				newWizardActionGroup.setWizardActionIds(JCA_PROJECT_IDS);
			else if (rootObject instanceof EJBJar && element instanceof EJBJar)
				newWizardActionGroup.setWizardActionIds(EJB_PROJECT_IDS);
			// If root object is ejb jar but we are selected on a ejb grouping, we'll use plugin
			// object
			// contributions to add the new menu options, so clear out menu.
			else if (rootObject instanceof EJBJar)
				newWizardActionGroup.setWizardActionIds(new String[0]);
			else if (rootObject instanceof WebApp)
				newWizardActionGroup.setWizardActionIds(WEB_PROJECT_IDS);

			newWizardActionGroup.setContext(getContext());
			newWizardActionGroup.fillContextMenu(menu);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#fillImportMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillImportMenu(IMenuManager menu) {

		Object rootObject = getRootObject();
		if (rootObject != null) {
			boolean importClasses = false;
			if (rootObject instanceof Application)
				importWizardActionGroup.setWizardActionIds(EAR_IMPORT_IDS);
			else if (rootObject instanceof ApplicationClient) {
				importClasses = true;
				importWizardActionGroup.setWizardActionIds(APPCLIENT_IMPORT_IDS);
			} else if (rootObject instanceof Connector) {
				importClasses = true;
				importWizardActionGroup.setWizardActionIds(JCA_IMPORT_IDS);
			} else if (rootObject instanceof EJBJar) {
				importClasses = true;
				importWizardActionGroup.setWizardActionIds(EJB_IMPORT_IDS);
			} else if (rootObject instanceof WebApp) {
				importClasses = true;
				importWizardActionGroup.setWizardActionIds(WEB_IMPORT_IDS);
			}
			importWizardActionGroup.setContext(getContext());
			importWizardActionGroup.fillContextMenu(menu);
			if (importClasses)
				menu.add(importClassesAction);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#fillExportMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillExportMenu(IMenuManager menu) {

		Object rootObject = getRootObject();
		if (rootObject != null) {

			if (rootObject instanceof Application)
				exportWizardActionGroup.setWizardActionIds(EAR_EXPORT_IDS);
			else if (rootObject instanceof ApplicationClient)
				exportWizardActionGroup.setWizardActionIds(APPCLIENT_EXPORT_IDS);
			else if (rootObject instanceof Connector)
				exportWizardActionGroup.setWizardActionIds(JCA_EXPORT_IDS);
			else if (rootObject instanceof EJBJar)
				exportWizardActionGroup.setWizardActionIds(EJB_EXPORT_IDS);
			else if (rootObject instanceof WebApp)
				exportWizardActionGroup.setWizardActionIds(WEB_EXPORT_IDS);

			exportWizardActionGroup.setContext(getContext());
			exportWizardActionGroup.fillContextMenu(menu);

		}
	}

	/**
	 * @return
	 */
	protected Object getRootObject() {
		Object rootObject = null;
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object element = selection.getFirstElement();
		IProject project = (IProject) AdaptabilityUtility.getAdapter(element, IProject.class);
		if (project != null) {
			J2EERootObjectManager rootObjectManager = this.containingExtension.getRootObjectManager();
			rootObject = rootObjectManager.getRootObject(project);
		}
		return rootObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillEditContextMenu(IMenuManager menu) {
		super.fillEditContextMenu(menu);

		//addEditAction(menu, deleteEJBAction);
		/* addEditAction(menu, deleteModuleAction); */
		addEditAction(menu, renameAction);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#open()
	 */
	public void open() {
		runDefaultAction((IStructuredSelection) getContext().getSelection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#delete()
	 */
	public void delete() {
		delete((IStructuredSelection) getContext().getSelection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#delete()
	 */
	public void delete(IStructuredSelection selection) {
		/*
		 * // deleteEJBAction.selectionChanged(selection);
		 * deleteModuleAction.selectionChanged((ISelection)selection);
		 *  // if(deleteEJBAction.isEnabled()) // deleteEJBAction.run(); // else
		 * if(deleteModuleAction.isEnabled()) deleteModuleAction.run();
		 */
		/* else */
		Object[] array = selection.toArray();
		if (isUtilityJars(array)) {
			J2EEUtilityJarItemProvider utilityJarItemProvider = null;
			List newSelection = new ArrayList();
			for (int i = 0; i < array.length; i++) {
				utilityJarItemProvider = (J2EEUtilityJarItemProvider) array[i];
				newSelection.addAll(utilityJarItemProvider.getChildren(null));
			}
			selection = new StructuredSelection(newSelection);
			/*
			 * deleteResourceAction.selectionChanged(selection); deleteResourceAction.run();
			 */
		}
		genericDelete.selectionChanged(genericDelete, selection);
		if (genericDelete.isEnabled())
			genericDelete.run();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.actions.CommonEditActionGroup#handleKeyPressed(org.eclipse.swt.events.KeyEvent,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void handleKeyPressed(KeyEvent event, IStructuredSelection selection) {
		/*
		 * if (event.character == SWT.DEL && event.stateMask == 0) { delete(selection); }
		 */
	}

}
