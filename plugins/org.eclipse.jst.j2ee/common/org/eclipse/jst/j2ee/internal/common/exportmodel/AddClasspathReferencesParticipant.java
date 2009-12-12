/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyVirtualComponent;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.export.AbstractExportParticipant;
import org.eclipse.wst.common.componentcore.export.ExportModelUtil;
import org.eclipse.wst.common.componentcore.export.ExportableResource;
import org.eclipse.wst.common.componentcore.export.ExportModel.ExportTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class AddClasspathReferencesParticipant extends AbstractExportParticipant {
	protected static IPath WEBLIB = new Path(J2EEConstants.WEB_INF_LIB).makeAbsolute();
	protected static final IPath WEB_CLASSES_PATH = new Path(J2EEConstants.WEB_INF_CLASSES);
	
	private List<ExportableResource> list;
	
	@Override
	public boolean shouldIgnoreReference(IVirtualComponent rootComponent,
			IVirtualReference referenced, ExportTaskModel dataModel) {
		if( (referenced.getReferencedComponent() instanceof ClasspathDependencyVirtualComponent)
				&& ((ClasspathDependencyVirtualComponent)referenced.getReferencedComponent()).isClassFolder())
			return true;
		if( referenced.getRuntimePath().equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH))
			return true;
		return false;
	}

	
	@Override
	public void finalize(IVirtualComponent component,
			ExportTaskModel dataModel, List<ExportableResource> resources) {
		this.list = resources;
		
		try {
			if (JavaEEProjectUtilities.isEARProject(component.getProject())) {
				// If an EAR, add classpath contributions for all referenced modules
				addReferencedComponentClasspathDependencies(component, false);
			} else {
				if (JavaEEProjectUtilities.isDynamicWebProject(component.getProject())) {
					// If a web, add classpath contributions for all WEB-INF/lib modules
					addReferencedComponentClasspathDependencies(component, true);
				}
			}
			// Add all Java output folders that have publish/export attributes
			addClassFolderDependencies(component);
		} catch( CoreException ce ) {
			// do nothing
		}
	}

	private void addReferencedComponentClasspathDependencies(final IVirtualComponent component, final boolean web) {
		final IVirtualReference[] refs = component.getReferences();
		final Set absolutePaths = new HashSet();
		for (int i = 0; i < refs.length; i++) {
			final IVirtualReference reference = refs[i];
			final IPath runtimePath = reference.getRuntimePath();
			final IVirtualComponent referencedComponent = reference.getReferencedComponent();
			
			// if we are adding to a web project, only process references with the /WEB-INF/lib runtime path
			if (web && !runtimePath.equals(WEBLIB)) {
				continue;
			}

			// if the reference cannot export dependencies, skip
			if (!canExportClasspathComponentDependencies(referencedComponent)) {
				continue;
			}
			
			if (!referencedComponent.isBinary() && referencedComponent instanceof J2EEModuleVirtualComponent) {
				final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) referencedComponent).getJavaClasspathReferences();
				for (int j = 0; j < cpRefs.length; j++) {
					final IVirtualReference cpRef = cpRefs[j];
					IPath cpRefRuntimePath = cpRef.getRuntimePath();

					if (cpRef.getReferencedComponent() instanceof ClasspathDependencyVirtualComponent) {
						// want to avoid adding dups
						ClasspathDependencyVirtualComponent cpComp = (ClasspathDependencyVirtualComponent) cpRef.getReferencedComponent();
						// don't want to process class folder refs here
						if (cpComp.isClassFolder()) {
							continue;
						}
						if (cpRefRuntimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
							// runtime path within deployed app will be runtime path of parent component
							cpRefRuntimePath = runtimePath;
						} else {
							//https://bugs.eclipse.org/bugs/show_bug.cgi?id=247090
							//if path isn't ../, it shouldn't be added here
							continue;
						}
						final IPath absolutePath = ClasspathDependencyUtil.getClasspathVirtualReferenceLocation(cpRef);
						if (absolutePaths.contains(absolutePath)) {
							// have already added a member for this archive
							continue;
						}
						new ExportModelUtil(list, null).addFile(cpComp, cpRefRuntimePath, cpComp);
						absolutePaths.add(absolutePath);
					}
				}
			}
		}
	}
	
	private boolean canExportClasspathComponentDependencies(IVirtualComponent component) {
		final IProject project = component.getProject();
		// check for valid project type
		if (JavaEEProjectUtilities.isEJBProject(project) 
				|| JavaEEProjectUtilities.isDynamicWebProject(project)
				|| JavaEEProjectUtilities.isJCAProject(project)
    			|| JavaEEProjectUtilities.isUtilityProject(project)) {
			return true;
		}
		return false;
	}

	private void addClassFolderDependencies(final IVirtualComponent component) throws CoreException {
		if (!component.isBinary() && component instanceof J2EEModuleVirtualComponent) {
			final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) component).getJavaClasspathReferences();
			for (int i = 0; i < cpRefs.length; i++) {
				final IVirtualReference cpRef = cpRefs[i];
				final IPath runtimePath = cpRef.getRuntimePath();
				final IVirtualComponent comp = cpRef.getReferencedComponent();
				if (comp instanceof ClasspathDependencyVirtualComponent) {
					final ClasspathDependencyVirtualComponent cpComp = (ClasspathDependencyVirtualComponent) comp;
					if (cpComp.isClassFolder()) {
						IPath targetPath = null;
						if (runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_COMPONENT_PATH)) {
							targetPath = Path.EMPTY;
						} else if (runtimePath.equals(IClasspathDependencyConstants.WEB_INF_CLASSES_PATH)) {
							targetPath =  WEB_CLASSES_PATH;
						} else {
							continue;
						}
						final IContainer container = cpComp.getClassFolder();
						new ExportModelUtil(list, null).addContainer(container, targetPath);
					}
				}
			}
		}
	}
}
