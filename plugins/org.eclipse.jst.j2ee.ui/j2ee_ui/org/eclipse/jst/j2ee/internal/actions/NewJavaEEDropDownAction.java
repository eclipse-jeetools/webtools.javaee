/*******************************************************************************
 * Copyright (c) 2007,2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowPulldownDelegate2;
import org.eclipse.ui.PlatformUI;

public abstract class NewJavaEEDropDownAction extends Action implements IMenuCreator, IWorkbenchWindowPulldownDelegate2 {

	public static class NewJavaEEWizardAction extends Action implements Comparable {

		private final static String ATT_NAME = "name";//$NON-NLS-1$
		private final static String ATT_CLASS = "class";//$NON-NLS-1$
		private final static String ATT_ICON = "icon";//$NON-NLS-1$
		private final static String TAG_DESCRIPTION = "description";	//$NON-NLS-1$

		private Shell fShell;
		private IStructuredSelection fSelection;
		private IConfigurationElement fConfigurationElement;
		
		private int menuIndex;

		public NewJavaEEWizardAction(IConfigurationElement element) {
			fConfigurationElement= element;
			setText(element.getAttribute(ATT_NAME));
			
			String description = getDescriptionFromConfig(fConfigurationElement);
			setDescription(description);
			setToolTipText(description);
			setImageDescriptor(getIconFromConfig(fConfigurationElement));
			setMenuIndex(getMenuIndexFromConfig(fConfigurationElement));
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			Shell shell = getShell();
			try {
				INewWizard wizard = createWizard();
				wizard.init(PlatformUI.getWorkbench(), getSelection());
				
				WizardDialog dialog = new WizardDialog(shell, wizard);
				PixelConverter converter = new PixelConverter(JFaceResources.getDialogFont());
				dialog.setMinimumPageSize(converter.convertWidthInCharsToPixels(70), converter.convertHeightInCharsToPixels(20));
				dialog.create();
				int res = dialog.open();
				
				notifyResult(res == Window.OK);
			} catch (CoreException e) {
				Logger.getLogger().log(e);
			}
		}
		
		/**
		 * Returns the configured selection. If no selection has been configured using {@link #setSelection(IStructuredSelection)},
		 * the currently selected element of the active workbench is returned.
		 * @return the configured selection
		 */
		protected IStructuredSelection getSelection() {
			if (fSelection == null) {
				return evaluateCurrentSelection();
			}
			return fSelection;
		}
		
		private IStructuredSelection evaluateCurrentSelection() {
			IWorkbenchWindow window = J2EEUIPlugin.getActiveWorkbenchWindow();
			if (window != null) {
				ISelection selection = window.getSelectionService().getSelection();
				if (selection instanceof IStructuredSelection) {
					return (IStructuredSelection) selection;
				}
			}
			return StructuredSelection.EMPTY;
		}
		
		/**
		 * Configures the selection to be used as initial selection of the wizard. 
		 * @param selection the selection to be set or <code>null</code> to use the selection of the active workbench window
		 */
		public void setSelection(IStructuredSelection selection) {
			fSelection = selection;
		}
		
		/**
		 * Returns the configured shell. If no shell has been configured using {@link #setShell(Shell)},
		 * 	the shell of the currently active workbench is returned.
		 * @return the configured shell
		 */
		protected Shell getShell() {
			if (fShell == null) {
				return J2EEUIPlugin.getActiveWorkbenchShell();
			}
			return fShell;
		}
		
		/**
		 * Configures the shell to be used as parent shell by the wizard.
		 * @param shell the shell to be set or <code>null</code> to use the shell of the active workbench window
		 */
		public void setShell(Shell shell) {
			fShell = shell;
		}
		
		private String getDescriptionFromConfig(IConfigurationElement config) {
			IConfigurationElement [] children = config.getChildren(TAG_DESCRIPTION);
			if (children.length >= 1) {
				return children[0].getValue();
			}
			return ""; //$NON-NLS-1$
		}

		private ImageDescriptor getIconFromConfig(IConfigurationElement config) {
			String iconName = config.getAttribute(ATT_ICON);
			if (iconName != null) {
				return J2EEUIPlugin.imageDescriptorFromPlugin(config.getContributor().getName(), iconName);
			}
			return null;
		}
		
		private int getMenuIndexFromConfig(IConfigurationElement config) {
			IConfigurationElement[] classElements = config.getChildren(TAG_CLASS);
			if (classElements.length > 0) {
				for (IConfigurationElement classElement : classElements) {
					IConfigurationElement[] paramElements = classElement.getChildren(TAG_PARAMETER);
					for (IConfigurationElement paramElement : paramElements) {
						if (ATT_MENUINDEX.equals(paramElement.getAttribute(TAG_NAME))) {
							return Integer.parseInt(paramElement.getAttribute(TAG_VALUE));
						}
					}
				}
			}
			return Integer.MAX_VALUE;
		}
		
		protected INewWizard createWizard() throws CoreException {
			return (INewWizard) CoreUtility.createExtension(fConfigurationElement, ATT_CLASS);
		}

		public int getMenuIndex() {
			return menuIndex;
		}

		public void setMenuIndex(int menuIndex) {
			this.menuIndex = menuIndex;
		}

		@Override
		public int compareTo(Object o) {
			NewJavaEEWizardAction action = (NewJavaEEWizardAction) o;
			return getMenuIndex() - action.getMenuIndex();
		}
	}
	
	protected final static String TAG_WIZARD = "wizard";//$NON-NLS-1$
	protected final static String TAG_PARAMETER = "parameter";//$NON-NLS-1$
	protected final static String TAG_NAME = "name";//$NON-NLS-1$
	protected final static String TAG_VALUE = "value";//$NON-NLS-1$
	protected final static String TAG_CLASS = "class"; //$NON-NLS-1$
	protected final static String ATT_MENUINDEX = "menuIndex";//$NON-NLS-1$
	protected final static String PL_NEW = "newWizards"; //$NON-NLS-1$
	
	protected Menu fMenu;
	
	protected Shell fWizardShell;
	
	public NewJavaEEDropDownAction() {
		fMenu = null;
		setMenuCreator(this);
	}

	@Override
	public void dispose() {
		if (fMenu != null) {
			fMenu.dispose();
			fMenu = null;
		}
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}

	@Override
	public Menu getMenu(Control parent) {
		if (fMenu == null) {
			fMenu = new Menu(parent);
			NewJavaEEWizardAction[] actions = getActionFromDescriptors();
			for (NewJavaEEWizardAction action : actions) {
				action.setShell(fWizardShell);
				ActionContributionItem item = new ActionContributionItem(action);
				item.fill(fMenu, -1);
			}			
		}
		return fMenu;
	}
	
	@Override
	public void run() {
		getDefaultAction().run();
	}
	
	public Action getDefaultAction() {
		Action[] actions = getActionFromDescriptors();
		if (actions.length > 0)
			return actions[0];
		return null;
	}
	
	public NewJavaEEWizardAction[] getActionFromDescriptors() {
		ArrayList<NewJavaEEWizardAction> containers = new ArrayList<NewJavaEEWizardAction>();
		
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, PL_NEW);
		if (extensionPoint != null) {
			IConfigurationElement[] elements = extensionPoint.getConfigurationElements();
			for (IConfigurationElement element : elements) {
				if (element.getName().equals(TAG_WIZARD) && isJavaEEProjectWizard(element) && !isFiltered(element)) {
					containers.add(new NewJavaEEWizardAction(element));
				}
			}
		}
		
		NewJavaEEWizardAction[] actions = containers.toArray(new NewJavaEEWizardAction[containers.size()]);
		Arrays.sort(actions);
		return actions; 
	}
		
	private boolean isFiltered(IConfigurationElement element) {
		//This checks if a filter is registered, and will skip creating an action if found
		String id = element.getAttribute("id"); //$NON-NLS-1$
		return NewJavaEEActionFilterExtensionPoint.hasFilter(id);
		
	}

	protected boolean isJavaEEProjectWizard(IConfigurationElement element) {
		IConfigurationElement[] classElements = element.getChildren(TAG_CLASS);
		if (classElements.length > 0) {
			for (IConfigurationElement classElement : classElements) {
				IConfigurationElement[] paramElements = classElement.getChildren(TAG_PARAMETER);
				for (IConfigurationElement paramElement : paramElements) {
					if (getTypeAttribute().equals(paramElement.getAttribute(TAG_NAME))) {
						return Boolean.valueOf(paramElement.getAttribute(TAG_VALUE)).booleanValue();
					}
				}
			}
		}
		// old way, deprecated
		if (Boolean.valueOf(element.getAttribute(getTypeAttribute())).booleanValue()) {
			return true;
		}
		return false;
	}
		
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		fWizardShell = window.getShell();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		run();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}
	
	protected abstract String getTypeAttribute();

}
