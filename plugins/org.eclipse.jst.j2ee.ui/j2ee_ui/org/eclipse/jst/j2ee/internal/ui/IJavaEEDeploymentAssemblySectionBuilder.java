/*******************************************************************************
 * Copyright (c) 2011, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage.ComponentResourceProxy;

public interface IJavaEEDeploymentAssemblySectionBuilder {

	/**
	 * Builds the section
	 * @param parent - The composite to which this section will be added
	 */
	public void buildSection(Composite parent);

	public void loadContents();

	public boolean saveContents();
	
	public void directiveAdded(Object element);
	
	public void directiveRemoved(Object element);
	
	public IStatus validate(IStatus currentStatus);

	public void componentResourceModified(ComponentResourceProxy originalResource, ComponentResourceProxy modifiedResource);
	

}