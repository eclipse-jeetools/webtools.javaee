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
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.moduleextension;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.internal.earcreation.UpdateModuleReferencesInEARProjectCommand;
import org.eclipse.jst.j2ee.internal.project.J2EEJavaProjectInfo;
import org.eclipse.jst.j2ee.internal.project.J2EENature;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public interface WebModuleExtension extends EarModuleExtension {

	static final String WEBSETTINGS_FILE_URI = ".websettings"; //$NON-NLS-1$

	void initializeEjbReferencesToModule(J2EENature nature, UpdateModuleReferencesInEARProjectCommand command);

	void addWLPProjects(IProject aProject, Set projectsToBuild);

	J2EEJavaProjectInfo createWebProjectInfo(Archive anArchive);

	void setContextRootForModuleMapping(WebModule m, IProject nestedProject) throws CoreException;

	//J2EEImportOperationOLD createImportOperation(IProject nestedProject, WARFile warFile,
	// EARImportConfiguration importConfig);

	String getContentFolder(IProject project, IFile webSettingsFile);

	J2EEJavaProjectInfo createProjectInfo();

	boolean compareWebContextRoot(Module module, IProject project) throws CoreException;

	boolean hasRuntime(IProject project);

}