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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;



import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.emf.utilities.CopyGroup;

import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Insert the type's description here. Creation date: (1/3/2001 10:34:42 AM)
 * 
 * @author: Administrator
 */
public class EJBProjectSaveStrategyImpl extends org.eclipse.jst.j2ee.internal.archive.operations.J2EESaveStrategyImpl {

	protected EJBEditModel editModel;
	protected CopyGroup ejbCopyGroup;

	protected boolean noMapSchemaImportFlag = false;

	/**
	 * WorkbenchSaveStrategyImpl constructor comment.
	 */
	public EJBProjectSaveStrategyImpl(IProject aProject) {
		super(aProject);
		initializeMofResourceURIList();
	}

	/**
	 * Insert the method's description here. Creation date: (9/7/2001 2:18:35 PM)
	 * 
	 * @return com.ibm.eclipse.ejb.archiveui.EJBImportCopyGroup
	 */
	public CopyGroup getEjbCopyGroup() {
		return ejbCopyGroup;
	}

	/**
	 * save method comment.
	 */
	public WorkbenchURIConverter getSourceURIConverter() {
		if (sourceURIConverter == null && project != null) {
			EJBNatureRuntime enr = EJBNatureRuntime.getRuntime(project);
			sourceURIConverter = new WorkbenchURIConverterImpl(enr.getSourceFolder());
			sourceURIConverter.setForceSaveRelative(true);
		}
		return sourceURIConverter;
	}

	/**
	 * save method comment.
	 */
	public void initializeMofResourceURIList() {

		if (mofResourceURIList == null) {
			mofResourceURIList = new ArrayList();
			mofResourceURIList.add(J2EEConstants.EJBJAR_DD_URI);
		}
	}

	/**
	 * We just blasted over what was in the workbench; the resource set on the nature needs to
	 * remove the obsolete resources
	 */
	protected void removeResourceIfLoaded(ResourceSet rs, String uri) {
		Resource r = rs.getResource(URI.createURI(uri), true);
		if (r != null)
			r.unload();
	}


	/**
	 * Insert the method's description here. Creation date: (9/7/2001 2:18:35 PM)
	 * 
	 * @param newEjbCopyGroup
	 *            com.ibm.eclipse.ejb.archiveui.EJBImportCopyGroup
	 */
	public void setEjbCopyGroup(CopyGroup newEjbCopyGroup) {
		ejbCopyGroup = newEjbCopyGroup;
	}

	protected boolean shouldOverwrite(String uri) {
		if (mofResourceURIList != null && mofResourceURIList.contains(uri))
			return true;
		return super.shouldOverwrite(uri);
	}

}