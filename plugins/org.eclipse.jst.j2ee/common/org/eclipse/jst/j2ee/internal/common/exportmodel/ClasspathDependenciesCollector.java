/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyComponent;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyProvider;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyReceiver;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.ChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;


public class ClasspathDependenciesCollector extends FlatVirtualComponent {

	public ClasspathDependenciesCollector(IVirtualComponent component) {
		super(component);
	}
	
	@Override
	protected boolean canOptimize() {
		return true;
	}

	@Override
	protected void optimize(List<IFlatResource> resources, List<IChildModuleReference> children) {
		if (getComponent() != null) {
			AbstractFlattenParticipant classpathReferences = null;
			IProject project = getComponent().getProject();
			
			if (JavaEEProjectUtilities.isEARProject(project)) {
				classpathReferences = new AddClasspathLibReferencesParticipant();
			}
			else if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
				classpathReferences = new WebLibClasspathReferencesParticipant();
			}
			
			if (classpathReferences != null) {
				classpathReferences.finalize(getComponent(), null, resources);
				// Retrieve the children modules contributed by classpath references and 
				// add them as children of this FlatVirtualComponent 
				List <IVirtualReference> refToModules = classpathReferences.getChildModules(getComponent(), null);
				if (refToModules != null){
					for (IVirtualReference ref:refToModules){
						ChildModuleReference cm = new ChildModuleReference(ref, new Path("")); //$NON-NLS-1$
						List<IChildModuleReference> duplicates = new ArrayList();
						for( IChildModuleReference tmp : children ) {
							if(tmp.getRelativeURI().equals(cm.getRelativeURI()))
								duplicates.add(tmp);
						}
						children.removeAll(duplicates);
						children.add(cm);
					}
				}
			}
		}
	}
	
	public List <IFlatFile> fetchFlatFiles() throws CoreException {
		IFlatResource [] resources = fetchResources();
		List<IFlatFile> flatFiles = new ArrayList<IFlatFile>();
		return fetchFlatFiles(resources, flatFiles);
	}
	
	/**
	 * This method will return an array of {@link IVirtualReference} for all of
	 * the utility modules contributed by classpath dependencies. 
	 * 
	 * @return - an array of {@link IVirtualReference} of utility modules
	 * @throws CoreException 
	 */
	public IVirtualReference[] getUtilityModuleReferences() throws CoreException {

		List <IVirtualReference> result = new ArrayList<IVirtualReference>();
		if (getComponent() != null) {			
			List<String> componentTypes = Collections.singletonList(IJ2EEFacetConstants.UTILITY);
			for (IChildModuleReference moduleRef:getChildModules()){
				if (componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(moduleRef.getReference().getReferencedComponent()))) {
					result.add(moduleRef.getReference());
				}
			}			

			if (result.size() > 0)
				return result.toArray(new IVirtualReference[result.size()]);
		}
		return new IVirtualReference[0];
	}

	private List <IFlatFile> fetchFlatFiles(IFlatResource[] resources, List<IFlatFile> flatFiles) {
		for (int i = 0; i < resources.length; i++) {
			IFlatResource resource = resources[i];
			if (resource instanceof IFlatFile) {
				if (((IFlatFile) resource).getAdapter(File.class) != null)
					flatFiles.add((IFlatFile)resource);
			} else if (resource instanceof IFlatFolder) {
				fetchFlatFiles(((IFlatFolder)resource).members(), flatFiles);
			}
		}
		return flatFiles;
	}
	
	private class WebLibClasspathReferencesParticipant extends AbstractFlattenParticipant {
		private List<IFlatResource> list;
		
		@Override
		public void finalize(IVirtualComponent webComponent, FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
			this.list = resources;
			addReferencedComponentClasspathDependencies((IClasspathDependencyReceiver)webComponent);
		}

		private void addReferencedComponentClasspathDependencies(final IClasspathDependencyReceiver webComponent) {
			final IVirtualReference[] refs = webComponent.getReferences();
			for (int i = 0; i < refs.length; i++) {
				final IVirtualReference reference = refs[i];
				final IPath runtimePath = reference.getRuntimePath();
				final IVirtualComponent referencedComponent = reference.getReferencedComponent();
				
				if (!runtimePath.equals(WebUtilities.WEBLIB)) {
					continue;
				}
				
				// if the reference cannot export dependencies, skip
				if( !(referencedComponent instanceof IClasspathDependencyProvider) )
					continue;

				if (!referencedComponent.isBinary() && referencedComponent instanceof IClasspathDependencyProvider) {
					final IVirtualReference[] cpRefs = ((IClasspathDependencyProvider) referencedComponent).getJavaClasspathReferences();
					for (int j = 0; j < cpRefs.length; j++) {
						final IVirtualReference cpRef = cpRefs[j];
						IPath cpRefRuntimePath = cpRef.getRuntimePath();

						if (cpRef.getReferencedComponent() instanceof IClasspathDependencyComponent) {
							IClasspathDependencyComponent cpComp = (IClasspathDependencyComponent) cpRef.getReferencedComponent();
							// don't want to process class folder refs here
							if (cpComp.isClassFolder())
								continue;

							if (!cpRefRuntimePath.toString().equals(IClasspathDependencyReceiver.RUNTIME_MAPPING_INTO_CONTAINER))
								continue;

							cpRefRuntimePath = runtimePath;
							new VirtualComponentFlattenUtility(list, null).addFile(cpComp, cpRefRuntimePath, cpComp);
						}
					}
				}
			}
		}
	}

}
