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
 * Created on Nov 4, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.common.jdt.internal.integration;

import java.util.ArrayList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JavaProjectCreationDataModel extends ProjectCreationDataModel {

	/**
	 * Optional, type String []. These names are relative.
	 */
	public static final String SOURCE_FOLDERS = "JavaProjectCreationDataModel.SOURCE_FOLDERS"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean default is True
	 */
	public static final String CREATE_SOURCE_FOLDERS = "JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS"; //$NON-NLS-1$


	/**
	 * Optional, type IClasspathEntry[]
	 */
	public static final String CLASSPATH_ENTRIES = "JavaProjectCreationDataModel.CLASSPATH_ENTRIES"; //$NON-NLS-1$

	/**
	 * Optional, type String
	 */
	public static final String OUTPUT_LOCATION = "JavaProjectCreationDataModel.OUTPUT_LOCATION"; //$NON-NLS-1$


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new JavaProjectCreationOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(OUTPUT_LOCATION);
		addValidBaseProperty(SOURCE_FOLDERS);
		addValidBaseProperty(CLASSPATH_ENTRIES);
		addValidBaseProperty(CREATE_SOURCE_FOLDERS);
	}

	protected Object getDefaultProperty(String propertyName) {
		// TODO pull these from the java preferences
		if (propertyName.equals(OUTPUT_LOCATION)) {
			return "bin"; //$NON-NLS-1$
		}
		if (propertyName.equals(SOURCE_FOLDERS)) {
			return new String[]{"src"}; //$NON-NLS-1$
		}
		if (propertyName.equals(CREATE_SOURCE_FOLDERS))
			return Boolean.TRUE;
		return super.getDefaultProperty(propertyName);
	}

	public IPath getOutputPath() {
		String outputLocation = getStringProperty(OUTPUT_LOCATION);
		return getProject().getFullPath().append(outputLocation);
	}

	public IClasspathEntry[] getClasspathEntries() {
		IClasspathEntry[] entries = (IClasspathEntry[]) getProperty(CLASSPATH_ENTRIES);
		IClasspathEntry[] sourceEntries = null;
		if (getBooleanProperty(CREATE_SOURCE_FOLDERS))
			sourceEntries = getSourceClasspathEntries();
		return combineArrays(sourceEntries, entries);
	}

	/**
	 * @param sourceEntries
	 * @param entries
	 * @return
	 */
	private IClasspathEntry[] combineArrays(IClasspathEntry[] sourceEntries, IClasspathEntry[] entries) {
		if (sourceEntries != null) {
			if (entries == null)
				return sourceEntries;
			return doCombineArrays(sourceEntries, entries);
		} else if (entries != null)
			return entries;
		return new IClasspathEntry[0];
	}

	/**
	 * @param sourceEntries
	 * @param entries
	 * @return
	 */
	private IClasspathEntry[] doCombineArrays(IClasspathEntry[] sourceEntries, IClasspathEntry[] entries) {
		IClasspathEntry[] result = new IClasspathEntry[sourceEntries.length + entries.length];
		System.arraycopy(sourceEntries, 0, result, 0, sourceEntries.length);
		System.arraycopy(entries, 0, result, sourceEntries.length, entries.length);
		return result;
	}

	private IClasspathEntry[] getSourceClasspathEntries() {
		String[] sourceFolders = (String[]) getProperty(SOURCE_FOLDERS);
		ArrayList list = new ArrayList();
		for (int i = 0; i < sourceFolders.length; i++) {
			list.add(JavaCore.newSourceEntry(getProject().getFullPath().append(sourceFolders[i])));
		}
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}
}