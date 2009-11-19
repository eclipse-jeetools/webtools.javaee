package org.eclipse.jst.j2ee.internal.componentcore;

import java.util.List;

import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.IModuleHandler;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JavaEEModuleHandler implements IModuleHandler {

	public String getArchiveName(IVirtualComponent comp) {
		return JavaEEProjectUtilities.getComponentURI(comp);
	}

	public List<IVirtualComponent> getFilteredListForAdd(IVirtualComponent sourceComponent, IVirtualComponent[] availableComponents) {
		// TODO Auto-generated method stub
		return null;
	}

}
