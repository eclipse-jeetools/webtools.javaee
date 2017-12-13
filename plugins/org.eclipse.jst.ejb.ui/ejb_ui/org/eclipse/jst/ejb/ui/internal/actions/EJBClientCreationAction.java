/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.ejb.ui.internal.extension.EJBClientActionRegistryReader;
import org.eclipse.jst.ejb.ui.internal.extension.IEJBClientActionExtender;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.ejb.ui.internal.wizard.EJBClientComponentCreationWizard;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientJarCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientJarCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.provider.GroupedEJBItemProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.EJBUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;



public class EJBClientCreationAction extends AbstractClientJARAction {
	
	public static String LABEL = Platform.getResourceString(
				Platform.getBundle(EJBUIPlugin.PLUGIN_ID),
				"%ejb.client.jar.creation.action.description_ui_"); //$NON-NLS-1$
	

	public EJBClientCreationAction() {
		super();
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("ejbclientjar_wiz")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2ee.common.actions.BaseAction#primRun(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void primRun(Shell shell) {
		if(getSelectedProject() == null) return;
		if(!checkBinaryProject(shell) || !checkEARProject(shell))
			return;
		
		IDataModel dm = DataModelFactory.createDataModel(new EjbClientJarCreationDataModelProvider());
		dm.setProperty(IEjbClientJarCreationDataModelProperties.EJB_PROJECT_NAME,
				getSelectedProject().getName() );
		
		EJBClientComponentCreationWizard wizard = new EJBClientComponentCreationWizard(dm);
		J2EEUIPlugin plugin = J2EEUIPlugin.getDefault();
		wizard.setDialogSettings(plugin.getDialogSettings());

		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.getShell().setSize(500, 550);
		dialog.open(); 
	}

    private boolean checkEARProject(Shell shell) {
        if (!hasEARProject()) {
            // if this is an EJB project that is not in an EAR project, check to see if an extension allows for its creation
        	IProject project = getSelectedProject();
            
        	List<IEJBClientActionExtender> ejbClientCreationExtensions = EJBClientActionRegistryReader.getInstance().getEJBClientActionExtenders();
			Iterator<IEJBClientActionExtender> i = ejbClientCreationExtensions.iterator();
			IEJBClientActionExtender current = null;
			int wizardRet;
			boolean done = false;
			// iterate through the extenders.  If the first way to create the EJB Client jar is not correct, the user can press cancel and the next wizard will be created.
			while (i.hasNext() && !done)
			{
				current = i.next();
				if (current.allowEJBClientCreation(project))
				{
					// if the extension allows its creation, then launch whatever wizard is applicable
					wizardRet = current.showEJBClientCreationWizard(shell, project);
					if (wizardRet == Window.OK)
					{
						done = true;
					}
				}
			}

			// if no extenders can create an EJB Client project, display the usual error message
			if (!done)
			{
				MessageDialog.openError(shell, EJBCreationResourceHandler.EJB_Client_JAR_Creation_Error_, EJBCreationResourceHandler.Cannot_Be_StandAlone_Project_For_Client_);
			}
            return false;
        }
        return true;		
    }

    private boolean checkBinaryProject(Shell shell) {
        if (isBinaryProject()) {
            MessageDialog.openError(shell, EJBCreationResourceHandler.EJB_Client_JAR_Creation_Error_, EJBCreationResourceHandler.Cannot_Be_Binary_Project_For_Client_); 
            return false;
        }
        return true;		
    }
    
    private boolean hasEARProject() {
        IProject project = getSelectedProject();
        return !EarUtilities.isStandaloneProject(project);
    }


    private boolean isBinaryProject() {
		IProject project = getSelectedProject();
		return JemProjectUtilities.isBinaryProject(project);
    }

    /**
     * @return
     */
    private IProject getSelectedProject() {
        if (selection.getFirstElement() instanceof EJBJar) {
            EJBJar selProject = (EJBJar) selection.getFirstElement();
            return ProjectUtilities.getProject(selProject);
        } else if (selection.getFirstElement() instanceof IProject) {
            return (IProject) selection.getFirstElement();
        } else if (selection.getFirstElement() instanceof IJavaProject) {
            return ((IJavaProject) selection.getFirstElement()).getProject();
        } else if(selection.getFirstElement() instanceof GroupedEJBItemProvider) {
        	if(((GroupedEJBItemProvider)selection.getFirstElement()).getParent() instanceof EJBJar) {
        		EJBJar jar = (EJBJar) ((GroupedEJBItemProvider)selection.getFirstElement()).getParent();
        		return ProjectUtilities.getProject(jar);
        	}
        }
        return null;
    }

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		if (EJBUtilities.hasEJBClientJARProject(getProject()))
			action.setEnabled(false);
		else 
			action.setEnabled(true);
	}
}
