/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BeaninfosPropertyPage.java,v $
 *  $Revision: 1.3.2.1 $  $Date: 2004/06/24 18:17:14 $ 
 */

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jdt.internal.ui.util.ExceptionHandler;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.dialogs.PropertyPage;

import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

/**
 * Property page for configuring Beaninfo path
 */
public class BeaninfosPropertyPage extends PropertyPage implements IStatusChangeListener {
		
	private BeaninfoPathsBlock fBuildPathsBlock;
	private IResourceChangeListener listener;
	private IProject project;
	
	/*
	 * @see PreferencePage#createControl(Composite)
	 */
	protected Control createContents(Composite parent) {
// when we get help working.
//		WorkbenchHelp.setHelp(parent, new DialogPageContextComputer(this, IJavaHelpContextIds.BUILD_PATH_PROPERTY_PAGE));

		// ensure the page has no special buttons
		noDefaultAndApplyButton();		
		
		project= getProject();
		if (project == null || !isJavaProject(project)) {
			return createWithoutJava(parent);
		} else if (!project.isOpen()) {
			return createForClosedProject(parent);
		} else {
			return createWithJava(parent);
		}
	}
	
	/**
	 * Content for valid projects.
	 */
	private Control createWithJava(Composite parent) {
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		fBuildPathsBlock= new BeaninfoPathsBlock(root, this);
		final IJavaProject jproject = JavaCore.create(project);
		fBuildPathsBlock.init(jproject);
		final IPath classpathfile = project.getFile(".classpath").getFullPath();
		listener = new IResourceChangeListener() {
			
			/* (non-Javadoc)
			 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
			 */
			public void resourceChanged(IResourceChangeEvent event) {
				if (fBuildPathsBlock != null) {
					if (event.getDelta().findMember(classpathfile) != null)
						getControl().getDisplay().asyncExec(new Runnable() {	// Can be called outside of display loop
							public void run() {
								fBuildPathsBlock.init(jproject);
							}
						});
				}
			}
		};
		project.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.POST_CHANGE);

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
				PlatformUI.getWorkbench().getProgressService().run(false, false, op);
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


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#dispose()
	 */
	public void dispose() {
		if (listener != null)
			project.getWorkspace().removeResourceChangeListener(listener);
		listener = null;
		super.dispose();
	}
}
