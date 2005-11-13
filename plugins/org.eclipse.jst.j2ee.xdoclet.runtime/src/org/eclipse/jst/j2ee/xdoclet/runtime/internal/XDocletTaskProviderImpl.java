/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.xdoclet.runtime.internal;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletTaskProvider;

public abstract class XDocletTaskProviderImpl implements XDocletTaskProvider {

	IExtension extension;
	Properties properties;
	XDocletPreferenceStore preferenceStore;
	IProject project;
	IProject clientProject;

	protected static String INCLUDE = ".include";

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getNamespace()
	 */
	public abstract String getNamespace();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getTask()
	 */
	public abstract String getTask();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#validate()
	 */
	public IStatus validate() {
		return Status.OK_STATUS;
	}

	protected void buildAttributes(StringBuffer stringBuffer) {

		String[] attributes = getAttributeNames();
		if (attributes == null)
			return;
		for (int i = 0; i < attributes.length; i++) {
			String attribute = attributes[i];
			boolean include = getPreferenceStore().getBooleanProperty(getNamespace() + attribute + INCLUDE);
			if (include) {
				addAttribute(attribute,stringBuffer);
			}
		}

	}

	protected void addAttribute(String attribute, StringBuffer stringBuffer) {
		stringBuffer.append(" " + attribute + "=\"");
		stringBuffer.append(getPreferenceStore().getProperty(getNamespace() + attribute).replaceAll("\\$","\\\\\\$"));
		stringBuffer.append('"');
	}

	protected String[] getAttributeNames() {
		IExtension taskConfig = getExtension();
		IConfigurationElement[] properties = taskConfig.getConfigurationElements();
		if (properties == null || properties.length == 1)
			return null;
		String attributeNames[] = new String[properties.length];
		for (int i = 1; i < properties.length; i++) {
			IConfigurationElement property = properties[i];
			attributeNames[i] = property.getAttribute("label");
		}
		return attributeNames;

	}

	
	public IExtension getExtension() {
		return extension;
	}

	
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getProperties()
	 */
	public Properties getProperties() {
		return properties;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#setPreferenceStore(org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore)
	 */
	public void setPreferenceStore(XDocletPreferenceStore preferenceStore) {
		this.preferenceStore = preferenceStore;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getPreferenceStore()
	 */
	public XDocletPreferenceStore getPreferenceStore() {
		return preferenceStore;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getClientProject()
	 */
	public IProject getClientProject() {
		return clientProject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#setClientProject(org.eclipse.core.resources.IProject)
	 */
	public void setClientProject(IProject clientProject) {
		this.clientProject = clientProject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getProject()
	 */
	public IProject getProject() {
		return project;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

}
