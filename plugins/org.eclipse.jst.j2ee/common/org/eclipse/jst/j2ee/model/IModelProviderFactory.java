package org.eclipse.jst.j2ee.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public interface IModelProviderFactory {
	
	IModelProvider create (IProject aProject);
	IModelProvider create (IVirtualComponent aComponent);

}
