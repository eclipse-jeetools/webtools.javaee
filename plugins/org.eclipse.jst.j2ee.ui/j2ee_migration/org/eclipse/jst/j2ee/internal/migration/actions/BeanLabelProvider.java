/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jst.j2ee.internal.ejb.provider.EjbItemProviderAdapterFactory;
import org.eclipse.jst.j2ee.internal.migration.EJBClientViewMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.EJBJarMigrationConfig;
import org.eclipse.swt.graphics.Image;


public class BeanLabelProvider implements ILabelProvider {
	protected AdapterFactoryLabelProvider delegate = new AdapterFactoryLabelProvider(new EjbItemProviderAdapterFactory());

	public Image getImage(Object element) {
		return delegate.getImage(getTarget(element));
	}

	protected Object getTarget(Object element) {
		if (element instanceof EJBJarMigrationConfig)
			return ((EJBJarMigrationConfig) element).getEJBJar();
		else if (element instanceof EJBClientViewMigrationConfig)
			return ((EJBClientViewMigrationConfig) element).getEjb();
		return null;
	}

	public String getText(Object element) {
		String text = delegate.getText(getTarget(element));
		return text == null ? "" : text; //$NON-NLS-1$
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public void dispose() {
		delegate.dispose();
	}

	public boolean isLabelProperty(Object element, String property) {
		return delegate.isLabelProperty(getTarget(element), property);
	}

	public void removeListener(ILabelProviderListener listener) {
	}
}

