/*******************************************************************************
 * Copyright (c) 2009, 2023 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;

public class DDAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IJavaElement.class) {
			if (adaptableObject instanceof AbstractDDNode) {
				AbstractDDNode ddNode = (AbstractDDNode) adaptableObject;
				Object node = ddNode.getAdapterNode();
				if (node instanceof IJavaElement) 
					return node;
				if (node instanceof IAdaptable) 
					return ((IAdaptable) node).getAdapter(IJavaElement.class);
					
				return Platform.getAdapterManager().getAdapter(node, IJavaElement.class);
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { IJavaElement.class };
	}

}
