/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Copy the classpath LIB references from a child with 
 * a ../ runtime path into a receiving parent.
 * 
 * @author rob
 *
 */
public class AddClasspathLibReferencesParticipant extends AbstractFlattenParticipant {
	private List<IFlatResource> list;
	

	@Override
	public boolean shouldIgnoreReference(IVirtualComponent rootComponent,
			IVirtualReference referenced, FlatComponentTaskModel dataModel) {
		if (referenced.getRuntimePath().toString().startsWith(IClasspathDependencyReceiver.RUNTIME_MAPPING_INTO_CONTAINER))
			return true;
		return false;
	}

	
	@Override
	public void finalize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		this.list = resources;
		if( !(component instanceof IClasspathDependencyReceiver ))
			return;
		if( ((IClasspathDependencyReceiver)component).canReceiveClasspathDependencies())
			addReferencedComponentClasspathDependencies((IClasspathDependencyReceiver)component);
	}

	private void addReferencedComponentClasspathDependencies(final IClasspathDependencyReceiver component) {
		final IVirtualReference[] refs = component.getReferences();
		for (int i = 0; i < refs.length; i++) {
			final IVirtualReference reference = refs[i];
			final IPath runtimePath = reference.getRuntimePath();
			final IVirtualComponent referencedComponent = reference.getReferencedComponent();

			// if the reference cannot export dependencies, skip
			if( !(referencedComponent instanceof IClasspathDependencyProvider) )
				continue;
			
			if (!referencedComponent.isBinary() && referencedComponent instanceof IClasspathDependencyProvider) {
				final IVirtualReference[] cpRefs = ((IClasspathDependencyProvider) referencedComponent).getJavaClasspathReferences();
				for (int j = 0; j < cpRefs.length; j++) {
					final IVirtualReference cpRef = cpRefs[j];
					IPath cpRefRuntimePath = cpRef.getRuntimePath();

					if (cpRef.getReferencedComponent() instanceof IClasspathDependencyComponent) {
						// want to avoid adding dups
						IClasspathDependencyComponent cpComp = (IClasspathDependencyComponent) cpRef.getReferencedComponent();
						// don't want to process class folder refs here
						if (cpComp.isClassFolder())
							continue;

						//if path isn't ../, it shouldn't be added here [bug 247090]
						if (!cpRefRuntimePath.toString().startsWith(IClasspathDependencyReceiver.RUNTIME_MAPPING_INTO_CONTAINER))
							continue;
						
						// TODO: verify this cpRefRuntimePath is acceptable?
						//if( !runtimePath.equals(component.getClasspathFolderPath(cpComp))) continue;
						
						cpRefRuntimePath = runtimePath.append(cpRefRuntimePath.removeFirstSegments(1));
						new VirtualComponentFlattenUtility(list, null).addFile(cpComp, cpRefRuntimePath, cpComp);
					}
				}
			}
		}
	}
	
	
	private List<IVirtualReference> getReferencedProjectComponentClasspathDependencies(final IClasspathDependencyReceiver component, FlatComponentTaskModel dataModel) {
		final IVirtualReference[] refs = component.getReferences();
		List<IVirtualReference> validReferences = new ArrayList<IVirtualReference>();
		
		for (int i = 0; i < refs.length; i++) {
			final IVirtualReference reference = refs[i];
			final IVirtualComponent referencedComponent = reference.getReferencedComponent();

			// if the reference cannot export dependencies, skip
			if( !(referencedComponent instanceof IClasspathDependencyProvider) )
				continue;
			
			if (!referencedComponent.isBinary() && referencedComponent instanceof IClasspathDependencyProvider) {
				final IVirtualReference[] cpRefs = ((IClasspathDependencyProvider) referencedComponent).getJavaClasspathReferences();
				for (int j = 0; j < cpRefs.length; j++) {
					final IVirtualReference cpRef = cpRefs[j];
					IPath cpRefRuntimePath = cpRef.getRuntimePath();

					// TODO: Create a new interface like IClasspathDependencyComponent but for project references
					if (!(cpRef.getReferencedComponent() instanceof IClasspathDependencyComponent)) {

						//if path isn't ../, it shouldn't be added here [bug 247090]
						if (!cpRefRuntimePath.toString().startsWith(IClasspathDependencyReceiver.RUNTIME_MAPPING_INTO_CONTAINER))
							continue;
											
						validReferences.add(new ClasspathLibVirtualReference(cpRef));
						
					}
				}
			}
		}
		return validReferences;
		
	}
	
	@Override
	public List<IVirtualReference> getChildModules(IVirtualComponent component, FlatComponentTaskModel dataModel){
		List<IVirtualReference> result = null;
		if( !(component instanceof IClasspathDependencyReceiver ))
			return null;		
		// Gather project classpath dependencies from child references
		if( ((IClasspathDependencyReceiver)component).canReceiveClasspathDependencies())
			result = getReferencedProjectComponentClasspathDependencies((IClasspathDependencyReceiver)component, dataModel);
		return result;
	}
	
	
	// Inner class used as a wrapper for VirtualReferences, delegating most of the methods to the wrapped object, except
	// the getRuntimePath() method. This is needed so a reference to a virtual component can be added correctly to the receiving parent  
	private static class ClasspathLibVirtualReference implements IVirtualReference{
		private IVirtualReference ref;
		ClasspathLibVirtualReference(IVirtualReference ref){
			this.ref = ref;
		}
	
		public void create(int updateFlags, IProgressMonitor aMonitor) {
			ref.create(updateFlags, aMonitor);			
		}

		public boolean exists() {
			return ref.exists();
		}

		public String getArchiveName() {
			return ref.getArchiveName();
		}

		public int getDependencyType() {
			return ref.getDependencyType();
		}

		public IVirtualComponent getEnclosingComponent() {
			return ref.getEnclosingComponent();
		}

		public IVirtualComponent getReferencedComponent() {
			return ref.getReferencedComponent();
		}

		public IPath getRuntimePath() {
			// remove the ../ portion of the path
			return ref.getRuntimePath().removeFirstSegments(1);
		}

		public boolean isDerived() {
			return ref.isDerived();
		}

		public void setArchiveName(String archiveName) {
			ref.setArchiveName(archiveName);
			
		}

		public void setDependencyType(int aDependencyType) {
			ref.setDependencyType(aDependencyType);
			
		}

		public void setReferencedComponent(
				IVirtualComponent referencedComponent, EObject dependentObject) {
			ref.setReferencedComponent(referencedComponent, dependentObject);
			
		}

		public void setRuntimePath(IPath aRuntimePath) {
			ref.setRuntimePath(aRuntimePath);
		}
	}
	
//	private boolean canExportClasspathComponentDependencies(IVirtualComponent component) {
//		final IProject project = component.getProject();
//		// check for valid project type
//		if (JavaEEProjectUtilities.isEJBProject(project) 
//				|| JavaEEProjectUtilities.isDynamicWebProject(project)
//				|| JavaEEProjectUtilities.isJCAProject(project)
//    			|| JavaEEProjectUtilities.isUtilityProject(project)) {
//			return true;
//		}
//		return false;
//	}
}
