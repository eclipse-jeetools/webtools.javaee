/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.ejb.ui.internal.wizard.EJBClientComponentCreationWizard;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientJarCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientJarCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.provider.GroupedEJBItemProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
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
            MessageDialog.openError(shell, EJBCreationResourceHandler.EJB_Client_JAR_Creation_Error_, EJBCreationResourceHandler.Cannot_Be_StandAlone_Project_For_Client_); 
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
        return !J2EEProjectUtilities.isStandaloneProject(project);
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
        } else if (selection.getFirstElement() instanceof JavaProject) {
            return ((JavaProject) selection.getFirstElement()).getProject();
        } else if(selection.getFirstElement() instanceof GroupedEJBItemProvider) {
        	if(((GroupedEJBItemProvider)selection.getFirstElement()).getParent() instanceof EJBJar) {
        		EJBJar jar = (EJBJar) ((GroupedEJBItemProvider)selection.getFirstElement()).getParent();
        		return ProjectUtilities.getProject(jar);
        	}
        }
        return null;
    }

	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		if( hasClientJar() )
			action.setEnabled( false );
	}
}
