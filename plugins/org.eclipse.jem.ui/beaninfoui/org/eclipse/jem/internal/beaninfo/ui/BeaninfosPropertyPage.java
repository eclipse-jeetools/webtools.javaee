/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BeaninfosPropertyPage.java,v $
 *  $Revision: 1.10 $  $Date: 2005/09/26 20:26:59 $ 
 */

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IMessageProvider;
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
		
	/**
	 * Applies the status to the status line of a dialog page.
	 *
	 * @param page the dialog page
	 * @param status the status
	 */
	public static void applyToStatusLine(DialogPage page, IStatus status) {
		String message= status.getMessage();
		switch (status.getSeverity()) {
			case IStatus.OK:
				page.setMessage(message, IMessageProvider.NONE);
				page.setErrorMessage(null);
				break;
			case IStatus.WARNING:
				page.setMessage(message, IMessageProvider.WARNING);
				page.setErrorMessage(null);
				break;
			case IStatus.INFO:
				page.setMessage(message, IMessageProvider.INFORMATION);
				page.setErrorMessage(null);
				break;
			default:
				if (message.length() == 0) {
					message= null;
				}
				page.setMessage(null);
				page.setErrorMessage(message);
				break;
		}
	}

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
		final IPath classpathfile = project.getFile(".classpath").getFullPath(); //$NON-NLS-1$
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
		label.setText(BeanInfoUIMessages.BeaninfoPropertiesPage_INFO__nojavaproject); 
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}

	/**
	 * Content for closed projects.
	 */		
	private Control createForClosedProject(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(BeanInfoUIMessages.BeaninfoPropertiesPage_INFO__closedproject); 
		
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
				String title= BeanInfoUIMessages.Beaninfo_UI__errortitle; 
				String message= BeanInfoUIMessages.Beaninfo_UI__error; 
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
		applyToStatusLine(this, status);
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
