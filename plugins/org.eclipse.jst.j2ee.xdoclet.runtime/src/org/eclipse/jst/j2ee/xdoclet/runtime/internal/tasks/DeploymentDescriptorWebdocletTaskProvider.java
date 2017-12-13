/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.xdoclet.runtime.internal.tasks;

import org.eclipse.jst.j2ee.xdoclet.runtime.internal.XDocletTaskProviderImpl;

public class DeploymentDescriptorWebdocletTaskProvider extends XDocletTaskProviderImpl {

	public static String NAMESPACE = "webdoclet.deploymentdescriptor.";

	public String getTask() {
		StringBuffer buffer = new StringBuffer(256);
		buffer.append("<deploymentdescriptor ");
		buildAttributes(buffer);
		buffer.append(" />");
		return buffer.toString();
	}

	public String getNamespace() {
		return NAMESPACE;
	}

	protected void addAttribute(String attribute, StringBuffer stringBuffer) {
		if ("clientjar".equals(attribute)) {
			if (getClientProject() != null) {
				super.addAttribute(attribute, stringBuffer);
			}
		} else {
			super.addAttribute(attribute, stringBuffer);
		}
	}
}
