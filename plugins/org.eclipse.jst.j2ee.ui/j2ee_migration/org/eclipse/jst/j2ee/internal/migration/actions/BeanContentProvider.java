/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.migration.EJBJarMigrationConfig;


public class BeanContentProvider implements ITreeContentProvider {
	protected Map configsToChildren;

	/**
	 * Constructor for BeanContentProvider.
	 */
	public BeanContentProvider() {
		super();
	}

	public BeanContentProvider(Map configs) {
		super();
		configsToChildren = configs;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getChildren(Object inputElement) {
		if (inputElement instanceof List)
			return getFilteredConfigs((List) inputElement).toArray();
		else if (inputElement instanceof EJBJarMigrationConfig)
			return getChildren((EJBJarMigrationConfig) inputElement);
		return null;
	}

	/**
	 * Exclude the configs that don't have applicable children
	 */
	protected List getFilteredConfigs(List configs) {
		return EJBJarMigrationConfig.filterConfigsWithNoApplicableClientConfigs(configs);
	}

	public Object[] getChildren(EJBJarMigrationConfig config) {
		if (configsToChildren == null)
			return config.getApplicableChildren().toArray();
		return ((Collection) configsToChildren.get(config)).toArray();
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children != null && children.length != 0;
	}
}