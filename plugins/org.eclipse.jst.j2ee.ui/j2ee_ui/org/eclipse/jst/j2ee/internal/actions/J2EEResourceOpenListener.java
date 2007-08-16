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
/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class J2EEResourceOpenListener implements IOpenListener{

	 private OpenJ2EEResourceAction action;
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IOpenListener#open(org.eclipse.jface.viewers.OpenEvent)
	 */
	
	public void open(OpenEvent anEvent) {
		ISelection selection = anEvent.getSelection();
		if (selection instanceof IStructuredSelection) {
		   
		    if (getAction().updateSelection((IStructuredSelection)selection))
		    	action.run();
		}
		
	}

	/**
	 * @return
	 */
	private OpenJ2EEResourceAction getAction() {
		if (action == null) 
			action = new OpenJ2EEResourceAction();
		return action;
	}

}
