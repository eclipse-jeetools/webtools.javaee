/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SelectDefaultConfigurationActionDelegate.java,v $
 *  $Revision: 1.3 $  $Date: 2004/06/02 19:59:58 $ 
 */
package org.eclipse.jem.internal.ui.proxy;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.*;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.ui.core.JEMUIPlugin;
 
/**
 * 
 * @since 1.0.0
 */
public class SelectDefaultConfigurationActionDelegate extends Action implements IWorkbenchWindowActionDelegate, IObjectActionDelegate {
	

	/**
	 * Helper to get single selected java project out of selection.
	 * Used by ProxyLaunchToolbarDelegate too.
	 * 
	 * @param selection
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected static IJavaProject getSelectedJavaProject(ISelection selection) {
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection) || ((IStructuredSelection) selection).size() > 1)
			return null;	// Can't handle it.
		else {
			Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (sel instanceof IProject && ((IProject) sel).isOpen()) {
				return JavaCore.create((IProject) sel);	// Will return null if not exist.
			} else if (sel instanceof IJavaProject && ((IJavaProject) sel).isOpen())
				return (IJavaProject) sel;
			else
				return null;
		}
	}
	
	/**
	 * 
	 * 
	 * @since 1.0.0
	 */
	public SelectDefaultConfigurationActionDelegate() {
		super(Platform.getResourceString(JEMUIPlugin.getPlugin().getBundle(), "%Action.selectDefault")); //$NON-NLS-1$
		setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

	private IWorkbenchWindow window;
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run()
	 */
	public void run() {
		selectDialog();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}	

	protected IJavaProject javaproject;
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		javaproject = getSelectedJavaProject(selection);
		try {
			setEnabled(
				javaproject != null
					&& javaproject.getProject().getPersistentProperty(ProxyLaunchSupport.PROPERTY_LAUNCH_CONFIGURATION) != null);
		} catch (Exception e) {
			setEnabled(false);	// Some error, so not enabled.
		}
		if (action != null)			
			action.setEnabled(isEnabled());
		if (javaproject != null)
			setToolTipText(MessageFormat.format(Platform.getResourceString(JEMUIPlugin.getPlugin().getBundle(), "%Action.selectDefaultTip"), new Object[] {javaproject.getElementName()})); //$NON-NLS-1$
		else
			setToolTipText(getText());
	}
	
	private static final Object[] EMPTY = new Object[0];
	protected void selectDialog() {
		try {
			ILabelProvider labelProvider = DebugUITools.newDebugModelPresentation();
			
			CheckedTreeSelectionDialog dialog = new CheckedTreeSelectionDialog(window.getShell(), labelProvider, new ITreeContentProvider() {
				public Object[] getChildren(Object parentElement) {
					return EMPTY;
				}

				public Object getParent(Object element) {
					return null;
				}

				public boolean hasChildren(Object element) {
					return false;
				}

				public Object[] getElements(Object inputElement) {
					if (inputElement != null && inputElement instanceof Object[])
						return (Object[]) inputElement;
					else 
						return null;
				}

				public void dispose() {
				}

				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				}
			}) {
				protected CheckboxTreeViewer createTreeViewer(Composite parent) {
					final CheckboxTreeViewer treeViewer = super.createTreeViewer(parent);
					treeViewer.addCheckStateListener(new ICheckStateListener() {
						boolean processingCheck = false;
						public void checkStateChanged(CheckStateChangedEvent event) {
							if (!processingCheck) {
								try {
									processingCheck = true;
									if (event.getChecked()) {
										// We are checking something, make sure old one unchecked.
										Object[] checked = treeViewer.getCheckedElements();
										for (int i = 0; i < checked.length; i++) {
											if (checked[i] != event.getElement())
												treeViewer.setChecked(checked[i], false);
										}
									}
								} finally {
									processingCheck = false;
								}
							}

						}
					});
					return treeViewer;
				}
				
				protected Composite createSelectionButtons(Composite composite) {
					return new Composite(composite, SWT.NONE);	// We don't want selection buttons since only one can be selected at a time.
				}
			};
			dialog.setTitle(ProxyUIMessages.getString("Select.title")); //$NON-NLS-1$	
			dialog.setMessage(MessageFormat.format(ProxyUIMessages.getString("Select.message"), new Object[] { javaproject.getElementName()})); //$NON-NLS-1$			

			ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
			List configsList = new ArrayList(configs.length+1);
			String jpName = javaproject.getElementName();
			for (int i = 0; i < configs.length; i++) {
				if (IProxyConstants.ID_PROXY_LAUNCH_GROUP.equals(configs[i].getCategory()) && !configs[i].getAttribute(IDebugUIConstants.ATTR_PRIVATE, false) && jpName.equals(configs[i].getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "")))
					configsList.add(configs[i]);
			}
			configs = (ILaunchConfiguration[]) configsList.toArray(new ILaunchConfiguration[configsList.size()]);
			dialog.setInput(configs);

			String launchName = javaproject.getProject().getPersistentProperty(ProxyLaunchSupport.PROPERTY_LAUNCH_CONFIGURATION);
			ILaunchConfiguration config = null;
			if (launchName != null) {
				for (int i = 0; i < configs.length; i++) {
					if (configs[i].getName().equals(launchName)) {
						config = configs[i];
						break;
					}
				}
				if (config != null) {
					dialog.setInitialSelections(new Object[] { config });
				}
			}
			if (dialog.open() == Window.OK) {
				config = (ILaunchConfiguration) dialog.getFirstResult();
				if (config != null)
					javaproject.getProject().setPersistentProperty(ProxyLaunchSupport.PROPERTY_LAUNCH_CONFIGURATION, config.getName());
				else
					javaproject.getProject().setPersistentProperty(ProxyLaunchSupport.PROPERTY_LAUNCH_CONFIGURATION, ProxyLaunchSupport.NOT_SET);
			} 
			
		} catch (CoreException e) {
			ErrorDialog.openError(window.getShell(), null, null, e.getStatus());
			ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
		}					
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// This is called instead of init(WorkbenchWindow) when this is on popup menu.
		window = targetPart.getSite().getWorkbenchWindow();
	}

}
