/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 13, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateManifestDataModelProvider extends AbstractDataModelProvider implements UpdateManifestDataModelProperties {

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(JAR_LIST);
		propertyNames.add(JAR_LIST_TEXT_UI);
		propertyNames.add(MERGE);
		propertyNames.add(MAIN_CLASS);
		propertyNames.add(MANIFEST_FILE);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MERGE)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(JAR_LIST)) {
			return new ArrayList();
		} else if (propertyName.equals(JAR_LIST_TEXT_UI)) {
			return getClasspathAsString();
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(JAR_LIST) && isPropertySet(JAR_LIST_TEXT_UI))
			setProperty(JAR_LIST_TEXT_UI, getClasspathAsString());
		return set;
	}

	public IProject getProject() {
		String projectName = (String) getProperty(PROJECT_NAME);
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	public String getClasspathAsString() {
		List classpathList = (List) getProperty(JAR_LIST);
		return convertClasspathListToString(classpathList);
	}

	public static String convertClasspathListToString(List list) {
		String classpathString = ""; //$NON-NLS-1$
		for (int i = 0; i < list.size(); i++) {
			classpathString += ((String) list.get(i)) + " "; //$NON-NLS-1$
		}
		return classpathString.trim();
	}

	public static List convertClasspathStringToList(String string) {
		List list = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(string, " "); //$NON-NLS-1$
		while (tokenizer.hasMoreTokens()) {
			list.add(tokenizer.nextToken());
		}
		return list;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new UpdateManifestOperation(model);
	}
}