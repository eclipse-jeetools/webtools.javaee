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
 *  $RCSfile: ProxyLaunchToolbarDelegate.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */
package org.eclipse.jem.internal.ui.proxy;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowPulldownDelegate2;

import org.eclipse.jem.internal.ui.core.JEMUIPlugin;
 
/**
 * The toolbar delegate for proxy launch.
 * @since 1.0.0
 */
public class ProxyLaunchToolbarDelegate implements IWorkbenchWindowPulldownDelegate2 {

	private Menu menu;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowPulldownDelegate2#getMenu(org.eclipse.swt.widgets.Menu)
	 */
	public Menu getMenu(Menu parent) {
		setMenu(new Menu(parent));
		fillMenu(menu);
		return menu;
	}
	
	private void setMenu(Menu menu) {
		if (this.menu != null) {
			this.menu.dispose();
		}
		this.menu = menu;
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowPulldownDelegate#getMenu(org.eclipse.swt.widgets.Control)
	 */
	public Menu getMenu(Control parent) {
		setMenu(new Menu(parent));
		fillMenu(menu);
		return menu;
	}
	
	private void fillMenu(Menu menu) {
		addToMenu(menu, proxyLaunchDelegate, -1);	
		addToMenu(menu, selectDelegate, -1);
	}
	
	/**
	 * Adds the given action to the specified menu with an accelerator specified
	 * by the given number.
	 * 
	 * @param menu the menu to add the action to
	 * @param action the action to add
	 * @param accelerator the number that should appear as an accelerator
	 */
	protected void addToMenu(Menu menu, IAction action, int accelerator) {
		StringBuffer label= new StringBuffer();
		if (accelerator >= 0 && accelerator < 10) {
			//add the numerical accelerator
			label.append('&');
			label.append(accelerator);
			label.append(' ');
		}
		label.append(action.getText());
		action.setText(label.toString());
		ActionContributionItem item= new ActionContributionItem(action);
		item.fill(menu, -1);
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
		setMenu(null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		selectDelegate.init(window);
	}

	protected SelectDefaultConfigurationActionDelegate selectDelegate = new SelectDefaultConfigurationActionDelegate();
	protected ProxyLaunchMenuDelegate proxyLaunchDelegate = new ProxyLaunchMenuDelegate();
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (selectDelegate.isEnabled())
			selectDelegate.run(action);
		else
			proxyLaunchDelegate.run(action);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		selectDelegate.selectionChanged(null, selection);
		if (selectDelegate.isEnabled())
			action.setToolTipText(selectDelegate.getToolTipText());
		else
			action.setToolTipText(JEMUIPlugin.getPlugin().getDescriptor().getResourceString("%Action.proxyLaunchTip"));
			
	}

}
