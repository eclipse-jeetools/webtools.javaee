package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
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
		List earComponents = getAllComponentsInWorkspaceOfType(component.getComponentTypeId());
		for (int i = 0; i < earComponents.size(); i++) {
			IVirtualComponent earComponent = (IVirtualComponent) earComponents.get(i);
			IVirtualReference[] references = earComponent.getReferences();
			for (int j = 0; j < references.length; j++) {
				IVirtualReference reference = references[j];
				if (reference.getReferencedComponent().getComponentTypeId().equals(component.getComponentTypeId())) {
					referencedEARComponents.add(earComponent);
				}
			}
		}
		return (IVirtualComponent[]) referencedEARComponents.toArray(new IVirtualComponent[referencedEARComponents.size()]);
	}

	public static boolean isStandaloneComponent(IVirtualComponent component) {
		List earComponents = getAllComponentsInWorkspaceOfType(component.getComponentTypeId());
		for (int i = 0; i < earComponents.size(); i++) {
			IVirtualComponent earComponent = (IVirtualComponent) earComponents.get(i);
			IVirtualReference[] references = earComponent.getReferences();
			for (int j = 0; j < references.length; j++) {
				IVirtualReference reference = references[j];
				if (reference.getReferencedComponent().getComponentTypeId().equals(component.getComponentTypeId()))
					return false;
			}
		}
		return true;
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
