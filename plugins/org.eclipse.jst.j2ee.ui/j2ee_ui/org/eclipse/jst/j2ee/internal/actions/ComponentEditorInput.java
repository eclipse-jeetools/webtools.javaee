/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class ComponentEditorInput implements IEditorInput {

	private IVirtualComponent component;
	
	public ComponentEditorInput(IVirtualComponent  component){
		this.component = component;
	}
	
	public boolean exists() {
		return component.exists();
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return component.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	public IVirtualComponent getComponent(){
		return component;
	}
	
}
