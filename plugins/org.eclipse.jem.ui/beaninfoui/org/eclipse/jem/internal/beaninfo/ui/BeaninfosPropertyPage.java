package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfosPropertyPage.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jdt.internal.ui.util.ExceptionHandler;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.dialogs.PropertyPage;

import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

/**
 * Property page for configuring Beaninfo path
 */
public class BeaninfosPropertyPage extends PropertyPage implements IStatusChangeListener {
		
	private BeaninfoPathsBlock fBuildPathsBlock;
	
	/*
	 * @see PreferencePage#createControl(Composite)
	 */
	protected Control createContents(Composite parent) {
// when we get help working.
//		WorkbenchHelp.setHelp(parent, new DialogPageContextComputer(this, IJavaHelpContextIds.BUILD_PATH_PROPERTY_PAGE));

		// ensure the page has no special buttons
		noDefaultAndApplyButton();		
		
		IProject project= getProject();
		if (project == null || !isJavaProject(project)) {
			return createWithoutJava(parent);
		} else if (!project.isOpen()) {
			return createForClosedProject(parent);
		} else {
			return createWithJava(parent, project);
		}
	}
	
	/**
	 * Content for valid projects.
	 */
	private Control createWithJava(Composite parent, IProject project) {
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		fBuildPathsBlock= new BeaninfoPathsBlock(root, this);
		fBuildPathsBlock.init(JavaCore.create(project));
		return fBuildPathsBlock.createControl(parent);
	}

	/**
	 * Content for non-Java projects.
	 */	
	private Control createWithoutJava(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPP_NOJAVAPROJECT)); 
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}

	/**
	 * Content for closed projects.
	 */		
	private Control createForClosedProject(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPP_CLOSEDPROJECT));
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}
	
	private IProject getProject() {
		IAdaptable adaptable= getElement();
		if (adaptable != null) {
			IJavaElement elem= (IJavaElement) adaptable.getAdapter(IJavaElement.class);
			if (elem instanceof IJavaProject) {
				return ((IJavaProject) elem).getProject();
			}
		}
		return null;
	}
	
	private boolean isJavaProject(IProject proj) {
		try {
			return proj.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException e) {
			JEMUIPlugin.getPlugin().getLogger().log(e.getStatus());
		}
		return false;
	}	
	
	/*
	 * @see IPreferencePage#performOk
	 */
	public boolean performOk() {
		if (fBuildPathsBlock != null) {
			IRunnableWithProgress runnable= fBuildPathsBlock.getRunnable();

			IRunnableWithProgress op= new WorkspaceModifyDelegatingOperation(runnable);
			Shell shell= getControl().getShell();
			try {
				new ProgressMonitorDialog(shell).run(false, false, op);
			} catch (InvocationTargetException e) {
				String title= BeanInfoUIMessages.getString(BeanInfoUIMessages.BUI_ERRORTITLE);
				String message= BeanInfoUIMessages.getString(BeanInfoUIMessages.BUI_ERROR); 
				ExceptionHandler.handle(e, shell, title, message);
				return false;
			} catch (InterruptedException e) {
				// cancelled
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @see IStatusChangeListener#statusChanged
	 */
	public void statusChanged(IStatus status) {
		setValid(!status.matches(IStatus.ERROR));
		StatusUtil.applyToStatusLine(this, status);
	}

}