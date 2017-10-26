/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.validation;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.model.internal.validation.WarValidator;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;


/**
 * Insert the type's description here. Creation date: (10/2/2001 6:46:56 PM)
 * 
 * @author: Administrator
 */
public class UIWarValidator extends WarValidator {
	
	protected org.eclipse.jst.j2ee.internal.web.validation.UIWarHelper warHelper;	




	/**
	 * UIWarValidator constructor comment.
	 */
	public UIWarValidator() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (10/2/2001 8:01:45 PM)
	 * 
	 * @return org.eclipse.wst.validation.internal.core.core.war.workbenchimpl.UIWarHelper
	 */
	public org.eclipse.jst.j2ee.internal.web.validation.UIWarHelper getWarHelper() {
		return warHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (10/2/2001 8:01:45 PM)
	 * 
	 * @param newWarHelper
	 *            org.eclipse.wst.validation.internal.core.core.war.workbenchimpl.UIWarHelper
	 */
	public void setWarHelper(org.eclipse.jst.j2ee.internal.web.validation.UIWarHelper newWarHelper) {
		warHelper = newWarHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (10/2/2001 6:49:26 PM)
	 */
	@Override
	public void validate() throws ValidationException {
		// First remove all previous msg. for this project
		//	_reporter.removeAllMessages(this,null);
		super.validate();
		validateEARForContextRoot();
	}

	/**
	 * Insert the method's description here. Creation date: (10/2/2001 6:49:26 PM)
	 */
	@Override
	public IStatus validateInJob(IValidationContext inHelper, IReporter inReporter)
			throws org.eclipse.wst.validation.internal.core.ValidationException {
		
		setWarHelper((UIWarHelper) inHelper);
		IProject proj = ((IWorkbenchContext) warHelper).getProject();
		IVirtualComponent wbModule = ComponentCore.createComponent(proj);
        if( wbModule != null && JavaEEProjectUtilities.isDynamicWebProject(proj)) {           	
        	IVirtualFile webFile = wbModule.getRootFolder().getFile(J2EEConstants.WEBAPP_DD_URI);
			if( webFile.exists()) {
				status = super.validateInJob(inHelper, inReporter);
				validateWebInfLibs(wbModule);
			}
        }
        return status;
	}	

	/*
	 * For web projects, need to validate that the classpath component dependencies (both
	 * from this project's classpath and from the exported classpath entries of referenced
	 * utility projects) do not conflict.
	 */
	private void validateWebInfLibs(final IVirtualComponent component) {

		final Set webLibMappings = new HashSet();
		
		// get the libs currently in the WEB-INF/lib folder
		final IVirtualFolder folder = component.getRootFolder().getFolder(WebArtifactEdit.WEBLIB);
		try {
			IVirtualResource[] resources = folder.members();
			for (int i = 0; i < resources.length; i++) {
				if (resources[i] instanceof IVirtualFile) {
					IResource[] underlyingResources = resources[i].getUnderlyingResources();
					for (int j = 0; j < underlyingResources.length; j++) {
						webLibMappings.add(underlyingResources[j].getName());
					}
				}
			}
		} catch (CoreException ce) {
			// swallow
		}
		final Map archiveToPath = new HashMap();
		final IVirtualReference[] webLibs = WebUtilities.getLibModules(component);
		for (int i = 0; i < webLibs.length; i++) {
			IVirtualComponent comp = webLibs[i].getReferencedComponent();
			String name = null;
			if (comp.isBinary()) {
				java.io.File diskFile = comp.getAdapter(java.io.File.class);
				checkLibName(diskFile.getName(), null, webLibMappings, true);
			} else {
				String archiveName = webLibs[i].getArchiveName();
				if (archiveName != null && archiveName.length() > 0) {
					name = archiveName;
				} else {
					name = comp.getName() + IJ2EEModuleConstants.JAR_EXT;
				}
				checkLibName(name, comp.getProject(), webLibMappings, true);
				if (comp instanceof J2EEModuleVirtualComponent) {
					final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) comp).getJavaClasspathReferences();
					for (int j = 0; j < cpRefs.length; j++) {
						final IVirtualReference ref = cpRefs[j];
						if (ref.getReferencedComponent() instanceof VirtualArchiveComponent) {
							final String cpCompName = ref.getArchiveName();
							checkLibName(cpCompName, comp.getProject(), webLibMappings, false);
							IPath cpEntryPath= ClasspathDependencyUtil.getClasspathVirtualReferenceLocation(ref);
							if (cpEntryPath != null) {
								IPath existingPath = (IPath) archiveToPath.get(cpCompName);
								if (existingPath != null && !existingPath.equals(cpEntryPath)) {
									_reporter.addMessage(this, new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_DUPLICATE_CLASSPATH_COMPONENT_URI, new String[]{cpCompName, comp.getProject().getName()}));
								} else {
									archiveToPath.put(cpCompName, cpEntryPath);
								}
							}
						}
					}
				}
			}
		}		
	}
	
	private void checkLibName(final String name, final IProject project, final Set webLibNames, final boolean addName) {
		if (webLibNames.contains(name)) {
			if (project != null) {
				//ignore this because of WAR import brings in the lib jars along with projects
				//_reporter.addMessage(this, new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_DUPLICATE_WEB_INF_LIB_OTHER_PROJECT, new String[]{name, project.getName()}));
			} else {
				_reporter.addMessage(this, new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_DUPLICATE_WEB_INF_LIB, new String[]{name}));
			}
		} else if (addName) {
			webLibNames.add(name);
		}
	}
	
	/**
	 * Insert the method's description here. Creation date: (10/2/2001 6:49:26 PM)
	 */
	public void validateEARForContextRoot() {

//		IProject project = getWarHelper().getProject();
//		J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
//		String contextRoot = webNature.getContextRoot();
//
//		if (webNature == null)
//			return;
//
//		EARNatureRuntime earRuntimeNature[] = webNature.getReferencingEARProjects();

//		for (int i = 0; i < earRuntimeNature.length; i++) {
//			// for each Ear Project that contains this web project update the context root.
//			// hold the model and validate the context root & release the model
//			org.eclipse.jst.j2ee.internal.earcreation.EAREditModel editModel = null;
//			try {
//				editModel = earRuntimeNature[i].getEarEditModelForRead(this);
//				if (editModel != null) {
//					Module module = null;
//					if ((module = earRuntimeNature[i].getModule(project)) != null) {
//						String webModcontextRoot = ((WebModule) module).getContextRoot();
//						if (webModcontextRoot != null && !webModcontextRoot.equals(contextRoot)) {
//							String[] params = new String[3];
//							params[0] = project.getName();
//							params[1] = ((WebModule) module).getContextRoot();
//							params[2] = earRuntimeNature[i].getProject().getName();
//							addError(WAR_CATEGORY, ERROR_EAR_INVALID_CONTEXT_ROOT, params);
//						}
//					}
//				}
//			} finally {
//				if (editModel != null) {
//					editModel.releaseAccess(this);
//					editModel = null;
//				}
//			}
//
//		}

	}
	
	/**
	 * Method validateLibModules.
	 * 
	 * 
	 */
	
	//TODO create validation for modules
/*	protected void validateLibModules(J2EEWebNatureRuntime webNature) {
		if (webNature != null) {
			ILibModule[] libModules = webNature.getLibModules();
			IProject project = webNature.getProject();

			for (int i = 0; i < libModules.length; i++) {
				ILibModule lib = libModules[i];
				IProject tProject = lib.getProject();
				if (tProject != null) { // Project could be null if JavaProject is deleted, for
										// example
					if (!tProject.exists() || !tProject.isOpen()) {
						addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_MISSING_WLP_PROJECT, new String[]{tProject.getName()}, webNature.getProject());
					}
				}

				String jarName = lib.getJarName();
				IPath jarPath = webNature.getLibraryFolder().getFullPath().append(jarName);
				if (((Resource) project).findExistingResourceVariant(jarPath) != null)
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_CONFLICTING_WLP_PROJECT, new String[]{jarName}, webNature.getProject());
			}
		}
	}*/	
	
	@Override
	public ISchedulingRule getSchedulingRule(IValidationContext helper) {
		
		IProject project = ((IWorkbenchContext) helper).getProject();
		IVirtualComponent comp = ComponentCore.createComponent( project );
		IFile webDeploymentDescriptor = null;
		if( comp != null ){
			IVirtualFile vf = comp.getRootFolder().getFile(new Path(J2EEConstants.WEBAPP_DD_URI));
			if( vf!= null ){
				webDeploymentDescriptor = vf.getUnderlyingFile();
			}
		}
		return webDeploymentDescriptor;
	}	

}
