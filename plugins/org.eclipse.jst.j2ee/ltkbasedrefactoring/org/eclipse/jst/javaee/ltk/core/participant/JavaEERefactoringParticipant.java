/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.participant;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jst.j2ee.project.EJBUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.ltk.core.change.DeleteEJBClientProjectChange;
import org.eclipse.jst.javaee.ltk.core.change.EARReferenceRemoveChange;
import org.eclipse.jst.javaee.ltk.core.change.NonEARModuleReferenceRemoveChange;
import org.eclipse.jst.javaee.ltk.core.nls.RefactoringResourceHandler;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.DeleteParticipant;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraph;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


public class JavaEERefactoringParticipant extends DeleteParticipant {

	private IProject projectToDelete = null;
	private IProject ejbClientProject = null;
	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws OperationCanceledException {
		
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {

		CompositeChange result= null;
		result = createChangeObjects(result,projectToDelete);
		
		if(ejbClientProject != null){
			
			result = createChangeObjects (result, ejbClientProject);
			DeleteEJBClientProjectChange dc= new DeleteEJBClientProjectChange(ejbClientProject, true, true);
			result.add(dc);
		}
		
		return result;
	}
	
	
	private CompositeChange createChangeObjects(CompositeChange compositeChange, IProject projectToBeDeleted){
		Set<IProject> referencingComponents = IDependencyGraph.INSTANCE.getReferencingComponents(projectToBeDeleted);
		IProject[] dependentProjectList = (IProject[]) referencingComponents.toArray(new IProject[referencingComponents.size()]);
	
		if (dependentProjectList.length > 0){
			if(compositeChange == null){
			compositeChange = new CompositeChange(getName());
			}
			
			for (int i = 0; i < dependentProjectList.length; i++){
				IProject dependentProject = dependentProjectList[i];
				if(JavaEEProjectUtilities.isEARProject(dependentProjectList[i])){
					EARReferenceRemoveChange ec = new EARReferenceRemoveChange(dependentProject, projectToBeDeleted);
					compositeChange.add(ec);
				}else{
					NonEARModuleReferenceRemoveChange nc = new NonEARModuleReferenceRemoveChange(dependentProject, projectToBeDeleted);
					compositeChange.add(nc);
				}
			}
		}
		
		return compositeChange;
	}
	
	
	private IProject getClientProject(IProject ejbProject) {
		if( ejbProject.exists() && ejbProject.isAccessible()){
			IVirtualComponent clientComponent = EJBUtilities.getEJBClientJar(ejbProject);
			if(clientComponent != null){
				return clientComponent.getProject();
			}
		}
		return null;
	}


	@Override
	public String getName() {
		
		String name = NLS.bind(
				RefactoringResourceHandler.JavaEE_Refactorings_Participant_title,
				new Object[] { projectToDelete.getName() });
		return name;
	}

	@Override
	protected boolean initialize(Object element) {
		if(!(element instanceof IProject))
			return false;
		projectToDelete = (IProject) element;
		if(JavaEEProjectUtilities.isEJBProject(projectToDelete)){
			ejbClientProject = getClientProject(projectToDelete);
		}
		return true;
	}

}
