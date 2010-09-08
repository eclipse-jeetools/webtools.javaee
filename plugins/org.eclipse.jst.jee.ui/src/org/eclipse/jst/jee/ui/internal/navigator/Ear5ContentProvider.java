/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.jee.ui.internal.navigator.ear.AbstractEarNode;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Application Client 5.0 Content provider is Deployment Descriptor content provider, 
 * used for constructing of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Ear5ContentProvider extends JEE5ContentProvider {

	private List getComponentReferencesAsList(List componentTypes, IVirtualComponent virtualComponent, IPath runtimePath) {
		List components = new ArrayList();
		IVirtualComponent earComponent = virtualComponent;
		if (earComponent != null ) {
			IVirtualReference[] refComponents = earComponent.getReferences();
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualComponent module = refComponents[i].getReferencedComponent();
				if (module == null) continue;
				// if component types passed in is null then return all components
				if (componentTypes == null || componentTypes.size() == 0) {
					components.add(refComponents[i]);
				} else {
					if (componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(module))) {
						components.add(refComponents[i]);
					}
				}
			}
		}
		return components;
	}

	public IVirtualReference[] getUtilityModuleReferences(IVirtualComponent component) {  
		List explicitUtilityReferences = 
			getComponentReferencesAsList(Collections.singletonList(J2EEProjectUtilities.UTILITY), component, null);

		// fetch other Utility Jars attached to the EAR project 
		List implicitUtilityReferenceTypes =
			Arrays.asList(new String[] {  
					IModuleConstants.JST_APPCLIENT_MODULE,
					IModuleConstants.JST_WEB_MODULE,	 
					IModuleConstants.JST_EJB_MODULE 
			});

		List implicitUtilityReferences = 
			getComponentReferencesAsList(implicitUtilityReferenceTypes, component, null);

		List allUtilityModuleReferences = new ArrayList();
		allUtilityModuleReferences.addAll(explicitUtilityReferences);
		allUtilityModuleReferences.addAll(implicitUtilityReferences);

		if(allUtilityModuleReferences.size() > 0)
			return (IVirtualReference[]) allUtilityModuleReferences.toArray(new IVirtualReference[allUtilityModuleReferences.size()]);
		return new IVirtualReference[0];

	}

	public Object[] getChildren(Object aParentElement) {
		IProject project = null;
		List children = new ArrayList();
		if (aParentElement instanceof GroupEARProvider) {
			children.addAll(((GroupEARProvider)aParentElement).getChildren());
		} else if (aParentElement instanceof AbstractEarNode) {
			return ((AbstractEarNode) aParentElement).getModules().toArray();
		} else if (aParentElement instanceof IAdaptable) {
			project = (IProject) ((IAdaptable) aParentElement).getAdapter(IPROJECT_CLASS);
			if (project != null && JavaEEProjectUtilities.isEARProject(project)) {
				IFacetedProject facetedProject;
				try {
					facetedProject = ProjectFacetsManager.create(project);
					if (facetedProject != null && 
							(facetedProject.hasProjectFacet(
									ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE).getVersion(
											J2EEVersionConstants.VERSION_5_0_TEXT)) || facetedProject.hasProjectFacet(
													ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE).getVersion(
															J2EEVersionConstants.VERSION_6_0_TEXT)))) {
						GroupEARProvider element = (GroupEARProvider) getCachedContentProvider(project);
						if (element != null){
							children.add(element);	
						}
						
					}
				} catch (CoreException e) {
					String msg = "Error in the JEEContentProvider.getChildren() for parent:" +  aParentElement; //$NON-NLS-1$
					JEEUIPlugin.logError(msg, e);
				}
			}
		}
		return children.toArray();
	}

	public boolean hasChildren(Object element) {
		if (element instanceof AbstractEarNode) {
			return ((AbstractEarNode) element).getModules().size() > 0;
		} else if (element instanceof GroupEARProvider){
			return true;
		} else return false;
	}

	public Object getParent(Object object) {
		if (object instanceof AbstractEarNode){
			return ((AbstractEarNode) object).getEarProject(); 
		}
		return null;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	protected AbstractGroupProvider getNewContentProviderInstance(IProject project) {
		return new GroupEARProvider((Application) getCachedModelProvider(project).getModelObject(), (EARVirtualComponent)ComponentCore.createComponent(project));
	}

	public EarLibVisitor getVisitor() {
		return new EarLibVisitor();
	}

	protected class EarLibVisitor implements IResourceDeltaVisitor {

		private Set<IProject> projects = new HashSet<IProject>();

		public boolean visit(IResourceDelta delta) throws CoreException {
			IResourceDelta[] affectedChildren = delta.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.REMOVED | IResourceDelta.REPLACED | IResourceDelta.CHANGED);
			if (affectedChildren != null){
				for (int i = 0; i < affectedChildren.length; i++) {
					if (affectedChildren[i].getResource() != null && affectedChildren[i].getResource().getName() != null
							&& (affectedChildren[i].getResource().getName().toLowerCase().endsWith(IJ2EEModuleConstants.JAR_EXT) 
									|| (affectedChildren[i].getResource().getName().equals(IJ2EEModuleConstants.JST_COMPONENT_FILE) && affectedChildren[i].getKind()==IResourceDelta.CHANGED))){
						projects.add(affectedChildren[i].getResource().getProject());
					}
				}
			}
			return true;
		}

		protected Set<IProject> getChangedProject() {
			return projects;
		}

	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		super.resourceChanged(event);
		if (event.getType() == IResourceChangeEvent.POST_CHANGE){
			EarLibVisitor visitor = getVisitor();
			if (visitor == null){
				return;
			}
			try {
				event.getDelta().accept(visitor);
				Set<IProject> changedProject = visitor.getChangedProject();
				for (IProject project : changedProject) {
					projectChanged(project);
				}
				
			} catch (CoreException e) {
				JEEUIPlugin.logError("Could not refresh changed project.", e); //$NON-NLS-1$
			}
		}
	} 

}
