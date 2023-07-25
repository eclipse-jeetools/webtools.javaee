/*******************************************************************************
 * Copyright (c)2006, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.ui.IActionDelegate;

public class ForceClasspathUpdateAction extends Action implements IActionDelegate{

	private List projectsList = new ArrayList();
	
	@Override
	public void run() {
		J2EEComponentClasspathUpdater.getInstance().forceUpdate(projectsList);
	}

	@Override
	public void run(IAction action) {
		J2EEComponentClasspathUpdater.getInstance().forceUpdate(projectsList);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(setSelection(selection));
	}
	
	private boolean setSelection(ISelection selection){
		projectsList.clear();
		if(selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			if (structuredSelection.size() > 0) {
	            Iterator iterator = structuredSelection.iterator();
	            while(iterator.hasNext()){
		            Object next = iterator.next();
		            if (next instanceof IProject) {
		            	projectsList.add(next);
		            } else {
		            	projectsList.clear();
		            	return false;
		            }
	            }
	        } else { // empty selection
	        	return false;
	        }
	        return true;
		}
		return false;
	}
	
	
	
}
