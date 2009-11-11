/******************************************************************************
 * Copyright (c) 2009 Red Hat, IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - additional support
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.dialogs.ChangeLibDirDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.jee.project.facet.EarCreateDeploymentFilesDataModelProvider;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class EarModuleDependenciesPropertyPage extends
		AddModuleDependenciesPropertiesPage {
	protected Button changeEarLibDirButton;
	private String libDir = null;

	public EarModuleDependenciesPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	protected IDataModelOperation generateEARDDOperation() {
		IDataModel model = DataModelFactory.createDataModel(new EarCreateDeploymentFilesDataModelProvider());
		model.setProperty(ICreateDeploymentFilesDataModelProperties.GENERATE_DD, rootComponent);
		model.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, project);
		return model.getDefaultOperation();
	}
	
	@Override
	public boolean postHandleChanges(IProgressMonitor monitor) {
		return true;
	}
	
	@Override
	protected void handleRemoved(ArrayList<IVirtualComponent> removed) {
		super.handleRemoved(removed);
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(rootComponent.getProject());
	}

	@Override
	protected IDataModelProvider getAddReferenceDataModelProvider(IVirtualComponent component) {
		return new AddComponentToEnterpriseApplicationDataModelProvider();
	}

	@Override
	protected String getAddFolderLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_0;
	}

	@Override
	protected String getAddReferenceLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_1;
	}

	@Override
	protected String getEditReferenceLabel() {
		
		return Messages.EarModuleDependenciesPropertyPage_2;
	}

	@Override
	protected String getModuleAssemblyRootPageDescription() {
		
		return Messages.EarModuleDependenciesPropertyPage_3;
	}

	@Override
	protected IDataModelProvider getRemoveReferenceDataModelProvider(IVirtualComponent component) {
		
			return new RemoveComponentFromEnterpriseApplicationDataModelProvider();
		
	}
	
	protected void handleChangeLibDirButton(boolean warnBlank) {
		String libDirText;
		IVirtualFile vFile = rootComponent.getRootFolder().getFile(new Path(J2EEConstants.APPLICATION_DD_URI));
		if (!vFile.exists()) {
			if (!MessageDialog.openQuestion(null, 
					J2EEUIMessages.getResourceString(J2EEUIMessages.NO_DD_MSG_TITLE), 
					J2EEUIMessages.getResourceString(J2EEUIMessages.GEN_DD_QUESTION))) return;
			createDD(new NullProgressMonitor());
		}
		Application app = (Application)ModelProviderManager.getModelProvider(project).getModelObject();
		
		libDirText = app.getLibraryDirectory();
			if (libDirText == null) libDirText = J2EEConstants.EAR_DEFAULT_LIB_DIR;
		
		ChangeLibDirDialog dlg = new ChangeLibDirDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getShell(), libDirText, warnBlank);
		if (dlg.open() == Dialog.CANCEL) return;
		libDir = dlg.getValue().trim();
		if (libDir.length() > 0) {
			if (!libDir.startsWith(J2EEConstants.EAR_ROOT_DIR)) libDir = IPath.SEPARATOR + libDir;
		}
		refresh();
	}
	
	protected void createDD(IProgressMonitor monitor) {
		if( rootComponent != null ){
			IDataModelOperation op = generateEARDDOperation();
			try {
				op.execute(monitor, null);
			} catch (ExecutionException e) {
				J2EEUIPlugin.logError(e);
			}
		}
	}


	@Override
	protected void createPushButtons() {
		//Create core buttons first
		super.createPushButtons();
		if (JavaEEProjectUtilities.isJEEComponent(rootComponent))
			changeEarLibDirButton = createPushButton(getChangeEarLibDirLabel());
	}

	protected String getChangeEarLibDirLabel() {
		return Messages.EarModuleDependenciesPropertyPage_4;
	}

	@Override
	public void handleEvent(Event event) {
		
		super.handleEvent(event);
		if( event.widget == changeEarLibDirButton ) 
			handleChangeLibDirButton(true);
	}

	@Override
	public boolean performOk() {
		
		boolean result = super.performOk();
		updateLibDir();
		return result;
	}
	private void updateLibDir() {
		
		if (libDir == null) return;
		final IEARModelProvider earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(project);
		Application app = (Application)earModel.getModelObject();
		String oldLibDir = app.getLibraryDirectory();
		if ((oldLibDir != null && !oldLibDir.equals(libDir)) || oldLibDir == null) {
			earModel.modify(new Runnable() {
				public void run() {		
				Application app2 = (Application)earModel.getModelObject();
				app2.setLibraryDirectory(libDir);
			}}, null);
		}
	}

}
