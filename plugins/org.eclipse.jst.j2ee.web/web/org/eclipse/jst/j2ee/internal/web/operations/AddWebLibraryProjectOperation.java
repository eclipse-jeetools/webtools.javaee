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
 * Created on May 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.web.internal.operation.ILibModule;
import org.eclipse.wst.web.internal.operation.LibModule;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class AddWebLibraryProjectOperation extends WTPOperation {


	public AddWebLibraryProjectOperation(AddWebLibraryProjectDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AddWebLibraryProjectDataModel model = (AddWebLibraryProjectDataModel) this.operationDataModel;
		IProject project = model.getTargetProject();
		String jarName = model.getStringProperty(AddWebLibraryProjectDataModel.JAR_NAME);
		String javaProjectName = model.getStringProperty(AddWebLibraryProjectDataModel.JAVA_PROJECT_NAME);
		ILibModule libModule = new LibModule(jarName, javaProjectName);
		try {
			// Set the libModules in the nature
			// ***NOTE*** must set in nature before setting classpath
			// ***NOTE*** so that Libraries node in J2EE Navigator is updated correctly
			J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
			ILibModule[] libModules = webNature.getLibModules();
			int len = libModules.length;
			ILibModule[] newLibModules = new ILibModule[len + 1];
			System.arraycopy(libModules, 0, newLibModules, 0, len);
			newLibModules[len] = libModule;
			webNature.setLibModules(newLibModules);

			// Construct the new classpath. First, remove the removed ones while
			// copying over all others.
			IJavaProject javaProject = ProjectUtilities.getJavaProject(project);
			IClasspathEntry[] existingClasspath = javaProject.getRawClasspath();
			if (existingClasspath == null)
				len = 0;
			else
				len = existingClasspath.length;
			Vector newClasspath = new Vector();
			IProject libProject = libModule.getProject();
			IClasspathEntry libClasspathEntry = JavaCore.newProjectEntry(libProject.getFullPath());
			IPath libPath = libClasspathEntry.getPath();
			boolean dup = false;
			for (int i = 0; i < len; i++) {
				IClasspathEntry classpathEntry = existingClasspath[i];
				newClasspath.add(existingClasspath[i]);
				if (classpathEntry.getPath().equals(libPath)) {
					dup = true;
				}
			}
			if (!dup)
				newClasspath.add(libClasspathEntry);
			len = newClasspath.size();
			IClasspathEntry[] newEntries = (IClasspathEntry[]) newClasspath.toArray(new IClasspathEntry[len]);
			javaProject.setRawClasspath(newEntries, monitor);
			ProjectUtilities.addToBuildSpec(LibCopyBuilder.BUILDER_ID, libProject);

			// Now update the target server and add new class path entries for the
			// new lib modules that have been added
			updateTargetServer();
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}

	private void updateTargetServer() {

		/*
		 * The following explains how it is determined whether or not to update the target server.
		 * The desired behaviour is the same whether or not server targetting has been enabled on
		 * the J2EE preference page, and hence that is not taken into account.
		 * 
		 * SCENARIOS: 1) Conditions: Target server on WLP is null, but the one on the Web project is
		 * not. Action: Set Web project's target server on WLP 2) Conditions: Target server on Web
		 * project is null Action: Do nothing 3) Conditions: Target servers on WLP and Web project
		 * are the same Action: Do nothing 4) Conditions: Target servers on WLP and Web project are
		 * different (and neither is null) Action: Query user
		 * 
		 * @author Pratik Shah
		 */
		//		IRuntime target = ServerTargetUtil.getServerTarget(project.getName());
		//		// Determine if the WLP's target server is to be updated
		//		boolean updateServerTarget = false;
		//		IRuntime currTarget = ServerTargetUtil.getServerTarget(javaProjectName);
		//		ServerTarget target =
		//				ServerTargetUtil.getServerTarget(project.getName());
		//		boolean yesToAll = false, noToAll = false;
		//		for (Iterator addIter = addedModules.iterator(); addIter.hasNext();) {
		//			ILibModule addModule = (ILibModule) addIter.next();
		//
		//			// Determine if the WLP's target server is to be updated
		//			boolean updateServerTarget = false;
		//			IServerTarget currTarget = ServerTargetUtil
		//					.getServerTarget(addModule.getProjectName());
		//			if( target != null && !target.equals(currTarget) ){
		//				if( currTarget == null ){
		//					updateServerTarget = true;
		//				} else {
		//					if( yesToAll ){
		//						updateServerTarget = true;
		//					} else if( !noToAll && quizmaster != null ){
		//						switch( quizmaster.queryOverwrite(project.getName(),
		//								target.getLabel(), addModule.getProjectName(),
		//								currTarget.getLabel()) ){
		//							case IOverwriteQuery.YES_TO_ALL :
		//								yesToAll = true;
		//							case IOverwriteQuery.YES :
		//								updateServerTarget = true;
		//								break;
		//							case IOverwriteQuery.NO_TO_ALL :
		//								noToAll = true;
		//						}
		//					}
		//				}
		//			}
		//			
		//			// Update it, if necessary
		//			if( updateServerTarget ){
		//				ServerTargetHelper.cleanUpNonServerTargetClasspath(
		//						addModule.getProject());
		//				ServerTargetManager.setServerTarget(addModule.getProject(),
		//						target, IServerTargetConstants.WEB_TYPE, monitor);
		//			}
		//
		//			/* By Default, the newEntry is created as an unexported entry. We need
		//			 * to make sure that the classpath doesn't contain this entry or else
		//			 * we get a Name Collision from the JavaModel. (Defect 210687) We cannot
		//			 * use the contains method on <code>newClasspath</code> because the equals
		//			 * method of the <code>ClasspathEntry</code> looks at the exported status to
		//			 * determine if they are similar. We are only interested in the PATHs
		//			 */
		//			IClasspathEntry newEntry =
		// JavaCore.newProjectEntry(addModule.getProject().getFullPath());
		//			boolean shouldBeAdded = true;
		//			for(Iterator classItr = newClasspath.iterator(); classItr.hasNext();) {
		//				IClasspathEntry classEntry = (IClasspathEntry)classItr.next();
		//				if (classEntry.getPath().equals(newEntry.getPath()))
		//					shouldBeAdded = false;
		//			}
		//			if (shouldBeAdded) newClasspath.add(newEntry);
		//			
		//			addLibCopyBuilder(addModule.getProject());
		//		}
		//	
		//		IClasspathEntry[] newEntries = (IClasspathEntry[]) newClasspath.toArray(new
		// IClasspathEntry[newClasspath.size()]);
		//		javaProject.setRawClasspath(newEntries, monitor);
		//		// Clear the list, so that hasChanged() in LibModuleComposite can
		//		// return false (in case this operation was run by clicking apply).
		//		addedModules.clear();
	}
}