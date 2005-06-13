package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEComponentUtilities extends ComponentUtilities {

	public J2EEComponentUtilities() {
		super();
	}

	public static IVirtualComponent[] getReferencingEARComponents(IVirtualComponent component) {
		Set referencedEARComponents = new HashSet();
		List earComponents = getAllComponentsInWorkspaceOfType(IModuleConstants.JST_EAR_MODULE);
		for (int i = 0; i < earComponents.size(); i++) {
			IVirtualComponent earComponent = (IVirtualComponent) earComponents.get(i);
			IVirtualReference[] references = earComponent.getReferences();
			for (int j = 0; j < references.length; j++) {
				IVirtualReference reference = references[j];
				if (reference.getReferencedComponent().getName().equals(component.getName())) {
					referencedEARComponents.add(earComponent);
				}
			}
		}
		return (IVirtualComponent[]) referencedEARComponents.toArray(new IVirtualComponent[referencedEARComponents.size()]);
	}

	public static boolean isStandaloneComponent(IVirtualComponent component) {
		List earComponents = getAllComponentsInWorkspaceOfType(IModuleConstants.JST_EAR_MODULE);
		for (int i = 0; i < earComponents.size(); i++) {
			IVirtualComponent earComponent = (IVirtualComponent) earComponents.get(i);
			IVirtualReference[] references = earComponent.getReferences();
			for (int j = 0; j < references.length; j++) {
				IVirtualReference reference = references[j];
				if (reference.getReferencedComponent().getName().equals(component.getName()))
					return false;
			}
		}
		return true;
	}
	
	public static Archive asArchive(String jarUri, IVirtualComponent component, boolean exportSource) {
		/*
		ComponentLoadStrategyImpl strat = new ComponentLoadStrategyImpl(component);
		strat.setExportSource(exportSource);
		return CommonarchiveFactoryImpl.getActiveFactory().primOpenArchive(strat, jarUri); */
		return null;
	}
	
	public static boolean isWebComponent(IVirtualComponent component) {
		return component.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE);
	}
	
	public static boolean isStandaloneWebComponent(IVirtualComponent component) {
		return (component.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE) && isStandaloneComponent(component));	
	}

	public static List getAllComponentsInWorkspaceOfType(String type) {
		List earComponents = new ArrayList();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < projects.length; i++) {
			IFlexibleProject flexProject = ComponentCore.createFlexibleProject(projects[i]);
			IVirtualComponent[] components = flexProject.getComponents();
			for (int j = 0; j < components.length; j++) {
				if (components[j].getComponentTypeId().equals(type))
					earComponents.add(components[j]);
			}
		}
		return earComponents;
	}

}
