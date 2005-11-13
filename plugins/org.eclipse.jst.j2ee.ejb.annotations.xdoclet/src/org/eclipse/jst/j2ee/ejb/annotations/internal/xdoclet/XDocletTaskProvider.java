/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;

public interface XDocletTaskProvider {
	
	public abstract String getNamespace();

	public abstract String getTask();

	public abstract IStatus validate();

	public abstract IExtension getExtension();

	public abstract void setExtension(IExtension extension);

	public abstract Properties getProperties();

	public abstract void setProperties(Properties properties);

	public abstract void setPreferenceStore(XDocletPreferenceStore preferenceStore);

	public abstract XDocletPreferenceStore getPreferenceStore();

	public abstract IProject getClientProject();

	public abstract void setClientProject(IProject clientProject);

	public abstract IProject getProject();

	public abstract void setProject(IProject project);
}
