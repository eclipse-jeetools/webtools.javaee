package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentLoadStrategyImpl;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEComponentUtilities extends ComponentUtilities {

	public J2EEComponentUtilities() {
		super();
	}

	public static IVirtualComponent[] getReferencingEARComponents(IVirtualComponent component) {
		List result = new ArrayList();
		IVirtualComponent[] refComponents = component.getReferencingComponents();
		for (int i=0; i<refComponents.length; i++) {
			if (refComponents[i].getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE))
				result.add(refComponents[i]);
		}
		return (IVirtualComponent[]) result.toArray(new IVirtualComponent[result.size()]);
	}

	public static boolean isStandaloneComponent(IVirtualComponent component) {
		if (getReferencingEARComponents(component).length>0)
			return false;
		return true;
	}
	
	public static IVirtualComponent getJ2EEComponentForEAR(Application application, Module module) {
		IVirtualComponent earComponent = ComponentUtilities.findComponent(application);
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForWrite(earComponent);
			IVirtualReference[] refs = edit.getJ2EEModuleReferences();
			for(int i = 0; i < refs.length; i++) {
				 if(refs[i].getReferencedComponent().getName().equals(module.getUri()))
							 return refs[i].getReferencedComponent();
			}
		} finally {
			if(edit != null) {
				edit.dispose();
			}
		}
		return null;
	}
	
	public static Archive asArchive(String jarUri, IVirtualComponent component, boolean exportSource) throws OpenFailureException {
		JavaComponentLoadStrategyImpl strat = new JavaComponentLoadStrategyImpl(component);
		strat.setExportSource(exportSource);
		return CommonarchiveFactoryImpl.getActiveFactory().primOpenArchive(strat, jarUri);
	}
	
	public static boolean isWebComponent(IVirtualComponent component) {
		return component.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE);
	}
	
	public static boolean isEARComponent(IVirtualComponent component) {
		return component.getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE);
	}
	
	public static boolean isStandaloneWebComponent(IVirtualComponent component) {
		return (component.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE) && isStandaloneComponent(component));	
	}
}
