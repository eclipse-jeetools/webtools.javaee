/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.change;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ltk.core.nls.RefactoringResourceHandler;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;


public class EARReferenceRemoveChange extends Change {

	
	public EARReferenceRemoveChange(IProject referencingEARProject, IProject projectToRemove) {
		super();
		this.referencingEARProject = referencingEARProject;
		this.projectToRemove = projectToRemove;
		this.referencingEARProjectComp = ComponentCore.createComponent(referencingEARProject);
		cachedRefs = referencingEARProjectComp.getReferences();
		this.projectToRemoveComp = ComponentCore.createComponent(projectToRemove);
		earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(referencingEARProject);
		moduleURI = earModel.getModuleURI(projectToRemoveComp);
	}

	IProject referencingEARProject = null;
	IVirtualComponent referencingEARProjectComp = null;
	IProject projectToRemove = null;
	IVirtualComponent projectToRemoveComp = null;
	IVirtualReference[] cachedRefs = null;
	IEARModelProvider earModel = null;
	String moduleURI = null;
	@Override
	public Object getModifiedElement() {
		return null;
	}

	@Override
	public String getName() {
		String name = NLS.bind( RefactoringResourceHandler.Remove_JavaEE_References, 
				new Object[] {projectToRemove.getName()});
		name += referencingEARProject.getName();
		return name;
	}

	@Override
	public void initializeValidationData(IProgressMonitor pm) {
	}

	@Override
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
	return null;
	}

	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		updateEARDD();
		removeReferencedComponents(pm);
		return null;
	}
	
	@Override
	public ChangeDescriptor getDescriptor() {
		return null;
	}
	
	private void updateEARDD() {
		if (!referencingEARProject.isAccessible())
			return;
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(referencingEARProject);
		boolean moduleInXML = false;
		//Check if module to remove is in xml
		ICommonApplication mergedApp = (ICommonApplication)earModel.getModelObject();
		ICommonModule module = mergedApp.getFirstEARModule(moduleURI);
		if (module != null) {
			IFile file = WorkbenchResourceHelper.getFile((EObject)module);
			if (file != null && file.exists())
				moduleInXML = true;
		}
		earModel.modify(new Runnable() {
			@Override
			public void run() {
				ICommonApplication application = (ICommonApplication)earModel.getModelObject();
				if (application == null)
					return;
				IVirtualComponent moduleComponent = projectToRemoveComp.getComponent();
				if(!moduleComponent.isBinary()){
					J2EEComponentClasspathUpdater.getInstance().queueUpdateModule(moduleComponent.getProject());
				}
				
				removeModule(application, moduleURI); 
				IVirtualFile vFile = referencingEARProjectComp.getRootFolder().getFile(moduleURI);
				IFile iFile = vFile.getUnderlyingFile();
				if(iFile.exists()){
					try {
						iFile.delete(true, new NullProgressMonitor());
					} catch (CoreException e) {
						J2EEPlugin.logError(e);
					}
				}
			
			}
		}, null);
		//If change is to "merged model" only - remove from merged model
		if (!moduleInXML)
			mergedApp.getEARModules().remove(module);
	}
	
	protected void removeModule(ICommonApplication application, String moduleURI) {
		ICommonModule module = application.getFirstEARModule(moduleURI);
		application.getEARModules().remove(module);
	}
	
	protected void removeReferencedComponents(IProgressMonitor monitor) {
		
		if (referencingEARProjectComp == null || !referencingEARProjectComp.getProject().isAccessible() || referencingEARProjectComp.isBinary()) return;
		
		IVirtualReference [] existingReferencesArray = cachedRefs;
		if(existingReferencesArray == null || existingReferencesArray.length == 0){
			return;
		}
		List existingReferences = new ArrayList();
		for(int i=0;i<existingReferencesArray.length; i++){
			existingReferences.add(existingReferencesArray[i]);
		}
		List targetprojectList = new ArrayList();
		if (projectToRemoveComp==null )
				return;
		IVirtualReference ref = findMatchingReference(existingReferences, projectToRemoveComp, null);
		//if a ref was found matching the specified deployPath, then remove it
		if(ref != null){
			removeRefereneceInComponent(referencingEARProjectComp, ref);
			existingReferences.remove(ref);
			//after removing the ref, check to see if it was the last ref removed to that component
			//and if it was, then also remove the project reference
			ref = findMatchingReference(existingReferences, projectToRemoveComp);
			if(ref == null){
				IProject targetProject = projectToRemoveComp.getProject();
				targetprojectList.add(targetProject);
			}
		}
		
		try {
			ProjectUtilities.removeReferenceProjects(referencingEARProjectComp.getProject(),targetprojectList);
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		}		
		
	}
	
	private IVirtualReference findMatchingReference(List existingReferences, IVirtualComponent comp) {
		return findMatchingReference(existingReferences, comp, null);
	}

	protected void removeRefereneceInComponent(IVirtualComponent component, IVirtualReference reference) {
		((VirtualComponent)component.getComponent()).removeReference(reference);
	}
	
	private IVirtualReference findMatchingReference(List existingReferences, IVirtualComponent comp, IPath path) {
		for(int i=0;i<existingReferences.size(); i++){
			IVirtualReference ref = (IVirtualReference)existingReferences.get(i);
			IVirtualComponent c = ref.getReferencedComponent();
			if(c != null && c.getName().equals(comp.getName())){
				if(path == null){
					return ref;
				} else if(path.equals(ref.getRuntimePath())){
					return ref;
				}
			}
		}
		return null;
	}


}
