/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	
	@Override
	public boolean exists() {
		return component.exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return component.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	public IVirtualComponent getComponent(){
		return component;
	}
	
}
