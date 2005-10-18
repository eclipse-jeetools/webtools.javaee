package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.core.IJ2EEModule;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.internal.Module;

public class FlexibleProjectServerUtil {
	
	public static final String J2EE_EJB_ID = "j2ee.ejb"; //$NON-NLS-1$
	public static final String J2EE_WEB_ID = "j2ee.web"; //$NON-NLS-1$
	public static final String J2EE_EAR_ID = "j2ee.ear"; //$NON-NLS-1$


	public static IJ2EEModule getModuleDelegate(WorkbenchComponent component) {
		IModule[] modules  = getModules(component);
		for (int i = 0; i < modules.length; i++) {
			Module module = (Module) modules[i];
			if (module.getName().equals(component.getName()))
				return getModuleDelegate(module);
		}

		return null;
	}
	
	public static IModule getModule(IVirtualComponent component) {
		IModule[] modules  = getModules(component);
		for (int i = 0; i < modules.length; i++) {
			Module module = (Module) modules[i];
			if (module.getName().equals(component.getName()))
				return module;
		}

		return null;
	}

	public static IJ2EEModule getModuleDelegate(Module module) {
		return (IJ2EEModule) module.loadAdapter(IJ2EEModule.class, null);
	}

	public static IModule getModule(WorkbenchComponent component) {
		IModule[] modules = getModules(component);
		for (int i = 0; i < modules.length; i++) {
			Module module = (Module) modules[i];
			if (module.getName().equals(component.getName()))
				return module;
		}

		return null;
	}

	protected static IModule[] getModules(WorkbenchComponent component) {
		IProject project = StructureEdit.getContainingProject(component);
		if (project == null)
			return null;
		IModule[] modules = ServerUtil.getModules(project);
		return modules;
	}
	
	protected static IModule[] getModules(IVirtualComponent component) {
		IProject project = component.getProject();
		if (project == null)
			return null;
		IModule[] modules = ServerUtil.getModules(project);
		return modules;
	}


	public static String convertJSTVersions(String id) {
		if (id.equals(J2EEProjectUtilities.EJB))
			return J2EE_EJB_ID;
		else if (id.equals(J2EEProjectUtilities.DYNAMIC_WEB))
			return J2EE_WEB_ID;
		else if (id.equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION))
			return J2EE_EAR_ID;
		return ""; //$NON-NLS-1$
	}

}
