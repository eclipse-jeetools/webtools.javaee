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
 * Created on Nov 26, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AddWebModuleToEARDataModel extends AddModuleToEARDataModel {

	public static AddWebModuleToEARDataModel createAddWebModuleToEARDataModel(String earProjectName, IProject moduleProject) {
		AddWebModuleToEARDataModel model = new AddWebModuleToEARDataModel();
		model.setProperty(AddWebModuleToEARDataModel.PROJECT_NAME, earProjectName);
		model.setProperty(AddWebModuleToEARDataModel.ARCHIVE_PROJECT, moduleProject);
		return model;
	}

	/**
	 * Optional - This is the context root stored with the module in the application.xml.
	 */
	public static final String CONTEXT_ROOT = "AddWebModuleToEARDataModel.CONTEXT_ROOT"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CONTEXT_ROOT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultURIExtension()
	 */
	protected String getDefaultURIExtension() {
		return "war"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddModuleToEARDataModel#isWebModuleArchive()
	 */
	public boolean isWebModuleArchive() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (notify && propertyName.equals(ARCHIVE_PROJECT))
			notifyDefaultChange(CONTEXT_ROOT);
		return notify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONTEXT_ROOT))
			return getDefaultContextRoot();
		return super.getDefaultProperty(propertyName);
	}

	private String getDefaultContextRoot() {
		IProject archiveProj = (IProject) getProperty(ARCHIVE_PROJECT);
		if (archiveProj == null || !J2EENature.hasRuntime(archiveProj, IWebNatureConstants.J2EE_NATURE_ID))
			return computeDefaultContextRoot();
		EARNatureRuntime earNature = J2EEProjectUtilities.getFirstReferencingEARProject(archiveProj);
		if (earNature != null) {
			WebModule webModule = (WebModule) earNature.getModule(archiveProj);
			if (webModule != null) {
				String root = webModule.getContextRoot();
				if (root != null && root.length() > 0)
					return root;
			}
		}
		return computeDefaultContextRoot();
	}

	private String computeDefaultContextRoot() {
		IProject proj = (IProject) getProperty(ARCHIVE_PROJECT);
		if (proj != null)
			return proj.getName().replace(' ', '_');
		return null;
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (CONTEXT_ROOT.equals(propertyName)) {
			//how to get context root?
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		}
		return super.doValidateProperty(propertyName);
	}

	//TODO: must implement validation
	public IStatus validateContextRoot(String contextRoot) {

		if (contextRoot == null)
			return null;

		String name = contextRoot;
		if (name.equals("") || name == null) { //$NON-NLS-1$
			//  this was added because the error message shouldnt be shown initially. It should be
			// shown only if context
			// root field is edited to
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Context_Root_cannot_be_empty_2", new Object[]{contextRoot}), null); //$NON-NLS-1$
		}

		if (name.trim().equals(name)) {
			StringTokenizer stok = new StringTokenizer(name, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						if (Character.isWhitespace(token.charAt(i))) {
							//Removed because context roots can contain white space
							//errorMessage =
							//	ResourceHandler.getString("_Context_root_cannot_conta_UI_");//$NON-NLS-1$
							// = " Context
							// root cannot contain whitespaces."
						} else {
							return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("The_character_is_invalid_in_a_context_root", new Object[]{(new Character(token.charAt(i))).toString()}), //$NON-NLS-1$
										null);
						}
					}
				}
			}
		} // en/ end of if(name.trim
		else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Names_cannot_begin_or_end_with_whitespace_5", new Object[]{contextRoot}), null); //$NON-NLS-1$

		return OK_STATUS;
	}
}