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
package org.eclipse.jst.j2ee.application.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateManifestDataModel extends WTPOperationDataModel {

	/**
	 * Project name with manifest to update, type String required.
	 */
	public static final String PROJECT_NAME = "UpdateManifestDataModel.PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * java.util.List of Strings
	 */
	public static final String JAR_LIST = "UpdateManifestDataModel.CLASSPATH_LIST"; //$NON-NLS-1$

	/**
	 * String. This is build from the JAR_LIST property. Never set this property.
	 */
	public static final String JAR_LIST_TEXT_UI = "UpdateManifestDataModel.CLASSPATH_LIST_TEXT_UI"; //$NON-NLS-1$

	/**
	 * Boolean, true merges, false replaces, default is true
	 */
	public static final String MERGE = "UpdateManifestDataModel.MERGE"; //$NON-NLS-1$

	/**
	 * String, no default.
	 */
	public static final String MAIN_CLASS = "UpdateManifestDataModel.MAIN_CLASS"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new UpdateManifestOperation(this);
	}

	protected void init() {
		super.init();
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(JAR_LIST);
		addValidBaseProperty(JAR_LIST_TEXT_UI);
		addValidBaseProperty(MERGE);
		addValidBaseProperty(MAIN_CLASS);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MERGE)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(JAR_LIST)) {
			return new ArrayList();
		} else if (propertyName.equals(JAR_LIST_TEXT_UI)) {
			return getClasspathAsString();
		}
		return super.getDefaultProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getPropertyName().equals(JAR_LIST)) {
			String text = getClasspathAsString();
			propertyChanged(new WTPOperationDataModelEvent(this, JAR_LIST_TEXT_UI, event.getFlag()));
		}
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
}