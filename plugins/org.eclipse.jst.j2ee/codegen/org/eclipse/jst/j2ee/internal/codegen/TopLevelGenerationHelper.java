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
package org.eclipse.jst.j2ee.internal.codegen;



import java.util.ArrayList;
import java.util.List;

/**
 * A generation helper to hold other helpers. This helper is passed into the code generation. This
 * class can be extended to help various kinds of generation.
 */
public class TopLevelGenerationHelper extends GenerationHelper {
	private List fHelpers = null;
	private String fProjectName = null;
	private boolean fDeleteAll = false;

	/**
	 * TopLevelGenerationHelper default constructor.
	 */
	public TopLevelGenerationHelper() {
		super();
	}

	/**
	 * Adds the helper as a child of this top level helper. This does not set the child helper's
	 * parent reference. If bidirectional navigation is desired, the caller should set the parent
	 * reference.
	 * 
	 * @param helper
	 *            GenerationHelper
	 */
	public void append(GenerationHelper helper) {
		getChildHelpers().add(helper);
	}

	/**
	 * Returns the helpers appended to this helper.
	 */
	public List getChildHelpers() {
		if (fHelpers == null)
			fHelpers = new ArrayList();
		return fHelpers;
	}

	/**
	 * Returns the simple name of the target project.
	 */
	public String getProjectName() {
		return fProjectName;
	}

	/**
	 * Returns true if this generation is suppose to delete all the target resources.
	 * 
	 * @return boolean
	 */
	public boolean isDeleteAll() {
		return fDeleteAll;
	}

	/**
	 * Set this attribute to true if this generation is suppose to delete all the target resources.
	 * 
	 * @param newDeleteAll
	 *            boolean
	 */
	public void setDeleteAll(boolean newDeleteAll) {
		fDeleteAll = newDeleteAll;
	}

	/**
	 * Sets the simple name of the target project.
	 */
	public void setProjectName(String projectName) {
		fProjectName = projectName;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.helpers.GenerationHelper#isTopLevelHelper()
	 */
	public boolean isTopLevelHelper() {
		return true;
	}

}